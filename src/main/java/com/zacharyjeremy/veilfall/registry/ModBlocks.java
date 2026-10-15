package com.zacharyjeremy.veilfall.registry;
import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.world.CorruptedGroundBlock;
import com.zacharyjeremy.veilfall.block.VeilRestorationTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public final class ModBlocks {
 private static final AbstractTreeGrower VEIL_TREE_GROWER = new AbstractTreeGrower() {
  @Override protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) { return TreeFeatures.DARK_OAK; }
 };
 public static final DeferredRegister<Block> BLOCKS=DeferredRegister.create(ForgeRegistries.BLOCKS,Veilfall.MOD_ID);
 public static final RegistryObject<Block> CORRUPTED_GROUND=BLOCKS.register("corrupted_ground",()->new CorruptedGroundBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).strength(1.5F,6.0F).randomTicks().lightLevel(s->2)));
 public static final RegistryObject<Block> FORGOTTEN_CONCRETE=BLOCKS.register("forgotten_concrete",()->new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).strength(2.0F,6.0F)));
 public static final RegistryObject<Block> VEILITE_ORE=BLOCKS.register("veilite_ore",()->new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).strength(5.0F,9.0F).requiresCorrectToolForDrops()));
 public static final RegistryObject<Block> DEEPSLATE_VEILITE_ORE=BLOCKS.register("deepslate_veilite_ore",()->new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).strength(5.5F,9.0F).requiresCorrectToolForDrops()));
 public static final RegistryObject<Block> VEIL_LOG=BLOCKS.register("veil_log",()->new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG).strength(3.0F,5.0F)));
 public static final RegistryObject<Block> STRIPPED_VEIL_LOG=BLOCKS.register("stripped_veil_log",()->new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG).strength(3.0F,5.0F)));
 public static final RegistryObject<Block> VEIL_PLANKS=BLOCKS.register("veil_planks",()->new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS).strength(2.2F,3.0F)));
 public static final RegistryObject<Block> VEIL_LEAVES=BLOCKS.register("veil_leaves",()->new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).strength(0.2F).noOcclusion().randomTicks()));
 public static final RegistryObject<Block> VEIL_SAPLING=BLOCKS.register("veil_sapling",()->new SaplingBlock(VEIL_TREE_GROWER, BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SAPLING).noOcclusion().randomTicks()));
 public static final RegistryObject<Block> VEIL_RESTORATION_TABLE=BLOCKS.register("veil_restoration_table",()->new VeilRestorationTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE).strength(2.5F)));
 private ModBlocks(){}
}