package net.minecraft.world.pathfinder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SwimNodeProcessor extends NodeProcessor {
   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
      super.func_176162_a(p_176162_1_, p_176162_2_);
   }

   public void func_176163_a() {
      super.func_176163_a();
   }

   public PathPoint func_176161_a(Entity p_176161_1_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
   }

   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176160_2_ - (double)(p_176160_1_.field_70130_N / 2.0F)), MathHelper.func_76128_c(p_176160_4_ + 0.5D), MathHelper.func_76128_c(p_176160_6_ - (double)(p_176160_1_.field_70130_N / 2.0F)));
   }

   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_) {
      int lvt_6_1_ = 0;
      EnumFacing[] lvt_7_1_ = EnumFacing.values();
      int lvt_8_1_ = lvt_7_1_.length;

      for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
         EnumFacing lvt_10_1_ = lvt_7_1_[lvt_9_1_];
         PathPoint lvt_11_1_ = this.func_176185_a(p_176164_2_, p_176164_3_.field_75839_a + lvt_10_1_.func_82601_c(), p_176164_3_.field_75837_b + lvt_10_1_.func_96559_d(), p_176164_3_.field_75838_c + lvt_10_1_.func_82599_e());
         if (lvt_11_1_ != null && !lvt_11_1_.field_75842_i && lvt_11_1_.func_75829_a(p_176164_4_) < p_176164_5_) {
            p_176164_1_[lvt_6_1_++] = lvt_11_1_;
         }
      }

      return lvt_6_1_;
   }

   private PathPoint func_176185_a(Entity p_176185_1_, int p_176185_2_, int p_176185_3_, int p_176185_4_) {
      int lvt_5_1_ = this.func_176186_b(p_176185_1_, p_176185_2_, p_176185_3_, p_176185_4_);
      return lvt_5_1_ == -1 ? this.func_176159_a(p_176185_2_, p_176185_3_, p_176185_4_) : null;
   }

   private int func_176186_b(Entity p_176186_1_, int p_176186_2_, int p_176186_3_, int p_176186_4_) {
      BlockPos.MutableBlockPos lvt_5_1_ = new BlockPos.MutableBlockPos();

      for(int lvt_6_1_ = p_176186_2_; lvt_6_1_ < p_176186_2_ + this.field_176168_c; ++lvt_6_1_) {
         for(int lvt_7_1_ = p_176186_3_; lvt_7_1_ < p_176186_3_ + this.field_176165_d; ++lvt_7_1_) {
            for(int lvt_8_1_ = p_176186_4_; lvt_8_1_ < p_176186_4_ + this.field_176166_e; ++lvt_8_1_) {
               Block lvt_9_1_ = this.field_176169_a.func_180495_p(lvt_5_1_.func_181079_c(lvt_6_1_, lvt_7_1_, lvt_8_1_)).func_177230_c();
               if (lvt_9_1_.func_149688_o() != Material.field_151586_h) {
                  return 0;
               }
            }
         }
      }

      return -1;
   }
}
