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

public class WorldGenCanopyTree extends WorldGenAbstractTree {
   private static final IBlockState field_181640_a;
   private static final IBlockState field_181641_b;

   public WorldGenCanopyTree(boolean p_i45461_1_) {
      super(p_i45461_1_);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(3) + p_180709_2_.nextInt(2) + 6;
      int lvt_5_1_ = p_180709_3_.func_177958_n();
      int lvt_6_1_ = p_180709_3_.func_177956_o();
      int lvt_7_1_ = p_180709_3_.func_177952_p();
      if (lvt_6_1_ >= 1 && lvt_6_1_ + lvt_4_1_ + 1 < 256) {
         BlockPos lvt_8_1_ = p_180709_3_.func_177977_b();
         Block lvt_9_1_ = p_180709_1_.func_180495_p(lvt_8_1_).func_177230_c();
         if (lvt_9_1_ != Blocks.field_150349_c && lvt_9_1_ != Blocks.field_150346_d) {
            return false;
         } else if (!this.func_181638_a(p_180709_1_, p_180709_3_, lvt_4_1_)) {
            return false;
         } else {
            this.func_175921_a(p_180709_1_, lvt_8_1_);
            this.func_175921_a(p_180709_1_, lvt_8_1_.func_177974_f());
            this.func_175921_a(p_180709_1_, lvt_8_1_.func_177968_d());
            this.func_175921_a(p_180709_1_, lvt_8_1_.func_177968_d().func_177974_f());
            EnumFacing lvt_10_1_ = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_180709_2_);
            int lvt_11_1_ = lvt_4_1_ - p_180709_2_.nextInt(4);
            int lvt_12_1_ = 2 - p_180709_2_.nextInt(3);
            int lvt_13_1_ = lvt_5_1_;
            int lvt_14_1_ = lvt_7_1_;
            int lvt_15_1_ = lvt_6_1_ + lvt_4_1_ - 1;

            int lvt_16_4_;
            int lvt_17_4_;
            for(lvt_16_4_ = 0; lvt_16_4_ < lvt_4_1_; ++lvt_16_4_) {
               if (lvt_16_4_ >= lvt_11_1_ && lvt_12_1_ > 0) {
                  lvt_13_1_ += lvt_10_1_.func_82601_c();
                  lvt_14_1_ += lvt_10_1_.func_82599_e();
                  --lvt_12_1_;
               }

               lvt_17_4_ = lvt_6_1_ + lvt_16_4_;
               BlockPos lvt_18_1_ = new BlockPos(lvt_13_1_, lvt_17_4_, lvt_14_1_);
               Material lvt_19_1_ = p_180709_1_.func_180495_p(lvt_18_1_).func_177230_c().func_149688_o();
               if (lvt_19_1_ == Material.field_151579_a || lvt_19_1_ == Material.field_151584_j) {
                  this.func_181639_b(p_180709_1_, lvt_18_1_);
                  this.func_181639_b(p_180709_1_, lvt_18_1_.func_177974_f());
                  this.func_181639_b(p_180709_1_, lvt_18_1_.func_177968_d());
                  this.func_181639_b(p_180709_1_, lvt_18_1_.func_177974_f().func_177968_d());
               }
            }

            for(lvt_16_4_ = -2; lvt_16_4_ <= 0; ++lvt_16_4_) {
               for(lvt_17_4_ = -2; lvt_17_4_ <= 0; ++lvt_17_4_) {
                  int lvt_18_2_ = -1;
                  this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_, lvt_15_1_ + lvt_18_2_, lvt_14_1_ + lvt_17_4_);
                  this.func_150526_a(p_180709_1_, 1 + lvt_13_1_ - lvt_16_4_, lvt_15_1_ + lvt_18_2_, lvt_14_1_ + lvt_17_4_);
                  this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_, lvt_15_1_ + lvt_18_2_, 1 + lvt_14_1_ - lvt_17_4_);
                  this.func_150526_a(p_180709_1_, 1 + lvt_13_1_ - lvt_16_4_, lvt_15_1_ + lvt_18_2_, 1 + lvt_14_1_ - lvt_17_4_);
                  if ((lvt_16_4_ > -2 || lvt_17_4_ > -1) && (lvt_16_4_ != -1 || lvt_17_4_ != -2)) {
                     int lvt_18_2_ = 1;
                     this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_, lvt_15_1_ + lvt_18_2_, lvt_14_1_ + lvt_17_4_);
                     this.func_150526_a(p_180709_1_, 1 + lvt_13_1_ - lvt_16_4_, lvt_15_1_ + lvt_18_2_, lvt_14_1_ + lvt_17_4_);
                     this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_, lvt_15_1_ + lvt_18_2_, 1 + lvt_14_1_ - lvt_17_4_);
                     this.func_150526_a(p_180709_1_, 1 + lvt_13_1_ - lvt_16_4_, lvt_15_1_ + lvt_18_2_, 1 + lvt_14_1_ - lvt_17_4_);
                  }
               }
            }

            if (p_180709_2_.nextBoolean()) {
               this.func_150526_a(p_180709_1_, lvt_13_1_, lvt_15_1_ + 2, lvt_14_1_);
               this.func_150526_a(p_180709_1_, lvt_13_1_ + 1, lvt_15_1_ + 2, lvt_14_1_);
               this.func_150526_a(p_180709_1_, lvt_13_1_ + 1, lvt_15_1_ + 2, lvt_14_1_ + 1);
               this.func_150526_a(p_180709_1_, lvt_13_1_, lvt_15_1_ + 2, lvt_14_1_ + 1);
            }

            for(lvt_16_4_ = -3; lvt_16_4_ <= 4; ++lvt_16_4_) {
               for(lvt_17_4_ = -3; lvt_17_4_ <= 4; ++lvt_17_4_) {
                  if ((lvt_16_4_ != -3 || lvt_17_4_ != -3) && (lvt_16_4_ != -3 || lvt_17_4_ != 4) && (lvt_16_4_ != 4 || lvt_17_4_ != -3) && (lvt_16_4_ != 4 || lvt_17_4_ != 4) && (Math.abs(lvt_16_4_) < 3 || Math.abs(lvt_17_4_) < 3)) {
                     this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_, lvt_15_1_, lvt_14_1_ + lvt_17_4_);
                  }
               }
            }

            for(lvt_16_4_ = -1; lvt_16_4_ <= 2; ++lvt_16_4_) {
               for(lvt_17_4_ = -1; lvt_17_4_ <= 2; ++lvt_17_4_) {
                  if ((lvt_16_4_ < 0 || lvt_16_4_ > 1 || lvt_17_4_ < 0 || lvt_17_4_ > 1) && p_180709_2_.nextInt(3) <= 0) {
                     int lvt_18_3_ = p_180709_2_.nextInt(3) + 2;

                     int lvt_19_4_;
                     for(lvt_19_4_ = 0; lvt_19_4_ < lvt_18_3_; ++lvt_19_4_) {
                        this.func_181639_b(p_180709_1_, new BlockPos(lvt_5_1_ + lvt_16_4_, lvt_15_1_ - lvt_19_4_ - 1, lvt_7_1_ + lvt_17_4_));
                     }

                     int lvt_20_2_;
                     for(lvt_19_4_ = -1; lvt_19_4_ <= 1; ++lvt_19_4_) {
                        for(lvt_20_2_ = -1; lvt_20_2_ <= 1; ++lvt_20_2_) {
                           this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_ + lvt_19_4_, lvt_15_1_, lvt_14_1_ + lvt_17_4_ + lvt_20_2_);
                        }
                     }

                     for(lvt_19_4_ = -2; lvt_19_4_ <= 2; ++lvt_19_4_) {
                        for(lvt_20_2_ = -2; lvt_20_2_ <= 2; ++lvt_20_2_) {
                           if (Math.abs(lvt_19_4_) != 2 || Math.abs(lvt_20_2_) != 2) {
                              this.func_150526_a(p_180709_1_, lvt_13_1_ + lvt_16_4_ + lvt_19_4_, lvt_15_1_ - 1, lvt_14_1_ + lvt_17_4_ + lvt_20_2_);
                           }
                        }
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private boolean func_181638_a(World p_181638_1_, BlockPos p_181638_2_, int p_181638_3_) {
      int lvt_4_1_ = p_181638_2_.func_177958_n();
      int lvt_5_1_ = p_181638_2_.func_177956_o();
      int lvt_6_1_ = p_181638_2_.func_177952_p();
      BlockPos.MutableBlockPos lvt_7_1_ = new BlockPos.MutableBlockPos();

      for(int lvt_8_1_ = 0; lvt_8_1_ <= p_181638_3_ + 1; ++lvt_8_1_) {
         int lvt_9_1_ = 1;
         if (lvt_8_1_ == 0) {
            lvt_9_1_ = 0;
         }

         if (lvt_8_1_ >= p_181638_3_ - 1) {
            lvt_9_1_ = 2;
         }

         for(int lvt_10_1_ = -lvt_9_1_; lvt_10_1_ <= lvt_9_1_; ++lvt_10_1_) {
            for(int lvt_11_1_ = -lvt_9_1_; lvt_11_1_ <= lvt_9_1_; ++lvt_11_1_) {
               if (!this.func_150523_a(p_181638_1_.func_180495_p(lvt_7_1_.func_181079_c(lvt_4_1_ + lvt_10_1_, lvt_5_1_ + lvt_8_1_, lvt_6_1_ + lvt_11_1_)).func_177230_c())) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   private void func_181639_b(World p_181639_1_, BlockPos p_181639_2_) {
      if (this.func_150523_a(p_181639_1_.func_180495_p(p_181639_2_).func_177230_c())) {
         this.func_175903_a(p_181639_1_, p_181639_2_, field_181640_a);
      }

   }

   private void func_150526_a(World p_150526_1_, int p_150526_2_, int p_150526_3_, int p_150526_4_) {
      BlockPos lvt_5_1_ = new BlockPos(p_150526_2_, p_150526_3_, p_150526_4_);
      Block lvt_6_1_ = p_150526_1_.func_180495_p(lvt_5_1_).func_177230_c();
      if (lvt_6_1_.func_149688_o() == Material.field_151579_a) {
         this.func_175903_a(p_150526_1_, lvt_5_1_, field_181641_b);
      }

   }

   static {
      field_181640_a = Blocks.field_150363_s.func_176223_P().func_177226_a(BlockNewLog.field_176300_b, BlockPlanks.EnumType.DARK_OAK);
      field_181641_b = Blocks.field_150361_u.func_176223_P().func_177226_a(BlockNewLeaf.field_176240_P, BlockPlanks.EnumType.DARK_OAK).func_177226_a(BlockLeaves.field_176236_b, false);
   }
}
