package net.minecraft.item;

import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.WeightedRandomChestContent;

public class ItemEnchantedBook extends Item {
   public boolean func_77636_d(ItemStack p_77636_1_) {
      return true;
   }

   public boolean func_77616_k(ItemStack p_77616_1_) {
      return false;
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return this.func_92110_g(p_77613_1_).func_74745_c() > 0 ? EnumRarity.UNCOMMON : super.func_77613_e(p_77613_1_);
   }

   public NBTTagList func_92110_g(ItemStack p_92110_1_) {
      NBTTagCompound lvt_2_1_ = p_92110_1_.func_77978_p();
      return lvt_2_1_ != null && lvt_2_1_.func_150297_b("StoredEnchantments", 9) ? (NBTTagList)lvt_2_1_.func_74781_a("StoredEnchantments") : new NBTTagList();
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
      NBTTagList lvt_5_1_ = this.func_92110_g(p_77624_1_);
      if (lvt_5_1_ != null) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_.func_74745_c(); ++lvt_6_1_) {
            int lvt_7_1_ = lvt_5_1_.func_150305_b(lvt_6_1_).func_74765_d("id");
            int lvt_8_1_ = lvt_5_1_.func_150305_b(lvt_6_1_).func_74765_d("lvl");
            if (Enchantment.func_180306_c(lvt_7_1_) != null) {
               p_77624_3_.add(Enchantment.func_180306_c(lvt_7_1_).func_77316_c(lvt_8_1_));
            }
         }
      }

   }

   public void func_92115_a(ItemStack p_92115_1_, EnchantmentData p_92115_2_) {
      NBTTagList lvt_3_1_ = this.func_92110_g(p_92115_1_);
      boolean lvt_4_1_ = true;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_3_1_.func_74745_c(); ++lvt_5_1_) {
         NBTTagCompound lvt_6_1_ = lvt_3_1_.func_150305_b(lvt_5_1_);
         if (lvt_6_1_.func_74765_d("id") == p_92115_2_.field_76302_b.field_77352_x) {
            if (lvt_6_1_.func_74765_d("lvl") < p_92115_2_.field_76303_c) {
               lvt_6_1_.func_74777_a("lvl", (short)p_92115_2_.field_76303_c);
            }

            lvt_4_1_ = false;
            break;
         }
      }

      if (lvt_4_1_) {
         NBTTagCompound lvt_5_2_ = new NBTTagCompound();
         lvt_5_2_.func_74777_a("id", (short)p_92115_2_.field_76302_b.field_77352_x);
         lvt_5_2_.func_74777_a("lvl", (short)p_92115_2_.field_76303_c);
         lvt_3_1_.func_74742_a(lvt_5_2_);
      }

      if (!p_92115_1_.func_77942_o()) {
         p_92115_1_.func_77982_d(new NBTTagCompound());
      }

      p_92115_1_.func_77978_p().func_74782_a("StoredEnchantments", lvt_3_1_);
   }

   public ItemStack func_92111_a(EnchantmentData p_92111_1_) {
      ItemStack lvt_2_1_ = new ItemStack(this);
      this.func_92115_a(lvt_2_1_, p_92111_1_);
      return lvt_2_1_;
   }

   public void func_92113_a(Enchantment p_92113_1_, List<ItemStack> p_92113_2_) {
      for(int lvt_3_1_ = p_92113_1_.func_77319_d(); lvt_3_1_ <= p_92113_1_.func_77325_b(); ++lvt_3_1_) {
         p_92113_2_.add(this.func_92111_a(new EnchantmentData(p_92113_1_, lvt_3_1_)));
      }

   }

   public WeightedRandomChestContent func_92114_b(Random p_92114_1_) {
      return this.func_92112_a(p_92114_1_, 1, 1, 1);
   }

   public WeightedRandomChestContent func_92112_a(Random p_92112_1_, int p_92112_2_, int p_92112_3_, int p_92112_4_) {
      ItemStack lvt_5_1_ = new ItemStack(Items.field_151122_aG, 1, 0);
      EnchantmentHelper.func_77504_a(p_92112_1_, lvt_5_1_, 30);
      return new WeightedRandomChestContent(lvt_5_1_, p_92112_2_, p_92112_3_, p_92112_4_);
   }
}
