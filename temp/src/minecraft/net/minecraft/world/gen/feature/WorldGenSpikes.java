package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSpikes extends WorldGenerator {
   private Block field_150520_a;

   public WorldGenSpikes(Block p_i45464_1_) {
      this.field_150520_a = p_i45464_1_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if (p_180709_1_.func_175623_d(p_180709_3_) && p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c() == this.field_150520_a) {
         int lvt_4_1_ = p_180709_2_.nextInt(32) + 6;
         int lvt_5_1_ = p_180709_2_.nextInt(4) + 1;
         BlockPos.MutableBlockPos lvt_6_1_ = new BlockPos.MutableBlockPos();

         int lvt_7_2_;
         int lvt_8_2_;
         int lvt_9_2_;
         int lvt_10_2_;
         for(lvt_7_2_ = p_180709_3_.func_177958_n() - lvt_5_1_; lvt_7_2_ <= p_180709_3_.func_177958_n() + lvt_5_1_; ++lvt_7_2_) {
            for(lvt_8_2_ = p_180709_3_.func_177952_p() - lvt_5_1_; lvt_8_2_ <= p_180709_3_.func_177952_p() + lvt_5_1_; ++lvt_8_2_) {
               lvt_9_2_ = lvt_7_2_ - p_180709_3_.func_177958_n();
               lvt_10_2_ = lvt_8_2_ - p_180709_3_.func_177952_p();
               if (lvt_9_2_ * lvt_9_2_ + lvt_10_2_ * lvt_10_2_ <= lvt_5_1_ * lvt_5_1_ + 1 && p_180709_1_.func_180495_p(lvt_6_1_.func_181079_c(lvt_7_2_, p_180709_3_.func_177956_o() - 1, lvt_8_2_)).func_177230_c() != this.field_150520_a) {
                  return false;
               }
            }
         }

         for(lvt_7_2_ = p_180709_3_.func_177956_o(); lvt_7_2_ < p_180709_3_.func_177956_o() + lvt_4_1_ && lvt_7_2_ < 256; ++lvt_7_2_) {
            for(lvt_8_2_ = p_180709_3_.func_177958_n() - lvt_5_1_; lvt_8_2_ <= p_180709_3_.func_177958_n() + lvt_5_1_; ++lvt_8_2_) {
               for(lvt_9_2_ = p_180709_3_.func_177952_p() - lvt_5_1_; lvt_9_2_ <= p_180709_3_.func_177952_p() + lvt_5_1_; ++lvt_9_2_) {
                  lvt_10_2_ = lvt_8_2_ - p_180709_3_.func_177958_n();
                  int lvt_11_1_ = lvt_9_2_ - p_180709_3_.func_177952_p();
                  if (lvt_10_2_ * lvt_10_2_ + lvt_11_1_ * lvt_11_1_ <= lvt_5_1_ * lvt_5_1_ + 1) {
                     p_180709_1_.func_180501_a(new BlockPos(lvt_8_2_, lvt_7_2_, lvt_9_2_), Blocks.field_150343_Z.func_176223_P(), 2);
                  }
               }
            }
         }

         Entity lvt_7_3_ = new EntityEnderCrystal(p_180709_1_);
         lvt_7_3_.func_70012_b((double)((float)p_180709_3_.func_177958_n() + 0.5F), (double)(p_180709_3_.func_177956_o() + lvt_4_1_), (double)((float)p_180709_3_.func_177952_p() + 0.5F), p_180709_2_.nextFloat() * 360.0F, 0.0F);
         p_180709_1_.func_72838_d(lvt_7_3_);
         p_180709_1_.func_180501_a(p_180709_3_.func_177981_b(lvt_4_1_), Blocks.field_150357_h.func_176223_P(), 2);
         return true;
      } else {
         return false;
      }
   }
}
