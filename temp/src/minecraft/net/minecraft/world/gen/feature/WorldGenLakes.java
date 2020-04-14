package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldGenLakes extends WorldGenerator {
   private Block field_150556_a;

   public WorldGenLakes(Block p_i45455_1_) {
      this.field_150556_a = p_i45455_1_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      for(p_180709_3_ = p_180709_3_.func_177982_a(-8, 0, -8); p_180709_3_.func_177956_o() > 5 && p_180709_1_.func_175623_d(p_180709_3_); p_180709_3_ = p_180709_3_.func_177977_b()) {
      }

      if (p_180709_3_.func_177956_o() <= 4) {
         return false;
      } else {
         p_180709_3_ = p_180709_3_.func_177979_c(4);
         boolean[] lvt_4_1_ = new boolean[2048];
         int lvt_5_1_ = p_180709_2_.nextInt(4) + 4;

         int lvt_6_6_;
         for(lvt_6_6_ = 0; lvt_6_6_ < lvt_5_1_; ++lvt_6_6_) {
            double lvt_7_1_ = p_180709_2_.nextDouble() * 6.0D + 3.0D;
            double lvt_9_1_ = p_180709_2_.nextDouble() * 4.0D + 2.0D;
            double lvt_11_1_ = p_180709_2_.nextDouble() * 6.0D + 3.0D;
            double lvt_13_1_ = p_180709_2_.nextDouble() * (16.0D - lvt_7_1_ - 2.0D) + 1.0D + lvt_7_1_ / 2.0D;
            double lvt_15_1_ = p_180709_2_.nextDouble() * (8.0D - lvt_9_1_ - 4.0D) + 2.0D + lvt_9_1_ / 2.0D;
            double lvt_17_1_ = p_180709_2_.nextDouble() * (16.0D - lvt_11_1_ - 2.0D) + 1.0D + lvt_11_1_ / 2.0D;

            for(int lvt_19_1_ = 1; lvt_19_1_ < 15; ++lvt_19_1_) {
               for(int lvt_20_1_ = 1; lvt_20_1_ < 15; ++lvt_20_1_) {
                  for(int lvt_21_1_ = 1; lvt_21_1_ < 7; ++lvt_21_1_) {
                     double lvt_22_1_ = ((double)lvt_19_1_ - lvt_13_1_) / (lvt_7_1_ / 2.0D);
                     double lvt_24_1_ = ((double)lvt_21_1_ - lvt_15_1_) / (lvt_9_1_ / 2.0D);
                     double lvt_26_1_ = ((double)lvt_20_1_ - lvt_17_1_) / (lvt_11_1_ / 2.0D);
                     double lvt_28_1_ = lvt_22_1_ * lvt_22_1_ + lvt_24_1_ * lvt_24_1_ + lvt_26_1_ * lvt_26_1_;
                     if (lvt_28_1_ < 1.0D) {
                        lvt_4_1_[(lvt_19_1_ * 16 + lvt_20_1_) * 8 + lvt_21_1_] = true;
                     }
                  }
               }
            }
         }

         int lvt_8_4_;
         int lvt_7_6_;
         boolean lvt_9_4_;
         for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
            for(lvt_7_6_ = 0; lvt_7_6_ < 16; ++lvt_7_6_) {
               for(lvt_8_4_ = 0; lvt_8_4_ < 8; ++lvt_8_4_) {
                  lvt_9_4_ = !lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_] && (lvt_6_6_ < 15 && lvt_4_1_[((lvt_6_6_ + 1) * 16 + lvt_7_6_) * 8 + lvt_8_4_] || lvt_6_6_ > 0 && lvt_4_1_[((lvt_6_6_ - 1) * 16 + lvt_7_6_) * 8 + lvt_8_4_] || lvt_7_6_ < 15 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_ + 1) * 8 + lvt_8_4_] || lvt_7_6_ > 0 && lvt_4_1_[(lvt_6_6_ * 16 + (lvt_7_6_ - 1)) * 8 + lvt_8_4_] || lvt_8_4_ < 7 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_ + 1] || lvt_8_4_ > 0 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + (lvt_8_4_ - 1)]);
                  if (lvt_9_4_) {
                     Material lvt_10_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_)).func_177230_c().func_149688_o();
                     if (lvt_8_4_ >= 4 && lvt_10_1_.func_76224_d()) {
                        return false;
                     }

                     if (lvt_8_4_ < 4 && !lvt_10_1_.func_76220_a() && p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_)).func_177230_c() != this.field_150556_a) {
                        return false;
                     }
                  }
               }
            }
         }

         for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
            for(lvt_7_6_ = 0; lvt_7_6_ < 16; ++lvt_7_6_) {
               for(lvt_8_4_ = 0; lvt_8_4_ < 8; ++lvt_8_4_) {
                  if (lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_]) {
                     p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_), lvt_8_4_ >= 4 ? Blocks.field_150350_a.func_176223_P() : this.field_150556_a.func_176223_P(), 2);
                  }
               }
            }
         }

         for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
            for(lvt_7_6_ = 0; lvt_7_6_ < 16; ++lvt_7_6_) {
               for(lvt_8_4_ = 4; lvt_8_4_ < 8; ++lvt_8_4_) {
                  if (lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_]) {
                     BlockPos lvt_9_3_ = p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_ - 1, lvt_7_6_);
                     if (p_180709_1_.func_180495_p(lvt_9_3_).func_177230_c() == Blocks.field_150346_d && p_180709_1_.func_175642_b(EnumSkyBlock.SKY, p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_)) > 0) {
                        BiomeGenBase lvt_10_2_ = p_180709_1_.func_180494_b(lvt_9_3_);
                        if (lvt_10_2_.field_76752_A.func_177230_c() == Blocks.field_150391_bh) {
                           p_180709_1_.func_180501_a(lvt_9_3_, Blocks.field_150391_bh.func_176223_P(), 2);
                        } else {
                           p_180709_1_.func_180501_a(lvt_9_3_, Blocks.field_150349_c.func_176223_P(), 2);
                        }
                     }
                  }
               }
            }
         }

         if (this.field_150556_a.func_149688_o() == Material.field_151587_i) {
            for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
               for(lvt_7_6_ = 0; lvt_7_6_ < 16; ++lvt_7_6_) {
                  for(lvt_8_4_ = 0; lvt_8_4_ < 8; ++lvt_8_4_) {
                     lvt_9_4_ = !lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_] && (lvt_6_6_ < 15 && lvt_4_1_[((lvt_6_6_ + 1) * 16 + lvt_7_6_) * 8 + lvt_8_4_] || lvt_6_6_ > 0 && lvt_4_1_[((lvt_6_6_ - 1) * 16 + lvt_7_6_) * 8 + lvt_8_4_] || lvt_7_6_ < 15 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_ + 1) * 8 + lvt_8_4_] || lvt_7_6_ > 0 && lvt_4_1_[(lvt_6_6_ * 16 + (lvt_7_6_ - 1)) * 8 + lvt_8_4_] || lvt_8_4_ < 7 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + lvt_8_4_ + 1] || lvt_8_4_ > 0 && lvt_4_1_[(lvt_6_6_ * 16 + lvt_7_6_) * 8 + (lvt_8_4_ - 1)]);
                     if (lvt_9_4_ && (lvt_8_4_ < 4 || p_180709_2_.nextInt(2) != 0) && p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_)).func_177230_c().func_149688_o().func_76220_a()) {
                        p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_4_, lvt_7_6_), Blocks.field_150348_b.func_176223_P(), 2);
                     }
                  }
               }
            }
         }

         if (this.field_150556_a.func_149688_o() == Material.field_151586_h) {
            for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
               for(lvt_7_6_ = 0; lvt_7_6_ < 16; ++lvt_7_6_) {
                  int lvt_8_5_ = 4;
                  if (p_180709_1_.func_175675_v(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_5_, lvt_7_6_))) {
                     p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(lvt_6_6_, lvt_8_5_, lvt_7_6_), Blocks.field_150432_aD.func_176223_P(), 2);
                  }
               }
            }
         }

         return true;
      }
   }
}
