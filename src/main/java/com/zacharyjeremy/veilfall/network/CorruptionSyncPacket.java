package com.zacharyjeremy.veilfall.network;

import com.zacharyjeremy.veilfall.client.ClientCorruptionState;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class CorruptionSyncPacket {
    private final int corruption;

    public CorruptionSyncPacket(int corruption) {
        this.corruption = Math.max(0, Math.min(100, corruption));
    }

    public static void encode(CorruptionSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeVarInt(packet.corruption);
    }

    public static CorruptionSyncPacket decode(FriendlyByteBuf buffer) {
        return new CorruptionSyncPacket(buffer.readVarInt());
    }

    public static void handle(CorruptionSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> ClientCorruptionState.set(packet.corruption)));
        context.setPacketHandled(true);
    }
}
