package com.zacharyjeremy.veilfall.client;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
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
  int c=ClientCorruptionState.get();
  if(c>=10 && mc.level.random.nextFloat()<0.025F){
   double x=mc.player.getX(), y=mc.player.getY(), z=mc.player.getZ();
   mc.level.addParticle(ParticleTypes.REVERSE_PORTAL, x+(mc.level.random.nextDouble()-0.5D)*24.0D, y+10.0D+mc.level.random.nextDouble()*6.0D, z+(mc.level.random.nextDouble()-0.5D)*24.0D, 0.0D, -0.01D, 0.0D);
  }
  if(c<10)return;
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

 @SubscribeEvent public static void renderVeilSun(RenderLevelStageEvent event){
  if(event.getStage()!=RenderLevelStageEvent.Stage.AFTER_SKY||!VeilfallConfig.VISUAL_EFFECTS.get())return;
  Minecraft mc=Minecraft.getInstance();
  if(mc.level==null||mc.player==null)return;
  int corruption=ClientCorruptionState.get();
  if(corruption<10)return;
  float radius=0.035F+corruption*0.0028F;
  float alpha=Math.min(0.96F,0.38F+corruption/125.0F);
  // A drifting celestial silhouette replaces the sun as the Veil consumes the sky.
  float solarAngle=mc.level.getTimeOfDay(1.0F)*(float)(Math.PI*2.0D);
  float centerX=(float)Math.sin(solarAngle)*0.42F;
  float centerY=(float)Math.cos(solarAngle)*0.27F;
  PoseStack pose=event.getPoseStack();
  pose.pushPose();
  pose.mulPose(mc.getEntityRenderDispatcher().cameraOrientation());
  RenderSystem.enableBlend();
  RenderSystem.defaultBlendFunc();
  RenderSystem.setShader(GameRenderer::getPositionColorShader);
  RenderSystem.disableDepthTest();
  Matrix4f matrix=pose.last().pose();
  float core=radius*0.72F;
  // Dark disk with a thin, dim violet accretion ring. This is a sky-space effect, not a world light source.
  RenderSystem.setShaderColor(1.0F,1.0F,1.0F,alpha);
  com.mojang.blaze3d.vertex.Tesselator tess=com.mojang.blaze3d.vertex.Tesselator.getInstance();
  com.mojang.blaze3d.vertex.BufferBuilder buffer=tess.getBuilder();
  buffer.begin(com.mojang.blaze3d.vertex.VertexFormat.Mode.TRIANGLE_FAN, com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR);
  buffer.vertex(matrix,centerX,centerY,0.0F).color(2,1,7,(int)(255*alpha)).endVertex();
  for(int i=0;i<=64;i++){
   double a=Math.PI*2.0*i/64.0;
   buffer.vertex(matrix,centerX+(float)Math.cos(a)*core,centerY+(float)Math.sin(a)*core,0.0F).color(2,1,7,(int)(255*alpha)).endVertex();
  }
  tess.end();
  buffer.begin(com.mojang.blaze3d.vertex.VertexFormat.Mode.LINE_STRIP,com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR);
  for(int i=0;i<=96;i++){
   double a=Math.PI*2.0*i/96.0;
   buffer.vertex(matrix,centerX+(float)Math.cos(a)*radius,centerY+(float)Math.sin(a)*radius,-0.001F).color(145,48,190,(int)(190*alpha)).endVertex();
  }
  tess.end();
  // A second broken ring and a thin hot edge give the event-horizon silhouette depth.
  buffer.begin(com.mojang.blaze3d.vertex.VertexFormat.Mode.LINE_STRIP,com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR);
  for(int i=0;i<=96;i++){
   double a=Math.PI*2.0*i/96.0;
   float wobble=radius*(1.16F+0.035F*(float)Math.sin(a*7.0+solarAngle));
   buffer.vertex(matrix,centerX+(float)Math.cos(a)*wobble,centerY+(float)Math.sin(a)*wobble,-0.002F)
     .color(70,22,111,(int)(115*alpha)).endVertex();
  }
  tess.end();
  buffer.begin(com.mojang.blaze3d.vertex.VertexFormat.Mode.LINE_STRIP,com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR);
  for(int i=0;i<=64;i++){
   double a=Math.PI*2.0*i/64.0;
   buffer.vertex(matrix,centerX+(float)Math.cos(a)*radius*0.82F,centerY+(float)Math.sin(a)*radius*0.82F,-0.003F)
     .color(12,5,19,(int)(255*alpha)).endVertex();
  }
  tess.end();
  RenderSystem.enableDepthTest();
  RenderSystem.disableBlend();
  RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
  pose.popPose();
 }

 private static float lerp(float a,float b,float t){return a+(b-a)*Math.max(0.0F,Math.min(1.0F,t));}
}