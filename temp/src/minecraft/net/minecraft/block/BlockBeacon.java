package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class BlockBeacon extends BlockContainer {
   public BlockBeacon() {
      super(Material.field_151592_s, MapColor.field_151648_G);
      this.func_149711_c(3.0F);
      this.func_149647_a(CreativeTabs.field_78026_f);
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityBeacon();
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity lvt_9_1_ = p_180639_1_.func_175625_s(p_180639_2_);
         if (lvt_9_1_ instanceof TileEntityBeacon) {
            p_180639_4_.func_71007_a((TileEntityBeacon)lvt_9_1_);
            p_180639_4_.func_71029_a(StatList.field_181730_N);
         }

         return true;
      }
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public int func_149645_b() {
      return 3;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      super.func_180633_a(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
      if (p_180633_5_.func_82837_s()) {
         TileEntity lvt_6_1_ = p_180633_1_.func_175625_s(p_180633_2_);
         if (lvt_6_1_ instanceof TileEntityBeacon) {
            ((TileEntityBeacon)lvt_6_1_).func_145999_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      TileEntity lvt_5_1_ = p_176204_1_.func_175625_s(p_176204_2_);
      if (lvt_5_1_ instanceof TileEntityBeacon) {
         ((TileEntityBeacon)lvt_5_1_).func_174908_m();
         p_176204_1_.func_175641_c(p_176204_2_, this, 1, 0);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public static void func_176450_d(final World p_176450_0_, final BlockPos p_176450_1_) {
      HttpUtil.field_180193_a.submit(new Runnable() {
         public void run() {
            Chunk lvt_1_1_ = p_176450_0_.func_175726_f(p_176450_1_);

            for(int lvt_2_1_ = p_176450_1_.func_177956_o() - 1; lvt_2_1_ >= 0; --lvt_2_1_) {
               final BlockPos lvt_3_1_ = new BlockPos(p_176450_1_.func_177958_n(), lvt_2_1_, p_176450_1_.func_177952_p());
               if (!lvt_1_1_.func_177444_d(lvt_3_1_)) {
                  break;
               }

               IBlockState lvt_4_1_ = p_176450_0_.func_180495_p(lvt_3_1_);
               if (lvt_4_1_.func_177230_c() == Blocks.field_150461_bJ) {
                  ((WorldServer)p_176450_0_).func_152344_a(new Runnable() {
                     public void run() {
                        TileEntity lvt_1_1_ = p_176450_0_.func_175625_s(lvt_3_1_);
                        if (lvt_1_1_ instanceof TileEntityBeacon) {
                           ((TileEntityBeacon)lvt_1_1_).func_174908_m();
                           p_176450_0_.func_175641_c(lvt_3_1_, Blocks.field_150461_bJ, 1, 0);
                        }

                     }
                  });
               }
            }

         }
      });
   }
}
