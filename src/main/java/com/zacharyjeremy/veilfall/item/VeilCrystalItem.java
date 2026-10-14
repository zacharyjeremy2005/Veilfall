package com.zacharyjeremy.veilfall.item;
import com.zacharyjeremy.veilfall.registry.ModBlocks;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
public class VeilCrystalItem extends Item {
 public VeilCrystalItem(Properties properties){super(properties.stacksTo(16));}
 @Override public InteractionResult useOn(UseOnContext context){
  Level level=context.getLevel();BlockPos center=context.getClickedPos();
  if(!level.getBlockState(center).is(ModBlocks.CORRUPTED_GROUND.get()))return InteractionResult.PASS;
  if(!level.isClientSide){
   int restored=0;
   for(BlockPos pos:BlockPos.betweenClosed(center.offset(-1,0,-1),center.offset(1,0,1))){
    if(level.getBlockState(pos).is(ModBlocks.CORRUPTED_GROUND.get())){level.setBlock(pos,Blocks.DIRT.defaultBlockState(),3);restored++;}
   }
   if(level instanceof ServerLevel server&&restored>0)CorruptionSavedData.get(server).addCorruption(-Math.max(2,restored*2));
   if(context.getPlayer()!=null&&!context.getPlayer().getAbilities().instabuild)context.getItemInHand().shrink(1);
  }
  return InteractionResult.sidedSuccess(level.isClientSide);
 }
}