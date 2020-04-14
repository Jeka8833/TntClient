package net.minecraft.util;

import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class Rotations {
   protected final float field_179419_a;
   protected final float field_179417_b;
   protected final float field_179418_c;

   public Rotations(float p_i46009_1_, float p_i46009_2_, float p_i46009_3_) {
      this.field_179419_a = p_i46009_1_;
      this.field_179417_b = p_i46009_2_;
      this.field_179418_c = p_i46009_3_;
   }

   public Rotations(NBTTagList p_i46010_1_) {
      this.field_179419_a = p_i46010_1_.func_150308_e(0);
      this.field_179417_b = p_i46010_1_.func_150308_e(1);
      this.field_179418_c = p_i46010_1_.func_150308_e(2);
   }

   public NBTTagList func_179414_a() {
      NBTTagList lvt_1_1_ = new NBTTagList();
      lvt_1_1_.func_74742_a(new NBTTagFloat(this.field_179419_a));
      lvt_1_1_.func_74742_a(new NBTTagFloat(this.field_179417_b));
      lvt_1_1_.func_74742_a(new NBTTagFloat(this.field_179418_c));
      return lvt_1_1_;
   }

   public boolean equals(Object p_equals_1_) {
      if (!(p_equals_1_ instanceof Rotations)) {
         return false;
      } else {
         Rotations lvt_2_1_ = (Rotations)p_equals_1_;
         return this.field_179419_a == lvt_2_1_.field_179419_a && this.field_179417_b == lvt_2_1_.field_179417_b && this.field_179418_c == lvt_2_1_.field_179418_c;
      }
   }

   public float func_179415_b() {
      return this.field_179419_a;
   }

   public float func_179416_c() {
      return this.field_179417_b;
   }

   public float func_179413_d() {
      return this.field_179418_c;
   }
}
