package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public final class ItemStack {
   public static final DecimalFormat field_111284_a = new DecimalFormat("#.###");
   public int field_77994_a;
   public int field_77992_b;
   private Item field_151002_e;
   private NBTTagCompound field_77990_d;
   private int field_77991_e;
   private EntityItemFrame field_82843_f;
   private Block field_179552_h;
   private boolean field_179553_i;
   private Block field_179550_j;
   private boolean field_179551_k;

   public ItemStack(Block p_i1876_1_) {
      this((Block)p_i1876_1_, 1);
   }

   public ItemStack(Block p_i1877_1_, int p_i1877_2_) {
      this((Block)p_i1877_1_, p_i1877_2_, 0);
   }

   public ItemStack(Block p_i1878_1_, int p_i1878_2_, int p_i1878_3_) {
      this(Item.func_150898_a(p_i1878_1_), p_i1878_2_, p_i1878_3_);
   }

   public ItemStack(Item p_i1879_1_) {
      this((Item)p_i1879_1_, 1);
   }

   public ItemStack(Item p_i1880_1_, int p_i1880_2_) {
      this((Item)p_i1880_1_, p_i1880_2_, 0);
   }

   public ItemStack(Item p_i1881_1_, int p_i1881_2_, int p_i1881_3_) {
      this.field_179552_h = null;
      this.field_179553_i = false;
      this.field_179550_j = null;
      this.field_179551_k = false;
      this.field_151002_e = p_i1881_1_;
      this.field_77994_a = p_i1881_2_;
      this.field_77991_e = p_i1881_3_;
      if (this.field_77991_e < 0) {
         this.field_77991_e = 0;
      }

   }

   public static ItemStack func_77949_a(NBTTagCompound p_77949_0_) {
      ItemStack lvt_1_1_ = new ItemStack();
      lvt_1_1_.func_77963_c(p_77949_0_);
      return lvt_1_1_.func_77973_b() != null ? lvt_1_1_ : null;
   }

   private ItemStack() {
      this.field_179552_h = null;
      this.field_179553_i = false;
      this.field_179550_j = null;
      this.field_179551_k = false;
   }

   public ItemStack func_77979_a(int p_77979_1_) {
      ItemStack lvt_2_1_ = new ItemStack(this.field_151002_e, p_77979_1_, this.field_77991_e);
      if (this.field_77990_d != null) {
         lvt_2_1_.field_77990_d = (NBTTagCompound)this.field_77990_d.func_74737_b();
      }

      this.field_77994_a -= p_77979_1_;
      return lvt_2_1_;
   }

   public Item func_77973_b() {
      return this.field_151002_e;
   }

   public boolean func_179546_a(EntityPlayer p_179546_1_, World p_179546_2_, BlockPos p_179546_3_, EnumFacing p_179546_4_, float p_179546_5_, float p_179546_6_, float p_179546_7_) {
      boolean lvt_8_1_ = this.func_77973_b().func_180614_a(this, p_179546_1_, p_179546_2_, p_179546_3_, p_179546_4_, p_179546_5_, p_179546_6_, p_179546_7_);
      if (lvt_8_1_) {
         p_179546_1_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)]);
      }

      return lvt_8_1_;
   }

   public float func_150997_a(Block p_150997_1_) {
      return this.func_77973_b().func_150893_a(this, p_150997_1_);
   }

   public ItemStack func_77957_a(World p_77957_1_, EntityPlayer p_77957_2_) {
      return this.func_77973_b().func_77659_a(this, p_77957_1_, p_77957_2_);
   }

   public ItemStack func_77950_b(World p_77950_1_, EntityPlayer p_77950_2_) {
      return this.func_77973_b().func_77654_b(this, p_77950_1_, p_77950_2_);
   }

   public NBTTagCompound func_77955_b(NBTTagCompound p_77955_1_) {
      ResourceLocation lvt_2_1_ = (ResourceLocation)Item.field_150901_e.func_177774_c(this.field_151002_e);
      p_77955_1_.func_74778_a("id", lvt_2_1_ == null ? "minecraft:air" : lvt_2_1_.toString());
      p_77955_1_.func_74774_a("Count", (byte)this.field_77994_a);
      p_77955_1_.func_74777_a("Damage", (short)this.field_77991_e);
      if (this.field_77990_d != null) {
         p_77955_1_.func_74782_a("tag", this.field_77990_d);
      }

      return p_77955_1_;
   }

   public void func_77963_c(NBTTagCompound p_77963_1_) {
      if (p_77963_1_.func_150297_b("id", 8)) {
         this.field_151002_e = Item.func_111206_d(p_77963_1_.func_74779_i("id"));
      } else {
         this.field_151002_e = Item.func_150899_d(p_77963_1_.func_74765_d("id"));
      }

      this.field_77994_a = p_77963_1_.func_74771_c("Count");
      this.field_77991_e = p_77963_1_.func_74765_d("Damage");
      if (this.field_77991_e < 0) {
         this.field_77991_e = 0;
      }

      if (p_77963_1_.func_150297_b("tag", 10)) {
         this.field_77990_d = p_77963_1_.func_74775_l("tag");
         if (this.field_151002_e != null) {
            this.field_151002_e.func_179215_a(this.field_77990_d);
         }
      }

   }

   public int func_77976_d() {
      return this.func_77973_b().func_77639_j();
   }

   public boolean func_77985_e() {
      return this.func_77976_d() > 1 && (!this.func_77984_f() || !this.func_77951_h());
   }

   public boolean func_77984_f() {
      if (this.field_151002_e == null) {
         return false;
      } else if (this.field_151002_e.func_77612_l() <= 0) {
         return false;
      } else {
         return !this.func_77942_o() || !this.func_77978_p().func_74767_n("Unbreakable");
      }
   }

   public boolean func_77981_g() {
      return this.field_151002_e.func_77614_k();
   }

   public boolean func_77951_h() {
      return this.func_77984_f() && this.field_77991_e > 0;
   }

   public int func_77952_i() {
      return this.field_77991_e;
   }

   public int func_77960_j() {
      return this.field_77991_e;
   }

   public void func_77964_b(int p_77964_1_) {
      this.field_77991_e = p_77964_1_;
      if (this.field_77991_e < 0) {
         this.field_77991_e = 0;
      }

   }

   public int func_77958_k() {
      return this.field_151002_e.func_77612_l();
   }

   public boolean func_96631_a(int p_96631_1_, Random p_96631_2_) {
      if (!this.func_77984_f()) {
         return false;
      } else {
         if (p_96631_1_ > 0) {
            int lvt_3_1_ = EnchantmentHelper.func_77506_a(Enchantment.field_77347_r.field_77352_x, this);
            int lvt_4_1_ = 0;

            for(int lvt_5_1_ = 0; lvt_3_1_ > 0 && lvt_5_1_ < p_96631_1_; ++lvt_5_1_) {
               if (EnchantmentDurability.func_92097_a(this, lvt_3_1_, p_96631_2_)) {
                  ++lvt_4_1_;
               }
            }

            p_96631_1_ -= lvt_4_1_;
            if (p_96631_1_ <= 0) {
               return false;
            }
         }

         this.field_77991_e += p_96631_1_;
         return this.field_77991_e > this.func_77958_k();
      }
   }

   public void func_77972_a(int p_77972_1_, EntityLivingBase p_77972_2_) {
      if (!(p_77972_2_ instanceof EntityPlayer) || !((EntityPlayer)p_77972_2_).field_71075_bZ.field_75098_d) {
         if (this.func_77984_f()) {
            if (this.func_96631_a(p_77972_1_, p_77972_2_.func_70681_au())) {
               p_77972_2_.func_70669_a(this);
               --this.field_77994_a;
               if (p_77972_2_ instanceof EntityPlayer) {
                  EntityPlayer lvt_3_1_ = (EntityPlayer)p_77972_2_;
                  lvt_3_1_.func_71029_a(StatList.field_75930_F[Item.func_150891_b(this.field_151002_e)]);
                  if (this.field_77994_a == 0 && this.func_77973_b() instanceof ItemBow) {
                     lvt_3_1_.func_71028_bD();
                  }
               }

               if (this.field_77994_a < 0) {
                  this.field_77994_a = 0;
               }

               this.field_77991_e = 0;
            }

         }
      }
   }

   public void func_77961_a(EntityLivingBase p_77961_1_, EntityPlayer p_77961_2_) {
      boolean lvt_3_1_ = this.field_151002_e.func_77644_a(this, p_77961_1_, p_77961_2_);
      if (lvt_3_1_) {
         p_77961_2_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)]);
      }

   }

   public void func_179548_a(World p_179548_1_, Block p_179548_2_, BlockPos p_179548_3_, EntityPlayer p_179548_4_) {
      boolean lvt_5_1_ = this.field_151002_e.func_179218_a(this, p_179548_1_, p_179548_2_, p_179548_3_, p_179548_4_);
      if (lvt_5_1_) {
         p_179548_4_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)]);
      }

   }

   public boolean func_150998_b(Block p_150998_1_) {
      return this.field_151002_e.func_150897_b(p_150998_1_);
   }

   public boolean func_111282_a(EntityPlayer p_111282_1_, EntityLivingBase p_111282_2_) {
      return this.field_151002_e.func_111207_a(this, p_111282_1_, p_111282_2_);
   }

   public ItemStack func_77946_l() {
      ItemStack lvt_1_1_ = new ItemStack(this.field_151002_e, this.field_77994_a, this.field_77991_e);
      if (this.field_77990_d != null) {
         lvt_1_1_.field_77990_d = (NBTTagCompound)this.field_77990_d.func_74737_b();
      }

      return lvt_1_1_;
   }

   public static boolean func_77970_a(ItemStack p_77970_0_, ItemStack p_77970_1_) {
      if (p_77970_0_ == null && p_77970_1_ == null) {
         return true;
      } else if (p_77970_0_ != null && p_77970_1_ != null) {
         if (p_77970_0_.field_77990_d == null && p_77970_1_.field_77990_d != null) {
            return false;
         } else {
            return p_77970_0_.field_77990_d == null || p_77970_0_.field_77990_d.equals(p_77970_1_.field_77990_d);
         }
      } else {
         return false;
      }
   }

   public static boolean func_77989_b(ItemStack p_77989_0_, ItemStack p_77989_1_) {
      if (p_77989_0_ == null && p_77989_1_ == null) {
         return true;
      } else {
         return p_77989_0_ != null && p_77989_1_ != null ? p_77989_0_.func_77959_d(p_77989_1_) : false;
      }
   }

   private boolean func_77959_d(ItemStack p_77959_1_) {
      if (this.field_77994_a != p_77959_1_.field_77994_a) {
         return false;
      } else if (this.field_151002_e != p_77959_1_.field_151002_e) {
         return false;
      } else if (this.field_77991_e != p_77959_1_.field_77991_e) {
         return false;
      } else if (this.field_77990_d == null && p_77959_1_.field_77990_d != null) {
         return false;
      } else {
         return this.field_77990_d == null || this.field_77990_d.equals(p_77959_1_.field_77990_d);
      }
   }

   public static boolean func_179545_c(ItemStack p_179545_0_, ItemStack p_179545_1_) {
      if (p_179545_0_ == null && p_179545_1_ == null) {
         return true;
      } else {
         return p_179545_0_ != null && p_179545_1_ != null ? p_179545_0_.func_77969_a(p_179545_1_) : false;
      }
   }

   public boolean func_77969_a(ItemStack p_77969_1_) {
      return p_77969_1_ != null && this.field_151002_e == p_77969_1_.field_151002_e && this.field_77991_e == p_77969_1_.field_77991_e;
   }

   public String func_77977_a() {
      return this.field_151002_e.func_77667_c(this);
   }

   public static ItemStack func_77944_b(ItemStack p_77944_0_) {
      return p_77944_0_ == null ? null : p_77944_0_.func_77946_l();
   }

   public String toString() {
      return this.field_77994_a + "x" + this.field_151002_e.func_77658_a() + "@" + this.field_77991_e;
   }

   public void func_77945_a(World p_77945_1_, Entity p_77945_2_, int p_77945_3_, boolean p_77945_4_) {
      if (this.field_77992_b > 0) {
         --this.field_77992_b;
      }

      this.field_151002_e.func_77663_a(this, p_77945_1_, p_77945_2_, p_77945_3_, p_77945_4_);
   }

   public void func_77980_a(World p_77980_1_, EntityPlayer p_77980_2_, int p_77980_3_) {
      p_77980_2_.func_71064_a(StatList.field_75928_D[Item.func_150891_b(this.field_151002_e)], p_77980_3_);
      this.field_151002_e.func_77622_d(this, p_77980_1_, p_77980_2_);
   }

   public boolean func_179549_c(ItemStack p_179549_1_) {
      return this.func_77959_d(p_179549_1_);
   }

   public int func_77988_m() {
      return this.func_77973_b().func_77626_a(this);
   }

   public EnumAction func_77975_n() {
      return this.func_77973_b().func_77661_b(this);
   }

   public void func_77974_b(World p_77974_1_, EntityPlayer p_77974_2_, int p_77974_3_) {
      this.func_77973_b().func_77615_a(this, p_77974_1_, p_77974_2_, p_77974_3_);
   }

   public boolean func_77942_o() {
      return this.field_77990_d != null;
   }

   public NBTTagCompound func_77978_p() {
      return this.field_77990_d;
   }

   public NBTTagCompound func_179543_a(String p_179543_1_, boolean p_179543_2_) {
      if (this.field_77990_d != null && this.field_77990_d.func_150297_b(p_179543_1_, 10)) {
         return this.field_77990_d.func_74775_l(p_179543_1_);
      } else if (p_179543_2_) {
         NBTTagCompound lvt_3_1_ = new NBTTagCompound();
         this.func_77983_a(p_179543_1_, lvt_3_1_);
         return lvt_3_1_;
      } else {
         return null;
      }
   }

   public NBTTagList func_77986_q() {
      return this.field_77990_d == null ? null : this.field_77990_d.func_150295_c("ench", 10);
   }

   public void func_77982_d(NBTTagCompound p_77982_1_) {
      this.field_77990_d = p_77982_1_;
   }

   public String func_82833_r() {
      String lvt_1_1_ = this.func_77973_b().func_77653_i(this);
      if (this.field_77990_d != null && this.field_77990_d.func_150297_b("display", 10)) {
         NBTTagCompound lvt_2_1_ = this.field_77990_d.func_74775_l("display");
         if (lvt_2_1_.func_150297_b("Name", 8)) {
            lvt_1_1_ = lvt_2_1_.func_74779_i("Name");
         }
      }

      return lvt_1_1_;
   }

   public ItemStack func_151001_c(String p_151001_1_) {
      if (this.field_77990_d == null) {
         this.field_77990_d = new NBTTagCompound();
      }

      if (!this.field_77990_d.func_150297_b("display", 10)) {
         this.field_77990_d.func_74782_a("display", new NBTTagCompound());
      }

      this.field_77990_d.func_74775_l("display").func_74778_a("Name", p_151001_1_);
      return this;
   }

   public void func_135074_t() {
      if (this.field_77990_d != null) {
         if (this.field_77990_d.func_150297_b("display", 10)) {
            NBTTagCompound lvt_1_1_ = this.field_77990_d.func_74775_l("display");
            lvt_1_1_.func_82580_o("Name");
            if (lvt_1_1_.func_82582_d()) {
               this.field_77990_d.func_82580_o("display");
               if (this.field_77990_d.func_82582_d()) {
                  this.func_77982_d((NBTTagCompound)null);
               }
            }

         }
      }
   }

   public boolean func_82837_s() {
      if (this.field_77990_d == null) {
         return false;
      } else {
         return !this.field_77990_d.func_150297_b("display", 10) ? false : this.field_77990_d.func_74775_l("display").func_150297_b("Name", 8);
      }
   }

   public List<String> func_82840_a(EntityPlayer p_82840_1_, boolean p_82840_2_) {
      List<String> lvt_3_1_ = Lists.newArrayList();
      String lvt_4_1_ = this.func_82833_r();
      if (this.func_82837_s()) {
         lvt_4_1_ = EnumChatFormatting.ITALIC + lvt_4_1_;
      }

      lvt_4_1_ = lvt_4_1_ + EnumChatFormatting.RESET;
      if (p_82840_2_) {
         String lvt_5_1_ = "";
         if (lvt_4_1_.length() > 0) {
            lvt_4_1_ = lvt_4_1_ + " (";
            lvt_5_1_ = ")";
         }

         int lvt_6_1_ = Item.func_150891_b(this.field_151002_e);
         if (this.func_77981_g()) {
            lvt_4_1_ = lvt_4_1_ + String.format("#%04d/%d%s", lvt_6_1_, this.field_77991_e, lvt_5_1_);
         } else {
            lvt_4_1_ = lvt_4_1_ + String.format("#%04d%s", lvt_6_1_, lvt_5_1_);
         }
      } else if (!this.func_82837_s() && this.field_151002_e == Items.field_151098_aY) {
         lvt_4_1_ = lvt_4_1_ + " #" + this.field_77991_e;
      }

      lvt_3_1_.add(lvt_4_1_);
      int lvt_5_2_ = 0;
      if (this.func_77942_o() && this.field_77990_d.func_150297_b("HideFlags", 99)) {
         lvt_5_2_ = this.field_77990_d.func_74762_e("HideFlags");
      }

      if ((lvt_5_2_ & 32) == 0) {
         this.field_151002_e.func_77624_a(this, p_82840_1_, lvt_3_1_, p_82840_2_);
      }

      NBTTagList lvt_7_5_;
      int lvt_8_5_;
      if (this.func_77942_o()) {
         if ((lvt_5_2_ & 1) == 0) {
            NBTTagList lvt_6_2_ = this.func_77986_q();
            if (lvt_6_2_ != null) {
               for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_2_.func_74745_c(); ++lvt_7_1_) {
                  int lvt_8_1_ = lvt_6_2_.func_150305_b(lvt_7_1_).func_74765_d("id");
                  int lvt_9_1_ = lvt_6_2_.func_150305_b(lvt_7_1_).func_74765_d("lvl");
                  if (Enchantment.func_180306_c(lvt_8_1_) != null) {
                     lvt_3_1_.add(Enchantment.func_180306_c(lvt_8_1_).func_77316_c(lvt_9_1_));
                  }
               }
            }
         }

         if (this.field_77990_d.func_150297_b("display", 10)) {
            NBTTagCompound lvt_6_3_ = this.field_77990_d.func_74775_l("display");
            if (lvt_6_3_.func_150297_b("color", 3)) {
               if (p_82840_2_) {
                  lvt_3_1_.add("Color: #" + Integer.toHexString(lvt_6_3_.func_74762_e("color")).toUpperCase());
               } else {
                  lvt_3_1_.add(EnumChatFormatting.ITALIC + StatCollector.func_74838_a("item.dyed"));
               }
            }

            if (lvt_6_3_.func_150299_b("Lore") == 9) {
               lvt_7_5_ = lvt_6_3_.func_150295_c("Lore", 8);
               if (lvt_7_5_.func_74745_c() > 0) {
                  for(lvt_8_5_ = 0; lvt_8_5_ < lvt_7_5_.func_74745_c(); ++lvt_8_5_) {
                     lvt_3_1_.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + lvt_7_5_.func_150307_f(lvt_8_5_));
                  }
               }
            }
         }
      }

      Multimap<String, AttributeModifier> lvt_6_4_ = this.func_111283_C();
      if (!lvt_6_4_.isEmpty() && (lvt_5_2_ & 2) == 0) {
         lvt_3_1_.add("");
         Iterator lvt_7_3_ = lvt_6_4_.entries().iterator();

         while(lvt_7_3_.hasNext()) {
            Entry<String, AttributeModifier> lvt_8_3_ = (Entry)lvt_7_3_.next();
            AttributeModifier lvt_9_2_ = (AttributeModifier)lvt_8_3_.getValue();
            double lvt_10_1_ = lvt_9_2_.func_111164_d();
            if (lvt_9_2_.func_111167_a() == Item.field_111210_e) {
               lvt_10_1_ += (double)EnchantmentHelper.func_152377_a(this, EnumCreatureAttribute.UNDEFINED);
            }

            double lvt_12_2_;
            if (lvt_9_2_.func_111169_c() != 1 && lvt_9_2_.func_111169_c() != 2) {
               lvt_12_2_ = lvt_10_1_;
            } else {
               lvt_12_2_ = lvt_10_1_ * 100.0D;
            }

            if (lvt_10_1_ > 0.0D) {
               lvt_3_1_.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + lvt_9_2_.func_111169_c(), field_111284_a.format(lvt_12_2_), StatCollector.func_74838_a("attribute.name." + (String)lvt_8_3_.getKey())));
            } else if (lvt_10_1_ < 0.0D) {
               lvt_12_2_ *= -1.0D;
               lvt_3_1_.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + lvt_9_2_.func_111169_c(), field_111284_a.format(lvt_12_2_), StatCollector.func_74838_a("attribute.name." + (String)lvt_8_3_.getKey())));
            }
         }
      }

      if (this.func_77942_o() && this.func_77978_p().func_74767_n("Unbreakable") && (lvt_5_2_ & 4) == 0) {
         lvt_3_1_.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a("item.unbreakable"));
      }

      Block lvt_9_4_;
      if (this.func_77942_o() && this.field_77990_d.func_150297_b("CanDestroy", 9) && (lvt_5_2_ & 8) == 0) {
         lvt_7_5_ = this.field_77990_d.func_150295_c("CanDestroy", 8);
         if (lvt_7_5_.func_74745_c() > 0) {
            lvt_3_1_.add("");
            lvt_3_1_.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("item.canBreak"));

            for(lvt_8_5_ = 0; lvt_8_5_ < lvt_7_5_.func_74745_c(); ++lvt_8_5_) {
               lvt_9_4_ = Block.func_149684_b(lvt_7_5_.func_150307_f(lvt_8_5_));
               if (lvt_9_4_ != null) {
                  lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + lvt_9_4_.func_149732_F());
               } else {
                  lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (this.func_77942_o() && this.field_77990_d.func_150297_b("CanPlaceOn", 9) && (lvt_5_2_ & 16) == 0) {
         lvt_7_5_ = this.field_77990_d.func_150295_c("CanPlaceOn", 8);
         if (lvt_7_5_.func_74745_c() > 0) {
            lvt_3_1_.add("");
            lvt_3_1_.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("item.canPlace"));

            for(lvt_8_5_ = 0; lvt_8_5_ < lvt_7_5_.func_74745_c(); ++lvt_8_5_) {
               lvt_9_4_ = Block.func_149684_b(lvt_7_5_.func_150307_f(lvt_8_5_));
               if (lvt_9_4_ != null) {
                  lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + lvt_9_4_.func_149732_F());
               } else {
                  lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (p_82840_2_) {
         if (this.func_77951_h()) {
            lvt_3_1_.add("Durability: " + (this.func_77958_k() - this.func_77952_i()) + " / " + this.func_77958_k());
         }

         lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + ((ResourceLocation)Item.field_150901_e.func_177774_c(this.field_151002_e)).toString());
         if (this.func_77942_o()) {
            lvt_3_1_.add(EnumChatFormatting.DARK_GRAY + "NBT: " + this.func_77978_p().func_150296_c().size() + " tag(s)");
         }
      }

      return lvt_3_1_;
   }

   public boolean func_77962_s() {
      return this.func_77973_b().func_77636_d(this);
   }

   public EnumRarity func_77953_t() {
      return this.func_77973_b().func_77613_e(this);
   }

   public boolean func_77956_u() {
      if (!this.func_77973_b().func_77616_k(this)) {
         return false;
      } else {
         return !this.func_77948_v();
      }
   }

   public void func_77966_a(Enchantment p_77966_1_, int p_77966_2_) {
      if (this.field_77990_d == null) {
         this.func_77982_d(new NBTTagCompound());
      }

      if (!this.field_77990_d.func_150297_b("ench", 9)) {
         this.field_77990_d.func_74782_a("ench", new NBTTagList());
      }

      NBTTagList lvt_3_1_ = this.field_77990_d.func_150295_c("ench", 10);
      NBTTagCompound lvt_4_1_ = new NBTTagCompound();
      lvt_4_1_.func_74777_a("id", (short)p_77966_1_.field_77352_x);
      lvt_4_1_.func_74777_a("lvl", (short)((byte)p_77966_2_));
      lvt_3_1_.func_74742_a(lvt_4_1_);
   }

   public boolean func_77948_v() {
      return this.field_77990_d != null && this.field_77990_d.func_150297_b("ench", 9);
   }

   public void func_77983_a(String p_77983_1_, NBTBase p_77983_2_) {
      if (this.field_77990_d == null) {
         this.func_77982_d(new NBTTagCompound());
      }

      this.field_77990_d.func_74782_a(p_77983_1_, p_77983_2_);
   }

   public boolean func_82835_x() {
      return this.func_77973_b().func_82788_x();
   }

   public boolean func_82839_y() {
      return this.field_82843_f != null;
   }

   public void func_82842_a(EntityItemFrame p_82842_1_) {
      this.field_82843_f = p_82842_1_;
   }

   public EntityItemFrame func_82836_z() {
      return this.field_82843_f;
   }

   public int func_82838_A() {
      return this.func_77942_o() && this.field_77990_d.func_150297_b("RepairCost", 3) ? this.field_77990_d.func_74762_e("RepairCost") : 0;
   }

   public void func_82841_c(int p_82841_1_) {
      if (!this.func_77942_o()) {
         this.field_77990_d = new NBTTagCompound();
      }

      this.field_77990_d.func_74768_a("RepairCost", p_82841_1_);
   }

   public Multimap<String, AttributeModifier> func_111283_C() {
      Object lvt_1_1_;
      if (this.func_77942_o() && this.field_77990_d.func_150297_b("AttributeModifiers", 9)) {
         lvt_1_1_ = HashMultimap.create();
         NBTTagList lvt_2_1_ = this.field_77990_d.func_150295_c("AttributeModifiers", 10);

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
            NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
            AttributeModifier lvt_5_1_ = SharedMonsterAttributes.func_111259_a(lvt_4_1_);
            if (lvt_5_1_ != null && lvt_5_1_.func_111167_a().getLeastSignificantBits() != 0L && lvt_5_1_.func_111167_a().getMostSignificantBits() != 0L) {
               ((Multimap)lvt_1_1_).put(lvt_4_1_.func_74779_i("AttributeName"), lvt_5_1_);
            }
         }
      } else {
         lvt_1_1_ = this.func_77973_b().func_111205_h();
      }

      return (Multimap)lvt_1_1_;
   }

   public void func_150996_a(Item p_150996_1_) {
      this.field_151002_e = p_150996_1_;
   }

   public IChatComponent func_151000_E() {
      ChatComponentText lvt_1_1_ = new ChatComponentText(this.func_82833_r());
      if (this.func_82837_s()) {
         lvt_1_1_.func_150256_b().func_150217_b(true);
      }

      IChatComponent lvt_2_1_ = (new ChatComponentText("[")).func_150257_a(lvt_1_1_).func_150258_a("]");
      if (this.field_151002_e != null) {
         NBTTagCompound lvt_3_1_ = new NBTTagCompound();
         this.func_77955_b(lvt_3_1_);
         lvt_2_1_.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ChatComponentText(lvt_3_1_.toString())));
         lvt_2_1_.func_150256_b().func_150238_a(this.func_77953_t().field_77937_e);
      }

      return lvt_2_1_;
   }

   public boolean func_179544_c(Block p_179544_1_) {
      if (p_179544_1_ == this.field_179552_h) {
         return this.field_179553_i;
      } else {
         this.field_179552_h = p_179544_1_;
         if (this.func_77942_o() && this.field_77990_d.func_150297_b("CanDestroy", 9)) {
            NBTTagList lvt_2_1_ = this.field_77990_d.func_150295_c("CanDestroy", 8);

            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
               Block lvt_4_1_ = Block.func_149684_b(lvt_2_1_.func_150307_f(lvt_3_1_));
               if (lvt_4_1_ == p_179544_1_) {
                  this.field_179553_i = true;
                  return true;
               }
            }
         }

         this.field_179553_i = false;
         return false;
      }
   }

   public boolean func_179547_d(Block p_179547_1_) {
      if (p_179547_1_ == this.field_179550_j) {
         return this.field_179551_k;
      } else {
         this.field_179550_j = p_179547_1_;
         if (this.func_77942_o() && this.field_77990_d.func_150297_b("CanPlaceOn", 9)) {
            NBTTagList lvt_2_1_ = this.field_77990_d.func_150295_c("CanPlaceOn", 8);

            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
               Block lvt_4_1_ = Block.func_149684_b(lvt_2_1_.func_150307_f(lvt_3_1_));
               if (lvt_4_1_ == p_179547_1_) {
                  this.field_179551_k = true;
                  return true;
               }
            }
         }

         this.field_179551_k = false;
         return false;
      }
   }
}
