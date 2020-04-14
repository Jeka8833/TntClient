package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMegaPineTree extends WorldGenHugeTrees {
   private static final IBlockState field_181633_e;
   private static final IBlockState field_181634_f;
   private static final IBlockState field_181635_g;
   private boolean field_150542_e;

   public WorldGenMegaPineTree(boolean p_i45457_1_, boolean p_i45457_2_) {
      super(p_i45457_1_, 13, 15, field_181633_e, field_181634_f);
      this.field_150542_e = p_i45457_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = this.func_150533_a(p_180709_2_);
      if (!this.func_175929_a(p_180709_1_, p_180709_2_, p_180709_3_, lvt_4_1_)) {
         return false;
      } else {
         this.func_150541_c(p_180709_1_, p_180709_3_.func_177958_n(), p_180709_3_.func_177952_p(), p_180709_3_.func_177956_o() + lvt_4_1_, 0, p_180709_2_);

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            Block lvt_6_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_5_1_)).func_177230_c();
            if (lvt_6_1_.func_149688_o() == Material.field_151579_a || lvt_6_1_.func_149688_o() == Material.field_151584_j) {
               this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_5_1_), this.field_76520_b);
            }

            if (lvt_5_1_ < lvt_4_1_ - 1) {
               lvt_6_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(1, lvt_5_1_, 0)).func_177230_c();
               if (lvt_6_1_.func_149688_o() == Material.field_151579_a || lvt_6_1_.func_149688_o() == Material.field_151584_j) {
                  this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(1, lvt_5_1_, 0), this.field_76520_b);
               }

               lvt_6_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(1, lvt_5_1_, 1)).func_177230_c();
               if (lvt_6_1_.func_149688_o() == Material.field_151579_a || lvt_6_1_.func_149688_o() == Material.field_151584_j) {
                  this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(1, lvt_5_1_, 1), this.field_76520_b);
               }

               lvt_6_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(0, lvt_5_1_, 1)).func_177230_c();
               if (lvt_6_1_.func_149688_o() == Material.field_151579_a || lvt_6_1_.func_149688_o() == Material.field_151584_j) {
                  this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(0, lvt_5_1_, 1), this.field_76520_b);
               }
            }
         }

         return true;
      }
   }

   private void func_150541_c(World p_150541_1_, int p_150541_2_, int p_150541_3_, int p_150541_4_, int p_150541_5_, Random p_150541_6_) {
      int lvt_7_1_ = p_150541_6_.nextInt(5) + (this.field_150542_e ? this.field_76522_a : 3);
      int lvt_8_1_ = 0;

      for(int lvt_9_1_ = p_150541_4_ - lvt_7_1_; lvt_9_1_ <= p_150541_4_; ++lvt_9_1_) {
         int lvt_10_1_ = p_150541_4_ - lvt_9_1_;
         int lvt_11_1_ = p_150541_5_ + MathHelper.func_76141_d((float)lvt_10_1_ / (float)lvt_7_1_ * 3.5F);
         this.func_175925_a(p_150541_1_, new BlockPos(p_150541_2_, lvt_9_1_, p_150541_3_), lvt_11_1_ + (lvt_10_1_ > 0 && lvt_11_1_ == lvt_8_1_ && (lvt_9_1_ & 1) == 0 ? 1 : 0));
         lvt_8_1_ = lvt_11_1_;
      }

   }

   public void func_180711_a(World p_180711_1_, Random p_180711_2_, BlockPos p_180711_3_) {
      this.func_175933_b(p_180711_1_, p_180711_3_.func_177976_e().func_177978_c());
      this.func_175933_b(p_180711_1_, p_180711_3_.func_177965_g(2).func_177978_c());
      this.func_175933_b(p_180711_1_, p_180711_3_.func_177976_e().func_177970_e(2));
      this.func_175933_b(p_180711_1_, p_180711_3_.func_177965_g(2).func_177970_e(2));

      for(int lvt_4_1_ = 0; lvt_4_1_ < 5; ++lvt_4_1_) {
         int lvt_5_1_ = p_180711_2_.nextInt(64);
         int lvt_6_1_ = lvt_5_1_ % 8;
         int lvt_7_1_ = lvt_5_1_ / 8;
         if (lvt_6_1_ == 0 || lvt_6_1_ == 7 || lvt_7_1_ == 0 || lvt_7_1_ == 7) {
            this.func_175933_b(p_180711_1_, p_180711_3_.func_177982_a(-3 + lvt_6_1_, 0, -3 + lvt_7_1_));
         }
      }

   }

   private void func_175933_b(World p_175933_1_, BlockPos p_175933_2_) {
      for(int lvt_3_1_ = -2; lvt_3_1_ <= 2; ++lvt_3_1_) {
         for(int lvt_4_1_ = -2; lvt_4_1_ <= 2; ++lvt_4_1_) {
            if (Math.abs(lvt_3_1_) != 2 || Math.abs(lvt_4_1_) != 2) {
               this.func_175934_c(p_175933_1_, p_175933_2_.func_177982_a(lvt_3_1_, 0, lvt_4_1_));
            }
         }
      }

   }

   private void func_175934_c(World p_175934_1_, BlockPos p_175934_2_) {
      for(int lvt_3_1_ = 2; lvt_3_1_ >= -3; --lvt_3_1_) {
         BlockPos lvt_4_1_ = p_175934_2_.func_177981_b(lvt_3_1_);
         Block lvt_5_1_ = p_175934_1_.func_180495_p(lvt_4_1_).func_177230_c();
         if (lvt_5_1_ == Blocks.field_150349_c || lvt_5_1_ == Blocks.field_150346_d) {
            this.func_175903_a(p_175934_1_, lvt_4_1_, field_181635_g);
            break;
         }

         if (lvt_5_1_.func_149688_o() != Material.field_151579_a && lvt_3_1_ < 0) {
            break;
         }
      }

   }

   static {
      field_181633_e = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.SPRUCE);
      field_181634_f = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.SPRUCE).func_177226_a(BlockLeaves.field_176236_b, false);
      field_181635_g = Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.PODZOL);
   }
}
