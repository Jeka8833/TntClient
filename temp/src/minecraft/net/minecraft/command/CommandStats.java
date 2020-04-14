package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandStats extends CommandBase {
   public String func_71517_b() {
      return "stats";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.stats.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.stats.usage", new Object[0]);
      } else {
         boolean lvt_3_3_;
         if (p_71515_2_[0].equals("entity")) {
            lvt_3_3_ = false;
         } else {
            if (!p_71515_2_[0].equals("block")) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            lvt_3_3_ = true;
         }

         byte lvt_4_2_;
         if (lvt_3_3_) {
            if (p_71515_2_.length < 5) {
               throw new WrongUsageException("commands.stats.block.usage", new Object[0]);
            }

            lvt_4_2_ = 4;
         } else {
            if (p_71515_2_.length < 3) {
               throw new WrongUsageException("commands.stats.entity.usage", new Object[0]);
            }

            lvt_4_2_ = 2;
         }

         int lvt_4_2_ = lvt_4_2_ + 1;
         String lvt_5_1_ = p_71515_2_[lvt_4_2_];
         if ("set".equals(lvt_5_1_)) {
            if (p_71515_2_.length < lvt_4_2_ + 3) {
               if (lvt_4_2_ == 5) {
                  throw new WrongUsageException("commands.stats.block.set.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.set.usage", new Object[0]);
            }
         } else {
            if (!"clear".equals(lvt_5_1_)) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            if (p_71515_2_.length < lvt_4_2_ + 1) {
               if (lvt_4_2_ == 5) {
                  throw new WrongUsageException("commands.stats.block.clear.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.clear.usage", new Object[0]);
            }
         }

         CommandResultStats.Type lvt_6_1_ = CommandResultStats.Type.func_179635_a(p_71515_2_[lvt_4_2_++]);
         if (lvt_6_1_ == null) {
            throw new CommandException("commands.stats.failed", new Object[0]);
         } else {
            World lvt_7_1_ = p_71515_1_.func_130014_f_();
            CommandResultStats lvt_8_4_;
            BlockPos lvt_9_4_;
            TileEntity lvt_10_3_;
            if (lvt_3_3_) {
               lvt_9_4_ = func_175757_a(p_71515_1_, p_71515_2_, 1, false);
               lvt_10_3_ = lvt_7_1_.func_175625_s(lvt_9_4_);
               if (lvt_10_3_ == null) {
                  throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{lvt_9_4_.func_177958_n(), lvt_9_4_.func_177956_o(), lvt_9_4_.func_177952_p()});
               }

               if (lvt_10_3_ instanceof TileEntityCommandBlock) {
                  lvt_8_4_ = ((TileEntityCommandBlock)lvt_10_3_).func_175124_c();
               } else {
                  if (!(lvt_10_3_ instanceof TileEntitySign)) {
                     throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{lvt_9_4_.func_177958_n(), lvt_9_4_.func_177956_o(), lvt_9_4_.func_177952_p()});
                  }

                  lvt_8_4_ = ((TileEntitySign)lvt_10_3_).func_174880_d();
               }
            } else {
               Entity lvt_9_2_ = func_175768_b(p_71515_1_, p_71515_2_[1]);
               lvt_8_4_ = lvt_9_2_.func_174807_aT();
            }

            if ("set".equals(lvt_5_1_)) {
               String lvt_9_3_ = p_71515_2_[lvt_4_2_++];
               String lvt_10_2_ = p_71515_2_[lvt_4_2_];
               if (lvt_9_3_.length() == 0 || lvt_10_2_.length() == 0) {
                  throw new CommandException("commands.stats.failed", new Object[0]);
               }

               CommandResultStats.func_179667_a(lvt_8_4_, lvt_6_1_, lvt_9_3_, lvt_10_2_);
               func_152373_a(p_71515_1_, this, "commands.stats.success", new Object[]{lvt_6_1_.func_179637_b(), lvt_10_2_, lvt_9_3_});
            } else if ("clear".equals(lvt_5_1_)) {
               CommandResultStats.func_179667_a(lvt_8_4_, lvt_6_1_, (String)null, (String)null);
               func_152373_a(p_71515_1_, this, "commands.stats.cleared", new Object[]{lvt_6_1_.func_179637_b()});
            }

            if (lvt_3_3_) {
               lvt_9_4_ = func_175757_a(p_71515_1_, p_71515_2_, 1, false);
               lvt_10_3_ = lvt_7_1_.func_175625_s(lvt_9_4_);
               lvt_10_3_.func_70296_d();
            }

         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"entity", "block"});
      } else if (p_180525_2_.length == 2 && p_180525_2_[0].equals("entity")) {
         return func_71530_a(p_180525_2_, this.func_175776_d());
      } else if (p_180525_2_.length >= 2 && p_180525_2_.length <= 4 && p_180525_2_[0].equals("block")) {
         return func_175771_a(p_180525_2_, 1, p_180525_3_);
      } else if (p_180525_2_.length == 3 && p_180525_2_[0].equals("entity") || p_180525_2_.length == 5 && p_180525_2_[0].equals("block")) {
         return func_71530_a(p_180525_2_, new String[]{"set", "clear"});
      } else if ((p_180525_2_.length != 4 || !p_180525_2_[0].equals("entity")) && (p_180525_2_.length != 6 || !p_180525_2_[0].equals("block"))) {
         return (p_180525_2_.length != 6 || !p_180525_2_[0].equals("entity")) && (p_180525_2_.length != 8 || !p_180525_2_[0].equals("block")) ? null : func_175762_a(p_180525_2_, this.func_175777_e());
      } else {
         return func_71530_a(p_180525_2_, CommandResultStats.Type.func_179634_c());
      }
   }

   protected String[] func_175776_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   protected List<String> func_175777_e() {
      Collection<ScoreObjective> lvt_1_1_ = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U().func_96514_c();
      List<String> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = lvt_1_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         ScoreObjective lvt_4_1_ = (ScoreObjective)lvt_3_1_.next();
         if (!lvt_4_1_.func_96680_c().func_96637_b()) {
            lvt_2_1_.add(lvt_4_1_.func_96679_b());
         }
      }

      return lvt_2_1_;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_1_.length > 0 && p_82358_1_[0].equals("entity") && p_82358_2_ == 1;
   }
}
