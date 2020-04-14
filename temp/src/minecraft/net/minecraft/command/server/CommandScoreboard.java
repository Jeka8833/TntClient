package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandScoreboard extends CommandBase {
   public String func_71517_b() {
      return "scoreboard";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.scoreboard.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (!this.func_175780_b(p_71515_1_, p_71515_2_)) {
         if (p_71515_2_.length < 1) {
            throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
         } else {
            if (p_71515_2_[0].equalsIgnoreCase("objectives")) {
               if (p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
               }

               if (p_71515_2_[1].equalsIgnoreCase("list")) {
                  this.func_147196_d(p_71515_1_);
               } else if (p_71515_2_[1].equalsIgnoreCase("add")) {
                  if (p_71515_2_.length < 4) {
                     throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
                  }

                  this.func_147193_c(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if (p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
                  }

                  this.func_147191_h(p_71515_1_, p_71515_2_[2]);
               } else {
                  if (!p_71515_2_[1].equalsIgnoreCase("setdisplay")) {
                     throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                  }

                  if (p_71515_2_.length != 3 && p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                  }

                  this.func_147198_k(p_71515_1_, p_71515_2_, 2);
               }
            } else if (p_71515_2_[0].equalsIgnoreCase("players")) {
               if (p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
               }

               if (p_71515_2_[1].equalsIgnoreCase("list")) {
                  if (p_71515_2_.length > 3) {
                     throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
                  }

                  this.func_147195_l(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("add")) {
                  if (p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if (p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("set")) {
                  if (p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("reset")) {
                  if (p_71515_2_.length != 3 && p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
                  }

                  this.func_147187_n(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("enable")) {
                  if (p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.players.enable.usage", new Object[0]);
                  }

                  this.func_175779_n(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("test")) {
                  if (p_71515_2_.length != 5 && p_71515_2_.length != 6) {
                     throw new WrongUsageException("commands.scoreboard.players.test.usage", new Object[0]);
                  }

                  this.func_175781_o(p_71515_1_, p_71515_2_, 2);
               } else {
                  if (!p_71515_2_[1].equalsIgnoreCase("operation")) {
                     throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                  }

                  if (p_71515_2_.length != 7) {
                     throw new WrongUsageException("commands.scoreboard.players.operation.usage", new Object[0]);
                  }

                  this.func_175778_p(p_71515_1_, p_71515_2_, 2);
               }
            } else {
               if (!p_71515_2_[0].equalsIgnoreCase("teams")) {
                  throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
               }

               if (p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
               }

               if (p_71515_2_[1].equalsIgnoreCase("list")) {
                  if (p_71515_2_.length > 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
                  }

                  this.func_147186_g(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("add")) {
                  if (p_71515_2_.length < 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
                  }

                  this.func_147185_d(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if (p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
                  }

                  this.func_147194_f(p_71515_1_, p_71515_2_, 2);
               } else if (p_71515_2_[1].equalsIgnoreCase("empty")) {
                  if (p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
                  }

                  this.func_147188_j(p_71515_1_, p_71515_2_, 2);
               } else if (!p_71515_2_[1].equalsIgnoreCase("join")) {
                  if (p_71515_2_[1].equalsIgnoreCase("leave")) {
                     if (p_71515_2_.length < 3 && !(p_71515_1_ instanceof EntityPlayer)) {
                        throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
                     }

                     this.func_147199_i(p_71515_1_, p_71515_2_, 2);
                  } else {
                     if (!p_71515_2_[1].equalsIgnoreCase("option")) {
                        throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                     }

                     if (p_71515_2_.length != 4 && p_71515_2_.length != 5) {
                        throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                     }

                     this.func_147200_e(p_71515_1_, p_71515_2_, 2);
                  }
               } else {
                  if (p_71515_2_.length < 4 && (p_71515_2_.length != 3 || !(p_71515_1_ instanceof EntityPlayer))) {
                     throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
                  }

                  this.func_147190_h(p_71515_1_, p_71515_2_, 2);
               }
            }

         }
      }
   }

   private boolean func_175780_b(ICommandSender p_175780_1_, String[] p_175780_2_) throws CommandException {
      int lvt_3_1_ = -1;

      for(int lvt_4_1_ = 0; lvt_4_1_ < p_175780_2_.length; ++lvt_4_1_) {
         if (this.func_82358_a(p_175780_2_, lvt_4_1_) && "*".equals(p_175780_2_[lvt_4_1_])) {
            if (lvt_3_1_ >= 0) {
               throw new CommandException("commands.scoreboard.noMultiWildcard", new Object[0]);
            }

            lvt_3_1_ = lvt_4_1_;
         }
      }

      if (lvt_3_1_ < 0) {
         return false;
      } else {
         List<String> lvt_4_2_ = Lists.newArrayList((Iterable)this.func_147192_d().func_96526_d());
         String lvt_5_1_ = p_175780_2_[lvt_3_1_];
         List<String> lvt_6_1_ = Lists.newArrayList();
         Iterator lvt_7_1_ = lvt_4_2_.iterator();

         while(lvt_7_1_.hasNext()) {
            String lvt_8_1_ = (String)lvt_7_1_.next();
            p_175780_2_[lvt_3_1_] = lvt_8_1_;

            try {
               this.func_71515_b(p_175780_1_, p_175780_2_);
               lvt_6_1_.add(lvt_8_1_);
            } catch (CommandException var11) {
               ChatComponentTranslation lvt_10_1_ = new ChatComponentTranslation(var11.getMessage(), var11.func_74844_a());
               lvt_10_1_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
               p_175780_1_.func_145747_a(lvt_10_1_);
            }
         }

         p_175780_2_[lvt_3_1_] = lvt_5_1_;
         p_175780_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_6_1_.size());
         if (lvt_6_1_.size() == 0) {
            throw new WrongUsageException("commands.scoreboard.allMatchesFailed", new Object[0]);
         } else {
            return true;
         }
      }
   }

   protected Scoreboard func_147192_d() {
      return MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
   }

   protected ScoreObjective func_147189_a(String p_147189_1_, boolean p_147189_2_) throws CommandException {
      Scoreboard lvt_3_1_ = this.func_147192_d();
      ScoreObjective lvt_4_1_ = lvt_3_1_.func_96518_b(p_147189_1_);
      if (lvt_4_1_ == null) {
         throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[]{p_147189_1_});
      } else if (p_147189_2_ && lvt_4_1_.func_96680_c().func_96637_b()) {
         throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[]{p_147189_1_});
      } else {
         return lvt_4_1_;
      }
   }

   protected ScorePlayerTeam func_147183_a(String p_147183_1_) throws CommandException {
      Scoreboard lvt_2_1_ = this.func_147192_d();
      ScorePlayerTeam lvt_3_1_ = lvt_2_1_.func_96508_e(p_147183_1_);
      if (lvt_3_1_ == null) {
         throw new CommandException("commands.scoreboard.teamNotFound", new Object[]{p_147183_1_});
      } else {
         return lvt_3_1_;
      }
   }

   protected void func_147193_c(ICommandSender p_147193_1_, String[] p_147193_2_, int p_147193_3_) throws CommandException {
      String lvt_4_1_ = p_147193_2_[p_147193_3_++];
      String lvt_5_1_ = p_147193_2_[p_147193_3_++];
      Scoreboard lvt_6_1_ = this.func_147192_d();
      IScoreObjectiveCriteria lvt_7_1_ = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(lvt_5_1_);
      if (lvt_7_1_ == null) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[]{lvt_5_1_});
      } else if (lvt_6_1_.func_96518_b(lvt_4_1_) != null) {
         throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[]{lvt_4_1_});
      } else if (lvt_4_1_.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[]{lvt_4_1_, 16});
      } else if (lvt_4_1_.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
      } else {
         if (p_147193_2_.length > p_147193_3_) {
            String lvt_8_1_ = func_147178_a(p_147193_1_, p_147193_2_, p_147193_3_).func_150260_c();
            if (lvt_8_1_.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[]{lvt_8_1_, 32});
            }

            if (lvt_8_1_.length() > 0) {
               lvt_6_1_.func_96535_a(lvt_4_1_, lvt_7_1_).func_96681_a(lvt_8_1_);
            } else {
               lvt_6_1_.func_96535_a(lvt_4_1_, lvt_7_1_);
            }
         } else {
            lvt_6_1_.func_96535_a(lvt_4_1_, lvt_7_1_);
         }

         func_152373_a(p_147193_1_, this, "commands.scoreboard.objectives.add.success", new Object[]{lvt_4_1_});
      }
   }

   protected void func_147185_d(ICommandSender p_147185_1_, String[] p_147185_2_, int p_147185_3_) throws CommandException {
      String lvt_4_1_ = p_147185_2_[p_147185_3_++];
      Scoreboard lvt_5_1_ = this.func_147192_d();
      if (lvt_5_1_.func_96508_e(lvt_4_1_) != null) {
         throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[]{lvt_4_1_});
      } else if (lvt_4_1_.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[]{lvt_4_1_, 16});
      } else if (lvt_4_1_.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
      } else {
         if (p_147185_2_.length > p_147185_3_) {
            String lvt_6_1_ = func_147178_a(p_147185_1_, p_147185_2_, p_147185_3_).func_150260_c();
            if (lvt_6_1_.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[]{lvt_6_1_, 32});
            }

            if (lvt_6_1_.length() > 0) {
               lvt_5_1_.func_96527_f(lvt_4_1_).func_96664_a(lvt_6_1_);
            } else {
               lvt_5_1_.func_96527_f(lvt_4_1_);
            }
         } else {
            lvt_5_1_.func_96527_f(lvt_4_1_);
         }

         func_152373_a(p_147185_1_, this, "commands.scoreboard.teams.add.success", new Object[]{lvt_4_1_});
      }
   }

   protected void func_147200_e(ICommandSender p_147200_1_, String[] p_147200_2_, int p_147200_3_) throws CommandException {
      ScorePlayerTeam lvt_4_1_ = this.func_147183_a(p_147200_2_[p_147200_3_++]);
      if (lvt_4_1_ != null) {
         String lvt_5_1_ = p_147200_2_[p_147200_3_++].toLowerCase();
         if (!lvt_5_1_.equalsIgnoreCase("color") && !lvt_5_1_.equalsIgnoreCase("friendlyfire") && !lvt_5_1_.equalsIgnoreCase("seeFriendlyInvisibles") && !lvt_5_1_.equalsIgnoreCase("nametagVisibility") && !lvt_5_1_.equalsIgnoreCase("deathMessageVisibility")) {
            throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
         } else if (p_147200_2_.length == 4) {
            if (lvt_5_1_.equalsIgnoreCase("color")) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
            } else if (!lvt_5_1_.equalsIgnoreCase("friendlyfire") && !lvt_5_1_.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if (!lvt_5_1_.equalsIgnoreCase("nametagVisibility") && !lvt_5_1_.equalsIgnoreCase("deathMessageVisibility")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
               } else {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_71527_a(Team.EnumVisible.func_178825_a())});
               }
            } else {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_96333_a(Arrays.asList("true", "false"))});
            }
         } else {
            String lvt_6_1_ = p_147200_2_[p_147200_3_];
            if (lvt_5_1_.equalsIgnoreCase("color")) {
               EnumChatFormatting lvt_7_1_ = EnumChatFormatting.func_96300_b(lvt_6_1_);
               if (lvt_7_1_ == null || lvt_7_1_.func_96301_b()) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
               }

               lvt_4_1_.func_178774_a(lvt_7_1_);
               lvt_4_1_.func_96666_b(lvt_7_1_.toString());
               lvt_4_1_.func_96662_c(EnumChatFormatting.RESET.toString());
            } else if (lvt_5_1_.equalsIgnoreCase("friendlyfire")) {
               if (!lvt_6_1_.equalsIgnoreCase("true") && !lvt_6_1_.equalsIgnoreCase("false")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_96333_a(Arrays.asList("true", "false"))});
               }

               lvt_4_1_.func_96660_a(lvt_6_1_.equalsIgnoreCase("true"));
            } else if (lvt_5_1_.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if (!lvt_6_1_.equalsIgnoreCase("true") && !lvt_6_1_.equalsIgnoreCase("false")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_96333_a(Arrays.asList("true", "false"))});
               }

               lvt_4_1_.func_98300_b(lvt_6_1_.equalsIgnoreCase("true"));
            } else {
               Team.EnumVisible lvt_7_3_;
               if (lvt_5_1_.equalsIgnoreCase("nametagVisibility")) {
                  lvt_7_3_ = Team.EnumVisible.func_178824_a(lvt_6_1_);
                  if (lvt_7_3_ == null) {
                     throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_71527_a(Team.EnumVisible.func_178825_a())});
                  }

                  lvt_4_1_.func_178772_a(lvt_7_3_);
               } else if (lvt_5_1_.equalsIgnoreCase("deathMessageVisibility")) {
                  lvt_7_3_ = Team.EnumVisible.func_178824_a(lvt_6_1_);
                  if (lvt_7_3_ == null) {
                     throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{lvt_5_1_, func_71527_a(Team.EnumVisible.func_178825_a())});
                  }

                  lvt_4_1_.func_178773_b(lvt_7_3_);
               }
            }

            func_152373_a(p_147200_1_, this, "commands.scoreboard.teams.option.success", new Object[]{lvt_5_1_, lvt_4_1_.func_96661_b(), lvt_6_1_});
         }
      }
   }

   protected void func_147194_f(ICommandSender p_147194_1_, String[] p_147194_2_, int p_147194_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      ScorePlayerTeam lvt_5_1_ = this.func_147183_a(p_147194_2_[p_147194_3_]);
      if (lvt_5_1_ != null) {
         lvt_4_1_.func_96511_d(lvt_5_1_);
         func_152373_a(p_147194_1_, this, "commands.scoreboard.teams.remove.success", new Object[]{lvt_5_1_.func_96661_b()});
      }
   }

   protected void func_147186_g(ICommandSender p_147186_1_, String[] p_147186_2_, int p_147186_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      if (p_147186_2_.length > p_147186_3_) {
         ScorePlayerTeam lvt_5_1_ = this.func_147183_a(p_147186_2_[p_147186_3_]);
         if (lvt_5_1_ == null) {
            return;
         }

         Collection<String> lvt_6_1_ = lvt_5_1_.func_96670_d();
         p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_6_1_.size());
         if (lvt_6_1_.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[]{lvt_5_1_.func_96661_b()});
         }

         ChatComponentTranslation lvt_7_1_ = new ChatComponentTranslation("commands.scoreboard.teams.list.player.count", new Object[]{lvt_6_1_.size(), lvt_5_1_.func_96661_b()});
         lvt_7_1_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147186_1_.func_145747_a(lvt_7_1_);
         p_147186_1_.func_145747_a(new ChatComponentText(func_71527_a(lvt_6_1_.toArray())));
      } else {
         Collection<ScorePlayerTeam> lvt_5_2_ = lvt_4_1_.func_96525_g();
         p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_5_2_.size());
         if (lvt_5_2_.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
         }

         ChatComponentTranslation lvt_6_2_ = new ChatComponentTranslation("commands.scoreboard.teams.list.count", new Object[]{lvt_5_2_.size()});
         lvt_6_2_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147186_1_.func_145747_a(lvt_6_2_);
         Iterator lvt_7_2_ = lvt_5_2_.iterator();

         while(lvt_7_2_.hasNext()) {
            ScorePlayerTeam lvt_8_1_ = (ScorePlayerTeam)lvt_7_2_.next();
            p_147186_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.teams.list.entry", new Object[]{lvt_8_1_.func_96661_b(), lvt_8_1_.func_96669_c(), lvt_8_1_.func_96670_d().size()}));
         }
      }

   }

   protected void func_147190_h(ICommandSender p_147190_1_, String[] p_147190_2_, int p_147190_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = p_147190_2_[p_147190_3_++];
      Set<String> lvt_6_1_ = Sets.newHashSet();
      Set<String> lvt_7_1_ = Sets.newHashSet();
      String lvt_8_2_;
      if (p_147190_1_ instanceof EntityPlayer && p_147190_3_ == p_147190_2_.length) {
         lvt_8_2_ = func_71521_c(p_147190_1_).func_70005_c_();
         if (lvt_4_1_.func_151392_a(lvt_8_2_, lvt_5_1_)) {
            lvt_6_1_.add(lvt_8_2_);
         } else {
            lvt_7_1_.add(lvt_8_2_);
         }
      } else {
         label50:
         while(true) {
            while(true) {
               if (p_147190_3_ >= p_147190_2_.length) {
                  break label50;
               }

               lvt_8_2_ = p_147190_2_[p_147190_3_++];
               if (lvt_8_2_.startsWith("@")) {
                  List<Entity> lvt_9_1_ = func_175763_c(p_147190_1_, lvt_8_2_);
                  Iterator lvt_10_1_ = lvt_9_1_.iterator();

                  while(lvt_10_1_.hasNext()) {
                     Entity lvt_11_1_ = (Entity)lvt_10_1_.next();
                     String lvt_12_1_ = func_175758_e(p_147190_1_, lvt_11_1_.func_110124_au().toString());
                     if (lvt_4_1_.func_151392_a(lvt_12_1_, lvt_5_1_)) {
                        lvt_6_1_.add(lvt_12_1_);
                     } else {
                        lvt_7_1_.add(lvt_12_1_);
                     }
                  }
               } else {
                  String lvt_9_2_ = func_175758_e(p_147190_1_, lvt_8_2_);
                  if (lvt_4_1_.func_151392_a(lvt_9_2_, lvt_5_1_)) {
                     lvt_6_1_.add(lvt_9_2_);
                  } else {
                     lvt_7_1_.add(lvt_9_2_);
                  }
               }
            }
         }
      }

      if (!lvt_6_1_.isEmpty()) {
         p_147190_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_6_1_.size());
         func_152373_a(p_147190_1_, this, "commands.scoreboard.teams.join.success", new Object[]{lvt_6_1_.size(), lvt_5_1_, func_71527_a(lvt_6_1_.toArray(new String[lvt_6_1_.size()]))});
      }

      if (!lvt_7_1_.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.join.failure", new Object[]{lvt_7_1_.size(), lvt_5_1_, func_71527_a(lvt_7_1_.toArray(new String[lvt_7_1_.size()]))});
      }
   }

   protected void func_147199_i(ICommandSender p_147199_1_, String[] p_147199_2_, int p_147199_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      Set<String> lvt_5_1_ = Sets.newHashSet();
      Set<String> lvt_6_1_ = Sets.newHashSet();
      String lvt_7_2_;
      if (p_147199_1_ instanceof EntityPlayer && p_147199_3_ == p_147199_2_.length) {
         lvt_7_2_ = func_71521_c(p_147199_1_).func_70005_c_();
         if (lvt_4_1_.func_96524_g(lvt_7_2_)) {
            lvt_5_1_.add(lvt_7_2_);
         } else {
            lvt_6_1_.add(lvt_7_2_);
         }
      } else {
         label50:
         while(true) {
            while(true) {
               if (p_147199_3_ >= p_147199_2_.length) {
                  break label50;
               }

               lvt_7_2_ = p_147199_2_[p_147199_3_++];
               if (lvt_7_2_.startsWith("@")) {
                  List<Entity> lvt_8_1_ = func_175763_c(p_147199_1_, lvt_7_2_);
                  Iterator lvt_9_1_ = lvt_8_1_.iterator();

                  while(lvt_9_1_.hasNext()) {
                     Entity lvt_10_1_ = (Entity)lvt_9_1_.next();
                     String lvt_11_1_ = func_175758_e(p_147199_1_, lvt_10_1_.func_110124_au().toString());
                     if (lvt_4_1_.func_96524_g(lvt_11_1_)) {
                        lvt_5_1_.add(lvt_11_1_);
                     } else {
                        lvt_6_1_.add(lvt_11_1_);
                     }
                  }
               } else {
                  String lvt_8_2_ = func_175758_e(p_147199_1_, lvt_7_2_);
                  if (lvt_4_1_.func_96524_g(lvt_8_2_)) {
                     lvt_5_1_.add(lvt_8_2_);
                  } else {
                     lvt_6_1_.add(lvt_8_2_);
                  }
               }
            }
         }
      }

      if (!lvt_5_1_.isEmpty()) {
         p_147199_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_5_1_.size());
         func_152373_a(p_147199_1_, this, "commands.scoreboard.teams.leave.success", new Object[]{lvt_5_1_.size(), func_71527_a(lvt_5_1_.toArray(new String[lvt_5_1_.size()]))});
      }

      if (!lvt_6_1_.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[]{lvt_6_1_.size(), func_71527_a(lvt_6_1_.toArray(new String[lvt_6_1_.size()]))});
      }
   }

   protected void func_147188_j(ICommandSender p_147188_1_, String[] p_147188_2_, int p_147188_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      ScorePlayerTeam lvt_5_1_ = this.func_147183_a(p_147188_2_[p_147188_3_]);
      if (lvt_5_1_ != null) {
         Collection<String> lvt_6_1_ = Lists.newArrayList((Iterable)lvt_5_1_.func_96670_d());
         p_147188_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_6_1_.size());
         if (lvt_6_1_.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{lvt_5_1_.func_96661_b()});
         } else {
            Iterator lvt_7_1_ = lvt_6_1_.iterator();

            while(lvt_7_1_.hasNext()) {
               String lvt_8_1_ = (String)lvt_7_1_.next();
               lvt_4_1_.func_96512_b(lvt_8_1_, lvt_5_1_);
            }

            func_152373_a(p_147188_1_, this, "commands.scoreboard.teams.empty.success", new Object[]{lvt_6_1_.size(), lvt_5_1_.func_96661_b()});
         }
      }
   }

   protected void func_147191_h(ICommandSender p_147191_1_, String p_147191_2_) throws CommandException {
      Scoreboard lvt_3_1_ = this.func_147192_d();
      ScoreObjective lvt_4_1_ = this.func_147189_a(p_147191_2_, false);
      lvt_3_1_.func_96519_k(lvt_4_1_);
      func_152373_a(p_147191_1_, this, "commands.scoreboard.objectives.remove.success", new Object[]{p_147191_2_});
   }

   protected void func_147196_d(ICommandSender p_147196_1_) throws CommandException {
      Scoreboard lvt_2_1_ = this.func_147192_d();
      Collection<ScoreObjective> lvt_3_1_ = lvt_2_1_.func_96514_c();
      if (lvt_3_1_.size() <= 0) {
         throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
      } else {
         ChatComponentTranslation lvt_4_1_ = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", new Object[]{lvt_3_1_.size()});
         lvt_4_1_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147196_1_.func_145747_a(lvt_4_1_);
         Iterator lvt_5_1_ = lvt_3_1_.iterator();

         while(lvt_5_1_.hasNext()) {
            ScoreObjective lvt_6_1_ = (ScoreObjective)lvt_5_1_.next();
            p_147196_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.objectives.list.entry", new Object[]{lvt_6_1_.func_96679_b(), lvt_6_1_.func_96678_d(), lvt_6_1_.func_96680_c().func_96636_a()}));
         }

      }
   }

   protected void func_147198_k(ICommandSender p_147198_1_, String[] p_147198_2_, int p_147198_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = p_147198_2_[p_147198_3_++];
      int lvt_6_1_ = Scoreboard.func_96537_j(lvt_5_1_);
      ScoreObjective lvt_7_1_ = null;
      if (p_147198_2_.length == 4) {
         lvt_7_1_ = this.func_147189_a(p_147198_2_[p_147198_3_], false);
      }

      if (lvt_6_1_ < 0) {
         throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{lvt_5_1_});
      } else {
         lvt_4_1_.func_96530_a(lvt_6_1_, lvt_7_1_);
         if (lvt_7_1_ != null) {
            func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{Scoreboard.func_96517_b(lvt_6_1_), lvt_7_1_.func_96679_b()});
         } else {
            func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{Scoreboard.func_96517_b(lvt_6_1_)});
         }

      }
   }

   protected void func_147195_l(ICommandSender p_147195_1_, String[] p_147195_2_, int p_147195_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      if (p_147195_2_.length > p_147195_3_) {
         String lvt_5_1_ = func_175758_e(p_147195_1_, p_147195_2_[p_147195_3_]);
         Map<ScoreObjective, Score> lvt_6_1_ = lvt_4_1_.func_96510_d(lvt_5_1_);
         p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_6_1_.size());
         if (lvt_6_1_.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[]{lvt_5_1_});
         }

         ChatComponentTranslation lvt_7_1_ = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", new Object[]{lvt_6_1_.size(), lvt_5_1_});
         lvt_7_1_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147195_1_.func_145747_a(lvt_7_1_);
         Iterator lvt_8_1_ = lvt_6_1_.values().iterator();

         while(lvt_8_1_.hasNext()) {
            Score lvt_9_1_ = (Score)lvt_8_1_.next();
            p_147195_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.players.list.player.entry", new Object[]{lvt_9_1_.func_96652_c(), lvt_9_1_.func_96645_d().func_96678_d(), lvt_9_1_.func_96645_d().func_96679_b()}));
         }
      } else {
         Collection<String> lvt_5_2_ = lvt_4_1_.func_96526_d();
         p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_5_2_.size());
         if (lvt_5_2_.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
         }

         ChatComponentTranslation lvt_6_2_ = new ChatComponentTranslation("commands.scoreboard.players.list.count", new Object[]{lvt_5_2_.size()});
         lvt_6_2_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147195_1_.func_145747_a(lvt_6_2_);
         p_147195_1_.func_145747_a(new ChatComponentText(func_71527_a(lvt_5_2_.toArray())));
      }

   }

   protected void func_147197_m(ICommandSender p_147197_1_, String[] p_147197_2_, int p_147197_3_) throws CommandException {
      String lvt_4_1_ = p_147197_2_[p_147197_3_ - 1];
      int lvt_5_1_ = p_147197_3_;
      String lvt_6_1_ = func_175758_e(p_147197_1_, p_147197_2_[p_147197_3_++]);
      if (lvt_6_1_.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{lvt_6_1_, 40});
      } else {
         ScoreObjective lvt_7_1_ = this.func_147189_a(p_147197_2_[p_147197_3_++], true);
         int lvt_8_1_ = lvt_4_1_.equalsIgnoreCase("set") ? func_175755_a(p_147197_2_[p_147197_3_++]) : func_180528_a(p_147197_2_[p_147197_3_++], 0);
         if (p_147197_2_.length > p_147197_3_) {
            Entity lvt_9_1_ = func_175768_b(p_147197_1_, p_147197_2_[lvt_5_1_]);

            try {
               NBTTagCompound lvt_10_1_ = JsonToNBT.func_180713_a(func_180529_a(p_147197_2_, p_147197_3_));
               NBTTagCompound lvt_11_1_ = new NBTTagCompound();
               lvt_9_1_.func_70109_d(lvt_11_1_);
               if (!NBTUtil.func_181123_a(lvt_10_1_, lvt_11_1_, true)) {
                  throw new CommandException("commands.scoreboard.players.set.tagMismatch", new Object[]{lvt_6_1_});
               }
            } catch (NBTException var12) {
               throw new CommandException("commands.scoreboard.players.set.tagError", new Object[]{var12.getMessage()});
            }
         }

         Scoreboard lvt_9_2_ = this.func_147192_d();
         Score lvt_10_3_ = lvt_9_2_.func_96529_a(lvt_6_1_, lvt_7_1_);
         if (lvt_4_1_.equalsIgnoreCase("set")) {
            lvt_10_3_.func_96647_c(lvt_8_1_);
         } else if (lvt_4_1_.equalsIgnoreCase("add")) {
            lvt_10_3_.func_96649_a(lvt_8_1_);
         } else {
            lvt_10_3_.func_96646_b(lvt_8_1_);
         }

         func_152373_a(p_147197_1_, this, "commands.scoreboard.players.set.success", new Object[]{lvt_7_1_.func_96679_b(), lvt_6_1_, lvt_10_3_.func_96652_c()});
      }
   }

   protected void func_147187_n(ICommandSender p_147187_1_, String[] p_147187_2_, int p_147187_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = func_175758_e(p_147187_1_, p_147187_2_[p_147187_3_++]);
      if (p_147187_2_.length > p_147187_3_) {
         ScoreObjective lvt_6_1_ = this.func_147189_a(p_147187_2_[p_147187_3_++], false);
         lvt_4_1_.func_178822_d(lvt_5_1_, lvt_6_1_);
         func_152373_a(p_147187_1_, this, "commands.scoreboard.players.resetscore.success", new Object[]{lvt_6_1_.func_96679_b(), lvt_5_1_});
      } else {
         lvt_4_1_.func_178822_d(lvt_5_1_, (ScoreObjective)null);
         func_152373_a(p_147187_1_, this, "commands.scoreboard.players.reset.success", new Object[]{lvt_5_1_});
      }

   }

   protected void func_175779_n(ICommandSender p_175779_1_, String[] p_175779_2_, int p_175779_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = func_96332_d(p_175779_1_, p_175779_2_[p_175779_3_++]);
      if (lvt_5_1_.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{lvt_5_1_, 40});
      } else {
         ScoreObjective lvt_6_1_ = this.func_147189_a(p_175779_2_[p_175779_3_], false);
         if (lvt_6_1_.func_96680_c() != IScoreObjectiveCriteria.field_178791_c) {
            throw new CommandException("commands.scoreboard.players.enable.noTrigger", new Object[]{lvt_6_1_.func_96679_b()});
         } else {
            Score lvt_7_1_ = lvt_4_1_.func_96529_a(lvt_5_1_, lvt_6_1_);
            lvt_7_1_.func_178815_a(false);
            func_152373_a(p_175779_1_, this, "commands.scoreboard.players.enable.success", new Object[]{lvt_6_1_.func_96679_b(), lvt_5_1_});
         }
      }
   }

   protected void func_175781_o(ICommandSender p_175781_1_, String[] p_175781_2_, int p_175781_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = func_175758_e(p_175781_1_, p_175781_2_[p_175781_3_++]);
      if (lvt_5_1_.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{lvt_5_1_, 40});
      } else {
         ScoreObjective lvt_6_1_ = this.func_147189_a(p_175781_2_[p_175781_3_++], false);
         if (!lvt_4_1_.func_178819_b(lvt_5_1_, lvt_6_1_)) {
            throw new CommandException("commands.scoreboard.players.test.notFound", new Object[]{lvt_6_1_.func_96679_b(), lvt_5_1_});
         } else {
            int lvt_7_1_ = p_175781_2_[p_175781_3_].equals("*") ? Integer.MIN_VALUE : func_175755_a(p_175781_2_[p_175781_3_]);
            ++p_175781_3_;
            int lvt_8_1_ = p_175781_3_ < p_175781_2_.length && !p_175781_2_[p_175781_3_].equals("*") ? func_180528_a(p_175781_2_[p_175781_3_], lvt_7_1_) : Integer.MAX_VALUE;
            Score lvt_9_1_ = lvt_4_1_.func_96529_a(lvt_5_1_, lvt_6_1_);
            if (lvt_9_1_.func_96652_c() >= lvt_7_1_ && lvt_9_1_.func_96652_c() <= lvt_8_1_) {
               func_152373_a(p_175781_1_, this, "commands.scoreboard.players.test.success", new Object[]{lvt_9_1_.func_96652_c(), lvt_7_1_, lvt_8_1_});
            } else {
               throw new CommandException("commands.scoreboard.players.test.failed", new Object[]{lvt_9_1_.func_96652_c(), lvt_7_1_, lvt_8_1_});
            }
         }
      }
   }

   protected void func_175778_p(ICommandSender p_175778_1_, String[] p_175778_2_, int p_175778_3_) throws CommandException {
      Scoreboard lvt_4_1_ = this.func_147192_d();
      String lvt_5_1_ = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
      ScoreObjective lvt_6_1_ = this.func_147189_a(p_175778_2_[p_175778_3_++], true);
      String lvt_7_1_ = p_175778_2_[p_175778_3_++];
      String lvt_8_1_ = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
      ScoreObjective lvt_9_1_ = this.func_147189_a(p_175778_2_[p_175778_3_], false);
      if (lvt_5_1_.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{lvt_5_1_, 40});
      } else if (lvt_8_1_.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{lvt_8_1_, 40});
      } else {
         Score lvt_10_1_ = lvt_4_1_.func_96529_a(lvt_5_1_, lvt_6_1_);
         if (!lvt_4_1_.func_178819_b(lvt_8_1_, lvt_9_1_)) {
            throw new CommandException("commands.scoreboard.players.operation.notFound", new Object[]{lvt_9_1_.func_96679_b(), lvt_8_1_});
         } else {
            Score lvt_11_1_ = lvt_4_1_.func_96529_a(lvt_8_1_, lvt_9_1_);
            if (lvt_7_1_.equals("+=")) {
               lvt_10_1_.func_96647_c(lvt_10_1_.func_96652_c() + lvt_11_1_.func_96652_c());
            } else if (lvt_7_1_.equals("-=")) {
               lvt_10_1_.func_96647_c(lvt_10_1_.func_96652_c() - lvt_11_1_.func_96652_c());
            } else if (lvt_7_1_.equals("*=")) {
               lvt_10_1_.func_96647_c(lvt_10_1_.func_96652_c() * lvt_11_1_.func_96652_c());
            } else if (lvt_7_1_.equals("/=")) {
               if (lvt_11_1_.func_96652_c() != 0) {
                  lvt_10_1_.func_96647_c(lvt_10_1_.func_96652_c() / lvt_11_1_.func_96652_c());
               }
            } else if (lvt_7_1_.equals("%=")) {
               if (lvt_11_1_.func_96652_c() != 0) {
                  lvt_10_1_.func_96647_c(lvt_10_1_.func_96652_c() % lvt_11_1_.func_96652_c());
               }
            } else if (lvt_7_1_.equals("=")) {
               lvt_10_1_.func_96647_c(lvt_11_1_.func_96652_c());
            } else if (lvt_7_1_.equals("<")) {
               lvt_10_1_.func_96647_c(Math.min(lvt_10_1_.func_96652_c(), lvt_11_1_.func_96652_c()));
            } else if (lvt_7_1_.equals(">")) {
               lvt_10_1_.func_96647_c(Math.max(lvt_10_1_.func_96652_c(), lvt_11_1_.func_96652_c()));
            } else {
               if (!lvt_7_1_.equals("><")) {
                  throw new CommandException("commands.scoreboard.players.operation.invalidOperation", new Object[]{lvt_7_1_});
               }

               int lvt_12_1_ = lvt_10_1_.func_96652_c();
               lvt_10_1_.func_96647_c(lvt_11_1_.func_96652_c());
               lvt_11_1_.func_96647_c(lvt_12_1_);
            }

            func_152373_a(p_175778_1_, this, "commands.scoreboard.players.operation.success", new Object[0]);
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"objectives", "players", "teams"});
      } else {
         if (p_180525_2_[0].equalsIgnoreCase("objectives")) {
            if (p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"list", "add", "remove", "setdisplay"});
            }

            if (p_180525_2_[1].equalsIgnoreCase("add")) {
               if (p_180525_2_.length == 4) {
                  Set<String> lvt_4_1_ = IScoreObjectiveCriteria.field_96643_a.keySet();
                  return func_175762_a(p_180525_2_, lvt_4_1_);
               }
            } else if (p_180525_2_[1].equalsIgnoreCase("remove")) {
               if (p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(false));
               }
            } else if (p_180525_2_[1].equalsIgnoreCase("setdisplay")) {
               if (p_180525_2_.length == 3) {
                  return func_71530_a(p_180525_2_, Scoreboard.func_178821_h());
               }

               if (p_180525_2_.length == 4) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(false));
               }
            }
         } else if (p_180525_2_[0].equalsIgnoreCase("players")) {
            if (p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"set", "add", "remove", "reset", "list", "enable", "test", "operation"});
            }

            if (!p_180525_2_[1].equalsIgnoreCase("set") && !p_180525_2_[1].equalsIgnoreCase("add") && !p_180525_2_[1].equalsIgnoreCase("remove") && !p_180525_2_[1].equalsIgnoreCase("reset")) {
               if (p_180525_2_[1].equalsIgnoreCase("enable")) {
                  if (p_180525_2_.length == 3) {
                     return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
                  }

                  if (p_180525_2_.length == 4) {
                     return func_175762_a(p_180525_2_, this.func_175782_e());
                  }
               } else if (!p_180525_2_[1].equalsIgnoreCase("list") && !p_180525_2_[1].equalsIgnoreCase("test")) {
                  if (p_180525_2_[1].equalsIgnoreCase("operation")) {
                     if (p_180525_2_.length == 3) {
                        return func_175762_a(p_180525_2_, this.func_147192_d().func_96526_d());
                     }

                     if (p_180525_2_.length == 4) {
                        return func_175762_a(p_180525_2_, this.func_147184_a(true));
                     }

                     if (p_180525_2_.length == 5) {
                        return func_71530_a(p_180525_2_, new String[]{"+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><"});
                     }

                     if (p_180525_2_.length == 6) {
                        return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
                     }

                     if (p_180525_2_.length == 7) {
                        return func_175762_a(p_180525_2_, this.func_147184_a(false));
                     }
                  }
               } else {
                  if (p_180525_2_.length == 3) {
                     return func_175762_a(p_180525_2_, this.func_147192_d().func_96526_d());
                  }

                  if (p_180525_2_.length == 4 && p_180525_2_[1].equalsIgnoreCase("test")) {
                     return func_175762_a(p_180525_2_, this.func_147184_a(false));
                  }
               }
            } else {
               if (p_180525_2_.length == 3) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }

               if (p_180525_2_.length == 4) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(true));
               }
            }
         } else if (p_180525_2_[0].equalsIgnoreCase("teams")) {
            if (p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
            }

            if (p_180525_2_[1].equalsIgnoreCase("join")) {
               if (p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
               }

               if (p_180525_2_.length >= 4) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }
            } else {
               if (p_180525_2_[1].equalsIgnoreCase("leave")) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }

               if (!p_180525_2_[1].equalsIgnoreCase("empty") && !p_180525_2_[1].equalsIgnoreCase("list") && !p_180525_2_[1].equalsIgnoreCase("remove")) {
                  if (p_180525_2_[1].equalsIgnoreCase("option")) {
                     if (p_180525_2_.length == 3) {
                        return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
                     }

                     if (p_180525_2_.length == 4) {
                        return func_71530_a(p_180525_2_, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility"});
                     }

                     if (p_180525_2_.length == 5) {
                        if (p_180525_2_[3].equalsIgnoreCase("color")) {
                           return func_175762_a(p_180525_2_, EnumChatFormatting.func_96296_a(true, false));
                        }

                        if (!p_180525_2_[3].equalsIgnoreCase("nametagVisibility") && !p_180525_2_[3].equalsIgnoreCase("deathMessageVisibility")) {
                           if (!p_180525_2_[3].equalsIgnoreCase("friendlyfire") && !p_180525_2_[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                              return null;
                           }

                           return func_71530_a(p_180525_2_, new String[]{"true", "false"});
                        }

                        return func_71530_a(p_180525_2_, Team.EnumVisible.func_178825_a());
                     }
                  }
               } else if (p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
               }
            }
         }

         return null;
      }
   }

   protected List<String> func_147184_a(boolean p_147184_1_) {
      Collection<ScoreObjective> lvt_2_1_ = this.func_147192_d().func_96514_c();
      List<String> lvt_3_1_ = Lists.newArrayList();
      Iterator lvt_4_1_ = lvt_2_1_.iterator();

      while(true) {
         ScoreObjective lvt_5_1_;
         do {
            if (!lvt_4_1_.hasNext()) {
               return lvt_3_1_;
            }

            lvt_5_1_ = (ScoreObjective)lvt_4_1_.next();
         } while(p_147184_1_ && lvt_5_1_.func_96680_c().func_96637_b());

         lvt_3_1_.add(lvt_5_1_.func_96679_b());
      }
   }

   protected List<String> func_175782_e() {
      Collection<ScoreObjective> lvt_1_1_ = this.func_147192_d().func_96514_c();
      List<String> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = lvt_1_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         ScoreObjective lvt_4_1_ = (ScoreObjective)lvt_3_1_.next();
         if (lvt_4_1_.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
            lvt_2_1_.add(lvt_4_1_.func_96679_b());
         }
      }

      return lvt_2_1_;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      if (!p_82358_1_[0].equalsIgnoreCase("players")) {
         if (p_82358_1_[0].equalsIgnoreCase("teams")) {
            return p_82358_2_ == 2;
         } else {
            return false;
         }
      } else if (p_82358_1_.length > 1 && p_82358_1_[1].equalsIgnoreCase("operation")) {
         return p_82358_2_ == 2 || p_82358_2_ == 5;
      } else {
         return p_82358_2_ == 2;
      }
   }
}
