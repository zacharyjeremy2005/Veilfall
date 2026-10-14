package com.zacharyjeremy.veilfall.registry;
import com.zacharyjeremy.veilfall.Veilfall;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
public final class ModCreativeTabs {
 public static final DeferredRegister<CreativeModeTab> TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,Veilfall.MOD_ID);
 public static final RegistryObject<CreativeModeTab> VEILFALL_TAB=TABS.register("veilfall",()->CreativeModeTab.builder()
  .title(Component.translatable("itemGroup.veilfall")).icon(()->new ItemStack(ModItems.VEILITE_INGOT.get()))
  .displayItems((parameters,output)->{
   output.accept(ModItems.CORRUPTED_GROUND_ITEM.get());output.accept(ModItems.FORGOTTEN_CONCRETE_ITEM.get());output.accept(ModItems.VEILITE_ORE_ITEM.get());output.accept(ModItems.DEEPSLATE_VEILITE_ORE_ITEM.get());output.accept(ModItems.VEIL_RESTORATION_TABLE_ITEM.get());
   output.accept(ModItems.PURIFICATION_SHARD.get());output.accept(ModItems.VEIL_CRYSTAL.get());output.accept(ModItems.RAW_VEILITE.get());output.accept(ModItems.VEILITE_INGOT.get());output.accept(ModItems.VEILITE_UPGRADE_TEMPLATE.get());
   output.accept(ModItems.VEILBLADE.get());output.accept(ModItems.CORRUPTION_AXE.get());output.accept(ModItems.RIFT_PICKAXE.get());
   output.accept(ModItems.VEILITE_SWORD.get());output.accept(ModItems.VEILITE_PICKAXE.get());output.accept(ModItems.VEILITE_AXE.get());output.accept(ModItems.VEILITE_SHOVEL.get());output.accept(ModItems.VEILITE_HOE.get());
   output.accept(ModItems.VEILITE_HELMET.get());output.accept(ModItems.VEILITE_CHESTPLATE.get());output.accept(ModItems.VEILITE_LEGGINGS.get());output.accept(ModItems.VEILITE_BOOTS.get());output.accept(ModItems.VEILITE_BOW.get());output.accept(ModItems.VEILITE_CROSSBOW.get());
   output.accept(ModItems.VEIL_STALKER_SPAWN_EGG.get());output.accept(ModItems.ROTWALKER_SPAWN_EGG.get());output.accept(ModItems.ASHEN_HUSK_SPAWN_EGG.get());output.accept(ModItems.BLIGHT_CRAWLER_SPAWN_EGG.get());output.accept(ModItems.HOLLOW_SENTINEL_SPAWN_EGG.get());output.accept(ModItems.RIFTBOUND_SPAWN_EGG.get());output.accept(ModItems.FLESH_MARIONETTE_SPAWN_EGG.get());output.accept(ModItems.DREAD_WALKER_SPAWN_EGG.get());output.accept(ModItems.VEIL_THRALL_SPAWN_EGG.get());output.accept(ModItems.CORRUPTION_BRUTE_SPAWN_EGG.get());output.accept(ModItems.THE_UNMAKING_SPAWN_EGG.get());
  }).build());
 private ModCreativeTabs(){}
}