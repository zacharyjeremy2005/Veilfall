package com.zacharyjeremy.veilfall.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "veilfall", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class CorruptionHud {
    private CorruptionHud() {}

    @SubscribeEvent
    public static void render(RenderGuiOverlayEvent.Post event) {
        if (!event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.options.hideGui) {
            return;
        }

        int value = ClientCorruptionState.get();
        int x = 10;
        int y = 10;
        int width = 120;
        int filled = width * value / 100;
        event.getGuiGraphics().drawString(minecraft.font,
                Component.literal("VEILFALL  " + value + "%"), x, y, 0xFFE8D9FF, true);
        event.getGuiGraphics().fill(x, y + 12, x + width, y + 19, 0xFF211A2A);
        if (filled > 0) {
            int color = value < 35 ? 0xFF8E55C8 : value < 70 ? 0xFFB43D83 : 0xFFE33145;
            event.getGuiGraphics().fill(x, y + 12, x + filled, y + 19, color);
        }
        event.getGuiGraphics().fill(x, y + 12, x + width, y + 13, 0xFFDDD3E8);
    }
}
