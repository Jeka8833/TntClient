package net.minecraft.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {
   public static int func_76272_a(Collection<? extends WeightedRandom.Item> p_76272_0_) {
      int lvt_1_1_ = 0;

      WeightedRandom.Item lvt_3_1_;
      for(Iterator lvt_2_1_ = p_76272_0_.iterator(); lvt_2_1_.hasNext(); lvt_1_1_ += lvt_3_1_.field_76292_a) {
         lvt_3_1_ = (WeightedRandom.Item)lvt_2_1_.next();
      }

      return lvt_1_1_;
   }

   public static <T extends WeightedRandom.Item> T func_76273_a(Random p_76273_0_, Collection<T> p_76273_1_, int p_76273_2_) {
      if (p_76273_2_ <= 0) {
         throw new IllegalArgumentException();
      } else {
         int lvt_3_1_ = p_76273_0_.nextInt(p_76273_2_);
         return func_180166_a(p_76273_1_, lvt_3_1_);
      }
   }

   public static <T extends WeightedRandom.Item> T func_180166_a(Collection<T> p_180166_0_, int p_180166_1_) {
      Iterator lvt_2_1_ = p_180166_0_.iterator();

      WeightedRandom.Item lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            return null;
         }

         lvt_3_1_ = (WeightedRandom.Item)lvt_2_1_.next();
         p_180166_1_ -= lvt_3_1_.field_76292_a;
      } while(p_180166_1_ >= 0);

      return lvt_3_1_;
   }

   public static <T extends WeightedRandom.Item> T func_76271_a(Random p_76271_0_, Collection<T> p_76271_1_) {
      return func_76273_a(p_76271_0_, p_76271_1_, func_76272_a(p_76271_1_));
   }

   public static class Item {
      protected int field_76292_a;

      public Item(int p_i1556_1_) {
         this.field_76292_a = p_i1556_1_;
      }
   }
}
