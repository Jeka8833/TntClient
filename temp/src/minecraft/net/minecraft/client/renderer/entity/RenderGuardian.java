package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderGuardian extends RenderLiving<EntityGuardian> {
   private static final ResourceLocation field_177114_e = new ResourceLocation("textures/entity/guardian.png");
   private static final ResourceLocation field_177116_j = new ResourceLocation("textures/entity/guardian_elder.png");
   private static final ResourceLocation field_177117_k = new ResourceLocation("textures/entity/guardian_beam.png");
   int field_177115_a;

   public RenderGuardian(RenderManager p_i46171_1_) {
      super(p_i46171_1_, new ModelGuardian(), 0.5F);
      this.field_177115_a = ((ModelGuardian)this.field_77045_g).func_178706_a();
   }

   public boolean func_177071_a(EntityGuardian p_177071_1_, ICamera p_177071_2_, double p_177071_3_, double p_177071_5_, double p_177071_7_) {
      if (super.func_177071_a((EntityLiving)p_177071_1_, p_177071_2_, p_177071_3_, p_177071_5_, p_177071_7_)) {
         return true;
      } else {
         if (p_177071_1_.func_175474_cn()) {
            EntityLivingBase lvt_9_1_ = p_177071_1_.func_175466_co();
            if (lvt_9_1_ != null) {
               Vec3 lvt_10_1_ = this.func_177110_a(lvt_9_1_, (double)lvt_9_1_.field_70131_O * 0.5D, 1.0F);
               Vec3 lvt_11_1_ = this.func_177110_a(p_177071_1_, (double)p_177071_1_.func_70047_e(), 1.0F);
               if (p_177071_2_.func_78546_a(AxisAlignedBB.func_178781_a(lvt_11_1_.field_72450_a, lvt_11_1_.field_72448_b, lvt_11_1_.field_72449_c, lvt_10_1_.field_72450_a, lvt_10_1_.field_72448_b, lvt_10_1_.field_72449_c))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_) {
      double lvt_5_1_ = p_177110_1_.field_70142_S + (p_177110_1_.field_70165_t - p_177110_1_.field_70142_S) * (double)p_177110_4_;
      double lvt_7_1_ = p_177110_2_ + p_177110_1_.field_70137_T + (p_177110_1_.field_70163_u - p_177110_1_.field_70137_T) * (double)p_177110_4_;
      double lvt_9_1_ = p_177110_1_.field_70136_U + (p_177110_1_.field_70161_v - p_177110_1_.field_70136_U) * (double)p_177110_4_;
      return new Vec3(lvt_5_1_, lvt_7_1_, lvt_9_1_);
   }

   public void func_76986_a(EntityGuardian p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if (this.field_177115_a != ((ModelGuardian)this.field_77045_g).func_178706_a()) {
         this.field_77045_g = new ModelGuardian();
         this.field_177115_a = ((ModelGuardian)this.field_77045_g).func_178706_a();
      }

      super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      EntityLivingBase lvt_10_1_ = p_76986_1_.func_175466_co();
      if (lvt_10_1_ != null) {
         float lvt_11_1_ = p_76986_1_.func_175477_p(p_76986_9_);
         Tessellator lvt_12_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_13_1_ = lvt_12_1_.func_178180_c();
         this.func_110776_a(field_177117_k);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.func_179140_f();
         GlStateManager.func_179129_p();
         GlStateManager.func_179084_k();
         GlStateManager.func_179132_a(true);
         float lvt_14_1_ = 240.0F;
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, lvt_14_1_, lvt_14_1_);
         GlStateManager.func_179120_a(770, 1, 1, 0);
         float lvt_15_1_ = (float)p_76986_1_.field_70170_p.func_82737_E() + p_76986_9_;
         float lvt_16_1_ = lvt_15_1_ * 0.5F % 1.0F;
         float lvt_17_1_ = p_76986_1_.func_70047_e();
         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + lvt_17_1_, (float)p_76986_6_);
         Vec3 lvt_18_1_ = this.func_177110_a(lvt_10_1_, (double)lvt_10_1_.field_70131_O * 0.5D, p_76986_9_);
         Vec3 lvt_19_1_ = this.func_177110_a(p_76986_1_, (double)lvt_17_1_, p_76986_9_);
         Vec3 lvt_20_1_ = lvt_18_1_.func_178788_d(lvt_19_1_);
         double lvt_21_1_ = lvt_20_1_.func_72433_c() + 1.0D;
         lvt_20_1_ = lvt_20_1_.func_72432_b();
         float lvt_23_1_ = (float)Math.acos(lvt_20_1_.field_72448_b);
         float lvt_24_1_ = (float)Math.atan2(lvt_20_1_.field_72449_c, lvt_20_1_.field_72450_a);
         GlStateManager.func_179114_b((1.5707964F + -lvt_24_1_) * 57.295776F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(lvt_23_1_ * 57.295776F, 1.0F, 0.0F, 0.0F);
         int lvt_25_1_ = 1;
         double lvt_26_1_ = (double)lvt_15_1_ * 0.05D * (1.0D - (double)(lvt_25_1_ & 1) * 2.5D);
         lvt_13_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         float lvt_28_1_ = lvt_11_1_ * lvt_11_1_;
         int lvt_29_1_ = 64 + (int)(lvt_28_1_ * 240.0F);
         int lvt_30_1_ = 32 + (int)(lvt_28_1_ * 192.0F);
         int lvt_31_1_ = 128 - (int)(lvt_28_1_ * 64.0F);
         double lvt_32_1_ = (double)lvt_25_1_ * 0.2D;
         double lvt_34_1_ = lvt_32_1_ * 1.41D;
         double lvt_36_1_ = 0.0D + Math.cos(lvt_26_1_ + 2.356194490192345D) * lvt_34_1_;
         double lvt_38_1_ = 0.0D + Math.sin(lvt_26_1_ + 2.356194490192345D) * lvt_34_1_;
         double lvt_40_1_ = 0.0D + Math.cos(lvt_26_1_ + 0.7853981633974483D) * lvt_34_1_;
         double lvt_42_1_ = 0.0D + Math.sin(lvt_26_1_ + 0.7853981633974483D) * lvt_34_1_;
         double lvt_44_1_ = 0.0D + Math.cos(lvt_26_1_ + 3.9269908169872414D) * lvt_34_1_;
         double lvt_46_1_ = 0.0D + Math.sin(lvt_26_1_ + 3.9269908169872414D) * lvt_34_1_;
         double lvt_48_1_ = 0.0D + Math.cos(lvt_26_1_ + 5.497787143782138D) * lvt_34_1_;
         double lvt_50_1_ = 0.0D + Math.sin(lvt_26_1_ + 5.497787143782138D) * lvt_34_1_;
         double lvt_52_1_ = 0.0D + Math.cos(lvt_26_1_ + 3.141592653589793D) * lvt_32_1_;
         double lvt_54_1_ = 0.0D + Math.sin(lvt_26_1_ + 3.141592653589793D) * lvt_32_1_;
         double lvt_56_1_ = 0.0D + Math.cos(lvt_26_1_ + 0.0D) * lvt_32_1_;
         double lvt_58_1_ = 0.0D + Math.sin(lvt_26_1_ + 0.0D) * lvt_32_1_;
         double lvt_60_1_ = 0.0D + Math.cos(lvt_26_1_ + 1.5707963267948966D) * lvt_32_1_;
         double lvt_62_1_ = 0.0D + Math.sin(lvt_26_1_ + 1.5707963267948966D) * lvt_32_1_;
         double lvt_64_1_ = 0.0D + Math.cos(lvt_26_1_ + 4.71238898038469D) * lvt_32_1_;
         double lvt_66_1_ = 0.0D + Math.sin(lvt_26_1_ + 4.71238898038469D) * lvt_32_1_;
         double lvt_70_1_ = 0.0D;
         double lvt_72_1_ = 0.4999D;
         double lvt_74_1_ = (double)(-1.0F + lvt_16_1_);
         double lvt_76_1_ = lvt_21_1_ * (0.5D / lvt_32_1_) + lvt_74_1_;
         lvt_13_1_.func_181662_b(lvt_52_1_, lvt_21_1_, lvt_54_1_).func_181673_a(0.4999D, lvt_76_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_52_1_, 0.0D, lvt_54_1_).func_181673_a(0.4999D, lvt_74_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_56_1_, 0.0D, lvt_58_1_).func_181673_a(0.0D, lvt_74_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_56_1_, lvt_21_1_, lvt_58_1_).func_181673_a(0.0D, lvt_76_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_60_1_, lvt_21_1_, lvt_62_1_).func_181673_a(0.4999D, lvt_76_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_60_1_, 0.0D, lvt_62_1_).func_181673_a(0.4999D, lvt_74_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_64_1_, 0.0D, lvt_66_1_).func_181673_a(0.0D, lvt_74_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_64_1_, lvt_21_1_, lvt_66_1_).func_181673_a(0.0D, lvt_76_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         double lvt_78_1_ = 0.0D;
         if (p_76986_1_.field_70173_aa % 2 == 0) {
            lvt_78_1_ = 0.5D;
         }

         lvt_13_1_.func_181662_b(lvt_36_1_, lvt_21_1_, lvt_38_1_).func_181673_a(0.5D, lvt_78_1_ + 0.5D).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_40_1_, lvt_21_1_, lvt_42_1_).func_181673_a(1.0D, lvt_78_1_ + 0.5D).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_48_1_, lvt_21_1_, lvt_50_1_).func_181673_a(1.0D, lvt_78_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_13_1_.func_181662_b(lvt_44_1_, lvt_21_1_, lvt_46_1_).func_181673_a(0.5D, lvt_78_1_).func_181669_b(lvt_29_1_, lvt_30_1_, lvt_31_1_, 255).func_181675_d();
         lvt_12_1_.func_78381_a();
         GlStateManager.func_179121_F();
      }

   }

   protected void func_77041_b(EntityGuardian p_77041_1_, float p_77041_2_) {
      if (p_77041_1_.func_175461_cl()) {
         GlStateManager.func_179152_a(2.35F, 2.35F, 2.35F);
      }

   }

   protected ResourceLocation func_110775_a(EntityGuardian p_110775_1_) {
      return p_110775_1_.func_175461_cl() ? field_177116_j : field_177114_e;
   }
}
