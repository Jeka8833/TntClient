package net.minecraft.scoreboard;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
   public ScoreHealthCriteria(String p_i2312_1_) {
      super(p_i2312_1_);
   }

   public int func_96635_a(List<EntityPlayer> p_96635_1_) {
      float lvt_2_1_ = 0.0F;

      EntityPlayer lvt_4_1_;
      for(Iterator lvt_3_1_ = p_96635_1_.iterator(); lvt_3_1_.hasNext(); lvt_2_1_ += lvt_4_1_.func_110143_aJ() + lvt_4_1_.func_110139_bj()) {
         lvt_4_1_ = (EntityPlayer)lvt_3_1_.next();
      }

      if (p_96635_1_.size() > 0) {
         lvt_2_1_ /= (float)p_96635_1_.size();
      }

      return MathHelper.func_76123_f(lvt_2_1_);
   }

   public boolean func_96637_b() {
      return true;
   }

   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
      return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
   }
}
