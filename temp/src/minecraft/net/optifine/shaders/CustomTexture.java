package net.optifine.shaders;

import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;

public class CustomTexture implements ICustomTexture {
   private int textureUnit = -1;
   private String path = null;
   private ITextureObject texture = null;

   public CustomTexture(int textureUnit, String path, ITextureObject texture) {
      this.textureUnit = textureUnit;
      this.path = path;
      this.texture = texture;
   }

   public int getTextureUnit() {
      return this.textureUnit;
   }

   public String getPath() {
      return this.path;
   }

   public ITextureObject getTexture() {
      return this.texture;
   }

   public int getTextureId() {
      return this.texture.func_110552_b();
   }

   public void deleteTexture() {
      TextureUtil.func_147942_a(this.texture.func_110552_b());
   }

   public int getTarget() {
      return 3553;
   }

   public String toString() {
      return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
   }
}
