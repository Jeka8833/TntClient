package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBoat extends Render<EntityBoat> {
   private static final ResourceLocation field_110782_f = new ResourceLocation("textures/entity/boat.png");
   protected ModelBase field_76998_a = new ModelBoat();

   public RenderBoat(RenderManager p_i46190_1_) {
      super(p_i46190_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(EntityBoat p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + 0.25F, (float)p_76986_6_);
      GlStateManager.func_179114_b(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
      float lvt_10_1_ = (float)p_76986_1_.func_70268_h() - p_76986_9_;
      float lvt_11_1_ = p_76986_1_.func_70271_g() - p_76986_9_;
      if (lvt_11_1_ < 0.0F) {
         lvt_11_1_ = 0.0F;
      }

      if (lvt_10_1_ > 0.0F) {
         GlStateManager.func_179114_b(MathHelper.func_76126_a(lvt_10_1_) * lvt_10_1_ * lvt_11_1_ / 10.0F * (float)p_76986_1_.func_70267_i(), 1.0F, 0.0F, 0.0F);
      }

      float lvt_12_1_ = 0.75F;
      GlStateManager.func_179152_a(lvt_12_1_, lvt_12_1_, lvt_12_1_);
      GlStateManager.func_179152_a(1.0F / lvt_12_1_, 1.0F / lvt_12_1_, 1.0F / lvt_12_1_);
      this.func_180548_c(p_76986_1_);
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.field_76998_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityBoat p_110775_1_) {
      return field_110782_f;
   }
}
