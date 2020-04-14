package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandXP extends CommandBase {
   public String func_71517_b() {
      return "xp";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.xp.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length <= 0) {
         throw new WrongUsageException("commands.xp.usage", new Object[0]);
      } else {
         String lvt_3_1_ = p_71515_2_[0];
         boolean lvt_4_1_ = lvt_3_1_.endsWith("l") || lvt_3_1_.endsWith("L");
         if (lvt_4_1_ && lvt_3_1_.length() > 1) {
            lvt_3_1_ = lvt_3_1_.substring(0, lvt_3_1_.length() - 1);
         }

         int lvt_5_1_ = func_175755_a(lvt_3_1_);
         boolean lvt_6_1_ = lvt_5_1_ < 0;
         if (lvt_6_1_) {
            lvt_5_1_ *= -1;
         }

         EntityPlayer lvt_7_1_ = p_71515_2_.length > 1 ? func_82359_c(p_71515_1_, p_71515_2_[1]) : func_71521_c(p_71515_1_);
         if (lvt_4_1_) {
            p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_7_1_.field_71068_ca);
            if (lvt_6_1_) {
               lvt_7_1_.func_82242_a(-lvt_5_1_);
               func_152373_a(p_71515_1_, this, "commands.xp.success.negative.levels", new Object[]{lvt_5_1_, lvt_7_1_.func_70005_c_()});
            } else {
               lvt_7_1_.func_82242_a(lvt_5_1_);
               func_152373_a(p_71515_1_, this, "commands.xp.success.levels", new Object[]{lvt_5_1_, lvt_7_1_.func_70005_c_()});
            }
         } else {
            p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_7_1_.field_71067_cb);
            if (lvt_6_1_) {
               throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            lvt_7_1_.func_71023_q(lvt_5_1_);
            func_152373_a(p_71515_1_, this, "commands.xp.success", new Object[]{lvt_5_1_, lvt_7_1_.func_70005_c_()});
         }

      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 2 ? func_71530_a(p_180525_2_, this.func_71542_c()) : null;
   }

   protected String[] func_71542_c() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
