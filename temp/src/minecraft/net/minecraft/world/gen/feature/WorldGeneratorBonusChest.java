package net.minecraft.world.gen.feature;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class WorldGeneratorBonusChest extends WorldGenerator {
   private final List<WeightedRandomChestContent> field_175909_a;
   private final int field_76545_b;

   public WorldGeneratorBonusChest(List<WeightedRandomChestContent> p_i45634_1_, int p_i45634_2_) {
      this.field_175909_a = p_i45634_1_;
      this.field_76545_b = p_i45634_2_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      Block lvt_4_1_;
      while(((lvt_4_1_ = p_180709_1_.func_180495_p(p_180709_3_).func_177230_c()).func_149688_o() == Material.field_151579_a || lvt_4_1_.func_149688_o() == Material.field_151584_j) && p_180709_3_.func_177956_o() > 1) {
         p_180709_3_ = p_180709_3_.func_177977_b();
      }

      if (p_180709_3_.func_177956_o() < 1) {
         return false;
      } else {
         p_180709_3_ = p_180709_3_.func_177984_a();

         for(int lvt_5_1_ = 0; lvt_5_1_ < 4; ++lvt_5_1_) {
            BlockPos lvt_6_1_ = p_180709_3_.func_177982_a(p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(3) - p_180709_2_.nextInt(3), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4));
            if (p_180709_1_.func_175623_d(lvt_6_1_) && World.func_175683_a(p_180709_1_, lvt_6_1_.func_177977_b())) {
               p_180709_1_.func_180501_a(lvt_6_1_, Blocks.field_150486_ae.func_176223_P(), 2);
               TileEntity lvt_7_1_ = p_180709_1_.func_175625_s(lvt_6_1_);
               if (lvt_7_1_ instanceof TileEntityChest) {
                  WeightedRandomChestContent.func_177630_a(p_180709_2_, this.field_175909_a, (TileEntityChest)lvt_7_1_, this.field_76545_b);
               }

               BlockPos lvt_8_1_ = lvt_6_1_.func_177974_f();
               BlockPos lvt_9_1_ = lvt_6_1_.func_177976_e();
               BlockPos lvt_10_1_ = lvt_6_1_.func_177978_c();
               BlockPos lvt_11_1_ = lvt_6_1_.func_177968_d();
               if (p_180709_1_.func_175623_d(lvt_9_1_) && World.func_175683_a(p_180709_1_, lvt_9_1_.func_177977_b())) {
                  p_180709_1_.func_180501_a(lvt_9_1_, Blocks.field_150478_aa.func_176223_P(), 2);
               }

               if (p_180709_1_.func_175623_d(lvt_8_1_) && World.func_175683_a(p_180709_1_, lvt_8_1_.func_177977_b())) {
                  p_180709_1_.func_180501_a(lvt_8_1_, Blocks.field_150478_aa.func_176223_P(), 2);
               }

               if (p_180709_1_.func_175623_d(lvt_10_1_) && World.func_175683_a(p_180709_1_, lvt_10_1_.func_177977_b())) {
                  p_180709_1_.func_180501_a(lvt_10_1_, Blocks.field_150478_aa.func_176223_P(), 2);
               }

               if (p_180709_1_.func_175623_d(lvt_11_1_) && World.func_175683_a(p_180709_1_, lvt_11_1_.func_177977_b())) {
                  p_180709_1_.func_180501_a(lvt_11_1_, Blocks.field_150478_aa.func_176223_P(), 2);
               }

               return true;
            }
         }

         return false;
      }
   }
}
