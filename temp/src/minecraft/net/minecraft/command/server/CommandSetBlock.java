package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase {
   public String func_71517_b() {
      return "setblock";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.setblock.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.setblock.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         Block lvt_4_1_ = CommandBase.func_147180_g(p_71515_1_, p_71515_2_[3]);
         int lvt_5_1_ = 0;
         if (p_71515_2_.length >= 5) {
            lvt_5_1_ = func_175764_a(p_71515_2_[4], 0, 15);
         }

         World lvt_6_1_ = p_71515_1_.func_130014_f_();
         if (!lvt_6_1_.func_175667_e(lvt_3_1_)) {
            throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound lvt_7_1_ = new NBTTagCompound();
            boolean lvt_8_1_ = false;
            if (p_71515_2_.length >= 7 && lvt_4_1_.func_149716_u()) {
               String lvt_9_1_ = func_147178_a(p_71515_1_, p_71515_2_, 6).func_150260_c();

               try {
                  lvt_7_1_ = JsonToNBT.func_180713_a(lvt_9_1_);
                  lvt_8_1_ = true;
               } catch (NBTException var12) {
                  throw new CommandException("commands.setblock.tagError", new Object[]{var12.getMessage()});
               }
            }

            if (p_71515_2_.length >= 6) {
               if (p_71515_2_[5].equals("destroy")) {
                  lvt_6_1_.func_175655_b(lvt_3_1_, true);
                  if (lvt_4_1_ == Blocks.field_150350_a) {
                     func_152373_a(p_71515_1_, this, "commands.setblock.success", new Object[0]);
                     return;
                  }
               } else if (p_71515_2_[5].equals("keep") && !lvt_6_1_.func_175623_d(lvt_3_1_)) {
                  throw new CommandException("commands.setblock.noChange", new Object[0]);
               }
            }

            TileEntity lvt_9_2_ = lvt_6_1_.func_175625_s(lvt_3_1_);
            if (lvt_9_2_ != null) {
               if (lvt_9_2_ instanceof IInventory) {
                  ((IInventory)lvt_9_2_).func_174888_l();
               }

               lvt_6_1_.func_180501_a(lvt_3_1_, Blocks.field_150350_a.func_176223_P(), lvt_4_1_ == Blocks.field_150350_a ? 2 : 4);
            }

            IBlockState lvt_10_2_ = lvt_4_1_.func_176203_a(lvt_5_1_);
            if (!lvt_6_1_.func_180501_a(lvt_3_1_, lvt_10_2_, 2)) {
               throw new CommandException("commands.setblock.noChange", new Object[0]);
            } else {
               if (lvt_8_1_) {
                  TileEntity lvt_11_1_ = lvt_6_1_.func_175625_s(lvt_3_1_);
                  if (lvt_11_1_ != null) {
                     lvt_7_1_.func_74768_a("x", lvt_3_1_.func_177958_n());
                     lvt_7_1_.func_74768_a("y", lvt_3_1_.func_177956_o());
                     lvt_7_1_.func_74768_a("z", lvt_3_1_.func_177952_p());
                     lvt_11_1_.func_145839_a(lvt_7_1_);
                  }
               }

               lvt_6_1_.func_175722_b(lvt_3_1_, lvt_10_2_.func_177230_c());
               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
               func_152373_a(p_71515_1_, this, "commands.setblock.success", new Object[0]);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length > 0 && p_180525_2_.length <= 3) {
         return func_175771_a(p_180525_2_, 0, p_180525_3_);
      } else if (p_180525_2_.length == 4) {
         return func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b());
      } else {
         return p_180525_2_.length == 6 ? func_71530_a(p_180525_2_, new String[]{"replace", "destroy", "keep"}) : null;
      }
   }
}
