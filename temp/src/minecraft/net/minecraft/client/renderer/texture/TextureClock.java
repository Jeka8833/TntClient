package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class TextureClock extends TextureAtlasSprite {
   private double field_94239_h;
   private double field_94240_i;

   public TextureClock(String p_i1285_1_) {
      super(p_i1285_1_);
   }

   public void func_94219_l() {
      if (!this.field_110976_a.isEmpty()) {
         Minecraft lvt_1_1_ = Minecraft.func_71410_x();
         double lvt_2_1_ = 0.0D;
         if (lvt_1_1_.field_71441_e != null && lvt_1_1_.field_71439_g != null) {
            lvt_2_1_ = (double)lvt_1_1_.field_71441_e.func_72826_c(1.0F);
            if (!lvt_1_1_.field_71441_e.field_73011_w.func_76569_d()) {
               lvt_2_1_ = Math.random();
            }
         }

         double lvt_4_1_;
         for(lvt_4_1_ = lvt_2_1_ - this.field_94239_h; lvt_4_1_ < -0.5D; ++lvt_4_1_) {
         }

         while(lvt_4_1_ >= 0.5D) {
            --lvt_4_1_;
         }

         lvt_4_1_ = MathHelper.func_151237_a(lvt_4_1_, -1.0D, 1.0D);
         this.field_94240_i += lvt_4_1_ * 0.1D;
         this.field_94240_i *= 0.8D;
         this.field_94239_h += this.field_94240_i;

         int lvt_6_1_;
         for(lvt_6_1_ = (int)((this.field_94239_h + 1.0D) * (double)this.field_110976_a.size()) % this.field_110976_a.size(); lvt_6_1_ < 0; lvt_6_1_ = (lvt_6_1_ + this.field_110976_a.size()) % this.field_110976_a.size()) {
         }

         if (lvt_6_1_ != this.field_110973_g) {
            this.field_110973_g = lvt_6_1_;
            TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
         }

      }
   }
}
