package net.minecraft.client.gui.inventory;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiContainerCreative extends InventoryEffectRenderer {
   private static final ResourceLocation field_147061_u = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
   private static int field_147058_w;
   private float field_147067_x;
   private boolean field_147066_y;
   private boolean field_147065_z;
   private GuiTextField field_147062_A;
   private List<Slot> field_147063_B;
   private Slot field_147064_C;
   private boolean field_147057_D;
   private CreativeCrafting field_147059_E;

   public GuiContainerCreative(EntityPlayer p_i1088_1_) {
      super(new GuiContainerCreative.ContainerCreative(p_i1088_1_));
      p_i1088_1_.field_71070_bA = this.field_147002_h;
      this.field_146291_p = true;
      this.field_147000_g = 136;
      this.field_146999_f = 195;
   }

   public void func_73876_c() {
      if (!this.field_146297_k.field_71442_b.func_78758_h()) {
         this.field_146297_k.func_147108_a(new GuiInventory(this.field_146297_k.field_71439_g));
      }

      this.func_175378_g();
   }

   protected void func_146984_a(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
      this.field_147057_D = true;
      boolean lvt_5_1_ = p_146984_4_ == 1;
      p_146984_4_ = p_146984_2_ == -999 && p_146984_4_ == 0 ? 4 : p_146984_4_;
      ItemStack lvt_7_1_;
      InventoryPlayer lvt_6_3_;
      if (p_146984_1_ == null && field_147058_w != CreativeTabs.field_78036_m.func_78021_a() && p_146984_4_ != 5) {
         lvt_6_3_ = this.field_146297_k.field_71439_g.field_71071_by;
         if (lvt_6_3_.func_70445_o() != null) {
            if (p_146984_3_ == 0) {
               this.field_146297_k.field_71439_g.func_71019_a(lvt_6_3_.func_70445_o(), true);
               this.field_146297_k.field_71442_b.func_78752_a(lvt_6_3_.func_70445_o());
               lvt_6_3_.func_70437_b((ItemStack)null);
            }

            if (p_146984_3_ == 1) {
               lvt_7_1_ = lvt_6_3_.func_70445_o().func_77979_a(1);
               this.field_146297_k.field_71439_g.func_71019_a(lvt_7_1_, true);
               this.field_146297_k.field_71442_b.func_78752_a(lvt_7_1_);
               if (lvt_6_3_.func_70445_o().field_77994_a == 0) {
                  lvt_6_3_.func_70437_b((ItemStack)null);
               }
            }
         }
      } else {
         int lvt_6_4_;
         if (p_146984_1_ == this.field_147064_C && lvt_5_1_) {
            for(lvt_6_4_ = 0; lvt_6_4_ < this.field_146297_k.field_71439_g.field_71069_bz.func_75138_a().size(); ++lvt_6_4_) {
               this.field_146297_k.field_71442_b.func_78761_a((ItemStack)null, lvt_6_4_);
            }
         } else {
            ItemStack lvt_6_5_;
            if (field_147058_w == CreativeTabs.field_78036_m.func_78021_a()) {
               if (p_146984_1_ == this.field_147064_C) {
                  this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
               } else if (p_146984_4_ == 4 && p_146984_1_ != null && p_146984_1_.func_75216_d()) {
                  lvt_6_5_ = p_146984_1_.func_75209_a(p_146984_3_ == 0 ? 1 : p_146984_1_.func_75211_c().func_77976_d());
                  this.field_146297_k.field_71439_g.func_71019_a(lvt_6_5_, true);
                  this.field_146297_k.field_71442_b.func_78752_a(lvt_6_5_);
               } else if (p_146984_4_ == 4 && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
                  this.field_146297_k.field_71439_g.func_71019_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o(), true);
                  this.field_146297_k.field_71442_b.func_78752_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o());
                  this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
               } else {
                  this.field_146297_k.field_71439_g.field_71069_bz.func_75144_a(p_146984_1_ == null ? p_146984_2_ : ((GuiContainerCreative.CreativeSlot)p_146984_1_).field_148332_b.field_75222_d, p_146984_3_, p_146984_4_, this.field_146297_k.field_71439_g);
                  this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
               }
            } else if (p_146984_4_ != 5 && p_146984_1_.field_75224_c == field_147060_v) {
               lvt_6_3_ = this.field_146297_k.field_71439_g.field_71071_by;
               lvt_7_1_ = lvt_6_3_.func_70445_o();
               ItemStack lvt_8_1_ = p_146984_1_.func_75211_c();
               ItemStack lvt_9_3_;
               if (p_146984_4_ == 2) {
                  if (lvt_8_1_ != null && p_146984_3_ >= 0 && p_146984_3_ < 9) {
                     lvt_9_3_ = lvt_8_1_.func_77946_l();
                     lvt_9_3_.field_77994_a = lvt_9_3_.func_77976_d();
                     this.field_146297_k.field_71439_g.field_71071_by.func_70299_a(p_146984_3_, lvt_9_3_);
                     this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
                  }

                  return;
               }

               if (p_146984_4_ == 3) {
                  if (lvt_6_3_.func_70445_o() == null && p_146984_1_.func_75216_d()) {
                     lvt_9_3_ = p_146984_1_.func_75211_c().func_77946_l();
                     lvt_9_3_.field_77994_a = lvt_9_3_.func_77976_d();
                     lvt_6_3_.func_70437_b(lvt_9_3_);
                  }

                  return;
               }

               if (p_146984_4_ == 4) {
                  if (lvt_8_1_ != null) {
                     lvt_9_3_ = lvt_8_1_.func_77946_l();
                     lvt_9_3_.field_77994_a = p_146984_3_ == 0 ? 1 : lvt_9_3_.func_77976_d();
                     this.field_146297_k.field_71439_g.func_71019_a(lvt_9_3_, true);
                     this.field_146297_k.field_71442_b.func_78752_a(lvt_9_3_);
                  }

                  return;
               }

               if (lvt_7_1_ != null && lvt_8_1_ != null && lvt_7_1_.func_77969_a(lvt_8_1_)) {
                  if (p_146984_3_ == 0) {
                     if (lvt_5_1_) {
                        lvt_7_1_.field_77994_a = lvt_7_1_.func_77976_d();
                     } else if (lvt_7_1_.field_77994_a < lvt_7_1_.func_77976_d()) {
                        ++lvt_7_1_.field_77994_a;
                     }
                  } else if (lvt_7_1_.field_77994_a <= 1) {
                     lvt_6_3_.func_70437_b((ItemStack)null);
                  } else {
                     --lvt_7_1_.field_77994_a;
                  }
               } else if (lvt_8_1_ != null && lvt_7_1_ == null) {
                  lvt_6_3_.func_70437_b(ItemStack.func_77944_b(lvt_8_1_));
                  lvt_7_1_ = lvt_6_3_.func_70445_o();
                  if (lvt_5_1_) {
                     lvt_7_1_.field_77994_a = lvt_7_1_.func_77976_d();
                  }
               } else {
                  lvt_6_3_.func_70437_b((ItemStack)null);
               }
            } else {
               this.field_147002_h.func_75144_a(p_146984_1_ == null ? p_146984_2_ : p_146984_1_.field_75222_d, p_146984_3_, p_146984_4_, this.field_146297_k.field_71439_g);
               if (Container.func_94532_c(p_146984_3_) == 2) {
                  for(lvt_6_4_ = 0; lvt_6_4_ < 9; ++lvt_6_4_) {
                     this.field_146297_k.field_71442_b.func_78761_a(this.field_147002_h.func_75139_a(45 + lvt_6_4_).func_75211_c(), 36 + lvt_6_4_);
                  }
               } else if (p_146984_1_ != null) {
                  lvt_6_5_ = this.field_147002_h.func_75139_a(p_146984_1_.field_75222_d).func_75211_c();
                  this.field_146297_k.field_71442_b.func_78761_a(lvt_6_5_, p_146984_1_.field_75222_d - this.field_147002_h.field_75151_b.size() + 9 + 36);
               }
            }
         }
      }

   }

   protected void func_175378_g() {
      int lvt_1_1_ = this.field_147003_i;
      super.func_175378_g();
      if (this.field_147062_A != null && this.field_147003_i != lvt_1_1_) {
         this.field_147062_A.field_146209_f = this.field_147003_i + 82;
      }

   }

   public void func_73866_w_() {
      if (this.field_146297_k.field_71442_b.func_78758_h()) {
         super.func_73866_w_();
         this.field_146292_n.clear();
         Keyboard.enableRepeatEvents(true);
         this.field_147062_A = new GuiTextField(0, this.field_146289_q, this.field_147003_i + 82, this.field_147009_r + 6, 89, this.field_146289_q.field_78288_b);
         this.field_147062_A.func_146203_f(15);
         this.field_147062_A.func_146185_a(false);
         this.field_147062_A.func_146189_e(false);
         this.field_147062_A.func_146193_g(16777215);
         int lvt_1_1_ = field_147058_w;
         field_147058_w = -1;
         this.func_147050_b(CreativeTabs.field_78032_a[lvt_1_1_]);
         this.field_147059_E = new CreativeCrafting(this.field_146297_k);
         this.field_146297_k.field_71439_g.field_71069_bz.func_75132_a(this.field_147059_E);
      } else {
         this.field_146297_k.func_147108_a(new GuiInventory(this.field_146297_k.field_71439_g));
      }

   }

   public void func_146281_b() {
      super.func_146281_b();
      if (this.field_146297_k.field_71439_g != null && this.field_146297_k.field_71439_g.field_71071_by != null) {
         this.field_146297_k.field_71439_g.field_71069_bz.func_82847_b(this.field_147059_E);
      }

      Keyboard.enableRepeatEvents(false);
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (field_147058_w != CreativeTabs.field_78027_g.func_78021_a()) {
         if (GameSettings.func_100015_a(this.field_146297_k.field_71474_y.field_74321_H)) {
            this.func_147050_b(CreativeTabs.field_78027_g);
         } else {
            super.func_73869_a(p_73869_1_, p_73869_2_);
         }

      } else {
         if (this.field_147057_D) {
            this.field_147057_D = false;
            this.field_147062_A.func_146180_a("");
         }

         if (!this.func_146983_a(p_73869_2_)) {
            if (this.field_147062_A.func_146201_a(p_73869_1_, p_73869_2_)) {
               this.func_147053_i();
            } else {
               super.func_73869_a(p_73869_1_, p_73869_2_);
            }

         }
      }
   }

   private void func_147053_i() {
      GuiContainerCreative.ContainerCreative lvt_1_1_ = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
      lvt_1_1_.field_148330_a.clear();
      Iterator lvt_2_3_ = Item.field_150901_e.iterator();

      while(lvt_2_3_.hasNext()) {
         Item lvt_3_1_ = (Item)lvt_2_3_.next();
         if (lvt_3_1_ != null && lvt_3_1_.func_77640_w() != null) {
            lvt_3_1_.func_150895_a(lvt_3_1_, (CreativeTabs)null, lvt_1_1_.field_148330_a);
         }
      }

      Enchantment[] lvt_2_2_ = Enchantment.field_77331_b;
      int lvt_3_2_ = lvt_2_2_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_2_; ++lvt_4_1_) {
         Enchantment lvt_5_1_ = lvt_2_2_[lvt_4_1_];
         if (lvt_5_1_ != null && lvt_5_1_.field_77351_y != null) {
            Items.field_151134_bR.func_92113_a(lvt_5_1_, lvt_1_1_.field_148330_a);
         }
      }

      lvt_2_3_ = lvt_1_1_.field_148330_a.iterator();
      String lvt_3_3_ = this.field_147062_A.func_146179_b().toLowerCase();

      while(lvt_2_3_.hasNext()) {
         ItemStack lvt_4_2_ = (ItemStack)lvt_2_3_.next();
         boolean lvt_5_2_ = false;
         Iterator lvt_6_1_ = lvt_4_2_.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x).iterator();

         while(lvt_6_1_.hasNext()) {
            String lvt_7_1_ = (String)lvt_6_1_.next();
            if (EnumChatFormatting.func_110646_a(lvt_7_1_).toLowerCase().contains(lvt_3_3_)) {
               lvt_5_2_ = true;
               break;
            }
         }

         if (!lvt_5_2_) {
            lvt_2_3_.remove();
         }
      }

      this.field_147067_x = 0.0F;
      lvt_1_1_.func_148329_a(0.0F);
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      CreativeTabs lvt_3_1_ = CreativeTabs.field_78032_a[field_147058_w];
      if (lvt_3_1_.func_78019_g()) {
         GlStateManager.func_179084_k();
         this.field_146289_q.func_78276_b(I18n.func_135052_a(lvt_3_1_.func_78024_c()), 8, 6, 4210752);
      }

   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      if (p_73864_3_ == 0) {
         int lvt_4_1_ = p_73864_1_ - this.field_147003_i;
         int lvt_5_1_ = p_73864_2_ - this.field_147009_r;
         CreativeTabs[] lvt_6_1_ = CreativeTabs.field_78032_a;
         int lvt_7_1_ = lvt_6_1_.length;

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
            CreativeTabs lvt_9_1_ = lvt_6_1_[lvt_8_1_];
            if (this.func_147049_a(lvt_9_1_, lvt_4_1_, lvt_5_1_)) {
               return;
            }
         }
      }

      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      if (p_146286_3_ == 0) {
         int lvt_4_1_ = p_146286_1_ - this.field_147003_i;
         int lvt_5_1_ = p_146286_2_ - this.field_147009_r;
         CreativeTabs[] lvt_6_1_ = CreativeTabs.field_78032_a;
         int lvt_7_1_ = lvt_6_1_.length;

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
            CreativeTabs lvt_9_1_ = lvt_6_1_[lvt_8_1_];
            if (this.func_147049_a(lvt_9_1_, lvt_4_1_, lvt_5_1_)) {
               this.func_147050_b(lvt_9_1_);
               return;
            }
         }
      }

      super.func_146286_b(p_146286_1_, p_146286_2_, p_146286_3_);
   }

   private boolean func_147055_p() {
      return field_147058_w != CreativeTabs.field_78036_m.func_78021_a() && CreativeTabs.field_78032_a[field_147058_w].func_78017_i() && ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148328_e();
   }

   private void func_147050_b(CreativeTabs p_147050_1_) {
      int lvt_2_1_ = field_147058_w;
      field_147058_w = p_147050_1_.func_78021_a();
      GuiContainerCreative.ContainerCreative lvt_3_1_ = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
      this.field_147008_s.clear();
      lvt_3_1_.field_148330_a.clear();
      p_147050_1_.func_78018_a(lvt_3_1_.field_148330_a);
      if (p_147050_1_ == CreativeTabs.field_78036_m) {
         Container lvt_4_1_ = this.field_146297_k.field_71439_g.field_71069_bz;
         if (this.field_147063_B == null) {
            this.field_147063_B = lvt_3_1_.field_75151_b;
         }

         lvt_3_1_.field_75151_b = Lists.newArrayList();

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.field_75151_b.size(); ++lvt_5_1_) {
            Slot lvt_6_1_ = new GuiContainerCreative.CreativeSlot((Slot)lvt_4_1_.field_75151_b.get(lvt_5_1_), lvt_5_1_);
            lvt_3_1_.field_75151_b.add(lvt_6_1_);
            int lvt_7_2_;
            int lvt_8_2_;
            int lvt_9_2_;
            if (lvt_5_1_ >= 5 && lvt_5_1_ < 9) {
               lvt_7_2_ = lvt_5_1_ - 5;
               lvt_8_2_ = lvt_7_2_ / 2;
               lvt_9_2_ = lvt_7_2_ % 2;
               lvt_6_1_.field_75223_e = 9 + lvt_8_2_ * 54;
               lvt_6_1_.field_75221_f = 6 + lvt_9_2_ * 27;
            } else if (lvt_5_1_ >= 0 && lvt_5_1_ < 5) {
               lvt_6_1_.field_75221_f = -2000;
               lvt_6_1_.field_75223_e = -2000;
            } else if (lvt_5_1_ < lvt_4_1_.field_75151_b.size()) {
               lvt_7_2_ = lvt_5_1_ - 9;
               lvt_8_2_ = lvt_7_2_ % 9;
               lvt_9_2_ = lvt_7_2_ / 9;
               lvt_6_1_.field_75223_e = 9 + lvt_8_2_ * 18;
               if (lvt_5_1_ >= 36) {
                  lvt_6_1_.field_75221_f = 112;
               } else {
                  lvt_6_1_.field_75221_f = 54 + lvt_9_2_ * 18;
               }
            }
         }

         this.field_147064_C = new Slot(field_147060_v, 0, 173, 112);
         lvt_3_1_.field_75151_b.add(this.field_147064_C);
      } else if (lvt_2_1_ == CreativeTabs.field_78036_m.func_78021_a()) {
         lvt_3_1_.field_75151_b = this.field_147063_B;
         this.field_147063_B = null;
      }

      if (this.field_147062_A != null) {
         if (p_147050_1_ == CreativeTabs.field_78027_g) {
            this.field_147062_A.func_146189_e(true);
            this.field_147062_A.func_146205_d(false);
            this.field_147062_A.func_146195_b(true);
            this.field_147062_A.func_146180_a("");
            this.func_147053_i();
         } else {
            this.field_147062_A.func_146189_e(false);
            this.field_147062_A.func_146205_d(true);
            this.field_147062_A.func_146195_b(false);
         }
      }

      this.field_147067_x = 0.0F;
      lvt_3_1_.func_148329_a(0.0F);
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int lvt_1_1_ = Mouse.getEventDWheel();
      if (lvt_1_1_ != 0 && this.func_147055_p()) {
         int lvt_2_1_ = ((GuiContainerCreative.ContainerCreative)this.field_147002_h).field_148330_a.size() / 9 - 5;
         if (lvt_1_1_ > 0) {
            lvt_1_1_ = 1;
         }

         if (lvt_1_1_ < 0) {
            lvt_1_1_ = -1;
         }

         this.field_147067_x = (float)((double)this.field_147067_x - (double)lvt_1_1_ / (double)lvt_2_1_);
         this.field_147067_x = MathHelper.func_76131_a(this.field_147067_x, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.field_147067_x);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      boolean lvt_4_1_ = Mouse.isButtonDown(0);
      int lvt_5_1_ = this.field_147003_i;
      int lvt_6_1_ = this.field_147009_r;
      int lvt_7_1_ = lvt_5_1_ + 175;
      int lvt_8_1_ = lvt_6_1_ + 18;
      int lvt_9_1_ = lvt_7_1_ + 14;
      int lvt_10_1_ = lvt_8_1_ + 112;
      if (!this.field_147065_z && lvt_4_1_ && p_73863_1_ >= lvt_7_1_ && p_73863_2_ >= lvt_8_1_ && p_73863_1_ < lvt_9_1_ && p_73863_2_ < lvt_10_1_) {
         this.field_147066_y = this.func_147055_p();
      }

      if (!lvt_4_1_) {
         this.field_147066_y = false;
      }

      this.field_147065_z = lvt_4_1_;
      if (this.field_147066_y) {
         this.field_147067_x = ((float)(p_73863_2_ - lvt_8_1_) - 7.5F) / ((float)(lvt_10_1_ - lvt_8_1_) - 15.0F);
         this.field_147067_x = MathHelper.func_76131_a(this.field_147067_x, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.field_147067_x);
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      CreativeTabs[] lvt_11_1_ = CreativeTabs.field_78032_a;
      int lvt_12_1_ = lvt_11_1_.length;

      for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_12_1_; ++lvt_13_1_) {
         CreativeTabs lvt_14_1_ = lvt_11_1_[lvt_13_1_];
         if (this.func_147052_b(lvt_14_1_, p_73863_1_, p_73863_2_)) {
            break;
         }
      }

      if (this.field_147064_C != null && field_147058_w == CreativeTabs.field_78036_m.func_78021_a() && this.func_146978_c(this.field_147064_C.field_75223_e, this.field_147064_C.field_75221_f, 16, 16, p_73863_1_, p_73863_2_)) {
         this.func_146279_a(I18n.func_135052_a("inventory.binSlot"), p_73863_1_, p_73863_2_);
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179140_f();
   }

   protected void func_146285_a(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_) {
      if (field_147058_w == CreativeTabs.field_78027_g.func_78021_a()) {
         List<String> lvt_4_1_ = p_146285_1_.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
         CreativeTabs lvt_5_1_ = p_146285_1_.func_77973_b().func_77640_w();
         if (lvt_5_1_ == null && p_146285_1_.func_77973_b() == Items.field_151134_bR) {
            Map<Integer, Integer> lvt_6_1_ = EnchantmentHelper.func_82781_a(p_146285_1_);
            if (lvt_6_1_.size() == 1) {
               Enchantment lvt_7_1_ = Enchantment.func_180306_c((Integer)lvt_6_1_.keySet().iterator().next());
               CreativeTabs[] lvt_8_1_ = CreativeTabs.field_78032_a;
               int lvt_9_1_ = lvt_8_1_.length;

               for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_9_1_; ++lvt_10_1_) {
                  CreativeTabs lvt_11_1_ = lvt_8_1_[lvt_10_1_];
                  if (lvt_11_1_.func_111226_a(lvt_7_1_.field_77351_y)) {
                     lvt_5_1_ = lvt_11_1_;
                     break;
                  }
               }
            }
         }

         if (lvt_5_1_ != null) {
            lvt_4_1_.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.func_135052_a(lvt_5_1_.func_78024_c()));
         }

         for(int lvt_6_2_ = 0; lvt_6_2_ < lvt_4_1_.size(); ++lvt_6_2_) {
            if (lvt_6_2_ == 0) {
               lvt_4_1_.set(lvt_6_2_, p_146285_1_.func_77953_t().field_77937_e + (String)lvt_4_1_.get(lvt_6_2_));
            } else {
               lvt_4_1_.set(lvt_6_2_, EnumChatFormatting.GRAY + (String)lvt_4_1_.get(lvt_6_2_));
            }
         }

         this.func_146283_a(lvt_4_1_, p_146285_2_, p_146285_3_);
      } else {
         super.func_146285_a(p_146285_1_, p_146285_2_, p_146285_3_);
      }

   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.func_74520_c();
      CreativeTabs lvt_4_1_ = CreativeTabs.field_78032_a[field_147058_w];
      CreativeTabs[] lvt_5_1_ = CreativeTabs.field_78032_a;
      int lvt_6_2_ = lvt_5_1_.length;

      int lvt_7_2_;
      for(lvt_7_2_ = 0; lvt_7_2_ < lvt_6_2_; ++lvt_7_2_) {
         CreativeTabs lvt_8_1_ = lvt_5_1_[lvt_7_2_];
         this.field_146297_k.func_110434_K().func_110577_a(field_147061_u);
         if (lvt_8_1_.func_78021_a() != field_147058_w) {
            this.func_147051_a(lvt_8_1_);
         }
      }

      this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + lvt_4_1_.func_78015_f()));
      this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
      this.field_147062_A.func_146194_f();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int lvt_5_2_ = this.field_147003_i + 175;
      lvt_6_2_ = this.field_147009_r + 18;
      lvt_7_2_ = lvt_6_2_ + 112;
      this.field_146297_k.func_110434_K().func_110577_a(field_147061_u);
      if (lvt_4_1_.func_78017_i()) {
         this.func_73729_b(lvt_5_2_, lvt_6_2_ + (int)((float)(lvt_7_2_ - lvt_6_2_ - 17) * this.field_147067_x), 232 + (this.func_147055_p() ? 0 : 12), 0, 12, 15);
      }

      this.func_147051_a(lvt_4_1_);
      if (lvt_4_1_ == CreativeTabs.field_78036_m) {
         GuiInventory.func_147046_a(this.field_147003_i + 43, this.field_147009_r + 45, 20, (float)(this.field_147003_i + 43 - p_146976_2_), (float)(this.field_147009_r + 45 - 30 - p_146976_3_), this.field_146297_k.field_71439_g);
      }

   }

   protected boolean func_147049_a(CreativeTabs p_147049_1_, int p_147049_2_, int p_147049_3_) {
      int lvt_4_1_ = p_147049_1_.func_78020_k();
      int lvt_5_1_ = 28 * lvt_4_1_;
      int lvt_6_1_ = 0;
      if (lvt_4_1_ == 5) {
         lvt_5_1_ = this.field_146999_f - 28 + 2;
      } else if (lvt_4_1_ > 0) {
         lvt_5_1_ += lvt_4_1_;
      }

      int lvt_6_1_;
      if (p_147049_1_.func_78023_l()) {
         lvt_6_1_ = lvt_6_1_ - 32;
      } else {
         lvt_6_1_ = lvt_6_1_ + this.field_147000_g;
      }

      return p_147049_2_ >= lvt_5_1_ && p_147049_2_ <= lvt_5_1_ + 28 && p_147049_3_ >= lvt_6_1_ && p_147049_3_ <= lvt_6_1_ + 32;
   }

   protected boolean func_147052_b(CreativeTabs p_147052_1_, int p_147052_2_, int p_147052_3_) {
      int lvt_4_1_ = p_147052_1_.func_78020_k();
      int lvt_5_1_ = 28 * lvt_4_1_;
      int lvt_6_1_ = 0;
      if (lvt_4_1_ == 5) {
         lvt_5_1_ = this.field_146999_f - 28 + 2;
      } else if (lvt_4_1_ > 0) {
         lvt_5_1_ += lvt_4_1_;
      }

      int lvt_6_1_;
      if (p_147052_1_.func_78023_l()) {
         lvt_6_1_ = lvt_6_1_ - 32;
      } else {
         lvt_6_1_ = lvt_6_1_ + this.field_147000_g;
      }

      if (this.func_146978_c(lvt_5_1_ + 3, lvt_6_1_ + 3, 23, 27, p_147052_2_, p_147052_3_)) {
         this.func_146279_a(I18n.func_135052_a(p_147052_1_.func_78024_c()), p_147052_2_, p_147052_3_);
         return true;
      } else {
         return false;
      }
   }

   protected void func_147051_a(CreativeTabs p_147051_1_) {
      boolean lvt_2_1_ = p_147051_1_.func_78021_a() == field_147058_w;
      boolean lvt_3_1_ = p_147051_1_.func_78023_l();
      int lvt_4_1_ = p_147051_1_.func_78020_k();
      int lvt_5_1_ = lvt_4_1_ * 28;
      int lvt_6_1_ = 0;
      int lvt_7_1_ = this.field_147003_i + 28 * lvt_4_1_;
      int lvt_8_1_ = this.field_147009_r;
      int lvt_9_1_ = 32;
      if (lvt_2_1_) {
         lvt_6_1_ += 32;
      }

      if (lvt_4_1_ == 5) {
         lvt_7_1_ = this.field_147003_i + this.field_146999_f - 28;
      } else if (lvt_4_1_ > 0) {
         lvt_7_1_ += lvt_4_1_;
      }

      if (lvt_3_1_) {
         lvt_8_1_ -= 28;
      } else {
         lvt_6_1_ += 64;
         lvt_8_1_ += this.field_147000_g - 4;
      }

      GlStateManager.func_179140_f();
      this.func_73729_b(lvt_7_1_, lvt_8_1_, lvt_5_1_, lvt_6_1_, 28, lvt_9_1_);
      this.field_73735_i = 100.0F;
      this.field_146296_j.field_77023_b = 100.0F;
      lvt_7_1_ += 6;
      lvt_8_1_ += 8 + (lvt_3_1_ ? 1 : -1);
      GlStateManager.func_179145_e();
      GlStateManager.func_179091_B();
      ItemStack lvt_10_1_ = p_147051_1_.func_151244_d();
      this.field_146296_j.func_180450_b(lvt_10_1_, lvt_7_1_, lvt_8_1_);
      this.field_146296_j.func_175030_a(this.field_146289_q, lvt_10_1_, lvt_7_1_, lvt_8_1_);
      GlStateManager.func_179140_f();
      this.field_146296_j.field_77023_b = 0.0F;
      this.field_73735_i = 0.0F;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146127_k == 0) {
         this.field_146297_k.func_147108_a(new GuiAchievements(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

      if (p_146284_1_.field_146127_k == 1) {
         this.field_146297_k.func_147108_a(new GuiStats(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

   }

   public int func_147056_g() {
      return field_147058_w;
   }

   static {
      field_147058_w = CreativeTabs.field_78030_b.func_78021_a();
   }

   class CreativeSlot extends Slot {
      private final Slot field_148332_b;

      public CreativeSlot(Slot p_i46313_2_, int p_i46313_3_) {
         super(p_i46313_2_.field_75224_c, p_i46313_3_, 0, 0);
         this.field_148332_b = p_i46313_2_;
      }

      public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
         this.field_148332_b.func_82870_a(p_82870_1_, p_82870_2_);
      }

      public boolean func_75214_a(ItemStack p_75214_1_) {
         return this.field_148332_b.func_75214_a(p_75214_1_);
      }

      public ItemStack func_75211_c() {
         return this.field_148332_b.func_75211_c();
      }

      public boolean func_75216_d() {
         return this.field_148332_b.func_75216_d();
      }

      public void func_75215_d(ItemStack p_75215_1_) {
         this.field_148332_b.func_75215_d(p_75215_1_);
      }

      public void func_75218_e() {
         this.field_148332_b.func_75218_e();
      }

      public int func_75219_a() {
         return this.field_148332_b.func_75219_a();
      }

      public int func_178170_b(ItemStack p_178170_1_) {
         return this.field_148332_b.func_178170_b(p_178170_1_);
      }

      public String func_178171_c() {
         return this.field_148332_b.func_178171_c();
      }

      public ItemStack func_75209_a(int p_75209_1_) {
         return this.field_148332_b.func_75209_a(p_75209_1_);
      }

      public boolean func_75217_a(IInventory p_75217_1_, int p_75217_2_) {
         return this.field_148332_b.func_75217_a(p_75217_1_, p_75217_2_);
      }
   }

   static class ContainerCreative extends Container {
      public List<ItemStack> field_148330_a = Lists.newArrayList();

      public ContainerCreative(EntityPlayer p_i1086_1_) {
         InventoryPlayer lvt_2_1_ = p_i1086_1_.field_71071_by;

         int lvt_3_2_;
         for(lvt_3_2_ = 0; lvt_3_2_ < 5; ++lvt_3_2_) {
            for(int lvt_4_1_ = 0; lvt_4_1_ < 9; ++lvt_4_1_) {
               this.func_75146_a(new Slot(GuiContainerCreative.field_147060_v, lvt_3_2_ * 9 + lvt_4_1_, 9 + lvt_4_1_ * 18, 18 + lvt_3_2_ * 18));
            }
         }

         for(lvt_3_2_ = 0; lvt_3_2_ < 9; ++lvt_3_2_) {
            this.func_75146_a(new Slot(lvt_2_1_, lvt_3_2_, 9 + lvt_3_2_ * 18, 112));
         }

         this.func_148329_a(0.0F);
      }

      public boolean func_75145_c(EntityPlayer p_75145_1_) {
         return true;
      }

      public void func_148329_a(float p_148329_1_) {
         int lvt_2_1_ = (this.field_148330_a.size() + 9 - 1) / 9 - 5;
         int lvt_3_1_ = (int)((double)(p_148329_1_ * (float)lvt_2_1_) + 0.5D);
         if (lvt_3_1_ < 0) {
            lvt_3_1_ = 0;
         }

         for(int lvt_4_1_ = 0; lvt_4_1_ < 5; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ < 9; ++lvt_5_1_) {
               int lvt_6_1_ = lvt_5_1_ + (lvt_4_1_ + lvt_3_1_) * 9;
               if (lvt_6_1_ >= 0 && lvt_6_1_ < this.field_148330_a.size()) {
                  GuiContainerCreative.field_147060_v.func_70299_a(lvt_5_1_ + lvt_4_1_ * 9, (ItemStack)this.field_148330_a.get(lvt_6_1_));
               } else {
                  GuiContainerCreative.field_147060_v.func_70299_a(lvt_5_1_ + lvt_4_1_ * 9, (ItemStack)null);
               }
            }
         }

      }

      public boolean func_148328_e() {
         return this.field_148330_a.size() > 45;
      }

      protected void func_75133_b(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
      }

      public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
         if (p_82846_2_ >= this.field_75151_b.size() - 9 && p_82846_2_ < this.field_75151_b.size()) {
            Slot lvt_3_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
            if (lvt_3_1_ != null && lvt_3_1_.func_75216_d()) {
               lvt_3_1_.func_75215_d((ItemStack)null);
            }
         }

         return null;
      }

      public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
         return p_94530_2_.field_75221_f > 90;
      }

      public boolean func_94531_b(Slot p_94531_1_) {
         return p_94531_1_.field_75224_c instanceof InventoryPlayer || p_94531_1_.field_75221_f > 90 && p_94531_1_.field_75223_e <= 162;
      }
   }
}
