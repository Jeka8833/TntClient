package net.minecraft.command;

import java.util.List;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandBlockData extends CommandBase {
   public String func_71517_b() {
      return "blockdata";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.blockdata.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.blockdata.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         World lvt_4_1_ = p_71515_1_.func_130014_f_();
         if (!lvt_4_1_.func_175667_e(lvt_3_1_)) {
            throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
         } else {
            TileEntity lvt_5_1_ = lvt_4_1_.func_175625_s(lvt_3_1_);
            if (lvt_5_1_ == null) {
               throw new CommandException("commands.blockdata.notValid", new Object[0]);
            } else {
               NBTTagCompound lvt_6_1_ = new NBTTagCompound();
               lvt_5_1_.func_145841_b(lvt_6_1_);
               NBTTagCompound lvt_7_1_ = (NBTTagCompound)lvt_6_1_.func_74737_b();

               NBTTagCompound lvt_8_2_;
               try {
                  lvt_8_2_ = JsonToNBT.func_180713_a(func_147178_a(p_71515_1_, p_71515_2_, 3).func_150260_c());
               } catch (NBTException var10) {
                  throw new CommandException("commands.blockdata.tagError", new Object[]{var10.getMessage()});
               }

               lvt_6_1_.func_179237_a(lvt_8_2_);
               lvt_6_1_.func_74768_a("x", lvt_3_1_.func_177958_n());
               lvt_6_1_.func_74768_a("y", lvt_3_1_.func_177956_o());
               lvt_6_1_.func_74768_a("z", lvt_3_1_.func_177952_p());
               if (lvt_6_1_.equals(lvt_7_1_)) {
                  throw new CommandException("commands.blockdata.failed", new Object[]{lvt_6_1_.toString()});
               } else {
                  lvt_5_1_.func_145839_a(lvt_6_1_);
                  lvt_5_1_.func_70296_d();
                  lvt_4_1_.func_175689_h(lvt_3_1_);
                  p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  func_152373_a(p_71515_1_, this, "commands.blockdata.success", new Object[]{lvt_6_1_.toString()});
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3 ? func_175771_a(p_180525_2_, 0, p_180525_3_) : null;
   }
}
