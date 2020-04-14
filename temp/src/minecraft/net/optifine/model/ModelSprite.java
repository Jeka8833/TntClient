package net.optifine.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSprite {
   private ModelRenderer modelRenderer = null;
   private int textureOffsetX = 0;
   private int textureOffsetY = 0;
   private float posX = 0.0F;
   private float posY = 0.0F;
   private float posZ = 0.0F;
   private int sizeX = 0;
   private int sizeY = 0;
   private int sizeZ = 0;
   private float sizeAdd = 0.0F;
   private float minU = 0.0F;
   private float minV = 0.0F;
   private float maxU = 0.0F;
   private float maxV = 0.0F;

   public ModelSprite(ModelRenderer modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd) {
      this.modelRenderer = modelRenderer;
      this.textureOffsetX = textureOffsetX;
      this.textureOffsetY = textureOffsetY;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.sizeX = sizeX;
      this.sizeY = sizeY;
      this.sizeZ = sizeZ;
      this.sizeAdd = sizeAdd;
      this.minU = (float)textureOffsetX / modelRenderer.field_78801_a;
      this.minV = (float)textureOffsetY / modelRenderer.field_78799_b;
      this.maxU = (float)(textureOffsetX + sizeX) / modelRenderer.field_78801_a;
      this.maxV = (float)(textureOffsetY + sizeY) / modelRenderer.field_78799_b;
   }

   public void render(Tessellator tessellator, float scale) {
      GlStateManager.func_179109_b(this.posX * scale, this.posY * scale, this.posZ * scale);
      float rMinU = this.minU;
      float rMaxU = this.maxU;
      float rMinV = this.minV;
      float rMaxV = this.maxV;
      if (this.modelRenderer.field_78809_i) {
         rMinU = this.maxU;
         rMaxU = this.minU;
      }

      if (this.modelRenderer.mirrorV) {
         rMinV = this.maxV;
         rMaxV = this.minV;
      }

      renderItemIn2D(tessellator, rMinU, rMinV, rMaxU, rMaxV, this.sizeX, this.sizeY, scale * (float)this.sizeZ, this.modelRenderer.field_78801_a, this.modelRenderer.field_78799_b);
      GlStateManager.func_179109_b(-this.posX * scale, -this.posY * scale, -this.posZ * scale);
   }

   public static void renderItemIn2D(Tessellator tess, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight) {
      if (width < 6.25E-4F) {
         width = 6.25E-4F;
      }

      float dU = maxU - minU;
      float dV = maxV - minV;
      double dimX = (double)(MathHelper.func_76135_e(dU) * (texWidth / 16.0F));
      double dimY = (double)(MathHelper.func_76135_e(dV) * (texHeight / 16.0F));
      WorldRenderer tessellator = tess.func_178180_c();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      tessellator.func_181662_b(0.0D, dimY, 0.0D).func_181673_a((double)minU, (double)maxV).func_181675_d();
      tessellator.func_181662_b(dimX, dimY, 0.0D).func_181673_a((double)maxU, (double)maxV).func_181675_d();
      tessellator.func_181662_b(dimX, 0.0D, 0.0D).func_181673_a((double)maxU, (double)minV).func_181675_d();
      tessellator.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a((double)minU, (double)minV).func_181675_d();
      tess.func_78381_a();
      GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      tessellator.func_181662_b(0.0D, 0.0D, (double)width).func_181673_a((double)minU, (double)minV).func_181675_d();
      tessellator.func_181662_b(dimX, 0.0D, (double)width).func_181673_a((double)maxU, (double)minV).func_181675_d();
      tessellator.func_181662_b(dimX, dimY, (double)width).func_181673_a((double)maxU, (double)maxV).func_181675_d();
      tessellator.func_181662_b(0.0D, dimY, (double)width).func_181673_a((double)minU, (double)maxV).func_181675_d();
      tess.func_78381_a();
      float var8 = 0.5F * dU / (float)sizeX;
      float var9 = 0.5F * dV / (float)sizeY;
      GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);

      int var10;
      float var11;
      float var12;
      for(var10 = 0; var10 < sizeX; ++var10) {
         var11 = (float)var10 / (float)sizeX;
         var12 = minU + dU * var11 + var8;
         tessellator.func_181662_b((double)var11 * dimX, dimY, (double)width).func_181673_a((double)var12, (double)maxV).func_181675_d();
         tessellator.func_181662_b((double)var11 * dimX, dimY, 0.0D).func_181673_a((double)var12, (double)maxV).func_181675_d();
         tessellator.func_181662_b((double)var11 * dimX, 0.0D, 0.0D).func_181673_a((double)var12, (double)minV).func_181675_d();
         tessellator.func_181662_b((double)var11 * dimX, 0.0D, (double)width).func_181673_a((double)var12, (double)minV).func_181675_d();
      }

      tess.func_78381_a();
      GL11.glNormal3f(1.0F, 0.0F, 0.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);

      float var13;
      for(var10 = 0; var10 < sizeX; ++var10) {
         var11 = (float)var10 / (float)sizeX;
         var12 = minU + dU * var11 + var8;
         var13 = var11 + 1.0F / (float)sizeX;
         tessellator.func_181662_b((double)var13 * dimX, 0.0D, (double)width).func_181673_a((double)var12, (double)minV).func_181675_d();
         tessellator.func_181662_b((double)var13 * dimX, 0.0D, 0.0D).func_181673_a((double)var12, (double)minV).func_181675_d();
         tessellator.func_181662_b((double)var13 * dimX, dimY, 0.0D).func_181673_a((double)var12, (double)maxV).func_181675_d();
         tessellator.func_181662_b((double)var13 * dimX, dimY, (double)width).func_181673_a((double)var12, (double)maxV).func_181675_d();
      }

      tess.func_78381_a();
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);

      for(var10 = 0; var10 < sizeY; ++var10) {
         var11 = (float)var10 / (float)sizeY;
         var12 = minV + dV * var11 + var9;
         var13 = var11 + 1.0F / (float)sizeY;
         tessellator.func_181662_b(0.0D, (double)var13 * dimY, (double)width).func_181673_a((double)minU, (double)var12).func_181675_d();
         tessellator.func_181662_b(dimX, (double)var13 * dimY, (double)width).func_181673_a((double)maxU, (double)var12).func_181675_d();
         tessellator.func_181662_b(dimX, (double)var13 * dimY, 0.0D).func_181673_a((double)maxU, (double)var12).func_181675_d();
         tessellator.func_181662_b(0.0D, (double)var13 * dimY, 0.0D).func_181673_a((double)minU, (double)var12).func_181675_d();
      }

      tess.func_78381_a();
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      tessellator.func_181668_a(7, DefaultVertexFormats.field_181707_g);

      for(var10 = 0; var10 < sizeY; ++var10) {
         var11 = (float)var10 / (float)sizeY;
         var12 = minV + dV * var11 + var9;
         tessellator.func_181662_b(dimX, (double)var11 * dimY, (double)width).func_181673_a((double)maxU, (double)var12).func_181675_d();
         tessellator.func_181662_b(0.0D, (double)var11 * dimY, (double)width).func_181673_a((double)minU, (double)var12).func_181675_d();
         tessellator.func_181662_b(0.0D, (double)var11 * dimY, 0.0D).func_181673_a((double)minU, (double)var12).func_181675_d();
         tessellator.func_181662_b(dimX, (double)var11 * dimY, 0.0D).func_181673_a((double)maxU, (double)var12).func_181675_d();
      }

      tess.func_78381_a();
   }
}
