package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderMinecart<T extends EntityMinecart> extends Render<T> {
   private static final ResourceLocation field_110804_g = new ResourceLocation("textures/entity/minecart.png");
   protected ModelBase field_77013_a = new ModelMinecart();

   public RenderMinecart(RenderManager p_i46155_1_) {
      super(p_i46155_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(T p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      this.func_180548_c(p_76986_1_);
      long lvt_10_1_ = (long)p_76986_1_.func_145782_y() * 493286711L;
      lvt_10_1_ = lvt_10_1_ * lvt_10_1_ * 4392167121L + lvt_10_1_ * 98761L;
      float lvt_12_1_ = (((float)(lvt_10_1_ >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float lvt_13_1_ = (((float)(lvt_10_1_ >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float lvt_14_1_ = (((float)(lvt_10_1_ >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GlStateManager.func_179109_b(lvt_12_1_, lvt_13_1_, lvt_14_1_);
      double lvt_15_1_ = p_76986_1_.field_70142_S + (p_76986_1_.field_70165_t - p_76986_1_.field_70142_S) * (double)p_76986_9_;
      double lvt_17_1_ = p_76986_1_.field_70137_T + (p_76986_1_.field_70163_u - p_76986_1_.field_70137_T) * (double)p_76986_9_;
      double lvt_19_1_ = p_76986_1_.field_70136_U + (p_76986_1_.field_70161_v - p_76986_1_.field_70136_U) * (double)p_76986_9_;
      double lvt_21_1_ = 0.30000001192092896D;
      Vec3 lvt_23_1_ = p_76986_1_.func_70489_a(lvt_15_1_, lvt_17_1_, lvt_19_1_);
      float lvt_24_1_ = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;
      if (lvt_23_1_ != null) {
         Vec3 lvt_25_1_ = p_76986_1_.func_70495_a(lvt_15_1_, lvt_17_1_, lvt_19_1_, lvt_21_1_);
         Vec3 lvt_26_1_ = p_76986_1_.func_70495_a(lvt_15_1_, lvt_17_1_, lvt_19_1_, -lvt_21_1_);
         if (lvt_25_1_ == null) {
            lvt_25_1_ = lvt_23_1_;
         }

         if (lvt_26_1_ == null) {
            lvt_26_1_ = lvt_23_1_;
         }

         p_76986_2_ += lvt_23_1_.field_72450_a - lvt_15_1_;
         p_76986_4_ += (lvt_25_1_.field_72448_b + lvt_26_1_.field_72448_b) / 2.0D - lvt_17_1_;
         p_76986_6_ += lvt_23_1_.field_72449_c - lvt_19_1_;
         Vec3 lvt_27_1_ = lvt_26_1_.func_72441_c(-lvt_25_1_.field_72450_a, -lvt_25_1_.field_72448_b, -lvt_25_1_.field_72449_c);
         if (lvt_27_1_.func_72433_c() != 0.0D) {
            lvt_27_1_ = lvt_27_1_.func_72432_b();
            p_76986_8_ = (float)(Math.atan2(lvt_27_1_.field_72449_c, lvt_27_1_.field_72450_a) * 180.0D / 3.141592653589793D);
            lvt_24_1_ = (float)(Math.atan(lvt_27_1_.field_72448_b) * 73.0D);
         }
      }

      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + 0.375F, (float)p_76986_6_);
      GlStateManager.func_179114_b(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-lvt_24_1_, 0.0F, 0.0F, 1.0F);
      float lvt_25_2_ = (float)p_76986_1_.func_70496_j() - p_76986_9_;
      float lvt_26_2_ = p_76986_1_.func_70491_i() - p_76986_9_;
      if (lvt_26_2_ < 0.0F) {
         lvt_26_2_ = 0.0F;
      }

      if (lvt_25_2_ > 0.0F) {
         GlStateManager.func_179114_b(MathHelper.func_76126_a(lvt_25_2_) * lvt_25_2_ * lvt_26_2_ / 10.0F * (float)p_76986_1_.func_70493_k(), 1.0F, 0.0F, 0.0F);
      }

      int lvt_27_2_ = p_76986_1_.func_94099_q();
      IBlockState lvt_28_1_ = p_76986_1_.func_174897_t();
      if (lvt_28_1_.func_177230_c().func_149645_b() != -1) {
         GlStateManager.func_179094_E();
         this.func_110776_a(TextureMap.field_110575_b);
         float lvt_29_1_ = 0.75F;
         GlStateManager.func_179152_a(lvt_29_1_, lvt_29_1_, lvt_29_1_);
         GlStateManager.func_179109_b(-0.5F, (float)(lvt_27_2_ - 8) / 16.0F, 0.5F);
         this.func_180560_a(p_76986_1_, p_76986_9_, lvt_28_1_);
         GlStateManager.func_179121_F();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_180548_c(p_76986_1_);
      }

      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.field_77013_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(T p_110775_1_) {
      return field_110804_g;
   }

   protected void func_180560_a(T p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
      GlStateManager.func_179094_E();
      Minecraft.func_71410_x().func_175602_ab().func_175016_a(p_180560_3_, p_180560_1_.func_70013_c(p_180560_2_));
      GlStateManager.func_179121_F();
   }
}
