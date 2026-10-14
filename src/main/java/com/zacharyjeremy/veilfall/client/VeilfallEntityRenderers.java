package com.zacharyjeremy.veilfall.client;

import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.registry.ModEntities;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Veilfall.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class VeilfallEntityRenderers {
    private VeilfallEntityRenderers() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.VEIL_STALKER.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.ROTWALKER.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.ASHEN_HUSK.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.BLIGHT_CRAWLER.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.HOLLOW_SENTINEL.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.RIFTBOUND.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.FLESH_MARIONETTE.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.DREAD_WALKER.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.VEIL_THRALL.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.CORRUPTION_BRUTE.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.THE_UNMAKING.get(), ZombieRenderer::new);
    }
}
