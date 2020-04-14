package net.minecraft.client.util;

import com.google.gson.JsonObject;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.JsonUtils;
import org.lwjgl.opengl.GL14;

public class JsonBlendingMode {
   private static JsonBlendingMode field_148118_a = null;
   private final int field_148116_b;
   private final int field_148117_c;
   private final int field_148114_d;
   private final int field_148115_e;
   private final int field_148112_f;
   private final boolean field_148113_g;
   private final boolean field_148119_h;

   private JsonBlendingMode(boolean p_i45084_1_, boolean p_i45084_2_, int p_i45084_3_, int p_i45084_4_, int p_i45084_5_, int p_i45084_6_, int p_i45084_7_) {
      this.field_148113_g = p_i45084_1_;
      this.field_148116_b = p_i45084_3_;
      this.field_148114_d = p_i45084_4_;
      this.field_148117_c = p_i45084_5_;
      this.field_148115_e = p_i45084_6_;
      this.field_148119_h = p_i45084_2_;
      this.field_148112_f = p_i45084_7_;
   }

   public JsonBlendingMode() {
      this(false, true, 1, 0, 1, 0, 32774);
   }

   public JsonBlendingMode(int p_i45085_1_, int p_i45085_2_, int p_i45085_3_) {
      this(false, false, p_i45085_1_, p_i45085_2_, p_i45085_1_, p_i45085_2_, p_i45085_3_);
   }

   public JsonBlendingMode(int p_i45086_1_, int p_i45086_2_, int p_i45086_3_, int p_i45086_4_, int p_i45086_5_) {
      this(true, false, p_i45086_1_, p_i45086_2_, p_i45086_3_, p_i45086_4_, p_i45086_5_);
   }

