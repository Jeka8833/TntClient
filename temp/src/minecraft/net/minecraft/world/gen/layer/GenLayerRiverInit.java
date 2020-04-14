package net.minecraft.world.gen.layer;

public class GenLayerRiverInit extends GenLayer {
   public GenLayerRiverInit(long p_i2127_1_, GenLayer p_i2127_3_) {
      super(p_i2127_1_);
      this.field_75909_a = p_i2127_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_75904_1_), (long)(lvt_7_1_ + p_75904_2_));
            lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_5_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] > 0 ? this.func_75902_a(299999) + 2 : 0;
         }
      }

      return lvt_6_1_;
   }
}
