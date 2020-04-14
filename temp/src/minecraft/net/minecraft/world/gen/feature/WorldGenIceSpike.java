package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenIceSpike extends WorldGenerator {
   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      while(p_180709_1_.func_175623_d(p_180709_3_) && p_180709_3_.func_177956_o() > 2) {
         p_180709_3_ = p_180709_3_.func_177977_b();
      }

      if (p_180709_1_.func_180495_p(p_180709_3_).func_177230_c() != Blocks.field_150433_aE) {
         return false;
      } else {
         p_180709_3_ = p_180709_3_.func_177981_b(p_180709_2_.nextInt(4));
         int lvt_4_1_ = p_180709_2_.nextInt(4) + 7;
         int lvt_5_1_ = lvt_4_1_ / 4 + p_180709_2_.nextInt(2);
         if (lvt_5_1_ > 1 && p_180709_2_.nextInt(60) == 0) {
            p_180709_3_ = p_180709_3_.func_177981_b(10 + p_180709_2_.nextInt(30));
         }

         int lvt_6_1_;
         int lvt_8_1_;
         for(lvt_6_1_ = 0; lvt_6_1_ < lvt_4_1_; ++lvt_6_1_) {
            float lvt_7_1_ = (1.0F - (float)lvt_6_1_ / (float)lvt_4_1_) * (float)lvt_5_1_;
            lvt_8_1_ = MathHelper.func_76123_f(lvt_7_1_);

            for(int lvt_9_1_ = -lvt_8_1_; lvt_9_1_ <= lvt_8_1_; ++lvt_9_1_) {
               float lvt_10_1_ = (float)MathHelper.func_76130_a(lvt_9_1_) - 0.25F;

               for(int lvt_11_1_ = -lvt_8_1_; lvt_11_1_ <= lvt_8_1_; ++lvt_11_1_) {
                  float lvt_12_1_ = (float)MathHelper.func_76130_a(lvt_11_1_) - 0.25F;
                  if ((lvt_9_1_ == 0 && lvt_11_1_ == 0 || lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_ <= lvt_7_1_ * lvt_7_1_) && (lvt_9_1_ != -lvt_8_1_ && lvt_9_1_ != lvt_8_1_ && lvt_11_1_ != -lvt_8_1_ && lvt_11_1_ != lvt_8_1_ || p_180709_2_.nextFloat() <= 0.75F)) {
                     Block lvt_13_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(lvt_9_1_, lvt_6_1_, lvt_11_1_)).func_177230_c();
                     if (lvt_13_1_.func_149688_o() == Material.field_151579_a || lvt_13_1_ == Blocks.field_150346_d || lvt_13_1_ == Blocks.field_150433_aE || lvt_13_1_ == Blocks.field_150432_aD) {
                        this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(lvt_9_1_, lvt_6_1_, lvt_11_1_), Blocks.field_150403_cj.func_176223_P());
                     }

                     if (lvt_6_1_ != 0 && lvt_8_1_ > 1) {
                        lvt_13_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(lvt_9_1_, -lvt_6_1_, lvt_11_1_)).func_177230_c();
                        if (lvt_13_1_.func_149688_o() == Material.field_151579_a || lvt_13_1_ == Blocks.field_150346_d || lvt_13_1_ == Blocks.field_150433_aE || lvt_13_1_ == Blocks.field_150432_aD) {
                           this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(lvt_9_1_, -lvt_6_1_, lvt_11_1_), Blocks.field_150403_cj.func_176223_P());
                        }
                     }
                  }
               }
            }
         }

         lvt_6_1_ = lvt_5_1_ - 1;
         if (lvt_6_1_ < 0) {
            lvt_6_1_ = 0;
         } else if (lvt_6_1_ > 1) {
            lvt_6_1_ = 1;
         }

         for(int lvt_7_2_ = -lvt_6_1_; lvt_7_2_ <= lvt_6_1_; ++lvt_7_2_) {
            for(lvt_8_1_ = -lvt_6_1_; lvt_8_1_ <= lvt_6_1_; ++lvt_8_1_) {
               BlockPos lvt_9_2_ = p_180709_3_.func_177982_a(lvt_7_2_, -1, lvt_8_1_);
               int lvt_10_2_ = 50;
               if (Math.abs(lvt_7_2_) == 1 && Math.abs(lvt_8_1_) == 1) {
                  lvt_10_2_ = p_180709_2_.nextInt(5);
               }

               while(lvt_9_2_.func_177956_o() > 50) {
                  Block lvt_11_2_ = p_180709_1_.func_180495_p(lvt_9_2_).func_177230_c();
                  if (lvt_11_2_.func_149688_o() != Material.field_151579_a && lvt_11_2_ != Blocks.field_150346_d && lvt_11_2_ != Blocks.field_150433_aE && lvt_11_2_ != Blocks.field_150432_aD && lvt_11_2_ != Blocks.field_150403_cj) {
                     break;
                  }

                  this.func_175903_a(p_180709_1_, lvt_9_2_, Blocks.field_150403_cj.func_176223_P());
                  lvt_9_2_ = lvt_9_2_.func_177977_b();
                  --lvt_10_2_;
                  if (lvt_10_2_ <= 0) {
                     lvt_9_2_ = lvt_9_2_.func_177979_c(p_180709_2_.nextInt(5) + 1);
                     lvt_10_2_ = p_180709_2_.nextInt(5);
                  }
               }
            }
         }

         return true;
      }
   }
}
