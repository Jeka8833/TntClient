package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.realms.RealmsSimpleScrolledSelectionList;
import net.minecraft.util.MathHelper;

public class GuiSimpleScrolledSelectionListProxy extends GuiSlot {
   private final RealmsSimpleScrolledSelectionList field_178050_u;

   public GuiSimpleScrolledSelectionListProxy(RealmsSimpleScrolledSelectionList p_i45525_1_, int p_i45525_2_, int p_i45525_3_, int p_i45525_4_, int p_i45525_5_, int p_i45525_6_) {
      super(Minecraft.func_71410_x(), p_i45525_2_, p_i45525_3_, p_i45525_4_, p_i45525_5_, p_i45525_6_);
      this.field_178050_u = p_i45525_1_;
   }

   protected int func_148127_b() {
      return this.field_178050_u.getItemCount();
   }

   protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      this.field_178050_u.selectItem(p_148144_1_, p_148144_2_, p_148144_3_, p_148144_4_);
   }

   protected boolean func_148131_a(int p_148131_1_) {
      return this.field_178050_u.isSelectedItem(p_148131_1_);
   }

   protected void func_148123_a() {
      this.field_178050_u.renderBackground();
   }

   protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
      this.field_178050_u.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
   }

   public int func_178048_e() {
      return super.field_148155_a;
   }

   public int func_178047_f() {
      return super.field_148162_h;
   }

   public int func_178049_g() {
      return super.field_148150_g;
   }

   protected int func_148138_e() {
      return this.field_178050_u.getMaxPosition();
   }

   protected int func_148137_d() {
      return this.field_178050_u.getScrollbarPosition();
   }

   public void func_178039_p() {
      super.func_178039_p();
   }

   public void func_148128_a(int p_148128_1_, int p_148128_2_, float p_148128_3_) {
      if (this.field_178041_q) {
         this.field_148150_g = p_148128_1_;
         this.field_148162_h = p_148128_2_;
         this.func_148123_a();
         int lvt_4_1_ = this.func_148137_d();
         int lvt_5_1_ = lvt_4_1_ + 6;
         this.func_148121_k();
         GlStateManager.func_179140_f();
         GlStateManager.func_179106_n();
         Tessellator lvt_6_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_7_1_ = lvt_6_1_.func_178180_c();
         int lvt_8_1_ = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
         int lvt_9_1_ = this.field_148153_b + 4 - (int)this.field_148169_q;
         if (this.field_148165_u) {
            this.func_148129_a(lvt_8_1_, lvt_9_1_, lvt_6_1_);
         }

         this.func_148120_b(lvt_8_1_, lvt_9_1_, p_148128_1_, p_148128_2_);
         GlStateManager.func_179097_i();
         int lvt_10_1_ = true;
         this.func_148136_c(0, this.field_148153_b, 255, 255);
         this.func_148136_c(this.field_148154_c, this.field_148158_l, 255, 255);
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 0, 1);
         GlStateManager.func_179118_c();
         GlStateManager.func_179103_j(7425);
         GlStateManager.func_179090_x();
         int lvt_11_1_ = this.func_148135_f();
         if (lvt_11_1_ > 0) {
            int lvt_12_1_ = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
            lvt_12_1_ = MathHelper.func_76125_a(lvt_12_1_, 32, this.field_148154_c - this.field_148153_b - 8);
            int lvt_13_1_ = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - lvt_12_1_) / lvt_11_1_ + this.field_148153_b;
            if (lvt_13_1_ < this.field_148153_b) {
               lvt_13_1_ = this.field_148153_b;
            }

            lvt_7_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)this.field_148154_c, 0.0D).func_181673_a(0.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_5_1_, (double)this.field_148154_c, 0.0D).func_181673_a(1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_5_1_, (double)this.field_148153_b, 0.0D).func_181673_a(1.0D, 0.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)this.field_148153_b, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            lvt_6_1_.func_78381_a();
            lvt_7_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)(lvt_13_1_ + lvt_12_1_), 0.0D).func_181673_a(0.0D, 1.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_5_1_, (double)(lvt_13_1_ + lvt_12_1_), 0.0D).func_181673_a(1.0D, 1.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_5_1_, (double)lvt_13_1_, 0.0D).func_181673_a(1.0D, 0.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)lvt_13_1_, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            lvt_6_1_.func_78381_a();
            lvt_7_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)(lvt_13_1_ + lvt_12_1_ - 1), 0.0D).func_181673_a(0.0D, 1.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)(lvt_5_1_ - 1), (double)(lvt_13_1_ + lvt_12_1_ - 1), 0.0D).func_181673_a(1.0D, 1.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)(lvt_5_1_ - 1), (double)lvt_13_1_, 0.0D).func_181673_a(1.0D, 0.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            lvt_7_1_.func_181662_b((double)lvt_4_1_, (double)lvt_13_1_, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            lvt_6_1_.func_78381_a();
         }

         this.func_148142_b(p_148128_1_, p_148128_2_);
         GlStateManager.func_179098_w();
         GlStateManager.func_179103_j(7424);
         GlStateManager.func_179141_d();
         GlStateManager.func_179084_k();
      }
   }
}
