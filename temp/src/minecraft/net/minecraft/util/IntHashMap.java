package net.minecraft.util;

public class IntHashMap<V> {
   private transient IntHashMap.Entry<V>[] field_76055_a = new IntHashMap.Entry[16];
   private transient int field_76053_b;
   private int field_76054_c = 12;
   private final float field_76051_d = 0.75F;

   private static int func_76044_g(int p_76044_0_) {
      p_76044_0_ ^= p_76044_0_ >>> 20 ^ p_76044_0_ >>> 12;
      return p_76044_0_ ^ p_76044_0_ >>> 7 ^ p_76044_0_ >>> 4;
   }

   private static int func_76043_a(int p_76043_0_, int p_76043_1_) {
      return p_76043_0_ & p_76043_1_ - 1;
   }

   public V func_76041_a(int p_76041_1_) {
      int lvt_2_1_ = func_76044_g(p_76041_1_);

      for(IntHashMap.Entry lvt_3_1_ = this.field_76055_a[func_76043_a(lvt_2_1_, this.field_76055_a.length)]; lvt_3_1_ != null; lvt_3_1_ = lvt_3_1_.field_76034_c) {
         if (lvt_3_1_.field_76035_a == p_76041_1_) {
            return lvt_3_1_.field_76033_b;
         }
      }

      return null;
   }

   public boolean func_76037_b(int p_76037_1_) {
      return this.func_76045_c(p_76037_1_) != null;
   }

   final IntHashMap.Entry<V> func_76045_c(int p_76045_1_) {
      int lvt_2_1_ = func_76044_g(p_76045_1_);

      for(IntHashMap.Entry lvt_3_1_ = this.field_76055_a[func_76043_a(lvt_2_1_, this.field_76055_a.length)]; lvt_3_1_ != null; lvt_3_1_ = lvt_3_1_.field_76034_c) {
         if (lvt_3_1_.field_76035_a == p_76045_1_) {
            return lvt_3_1_;
         }
      }

      return null;
   }

   public void func_76038_a(int p_76038_1_, V p_76038_2_) {
      int lvt_3_1_ = func_76044_g(p_76038_1_);
      int lvt_4_1_ = func_76043_a(lvt_3_1_, this.field_76055_a.length);

      for(IntHashMap.Entry lvt_5_1_ = this.field_76055_a[lvt_4_1_]; lvt_5_1_ != null; lvt_5_1_ = lvt_5_1_.field_76034_c) {
         if (lvt_5_1_.field_76035_a == p_76038_1_) {
            lvt_5_1_.field_76033_b = p_76038_2_;
            return;
         }
      }

      this.func_76040_a(lvt_3_1_, p_76038_1_, p_76038_2_, lvt_4_1_);
   }

   private void func_76047_h(int p_76047_1_) {
      IntHashMap.Entry<V>[] lvt_2_1_ = this.field_76055_a;
      int lvt_3_1_ = lvt_2_1_.length;
      if (lvt_3_1_ == 1073741824) {
         this.field_76054_c = Integer.MAX_VALUE;
      } else {
         IntHashMap.Entry<V>[] lvt_4_1_ = new IntHashMap.Entry[p_76047_1_];
         this.func_76048_a(lvt_4_1_);
         this.field_76055_a = lvt_4_1_;
         this.field_76054_c = (int)((float)p_76047_1_ * this.field_76051_d);
      }
   }

