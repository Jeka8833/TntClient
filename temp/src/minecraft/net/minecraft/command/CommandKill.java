package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandKill extends CommandBase {
   public String func_71517_b() {
      return "kill";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.kill.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length == 0) {
         EntityPlayer lvt_3_1_ = func_71521_c(p_71515_1_);
         lvt_3_1_.func_174812_G();
         func_152373_a(p_71515_1_, this, "commands.kill.successful", new Object[]{lvt_3_1_.func_145748_c_()});
      } else {
         Entity lvt_3_2_ = func_175768_b(p_71515_1_, p_71515_2_[0]);
         lvt_3_2_.func_174812_G();
         func_152373_a(p_71515_1_, this, "commands.kill.successful", new Object[]{lvt_3_2_.func_145748_c_()});
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1 ? func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()) : null;
   }
}
