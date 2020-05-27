package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.EmissiveTextures;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleTexture extends AbstractTexture {
   private static final Logger field_147639_c = LogManager.getLogger();
   protected final ResourceLocation field_110568_b;
   public ResourceLocation locationEmissive;
   public boolean isEmissive;

   public SimpleTexture(ResourceLocation p_i1275_1_) {
      this.field_110568_b = p_i1275_1_;
   }

   public void func_110551_a(IResourceManager p_110551_1_) throws IOException {
      this.func_147631_c();
      InputStream inputstream = null;

      try {
         IResource iresource = p_110551_1_.func_110536_a(this.field_110568_b);
         inputstream = iresource.func_110527_b();
         BufferedImage bufferedimage = TextureUtil.func_177053_a(inputstream);
         boolean flag = false;
         boolean flag1 = false;
         if (iresource.func_110528_c()) {
            try {
               TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.func_110526_a("texture");
               if (texturemetadatasection != null) {
                  flag = texturemetadatasection.func_110479_a();
                  flag1 = texturemetadatasection.func_110480_b();
               }
            } catch (RuntimeException var11) {
               field_147639_c.warn((String)("Failed reading metadata of: " + this.field_110568_b), (Throwable)var11);
            }
         }

         if (Config.isShaders()) {
            ShadersTex.loadSimpleTexture(this.func_110552_b(), bufferedimage, flag, flag1, p_110551_1_, this.field_110568_b, this.getMultiTexID());
         } else {
            TextureUtil.func_110989_a(this.func_110552_b(), bufferedimage, flag, flag1);
         }

         if (EmissiveTextures.isActive()) {
            EmissiveTextures.loadTexture(this.field_110568_b, this);
         }
      } finally {
         if (inputstream != null) {
            inputstream.close();
         }

      }

   }
}