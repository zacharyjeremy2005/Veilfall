package com.zacharyjeremy.veilfall.registry;

import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.item.PurificationShardItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Veilfall.MOD_ID);

    public static final RegistryObject<Item> CORRUPTED_GROUND_ITEM = ITEMS.register(
            "corrupted_ground",
            () -> new BlockItem(ModBlocks.CORRUPTED_GROUND.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> PURIFICATION_SHARD = ITEMS.register(
            "purification_shard",
            () -> new PurificationShardItem(new Item.Properties().stacksTo(16))
    );

    public static final RegistryObject<Item> VEILBLADE = ITEMS.register(
            "veilblade", () -> new SwordItem(Tiers.DIAMOND, 5, -2.4F, new Item.Properties())
    );

    public static final RegistryObject<Item> CORRUPTION_AXE = ITEMS.register(
            "corruption_axe", () -> new AxeItem(Tiers.DIAMOND, 5.0F, -3.1F, new Item.Properties())
    );

    public static final RegistryObject<Item> RIFT_PICKAXE = ITEMS.register(
            "rift_pickaxe", () -> new PickaxeItem(Tiers.DIAMOND, 1, -2.8F, new Item.Properties())
    );

    private ModItems() {}
}
