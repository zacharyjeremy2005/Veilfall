package com.zacharyjeremy.veilfall.event;

import com.zacharyjeremy.veilfall.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "veilfall", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VeilfallCreativeTab {
    private VeilfallCreativeTab() {}

    @SubscribeEvent
    public static void addItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.accept(ModItems.CORRUPTED_GROUND_ITEM);
        }
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(ModItems.PURIFICATION_SHARD);
        }
    }
}
