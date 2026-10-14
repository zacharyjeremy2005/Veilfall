package com.zacharyjeremy.veilfall.registry;
import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.item.*;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public final class ModItems {
 public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS,Veilfall.MOD_ID);
 public static final RegistryObject<Item> CORRUPTED_GROUND_ITEM=blockItem("corrupted_ground",ModBlocks.CORRUPTED_GROUND);
 public static final RegistryObject<Item> FORGOTTEN_CONCRETE_ITEM=blockItem("forgotten_concrete",ModBlocks.FORGOTTEN_CONCRETE);
 public static final RegistryObject<Item> VEILITE_ORE_ITEM=blockItem("veilite_ore",ModBlocks.VEILITE_ORE);
 public static final RegistryObject<Item> DEEPSLATE_VEILITE_ORE_ITEM=blockItem("deepslate_veilite_ore",ModBlocks.DEEPSLATE_VEILITE_ORE);
 public static final RegistryObject<Item> VEIL_RESTORATION_TABLE_ITEM=blockItem("veil_restoration_table",ModBlocks.VEIL_RESTORATION_TABLE);
 public static final RegistryObject<Item> PURIFICATION_SHARD=ITEMS.register("purification_shard",()->new PurificationShardItem(new Item.Properties().stacksTo(16)));
 public static final RegistryObject<Item> RAW_VEILITE=ITEMS.register("raw_veilite",()->new Item(new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_INGOT=ITEMS.register("veilite_ingot",()->new Item(new Item.Properties()));
 public static final RegistryObject<Item> VEIL_CRYSTAL=ITEMS.register("veil_crystal",()->new Item(new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_UPGRADE_TEMPLATE=ITEMS.register("veilite_upgrade_template",()->new Item(new Item.Properties()));
 public static final RegistryObject<Item> VEILBLADE=ITEMS.register("veilblade",()->new SwordItem(Tiers.DIAMOND,5,-2.4F,new Item.Properties()));
 public static final RegistryObject<Item> CORRUPTION_AXE=ITEMS.register("corruption_axe",()->new AxeItem(Tiers.DIAMOND,5.0F,-3.1F,new Item.Properties()));
 public static final RegistryObject<Item> RIFT_PICKAXE=ITEMS.register("rift_pickaxe",()->new PickaxeItem(Tiers.DIAMOND,1,-2.8F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_SWORD=ITEMS.register("veilite_sword",()->new SwordItem(VeiliteTier.INSTANCE,8,-2.3F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_PICKAXE=ITEMS.register("veilite_pickaxe",()->new PickaxeItem(VeiliteTier.INSTANCE,3,-2.6F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_AXE=ITEMS.register("veilite_axe",()->new AxeItem(VeiliteTier.INSTANCE,9.0F,-3.0F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_SHOVEL=ITEMS.register("veilite_shovel",()->new ShovelItem(VeiliteTier.INSTANCE,4.0F,-3.0F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_HOE=ITEMS.register("veilite_hoe",()->new HoeItem(VeiliteTier.INSTANCE,1,-2.0F,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_HELMET=ITEMS.register("veilite_helmet",()->new ArmorItem(VeiliteArmorMaterial.INSTANCE,ArmorItem.Type.HELMET,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_CHESTPLATE=ITEMS.register("veilite_chestplate",()->new ArmorItem(VeiliteArmorMaterial.INSTANCE,ArmorItem.Type.CHESTPLATE,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_LEGGINGS=ITEMS.register("veilite_leggings",()->new ArmorItem(VeiliteArmorMaterial.INSTANCE,ArmorItem.Type.LEGGINGS,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_BOOTS=ITEMS.register("veilite_boots",()->new ArmorItem(VeiliteArmorMaterial.INSTANCE,ArmorItem.Type.BOOTS,new Item.Properties()));
 public static final RegistryObject<Item> VEILITE_BOW=ITEMS.register("veilite_bow",()->new BowItem(new Item.Properties().durability(1800)));
 public static final RegistryObject<Item> VEILITE_CROSSBOW=ITEMS.register("veilite_crossbow",()->new CrossbowItem(new Item.Properties().durability(1800)));
 public static final RegistryObject<Item> VEIL_STALKER_SPAWN_EGG=egg("veil_stalker",ModEntities.VEIL_STALKER,0x241C2B,0xA42B71);
 public static final RegistryObject<Item> ROTWALKER_SPAWN_EGG=egg("rotwalker",ModEntities.ROTWALKER,0x51423C,0xA2A35A);
 public static final RegistryObject<Item> ASHEN_HUSK_SPAWN_EGG=egg("ashen_husk",ModEntities.ASHEN_HUSK,0x777777,0x3B3B3B);
 public static final RegistryObject<Item> BLIGHT_CRAWLER_SPAWN_EGG=egg("blight_crawler",ModEntities.BLIGHT_CRAWLER,0x263F2A,0x8CCB42);
 public static final RegistryObject<Item> HOLLOW_SENTINEL_SPAWN_EGG=egg("hollow_sentinel",ModEntities.HOLLOW_SENTINEL,0x252536,0x5E67A5);
 public static final RegistryObject<Item> RIFTBOUND_SPAWN_EGG=egg("riftbound",ModEntities.RIFTBOUND,0x271B42,0xC65AFF);
 public static final RegistryObject<Item> FLESH_MARIONETTE_SPAWN_EGG=egg("flesh_marionette",ModEntities.FLESH_MARIONETTE,0x713D42,0xD3A0A0);
 public static final RegistryObject<Item> DREAD_WALKER_SPAWN_EGG=egg("dread_walker",ModEntities.DREAD_WALKER,0x141414,0x7C1D2B);
 public static final RegistryObject<Item> VEIL_THRALL_SPAWN_EGG=egg("veil_thrall",ModEntities.VEIL_THRALL,0x45405B,0xA58BE8);
 public static final RegistryObject<Item> CORRUPTION_BRUTE_SPAWN_EGG=egg("corruption_brute",ModEntities.CORRUPTION_BRUTE,0x3B2028,0xC12C42);
 public static final RegistryObject<Item> THE_UNMAKING_SPAWN_EGG=egg("the_unmaking",ModEntities.THE_UNMAKING,0x09090D,0xFF1744);
 private static RegistryObject<Item> blockItem(String name,RegistryObject<net.minecraft.world.level.block.Block> block){return ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));}
 private static <T extends net.minecraft.world.entity.Mob> RegistryObject<Item> egg(String name,RegistryObject<net.minecraft.world.entity.EntityType<T>> type,int background,int spots){return ITEMS.register(name+"_spawn_egg",()->new SpawnEggItem(type.get(),background,spots,new Item.Properties()));}
 private ModItems(){}
}