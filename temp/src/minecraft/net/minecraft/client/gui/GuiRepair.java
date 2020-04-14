package net.minecraft.client.gui;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class GuiRepair extends GuiContainer implements ICrafting {
   private static final ResourceLocation field_147093_u = new ResourceLocation("textures/gui/container/anvil.png");
   private ContainerRepair field_147092_v;
   private GuiTextField field_147091_w;
   private InventoryPlayer field_147094_x;

   public GuiRepair(InventoryPlayer p_i45508_1_, World p_i45508_2_) {
      super(new ContainerRepair(p_i45508_1_, p_i45508_2_, Minecraft.func_71410_x().field_71439_g));
      this.field_147094_x = p_i45508_1_;
      this.field_147092_v = (ContainerRepair)this.field_147002_h;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      Keyboard.enableRepeatEvents(true);
      int lvt_1_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_2_1_ = (this.field_146295_m - this.field_147000_g) / 2;
      this.field_147091_w = new GuiTextField(0, this.field_146289_q, lvt_1_1_ + 62, lvt_2_1_ + 24, 103, 12);
      this.field_147091_w.func_146193_g(-1);
      this.field_147091_w.func_146204_h(-1);
      this.field_147091_w.func_146185_a(false);
      this.field_147091_w.func_146203_f(30);
      this.field_147002_h.func_82847_b(this);
      this.field_147002_h.func_75132_a(this);
   }

   public void func_146281_b() {
      super.func_146281_b();
      Keyboard.enableRepeatEvents(false);
      this.field_147002_h.func_82847_b(this);
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      GlStateManager.func_179140_f();
      GlStateManager.func_179084_k();
      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.repair"), 60, 6, 4210752);
      if (this.field_147092_v.field_82854_e > 0) {
         int lvt_3_1_ = 8453920;
         boolean lvt_4_1_ = true;
         String lvt_5_1_ = I18n.func_135052_a("container.repair.cost", this.field_147092_v.field_82854_e);
         if (this.field_147092_v.field_82854_e >= 40 && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) {
            lvt_5_1_ = I18n.func_135052_a("container.repair.expensive");
            lvt_3_1_ = 16736352;
         } else if (!this.field_147092_v.func_75139_a(2).func_75216_d()) {
            lvt_4_1_ = false;
         } else if (!this.field_147092_v.func_75139_a(2).func_82869_a(this.field_147094_x.field_70458_d)) {
            lvt_3_1_ = 16736352;
         }

         if (lvt_4_1_) {
            int lvt_6_1_ = -16777216 | (lvt_3_1_ & 16579836) >> 2 | lvt_3_1_ & -16777216;
            int lvt_7_1_ = this.field_146999_f - 8 - this.field_146289_q.func_78256_a(lvt_5_1_);
            int lvt_8_1_ = 67;
            if (this.field_146289_q.func_82883_a()) {
               func_73734_a(lvt_7_1_ - 3, lvt_8_1_ - 2, this.field_146999_f - 7, lvt_8_1_ + 10, -16777216);
               func_73734_a(lvt_7_1_ - 2, lvt_8_1_ - 1, this.field_146999_f - 8, lvt_8_1_ + 9, -12895429);
            } else {
               this.field_146289_q.func_78276_b(lvt_5_1_, lvt_7_1_, lvt_8_1_ + 1, lvt_6_1_);
               this.field_146289_q.func_78276_b(lvt_5_1_, lvt_7_1_ + 1, lvt_8_1_, lvt_6_1_);
               this.field_146289_q.func_78276_b(lvt_5_1_, lvt_7_1_ + 1, lvt_8_1_ + 1, lvt_6_1_);
            }

            this.field_146289_q.func_78276_b(lvt_5_1_, lvt_7_1_, lvt_8_1_, lvt_3_1_);
         }
      }

      GlStateManager.func_179145_e();
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (this.field_147091_w.func_146201_a(p_73869_1_, p_73869_2_)) {
         this.func_147090_g();
      } else {
         super.func_73869_a(p_73869_1_, p_73869_2_);
      }

   }

   private void func_147090_g() {
      String lvt_1_1_ = this.field_147091_w.func_146179_b();
      Slot lvt_2_1_ = this.field_147092_v.func_75139_a(0);
      if (lvt_2_1_ != null && lvt_2_1_.func_75216_d() && !lvt_2_1_.func_75211_c().func_82837_s() && lvt_1_1_.equals(lvt_2_1_.func_75211_c().func_82833_r())) {
         lvt_1_1_ = "";
      }

      this.field_147092_v.func_82850_a(lvt_1_1_);
      this.field_146297_k.field_71439_g.field_71174_a.func_147297_a(new C17PacketCustomPayload("MC|ItemName", (new PacketBuffer(Unpooled.buffer())).func_180714_a(lvt_1_1_)));
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_147091_w.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      GlStateManager.func_179140_f();
      GlStateManager.func_179084_k();
      this.field_147091_w.func_146194_f();
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147093_u);
      int lvt_4_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_5_1_ = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(lvt_4_1_, lvt_5_1_, 0, 0, this.field_146999_f, this.field_147000_g);
      this.func_73729_b(lvt_4_1_ + 59, lvt_5_1_ + 20, 0, this.field_147000_g + (this.field_147092_v.func_75139_a(0).func_75216_d() ? 0 : 16), 110, 16);
      if ((this.field_147092_v.func_75139_a(0).func_75216_d() || this.field_147092_v.func_75139_a(1).func_75216_d()) && !this.field_147092_v.func_75139_a(2).func_75216_d()) {
         this.func_73729_b(lvt_4_1_ + 99, lvt_5_1_ + 45, this.field_146999_f, 0, 28, 21);
      }

   }

   public void func_71110_a(Container p_71110_1_, List<ItemStack> p_71110_2_) {
      this.func_71111_a(p_71110_1_, 0, p_71110_1_.func_75139_a(0).func_75211_c());
   }

   public void func_71111_a(Container p_71111_1_, int p_71111_2_, ItemStack p_71111_3_) {
      if (p_71111_2_ == 0) {
         this.field_147091_w.func_146180_a(p_71111_3_ == null ? "" : p_71111_3_.func_82833_r());
         this.field_147091_w.func_146184_c(p_71111_3_ != null);
         if (p_71111_3_ != null) {
            this.func_147090_g();
         }
      }

   }

   public void func_71112_a(Container p_71112_1_, int p_71112_2_, int p_71112_3_) {
   }

   public void func_175173_a(Container p_175173_1_, IInventory p_175173_2_) {
   }
}