   private void func_76048_a(IntHashMap.Entry<V>[] p_76048_1_) {
      IntHashMap.Entry<V>[] lvt_2_1_ = this.field_76055_a;
      int lvt_3_1_ = p_76048_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_2_1_.length; ++lvt_4_1_) {
         IntHashMap.Entry<V> lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_ != null) {
            lvt_2_1_[lvt_4_1_] = null;

            IntHashMap.Entry lvt_6_1_;
            do {
               lvt_6_1_ = lvt_5_1_.field_76034_c;
               int lvt_7_1_ = func_76043_a(lvt_5_1_.field_76032_d, lvt_3_1_);
               lvt_5_1_.field_76034_c = p_76048_1_[lvt_7_1_];
               p_76048_1_[lvt_7_1_] = lvt_5_1_;
               lvt_5_1_ = lvt_6_1_;
            } while(lvt_6_1_ != null);
         }
      }

   }

   public V func_76049_d(int p_76049_1_) {
      IntHashMap.Entry<V> lvt_2_1_ = this.func_76036_e(p_76049_1_);
      return lvt_2_1_ == null ? null : lvt_2_1_.field_76033_b;
   }

   final IntHashMap.Entry<V> func_76036_e(int p_76036_1_) {
      int lvt_2_1_ = func_76044_g(p_76036_1_);
      int lvt_3_1_ = func_76043_a(lvt_2_1_, this.field_76055_a.length);
      IntHashMap.Entry<V> lvt_4_1_ = this.field_76055_a[lvt_3_1_];

      IntHashMap.Entry lvt_5_1_;
      IntHashMap.Entry lvt_6_1_;
      for(lvt_5_1_ = lvt_4_1_; lvt_5_1_ != null; lvt_5_1_ = lvt_6_1_) {
         lvt_6_1_ = lvt_5_1_.field_76034_c;
         if (lvt_5_1_.field_76035_a == p_76036_1_) {
            --this.field_76053_b;
            if (lvt_4_1_ == lvt_5_1_) {
               this.field_76055_a[lvt_3_1_] = lvt_6_1_;
            } else {
               lvt_4_1_.field_76034_c = lvt_6_1_;
            }

            return lvt_5_1_;
         }

         lvt_4_1_ = lvt_5_1_;
      }

      return lvt_5_1_;
   }

   public void func_76046_c() {
      IntHashMap.Entry<V>[] lvt_1_1_ = this.field_76055_a;

      for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_.length; ++lvt_2_1_) {
         lvt_1_1_[lvt_2_1_] = null;
      }

      this.field_76053_b = 0;
   }

   private void func_76040_a(int p_76040_1_, int p_76040_2_, V p_76040_3_, int p_76040_4_) {
      IntHashMap.Entry<V> lvt_5_1_ = this.field_76055_a[p_76040_4_];
      this.field_76055_a[p_76040_4_] = new IntHashMap.Entry(p_76040_1_, p_76040_2_, p_76040_3_, lvt_5_1_);
      if (this.field_76053_b++ >= this.field_76054_c) {
         this.func_76047_h(2 * this.field_76055_a.length);
      }

   }

   static class Entry<V> {
      final int field_76035_a;
      V field_76033_b;
      IntHashMap.Entry<V> field_76034_c;
      final int field_76032_d;

      Entry(int p_i1552_1_, int p_i1552_2_, V p_i1552_3_, IntHashMap.Entry<V> p_i1552_4_) {
         this.field_76033_b = p_i1552_3_;
         this.field_76034_c = p_i1552_4_;
         this.field_76035_a = p_i1552_2_;
         this.field_76032_d = p_i1552_1_;
      }

      public final int func_76031_a() {
         return this.field_76035_a;
      }

      public final V func_76030_b() {
         return this.field_76033_b;
      }

      public final boolean equals(Object p_equals_1_) {
         if (!(p_equals_1_ instanceof IntHashMap.Entry)) {
            return false;
         } else {
            IntHashMap.Entry<V> lvt_2_1_ = (IntHashMap.Entry)p_equals_1_;
            Object lvt_3_1_ = this.func_76031_a();
            Object lvt_4_1_ = lvt_2_1_.func_76031_a();
            if (lvt_3_1_ == lvt_4_1_ || lvt_3_1_ != null && lvt_3_1_.equals(lvt_4_1_)) {
               Object lvt_5_1_ = this.func_76030_b();
               Object lvt_6_1_ = lvt_2_1_.func_76030_b();
               if (lvt_5_1_ == lvt_6_1_ || lvt_5_1_ != null && lvt_5_1_.equals(lvt_6_1_)) {
                  return true;
               }
            }

            return false;
         }
      }

      public final int hashCode() {
         return IntHashMap.func_76044_g(this.field_76035_a);
      }

      public final String toString() {
         return this.func_76031_a() + "=" + this.func_76030_b();
      }
   }
}
