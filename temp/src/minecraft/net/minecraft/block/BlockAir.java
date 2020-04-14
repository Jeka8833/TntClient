package net.minecraft.block;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockAir extends Block {
   private static Map mapOriginalOpacity = new IdentityHashMap();

   protected BlockAir() {
      super(Material.field_151579_a);
   }

   public int func_149645_b() {
      return -1;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176209_a(IBlockState p_176209_1_, boolean p_176209_2_) {
      return false;
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
   }

   public boolean func_176200_f(World p_176200_1_, BlockPos p_176200_2_) {
      return true;
   }

   public static void setLightOpacity(Block p_setLightOpacity_0_, int p_setLightOpacity_1_) {
      if (!mapOriginalOpacity.containsKey(p_setLightOpacity_0_)) {
         mapOriginalOpacity.put(p_setLightOpacity_0_, p_setLightOpacity_0_.field_149786_r);
      }

      p_setLightOpacity_0_.field_149786_r = p_setLightOpacity_1_;
   }

   public static void restoreLightOpacity(Block p_restoreLightOpacity_0_) {
      if (mapOriginalOpacity.containsKey(p_restoreLightOpacity_0_)) {
         int opacity = (Integer)mapOriginalOpacity.get(p_restoreLightOpacity_0_);
         setLightOpacity(p_restoreLightOpacity_0_, opacity);
      }
   }
}
