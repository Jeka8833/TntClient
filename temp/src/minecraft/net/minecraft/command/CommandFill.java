package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandFill extends CommandBase {
   public String func_71517_b() {
      return "fill";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.fill.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 7) {
         throw new WrongUsageException("commands.fill.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         BlockPos lvt_4_1_ = func_175757_a(p_71515_1_, p_71515_2_, 3, false);
         Block lvt_5_1_ = CommandBase.func_147180_g(p_71515_1_, p_71515_2_[6]);
         int lvt_6_1_ = 0;
         if (p_71515_2_.length >= 8) {
            lvt_6_1_ = func_175764_a(p_71515_2_[7], 0, 15);
         }

         BlockPos lvt_7_1_ = new BlockPos(Math.min(lvt_3_1_.func_177958_n(), lvt_4_1_.func_177958_n()), Math.min(lvt_3_1_.func_177956_o(), lvt_4_1_.func_177956_o()), Math.min(lvt_3_1_.func_177952_p(), lvt_4_1_.func_177952_p()));
         BlockPos lvt_8_1_ = new BlockPos(Math.max(lvt_3_1_.func_177958_n(), lvt_4_1_.func_177958_n()), Math.max(lvt_3_1_.func_177956_o(), lvt_4_1_.func_177956_o()), Math.max(lvt_3_1_.func_177952_p(), lvt_4_1_.func_177952_p()));
         int lvt_9_1_ = (lvt_8_1_.func_177958_n() - lvt_7_1_.func_177958_n() + 1) * (lvt_8_1_.func_177956_o() - lvt_7_1_.func_177956_o() + 1) * (lvt_8_1_.func_177952_p() - lvt_7_1_.func_177952_p() + 1);
         if (lvt_9_1_ > 32768) {
            throw new CommandException("commands.fill.tooManyBlocks", new Object[]{lvt_9_1_, 32768});
         } else if (lvt_7_1_.func_177956_o() >= 0 && lvt_8_1_.func_177956_o() < 256) {
            World lvt_10_1_ = p_71515_1_.func_130014_f_();

            for(int lvt_11_1_ = lvt_7_1_.func_177952_p(); lvt_11_1_ < lvt_8_1_.func_177952_p() + 16; lvt_11_1_ += 16) {
               for(int lvt_12_1_ = lvt_7_1_.func_177958_n(); lvt_12_1_ < lvt_8_1_.func_177958_n() + 16; lvt_12_1_ += 16) {
                  if (!lvt_10_1_.func_175667_e(new BlockPos(lvt_12_1_, lvt_8_1_.func_177956_o() - lvt_7_1_.func_177956_o(), lvt_11_1_))) {
                     throw new CommandException("commands.fill.outOfWorld", new Object[0]);
                  }
               }
            }

            NBTTagCompound lvt_11_2_ = new NBTTagCompound();
            boolean lvt_12_2_ = false;
            if (p_71515_2_.length >= 10 && lvt_5_1_.func_149716_u()) {
               String lvt_13_1_ = func_147178_a(p_71515_1_, p_71515_2_, 9).func_150260_c();

               try {
                  lvt_11_2_ = JsonToNBT.func_180713_a(lvt_13_1_);
                  lvt_12_2_ = true;
               } catch (NBTException var21) {
                  throw new CommandException("commands.fill.tagError", new Object[]{var21.getMessage()});
               }
            }

            List<BlockPos> lvt_13_2_ = Lists.newArrayList();
            lvt_9_1_ = 0;

            for(int lvt_14_2_ = lvt_7_1_.func_177952_p(); lvt_14_2_ <= lvt_8_1_.func_177952_p(); ++lvt_14_2_) {
               for(int lvt_15_1_ = lvt_7_1_.func_177956_o(); lvt_15_1_ <= lvt_8_1_.func_177956_o(); ++lvt_15_1_) {
                  for(int lvt_16_1_ = lvt_7_1_.func_177958_n(); lvt_16_1_ <= lvt_8_1_.func_177958_n(); ++lvt_16_1_) {
                     BlockPos lvt_17_1_ = new BlockPos(lvt_16_1_, lvt_15_1_, lvt_14_2_);
                     IBlockState lvt_19_1_;
                     if (p_71515_2_.length >= 9) {
                        if (!p_71515_2_[8].equals("outline") && !p_71515_2_[8].equals("hollow")) {
                           if (p_71515_2_[8].equals("destroy")) {
                              lvt_10_1_.func_175655_b(lvt_17_1_, true);
                           } else if (p_71515_2_[8].equals("keep")) {
                              if (!lvt_10_1_.func_175623_d(lvt_17_1_)) {
                                 continue;
                              }
                           } else if (p_71515_2_[8].equals("replace") && !lvt_5_1_.func_149716_u()) {
                              if (p_71515_2_.length > 9) {
                                 Block lvt_18_1_ = CommandBase.func_147180_g(p_71515_1_, p_71515_2_[9]);
                                 if (lvt_10_1_.func_180495_p(lvt_17_1_).func_177230_c() != lvt_18_1_) {
                                    continue;
                                 }
                              }

                              if (p_71515_2_.length > 10) {
                                 int lvt_18_2_ = CommandBase.func_175755_a(p_71515_2_[10]);
                                 lvt_19_1_ = lvt_10_1_.func_180495_p(lvt_17_1_);
                                 if (lvt_19_1_.func_177230_c().func_176201_c(lvt_19_1_) != lvt_18_2_) {
                                    continue;
                                 }
                              }
                           }
                        } else if (lvt_16_1_ != lvt_7_1_.func_177958_n() && lvt_16_1_ != lvt_8_1_.func_177958_n() && lvt_15_1_ != lvt_7_1_.func_177956_o() && lvt_15_1_ != lvt_8_1_.func_177956_o() && lvt_14_2_ != lvt_7_1_.func_177952_p() && lvt_14_2_ != lvt_8_1_.func_177952_p()) {
                           if (p_71515_2_[8].equals("hollow")) {
                              lvt_10_1_.func_180501_a(lvt_17_1_, Blocks.field_150350_a.func_176223_P(), 2);
                              lvt_13_2_.add(lvt_17_1_);
                           }
                           continue;
                        }
                     }

                     TileEntity lvt_18_3_ = lvt_10_1_.func_175625_s(lvt_17_1_);
                     if (lvt_18_3_ != null) {
                        if (lvt_18_3_ instanceof IInventory) {
                           ((IInventory)lvt_18_3_).func_174888_l();
                        }

                        lvt_10_1_.func_180501_a(lvt_17_1_, Blocks.field_180401_cv.func_176223_P(), lvt_5_1_ == Blocks.field_180401_cv ? 2 : 4);
                     }

                     lvt_19_1_ = lvt_5_1_.func_176203_a(lvt_6_1_);
                     if (lvt_10_1_.func_180501_a(lvt_17_1_, lvt_19_1_, 2)) {
                        lvt_13_2_.add(lvt_17_1_);
                        ++lvt_9_1_;
                        if (lvt_12_2_) {
                           TileEntity lvt_20_1_ = lvt_10_1_.func_175625_s(lvt_17_1_);
                           if (lvt_20_1_ != null) {
                              lvt_11_2_.func_74768_a("x", lvt_17_1_.func_177958_n());
                              lvt_11_2_.func_74768_a("y", lvt_17_1_.func_177956_o());
                              lvt_11_2_.func_74768_a("z", lvt_17_1_.func_177952_p());
                              lvt_20_1_.func_145839_a(lvt_11_2_);
                           }
                        }
                     }
                  }
               }
            }

            Iterator lvt_14_3_ = lvt_13_2_.iterator();

            while(lvt_14_3_.hasNext()) {
               BlockPos lvt_15_2_ = (BlockPos)lvt_14_3_.next();
               Block lvt_16_2_ = lvt_10_1_.func_180495_p(lvt_15_2_).func_177230_c();
               lvt_10_1_.func_175722_b(lvt_15_2_, lvt_16_2_);
            }

            if (lvt_9_1_ <= 0) {
               throw new CommandException("commands.fill.failed", new Object[0]);
            } else {
               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, lvt_9_1_);
               func_152373_a(p_71515_1_, this, "commands.fill.success", new Object[]{lvt_9_1_});
            }
         } else {
            throw new CommandException("commands.fill.outOfWorld", new Object[0]);
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length > 0 && p_180525_2_.length <= 3) {
         return func_175771_a(p_180525_2_, 0, p_180525_3_);
      } else if (p_180525_2_.length > 3 && p_180525_2_.length <= 6) {
         return func_175771_a(p_180525_2_, 3, p_180525_3_);
      } else if (p_180525_2_.length == 7) {
         return func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b());
      } else if (p_180525_2_.length == 9) {
         return func_71530_a(p_180525_2_, new String[]{"replace", "destroy", "keep", "hollow", "outline"});
      } else {
         return p_180525_2_.length == 10 && "replace".equals(p_180525_2_[8]) ? func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()) : null;
      }
   }
}
