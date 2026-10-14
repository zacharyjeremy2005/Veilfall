package com.zacharyjeremy.veilfall.world;

import com.zacharyjeremy.veilfall.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptedGroundBlock extends Block {
    public CorruptedGroundBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(4) != 0) {
            return;
        }

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos targetPos = pos.relative(direction);
        BlockState target = level.getBlockState(targetPos);

        if (target.is(Blocks.GRASS_BLOCK)
                || target.is(Blocks.DIRT)
                || target.is(Blocks.COARSE_DIRT)
                || target.is(Blocks.PODZOL)
                || target.is(Blocks.ROOTED_DIRT)) {
            level.setBlock(targetPos, ModBlocks.CORRUPTED_GROUND.get().defaultBlockState(), 3);
            CorruptionSavedData.get(level).addCorruption(1);
        }
    }
}
