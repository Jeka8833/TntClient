package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandSaveOn extends CommandBase {
   public String func_71517_b() {
      return "save-on";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.save-on.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      MinecraftServer lvt_3_1_ = MinecraftServer.func_71276_C();
      boolean lvt_4_1_ = false;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_3_1_.field_71305_c.length; ++lvt_5_1_) {
         if (lvt_3_1_.field_71305_c[lvt_5_1_] != null) {
            WorldServer lvt_6_1_ = lvt_3_1_.field_71305_c[lvt_5_1_];
            if (lvt_6_1_.field_73058_d) {
               lvt_6_1_.field_73058_d = false;
               lvt_4_1_ = true;
            }
         }
      }

      if (lvt_4_1_) {
         func_152373_a(p_71515_1_, this, "commands.save.enabled", new Object[0]);
      } else {
         throw new CommandException("commands.save-on.alreadyOn", new Object[0]);
      }
   }
}
