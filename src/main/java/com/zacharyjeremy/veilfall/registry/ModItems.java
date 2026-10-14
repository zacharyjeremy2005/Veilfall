package com.zacharyjeremy.veilfall.registry;

import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.item.PurificationShardItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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

    private ModItems() {}
}
