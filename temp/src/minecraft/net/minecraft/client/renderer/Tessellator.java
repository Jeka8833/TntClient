package net.minecraft.client.renderer;

import net.optifine.SmartAnimations;

public class Tessellator {
   private WorldRenderer field_178183_a;
   private WorldVertexBufferUploader field_178182_b = new WorldVertexBufferUploader();
   private static final Tessellator field_78398_a = new Tessellator(2097152);

   public static Tessellator func_178181_a() {
      return field_78398_a;
   }

   public Tessellator(int p_i1250_1_) {
      this.field_178183_a = new WorldRenderer(p_i1250_1_);
   }

   public void func_78381_a() {
      if (this.field_178183_a.animatedSprites != null) {
         SmartAnimations.spritesRendered(this.field_178183_a.animatedSprites);
      }

      this.field_178183_a.func_178977_d();
      this.field_178182_b.func_181679_a(this.field_178183_a);
   }

   public WorldRenderer func_178180_c() {
      return this.field_178183_a;
   }
}
