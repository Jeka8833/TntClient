package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandOp extends CommandBase {
   public String func_71517_b() {
      return "op";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.op.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length == 1 && p_71515_2_[0].length() > 0) {
         MinecraftServer lvt_3_1_ = MinecraftServer.func_71276_C();
         GameProfile lvt_4_1_ = lvt_3_1_.func_152358_ax().func_152655_a(p_71515_2_[0]);
         if (lvt_4_1_ == null) {
            throw new CommandException("commands.op.failed", new Object[]{p_71515_2_[0]});
         } else {
            lvt_3_1_.func_71203_ab().func_152605_a(lvt_4_1_);
            func_152373_a(p_71515_1_, this, "commands.op.success", new Object[]{p_71515_2_[0]});
         }
      } else {
         throw new WrongUsageException("commands.op.usage", new Object[0]);
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         String lvt_4_1_ = p_180525_2_[p_180525_2_.length - 1];
         List<String> lvt_5_1_ = Lists.newArrayList();
         GameProfile[] lvt_6_1_ = MinecraftServer.func_71276_C().func_152357_F();
         int lvt_7_1_ = lvt_6_1_.length;

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
            GameProfile lvt_9_1_ = lvt_6_1_[lvt_8_1_];
            if (!MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(lvt_9_1_) && func_71523_a(lvt_4_1_, lvt_9_1_.getName())) {
               lvt_5_1_.add(lvt_9_1_.getName());
            }
         }

         return lvt_5_1_;
      } else {
         return null;
      }
   }
}
