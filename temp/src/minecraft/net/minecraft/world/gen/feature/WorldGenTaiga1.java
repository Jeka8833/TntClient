package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTaiga1 extends WorldGenAbstractTree {
   private static final IBlockState field_181636_a;
   private static final IBlockState field_181637_b;

   public WorldGenTaiga1() {
      super(false);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(5) + 7;
      int lvt_5_1_ = lvt_4_1_ - p_180709_2_.nextInt(2) - 3;
      int lvt_6_1_ = lvt_4_1_ - lvt_5_1_;
      int lvt_7_1_ = 1 + p_180709_2_.nextInt(lvt_6_1_ + 1);
      boolean lvt_8_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         int lvt_12_2_;
         int lvt_13_2_;
         int lvt_10_2_;
         for(int lvt_9_1_ = p_180709_3_.func_177956_o(); lvt_9_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_ && lvt_8_1_; ++lvt_9_1_) {
            int lvt_10_1_ = true;
            if (lvt_9_1_ - p_180709_3_.func_177956_o() < lvt_5_1_) {
               lvt_10_2_ = 0;
            } else {
               lvt_10_2_ = lvt_7_1_;
            }

            BlockPos.MutableBlockPos lvt_11_1_ = new BlockPos.MutableBlockPos();

            for(lvt_12_2_ = p_180709_3_.func_177958_n() - lvt_10_2_; lvt_12_2_ <= p_180709_3_.func_177958_n() + lvt_10_2_ && lvt_8_1_; ++lvt_12_2_) {
               for(lvt_13_2_ = p_180709_3_.func_177952_p() - lvt_10_2_; lvt_13_2_ <= p_180709_3_.func_177952_p() + lvt_10_2_ && lvt_8_1_; ++lvt_13_2_) {
                  if (lvt_9_1_ >= 0 && lvt_9_1_ < 256) {
                     if (!this.func_150523_a(p_180709_1_.func_180495_p(lvt_11_1_.func_181079_c(lvt_12_2_, lvt_9_1_, lvt_13_2_)).func_177230_c())) {
                        lvt_8_1_ = false;
                     }
                  } else {
                     lvt_8_1_ = false;
                  }
               }
            }
         }

         if (!lvt_8_1_) {
            return false;
         } else {
            Block lvt_9_2_ = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
            if ((lvt_9_2_ == Blocks.field_150349_c || lvt_9_2_ == Blocks.field_150346_d) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());
               lvt_10_2_ = 0;

               int lvt_11_2_;
               for(lvt_11_2_ = p_180709_3_.func_177956_o() + lvt_4_1_; lvt_11_2_ >= p_180709_3_.func_177956_o() + lvt_5_1_; --lvt_11_2_) {
                  for(lvt_12_2_ = p_180709_3_.func_177958_n() - lvt_10_2_; lvt_12_2_ <= p_180709_3_.func_177958_n() + lvt_10_2_; ++lvt_12_2_) {
                     lvt_13_2_ = lvt_12_2_ - p_180709_3_.func_177958_n();

                     for(int lvt_14_1_ = p_180709_3_.func_177952_p() - lvt_10_2_; lvt_14_1_ <= p_180709_3_.func_177952_p() + lvt_10_2_; ++lvt_14_1_) {
                        int lvt_15_1_ = lvt_14_1_ - p_180709_3_.func_177952_p();
                        if (Math.abs(lvt_13_2_) != lvt_10_2_ || Math.abs(lvt_15_1_) != lvt_10_2_ || lvt_10_2_ <= 0) {
                           BlockPos lvt_16_1_ = new BlockPos(lvt_12_2_, lvt_11_2_, lvt_14_1_);
                           if (!p_180709_1_.func_180495_p(lvt_16_1_).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, lvt_16_1_, field_181637_b);
                           }
                        }
                     }
                  }

                  if (lvt_10_2_ >= 1 && lvt_11_2_ == p_180709_3_.func_177956_o() + lvt_5_1_ + 1) {
                     --lvt_10_2_;
                  } else if (lvt_10_2_ < lvt_7_1_) {
                     ++lvt_10_2_;
                  }
               }

               for(lvt_11_2_ = 0; lvt_11_2_ < lvt_4_1_ - 1; ++lvt_11_2_) {
                  Block lvt_12_3_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_11_2_)).func_177230_c();
                  if (lvt_12_3_.func_149688_o() == Material.field_151579_a || lvt_12_3_.func_149688_o() == Material.field_151584_j) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_11_2_), field_181636_a);
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
      field_181636_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.SPRUCE);
      field_181637_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.SPRUCE).func_177226_a(BlockLeaves.field_176236_b, false);
   }
}
