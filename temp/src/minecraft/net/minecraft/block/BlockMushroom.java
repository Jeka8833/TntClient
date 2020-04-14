package net.minecraft.block;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockMushroom extends BlockBush implements IGrowable {
   protected BlockMushroom() {
      float lvt_1_1_ = 0.2F;
      this.func_149676_a(0.5F - lvt_1_1_, 0.0F, 0.5F - lvt_1_1_, 0.5F + lvt_1_1_, lvt_1_1_ * 2.0F, 0.5F + lvt_1_1_);
      this.func_149675_a(true);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (p_180650_4_.nextInt(25) == 0) {
         int lvt_5_1_ = 5;
         int lvt_6_1_ = true;
         Iterator lvt_7_1_ = BlockPos.func_177975_b(p_180650_2_.func_177982_a(-4, -1, -4), p_180650_2_.func_177982_a(4, 1, 4)).iterator();

         while(lvt_7_1_.hasNext()) {
            BlockPos lvt_8_1_ = (BlockPos)lvt_7_1_.next();
            if (p_180650_1_.func_180495_p(lvt_8_1_).func_177230_c() == this) {
               --lvt_5_1_;
               if (lvt_5_1_ <= 0) {
                  return;
               }
            }
         }

         BlockPos lvt_7_2_ = p_180650_2_.func_177982_a(p_180650_4_.nextInt(3) - 1, p_180650_4_.nextInt(2) - p_180650_4_.nextInt(2), p_180650_4_.nextInt(3) - 1);

         for(int lvt_8_2_ = 0; lvt_8_2_ < 4; ++lvt_8_2_) {
            if (p_180650_1_.func_175623_d(lvt_7_2_) && this.func_180671_f(p_180650_1_, lvt_7_2_, this.func_176223_P())) {
               p_180650_2_ = lvt_7_2_;
            }

            lvt_7_2_ = p_180650_2_.func_177982_a(p_180650_4_.nextInt(3) - 1, p_180650_4_.nextInt(2) - p_180650_4_.nextInt(2), p_180650_4_.nextInt(3) - 1);
         }

         if (p_180650_1_.func_175623_d(lvt_7_2_) && this.func_180671_f(p_180650_1_, lvt_7_2_, this.func_176223_P())) {
            p_180650_1_.func_180501_a(lvt_7_2_, this.func_176223_P(), 2);
         }
      }

   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return super.func_176196_c(p_176196_1_, p_176196_2_) && this.func_180671_f(p_176196_1_, p_176196_2_, this.func_176223_P());
   }

   protected boolean func_149854_a(Block p_149854_1_) {
      return p_149854_1_.func_149730_j();
   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      if (p_180671_2_.func_177956_o() >= 0 && p_180671_2_.func_177956_o() < 256) {
         IBlockState lvt_4_1_ = p_180671_1_.func_180495_p(p_180671_2_.func_177977_b());
         if (lvt_4_1_.func_177230_c() == Blocks.field_150391_bh) {
            return true;
         } else if (lvt_4_1_.func_177230_c() == Blocks.field_150346_d && lvt_4_1_.func_177229_b(BlockDirt.field_176386_a) == BlockDirt.DirtType.PODZOL) {
            return true;
         } else {
            return p_180671_1_.func_175699_k(p_180671_2_) < 13 && this.func_149854_a(lvt_4_1_.func_177230_c());
         }
      } else {
         return false;
      }
   }

   public boolean func_176485_d(World p_176485_1_, BlockPos p_176485_2_, IBlockState p_176485_3_, Random p_176485_4_) {
      p_176485_1_.func_175698_g(p_176485_2_);
      WorldGenerator lvt_5_1_ = null;
      if (this == Blocks.field_150338_P) {
         lvt_5_1_ = new WorldGenBigMushroom(Blocks.field_150420_aW);
      } else if (this == Blocks.field_150337_Q) {
         lvt_5_1_ = new WorldGenBigMushroom(Blocks.field_150419_aX);
      }

      if (lvt_5_1_ != null && lvt_5_1_.func_180709_b(p_176485_1_, p_176485_4_, p_176485_2_)) {
         return true;
      } else {
         p_176485_1_.func_180501_a(p_176485_2_, p_176485_3_, 3);
         return false;
      }
   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return true;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return (double)p_180670_2_.nextFloat() < 0.4D;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      this.func_176485_d(p_176474_1_, p_176474_3_, p_176474_4_, p_176474_2_);
   }
}
