package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.border.WorldBorder;

public class CommandWorldBorder extends CommandBase {
   public String func_71517_b() {
      return "worldborder";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.worldborder.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
      } else {
         WorldBorder lvt_3_1_ = this.func_175772_d();
         double lvt_4_5_;
         double lvt_6_4_;
         long lvt_8_2_;
         if (p_71515_2_[0].equals("set")) {
            if (p_71515_2_.length != 2 && p_71515_2_.length != 3) {
               throw new WrongUsageException("commands.worldborder.set.usage", new Object[0]);
            }

            lvt_4_5_ = lvt_3_1_.func_177751_j();
            lvt_6_4_ = func_175756_a(p_71515_2_[1], 1.0D, 6.0E7D);
            lvt_8_2_ = p_71515_2_.length > 2 ? func_175760_a(p_71515_2_[2], 0L, 9223372036854775L) * 1000L : 0L;
            if (lvt_8_2_ > 0L) {
               lvt_3_1_.func_177738_a(lvt_4_5_, lvt_6_4_, lvt_8_2_);
               if (lvt_4_5_ > lvt_6_4_) {
                  func_152373_a(p_71515_1_, this, "commands.worldborder.setSlowly.shrink.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_), Long.toString(lvt_8_2_ / 1000L)});
               } else {
                  func_152373_a(p_71515_1_, this, "commands.worldborder.setSlowly.grow.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_), Long.toString(lvt_8_2_ / 1000L)});
               }
            } else {
               lvt_3_1_.func_177750_a(lvt_6_4_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_)});
            }
         } else if (p_71515_2_[0].equals("add")) {
            if (p_71515_2_.length != 2 && p_71515_2_.length != 3) {
               throw new WrongUsageException("commands.worldborder.add.usage", new Object[0]);
            }

            lvt_4_5_ = lvt_3_1_.func_177741_h();
            lvt_6_4_ = lvt_4_5_ + func_175756_a(p_71515_2_[1], -lvt_4_5_, 6.0E7D - lvt_4_5_);
            lvt_8_2_ = lvt_3_1_.func_177732_i() + (p_71515_2_.length > 2 ? func_175760_a(p_71515_2_[2], 0L, 9223372036854775L) * 1000L : 0L);
            if (lvt_8_2_ > 0L) {
               lvt_3_1_.func_177738_a(lvt_4_5_, lvt_6_4_, lvt_8_2_);
               if (lvt_4_5_ > lvt_6_4_) {
                  func_152373_a(p_71515_1_, this, "commands.worldborder.setSlowly.shrink.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_), Long.toString(lvt_8_2_ / 1000L)});
               } else {
                  func_152373_a(p_71515_1_, this, "commands.worldborder.setSlowly.grow.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_), Long.toString(lvt_8_2_ / 1000L)});
               }
            } else {
               lvt_3_1_.func_177750_a(lvt_6_4_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", lvt_6_4_), String.format("%.1f", lvt_4_5_)});
            }
         } else if (p_71515_2_[0].equals("center")) {
            if (p_71515_2_.length != 3) {
               throw new WrongUsageException("commands.worldborder.center.usage", new Object[0]);
            }

            BlockPos lvt_4_3_ = p_71515_1_.func_180425_c();
            double lvt_5_1_ = func_175761_b((double)lvt_4_3_.func_177958_n() + 0.5D, p_71515_2_[1], true);
            double lvt_7_1_ = func_175761_b((double)lvt_4_3_.func_177952_p() + 0.5D, p_71515_2_[2], true);
            lvt_3_1_.func_177739_c(lvt_5_1_, lvt_7_1_);
            func_152373_a(p_71515_1_, this, "commands.worldborder.center.success", new Object[]{lvt_5_1_, lvt_7_1_});
         } else if (p_71515_2_[0].equals("damage")) {
            if (p_71515_2_.length < 2) {
               throw new WrongUsageException("commands.worldborder.damage.usage", new Object[0]);
            }

            if (p_71515_2_[1].equals("buffer")) {
               if (p_71515_2_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.buffer.usage", new Object[0]);
               }

               lvt_4_5_ = func_180526_a(p_71515_2_[2], 0.0D);
               lvt_6_4_ = lvt_3_1_.func_177742_m();
               lvt_3_1_.func_177724_b(lvt_4_5_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.damage.buffer.success", new Object[]{String.format("%.1f", lvt_4_5_), String.format("%.1f", lvt_6_4_)});
            } else if (p_71515_2_[1].equals("amount")) {
               if (p_71515_2_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.amount.usage", new Object[0]);
               }

               lvt_4_5_ = func_180526_a(p_71515_2_[2], 0.0D);
               lvt_6_4_ = lvt_3_1_.func_177727_n();
               lvt_3_1_.func_177744_c(lvt_4_5_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.damage.amount.success", new Object[]{String.format("%.2f", lvt_4_5_), String.format("%.2f", lvt_6_4_)});
            }
         } else if (p_71515_2_[0].equals("warning")) {
            if (p_71515_2_.length < 2) {
               throw new WrongUsageException("commands.worldborder.warning.usage", new Object[0]);
            }

            int lvt_4_6_ = func_180528_a(p_71515_2_[2], 0);
            int lvt_5_3_;
            if (p_71515_2_[1].equals("time")) {
               if (p_71515_2_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.time.usage", new Object[0]);
               }

               lvt_5_3_ = lvt_3_1_.func_177740_p();
               lvt_3_1_.func_177723_b(lvt_4_6_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.warning.time.success", new Object[]{lvt_4_6_, lvt_5_3_});
            } else if (p_71515_2_[1].equals("distance")) {
               if (p_71515_2_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.distance.usage", new Object[0]);
               }

               lvt_5_3_ = lvt_3_1_.func_177748_q();
               lvt_3_1_.func_177747_c(lvt_4_6_);
               func_152373_a(p_71515_1_, this, "commands.worldborder.warning.distance.success", new Object[]{lvt_4_6_, lvt_5_3_});
            }
         } else {
            if (!p_71515_2_[0].equals("get")) {
               throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
            }

            lvt_4_5_ = lvt_3_1_.func_177741_h();
            p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, MathHelper.func_76128_c(lvt_4_5_ + 0.5D));
            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.worldborder.get.success", new Object[]{String.format("%.0f", lvt_4_5_)}));
         }

      }
   }

   protected WorldBorder func_175772_d() {
      return MinecraftServer.func_71276_C().field_71305_c[0].func_175723_af();
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"set", "center", "damage", "warning", "add", "get"});
      } else if (p_180525_2_.length == 2 && p_180525_2_[0].equals("damage")) {
         return func_71530_a(p_180525_2_, new String[]{"buffer", "amount"});
      } else if (p_180525_2_.length >= 2 && p_180525_2_.length <= 3 && p_180525_2_[0].equals("center")) {
         return func_181043_b(p_180525_2_, 1, p_180525_3_);
      } else {
         return p_180525_2_.length == 2 && p_180525_2_[0].equals("warning") ? func_71530_a(p_180525_2_, new String[]{"time", "distance"}) : null;
      }
   }
}
