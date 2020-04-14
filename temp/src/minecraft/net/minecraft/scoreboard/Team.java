package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public abstract class Team {
   public boolean func_142054_a(Team p_142054_1_) {
      if (p_142054_1_ == null) {
         return false;
      } else {
         return this == p_142054_1_;
      }
   }

   public abstract String func_96661_b();

   public abstract String func_142053_d(String var1);

   public abstract boolean func_98297_h();

   public abstract boolean func_96665_g();

   public abstract Team.EnumVisible func_178770_i();

   public abstract Collection<String> func_96670_d();

   public abstract Team.EnumVisible func_178771_j();

   public static enum EnumVisible {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

      private static Map<String, Team.EnumVisible> field_178828_g = Maps.newHashMap();
      public final String field_178830_e;
      public final int field_178827_f;

      public static String[] func_178825_a() {
         return (String[])field_178828_g.keySet().toArray(new String[field_178828_g.size()]);
      }

      public static Team.EnumVisible func_178824_a(String p_178824_0_) {
         return (Team.EnumVisible)field_178828_g.get(p_178824_0_);
      }

      private EnumVisible(String p_i45550_3_, int p_i45550_4_) {
         this.field_178830_e = p_i45550_3_;
         this.field_178827_f = p_i45550_4_;
      }

      static {
         Team.EnumVisible[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            Team.EnumVisible lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_178828_g.put(lvt_3_1_.field_178830_e, lvt_3_1_);
         }

      }
   }
}
