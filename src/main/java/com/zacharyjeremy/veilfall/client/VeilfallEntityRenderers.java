package com.zacharyjeremy.veilfall.client;
import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.registry.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid=Veilfall.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD,value=Dist.CLIENT)
public final class VeilfallEntityRenderers {
 private VeilfallEntityRenderers(){}
 @SubscribeEvent public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
  event.registerEntityRenderer(ModEntities.VEIL_STALKER.get(),ctx->new VeilfallZombieRenderer(ctx,"veil_stalker"));
  event.registerEntityRenderer(ModEntities.ROTWALKER.get(),ctx->new VeilfallZombieRenderer(ctx,"rotwalker"));
  event.registerEntityRenderer(ModEntities.ASHEN_HUSK.get(),ctx->new VeilfallZombieRenderer(ctx,"ashen_husk"));
  event.registerEntityRenderer(ModEntities.BLIGHT_CRAWLER.get(),ctx->new VeilfallZombieRenderer(ctx,"blight_crawler"));
  event.registerEntityRenderer(ModEntities.HOLLOW_SENTINEL.get(),ctx->new VeilfallZombieRenderer(ctx,"hollow_sentinel"));
  event.registerEntityRenderer(ModEntities.RIFTBOUND.get(),ctx->new VeilfallZombieRenderer(ctx,"riftbound"));
  event.registerEntityRenderer(ModEntities.FLESH_MARIONETTE.get(),ctx->new VeilfallZombieRenderer(ctx,"flesh_marionette"));
  event.registerEntityRenderer(ModEntities.DREAD_WALKER.get(),ctx->new VeilfallZombieRenderer(ctx,"dread_walker"));
  event.registerEntityRenderer(ModEntities.VEIL_THRALL.get(),ctx->new VeilfallZombieRenderer(ctx,"veil_thrall"));
  event.registerEntityRenderer(ModEntities.CORRUPTION_BRUTE.get(),ctx->new VeilfallZombieRenderer(ctx,"corruption_brute"));
  event.registerEntityRenderer(ModEntities.THE_UNMAKING.get(),ctx->new VeilfallZombieRenderer(ctx,"the_unmaking"));
 }
}