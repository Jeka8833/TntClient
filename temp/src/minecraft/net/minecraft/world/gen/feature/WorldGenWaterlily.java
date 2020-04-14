package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenWaterlily extends WorldGenerator {
   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < 10; ++lvt_4_1_) {
         int lvt_5_1_ = p_180709_3_.func_177958_n() + p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8);
         int lvt_6_1_ = p_180709_3_.func_177956_o() + p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4);
         int lvt_7_1_ = p_180709_3_.func_177952_p() + p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8);
         if (p_180709_1_.func_175623_d(new BlockPos(lvt_5_1_, lvt_6_1_, lvt_7_1_)) && Blocks.field_150392_bi.func_176196_c(p_180709_1_, new BlockPos(lvt_5_1_, lvt_6_1_, lvt_7_1_))) {
            p_180709_1_.func_180501_a(new BlockPos(lvt_5_1_, lvt_6_1_, lvt_7_1_), Blocks.field_150392_bi.func_176223_P(), 2);
         }
      }

      return true;
   }
}
