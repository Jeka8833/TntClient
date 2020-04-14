package net.minecraft.stats;

import net.minecraft.item.Item;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.IChatComponent;

public class StatCrafting extends StatBase {
   private final Item field_150960_a;

   public StatCrafting(String p_i45910_1_, String p_i45910_2_, IChatComponent p_i45910_3_, Item p_i45910_4_) {
      super(p_i45910_1_ + p_i45910_2_, p_i45910_3_);
      this.field_150960_a = p_i45910_4_;
      int lvt_5_1_ = Item.func_150891_b(p_i45910_4_);
      if (lvt_5_1_ != 0) {
         IScoreObjectiveCriteria.field_96643_a.put(p_i45910_1_ + lvt_5_1_, this.func_150952_k());
      }

   }

   public Item func_150959_a() {
      return this.field_150960_a;
   }
}
