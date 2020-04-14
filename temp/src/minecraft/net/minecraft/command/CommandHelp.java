package net.minecraft.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class CommandHelp extends CommandBase {
   public String func_71517_b() {
      return "help";
   }

   public int func_82362_a() {
      return 0;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.help.usage";
   }

   public List<String> func_71514_a() {
      return Arrays.asList("?");
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      List<ICommand> lvt_3_1_ = this.func_71534_d(p_71515_1_);
      int lvt_4_1_ = true;
      int lvt_5_1_ = (lvt_3_1_.size() - 1) / 7;
      boolean var6 = false;

      int lvt_6_1_;
      try {
         lvt_6_1_ = p_71515_2_.length == 0 ? 0 : func_175764_a(p_71515_2_[0], 1, lvt_5_1_ + 1) - 1;
      } catch (NumberInvalidException var12) {
         Map<String, ICommand> lvt_8_1_ = this.func_71535_c();
         ICommand lvt_9_1_ = (ICommand)lvt_8_1_.get(p_71515_2_[0]);
         if (lvt_9_1_ != null) {
            throw new WrongUsageException(lvt_9_1_.func_71518_a(p_71515_1_), new Object[0]);
         }

         if (MathHelper.func_82715_a(p_71515_2_[0], -1) != -1) {
            throw var12;
         }

         throw new CommandNotFoundException();
      }

      int lvt_7_2_ = Math.min((lvt_6_1_ + 1) * 7, lvt_3_1_.size());
      ChatComponentTranslation lvt_8_2_ = new ChatComponentTranslation("commands.help.header", new Object[]{lvt_6_1_ + 1, lvt_5_1_ + 1});
      lvt_8_2_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
      p_71515_1_.func_145747_a(lvt_8_2_);

      for(int lvt_9_2_ = lvt_6_1_ * 7; lvt_9_2_ < lvt_7_2_; ++lvt_9_2_) {
         ICommand lvt_10_1_ = (ICommand)lvt_3_1_.get(lvt_9_2_);
         ChatComponentTranslation lvt_11_1_ = new ChatComponentTranslation(lvt_10_1_.func_71518_a(p_71515_1_), new Object[0]);
         lvt_11_1_.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + lvt_10_1_.func_71517_b() + " "));
         p_71515_1_.func_145747_a(lvt_11_1_);
      }

      if (lvt_6_1_ == 0 && p_71515_1_ instanceof EntityPlayer) {
         ChatComponentTranslation lvt_9_3_ = new ChatComponentTranslation("commands.help.footer", new Object[0]);
         lvt_9_3_.func_150256_b().func_150238_a(EnumChatFormatting.GREEN);
         p_71515_1_.func_145747_a(lvt_9_3_);
      }

   }

   protected List<ICommand> func_71534_d(ICommandSender p_71534_1_) {
      List<ICommand> lvt_2_1_ = MinecraftServer.func_71276_C().func_71187_D().func_71557_a(p_71534_1_);
      Collections.sort(lvt_2_1_);
      return lvt_2_1_;
   }

   protected Map<String, ICommand> func_71535_c() {
      return MinecraftServer.func_71276_C().func_71187_D().func_71555_a();
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         Set<String> lvt_4_1_ = this.func_71535_c().keySet();
         return func_71530_a(p_180525_2_, (String[])lvt_4_1_.toArray(new String[lvt_4_1_.size()]));
      } else {
         return null;
      }
   }
}
