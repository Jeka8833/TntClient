package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandEffect extends CommandBase {
   public String func_71517_b() {
      return "effect";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.effect.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.effect.usage", new Object[0]);
      } else {
         EntityLivingBase lvt_3_1_ = (EntityLivingBase)func_175759_a(p_71515_1_, p_71515_2_[0], EntityLivingBase.class);
         if (p_71515_2_[1].equals("clear")) {
            if (lvt_3_1_.func_70651_bq().isEmpty()) {
               throw new CommandException("commands.effect.failure.notActive.all", new Object[]{lvt_3_1_.func_70005_c_()});
            } else {
               lvt_3_1_.func_70674_bp();
               func_152373_a(p_71515_1_, this, "commands.effect.success.removed.all", new Object[]{lvt_3_1_.func_70005_c_()});
            }
         } else {
            int lvt_4_2_;
            try {
               lvt_4_2_ = func_180528_a(p_71515_2_[1], 1);
            } catch (NumberInvalidException var11) {
               Potion lvt_6_1_ = Potion.func_180142_b(p_71515_2_[1]);
               if (lvt_6_1_ == null) {
                  throw var11;
               }

               lvt_4_2_ = lvt_6_1_.field_76415_H;
            }

            int lvt_5_2_ = 600;
            int lvt_6_2_ = 30;
            int lvt_7_1_ = 0;
            if (lvt_4_2_ >= 0 && lvt_4_2_ < Potion.field_76425_a.length && Potion.field_76425_a[lvt_4_2_] != null) {
               Potion lvt_8_1_ = Potion.field_76425_a[lvt_4_2_];
               if (p_71515_2_.length >= 3) {
                  lvt_6_2_ = func_175764_a(p_71515_2_[2], 0, 1000000);
                  if (lvt_8_1_.func_76403_b()) {
                     lvt_5_2_ = lvt_6_2_;
                  } else {
                     lvt_5_2_ = lvt_6_2_ * 20;
                  }
               } else if (lvt_8_1_.func_76403_b()) {
                  lvt_5_2_ = 1;
               }

               if (p_71515_2_.length >= 4) {
                  lvt_7_1_ = func_175764_a(p_71515_2_[3], 0, 255);
               }

               boolean lvt_9_1_ = true;
               if (p_71515_2_.length >= 5 && "true".equalsIgnoreCase(p_71515_2_[4])) {
                  lvt_9_1_ = false;
               }

               if (lvt_6_2_ > 0) {
                  PotionEffect lvt_10_1_ = new PotionEffect(lvt_4_2_, lvt_5_2_, lvt_7_1_, false, lvt_9_1_);
                  lvt_3_1_.func_70690_d(lvt_10_1_);
                  func_152373_a(p_71515_1_, this, "commands.effect.success", new Object[]{new ChatComponentTranslation(lvt_10_1_.func_76453_d(), new Object[0]), lvt_4_2_, lvt_7_1_, lvt_3_1_.func_70005_c_(), lvt_6_2_});
               } else if (lvt_3_1_.func_82165_m(lvt_4_2_)) {
                  lvt_3_1_.func_82170_o(lvt_4_2_);
                  func_152373_a(p_71515_1_, this, "commands.effect.success.removed", new Object[]{new ChatComponentTranslation(lvt_8_1_.func_76393_a(), new Object[0]), lvt_3_1_.func_70005_c_()});
               } else {
                  throw new CommandException("commands.effect.failure.notActive", new Object[]{new ChatComponentTranslation(lvt_8_1_.func_76393_a(), new Object[0]), lvt_3_1_.func_70005_c_()});
               }
            } else {
               throw new NumberInvalidException("commands.effect.notFound", new Object[]{lvt_4_2_});
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_98152_d());
      } else if (p_180525_2_.length == 2) {
         return func_175762_a(p_180525_2_, Potion.func_181168_c());
      } else {
         return p_180525_2_.length == 5 ? func_71530_a(p_180525_2_, new String[]{"true", "false"}) : null;
      }
   }

   protected String[] func_98152_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
