package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CommandPlaySound extends CommandBase {
   public String func_71517_b() {
      return "playsound";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.playsound.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException(this.func_71518_a(p_71515_1_), new Object[0]);
      } else {
         int lvt_3_1_ = 0;
         int lvt_3_1_ = lvt_3_1_ + 1;
         String lvt_4_1_ = p_71515_2_[lvt_3_1_];
         EntityPlayerMP lvt_5_1_ = func_82359_c(p_71515_1_, p_71515_2_[lvt_3_1_++]);
         Vec3 lvt_6_1_ = p_71515_1_.func_174791_d();
         double lvt_7_1_ = lvt_6_1_.field_72450_a;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_7_1_ = func_175761_b(lvt_7_1_, p_71515_2_[lvt_3_1_++], true);
         }

         double lvt_9_1_ = lvt_6_1_.field_72448_b;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_9_1_ = func_175769_b(lvt_9_1_, p_71515_2_[lvt_3_1_++], 0, 0, false);
         }

         double lvt_11_1_ = lvt_6_1_.field_72449_c;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_11_1_ = func_175761_b(lvt_11_1_, p_71515_2_[lvt_3_1_++], true);
         }

         double lvt_13_1_ = 1.0D;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_13_1_ = func_175756_a(p_71515_2_[lvt_3_1_++], 0.0D, 3.4028234663852886E38D);
         }

         double lvt_15_1_ = 1.0D;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_15_1_ = func_175756_a(p_71515_2_[lvt_3_1_++], 0.0D, 2.0D);
         }

         double lvt_17_1_ = 0.0D;
         if (p_71515_2_.length > lvt_3_1_) {
            lvt_17_1_ = func_175756_a(p_71515_2_[lvt_3_1_], 0.0D, 1.0D);
         }

         double lvt_19_1_ = lvt_13_1_ > 1.0D ? lvt_13_1_ * 16.0D : 16.0D;
         double lvt_21_1_ = lvt_5_1_.func_70011_f(lvt_7_1_, lvt_9_1_, lvt_11_1_);
         if (lvt_21_1_ > lvt_19_1_) {
            if (lvt_17_1_ <= 0.0D) {
               throw new CommandException("commands.playsound.playerTooFar", new Object[]{lvt_5_1_.func_70005_c_()});
            }

            double lvt_23_1_ = lvt_7_1_ - lvt_5_1_.field_70165_t;
            double lvt_25_1_ = lvt_9_1_ - lvt_5_1_.field_70163_u;
            double lvt_27_1_ = lvt_11_1_ - lvt_5_1_.field_70161_v;
            double lvt_29_1_ = Math.sqrt(lvt_23_1_ * lvt_23_1_ + lvt_25_1_ * lvt_25_1_ + lvt_27_1_ * lvt_27_1_);
            if (lvt_29_1_ > 0.0D) {
               lvt_7_1_ = lvt_5_1_.field_70165_t + lvt_23_1_ / lvt_29_1_ * 2.0D;
               lvt_9_1_ = lvt_5_1_.field_70163_u + lvt_25_1_ / lvt_29_1_ * 2.0D;
               lvt_11_1_ = lvt_5_1_.field_70161_v + lvt_27_1_ / lvt_29_1_ * 2.0D;
            }

            lvt_13_1_ = lvt_17_1_;
         }

         lvt_5_1_.field_71135_a.func_147359_a(new S29PacketSoundEffect(lvt_4_1_, lvt_7_1_, lvt_9_1_, lvt_11_1_, (float)lvt_13_1_, (float)lvt_15_1_));
         func_152373_a(p_71515_1_, this, "commands.playsound.success", new Object[]{lvt_4_1_, lvt_5_1_.func_70005_c_()});
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 2) {
         return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
      } else {
         return p_180525_2_.length > 2 && p_180525_2_.length <= 5 ? func_175771_a(p_180525_2_, 2, p_180525_3_) : null;
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
