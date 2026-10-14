package com.zacharyjeremy.veilfall.block;
import com.zacharyjeremy.veilfall.registry.ModBlocks;
import com.zacharyjeremy.veilfall.registry.ModItems;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.server.level.ServerLevel;
public final class VeilRestorationTableBlock extends Block {
 public VeilRestorationTableBlock(Properties properties){super(properties);}
 @Override public InteractionResult use(BlockState state,Level level,BlockPos pos,Player player,InteractionHand hand,BlockHitResult hit){
  ItemStack held=player.getItemInHand(hand);
  if(!held.is(ModItems.VEIL_CRYSTAL.get()))return InteractionResult.PASS;
  if(level.isClientSide)return InteractionResult.SUCCESS;
  int restored=0;
  for(BlockPos target:BlockPos.betweenClosed(pos.offset(-4,-1,-4),pos.offset(4,1,4))){
   if(level.getBlockState(target).is(ModBlocks.CORRUPTED_GROUND.get())){
    level.setBlock(target,Blocks.DIRT.defaultBlockState(),3);restored++;
   }
  }
  if(restored==0){player.displayClientMessage(net.minecraft.network.chat.Component.literal("§7The table cannot find corruption nearby."),true);return InteractionResult.FAIL;}
  if(!player.getAbilities().instabuild)held.shrink(1);
  if(level instanceof ServerLevel server){
   CorruptionSavedData.get(server).addCorruption(-Math.max(5,Math.min(25,restored/2)));
   server.sendParticles(ParticleTypes.END_ROD,pos.getX()+0.5,pos.getY()+1.0,pos.getZ()+0.5,36,1.2,0.8,1.2,0.04);
   server.playSound(null,pos,SoundEvents.AMETHYST_BLOCK_CHIME,SoundSource.BLOCKS,1.0F,0.65F+server.random.nextFloat()*0.2F);
  }
  player.displayClientMessage(net.minecraft.network.chat.Component.literal("§bThe Veil Crystal restores "+restored+" forgotten blocks."),true);
  return InteractionResult.CONSUME;
 }
}