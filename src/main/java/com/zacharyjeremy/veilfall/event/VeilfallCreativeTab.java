package com.zacharyjeremy.veilfall.event;
import com.zacharyjeremy.veilfall.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="veilfall",bus=Mod.EventBusSubscriber.Bus.MOD)
public final class VeilfallCreativeTab {
 private VeilfallCreativeTab(){}
 @SubscribeEvent public static void addItems(BuildCreativeModeTabContentsEvent event){
  if(event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)){event.accept(ModItems.CORRUPTED_GROUND_ITEM);event.accept(ModItems.FORGOTTEN_CONCRETE_ITEM);event.accept(ModItems.VEILITE_ORE_ITEM);event.accept(ModItems.DEEPSLATE_VEILITE_ORE_ITEM);}
  if(event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)){event.accept(ModItems.PURIFICATION_SHARD);event.accept(ModItems.RAW_VEILITE);event.accept(ModItems.VEILITE_INGOT);event.accept(ModItems.VEIL_CRYSTAL);event.accept(ModItems.VEILITE_UPGRADE_TEMPLATE);}
  if(event.getTabKey().equals(CreativeModeTabs.COMBAT)){event.accept(ModItems.VEILBLADE);event.accept(ModItems.CORRUPTION_AXE);event.accept(ModItems.VEILITE_SWORD);event.accept(ModItems.VEILITE_AXE);event.accept(ModItems.VEILITE_BOW);event.accept(ModItems.VEILITE_CROSSBOW);event.accept(ModItems.VEILITE_HELMET);event.accept(ModItems.VEILITE_CHESTPLATE);event.accept(ModItems.VEILITE_LEGGINGS);event.accept(ModItems.VEILITE_BOOTS);}
  if(event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)){event.accept(ModItems.RIFT_PICKAXE);event.accept(ModItems.VEILITE_PICKAXE);event.accept(ModItems.VEILITE_SHOVEL);event.accept(ModItems.VEILITE_HOE);event.accept(ModItems.VEIL_RESTORATION_TABLE_ITEM);}
 }
}