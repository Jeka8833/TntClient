package net.minecraft.world;

import net.minecraft.util.MathHelper;

public class DifficultyInstance {
   private final EnumDifficulty field_180172_a;
   private final float field_180171_b;

   public DifficultyInstance(EnumDifficulty p_i45904_1_, long p_i45904_2_, long p_i45904_4_, float p_i45904_6_) {
      this.field_180172_a = p_i45904_1_;
      this.field_180171_b = this.func_180169_a(p_i45904_1_, p_i45904_2_, p_i45904_4_, p_i45904_6_);
   }

   public float func_180168_b() {
      return this.field_180171_b;
   }

   public float func_180170_c() {
      if (this.field_180171_b < 2.0F) {
         return 0.0F;
      } else {
         return this.field_180171_b > 4.0F ? 1.0F : (this.field_180171_b - 2.0F) / 2.0F;
      }
   }

   private float func_180169_a(EnumDifficulty p_180169_1_, long p_180169_2_, long p_180169_4_, float p_180169_6_) {
      if (p_180169_1_ == EnumDifficulty.PEACEFUL) {
         return 0.0F;
      } else {
         boolean lvt_7_1_ = p_180169_1_ == EnumDifficulty.HARD;
         float lvt_8_1_ = 0.75F;
         float lvt_9_1_ = MathHelper.func_76131_a(((float)p_180169_2_ + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
         lvt_8_1_ += lvt_9_1_;
         float lvt_10_1_ = 0.0F;
         lvt_10_1_ += MathHelper.func_76131_a((float)p_180169_4_ / 3600000.0F, 0.0F, 1.0F) * (lvt_7_1_ ? 1.0F : 0.75F);
         lvt_10_1_ += MathHelper.func_76131_a(p_180169_6_ * 0.25F, 0.0F, lvt_9_1_);
         if (p_180169_1_ == EnumDifficulty.EASY) {
            lvt_10_1_ *= 0.5F;
         }

         lvt_8_1_ += lvt_10_1_;
         return (float)p_180169_1_.func_151525_a() * lvt_8_1_;
      }
   }
}
