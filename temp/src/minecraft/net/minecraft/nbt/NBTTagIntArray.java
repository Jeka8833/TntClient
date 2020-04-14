package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase {
   private int[] field_74749_a;

   NBTTagIntArray() {
   }

   public NBTTagIntArray(int[] p_i45132_1_) {
      this.field_74749_a = p_i45132_1_;
   }

   void func_74734_a(DataOutput p_74734_1_) throws IOException {
      p_74734_1_.writeInt(this.field_74749_a.length);

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_74749_a.length; ++lvt_2_1_) {
         p_74734_1_.writeInt(this.field_74749_a[lvt_2_1_]);
      }

   }

   void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException {
      p_152446_3_.func_152450_a(192L);
      int lvt_4_1_ = p_152446_1_.readInt();
      p_152446_3_.func_152450_a((long)(32 * lvt_4_1_));
      this.field_74749_a = new int[lvt_4_1_];

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         this.field_74749_a[lvt_5_1_] = p_152446_1_.readInt();
      }

   }

   public byte func_74732_a() {
      return 11;
   }

   public String toString() {
      String lvt_1_1_ = "[";
      int[] lvt_2_1_ = this.field_74749_a;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         int lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         lvt_1_1_ = lvt_1_1_ + lvt_5_1_ + ",";
      }

      return lvt_1_1_ + "]";
   }

   public NBTBase func_74737_b() {
      int[] lvt_1_1_ = new int[this.field_74749_a.length];
      System.arraycopy(this.field_74749_a, 0, lvt_1_1_, 0, this.field_74749_a.length);
      return new NBTTagIntArray(lvt_1_1_);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) ? Arrays.equals(this.field_74749_a, ((NBTTagIntArray)p_equals_1_).field_74749_a) : false;
   }

   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.field_74749_a);
   }

   public int[] func_150302_c() {
      return this.field_74749_a;
   }
}
