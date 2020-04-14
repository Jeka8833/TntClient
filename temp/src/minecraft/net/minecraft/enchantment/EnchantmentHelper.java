package net.minecraft.enchantment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandom;

public class EnchantmentHelper {
   private static final Random field_77522_a = new Random();
   private static final EnchantmentHelper.ModifierDamage field_77520_b = new EnchantmentHelper.ModifierDamage();
   private static final EnchantmentHelper.ModifierLiving field_77521_c = new EnchantmentHelper.ModifierLiving();
   private static final EnchantmentHelper.HurtIterator field_151388_d = new EnchantmentHelper.HurtIterator();
   private static final EnchantmentHelper.DamageIterator field_151389_e = new EnchantmentHelper.DamageIterator();

   public static int func_77506_a(int p_77506_0_, ItemStack p_77506_1_) {
      if (p_77506_1_ == null) {
         return 0;
      } else {
         NBTTagList lvt_2_1_ = p_77506_1_.func_77986_q();
         if (lvt_2_1_ == null) {
            return 0;
         } else {
            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
               int lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("id");
               int lvt_5_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("lvl");
               if (lvt_4_1_ == p_77506_0_) {
                  return lvt_5_1_;
               }
            }

            return 0;
         }
      }
   }

   public static Map<Integer, Integer> func_82781_a(ItemStack p_82781_0_) {
      Map<Integer, Integer> lvt_1_1_ = Maps.newLinkedHashMap();
      NBTTagList lvt_2_1_ = p_82781_0_.func_77973_b() == Items.field_151134_bR ? Items.field_151134_bR.func_92110_g(p_82781_0_) : p_82781_0_.func_77986_q();
      if (lvt_2_1_ != null) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
            int lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("id");
            int lvt_5_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("lvl");
            lvt_1_1_.put(Integer.valueOf(lvt_4_1_), Integer.valueOf(lvt_5_1_));
         }
      }

      return lvt_1_1_;
   }

   public static void func_82782_a(Map<Integer, Integer> p_82782_0_, ItemStack p_82782_1_) {
      NBTTagList lvt_2_1_ = new NBTTagList();
      Iterator lvt_3_1_ = p_82782_0_.keySet().iterator();

      while(lvt_3_1_.hasNext()) {
         int lvt_4_1_ = (Integer)lvt_3_1_.next();
         Enchantment lvt_5_1_ = Enchantment.func_180306_c(lvt_4_1_);
         if (lvt_5_1_ != null) {
            NBTTagCompound lvt_6_1_ = new NBTTagCompound();
            lvt_6_1_.func_74777_a("id", (short)lvt_4_1_);
            lvt_6_1_.func_74777_a("lvl", (short)(Integer)p_82782_0_.get(lvt_4_1_));
            lvt_2_1_.func_74742_a(lvt_6_1_);
            if (p_82782_1_.func_77973_b() == Items.field_151134_bR) {
               Items.field_151134_bR.func_92115_a(p_82782_1_, new EnchantmentData(lvt_5_1_, (Integer)p_82782_0_.get(lvt_4_1_)));
            }
         }
      }

      if (lvt_2_1_.func_74745_c() > 0) {
         if (p_82782_1_.func_77973_b() != Items.field_151134_bR) {
            p_82782_1_.func_77983_a("ench", lvt_2_1_);
         }
      } else if (p_82782_1_.func_77942_o()) {
         p_82782_1_.func_77978_p().func_82580_o("ench");
      }

   }

   public static int func_77511_a(int p_77511_0_, ItemStack[] p_77511_1_) {
      if (p_77511_1_ == null) {
         return 0;
      } else {
         int lvt_2_1_ = 0;
         ItemStack[] lvt_3_1_ = p_77511_1_;
         int lvt_4_1_ = p_77511_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            ItemStack lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            int lvt_7_1_ = func_77506_a(p_77511_0_, lvt_6_1_);
            if (lvt_7_1_ > lvt_2_1_) {
               lvt_2_1_ = lvt_7_1_;
            }
         }

         return lvt_2_1_;
      }
   }

   private static void func_77518_a(EnchantmentHelper.IModifier p_77518_0_, ItemStack p_77518_1_) {
      if (p_77518_1_ != null) {
         NBTTagList lvt_2_1_ = p_77518_1_.func_77986_q();
         if (lvt_2_1_ != null) {
            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
               int lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("id");
               int lvt_5_1_ = lvt_2_1_.func_150305_b(lvt_3_1_).func_74765_d("lvl");
               if (Enchantment.func_180306_c(lvt_4_1_) != null) {
                  p_77518_0_.func_77493_a(Enchantment.func_180306_c(lvt_4_1_), lvt_5_1_);
               }
            }

         }
      }
   }

   private static void func_77516_a(EnchantmentHelper.IModifier p_77516_0_, ItemStack[] p_77516_1_) {
      ItemStack[] lvt_2_1_ = p_77516_1_;
      int lvt_3_1_ = p_77516_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         ItemStack lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         func_77518_a(p_77516_0_, lvt_5_1_);
      }

   }

   public static int func_77508_a(ItemStack[] p_77508_0_, DamageSource p_77508_1_) {
      field_77520_b.field_77497_a = 0;
      field_77520_b.field_77496_b = p_77508_1_;
      func_77516_a(field_77520_b, p_77508_0_);
      if (field_77520_b.field_77497_a > 25) {
         field_77520_b.field_77497_a = 25;
      } else if (field_77520_b.field_77497_a < 0) {
         field_77520_b.field_77497_a = 0;
      }

      return (field_77520_b.field_77497_a + 1 >> 1) + field_77522_a.nextInt((field_77520_b.field_77497_a >> 1) + 1);
   }

   public static float func_152377_a(ItemStack p_152377_0_, EnumCreatureAttribute p_152377_1_) {
      field_77521_c.field_77495_a = 0.0F;
      field_77521_c.field_77494_b = p_152377_1_;
      func_77518_a(field_77521_c, p_152377_0_);
      return field_77521_c.field_77495_a;
   }

   public static void func_151384_a(EntityLivingBase p_151384_0_, Entity p_151384_1_) {
      field_151388_d.field_151363_b = p_151384_1_;
      field_151388_d.field_151364_a = p_151384_0_;
      if (p_151384_0_ != null) {
         func_77516_a(field_151388_d, p_151384_0_.func_70035_c());
      }

      if (p_151384_1_ instanceof EntityPlayer) {
         func_77518_a(field_151388_d, p_151384_0_.func_70694_bm());
      }

   }

   public static void func_151385_b(EntityLivingBase p_151385_0_, Entity p_151385_1_) {
      field_151389_e.field_151366_a = p_151385_0_;
      field_151389_e.field_151365_b = p_151385_1_;
      if (p_151385_0_ != null) {
         func_77516_a(field_151389_e, p_151385_0_.func_70035_c());
      }

      if (p_151385_0_ instanceof EntityPlayer) {
         func_77518_a(field_151389_e, p_151385_0_.func_70694_bm());
      }

   }

   public static int func_77501_a(EntityLivingBase p_77501_0_) {
      return func_77506_a(Enchantment.field_180313_o.field_77352_x, p_77501_0_.func_70694_bm());
   }

   public static int func_90036_a(EntityLivingBase p_90036_0_) {
      return func_77506_a(Enchantment.field_77334_n.field_77352_x, p_90036_0_.func_70694_bm());
   }

   public static int func_180319_a(Entity p_180319_0_) {
      return func_77511_a(Enchantment.field_180317_h.field_77352_x, p_180319_0_.func_70035_c());
   }

   public static int func_180318_b(Entity p_180318_0_) {
      return func_77511_a(Enchantment.field_180316_k.field_77352_x, p_180318_0_.func_70035_c());
   }

   public static int func_77509_b(EntityLivingBase p_77509_0_) {
      return func_77506_a(Enchantment.field_77349_p.field_77352_x, p_77509_0_.func_70694_bm());
   }

   public static boolean func_77502_d(EntityLivingBase p_77502_0_) {
      return func_77506_a(Enchantment.field_77348_q.field_77352_x, p_77502_0_.func_70694_bm()) > 0;
   }

   public static int func_77517_e(EntityLivingBase p_77517_0_) {
      return func_77506_a(Enchantment.field_77346_s.field_77352_x, p_77517_0_.func_70694_bm());
   }

   public static int func_151386_g(EntityLivingBase p_151386_0_) {
      return func_77506_a(Enchantment.field_151370_z.field_77352_x, p_151386_0_.func_70694_bm());
   }

   public static int func_151387_h(EntityLivingBase p_151387_0_) {
      return func_77506_a(Enchantment.field_151369_A.field_77352_x, p_151387_0_.func_70694_bm());
   }

   public static int func_77519_f(EntityLivingBase p_77519_0_) {
      return func_77506_a(Enchantment.field_77335_o.field_77352_x, p_77519_0_.func_70694_bm());
   }

   public static boolean func_77510_g(EntityLivingBase p_77510_0_) {
      return func_77511_a(Enchantment.field_77341_i.field_77352_x, p_77510_0_.func_70035_c()) > 0;
   }

   public static ItemStack func_92099_a(Enchantment p_92099_0_, EntityLivingBase p_92099_1_) {
      ItemStack[] lvt_2_1_ = p_92099_1_.func_70035_c();
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         ItemStack lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_ != null && func_77506_a(p_92099_0_.field_77352_x, lvt_5_1_) > 0) {
            return lvt_5_1_;
         }
      }

      return null;
   }

   public static int func_77514_a(Random p_77514_0_, int p_77514_1_, int p_77514_2_, ItemStack p_77514_3_) {
      Item lvt_4_1_ = p_77514_3_.func_77973_b();
      int lvt_5_1_ = lvt_4_1_.func_77619_b();
      if (lvt_5_1_ <= 0) {
         return 0;
      } else {
         if (p_77514_2_ > 15) {
            p_77514_2_ = 15;
         }

         int lvt_6_1_ = p_77514_0_.nextInt(8) + 1 + (p_77514_2_ >> 1) + p_77514_0_.nextInt(p_77514_2_ + 1);
         if (p_77514_1_ == 0) {
            return Math.max(lvt_6_1_ / 3, 1);
         } else {
            return p_77514_1_ == 1 ? lvt_6_1_ * 2 / 3 + 1 : Math.max(lvt_6_1_, p_77514_2_ * 2);
         }
      }
   }

   public static ItemStack func_77504_a(Random p_77504_0_, ItemStack p_77504_1_, int p_77504_2_) {
      List<EnchantmentData> lvt_3_1_ = func_77513_b(p_77504_0_, p_77504_1_, p_77504_2_);
      boolean lvt_4_1_ = p_77504_1_.func_77973_b() == Items.field_151122_aG;
      if (lvt_4_1_) {
         p_77504_1_.func_150996_a(Items.field_151134_bR);
      }

      if (lvt_3_1_ != null) {
         Iterator lvt_5_1_ = lvt_3_1_.iterator();

         while(lvt_5_1_.hasNext()) {
            EnchantmentData lvt_6_1_ = (EnchantmentData)lvt_5_1_.next();
            if (lvt_4_1_) {
               Items.field_151134_bR.func_92115_a(p_77504_1_, lvt_6_1_);
            } else {
               p_77504_1_.func_77966_a(lvt_6_1_.field_76302_b, lvt_6_1_.field_76303_c);
            }
         }
      }

      return p_77504_1_;
   }

   public static List<EnchantmentData> func_77513_b(Random p_77513_0_, ItemStack p_77513_1_, int p_77513_2_) {
      Item lvt_3_1_ = p_77513_1_.func_77973_b();
      int lvt_4_1_ = lvt_3_1_.func_77619_b();
      if (lvt_4_1_ <= 0) {
         return null;
      } else {
         lvt_4_1_ /= 2;
         lvt_4_1_ = 1 + p_77513_0_.nextInt((lvt_4_1_ >> 1) + 1) + p_77513_0_.nextInt((lvt_4_1_ >> 1) + 1);
         int lvt_5_1_ = lvt_4_1_ + p_77513_2_;
         float lvt_6_1_ = (p_77513_0_.nextFloat() + p_77513_0_.nextFloat() - 1.0F) * 0.15F;
         int lvt_7_1_ = (int)((float)lvt_5_1_ * (1.0F + lvt_6_1_) + 0.5F);
         if (lvt_7_1_ < 1) {
            lvt_7_1_ = 1;
         }

         List<EnchantmentData> lvt_8_1_ = null;
         Map<Integer, EnchantmentData> lvt_9_1_ = func_77505_b(lvt_7_1_, p_77513_1_);
         if (lvt_9_1_ != null && !lvt_9_1_.isEmpty()) {
            EnchantmentData lvt_10_1_ = (EnchantmentData)WeightedRandom.func_76271_a(p_77513_0_, lvt_9_1_.values());
            if (lvt_10_1_ != null) {
               lvt_8_1_ = Lists.newArrayList();
               lvt_8_1_.add(lvt_10_1_);

               for(int lvt_11_1_ = lvt_7_1_; p_77513_0_.nextInt(50) <= lvt_11_1_; lvt_11_1_ >>= 1) {
                  Iterator lvt_12_1_ = lvt_9_1_.keySet().iterator();

                  while(lvt_12_1_.hasNext()) {
                     Integer lvt_13_1_ = (Integer)lvt_12_1_.next();
                     boolean lvt_14_1_ = true;
                     Iterator lvt_15_1_ = lvt_8_1_.iterator();

                     while(lvt_15_1_.hasNext()) {
                        EnchantmentData lvt_16_1_ = (EnchantmentData)lvt_15_1_.next();
                        if (!lvt_16_1_.field_76302_b.func_77326_a(Enchantment.func_180306_c(lvt_13_1_))) {
                           lvt_14_1_ = false;
                           break;
                        }
                     }

                     if (!lvt_14_1_) {
                        lvt_12_1_.remove();
                     }
                  }

                  if (!lvt_9_1_.isEmpty()) {
                     EnchantmentData lvt_13_2_ = (EnchantmentData)WeightedRandom.func_76271_a(p_77513_0_, lvt_9_1_.values());
                     lvt_8_1_.add(lvt_13_2_);
                  }
               }
            }
         }

         return lvt_8_1_;
      }
   }

   public static Map<Integer, EnchantmentData> func_77505_b(int p_77505_0_, ItemStack p_77505_1_) {
      Item lvt_2_1_ = p_77505_1_.func_77973_b();
      Map<Integer, EnchantmentData> lvt_3_1_ = null;
      boolean lvt_4_1_ = p_77505_1_.func_77973_b() == Items.field_151122_aG;
      Enchantment[] lvt_5_1_ = Enchantment.field_77331_b;
      int lvt_6_1_ = lvt_5_1_.length;

      for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
         Enchantment lvt_8_1_ = lvt_5_1_[lvt_7_1_];
         if (lvt_8_1_ != null && (lvt_8_1_.field_77351_y.func_77557_a(lvt_2_1_) || lvt_4_1_)) {
            for(int lvt_9_1_ = lvt_8_1_.func_77319_d(); lvt_9_1_ <= lvt_8_1_.func_77325_b(); ++lvt_9_1_) {
               if (p_77505_0_ >= lvt_8_1_.func_77321_a(lvt_9_1_) && p_77505_0_ <= lvt_8_1_.func_77317_b(lvt_9_1_)) {
                  if (lvt_3_1_ == null) {
                     lvt_3_1_ = Maps.newHashMap();
                  }

                  lvt_3_1_.put(lvt_8_1_.field_77352_x, new EnchantmentData(lvt_8_1_, lvt_9_1_));
               }
            }
         }
      }

      return lvt_3_1_;
   }

   static final class DamageIterator implements EnchantmentHelper.IModifier {
      public EntityLivingBase field_151366_a;
      public Entity field_151365_b;

      private DamageIterator() {
      }

      public void func_77493_a(Enchantment p_77493_1_, int p_77493_2_) {
         p_77493_1_.func_151368_a(this.field_151366_a, this.field_151365_b, p_77493_2_);
      }
   }

   static final class HurtIterator implements EnchantmentHelper.IModifier {
      public EntityLivingBase field_151364_a;
      public Entity field_151363_b;

      private HurtIterator() {
      }

      public void func_77493_a(Enchantment p_77493_1_, int p_77493_2_) {
         p_77493_1_.func_151367_b(this.field_151364_a, this.field_151363_b, p_77493_2_);
      }
   }

   static final class ModifierLiving implements EnchantmentHelper.IModifier {
      public float field_77495_a;
      public EnumCreatureAttribute field_77494_b;

      private ModifierLiving() {
      }

      public void func_77493_a(Enchantment p_77493_1_, int p_77493_2_) {
         this.field_77495_a += p_77493_1_.func_152376_a(p_77493_2_, this.field_77494_b);
      }
   }

   static final class ModifierDamage implements EnchantmentHelper.IModifier {
      public int field_77497_a;
      public DamageSource field_77496_b;

      private ModifierDamage() {
      }

      public void func_77493_a(Enchantment p_77493_1_, int p_77493_2_) {
         this.field_77497_a += p_77493_1_.func_77318_a(p_77493_2_, this.field_77496_b);
      }
   }

   interface IModifier {
      void func_77493_a(Enchantment var1, int var2);
   }
}
