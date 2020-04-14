package net.optifine.shaders;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import org.apache.commons.io.IOUtils;

public class SimpleShaderTexture extends AbstractTexture {
   private String texturePath;
   private static final IMetadataSerializer METADATA_SERIALIZER = makeMetadataSerializer();

   public SimpleShaderTexture(String texturePath) {
      this.texturePath = texturePath;
   }

   public void func_110551_a(IResourceManager resourceManager) throws IOException {
      this.func_147631_c();
      InputStream inputStream = Shaders.getShaderPackResourceStream(this.texturePath);
      if (inputStream == null) {
         throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
      } else {
         try {
            BufferedImage bufferedimage = TextureUtil.func_177053_a(inputStream);
            TextureMetadataSection tms = this.loadTextureMetadataSection();
            TextureUtil.func_110989_a(this.func_110552_b(), bufferedimage, tms.func_110479_a(), tms.func_110480_b());
         } finally {
            IOUtils.closeQuietly(inputStream);
         }

      }
   }

   private TextureMetadataSection loadTextureMetadataSection() {
      String pathMeta = this.texturePath + ".mcmeta";
      String sectionName = "texture";
      InputStream inMeta = Shaders.getShaderPackResourceStream(pathMeta);
      if (inMeta != null) {
         IMetadataSerializer ms = METADATA_SERIALIZER;
         BufferedReader brMeta = new BufferedReader(new InputStreamReader(inMeta));

         try {
            JsonObject jsonMeta = (new JsonParser()).parse((Reader)brMeta).getAsJsonObject();
            TextureMetadataSection meta = (TextureMetadataSection)ms.func_110503_a(sectionName, jsonMeta);
            if (meta != null) {
               TextureMetadataSection var8 = meta;
               return var8;
            }
         } catch (RuntimeException var12) {
            SMCLog.warning("Error reading metadata: " + pathMeta);
            SMCLog.warning("" + var12.getClass().getName() + ": " + var12.getMessage());
         } finally {
            IOUtils.closeQuietly((Reader)brMeta);
            IOUtils.closeQuietly(inMeta);
         }
      }

      return new TextureMetadataSection(false, false, new ArrayList());
   }

   private static IMetadataSerializer makeMetadataSerializer() {
      IMetadataSerializer ms = new IMetadataSerializer();
      ms.func_110504_a(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      ms.func_110504_a(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      ms.func_110504_a(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      ms.func_110504_a(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      ms.func_110504_a(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
      return ms;
   }
}
