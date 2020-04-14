package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderCreeper extends RenderLiving<EntityCreeper> {
   private static final ResourceLocation field_110830_f = new ResourceLocation("textures/entity/creeper/creeper.png");

   public RenderCreeper(RenderManager p_i46186_1_) {
      super(p_i46186_1_, new ModelCreeper(), 0.5F);
      this.func_177094_a(new LayerCreeperCharge(this));
   }

   protected void func_77041_b(EntityCreeper p_77041_1_, float p_77041_2_) {
      float lvt_3_1_ = p_77041_1_.func_70831_j(p_77041_2_);
      float lvt_4_1_ = 1.0F + MathHelper.func_76126_a(lvt_3_1_ * 100.0F) * lvt_3_1_ * 0.01F;
      lvt_3_1_ = MathHelper.func_76131_a(lvt_3_1_, 0.0F, 1.0F);
      lvt_3_1_ *= lvt_3_1_;
      lvt_3_1_ *= lvt_3_1_;
      float lvt_5_1_ = (1.0F + lvt_3_1_ * 0.4F) * lvt_4_1_;
      float lvt_6_1_ = (1.0F + lvt_3_1_ * 0.1F) / lvt_4_1_;
      GlStateManager.func_179152_a(lvt_5_1_, lvt_6_1_, lvt_5_1_);
   }

   protected int func_77030_a(EntityCreeper p_77030_1_, float p_77030_2_, float p_77030_3_) {
      float lvt_4_1_ = p_77030_1_.func_70831_j(p_77030_3_);
      if ((int)(lvt_4_1_ * 10.0F) % 2 == 0) {
         return 0;
      } else {
         int lvt_5_1_ = (int)(lvt_4_1_ * 0.2F * 255.0F);
         lvt_5_1_ = MathHelper.func_76125_a(lvt_5_1_, 0, 255);
         return lvt_5_1_ << 24 | 16777215;
      }
   }

   protected ResourceLocation func_110775_a(EntityCreeper p_110775_1_) {
      return field_110830_f;
   }
}
