package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenHugeTrees extends WorldGenAbstractTree {
   protected final int field_76522_a;
   protected final IBlockState field_76520_b;
   protected final IBlockState field_76521_c;
   protected int field_150538_d;

   public WorldGenHugeTrees(boolean p_i46447_1_, int p_i46447_2_, int p_i46447_3_, IBlockState p_i46447_4_, IBlockState p_i46447_5_) {
      super(p_i46447_1_);
      this.field_76522_a = p_i46447_2_;
      this.field_150538_d = p_i46447_3_;
      this.field_76520_b = p_i46447_4_;
      this.field_76521_c = p_i46447_5_;
   }

   protected int func_150533_a(Random p_150533_1_) {
      int lvt_2_1_ = p_150533_1_.nextInt(3) + this.field_76522_a;
      if (this.field_150538_d > 1) {
         lvt_2_1_ += p_150533_1_.nextInt(this.field_150538_d);
      }

      return lvt_2_1_;
   }

   private boolean func_175926_c(World p_175926_1_, BlockPos p_175926_2_, int p_175926_3_) {
      boolean lvt_4_1_ = true;
      if (p_175926_2_.func_177956_o() >= 1 && p_175926_2_.func_177956_o() + p_175926_3_ + 1 <= 256) {
         for(int lvt_5_1_ = 0; lvt_5_1_ <= 1 + p_175926_3_; ++lvt_5_1_) {
            int lvt_6_1_ = 2;
            if (lvt_5_1_ == 0) {
               lvt_6_1_ = 1;
            } else if (lvt_5_1_ >= 1 + p_175926_3_ - 2) {
               lvt_6_1_ = 2;
            }

            for(int lvt_7_1_ = -lvt_6_1_; lvt_7_1_ <= lvt_6_1_ && lvt_4_1_; ++lvt_7_1_) {
               for(int lvt_8_1_ = -lvt_6_1_; lvt_8_1_ <= lvt_6_1_ && lvt_4_1_; ++lvt_8_1_) {
                  if (p_175926_2_.func_177956_o() + lvt_5_1_ < 0 || p_175926_2_.func_177956_o() + lvt_5_1_ >= 256 || !this.func_150523_a(p_175926_1_.func_180495_p(p_175926_2_.func_177982_a(lvt_7_1_, lvt_5_1_, lvt_8_1_)).func_177230_c())) {
                     lvt_4_1_ = false;
                  }
               }
            }
         }

         return lvt_4_1_;
      } else {
         return false;
      }
   }

   private boolean func_175927_a(BlockPos p_175927_1_, World p_175927_2_) {
      BlockPos lvt_3_1_ = p_175927_1_.func_177977_b();
      Block lvt_4_1_ = p_175927_2_.func_180495_p(lvt_3_1_).func_177230_c();
      if ((lvt_4_1_ == Blocks.field_150349_c || lvt_4_1_ == Blocks.field_150346_d) && p_175927_1_.func_177956_o() >= 2) {
         this.func_175921_a(p_175927_2_, lvt_3_1_);
         this.func_175921_a(p_175927_2_, lvt_3_1_.func_177974_f());
         this.func_175921_a(p_175927_2_, lvt_3_1_.func_177968_d());
         this.func_175921_a(p_175927_2_, lvt_3_1_.func_177968_d().func_177974_f());
         return true;
      } else {
         return false;
      }
   }

   protected boolean func_175929_a(World p_175929_1_, Random p_175929_2_, BlockPos p_175929_3_, int p_175929_4_) {
      return this.func_175926_c(p_175929_1_, p_175929_3_, p_175929_4_) && this.func_175927_a(p_175929_3_, p_175929_1_);
   }

   protected void func_175925_a(World p_175925_1_, BlockPos p_175925_2_, int p_175925_3_) {
      int lvt_4_1_ = p_175925_3_ * p_175925_3_;

      for(int lvt_5_1_ = -p_175925_3_; lvt_5_1_ <= p_175925_3_ + 1; ++lvt_5_1_) {
         for(int lvt_6_1_ = -p_175925_3_; lvt_6_1_ <= p_175925_3_ + 1; ++lvt_6_1_) {
            int lvt_7_1_ = lvt_5_1_ - 1;
            int lvt_8_1_ = lvt_6_1_ - 1;
            if (lvt_5_1_ * lvt_5_1_ + lvt_6_1_ * lvt_6_1_ <= lvt_4_1_ || lvt_7_1_ * lvt_7_1_ + lvt_8_1_ * lvt_8_1_ <= lvt_4_1_ || lvt_5_1_ * lvt_5_1_ + lvt_8_1_ * lvt_8_1_ <= lvt_4_1_ || lvt_7_1_ * lvt_7_1_ + lvt_6_1_ * lvt_6_1_ <= lvt_4_1_) {
               BlockPos lvt_9_1_ = p_175925_2_.func_177982_a(lvt_5_1_, 0, lvt_6_1_);
               Material lvt_10_1_ = p_175925_1_.func_180495_p(lvt_9_1_).func_177230_c().func_149688_o();
               if (lvt_10_1_ == Material.field_151579_a || lvt_10_1_ == Material.field_151584_j) {
                  this.func_175903_a(p_175925_1_, lvt_9_1_, this.field_76521_c);
               }
            }
         }
      }

   }

   protected void func_175928_b(World p_175928_1_, BlockPos p_175928_2_, int p_175928_3_) {
      int lvt_4_1_ = p_175928_3_ * p_175928_3_;

      for(int lvt_5_1_ = -p_175928_3_; lvt_5_1_ <= p_175928_3_; ++lvt_5_1_) {
         for(int lvt_6_1_ = -p_175928_3_; lvt_6_1_ <= p_175928_3_; ++lvt_6_1_) {
            if (lvt_5_1_ * lvt_5_1_ + lvt_6_1_ * lvt_6_1_ <= lvt_4_1_) {
               BlockPos lvt_7_1_ = p_175928_2_.func_177982_a(lvt_5_1_, 0, lvt_6_1_);
               Material lvt_8_1_ = p_175928_1_.func_180495_p(lvt_7_1_).func_177230_c().func_149688_o();
               if (lvt_8_1_ == Material.field_151579_a || lvt_8_1_ == Material.field_151584_j) {
                  this.func_175903_a(p_175928_1_, lvt_7_1_, this.field_76521_c);
               }
            }
         }
      }

   }
}
