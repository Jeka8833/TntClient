package net.minecraft.client.shader;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import org.lwjgl.util.vector.Matrix4f;

public class Shader {
   private final ShaderManager field_148051_c;
   public final Framebuffer field_148052_a;
   public final Framebuffer field_148050_b;
   private final List<Object> field_148048_d = Lists.newArrayList();
   private final List<String> field_148049_e = Lists.newArrayList();
   private final List<Integer> field_148046_f = Lists.newArrayList();
   private final List<Integer> field_148047_g = Lists.newArrayList();
   private Matrix4f field_148053_h;

   public Shader(IResourceManager p_i45089_1_, String p_i45089_2_, Framebuffer p_i45089_3_, Framebuffer p_i45089_4_) throws JsonException, IOException {
      this.field_148051_c = new ShaderManager(p_i45089_1_, p_i45089_2_);
      this.field_148052_a = p_i45089_3_;
      this.field_148050_b = p_i45089_4_;
   }

   public void func_148044_b() {
      this.field_148051_c.func_147988_a();
   }

   public void func_148041_a(String p_148041_1_, Object p_148041_2_, int p_148041_3_, int p_148041_4_) {
      this.field_148049_e.add(this.field_148049_e.size(), p_148041_1_);
      this.field_148048_d.add(this.field_148048_d.size(), p_148041_2_);
      this.field_148046_f.add(this.field_148046_f.size(), p_148041_3_);
      this.field_148047_g.add(this.field_148047_g.size(), p_148041_4_);
   }

   private void func_148040_d() {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179097_i();
      GlStateManager.func_179118_c();
      GlStateManager.func_179106_n();
      GlStateManager.func_179140_f();
      GlStateManager.func_179119_h();
      GlStateManager.func_179098_w();
      GlStateManager.func_179144_i(0);
   }

   public void func_148045_a(Matrix4f p_148045_1_) {
      this.field_148053_h = p_148045_1_;
   }

   public void func_148042_a(float p_148042_1_) {
      this.func_148040_d();
      this.field_148052_a.func_147609_e();
      float lvt_2_1_ = (float)this.field_148050_b.field_147622_a;
      float lvt_3_1_ = (float)this.field_148050_b.field_147620_b;
      GlStateManager.func_179083_b(0, 0, (int)lvt_2_1_, (int)lvt_3_1_);
      this.field_148051_c.func_147992_a("DiffuseSampler", this.field_148052_a);

      for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_148048_d.size(); ++lvt_4_1_) {
         this.field_148051_c.func_147992_a((String)this.field_148049_e.get(lvt_4_1_), this.field_148048_d.get(lvt_4_1_));
         this.field_148051_c.func_147984_b("AuxSize" + lvt_4_1_).func_148087_a((float)(Integer)this.field_148046_f.get(lvt_4_1_), (float)(Integer)this.field_148047_g.get(lvt_4_1_));
      }

      this.field_148051_c.func_147984_b("ProjMat").func_148088_a(this.field_148053_h);
      this.field_148051_c.func_147984_b("InSize").func_148087_a((float)this.field_148052_a.field_147622_a, (float)this.field_148052_a.field_147620_b);
      this.field_148051_c.func_147984_b("OutSize").func_148087_a(lvt_2_1_, lvt_3_1_);
      this.field_148051_c.func_147984_b("Time").func_148090_a(p_148042_1_);
      Minecraft lvt_4_2_ = Minecraft.func_71410_x();
      this.field_148051_c.func_147984_b("ScreenSize").func_148087_a((float)lvt_4_2_.field_71443_c, (float)lvt_4_2_.field_71440_d);
      this.field_148051_c.func_147995_c();
      this.field_148050_b.func_147614_f();
      this.field_148050_b.func_147610_a(false);
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179135_a(true, true, true, true);
      Tessellator lvt_5_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_6_1_ = lvt_5_1_.func_178180_c();
      lvt_6_1_.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      lvt_6_1_.func_181662_b(0.0D, (double)lvt_3_1_, 500.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      lvt_6_1_.func_181662_b((double)lvt_2_1_, (double)lvt_3_1_, 500.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      lvt_6_1_.func_181662_b((double)lvt_2_1_, 0.0D, 500.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      lvt_6_1_.func_181662_b(0.0D, 0.0D, 500.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      lvt_5_1_.func_78381_a();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179135_a(true, true, true, true);
      this.field_148051_c.func_147993_b();
      this.field_148050_b.func_147609_e();
      this.field_148052_a.func_147606_d();
      Iterator lvt_7_1_ = this.field_148048_d.iterator();

      while(lvt_7_1_.hasNext()) {
         Object lvt_8_1_ = lvt_7_1_.next();
         if (lvt_8_1_ instanceof Framebuffer) {
            ((Framebuffer)lvt_8_1_).func_147606_d();
         }
      }

   }

   public ShaderManager func_148043_c() {
      return this.field_148051_c;
   }
}
