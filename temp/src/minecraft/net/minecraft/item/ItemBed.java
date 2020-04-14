package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {
   public ItemBed() {
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (p_180614_3_.field_72995_K) {
         return true;
      } else if (p_180614_5_ != EnumFacing.UP) {
         return false;
      } else {
         IBlockState lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_);
         Block lvt_10_1_ = lvt_9_1_.func_177230_c();
         boolean lvt_11_1_ = lvt_10_1_.func_176200_f(p_180614_3_, p_180614_4_);
         if (!lvt_11_1_) {
            p_180614_4_ = p_180614_4_.func_177984_a();
         }

         int lvt_12_1_ = MathHelper.func_76128_c((double)(p_180614_2_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
         EnumFacing lvt_13_1_ = EnumFacing.func_176731_b(lvt_12_1_);
         BlockPos lvt_14_1_ = p_180614_4_.func_177972_a(lvt_13_1_);
         if (p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_) && p_180614_2_.func_175151_a(lvt_14_1_, p_180614_5_, p_180614_1_)) {
            boolean lvt_15_1_ = p_180614_3_.func_180495_p(lvt_14_1_).func_177230_c().func_176200_f(p_180614_3_, lvt_14_1_);
            boolean lvt_16_1_ = lvt_11_1_ || p_180614_3_.func_175623_d(p_180614_4_);
            boolean lvt_17_1_ = lvt_15_1_ || p_180614_3_.func_175623_d(lvt_14_1_);
            if (lvt_16_1_ && lvt_17_1_ && World.func_175683_a(p_180614_3_, p_180614_4_.func_177977_b()) && World.func_175683_a(p_180614_3_, lvt_14_1_.func_177977_b())) {
               IBlockState lvt_18_1_ = Blocks.field_150324_C.func_176223_P().func_177226_a(BlockBed.field_176471_b, false).func_177226_a(BlockBed.field_176387_N, lvt_13_1_).func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.FOOT);
               if (p_180614_3_.func_180501_a(p_180614_4_, lvt_18_1_, 3)) {
                  IBlockState lvt_19_1_ = lvt_18_1_.func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.HEAD);
                  p_180614_3_.func_180501_a(lvt_14_1_, lvt_19_1_, 3);
               }

               --p_180614_1_.field_77994_a;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }
}
