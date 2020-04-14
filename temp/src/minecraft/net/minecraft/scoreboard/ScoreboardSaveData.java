package net.minecraft.scoreboard;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreboardSaveData extends WorldSavedData {
   private static final Logger field_151481_a = LogManager.getLogger();
   private Scoreboard field_96507_a;
   private NBTTagCompound field_96506_b;

   public ScoreboardSaveData() {
      this("scoreboard");
   }

   public ScoreboardSaveData(String p_i2310_1_) {
      super(p_i2310_1_);
   }

   public void func_96499_a(Scoreboard p_96499_1_) {
      this.field_96507_a = p_96499_1_;
      if (this.field_96506_b != null) {
         this.func_76184_a(this.field_96506_b);
      }

   }

   public void func_76184_a(NBTTagCompound p_76184_1_) {
      if (this.field_96507_a == null) {
         this.field_96506_b = p_76184_1_;
      } else {
         this.func_96501_b(p_76184_1_.func_150295_c("Objectives", 10));
         this.func_96500_c(p_76184_1_.func_150295_c("PlayerScores", 10));
         if (p_76184_1_.func_150297_b("DisplaySlots", 10)) {
            this.func_96504_c(p_76184_1_.func_74775_l("DisplaySlots"));
         }

         if (p_76184_1_.func_150297_b("Teams", 9)) {
            this.func_96498_a(p_76184_1_.func_150295_c("Teams", 10));
         }

      }
   }

   protected void func_96498_a(NBTTagList p_96498_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_96498_1_.func_74745_c(); ++lvt_2_1_) {
         NBTTagCompound lvt_3_1_ = p_96498_1_.func_150305_b(lvt_2_1_);
         String lvt_4_1_ = lvt_3_1_.func_74779_i("Name");
         if (lvt_4_1_.length() > 16) {
            lvt_4_1_ = lvt_4_1_.substring(0, 16);
         }

         ScorePlayerTeam lvt_5_1_ = this.field_96507_a.func_96527_f(lvt_4_1_);
         String lvt_6_1_ = lvt_3_1_.func_74779_i("DisplayName");
         if (lvt_6_1_.length() > 32) {
            lvt_6_1_ = lvt_6_1_.substring(0, 32);
         }

         lvt_5_1_.func_96664_a(lvt_6_1_);
         if (lvt_3_1_.func_150297_b("TeamColor", 8)) {
            lvt_5_1_.func_178774_a(EnumChatFormatting.func_96300_b(lvt_3_1_.func_74779_i("TeamColor")));
         }

         lvt_5_1_.func_96666_b(lvt_3_1_.func_74779_i("Prefix"));
         lvt_5_1_.func_96662_c(lvt_3_1_.func_74779_i("Suffix"));
         if (lvt_3_1_.func_150297_b("AllowFriendlyFire", 99)) {
            lvt_5_1_.func_96660_a(lvt_3_1_.func_74767_n("AllowFriendlyFire"));
         }

         if (lvt_3_1_.func_150297_b("SeeFriendlyInvisibles", 99)) {
            lvt_5_1_.func_98300_b(lvt_3_1_.func_74767_n("SeeFriendlyInvisibles"));
         }

         Team.EnumVisible lvt_7_2_;
         if (lvt_3_1_.func_150297_b("NameTagVisibility", 8)) {
            lvt_7_2_ = Team.EnumVisible.func_178824_a(lvt_3_1_.func_74779_i("NameTagVisibility"));
            if (lvt_7_2_ != null) {
               lvt_5_1_.func_178772_a(lvt_7_2_);
            }
         }

         if (lvt_3_1_.func_150297_b("DeathMessageVisibility", 8)) {
            lvt_7_2_ = Team.EnumVisible.func_178824_a(lvt_3_1_.func_74779_i("DeathMessageVisibility"));
            if (lvt_7_2_ != null) {
               lvt_5_1_.func_178773_b(lvt_7_2_);
            }
         }

         this.func_96502_a(lvt_5_1_, lvt_3_1_.func_150295_c("Players", 8));
      }

   }

   protected void func_96502_a(ScorePlayerTeam p_96502_1_, NBTTagList p_96502_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < p_96502_2_.func_74745_c(); ++lvt_3_1_) {
         this.field_96507_a.func_151392_a(p_96502_2_.func_150307_f(lvt_3_1_), p_96502_1_.func_96661_b());
      }

   }

   protected void func_96504_c(NBTTagCompound p_96504_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < 19; ++lvt_2_1_) {
         if (p_96504_1_.func_150297_b("slot_" + lvt_2_1_, 8)) {
            String lvt_3_1_ = p_96504_1_.func_74779_i("slot_" + lvt_2_1_);
            ScoreObjective lvt_4_1_ = this.field_96507_a.func_96518_b(lvt_3_1_);
            this.field_96507_a.func_96530_a(lvt_2_1_, lvt_4_1_);
         }
      }

   }

   protected void func_96501_b(NBTTagList p_96501_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_96501_1_.func_74745_c(); ++lvt_2_1_) {
         NBTTagCompound lvt_3_1_ = p_96501_1_.func_150305_b(lvt_2_1_);
         IScoreObjectiveCriteria lvt_4_1_ = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(lvt_3_1_.func_74779_i("CriteriaName"));
         if (lvt_4_1_ != null) {
            String lvt_5_1_ = lvt_3_1_.func_74779_i("Name");
            if (lvt_5_1_.length() > 16) {
               lvt_5_1_ = lvt_5_1_.substring(0, 16);
            }

            ScoreObjective lvt_6_1_ = this.field_96507_a.func_96535_a(lvt_5_1_, lvt_4_1_);
            lvt_6_1_.func_96681_a(lvt_3_1_.func_74779_i("DisplayName"));
            lvt_6_1_.func_178767_a(IScoreObjectiveCriteria.EnumRenderType.func_178795_a(lvt_3_1_.func_74779_i("RenderType")));
         }
      }

   }

   protected void func_96500_c(NBTTagList p_96500_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_96500_1_.func_74745_c(); ++lvt_2_1_) {
         NBTTagCompound lvt_3_1_ = p_96500_1_.func_150305_b(lvt_2_1_);
         ScoreObjective lvt_4_1_ = this.field_96507_a.func_96518_b(lvt_3_1_.func_74779_i("Objective"));
         String lvt_5_1_ = lvt_3_1_.func_74779_i("Name");
         if (lvt_5_1_.length() > 40) {
            lvt_5_1_ = lvt_5_1_.substring(0, 40);
         }

         Score lvt_6_1_ = this.field_96507_a.func_96529_a(lvt_5_1_, lvt_4_1_);
         lvt_6_1_.func_96647_c(lvt_3_1_.func_74762_e("Score"));
         if (lvt_3_1_.func_74764_b("Locked")) {
            lvt_6_1_.func_178815_a(lvt_3_1_.func_74767_n("Locked"));
         }
      }

   }

   public void func_76187_b(NBTTagCompound p_76187_1_) {
      if (this.field_96507_a == null) {
         field_151481_a.warn("Tried to save scoreboard without having a scoreboard...");
      } else {
         p_76187_1_.func_74782_a("Objectives", this.func_96505_b());
         p_76187_1_.func_74782_a("PlayerScores", this.func_96503_e());
         p_76187_1_.func_74782_a("Teams", this.func_96496_a());
         this.func_96497_d(p_76187_1_);
      }
   }

   protected NBTTagList func_96496_a() {
      NBTTagList lvt_1_1_ = new NBTTagList();
      Collection<ScorePlayerTeam> lvt_2_1_ = this.field_96507_a.func_96525_g();
      Iterator lvt_3_1_ = lvt_2_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         ScorePlayerTeam lvt_4_1_ = (ScorePlayerTeam)lvt_3_1_.next();
         NBTTagCompound lvt_5_1_ = new NBTTagCompound();
         lvt_5_1_.func_74778_a("Name", lvt_4_1_.func_96661_b());
         lvt_5_1_.func_74778_a("DisplayName", lvt_4_1_.func_96669_c());
         if (lvt_4_1_.func_178775_l().func_175746_b() >= 0) {
            lvt_5_1_.func_74778_a("TeamColor", lvt_4_1_.func_178775_l().func_96297_d());
         }

         lvt_5_1_.func_74778_a("Prefix", lvt_4_1_.func_96668_e());
         lvt_5_1_.func_74778_a("Suffix", lvt_4_1_.func_96663_f());
         lvt_5_1_.func_74757_a("AllowFriendlyFire", lvt_4_1_.func_96665_g());
         lvt_5_1_.func_74757_a("SeeFriendlyInvisibles", lvt_4_1_.func_98297_h());
         lvt_5_1_.func_74778_a("NameTagVisibility", lvt_4_1_.func_178770_i().field_178830_e);
         lvt_5_1_.func_74778_a("DeathMessageVisibility", lvt_4_1_.func_178771_j().field_178830_e);
         NBTTagList lvt_6_1_ = new NBTTagList();
         Iterator lvt_7_1_ = lvt_4_1_.func_96670_d().iterator();

         while(lvt_7_1_.hasNext()) {
            String lvt_8_1_ = (String)lvt_7_1_.next();
            lvt_6_1_.func_74742_a(new NBTTagString(lvt_8_1_));
         }

         lvt_5_1_.func_74782_a("Players", lvt_6_1_);
         lvt_1_1_.func_74742_a(lvt_5_1_);
      }

      return lvt_1_1_;
   }

   protected void func_96497_d(NBTTagCompound p_96497_1_) {
      NBTTagCompound lvt_2_1_ = new NBTTagCompound();
      boolean lvt_3_1_ = false;

      for(int lvt_4_1_ = 0; lvt_4_1_ < 19; ++lvt_4_1_) {
         ScoreObjective lvt_5_1_ = this.field_96507_a.func_96539_a(lvt_4_1_);
         if (lvt_5_1_ != null) {
            lvt_2_1_.func_74778_a("slot_" + lvt_4_1_, lvt_5_1_.func_96679_b());
            lvt_3_1_ = true;
         }
      }

      if (lvt_3_1_) {
         p_96497_1_.func_74782_a("DisplaySlots", lvt_2_1_);
      }

   }

   protected NBTTagList func_96505_b() {
      NBTTagList lvt_1_1_ = new NBTTagList();
      Collection<ScoreObjective> lvt_2_1_ = this.field_96507_a.func_96514_c();
      Iterator lvt_3_1_ = lvt_2_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         ScoreObjective lvt_4_1_ = (ScoreObjective)lvt_3_1_.next();
         if (lvt_4_1_.func_96680_c() != null) {
            NBTTagCompound lvt_5_1_ = new NBTTagCompound();
            lvt_5_1_.func_74778_a("Name", lvt_4_1_.func_96679_b());
            lvt_5_1_.func_74778_a("CriteriaName", lvt_4_1_.func_96680_c().func_96636_a());
            lvt_5_1_.func_74778_a("DisplayName", lvt_4_1_.func_96678_d());
            lvt_5_1_.func_74778_a("RenderType", lvt_4_1_.func_178766_e().func_178796_a());
            lvt_1_1_.func_74742_a(lvt_5_1_);
         }
      }

      return lvt_1_1_;
   }

   protected NBTTagList func_96503_e() {
      NBTTagList lvt_1_1_ = new NBTTagList();
      Collection<Score> lvt_2_1_ = this.field_96507_a.func_96528_e();
      Iterator lvt_3_1_ = lvt_2_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         Score lvt_4_1_ = (Score)lvt_3_1_.next();
         if (lvt_4_1_.func_96645_d() != null) {
            NBTTagCompound lvt_5_1_ = new NBTTagCompound();
            lvt_5_1_.func_74778_a("Name", lvt_4_1_.func_96653_e());
            lvt_5_1_.func_74778_a("Objective", lvt_4_1_.func_96645_d().func_96679_b());
            lvt_5_1_.func_74768_a("Score", lvt_4_1_.func_96652_c());
            lvt_5_1_.func_74757_a("Locked", lvt_4_1_.func_178816_g());
            lvt_1_1_.func_74742_a(lvt_5_1_);
         }
      }

      return lvt_1_1_;
   }
}
