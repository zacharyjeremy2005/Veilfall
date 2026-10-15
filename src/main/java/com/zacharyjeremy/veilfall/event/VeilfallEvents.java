package com.zacharyjeremy.veilfall.event;
import com.zacharyjeremy.veilfall.network.CorruptionSyncPacket;
import com.zacharyjeremy.veilfall.config.VeilfallConfig;
import com.zacharyjeremy.veilfall.network.VeilfallNetwork;
import com.zacharyjeremy.veilfall.registry.ModBlocks;
import com.zacharyjeremy.veilfall.registry.ModEntities;
import com.zacharyjeremy.veilfall.entity.VeilfallBoss;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
@Mod.EventBusSubscriber(modid="veilfall",bus=Mod.EventBusSubscriber.Bus.FORGE)
public final class VeilfallEvents {
 private static final int CORRUPTION_START_TICKS=6000;
 private static final int CORRUPTION_STEP_TICKS=1200;
 private VeilfallEvents(){}
 @SubscribeEvent public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){if(event.getEntity() instanceof ServerPlayer player)sendCurrentCorruption(player);}
 @SubscribeEvent public static void onServerTick(TickEvent.ServerTickEvent event){
  if(event.phase!=TickEvent.Phase.END)return;
  int tick=event.getServer().getTickCount();
  int rate = VeilfallConfig.CORRUPTION_RATE.get();
  int stepTicks = Math.max(1, CORRUPTION_STEP_TICKS / rate);
  int startTicks = Math.max(1, CORRUPTION_START_TICKS / rate);
  if(tick%stepTicks==0){
   var players=event.getServer().getPlayerList().getPlayers();
   if(!players.isEmpty()&&tick>=startTicks){
    ServerPlayer anchor=players.get(0);CorruptionSavedData data=CorruptionSavedData.get(event.getServer().overworld());
    if(data.getCorruption()==0){data.setCorruption(1);seedCorruption(anchor.serverLevel(),anchor);}
    else{
     int previous=data.getCorruption();data.addCorruption(1);int value=data.getCorruption();
     if(tick%(Math.max(1,stepTicks*2))==0)seedCorruption(anchor.serverLevel(),anchor);
     if(previous<20&&value>=20)announce(event.getServer().overworld(),"The Veil is stirring. Strange sounds carry on the wind...",SoundEvents.AMETHYST_BLOCK_CHIME);
     if(previous<25&&value>=25){announce(event.getServer().overworld(),"PHASE I — THE VEIL IS OPENING",SoundEvents.RESPAWN_ANCHOR_CHARGE);triggerRiftSurge(event.getServer(),1);}
     if(previous<45&&value>=45)announce(event.getServer().overworld(),"The air is splitting. Prepare for a violent change.",SoundEvents.RESPAWN_ANCHOR_CHARGE);
     if(previous<50&&value>=50){announce(event.getServer().overworld(),"PHASE II — REALITY IS FRACTURING",SoundEvents.WITHER_AMBIENT);triggerRiftSurge(event.getServer(),2);}
     if(previous<70&&value>=70)announce(event.getServer().overworld(),"Something vast is moving beneath the world...",SoundEvents.WITHER_AMBIENT);
     if(previous<75&&value>=75){announce(event.getServer().overworld(),"PHASE III — THE WORLD IS UNRAVELING",SoundEvents.WITHER_SPAWN);triggerRiftSurge(event.getServer(),3);}
     if(previous<95&&value>=95)announce(event.getServer().overworld(),"FINAL WARNING: the Veil is about to collapse.",SoundEvents.WITHER_SPAWN);
     if(previous<100&&value>=100){announce(event.getServer().overworld(),"PHASE IV — THE UNMAKING HAS BEGUN",SoundEvents.ENDER_DRAGON_GROWL);triggerRiftSurge(event.getServer(),4);}
    }
   }
  }
  if(tick%100!=0)return;
  int value=CorruptionSavedData.get(event.getServer().overworld()).getCorruption();
  CorruptionSyncPacket packet=new CorruptionSyncPacket(value);
  for(ServerPlayer player:event.getServer().getPlayerList().getPlayers())VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(()->player),packet);
 }
 private static void triggerRiftSurge(net.minecraft.server.MinecraftServer server,int phase){
  for(ServerPlayer player:server.getPlayerList().getPlayers()){
   ServerLevel level=player.serverLevel();
   if(phase==4&&!level.getEntitiesOfClass(VeilfallBoss.class,player.getBoundingBox().inflate(96.0D)).isEmpty())continue;
   int count=phase==1?1:phase==2?2:phase==3?3:1;
   for(int i=0;i<count;i++){
    EntityType<? extends Mob> type;
    if(phase==4)type=ModEntities.THE_UNMAKING.get();
    else if(phase==1)type=ModEntities.VEIL_STALKER.get();
    else if(phase==2)type=level.random.nextBoolean()?ModEntities.ROTWALKER.get():ModEntities.RIFTBOUND.get();
    else type=level.random.nextBoolean()?ModEntities.CORRUPTION_BRUTE.get():ModEntities.BLIGHT_CRAWLER.get();
    spawnOnCorruptedGround(level,player,type);
   }
  }
 }
 private static <T extends Mob> void spawnOnCorruptedGround(ServerLevel level,ServerPlayer player,EntityType<T> type){
  BlockPos origin=player.blockPosition();
  for(int attempt=0;attempt<40;attempt++){
   int dx=level.random.nextInt(33)-16,dz=level.random.nextInt(33)-16;
   BlockPos surface=level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,new BlockPos(origin.getX()+dx,origin.getY(),origin.getZ()+dz));
   if(!level.getBlockState(surface.below()).is(ModBlocks.CORRUPTED_GROUND.get())||!level.getBlockState(surface).isAir())continue;
   T mob=type.create(level);if(mob==null)return;
   mob.moveTo(surface.getX()+0.5D,surface.getY(),surface.getZ()+0.5D,level.random.nextFloat()*360.0F,0.0F);
   level.addFreshEntity(mob);return;
  }
 }
 private static void announce(ServerLevel level,String message,net.minecraft.sounds.SoundEvent sound){
  for(ServerPlayer player:level.getServer().getPlayerList().getPlayers()){
   player.displayClientMessage(Component.literal("§5[VEILFALL] §r"+message),false);
   player.playNotifySound(sound,SoundSource.AMBIENT,0.9F,0.72F+level.random.nextFloat()*0.18F);
  }
 }
 private static void seedCorruption(ServerLevel level,ServerPlayer player){
  BlockPos origin=player.blockPosition();
  for(int attempt=0;attempt<24;attempt++){
   int dx=level.random.nextInt(25)-12,dz=level.random.nextInt(25)-12;
   if(Math.abs(dx)<4&&Math.abs(dz)<4)continue;
   BlockPos surface=level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE,new BlockPos(origin.getX()+dx,origin.getY(),origin.getZ()+dz));
   var ground=level.getBlockState(surface.below());
   if((ground.is(Blocks.GRASS_BLOCK)||ground.is(Blocks.DIRT)||ground.is(Blocks.COARSE_DIRT)||ground.is(Blocks.PODZOL)||ground.is(Blocks.ROOTED_DIRT))&&level.getBlockState(surface).isAir()){
    level.setBlock(surface.below(),ModBlocks.CORRUPTED_GROUND.get().defaultBlockState(),3);return;
   }
  }
 }
 public static void sendCurrentCorruption(ServerPlayer player){
  int value=CorruptionSavedData.get(player.serverLevel()).getCorruption();
  VeilfallNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(()->player),new CorruptionSyncPacket(value));
 }
}