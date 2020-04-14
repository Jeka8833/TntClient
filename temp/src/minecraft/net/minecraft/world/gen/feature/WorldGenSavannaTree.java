package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenSavannaTree extends WorldGenAbstractTree {
   private static final IBlockState field_181643_a;
   private static final IBlockState field_181644_b;

   public WorldGenSavannaTree(boolean p_i45463_1_) {
      super(p_i45463_1_);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(3) + p_180709_2_.nextInt(3) + 5;
      boolean lvt_5_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         int lvt_9_1_;
         int lvt_10_2_;
         for(int lvt_6_1_ = p_180709_3_.func_177956_o(); lvt_6_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_; ++lvt_6_1_) {
            int lvt_7_1_ = 1;
            if (lvt_6_1_ == p_180709_3_.func_177956_o()) {
               lvt_7_1_ = 0;
            }

            if (lvt_6_1_ >= p_180709_3_.func_177956_o() + 1 + lvt_4_1_ - 2) {
               lvt_7_1_ = 2;
            }

            BlockPos.MutableBlockPos lvt_8_1_ = new BlockPos.MutableBlockPos();

            for(lvt_9_1_ = p_180709_3_.func_177958_n() - lvt_7_1_; lvt_9_1_ <= p_180709_3_.func_177958_n() + lvt_7_1_ && lvt_5_1_; ++lvt_9_1_) {
               for(lvt_10_2_ = p_180709_3_.func_177952_p() - lvt_7_1_; lvt_10_2_ <= p_180709_3_.func_177952_p() + lvt_7_1_ && lvt_5_1_; ++lvt_10_2_) {
                  if (lvt_6_1_ >= 0 && lvt_6_1_ < 256) {
                     if (!this.func_150523_a(p_180709_1_.func_180495_p(lvt_8_1_.func_181079_c(lvt_9_1_, lvt_6_1_, lvt_10_2_)).func_177230_c())) {
                        lvt_5_1_ = false;
                     }
                  } else {
                     lvt_5_1_ = false;
                  }
               }
            }
         }

         if (!lvt_5_1_) {
            return false;
         } else {
            Block lvt_6_2_ = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
            if ((lvt_6_2_ == Blocks.field_150349_c || lvt_6_2_ == Blocks.field_150346_d) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());
               EnumFacing lvt_7_2_ = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_180709_2_);
               int lvt_8_2_ = lvt_4_1_ - p_180709_2_.nextInt(4) - 1;
               lvt_9_1_ = 3 - p_180709_2_.nextInt(3);
               lvt_10_2_ = p_180709_3_.func_177958_n();
               int lvt_11_1_ = p_180709_3_.func_177952_p();
               int lvt_12_1_ = 0;

               int lvt_14_4_;
               for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_4_1_; ++lvt_13_1_) {
                  lvt_14_4_ = p_180709_3_.func_177956_o() + lvt_13_1_;
                  if (lvt_13_1_ >= lvt_8_2_ && lvt_9_1_ > 0) {
                     lvt_10_2_ += lvt_7_2_.func_82601_c();
                     lvt_11_1_ += lvt_7_2_.func_82599_e();
                     --lvt_9_1_;
                  }

                  BlockPos lvt_15_1_ = new BlockPos(lvt_10_2_, lvt_14_4_, lvt_11_1_);
                  Material lvt_16_1_ = p_180709_1_.func_180495_p(lvt_15_1_).func_177230_c().func_149688_o();
                  if (lvt_16_1_ == Material.field_151579_a || lvt_16_1_ == Material.field_151584_j) {
                     this.func_181642_b(p_180709_1_, lvt_15_1_);
                     lvt_12_1_ = lvt_14_4_;
                  }
               }

               BlockPos lvt_13_2_ = new BlockPos(lvt_10_2_, lvt_12_1_, lvt_11_1_);

               int lvt_15_4_;
               for(lvt_14_4_ = -3; lvt_14_4_ <= 3; ++lvt_14_4_) {
                  for(lvt_15_4_ = -3; lvt_15_4_ <= 3; ++lvt_15_4_) {
                     if (Math.abs(lvt_14_4_) != 3 || Math.abs(lvt_15_4_) != 3) {
                        this.func_175924_b(p_180709_1_, lvt_13_2_.func_177982_a(lvt_14_4_, 0, lvt_15_4_));
                     }
                  }
               }

               lvt_13_2_ = lvt_13_2_.func_177984_a();

               for(lvt_14_4_ = -1; lvt_14_4_ <= 1; ++lvt_14_4_) {
                  for(lvt_15_4_ = -1; lvt_15_4_ <= 1; ++lvt_15_4_) {
                     this.func_175924_b(p_180709_1_, lvt_13_2_.func_177982_a(lvt_14_4_, 0, lvt_15_4_));
                  }
               }

               this.func_175924_b(p_180709_1_, lvt_13_2_.func_177965_g(2));
               this.func_175924_b(p_180709_1_, lvt_13_2_.func_177985_f(2));
               this.func_175924_b(p_180709_1_, lvt_13_2_.func_177970_e(2));
               this.func_175924_b(p_180709_1_, lvt_13_2_.func_177964_d(2));
               lvt_10_2_ = p_180709_3_.func_177958_n();
               lvt_11_1_ = p_180709_3_.func_177952_p();
               EnumFacing lvt_13_3_ = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_180709_2_);
               if (lvt_13_3_ != lvt_7_2_) {
                  lvt_14_4_ = lvt_8_2_ - p_180709_2_.nextInt(2) - 1;
                  lvt_15_4_ = 1 + p_180709_2_.nextInt(3);
                  lvt_12_1_ = 0;

                  int lvt_17_3_;
                  for(int lvt_16_2_ = lvt_14_4_; lvt_16_2_ < lvt_4_1_ && lvt_15_4_ > 0; --lvt_15_4_) {
                     if (lvt_16_2_ >= 1) {
                        lvt_17_3_ = p_180709_3_.func_177956_o() + lvt_16_2_;
                        lvt_10_2_ += lvt_13_3_.func_82601_c();
                        lvt_11_1_ += lvt_13_3_.func_82599_e();
                        BlockPos lvt_18_1_ = new BlockPos(lvt_10_2_, lvt_17_3_, lvt_11_1_);
                        Material lvt_19_1_ = p_180709_1_.func_180495_p(lvt_18_1_).func_177230_c().func_149688_o();
                        if (lvt_19_1_ == Material.field_151579_a || lvt_19_1_ == Material.field_151584_j) {
                           this.func_181642_b(p_180709_1_, lvt_18_1_);
                           lvt_12_1_ = lvt_17_3_;
                        }
                     }

                     ++lvt_16_2_;
                  }

                  if (lvt_12_1_ > 0) {
                     BlockPos lvt_16_3_ = new BlockPos(lvt_10_2_, lvt_12_1_, lvt_11_1_);

                     int lvt_18_3_;
                     for(lvt_17_3_ = -2; lvt_17_3_ <= 2; ++lvt_17_3_) {
                        for(lvt_18_3_ = -2; lvt_18_3_ <= 2; ++lvt_18_3_) {
                           if (Math.abs(lvt_17_3_) != 2 || Math.abs(lvt_18_3_) != 2) {
                              this.func_175924_b(p_180709_1_, lvt_16_3_.func_177982_a(lvt_17_3_, 0, lvt_18_3_));
                           }
                        }
                     }

                     lvt_16_3_ = lvt_16_3_.func_177984_a();

                     for(lvt_17_3_ = -1; lvt_17_3_ <= 1; ++lvt_17_3_) {
                        for(lvt_18_3_ = -1; lvt_18_3_ <= 1; ++lvt_18_3_) {
                           this.func_175924_b(p_180709_1_, lvt_16_3_.func_177982_a(lvt_17_3_, 0, lvt_18_3_));
                        }
                     }
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void func_181642_b(World p_181642_1_, BlockPos p_181642_2_) {
      this.func_175903_a(p_181642_1_, p_181642_2_, field_181643_a);
   }

   private void func_175924_b(World p_175924_1_, BlockPos p_175924_2_) {
      Material lvt_3_1_ = p_175924_1_.func_180495_p(p_175924_2_).func_177230_c().func_149688_o();
      if (lvt_3_1_ == Material.field_151579_a || lvt_3_1_ == Material.field_151584_j) {
         this.func_175903_a(p_175924_1_, p_175924_2_, field_181644_b);
      }

   }

   static {
      field_181643_a = Blocks.field_150363_s.func_176223_P().func_177226_a(BlockNewLog.field_176300_b, BlockPlanks.EnumType.ACACIA);
      field_181644_b = Blocks.field_150361_u.func_176223_P().func_177226_a(BlockNewLeaf.field_176240_P, BlockPlanks.EnumType.ACACIA).func_177226_a(BlockLeaves.field_176236_b, false);
   }
}
