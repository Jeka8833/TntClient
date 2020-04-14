package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {
   public String func_71517_b() {
      return "summon";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.summon.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.summon.usage", new Object[0]);
      } else {
         String lvt_3_1_ = p_71515_2_[0];
         BlockPos lvt_4_1_ = p_71515_1_.func_180425_c();
         Vec3 lvt_5_1_ = p_71515_1_.func_174791_d();
         double lvt_6_1_ = lvt_5_1_.field_72450_a;
         double lvt_8_1_ = lvt_5_1_.field_72448_b;
         double lvt_10_1_ = lvt_5_1_.field_72449_c;
         if (p_71515_2_.length >= 4) {
            lvt_6_1_ = func_175761_b(lvt_6_1_, p_71515_2_[1], true);
            lvt_8_1_ = func_175761_b(lvt_8_1_, p_71515_2_[2], false);
            lvt_10_1_ = func_175761_b(lvt_10_1_, p_71515_2_[3], true);
            lvt_4_1_ = new BlockPos(lvt_6_1_, lvt_8_1_, lvt_10_1_);
         }

         World lvt_12_1_ = p_71515_1_.func_130014_f_();
         if (!lvt_12_1_.func_175667_e(lvt_4_1_)) {
            throw new CommandException("commands.summon.outOfWorld", new Object[0]);
         } else if ("LightningBolt".equals(lvt_3_1_)) {
            lvt_12_1_.func_72942_c(new EntityLightningBolt(lvt_12_1_, lvt_6_1_, lvt_8_1_, lvt_10_1_));
            func_152373_a(p_71515_1_, this, "commands.summon.success", new Object[0]);
         } else {
            NBTTagCompound lvt_13_1_ = new NBTTagCompound();
            boolean lvt_14_1_ = false;
            if (p_71515_2_.length >= 5) {
               IChatComponent lvt_15_1_ = func_147178_a(p_71515_1_, p_71515_2_, 4);

               try {
                  lvt_13_1_ = JsonToNBT.func_180713_a(lvt_15_1_.func_150260_c());
                  lvt_14_1_ = true;
               } catch (NBTException var20) {
                  throw new CommandException("commands.summon.tagError", new Object[]{var20.getMessage()});
               }
            }

            lvt_13_1_.func_74778_a("id", lvt_3_1_);

            Entity lvt_15_3_;
            try {
               lvt_15_3_ = EntityList.func_75615_a(lvt_13_1_, lvt_12_1_);
            } catch (RuntimeException var19) {
               throw new CommandException("commands.summon.failed", new Object[0]);
            }

            if (lvt_15_3_ == null) {
               throw new CommandException("commands.summon.failed", new Object[0]);
            } else {
               lvt_15_3_.func_70012_b(lvt_6_1_, lvt_8_1_, lvt_10_1_, lvt_15_3_.field_70177_z, lvt_15_3_.field_70125_A);
               if (!lvt_14_1_ && lvt_15_3_ instanceof EntityLiving) {
                  ((EntityLiving)lvt_15_3_).func_180482_a(lvt_12_1_.func_175649_E(new BlockPos(lvt_15_3_)), (IEntityLivingData)null);
               }

               lvt_12_1_.func_72838_d(lvt_15_3_);
               Entity lvt_16_3_ = lvt_15_3_;

               for(NBTTagCompound lvt_17_1_ = lvt_13_1_; lvt_16_3_ != null && lvt_17_1_.func_150297_b("Riding", 10); lvt_17_1_ = lvt_17_1_.func_74775_l("Riding")) {
                  Entity lvt_18_1_ = EntityList.func_75615_a(lvt_17_1_.func_74775_l("Riding"), lvt_12_1_);
                  if (lvt_18_1_ != null) {
                     lvt_18_1_.func_70012_b(lvt_6_1_, lvt_8_1_, lvt_10_1_, lvt_18_1_.field_70177_z, lvt_18_1_.field_70125_A);
                     lvt_12_1_.func_72838_d(lvt_18_1_);
                     lvt_16_3_.func_70078_a(lvt_18_1_);
                  }

                  lvt_16_3_ = lvt_18_1_;
               }

               func_152373_a(p_71515_1_, this, "commands.summon.success", new Object[0]);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_175762_a(p_180525_2_, EntityList.func_180124_b());
      } else {
         return p_180525_2_.length > 1 && p_180525_2_.length <= 4 ? func_175771_a(p_180525_2_, 1, p_180525_3_) : null;
      }
   }
}
