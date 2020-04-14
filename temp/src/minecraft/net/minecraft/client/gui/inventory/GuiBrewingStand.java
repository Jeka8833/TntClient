package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBrewingStand extends GuiContainer {
   private static final ResourceLocation field_147014_u = new ResourceLocation("textures/gui/container/brewing_stand.png");
   private final InventoryPlayer field_175384_v;
   private IInventory field_147013_v;

   public GuiBrewingStand(InventoryPlayer p_i45506_1_, IInventory p_i45506_2_) {
      super(new ContainerBrewingStand(p_i45506_1_, p_i45506_2_));
      this.field_175384_v = p_i45506_1_;
      this.field_147013_v = p_i45506_2_;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      String lvt_3_1_ = this.field_147013_v.func_145748_c_().func_150260_c();
      this.field_146289_q.func_78276_b(lvt_3_1_, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(lvt_3_1_) / 2, 6, 4210752);
      this.field_146289_q.func_78276_b(this.field_175384_v.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147014_u);
      int lvt_4_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_5_1_ = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(lvt_4_1_, lvt_5_1_, 0, 0, this.field_146999_f, this.field_147000_g);
      int lvt_6_1_ = this.field_147013_v.func_174887_a_(0);
      if (lvt_6_1_ > 0) {
         int lvt_7_1_ = (int)(28.0F * (1.0F - (float)lvt_6_1_ / 400.0F));
         if (lvt_7_1_ > 0) {
            this.func_73729_b(lvt_4_1_ + 97, lvt_5_1_ + 16, 176, 0, 9, lvt_7_1_);
         }

         int lvt_8_1_ = lvt_6_1_ / 2 % 7;
         switch(lvt_8_1_) {
         case 0:
            lvt_7_1_ = 29;
            break;
         case 1:
            lvt_7_1_ = 24;
            break;
         case 2:
            lvt_7_1_ = 20;
            break;
         case 3:
            lvt_7_1_ = 16;
            break;
         case 4:
            lvt_7_1_ = 11;
            break;
         case 5:
            lvt_7_1_ = 6;
            break;
         case 6:
            lvt_7_1_ = 0;
         }

         if (lvt_7_1_ > 0) {
            this.func_73729_b(lvt_4_1_ + 65, lvt_5_1_ + 14 + 29 - lvt_7_1_, 185, 29 - lvt_7_1_, 12, lvt_7_1_);
         }
      }

   }
}
