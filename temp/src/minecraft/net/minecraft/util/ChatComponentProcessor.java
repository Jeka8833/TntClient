package net.minecraft.util;

import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.Entity;

public class ChatComponentProcessor {
   public static IChatComponent func_179985_a(ICommandSender p_179985_0_, IChatComponent p_179985_1_, Entity p_179985_2_) throws CommandException {
      IChatComponent lvt_3_1_ = null;
      if (p_179985_1_ instanceof ChatComponentScore) {
         ChatComponentScore lvt_4_1_ = (ChatComponentScore)p_179985_1_;
         String lvt_5_1_ = lvt_4_1_.func_179995_g();
         if (PlayerSelector.func_82378_b(lvt_5_1_)) {
            List<Entity> lvt_6_1_ = PlayerSelector.func_179656_b(p_179985_0_, lvt_5_1_, Entity.class);
            if (lvt_6_1_.size() != 1) {
               throw new EntityNotFoundException();
            }

            lvt_5_1_ = ((Entity)lvt_6_1_.get(0)).func_70005_c_();
         }

         lvt_3_1_ = p_179985_2_ != null && lvt_5_1_.equals("*") ? new ChatComponentScore(p_179985_2_.func_70005_c_(), lvt_4_1_.func_179994_h()) : new ChatComponentScore(lvt_5_1_, lvt_4_1_.func_179994_h());
         ((ChatComponentScore)lvt_3_1_).func_179997_b(lvt_4_1_.func_150261_e());
      } else if (p_179985_1_ instanceof ChatComponentSelector) {
         String lvt_4_2_ = ((ChatComponentSelector)p_179985_1_).func_179992_g();
         lvt_3_1_ = PlayerSelector.func_150869_b(p_179985_0_, lvt_4_2_);
         if (lvt_3_1_ == null) {
            lvt_3_1_ = new ChatComponentText("");
         }
      } else if (p_179985_1_ instanceof ChatComponentText) {
         lvt_3_1_ = new ChatComponentText(((ChatComponentText)p_179985_1_).func_150265_g());
      } else {
         if (!(p_179985_1_ instanceof ChatComponentTranslation)) {
            return p_179985_1_;
         }

         Object[] lvt_4_3_ = ((ChatComponentTranslation)p_179985_1_).func_150271_j();

         for(int lvt_5_2_ = 0; lvt_5_2_ < lvt_4_3_.length; ++lvt_5_2_) {
            Object lvt_6_2_ = lvt_4_3_[lvt_5_2_];
            if (lvt_6_2_ instanceof IChatComponent) {
               lvt_4_3_[lvt_5_2_] = func_179985_a(p_179985_0_, (IChatComponent)lvt_6_2_, p_179985_2_);
            }
         }

         lvt_3_1_ = new ChatComponentTranslation(((ChatComponentTranslation)p_179985_1_).func_150268_i(), lvt_4_3_);
      }

      ChatStyle lvt_4_4_ = p_179985_1_.func_150256_b();
      if (lvt_4_4_ != null) {
         ((IChatComponent)lvt_3_1_).func_150255_a(lvt_4_4_.func_150232_l());
      }

      Iterator lvt_5_3_ = p_179985_1_.func_150253_a().iterator();

      while(lvt_5_3_.hasNext()) {
         IChatComponent lvt_6_3_ = (IChatComponent)lvt_5_3_.next();
         ((IChatComponent)lvt_3_1_).func_150257_a(func_179985_a(p_179985_0_, lvt_6_3_, p_179985_2_));
      }

      return (IChatComponent)lvt_3_1_;
   }
}
