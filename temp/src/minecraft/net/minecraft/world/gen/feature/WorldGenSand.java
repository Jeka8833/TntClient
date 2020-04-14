package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSand extends WorldGenerator {
   private Block field_150517_a;
   private int field_76539_b;

   public WorldGenSand(Block p_i45462_1_, int p_i45462_2_) {
      this.field_150517_a = p_i45462_1_;
      this.field_76539_b = p_i45462_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if (p_180709_1_.func_180495_p(p_180709_3_).func_177230_c().func_149688_o() != Material.field_151586_h) {
         return false;
      } else {
         int lvt_4_1_ = p_180709_2_.nextInt(this.field_76539_b - 2) + 2;
         int lvt_5_1_ = 2;

         for(int lvt_6_1_ = p_180709_3_.func_177958_n() - lvt_4_1_; lvt_6_1_ <= p_180709_3_.func_177958_n() + lvt_4_1_; ++lvt_6_1_) {
            for(int lvt_7_1_ = p_180709_3_.func_177952_p() - lvt_4_1_; lvt_7_1_ <= p_180709_3_.func_177952_p() + lvt_4_1_; ++lvt_7_1_) {
               int lvt_8_1_ = lvt_6_1_ - p_180709_3_.func_177958_n();
               int lvt_9_1_ = lvt_7_1_ - p_180709_3_.func_177952_p();
               if (lvt_8_1_ * lvt_8_1_ + lvt_9_1_ * lvt_9_1_ <= lvt_4_1_ * lvt_4_1_) {
                  for(int lvt_10_1_ = p_180709_3_.func_177956_o() - lvt_5_1_; lvt_10_1_ <= p_180709_3_.func_177956_o() + lvt_5_1_; ++lvt_10_1_) {
                     BlockPos lvt_11_1_ = new BlockPos(lvt_6_1_, lvt_10_1_, lvt_7_1_);
                     Block lvt_12_1_ = p_180709_1_.func_180495_p(lvt_11_1_).func_177230_c();
                     if (lvt_12_1_ == Blocks.field_150346_d || lvt_12_1_ == Blocks.field_150349_c) {
                        p_180709_1_.func_180501_a(lvt_11_1_, this.field_150517_a.func_176223_P(), 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }
}
