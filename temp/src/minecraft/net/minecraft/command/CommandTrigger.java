package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTrigger extends CommandBase {
   public String func_71517_b() {
      return "trigger";
   }

   public int func_82362_a() {
      return 0;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.trigger.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 3) {
         throw new WrongUsageException("commands.trigger.usage", new Object[0]);
      } else {
         EntityPlayerMP lvt_3_3_;
         if (p_71515_1_ instanceof EntityPlayerMP) {
            lvt_3_3_ = (EntityPlayerMP)p_71515_1_;
         } else {
            Entity lvt_4_1_ = p_71515_1_.func_174793_f();
            if (!(lvt_4_1_ instanceof EntityPlayerMP)) {
               throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
            }

            lvt_3_3_ = (EntityPlayerMP)lvt_4_1_;
         }

         Scoreboard lvt_4_2_ = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
         ScoreObjective lvt_5_1_ = lvt_4_2_.func_96518_b(p_71515_2_[0]);
         if (lvt_5_1_ != null && lvt_5_1_.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
            int lvt_6_1_ = func_175755_a(p_71515_2_[2]);
            if (!lvt_4_2_.func_178819_b(lvt_3_3_.func_70005_c_(), lvt_5_1_)) {
               throw new CommandException("commands.trigger.invalidObjective", new Object[]{p_71515_2_[0]});
            } else {
               Score lvt_7_1_ = lvt_4_2_.func_96529_a(lvt_3_3_.func_70005_c_(), lvt_5_1_);
               if (lvt_7_1_.func_178816_g()) {
                  throw new CommandException("commands.trigger.disabled", new Object[]{p_71515_2_[0]});
               } else {
                  if ("set".equals(p_71515_2_[1])) {
                     lvt_7_1_.func_96647_c(lvt_6_1_);
                  } else {
                     if (!"add".equals(p_71515_2_[1])) {
                        throw new CommandException("commands.trigger.invalidMode", new Object[]{p_71515_2_[1]});
                     }

                     lvt_7_1_.func_96649_a(lvt_6_1_);
                  }

                  lvt_7_1_.func_178815_a(true);
                  if (lvt_3_3_.field_71134_c.func_73083_d()) {
                     func_152373_a(p_71515_1_, this, "commands.trigger.success", new Object[]{p_71515_2_[0], p_71515_2_[1], p_71515_2_[2]});
                  }

               }
            }
         } else {
            throw new CommandException("commands.trigger.invalidObjective", new Object[]{p_71515_2_[0]});
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         Scoreboard lvt_4_1_ = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
         List<String> lvt_5_1_ = Lists.newArrayList();
         Iterator lvt_6_1_ = lvt_4_1_.func_96514_c().iterator();

         while(lvt_6_1_.hasNext()) {
            ScoreObjective lvt_7_1_ = (ScoreObjective)lvt_6_1_.next();
            if (lvt_7_1_.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
               lvt_5_1_.add(lvt_7_1_.func_96679_b());
            }
         }

         return func_71530_a(p_180525_2_, (String[])lvt_5_1_.toArray(new String[lvt_5_1_.size()]));
      } else {
         return p_180525_2_.length == 2 ? func_71530_a(p_180525_2_, new String[]{"add", "set"}) : null;
      }
   }
}
