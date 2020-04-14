package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;

public class RenderGhast extends RenderLiving<EntityGhast> {
   private static final ResourceLocation field_110869_a = new ResourceLocation("textures/entity/ghast/ghast.png");
   private static final ResourceLocation field_110868_f = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

   public RenderGhast(RenderManager p_i46174_1_) {
      super(p_i46174_1_, new ModelGhast(), 0.5F);
   }

   protected ResourceLocation func_110775_a(EntityGhast p_110775_1_) {
      return p_110775_1_.func_110182_bF() ? field_110868_f : field_110869_a;
   }

   protected void func_77041_b(EntityGhast p_77041_1_, float p_77041_2_) {
      float lvt_3_1_ = 1.0F;
      float lvt_4_1_ = (8.0F + lvt_3_1_) / 2.0F;
      float lvt_5_1_ = (8.0F + 1.0F / lvt_3_1_) / 2.0F;
      GlStateManager.func_179152_a(lvt_5_1_, lvt_4_1_, lvt_5_1_);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
