package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {
   public String func_71517_b() {
      return "save-all";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.save.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      MinecraftServer lvt_3_1_ = MinecraftServer.func_71276_C();
      p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.start", new Object[0]));
      if (lvt_3_1_.func_71203_ab() != null) {
         lvt_3_1_.func_71203_ab().func_72389_g();
      }

      try {
         int lvt_4_2_;
         WorldServer lvt_5_2_;
         boolean lvt_6_2_;
         for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_1_.field_71305_c.length; ++lvt_4_2_) {
            if (lvt_3_1_.field_71305_c[lvt_4_2_] != null) {
               lvt_5_2_ = lvt_3_1_.field_71305_c[lvt_4_2_];
               lvt_6_2_ = lvt_5_2_.field_73058_d;
               lvt_5_2_.field_73058_d = false;
               lvt_5_2_.func_73044_a(true, (IProgressUpdate)null);
               lvt_5_2_.field_73058_d = lvt_6_2_;
            }
         }

         if (p_71515_2_.length > 0 && "flush".equals(p_71515_2_[0])) {
            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.flushStart", new Object[0]));

            for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_1_.field_71305_c.length; ++lvt_4_2_) {
               if (lvt_3_1_.field_71305_c[lvt_4_2_] != null) {
                  lvt_5_2_ = lvt_3_1_.field_71305_c[lvt_4_2_];
                  lvt_6_2_ = lvt_5_2_.field_73058_d;
                  lvt_5_2_.field_73058_d = false;
                  lvt_5_2_.func_104140_m();
                  lvt_5_2_.field_73058_d = lvt_6_2_;
               }
            }

            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (MinecraftException var7) {
         func_152373_a(p_71515_1_, this, "commands.save.failed", new Object[]{var7.getMessage()});
         return;
      }

      func_152373_a(p_71515_1_, this, "commands.save.success", new Object[0]);
   }
}
