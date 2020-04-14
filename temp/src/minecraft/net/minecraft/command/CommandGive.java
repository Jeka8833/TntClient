package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandGive extends CommandBase {
   public String func_71517_b() {
      return "give";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.give.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.give.usage", new Object[0]);
      } else {
         EntityPlayer lvt_3_1_ = func_82359_c(p_71515_1_, p_71515_2_[0]);
         Item lvt_4_1_ = func_147179_f(p_71515_1_, p_71515_2_[1]);
         int lvt_5_1_ = p_71515_2_.length >= 3 ? func_175764_a(p_71515_2_[2], 1, 64) : 1;
         int lvt_6_1_ = p_71515_2_.length >= 4 ? func_175755_a(p_71515_2_[3]) : 0;
         ItemStack lvt_7_1_ = new ItemStack(lvt_4_1_, lvt_5_1_, lvt_6_1_);
         if (p_71515_2_.length >= 5) {
            String lvt_8_1_ = func_147178_a(p_71515_1_, p_71515_2_, 4).func_150260_c();

            try {
               lvt_7_1_.func_77982_d(JsonToNBT.func_180713_a(lvt_8_1_));
            } catch (NBTException var10) {
               throw new CommandException("commands.give.tagError", new Object[]{var10.getMessage()});
            }
         }

         boolean lvt_8_2_ = lvt_3_1_.field_71071_by.func_70441_a(lvt_7_1_);
         if (lvt_8_2_) {
            lvt_3_1_.field_70170_p.func_72956_a(lvt_3_1_, "random.pop", 0.2F, ((lvt_3_1_.func_70681_au().nextFloat() - lvt_3_1_.func_70681_au().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            lvt_3_1_.field_71069_bz.func_75142_b();
         }

         EntityItem lvt_9_2_;
         if (lvt_8_2_ && lvt_7_1_.field_77994_a <= 0) {
            lvt_7_1_.field_77994_a = 1;
            p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, lvt_5_1_);
            lvt_9_2_ = lvt_3_1_.func_71019_a(lvt_7_1_, false);
            if (lvt_9_2_ != null) {
               lvt_9_2_.func_174870_v();
            }
         } else {
            p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, lvt_5_1_ - lvt_7_1_.field_77994_a);
            lvt_9_2_ = lvt_3_1_.func_71019_a(lvt_7_1_, false);
            if (lvt_9_2_ != null) {
               lvt_9_2_.func_174868_q();
               lvt_9_2_.func_145797_a(lvt_3_1_.func_70005_c_());
            }
         }

         func_152373_a(p_71515_1_, this, "commands.give.success", new Object[]{lvt_7_1_.func_151000_E(), lvt_5_1_, lvt_3_1_.func_70005_c_()});
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_71536_c());
      } else {
         return p_180525_2_.length == 2 ? func_175762_a(p_180525_2_, Item.field_150901_e.func_148742_b()) : null;
      }
   }

   protected String[] func_71536_c() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
