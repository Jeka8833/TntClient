package net.minecraft.client.renderer.culling;

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class ClippingHelperImpl extends ClippingHelper {
   private static ClippingHelperImpl field_78563_e = new ClippingHelperImpl();
   private FloatBuffer field_78561_f = GLAllocation.func_74529_h(16);
   private FloatBuffer field_78562_g = GLAllocation.func_74529_h(16);
   private FloatBuffer field_78564_h = GLAllocation.func_74529_h(16);

   public static ClippingHelper func_78558_a() {
      field_78563_e.func_78560_b();
      return field_78563_e;
   }

   private void func_180547_a(float[] p_180547_1_) {
      float lvt_2_1_ = MathHelper.func_76129_c(p_180547_1_[0] * p_180547_1_[0] + p_180547_1_[1] * p_180547_1_[1] + p_180547_1_[2] * p_180547_1_[2]);
      p_180547_1_[0] /= lvt_2_1_;
      p_180547_1_[1] /= lvt_2_1_;
      p_180547_1_[2] /= lvt_2_1_;
      p_180547_1_[3] /= lvt_2_1_;
   }

   public void func_78560_b() {
      this.field_78561_f.clear();
      this.field_78562_g.clear();
      this.field_78564_h.clear();
      GlStateManager.func_179111_a(2983, this.field_78561_f);
      GlStateManager.func_179111_a(2982, this.field_78562_g);
      float[] lvt_1_1_ = this.field_178625_b;
      float[] lvt_2_1_ = this.field_178626_c;
      this.field_78561_f.flip().limit(16);
      this.field_78561_f.get(lvt_1_1_);
      this.field_78562_g.flip().limit(16);
      this.field_78562_g.get(lvt_2_1_);
      this.field_78554_d[0] = lvt_2_1_[0] * lvt_1_1_[0] + lvt_2_1_[1] * lvt_1_1_[4] + lvt_2_1_[2] * lvt_1_1_[8] + lvt_2_1_[3] * lvt_1_1_[12];
      this.field_78554_d[1] = lvt_2_1_[0] * lvt_1_1_[1] + lvt_2_1_[1] * lvt_1_1_[5] + lvt_2_1_[2] * lvt_1_1_[9] + lvt_2_1_[3] * lvt_1_1_[13];
      this.field_78554_d[2] = lvt_2_1_[0] * lvt_1_1_[2] + lvt_2_1_[1] * lvt_1_1_[6] + lvt_2_1_[2] * lvt_1_1_[10] + lvt_2_1_[3] * lvt_1_1_[14];
      this.field_78554_d[3] = lvt_2_1_[0] * lvt_1_1_[3] + lvt_2_1_[1] * lvt_1_1_[7] + lvt_2_1_[2] * lvt_1_1_[11] + lvt_2_1_[3] * lvt_1_1_[15];
      this.field_78554_d[4] = lvt_2_1_[4] * lvt_1_1_[0] + lvt_2_1_[5] * lvt_1_1_[4] + lvt_2_1_[6] * lvt_1_1_[8] + lvt_2_1_[7] * lvt_1_1_[12];
      this.field_78554_d[5] = lvt_2_1_[4] * lvt_1_1_[1] + lvt_2_1_[5] * lvt_1_1_[5] + lvt_2_1_[6] * lvt_1_1_[9] + lvt_2_1_[7] * lvt_1_1_[13];
      this.field_78554_d[6] = lvt_2_1_[4] * lvt_1_1_[2] + lvt_2_1_[5] * lvt_1_1_[6] + lvt_2_1_[6] * lvt_1_1_[10] + lvt_2_1_[7] * lvt_1_1_[14];
      this.field_78554_d[7] = lvt_2_1_[4] * lvt_1_1_[3] + lvt_2_1_[5] * lvt_1_1_[7] + lvt_2_1_[6] * lvt_1_1_[11] + lvt_2_1_[7] * lvt_1_1_[15];
      this.field_78554_d[8] = lvt_2_1_[8] * lvt_1_1_[0] + lvt_2_1_[9] * lvt_1_1_[4] + lvt_2_1_[10] * lvt_1_1_[8] + lvt_2_1_[11] * lvt_1_1_[12];
      this.field_78554_d[9] = lvt_2_1_[8] * lvt_1_1_[1] + lvt_2_1_[9] * lvt_1_1_[5] + lvt_2_1_[10] * lvt_1_1_[9] + lvt_2_1_[11] * lvt_1_1_[13];
      this.field_78554_d[10] = lvt_2_1_[8] * lvt_1_1_[2] + lvt_2_1_[9] * lvt_1_1_[6] + lvt_2_1_[10] * lvt_1_1_[10] + lvt_2_1_[11] * lvt_1_1_[14];
      this.field_78554_d[11] = lvt_2_1_[8] * lvt_1_1_[3] + lvt_2_1_[9] * lvt_1_1_[7] + lvt_2_1_[10] * lvt_1_1_[11] + lvt_2_1_[11] * lvt_1_1_[15];
      this.field_78554_d[12] = lvt_2_1_[12] * lvt_1_1_[0] + lvt_2_1_[13] * lvt_1_1_[4] + lvt_2_1_[14] * lvt_1_1_[8] + lvt_2_1_[15] * lvt_1_1_[12];
      this.field_78554_d[13] = lvt_2_1_[12] * lvt_1_1_[1] + lvt_2_1_[13] * lvt_1_1_[5] + lvt_2_1_[14] * lvt_1_1_[9] + lvt_2_1_[15] * lvt_1_1_[13];
      this.field_78554_d[14] = lvt_2_1_[12] * lvt_1_1_[2] + lvt_2_1_[13] * lvt_1_1_[6] + lvt_2_1_[14] * lvt_1_1_[10] + lvt_2_1_[15] * lvt_1_1_[14];
      this.field_78554_d[15] = lvt_2_1_[12] * lvt_1_1_[3] + lvt_2_1_[13] * lvt_1_1_[7] + lvt_2_1_[14] * lvt_1_1_[11] + lvt_2_1_[15] * lvt_1_1_[15];
      float[] lvt_3_1_ = this.field_78557_a[0];
      lvt_3_1_[0] = this.field_78554_d[3] - this.field_78554_d[0];
      lvt_3_1_[1] = this.field_78554_d[7] - this.field_78554_d[4];
      lvt_3_1_[2] = this.field_78554_d[11] - this.field_78554_d[8];
      lvt_3_1_[3] = this.field_78554_d[15] - this.field_78554_d[12];
      this.func_180547_a(lvt_3_1_);
      float[] lvt_4_1_ = this.field_78557_a[1];
      lvt_4_1_[0] = this.field_78554_d[3] + this.field_78554_d[0];
      lvt_4_1_[1] = this.field_78554_d[7] + this.field_78554_d[4];
      lvt_4_1_[2] = this.field_78554_d[11] + this.field_78554_d[8];
      lvt_4_1_[3] = this.field_78554_d[15] + this.field_78554_d[12];
      this.func_180547_a(lvt_4_1_);
      float[] lvt_5_1_ = this.field_78557_a[2];
      lvt_5_1_[0] = this.field_78554_d[3] + this.field_78554_d[1];
      lvt_5_1_[1] = this.field_78554_d[7] + this.field_78554_d[5];
      lvt_5_1_[2] = this.field_78554_d[11] + this.field_78554_d[9];
      lvt_5_1_[3] = this.field_78554_d[15] + this.field_78554_d[13];
      this.func_180547_a(lvt_5_1_);
      float[] lvt_6_1_ = this.field_78557_a[3];
      lvt_6_1_[0] = this.field_78554_d[3] - this.field_78554_d[1];
      lvt_6_1_[1] = this.field_78554_d[7] - this.field_78554_d[5];
      lvt_6_1_[2] = this.field_78554_d[11] - this.field_78554_d[9];
      lvt_6_1_[3] = this.field_78554_d[15] - this.field_78554_d[13];
      this.func_180547_a(lvt_6_1_);
      float[] lvt_7_1_ = this.field_78557_a[4];
      lvt_7_1_[0] = this.field_78554_d[3] - this.field_78554_d[2];
      lvt_7_1_[1] = this.field_78554_d[7] - this.field_78554_d[6];
      lvt_7_1_[2] = this.field_78554_d[11] - this.field_78554_d[10];
      lvt_7_1_[3] = this.field_78554_d[15] - this.field_78554_d[14];
      this.func_180547_a(lvt_7_1_);
      float[] lvt_8_1_ = this.field_78557_a[5];
      lvt_8_1_[0] = this.field_78554_d[3] + this.field_78554_d[2];
      lvt_8_1_[1] = this.field_78554_d[7] + this.field_78554_d[6];
      lvt_8_1_[2] = this.field_78554_d[11] + this.field_78554_d[10];
      lvt_8_1_[3] = this.field_78554_d[15] + this.field_78554_d[14];
      this.func_180547_a(lvt_8_1_);
   }
}
