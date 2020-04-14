package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GLAllocation {
   public static synchronized int func_74526_a(int p_74526_0_) {
      int lvt_1_1_ = GL11.glGenLists(p_74526_0_);
      if (lvt_1_1_ == 0) {
         int lvt_2_1_ = GL11.glGetError();
         String lvt_3_1_ = "No error code reported";
         if (lvt_2_1_ != 0) {
            lvt_3_1_ = GLU.gluErrorString(lvt_2_1_);
         }

         throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + p_74526_0_ + ", GL error (" + lvt_2_1_ + "): " + lvt_3_1_);
      } else {
         return lvt_1_1_;
      }
   }

   public static synchronized void func_178874_a(int p_178874_0_, int p_178874_1_) {
      GL11.glDeleteLists(p_178874_0_, p_178874_1_);
   }

   public static synchronized void func_74523_b(int p_74523_0_) {
      GL11.glDeleteLists(p_74523_0_, 1);
   }

   public static synchronized ByteBuffer func_74524_c(int p_74524_0_) {
      return ByteBuffer.allocateDirect(p_74524_0_).order(ByteOrder.nativeOrder());
   }

   public static IntBuffer func_74527_f(int p_74527_0_) {
      return func_74524_c(p_74527_0_ << 2).asIntBuffer();
   }

   public static FloatBuffer func_74529_h(int p_74529_0_) {
      return func_74524_c(p_74529_0_ << 2).asFloatBuffer();
   }
}
