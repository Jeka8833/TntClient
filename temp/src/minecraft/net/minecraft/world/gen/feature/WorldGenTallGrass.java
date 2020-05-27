package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTallGrass extends WorldGenerator {
   private final IBlockState field_175907_a;

   public WorldGenTallGrass(BlockTallGrass.EnumType p_i45629_1_) {
      this.field_175907_a = Blocks.field_150329_H.func_176223_P().func_177226_a(BlockTallGrass.field_176497_a, p_i45629_1_);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      Block lvt_4_1_;
      while(((lvt_4_1_ = p_180709_1_.func_180495_p(p_180709_3_).func_177230_c()).func_149688_o() == Material.field_151579_a || lvt_4_1_.func_149688_o() == Material.field_151584_j) && p_180709_3_.func_177956_o() > 0) {
         p_180709_3_ = p_180709_3_.func_177977_b();
      }

      for(int lvt_5_1_ = 0; lvt_5_1_ < 128; ++lvt_5_1_) {
         BlockPos lvt_6_1_ = p_180709_3_.func_177982_a(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
         if (p_180709_1_.func_175623_d(lvt_6_1_) && Blocks.field_150329_H.func_180671_f(p_180709_1_, lvt_6_1_, this.field_175907_a)) {
            p_180709_1_.func_180501_a(lvt_6_1_, this.field_175907_a, 2);
         }
      }

      return true;
   }
}