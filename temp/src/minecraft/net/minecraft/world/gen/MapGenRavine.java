package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenRavine extends MapGenBase {
   private float[] field_75046_d = new float[1024];

   protected void func_180707_a(long p_180707_1_, int p_180707_3_, int p_180707_4_, ChunkPrimer p_180707_5_, double p_180707_6_, double p_180707_8_, double p_180707_10_, float p_180707_12_, float p_180707_13_, float p_180707_14_, int p_180707_15_, int p_180707_16_, double p_180707_17_) {
      Random lvt_19_1_ = new Random(p_180707_1_);
      double lvt_20_1_ = (double)(p_180707_3_ * 16 + 8);
      double lvt_22_1_ = (double)(p_180707_4_ * 16 + 8);
      float lvt_24_1_ = 0.0F;
      float lvt_25_1_ = 0.0F;
      if (p_180707_16_ <= 0) {
         int lvt_26_1_ = this.field_75040_a * 16 - 16;
         p_180707_16_ = lvt_26_1_ - lvt_19_1_.nextInt(lvt_26_1_ / 4);
      }

      boolean lvt_26_2_ = false;
      if (p_180707_15_ == -1) {
         p_180707_15_ = p_180707_16_ / 2;
         lvt_26_2_ = true;
      }

      float lvt_27_1_ = 1.0F;

      for(int lvt_28_1_ = 0; lvt_28_1_ < 256; ++lvt_28_1_) {
         if (lvt_28_1_ == 0 || lvt_19_1_.nextInt(3) == 0) {
            lvt_27_1_ = 1.0F + lvt_19_1_.nextFloat() * lvt_19_1_.nextFloat() * 1.0F;
         }

         this.field_75046_d[lvt_28_1_] = lvt_27_1_ * lvt_27_1_;
      }

      for(; p_180707_15_ < p_180707_16_; ++p_180707_15_) {
         double lvt_28_2_ = 1.5D + (double)(MathHelper.func_76126_a((float)p_180707_15_ * 3.1415927F / (float)p_180707_16_) * p_180707_12_ * 1.0F);
         double lvt_30_1_ = lvt_28_2_ * p_180707_17_;
         lvt_28_2_ *= (double)lvt_19_1_.nextFloat() * 0.25D + 0.75D;
         lvt_30_1_ *= (double)lvt_19_1_.nextFloat() * 0.25D + 0.75D;
         float lvt_32_1_ = MathHelper.func_76134_b(p_180707_14_);
         float lvt_33_1_ = MathHelper.func_76126_a(p_180707_14_);
         p_180707_6_ += (double)(MathHelper.func_76134_b(p_180707_13_) * lvt_32_1_);
         p_180707_8_ += (double)lvt_33_1_;
         p_180707_10_ += (double)(MathHelper.func_76126_a(p_180707_13_) * lvt_32_1_);
         p_180707_14_ *= 0.7F;
         p_180707_14_ += lvt_25_1_ * 0.05F;
         p_180707_13_ += lvt_24_1_ * 0.05F;
         lvt_25_1_ *= 0.8F;
         lvt_24_1_ *= 0.5F;
         lvt_25_1_ += (lvt_19_1_.nextFloat() - lvt_19_1_.nextFloat()) * lvt_19_1_.nextFloat() * 2.0F;
         lvt_24_1_ += (lvt_19_1_.nextFloat() - lvt_19_1_.nextFloat()) * lvt_19_1_.nextFloat() * 4.0F;
         if (lvt_26_2_ || lvt_19_1_.nextInt(4) != 0) {
            double lvt_34_1_ = p_180707_6_ - lvt_20_1_;
            double lvt_36_1_ = p_180707_10_ - lvt_22_1_;
            double lvt_38_1_ = (double)(p_180707_16_ - p_180707_15_);
            double lvt_40_1_ = (double)(p_180707_12_ + 2.0F + 16.0F);
            if (lvt_34_1_ * lvt_34_1_ + lvt_36_1_ * lvt_36_1_ - lvt_38_1_ * lvt_38_1_ > lvt_40_1_ * lvt_40_1_) {
               return;
            }

            if (p_180707_6_ >= lvt_20_1_ - 16.0D - lvt_28_2_ * 2.0D && p_180707_10_ >= lvt_22_1_ - 16.0D - lvt_28_2_ * 2.0D && p_180707_6_ <= lvt_20_1_ + 16.0D + lvt_28_2_ * 2.0D && p_180707_10_ <= lvt_22_1_ + 16.0D + lvt_28_2_ * 2.0D) {
               int lvt_34_2_ = MathHelper.func_76128_c(p_180707_6_ - lvt_28_2_) - p_180707_3_ * 16 - 1;
               int lvt_35_1_ = MathHelper.func_76128_c(p_180707_6_ + lvt_28_2_) - p_180707_3_ * 16 + 1;
               int lvt_36_2_ = MathHelper.func_76128_c(p_180707_8_ - lvt_30_1_) - 1;
               int lvt_37_1_ = MathHelper.func_76128_c(p_180707_8_ + lvt_30_1_) + 1;
               int lvt_38_2_ = MathHelper.func_76128_c(p_180707_10_ - lvt_28_2_) - p_180707_4_ * 16 - 1;
               int lvt_39_1_ = MathHelper.func_76128_c(p_180707_10_ + lvt_28_2_) - p_180707_4_ * 16 + 1;
               if (lvt_34_2_ < 0) {
                  lvt_34_2_ = 0;
               }

               if (lvt_35_1_ > 16) {
                  lvt_35_1_ = 16;
               }

               if (lvt_36_2_ < 1) {
                  lvt_36_2_ = 1;
               }

               if (lvt_37_1_ > 248) {
                  lvt_37_1_ = 248;
               }

               if (lvt_38_2_ < 0) {
                  lvt_38_2_ = 0;
               }

               if (lvt_39_1_ > 16) {
                  lvt_39_1_ = 16;
               }

               boolean lvt_40_2_ = false;

               int lvt_42_2_;
               for(int lvt_41_1_ = lvt_34_2_; !lvt_40_2_ && lvt_41_1_ < lvt_35_1_; ++lvt_41_1_) {
                  for(lvt_42_2_ = lvt_38_2_; !lvt_40_2_ && lvt_42_2_ < lvt_39_1_; ++lvt_42_2_) {
                     for(int lvt_43_1_ = lvt_37_1_ + 1; !lvt_40_2_ && lvt_43_1_ >= lvt_36_2_ - 1; --lvt_43_1_) {
                        if (lvt_43_1_ >= 0 && lvt_43_1_ < 256) {
                           IBlockState lvt_44_1_ = p_180707_5_.func_177856_a(lvt_41_1_, lvt_43_1_, lvt_42_2_);
                           if (lvt_44_1_.func_177230_c() == Blocks.field_150358_i || lvt_44_1_.func_177230_c() == Blocks.field_150355_j) {
                              lvt_40_2_ = true;
                           }

                           if (lvt_43_1_ != lvt_36_2_ - 1 && lvt_41_1_ != lvt_34_2_ && lvt_41_1_ != lvt_35_1_ - 1 && lvt_42_2_ != lvt_38_2_ && lvt_42_2_ != lvt_39_1_ - 1) {
                              lvt_43_1_ = lvt_36_2_;
                           }
                        }
                     }
                  }
               }

               if (!lvt_40_2_) {
                  BlockPos.MutableBlockPos lvt_41_2_ = new BlockPos.MutableBlockPos();

                  for(lvt_42_2_ = lvt_34_2_; lvt_42_2_ < lvt_35_1_; ++lvt_42_2_) {
                     double lvt_43_2_ = ((double)(lvt_42_2_ + p_180707_3_ * 16) + 0.5D - p_180707_6_) / lvt_28_2_;

                     for(int lvt_45_1_ = lvt_38_2_; lvt_45_1_ < lvt_39_1_; ++lvt_45_1_) {
                        double lvt_46_1_ = ((double)(lvt_45_1_ + p_180707_4_ * 16) + 0.5D - p_180707_10_) / lvt_28_2_;
                        boolean lvt_48_1_ = false;
                        if (lvt_43_2_ * lvt_43_2_ + lvt_46_1_ * lvt_46_1_ < 1.0D) {
                           for(int lvt_49_1_ = lvt_37_1_; lvt_49_1_ > lvt_36_2_; --lvt_49_1_) {
                              double lvt_50_1_ = ((double)(lvt_49_1_ - 1) + 0.5D - p_180707_8_) / lvt_30_1_;
                              if ((lvt_43_2_ * lvt_43_2_ + lvt_46_1_ * lvt_46_1_) * (double)this.field_75046_d[lvt_49_1_ - 1] + lvt_50_1_ * lvt_50_1_ / 6.0D < 1.0D) {
                                 IBlockState lvt_52_1_ = p_180707_5_.func_177856_a(lvt_42_2_, lvt_49_1_, lvt_45_1_);
                                 if (lvt_52_1_.func_177230_c() == Blocks.field_150349_c) {
                                    lvt_48_1_ = true;
                                 }

                                 if (lvt_52_1_.func_177230_c() == Blocks.field_150348_b || lvt_52_1_.func_177230_c() == Blocks.field_150346_d || lvt_52_1_.func_177230_c() == Blocks.field_150349_c) {
                                    if (lvt_49_1_ - 1 < 10) {
                                       p_180707_5_.func_177855_a(lvt_42_2_, lvt_49_1_, lvt_45_1_, Blocks.field_150356_k.func_176223_P());
                                    } else {
                                       p_180707_5_.func_177855_a(lvt_42_2_, lvt_49_1_, lvt_45_1_, Blocks.field_150350_a.func_176223_P());
                                       if (lvt_48_1_ && p_180707_5_.func_177856_a(lvt_42_2_, lvt_49_1_ - 1, lvt_45_1_).func_177230_c() == Blocks.field_150346_d) {
                                          lvt_41_2_.func_181079_c(lvt_42_2_ + p_180707_3_ * 16, 0, lvt_45_1_ + p_180707_4_ * 16);
                                          p_180707_5_.func_177855_a(lvt_42_2_, lvt_49_1_ - 1, lvt_45_1_, this.field_75039_c.func_180494_b(lvt_41_2_).field_76752_A);
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (lvt_26_2_) {
                     break;
                  }
               }
            }
         }
      }

   }

   protected void func_180701_a(World p_180701_1_, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
      if (this.field_75038_b.nextInt(50) == 0) {
         double lvt_7_1_ = (double)(p_180701_2_ * 16 + this.field_75038_b.nextInt(16));
         double lvt_9_1_ = (double)(this.field_75038_b.nextInt(this.field_75038_b.nextInt(40) + 8) + 20);
         double lvt_11_1_ = (double)(p_180701_3_ * 16 + this.field_75038_b.nextInt(16));
         int lvt_13_1_ = 1;

         for(int lvt_14_1_ = 0; lvt_14_1_ < lvt_13_1_; ++lvt_14_1_) {
            float lvt_15_1_ = this.field_75038_b.nextFloat() * 3.1415927F * 2.0F;
            float lvt_16_1_ = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float lvt_17_1_ = (this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat()) * 2.0F;
            this.func_180707_a(this.field_75038_b.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_17_1_, lvt_15_1_, lvt_16_1_, 0, 0, 3.0D);
         }

      }
   }
}
