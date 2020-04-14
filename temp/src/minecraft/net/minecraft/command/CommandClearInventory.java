package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandClearInventory extends CommandBase {
   public String func_71517_b() {
      return "clear";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.clear.usage";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      EntityPlayerMP lvt_3_1_ = p_71515_2_.length == 0 ? func_71521_c(p_71515_1_) : func_82359_c(p_71515_1_, p_71515_2_[0]);
      Item lvt_4_1_ = p_71515_2_.length >= 2 ? func_147179_f(p_71515_1_, p_71515_2_[1]) : null;
      int lvt_5_1_ = p_71515_2_.length >= 3 ? func_180528_a(p_71515_2_[2], -1) : -1;
      int lvt_6_1_ = p_71515_2_.length >= 4 ? func_180528_a(p_71515_2_[3], -1) : -1;
      NBTTagCompound lvt_7_1_ = null;
      if (p_71515_2_.length >= 5) {
         try {
            lvt_7_1_ = JsonToNBT.func_180713_a(func_180529_a(p_71515_2_, 4));
         } catch (NBTException var9) {
            throw new CommandException("commands.clear.tagError", new Object[]{var9.getMessage()});
         }
      }

      if (p_71515_2_.length >= 2 && lvt_4_1_ == null) {
         throw new CommandException("commands.clear.failure", new Object[]{lvt_3_1_.func_70005_c_()});
      } else {
         int lvt_8_2_ = lvt_3_1_.field_71071_by.func_174925_a(lvt_4_1_, lvt_5_1_, lvt_6_1_, lvt_7_1_);
         lvt_3_1_.field_71069_bz.func_75142_b();
         if (!lvt_3_1_.field_71075_bZ.field_75098_d) {
            lvt_3_1_.func_71113_k();
         }

         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, lvt_8_2_);
         if (lvt_8_2_ == 0) {
            throw new CommandException("commands.clear.failure", new Object[]{lvt_3_1_.func_70005_c_()});
         } else {
            if (lvt_6_1_ == 0) {
               p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.clear.testing", new Object[]{lvt_3_1_.func_70005_c_(), lvt_8_2_}));
            } else {
               func_152373_a(p_71515_1_, this, "commands.clear.success", new Object[]{lvt_3_1_.func_70005_c_(), lvt_8_2_});
            }

         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_147209_d());
      } else {
         return p_180525_2_.length == 2 ? func_175762_a(p_180525_2_, Item.field_150901_e.func_148742_b()) : null;
      }
   }

   protected String[] func_147209_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
