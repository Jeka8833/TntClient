package net.minecraft.pathfinding;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class PathNavigateGround extends PathNavigate {
   protected WalkNodeProcessor field_179695_a;
   private boolean field_179694_f;

   public PathNavigateGround(EntityLiving p_i45875_1_, World p_i45875_2_) {
      super(p_i45875_1_, p_i45875_2_);
   }

   protected PathFinder func_179679_a() {
      this.field_179695_a = new WalkNodeProcessor();
      this.field_179695_a.func_176175_a(true);
      return new PathFinder(this.field_179695_a);
   }

   protected boolean func_75485_k() {
      return this.field_75515_a.field_70122_E || this.func_179684_h() && this.func_75506_l() || this.field_75515_a.func_70115_ae() && this.field_75515_a instanceof EntityZombie && this.field_75515_a.field_70154_o instanceof EntityChicken;
   }

   protected Vec3 func_75502_i() {
      return new Vec3(this.field_75515_a.field_70165_t, (double)this.func_179687_p(), this.field_75515_a.field_70161_v);
   }

   private int func_179687_p() {
      if (this.field_75515_a.func_70090_H() && this.func_179684_h()) {
         int lvt_1_1_ = (int)this.field_75515_a.func_174813_aQ().field_72338_b;
         Block lvt_2_1_ = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), lvt_1_1_, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
         int lvt_3_1_ = 0;

         do {
            if (lvt_2_1_ != Blocks.field_150358_i && lvt_2_1_ != Blocks.field_150355_j) {
               return lvt_1_1_;
            }

            ++lvt_1_1_;
            lvt_2_1_ = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), lvt_1_1_, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
            ++lvt_3_1_;
         } while(lvt_3_1_ <= 16);

         return (int)this.field_75515_a.func_174813_aQ().field_72338_b;
      } else {
         return (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D);
      }
   }

   protected void func_75487_m() {
      super.func_75487_m();
      if (this.field_179694_f) {
         if (this.field_75513_b.func_175678_i(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(this.field_75515_a.field_70161_v)))) {
            return;
         }

         for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75514_c.func_75874_d(); ++lvt_1_1_) {
            PathPoint lvt_2_1_ = this.field_75514_c.func_75877_a(lvt_1_1_);
            if (this.field_75513_b.func_175678_i(new BlockPos(lvt_2_1_.field_75839_a, lvt_2_1_.field_75837_b, lvt_2_1_.field_75838_c))) {
               this.field_75514_c.func_75871_b(lvt_1_1_ - 1);
               return;
            }
         }
      }

   }

   protected boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
      int lvt_6_1_ = MathHelper.func_76128_c(p_75493_1_.field_72450_a);
      int lvt_7_1_ = MathHelper.func_76128_c(p_75493_1_.field_72449_c);
      double lvt_8_1_ = p_75493_2_.field_72450_a - p_75493_1_.field_72450_a;
      double lvt_10_1_ = p_75493_2_.field_72449_c - p_75493_1_.field_72449_c;
      double lvt_12_1_ = lvt_8_1_ * lvt_8_1_ + lvt_10_1_ * lvt_10_1_;
      if (lvt_12_1_ < 1.0E-8D) {
         return false;
      } else {
         double lvt_14_1_ = 1.0D / Math.sqrt(lvt_12_1_);
         lvt_8_1_ *= lvt_14_1_;
         lvt_10_1_ *= lvt_14_1_;
         p_75493_3_ += 2;
         p_75493_5_ += 2;
         if (!this.func_179683_a(lvt_6_1_, (int)p_75493_1_.field_72448_b, lvt_7_1_, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, lvt_8_1_, lvt_10_1_)) {
            return false;
         } else {
            p_75493_3_ -= 2;
            p_75493_5_ -= 2;
            double lvt_16_1_ = 1.0D / Math.abs(lvt_8_1_);
            double lvt_18_1_ = 1.0D / Math.abs(lvt_10_1_);
            double lvt_20_1_ = (double)(lvt_6_1_ * 1) - p_75493_1_.field_72450_a;
            double lvt_22_1_ = (double)(lvt_7_1_ * 1) - p_75493_1_.field_72449_c;
            if (lvt_8_1_ >= 0.0D) {
               ++lvt_20_1_;
            }

            if (lvt_10_1_ >= 0.0D) {
               ++lvt_22_1_;
            }

            lvt_20_1_ /= lvt_8_1_;
            lvt_22_1_ /= lvt_10_1_;
            int lvt_24_1_ = lvt_8_1_ < 0.0D ? -1 : 1;
            int lvt_25_1_ = lvt_10_1_ < 0.0D ? -1 : 1;
            int lvt_26_1_ = MathHelper.func_76128_c(p_75493_2_.field_72450_a);
            int lvt_27_1_ = MathHelper.func_76128_c(p_75493_2_.field_72449_c);
            int lvt_28_1_ = lvt_26_1_ - lvt_6_1_;
            int lvt_29_1_ = lvt_27_1_ - lvt_7_1_;

            do {
               if (lvt_28_1_ * lvt_24_1_ <= 0 && lvt_29_1_ * lvt_25_1_ <= 0) {
                  return true;
               }

               if (lvt_20_1_ < lvt_22_1_) {
                  lvt_20_1_ += lvt_16_1_;
                  lvt_6_1_ += lvt_24_1_;
                  lvt_28_1_ = lvt_26_1_ - lvt_6_1_;
               } else {
                  lvt_22_1_ += lvt_18_1_;
                  lvt_7_1_ += lvt_25_1_;
                  lvt_29_1_ = lvt_27_1_ - lvt_7_1_;
               }
            } while(this.func_179683_a(lvt_6_1_, (int)p_75493_1_.field_72448_b, lvt_7_1_, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, lvt_8_1_, lvt_10_1_));

            return false;
         }
      }
   }

   private boolean func_179683_a(int p_179683_1_, int p_179683_2_, int p_179683_3_, int p_179683_4_, int p_179683_5_, int p_179683_6_, Vec3 p_179683_7_, double p_179683_8_, double p_179683_10_) {
      int lvt_12_1_ = p_179683_1_ - p_179683_4_ / 2;
      int lvt_13_1_ = p_179683_3_ - p_179683_6_ / 2;
      if (!this.func_179692_b(lvt_12_1_, p_179683_2_, lvt_13_1_, p_179683_4_, p_179683_5_, p_179683_6_, p_179683_7_, p_179683_8_, p_179683_10_)) {
         return false;
      } else {
         for(int lvt_14_1_ = lvt_12_1_; lvt_14_1_ < lvt_12_1_ + p_179683_4_; ++lvt_14_1_) {
            for(int lvt_15_1_ = lvt_13_1_; lvt_15_1_ < lvt_13_1_ + p_179683_6_; ++lvt_15_1_) {
               double lvt_16_1_ = (double)lvt_14_1_ + 0.5D - p_179683_7_.field_72450_a;
               double lvt_18_1_ = (double)lvt_15_1_ + 0.5D - p_179683_7_.field_72449_c;
               if (lvt_16_1_ * p_179683_8_ + lvt_18_1_ * p_179683_10_ >= 0.0D) {
                  Block lvt_20_1_ = this.field_75513_b.func_180495_p(new BlockPos(lvt_14_1_, p_179683_2_ - 1, lvt_15_1_)).func_177230_c();
                  Material lvt_21_1_ = lvt_20_1_.func_149688_o();
                  if (lvt_21_1_ == Material.field_151579_a) {
                     return false;
                  }

                  if (lvt_21_1_ == Material.field_151586_h && !this.field_75515_a.func_70090_H()) {
                     return false;
                  }

                  if (lvt_21_1_ == Material.field_151587_i) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private boolean func_179692_b(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3 p_179692_7_, double p_179692_8_, double p_179692_10_) {
      Iterator lvt_12_1_ = BlockPos.func_177980_a(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1)).iterator();

      while(lvt_12_1_.hasNext()) {
         BlockPos lvt_13_1_ = (BlockPos)lvt_12_1_.next();
         double lvt_14_1_ = (double)lvt_13_1_.func_177958_n() + 0.5D - p_179692_7_.field_72450_a;
         double lvt_16_1_ = (double)lvt_13_1_.func_177952_p() + 0.5D - p_179692_7_.field_72449_c;
         if (lvt_14_1_ * p_179692_8_ + lvt_16_1_ * p_179692_10_ >= 0.0D) {
            Block lvt_18_1_ = this.field_75513_b.func_180495_p(lvt_13_1_).func_177230_c();
            if (!lvt_18_1_.func_176205_b(this.field_75513_b, lvt_13_1_)) {
               return false;
            }
         }
      }

      return true;
   }

   public void func_179690_a(boolean p_179690_1_) {
      this.field_179695_a.func_176176_c(p_179690_1_);
   }

   public boolean func_179689_e() {
      return this.field_179695_a.func_176173_e();
   }

   public void func_179688_b(boolean p_179688_1_) {
      this.field_179695_a.func_176172_b(p_179688_1_);
   }

   public void func_179691_c(boolean p_179691_1_) {
      this.field_179695_a.func_176175_a(p_179691_1_);
   }

   public boolean func_179686_g() {
      return this.field_179695_a.func_176179_b();
   }

   public void func_179693_d(boolean p_179693_1_) {
      this.field_179695_a.func_176178_d(p_179693_1_);
   }

   public boolean func_179684_h() {
      return this.field_179695_a.func_176174_d();
   }

   public void func_179685_e(boolean p_179685_1_) {
      this.field_179694_f = p_179685_1_;
   }
}
