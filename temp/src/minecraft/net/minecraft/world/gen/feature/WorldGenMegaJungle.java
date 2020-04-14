package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMegaJungle extends WorldGenHugeTrees {
   public WorldGenMegaJungle(boolean p_i46448_1_, int p_i46448_2_, int p_i46448_3_, IBlockState p_i46448_4_, IBlockState p_i46448_5_) {
      super(p_i46448_1_, p_i46448_2_, p_i46448_3_, p_i46448_4_, p_i46448_5_);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = this.func_150533_a(p_180709_2_);
      if (!this.func_175929_a(p_180709_1_, p_180709_2_, p_180709_3_, lvt_4_1_)) {
         return false;
      } else {
         this.func_175930_c(p_180709_1_, p_180709_3_.func_177981_b(lvt_4_1_), 2);

         for(int lvt_5_1_ = p_180709_3_.func_177956_o() + lvt_4_1_ - 2 - p_180709_2_.nextInt(4); lvt_5_1_ > p_180709_3_.func_177956_o() + lvt_4_1_ / 2; lvt_5_1_ -= 2 + p_180709_2_.nextInt(4)) {
            float lvt_6_1_ = p_180709_2_.nextFloat() * 3.1415927F * 2.0F;
            int lvt_7_1_ = p_180709_3_.func_177958_n() + (int)(0.5F + MathHelper.func_76134_b(lvt_6_1_) * 4.0F);
            int lvt_8_1_ = p_180709_3_.func_177952_p() + (int)(0.5F + MathHelper.func_76126_a(lvt_6_1_) * 4.0F);

            int lvt_9_2_;
            for(lvt_9_2_ = 0; lvt_9_2_ < 5; ++lvt_9_2_) {
               lvt_7_1_ = p_180709_3_.func_177958_n() + (int)(1.5F + MathHelper.func_76134_b(lvt_6_1_) * (float)lvt_9_2_);
               lvt_8_1_ = p_180709_3_.func_177952_p() + (int)(1.5F + MathHelper.func_76126_a(lvt_6_1_) * (float)lvt_9_2_);
               this.func_175903_a(p_180709_1_, new BlockPos(lvt_7_1_, lvt_5_1_ - 3 + lvt_9_2_ / 2, lvt_8_1_), this.field_76520_b);
            }

            lvt_9_2_ = 1 + p_180709_2_.nextInt(2);
            int lvt_10_1_ = lvt_5_1_;

            for(int lvt_11_1_ = lvt_5_1_ - lvt_9_2_; lvt_11_1_ <= lvt_10_1_; ++lvt_11_1_) {
               int lvt_12_1_ = lvt_11_1_ - lvt_10_1_;
               this.func_175928_b(p_180709_1_, new BlockPos(lvt_7_1_, lvt_11_1_, lvt_8_1_), 1 - lvt_12_1_);
            }
         }

         for(int lvt_6_2_ = 0; lvt_6_2_ < lvt_4_1_; ++lvt_6_2_) {
            BlockPos lvt_7_2_ = p_180709_3_.func_177981_b(lvt_6_2_);
            if (this.func_150523_a(p_180709_1_.func_180495_p(lvt_7_2_).func_177230_c())) {
               this.func_175903_a(p_180709_1_, lvt_7_2_, this.field_76520_b);
               if (lvt_6_2_ > 0) {
                  this.func_181632_a(p_180709_1_, p_180709_2_, lvt_7_2_.func_177976_e(), BlockVine.field_176278_M);
                  this.func_181632_a(p_180709_1_, p_180709_2_, lvt_7_2_.func_177978_c(), BlockVine.field_176279_N);
               }
            }

            if (lvt_6_2_ < lvt_4_1_ - 1) {
               BlockPos lvt_8_2_ = lvt_7_2_.func_177974_f();
               if (this.func_150523_a(p_180709_1_.func_180495_p(lvt_8_2_).func_177230_c())) {
                  this.func_175903_a(p_180709_1_, lvt_8_2_, this.field_76520_b);
                  if (lvt_6_2_ > 0) {
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_8_2_.func_177974_f(), BlockVine.field_176280_O);
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_8_2_.func_177978_c(), BlockVine.field_176279_N);
                  }
               }

               BlockPos lvt_9_3_ = lvt_7_2_.func_177968_d().func_177974_f();
               if (this.func_150523_a(p_180709_1_.func_180495_p(lvt_9_3_).func_177230_c())) {
                  this.func_175903_a(p_180709_1_, lvt_9_3_, this.field_76520_b);
                  if (lvt_6_2_ > 0) {
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_9_3_.func_177974_f(), BlockVine.field_176280_O);
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_9_3_.func_177968_d(), BlockVine.field_176273_b);
                  }
               }

               BlockPos lvt_10_2_ = lvt_7_2_.func_177968_d();
               if (this.func_150523_a(p_180709_1_.func_180495_p(lvt_10_2_).func_177230_c())) {
                  this.func_175903_a(p_180709_1_, lvt_10_2_, this.field_76520_b);
                  if (lvt_6_2_ > 0) {
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_10_2_.func_177976_e(), BlockVine.field_176278_M);
                     this.func_181632_a(p_180709_1_, p_180709_2_, lvt_10_2_.func_177968_d(), BlockVine.field_176273_b);
                  }
               }
            }
         }

         return true;
      }
   }

   private void func_181632_a(World p_181632_1_, Random p_181632_2_, BlockPos p_181632_3_, PropertyBool p_181632_4_) {
      if (p_181632_2_.nextInt(3) > 0 && p_181632_1_.func_175623_d(p_181632_3_)) {
         this.func_175903_a(p_181632_1_, p_181632_3_, Blocks.field_150395_bd.func_176223_P().func_177226_a(p_181632_4_, true));
      }

   }

   private void func_175930_c(World p_175930_1_, BlockPos p_175930_2_, int p_175930_3_) {
      int lvt_4_1_ = 2;

      for(int lvt_5_1_ = -lvt_4_1_; lvt_5_1_ <= 0; ++lvt_5_1_) {
         this.func_175925_a(p_175930_1_, p_175930_2_.func_177981_b(lvt_5_1_), p_175930_3_ + 1 - lvt_5_1_);
      }

   }
}
