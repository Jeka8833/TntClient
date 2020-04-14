package net.minecraft.command;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDebug extends CommandBase {
   private static final Logger field_147208_a = LogManager.getLogger();
   private long field_147206_b;
   private int field_147207_c;

   public String func_71517_b() {
      return "debug";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.debug.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.debug.usage", new Object[0]);
      } else {
         if (p_71515_2_[0].equals("start")) {
            if (p_71515_2_.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            func_152373_a(p_71515_1_, this, "commands.debug.start", new Object[0]);
            MinecraftServer.func_71276_C().func_71223_ag();
            this.field_147206_b = MinecraftServer.func_130071_aq();
            this.field_147207_c = MinecraftServer.func_71276_C().func_71259_af();
         } else {
            if (!p_71515_2_[0].equals("stop")) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if (p_71515_2_.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if (!MinecraftServer.func_71276_C().field_71304_b.field_76327_a) {
               throw new CommandException("commands.debug.notStarted", new Object[0]);
            }

            long lvt_3_1_ = MinecraftServer.func_130071_aq();
            int lvt_5_1_ = MinecraftServer.func_71276_C().func_71259_af();
            long lvt_6_1_ = lvt_3_1_ - this.field_147206_b;
            int lvt_8_1_ = lvt_5_1_ - this.field_147207_c;
            this.func_147205_a(lvt_6_1_, lvt_8_1_);
            MinecraftServer.func_71276_C().field_71304_b.field_76327_a = false;
            func_152373_a(p_71515_1_, this, "commands.debug.stop", new Object[]{(float)lvt_6_1_ / 1000.0F, lvt_8_1_});
         }

      }
   }

   private void func_147205_a(long p_147205_1_, int p_147205_3_) {
      File lvt_4_1_ = new File(MinecraftServer.func_71276_C().func_71209_f("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
      lvt_4_1_.getParentFile().mkdirs();

      try {
         FileWriter lvt_5_1_ = new FileWriter(lvt_4_1_);
         lvt_5_1_.write(this.func_147204_b(p_147205_1_, p_147205_3_));
         lvt_5_1_.close();
      } catch (Throwable var6) {
         field_147208_a.error("Could not save profiler results to " + lvt_4_1_, var6);
      }

   }

   private String func_147204_b(long p_147204_1_, int p_147204_3_) {
      StringBuilder lvt_4_1_ = new StringBuilder();
      lvt_4_1_.append("---- Minecraft Profiler Results ----\n");
      lvt_4_1_.append("// ");
      lvt_4_1_.append(func_147203_d());
      lvt_4_1_.append("\n\n");
      lvt_4_1_.append("Time span: ").append(p_147204_1_).append(" ms\n");
      lvt_4_1_.append("Tick span: ").append(p_147204_3_).append(" ticks\n");
      lvt_4_1_.append("// This is approximately ").append(String.format("%.2f", (float)p_147204_3_ / ((float)p_147204_1_ / 1000.0F))).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
      lvt_4_1_.append("--- BEGIN PROFILE DUMP ---\n\n");
      this.func_147202_a(0, "root", lvt_4_1_);
      lvt_4_1_.append("--- END PROFILE DUMP ---\n\n");
      return lvt_4_1_.toString();
   }

   private void func_147202_a(int p_147202_1_, String p_147202_2_, StringBuilder p_147202_3_) {
      List<Profiler.Result> lvt_4_1_ = MinecraftServer.func_71276_C().field_71304_b.func_76321_b(p_147202_2_);
      if (lvt_4_1_ != null && lvt_4_1_.size() >= 3) {
         for(int lvt_5_1_ = 1; lvt_5_1_ < lvt_4_1_.size(); ++lvt_5_1_) {
            Profiler.Result lvt_6_1_ = (Profiler.Result)lvt_4_1_.get(lvt_5_1_);
            p_147202_3_.append(String.format("[%02d] ", p_147202_1_));

            for(int lvt_7_1_ = 0; lvt_7_1_ < p_147202_1_; ++lvt_7_1_) {
               p_147202_3_.append(" ");
            }

            p_147202_3_.append(lvt_6_1_.field_76331_c).append(" - ").append(String.format("%.2f", lvt_6_1_.field_76332_a)).append("%/").append(String.format("%.2f", lvt_6_1_.field_76330_b)).append("%\n");
            if (!lvt_6_1_.field_76331_c.equals("unspecified")) {
               try {
                  this.func_147202_a(p_147202_1_ + 1, p_147202_2_ + "." + lvt_6_1_.field_76331_c, p_147202_3_);
               } catch (Exception var8) {
                  p_147202_3_.append("[[ EXCEPTION ").append(var8).append(" ]]");
               }
            }
         }

      }
   }

   private static String func_147203_d() {
      String[] lvt_0_1_ = new String[]{"Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server."};

      try {
         return lvt_0_1_[(int)(System.nanoTime() % (long)lvt_0_1_.length)];
      } catch (Throwable var2) {
         return "Witty comment unavailable :(";
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1 ? func_71530_a(p_180525_2_, new String[]{"start", "stop"}) : null;
   }
}
