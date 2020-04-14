package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCube extends RenderLiving<EntityMagmaCube> {
   private static final ResourceLocation field_110873_a = new ResourceLocation("textures/entity/slime/magmacube.png");

   public RenderMagmaCube(RenderManager p_i46159_1_) {
      super(p_i46159_1_, new ModelMagmaCube(), 0.25F);
   }

   protected ResourceLocation func_110775_a(EntityMagmaCube p_110775_1_) {
      return field_110873_a;
   }

   protected void func_77041_b(EntityMagmaCube p_77041_1_, float p_77041_2_) {
      int lvt_3_1_ = p_77041_1_.func_70809_q();
      float lvt_4_1_ = (p_77041_1_.field_70812_c + (p_77041_1_.field_70811_b - p_77041_1_.field_70812_c) * p_77041_2_) / ((float)lvt_3_1_ * 0.5F + 1.0F);
      float lvt_5_1_ = 1.0F / (lvt_4_1_ + 1.0F);
      float lvt_6_1_ = (float)lvt_3_1_;
      GlStateManager.func_179152_a(lvt_5_1_ * lvt_6_1_, 1.0F / lvt_5_1_ * lvt_6_1_, lvt_5_1_ * lvt_6_1_);
   }
}
