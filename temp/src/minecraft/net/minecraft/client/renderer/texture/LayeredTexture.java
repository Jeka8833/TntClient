package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LayeredTexture extends AbstractTexture {
   private static final Logger field_147638_c = LogManager.getLogger();
   public final List<String> field_110567_b;
   private ResourceLocation textureLocation;

   public LayeredTexture(String... p_i1274_1_) {
      this.field_110567_b = Lists.newArrayList((Object[])p_i1274_1_);
      if (p_i1274_1_.length > 0 && p_i1274_1_[0] != null) {
         this.textureLocation = new ResourceLocation(p_i1274_1_[0]);
      }

   }

   public void func_110551_a(IResourceManager p_110551_1_) throws IOException {
      this.func_147631_c();
      BufferedImage bufferedimage = null;

      try {
         Iterator i$ = this.field_110567_b.iterator();

         while(i$.hasNext()) {
            String s = (String)i$.next();
            if (s != null) {
               InputStream inputstream = p_110551_1_.func_110536_a(new ResourceLocation(s)).func_110527_b();
               BufferedImage bufferedimage1 = TextureUtil.func_177053_a(inputstream);
               if (bufferedimage == null) {
                  bufferedimage = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), 2);
               }

               bufferedimage.getGraphics().drawImage(bufferedimage1, 0, 0, (ImageObserver)null);
            }
         }
      } catch (IOException var7) {
         field_147638_c.error((String)"Couldn't load layered image", (Throwable)var7);
         return;
      }

      if (Config.isShaders()) {
         ShadersTex.loadSimpleTexture(this.func_110552_b(), bufferedimage, false, false, p_110551_1_, this.textureLocation, this.getMultiTexID());
      } else {
         TextureUtil.func_110987_a(this.func_110552_b(), bufferedimage);
      }

   }
}
