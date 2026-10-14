package com.zacharyjeremy.veilfall.client;
import com.zacharyjeremy.veilfall.config.VeilfallConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="veilfall",bus=Mod.EventBusSubscriber.Bus.FORGE,value=Dist.CLIENT)
public final class CorruptionHud {
 private CorruptionHud(){}
 @SubscribeEvent public static void render(RenderGuiOverlayEvent.Post event){
  Minecraft mc=Minecraft.getInstance();
  if(mc.player==null||mc.options.hideGui||!VeilfallConfig.HUD_ENABLED.get())return;
  int value=ClientCorruptionState.get();var g=event.getGuiGraphics();
  int screenW=event.getWindow().getGuiScaledWidth(),screenH=event.getWindow().getGuiScaledHeight();
  if(event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())&&value>=25&&VeilfallConfig.VISUAL_EFFECTS.get()){
   int pulse=(int)(Math.sin(mc.level==null?0:mc.level.getGameTime()*0.045)*3);
   int tint=value>=75?0x12A91438:value>=50?0x0D622C82:0x086F42A0;
   if(value>=75&&pulse>0)tint=0x18C21B3A;
   g.fill(0,0,screenW,screenH,tint);
  }
  if(!event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id()))return;
  int scale=VeilfallConfig.HUD_SCALE.get(),width=144;
  Component label=Component.translatable("hud.veilfall.label",value);
  int x=(screenW-width*scale/100)/2,y=6;
  g.pose().pushPose();g.pose().translate(x,y,0);g.pose().scale(scale/100.0F,scale/100.0F,1);
  int labelWidth=mc.font.width(label);
  g.fill(-5,-4,width+5,34,0xA5100C19);g.fill(-5,-4,width+5,-2,0xFF9D68BE);
  g.drawString(mc.font,label,(width-labelWidth)/2,0,0xFFEDE2FF,true);
  g.fill(0,12,width,19,0xFF211A2A);
  int filled=width*value/100;
  int barColor=value<25?0xFF7D69B8:value<50?0xFF9A4FD1:value<75?0xFFC13D91:0xFFE33145;
  if(filled>0)g.fill(0,12,filled,19,barColor);
  g.fill(0,12,width,13,0xFFDDD3E8);
  Component phase=phaseName(value);int phaseWidth=mc.font.width(phase);
  g.drawString(mc.font,phase,(width-phaseWidth)/2,23,value>=75?0xFFFF899B:0xFFCEBCE8,false);
  g.pose().popPose();
 }
 private static Component phaseName(int value){
  if(value>=100)return Component.translatable("hud.veilfall.phase_unmaking");
  if(value>=75)return Component.translatable("hud.veilfall.phase_unraveling");
  if(value>=50)return Component.translatable("hud.veilfall.phase_fracture");
  if(value>=25)return Component.translatable("hud.veilfall.phase_breach");
  return Component.translatable("hud.veilfall.phase_dormant");
 }
}