package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer<TileEntityChest> {
   private static final ResourceLocation field_147507_b = new ResourceLocation("textures/entity/chest/trapped_double.png");
   private static final ResourceLocation field_147508_c = new ResourceLocation("textures/entity/chest/christmas_double.png");
   private static final ResourceLocation field_147505_d = new ResourceLocation("textures/entity/chest/normal_double.png");
   private static final ResourceLocation field_147506_e = new ResourceLocation("textures/entity/chest/trapped.png");
   private static final ResourceLocation field_147503_f = new ResourceLocation("textures/entity/chest/christmas.png");
   private static final ResourceLocation field_147504_g = new ResourceLocation("textures/entity/chest/normal.png");
   private ModelChest field_147510_h = new ModelChest();
   private ModelChest field_147511_i = new ModelLargeChest();
   private boolean field_147509_j;

   public TileEntityChestRenderer() {
      Calendar lvt_1_1_ = Calendar.getInstance();
      if (lvt_1_1_.get(2) + 1 == 12 && lvt_1_1_.get(5) >= 24 && lvt_1_1_.get(5) <= 26) {
         this.field_147509_j = true;
      }

   }

   public void func_180535_a(TileEntityChest p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179132_a(true);
      int lvt_10_2_;
      if (!p_180535_1_.func_145830_o()) {
         lvt_10_2_ = 0;
      } else {
         Block lvt_11_1_ = p_180535_1_.func_145838_q();
         lvt_10_2_ = p_180535_1_.func_145832_p();
         if (lvt_11_1_ instanceof BlockChest && lvt_10_2_ == 0) {
            ((BlockChest)lvt_11_1_).func_176455_e(p_180535_1_.func_145831_w(), p_180535_1_.func_174877_v(), p_180535_1_.func_145831_w().func_180495_p(p_180535_1_.func_174877_v()));
            lvt_10_2_ = p_180535_1_.func_145832_p();
         }

         p_180535_1_.func_145979_i();
      }

      if (p_180535_1_.field_145992_i == null && p_180535_1_.field_145991_k == null) {
         ModelChest lvt_11_3_;
         if (p_180535_1_.field_145990_j == null && p_180535_1_.field_145988_l == null) {
            lvt_11_3_ = this.field_147510_h;
            if (p_180535_9_ >= 0) {
               this.func_147499_a(field_178460_a[p_180535_9_]);
               GlStateManager.func_179128_n(5890);
               GlStateManager.func_179094_E();
               GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
               GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.func_179128_n(5888);
            } else if (this.field_147509_j) {
               this.func_147499_a(field_147503_f);
            } else if (p_180535_1_.func_145980_j() == 1) {
               this.func_147499_a(field_147506_e);
            } else {
               this.func_147499_a(field_147504_g);
            }
         } else {
            lvt_11_3_ = this.field_147511_i;
            if (p_180535_9_ >= 0) {
               this.func_147499_a(field_178460_a[p_180535_9_]);
               GlStateManager.func_179128_n(5890);
               GlStateManager.func_179094_E();
               GlStateManager.func_179152_a(8.0F, 4.0F, 1.0F);
               GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.func_179128_n(5888);
            } else if (this.field_147509_j) {
               this.func_147499_a(field_147508_c);
            } else if (p_180535_1_.func_145980_j() == 1) {
               this.func_147499_a(field_147507_b);
            } else {
               this.func_147499_a(field_147505_d);
            }
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179091_B();
         if (p_180535_9_ < 0) {
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GlStateManager.func_179109_b((float)p_180535_2_, (float)p_180535_4_ + 1.0F, (float)p_180535_6_ + 1.0F);
         GlStateManager.func_179152_a(1.0F, -1.0F, -1.0F);
         GlStateManager.func_179109_b(0.5F, 0.5F, 0.5F);
         int lvt_12_1_ = 0;
         if (lvt_10_2_ == 2) {
            lvt_12_1_ = 180;
         }

         if (lvt_10_2_ == 3) {
            lvt_12_1_ = 0;
         }

         if (lvt_10_2_ == 4) {
            lvt_12_1_ = 90;
         }

         if (lvt_10_2_ == 5) {
            lvt_12_1_ = -90;
         }

         if (lvt_10_2_ == 2 && p_180535_1_.field_145990_j != null) {
            GlStateManager.func_179109_b(1.0F, 0.0F, 0.0F);
         }

         if (lvt_10_2_ == 5 && p_180535_1_.field_145988_l != null) {
            GlStateManager.func_179109_b(0.0F, 0.0F, -1.0F);
         }

         GlStateManager.func_179114_b((float)lvt_12_1_, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
         float lvt_13_1_ = p_180535_1_.field_145986_n + (p_180535_1_.field_145989_m - p_180535_1_.field_145986_n) * p_180535_8_;
         float lvt_14_2_;
         if (p_180535_1_.field_145992_i != null) {
            lvt_14_2_ = p_180535_1_.field_145992_i.field_145986_n + (p_180535_1_.field_145992_i.field_145989_m - p_180535_1_.field_145992_i.field_145986_n) * p_180535_8_;
            if (lvt_14_2_ > lvt_13_1_) {
               lvt_13_1_ = lvt_14_2_;
            }
         }

         if (p_180535_1_.field_145991_k != null) {
            lvt_14_2_ = p_180535_1_.field_145991_k.field_145986_n + (p_180535_1_.field_145991_k.field_145989_m - p_180535_1_.field_145991_k.field_145986_n) * p_180535_8_;
            if (lvt_14_2_ > lvt_13_1_) {
               lvt_13_1_ = lvt_14_2_;
            }
         }

         lvt_13_1_ = 1.0F - lvt_13_1_;
         lvt_13_1_ = 1.0F - lvt_13_1_ * lvt_13_1_ * lvt_13_1_;
         lvt_11_3_.field_78234_a.field_78795_f = -(lvt_13_1_ * 3.1415927F / 2.0F);
         lvt_11_3_.func_78231_a();
         GlStateManager.func_179101_C();
         GlStateManager.func_179121_F();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         if (p_180535_9_ >= 0) {
            GlStateManager.func_179128_n(5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
         }

      }
   }
}
