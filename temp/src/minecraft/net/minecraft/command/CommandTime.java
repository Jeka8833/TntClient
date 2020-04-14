package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {
   public String func_71517_b() {
      return "time";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.time.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length > 1) {
         int lvt_3_6_;
         if (p_71515_2_[0].equals("set")) {
            if (p_71515_2_[1].equals("day")) {
               lvt_3_6_ = 1000;
            } else if (p_71515_2_[1].equals("night")) {
               lvt_3_6_ = 13000;
            } else {
               lvt_3_6_ = func_180528_a(p_71515_2_[1], 0);
            }

            this.func_71552_a(p_71515_1_, lvt_3_6_);
            func_152373_a(p_71515_1_, this, "commands.time.set", new Object[]{lvt_3_6_});
            return;
         }

         if (p_71515_2_[0].equals("add")) {
            lvt_3_6_ = func_180528_a(p_71515_2_[1], 0);
            this.func_71553_b(p_71515_1_, lvt_3_6_);
            func_152373_a(p_71515_1_, this, "commands.time.added", new Object[]{lvt_3_6_});
            return;
         }

         if (p_71515_2_[0].equals("query")) {
            if (p_71515_2_[1].equals("daytime")) {
               lvt_3_6_ = (int)(p_71515_1_.func_130014_f_().func_72820_D() % 2147483647L);
               p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_3_6_);
               func_152373_a(p_71515_1_, this, "commands.time.query", new Object[]{lvt_3_6_});
               return;
            }

            if (p_71515_2_[1].equals("gametime")) {
               lvt_3_6_ = (int)(p_71515_1_.func_130014_f_().func_82737_E() % 2147483647L);
               p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_3_6_);
               func_152373_a(p_71515_1_, this, "commands.time.query", new Object[]{lvt_3_6_});
               return;
            }
         }
      }

      throw new WrongUsageException("commands.time.usage", new Object[0]);
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"set", "add", "query"});
      } else if (p_180525_2_.length == 2 && p_180525_2_[0].equals("set")) {
         return func_71530_a(p_180525_2_, new String[]{"day", "night"});
      } else {
         return p_180525_2_.length == 2 && p_180525_2_[0].equals("query") ? func_71530_a(p_180525_2_, new String[]{"daytime", "gametime"}) : null;
      }
   }

   protected void func_71552_a(ICommandSender p_71552_1_, int p_71552_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < MinecraftServer.func_71276_C().field_71305_c.length; ++lvt_3_1_) {
         MinecraftServer.func_71276_C().field_71305_c[lvt_3_1_].func_72877_b((long)p_71552_2_);
      }

   }

   protected void func_71553_b(ICommandSender p_71553_1_, int p_71553_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < MinecraftServer.func_71276_C().field_71305_c.length; ++lvt_3_1_) {
         WorldServer lvt_4_1_ = MinecraftServer.func_71276_C().field_71305_c[lvt_3_1_];
         lvt_4_1_.func_72877_b(lvt_4_1_.func_72820_D() + (long)p_71553_2_);
      }

   }
}
