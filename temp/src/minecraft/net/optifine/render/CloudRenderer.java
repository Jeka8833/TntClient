package net.optifine.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class CloudRenderer {
   private Minecraft mc;
   private boolean updated = false;
   private boolean renderFancy = false;
   int cloudTickCounter;
   private Vec3 cloudColor;
   float partialTicks;
   private boolean updateRenderFancy = false;
   private int updateCloudTickCounter = 0;
   private Vec3 updateCloudColor = new Vec3(-1.0D, -1.0D, -1.0D);
   private double updatePlayerX = 0.0D;
   private double updatePlayerY = 0.0D;
   private double updatePlayerZ = 0.0D;
   private int glListClouds = -1;

   public CloudRenderer(Minecraft mc) {
      this.mc = mc;
      this.glListClouds = GLAllocation.func_74526_a(1);
   }

   public void prepareToRender(boolean renderFancy, int cloudTickCounter, float partialTicks, Vec3 cloudColor) {
      this.renderFancy = renderFancy;
      this.cloudTickCounter = cloudTickCounter;
      this.partialTicks = partialTicks;
      this.cloudColor = cloudColor;
   }

   public boolean shouldUpdateGlList() {
      if (!this.updated) {
         return true;
      } else if (this.renderFancy != this.updateRenderFancy) {
         return true;
      } else if (this.cloudTickCounter >= this.updateCloudTickCounter + 20) {
         return true;
      } else if (Math.abs(this.cloudColor.field_72450_a - this.updateCloudColor.field_72450_a) > 0.003D) {
         return true;
      } else if (Math.abs(this.cloudColor.field_72448_b - this.updateCloudColor.field_72448_b) > 0.003D) {
         return true;
      } else if (Math.abs(this.cloudColor.field_72449_c - this.updateCloudColor.field_72449_c) > 0.003D) {
         return true;
      } else {
         Entity rve = this.mc.func_175606_aa();
         boolean belowCloudsPrev = this.updatePlayerY + (double)rve.func_70047_e() < 128.0D + (double)(this.mc.field_71474_y.ofCloudsHeight * 128.0F);
         boolean belowClouds = rve.field_70167_r + (double)rve.func_70047_e() < 128.0D + (double)(this.mc.field_71474_y.ofCloudsHeight * 128.0F);
         return belowClouds != belowCloudsPrev;
      }
   }

   public void startUpdateGlList() {
      GL11.glNewList(this.glListClouds, 4864);
   }

   public void endUpdateGlList() {
      GL11.glEndList();
      this.updateRenderFancy = this.renderFancy;
      this.updateCloudTickCounter = this.cloudTickCounter;
      this.updateCloudColor = this.cloudColor;
      this.updatePlayerX = this.mc.func_175606_aa().field_70169_q;
      this.updatePlayerY = this.mc.func_175606_aa().field_70167_r;
      this.updatePlayerZ = this.mc.func_175606_aa().field_70166_s;
      this.updated = true;
      GlStateManager.func_179117_G();
   }

   public void renderGlList() {
      Entity entityliving = this.mc.func_175606_aa();
      double exactPlayerX = entityliving.field_70169_q + (entityliving.field_70165_t - entityliving.field_70169_q) * (double)this.partialTicks;
      double exactPlayerY = entityliving.field_70167_r + (entityliving.field_70163_u - entityliving.field_70167_r) * (double)this.partialTicks;
      double exactPlayerZ = entityliving.field_70166_s + (entityliving.field_70161_v - entityliving.field_70166_s) * (double)this.partialTicks;
      double dc = (double)((float)(this.cloudTickCounter - this.updateCloudTickCounter) + this.partialTicks);
      float cdx = (float)(exactPlayerX - this.updatePlayerX + dc * 0.03D);
      float cdy = (float)(exactPlayerY - this.updatePlayerY);
      float cdz = (float)(exactPlayerZ - this.updatePlayerZ);
      GlStateManager.func_179094_E();
      if (this.renderFancy) {
         GlStateManager.func_179109_b(-cdx / 12.0F, -cdy, -cdz / 12.0F);
      } else {
         GlStateManager.func_179109_b(-cdx, -cdy, -cdz);
      }

      GlStateManager.func_179148_o(this.glListClouds);
      GlStateManager.func_179121_F();
      GlStateManager.func_179117_G();
   }

   public void reset() {
      this.updated = false;
   }
}
