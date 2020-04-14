package net.minecraft.world.gen.feature;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMinable extends WorldGenerator {
   private final IBlockState field_175920_a;
   private final int field_76541_b;
   private final Predicate<IBlockState> field_175919_c;

   public WorldGenMinable(IBlockState p_i45630_1_, int p_i45630_2_) {
      this(p_i45630_1_, p_i45630_2_, BlockHelper.func_177642_a(Blocks.field_150348_b));
   }

   public WorldGenMinable(IBlockState p_i45631_1_, int p_i45631_2_, Predicate<IBlockState> p_i45631_3_) {
      this.field_175920_a = p_i45631_1_;
      this.field_76541_b = p_i45631_2_;
      this.field_175919_c = p_i45631_3_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      float lvt_4_1_ = p_180709_2_.nextFloat() * 3.1415927F;
      double lvt_5_1_ = (double)((float)(p_180709_3_.func_177958_n() + 8) + MathHelper.func_76126_a(lvt_4_1_) * (float)this.field_76541_b / 8.0F);
      double lvt_7_1_ = (double)((float)(p_180709_3_.func_177958_n() + 8) - MathHelper.func_76126_a(lvt_4_1_) * (float)this.field_76541_b / 8.0F);
      double lvt_9_1_ = (double)((float)(p_180709_3_.func_177952_p() + 8) + MathHelper.func_76134_b(lvt_4_1_) * (float)this.field_76541_b / 8.0F);
      double lvt_11_1_ = (double)((float)(p_180709_3_.func_177952_p() + 8) - MathHelper.func_76134_b(lvt_4_1_) * (float)this.field_76541_b / 8.0F);
      double lvt_13_1_ = (double)(p_180709_3_.func_177956_o() + p_180709_2_.nextInt(3) - 2);
      double lvt_15_1_ = (double)(p_180709_3_.func_177956_o() + p_180709_2_.nextInt(3) - 2);

      for(int lvt_17_1_ = 0; lvt_17_1_ < this.field_76541_b; ++lvt_17_1_) {
         float lvt_18_1_ = (float)lvt_17_1_ / (float)this.field_76541_b;
         double lvt_19_1_ = lvt_5_1_ + (lvt_7_1_ - lvt_5_1_) * (double)lvt_18_1_;
         double lvt_21_1_ = lvt_13_1_ + (lvt_15_1_ - lvt_13_1_) * (double)lvt_18_1_;
         double lvt_23_1_ = lvt_9_1_ + (lvt_11_1_ - lvt_9_1_) * (double)lvt_18_1_;
         double lvt_25_1_ = p_180709_2_.nextDouble() * (double)this.field_76541_b / 16.0D;
         double lvt_27_1_ = (double)(MathHelper.func_76126_a(3.1415927F * lvt_18_1_) + 1.0F) * lvt_25_1_ + 1.0D;
         double lvt_29_1_ = (double)(MathHelper.func_76126_a(3.1415927F * lvt_18_1_) + 1.0F) * lvt_25_1_ + 1.0D;
         int lvt_31_1_ = MathHelper.func_76128_c(lvt_19_1_ - lvt_27_1_ / 2.0D);
         int lvt_32_1_ = MathHelper.func_76128_c(lvt_21_1_ - lvt_29_1_ / 2.0D);
         int lvt_33_1_ = MathHelper.func_76128_c(lvt_23_1_ - lvt_27_1_ / 2.0D);
         int lvt_34_1_ = MathHelper.func_76128_c(lvt_19_1_ + lvt_27_1_ / 2.0D);
         int lvt_35_1_ = MathHelper.func_76128_c(lvt_21_1_ + lvt_29_1_ / 2.0D);
         int lvt_36_1_ = MathHelper.func_76128_c(lvt_23_1_ + lvt_27_1_ / 2.0D);

         for(int lvt_37_1_ = lvt_31_1_; lvt_37_1_ <= lvt_34_1_; ++lvt_37_1_) {
            double lvt_38_1_ = ((double)lvt_37_1_ + 0.5D - lvt_19_1_) / (lvt_27_1_ / 2.0D);
            if (lvt_38_1_ * lvt_38_1_ < 1.0D) {
               for(int lvt_40_1_ = lvt_32_1_; lvt_40_1_ <= lvt_35_1_; ++lvt_40_1_) {
                  double lvt_41_1_ = ((double)lvt_40_1_ + 0.5D - lvt_21_1_) / (lvt_29_1_ / 2.0D);
                  if (lvt_38_1_ * lvt_38_1_ + lvt_41_1_ * lvt_41_1_ < 1.0D) {
                     for(int lvt_43_1_ = lvt_33_1_; lvt_43_1_ <= lvt_36_1_; ++lvt_43_1_) {
                        double lvt_44_1_ = ((double)lvt_43_1_ + 0.5D - lvt_23_1_) / (lvt_27_1_ / 2.0D);
                        if (lvt_38_1_ * lvt_38_1_ + lvt_41_1_ * lvt_41_1_ + lvt_44_1_ * lvt_44_1_ < 1.0D) {
                           BlockPos lvt_46_1_ = new BlockPos(lvt_37_1_, lvt_40_1_, lvt_43_1_);
                           if (this.field_175919_c.apply(p_180709_1_.func_180495_p(lvt_46_1_))) {
                              p_180709_1_.func_180501_a(lvt_46_1_, this.field_175920_a, 2);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }
}
