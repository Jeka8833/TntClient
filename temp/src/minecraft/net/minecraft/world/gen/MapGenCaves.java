package net.minecraft.world.gen;

import com.google.common.base.Objects;
import java.util.Random;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenCaves extends MapGenBase {
   protected void func_180703_a(long p_180703_1_, int p_180703_3_, int p_180703_4_, ChunkPrimer p_180703_5_, double p_180703_6_, double p_180703_8_, double p_180703_10_) {
      this.func_180702_a(p_180703_1_, p_180703_3_, p_180703_4_, p_180703_5_, p_180703_6_, p_180703_8_, p_180703_10_, 1.0F + this.field_75038_b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
   }

   protected void func_180702_a(long p_180702_1_, int p_180702_3_, int p_180702_4_, ChunkPrimer p_180702_5_, double p_180702_6_, double p_180702_8_, double p_180702_10_, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_) {
      double lvt_19_1_ = (double)(p_180702_3_ * 16 + 8);
      double lvt_21_1_ = (double)(p_180702_4_ * 16 + 8);
      float lvt_23_1_ = 0.0F;
      float lvt_24_1_ = 0.0F;
      Random lvt_25_1_ = new Random(p_180702_1_);
      if (p_180702_16_ <= 0) {
         int lvt_26_1_ = this.field_75040_a * 16 - 16;
         p_180702_16_ = lvt_26_1_ - lvt_25_1_.nextInt(lvt_26_1_ / 4);
      }

      boolean lvt_26_2_ = false;
      if (p_180702_15_ == -1) {
         p_180702_15_ = p_180702_16_ / 2;
         lvt_26_2_ = true;
      }

      int lvt_27_1_ = lvt_25_1_.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;

      for(boolean lvt_28_1_ = lvt_25_1_.nextInt(6) == 0; p_180702_15_ < p_180702_16_; ++p_180702_15_) {
         double lvt_29_1_ = 1.5D + (double)(MathHelper.func_76126_a((float)p_180702_15_ * 3.1415927F / (float)p_180702_16_) * p_180702_12_ * 1.0F);
         double lvt_31_1_ = lvt_29_1_ * p_180702_17_;
         float lvt_33_1_ = MathHelper.func_76134_b(p_180702_14_);
         float lvt_34_1_ = MathHelper.func_76126_a(p_180702_14_);
         p_180702_6_ += (double)(MathHelper.func_76134_b(p_180702_13_) * lvt_33_1_);
         p_180702_8_ += (double)lvt_34_1_;
         p_180702_10_ += (double)(MathHelper.func_76126_a(p_180702_13_) * lvt_33_1_);
         if (lvt_28_1_) {
            p_180702_14_ *= 0.92F;
         } else {
            p_180702_14_ *= 0.7F;
         }

         p_180702_14_ += lvt_24_1_ * 0.1F;
         p_180702_13_ += lvt_23_1_ * 0.1F;
         lvt_24_1_ *= 0.9F;
         lvt_23_1_ *= 0.75F;
         lvt_24_1_ += (lvt_25_1_.nextFloat() - lvt_25_1_.nextFloat()) * lvt_25_1_.nextFloat() * 2.0F;
         lvt_23_1_ += (lvt_25_1_.nextFloat() - lvt_25_1_.nextFloat()) * lvt_25_1_.nextFloat() * 4.0F;
         if (!lvt_26_2_ && p_180702_15_ == lvt_27_1_ && p_180702_12_ > 1.0F && p_180702_16_ > 0) {
            this.func_180702_a(lvt_25_1_.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, lvt_25_1_.nextFloat() * 0.5F + 0.5F, p_180702_13_ - 1.5707964F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
            this.func_180702_a(lvt_25_1_.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, lvt_25_1_.nextFloat() * 0.5F + 0.5F, p_180702_13_ + 1.5707964F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
            return;
         }

         if (lvt_26_2_ || lvt_25_1_.nextInt(4) != 0) {
            double lvt_35_1_ = p_180702_6_ - lvt_19_1_;
            double lvt_37_1_ = p_180702_10_ - lvt_21_1_;
            double lvt_39_1_ = (double)(p_180702_16_ - p_180702_15_);
            double lvt_41_1_ = (double)(p_180702_12_ + 2.0F + 16.0F);
            if (lvt_35_1_ * lvt_35_1_ + lvt_37_1_ * lvt_37_1_ - lvt_39_1_ * lvt_39_1_ > lvt_41_1_ * lvt_41_1_) {
               return;
            }

            if (p_180702_6_ >= lvt_19_1_ - 16.0D - lvt_29_1_ * 2.0D && p_180702_10_ >= lvt_21_1_ - 16.0D - lvt_29_1_ * 2.0D && p_180702_6_ <= lvt_19_1_ + 16.0D + lvt_29_1_ * 2.0D && p_180702_10_ <= lvt_21_1_ + 16.0D + lvt_29_1_ * 2.0D) {
               int lvt_35_2_ = MathHelper.func_76128_c(p_180702_6_ - lvt_29_1_) - p_180702_3_ * 16 - 1;
               int lvt_36_1_ = MathHelper.func_76128_c(p_180702_6_ + lvt_29_1_) - p_180702_3_ * 16 + 1;
               int lvt_37_2_ = MathHelper.func_76128_c(p_180702_8_ - lvt_31_1_) - 1;
               int lvt_38_1_ = MathHelper.func_76128_c(p_180702_8_ + lvt_31_1_) + 1;
               int lvt_39_2_ = MathHelper.func_76128_c(p_180702_10_ - lvt_29_1_) - p_180702_4_ * 16 - 1;
               int lvt_40_1_ = MathHelper.func_76128_c(p_180702_10_ + lvt_29_1_) - p_180702_4_ * 16 + 1;
               if (lvt_35_2_ < 0) {
                  lvt_35_2_ = 0;
               }

               if (lvt_36_1_ > 16) {
                  lvt_36_1_ = 16;
               }

               if (lvt_37_2_ < 1) {
                  lvt_37_2_ = 1;
               }

               if (lvt_38_1_ > 248) {
                  lvt_38_1_ = 248;
               }

               if (lvt_39_2_ < 0) {
                  lvt_39_2_ = 0;
               }

               if (lvt_40_1_ > 16) {
                  lvt_40_1_ = 16;
               }

               boolean lvt_41_2_ = false;

               int lvt_43_2_;
               for(int lvt_42_1_ = lvt_35_2_; !lvt_41_2_ && lvt_42_1_ < lvt_36_1_; ++lvt_42_1_) {
                  for(lvt_43_2_ = lvt_39_2_; !lvt_41_2_ && lvt_43_2_ < lvt_40_1_; ++lvt_43_2_) {
                     for(int lvt_44_1_ = lvt_38_1_ + 1; !lvt_41_2_ && lvt_44_1_ >= lvt_37_2_ - 1; --lvt_44_1_) {
                        if (lvt_44_1_ >= 0 && lvt_44_1_ < 256) {
                           IBlockState lvt_45_1_ = p_180702_5_.func_177856_a(lvt_42_1_, lvt_44_1_, lvt_43_2_);
                           if (lvt_45_1_.func_177230_c() == Blocks.field_150358_i || lvt_45_1_.func_177230_c() == Blocks.field_150355_j) {
                              lvt_41_2_ = true;
                           }

                           if (lvt_44_1_ != lvt_37_2_ - 1 && lvt_42_1_ != lvt_35_2_ && lvt_42_1_ != lvt_36_1_ - 1 && lvt_43_2_ != lvt_39_2_ && lvt_43_2_ != lvt_40_1_ - 1) {
                              lvt_44_1_ = lvt_37_2_;
                           }
                        }
                     }
                  }
               }

               if (!lvt_41_2_) {
                  BlockPos.MutableBlockPos lvt_42_2_ = new BlockPos.MutableBlockPos();

                  for(lvt_43_2_ = lvt_35_2_; lvt_43_2_ < lvt_36_1_; ++lvt_43_2_) {
                     double lvt_44_2_ = ((double)(lvt_43_2_ + p_180702_3_ * 16) + 0.5D - p_180702_6_) / lvt_29_1_;

                     for(int lvt_46_1_ = lvt_39_2_; lvt_46_1_ < lvt_40_1_; ++lvt_46_1_) {
                        double lvt_47_1_ = ((double)(lvt_46_1_ + p_180702_4_ * 16) + 0.5D - p_180702_10_) / lvt_29_1_;
                        boolean lvt_49_1_ = false;
                        if (lvt_44_2_ * lvt_44_2_ + lvt_47_1_ * lvt_47_1_ < 1.0D) {
                           for(int lvt_50_1_ = lvt_38_1_; lvt_50_1_ > lvt_37_2_; --lvt_50_1_) {
                              double lvt_51_1_ = ((double)(lvt_50_1_ - 1) + 0.5D - p_180702_8_) / lvt_31_1_;
                              if (lvt_51_1_ > -0.7D && lvt_44_2_ * lvt_44_2_ + lvt_51_1_ * lvt_51_1_ + lvt_47_1_ * lvt_47_1_ < 1.0D) {
                                 IBlockState lvt_53_1_ = p_180702_5_.func_177856_a(lvt_43_2_, lvt_50_1_, lvt_46_1_);
                                 IBlockState lvt_54_1_ = (IBlockState)Objects.firstNonNull(p_180702_5_.func_177856_a(lvt_43_2_, lvt_50_1_ + 1, lvt_46_1_), Blocks.field_150350_a.func_176223_P());
                                 if (lvt_53_1_.func_177230_c() == Blocks.field_150349_c || lvt_53_1_.func_177230_c() == Blocks.field_150391_bh) {
                                    lvt_49_1_ = true;
                                 }

                                 if (this.func_175793_a(lvt_53_1_, lvt_54_1_)) {
                                    if (lvt_50_1_ - 1 < 10) {
                                       p_180702_5_.func_177855_a(lvt_43_2_, lvt_50_1_, lvt_46_1_, Blocks.field_150353_l.func_176223_P());
                                    } else {
                                       p_180702_5_.func_177855_a(lvt_43_2_, lvt_50_1_, lvt_46_1_, Blocks.field_150350_a.func_176223_P());
                                       if (lvt_54_1_.func_177230_c() == Blocks.field_150354_m) {
                                          p_180702_5_.func_177855_a(lvt_43_2_, lvt_50_1_ + 1, lvt_46_1_, lvt_54_1_.func_177229_b(BlockSand.field_176504_a) == BlockSand.EnumType.RED_SAND ? Blocks.field_180395_cM.func_176223_P() : Blocks.field_150322_A.func_176223_P());
                                       }

                                       if (lvt_49_1_ && p_180702_5_.func_177856_a(lvt_43_2_, lvt_50_1_ - 1, lvt_46_1_).func_177230_c() == Blocks.field_150346_d) {
                                          lvt_42_2_.func_181079_c(lvt_43_2_ + p_180702_3_ * 16, 0, lvt_46_1_ + p_180702_4_ * 16);
                                          p_180702_5_.func_177855_a(lvt_43_2_, lvt_50_1_ - 1, lvt_46_1_, this.field_75039_c.func_180494_b(lvt_42_2_).field_76752_A.func_177230_c().func_176223_P());
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

   protected boolean func_175793_a(IBlockState p_175793_1_, IBlockState p_175793_2_) {
      if (p_175793_1_.func_177230_c() == Blocks.field_150348_b) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150346_d) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150349_c) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150405_ch) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150406_ce) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150322_A) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_180395_cM) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150391_bh) {
         return true;
      } else if (p_175793_1_.func_177230_c() == Blocks.field_150431_aC) {
         return true;
      } else {
         return (p_175793_1_.func_177230_c() == Blocks.field_150354_m || p_175793_1_.func_177230_c() == Blocks.field_150351_n) && p_175793_2_.func_177230_c().func_149688_o() != Material.field_151586_h;
      }
   }

   protected void func_180701_a(World p_180701_1_, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
      int lvt_7_1_ = this.field_75038_b.nextInt(this.field_75038_b.nextInt(this.field_75038_b.nextInt(15) + 1) + 1);
      if (this.field_75038_b.nextInt(7) != 0) {
         lvt_7_1_ = 0;
      }

      for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
         double lvt_9_1_ = (double)(p_180701_2_ * 16 + this.field_75038_b.nextInt(16));
         double lvt_11_1_ = (double)this.field_75038_b.nextInt(this.field_75038_b.nextInt(120) + 8);
         double lvt_13_1_ = (double)(p_180701_3_ * 16 + this.field_75038_b.nextInt(16));
         int lvt_15_1_ = 1;
         if (this.field_75038_b.nextInt(4) == 0) {
            this.func_180703_a(this.field_75038_b.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, lvt_9_1_, lvt_11_1_, lvt_13_1_);
            lvt_15_1_ += this.field_75038_b.nextInt(4);
         }

         for(int lvt_16_1_ = 0; lvt_16_1_ < lvt_15_1_; ++lvt_16_1_) {
            float lvt_17_1_ = this.field_75038_b.nextFloat() * 3.1415927F * 2.0F;
            float lvt_18_1_ = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float lvt_19_1_ = this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat();
            if (this.field_75038_b.nextInt(10) == 0) {
               lvt_19_1_ *= this.field_75038_b.nextFloat() * this.field_75038_b.nextFloat() * 3.0F + 1.0F;
            }

            this.func_180702_a(this.field_75038_b.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_19_1_, lvt_17_1_, lvt_18_1_, 0, 0, 1.0D);
         }
      }

   }
}
