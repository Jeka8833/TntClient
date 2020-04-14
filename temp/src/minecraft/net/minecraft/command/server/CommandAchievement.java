package net.minecraft.command.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;

public class CommandAchievement extends CommandBase {
   public String func_71517_b() {
      return "achievement";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.achievement.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.achievement.usage", new Object[0]);
      } else {
         final StatBase lvt_3_1_ = StatList.func_151177_a(p_71515_2_[1]);
         if (lvt_3_1_ == null && !p_71515_2_[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", new Object[]{p_71515_2_[1]});
         } else {
            final EntityPlayerMP lvt_4_1_ = p_71515_2_.length >= 3 ? func_82359_c(p_71515_1_, p_71515_2_[2]) : func_71521_c(p_71515_1_);
            boolean lvt_5_1_ = p_71515_2_[0].equalsIgnoreCase("give");
            boolean lvt_6_1_ = p_71515_2_[0].equalsIgnoreCase("take");
            if (lvt_5_1_ || lvt_6_1_) {
               if (lvt_3_1_ == null) {
                  Iterator lvt_7_2_;
                  Achievement lvt_8_2_;
                  if (lvt_5_1_) {
                     lvt_7_2_ = AchievementList.field_76007_e.iterator();

                     while(lvt_7_2_.hasNext()) {
                        lvt_8_2_ = (Achievement)lvt_7_2_.next();
                        lvt_4_1_.func_71029_a(lvt_8_2_);
                     }

                     func_152373_a(p_71515_1_, this, "commands.achievement.give.success.all", new Object[]{lvt_4_1_.func_70005_c_()});
                  } else if (lvt_6_1_) {
                     lvt_7_2_ = Lists.reverse(AchievementList.field_76007_e).iterator();

                     while(lvt_7_2_.hasNext()) {
                        lvt_8_2_ = (Achievement)lvt_7_2_.next();
                        lvt_4_1_.func_175145_a(lvt_8_2_);
                     }

                     func_152373_a(p_71515_1_, this, "commands.achievement.take.success.all", new Object[]{lvt_4_1_.func_70005_c_()});
                  }

               } else {
                  if (lvt_3_1_ instanceof Achievement) {
                     Achievement lvt_7_3_ = (Achievement)lvt_3_1_;
                     ArrayList lvt_8_3_;
                     if (lvt_5_1_) {
                        if (lvt_4_1_.func_147099_x().func_77443_a(lvt_7_3_)) {
                           throw new CommandException("commands.achievement.alreadyHave", new Object[]{lvt_4_1_.func_70005_c_(), lvt_3_1_.func_150955_j()});
                        }

                        for(lvt_8_3_ = Lists.newArrayList(); lvt_7_3_.field_75992_c != null && !lvt_4_1_.func_147099_x().func_77443_a(lvt_7_3_.field_75992_c); lvt_7_3_ = lvt_7_3_.field_75992_c) {
                           lvt_8_3_.add(lvt_7_3_.field_75992_c);
                        }

                        Iterator lvt_9_1_ = Lists.reverse(lvt_8_3_).iterator();

                        while(lvt_9_1_.hasNext()) {
                           Achievement lvt_10_1_ = (Achievement)lvt_9_1_.next();
                           lvt_4_1_.func_71029_a(lvt_10_1_);
                        }
                     } else if (lvt_6_1_) {
                        if (!lvt_4_1_.func_147099_x().func_77443_a(lvt_7_3_)) {
                           throw new CommandException("commands.achievement.dontHave", new Object[]{lvt_4_1_.func_70005_c_(), lvt_3_1_.func_150955_j()});
                        }

                        lvt_8_3_ = Lists.newArrayList((Iterator)Iterators.filter(AchievementList.field_76007_e.iterator(), new Predicate<Achievement>() {
                           public boolean apply(Achievement p_apply_1_) {
                              return lvt_4_1_.func_147099_x().func_77443_a(p_apply_1_) && p_apply_1_ != lvt_3_1_;
                           }
                        }));
                        List<Achievement> lvt_9_2_ = Lists.newArrayList((Iterable)lvt_8_3_);
                        Iterator lvt_10_3_ = lvt_8_3_.iterator();

                        label118:
                        while(true) {
                           Achievement lvt_11_1_;
                           Achievement lvt_12_1_;
                           boolean lvt_13_1_;
                           do {
                              if (!lvt_10_3_.hasNext()) {
                                 lvt_10_3_ = lvt_9_2_.iterator();

                                 while(lvt_10_3_.hasNext()) {
                                    lvt_11_1_ = (Achievement)lvt_10_3_.next();
                                    lvt_4_1_.func_175145_a(lvt_11_1_);
                                 }
                                 break label118;
                              }

                              lvt_11_1_ = (Achievement)lvt_10_3_.next();
                              lvt_12_1_ = lvt_11_1_;

                              for(lvt_13_1_ = false; lvt_12_1_ != null; lvt_12_1_ = lvt_12_1_.field_75992_c) {
                                 if (lvt_12_1_ == lvt_3_1_) {
                                    lvt_13_1_ = true;
                                 }
                              }
                           } while(lvt_13_1_);

                           for(lvt_12_1_ = lvt_11_1_; lvt_12_1_ != null; lvt_12_1_ = lvt_12_1_.field_75992_c) {
                              lvt_9_2_.remove(lvt_11_1_);
                           }
                        }
                     }
                  }

                  if (lvt_5_1_) {
                     lvt_4_1_.func_71029_a(lvt_3_1_);
                     func_152373_a(p_71515_1_, this, "commands.achievement.give.success.one", new Object[]{lvt_4_1_.func_70005_c_(), lvt_3_1_.func_150955_j()});
                  } else if (lvt_6_1_) {
                     lvt_4_1_.func_175145_a(lvt_3_1_);
                     func_152373_a(p_71515_1_, this, "commands.achievement.take.success.one", new Object[]{lvt_3_1_.func_150955_j(), lvt_4_1_.func_70005_c_()});
                  }

               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"give", "take"});
      } else if (p_180525_2_.length != 2) {
         return p_180525_2_.length == 3 ? func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()) : null;
      } else {
         List<String> lvt_4_1_ = Lists.newArrayList();
         Iterator lvt_5_1_ = StatList.field_75940_b.iterator();

         while(lvt_5_1_.hasNext()) {
            StatBase lvt_6_1_ = (StatBase)lvt_5_1_.next();
            lvt_4_1_.add(lvt_6_1_.field_75975_e);
         }

         return func_175762_a(p_180525_2_, lvt_4_1_);
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 2;
   }
}
