package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
   private static final List<String> field_176434_a = Lists.newArrayList((Object[])("harp", "bd", "snare", "hat", "bassattack"));

   public BlockNote() {
      super(Material.field_151575_d);
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      boolean lvt_5_1_ = p_176204_1_.func_175640_z(p_176204_2_);
      TileEntity lvt_6_1_ = p_176204_1_.func_175625_s(p_176204_2_);
      if (lvt_6_1_ instanceof TileEntityNote) {
         TileEntityNote lvt_7_1_ = (TileEntityNote)lvt_6_1_;
         if (lvt_7_1_.field_145880_i != lvt_5_1_) {
            if (lvt_5_1_) {
               lvt_7_1_.func_175108_a(p_176204_1_, p_176204_2_);
            }

            lvt_7_1_.field_145880_i = lvt_5_1_;
         }
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity lvt_9_1_ = p_180639_1_.func_175625_s(p_180639_2_);
         if (lvt_9_1_ instanceof TileEntityNote) {
            TileEntityNote lvt_10_1_ = (TileEntityNote)lvt_9_1_;
            lvt_10_1_.func_145877_a();
            lvt_10_1_.func_175108_a(p_180639_1_, p_180639_2_);
            p_180639_4_.func_71029_a(StatList.field_181735_S);
         }

         return true;
      }
   }

   public void func_180649_a(World p_180649_1_, BlockPos p_180649_2_, EntityPlayer p_180649_3_) {
      if (!p_180649_1_.field_72995_K) {
         TileEntity lvt_4_1_ = p_180649_1_.func_175625_s(p_180649_2_);
         if (lvt_4_1_ instanceof TileEntityNote) {
            ((TileEntityNote)lvt_4_1_).func_175108_a(p_180649_1_, p_180649_2_);
            p_180649_3_.func_71029_a(StatList.field_181734_R);
         }

      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityNote();
   }

   private String func_176433_b(int p_176433_1_) {
      if (p_176433_1_ < 0 || p_176433_1_ >= field_176434_a.size()) {
         p_176433_1_ = 0;
      }

      return (String)field_176434_a.get(p_176433_1_);
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      float lvt_6_1_ = (float)Math.pow(2.0D, (double)(p_180648_5_ - 12) / 12.0D);
      p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "note." + this.func_176433_b(p_180648_4_), 3.0F, lvt_6_1_);
      p_180648_1_.func_175688_a(EnumParticleTypes.NOTE, (double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 1.2D, (double)p_180648_2_.func_177952_p() + 0.5D, (double)p_180648_5_ / 24.0D, 0.0D, 0.0D);
      return true;
   }

   public int func_149645_b() {
      return 3;
   }
}
