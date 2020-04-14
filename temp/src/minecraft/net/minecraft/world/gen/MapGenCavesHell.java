package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenCavesHell extends MapGenBase {
   protected void func_180705_a(long p_180705_1_, int p_180705_3_, int p_180705_4_, ChunkPrimer p_180705_5_, double p_180705_6_, double p_180705_8_, double p_180705_10_) {
      this.func_180704_a(p_180705_1_, p_180705_3_, p_180705_4_, p_180705_5_, p_180705_6_, p_180705_8_, p_180705_10_, 1.0F + this.field_75038_b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
   }

   protected void func_180704_a(long p_180704_1_, int p_180704_3_, int p_180704_4_, ChunkPrimer p_180704_5_, double p_180704_6_, double p_180704_8_, double p_180704_10_, float p_180704_12_, float p_180704_13_, float p_180704_14_, int p_180704_15_, int p_180704_16_, double p_180704_17_) {
      double lvt_19_1_ = (double)(p_180704_3_ * 16 + 8);
      double lvt_21_1_ = (double)(p_180704_4_ * 16 + 8);
      float lvt_23_1_ = 0.0F;
      float lvt_24_1_ = 0.0F;
      Random lvt_25_1_ = new Random(p_180704_1_);
      if (p_180704_16_ <= 0) {
         int lvt_26_1_ = this.field_75040_a * 16 - 16;
         p_180704_16_ = lvt_26_1_ - lvt_25_1_.nextInt(lvt_26_1_ / 4);
      }

      boolean lvt_26_2_ = false;
      if (p_180704_15_ == -1) {
         p_180704_15_ = p_180704_16_ / 2;
         lvt_26_2_ = true;
      }

      int lvt_27_1_ = lvt_25_1_.nextInt(p_180704_16_ / 2) + p_180704_16_ / 4;

      for(boolean lvt_28_1_ = lvt_25_1_.nextInt(6) == 0; p_180704_15_ < p_180704_16_; ++p_180704_15_) {
         double lvt_29_1_ = 1.5D + (double)(MathHelper.func_76126_a((float)p_180704_15_ * 3.1415927F / (float)p_180704_16_) * p_180704_12_ * 1.0F);
         double lvt_31_1_ = lvt_29_1_ * p_180704_17_;
         float lvt_33_1_ = MathHelper.func_76134_b(p_180704_14_);
         float lvt_34_1_ = MathHelper.func_76126_a(p_180704_14_);
         p_180704_6_ += (double)(MathHelper.func_76134_b(p_180704_13_) * lvt_33_1_);
         p_180704_8_ += (double)lvt_34_1_;
         p_180704_10_ += (double)(MathHelper.func_76126_a(p_180704_13_) * lvt_33_1_);
         if (lvt_28_1_) {
            p_180704_14_ *= 0.92F;
         } else {
            p_180704_14_ *= 0.7F;
         }

         p_180704_14_ += lvt_24_1_ * 0.1F;
         p_180704_13_ += lvt_23_1_ * 0.1F;
         lvt_24_1_ *= 0.9F;
         lvt_23_1_ *= 0.75F;
         lvt_24_1_ += (lvt_25_1_.nextFloat() - lvt_25_1_.nextFloat()) * lvt_25_1_.nextFloat() * 2.0F;
         lvt_23_1_ += (lvt_25_1_.nextFloat() - lvt_25_1_.nextFloat()) * lvt_25_1_.nextFloat() * 4.0F;
         if (!lvt_26_2_ && p_180704_15_ == lvt_27_1_ && p_180704_12_ > 1.0F) {
            this.func_180704_a(lvt_25_1_.nextLong(), p_180704_3_, p_180704_4_, p_180704_5_, p_180704_6_, p_180704_8_, p_180704_10_, lvt_25_1_.nextFloat() * 0.5F + 0.5F, p_180704_13_ - 1.5707964F, p_180704_14_ / 3.0F, p_180704_15_, p_180704_16_, 1.0D);
            this.func_180704_a(lvt_25_1_.nextLong(), p_180704_3_, p_180704_4_, p_180704_5_, p_180704_6_, p_180704_8_, p_180704_10_, lvt_25_1_.nextFloat() * 0.5F + 0.5F, p_180704_13_ + 1.5707964F, p_180704_14_ / 3.0F, p_180704_15_, p_180704_16_, 1.0D);
            return;
         }

         if (lvt_26_2_ || lvt_25_1_.nextInt(4) != 0) {
            double lvt_35_1_ = p_180704_6_ - lvt_19_1_;
            double lvt_37_1_ = p_180704_10_ - lvt_21_1_;
            double lvt_39_1_ = (double)(p_180704_16_ - p_180704_15_);
            double lvt_41_1_ = (double)(p_180704_12_ + 2.0F + 16.0F);
            if (lvt_35_1_ * lvt_35_1_ + lvt_37_1_ * lvt_37_1_ - lvt_39_1_ * lvt_39_1_ > lvt_41_1_ * lvt_41_1_) {
               return;
            }

            if (p_180704_6_ >= lvt_19_1_ - 16.0D - lvt_29_1_ * 2.0D && p_180704_10_ >= lvt_21_1_ - 16.0D - lvt_29_1_ * 2.0D && p_180704_6_ <= lvt_19_1_ + 16.0D + lvt_29_1_ * 2.0D && p_180704_10_ <= lvt_21_1_ + 16.0D + lvt_29_1_ * 2.0D) {
               int lvt_35_2_ = MathHelper.func_76128_c(p_180704_6_ - lvt_29_1_) - p_180704_3_ * 16 - 1;
               int lvt_36_1_ = MathHelper.func_76128_c(p_180704_6_ + lvt_29_1_) - p_180704_3_ * 16 + 1;
               int lvt_37_2_ = MathHelper.func_76128_c(p_180704_8_ - lvt_31_1_) - 1;
               int lvt_38_1_ = MathHelper.func_76128_c(p_180704_8_ + lvt_31_1_) + 1;
               int lvt_39_2_ = MathHelper.func_76128_c(p_180704_10_ - lvt_29_1_) - p_180704_4_ * 16 - 1;
               int lvt_40_1_ = MathHelper.func_76128_c(p_180704_10_ + lvt_29_1_) - p_180704_4_ * 16 + 1;
               if (lvt_35_2_ < 0) {
                  lvt_35_2_ = 0;
               }

               if (lvt_36_1_ > 16) {
                  lvt_36_1_ = 16;
               }

               if (lvt_37_2_ < 1) {
                  lvt_37_2_ = 1;
               }

               if (lvt_38_1_ > 120) {
                  lvt_38_1_ = 120;
               }

               if (lvt_39_2_ < 0) {
                  lvt_39_2_ = 0;
               }

               if (lvt_40_1_ > 16) {
                  lvt_40_1_ = 16;
               }

               boolean lvt_41_2_ = false;

               int lvt_42_2_;
               for(lvt_42_2_ = lvt_35_2_; !lvt_41_2_ && lvt_42_2_ < lvt_36_1_; ++lvt_42_2_) {
                  for(int lvt_43_1_ = lvt_39_2_; !lvt_41_2_ && lvt_43_1_ < lvt_40_1_; ++lvt_43_1_) {
                     for(int lvt_44_1_ = lvt_38_1_ + 1; !lvt_41_2_ && lvt_44_1_ >= lvt_37_2_ - 1; --lvt_44_1_) {
                        if (lvt_44_1_ >= 0 && lvt_44_1_ < 128) {
                           IBlockState lvt_45_1_ = p_180704_5_.func_177856_a(lvt_42_2_, lvt_44_1_, lvt_43_1_);
                           if (lvt_45_1_.func_177230_c() == Blocks.field_150356_k || lvt_45_1_.func_177230_c() == Blocks.field_150353_l) {
                              lvt_41_2_ = true;
                           }

                           if (lvt_44_1_ != lvt_37_2_ - 1 && lvt_42_2_ != lvt_35_2_ && lvt_42_2_ != lvt_36_1_ - 1 && lvt_43_1_ != lvt_39_2_ && lvt_43_1_ != lvt_40_1_ - 1) {
                              lvt_44_1_ = lvt_37_2_;
                           }
                        }
                     }
                  }
               }

               if (!lvt_41_2_) {
                  for(lvt_42_2_ = lvt_35_2_; lvt_42_2_ < lvt_36_1_; ++lvt_42_2_) {
                     double lvt_43_2_ = ((double)(lvt_42_2_ + p_180704_3_ * 16) + 0.5D - p_180704_6_) / lvt_29_1_;

                     for(int lvt_45_2_ = lvt_39_2_; lvt_45_2_ < lvt_40_1_; ++lvt_45_2_) {
                        double lvt_46_1_ = ((double)(lvt_45_2_ + p_180704_4_ * 16) + 0.5D - p_180704_10_) / lvt_29_1_;

                        for(int lvt_48_1_ = lvt_38_1_; lvt_48_1_ > lvt_37_2_; --lvt_48_1_) {
                           double lvt_49_1_ = ((double)(lvt_48_1_ - 1) + 0.5D - p_180704_8_) / lvt_31_1_;
                           if (lvt_49_1_ > -0.7D && lvt_43_2_ * lvt_43_2_ + lvt_49_1_ * lvt_49_1_ + lvt_46_1_ * lvt_46_1_ < 1.0D) {
                              IBlockState lvt_51_1_ = p_180704_5_.func_177856_a(lvt_42_2_, lvt_48_1_, lvt_45_2_);
                              if (lvt_51_1_.func_177230_c() == Blocks.field_150424_aL || lvt_51_1_.func_177230_c() == Blocks.field_150346_d || lvt_51_1_.func_177230_c() == Blocks.field_150349_c) {
                                 p_180704_5_.func_177855_a(lvt_42_2_, lvt_48_1_, lvt_45_2_, Blocks.field_150350_a.func_176223_P());
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
      int lvt_7_1_ = this.field_75038_b.nextInt(this.field_75038_b.nextInt(this.field_75038_b.nextInt(10) + 1) + 1);
      if (this.field_75038_b.nextInt(5) != 0) {
         lvt_7_1_ = 0;
      }

      for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
         double lvt_9_1_ = (double)(p_180701_2_ * 16 + this.field_75038_b.nextInt(16));
         double lvt_11_1_ = (double)this.field_75038_b.nextInt(128);
         double lvt_13_1_ = (double)(p_180701_3_ * 16 + this.field_75038_b.nextInt(16));
         int lvt_15_1_ = 1;
         if (this.field_75038_b.nextInt(4) == 0) {
            this.func_180705_a(this.field_75038_b.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, lvt_9_1_, lvt_11_1_, lvt_13_1_);
            lvt_15_1_ += this.field_75038_b.nextInt(4);
         }

         for(int lvt_16_1_ = 0; lvt_16_1_ < lvt_15_1_; ++lvt_16_1_) {
            float lvt_17_1_ = this.field_75038_b.nextFloat() * 3.1415927F * 2.0F;
            float lvt_18_1_ = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float lvt_19_1_ = this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat();
            this.func_180704_a(this.field_75038_b.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_19_1_ * 2.0F, lvt_17_1_, lvt_18_1_, 0, 0, 0.5D);
         }
      }

   }
}
