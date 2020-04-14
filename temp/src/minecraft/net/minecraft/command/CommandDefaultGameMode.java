package net.minecraft.command;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode {
   public String func_71517_b() {
      return "defaultgamemode";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.defaultgamemode.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length <= 0) {
         throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType lvt_3_1_ = this.func_71539_b(p_71515_1_, p_71515_2_[0]);
         this.func_71541_a(lvt_3_1_);
         func_152373_a(p_71515_1_, this, "commands.defaultgamemode.success", new Object[]{new ChatComponentTranslation("gameMode." + lvt_3_1_.func_77149_b(), new Object[0])});
      }
   }

   protected void func_71541_a(WorldSettings.GameType p_71541_1_) {
      MinecraftServer lvt_2_1_ = MinecraftServer.func_71276_C();
      lvt_2_1_.func_71235_a(p_71541_1_);
      EntityPlayerMP lvt_4_1_;
      if (lvt_2_1_.func_104056_am()) {
         for(Iterator lvt_3_1_ = MinecraftServer.func_71276_C().func_71203_ab().func_181057_v().iterator(); lvt_3_1_.hasNext(); lvt_4_1_.field_70143_R = 0.0F) {
            lvt_4_1_ = (EntityPlayerMP)lvt_3_1_.next();
            lvt_4_1_.func_71033_a(p_71541_1_);
         }
      }

   }
}
