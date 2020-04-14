package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemEditableBook extends Item {
   public ItemEditableBook() {
      this.func_77625_d(1);
   }

   public static boolean func_77828_a(NBTTagCompound p_77828_0_) {
      if (!ItemWritableBook.func_150930_a(p_77828_0_)) {
         return false;
      } else if (!p_77828_0_.func_150297_b("title", 8)) {
         return false;
      } else {
         String lvt_1_1_ = p_77828_0_.func_74779_i("title");
         if (lvt_1_1_ != null && lvt_1_1_.length() <= 32) {
            return p_77828_0_.func_150297_b("author", 8);
         } else {
            return false;
         }
      }
   }

   public static int func_179230_h(ItemStack p_179230_0_) {
      return p_179230_0_.func_77978_p().func_74762_e("generation");
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      if (p_77653_1_.func_77942_o()) {
         NBTTagCompound lvt_2_1_ = p_77653_1_.func_77978_p();
         String lvt_3_1_ = lvt_2_1_.func_74779_i("title");
         if (!StringUtils.func_151246_b(lvt_3_1_)) {
            return lvt_3_1_;
         }
      }

      return super.func_77653_i(p_77653_1_);
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      if (p_77624_1_.func_77942_o()) {
         NBTTagCompound lvt_5_1_ = p_77624_1_.func_77978_p();
         String lvt_6_1_ = lvt_5_1_.func_74779_i("author");
         if (!StringUtils.func_151246_b(lvt_6_1_)) {
            p_77624_3_.add(EnumChatFormatting.GRAY + StatCollector.func_74837_a("book.byAuthor", lvt_6_1_));
         }

         p_77624_3_.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("book.generation." + lvt_5_1_.func_74762_e("generation")));
      }

   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if (!p_77659_2_.field_72995_K) {
         this.func_179229_a(p_77659_1_, p_77659_3_);
      }

      p_77659_3_.func_71048_c(p_77659_1_);
      p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
      return p_77659_1_;
   }

   private void func_179229_a(ItemStack p_179229_1_, EntityPlayer p_179229_2_) {
      if (p_179229_1_ != null && p_179229_1_.func_77978_p() != null) {
         NBTTagCompound lvt_3_1_ = p_179229_1_.func_77978_p();
         if (!lvt_3_1_.func_74767_n("resolved")) {
            lvt_3_1_.func_74757_a("resolved", true);
            if (func_77828_a(lvt_3_1_)) {
               NBTTagList lvt_4_1_ = lvt_3_1_.func_150295_c("pages", 8);

               for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.func_74745_c(); ++lvt_5_1_) {
                  String lvt_6_1_ = lvt_4_1_.func_150307_f(lvt_5_1_);

                  Object lvt_7_2_;
                  try {
                     IChatComponent lvt_7_1_ = IChatComponent.Serializer.func_150699_a(lvt_6_1_);
                     lvt_7_2_ = ChatComponentProcessor.func_179985_a(p_179229_2_, lvt_7_1_, p_179229_2_);
                  } catch (Exception var9) {
                     lvt_7_2_ = new ChatComponentText(lvt_6_1_);
                  }

                  lvt_4_1_.func_150304_a(lvt_5_1_, new NBTTagString(IChatComponent.Serializer.func_150696_a((IChatComponent)lvt_7_2_)));
               }

               lvt_3_1_.func_74782_a("pages", lvt_4_1_);
               if (p_179229_2_ instanceof EntityPlayerMP && p_179229_2_.func_71045_bC() == p_179229_1_) {
                  Slot lvt_5_2_ = p_179229_2_.field_71070_bA.func_75147_a(p_179229_2_.field_71071_by, p_179229_2_.field_71071_by.field_70461_c);
                  ((EntityPlayerMP)p_179229_2_).field_71135_a.func_147359_a(new S2FPacketSetSlot(0, lvt_5_2_.field_75222_d, p_179229_1_));
               }

            }
         }
      }
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      return true;
   }
}
