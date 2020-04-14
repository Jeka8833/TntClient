package net.minecraft.command;

import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandParticle extends CommandBase {
   public String func_71517_b() {
      return "particle";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.particle.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 8) {
         throw new WrongUsageException("commands.particle.usage", new Object[0]);
      } else {
         boolean lvt_3_1_ = false;
         EnumParticleTypes lvt_4_1_ = null;
         EnumParticleTypes[] lvt_5_1_ = EnumParticleTypes.values();
         int lvt_6_1_ = lvt_5_1_.length;

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
            EnumParticleTypes lvt_8_1_ = lvt_5_1_[lvt_7_1_];
            if (lvt_8_1_.func_179343_f()) {
               if (p_71515_2_[0].startsWith(lvt_8_1_.func_179346_b())) {
                  lvt_3_1_ = true;
                  lvt_4_1_ = lvt_8_1_;
                  break;
               }
            } else if (p_71515_2_[0].equals(lvt_8_1_.func_179346_b())) {
               lvt_3_1_ = true;
               lvt_4_1_ = lvt_8_1_;
               break;
            }
         }

         if (!lvt_3_1_) {
            throw new CommandException("commands.particle.notFound", new Object[]{p_71515_2_[0]});
         } else {
            String lvt_5_2_ = p_71515_2_[0];
            Vec3 lvt_6_2_ = p_71515_1_.func_174791_d();
            double lvt_7_2_ = (double)((float)func_175761_b(lvt_6_2_.field_72450_a, p_71515_2_[1], true));
            double lvt_9_1_ = (double)((float)func_175761_b(lvt_6_2_.field_72448_b, p_71515_2_[2], true));
            double lvt_11_1_ = (double)((float)func_175761_b(lvt_6_2_.field_72449_c, p_71515_2_[3], true));
            double lvt_13_1_ = (double)((float)func_175765_c(p_71515_2_[4]));
            double lvt_15_1_ = (double)((float)func_175765_c(p_71515_2_[5]));
            double lvt_17_1_ = (double)((float)func_175765_c(p_71515_2_[6]));
            double lvt_19_1_ = (double)((float)func_175765_c(p_71515_2_[7]));
            int lvt_21_1_ = 0;
            if (p_71515_2_.length > 8) {
               lvt_21_1_ = func_180528_a(p_71515_2_[8], 0);
            }

            boolean lvt_22_1_ = false;
            if (p_71515_2_.length > 9 && "force".equals(p_71515_2_[9])) {
               lvt_22_1_ = true;
            }

            World lvt_23_1_ = p_71515_1_.func_130014_f_();
            if (lvt_23_1_ instanceof WorldServer) {
               WorldServer lvt_24_1_ = (WorldServer)lvt_23_1_;
               int[] lvt_25_1_ = new int[lvt_4_1_.func_179345_d()];
               if (lvt_4_1_.func_179343_f()) {
                  String[] lvt_26_1_ = p_71515_2_[0].split("_", 3);

                  for(int lvt_27_1_ = 1; lvt_27_1_ < lvt_26_1_.length; ++lvt_27_1_) {
                     try {
                        lvt_25_1_[lvt_27_1_ - 1] = Integer.parseInt(lvt_26_1_[lvt_27_1_]);
                     } catch (NumberFormatException var29) {
                        throw new CommandException("commands.particle.notFound", new Object[]{p_71515_2_[0]});
                     }
                  }
               }

               lvt_24_1_.func_180505_a(lvt_4_1_, lvt_22_1_, lvt_7_2_, lvt_9_1_, lvt_11_1_, lvt_21_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_19_1_, lvt_25_1_);
               func_152373_a(p_71515_1_, this, "commands.particle.success", new Object[]{lvt_5_2_, Math.max(lvt_21_1_, 1)});
            }

         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, EnumParticleTypes.func_179349_a());
      } else if (p_180525_2_.length > 1 && p_180525_2_.length <= 4) {
         return func_175771_a(p_180525_2_, 1, p_180525_3_);
      } else {
         return p_180525_2_.length == 10 ? func_71530_a(p_180525_2_, new String[]{"normal", "force"}) : null;
      }
   }
}
