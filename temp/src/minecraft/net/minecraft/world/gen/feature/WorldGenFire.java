package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenFire extends WorldGenerator {
   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < 64; ++lvt_4_1_) {
         BlockPos lvt_5_1_ = p_180709_3_.func_177982_a(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
         if (p_180709_1_.func_175623_d(lvt_5_1_) && p_180709_1_.func_180495_p(lvt_5_1_.func_177977_b()).func_177230_c() == Blocks.field_150424_aL) {
            p_180709_1_.func_180501_a(lvt_5_1_, Blocks.field_150480_ab.func_176223_P(), 2);
         }
      }

      return true;
   }
}
