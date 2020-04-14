package net.minecraft.entity;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlying extends EntityLiving {
   public EntityFlying(World p_i1587_1_) {
      super(p_i1587_1_);
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      if (this.func_70090_H()) {
         this.func_70060_a(p_70612_1_, p_70612_2_, 0.02F);
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= 0.800000011920929D;
         this.field_70181_x *= 0.800000011920929D;
         this.field_70179_y *= 0.800000011920929D;
      } else if (this.func_180799_ab()) {
         this.func_70060_a(p_70612_1_, p_70612_2_, 0.02F);
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= 0.5D;
         this.field_70181_x *= 0.5D;
         this.field_70179_y *= 0.5D;
      } else {
         float lvt_3_1_ = 0.91F;
         if (this.field_70122_E) {
            lvt_3_1_ = this.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v))).func_177230_c().field_149765_K * 0.91F;
         }

         float lvt_4_1_ = 0.16277136F / (lvt_3_1_ * lvt_3_1_ * lvt_3_1_);
         this.func_70060_a(p_70612_1_, p_70612_2_, this.field_70122_E ? 0.1F * lvt_4_1_ : 0.02F);
         lvt_3_1_ = 0.91F;
         if (this.field_70122_E) {
            lvt_3_1_ = this.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v))).func_177230_c().field_149765_K * 0.91F;
         }

         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= (double)lvt_3_1_;
         this.field_70181_x *= (double)lvt_3_1_;
         this.field_70179_y *= (double)lvt_3_1_;
      }

      this.field_70722_aY = this.field_70721_aZ;
      double lvt_3_2_ = this.field_70165_t - this.field_70169_q;
      double lvt_5_1_ = this.field_70161_v - this.field_70166_s;
      float lvt_7_1_ = MathHelper.func_76133_a(lvt_3_2_ * lvt_3_2_ + lvt_5_1_ * lvt_5_1_) * 4.0F;
      if (lvt_7_1_ > 1.0F) {
         lvt_7_1_ = 1.0F;
      }

      this.field_70721_aZ += (lvt_7_1_ - this.field_70721_aZ) * 0.4F;
      this.field_70754_ba += this.field_70721_aZ;
   }

   public boolean func_70617_f_() {
      return false;
   }
}
