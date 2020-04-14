package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenShrub extends WorldGenTrees {
   private final IBlockState field_150528_a;
   private final IBlockState field_150527_b;

   public WorldGenShrub(IBlockState p_i46450_1_, IBlockState p_i46450_2_) {
      super(false);
      this.field_150527_b = p_i46450_1_;
      this.field_150528_a = p_i46450_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      Block lvt_4_1_;
      while(((lvt_4_1_ = p_180709_1_.func_180495_p(p_180709_3_).func_177230_c()).func_149688_o() == Material.field_151579_a || lvt_4_1_.func_149688_o() == Material.field_151584_j) && p_180709_3_.func_177956_o() > 0) {
         p_180709_3_ = p_180709_3_.func_177977_b();
      }

      Block lvt_5_1_ = p_180709_1_.func_180495_p(p_180709_3_).func_177230_c();
      if (lvt_5_1_ == Blocks.field_150346_d || lvt_5_1_ == Blocks.field_150349_c) {
         p_180709_3_ = p_180709_3_.func_177984_a();
         this.func_175903_a(p_180709_1_, p_180709_3_, this.field_150527_b);

         for(int lvt_6_1_ = p_180709_3_.func_177956_o(); lvt_6_1_ <= p_180709_3_.func_177956_o() + 2; ++lvt_6_1_) {
            int lvt_7_1_ = lvt_6_1_ - p_180709_3_.func_177956_o();
            int lvt_8_1_ = 2 - lvt_7_1_;

            for(int lvt_9_1_ = p_180709_3_.func_177958_n() - lvt_8_1_; lvt_9_1_ <= p_180709_3_.func_177958_n() + lvt_8_1_; ++lvt_9_1_) {
               int lvt_10_1_ = lvt_9_1_ - p_180709_3_.func_177958_n();

               for(int lvt_11_1_ = p_180709_3_.func_177952_p() - lvt_8_1_; lvt_11_1_ <= p_180709_3_.func_177952_p() + lvt_8_1_; ++lvt_11_1_) {
                  int lvt_12_1_ = lvt_11_1_ - p_180709_3_.func_177952_p();
                  if (Math.abs(lvt_10_1_) != lvt_8_1_ || Math.abs(lvt_12_1_) != lvt_8_1_ || p_180709_2_.nextInt(2) != 0) {
                     BlockPos lvt_13_1_ = new BlockPos(lvt_9_1_, lvt_6_1_, lvt_11_1_);
                     if (!p_180709_1_.func_180495_p(lvt_13_1_).func_177230_c().func_149730_j()) {
                        this.func_175903_a(p_180709_1_, lvt_13_1_, this.field_150528_a);
                     }
                  }
               }
            }
         }
      }

      return true;
   }
}
