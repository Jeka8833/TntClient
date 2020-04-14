package net.minecraft.util;

import java.util.Iterator;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;

public class ChatComponentScore extends ChatComponentStyle {
   private final String field_179999_b;
   private final String field_180000_c;
   private String field_179998_d = "";

   public ChatComponentScore(String p_i45997_1_, String p_i45997_2_) {
      this.field_179999_b = p_i45997_1_;
      this.field_180000_c = p_i45997_2_;
   }

   public String func_179995_g() {
      return this.field_179999_b;
   }

   public String func_179994_h() {
      return this.field_180000_c;
   }

   public void func_179997_b(String p_179997_1_) {
      this.field_179998_d = p_179997_1_;
   }

   public String func_150261_e() {
      MinecraftServer lvt_1_1_ = MinecraftServer.func_71276_C();
      if (lvt_1_1_ != null && lvt_1_1_.func_175578_N() && StringUtils.func_151246_b(this.field_179998_d)) {
         Scoreboard lvt_2_1_ = lvt_1_1_.func_71218_a(0).func_96441_U();
         ScoreObjective lvt_3_1_ = lvt_2_1_.func_96518_b(this.field_180000_c);
         if (lvt_2_1_.func_178819_b(this.field_179999_b, lvt_3_1_)) {
            Score lvt_4_1_ = lvt_2_1_.func_96529_a(this.field_179999_b, lvt_3_1_);
            this.func_179997_b(String.format("%d", lvt_4_1_.func_96652_c()));
         } else {
            this.field_179998_d = "";
         }
      }

      return this.field_179998_d;
   }

   public ChatComponentScore func_150259_f() {
      ChatComponentScore lvt_1_1_ = new ChatComponentScore(this.field_179999_b, this.field_180000_c);
      lvt_1_1_.func_179997_b(this.field_179998_d);
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
      } else if (!(p_equals_1_ instanceof ChatComponentScore)) {
         return false;
      } else {
         ChatComponentScore lvt_2_1_ = (ChatComponentScore)p_equals_1_;
         return this.field_179999_b.equals(lvt_2_1_.field_179999_b) && this.field_180000_c.equals(lvt_2_1_.field_180000_c) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "ScoreComponent{name='" + this.field_179999_b + '\'' + "objective='" + this.field_180000_c + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }
}
