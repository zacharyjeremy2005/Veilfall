package com.zacharyjeremy.veilfall.config;
import net.minecraftforge.common.ForgeConfigSpec;
public final class VeilfallConfig {
 public static final ForgeConfigSpec CLIENT_SPEC;
 public static final ForgeConfigSpec.BooleanValue HUD_ENABLED;
 public static final ForgeConfigSpec.IntValue HUD_SCALE;
 public static final ForgeConfigSpec.BooleanValue VISUAL_EFFECTS;
 static {
  ForgeConfigSpec.Builder b=new ForgeConfigSpec.Builder();b.push("corruption_hud");
  HUD_ENABLED=b.comment("Show or hide the Veilfall corruption meter.").define("enabled",true);
  HUD_SCALE=b.comment("HUD size percentage: 50 compact, 100 normal, 200 large.").defineInRange("scale_percent",100,50,200);
  VISUAL_EFFECTS=b.comment("Enable Veilfall fog tint and airborne corruption particles.").define("phase_visual_effects",true);
  b.pop();CLIENT_SPEC=b.build();
 }
 private VeilfallConfig(){}
}