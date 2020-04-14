package net.minecraft.command;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandCompare extends CommandBase {
   public String func_71517_b() {
      return "testforblocks";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.compare.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 9) {
         throw new WrongUsageException("commands.compare.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos lvt_3_1_ = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         BlockPos lvt_4_1_ = func_175757_a(p_71515_1_, p_71515_2_, 3, false);
         BlockPos lvt_5_1_ = func_175757_a(p_71515_1_, p_71515_2_, 6, false);
         StructureBoundingBox lvt_6_1_ = new StructureBoundingBox(lvt_3_1_, lvt_4_1_);
         StructureBoundingBox lvt_7_1_ = new StructureBoundingBox(lvt_5_1_, lvt_5_1_.func_177971_a(lvt_6_1_.func_175896_b()));
         int lvt_8_1_ = lvt_6_1_.func_78883_b() * lvt_6_1_.func_78882_c() * lvt_6_1_.func_78880_d();
         if (lvt_8_1_ > 524288) {
            throw new CommandException("commands.compare.tooManyBlocks", new Object[]{lvt_8_1_, 524288});
         } else if (lvt_6_1_.field_78895_b >= 0 && lvt_6_1_.field_78894_e < 256 && lvt_7_1_.field_78895_b >= 0 && lvt_7_1_.field_78894_e < 256) {
            World lvt_9_1_ = p_71515_1_.func_130014_f_();
            if (lvt_9_1_.func_175711_a(lvt_6_1_) && lvt_9_1_.func_175711_a(lvt_7_1_)) {
               boolean lvt_10_1_ = false;
               if (p_71515_2_.length > 9 && p_71515_2_[9].equals("masked")) {
                  lvt_10_1_ = true;
               }

               lvt_8_1_ = 0;
               BlockPos lvt_11_1_ = new BlockPos(lvt_7_1_.field_78897_a - lvt_6_1_.field_78897_a, lvt_7_1_.field_78895_b - lvt_6_1_.field_78895_b, lvt_7_1_.field_78896_c - lvt_6_1_.field_78896_c);
               BlockPos.MutableBlockPos lvt_12_1_ = new BlockPos.MutableBlockPos();
               BlockPos.MutableBlockPos lvt_13_1_ = new BlockPos.MutableBlockPos();

               for(int lvt_14_1_ = lvt_6_1_.field_78896_c; lvt_14_1_ <= lvt_6_1_.field_78892_f; ++lvt_14_1_) {
                  for(int lvt_15_1_ = lvt_6_1_.field_78895_b; lvt_15_1_ <= lvt_6_1_.field_78894_e; ++lvt_15_1_) {
                     for(int lvt_16_1_ = lvt_6_1_.field_78897_a; lvt_16_1_ <= lvt_6_1_.field_78893_d; ++lvt_16_1_) {
                        lvt_12_1_.func_181079_c(lvt_16_1_, lvt_15_1_, lvt_14_1_);
                        lvt_13_1_.func_181079_c(lvt_16_1_ + lvt_11_1_.func_177958_n(), lvt_15_1_ + lvt_11_1_.func_177956_o(), lvt_14_1_ + lvt_11_1_.func_177952_p());
                        boolean lvt_17_1_ = false;
                        IBlockState lvt_18_1_ = lvt_9_1_.func_180495_p(lvt_12_1_);
                        if (!lvt_10_1_ || lvt_18_1_.func_177230_c() != Blocks.field_150350_a) {
                           if (lvt_18_1_ == lvt_9_1_.func_180495_p(lvt_13_1_)) {
                              TileEntity lvt_19_1_ = lvt_9_1_.func_175625_s(lvt_12_1_);
                              TileEntity lvt_20_1_ = lvt_9_1_.func_175625_s(lvt_13_1_);
                              if (lvt_19_1_ != null && lvt_20_1_ != null) {
                                 NBTTagCompound lvt_21_1_ = new NBTTagCompound();
                                 lvt_19_1_.func_145841_b(lvt_21_1_);
                                 lvt_21_1_.func_82580_o("x");
                                 lvt_21_1_.func_82580_o("y");
                                 lvt_21_1_.func_82580_o("z");
                                 NBTTagCompound lvt_22_1_ = new NBTTagCompound();
                                 lvt_20_1_.func_145841_b(lvt_22_1_);
                                 lvt_22_1_.func_82580_o("x");
                                 lvt_22_1_.func_82580_o("y");
                                 lvt_22_1_.func_82580_o("z");
                                 if (!lvt_21_1_.equals(lvt_22_1_)) {
                                    lvt_17_1_ = true;
                                 }
                              } else if (lvt_19_1_ != null) {
                                 lvt_17_1_ = true;
                              }
                           } else {
                              lvt_17_1_ = true;
                           }

                           ++lvt_8_1_;
                           if (lvt_17_1_) {
                              throw new CommandException("commands.compare.failed", new Object[0]);
                           }
                        }
                     }
                  }
               }

               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, lvt_8_1_);
               func_152373_a(p_71515_1_, this, "commands.compare.success", new Object[]{lvt_8_1_});
            } else {
               throw new CommandException("commands.compare.outOfWorld", new Object[0]);
            }
         } else {
            throw new CommandException("commands.compare.outOfWorld", new Object[0]);
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
      } else {
         return p_180525_2_.length == 10 ? func_71530_a(p_180525_2_, new String[]{"masked", "all"}) : null;
      }
   }
}
