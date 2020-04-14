package net.minecraft.src;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.DynamicLights;
import net.optifine.GlErrors;
import net.optifine.VersionCheckThread;
import net.optifine.config.GlVersion;
import net.optifine.gui.GuiMessage;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;
import net.optifine.util.DisplayModeComparator;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

public class Config {
   public static final String OF_NAME = "OptiFine";
   public static final String MC_VERSION = "1.8.9";
   public static final String OF_EDITION = "HD_U";
   public static final String OF_RELEASE = "L5";
   public static final String VERSION = "OptiFine_1.8.9_HD_U_L5";
   private static String build = null;
   private static String newRelease = null;
   private static boolean notify64BitJava = false;
   public static String openGlVersion = null;
   public static String openGlRenderer = null;
   public static String openGlVendor = null;
   public static String[] openGlExtensions = null;
   public static GlVersion glVersion = null;
   public static GlVersion glslVersion = null;
   public static int minecraftVersionInt = -1;
   public static boolean fancyFogAvailable = false;
   public static boolean occlusionAvailable = false;
   private static GameSettings gameSettings = null;
   private static Minecraft minecraft = Minecraft.func_71410_x();
   private static boolean initialized = false;
   private static Thread minecraftThread = null;
   private static DisplayMode desktopDisplayMode = null;
   private static DisplayMode[] displayModes = null;
   private static int antialiasingLevel = 0;
   private static int availableProcessors = 0;
   public static boolean zoomMode = false;
   private static int texturePackClouds = 0;
   public static boolean waterOpacityChanged = false;
   private static boolean fullscreenModeChecked = false;
   private static boolean desktopModeChecked = false;
   private static DefaultResourcePack defaultResourcePackLazy = null;
   public static final Float DEF_ALPHA_FUNC_LEVEL = 0.1F;
   private static final Logger LOGGER = LogManager.getLogger();
   public static final boolean logDetail = System.getProperty("log.detail", "false").equals("true");
   private static String mcDebugLast = null;
   private static int fpsMinLast = 0;
   public static float renderPartialTicks;

   private Config() {
   }

   public static String getVersion() {
      return "OptiFine_1.8.9_HD_U_L5";
   }

   public static String getVersionDebug() {
      StringBuffer sb = new StringBuffer(32);
      if (isDynamicLights()) {
         sb.append("DL: ");
         sb.append(String.valueOf(DynamicLights.getCount()));
         sb.append(", ");
      }

      sb.append("OptiFine_1.8.9_HD_U_L5");
      String shaderPack = Shaders.getShaderPackName();
      if (shaderPack != null) {
         sb.append(", ");
         sb.append(shaderPack);
      }

      return sb.toString();
   }

   public static void initGameSettings(GameSettings p_initGameSettings_0_) {
      if (gameSettings == null) {
         gameSettings = p_initGameSettings_0_;
         desktopDisplayMode = Display.getDesktopDisplayMode();
         updateAvailableProcessors();
         ReflectorForge.putLaunchBlackboard("optifine.ForgeSplashCompatible", Boolean.TRUE);
      }
   }

   public static void initDisplay() {
      checkInitialized();
      antialiasingLevel = gameSettings.ofAaLevel;
      checkDisplaySettings();
      checkDisplayMode();
      minecraftThread = Thread.currentThread();
      updateThreadPriorities();
      Shaders.startup(Minecraft.func_71410_x());
   }

   public static void checkInitialized() {
      if (!initialized) {
         if (Display.isCreated()) {
            initialized = true;
            checkOpenGlCaps();
            startVersionCheckThread();
         }
      }
   }

