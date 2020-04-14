package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSlab extends ItemBlock {
   private final BlockSlab field_150949_c;
   private final BlockSlab field_179226_c;

   public ItemSlab(Block p_i45782_1_, BlockSlab p_i45782_2_, BlockSlab p_i45782_3_) {
      super(p_i45782_1_);
      this.field_150949_c = p_i45782_2_;
      this.field_179226_c = p_i45782_3_;
      this.func_77656_e(0);
      this.func_77627_a(true);
   }

   public int func_77647_b(int p_77647_1_) {
      return p_77647_1_;
   }

   public String func_77667_c(ItemStack p_77667_1_) {
      return this.field_150949_c.func_150002_b(p_77667_1_.func_77960_j());
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (p_180614_1_.field_77994_a == 0) {
         return false;
      } else if (!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         Object lvt_9_1_ = this.field_150949_c.func_176553_a(p_180614_1_);
         IBlockState lvt_10_1_ = p_180614_3_.func_180495_p(p_180614_4_);
         if (lvt_10_1_.func_177230_c() == this.field_150949_c) {
            IProperty lvt_11_1_ = this.field_150949_c.func_176551_l();
            Comparable lvt_12_1_ = lvt_10_1_.func_177229_b(lvt_11_1_);
            BlockSlab.EnumBlockHalf lvt_13_1_ = (BlockSlab.EnumBlockHalf)lvt_10_1_.func_177229_b(BlockSlab.field_176554_a);
            if ((p_180614_5_ == EnumFacing.UP && lvt_13_1_ == BlockSlab.EnumBlockHalf.BOTTOM || p_180614_5_ == EnumFacing.DOWN && lvt_13_1_ == BlockSlab.EnumBlockHalf.TOP) && lvt_12_1_ == lvt_9_1_) {
               IBlockState lvt_14_1_ = this.field_179226_c.func_176223_P().func_177226_a(lvt_11_1_, lvt_12_1_);
               if (p_180614_3_.func_72855_b(this.field_179226_c.func_180640_a(p_180614_3_, p_180614_4_, lvt_14_1_)) && p_180614_3_.func_180501_a(p_180614_4_, lvt_14_1_, 3)) {
                  p_180614_3_.func_72908_a((double)((float)p_180614_4_.func_177958_n() + 0.5F), (double)((float)p_180614_4_.func_177956_o() + 0.5F), (double)((float)p_180614_4_.func_177952_p() + 0.5F), this.field_179226_c.field_149762_H.func_150496_b(), (this.field_179226_c.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_179226_c.field_149762_H.func_150494_d() * 0.8F);
                  --p_180614_1_.field_77994_a;
               }

               return true;
            }
         }

         return this.func_180615_a(p_180614_1_, p_180614_3_, p_180614_4_.func_177972_a(p_180614_5_), lvt_9_1_) ? true : super.func_180614_a(p_180614_1_, p_180614_2_, p_180614_3_, p_180614_4_, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_);
      }
   }

   public boolean func_179222_a(World p_179222_1_, BlockPos p_179222_2_, EnumFacing p_179222_3_, EntityPlayer p_179222_4_, ItemStack p_179222_5_) {
      BlockPos lvt_6_1_ = p_179222_2_;
      IProperty lvt_7_1_ = this.field_150949_c.func_176551_l();
      Object lvt_8_1_ = this.field_150949_c.func_176553_a(p_179222_5_);
      IBlockState lvt_9_1_ = p_179222_1_.func_180495_p(p_179222_2_);
      if (lvt_9_1_.func_177230_c() == this.field_150949_c) {
         boolean lvt_10_1_ = lvt_9_1_.func_177229_b(BlockSlab.field_176554_a) == BlockSlab.EnumBlockHalf.TOP;
         if ((p_179222_3_ == EnumFacing.UP && !lvt_10_1_ || p_179222_3_ == EnumFacing.DOWN && lvt_10_1_) && lvt_8_1_ == lvt_9_1_.func_177229_b(lvt_7_1_)) {
            return true;
         }
      }

      p_179222_2_ = p_179222_2_.func_177972_a(p_179222_3_);
      IBlockState lvt_10_2_ = p_179222_1_.func_180495_p(p_179222_2_);
      return lvt_10_2_.func_177230_c() == this.field_150949_c && lvt_8_1_ == lvt_10_2_.func_177229_b(lvt_7_1_) ? true : super.func_179222_a(p_179222_1_, lvt_6_1_, p_179222_3_, p_179222_4_, p_179222_5_);
   }

   private boolean func_180615_a(ItemStack p_180615_1_, World p_180615_2_, BlockPos p_180615_3_, Object p_180615_4_) {
      IBlockState lvt_5_1_ = p_180615_2_.func_180495_p(p_180615_3_);
      if (lvt_5_1_.func_177230_c() == this.field_150949_c) {
         Comparable lvt_6_1_ = lvt_5_1_.func_177229_b(this.field_150949_c.func_176551_l());
         if (lvt_6_1_ == p_180615_4_) {
            IBlockState lvt_7_1_ = this.field_179226_c.func_176223_P().func_177226_a(this.field_150949_c.func_176551_l(), lvt_6_1_);
            if (p_180615_2_.func_72855_b(this.field_179226_c.func_180640_a(p_180615_2_, p_180615_3_, lvt_7_1_)) && p_180615_2_.func_180501_a(p_180615_3_, lvt_7_1_, 3)) {
               p_180615_2_.func_72908_a((double)((float)p_180615_3_.func_177958_n() + 0.5F), (double)((float)p_180615_3_.func_177956_o() + 0.5F), (double)((float)p_180615_3_.func_177952_p() + 0.5F), this.field_179226_c.field_149762_H.func_150496_b(), (this.field_179226_c.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_179226_c.field_149762_H.func_150494_d() * 0.8F);
               --p_180615_1_.field_77994_a;
            }

            return true;
         }
      }

      return false;
   }
}
