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
  if(!event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id()))return;
  Minecraft mc=Minecraft.getInstance();
  if(mc.player==null||mc.options.hideGui||!VeilfallConfig.HUD_ENABLED.get())return;
  int value=ClientCorruptionState.get(),scale=VeilfallConfig.HUD_SCALE.get(),width=120;
  String label="VEILFALL  "+value+"%";
  int guiWidth=event.getWindow().getGuiScaledWidth();
  int textWidth=mc.font.width(label)*scale/100;
  int x=(guiWidth-Math.max(width*scale/100,textWidth))/2;
  int y=6;
  var g=event.getGuiGraphics();g.pose().pushPose();g.pose().translate(x,y,0);g.pose().scale(scale/100.0F,scale/100.0F,1);
  int labelWidth=mc.font.width(label);
  g.drawString(mc.font,Component.literal(label),(width-labelWidth)/2,0,0xFFE8D9FF,true);
  g.fill(0,12,width,19,0xFF211A2A);
  int filled=width*value/100;
  if(filled>0)g.fill(0,12,filled,19,value<35?0xFF8E55C8:value<70?0xFFB43D83:0xFFE33145);
  g.fill(0,12,width,13,0xFFDDD3E8);g.pose().popPose();
 }
}