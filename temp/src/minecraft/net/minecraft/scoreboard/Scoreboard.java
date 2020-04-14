package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class Scoreboard {
   private final Map<String, ScoreObjective> field_96545_a = Maps.newHashMap();
   private final Map<IScoreObjectiveCriteria, List<ScoreObjective>> field_96543_b = Maps.newHashMap();
   private final Map<String, Map<ScoreObjective, Score>> field_96544_c = Maps.newHashMap();
   private final ScoreObjective[] field_96541_d = new ScoreObjective[19];
   private final Map<String, ScorePlayerTeam> field_96542_e = Maps.newHashMap();
   private final Map<String, ScorePlayerTeam> field_96540_f = Maps.newHashMap();
   private static String[] field_178823_g = null;

   public ScoreObjective func_96518_b(String p_96518_1_) {
      return (ScoreObjective)this.field_96545_a.get(p_96518_1_);
   }

   public ScoreObjective func_96535_a(String p_96535_1_, IScoreObjectiveCriteria p_96535_2_) {
      if (p_96535_1_.length() > 16) {
         throw new IllegalArgumentException("The objective name '" + p_96535_1_ + "' is too long!");
      } else {
         ScoreObjective lvt_3_1_ = this.func_96518_b(p_96535_1_);
         if (lvt_3_1_ != null) {
            throw new IllegalArgumentException("An objective with the name '" + p_96535_1_ + "' already exists!");
         } else {
            lvt_3_1_ = new ScoreObjective(this, p_96535_1_, p_96535_2_);
            List<ScoreObjective> lvt_4_1_ = (List)this.field_96543_b.get(p_96535_2_);
            if (lvt_4_1_ == null) {
               lvt_4_1_ = Lists.newArrayList();
               this.field_96543_b.put(p_96535_2_, lvt_4_1_);
            }

            ((List)lvt_4_1_).add(lvt_3_1_);
            this.field_96545_a.put(p_96535_1_, lvt_3_1_);
            this.func_96522_a(lvt_3_1_);
            return lvt_3_1_;
         }
      }
   }

   public Collection<ScoreObjective> func_96520_a(IScoreObjectiveCriteria p_96520_1_) {
      Collection<ScoreObjective> lvt_2_1_ = (Collection)this.field_96543_b.get(p_96520_1_);
      return lvt_2_1_ == null ? Lists.newArrayList() : Lists.newArrayList((Iterable)lvt_2_1_);
   }

   public boolean func_178819_b(String p_178819_1_, ScoreObjective p_178819_2_) {
      Map<ScoreObjective, Score> lvt_3_1_ = (Map)this.field_96544_c.get(p_178819_1_);
      if (lvt_3_1_ == null) {
         return false;
      } else {
         Score lvt_4_1_ = (Score)lvt_3_1_.get(p_178819_2_);
         return lvt_4_1_ != null;
      }
   }

   public Score func_96529_a(String p_96529_1_, ScoreObjective p_96529_2_) {
      if (p_96529_1_.length() > 40) {
         throw new IllegalArgumentException("The player name '" + p_96529_1_ + "' is too long!");
      } else {
         Map<ScoreObjective, Score> lvt_3_1_ = (Map)this.field_96544_c.get(p_96529_1_);
         if (lvt_3_1_ == null) {
            lvt_3_1_ = Maps.newHashMap();
            this.field_96544_c.put(p_96529_1_, lvt_3_1_);
         }

         Score lvt_4_1_ = (Score)((Map)lvt_3_1_).get(p_96529_2_);
         if (lvt_4_1_ == null) {
            lvt_4_1_ = new Score(this, p_96529_2_, p_96529_1_);
            ((Map)lvt_3_1_).put(p_96529_2_, lvt_4_1_);
         }

         return lvt_4_1_;
      }
   }

   public Collection<Score> func_96534_i(ScoreObjective p_96534_1_) {
      List<Score> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = this.field_96544_c.values().iterator();

      while(lvt_3_1_.hasNext()) {
         Map<ScoreObjective, Score> lvt_4_1_ = (Map)lvt_3_1_.next();
         Score lvt_5_1_ = (Score)lvt_4_1_.get(p_96534_1_);
         if (lvt_5_1_ != null) {
            lvt_2_1_.add(lvt_5_1_);
         }
      }

      Collections.sort(lvt_2_1_, Score.field_96658_a);
      return lvt_2_1_;
   }

   public Collection<ScoreObjective> func_96514_c() {
      return this.field_96545_a.values();
   }

   public Collection<String> func_96526_d() {
      return this.field_96544_c.keySet();
   }

   public void func_178822_d(String p_178822_1_, ScoreObjective p_178822_2_) {
      Map lvt_3_2_;
      if (p_178822_2_ == null) {
         lvt_3_2_ = (Map)this.field_96544_c.remove(p_178822_1_);
         if (lvt_3_2_ != null) {
            this.func_96516_a(p_178822_1_);
         }
      } else {
         lvt_3_2_ = (Map)this.field_96544_c.get(p_178822_1_);
         if (lvt_3_2_ != null) {
            Score lvt_4_1_ = (Score)lvt_3_2_.remove(p_178822_2_);
            if (lvt_3_2_.size() < 1) {
               Map<ScoreObjective, Score> lvt_5_1_ = (Map)this.field_96544_c.remove(p_178822_1_);
               if (lvt_5_1_ != null) {
                  this.func_96516_a(p_178822_1_);
               }
            } else if (lvt_4_1_ != null) {
               this.func_178820_a(p_178822_1_, p_178822_2_);
            }
         }
      }

   }

   public Collection<Score> func_96528_e() {
      Collection<Map<ScoreObjective, Score>> lvt_1_1_ = this.field_96544_c.values();
      List<Score> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = lvt_1_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         Map<ScoreObjective, Score> lvt_4_1_ = (Map)lvt_3_1_.next();
         lvt_2_1_.addAll(lvt_4_1_.values());
      }

      return lvt_2_1_;
   }

   public Map<ScoreObjective, Score> func_96510_d(String p_96510_1_) {
      Map<ScoreObjective, Score> lvt_2_1_ = (Map)this.field_96544_c.get(p_96510_1_);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = Maps.newHashMap();
      }

      return (Map)lvt_2_1_;
   }

   public void func_96519_k(ScoreObjective p_96519_1_) {
      this.field_96545_a.remove(p_96519_1_.func_96679_b());

      for(int lvt_2_1_ = 0; lvt_2_1_ < 19; ++lvt_2_1_) {
         if (this.func_96539_a(lvt_2_1_) == p_96519_1_) {
            this.func_96530_a(lvt_2_1_, (ScoreObjective)null);
         }
      }

      List<ScoreObjective> lvt_2_2_ = (List)this.field_96543_b.get(p_96519_1_.func_96680_c());
      if (lvt_2_2_ != null) {
         lvt_2_2_.remove(p_96519_1_);
      }

      Iterator lvt_3_1_ = this.field_96544_c.values().iterator();

      while(lvt_3_1_.hasNext()) {
         Map<ScoreObjective, Score> lvt_4_1_ = (Map)lvt_3_1_.next();
         lvt_4_1_.remove(p_96519_1_);
      }

      this.func_96533_c(p_96519_1_);
   }

   public void func_96530_a(int p_96530_1_, ScoreObjective p_96530_2_) {
      this.field_96541_d[p_96530_1_] = p_96530_2_;
   }

   public ScoreObjective func_96539_a(int p_96539_1_) {
      return this.field_96541_d[p_96539_1_];
   }

   public ScorePlayerTeam func_96508_e(String p_96508_1_) {
      return (ScorePlayerTeam)this.field_96542_e.get(p_96508_1_);
   }

   public ScorePlayerTeam func_96527_f(String p_96527_1_) {
      if (p_96527_1_.length() > 16) {
         throw new IllegalArgumentException("The team name '" + p_96527_1_ + "' is too long!");
      } else {
         ScorePlayerTeam lvt_2_1_ = this.func_96508_e(p_96527_1_);
         if (lvt_2_1_ != null) {
            throw new IllegalArgumentException("A team with the name '" + p_96527_1_ + "' already exists!");
         } else {
            lvt_2_1_ = new ScorePlayerTeam(this, p_96527_1_);
            this.field_96542_e.put(p_96527_1_, lvt_2_1_);
            this.func_96523_a(lvt_2_1_);
            return lvt_2_1_;
         }
      }
   }

   public void func_96511_d(ScorePlayerTeam p_96511_1_) {
      this.field_96542_e.remove(p_96511_1_.func_96661_b());
      Iterator lvt_2_1_ = p_96511_1_.func_96670_d().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         this.field_96540_f.remove(lvt_3_1_);
      }

      this.func_96513_c(p_96511_1_);
   }

   public boolean func_151392_a(String p_151392_1_, String p_151392_2_) {
      if (p_151392_1_.length() > 40) {
         throw new IllegalArgumentException("The player name '" + p_151392_1_ + "' is too long!");
      } else if (!this.field_96542_e.containsKey(p_151392_2_)) {
         return false;
      } else {
         ScorePlayerTeam lvt_3_1_ = this.func_96508_e(p_151392_2_);
         if (this.func_96509_i(p_151392_1_) != null) {
            this.func_96524_g(p_151392_1_);
         }

         this.field_96540_f.put(p_151392_1_, lvt_3_1_);
         lvt_3_1_.func_96670_d().add(p_151392_1_);
         return true;
      }
   }

   public boolean func_96524_g(String p_96524_1_) {
      ScorePlayerTeam lvt_2_1_ = this.func_96509_i(p_96524_1_);
      if (lvt_2_1_ != null) {
         this.func_96512_b(p_96524_1_, lvt_2_1_);
         return true;
      } else {
         return false;
      }
   }

   public void func_96512_b(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
      if (this.func_96509_i(p_96512_1_) != p_96512_2_) {
         throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + p_96512_2_.func_96661_b() + "'.");
      } else {
         this.field_96540_f.remove(p_96512_1_);
         p_96512_2_.func_96670_d().remove(p_96512_1_);
      }
   }

   public Collection<String> func_96531_f() {
      return this.field_96542_e.keySet();
   }

   public Collection<ScorePlayerTeam> func_96525_g() {
      return this.field_96542_e.values();
   }

   public ScorePlayerTeam func_96509_i(String p_96509_1_) {
      return (ScorePlayerTeam)this.field_96540_f.get(p_96509_1_);
   }

   public void func_96522_a(ScoreObjective p_96522_1_) {
   }

   public void func_96532_b(ScoreObjective p_96532_1_) {
   }

   public void func_96533_c(ScoreObjective p_96533_1_) {
   }

   public void func_96536_a(Score p_96536_1_) {
   }

   public void func_96516_a(String p_96516_1_) {
   }

   public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {
   }

   public void func_96523_a(ScorePlayerTeam p_96523_1_) {
   }

   public void func_96538_b(ScorePlayerTeam p_96538_1_) {
   }

   public void func_96513_c(ScorePlayerTeam p_96513_1_) {
   }

   public static String func_96517_b(int p_96517_0_) {
      switch(p_96517_0_) {
      case 0:
         return "list";
      case 1:
         return "sidebar";
      case 2:
         return "belowName";
      default:
         if (p_96517_0_ >= 3 && p_96517_0_ <= 18) {
            EnumChatFormatting lvt_1_1_ = EnumChatFormatting.func_175744_a(p_96517_0_ - 3);
            if (lvt_1_1_ != null && lvt_1_1_ != EnumChatFormatting.RESET) {
               return "sidebar.team." + lvt_1_1_.func_96297_d();
            }
         }

         return null;
      }
   }

   public static int func_96537_j(String p_96537_0_) {
      if (p_96537_0_.equalsIgnoreCase("list")) {
         return 0;
      } else if (p_96537_0_.equalsIgnoreCase("sidebar")) {
         return 1;
      } else if (p_96537_0_.equalsIgnoreCase("belowName")) {
         return 2;
      } else {
         if (p_96537_0_.startsWith("sidebar.team.")) {
            String lvt_1_1_ = p_96537_0_.substring("sidebar.team.".length());
            EnumChatFormatting lvt_2_1_ = EnumChatFormatting.func_96300_b(lvt_1_1_);
            if (lvt_2_1_ != null && lvt_2_1_.func_175746_b() >= 0) {
               return lvt_2_1_.func_175746_b() + 3;
            }
         }

         return -1;
      }
   }

   public static String[] func_178821_h() {
      if (field_178823_g == null) {
         field_178823_g = new String[19];

         for(int lvt_0_1_ = 0; lvt_0_1_ < 19; ++lvt_0_1_) {
            field_178823_g[lvt_0_1_] = func_96517_b(lvt_0_1_);
         }
      }

      return field_178823_g;
   }

   public void func_181140_a(Entity p_181140_1_) {
      if (p_181140_1_ != null && !(p_181140_1_ instanceof EntityPlayer) && !p_181140_1_.func_70089_S()) {
         String lvt_2_1_ = p_181140_1_.func_110124_au().toString();
         this.func_178822_d(lvt_2_1_, (ScoreObjective)null);
         this.func_96524_g(lvt_2_1_);
      }
   }
}
