package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenForest extends WorldGenAbstractTree {
   private static final IBlockState field_181629_a;
   private static final IBlockState field_181630_b;
   private boolean field_150531_a;

   public WorldGenForest(boolean p_i45449_1_, boolean p_i45449_2_) {
      super(p_i45449_1_);
      this.field_150531_a = p_i45449_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(3) + 5;
      if (this.field_150531_a) {
         lvt_4_1_ += p_180709_2_.nextInt(7);
      }

      boolean lvt_5_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         int lvt_9_2_;
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

            for(lvt_9_2_ = p_180709_3_.func_177958_n() - lvt_7_1_; lvt_9_2_ <= p_180709_3_.func_177958_n() + lvt_7_1_ && lvt_5_1_; ++lvt_9_2_) {
               for(lvt_10_2_ = p_180709_3_.func_177952_p() - lvt_7_1_; lvt_10_2_ <= p_180709_3_.func_177952_p() + lvt_7_1_ && lvt_5_1_; ++lvt_10_2_) {
                  if (lvt_6_1_ >= 0 && lvt_6_1_ < 256) {
                     if (!this.func_150523_a(p_180709_1_.func_180495_p(lvt_8_1_.func_181079_c(lvt_9_2_, lvt_6_1_, lvt_10_2_)).func_177230_c())) {
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
            if ((lvt_6_2_ == Blocks.field_150349_c || lvt_6_2_ == Blocks.field_150346_d || lvt_6_2_ == Blocks.field_150458_ak) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());

               int lvt_7_2_;
               for(lvt_7_2_ = p_180709_3_.func_177956_o() - 3 + lvt_4_1_; lvt_7_2_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_7_2_) {
                  int lvt_8_2_ = lvt_7_2_ - (p_180709_3_.func_177956_o() + lvt_4_1_);
                  lvt_9_2_ = 1 - lvt_8_2_ / 2;

                  for(lvt_10_2_ = p_180709_3_.func_177958_n() - lvt_9_2_; lvt_10_2_ <= p_180709_3_.func_177958_n() + lvt_9_2_; ++lvt_10_2_) {
                     int lvt_11_1_ = lvt_10_2_ - p_180709_3_.func_177958_n();

                     for(int lvt_12_1_ = p_180709_3_.func_177952_p() - lvt_9_2_; lvt_12_1_ <= p_180709_3_.func_177952_p() + lvt_9_2_; ++lvt_12_1_) {
                        int lvt_13_1_ = lvt_12_1_ - p_180709_3_.func_177952_p();
                        if (Math.abs(lvt_11_1_) != lvt_9_2_ || Math.abs(lvt_13_1_) != lvt_9_2_ || p_180709_2_.nextInt(2) != 0 && lvt_8_2_ != 0) {
                           BlockPos lvt_14_1_ = new BlockPos(lvt_10_2_, lvt_7_2_, lvt_12_1_);
                           Block lvt_15_1_ = p_180709_1_.func_180495_p(lvt_14_1_).func_177230_c();
                           if (lvt_15_1_.func_149688_o() == Material.field_151579_a || lvt_15_1_.func_149688_o() == Material.field_151584_j) {
                              this.func_175903_a(p_180709_1_, lvt_14_1_, field_181630_b);
                           }
                        }
                     }
                  }
               }

               for(lvt_7_2_ = 0; lvt_7_2_ < lvt_4_1_; ++lvt_7_2_) {
                  Block lvt_8_3_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_7_2_)).func_177230_c();
                  if (lvt_8_3_.func_149688_o() == Material.field_151579_a || lvt_8_3_.func_149688_o() == Material.field_151584_j) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_7_2_), field_181629_a);
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

   static {
      field_181629_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.BIRCH);
      field_181630_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.BIRCH).func_177226_a(BlockOldLeaf.field_176236_b, false);
   }
}
