package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPotion extends Item {
   private Map<Integer, List<PotionEffect>> field_77836_a = Maps.newHashMap();
   private static final Map<List<PotionEffect>, Integer> field_77835_b = Maps.newLinkedHashMap();

   public ItemPotion() {
      this.func_77625_d(1);
      this.func_77627_a(true);
      this.func_77656_e(0);
      this.func_77637_a(CreativeTabs.field_78038_k);
   }

   public List<PotionEffect> func_77832_l(ItemStack p_77832_1_) {
      if (p_77832_1_.func_77942_o() && p_77832_1_.func_77978_p().func_150297_b("CustomPotionEffects", 9)) {
         List<PotionEffect> lvt_2_2_ = Lists.newArrayList();
         NBTTagList lvt_3_1_ = p_77832_1_.func_77978_p().func_150295_c("CustomPotionEffects", 10);

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.func_74745_c(); ++lvt_4_1_) {
            NBTTagCompound lvt_5_1_ = lvt_3_1_.func_150305_b(lvt_4_1_);
            PotionEffect lvt_6_1_ = PotionEffect.func_82722_b(lvt_5_1_);
            if (lvt_6_1_ != null) {
               lvt_2_2_.add(lvt_6_1_);
            }
         }

         return lvt_2_2_;
      } else {
         List<PotionEffect> lvt_2_1_ = (List)this.field_77836_a.get(p_77832_1_.func_77960_j());
         if (lvt_2_1_ == null) {
            lvt_2_1_ = PotionHelper.func_77917_b(p_77832_1_.func_77960_j(), false);
            this.field_77836_a.put(p_77832_1_.func_77960_j(), lvt_2_1_);
         }

         return lvt_2_1_;
      }
   }

   public List<PotionEffect> func_77834_f(int p_77834_1_) {
      List<PotionEffect> lvt_2_1_ = (List)this.field_77836_a.get(p_77834_1_);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = PotionHelper.func_77917_b(p_77834_1_, false);
         this.field_77836_a.put(p_77834_1_, lvt_2_1_);
      }

      return lvt_2_1_;
   }

   public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      if (!p_77654_3_.field_71075_bZ.field_75098_d) {
         --p_77654_1_.field_77994_a;
      }

      if (!p_77654_2_.field_72995_K) {
         List<PotionEffect> lvt_4_1_ = this.func_77832_l(p_77654_1_);
         if (lvt_4_1_ != null) {
            Iterator lvt_5_1_ = lvt_4_1_.iterator();

            while(lvt_5_1_.hasNext()) {
               PotionEffect lvt_6_1_ = (PotionEffect)lvt_5_1_.next();
               p_77654_3_.func_70690_d(new PotionEffect(lvt_6_1_));
            }
         }
      }

      p_77654_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
      if (!p_77654_3_.field_71075_bZ.field_75098_d) {
         if (p_77654_1_.field_77994_a <= 0) {
            return new ItemStack(Items.field_151069_bo);
         }

         p_77654_3_.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
      }

      return p_77654_1_;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 32;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.DRINK;
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if (func_77831_g(p_77659_1_.func_77960_j())) {
         if (!p_77659_3_.field_71075_bZ.field_75098_d) {
            --p_77659_1_.field_77994_a;
         }

         p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
         if (!p_77659_2_.field_72995_K) {
            p_77659_2_.func_72838_d(new EntityPotion(p_77659_2_, p_77659_3_, p_77659_1_));
         }

         p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
         return p_77659_1_;
      } else {
         p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
         return p_77659_1_;
      }
   }

   public static boolean func_77831_g(int p_77831_0_) {
      return (p_77831_0_ & 16384) != 0;
   }

   public int func_77620_a(int p_77620_1_) {
      return PotionHelper.func_77915_a(p_77620_1_, false);
   }

   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      return p_82790_2_ > 0 ? 16777215 : this.func_77620_a(p_82790_1_.func_77960_j());
   }

   public boolean func_77833_h(int p_77833_1_) {
      List<PotionEffect> lvt_2_1_ = this.func_77834_f(p_77833_1_);
      if (lvt_2_1_ != null && !lvt_2_1_.isEmpty()) {
         Iterator lvt_3_1_ = lvt_2_1_.iterator();

         PotionEffect lvt_4_1_;
         do {
            if (!lvt_3_1_.hasNext()) {
               return false;
            }

            lvt_4_1_ = (PotionEffect)lvt_3_1_.next();
         } while(!Potion.field_76425_a[lvt_4_1_.func_76456_a()].func_76403_b());

         return true;
      } else {
         return false;
      }
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      if (p_77653_1_.func_77960_j() == 0) {
         return StatCollector.func_74838_a("item.emptyPotion.name").trim();
      } else {
         String lvt_2_1_ = "";
         if (func_77831_g(p_77653_1_.func_77960_j())) {
            lvt_2_1_ = StatCollector.func_74838_a("potion.prefix.grenade").trim() + " ";
         }

         List<PotionEffect> lvt_3_1_ = Items.field_151068_bn.func_77832_l(p_77653_1_);
         String lvt_4_2_;
         if (lvt_3_1_ != null && !lvt_3_1_.isEmpty()) {
            lvt_4_2_ = ((PotionEffect)lvt_3_1_.get(0)).func_76453_d();
            lvt_4_2_ = lvt_4_2_ + ".postfix";
            return lvt_2_1_ + StatCollector.func_74838_a(lvt_4_2_).trim();
         } else {
            lvt_4_2_ = PotionHelper.func_77905_c(p_77653_1_.func_77960_j());
            return StatCollector.func_74838_a(lvt_4_2_).trim() + " " + super.func_77653_i(p_77653_1_);
         }
      }
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      if (p_77624_1_.func_77960_j() != 0) {
         List<PotionEffect> lvt_5_1_ = Items.field_151068_bn.func_77832_l(p_77624_1_);
         Multimap<String, AttributeModifier> lvt_6_1_ = HashMultimap.create();
         Iterator lvt_7_3_;
         if (lvt_5_1_ != null && !lvt_5_1_.isEmpty()) {
            lvt_7_3_ = lvt_5_1_.iterator();

            while(lvt_7_3_.hasNext()) {
               PotionEffect lvt_8_1_ = (PotionEffect)lvt_7_3_.next();
               String lvt_9_1_ = StatCollector.func_74838_a(lvt_8_1_.func_76453_d()).trim();
               Potion lvt_10_1_ = Potion.field_76425_a[lvt_8_1_.func_76456_a()];
               Map<IAttribute, AttributeModifier> lvt_11_1_ = lvt_10_1_.func_111186_k();
               if (lvt_11_1_ != null && lvt_11_1_.size() > 0) {
                  Iterator lvt_12_1_ = lvt_11_1_.entrySet().iterator();

                  while(lvt_12_1_.hasNext()) {
                     Entry<IAttribute, AttributeModifier> lvt_13_1_ = (Entry)lvt_12_1_.next();
                     AttributeModifier lvt_14_1_ = (AttributeModifier)lvt_13_1_.getValue();
                     AttributeModifier lvt_15_1_ = new AttributeModifier(lvt_14_1_.func_111166_b(), lvt_10_1_.func_111183_a(lvt_8_1_.func_76458_c(), lvt_14_1_), lvt_14_1_.func_111169_c());
                     lvt_6_1_.put(((IAttribute)lvt_13_1_.getKey()).func_111108_a(), lvt_15_1_);
                  }
               }

               if (lvt_8_1_.func_76458_c() > 0) {
                  lvt_9_1_ = lvt_9_1_ + " " + StatCollector.func_74838_a("potion.potency." + lvt_8_1_.func_76458_c()).trim();
               }

               if (lvt_8_1_.func_76459_b() > 20) {
                  lvt_9_1_ = lvt_9_1_ + " (" + Potion.func_76389_a(lvt_8_1_) + ")";
               }

               if (lvt_10_1_.func_76398_f()) {
                  p_77624_3_.add(EnumChatFormatting.RED + lvt_9_1_);
               } else {
                  p_77624_3_.add(EnumChatFormatting.GRAY + lvt_9_1_);
               }
            }
         } else {
            String lvt_7_2_ = StatCollector.func_74838_a("potion.empty").trim();
            p_77624_3_.add(EnumChatFormatting.GRAY + lvt_7_2_);
         }

         if (!lvt_6_1_.isEmpty()) {
            p_77624_3_.add("");
            p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("potion.effects.whenDrank"));
            lvt_7_3_ = lvt_6_1_.entries().iterator();

            while(lvt_7_3_.hasNext()) {
               Entry<String, AttributeModifier> lvt_8_2_ = (Entry)lvt_7_3_.next();
               AttributeModifier lvt_9_2_ = (AttributeModifier)lvt_8_2_.getValue();
               double lvt_10_2_ = lvt_9_2_.func_111164_d();
               double lvt_12_3_;
               if (lvt_9_2_.func_111169_c() != 1 && lvt_9_2_.func_111169_c() != 2) {
                  lvt_12_3_ = lvt_9_2_.func_111164_d();
               } else {
                  lvt_12_3_ = lvt_9_2_.func_111164_d() * 100.0D;
               }

               if (lvt_10_2_ > 0.0D) {
                  p_77624_3_.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + lvt_9_2_.func_111169_c(), ItemStack.field_111284_a.format(lvt_12_3_), StatCollector.func_74838_a("attribute.name." + (String)lvt_8_2_.getKey())));
               } else if (lvt_10_2_ < 0.0D) {
                  lvt_12_3_ *= -1.0D;
                  p_77624_3_.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + lvt_9_2_.func_111169_c(), ItemStack.field_111284_a.format(lvt_12_3_), StatCollector.func_74838_a("attribute.name." + (String)lvt_8_2_.getKey())));
               }
            }
         }

      }
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      List<PotionEffect> lvt_2_1_ = this.func_77832_l(p_77636_1_);
      return lvt_2_1_ != null && !lvt_2_1_.isEmpty();
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      super.func_150895_a(p_150895_1_, p_150895_2_, p_150895_3_);
      int lvt_5_1_;
      if (field_77835_b.isEmpty()) {
         for(int lvt_4_1_ = 0; lvt_4_1_ <= 15; ++lvt_4_1_) {
            for(lvt_5_1_ = 0; lvt_5_1_ <= 1; ++lvt_5_1_) {
               int lvt_6_1_;
               if (lvt_5_1_ == 0) {
                  lvt_6_1_ = lvt_4_1_ | 8192;
               } else {
                  lvt_6_1_ = lvt_4_1_ | 16384;
               }

               for(int lvt_7_1_ = 0; lvt_7_1_ <= 2; ++lvt_7_1_) {
                  int lvt_8_1_ = lvt_6_1_;
                  if (lvt_7_1_ != 0) {
                     if (lvt_7_1_ == 1) {
                        lvt_8_1_ = lvt_6_1_ | 32;
                     } else if (lvt_7_1_ == 2) {
                        lvt_8_1_ = lvt_6_1_ | 64;
                     }
                  }

                  List<PotionEffect> lvt_9_1_ = PotionHelper.func_77917_b(lvt_8_1_, false);
                  if (lvt_9_1_ != null && !lvt_9_1_.isEmpty()) {
                     field_77835_b.put(lvt_9_1_, lvt_8_1_);
                  }
               }
            }
         }
      }

      Iterator lvt_4_2_ = field_77835_b.values().iterator();

      while(lvt_4_2_.hasNext()) {
         lvt_5_1_ = (Integer)lvt_4_2_.next();
         p_150895_3_.add(new ItemStack(p_150895_1_, 1, lvt_5_1_));
      }

   }
}
