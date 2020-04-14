package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandClone extends CommandBase {
   public String func_71517_b() {
      return "clone";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.clone.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 9) {
         throw new WrongUsageException("commands.clone.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         BlockPos lvt_4_1_ = func_175757_a(p_71515_1_, p_71515_2_, 3, false);
         BlockPos lvt_5_1_ = func_175757_a(p_71515_1_, p_71515_2_, 6, false);
         StructureBoundingBox lvt_6_1_ = new StructureBoundingBox(lvt_3_1_, lvt_4_1_);
         StructureBoundingBox lvt_7_1_ = new StructureBoundingBox(lvt_5_1_, lvt_5_1_.func_177971_a(lvt_6_1_.func_175896_b()));
         int lvt_8_1_ = lvt_6_1_.func_78883_b() * lvt_6_1_.func_78882_c() * lvt_6_1_.func_78880_d();
         if (lvt_8_1_ > 32768) {
            throw new CommandException("commands.clone.tooManyBlocks", new Object[]{lvt_8_1_, 32768});
         } else {
            boolean lvt_9_1_ = false;
            Block lvt_10_1_ = null;
            int lvt_11_1_ = -1;
            if ((p_71515_2_.length < 11 || !p_71515_2_[10].equals("force") && !p_71515_2_[10].equals("move")) && lvt_6_1_.func_78884_a(lvt_7_1_)) {
               throw new CommandException("commands.clone.noOverlap", new Object[0]);
            } else {
               if (p_71515_2_.length >= 11 && p_71515_2_[10].equals("move")) {
                  lvt_9_1_ = true;
               }

               if (lvt_6_1_.field_78895_b >= 0 && lvt_6_1_.field_78894_e < 256 && lvt_7_1_.field_78895_b >= 0 && lvt_7_1_.field_78894_e < 256) {
                  World lvt_12_1_ = p_71515_1_.func_130014_f_();
                  if (lvt_12_1_.func_175711_a(lvt_6_1_) && lvt_12_1_.func_175711_a(lvt_7_1_)) {
                     boolean lvt_13_1_ = false;
                     if (p_71515_2_.length >= 10) {
                        if (p_71515_2_[9].equals("masked")) {
                           lvt_13_1_ = true;
                        } else if (p_71515_2_[9].equals("filtered")) {
                           if (p_71515_2_.length < 12) {
                              throw new WrongUsageException("commands.clone.usage", new Object[0]);
                           }

                           lvt_10_1_ = func_147180_g(p_71515_1_, p_71515_2_[11]);
                           if (p_71515_2_.length >= 13) {
                              lvt_11_1_ = func_175764_a(p_71515_2_[12], 0, 15);
                           }
                        }
                     }

                     List<CommandClone.StaticCloneData> lvt_14_1_ = Lists.newArrayList();
                     List<CommandClone.StaticCloneData> lvt_15_1_ = Lists.newArrayList();
                     List<CommandClone.StaticCloneData> lvt_16_1_ = Lists.newArrayList();
                     LinkedList<BlockPos> lvt_17_1_ = Lists.newLinkedList();
                     BlockPos lvt_18_1_ = new BlockPos(lvt_7_1_.field_78897_a - lvt_6_1_.field_78897_a, lvt_7_1_.field_78895_b - lvt_6_1_.field_78895_b, lvt_7_1_.field_78896_c - lvt_6_1_.field_78896_c);

                     for(int lvt_19_1_ = lvt_6_1_.field_78896_c; lvt_19_1_ <= lvt_6_1_.field_78892_f; ++lvt_19_1_) {
                        for(int lvt_20_1_ = lvt_6_1_.field_78895_b; lvt_20_1_ <= lvt_6_1_.field_78894_e; ++lvt_20_1_) {
                           for(int lvt_21_1_ = lvt_6_1_.field_78897_a; lvt_21_1_ <= lvt_6_1_.field_78893_d; ++lvt_21_1_) {
                              BlockPos lvt_22_1_ = new BlockPos(lvt_21_1_, lvt_20_1_, lvt_19_1_);
                              BlockPos lvt_23_1_ = lvt_22_1_.func_177971_a(lvt_18_1_);
                              IBlockState lvt_24_1_ = lvt_12_1_.func_180495_p(lvt_22_1_);
                              if ((!lvt_13_1_ || lvt_24_1_.func_177230_c() != Blocks.field_150350_a) && (lvt_10_1_ == null || lvt_24_1_.func_177230_c() == lvt_10_1_ && (lvt_11_1_ < 0 || lvt_24_1_.func_177230_c().func_176201_c(lvt_24_1_) == lvt_11_1_))) {
                                 TileEntity lvt_25_1_ = lvt_12_1_.func_175625_s(lvt_22_1_);
                                 if (lvt_25_1_ != null) {
                                    NBTTagCompound lvt_26_1_ = new NBTTagCompound();
                                    lvt_25_1_.func_145841_b(lvt_26_1_);
                                    lvt_15_1_.add(new CommandClone.StaticCloneData(lvt_23_1_, lvt_24_1_, lvt_26_1_));
                                    lvt_17_1_.addLast(lvt_22_1_);
                                 } else if (!lvt_24_1_.func_177230_c().func_149730_j() && !lvt_24_1_.func_177230_c().func_149686_d()) {
                                    lvt_16_1_.add(new CommandClone.StaticCloneData(lvt_23_1_, lvt_24_1_, (NBTTagCompound)null));
                                    lvt_17_1_.addFirst(lvt_22_1_);
                                 } else {
                                    lvt_14_1_.add(new CommandClone.StaticCloneData(lvt_23_1_, lvt_24_1_, (NBTTagCompound)null));
                                    lvt_17_1_.addLast(lvt_22_1_);
                                 }
                              }
                           }
                        }
                     }

                     if (lvt_9_1_) {
                        Iterator lvt_19_3_;
                        BlockPos lvt_20_3_;
                        for(lvt_19_3_ = lvt_17_1_.iterator(); lvt_19_3_.hasNext(); lvt_12_1_.func_180501_a(lvt_20_3_, Blocks.field_180401_cv.func_176223_P(), 2)) {
                           lvt_20_3_ = (BlockPos)lvt_19_3_.next();
                           TileEntity lvt_21_2_ = lvt_12_1_.func_175625_s(lvt_20_3_);
                           if (lvt_21_2_ instanceof IInventory) {
                              ((IInventory)lvt_21_2_).func_174888_l();
                           }
                        }

                        lvt_19_3_ = lvt_17_1_.iterator();

                        while(lvt_19_3_.hasNext()) {
                           lvt_20_3_ = (BlockPos)lvt_19_3_.next();
                           lvt_12_1_.func_180501_a(lvt_20_3_, Blocks.field_150350_a.func_176223_P(), 3);
                        }
                     }

                     List<CommandClone.StaticCloneData> lvt_19_4_ = Lists.newArrayList();
                     lvt_19_4_.addAll(lvt_14_1_);
                     lvt_19_4_.addAll(lvt_15_1_);
                     lvt_19_4_.addAll(lvt_16_1_);
                     List<CommandClone.StaticCloneData> lvt_20_4_ = Lists.reverse(lvt_19_4_);

                     Iterator lvt_21_6_;
                     CommandClone.StaticCloneData lvt_22_5_;
                     TileEntity lvt_23_3_;
                     for(lvt_21_6_ = lvt_20_4_.iterator(); lvt_21_6_.hasNext(); lvt_12_1_.func_180501_a(lvt_22_5_.field_179537_a, Blocks.field_180401_cv.func_176223_P(), 2)) {
                        lvt_22_5_ = (CommandClone.StaticCloneData)lvt_21_6_.next();
                        lvt_23_3_ = lvt_12_1_.func_175625_s(lvt_22_5_.field_179537_a);
                        if (lvt_23_3_ instanceof IInventory) {
                           ((IInventory)lvt_23_3_).func_174888_l();
                        }
                     }

                     lvt_8_1_ = 0;
                     lvt_21_6_ = lvt_19_4_.iterator();

                     while(lvt_21_6_.hasNext()) {
                        lvt_22_5_ = (CommandClone.StaticCloneData)lvt_21_6_.next();
                        if (lvt_12_1_.func_180501_a(lvt_22_5_.field_179537_a, lvt_22_5_.field_179535_b, 2)) {
                           ++lvt_8_1_;
                        }
                     }

                     for(lvt_21_6_ = lvt_15_1_.iterator(); lvt_21_6_.hasNext(); lvt_12_1_.func_180501_a(lvt_22_5_.field_179537_a, lvt_22_5_.field_179535_b, 2)) {
                        lvt_22_5_ = (CommandClone.StaticCloneData)lvt_21_6_.next();
                        lvt_23_3_ = lvt_12_1_.func_175625_s(lvt_22_5_.field_179537_a);
                        if (lvt_22_5_.field_179536_c != null && lvt_23_3_ != null) {
                           lvt_22_5_.field_179536_c.func_74768_a("x", lvt_22_5_.field_179537_a.func_177958_n());
                           lvt_22_5_.field_179536_c.func_74768_a("y", lvt_22_5_.field_179537_a.func_177956_o());
                           lvt_22_5_.field_179536_c.func_74768_a("z", lvt_22_5_.field_179537_a.func_177952_p());
                           lvt_23_3_.func_145839_a(lvt_22_5_.field_179536_c);
                           lvt_23_3_.func_70296_d();
                        }
                     }

                     lvt_21_6_ = lvt_20_4_.iterator();

                     while(lvt_21_6_.hasNext()) {
                        lvt_22_5_ = (CommandClone.StaticCloneData)lvt_21_6_.next();
                        lvt_12_1_.func_175722_b(lvt_22_5_.field_179537_a, lvt_22_5_.field_179535_b.func_177230_c());
                     }

                     List<NextTickListEntry> lvt_21_7_ = lvt_12_1_.func_175712_a(lvt_6_1_, false);
                     if (lvt_21_7_ != null) {
                        Iterator lvt_22_6_ = lvt_21_7_.iterator();

                        while(lvt_22_6_.hasNext()) {
                           NextTickListEntry lvt_23_4_ = (NextTickListEntry)lvt_22_6_.next();
                           if (lvt_6_1_.func_175898_b(lvt_23_4_.field_180282_a)) {
                              BlockPos lvt_24_2_ = lvt_23_4_.field_180282_a.func_177971_a(lvt_18_1_);
                              lvt_12_1_.func_180497_b(lvt_24_2_, lvt_23_4_.func_151351_a(), (int)(lvt_23_4_.field_77180_e - lvt_12_1_.func_72912_H().func_82573_f()), lvt_23_4_.field_82754_f);
                           }
                        }
                     }

                     if (lvt_8_1_ <= 0) {
                        throw new CommandException("commands.clone.failed", new Object[0]);
                     } else {
                        p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, lvt_8_1_);
                        func_152373_a(p_71515_1_, this, "commands.clone.success", new Object[]{lvt_8_1_});
                     }
                  } else {
                     throw new CommandException("commands.clone.outOfWorld", new Object[0]);
                  }
               } else {
                  throw new CommandException("commands.clone.outOfWorld", new Object[0]);
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length > 0 && p_180525_2_.length <= 3) {
         return func_175771_a(p_180525_2_, 0, p_180525_3_);
      } else if (p_180525_2_.length > 3 && p_180525_2_.length <= 6) {
         return func_175771_a(p_180525_2_, 3, p_180525_3_);
      } else if (p_180525_2_.length > 6 && p_180525_2_.length <= 9) {
         return func_175771_a(p_180525_2_, 6, p_180525_3_);
      } else if (p_180525_2_.length == 10) {
         return func_71530_a(p_180525_2_, new String[]{"replace", "masked", "filtered"});
      } else if (p_180525_2_.length == 11) {
         return func_71530_a(p_180525_2_, new String[]{"normal", "force", "move"});
      } else {
         return p_180525_2_.length == 12 && "filtered".equals(p_180525_2_[9]) ? func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()) : null;
      }
   }

   static class StaticCloneData {
      public final BlockPos field_179537_a;
      public final IBlockState field_179535_b;
      public final NBTTagCompound field_179536_c;

      public StaticCloneData(BlockPos p_i46037_1_, IBlockState p_i46037_2_, NBTTagCompound p_i46037_3_) {
         this.field_179537_a = p_i46037_1_;
         this.field_179535_b = p_i46037_2_;
         this.field_179536_c = p_i46037_3_;
      }
   }
}
