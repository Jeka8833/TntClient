package net.optifine.shaders.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDownloadShaders extends GuiButton {
   public GuiButtonDownloadShaders(int buttonID, int xPos, int yPos) {
      super(buttonID, xPos, yPos, 22, 20, "");
   }

   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
      if (this.field_146125_m) {
         super.func_146112_a(mc, mouseX, mouseY);
         ResourceLocation locTexture = new ResourceLocation("optifine/textures/icons.png");
         mc.func_110434_K().func_110577_a(locTexture);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_73729_b(this.field_146128_h + 3, this.field_146129_i + 2, 0, 0, 16, 16);
      }
   }
}
