package net.minecraft.world.gen.layer;

public class GenLayerEdge extends GenLayer {
   private final GenLayerEdge.Mode field_151627_c;

   public GenLayerEdge(long p_i45474_1_, GenLayer p_i45474_3_, GenLayerEdge.Mode p_i45474_4_) {
      super(p_i45474_1_);
      this.field_75909_a = p_i45474_3_;
      this.field_151627_c = p_i45474_4_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      switch(this.field_151627_c) {
      case COOL_WARM:
      default:
         return this.func_151626_c(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      case HEAT_ICE:
         return this.func_151624_d(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      case SPECIAL:
         return this.func_151625_e(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      }
   }

   private int[] func_151626_c(int p_151626_1_, int p_151626_2_, int p_151626_3_, int p_151626_4_) {
      int lvt_5_1_ = p_151626_1_ - 1;
      int lvt_6_1_ = p_151626_2_ - 1;
      int lvt_7_1_ = 1 + p_151626_3_ + 1;
      int lvt_8_1_ = 1 + p_151626_4_ + 1;
      int[] lvt_9_1_ = this.field_75909_a.func_75904_a(lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);
      int[] lvt_10_1_ = IntCache.func_76445_a(p_151626_3_ * p_151626_4_);

      for(int lvt_11_1_ = 0; lvt_11_1_ < p_151626_4_; ++lvt_11_1_) {
         for(int lvt_12_1_ = 0; lvt_12_1_ < p_151626_3_; ++lvt_12_1_) {
            this.func_75903_a((long)(lvt_12_1_ + p_151626_1_), (long)(lvt_11_1_ + p_151626_2_));
            int lvt_13_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1) * lvt_7_1_];
            if (lvt_13_1_ == 1) {
               int lvt_14_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 - 1) * lvt_7_1_];
               int lvt_15_1_ = lvt_9_1_[lvt_12_1_ + 1 + 1 + (lvt_11_1_ + 1) * lvt_7_1_];
               int lvt_16_1_ = lvt_9_1_[lvt_12_1_ + 1 - 1 + (lvt_11_1_ + 1) * lvt_7_1_];
               int lvt_17_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 + 1) * lvt_7_1_];
               boolean lvt_18_1_ = lvt_14_1_ == 3 || lvt_15_1_ == 3 || lvt_16_1_ == 3 || lvt_17_1_ == 3;
               boolean lvt_19_1_ = lvt_14_1_ == 4 || lvt_15_1_ == 4 || lvt_16_1_ == 4 || lvt_17_1_ == 4;
               if (lvt_18_1_ || lvt_19_1_) {
                  lvt_13_1_ = 2;
               }
            }

            lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_151626_3_] = lvt_13_1_;
         }
      }

      return lvt_10_1_;
   }

   private int[] func_151624_d(int p_151624_1_, int p_151624_2_, int p_151624_3_, int p_151624_4_) {
      int lvt_5_1_ = p_151624_1_ - 1;
      int lvt_6_1_ = p_151624_2_ - 1;
      int lvt_7_1_ = 1 + p_151624_3_ + 1;
      int lvt_8_1_ = 1 + p_151624_4_ + 1;
      int[] lvt_9_1_ = this.field_75909_a.func_75904_a(lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);
      int[] lvt_10_1_ = IntCache.func_76445_a(p_151624_3_ * p_151624_4_);

      for(int lvt_11_1_ = 0; lvt_11_1_ < p_151624_4_; ++lvt_11_1_) {
         for(int lvt_12_1_ = 0; lvt_12_1_ < p_151624_3_; ++lvt_12_1_) {
            int lvt_13_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1) * lvt_7_1_];
            if (lvt_13_1_ == 4) {
               int lvt_14_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 - 1) * lvt_7_1_];
               int lvt_15_1_ = lvt_9_1_[lvt_12_1_ + 1 + 1 + (lvt_11_1_ + 1) * lvt_7_1_];
               int lvt_16_1_ = lvt_9_1_[lvt_12_1_ + 1 - 1 + (lvt_11_1_ + 1) * lvt_7_1_];
               int lvt_17_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 + 1) * lvt_7_1_];
               boolean lvt_18_1_ = lvt_14_1_ == 2 || lvt_15_1_ == 2 || lvt_16_1_ == 2 || lvt_17_1_ == 2;
               boolean lvt_19_1_ = lvt_14_1_ == 1 || lvt_15_1_ == 1 || lvt_16_1_ == 1 || lvt_17_1_ == 1;
               if (lvt_19_1_ || lvt_18_1_) {
                  lvt_13_1_ = 3;
               }
            }

            lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_151624_3_] = lvt_13_1_;
         }
      }

      return lvt_10_1_;
   }

   private int[] func_151625_e(int p_151625_1_, int p_151625_2_, int p_151625_3_, int p_151625_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_151625_1_, p_151625_2_, p_151625_3_, p_151625_4_);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_151625_3_ * p_151625_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_151625_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_151625_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_151625_1_), (long)(lvt_7_1_ + p_151625_2_));
            int lvt_9_1_ = lvt_5_1_[lvt_8_1_ + lvt_7_1_ * p_151625_3_];
            if (lvt_9_1_ != 0 && this.func_75902_a(13) == 0) {
               lvt_9_1_ |= 1 + this.func_75902_a(15) << 8 & 3840;
            }

            lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_151625_3_] = lvt_9_1_;
         }
      }

      return lvt_6_1_;
   }

   public static enum Mode {
      COOL_WARM,
      HEAT_ICE,
      SPECIAL;
   }
}
