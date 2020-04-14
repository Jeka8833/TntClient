package net.minecraft.command;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase {
   public String func_71517_b() {
      return "gamerule";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.gamerule.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      GameRules lvt_3_1_ = this.func_82366_d();
      String lvt_4_1_ = p_71515_2_.length > 0 ? p_71515_2_[0] : "";
      String lvt_5_1_ = p_71515_2_.length > 1 ? func_180529_a(p_71515_2_, 1) : "";
      switch(p_71515_2_.length) {
      case 0:
         p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(lvt_3_1_.func_82763_b())));
         break;
      case 1:
         if (!lvt_3_1_.func_82765_e(lvt_4_1_)) {
            throw new CommandException("commands.gamerule.norule", new Object[]{lvt_4_1_});
         }

         String lvt_6_1_ = lvt_3_1_.func_82767_a(lvt_4_1_);
         p_71515_1_.func_145747_a((new ChatComponentText(lvt_4_1_)).func_150258_a(" = ").func_150258_a(lvt_6_1_));
         p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, lvt_3_1_.func_180263_c(lvt_4_1_));
         break;
      default:
         if (lvt_3_1_.func_180264_a(lvt_4_1_, GameRules.ValueType.BOOLEAN_VALUE) && !"true".equals(lvt_5_1_) && !"false".equals(lvt_5_1_)) {
            throw new CommandException("commands.generic.boolean.invalid", new Object[]{lvt_5_1_});
         }

         lvt_3_1_.func_82764_b(lvt_4_1_, lvt_5_1_);
         func_175773_a(lvt_3_1_, lvt_4_1_);
         func_152373_a(p_71515_1_, this, "commands.gamerule.success", new Object[0]);
      }

   }

   public static void func_175773_a(GameRules p_175773_0_, String p_175773_1_) {
      if ("reducedDebugInfo".equals(p_175773_1_)) {
         byte lvt_2_1_ = p_175773_0_.func_82766_b(p_175773_1_) ? 22 : 23;
         Iterator lvt_3_1_ = MinecraftServer.func_71276_C().func_71203_ab().func_181057_v().iterator();

         while(lvt_3_1_.hasNext()) {
            EntityPlayerMP lvt_4_1_ = (EntityPlayerMP)lvt_3_1_.next();
            lvt_4_1_.field_71135_a.func_147359_a(new S19PacketEntityStatus(lvt_4_1_, (byte)lvt_2_1_));
         }
      }

   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_82366_d().func_82763_b());
      } else {
         if (p_180525_2_.length == 2) {
            GameRules lvt_4_1_ = this.func_82366_d();
            if (lvt_4_1_.func_180264_a(p_180525_2_[0], GameRules.ValueType.BOOLEAN_VALUE)) {
               return func_71530_a(p_180525_2_, new String[]{"true", "false"});
            }
         }

         return null;
      }
   }

   private GameRules func_82366_d() {
      return MinecraftServer.func_71276_C().func_71218_a(0).func_82736_K();
   }
}
