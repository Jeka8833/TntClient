package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public interface IScoreObjectiveCriteria {
   Map<String, IScoreObjectiveCriteria> field_96643_a = Maps.newHashMap();
   IScoreObjectiveCriteria field_96641_b = new ScoreDummyCriteria("dummy");
   IScoreObjectiveCriteria field_178791_c = new ScoreDummyCriteria("trigger");
   IScoreObjectiveCriteria field_96642_c = new ScoreDummyCriteria("deathCount");
   IScoreObjectiveCriteria field_96639_d = new ScoreDummyCriteria("playerKillCount");
   IScoreObjectiveCriteria field_96640_e = new ScoreDummyCriteria("totalKillCount");
   IScoreObjectiveCriteria field_96638_f = new ScoreHealthCriteria("health");
   IScoreObjectiveCriteria[] field_178792_h = new IScoreObjectiveCriteria[]{new GoalColor("teamkill.", EnumChatFormatting.BLACK), new GoalColor("teamkill.", EnumChatFormatting.DARK_BLUE), new GoalColor("teamkill.", EnumChatFormatting.DARK_GREEN), new GoalColor("teamkill.", EnumChatFormatting.DARK_AQUA), new GoalColor("teamkill.", EnumChatFormatting.DARK_RED), new GoalColor("teamkill.", EnumChatFormatting.DARK_PURPLE), new GoalColor("teamkill.", EnumChatFormatting.GOLD), new GoalColor("teamkill.", EnumChatFormatting.GRAY), new GoalColor("teamkill.", EnumChatFormatting.DARK_GRAY), new GoalColor("teamkill.", EnumChatFormatting.BLUE), new GoalColor("teamkill.", EnumChatFormatting.GREEN), new GoalColor("teamkill.", EnumChatFormatting.AQUA), new GoalColor("teamkill.", EnumChatFormatting.RED), new GoalColor("teamkill.", EnumChatFormatting.LIGHT_PURPLE), new GoalColor("teamkill.", EnumChatFormatting.YELLOW), new GoalColor("teamkill.", EnumChatFormatting.WHITE)};
   IScoreObjectiveCriteria[] field_178793_i = new IScoreObjectiveCriteria[]{new GoalColor("killedByTeam.", EnumChatFormatting.BLACK), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_BLUE), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_GREEN), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_AQUA), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_RED), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_PURPLE), new GoalColor("killedByTeam.", EnumChatFormatting.GOLD), new GoalColor("killedByTeam.", EnumChatFormatting.GRAY), new GoalColor("killedByTeam.", EnumChatFormatting.DARK_GRAY), new GoalColor("killedByTeam.", EnumChatFormatting.BLUE), new GoalColor("killedByTeam.", EnumChatFormatting.GREEN), new GoalColor("killedByTeam.", EnumChatFormatting.AQUA), new GoalColor("killedByTeam.", EnumChatFormatting.RED), new GoalColor("killedByTeam.", EnumChatFormatting.LIGHT_PURPLE), new GoalColor("killedByTeam.", EnumChatFormatting.YELLOW), new GoalColor("killedByTeam.", EnumChatFormatting.WHITE)};

   String func_96636_a();

   int func_96635_a(List<EntityPlayer> var1);

   boolean func_96637_b();

   IScoreObjectiveCriteria.EnumRenderType func_178790_c();

   public static enum EnumRenderType {
      INTEGER("integer"),
      HEARTS("hearts");

      private static final Map<String, IScoreObjectiveCriteria.EnumRenderType> field_178801_c = Maps.newHashMap();
      private final String field_178798_d;

      private EnumRenderType(String p_i45548_3_) {
         this.field_178798_d = p_i45548_3_;
      }

      public String func_178796_a() {
         return this.field_178798_d;
      }

      public static IScoreObjectiveCriteria.EnumRenderType func_178795_a(String p_178795_0_) {
         IScoreObjectiveCriteria.EnumRenderType lvt_1_1_ = (IScoreObjectiveCriteria.EnumRenderType)field_178801_c.get(p_178795_0_);
         return lvt_1_1_ == null ? INTEGER : lvt_1_1_;
      }

      static {
         IScoreObjectiveCriteria.EnumRenderType[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            IScoreObjectiveCriteria.EnumRenderType lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_178801_c.put(lvt_3_1_.func_178796_a(), lvt_3_1_);
         }

      }
   }
}
