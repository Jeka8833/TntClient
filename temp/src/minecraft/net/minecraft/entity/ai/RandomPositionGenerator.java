package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGenerator {
   private static Vec3 field_75465_a = new Vec3(0.0D, 0.0D, 0.0D);

   public static Vec3 func_75463_a(EntityCreature p_75463_0_, int p_75463_1_, int p_75463_2_) {
      return func_75462_c(p_75463_0_, p_75463_1_, p_75463_2_, (Vec3)null);
   }

   public static Vec3 func_75464_a(EntityCreature p_75464_0_, int p_75464_1_, int p_75464_2_, Vec3 p_75464_3_) {
      field_75465_a = p_75464_3_.func_178786_a(p_75464_0_.field_70165_t, p_75464_0_.field_70163_u, p_75464_0_.field_70161_v);
      return func_75462_c(p_75464_0_, p_75464_1_, p_75464_2_, field_75465_a);
   }

   public static Vec3 func_75461_b(EntityCreature p_75461_0_, int p_75461_1_, int p_75461_2_, Vec3 p_75461_3_) {
      field_75465_a = (new Vec3(p_75461_0_.field_70165_t, p_75461_0_.field_70163_u, p_75461_0_.field_70161_v)).func_178788_d(p_75461_3_);
      return func_75462_c(p_75461_0_, p_75461_1_, p_75461_2_, field_75465_a);
   }

   private static Vec3 func_75462_c(EntityCreature p_75462_0_, int p_75462_1_, int p_75462_2_, Vec3 p_75462_3_) {
      Random lvt_4_1_ = p_75462_0_.func_70681_au();
      boolean lvt_5_1_ = false;
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;
      int lvt_8_1_ = 0;
      float lvt_9_1_ = -99999.0F;
      boolean lvt_10_2_;
      if (p_75462_0_.func_110175_bO()) {
         double lvt_11_1_ = p_75462_0_.func_180486_cf().func_177954_c((double)MathHelper.func_76128_c(p_75462_0_.field_70165_t), (double)MathHelper.func_76128_c(p_75462_0_.field_70163_u), (double)MathHelper.func_76128_c(p_75462_0_.field_70161_v)) + 4.0D;
         double lvt_13_1_ = (double)(p_75462_0_.func_110174_bM() + (float)p_75462_1_);
         lvt_10_2_ = lvt_11_1_ < lvt_13_1_ * lvt_13_1_;
      } else {
         lvt_10_2_ = false;
      }

      for(int lvt_11_2_ = 0; lvt_11_2_ < 10; ++lvt_11_2_) {
         int lvt_12_1_ = lvt_4_1_.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
         int lvt_13_2_ = lvt_4_1_.nextInt(2 * p_75462_2_ + 1) - p_75462_2_;
         int lvt_14_1_ = lvt_4_1_.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
         if (p_75462_3_ == null || (double)lvt_12_1_ * p_75462_3_.field_72450_a + (double)lvt_14_1_ * p_75462_3_.field_72449_c >= 0.0D) {
            BlockPos lvt_15_1_;
            if (p_75462_0_.func_110175_bO() && p_75462_1_ > 1) {
               lvt_15_1_ = p_75462_0_.func_180486_cf();
               if (p_75462_0_.field_70165_t > (double)lvt_15_1_.func_177958_n()) {
                  lvt_12_1_ -= lvt_4_1_.nextInt(p_75462_1_ / 2);
               } else {
                  lvt_12_1_ += lvt_4_1_.nextInt(p_75462_1_ / 2);
               }

               if (p_75462_0_.field_70161_v > (double)lvt_15_1_.func_177952_p()) {
                  lvt_14_1_ -= lvt_4_1_.nextInt(p_75462_1_ / 2);
               } else {
                  lvt_14_1_ += lvt_4_1_.nextInt(p_75462_1_ / 2);
               }
            }

            lvt_12_1_ += MathHelper.func_76128_c(p_75462_0_.field_70165_t);
            lvt_13_2_ += MathHelper.func_76128_c(p_75462_0_.field_70163_u);
            lvt_14_1_ += MathHelper.func_76128_c(p_75462_0_.field_70161_v);
            lvt_15_1_ = new BlockPos(lvt_12_1_, lvt_13_2_, lvt_14_1_);
            if (!lvt_10_2_ || p_75462_0_.func_180485_d(lvt_15_1_)) {
               float lvt_16_1_ = p_75462_0_.func_180484_a(lvt_15_1_);
               if (lvt_16_1_ > lvt_9_1_) {
                  lvt_9_1_ = lvt_16_1_;
                  lvt_6_1_ = lvt_12_1_;
                  lvt_7_1_ = lvt_13_2_;
                  lvt_8_1_ = lvt_14_1_;
                  lvt_5_1_ = true;
               }
            }
         }
      }

      if (lvt_5_1_) {
         return new Vec3((double)lvt_6_1_, (double)lvt_7_1_, (double)lvt_8_1_);
      } else {
         return null;
      }
   }
}
