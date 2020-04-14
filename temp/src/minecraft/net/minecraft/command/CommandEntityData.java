package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class CommandEntityData extends CommandBase {
   public String func_71517_b() {
      return "entitydata";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.entitydata.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.entitydata.usage", new Object[0]);
      } else {
         Entity lvt_3_1_ = func_175768_b(p_71515_1_, p_71515_2_[0]);
         if (lvt_3_1_ instanceof EntityPlayer) {
            throw new CommandException("commands.entitydata.noPlayers", new Object[]{lvt_3_1_.func_145748_c_()});
         } else {
            NBTTagCompound lvt_4_1_ = new NBTTagCompound();
            lvt_3_1_.func_70109_d(lvt_4_1_);
            NBTTagCompound lvt_5_1_ = (NBTTagCompound)lvt_4_1_.func_74737_b();

            NBTTagCompound lvt_6_2_;
            try {
               lvt_6_2_ = JsonToNBT.func_180713_a(func_147178_a(p_71515_1_, p_71515_2_, 1).func_150260_c());
            } catch (NBTException var8) {
               throw new CommandException("commands.entitydata.tagError", new Object[]{var8.getMessage()});
            }

            lvt_6_2_.func_82580_o("UUIDMost");
            lvt_6_2_.func_82580_o("UUIDLeast");
            lvt_4_1_.func_179237_a(lvt_6_2_);
            if (lvt_4_1_.equals(lvt_5_1_)) {
               throw new CommandException("commands.entitydata.failed", new Object[]{lvt_4_1_.toString()});
            } else {
               lvt_3_1_.func_70020_e(lvt_4_1_);
               func_152373_a(p_71515_1_, this, "commands.entitydata.success", new Object[]{lvt_4_1_.toString()});
            }
         }
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
