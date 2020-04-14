package net.minecraft.world.gen.feature;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBlockBlob extends WorldGenerator {
   private final Block field_150545_a;
   private final int field_150544_b;

   public WorldGenBlockBlob(Block p_i45450_1_, int p_i45450_2_) {
      super(false);
      this.field_150545_a = p_i45450_1_;
      this.field_150544_b = p_i45450_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      while(true) {
         label50: {
            if (p_180709_3_.func_177956_o() > 3) {
               if (p_180709_1_.func_175623_d(p_180709_3_.func_177977_b())) {
                  break label50;
               }

               Block lvt_4_1_ = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
               if (lvt_4_1_ != Blocks.field_150349_c && lvt_4_1_ != Blocks.field_150346_d && lvt_4_1_ != Blocks.field_150348_b) {
                  break label50;
               }
            }

            if (p_180709_3_.func_177956_o() <= 3) {
               return false;
            }

            int lvt_4_2_ = this.field_150544_b;

            for(int lvt_5_1_ = 0; lvt_4_2_ >= 0 && lvt_5_1_ < 3; ++lvt_5_1_) {
               int lvt_6_1_ = lvt_4_2_ + p_180709_2_.nextInt(2);
               int lvt_7_1_ = lvt_4_2_ + p_180709_2_.nextInt(2);
               int lvt_8_1_ = lvt_4_2_ + p_180709_2_.nextInt(2);
               float lvt_9_1_ = (float)(lvt_6_1_ + lvt_7_1_ + lvt_8_1_) * 0.333F + 0.5F;
               Iterator lvt_10_1_ = BlockPos.func_177980_a(p_180709_3_.func_177982_a(-lvt_6_1_, -lvt_7_1_, -lvt_8_1_), p_180709_3_.func_177982_a(lvt_6_1_, lvt_7_1_, lvt_8_1_)).iterator();

               while(lvt_10_1_.hasNext()) {
                  BlockPos lvt_11_1_ = (BlockPos)lvt_10_1_.next();
                  if (lvt_11_1_.func_177951_i(p_180709_3_) <= (double)(lvt_9_1_ * lvt_9_1_)) {
                     p_180709_1_.func_180501_a(lvt_11_1_, this.field_150545_a.func_176223_P(), 4);
                  }
               }

               p_180709_3_ = p_180709_3_.func_177982_a(-(lvt_4_2_ + 1) + p_180709_2_.nextInt(2 + lvt_4_2_ * 2), 0 - p_180709_2_.nextInt(2), -(lvt_4_2_ + 1) + p_180709_2_.nextInt(2 + lvt_4_2_ * 2));
            }

            return true;
         }

         p_180709_3_ = p_180709_3_.func_177977_b();
      }
   }
}
