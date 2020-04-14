package net.minecraft.stats;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;

public class StatBase {
   public final String field_75975_e;
   private final IChatComponent field_75978_a;
   public boolean field_75972_f;
   private final IStatType field_75976_b;
   private final IScoreObjectiveCriteria field_150957_c;
   private Class<? extends IJsonSerializable> field_150956_d;
   private static NumberFormat field_75977_c;
   public static IStatType field_75980_h;
   private static DecimalFormat field_75974_d;
   public static IStatType field_75981_i;
   public static IStatType field_75979_j;
   public static IStatType field_111202_k;

   public StatBase(String p_i45307_1_, IChatComponent p_i45307_2_, IStatType p_i45307_3_) {
      this.field_75975_e = p_i45307_1_;
      this.field_75978_a = p_i45307_2_;
      this.field_75976_b = p_i45307_3_;
      this.field_150957_c = new ObjectiveStat(this);
      IScoreObjectiveCriteria.field_96643_a.put(this.field_150957_c.func_96636_a(), this.field_150957_c);
   }

   public StatBase(String p_i45308_1_, IChatComponent p_i45308_2_) {
      this(p_i45308_1_, p_i45308_2_, field_75980_h);
   }

   public StatBase func_75966_h() {
      this.field_75972_f = true;
      return this;
   }

   public StatBase func_75971_g() {
      if (StatList.field_75942_a.containsKey(this.field_75975_e)) {
         throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.field_75942_a.get(this.field_75975_e)).field_75978_a + "\" and \"" + this.field_75978_a + "\" at id " + this.field_75975_e);
      } else {
         StatList.field_75940_b.add(this);
         StatList.field_75942_a.put(this.field_75975_e, this);
         return this;
      }
   }

   public boolean func_75967_d() {
      return false;
   }

   public String func_75968_a(int p_75968_1_) {
      return this.field_75976_b.func_75843_a(p_75968_1_);
   }

   public IChatComponent func_150951_e() {
      IChatComponent lvt_1_1_ = this.field_75978_a.func_150259_f();
      lvt_1_1_.func_150256_b().func_150238_a(EnumChatFormatting.GRAY);
      lvt_1_1_.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new ChatComponentText(this.field_75975_e)));
      return lvt_1_1_;
   }

   public IChatComponent func_150955_j() {
      IChatComponent lvt_1_1_ = this.func_150951_e();
      IChatComponent lvt_2_1_ = (new ChatComponentText("[")).func_150257_a(lvt_1_1_).func_150258_a("]");
      lvt_2_1_.func_150255_a(lvt_1_1_.func_150256_b());
      return lvt_2_1_;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         StatBase lvt_2_1_ = (StatBase)p_equals_1_;
         return this.field_75975_e.equals(lvt_2_1_.field_75975_e);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_75975_e.hashCode();
   }

   public String toString() {
      return "Stat{id=" + this.field_75975_e + ", nameId=" + this.field_75978_a + ", awardLocallyOnly=" + this.field_75972_f + ", formatter=" + this.field_75976_b + ", objectiveCriteria=" + this.field_150957_c + '}';
   }

   public IScoreObjectiveCriteria func_150952_k() {
      return this.field_150957_c;
   }

   public Class<? extends IJsonSerializable> func_150954_l() {
      return this.field_150956_d;
   }

   public StatBase func_150953_b(Class<? extends IJsonSerializable> p_150953_1_) {
      this.field_150956_d = p_150953_1_;
      return this;
   }

   static {
      field_75977_c = NumberFormat.getIntegerInstance(Locale.US);
      field_75980_h = new IStatType() {
         public String func_75843_a(int p_75843_1_) {
            return StatBase.field_75977_c.format((long)p_75843_1_);
         }
      };
      field_75974_d = new DecimalFormat("########0.00");
      field_75981_i = new IStatType() {
         public String func_75843_a(int p_75843_1_) {
            double lvt_2_1_ = (double)p_75843_1_ / 20.0D;
            double lvt_4_1_ = lvt_2_1_ / 60.0D;
            double lvt_6_1_ = lvt_4_1_ / 60.0D;
            double lvt_8_1_ = lvt_6_1_ / 24.0D;
            double lvt_10_1_ = lvt_8_1_ / 365.0D;
            if (lvt_10_1_ > 0.5D) {
               return StatBase.field_75974_d.format(lvt_10_1_) + " y";
            } else if (lvt_8_1_ > 0.5D) {
               return StatBase.field_75974_d.format(lvt_8_1_) + " d";
            } else if (lvt_6_1_ > 0.5D) {
               return StatBase.field_75974_d.format(lvt_6_1_) + " h";
            } else {
               return lvt_4_1_ > 0.5D ? StatBase.field_75974_d.format(lvt_4_1_) + " m" : lvt_2_1_ + " s";
            }
         }
      };
      field_75979_j = new IStatType() {
         public String func_75843_a(int p_75843_1_) {
            double lvt_2_1_ = (double)p_75843_1_ / 100.0D;
            double lvt_4_1_ = lvt_2_1_ / 1000.0D;
            if (lvt_4_1_ > 0.5D) {
               return StatBase.field_75974_d.format(lvt_4_1_) + " km";
            } else {
               return lvt_2_1_ > 0.5D ? StatBase.field_75974_d.format(lvt_2_1_) + " m" : p_75843_1_ + " cm";
            }
         }
      };
      field_111202_k = new IStatType() {
         public String func_75843_a(int p_75843_1_) {
            return StatBase.field_75974_d.format((double)p_75843_1_ * 0.1D);
         }
      };
   }
}
