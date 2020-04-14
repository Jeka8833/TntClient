package net.minecraft.command;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandEnchant extends CommandBase {
   public String func_71517_b() {
      return "enchant";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.enchant.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.enchant.usage", new Object[0]);
      } else {
         EntityPlayer lvt_3_1_ = func_82359_c(p_71515_1_, p_71515_2_[0]);
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 0);

         int lvt_4_2_;
         try {
            lvt_4_2_ = func_180528_a(p_71515_2_[1], 0);
         } catch (NumberInvalidException var12) {
            Enchantment lvt_6_1_ = Enchantment.func_180305_b(p_71515_2_[1]);
            if (lvt_6_1_ == null) {
               throw var12;
            }

            lvt_4_2_ = lvt_6_1_.field_77352_x;
         }

         int lvt_5_2_ = 1;
         ItemStack lvt_6_2_ = lvt_3_1_.func_71045_bC();
         if (lvt_6_2_ == null) {
            throw new CommandException("commands.enchant.noItem", new Object[0]);
         } else {
            Enchantment lvt_7_1_ = Enchantment.func_180306_c(lvt_4_2_);
            if (lvt_7_1_ == null) {
               throw new NumberInvalidException("commands.enchant.notFound", new Object[]{lvt_4_2_});
            } else if (!lvt_7_1_.func_92089_a(lvt_6_2_)) {
               throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if (p_71515_2_.length >= 3) {
                  lvt_5_2_ = func_175764_a(p_71515_2_[2], lvt_7_1_.func_77319_d(), lvt_7_1_.func_77325_b());
               }

               if (lvt_6_2_.func_77942_o()) {
                  NBTTagList lvt_8_1_ = lvt_6_2_.func_77986_q();
                  if (lvt_8_1_ != null) {
                     for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_.func_74745_c(); ++lvt_9_1_) {
                        int lvt_10_1_ = lvt_8_1_.func_150305_b(lvt_9_1_).func_74765_d("id");
                        if (Enchantment.func_180306_c(lvt_10_1_) != null) {
                           Enchantment lvt_11_1_ = Enchantment.func_180306_c(lvt_10_1_);
                           if (!lvt_11_1_.func_77326_a(lvt_7_1_)) {
                              throw new CommandException("commands.enchant.cantCombine", new Object[]{lvt_7_1_.func_77316_c(lvt_5_2_), lvt_11_1_.func_77316_c(lvt_8_1_.func_150305_b(lvt_9_1_).func_74765_d("lvl"))});
                           }
                        }
                     }
                  }
               }

               lvt_6_2_.func_77966_a(lvt_7_1_, lvt_5_2_);
               func_152373_a(p_71515_1_, this, "commands.enchant.success", new Object[0]);
               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 1);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if (p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_90022_d());
      } else {
         return p_180525_2_.length == 2 ? func_175762_a(p_180525_2_, Enchantment.func_181077_c()) : null;
      }
   }

   protected String[] func_90022_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
