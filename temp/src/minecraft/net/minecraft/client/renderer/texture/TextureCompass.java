package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TextureCompass extends TextureAtlasSprite {
   public double field_94244_i;
   public double field_94242_j;
   public static String field_176608_l;

   public TextureCompass(String p_i1286_1_) {
      super(p_i1286_1_);
      field_176608_l = p_i1286_1_;
   }

   public void func_94219_l() {
      Minecraft lvt_1_1_ = Minecraft.func_71410_x();
      if (lvt_1_1_.field_71441_e != null && lvt_1_1_.field_71439_g != null) {
         this.func_94241_a(lvt_1_1_.field_71441_e, lvt_1_1_.field_71439_g.field_70165_t, lvt_1_1_.field_71439_g.field_70161_v, (double)lvt_1_1_.field_71439_g.field_70177_z, false, false);
      } else {
         this.func_94241_a((World)null, 0.0D, 0.0D, 0.0D, true, false);
      }

   }

   public void func_94241_a(World p_94241_1_, double p_94241_2_, double p_94241_4_, double p_94241_6_, boolean p_94241_8_, boolean p_94241_9_) {
      if (!this.field_110976_a.isEmpty()) {
         double lvt_10_1_ = 0.0D;
         if (p_94241_1_ != null && !p_94241_8_) {
            BlockPos lvt_12_1_ = p_94241_1_.func_175694_M();
            double lvt_13_1_ = (double)lvt_12_1_.func_177958_n() - p_94241_2_;
            double lvt_15_1_ = (double)lvt_12_1_.func_177952_p() - p_94241_4_;
            p_94241_6_ %= 360.0D;
            lvt_10_1_ = -((p_94241_6_ - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(lvt_15_1_, lvt_13_1_));
            if (!p_94241_1_.field_73011_w.func_76569_d()) {
               lvt_10_1_ = Math.random() * 3.1415927410125732D * 2.0D;
            }
         }

         if (p_94241_9_) {
            this.field_94244_i = lvt_10_1_;
         } else {
            double lvt_12_2_;
            for(lvt_12_2_ = lvt_10_1_ - this.field_94244_i; lvt_12_2_ < -3.141592653589793D; lvt_12_2_ += 6.283185307179586D) {
            }

            while(lvt_12_2_ >= 3.141592653589793D) {
               lvt_12_2_ -= 6.283185307179586D;
            }

            lvt_12_2_ = MathHelper.func_151237_a(lvt_12_2_, -1.0D, 1.0D);
            this.field_94242_j += lvt_12_2_ * 0.1D;
            this.field_94242_j *= 0.8D;
            this.field_94244_i += this.field_94242_j;
         }

         int lvt_12_3_;
         for(lvt_12_3_ = (int)((this.field_94244_i / 6.283185307179586D + 1.0D) * (double)this.field_110976_a.size()) % this.field_110976_a.size(); lvt_12_3_ < 0; lvt_12_3_ = (lvt_12_3_ + this.field_110976_a.size()) % this.field_110976_a.size()) {
         }

         if (lvt_12_3_ != this.field_110973_g) {
            this.field_110973_g = lvt_12_3_;
            TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
         }

      }
   }
}
