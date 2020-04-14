package net.minecraft.world.gen.layer;

public class GenLayerVoronoiZoom extends GenLayer {
   public GenLayerVoronoiZoom(long p_i2133_1_, GenLayer p_i2133_3_) {
      super(p_i2133_1_);
      super.field_75909_a = p_i2133_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      p_75904_1_ -= 2;
      p_75904_2_ -= 2;
      int lvt_5_1_ = p_75904_1_ >> 2;
      int lvt_6_1_ = p_75904_2_ >> 2;
      int lvt_7_1_ = (p_75904_3_ >> 2) + 2;
      int lvt_8_1_ = (p_75904_4_ >> 2) + 2;
      int[] lvt_9_1_ = this.field_75909_a.func_75904_a(lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);
      int lvt_10_1_ = lvt_7_1_ - 1 << 2;
      int lvt_11_1_ = lvt_8_1_ - 1 << 2;
      int[] lvt_12_1_ = IntCache.func_76445_a(lvt_10_1_ * lvt_11_1_);

      int lvt_14_1_;
      for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_8_1_ - 1; ++lvt_13_1_) {
         lvt_14_1_ = 0;
         int lvt_15_1_ = lvt_9_1_[lvt_14_1_ + 0 + (lvt_13_1_ + 0) * lvt_7_1_];

         for(int lvt_16_1_ = lvt_9_1_[lvt_14_1_ + 0 + (lvt_13_1_ + 1) * lvt_7_1_]; lvt_14_1_ < lvt_7_1_ - 1; ++lvt_14_1_) {
            double lvt_17_1_ = 3.6D;
            this.func_75903_a((long)(lvt_14_1_ + lvt_5_1_ << 2), (long)(lvt_13_1_ + lvt_6_1_ << 2));
            double lvt_19_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D;
            double lvt_21_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D;
            this.func_75903_a((long)(lvt_14_1_ + lvt_5_1_ + 1 << 2), (long)(lvt_13_1_ + lvt_6_1_ << 2));
            double lvt_23_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
            double lvt_25_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D;
            this.func_75903_a((long)(lvt_14_1_ + lvt_5_1_ << 2), (long)(lvt_13_1_ + lvt_6_1_ + 1 << 2));
            double lvt_27_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D;
            double lvt_29_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
            this.func_75903_a((long)(lvt_14_1_ + lvt_5_1_ + 1 << 2), (long)(lvt_13_1_ + lvt_6_1_ + 1 << 2));
            double lvt_31_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
            double lvt_33_1_ = ((double)this.func_75902_a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
            int lvt_35_1_ = lvt_9_1_[lvt_14_1_ + 1 + (lvt_13_1_ + 0) * lvt_7_1_] & 255;
            int lvt_36_1_ = lvt_9_1_[lvt_14_1_ + 1 + (lvt_13_1_ + 1) * lvt_7_1_] & 255;

            for(int lvt_37_1_ = 0; lvt_37_1_ < 4; ++lvt_37_1_) {
               int lvt_38_1_ = ((lvt_13_1_ << 2) + lvt_37_1_) * lvt_10_1_ + (lvt_14_1_ << 2);

               for(int lvt_39_1_ = 0; lvt_39_1_ < 4; ++lvt_39_1_) {
                  double lvt_40_1_ = ((double)lvt_37_1_ - lvt_21_1_) * ((double)lvt_37_1_ - lvt_21_1_) + ((double)lvt_39_1_ - lvt_19_1_) * ((double)lvt_39_1_ - lvt_19_1_);
                  double lvt_42_1_ = ((double)lvt_37_1_ - lvt_25_1_) * ((double)lvt_37_1_ - lvt_25_1_) + ((double)lvt_39_1_ - lvt_23_1_) * ((double)lvt_39_1_ - lvt_23_1_);
                  double lvt_44_1_ = ((double)lvt_37_1_ - lvt_29_1_) * ((double)lvt_37_1_ - lvt_29_1_) + ((double)lvt_39_1_ - lvt_27_1_) * ((double)lvt_39_1_ - lvt_27_1_);
                  double lvt_46_1_ = ((double)lvt_37_1_ - lvt_33_1_) * ((double)lvt_37_1_ - lvt_33_1_) + ((double)lvt_39_1_ - lvt_31_1_) * ((double)lvt_39_1_ - lvt_31_1_);
                  if (lvt_40_1_ < lvt_42_1_ && lvt_40_1_ < lvt_44_1_ && lvt_40_1_ < lvt_46_1_) {
                     lvt_12_1_[lvt_38_1_++] = lvt_15_1_;
                  } else if (lvt_42_1_ < lvt_40_1_ && lvt_42_1_ < lvt_44_1_ && lvt_42_1_ < lvt_46_1_) {
                     lvt_12_1_[lvt_38_1_++] = lvt_35_1_;
                  } else if (lvt_44_1_ < lvt_40_1_ && lvt_44_1_ < lvt_42_1_ && lvt_44_1_ < lvt_46_1_) {
                     lvt_12_1_[lvt_38_1_++] = lvt_16_1_;
                  } else {
                     lvt_12_1_[lvt_38_1_++] = lvt_36_1_;
                  }
               }
            }

            lvt_15_1_ = lvt_35_1_;
            lvt_16_1_ = lvt_36_1_;
         }
      }

      int[] lvt_13_2_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(lvt_14_1_ = 0; lvt_14_1_ < p_75904_4_; ++lvt_14_1_) {
         System.arraycopy(lvt_12_1_, (lvt_14_1_ + (p_75904_2_ & 3)) * lvt_10_1_ + (p_75904_1_ & 3), lvt_13_2_, lvt_14_1_ * p_75904_3_, p_75904_3_);
      }

      return lvt_13_2_;
   }
}