   private static void checkOpenGlCaps() {
      log("");
      log(getVersion());
      log("Build: " + getBuild());
      log("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
      log("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
      log("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
      log("LWJGL: " + Sys.getVersion());
      openGlVersion = GL11.glGetString(7938);
      openGlRenderer = GL11.glGetString(7937);
      openGlVendor = GL11.glGetString(7936);
      log("OpenGL: " + openGlRenderer + ", version " + openGlVersion + ", " + openGlVendor);
      log("OpenGL Version: " + getOpenGlVersionString());
      if (!GLContext.getCapabilities().OpenGL12) {
         log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
      }

      fancyFogAvailable = GLContext.getCapabilities().GL_NV_fog_distance;
      if (!fancyFogAvailable) {
         log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
      }

      occlusionAvailable = GLContext.getCapabilities().GL_ARB_occlusion_query;
      if (!occlusionAvailable) {
         log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
      }

      int maxTexSize = TextureUtils.getGLMaximumTextureSize();
      dbg("Maximum texture size: " + maxTexSize + "x" + maxTexSize);
   }

   public static String getBuild() {
      if (build == null) {
         try {
            InputStream in = Config.class.getResourceAsStream("/buildof.txt");
            if (in == null) {
               return null;
            }

            build = readLines(in)[0];
         } catch (Exception var1) {
            warn("" + var1.getClass().getName() + ": " + var1.getMessage());
            build = "";
         }
      }

      return build;
   }

   public static boolean isFancyFogAvailable() {
      return fancyFogAvailable;
   }

   public static boolean isOcclusionAvailable() {
      return occlusionAvailable;
   }

   public static int getMinecraftVersionInt() {
      if (minecraftVersionInt < 0) {
         String[] verStrs = tokenize("1.8.9", ".");
         int ver = 0;
         if (verStrs.length > 0) {
            ver += 10000 * parseInt(verStrs[0], 0);
         }

         if (verStrs.length > 1) {
            ver += 100 * parseInt(verStrs[1], 0);
         }

         if (verStrs.length > 2) {
            ver += 1 * parseInt(verStrs[2], 0);
         }

         minecraftVersionInt = ver;
      }

      return minecraftVersionInt;
   }

   public static String getOpenGlVersionString() {
      GlVersion ver = getGlVersion();
      String verStr = "" + ver.getMajor() + "." + ver.getMinor() + "." + ver.getRelease();
      return verStr;
   }

   private static GlVersion getGlVersionLwjgl() {
      if (GLContext.getCapabilities().OpenGL44) {
         return new GlVersion(4, 4);
      } else if (GLContext.getCapabilities().OpenGL43) {
         return new GlVersion(4, 3);
      } else if (GLContext.getCapabilities().OpenGL42) {
         return new GlVersion(4, 2);
      } else if (GLContext.getCapabilities().OpenGL41) {
         return new GlVersion(4, 1);
      } else if (GLContext.getCapabilities().OpenGL40) {
         return new GlVersion(4, 0);
      } else if (GLContext.getCapabilities().OpenGL33) {
         return new GlVersion(3, 3);
      } else if (GLContext.getCapabilities().OpenGL32) {
         return new GlVersion(3, 2);
      } else if (GLContext.getCapabilities().OpenGL31) {
         return new GlVersion(3, 1);
      } else if (GLContext.getCapabilities().OpenGL30) {
         return new GlVersion(3, 0);
      } else if (GLContext.getCapabilities().OpenGL21) {
         return new GlVersion(2, 1);
      } else if (GLContext.getCapabilities().OpenGL20) {
         return new GlVersion(2, 0);
      } else if (GLContext.getCapabilities().OpenGL15) {
         return new GlVersion(1, 5);
      } else if (GLContext.getCapabilities().OpenGL14) {
         return new GlVersion(1, 4);
      } else if (GLContext.getCapabilities().OpenGL13) {
         return new GlVersion(1, 3);
      } else if (GLContext.getCapabilities().OpenGL12) {
         return new GlVersion(1, 2);
      } else {
         return GLContext.getCapabilities().OpenGL11 ? new GlVersion(1, 1) : new GlVersion(1, 0);
      }
   }

   public static GlVersion getGlVersion() {
      if (glVersion == null) {
         String verStr = GL11.glGetString(7938);
         glVersion = parseGlVersion(verStr, (GlVersion)null);
         if (glVersion == null) {
            glVersion = getGlVersionLwjgl();
         }

         if (glVersion == null) {
            glVersion = new GlVersion(1, 0);
         }
      }

      return glVersion;
   }

   public static GlVersion getGlslVersion() {
      if (glslVersion == null) {
         String verStr = GL11.glGetString(35724);
         glslVersion = parseGlVersion(verStr, (GlVersion)null);
         if (glslVersion == null) {
            glslVersion = new GlVersion(1, 10);
         }
      }

      return glslVersion;
   }

   public static GlVersion parseGlVersion(String p_parseGlVersion_0_, GlVersion p_parseGlVersion_1_) {
      try {
         if (p_parseGlVersion_0_ == null) {
            return p_parseGlVersion_1_;
         } else {
            Pattern REGEXP_VERSION = Pattern.compile("([0-9]+)\\.([0-9]+)(\\.([0-9]+))?(.+)?");
            Matcher matcher = REGEXP_VERSION.matcher(p_parseGlVersion_0_);
            if (!matcher.matches()) {
               return p_parseGlVersion_1_;
            } else {
               int major = Integer.parseInt(matcher.group(1));
               int minor = Integer.parseInt(matcher.group(2));
               int release = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
               String suffix = matcher.group(5);
               return new GlVersion(major, minor, release, suffix);
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         return p_parseGlVersion_1_;
      }
   }

   public static String[] getOpenGlExtensions() {
      if (openGlExtensions == null) {
         openGlExtensions = detectOpenGlExtensions();
      }

      return openGlExtensions;
   }

   private static String[] detectOpenGlExtensions() {
      try {
         GlVersion ver = getGlVersion();
         if (ver.getMajor() >= 3) {
            int countExt = GL11.glGetInteger(33309);
            if (countExt > 0) {
               String[] exts = new String[countExt];

               for(int i = 0; i < countExt; ++i) {
                  exts[i] = GL30.glGetStringi(7939, i);
               }

               return exts;
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      try {
         String extStr = GL11.glGetString(7939);
         String[] exts = extStr.split(" ");
         return exts;
      } catch (Exception var4) {
         var4.printStackTrace();
         return new String[0];
      }
   }

   public static void updateThreadPriorities() {
      updateAvailableProcessors();
      int ELEVATED_PRIORITY = true;
      if (isSingleProcessor()) {
         if (isSmoothWorld()) {
            minecraftThread.setPriority(10);
            setThreadPriority("Server thread", 1);
         } else {
            minecraftThread.setPriority(5);
            setThreadPriority("Server thread", 5);
         }
      } else {
         minecraftThread.setPriority(10);
         setThreadPriority("Server thread", 5);
      }

   }

   private static void setThreadPriority(String p_setThreadPriority_0_, int p_setThreadPriority_1_) {
      try {
         ThreadGroup tg = Thread.currentThread().getThreadGroup();
         if (tg == null) {
            return;
         }

         int num = (tg.activeCount() + 10) * 2;
         Thread[] ts = new Thread[num];
         tg.enumerate(ts, false);

         for(int i = 0; i < ts.length; ++i) {
            Thread t = ts[i];
            if (t != null && t.getName().startsWith(p_setThreadPriority_0_)) {
               t.setPriority(p_setThreadPriority_1_);
            }
         }
      } catch (Throwable var7) {
         warn(var7.getClass().getName() + ": " + var7.getMessage());
      }

   }

   public static boolean isMinecraftThread() {
      return Thread.currentThread() == minecraftThread;
   }

   private static void startVersionCheckThread() {
      VersionCheckThread vct = new VersionCheckThread();
      vct.start();
   }

   public static boolean isMipmaps() {
      return gameSettings.field_151442_I > 0;
   }

   public static int getMipmapLevels() {
      return gameSettings.field_151442_I;
   }

   public static int getMipmapType() {
      switch(gameSettings.ofMipmapType) {
      case 0:
         return 9986;
      case 1:
         return 9986;
      case 2:
         if (isMultiTexture()) {
            return 9985;
         }

         return 9986;
      case 3:
         if (isMultiTexture()) {
            return 9987;
         }

         return 9986;
      default:
         return 9986;
      }
   }

   public static boolean isUseAlphaFunc() {
      float alphaFuncLevel = getAlphaFuncLevel();
      return alphaFuncLevel > DEF_ALPHA_FUNC_LEVEL + 1.0E-5F;
   }

   public static float getAlphaFuncLevel() {
      return DEF_ALPHA_FUNC_LEVEL;
   }

   public static boolean isFogFancy() {
      if (!isFancyFogAvailable()) {
         return false;
      } else {
         return gameSettings.ofFogType == 2;
      }
   }

   public static boolean isFogFast() {
      return gameSettings.ofFogType == 1;
   }

   public static boolean isFogOff() {
      return gameSettings.ofFogType == 3;
   }

   public static boolean isFogOn() {
      return gameSettings.ofFogType != 3;
   }

   public static float getFogStart() {
      return gameSettings.ofFogStart;
   }

   public static void detail(String p_detail_0_) {
      if (logDetail) {
         LOGGER.info("[OptiFine] " + p_detail_0_);
      }

   }

   public static void dbg(String p_dbg_0_) {
      LOGGER.info("[OptiFine] " + p_dbg_0_);
   }

   public static void warn(String p_warn_0_) {
      LOGGER.warn("[OptiFine] " + p_warn_0_);
   }

   public static void error(String p_error_0_) {
      LOGGER.error("[OptiFine] " + p_error_0_);
   }

   public static void log(String p_log_0_) {
      dbg(p_log_0_);
   }

   public static int getUpdatesPerFrame() {
      return gameSettings.ofChunkUpdates;
   }

   public static boolean isDynamicUpdates() {
      return gameSettings.ofChunkUpdatesDynamic;
   }

   public static boolean isRainFancy() {
      if (gameSettings.ofRain == 0) {
         return gameSettings.field_74347_j;
      } else {
         return gameSettings.ofRain == 2;
      }
   }

   public static boolean isRainOff() {
      return gameSettings.ofRain == 3;
   }

   public static boolean isCloudsFancy() {
      if (gameSettings.ofClouds != 0) {
         return gameSettings.ofClouds == 2;
      } else if (isShaders() && !Shaders.shaderPackClouds.isDefault()) {
         return Shaders.shaderPackClouds.isFancy();
      } else if (texturePackClouds != 0) {
         return texturePackClouds == 2;
      } else {
         return gameSettings.field_74347_j;
      }
   }

   public static boolean isCloudsOff() {
      if (gameSettings.ofClouds != 0) {
         return gameSettings.ofClouds == 3;
      } else if (isShaders() && !Shaders.shaderPackClouds.isDefault()) {
         return Shaders.shaderPackClouds.isOff();
      } else if (texturePackClouds != 0) {
         return texturePackClouds == 3;
      } else {
         return false;
      }
   }

   public static void updateTexturePackClouds() {
      texturePackClouds = 0;
      IResourceManager rm = getResourceManager();
      if (rm != null) {
         try {
            InputStream in = rm.func_110536_a(new ResourceLocation("mcpatcher/color.properties")).func_110527_b();
            if (in == null) {
               return;
            }

            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            String cloudStr = props.getProperty("clouds");
            if (cloudStr == null) {
               return;
            }

            dbg("Texture pack clouds: " + cloudStr);
            cloudStr = cloudStr.toLowerCase();
            if (cloudStr.equals("fast")) {
               texturePackClouds = 1;
            }

            if (cloudStr.equals("fancy")) {
               texturePackClouds = 2;
            }

            if (cloudStr.equals("off")) {
               texturePackClouds = 3;
            }
         } catch (Exception var4) {
         }

      }
   }

   public static ModelManager getModelManager() {
      return minecraft.func_175599_af().modelManager;
   }

   public static boolean isTreesFancy() {
      if (gameSettings.ofTrees == 0) {
         return gameSettings.field_74347_j;
      } else {
         return gameSettings.ofTrees != 1;
      }
   }

   public static boolean isTreesSmart() {
      return gameSettings.ofTrees == 4;
   }

   public static boolean isCullFacesLeaves() {
      if (gameSettings.ofTrees == 0) {
         return !gameSettings.field_74347_j;
      } else {
         return gameSettings.ofTrees == 4;
      }
   }

   public static boolean isDroppedItemsFancy() {
      if (gameSettings.ofDroppedItems == 0) {
         return gameSettings.field_74347_j;
      } else {
         return gameSettings.ofDroppedItems == 2;
      }
   }

   public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
      if (p_limit_0_ < p_limit_1_) {
         return p_limit_1_;
      } else {
         return p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_;
      }
   }

   public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
      if (p_limit_0_ < p_limit_1_) {
         return p_limit_1_;
      } else {
         return p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_;
      }
   }

   public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
      if (p_limit_0_ < p_limit_2_) {
         return p_limit_2_;
      } else {
         return p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_;
      }
   }

   public static float limitTo1(float p_limitTo1_0_) {
      if (p_limitTo1_0_ < 0.0F) {
         return 0.0F;
      } else {
         return p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_;
      }
   }

   public static boolean isAnimatedWater() {
      return gameSettings.ofAnimatedWater != 2;
   }

   public static boolean isGeneratedWater() {
      return gameSettings.ofAnimatedWater == 1;
   }

   public static boolean isAnimatedPortal() {
      return gameSettings.ofAnimatedPortal;
   }

   public static boolean isAnimatedLava() {
      return gameSettings.ofAnimatedLava != 2;
   }

   public static boolean isGeneratedLava() {
      return gameSettings.ofAnimatedLava == 1;
   }

   public static boolean isAnimatedFire() {
      return gameSettings.ofAnimatedFire;
   }

   public static boolean isAnimatedRedstone() {
      return gameSettings.ofAnimatedRedstone;
   }

   public static boolean isAnimatedExplosion() {
      return gameSettings.ofAnimatedExplosion;
   }

   public static boolean isAnimatedFlame() {
      return gameSettings.ofAnimatedFlame;
   }

   public static boolean isAnimatedSmoke() {
      return gameSettings.ofAnimatedSmoke;
   }

   public static boolean isVoidParticles() {
      return gameSettings.ofVoidParticles;
   }

   public static boolean isWaterParticles() {
      return gameSettings.ofWaterParticles;
   }

   public static boolean isRainSplash() {
      return gameSettings.ofRainSplash;
   }

   public static boolean isPortalParticles() {
      return gameSettings.ofPortalParticles;
   }

   public static boolean isPotionParticles() {
      return gameSettings.ofPotionParticles;
   }

   public static boolean isFireworkParticles() {
      return gameSettings.ofFireworkParticles;
   }

   public static float getAmbientOcclusionLevel() {
      return isShaders() && Shaders.aoLevel >= 0.0F ? Shaders.aoLevel : gameSettings.ofAoLevel;
   }

   public static String listToString(List p_listToString_0_) {
      return listToString(p_listToString_0_, ", ");
   }

   public static String listToString(List p_listToString_0_, String p_listToString_1_) {
      if (p_listToString_0_ == null) {
         return "";
      } else {
         StringBuffer buf = new StringBuffer(p_listToString_0_.size() * 5);

         for(int i = 0; i < p_listToString_0_.size(); ++i) {
            Object obj = p_listToString_0_.get(i);
            if (i > 0) {
               buf.append(p_listToString_1_);
            }

            buf.append(String.valueOf(obj));
         }

         return buf.toString();
      }
   }

   public static String arrayToString(Object[] p_arrayToString_0_) {
      return arrayToString(p_arrayToString_0_, ", ");
   }

   public static String arrayToString(Object[] p_arrayToString_0_, String p_arrayToString_1_) {
      if (p_arrayToString_0_ == null) {
         return "";
      } else {
         StringBuffer buf = new StringBuffer(p_arrayToString_0_.length * 5);

         for(int i = 0; i < p_arrayToString_0_.length; ++i) {
            Object obj = p_arrayToString_0_[i];
            if (i > 0) {
               buf.append(p_arrayToString_1_);
            }

            buf.append(String.valueOf(obj));
         }

         return buf.toString();
      }
   }

   public static String arrayToString(int[] p_arrayToString_0_) {
      return arrayToString(p_arrayToString_0_, ", ");
   }

   public static String arrayToString(int[] p_arrayToString_0_, String p_arrayToString_1_) {
      if (p_arrayToString_0_ == null) {
         return "";
      } else {
         StringBuffer buf = new StringBuffer(p_arrayToString_0_.length * 5);

         for(int i = 0; i < p_arrayToString_0_.length; ++i) {
            int x = p_arrayToString_0_[i];
            if (i > 0) {
               buf.append(p_arrayToString_1_);
            }

            buf.append(String.valueOf(x));
         }

         return buf.toString();
      }
   }

   public static String arrayToString(float[] p_arrayToString_0_) {
      return arrayToString(p_arrayToString_0_, ", ");
   }

   public static String arrayToString(float[] p_arrayToString_0_, String p_arrayToString_1_) {
      if (p_arrayToString_0_ == null) {
         return "";
      } else {
         StringBuffer buf = new StringBuffer(p_arrayToString_0_.length * 5);

         for(int i = 0; i < p_arrayToString_0_.length; ++i) {
            float x = p_arrayToString_0_[i];
            if (i > 0) {
               buf.append(p_arrayToString_1_);
            }

            buf.append(String.valueOf(x));
         }

         return buf.toString();
      }
   }

   public static Minecraft getMinecraft() {
      return minecraft;
   }

   public static TextureManager getTextureManager() {
      return minecraft.func_110434_K();
   }

   public static IResourceManager getResourceManager() {
      return minecraft.func_110442_L();
   }

   public static InputStream getResourceStream(ResourceLocation p_getResourceStream_0_) throws IOException {
      return getResourceStream(minecraft.func_110442_L(), p_getResourceStream_0_);
   }

   public static InputStream getResourceStream(IResourceManager p_getResourceStream_0_, ResourceLocation p_getResourceStream_1_) throws IOException {
      IResource res = p_getResourceStream_0_.func_110536_a(p_getResourceStream_1_);
      return res == null ? null : res.func_110527_b();
   }

   public static IResource getResource(ResourceLocation p_getResource_0_) throws IOException {
      return minecraft.func_110442_L().func_110536_a(p_getResource_0_);
   }

   public static boolean hasResource(ResourceLocation p_hasResource_0_) {
      if (p_hasResource_0_ == null) {
         return false;
      } else {
         IResourcePack rp = getDefiningResourcePack(p_hasResource_0_);
         return rp != null;
      }
   }

   public static boolean hasResource(IResourceManager p_hasResource_0_, ResourceLocation p_hasResource_1_) {
      try {
         IResource res = p_hasResource_0_.func_110536_a(p_hasResource_1_);
         return res != null;
      } catch (IOException var3) {
         return false;
      }
   }

   public static IResourcePack[] getResourcePacks() {
      ResourcePackRepository rep = minecraft.func_110438_M();
      List entries = rep.func_110613_c();
      List list = new ArrayList();
      Iterator it = entries.iterator();

      while(it.hasNext()) {
         ResourcePackRepository.Entry entry = (ResourcePackRepository.Entry)it.next();
         list.add(entry.func_110514_c());
      }

      if (rep.func_148530_e() != null) {
         list.add(rep.func_148530_e());
      }

      IResourcePack[] rps = (IResourcePack[])((IResourcePack[])list.toArray(new IResourcePack[list.size()]));
      return rps;
   }

   public static String getResourcePackNames() {
      if (minecraft.func_110438_M() == null) {
         return "";
      } else {
         IResourcePack[] rps = getResourcePacks();
         if (rps.length <= 0) {
            return getDefaultResourcePack().func_130077_b();
         } else {
            String[] names = new String[rps.length];

            for(int i = 0; i < rps.length; ++i) {
               names[i] = rps[i].func_130077_b();
            }

            String nameStr = arrayToString((Object[])names);
            return nameStr;
         }
      }
   }

   public static DefaultResourcePack getDefaultResourcePack() {
      if (defaultResourcePackLazy == null) {
         Minecraft mc = Minecraft.func_71410_x();
         defaultResourcePackLazy = (DefaultResourcePack)Reflector.getFieldValue(mc, Reflector.Minecraft_defaultResourcePack);
         if (defaultResourcePackLazy == null) {
            ResourcePackRepository repository = mc.func_110438_M();
            if (repository != null) {
               defaultResourcePackLazy = (DefaultResourcePack)repository.field_110620_b;
            }
         }
      }

      return defaultResourcePackLazy;
   }

   public static boolean isFromDefaultResourcePack(ResourceLocation p_isFromDefaultResourcePack_0_) {
      IResourcePack rp = getDefiningResourcePack(p_isFromDefaultResourcePack_0_);
      return rp == getDefaultResourcePack();
   }

   public static IResourcePack getDefiningResourcePack(ResourceLocation p_getDefiningResourcePack_0_) {
      ResourcePackRepository rep = minecraft.func_110438_M();
      IResourcePack serverRp = rep.func_148530_e();
      if (serverRp != null && serverRp.func_110589_b(p_getDefiningResourcePack_0_)) {
         return serverRp;
      } else {
         List<ResourcePackRepository.Entry> entries = rep.field_110617_f;

         for(int i = entries.size() - 1; i >= 0; --i) {
            ResourcePackRepository.Entry entry = (ResourcePackRepository.Entry)entries.get(i);
            IResourcePack rp = entry.func_110514_c();
            if (rp.func_110589_b(p_getDefiningResourcePack_0_)) {
               return rp;
            }
         }

         if (getDefaultResourcePack().func_110589_b(p_getDefiningResourcePack_0_)) {
            return getDefaultResourcePack();
         } else {
            return null;
         }
      }
   }

   public static RenderGlobal getRenderGlobal() {
      return minecraft.field_71438_f;
   }

   public static boolean isBetterGrass() {
      return gameSettings.ofBetterGrass != 3;
   }

   public static boolean isBetterGrassFancy() {
      return gameSettings.ofBetterGrass == 2;
   }

   public static boolean isWeatherEnabled() {
      return gameSettings.ofWeather;
   }

   public static boolean isSkyEnabled() {
      return gameSettings.ofSky;
   }

   public static boolean isSunMoonEnabled() {
      return gameSettings.ofSunMoon;
   }

   public static boolean isSunTexture() {
      if (!isSunMoonEnabled()) {
         return false;
      } else {
         return !isShaders() || Shaders.isSun();
      }
   }

   public static boolean isMoonTexture() {
      if (!isSunMoonEnabled()) {
         return false;
      } else {
         return !isShaders() || Shaders.isMoon();
      }
   }

   public static boolean isVignetteEnabled() {
      if (isShaders() && !Shaders.isVignette()) {
         return false;
      } else if (gameSettings.ofVignette == 0) {
         return gameSettings.field_74347_j;
      } else {
         return gameSettings.ofVignette == 2;
      }
   }

   public static boolean isStarsEnabled() {
      return gameSettings.ofStars;
   }

   public static void sleep(long p_sleep_0_) {
      try {
         Thread.sleep(p_sleep_0_);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

   }

   public static boolean isTimeDayOnly() {
      return gameSettings.ofTime == 1;
   }

   public static boolean isTimeDefault() {
      return gameSettings.ofTime == 0;
   }

   public static boolean isTimeNightOnly() {
      return gameSettings.ofTime == 2;
   }

   public static boolean isClearWater() {
      return gameSettings.ofClearWater;
   }

   public static int getAnisotropicFilterLevel() {
      return gameSettings.ofAfLevel;
   }

   public static boolean isAnisotropicFiltering() {
      return getAnisotropicFilterLevel() > 1;
   }

   public static int getAntialiasingLevel() {
      return antialiasingLevel;
   }

   public static boolean isAntialiasing() {
      return getAntialiasingLevel() > 0;
   }

   public static boolean isAntialiasingConfigured() {
      return getGameSettings().ofAaLevel > 0;
   }

   public static boolean isMultiTexture() {
      if (getAnisotropicFilterLevel() > 1) {
         return true;
      } else {
         return getAntialiasingLevel() > 0;
      }
   }

   public static boolean between(int p_between_0_, int p_between_1_, int p_between_2_) {
      return p_between_0_ >= p_between_1_ && p_between_0_ <= p_between_2_;
   }

   public static boolean between(float p_between_0_, float p_between_1_, float p_between_2_) {
      return p_between_0_ >= p_between_1_ && p_between_0_ <= p_between_2_;
   }

   public static boolean isDrippingWaterLava() {
      return gameSettings.ofDrippingWaterLava;
   }

   public static boolean isBetterSnow() {
      return gameSettings.ofBetterSnow;
   }

   public static Dimension getFullscreenDimension() {
      if (desktopDisplayMode == null) {
         return null;
      } else if (gameSettings == null) {
         return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
      } else {
         String dimStr = gameSettings.ofFullscreenMode;
         if (dimStr.equals("Default")) {
            return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
         } else {
            String[] dimStrs = tokenize(dimStr, " x");
            return dimStrs.length < 2 ? new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight()) : new Dimension(parseInt(dimStrs[0], -1), parseInt(dimStrs[1], -1));
         }
      }
   }

   public static int parseInt(String p_parseInt_0_, int p_parseInt_1_) {
      try {
         if (p_parseInt_0_ == null) {
            return p_parseInt_1_;
         } else {
            p_parseInt_0_ = p_parseInt_0_.trim();
            return Integer.parseInt(p_parseInt_0_);
         }
      } catch (NumberFormatException var3) {
         return p_parseInt_1_;
      }
   }

   public static float parseFloat(String p_parseFloat_0_, float p_parseFloat_1_) {
      try {
         if (p_parseFloat_0_ == null) {
            return p_parseFloat_1_;
         } else {
            p_parseFloat_0_ = p_parseFloat_0_.trim();
            return Float.parseFloat(p_parseFloat_0_);
         }
      } catch (NumberFormatException var3) {
         return p_parseFloat_1_;
      }
   }

   public static boolean parseBoolean(String p_parseBoolean_0_, boolean p_parseBoolean_1_) {
      try {
         if (p_parseBoolean_0_ == null) {
            return p_parseBoolean_1_;
         } else {
            p_parseBoolean_0_ = p_parseBoolean_0_.trim();
            return Boolean.parseBoolean(p_parseBoolean_0_);
         }
      } catch (NumberFormatException var3) {
         return p_parseBoolean_1_;
      }
   }

   public static Boolean parseBoolean(String p_parseBoolean_0_, Boolean p_parseBoolean_1_) {
      try {
         if (p_parseBoolean_0_ == null) {
            return p_parseBoolean_1_;
         } else {
            p_parseBoolean_0_ = p_parseBoolean_0_.trim().toLowerCase();
            if (p_parseBoolean_0_.equals("true")) {
               return Boolean.TRUE;
            } else {
               return p_parseBoolean_0_.equals("false") ? Boolean.FALSE : p_parseBoolean_1_;
            }
         }
      } catch (NumberFormatException var3) {
         return p_parseBoolean_1_;
      }
   }

   public static String[] tokenize(String p_tokenize_0_, String p_tokenize_1_) {
      StringTokenizer tok = new StringTokenizer(p_tokenize_0_, p_tokenize_1_);
      ArrayList list = new ArrayList();

      while(tok.hasMoreTokens()) {
         String token = tok.nextToken();
         list.add(token);
      }

      String[] strs = (String[])((String[])list.toArray(new String[list.size()]));
      return strs;
   }

   public static DisplayMode getDesktopDisplayMode() {
      return desktopDisplayMode;
   }

   public static DisplayMode[] getDisplayModes() {
      if (displayModes == null) {
         try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            Set<Dimension> setDimensions = getDisplayModeDimensions(modes);
            List list = new ArrayList();
            Iterator it = setDimensions.iterator();

            while(it.hasNext()) {
               Dimension dim = (Dimension)it.next();
               DisplayMode[] dimModes = getDisplayModes(modes, dim);
               DisplayMode dm = getDisplayMode(dimModes, desktopDisplayMode);
               if (dm != null) {
                  list.add(dm);
               }
            }

            DisplayMode[] fsModes = (DisplayMode[])((DisplayMode[])list.toArray(new DisplayMode[list.size()]));
            Arrays.sort(fsModes, new DisplayModeComparator());
            return fsModes;
         } catch (Exception var7) {
            var7.printStackTrace();
            displayModes = new DisplayMode[]{desktopDisplayMode};
         }
      }

      return displayModes;
   }

   public static DisplayMode getLargestDisplayMode() {
      DisplayMode[] modes = getDisplayModes();
      if (modes != null && modes.length >= 1) {
         DisplayMode mode = modes[modes.length - 1];
         if (desktopDisplayMode.getWidth() > mode.getWidth()) {
            return desktopDisplayMode;
         } else {
            return desktopDisplayMode.getWidth() == mode.getWidth() && desktopDisplayMode.getHeight() > mode.getHeight() ? desktopDisplayMode : mode;
         }
      } else {
         return desktopDisplayMode;
      }
   }

   private static Set<Dimension> getDisplayModeDimensions(DisplayMode[] p_getDisplayModeDimensions_0_) {
      Set<Dimension> set = new HashSet();

      for(int i = 0; i < p_getDisplayModeDimensions_0_.length; ++i) {
         DisplayMode mode = p_getDisplayModeDimensions_0_[i];
         Dimension dim = new Dimension(mode.getWidth(), mode.getHeight());
         set.add(dim);
      }

      return set;
   }

   private static DisplayMode[] getDisplayModes(DisplayMode[] p_getDisplayModes_0_, Dimension p_getDisplayModes_1_) {
      List list = new ArrayList();

      for(int i = 0; i < p_getDisplayModes_0_.length; ++i) {
         DisplayMode mode = p_getDisplayModes_0_[i];
         if ((double)mode.getWidth() == p_getDisplayModes_1_.getWidth() && (double)mode.getHeight() == p_getDisplayModes_1_.getHeight()) {
            list.add(mode);
         }
      }

      DisplayMode[] dimModes = (DisplayMode[])((DisplayMode[])list.toArray(new DisplayMode[list.size()]));
      return dimModes;
   }

   private static DisplayMode getDisplayMode(DisplayMode[] p_getDisplayMode_0_, DisplayMode p_getDisplayMode_1_) {
      if (p_getDisplayMode_1_ != null) {
         for(int i = 0; i < p_getDisplayMode_0_.length; ++i) {
            DisplayMode mode = p_getDisplayMode_0_[i];
            if (mode.getBitsPerPixel() == p_getDisplayMode_1_.getBitsPerPixel() && mode.getFrequency() == p_getDisplayMode_1_.getFrequency()) {
               return mode;
            }
         }
      }

      if (p_getDisplayMode_0_.length <= 0) {
         return null;
      } else {
         Arrays.sort(p_getDisplayMode_0_, new DisplayModeComparator());
         return p_getDisplayMode_0_[p_getDisplayMode_0_.length - 1];
      }
   }

   public static String[] getDisplayModeNames() {
      DisplayMode[] modes = getDisplayModes();
      String[] names = new String[modes.length];

      for(int i = 0; i < modes.length; ++i) {
         DisplayMode mode = modes[i];
         String name = "" + mode.getWidth() + "x" + mode.getHeight();
         names[i] = name;
      }

      return names;
   }

   public static DisplayMode getDisplayMode(Dimension p_getDisplayMode_0_) throws LWJGLException {
      DisplayMode[] modes = getDisplayModes();

      for(int i = 0; i < modes.length; ++i) {
         DisplayMode dm = modes[i];
         if (dm.getWidth() == p_getDisplayMode_0_.width && dm.getHeight() == p_getDisplayMode_0_.height) {
            return dm;
         }
      }

      return desktopDisplayMode;
   }

   public static boolean isAnimatedTerrain() {
      return gameSettings.ofAnimatedTerrain;
   }

   public static boolean isAnimatedTextures() {
      return gameSettings.ofAnimatedTextures;
   }

   public static boolean isSwampColors() {
      return gameSettings.ofSwampColors;
   }

   public static boolean isRandomEntities() {
      return gameSettings.ofRandomEntities;
   }

   public static void checkGlError(String p_checkGlError_0_) {
      int errorCode = GlStateManager.glGetError();
      if (errorCode != 0 && GlErrors.isEnabled(errorCode)) {
         String errorText = getGlErrorString(errorCode);
         String messageLog = String.format("OpenGL error: %s (%s), at: %s", errorCode, errorText, p_checkGlError_0_);
         error(messageLog);
         if (isShowGlErrors() && TimedEvent.isActive("ShowGlError", 10000L)) {
            String message = I18n.func_135052_a("of.message.openglError", errorCode, errorText);
            minecraft.field_71456_v.func_146158_b().func_146227_a(new ChatComponentText(message));
         }
      }

   }

   public static boolean isSmoothBiomes() {
      return gameSettings.ofSmoothBiomes;
   }

   public static boolean isCustomColors() {
      return gameSettings.ofCustomColors;
   }

   public static boolean isCustomSky() {
      return gameSettings.ofCustomSky;
   }

   public static boolean isCustomFonts() {
      return gameSettings.ofCustomFonts;
   }

   public static boolean isShowCapes() {
      return gameSettings.ofShowCapes;
   }

   public static boolean isConnectedTextures() {
      return gameSettings.ofConnectedTextures != 3;
   }

   public static boolean isNaturalTextures() {
      return gameSettings.ofNaturalTextures;
   }

   public static boolean isEmissiveTextures() {
      return gameSettings.ofEmissiveTextures;
   }

   public static boolean isConnectedTexturesFancy() {
      return gameSettings.ofConnectedTextures == 2;
   }

   public static boolean isFastRender() {
      return gameSettings.ofFastRender;
   }

   public static boolean isTranslucentBlocksFancy() {
      if (gameSettings.ofTranslucentBlocks == 0) {
         return gameSettings.field_74347_j;
      } else {
         return gameSettings.ofTranslucentBlocks == 2;
      }
   }

   public static boolean isShaders() {
      return Shaders.shaderPackLoaded;
   }

   public static String[] readLines(File p_readLines_0_) throws IOException {
      FileInputStream fis = new FileInputStream(p_readLines_0_);
      return readLines((InputStream)fis);
   }

   public static String[] readLines(InputStream p_readLines_0_) throws IOException {
      List list = new ArrayList();
      InputStreamReader isr = new InputStreamReader(p_readLines_0_, "ASCII");
      BufferedReader br = new BufferedReader(isr);

      while(true) {
         String line = br.readLine();
         if (line == null) {
            String[] lines = (String[])((String[])list.toArray(new String[list.size()]));
            return lines;
         }

         list.add(line);
      }
   }

   public static String readFile(File p_readFile_0_) throws IOException {
      FileInputStream fin = new FileInputStream(p_readFile_0_);
      return readInputStream(fin, "ASCII");
   }

   public static String readInputStream(InputStream p_readInputStream_0_) throws IOException {
      return readInputStream(p_readInputStream_0_, "ASCII");
   }

   public static String readInputStream(InputStream p_readInputStream_0_, String p_readInputStream_1_) throws IOException {
      InputStreamReader inr = new InputStreamReader(p_readInputStream_0_, p_readInputStream_1_);
      BufferedReader br = new BufferedReader(inr);
      StringBuffer sb = new StringBuffer();

      while(true) {
         String line = br.readLine();
         if (line == null) {
            return sb.toString();
         }

         sb.append(line);
         sb.append("\n");
      }
   }

   public static byte[] readAll(InputStream p_readAll_0_) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];

      while(true) {
         int len = p_readAll_0_.read(buf);
         if (len < 0) {
            p_readAll_0_.close();
            byte[] bytes = baos.toByteArray();
            return bytes;
         }

         baos.write(buf, 0, len);
      }
   }

   public static GameSettings getGameSettings() {
      return gameSettings;
   }

   public static String getNewRelease() {
      return newRelease;
   }

   public static void setNewRelease(String p_setNewRelease_0_) {
      newRelease = p_setNewRelease_0_;
   }

   public static int compareRelease(String p_compareRelease_0_, String p_compareRelease_1_) {
      String[] rels1 = splitRelease(p_compareRelease_0_);
      String[] rels2 = splitRelease(p_compareRelease_1_);
      String branch1 = rels1[0];
      String branch2 = rels2[0];
      if (!branch1.equals(branch2)) {
         return branch1.compareTo(branch2);
      } else {
         int rev1 = parseInt(rels1[1], -1);
         int rev2 = parseInt(rels2[1], -1);
         if (rev1 != rev2) {
            return rev1 - rev2;
         } else {
            String suf1 = rels1[2];
            String suf2 = rels2[2];
            if (!suf1.equals(suf2)) {
               if (suf1.isEmpty()) {
                  return 1;
               }

               if (suf2.isEmpty()) {
                  return -1;
               }
            }

            return suf1.compareTo(suf2);
         }
      }
   }

   private static String[] splitRelease(String p_splitRelease_0_) {
      if (p_splitRelease_0_ != null && p_splitRelease_0_.length() > 0) {
         Pattern p = Pattern.compile("([A-Z])([0-9]+)(.*)");
         Matcher m = p.matcher(p_splitRelease_0_);
         if (!m.matches()) {
            return new String[]{"", "", ""};
         } else {
            String branch = normalize(m.group(1));
            String revision = normalize(m.group(2));
            String suffix = normalize(m.group(3));
            return new String[]{branch, revision, suffix};
         }
      } else {
         return new String[]{"", "", ""};
      }
   }

   public static int intHash(int p_intHash_0_) {
      p_intHash_0_ = p_intHash_0_ ^ 61 ^ p_intHash_0_ >> 16;
      p_intHash_0_ += p_intHash_0_ << 3;
      p_intHash_0_ ^= p_intHash_0_ >> 4;
      p_intHash_0_ *= 668265261;
      p_intHash_0_ ^= p_intHash_0_ >> 15;
      return p_intHash_0_;
   }

   public static int getRandom(BlockPos p_getRandom_0_, int p_getRandom_1_) {
      int rand = intHash(p_getRandom_1_ + 37);
      rand = intHash(rand + p_getRandom_0_.func_177958_n());
      rand = intHash(rand + p_getRandom_0_.func_177952_p());
      rand = intHash(rand + p_getRandom_0_.func_177956_o());
      return rand;
   }

   public static int getAvailableProcessors() {
      return availableProcessors;
   }

   public static void updateAvailableProcessors() {
      availableProcessors = Runtime.getRuntime().availableProcessors();
   }

   public static boolean isSingleProcessor() {
      return getAvailableProcessors() <= 1;
   }

   public static boolean isSmoothWorld() {
      return gameSettings.ofSmoothWorld;
   }

   public static boolean isLazyChunkLoading() {
      return gameSettings.ofLazyChunkLoading;
   }

   public static boolean isDynamicFov() {
      return gameSettings.ofDynamicFov;
   }

   public static boolean isAlternateBlocks() {
      return gameSettings.ofAlternateBlocks;
   }

   public static int getChunkViewDistance() {
      if (gameSettings == null) {
         return 10;
      } else {
         int chunkDistance = gameSettings.field_151451_c;
         return chunkDistance;
      }
   }

   public static boolean equals(Object p_equals_0_, Object p_equals_1_) {
      if (p_equals_0_ == p_equals_1_) {
         return true;
      } else {
         return p_equals_0_ == null ? false : p_equals_0_.equals(p_equals_1_);
      }
   }

   public static boolean equalsOne(Object p_equalsOne_0_, Object[] p_equalsOne_1_) {
      if (p_equalsOne_1_ == null) {
         return false;
      } else {
         for(int i = 0; i < p_equalsOne_1_.length; ++i) {
            Object b = p_equalsOne_1_[i];
            if (equals(p_equalsOne_0_, b)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean equalsOne(int p_equalsOne_0_, int[] p_equalsOne_1_) {
      for(int i = 0; i < p_equalsOne_1_.length; ++i) {
         if (p_equalsOne_1_[i] == p_equalsOne_0_) {
            return true;
         }
      }

      return false;
   }

   public static boolean isSameOne(Object p_isSameOne_0_, Object[] p_isSameOne_1_) {
      if (p_isSameOne_1_ == null) {
         return false;
      } else {
         for(int i = 0; i < p_isSameOne_1_.length; ++i) {
            Object b = p_isSameOne_1_[i];
            if (p_isSameOne_0_ == b) {
               return true;
            }
         }

         return false;
      }
   }

   public static String normalize(String p_normalize_0_) {
      return p_normalize_0_ == null ? "" : p_normalize_0_;
   }

   public static void checkDisplaySettings() {
      int samples = getAntialiasingLevel();
      if (samples > 0) {
         DisplayMode displayMode = Display.getDisplayMode();
         dbg("FSAA Samples: " + samples);

         try {
            Display.destroy();
            Display.setDisplayMode(displayMode);
            Display.create((new PixelFormat()).withDepthBits(24).withSamples(samples));
            Display.setResizable(false);
            Display.setResizable(true);
         } catch (LWJGLException var15) {
            warn("Error setting FSAA: " + samples + "x");
            var15.printStackTrace();

            try {
               Display.setDisplayMode(displayMode);
               Display.create((new PixelFormat()).withDepthBits(24));
               Display.setResizable(false);
               Display.setResizable(true);
            } catch (LWJGLException var14) {
               var14.printStackTrace();

               try {
                  Display.setDisplayMode(displayMode);
                  Display.create();
                  Display.setResizable(false);
                  Display.setResizable(true);
               } catch (LWJGLException var13) {
                  var13.printStackTrace();
               }
            }
         }

         if (!Minecraft.field_142025_a && getDefaultResourcePack() != null) {
            InputStream var2 = null;
            InputStream var3 = null;

            try {
               var2 = getDefaultResourcePack().func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
               var3 = getDefaultResourcePack().func_152780_c(new ResourceLocation("icons/icon_32x32.png"));
               if (var2 != null && var3 != null) {
                  Display.setIcon(new ByteBuffer[]{readIconImage(var2), readIconImage(var3)});
               }
            } catch (IOException var11) {
               warn("Error setting window icon: " + var11.getClass().getName() + ": " + var11.getMessage());
            } finally {
               IOUtils.closeQuietly(var2);
               IOUtils.closeQuietly(var3);
            }
         }
      }

   }

   private static ByteBuffer readIconImage(InputStream p_readIconImage_0_) throws IOException {
      BufferedImage var2 = ImageIO.read(p_readIconImage_0_);
      int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), (int[])null, 0, var2.getWidth());
      ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
      int[] var5 = var3;
      int var6 = var3.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int var8 = var5[var7];
         var4.putInt(var8 << 8 | var8 >> 24 & 255);
      }

      var4.flip();
      return var4;
   }

   public static void checkDisplayMode() {
      try {
         if (minecraft.func_71372_G()) {
            if (fullscreenModeChecked) {
               return;
            }

            fullscreenModeChecked = true;
            desktopModeChecked = false;
            DisplayMode mode = Display.getDisplayMode();
            Dimension dim = getFullscreenDimension();
            if (dim == null) {
               return;
            }

            if (mode.getWidth() == dim.width && mode.getHeight() == dim.height) {
               return;
            }

            DisplayMode newMode = getDisplayMode(dim);
            if (newMode == null) {
               return;
            }

            Display.setDisplayMode(newMode);
            minecraft.field_71443_c = Display.getDisplayMode().getWidth();
            minecraft.field_71440_d = Display.getDisplayMode().getHeight();
            if (minecraft.field_71443_c <= 0) {
               minecraft.field_71443_c = 1;
            }

            if (minecraft.field_71440_d <= 0) {
               minecraft.field_71440_d = 1;
            }

            if (minecraft.field_71462_r != null) {
               ScaledResolution sr = new ScaledResolution(minecraft);
               int sw = sr.func_78326_a();
               int sh = sr.func_78328_b();
               minecraft.field_71462_r.func_146280_a(minecraft, sw, sh);
            }

            updateFramebufferSize();
            Display.setFullscreen(true);
            minecraft.field_71474_y.updateVSync();
            GlStateManager.func_179098_w();
         } else {
            if (desktopModeChecked) {
               return;
            }

            desktopModeChecked = true;
            fullscreenModeChecked = false;
            minecraft.field_71474_y.updateVSync();
            Display.update();
            GlStateManager.func_179098_w();
            Display.setResizable(false);
            Display.setResizable(true);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
         gameSettings.ofFullscreenMode = "Default";
         gameSettings.saveOfOptions();
      }

   }

   public static void updateFramebufferSize() {
      minecraft.func_147110_a().func_147613_a(minecraft.field_71443_c, minecraft.field_71440_d);
      if (minecraft.field_71460_t != null) {
         minecraft.field_71460_t.func_147704_a(minecraft.field_71443_c, minecraft.field_71440_d);
      }

      minecraft.field_71461_s = new LoadingScreenRenderer(minecraft);
   }

   public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_) {
      if (p_addObjectToArray_0_ == null) {
         throw new NullPointerException("The given array is NULL");
      } else {
         int arrLen = p_addObjectToArray_0_.length;
         int newLen = arrLen + 1;
         Object[] newArr = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), newLen));
         System.arraycopy(p_addObjectToArray_0_, 0, newArr, 0, arrLen);
         newArr[arrLen] = p_addObjectToArray_1_;
         return newArr;
      }
   }

