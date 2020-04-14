package net.minecraft.client.shader;

import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Framebuffer {
   public int field_147622_a;
   public int field_147620_b;
   public int field_147621_c;
   public int field_147618_d;
   public boolean field_147619_e;
   public int field_147616_f;
   public int field_147617_g;
   public int field_147624_h;
   public float[] field_147625_i;
   public int field_147623_j;

   public Framebuffer(int p_i45078_1_, int p_i45078_2_, boolean p_i45078_3_) {
      this.field_147619_e = p_i45078_3_;
      this.field_147616_f = -1;
      this.field_147617_g = -1;
      this.field_147624_h = -1;
      this.field_147625_i = new float[4];
      this.field_147625_i[0] = 1.0F;
      this.field_147625_i[1] = 1.0F;
      this.field_147625_i[2] = 1.0F;
      this.field_147625_i[3] = 0.0F;
      this.func_147613_a(p_i45078_1_, p_i45078_2_);
   }

   public void func_147613_a(int p_147613_1_, int p_147613_2_) {
      if (!OpenGlHelper.func_148822_b()) {
         this.field_147621_c = p_147613_1_;
         this.field_147618_d = p_147613_2_;
      } else {
         GlStateManager.func_179126_j();
         if (this.field_147616_f >= 0) {
            this.func_147608_a();
         }

         this.func_147605_b(p_147613_1_, p_147613_2_);
         this.func_147611_b();
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
      }
   }

   public void func_147608_a() {
      if (OpenGlHelper.func_148822_b()) {
         this.func_147606_d();
         this.func_147609_e();
         if (this.field_147624_h > -1) {
            OpenGlHelper.func_153184_g(this.field_147624_h);
            this.field_147624_h = -1;
         }

         if (this.field_147617_g > -1) {
            TextureUtil.func_147942_a(this.field_147617_g);
            this.field_147617_g = -1;
         }

         if (this.field_147616_f > -1) {
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
            OpenGlHelper.func_153174_h(this.field_147616_f);
            this.field_147616_f = -1;
         }

      }
   }

   public void func_147605_b(int p_147605_1_, int p_147605_2_) {
      this.field_147621_c = p_147605_1_;
      this.field_147618_d = p_147605_2_;
      this.field_147622_a = p_147605_1_;
      this.field_147620_b = p_147605_2_;
      if (!OpenGlHelper.func_148822_b()) {
         this.func_147614_f();
      } else {
         this.field_147616_f = OpenGlHelper.func_153165_e();
         this.field_147617_g = TextureUtil.func_110996_a();
         if (this.field_147619_e) {
            this.field_147624_h = OpenGlHelper.func_153185_f();
         }

         this.func_147607_a(9728);
         GlStateManager.func_179144_i(this.field_147617_g);
         GL11.glTexImage2D(3553, 0, 32856, this.field_147622_a, this.field_147620_b, 0, 6408, 5121, (ByteBuffer)((ByteBuffer)null));
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.field_147616_f);
         OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, this.field_147617_g, 0);
         if (this.field_147619_e) {
            OpenGlHelper.func_153176_h(OpenGlHelper.field_153199_f, this.field_147624_h);
            OpenGlHelper.func_153186_a(OpenGlHelper.field_153199_f, 33190, this.field_147622_a, this.field_147620_b);
            OpenGlHelper.func_153190_b(OpenGlHelper.field_153198_e, OpenGlHelper.field_153201_h, OpenGlHelper.field_153199_f, this.field_147624_h);
         }

         this.func_147614_f();
         this.func_147606_d();
      }
   }

   public void func_147607_a(int p_147607_1_) {
      if (OpenGlHelper.func_148822_b()) {
         this.field_147623_j = p_147607_1_;
         GlStateManager.func_179144_i(this.field_147617_g);
         GL11.glTexParameterf(3553, 10241, (float)p_147607_1_);
         GL11.glTexParameterf(3553, 10240, (float)p_147607_1_);
         GL11.glTexParameterf(3553, 10242, 10496.0F);
         GL11.glTexParameterf(3553, 10243, 10496.0F);
         GlStateManager.func_179144_i(0);
      }

   }

   public void func_147611_b() {
      int lvt_1_1_ = OpenGlHelper.func_153167_i(OpenGlHelper.field_153198_e);
      if (lvt_1_1_ != OpenGlHelper.field_153202_i) {
         if (lvt_1_1_ == OpenGlHelper.field_153203_j) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
         } else if (lvt_1_1_ == OpenGlHelper.field_153204_k) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
         } else if (lvt_1_1_ == OpenGlHelper.field_153205_l) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
         } else if (lvt_1_1_ == OpenGlHelper.field_153206_m) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
         } else {
            throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + lvt_1_1_);
         }
      }
   }

   public void func_147612_c() {
      if (OpenGlHelper.func_148822_b()) {
         GlStateManager.func_179144_i(this.field_147617_g);
      }

   }

   public void func_147606_d() {
      if (OpenGlHelper.func_148822_b()) {
         GlStateManager.func_179144_i(0);
      }

   }

   public void func_147610_a(boolean p_147610_1_) {
      if (OpenGlHelper.func_148822_b()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.field_147616_f);
         if (p_147610_1_) {
            GlStateManager.func_179083_b(0, 0, this.field_147621_c, this.field_147618_d);
         }
      }

   }

   public void func_147609_e() {
      if (OpenGlHelper.func_148822_b()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
      }

   }

   public void func_147604_a(float p_147604_1_, float p_147604_2_, float p_147604_3_, float p_147604_4_) {
      this.field_147625_i[0] = p_147604_1_;
      this.field_147625_i[1] = p_147604_2_;
      this.field_147625_i[2] = p_147604_3_;
      this.field_147625_i[3] = p_147604_4_;
   }

   public void func_147615_c(int p_147615_1_, int p_147615_2_) {
      this.func_178038_a(p_147615_1_, p_147615_2_, true);
   }

   public void func_178038_a(int p_178038_1_, int p_178038_2_, boolean p_178038_3_) {
      if (OpenGlHelper.func_148822_b()) {
         GlStateManager.func_179135_a(true, true, true, false);
         GlStateManager.func_179097_i();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         GlStateManager.func_179130_a(0.0D, (double)p_178038_1_, (double)p_178038_2_, 0.0D, 1000.0D, 3000.0D);
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179096_D();
         GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
         GlStateManager.func_179083_b(0, 0, p_178038_1_, p_178038_2_);
         GlStateManager.func_179098_w();
         GlStateManager.func_179140_f();
         GlStateManager.func_179118_c();
         if (p_178038_3_) {
            GlStateManager.func_179084_k();
            GlStateManager.func_179142_g();
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_147612_c();
         float lvt_4_1_ = (float)p_178038_1_;
         float lvt_5_1_ = (float)p_178038_2_;
         float lvt_6_1_ = (float)this.field_147621_c / (float)this.field_147622_a;
         float lvt_7_1_ = (float)this.field_147618_d / (float)this.field_147620_b;
         Tessellator lvt_8_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_9_1_ = lvt_8_1_.func_178180_c();
         lvt_9_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         lvt_9_1_.func_181662_b(0.0D, (double)lvt_5_1_, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
         lvt_9_1_.func_181662_b((double)lvt_4_1_, (double)lvt_5_1_, 0.0D).func_181673_a((double)lvt_6_1_, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
         lvt_9_1_.func_181662_b((double)lvt_4_1_, 0.0D, 0.0D).func_181673_a((double)lvt_6_1_, (double)lvt_7_1_).func_181669_b(255, 255, 255, 255).func_181675_d();
         lvt_9_1_.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, (double)lvt_7_1_).func_181669_b(255, 255, 255, 255).func_181675_d();
         lvt_8_1_.func_78381_a();
         this.func_147606_d();
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179135_a(true, true, true, true);
      }
   }

   public void func_147614_f() {
      this.func_147610_a(true);
      GlStateManager.func_179082_a(this.field_147625_i[0], this.field_147625_i[1], this.field_147625_i[2], this.field_147625_i[3]);
      int lvt_1_1_ = 16384;
      if (this.field_147619_e) {
         GlStateManager.func_179151_a(1.0D);
         lvt_1_1_ |= 256;
      }

      GlStateManager.func_179086_m(lvt_1_1_);
      this.func_147609_e();
   }
}
