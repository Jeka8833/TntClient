package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenReed extends WorldGenerator {
   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < 20; ++lvt_4_1_) {
         BlockPos lvt_5_1_ = p_180709_3_.func_177982_a(p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), 0, p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4));
         if (p_180709_1_.func_175623_d(lvt_5_1_)) {
            BlockPos lvt_6_1_ = lvt_5_1_.func_177977_b();
            if (p_180709_1_.func_180495_p(lvt_6_1_.func_177976_e()).func_177230_c().func_149688_o() == Material.field_151586_h || p_180709_1_.func_180495_p(lvt_6_1_.func_177974_f()).func_177230_c().func_149688_o() == Material.field_151586_h || p_180709_1_.func_180495_p(lvt_6_1_.func_177978_c()).func_177230_c().func_149688_o() == Material.field_151586_h || p_180709_1_.func_180495_p(lvt_6_1_.func_177968_d()).func_177230_c().func_149688_o() == Material.field_151586_h) {
               int lvt_7_1_ = 2 + p_180709_2_.nextInt(p_180709_2_.nextInt(3) + 1);

               for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
                  if (Blocks.field_150436_aH.func_176354_d(p_180709_1_, lvt_5_1_)) {
                     p_180709_1_.func_180501_a(lvt_5_1_.func_177981_b(lvt_8_1_), Blocks.field_150436_aH.func_176223_P(), 2);
                  }
               }
            }
         }
      }

      return true;
   }
}
