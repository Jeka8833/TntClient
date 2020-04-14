package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;

public class RenderWither extends RenderLiving<EntityWither> {
   private static final ResourceLocation field_110913_a = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation field_110912_f = new ResourceLocation("textures/entity/wither/wither.png");

   public RenderWither(RenderManager p_i46130_1_) {
      super(p_i46130_1_, new ModelWither(0.0F), 1.0F);
      this.func_177094_a(new LayerWitherAura(this));
   }

   public void func_76986_a(EntityWither p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      BossStatus.func_82824_a(p_76986_1_, true);
      super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityWither p_110775_1_) {
      int lvt_2_1_ = p_110775_1_.func_82212_n();
      return lvt_2_1_ > 0 && (lvt_2_1_ > 80 || lvt_2_1_ / 5 % 2 != 1) ? field_110913_a : field_110912_f;
   }

   protected void func_77041_b(EntityWither p_77041_1_, float p_77041_2_) {
      float lvt_3_1_ = 2.0F;
      int lvt_4_1_ = p_77041_1_.func_82212_n();
      if (lvt_4_1_ > 0) {
         lvt_3_1_ -= ((float)lvt_4_1_ - p_77041_2_) / 220.0F * 0.5F;
      }

      GlStateManager.func_179152_a(lvt_3_1_, lvt_3_1_, lvt_3_1_);
   }
}
