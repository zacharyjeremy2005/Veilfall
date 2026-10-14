package com.zacharyjeremy.veilfall.network;

import com.zacharyjeremy.veilfall.Veilfall;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class VeilfallNetwork {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Veilfall.MOD_ID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );
    private static boolean registered = false;

    private VeilfallNetwork() {}

    public static void register() {
        if (registered) return;
        CHANNEL.registerMessage(0, CorruptionSyncPacket.class,
                CorruptionSyncPacket::encode,
                CorruptionSyncPacket::decode,
                CorruptionSyncPacket::handle);
        registered = true;
    }
}
