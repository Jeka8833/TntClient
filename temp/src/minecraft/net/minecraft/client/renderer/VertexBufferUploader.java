package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.src.Config;

public class VertexBufferUploader extends WorldVertexBufferUploader {
   private VertexBuffer field_178179_a = null;

   public void func_181679_a(WorldRenderer p_181679_1_) {
      if (p_181679_1_.func_178979_i() == 7 && Config.isQuadsToTriangles()) {
         p_181679_1_.quadsToTriangles();
         this.field_178179_a.setDrawMode(p_181679_1_.func_178979_i());
      }

      this.field_178179_a.func_181722_a(p_181679_1_.func_178966_f());
      p_181679_1_.func_178965_a();
   }

   public void func_178178_a(VertexBuffer p_178178_1_) {
      this.field_178179_a = p_178178_1_;
   }
}
