package com.zacharyjeremy.veilfall.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class VeilfallConfig {
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec.BooleanValue HUD_ENABLED;
    public static final ForgeConfigSpec.IntValue HUD_SCALE;
    public static final ForgeConfigSpec.BooleanValue VISUAL_EFFECTS;
    public static final ForgeConfigSpec.IntValue CORRUPTION_RATE;

    static {
        ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();
        client.push("corruption_hud");
        HUD_ENABLED = client.comment("Show or hide the Veilfall corruption meter.").define("enabled", true);
        HUD_SCALE = client.comment("HUD size percentage: 50 compact, 100 normal, 200 large.")
                .defineInRange("scale_percent", 100, 50, 200);
        VISUAL_EFFECTS = client.comment("Enable Veilfall fog tint and airborne corruption particles.")
                .define("phase_visual_effects", true);
        client.pop();
        CLIENT_SPEC = client.build();

        ForgeConfigSpec.Builder server = new ForgeConfigSpec.Builder();
        server.push("corruption");
        CORRUPTION_RATE = server.comment(
                "Corruption speed multiplier: 1=normal, 5=five times faster, 10=ten times faster.")
                .defineInRange("rate", 1, 1, 10);
        server.pop();
        SERVER_SPEC = server.build();
    }

    private VeilfallConfig() {}
}
