package net.minecraft.client.resources;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class ResourcePackListEntry implements GuiListExtended.IGuiListEntry {
   private static final ResourceLocation field_148316_c = new ResourceLocation("textures/gui/resource_packs.png");
   private static final IChatComponent field_183020_d = new ChatComponentTranslation("resourcePack.incompatible", new Object[0]);
   private static final IChatComponent field_183021_e = new ChatComponentTranslation("resourcePack.incompatible.old", new Object[0]);
   private static final IChatComponent field_183022_f = new ChatComponentTranslation("resourcePack.incompatible.new", new Object[0]);
   protected final Minecraft field_148317_a;
   protected final GuiScreenResourcePacks field_148315_b;

   public ResourcePackListEntry(GuiScreenResourcePacks p_i45051_1_) {
      this.field_148315_b = p_i45051_1_;
      this.field_148317_a = Minecraft.func_71410_x();
   }

   public void func_180790_a(int p_180790_1_, int p_180790_2_, int p_180790_3_, int p_180790_4_, int p_180790_5_, int p_180790_6_, int p_180790_7_, boolean p_180790_8_) {
      int lvt_9_1_ = this.func_183019_a();
      if (lvt_9_1_ != 1) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         Gui.func_73734_a(p_180790_2_ - 1, p_180790_3_ - 1, p_180790_2_ + p_180790_4_ - 9, p_180790_3_ + p_180790_5_ + 1, -8978432);
      }

      this.func_148313_c();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.func_146110_a(p_180790_2_, p_180790_3_, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      String lvt_10_1_ = this.func_148312_b();
      String lvt_11_1_ = this.func_148311_a();
      int lvt_12_1_;
      if ((this.field_148317_a.field_71474_y.field_85185_A || p_180790_8_) && this.func_148310_d()) {
         this.field_148317_a.func_110434_K().func_110577_a(field_148316_c);
         Gui.func_73734_a(p_180790_2_, p_180790_3_, p_180790_2_ + 32, p_180790_3_ + 32, -1601138544);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         lvt_12_1_ = p_180790_6_ - p_180790_2_;
         int lvt_13_1_ = p_180790_7_ - p_180790_3_;
         if (lvt_9_1_ < 1) {
            lvt_10_1_ = field_183020_d.func_150254_d();
            lvt_11_1_ = field_183021_e.func_150254_d();
         } else if (lvt_9_1_ > 1) {
            lvt_10_1_ = field_183020_d.func_150254_d();
            lvt_11_1_ = field_183022_f.func_150254_d();
         }

         if (this.func_148309_e()) {
            if (lvt_12_1_ < 32) {
               Gui.func_146110_a(p_180790_2_, p_180790_3_, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.func_146110_a(p_180790_2_, p_180790_3_, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         } else {
            if (this.func_148308_f()) {
               if (lvt_12_1_ < 16) {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.func_148314_g()) {
               if (lvt_12_1_ < 32 && lvt_12_1_ > 16 && lvt_13_1_ < 16) {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }

            if (this.func_148307_h()) {
               if (lvt_12_1_ < 32 && lvt_12_1_ > 16 && lvt_13_1_ > 16) {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  Gui.func_146110_a(p_180790_2_, p_180790_3_, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            }
         }
      }

      lvt_12_1_ = this.field_148317_a.field_71466_p.func_78256_a(lvt_10_1_);
      if (lvt_12_1_ > 157) {
         lvt_10_1_ = this.field_148317_a.field_71466_p.func_78269_a(lvt_10_1_, 157 - this.field_148317_a.field_71466_p.func_78256_a("...")) + "...";
      }

      this.field_148317_a.field_71466_p.func_175063_a(lvt_10_1_, (float)(p_180790_2_ + 32 + 2), (float)(p_180790_3_ + 1), 16777215);
      List<String> lvt_13_2_ = this.field_148317_a.field_71466_p.func_78271_c(lvt_11_1_, 157);

      for(int lvt_14_1_ = 0; lvt_14_1_ < 2 && lvt_14_1_ < lvt_13_2_.size(); ++lvt_14_1_) {
         this.field_148317_a.field_71466_p.func_175063_a((String)lvt_13_2_.get(lvt_14_1_), (float)(p_180790_2_ + 32 + 2), (float)(p_180790_3_ + 12 + 10 * lvt_14_1_), 8421504);
      }

   }

   protected abstract int func_183019_a();

   protected abstract String func_148311_a();

   protected abstract String func_148312_b();

   protected abstract void func_148313_c();

   protected boolean func_148310_d() {
      return true;
   }

   protected boolean func_148309_e() {
      return !this.field_148315_b.func_146961_a(this);
   }

   protected boolean func_148308_f() {
      return this.field_148315_b.func_146961_a(this);
   }

   protected boolean func_148314_g() {
      List<ResourcePackListEntry> lvt_1_1_ = this.field_148315_b.func_146962_b(this);
      int lvt_2_1_ = lvt_1_1_.indexOf(this);
      return lvt_2_1_ > 0 && ((ResourcePackListEntry)lvt_1_1_.get(lvt_2_1_ - 1)).func_148310_d();
   }

   protected boolean func_148307_h() {
      List<ResourcePackListEntry> lvt_1_1_ = this.field_148315_b.func_146962_b(this);
      int lvt_2_1_ = lvt_1_1_.indexOf(this);
      return lvt_2_1_ >= 0 && lvt_2_1_ < lvt_1_1_.size() - 1 && ((ResourcePackListEntry)lvt_1_1_.get(lvt_2_1_ + 1)).func_148310_d();
   }

   public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      if (this.func_148310_d() && p_148278_5_ <= 32) {
         if (this.func_148309_e()) {
            this.field_148315_b.func_175288_g();
            int lvt_7_1_ = this.func_183019_a();
            if (lvt_7_1_ != 1) {
               String lvt_8_1_ = I18n.func_135052_a("resourcePack.incompatible.confirm.title");
               String lvt_9_1_ = I18n.func_135052_a("resourcePack.incompatible.confirm." + (lvt_7_1_ > 1 ? "new" : "old"));
               this.field_148317_a.func_147108_a(new GuiYesNo(new GuiYesNoCallback() {
                  public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
                     List<ResourcePackListEntry> lvt_3_1_ = ResourcePackListEntry.this.field_148315_b.func_146962_b(ResourcePackListEntry.this);
                     ResourcePackListEntry.this.field_148317_a.func_147108_a(ResourcePackListEntry.this.field_148315_b);
                     if (p_73878_1_) {
                        lvt_3_1_.remove(ResourcePackListEntry.this);
                        ResourcePackListEntry.this.field_148315_b.func_146963_h().add(0, ResourcePackListEntry.this);
                     }

                  }
               }, lvt_8_1_, lvt_9_1_, 0));
            } else {
               this.field_148315_b.func_146962_b(this).remove(this);
               this.field_148315_b.func_146963_h().add(0, this);
            }

            return true;
         }

         if (p_148278_5_ < 16 && this.func_148308_f()) {
            this.field_148315_b.func_146962_b(this).remove(this);
            this.field_148315_b.func_146964_g().add(0, this);
            this.field_148315_b.func_175288_g();
            return true;
         }

         List lvt_7_3_;
         int lvt_8_3_;
         if (p_148278_5_ > 16 && p_148278_6_ < 16 && this.func_148314_g()) {
            lvt_7_3_ = this.field_148315_b.func_146962_b(this);
            lvt_8_3_ = lvt_7_3_.indexOf(this);
            lvt_7_3_.remove(this);
            lvt_7_3_.add(lvt_8_3_ - 1, this);
            this.field_148315_b.func_175288_g();
            return true;
         }

         if (p_148278_5_ > 16 && p_148278_6_ > 16 && this.func_148307_h()) {
            lvt_7_3_ = this.field_148315_b.func_146962_b(this);
            lvt_8_3_ = lvt_7_3_.indexOf(this);
            lvt_7_3_.remove(this);
            lvt_7_3_.add(lvt_8_3_ + 1, this);
            this.field_148315_b.func_175288_g();
            return true;
         }
      }

      return false;
   }

   public void func_178011_a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
   }

   public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
   }
}
