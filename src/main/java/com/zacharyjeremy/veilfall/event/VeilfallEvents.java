package com.zacharyjeremy.veilfall.event;

import com.zacharyjeremy.veilfall.network.CorruptionSyncPacket;
import com.zacharyjeremy.veilfall.network.VeilfallNetwork;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = "veilfall", bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class VeilfallEvents {
    private VeilfallEvents() {}

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            sendCurrentCorruption(player);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.getServer().getTickCount() % 100 != 0) {
            return;
        }
        int value = CorruptionSavedData.get(event.getServer().overworld()).getCorruption();
        CorruptionSyncPacket packet = new CorruptionSyncPacket(value);
        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
        }
    }

    public static void sendCurrentCorruption(ServerPlayer player) {
        int value = CorruptionSavedData.get(player.serverLevel()).getCorruption();
        VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new CorruptionSyncPacket(value));
    }
}
