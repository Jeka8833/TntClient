package net.minecraft.util;

import java.util.Iterator;

public class ChatComponentSelector extends ChatComponentStyle {
   private final String field_179993_b;

   public ChatComponentSelector(String p_i45996_1_) {
      this.field_179993_b = p_i45996_1_;
   }

   public String func_179992_g() {
      return this.field_179993_b;
   }

   public String func_150261_e() {
      return this.field_179993_b;
   }

   public ChatComponentSelector func_150259_f() {
      ChatComponentSelector lvt_1_1_ = new ChatComponentSelector(this.field_179993_b);
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
      } else if (!(p_equals_1_ instanceof ChatComponentSelector)) {
         return false;
      } else {
         ChatComponentSelector lvt_2_1_ = (ChatComponentSelector)p_equals_1_;
         return this.field_179993_b.equals(lvt_2_1_.field_179993_b) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "SelectorComponent{pattern='" + this.field_179993_b + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }
}
