package net.minecraft.command;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandExecuteAt extends CommandBase {
   public String func_71517_b() {
      return "execute";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.execute.usage";
   }

   public void func_71515_b(final ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 5) {
         throw new WrongUsageException("commands.execute.usage", new Object[0]);
      } else {
         final Entity lvt_3_1_ = func_175759_a(p_71515_1_, p_71515_2_[0], Entity.class);
         final double lvt_4_1_ = func_175761_b(lvt_3_1_.field_70165_t, p_71515_2_[1], false);
         final double lvt_6_1_ = func_175761_b(lvt_3_1_.field_70163_u, p_71515_2_[2], false);
         final double lvt_8_1_ = func_175761_b(lvt_3_1_.field_70161_v, p_71515_2_[3], false);
         final BlockPos lvt_10_1_ = new BlockPos(lvt_4_1_, lvt_6_1_, lvt_8_1_);
         int lvt_11_1_ = 4;
         if ("detect".equals(p_71515_2_[4]) && p_71515_2_.length > 10) {
            World lvt_12_1_ = lvt_3_1_.func_130014_f_();
            double lvt_13_1_ = func_175761_b(lvt_4_1_, p_71515_2_[5], false);
            double lvt_15_1_ = func_175761_b(lvt_6_1_, p_71515_2_[6], false);
            double lvt_17_1_ = func_175761_b(lvt_8_1_, p_71515_2_[7], false);
            Block lvt_19_1_ = func_147180_g(p_71515_1_, p_71515_2_[8]);
            int lvt_20_1_ = func_175764_a(p_71515_2_[9], -1, 15);
            BlockPos lvt_21_1_ = new BlockPos(lvt_13_1_, lvt_15_1_, lvt_17_1_);
            IBlockState lvt_22_1_ = lvt_12_1_.func_180495_p(lvt_21_1_);
            if (lvt_22_1_.func_177230_c() != lvt_19_1_ || lvt_20_1_ >= 0 && lvt_22_1_.func_177230_c().func_176201_c(lvt_22_1_) != lvt_20_1_) {
               throw new CommandException("commands.execute.failed", new Object[]{"detect", lvt_3_1_.func_70005_c_()});
            }

            lvt_11_1_ = 10;
         }

         String lvt_12_2_ = func_180529_a(p_71515_2_, lvt_11_1_);
         ICommandSender lvt_14_1_ = new ICommandSender() {
            public String func_70005_c_() {
               return lvt_3_1_.func_70005_c_();
            }

            public IChatComponent func_145748_c_() {
               return lvt_3_1_.func_145748_c_();
            }

            public void func_145747_a(IChatComponent p_145747_1_) {
               p_71515_1_.func_145747_a(p_145747_1_);
            }

            public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
               return p_71515_1_.func_70003_b(p_70003_1_, p_70003_2_);
            }

            public BlockPos func_180425_c() {
               return lvt_10_1_;
            }

            public Vec3 func_174791_d() {
               return new Vec3(lvt_4_1_, lvt_6_1_, lvt_8_1_);
            }

            public World func_130014_f_() {
               return lvt_3_1_.field_70170_p;
            }

            public Entity func_174793_f() {
               return lvt_3_1_;
            }

            public boolean func_174792_t_() {
               MinecraftServer lvt_1_1_ = MinecraftServer.func_71276_C();
               return lvt_1_1_ == null || lvt_1_1_.field_71305_c[0].func_82736_K().func_82766_b("commandBlockOutput");
            }

            public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
               lvt_3_1_.func_174794_a(p_174794_1_, p_174794_2_);
            }
         };
         ICommandManager lvt_15_2_ = MinecraftServer.func_71276_C().func_71187_D();

         try {
            int lvt_16_1_ = lvt_15_2_.func_71556_a(lvt_14_1_, lvt_12_2_);
            if (lvt_16_1_ < 1) {
               throw new CommandException("commands.execute.allInvocationsFailed", new Object[]{lvt_12_2_});
            }
         } catch (Throwable var23) {
            throw new CommandException("commands.execute.failed", new Object[]{lvt_12_2_, lvt_3_1_.func_70005_c_()});
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
      } else if (p_180525_2_.length > 1 && p_180525_2_.length <= 4) {
         return func_175771_a(p_180525_2_, 1, p_180525_3_);
      } else if (p_180525_2_.length > 5 && p_180525_2_.length <= 8 && "detect".equals(p_180525_2_[4])) {
         return func_175771_a(p_180525_2_, 5, p_180525_3_);
      } else {
         return p_180525_2_.length == 9 && "detect".equals(p_180525_2_[4]) ? func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()) : null;
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
