package com.zacharyjeremy.veilfall.registry;

import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.world.CorruptedGroundBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Veilfall.MOD_ID);

    public static final RegistryObject<Block> CORRUPTED_GROUND = BLOCKS.register(
            "corrupted_ground",
            () -> new CorruptedGroundBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(1.5F, 6.0F)
                    .randomTicks()
                    .lightLevel(state -> 2))
    );

    private ModBlocks() {}
}
