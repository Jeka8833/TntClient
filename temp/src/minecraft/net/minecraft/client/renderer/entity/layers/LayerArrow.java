package net.minecraft.client.renderer.entity.layers;

import java.util.Random;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;

public class LayerArrow implements LayerRenderer<EntityLivingBase> {
   private final RendererLivingEntity field_177168_a;

   public LayerArrow(RendererLivingEntity p_i46124_1_) {
      this.field_177168_a = p_i46124_1_;
   }

   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      int lvt_9_1_ = p_177141_1_.func_85035_bI();
      if (lvt_9_1_ > 0) {
         Entity lvt_10_1_ = new EntityArrow(p_177141_1_.field_70170_p, p_177141_1_.field_70165_t, p_177141_1_.field_70163_u, p_177141_1_.field_70161_v);
         Random lvt_11_1_ = new Random((long)p_177141_1_.func_145782_y());
         RenderHelper.func_74518_a();

         for(int lvt_12_1_ = 0; lvt_12_1_ < lvt_9_1_; ++lvt_12_1_) {
            GlStateManager.func_179094_E();
            ModelRenderer lvt_13_1_ = this.field_177168_a.func_177087_b().func_85181_a(lvt_11_1_);
            ModelBox lvt_14_1_ = (ModelBox)lvt_13_1_.field_78804_l.get(lvt_11_1_.nextInt(lvt_13_1_.field_78804_l.size()));
            lvt_13_1_.func_78794_c(0.0625F);
            float lvt_15_1_ = lvt_11_1_.nextFloat();
            float lvt_16_1_ = lvt_11_1_.nextFloat();
            float lvt_17_1_ = lvt_11_1_.nextFloat();
            float lvt_18_1_ = (lvt_14_1_.field_78252_a + (lvt_14_1_.field_78248_d - lvt_14_1_.field_78252_a) * lvt_15_1_) / 16.0F;
            float lvt_19_1_ = (lvt_14_1_.field_78250_b + (lvt_14_1_.field_78249_e - lvt_14_1_.field_78250_b) * lvt_16_1_) / 16.0F;
            float lvt_20_1_ = (lvt_14_1_.field_78251_c + (lvt_14_1_.field_78246_f - lvt_14_1_.field_78251_c) * lvt_17_1_) / 16.0F;
            GlStateManager.func_179109_b(lvt_18_1_, lvt_19_1_, lvt_20_1_);
            lvt_15_1_ = lvt_15_1_ * 2.0F - 1.0F;
            lvt_16_1_ = lvt_16_1_ * 2.0F - 1.0F;
            lvt_17_1_ = lvt_17_1_ * 2.0F - 1.0F;
            lvt_15_1_ *= -1.0F;
            lvt_16_1_ *= -1.0F;
            lvt_17_1_ *= -1.0F;
            float lvt_21_1_ = MathHelper.func_76129_c(lvt_15_1_ * lvt_15_1_ + lvt_17_1_ * lvt_17_1_);
            lvt_10_1_.field_70126_B = lvt_10_1_.field_70177_z = (float)(Math.atan2((double)lvt_15_1_, (double)lvt_17_1_) * 180.0D / 3.1415927410125732D);
            lvt_10_1_.field_70127_C = lvt_10_1_.field_70125_A = (float)(Math.atan2((double)lvt_16_1_, (double)lvt_21_1_) * 180.0D / 3.1415927410125732D);
            double lvt_22_1_ = 0.0D;
            double lvt_24_1_ = 0.0D;
            double lvt_26_1_ = 0.0D;
            this.field_177168_a.func_177068_d().func_147940_a(lvt_10_1_, lvt_22_1_, lvt_24_1_, lvt_26_1_, 0.0F, p_177141_4_);
            GlStateManager.func_179121_F();
         }

         RenderHelper.func_74519_b();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