   public void func_148109_a() {
      if (!this.equals(field_148118_a)) {
         if (field_148118_a == null || this.field_148119_h != field_148118_a.func_148111_b()) {
            field_148118_a = this;
            if (this.field_148119_h) {
               GlStateManager.func_179084_k();
               return;
            }

            GlStateManager.func_179147_l();
         }

         GL14.glBlendEquation(this.field_148112_f);
         if (this.field_148113_g) {
            GlStateManager.func_179120_a(this.field_148116_b, this.field_148114_d, this.field_148117_c, this.field_148115_e);
         } else {
            GlStateManager.func_179112_b(this.field_148116_b, this.field_148114_d);
         }

      }
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof JsonBlendingMode)) {
         return false;
      } else {
         JsonBlendingMode lvt_2_1_ = (JsonBlendingMode)p_equals_1_;
         if (this.field_148112_f != lvt_2_1_.field_148112_f) {
            return false;
         } else if (this.field_148115_e != lvt_2_1_.field_148115_e) {
            return false;
         } else if (this.field_148114_d != lvt_2_1_.field_148114_d) {
            return false;
         } else if (this.field_148119_h != lvt_2_1_.field_148119_h) {
            return false;
         } else if (this.field_148113_g != lvt_2_1_.field_148113_g) {
            return false;
         } else if (this.field_148117_c != lvt_2_1_.field_148117_c) {
            return false;
         } else {
            return this.field_148116_b == lvt_2_1_.field_148116_b;
         }
      }
   }

   public int hashCode() {
      int lvt_1_1_ = this.field_148116_b;
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_148117_c;
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_148114_d;
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_148115_e;
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_148112_f;
      lvt_1_1_ = 31 * lvt_1_1_ + (this.field_148113_g ? 1 : 0);
      lvt_1_1_ = 31 * lvt_1_1_ + (this.field_148119_h ? 1 : 0);
      return lvt_1_1_;
   }

   public boolean func_148111_b() {
      return this.field_148119_h;
   }

   public static JsonBlendingMode func_148110_a(JsonObject p_148110_0_) {
      if (p_148110_0_ == null) {
         return new JsonBlendingMode();
      } else {
         int lvt_1_1_ = 32774;
         int lvt_2_1_ = 1;
         int lvt_3_1_ = 0;
         int lvt_4_1_ = 1;
         int lvt_5_1_ = 0;
         boolean lvt_6_1_ = true;
         boolean lvt_7_1_ = false;
         if (JsonUtils.func_151205_a(p_148110_0_, "func")) {
            lvt_1_1_ = func_148108_a(p_148110_0_.get("func").getAsString());
            if (lvt_1_1_ != 32774) {
               lvt_6_1_ = false;
            }
         }

         if (JsonUtils.func_151205_a(p_148110_0_, "srcrgb")) {
            lvt_2_1_ = func_148107_b(p_148110_0_.get("srcrgb").getAsString());
            if (lvt_2_1_ != 1) {
               lvt_6_1_ = false;
            }
         }

         if (JsonUtils.func_151205_a(p_148110_0_, "dstrgb")) {
            lvt_3_1_ = func_148107_b(p_148110_0_.get("dstrgb").getAsString());
            if (lvt_3_1_ != 0) {
               lvt_6_1_ = false;
            }
         }

         if (JsonUtils.func_151205_a(p_148110_0_, "srcalpha")) {
            lvt_4_1_ = func_148107_b(p_148110_0_.get("srcalpha").getAsString());
            if (lvt_4_1_ != 1) {
               lvt_6_1_ = false;
            }

            lvt_7_1_ = true;
         }

         if (JsonUtils.func_151205_a(p_148110_0_, "dstalpha")) {
            lvt_5_1_ = func_148107_b(p_148110_0_.get("dstalpha").getAsString());
            if (lvt_5_1_ != 0) {
               lvt_6_1_ = false;
            }

            lvt_7_1_ = true;
         }

         if (lvt_6_1_) {
            return new JsonBlendingMode();
         } else {
            return lvt_7_1_ ? new JsonBlendingMode(lvt_2_1_, lvt_3_1_, lvt_4_1_, lvt_5_1_, lvt_1_1_) : new JsonBlendingMode(lvt_2_1_, lvt_3_1_, lvt_1_1_);
         }
      }
   }

   private static int func_148108_a(String p_148108_0_) {
      String lvt_1_1_ = p_148108_0_.trim().toLowerCase();
      if (lvt_1_1_.equals("add")) {
         return 32774;
      } else if (lvt_1_1_.equals("subtract")) {
         return 32778;
      } else if (lvt_1_1_.equals("reversesubtract")) {
         return 32779;
      } else if (lvt_1_1_.equals("reverse_subtract")) {
         return 32779;
      } else if (lvt_1_1_.equals("min")) {
         return 32775;
      } else {
         return lvt_1_1_.equals("max") ? '\u8008' : '\u8006';
      }
   }

   private static int func_148107_b(String p_148107_0_) {
      String lvt_1_1_ = p_148107_0_.trim().toLowerCase();
      lvt_1_1_ = lvt_1_1_.replaceAll("_", "");
      lvt_1_1_ = lvt_1_1_.replaceAll("one", "1");
      lvt_1_1_ = lvt_1_1_.replaceAll("zero", "0");
      lvt_1_1_ = lvt_1_1_.replaceAll("minus", "-");
      if (lvt_1_1_.equals("0")) {
         return 0;
      } else if (lvt_1_1_.equals("1")) {
         return 1;
      } else if (lvt_1_1_.equals("srccolor")) {
         return 768;
      } else if (lvt_1_1_.equals("1-srccolor")) {
         return 769;
      } else if (lvt_1_1_.equals("dstcolor")) {
         return 774;
      } else if (lvt_1_1_.equals("1-dstcolor")) {
         return 775;
      } else if (lvt_1_1_.equals("srcalpha")) {
         return 770;
      } else if (lvt_1_1_.equals("1-srcalpha")) {
         return 771;
      } else if (lvt_1_1_.equals("dstalpha")) {
         return 772;
      } else {
         return lvt_1_1_.equals("1-dstalpha") ? 773 : -1;
      }
   }
}
