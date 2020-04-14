package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerIronGolemFlower;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;

public class RenderIronGolem extends RenderLiving<EntityIronGolem> {
   private static final ResourceLocation field_110899_a = new ResourceLocation("textures/entity/iron_golem.png");

   public RenderIronGolem(RenderManager p_i46133_1_) {
      super(p_i46133_1_, new ModelIronGolem(), 0.5F);
      this.func_177094_a(new LayerIronGolemFlower(this));
   }

   protected ResourceLocation func_110775_a(EntityIronGolem p_110775_1_) {
      return field_110899_a;
   }

   protected void func_77043_a(EntityIronGolem p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      super.func_77043_a(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
      if ((double)p_77043_1_.field_70721_aZ >= 0.01D) {
         float lvt_5_1_ = 13.0F;
         float lvt_6_1_ = p_77043_1_.field_70754_ba - p_77043_1_.field_70721_aZ * (1.0F - p_77043_4_) + 6.0F;
         float lvt_7_1_ = (Math.abs(lvt_6_1_ % lvt_5_1_ - lvt_5_1_ * 0.5F) - lvt_5_1_ * 0.25F) / (lvt_5_1_ * 0.25F);
         GlStateManager.func_179114_b(6.5F * lvt_7_1_, 0.0F, 0.0F, 1.0F);
      }
   }
}
