package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnchantmentTable extends BlockContainer {
   protected BlockEnchantmentTable() {
      super(Material.field_151576_e, MapColor.field_151645_D);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.func_149713_g(0);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      super.func_180655_c(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);

      for(int lvt_5_1_ = -2; lvt_5_1_ <= 2; ++lvt_5_1_) {
         for(int lvt_6_1_ = -2; lvt_6_1_ <= 2; ++lvt_6_1_) {
            if (lvt_5_1_ > -2 && lvt_5_1_ < 2 && lvt_6_1_ == -1) {
               lvt_6_1_ = 2;
            }

            if (p_180655_4_.nextInt(16) == 0) {
               for(int lvt_7_1_ = 0; lvt_7_1_ <= 1; ++lvt_7_1_) {
                  BlockPos lvt_8_1_ = p_180655_2_.func_177982_a(lvt_5_1_, lvt_7_1_, lvt_6_1_);
                  if (p_180655_1_.func_180495_p(lvt_8_1_).func_177230_c() == Blocks.field_150342_X) {
                     if (!p_180655_1_.func_175623_d(p_180655_2_.func_177982_a(lvt_5_1_ / 2, 0, lvt_6_1_ / 2))) {
                        break;
                     }

                     p_180655_1_.func_175688_a(EnumParticleTypes.ENCHANTMENT_TABLE, (double)p_180655_2_.func_177958_n() + 0.5D, (double)p_180655_2_.func_177956_o() + 2.0D, (double)p_180655_2_.func_177952_p() + 0.5D, (double)((float)lvt_5_1_ + p_180655_4_.nextFloat()) - 0.5D, (double)((float)lvt_7_1_ - p_180655_4_.nextFloat() - 1.0F), (double)((float)lvt_6_1_ + p_180655_4_.nextFloat()) - 0.5D);
                  }
               }
            }
         }
      }

   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return 3;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityEnchantmentTable();
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity lvt_9_1_ = p_180639_1_.func_175625_s(p_180639_2_);
         if (lvt_9_1_ instanceof TileEntityEnchantmentTable) {
            p_180639_4_.func_180468_a((TileEntityEnchantmentTable)lvt_9_1_);
         }

         return true;
      }
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      super.func_180633_a(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
      if (p_180633_5_.func_82837_s()) {
         TileEntity lvt_6_1_ = p_180633_1_.func_175625_s(p_180633_2_);
         if (lvt_6_1_ instanceof TileEntityEnchantmentTable) {
            ((TileEntityEnchantmentTable)lvt_6_1_).func_145920_a(p_180633_5_.func_82833_r());
         }
      }

   }
}
