package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemBow extends Item {
   public static final String[] field_94601_a = new String[]{"pulling_0", "pulling_1", "pulling_2"};

   public ItemBow() {
      this.field_77777_bU = 1;
      this.func_77656_e(384);
      this.func_77637_a(CreativeTabs.field_78037_j);
   }

   public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
      boolean lvt_5_1_ = p_77615_3_.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, p_77615_1_) > 0;
      if (lvt_5_1_ || p_77615_3_.field_71071_by.func_146028_b(Items.field_151032_g)) {
         int lvt_6_1_ = this.func_77626_a(p_77615_1_) - p_77615_4_;
         float lvt_7_1_ = (float)lvt_6_1_ / 20.0F;
         lvt_7_1_ = (lvt_7_1_ * lvt_7_1_ + lvt_7_1_ * 2.0F) / 3.0F;
         if ((double)lvt_7_1_ < 0.1D) {
            return;
         }

         if (lvt_7_1_ > 1.0F) {
            lvt_7_1_ = 1.0F;
         }

         EntityArrow lvt_8_1_ = new EntityArrow(p_77615_2_, p_77615_3_, lvt_7_1_ * 2.0F);
         if (lvt_7_1_ == 1.0F) {
            lvt_8_1_.func_70243_d(true);
         }

         int lvt_9_1_ = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, p_77615_1_);
         if (lvt_9_1_ > 0) {
            lvt_8_1_.func_70239_b(lvt_8_1_.func_70242_d() + (double)lvt_9_1_ * 0.5D + 0.5D);
         }

         int lvt_10_1_ = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, p_77615_1_);
         if (lvt_10_1_ > 0) {
            lvt_8_1_.func_70240_a(lvt_10_1_);
         }

         if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, p_77615_1_) > 0) {
            lvt_8_1_.func_70015_d(100);
         }

         p_77615_1_.func_77972_a(1, p_77615_3_);
         p_77615_2_.func_72956_a(p_77615_3_, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + lvt_7_1_ * 0.5F);
         if (lvt_5_1_) {
            lvt_8_1_.field_70251_a = 2;
         } else {
            p_77615_3_.field_71071_by.func_146026_a(Items.field_151032_g);
         }

         p_77615_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
         if (!p_77615_2_.field_72995_K) {
            p_77615_2_.func_72838_d(lvt_8_1_);
         }
      }

   }

   public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      return p_77654_1_;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 72000;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.BOW;
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if (p_77659_3_.field_71075_bZ.field_75098_d || p_77659_3_.field_71071_by.func_146028_b(Items.field_151032_g)) {
         p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
      }

      return p_77659_1_;
   }

   public int func_77619_b() {
      return 1;
   }
}
