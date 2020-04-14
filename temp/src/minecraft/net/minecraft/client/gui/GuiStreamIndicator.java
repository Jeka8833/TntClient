package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiStreamIndicator {
   private static final ResourceLocation field_152441_a = new ResourceLocation("textures/gui/stream_indicator.png");
   private final Minecraft field_152442_b;
   private float field_152443_c = 1.0F;
   private int field_152444_d = 1;

   public GuiStreamIndicator(Minecraft p_i46322_1_) {
      this.field_152442_b = p_i46322_1_;
   }

   public void func_152437_a(int p_152437_1_, int p_152437_2_) {
      if (this.field_152442_b.func_152346_Z().func_152934_n()) {
         GlStateManager.func_179147_l();
         int lvt_3_1_ = this.field_152442_b.func_152346_Z().func_152920_A();
         if (lvt_3_1_ > 0) {
            String lvt_4_1_ = "" + lvt_3_1_;
            int lvt_5_1_ = this.field_152442_b.field_71466_p.func_78256_a(lvt_4_1_);
            int lvt_6_1_ = true;
            int lvt_7_1_ = p_152437_1_ - lvt_5_1_ - 1;
            int lvt_8_1_ = p_152437_2_ + 20 - 1;
            int lvt_10_1_ = p_152437_2_ + 20 + this.field_152442_b.field_71466_p.field_78288_b - 1;
            GlStateManager.func_179090_x();
            Tessellator lvt_11_1_ = Tessellator.func_178181_a();
            WorldRenderer lvt_12_1_ = lvt_11_1_.func_178180_c();
            GlStateManager.func_179131_c(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.field_152443_c) / 2.0F);
            lvt_12_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);
            lvt_12_1_.func_181662_b((double)lvt_7_1_, (double)lvt_10_1_, 0.0D).func_181675_d();
            lvt_12_1_.func_181662_b((double)p_152437_1_, (double)lvt_10_1_, 0.0D).func_181675_d();
            lvt_12_1_.func_181662_b((double)p_152437_1_, (double)lvt_8_1_, 0.0D).func_181675_d();
            lvt_12_1_.func_181662_b((double)lvt_7_1_, (double)lvt_8_1_, 0.0D).func_181675_d();
            lvt_11_1_.func_78381_a();
            GlStateManager.func_179098_w();
            this.field_152442_b.field_71466_p.func_78276_b(lvt_4_1_, p_152437_1_ - lvt_5_1_, p_152437_2_ + 20, 16777215);
         }

         this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152440_b(), 0);
         this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152438_c(), 17);
      }

   }

   private void func_152436_a(int p_152436_1_, int p_152436_2_, int p_152436_3_, int p_152436_4_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.field_152443_c);
      this.field_152442_b.func_110434_K().func_110577_a(field_152441_a);
      float lvt_5_1_ = 150.0F;
      float lvt_6_1_ = 0.0F;
      float lvt_7_1_ = (float)p_152436_3_ * 0.015625F;
      float lvt_8_1_ = 1.0F;
      float lvt_9_1_ = (float)(p_152436_3_ + 16) * 0.015625F;
      Tessellator lvt_10_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_11_1_ = lvt_10_1_.func_178180_c();
      lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_11_1_.func_181662_b((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 16), (double)lvt_5_1_).func_181673_a((double)lvt_6_1_, (double)lvt_9_1_).func_181675_d();
      lvt_11_1_.func_181662_b((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 16), (double)lvt_5_1_).func_181673_a((double)lvt_8_1_, (double)lvt_9_1_).func_181675_d();
      lvt_11_1_.func_181662_b((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 0), (double)lvt_5_1_).func_181673_a((double)lvt_8_1_, (double)lvt_7_1_).func_181675_d();
      lvt_11_1_.func_181662_b((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 0), (double)lvt_5_1_).func_181673_a((double)lvt_6_1_, (double)lvt_7_1_).func_181675_d();
      lvt_10_1_.func_78381_a();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private int func_152440_b() {
      return this.field_152442_b.func_152346_Z().func_152919_o() ? 16 : 0;
   }

   private int func_152438_c() {
      return this.field_152442_b.func_152346_Z().func_152929_G() ? 48 : 32;
   }

   public void func_152439_a() {
      if (this.field_152442_b.func_152346_Z().func_152934_n()) {
         this.field_152443_c += 0.025F * (float)this.field_152444_d;
         if (this.field_152443_c < 0.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 0.0F;
         } else if (this.field_152443_c > 1.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 1.0F;
         }
      } else {
         this.field_152443_c = 1.0F;
         this.field_152444_d = 1;
      }

   }
}
