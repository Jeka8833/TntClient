package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiFurnace extends GuiContainer {
   private static final ResourceLocation field_147087_u = new ResourceLocation("textures/gui/container/furnace.png");
   private final InventoryPlayer field_175383_v;
   private IInventory field_147086_v;

   public GuiFurnace(InventoryPlayer p_i45501_1_, IInventory p_i45501_2_) {
      super(new ContainerFurnace(p_i45501_1_, p_i45501_2_));
      this.field_175383_v = p_i45501_1_;
      this.field_147086_v = p_i45501_2_;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      String lvt_3_1_ = this.field_147086_v.func_145748_c_().func_150260_c();
      this.field_146289_q.func_78276_b(lvt_3_1_, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(lvt_3_1_) / 2, 6, 4210752);
      this.field_146289_q.func_78276_b(this.field_175383_v.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147087_u);
      int lvt_4_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_5_1_ = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(lvt_4_1_, lvt_5_1_, 0, 0, this.field_146999_f, this.field_147000_g);
      int lvt_6_2_;
      if (TileEntityFurnace.func_174903_a(this.field_147086_v)) {
         lvt_6_2_ = this.func_175382_i(13);
         this.func_73729_b(lvt_4_1_ + 56, lvt_5_1_ + 36 + 12 - lvt_6_2_, 176, 12 - lvt_6_2_, 14, lvt_6_2_ + 1);
      }

      lvt_6_2_ = this.func_175381_h(24);
      this.func_73729_b(lvt_4_1_ + 79, lvt_5_1_ + 34, 176, 14, lvt_6_2_ + 1, 16);
   }

   private int func_175381_h(int p_175381_1_) {
      int lvt_2_1_ = this.field_147086_v.func_174887_a_(2);
      int lvt_3_1_ = this.field_147086_v.func_174887_a_(3);
      return lvt_3_1_ != 0 && lvt_2_1_ != 0 ? lvt_2_1_ * p_175381_1_ / lvt_3_1_ : 0;
   }

   private int func_175382_i(int p_175382_1_) {
      int lvt_2_1_ = this.field_147086_v.func_174887_a_(1);
      if (lvt_2_1_ == 0) {
         lvt_2_1_ = 200;
      }

      return this.field_147086_v.func_174887_a_(0) * p_175382_1_ / lvt_2_1_;
   }
}
