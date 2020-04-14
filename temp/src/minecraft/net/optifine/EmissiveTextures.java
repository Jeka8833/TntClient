package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.PropertiesOrdered;

public class EmissiveTextures {
   private static String suffixEmissive = null;
   private static String suffixEmissivePng = null;
   private static boolean active = false;
   private static boolean render = false;
   private static boolean hasEmissive = false;
   private static boolean renderEmissive = false;
   private static float lightMapX;
   private static float lightMapY;
   private static final String SUFFIX_PNG = ".png";
   private static final ResourceLocation LOCATION_EMPTY = new ResourceLocation("mcpatcher/ctm/default/empty.png");

   public static boolean isActive() {
      return active;
   }

   public static String getSuffixEmissive() {
      return suffixEmissive;
   }

   public static void beginRender() {
      render = true;
      hasEmissive = false;
   }

   public static ITextureObject getEmissiveTexture(ITextureObject texture, Map<ResourceLocation, ITextureObject> mapTextures) {
      if (!render) {
         return texture;
      } else if (!(texture instanceof SimpleTexture)) {
         return texture;
      } else {
         SimpleTexture simpleTexture = (SimpleTexture)texture;
         ResourceLocation locationEmissive = simpleTexture.locationEmissive;
         if (!renderEmissive) {
            if (locationEmissive != null) {
               hasEmissive = true;
            }

            return texture;
         } else {
            if (locationEmissive == null) {
               locationEmissive = LOCATION_EMPTY;
            }

            ITextureObject textureEmissive = (ITextureObject)mapTextures.get(locationEmissive);
            if (textureEmissive == null) {
               textureEmissive = new SimpleTexture(locationEmissive);
               TextureManager textureManager = Config.getTextureManager();
               textureManager.func_110579_a(locationEmissive, (ITextureObject)textureEmissive);
            }

            return (ITextureObject)textureEmissive;
         }
      }
   }

   public static boolean hasEmissive() {
      return hasEmissive;
   }

   public static void beginRenderEmissive() {
      lightMapX = OpenGlHelper.lastBrightnessX;
      lightMapY = OpenGlHelper.lastBrightnessY;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, lightMapY);
      renderEmissive = true;
   }

   public static void endRenderEmissive() {
      renderEmissive = false;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, lightMapX, lightMapY);
   }

   public static void endRender() {
      render = false;
      hasEmissive = false;
   }

   public static void update() {
      active = false;
      suffixEmissive = null;
      suffixEmissivePng = null;
      if (Config.isEmissiveTextures()) {
         try {
            String fileName = "optifine/emissive.properties";
            ResourceLocation loc = new ResourceLocation(fileName);
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
               return;
            }

            dbg("Loading " + fileName);
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            suffixEmissive = props.getProperty("suffix.emissive");
            if (suffixEmissive != null) {
               suffixEmissivePng = suffixEmissive + ".png";
            }

            active = suffixEmissive != null;
         } catch (FileNotFoundException var4) {
            return;
         } catch (IOException var5) {
            var5.printStackTrace();
         }

      }
   }

   private static void dbg(String str) {
      Config.dbg("EmissiveTextures: " + str);
   }

   private static void warn(String str) {
      Config.warn("EmissiveTextures: " + str);
   }

   public static boolean isEmissive(ResourceLocation loc) {
      return suffixEmissivePng == null ? false : loc.func_110623_a().endsWith(suffixEmissivePng);
   }

   public static void loadTexture(ResourceLocation loc, SimpleTexture tex) {
      if (loc != null && tex != null) {
         tex.isEmissive = false;
         tex.locationEmissive = null;
         if (suffixEmissivePng != null) {
            String path = loc.func_110623_a();
            if (path.endsWith(".png")) {
               if (path.endsWith(suffixEmissivePng)) {
                  tex.isEmissive = true;
               } else {
                  String pathEmPng = path.substring(0, path.length() - ".png".length()) + suffixEmissivePng;
                  ResourceLocation locEmPng = new ResourceLocation(loc.func_110624_b(), pathEmPng);
                  if (Config.hasResource(locEmPng)) {
                     tex.locationEmissive = locEmPng;
                  }
               }
            }
         }
      }
   }
}
