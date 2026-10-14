package com.zacharyjeremy.veilfall.event;

import com.zacharyjeremy.veilfall.network.CorruptionSyncPacket;
import com.zacharyjeremy.veilfall.network.VeilfallNetwork;
import com.zacharyjeremy.veilfall.registry.ModBlocks;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = "veilfall", bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class VeilfallEvents {
    private static final int CORRUPTION_START_TICKS = 6000; // 5 minutes after server starts
    private static final int CORRUPTION_STEP_TICKS = 1200;  // 1 minute
    private VeilfallEvents() {}

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            sendCurrentCorruption(player);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        int tick = event.getServer().getTickCount();
        if (tick % CORRUPTION_STEP_TICKS == 0) {
            var players = event.getServer().getPlayerList().getPlayers();
            if (!players.isEmpty() && tick >= CORRUPTION_START_TICKS) {
                CorruptionSavedData data = CorruptionSavedData.get(event.getServer().overworld());
                if (data.getCorruption() == 0) {
                    // The apocalypse begins on its own after five minutes of active play.
                    data.setCorruption(1);
                    seedCorruption(players.get(0).serverLevel(), players.get(0));
                } else {
                    data.addCorruption(1);
                    if (tick % (CORRUPTION_STEP_TICKS * 2) == 0) {
                        seedCorruption(players.get(0).serverLevel(), players.get(0));
                    }
                }
            }
        }

        if (tick % 100 != 0) {
            return;
        }
        int value = CorruptionSavedData.get(event.getServer().overworld()).getCorruption();
        CorruptionSyncPacket packet = new CorruptionSyncPacket(value);
        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
        }
    }

    private static void seedCorruption(ServerLevel level, ServerPlayer player) {
        // Try several nearby spots so the first corrupted patch appears on natural ground.
        BlockPos origin = player.blockPosition();
        for (int attempt = 0; attempt < 24; attempt++) {
            int dx = level.random.nextInt(25) - 12;
            int dz = level.random.nextInt(25) - 12;
            if (Math.abs(dx) < 4 && Math.abs(dz) < 4) {
                continue;
            }
            BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE,
                    new BlockPos(origin.getX() + dx, origin.getY(), origin.getZ() + dz));
            var ground = level.getBlockState(surface.below());
            if ((ground.is(Blocks.GRASS_BLOCK) || ground.is(Blocks.DIRT)
                    || ground.is(Blocks.COARSE_DIRT) || ground.is(Blocks.PODZOL)
                    || ground.is(Blocks.ROOTED_DIRT))
                    && level.getBlockState(surface).isAir()) {
                level.setBlock(surface.below(), ModBlocks.CORRUPTED_GROUND.get().defaultBlockState(), 3);
                return;
            }
        }
    }

    public static void sendCurrentCorruption(ServerPlayer player) {
        int value = CorruptionSavedData.get(player.serverLevel()).getCorruption();
        VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new CorruptionSyncPacket(value));
    }
}