   public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_, int p_addObjectToArray_2_) {
      List list = new ArrayList(Arrays.asList(p_addObjectToArray_0_));
      list.add(p_addObjectToArray_2_, p_addObjectToArray_1_);
      Object[] newArr = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), list.size()));
      return list.toArray(newArr);
   }

   public static Object[] addObjectsToArray(Object[] p_addObjectsToArray_0_, Object[] p_addObjectsToArray_1_) {
      if (p_addObjectsToArray_0_ == null) {
         throw new NullPointerException("The given array is NULL");
      } else if (p_addObjectsToArray_1_.length == 0) {
         return p_addObjectsToArray_0_;
      } else {
         int arrLen = p_addObjectsToArray_0_.length;
         int newLen = arrLen + p_addObjectsToArray_1_.length;
         Object[] newArr = (Object[])((Object[])Array.newInstance(p_addObjectsToArray_0_.getClass().getComponentType(), newLen));
         System.arraycopy(p_addObjectsToArray_0_, 0, newArr, 0, arrLen);
         System.arraycopy(p_addObjectsToArray_1_, 0, newArr, arrLen, p_addObjectsToArray_1_.length);
         return newArr;
      }
   }

   public static Object[] removeObjectFromArray(Object[] p_removeObjectFromArray_0_, Object p_removeObjectFromArray_1_) {
      List list = new ArrayList(Arrays.asList(p_removeObjectFromArray_0_));
      list.remove(p_removeObjectFromArray_1_);
      Object[] newArr = collectionToArray(list, p_removeObjectFromArray_0_.getClass().getComponentType());
      return newArr;
   }

   public static Object[] collectionToArray(Collection p_collectionToArray_0_, Class p_collectionToArray_1_) {
      if (p_collectionToArray_0_ == null) {
         return null;
      } else if (p_collectionToArray_1_ == null) {
         return null;
      } else if (p_collectionToArray_1_.isPrimitive()) {
         throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + p_collectionToArray_1_);
      } else {
         Object[] array = (Object[])((Object[])Array.newInstance(p_collectionToArray_1_, p_collectionToArray_0_.size()));
         return p_collectionToArray_0_.toArray(array);
      }
   }

   public static boolean isCustomItems() {
      return gameSettings.ofCustomItems;
   }

   public static void drawFps() {
      int fps = Minecraft.func_175610_ah();
      String updates = getUpdates(minecraft.field_71426_K);
      int renderersActive = minecraft.field_71438_f.getCountActiveRenderers();
      int entities = minecraft.field_71438_f.getCountEntitiesRendered();
      int tileEntities = minecraft.field_71438_f.getCountTileEntitiesRendered();
      String fpsStr = "" + fps + "/" + getFpsMin() + " fps, C: " + renderersActive + ", E: " + entities + "+" + tileEntities + ", U: " + updates;
      minecraft.field_71466_p.func_78276_b(fpsStr, 2, 2, -2039584);
   }

   public static int getFpsMin() {
      if (minecraft.field_71426_K == mcDebugLast) {
         return fpsMinLast;
      } else {
         mcDebugLast = minecraft.field_71426_K;
         FrameTimer ft = minecraft.func_181539_aj();
         long[] frames = ft.func_181746_c();
         int index = ft.func_181750_b();
         int indexEnd = ft.func_181749_a();
         if (index == indexEnd) {
            return fpsMinLast;
         } else {
            int fps = Minecraft.func_175610_ah();
            if (fps <= 0) {
               fps = 1;
            }

            long timeAvgNs = (long)(1.0D / (double)fps * 1.0E9D);
            long timeMaxNs = timeAvgNs;
            long timeTotalNs = 0L;

            for(int ix = MathHelper.func_180184_b(index - 1, frames.length); ix != indexEnd && (double)timeTotalNs < 1.0E9D; ix = MathHelper.func_180184_b(ix - 1, frames.length)) {
               long timeNs = frames[ix];
               if (timeNs > timeMaxNs) {
                  timeMaxNs = timeNs;
               }

               timeTotalNs += timeNs;
            }

            double timeMaxSec = (double)timeMaxNs / 1.0E9D;
            fpsMinLast = (int)(1.0D / timeMaxSec);
            return fpsMinLast;
         }
      }
   }

   private static String getUpdates(String p_getUpdates_0_) {
      int pos1 = p_getUpdates_0_.indexOf(40);
      if (pos1 < 0) {
         return "";
      } else {
         int pos2 = p_getUpdates_0_.indexOf(32, pos1);
         return pos2 < 0 ? "" : p_getUpdates_0_.substring(pos1 + 1, pos2);
      }
   }

   public static int getBitsOs() {
      String progFiles86 = System.getenv("ProgramFiles(X86)");
      return progFiles86 != null ? 64 : 32;
   }

   public static int getBitsJre() {
      String[] propNames = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};

      for(int i = 0; i < propNames.length; ++i) {
         String propName = propNames[i];
         String propVal = System.getProperty(propName);
         if (propVal != null && propVal.contains("64")) {
            return 64;
         }
      }

      return 32;
   }

   public static boolean isNotify64BitJava() {
      return notify64BitJava;
   }

   public static void setNotify64BitJava(boolean p_setNotify64BitJava_0_) {
      notify64BitJava = p_setNotify64BitJava_0_;
   }

   public static boolean isConnectedModels() {
      return false;
   }

   public static void showGuiMessage(String p_showGuiMessage_0_, String p_showGuiMessage_1_) {
      GuiMessage gui = new GuiMessage(minecraft.field_71462_r, p_showGuiMessage_0_, p_showGuiMessage_1_);
      minecraft.func_147108_a(gui);
   }

   public static int[] addIntToArray(int[] p_addIntToArray_0_, int p_addIntToArray_1_) {
      return addIntsToArray(p_addIntToArray_0_, new int[]{p_addIntToArray_1_});
   }

   public static int[] addIntsToArray(int[] p_addIntsToArray_0_, int[] p_addIntsToArray_1_) {
      if (p_addIntsToArray_0_ != null && p_addIntsToArray_1_ != null) {
         int arrLen = p_addIntsToArray_0_.length;
         int newLen = arrLen + p_addIntsToArray_1_.length;
         int[] newArray = new int[newLen];
         System.arraycopy(p_addIntsToArray_0_, 0, newArray, 0, arrLen);

         for(int index = 0; index < p_addIntsToArray_1_.length; ++index) {
            newArray[index + arrLen] = p_addIntsToArray_1_[index];
         }

         return newArray;
      } else {
         throw new NullPointerException("The given array is NULL");
      }
   }

   public static DynamicTexture getMojangLogoTexture(DynamicTexture p_getMojangLogoTexture_0_) {
      try {
         ResourceLocation locationMojangPng = new ResourceLocation("textures/gui/title/mojang.png");
         InputStream in = getResourceStream(locationMojangPng);
         if (in == null) {
            return p_getMojangLogoTexture_0_;
         } else {
            BufferedImage bi = ImageIO.read(in);
            if (bi == null) {
               return p_getMojangLogoTexture_0_;
            } else {
               DynamicTexture dt = new DynamicTexture(bi);
               return dt;
            }
         }
      } catch (Exception var5) {
         warn(var5.getClass().getName() + ": " + var5.getMessage());
         return p_getMojangLogoTexture_0_;
      }
   }

   public static void writeFile(File p_writeFile_0_, String p_writeFile_1_) throws IOException {
      FileOutputStream fos = new FileOutputStream(p_writeFile_0_);
      byte[] bytes = p_writeFile_1_.getBytes("ASCII");
      fos.write(bytes);
      fos.close();
   }

   public static TextureMap getTextureMap() {
      return getMinecraft().func_147117_R();
   }

   public static boolean isDynamicLights() {
      return gameSettings.ofDynamicLights != 3;
   }

   public static boolean isDynamicLightsFast() {
      return gameSettings.ofDynamicLights == 1;
   }

   public static boolean isDynamicHandLight() {
      if (!isDynamicLights()) {
         return false;
      } else {
         return isShaders() ? Shaders.isDynamicHandLight() : true;
      }
   }

   public static boolean isCustomEntityModels() {
      return gameSettings.ofCustomEntityModels;
   }

   public static boolean isCustomGuis() {
      return gameSettings.ofCustomGuis;
   }

   public static int getScreenshotSize() {
      return gameSettings.ofScreenshotSize;
   }

   public static int[] toPrimitive(Integer[] p_toPrimitive_0_) {
      if (p_toPrimitive_0_ == null) {
         return null;
      } else if (p_toPrimitive_0_.length == 0) {
         return new int[0];
      } else {
         int[] intArr = new int[p_toPrimitive_0_.length];

         for(int i = 0; i < intArr.length; ++i) {
            intArr[i] = p_toPrimitive_0_[i];
         }

         return intArr;
      }
   }

   public static boolean isRenderRegions() {
      return gameSettings.ofRenderRegions;
   }

   public static boolean isVbo() {
      return OpenGlHelper.func_176075_f();
   }

   public static boolean isSmoothFps() {
      return gameSettings.ofSmoothFps;
   }

   public static boolean openWebLink(URI p_openWebLink_0_) {
      try {
         Desktop.getDesktop().browse(p_openWebLink_0_);
         return true;
      } catch (Exception var2) {
         warn("Error opening link: " + p_openWebLink_0_);
         warn(var2.getClass().getName() + ": " + var2.getMessage());
         return false;
      }
   }

   public static boolean isShowGlErrors() {
      return gameSettings.ofShowGlErrors;
   }

   public static String arrayToString(boolean[] p_arrayToString_0_, String p_arrayToString_1_) {
      if (p_arrayToString_0_ == null) {
         return "";
      } else {
         StringBuffer buf = new StringBuffer(p_arrayToString_0_.length * 5);

         for(int i = 0; i < p_arrayToString_0_.length; ++i) {
            boolean x = p_arrayToString_0_[i];
            if (i > 0) {
               buf.append(p_arrayToString_1_);
            }

            buf.append(String.valueOf(x));
         }

         return buf.toString();
      }
   }

   public static boolean isIntegratedServerRunning() {
      if (minecraft.func_71401_C() == null) {
         return false;
      } else {
         return minecraft.func_71387_A();
      }
   }

   public static IntBuffer createDirectIntBuffer(int p_createDirectIntBuffer_0_) {
      return GLAllocation.func_74524_c(p_createDirectIntBuffer_0_ << 2).asIntBuffer();
   }

   public static String getGlErrorString(int p_getGlErrorString_0_) {
      switch(p_getGlErrorString_0_) {
      case 0:
         return "No error";
      case 1280:
         return "Invalid enum";
      case 1281:
         return "Invalid value";
      case 1282:
         return "Invalid operation";
      case 1283:
         return "Stack overflow";
      case 1284:
         return "Stack underflow";
      case 1285:
         return "Out of memory";
      case 1286:
         return "Invalid framebuffer operation";
      default:
         return "Unknown";
      }
   }

   public static boolean isTrue(Boolean p_isTrue_0_) {
      return p_isTrue_0_ != null && p_isTrue_0_;
   }

   public static boolean isQuadsToTriangles() {
      if (!isShaders()) {
         return false;
      } else {
         return !Shaders.canRenderQuads();
      }
   }
}
