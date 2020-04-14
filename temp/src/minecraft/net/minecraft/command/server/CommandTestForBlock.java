package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandTestForBlock extends CommandBase {
   public String func_71517_b() {
      return "testforblock";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.testforblock.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.testforblock.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         Block lvt_4_1_ = Block.func_149684_b(p_71515_2_[3]);
         if (lvt_4_1_ == null) {
            throw new NumberInvalidException("commands.setblock.notFound", new Object[]{p_71515_2_[3]});
         } else {
            int lvt_5_1_ = -1;
            if (p_71515_2_.length >= 5) {
               lvt_5_1_ = func_175764_a(p_71515_2_[4], -1, 15);
            }

            World lvt_6_1_ = p_71515_1_.func_130014_f_();
            if (!lvt_6_1_.func_175667_e(lvt_3_1_)) {
               throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               NBTTagCompound lvt_7_1_ = new NBTTagCompound();
               boolean lvt_8_1_ = false;
               if (p_71515_2_.length >= 6 && lvt_4_1_.func_149716_u()) {
                  String lvt_9_1_ = func_147178_a(p_71515_1_, p_71515_2_, 5).func_150260_c();

                  try {
                     lvt_7_1_ = JsonToNBT.func_180713_a(lvt_9_1_);
                     lvt_8_1_ = true;
                  } catch (NBTException var13) {
                     throw new CommandException("commands.setblock.tagError", new Object[]{var13.getMessage()});
                  }
               }

               IBlockState lvt_9_2_ = lvt_6_1_.func_180495_p(lvt_3_1_);
               Block lvt_10_2_ = lvt_9_2_.func_177230_c();
               if (lvt_10_2_ != lvt_4_1_) {
                  throw new CommandException("commands.testforblock.failed.tile", new Object[]{lvt_3_1_.func_177958_n(), lvt_3_1_.func_177956_o(), lvt_3_1_.func_177952_p(), lvt_10_2_.func_149732_F(), lvt_4_1_.func_149732_F()});
               } else {
                  if (lvt_5_1_ > -1) {
                     int lvt_11_1_ = lvt_9_2_.func_177230_c().func_176201_c(lvt_9_2_);
                     if (lvt_11_1_ != lvt_5_1_) {
                        throw new CommandException("commands.testforblock.failed.data", new Object[]{lvt_3_1_.func_177958_n(), lvt_3_1_.func_177956_o(), lvt_3_1_.func_177952_p(), lvt_11_1_, lvt_5_1_});
                     }
                  }

                  if (lvt_8_1_) {
                     TileEntity lvt_11_2_ = lvt_6_1_.func_175625_s(lvt_3_1_);
                     if (lvt_11_2_ == null) {
                        throw new CommandException("commands.testforblock.failed.tileEntity", new Object[]{lvt_3_1_.func_177958_n(), lvt_3_1_.func_177956_o(), lvt_3_1_.func_177952_p()});
                     }

                     NBTTagCompound lvt_12_1_ = new NBTTagCompound();
                     lvt_11_2_.func_145841_b(lvt_12_1_);
                     if (!NBTUtil.func_181123_a(lvt_7_1_, lvt_12_1_, true)) {
                        throw new CommandException("commands.testforblock.failed.nbt", new Object[]{lvt_3_1_.func_177958_n(), lvt_3_1_.func_177956_o(), lvt_3_1_.func_177952_p()});
                     }
                  }

                  p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  func_152373_a(p_71515_1_, this, "commands.testforblock.success", new Object[]{lvt_3_1_.func_177958_n(), lvt_3_1_.func_177956_o(), lvt_3_1_.func_177952_p()});
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length > 0 && p_180525_2_.length <= 3) {
         return func_175771_a(p_180525_2_, 0, p_180525_3_);
      } else {
         return p_180525_2_.length == 4 ? func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()) : null;
      }
   }
}
