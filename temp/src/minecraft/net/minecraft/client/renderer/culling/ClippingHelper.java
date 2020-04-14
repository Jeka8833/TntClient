package net.minecraft.client.renderer.culling;

public class ClippingHelper {
   public float[][] field_78557_a = new float[6][4];
   public float[] field_178625_b = new float[16];
   public float[] field_178626_c = new float[16];
   public float[] field_78554_d = new float[16];
   public boolean disabled = false;

   private float dot(float[] p_dot_1_, float p_dot_2_, float p_dot_3_, float p_dot_4_) {
      return p_dot_1_[0] * p_dot_2_ + p_dot_1_[1] * p_dot_3_ + p_dot_1_[2] * p_dot_4_ + p_dot_1_[3];
   }

   public boolean func_78553_b(double p_78553_1_, double p_78553_3_, double p_78553_5_, double p_78553_7_, double p_78553_9_, double p_78553_11_) {
      if (this.disabled) {
         return true;
      } else {
         float minXf = (float)p_78553_1_;
         float minYf = (float)p_78553_3_;
         float minZf = (float)p_78553_5_;
         float maxXf = (float)p_78553_7_;
         float maxYf = (float)p_78553_9_;
         float maxZf = (float)p_78553_11_;

         for(int var13 = 0; var13 < 6; ++var13) {
            float[] frustumi = this.field_78557_a[var13];
            float frustumi0 = frustumi[0];
            float frustumi1 = frustumi[1];
            float frustumi2 = frustumi[2];
            float frustumi3 = frustumi[3];
            if (frustumi0 * minXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean isBoxInFrustumFully(double p_isBoxInFrustumFully_1_, double p_isBoxInFrustumFully_3_, double p_isBoxInFrustumFully_5_, double p_isBoxInFrustumFully_7_, double p_isBoxInFrustumFully_9_, double p_isBoxInFrustumFully_11_) {
      if (this.disabled) {
         return true;
      } else {
         float minXf = (float)p_isBoxInFrustumFully_1_;
         float minYf = (float)p_isBoxInFrustumFully_3_;
         float minZf = (float)p_isBoxInFrustumFully_5_;
         float maxXf = (float)p_isBoxInFrustumFully_7_;
         float maxYf = (float)p_isBoxInFrustumFully_9_;
         float maxZf = (float)p_isBoxInFrustumFully_11_;

         for(int i = 0; i < 6; ++i) {
            float[] frustumi = this.field_78557_a[i];
            float frustumi0 = frustumi[0];
            float frustumi1 = frustumi[1];
            float frustumi2 = frustumi[2];
            float frustumi3 = frustumi[3];
            if (i < 4) {
               if (frustumi0 * minXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F || frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F || frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F || frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F || frustumi0 * minXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F || frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F || frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F || frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F) {
                  return false;
               }
            } else if (frustumi0 * minXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * minZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * minYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * minXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F && frustumi0 * maxXf + frustumi1 * maxYf + frustumi2 * maxZf + frustumi3 <= 0.0F) {
               return false;
            }
         }

         return true;
      }
   }
}
