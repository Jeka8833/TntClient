package net.minecraft.util;

import java.util.Iterator;

public class ChatComponentText extends ChatComponentStyle {
   private final String field_150267_b;

   public ChatComponentText(String p_i45159_1_) {
      this.field_150267_b = p_i45159_1_;
   }

   public String func_150265_g() {
      return this.field_150267_b;
   }

   public String func_150261_e() {
      return this.field_150267_b;
   }

   public ChatComponentText func_150259_f() {
      ChatComponentText lvt_1_1_ = new ChatComponentText(this.field_150267_b);
      lvt_1_1_.func_150255_a(this.func_150256_b().func_150232_l());
      Iterator lvt_2_1_ = this.func_150253_a().iterator();

      while(lvt_2_1_.hasNext()) {
         IChatComponent lvt_3_1_ = (IChatComponent)lvt_2_1_.next();
         lvt_1_1_.func_150257_a(lvt_3_1_.func_150259_f());
      }

      return lvt_1_1_;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof ChatComponentText)) {
         return false;
      } else {
         ChatComponentText lvt_2_1_ = (ChatComponentText)p_equals_1_;
         return this.field_150267_b.equals(lvt_2_1_.func_150265_g()) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "TextComponent{text='" + this.field_150267_b + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }
}
