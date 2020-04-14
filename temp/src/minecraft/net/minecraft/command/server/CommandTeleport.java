package net.minecraft.command.server;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CommandTeleport extends CommandBase {
   public String func_71517_b() {
      return "tp";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.tp.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.tp.usage", new Object[0]);
      } else {
         int lvt_3_1_ = 0;
         Object lvt_4_2_;
         if (p_71515_2_.length != 2 && p_71515_2_.length != 4 && p_71515_2_.length != 6) {
            lvt_4_2_ = func_71521_c(p_71515_1_);
         } else {
            lvt_4_2_ = func_175768_b(p_71515_1_, p_71515_2_[0]);
            lvt_3_1_ = 1;
         }

         if (p_71515_2_.length != 1 && p_71515_2_.length != 2) {
            if (p_71515_2_.length < lvt_3_1_ + 3) {
               throw new WrongUsageException("commands.tp.usage", new Object[0]);
            } else if (((Entity)lvt_4_2_).field_70170_p != null) {
               int lvt_5_2_ = lvt_3_1_ + 1;
               CommandBase.CoordinateArg lvt_6_1_ = func_175770_a(((Entity)lvt_4_2_).field_70165_t, p_71515_2_[lvt_3_1_], true);
               CommandBase.CoordinateArg lvt_7_1_ = func_175767_a(((Entity)lvt_4_2_).field_70163_u, p_71515_2_[lvt_5_2_++], 0, 0, false);
               CommandBase.CoordinateArg lvt_8_1_ = func_175770_a(((Entity)lvt_4_2_).field_70161_v, p_71515_2_[lvt_5_2_++], true);
               CommandBase.CoordinateArg lvt_9_1_ = func_175770_a((double)((Entity)lvt_4_2_).field_70177_z, p_71515_2_.length > lvt_5_2_ ? p_71515_2_[lvt_5_2_++] : "~", false);
               CommandBase.CoordinateArg lvt_10_1_ = func_175770_a((double)((Entity)lvt_4_2_).field_70125_A, p_71515_2_.length > lvt_5_2_ ? p_71515_2_[lvt_5_2_] : "~", false);
               float lvt_12_1_;
               if (lvt_4_2_ instanceof EntityPlayerMP) {
                  Set<S08PacketPlayerPosLook.EnumFlags> lvt_11_1_ = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);
                  if (lvt_6_1_.func_179630_c()) {
                     lvt_11_1_.add(S08PacketPlayerPosLook.EnumFlags.X);
                  }

                  if (lvt_7_1_.func_179630_c()) {
                     lvt_11_1_.add(S08PacketPlayerPosLook.EnumFlags.Y);
                  }

                  if (lvt_8_1_.func_179630_c()) {
                     lvt_11_1_.add(S08PacketPlayerPosLook.EnumFlags.Z);
                  }

                  if (lvt_10_1_.func_179630_c()) {
                     lvt_11_1_.add(S08PacketPlayerPosLook.EnumFlags.X_ROT);
                  }

                  if (lvt_9_1_.func_179630_c()) {
                     lvt_11_1_.add(S08PacketPlayerPosLook.EnumFlags.Y_ROT);
                  }

                  lvt_12_1_ = (float)lvt_9_1_.func_179629_b();
                  if (!lvt_9_1_.func_179630_c()) {
                     lvt_12_1_ = MathHelper.func_76142_g(lvt_12_1_);
                  }

                  float lvt_13_1_ = (float)lvt_10_1_.func_179629_b();
                  if (!lvt_10_1_.func_179630_c()) {
                     lvt_13_1_ = MathHelper.func_76142_g(lvt_13_1_);
                  }

                  if (lvt_13_1_ > 90.0F || lvt_13_1_ < -90.0F) {
                     lvt_13_1_ = MathHelper.func_76142_g(180.0F - lvt_13_1_);
                     lvt_12_1_ = MathHelper.func_76142_g(lvt_12_1_ + 180.0F);
                  }

                  ((Entity)lvt_4_2_).func_70078_a((Entity)null);
                  ((EntityPlayerMP)lvt_4_2_).field_71135_a.func_175089_a(lvt_6_1_.func_179629_b(), lvt_7_1_.func_179629_b(), lvt_8_1_.func_179629_b(), lvt_12_1_, lvt_13_1_, lvt_11_1_);
                  ((Entity)lvt_4_2_).func_70034_d(lvt_12_1_);
               } else {
                  float lvt_11_2_ = (float)MathHelper.func_76138_g(lvt_9_1_.func_179628_a());
                  lvt_12_1_ = (float)MathHelper.func_76138_g(lvt_10_1_.func_179628_a());
                  if (lvt_12_1_ > 90.0F || lvt_12_1_ < -90.0F) {
                     lvt_12_1_ = MathHelper.func_76142_g(180.0F - lvt_12_1_);
                     lvt_11_2_ = MathHelper.func_76142_g(lvt_11_2_ + 180.0F);
                  }

                  ((Entity)lvt_4_2_).func_70012_b(lvt_6_1_.func_179628_a(), lvt_7_1_.func_179628_a(), lvt_8_1_.func_179628_a(), lvt_11_2_, lvt_12_1_);
                  ((Entity)lvt_4_2_).func_70034_d(lvt_11_2_);
               }

               func_152373_a(p_71515_1_, this, "commands.tp.success.coordinates", new Object[]{((Entity)lvt_4_2_).func_70005_c_(), lvt_6_1_.func_179628_a(), lvt_7_1_.func_179628_a(), lvt_8_1_.func_179628_a()});
            }
         } else {
            Entity lvt_5_1_ = func_175768_b(p_71515_1_, p_71515_2_[p_71515_2_.length - 1]);
            if (lvt_5_1_.field_70170_p != ((Entity)lvt_4_2_).field_70170_p) {
               throw new CommandException("commands.tp.notSameDimension", new Object[0]);
            } else {
               ((Entity)lvt_4_2_).func_70078_a((Entity)null);
               if (lvt_4_2_ instanceof EntityPlayerMP) {
                  ((EntityPlayerMP)lvt_4_2_).field_71135_a.func_147364_a(lvt_5_1_.field_70165_t, lvt_5_1_.field_70163_u, lvt_5_1_.field_70161_v, lvt_5_1_.field_70177_z, lvt_5_1_.field_70125_A);
               } else {
                  ((Entity)lvt_4_2_).func_70012_b(lvt_5_1_.field_70165_t, lvt_5_1_.field_70163_u, lvt_5_1_.field_70161_v, lvt_5_1_.field_70177_z, lvt_5_1_.field_70125_A);
               }

               func_152373_a(p_71515_1_, this, "commands.tp.success", new Object[]{((Entity)lvt_4_2_).func_70005_c_(), lvt_5_1_.func_70005_c_()});
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length != 1 && p_180525_2_.length != 2 ? null : func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
