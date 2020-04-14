package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderEntityItem extends Render<EntityItem> {
   private final RenderItem field_177080_a;
   private Random field_177079_e = new Random();

   public RenderEntityItem(RenderManager p_i46167_1_, RenderItem p_i46167_2_) {
      super(p_i46167_1_);
      this.field_177080_a = p_i46167_2_;
      this.field_76989_e = 0.15F;
      this.field_76987_f = 0.75F;
   }

   private int func_177077_a(EntityItem p_177077_1_, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
      ItemStack lvt_10_1_ = p_177077_1_.func_92059_d();
      Item lvt_11_1_ = lvt_10_1_.func_77973_b();
      if (lvt_11_1_ == null) {
         return 0;
      } else {
         boolean lvt_12_1_ = p_177077_9_.func_177556_c();
         int lvt_13_1_ = this.func_177078_a(lvt_10_1_);
         float lvt_14_1_ = 0.25F;
         float lvt_15_1_ = MathHelper.func_76126_a(((float)p_177077_1_.func_174872_o() + p_177077_8_) / 10.0F + p_177077_1_.field_70290_d) * 0.1F + 0.1F;
         float lvt_16_1_ = p_177077_9_.func_177552_f().func_181688_b(ItemCameraTransforms.TransformType.GROUND).field_178363_d.y;
         GlStateManager.func_179109_b((float)p_177077_2_, (float)p_177077_4_ + lvt_15_1_ + 0.25F * lvt_16_1_, (float)p_177077_6_);
         float lvt_17_2_;
         if (lvt_12_1_ || this.field_76990_c.field_78733_k != null) {
            lvt_17_2_ = (((float)p_177077_1_.func_174872_o() + p_177077_8_) / 20.0F + p_177077_1_.field_70290_d) * 57.295776F;
            GlStateManager.func_179114_b(lvt_17_2_, 0.0F, 1.0F, 0.0F);
         }

         if (!lvt_12_1_) {
            lvt_17_2_ = -0.0F * (float)(lvt_13_1_ - 1) * 0.5F;
            float lvt_18_1_ = -0.0F * (float)(lvt_13_1_ - 1) * 0.5F;
            float lvt_19_1_ = -0.046875F * (float)(lvt_13_1_ - 1) * 0.5F;
            GlStateManager.func_179109_b(lvt_17_2_, lvt_18_1_, lvt_19_1_);
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         return lvt_13_1_;
      }
   }

   private int func_177078_a(ItemStack p_177078_1_) {
      int lvt_2_1_ = 1;
      if (p_177078_1_.field_77994_a > 48) {
         lvt_2_1_ = 5;
      } else if (p_177078_1_.field_77994_a > 32) {
         lvt_2_1_ = 4;
      } else if (p_177078_1_.field_77994_a > 16) {
         lvt_2_1_ = 3;
      } else if (p_177078_1_.field_77994_a > 1) {
         lvt_2_1_ = 2;
      }

      return lvt_2_1_;
   }

   public void func_76986_a(EntityItem p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      ItemStack lvt_10_1_ = p_76986_1_.func_92059_d();
      this.field_177079_e.setSeed(187L);
      boolean lvt_11_1_ = false;
      if (this.func_180548_c(p_76986_1_)) {
         this.field_76990_c.field_78724_e.func_110581_b(this.func_110775_a(p_76986_1_)).func_174936_b(false, false);
         lvt_11_1_ = true;
      }

      GlStateManager.func_179091_B();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179094_E();
      IBakedModel lvt_12_1_ = this.field_177080_a.func_175037_a().func_178089_a(lvt_10_1_);
      int lvt_13_1_ = this.func_177077_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_9_, lvt_12_1_);

      for(int lvt_14_1_ = 0; lvt_14_1_ < lvt_13_1_; ++lvt_14_1_) {
         float lvt_15_1_;
         float lvt_16_1_;
         float lvt_17_1_;
         if (lvt_12_1_.func_177556_c()) {
            GlStateManager.func_179094_E();
            if (lvt_14_1_ > 0) {
               lvt_15_1_ = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               lvt_16_1_ = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               lvt_17_1_ = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               GlStateManager.func_179109_b(lvt_15_1_, lvt_16_1_, lvt_17_1_);
            }

            GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
            lvt_12_1_.func_177552_f().func_181689_a(ItemCameraTransforms.TransformType.GROUND);
            this.field_177080_a.func_180454_a(lvt_10_1_, lvt_12_1_);
            GlStateManager.func_179121_F();
         } else {
            GlStateManager.func_179094_E();
            lvt_12_1_.func_177552_f().func_181689_a(ItemCameraTransforms.TransformType.GROUND);
            this.field_177080_a.func_180454_a(lvt_10_1_, lvt_12_1_);
            GlStateManager.func_179121_F();
            lvt_15_1_ = lvt_12_1_.func_177552_f().field_181699_o.field_178363_d.x;
            lvt_16_1_ = lvt_12_1_.func_177552_f().field_181699_o.field_178363_d.y;
            lvt_17_1_ = lvt_12_1_.func_177552_f().field_181699_o.field_178363_d.z;
            GlStateManager.func_179109_b(0.0F * lvt_15_1_, 0.0F * lvt_16_1_, 0.046875F * lvt_17_1_);
         }
      }

      GlStateManager.func_179121_F();
      GlStateManager.func_179101_C();
      GlStateManager.func_179084_k();
      this.func_180548_c(p_76986_1_);
      if (lvt_11_1_) {
         this.field_76990_c.field_78724_e.func_110581_b(this.func_110775_a(p_76986_1_)).func_174935_a();
      }

      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityItem p_110775_1_) {
      return TextureMap.field_110575_b;
   }
}
