package com.zacharyjeremy.veilfall.client;
import net.minecraft.client.Minecraft;
import com.zacharyjeremy.veilfall.config.VeilfallConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="veilfall",bus=Mod.EventBusSubscriber.Bus.FORGE,value=Dist.CLIENT)
public final class VeilfallVisualEffects {
 private VeilfallVisualEffects(){}
 @SubscribeEvent public static void fogColors(ViewportEvent.ComputeFogColor event){
  if(!VeilfallConfig.VISUAL_EFFECTS.get())return;
  Minecraft mc=Minecraft.getInstance();if(mc.level==null)return;
  float c=ClientCorruptionState.get()/100.0F;
  if(c<=0)return;
  float r=lerp(event.getRed(),0.34F+0.12F*c,c*0.78F);
  float g=lerp(event.getGreen(),0.17F-0.07F*c,c*0.72F);
  float b=lerp(event.getBlue(),0.42F-0.13F*c,c*0.74F);
  if(c>=0.75F){r=lerp(r,0.38F,0.45F);g=lerp(g,0.055F,0.5F);b=lerp(b,0.16F,0.5F);}
  event.setRed(r);event.setGreen(Math.max(0.025F,g));event.setBlue(Math.max(0.04F,b));
 }
 @SubscribeEvent public static void ambientParticles(TickEvent.ClientTickEvent event){
  if(event.phase!=TickEvent.Phase.END||!VeilfallConfig.VISUAL_EFFECTS.get())return;
  Minecraft mc=Minecraft.getInstance();if(mc.level==null||mc.player==null)return;
  int c=ClientCorruptionState.get();\n  if(c>=10 && mc.level.random.nextFloat()<0.025F){\n   double x=mc.player.getX(), y=mc.player.getY(), z=mc.player.getZ();\n   mc.level.addParticle(ParticleTypes.REVERSE_PORTAL, x+(mc.level.random.nextDouble()-0.5D)*24.0D, y+10.0D+mc.level.random.nextDouble()*6.0D, z+(mc.level.random.nextDouble()-0.5D)*24.0D, 0.0D, -0.01D, 0.0D);\n  }\n  if(c<10)return;
  double x=mc.player.getX(),y=mc.player.getY()+0.5,z=mc.player.getZ();
  if(mc.level.random.nextFloat()<(c>=75?0.28F:c>=50?0.18F:0.10F)){
   double px=x+(mc.level.random.nextDouble()-0.5D)*18.0D;
   double py=y+mc.level.random.nextDouble()*7.0D;
   double pz=z+(mc.level.random.nextDouble()-0.5D)*18.0D;
   if(c>=75)mc.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME,px,py,pz,0.0D,0.012D,0.0D);
   else if(c>=50)mc.level.addParticle(ParticleTypes.REVERSE_PORTAL,px,py,pz,0.0D,0.015D,0.0D);
   else mc.level.addParticle(ParticleTypes.ASH,px,py,pz,0.0D,0.008D,0.0D);
  }
  if(c>=25&&mc.level.random.nextFloat()<0.035F){
   mc.level.addParticle(ParticleTypes.PORTAL,x+(mc.level.random.nextDouble()-0.5D)*3.0D,y+mc.level.random.nextDouble()*2.5D,z+(mc.level.random.nextDouble()-0.5D)*3.0D,0.0D,0.02D,0.0D);
  }
 }
 private static float lerp(float a,float b,float t){return a+(b-a)*Math.max(0.0F,Math.min(1.0F,t));}
}