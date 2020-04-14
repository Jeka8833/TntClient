package net.optifine.shaders;

import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.GlErrors;
import net.optifine.Lang;
import net.optifine.config.ConnectedParser;
import net.optifine.expr.IExpressionBool;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ScreenShaderOptions;
import net.optifine.shaders.config.ShaderLine;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionRest;
import net.optifine.shaders.config.ShaderPackParser;
import net.optifine.shaders.config.ShaderParser;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderUniform1f;
import net.optifine.shaders.uniform.ShaderUniform1i;
import net.optifine.shaders.uniform.ShaderUniform2i;
import net.optifine.shaders.uniform.ShaderUniform3f;
import net.optifine.shaders.uniform.ShaderUniform4f;
import net.optifine.shaders.uniform.ShaderUniform4i;
import net.optifine.shaders.uniform.ShaderUniformM4;
import net.optifine.shaders.uniform.ShaderUniforms;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;

public class Shaders {
   static Minecraft mc;
   static EntityRenderer entityRenderer;
   public static boolean isInitializedOnce = false;
   public static boolean isShaderPackInitialized = false;
   public static ContextCapabilities capabilities;
   public static String glVersionString;
   public static String glVendorString;
   public static String glRendererString;
   public static boolean hasGlGenMipmap = false;
   public static int countResetDisplayLists = 0;
   private static int renderDisplayWidth = 0;
   private static int renderDisplayHeight = 0;
   public static int renderWidth = 0;
   public static int renderHeight = 0;
   public static boolean isRenderingWorld = false;
   public static boolean isRenderingSky = false;
   public static boolean isCompositeRendered = false;
   public static boolean isRenderingDfb = false;
   public static boolean isShadowPass = false;
   public static boolean isEntitiesGlowing = false;
   public static boolean isSleeping;
   private static boolean isRenderingFirstPersonHand;
   private static boolean isHandRenderedMain;
   private static boolean isHandRenderedOff;
   private static boolean skipRenderHandMain;
   private static boolean skipRenderHandOff;
   public static boolean renderItemKeepDepthMask = false;
   public static boolean itemToRenderMainTranslucent = false;
   public static boolean itemToRenderOffTranslucent = false;
   static float[] sunPosition = new float[4];
   static float[] moonPosition = new float[4];
   static float[] shadowLightPosition = new float[4];
   static float[] upPosition = new float[4];
   static float[] shadowLightPositionVector = new float[4];
   static float[] upPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   static float[] sunPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   static float[] moonPosModelView = new float[]{0.0F, -100.0F, 0.0F, 0.0F};
   private static float[] tempMat = new float[16];
   static float clearColorR;
   static float clearColorG;
   static float clearColorB;
   static float skyColorR;
   static float skyColorG;
   static float skyColorB;
   static long worldTime = 0L;
   static long lastWorldTime = 0L;
   static long diffWorldTime = 0L;
   static float celestialAngle = 0.0F;
   static float sunAngle = 0.0F;
   static float shadowAngle = 0.0F;
   static int moonPhase = 0;
   static long systemTime = 0L;
   static long lastSystemTime = 0L;
   static long diffSystemTime = 0L;
   static int frameCounter = 0;
   static float frameTime = 0.0F;
   static float frameTimeCounter = 0.0F;
   static int systemTimeInt32 = 0;
   static float rainStrength = 0.0F;
   static float wetness = 0.0F;
   public static float wetnessHalfLife = 600.0F;
   public static float drynessHalfLife = 200.0F;
   public static float eyeBrightnessHalflife = 10.0F;
   static boolean usewetness = false;
   static int isEyeInWater = 0;
   static int eyeBrightness = 0;
   static float eyeBrightnessFadeX = 0.0F;
   static float eyeBrightnessFadeY = 0.0F;
   static float eyePosY = 0.0F;
   static float centerDepth = 0.0F;
   static float centerDepthSmooth = 0.0F;
   static float centerDepthSmoothHalflife = 1.0F;
   static boolean centerDepthSmoothEnabled = false;
   static int superSamplingLevel = 1;
   static float nightVision = 0.0F;
   static float blindness = 0.0F;
   static boolean lightmapEnabled = false;
   static boolean fogEnabled = true;
   public static int entityAttrib = 10;
   public static int midTexCoordAttrib = 11;
   public static int tangentAttrib = 12;
   public static boolean useEntityAttrib = false;
   public static boolean useMidTexCoordAttrib = false;
   public static boolean useTangentAttrib = false;
   public static boolean progUseEntityAttrib = false;
   public static boolean progUseMidTexCoordAttrib = false;
   public static boolean progUseTangentAttrib = false;
   private static boolean progArbGeometryShader4 = false;
   private static int progMaxVerticesOut = 3;
   private static boolean hasGeometryShaders = false;
   public static int atlasSizeX = 0;
   public static int atlasSizeY = 0;
   private static ShaderUniforms shaderUniforms = new ShaderUniforms();
   public static ShaderUniform4f uniform_entityColor;
   public static ShaderUniform1i uniform_entityId;
   public static ShaderUniform1i uniform_blockEntityId;
   public static ShaderUniform1i uniform_texture;
   public static ShaderUniform1i uniform_lightmap;
   public static ShaderUniform1i uniform_normals;
   public static ShaderUniform1i uniform_specular;
   public static ShaderUniform1i uniform_shadow;
   public static ShaderUniform1i uniform_watershadow;
   public static ShaderUniform1i uniform_shadowtex0;
   public static ShaderUniform1i uniform_shadowtex1;
   public static ShaderUniform1i uniform_depthtex0;
   public static ShaderUniform1i uniform_depthtex1;
   public static ShaderUniform1i uniform_shadowcolor;
   public static ShaderUniform1i uniform_shadowcolor0;
   public static ShaderUniform1i uniform_shadowcolor1;
   public static ShaderUniform1i uniform_noisetex;
   public static ShaderUniform1i uniform_gcolor;
   public static ShaderUniform1i uniform_gdepth;
   public static ShaderUniform1i uniform_gnormal;
   public static ShaderUniform1i uniform_composite;
   public static ShaderUniform1i uniform_gaux1;
   public static ShaderUniform1i uniform_gaux2;
   public static ShaderUniform1i uniform_gaux3;
   public static ShaderUniform1i uniform_gaux4;
   public static ShaderUniform1i uniform_colortex0;
   public static ShaderUniform1i uniform_colortex1;
   public static ShaderUniform1i uniform_colortex2;
   public static ShaderUniform1i uniform_colortex3;
   public static ShaderUniform1i uniform_colortex4;
   public static ShaderUniform1i uniform_colortex5;
   public static ShaderUniform1i uniform_colortex6;
   public static ShaderUniform1i uniform_colortex7;
   public static ShaderUniform1i uniform_gdepthtex;
   public static ShaderUniform1i uniform_depthtex2;
   public static ShaderUniform1i uniform_tex;
   public static ShaderUniform1i uniform_heldItemId;
   public static ShaderUniform1i uniform_heldBlockLightValue;
   public static ShaderUniform1i uniform_heldItemId2;
   public static ShaderUniform1i uniform_heldBlockLightValue2;
   public static ShaderUniform1i uniform_fogMode;
   public static ShaderUniform1f uniform_fogDensity;
   public static ShaderUniform3f uniform_fogColor;
   public static ShaderUniform3f uniform_skyColor;
   public static ShaderUniform1i uniform_worldTime;
   public static ShaderUniform1i uniform_worldDay;
   public static ShaderUniform1i uniform_moonPhase;
   public static ShaderUniform1i uniform_frameCounter;
   public static ShaderUniform1f uniform_frameTime;
   public static ShaderUniform1f uniform_frameTimeCounter;
   public static ShaderUniform1f uniform_sunAngle;
   public static ShaderUniform1f uniform_shadowAngle;
   public static ShaderUniform1f uniform_rainStrength;
   public static ShaderUniform1f uniform_aspectRatio;
   public static ShaderUniform1f uniform_viewWidth;
   public static ShaderUniform1f uniform_viewHeight;
   public static ShaderUniform1f uniform_near;
   public static ShaderUniform1f uniform_far;
   public static ShaderUniform3f uniform_sunPosition;
   public static ShaderUniform3f uniform_moonPosition;
   public static ShaderUniform3f uniform_shadowLightPosition;
   public static ShaderUniform3f uniform_upPosition;
   public static ShaderUniform3f uniform_previousCameraPosition;
   public static ShaderUniform3f uniform_cameraPosition;
   public static ShaderUniformM4 uniform_gbufferModelView;
   public static ShaderUniformM4 uniform_gbufferModelViewInverse;
   public static ShaderUniformM4 uniform_gbufferPreviousProjection;
   public static ShaderUniformM4 uniform_gbufferProjection;
   public static ShaderUniformM4 uniform_gbufferProjectionInverse;
   public static ShaderUniformM4 uniform_gbufferPreviousModelView;
   public static ShaderUniformM4 uniform_shadowProjection;
   public static ShaderUniformM4 uniform_shadowProjectionInverse;
   public static ShaderUniformM4 uniform_shadowModelView;
   public static ShaderUniformM4 uniform_shadowModelViewInverse;
   public static ShaderUniform1f uniform_wetness;
   public static ShaderUniform1f uniform_eyeAltitude;
   public static ShaderUniform2i uniform_eyeBrightness;
   public static ShaderUniform2i uniform_eyeBrightnessSmooth;
   public static ShaderUniform2i uniform_terrainTextureSize;
   public static ShaderUniform1i uniform_terrainIconSize;
   public static ShaderUniform1i uniform_isEyeInWater;
   public static ShaderUniform1f uniform_nightVision;
   public static ShaderUniform1f uniform_blindness;
   public static ShaderUniform1f uniform_screenBrightness;
   public static ShaderUniform1i uniform_hideGUI;
   public static ShaderUniform1f uniform_centerDepthSmooth;
   public static ShaderUniform2i uniform_atlasSize;
   public static ShaderUniform4i uniform_blendFunc;
   public static ShaderUniform1i uniform_instanceId;
   static double previousCameraPositionX;
   static double previousCameraPositionY;
   static double previousCameraPositionZ;
   static double cameraPositionX;
   static double cameraPositionY;
   static double cameraPositionZ;
   static int cameraOffsetX;
   static int cameraOffsetZ;
   static int shadowPassInterval;
   public static boolean needResizeShadow;
   static int shadowMapWidth;
   static int shadowMapHeight;
   static int spShadowMapWidth;
   static int spShadowMapHeight;
   static float shadowMapFOV;
   static float shadowMapHalfPlane;
   static boolean shadowMapIsOrtho;
   static float shadowDistanceRenderMul;
   static int shadowPassCounter;
   static int preShadowPassThirdPersonView;
   public static boolean shouldSkipDefaultShadow;
   static boolean waterShadowEnabled;
   static final int MaxDrawBuffers = 8;
   static final int MaxColorBuffers = 8;
   static final int MaxDepthBuffers = 3;
   static final int MaxShadowColorBuffers = 8;
   static final int MaxShadowDepthBuffers = 2;
   static int usedColorBuffers;
   static int usedDepthBuffers;
   static int usedShadowColorBuffers;
   static int usedShadowDepthBuffers;
   static int usedColorAttachs;
   static int usedDrawBuffers;
   static int dfb;
   static int sfb;
   private static int[] gbuffersFormat;
   public static boolean[] gbuffersClear;
   public static Vector4f[] gbuffersClearColor;
   private static Programs programs;
   public static final Program ProgramNone;
   public static final Program ProgramShadow;
   public static final Program ProgramShadowSolid;
   public static final Program ProgramShadowCutout;
   public static final Program ProgramBasic;
   public static final Program ProgramTextured;
   public static final Program ProgramTexturedLit;
   public static final Program ProgramSkyBasic;
   public static final Program ProgramSkyTextured;
   public static final Program ProgramClouds;
   public static final Program ProgramTerrain;
   public static final Program ProgramTerrainSolid;
   public static final Program ProgramTerrainCutoutMip;
   public static final Program ProgramTerrainCutout;
   public static final Program ProgramDamagedBlock;
   public static final Program ProgramBlock;
   public static final Program ProgramBeaconBeam;
   public static final Program ProgramItem;
   public static final Program ProgramEntities;
   public static final Program ProgramEntitiesGlowing;
   public static final Program ProgramArmorGlint;
   public static final Program ProgramSpiderEyes;
   public static final Program ProgramHand;
   public static final Program ProgramWeather;
   public static final Program ProgramDeferredPre;
   public static final Program[] ProgramsDeferred;
   public static final Program ProgramDeferred;
   public static final Program ProgramWater;
   public static final Program ProgramHandWater;
   public static final Program ProgramCompositePre;
   public static final Program[] ProgramsComposite;
   public static final Program ProgramComposite;
   public static final Program ProgramFinal;
   public static final int ProgramCount;
   public static final Program[] ProgramsAll;
   public static Program activeProgram;
   public static int activeProgramID;
   private static ProgramStack programStackLeash;
   private static boolean hasDeferredPrograms;
   static IntBuffer activeDrawBuffers;
   private static int activeCompositeMipmapSetting;
   public static Properties loadedShaders;
   public static Properties shadersConfig;
   public static ITextureObject defaultTexture;
   public static boolean[] shadowHardwareFilteringEnabled;
   public static boolean[] shadowMipmapEnabled;
   public static boolean[] shadowFilterNearest;
   public static boolean[] shadowColorMipmapEnabled;
   public static boolean[] shadowColorFilterNearest;
   public static boolean configTweakBlockDamage;
   public static boolean configCloudShadow;
   public static float configHandDepthMul;
   public static float configRenderResMul;
   public static float configShadowResMul;
   public static int configTexMinFilB;
   public static int configTexMinFilN;
   public static int configTexMinFilS;
   public static int configTexMagFilB;
   public static int configTexMagFilN;
   public static int configTexMagFilS;
   public static boolean configShadowClipFrustrum;
   public static boolean configNormalMap;
   public static boolean configSpecularMap;
   public static PropertyDefaultTrueFalse configOldLighting;
   public static PropertyDefaultTrueFalse configOldHandLight;
   public static int configAntialiasingLevel;
   public static final int texMinFilRange = 3;
   public static final int texMagFilRange = 2;
   public static final String[] texMinFilDesc;
   public static final String[] texMagFilDesc;
   public static final int[] texMinFilValue;
   public static final int[] texMagFilValue;
   private static IShaderPack shaderPack;
   public static boolean shaderPackLoaded;
   public static String currentShaderName;
   public static final String SHADER_PACK_NAME_NONE = "OFF";
   public static final String SHADER_PACK_NAME_DEFAULT = "(internal)";
   public static final String SHADER_PACKS_DIR_NAME = "shaderpacks";
   public static final String OPTIONS_FILE_NAME = "optionsshaders.txt";
   public static final File shaderPacksDir;
   static File configFile;
   private static ShaderOption[] shaderPackOptions;
   private static Set<String> shaderPackOptionSliders;
   static ShaderProfile[] shaderPackProfiles;
   static Map<String, ScreenShaderOptions> shaderPackGuiScreens;
   static Map<String, IExpressionBool> shaderPackProgramConditions;
   public static final String PATH_SHADERS_PROPERTIES = "/shaders/shaders.properties";
   public static PropertyDefaultFastFancyOff shaderPackClouds;
   public static PropertyDefaultTrueFalse shaderPackOldLighting;
   public static PropertyDefaultTrueFalse shaderPackOldHandLight;
   public static PropertyDefaultTrueFalse shaderPackDynamicHandLight;
   public static PropertyDefaultTrueFalse shaderPackShadowTranslucent;
   public static PropertyDefaultTrueFalse shaderPackUnderwaterOverlay;
   public static PropertyDefaultTrueFalse shaderPackSun;
   public static PropertyDefaultTrueFalse shaderPackMoon;
   public static PropertyDefaultTrueFalse shaderPackVignette;
   public static PropertyDefaultTrueFalse shaderPackBackFaceSolid;
   public static PropertyDefaultTrueFalse shaderPackBackFaceCutout;
   public static PropertyDefaultTrueFalse shaderPackBackFaceCutoutMipped;
   public static PropertyDefaultTrueFalse shaderPackBackFaceTranslucent;
   public static PropertyDefaultTrueFalse shaderPackRainDepth;
   public static PropertyDefaultTrueFalse shaderPackBeaconBeamDepth;
   public static PropertyDefaultTrueFalse shaderPackSeparateAo;
   public static PropertyDefaultTrueFalse shaderPackFrustumCulling;
   private static Map<String, String> shaderPackResources;
   private static World currentWorld;
   private static List<Integer> shaderPackDimensions;
   private static ICustomTexture[] customTexturesGbuffers;
   private static ICustomTexture[] customTexturesComposite;
   private static ICustomTexture[] customTexturesDeferred;
   private static String noiseTexturePath;
   private static CustomUniforms customUniforms;
   private static final int STAGE_GBUFFERS = 0;
   private static final int STAGE_COMPOSITE = 1;
   private static final int STAGE_DEFERRED = 2;
   private static final String[] STAGE_NAMES;
   public static final boolean enableShadersOption = true;
   private static final boolean enableShadersDebug = true;
   public static final boolean saveFinalShaders;
   public static float blockLightLevel05;
   public static float blockLightLevel06;
   public static float blockLightLevel08;
   public static float aoLevel;
   public static float sunPathRotation;
   public static float shadowAngleInterval;
   public static int fogMode;
   public static float fogDensity;
   public static float fogColorR;
   public static float fogColorG;
   public static float fogColorB;
   public static float shadowIntervalSize;
   public static int terrainIconSize;
   public static int[] terrainTextureSize;
   private static ICustomTexture noiseTexture;
   private static boolean noiseTextureEnabled;
   private static int noiseTextureResolution;
   static final int[] colorTextureImageUnit;
   private static final int bigBufferSize;
   private static final ByteBuffer bigBuffer;
   static final float[] faProjection;
   static final float[] faProjectionInverse;
   static final float[] faModelView;
   static final float[] faModelViewInverse;
   static final float[] faShadowProjection;
   static final float[] faShadowProjectionInverse;
   static final float[] faShadowModelView;
   static final float[] faShadowModelViewInverse;
   static final FloatBuffer projection;
   static final FloatBuffer projectionInverse;
   static final FloatBuffer modelView;
   static final FloatBuffer modelViewInverse;
   static final FloatBuffer shadowProjection;
   static final FloatBuffer shadowProjectionInverse;
   static final FloatBuffer shadowModelView;
   static final FloatBuffer shadowModelViewInverse;
   static final FloatBuffer previousProjection;
   static final FloatBuffer previousModelView;
   static final FloatBuffer tempMatrixDirectBuffer;
   static final FloatBuffer tempDirectFloatBuffer;
   static final IntBuffer dfbColorTextures;
   static final IntBuffer dfbDepthTextures;
   static final IntBuffer sfbColorTextures;
   static final IntBuffer sfbDepthTextures;
   static final IntBuffer dfbDrawBuffers;
   static final IntBuffer sfbDrawBuffers;
   static final IntBuffer drawBuffersNone;
   static final IntBuffer drawBuffersColorAtt0;
   static final FlipTextures dfbColorTexturesFlip;
   static Map<Block, Integer> mapBlockToEntityData;
   private static final String[] formatNames;
   private static final int[] formatIds;
   private static final Pattern patternLoadEntityDataMap;
   public static int[] entityData;
   public static int entityDataIndex;

   private Shaders() {
   }

   private static ByteBuffer nextByteBuffer(int size) {
      ByteBuffer buffer = bigBuffer;
      int pos = buffer.limit();
      buffer.position(pos).limit(pos + size);
      return buffer.slice();
   }

   public static IntBuffer nextIntBuffer(int size) {
      ByteBuffer buffer = bigBuffer;
      int pos = buffer.limit();
      buffer.position(pos).limit(pos + size * 4);
      return buffer.asIntBuffer();
   }

   private static FloatBuffer nextFloatBuffer(int size) {
      ByteBuffer buffer = bigBuffer;
      int pos = buffer.limit();
      buffer.position(pos).limit(pos + size * 4);
      return buffer.asFloatBuffer();
   }

   private static IntBuffer[] nextIntBufferArray(int count, int size) {
      IntBuffer[] aib = new IntBuffer[count];

      for(int i = 0; i < count; ++i) {
         aib[i] = nextIntBuffer(size);
      }

      return aib;
   }

   public static void loadConfig() {
      SMCLog.info("Load shaders configuration.");

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }
      } catch (Exception var8) {
         SMCLog.severe("Failed to open the shaderpacks directory: " + shaderPacksDir);
      }

      shadersConfig = new PropertiesOrdered();
      shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
      if (configFile.exists()) {
         try {
            FileReader reader = new FileReader(configFile);
            shadersConfig.load(reader);
            reader.close();
         } catch (Exception var7) {
         }
      }

      if (!configFile.exists()) {
         try {
            storeConfig();
         } catch (Exception var6) {
         }
      }

      EnumShaderOption[] ops = EnumShaderOption.values();

      for(int i = 0; i < ops.length; ++i) {
         EnumShaderOption op = ops[i];
         String key = op.getPropertyKey();
         String def = op.getValueDefault();
         String val = shadersConfig.getProperty(key, def);
         setEnumShaderOption(op, val);
      }

      loadShaderPack();
   }

   private static void setEnumShaderOption(EnumShaderOption eso, String str) {
      if (str == null) {
         str = eso.getValueDefault();
      }

      switch(eso) {
      case ANTIALIASING:
         configAntialiasingLevel = Config.parseInt(str, 0);
         break;
      case NORMAL_MAP:
         configNormalMap = Config.parseBoolean(str, true);
         break;
      case SPECULAR_MAP:
         configSpecularMap = Config.parseBoolean(str, true);
         break;
      case RENDER_RES_MUL:
         configRenderResMul = Config.parseFloat(str, 1.0F);
         break;
      case SHADOW_RES_MUL:
         configShadowResMul = Config.parseFloat(str, 1.0F);
         break;
      case HAND_DEPTH_MUL:
         configHandDepthMul = Config.parseFloat(str, 0.125F);
         break;
      case CLOUD_SHADOW:
         configCloudShadow = Config.parseBoolean(str, true);
         break;
      case OLD_HAND_LIGHT:
         configOldHandLight.setPropertyValue(str);
         break;
      case OLD_LIGHTING:
         configOldLighting.setPropertyValue(str);
         break;
      case SHADER_PACK:
         currentShaderName = str;
         break;
      case TWEAK_BLOCK_DAMAGE:
         configTweakBlockDamage = Config.parseBoolean(str, true);
         break;
      case SHADOW_CLIP_FRUSTRUM:
         configShadowClipFrustrum = Config.parseBoolean(str, true);
         break;
      case TEX_MIN_FIL_B:
         configTexMinFilB = Config.parseInt(str, 0);
         break;
      case TEX_MIN_FIL_N:
         configTexMinFilN = Config.parseInt(str, 0);
         break;
      case TEX_MIN_FIL_S:
         configTexMinFilS = Config.parseInt(str, 0);
         break;
      case TEX_MAG_FIL_B:
         configTexMagFilB = Config.parseInt(str, 0);
         break;
      case TEX_MAG_FIL_N:
         configTexMagFilB = Config.parseInt(str, 0);
         break;
      case TEX_MAG_FIL_S:
         configTexMagFilB = Config.parseInt(str, 0);
         break;
      default:
         throw new IllegalArgumentException("Unknown option: " + eso);
      }

   }

   public static void storeConfig() {
      SMCLog.info("Save shaders configuration.");
      if (shadersConfig == null) {
         shadersConfig = new PropertiesOrdered();
      }

      EnumShaderOption[] ops = EnumShaderOption.values();

      for(int i = 0; i < ops.length; ++i) {
         EnumShaderOption op = ops[i];
         String key = op.getPropertyKey();
         String val = getEnumShaderOption(op);
         shadersConfig.setProperty(key, val);
      }

      try {
         FileWriter writer = new FileWriter(configFile);
         shadersConfig.store(writer, (String)null);
         writer.close();
      } catch (Exception var5) {
         SMCLog.severe("Error saving configuration: " + var5.getClass().getName() + ": " + var5.getMessage());
      }

   }

   public static String getEnumShaderOption(EnumShaderOption eso) {
      switch(eso) {
      case ANTIALIASING:
         return Integer.toString(configAntialiasingLevel);
      case NORMAL_MAP:
         return Boolean.toString(configNormalMap);
      case SPECULAR_MAP:
         return Boolean.toString(configSpecularMap);
      case RENDER_RES_MUL:
         return Float.toString(configRenderResMul);
      case SHADOW_RES_MUL:
         return Float.toString(configShadowResMul);
      case HAND_DEPTH_MUL:
         return Float.toString(configHandDepthMul);
      case CLOUD_SHADOW:
         return Boolean.toString(configCloudShadow);
      case OLD_HAND_LIGHT:
         return configOldHandLight.getPropertyValue();
      case OLD_LIGHTING:
         return configOldLighting.getPropertyValue();
      case SHADER_PACK:
         return currentShaderName;
      case TWEAK_BLOCK_DAMAGE:
         return Boolean.toString(configTweakBlockDamage);
      case SHADOW_CLIP_FRUSTRUM:
         return Boolean.toString(configShadowClipFrustrum);
      case TEX_MIN_FIL_B:
         return Integer.toString(configTexMinFilB);
      case TEX_MIN_FIL_N:
         return Integer.toString(configTexMinFilN);
      case TEX_MIN_FIL_S:
         return Integer.toString(configTexMinFilS);
      case TEX_MAG_FIL_B:
         return Integer.toString(configTexMagFilB);
      case TEX_MAG_FIL_N:
         return Integer.toString(configTexMagFilB);
      case TEX_MAG_FIL_S:
         return Integer.toString(configTexMagFilB);
      default:
         throw new IllegalArgumentException("Unknown option: " + eso);
      }
   }

   public static void setShaderPack(String par1name) {
      currentShaderName = par1name;
      shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), par1name);
      loadShaderPack();
   }

   public static void loadShaderPack() {
      boolean shaderPackLoadedPrev = shaderPackLoaded;
      boolean oldLightingPrev = isOldLighting();
      if (mc.field_71438_f != null) {
         mc.field_71438_f.pauseChunkUpdates();
      }

      shaderPackLoaded = false;
      if (shaderPack != null) {
         shaderPack.close();
         shaderPack = null;
         shaderPackResources.clear();
         shaderPackDimensions.clear();
         shaderPackOptions = null;
         shaderPackOptionSliders = null;
         shaderPackProfiles = null;
         shaderPackGuiScreens = null;
         shaderPackProgramConditions.clear();
         shaderPackClouds.resetValue();
         shaderPackOldHandLight.resetValue();
         shaderPackDynamicHandLight.resetValue();
         shaderPackOldLighting.resetValue();
         resetCustomTextures();
         noiseTexturePath = null;
      }

      boolean shadersBlocked = false;
      if (Config.isAntialiasing()) {
         SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
         shadersBlocked = true;
      }

      if (Config.isAnisotropicFiltering()) {
         SMCLog.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + Config.getAnisotropicFilterLevel() + "x");
         shadersBlocked = true;
      }

      if (Config.isFastRender()) {
         SMCLog.info("Shaders can not be loaded, Fast Render is enabled.");
         shadersBlocked = true;
      }

      String packName = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "(internal)");
      if (!shadersBlocked) {
         shaderPack = getShaderPack(packName);
         shaderPackLoaded = shaderPack != null;
      }

      if (shaderPackLoaded) {
         SMCLog.info("Loaded shaderpack: " + getShaderPackName());
      } else {
         SMCLog.info("No shaderpack loaded.");
         shaderPack = new ShaderPackNone();
      }

      if (saveFinalShaders) {
         clearDirectory(new File(shaderPacksDir, "debug"));
      }

      loadShaderPackResources();
      loadShaderPackDimensions();
      shaderPackOptions = loadShaderPackOptions();
      loadShaderPackProperties();
      boolean formatChanged = shaderPackLoaded != shaderPackLoadedPrev;
      boolean oldLightingChanged = isOldLighting() != oldLightingPrev;
      if (formatChanged || oldLightingChanged) {
         DefaultVertexFormats.updateVertexFormats();
         if (Reflector.LightUtil.exists()) {
            Reflector.LightUtil_itemConsumer.setValue((Object)null);
            Reflector.LightUtil_tessellator.setValue((Object)null);
         }

         updateBlockLightLevel();
      }

      if (mc.func_110438_M() != null) {
         CustomBlockLayers.update();
      }

      if (mc.field_71438_f != null) {
         mc.field_71438_f.resumeChunkUpdates();
      }

      if ((formatChanged || oldLightingChanged) && mc.func_110442_L() != null) {
         mc.func_175603_A();
      }

   }

   public static IShaderPack getShaderPack(String name) {
      if (name == null) {
         return null;
      } else {
         name = name.trim();
         if (!name.isEmpty() && !name.equals("OFF")) {
            if (name.equals("(internal)")) {
               return new ShaderPackDefault();
            } else {
               try {
                  File packFile = new File(shaderPacksDir, name);
                  if (packFile.isDirectory()) {
                     return new ShaderPackFolder(name, packFile);
                  } else {
                     return packFile.isFile() && name.toLowerCase().endsWith(".zip") ? new ShaderPackZip(name, packFile) : null;
                  }
               } catch (Exception var2) {
                  var2.printStackTrace();
                  return null;
               }
            }
         } else {
            return null;
         }
      }
   }

   public static IShaderPack getShaderPack() {
      return shaderPack;
   }

   private static void loadShaderPackDimensions() {
      shaderPackDimensions.clear();

      for(int i = -128; i <= 128; ++i) {
         String worldDir = "/shaders/world" + i;
         if (shaderPack.hasDirectory(worldDir)) {
            shaderPackDimensions.add(i);
         }
      }

      if (shaderPackDimensions.size() > 0) {
         Integer[] ids = (Integer[])((Integer[])shaderPackDimensions.toArray(new Integer[shaderPackDimensions.size()]));
         Config.dbg("[Shaders] Worlds: " + Config.arrayToString((Object[])ids));
      }

   }

   private static void loadShaderPackProperties() {
      shaderPackClouds.resetValue();
      shaderPackOldHandLight.resetValue();
      shaderPackDynamicHandLight.resetValue();
      shaderPackOldLighting.resetValue();
      shaderPackShadowTranslucent.resetValue();
      shaderPackUnderwaterOverlay.resetValue();
      shaderPackSun.resetValue();
      shaderPackMoon.resetValue();
      shaderPackVignette.resetValue();
      shaderPackBackFaceSolid.resetValue();
      shaderPackBackFaceCutout.resetValue();
      shaderPackBackFaceCutoutMipped.resetValue();
      shaderPackBackFaceTranslucent.resetValue();
      shaderPackRainDepth.resetValue();
      shaderPackBeaconBeamDepth.resetValue();
      shaderPackSeparateAo.resetValue();
      shaderPackFrustumCulling.resetValue();
      BlockAliases.reset();
      ItemAliases.reset();
      EntityAliases.reset();
      customUniforms = null;

      for(int i = 0; i < ProgramsAll.length; ++i) {
         Program p = ProgramsAll[i];
         p.resetProperties();
      }

      if (shaderPack != null) {
         BlockAliases.update(shaderPack);
         ItemAliases.update(shaderPack);
         EntityAliases.update(shaderPack);
         String path = "/shaders/shaders.properties";

         try {
            InputStream in = shaderPack.getResourceAsStream(path);
            if (in == null) {
               return;
            }

            in = MacroProcessor.process(in, path);
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            shaderPackClouds.loadFrom(props);
            shaderPackOldHandLight.loadFrom(props);
            shaderPackDynamicHandLight.loadFrom(props);
            shaderPackOldLighting.loadFrom(props);
            shaderPackShadowTranslucent.loadFrom(props);
            shaderPackUnderwaterOverlay.loadFrom(props);
            shaderPackSun.loadFrom(props);
            shaderPackVignette.loadFrom(props);
            shaderPackMoon.loadFrom(props);
            shaderPackBackFaceSolid.loadFrom(props);
            shaderPackBackFaceCutout.loadFrom(props);
            shaderPackBackFaceCutoutMipped.loadFrom(props);
            shaderPackBackFaceTranslucent.loadFrom(props);
            shaderPackRainDepth.loadFrom(props);
            shaderPackBeaconBeamDepth.loadFrom(props);
            shaderPackSeparateAo.loadFrom(props);
            shaderPackFrustumCulling.loadFrom(props);
            shaderPackOptionSliders = ShaderPackParser.parseOptionSliders(props, shaderPackOptions);
            shaderPackProfiles = ShaderPackParser.parseProfiles(props, shaderPackOptions);
            shaderPackGuiScreens = ShaderPackParser.parseGuiScreens(props, shaderPackProfiles, shaderPackOptions);
            shaderPackProgramConditions = ShaderPackParser.parseProgramConditions(props, shaderPackOptions);
            customTexturesGbuffers = loadCustomTextures(props, 0);
            customTexturesComposite = loadCustomTextures(props, 1);
            customTexturesDeferred = loadCustomTextures(props, 2);
            noiseTexturePath = props.getProperty("texture.noise");
            if (noiseTexturePath != null) {
               noiseTextureEnabled = true;
            }

            customUniforms = ShaderPackParser.parseCustomUniforms(props);
            ShaderPackParser.parseAlphaStates(props);
            ShaderPackParser.parseBlendStates(props);
            ShaderPackParser.parseRenderScales(props);
            ShaderPackParser.parseBuffersFlip(props);
         } catch (IOException var3) {
            Config.warn("[Shaders] Error reading: " + path);
         }

      }
   }

   private static ICustomTexture[] loadCustomTextures(Properties props, int stage) {
      String PREFIX_TEXTURE = "texture." + STAGE_NAMES[stage] + ".";
      Set keys = props.keySet();
      List<ICustomTexture> list = new ArrayList();
      Iterator it = keys.iterator();

      while(it.hasNext()) {
         String key = (String)it.next();
         if (key.startsWith(PREFIX_TEXTURE)) {
            String name = StrUtils.removePrefix(key, PREFIX_TEXTURE);
            name = StrUtils.removeSuffix(name, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String path = props.getProperty(key).trim();
            int index = getTextureIndex(stage, name);
            if (index < 0) {
               SMCLog.warning("Invalid texture name: " + key);
            } else {
               ICustomTexture ct = loadCustomTexture(index, path);
               if (ct != null) {
                  SMCLog.info("Custom texture: " + key + " = " + path);
                  list.add(ct);
               }
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         ICustomTexture[] cts = (ICustomTexture[])((ICustomTexture[])list.toArray(new ICustomTexture[list.size()]));
         return cts;
      }
   }

   private static ICustomTexture loadCustomTexture(int textureUnit, String path) {
      if (path == null) {
         return null;
      } else {
         path = path.trim();
         if (path.indexOf(58) >= 0) {
            return loadCustomTextureLocation(textureUnit, path);
         } else {
            return path.indexOf(32) >= 0 ? loadCustomTextureRaw(textureUnit, path) : loadCustomTextureShaders(textureUnit, path);
         }
      }
   }

   private static ICustomTexture loadCustomTextureLocation(int textureUnit, String path) {
      String pathFull = path.trim();
      int variant = 0;
      if (pathFull.startsWith("minecraft:textures/")) {
         pathFull = StrUtils.addSuffixCheck(pathFull, ".png");
         if (pathFull.endsWith("_n.png")) {
            pathFull = StrUtils.replaceSuffix(pathFull, "_n.png", ".png");
            variant = 1;
         } else if (pathFull.endsWith("_s.png")) {
            pathFull = StrUtils.replaceSuffix(pathFull, "_s.png", ".png");
            variant = 2;
         }
      }

      ResourceLocation loc = new ResourceLocation(pathFull);
      CustomTextureLocation ctv = new CustomTextureLocation(textureUnit, loc, variant);
      return ctv;
   }

   private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line) {
      ConnectedParser cp = new ConnectedParser("Shaders");
      String[] parts = Config.tokenize(line, " ");
      Deque<String> params = new ArrayDeque(Arrays.asList(parts));
      String path = (String)params.poll();
      TextureType type = (TextureType)cp.parseEnum((String)params.poll(), TextureType.values(), "texture type");
      if (type == null) {
         SMCLog.warning("Invalid raw texture type: " + line);
         return null;
      } else {
         InternalFormat internalFormat = (InternalFormat)cp.parseEnum((String)params.poll(), InternalFormat.values(), "internal format");
         if (internalFormat == null) {
            SMCLog.warning("Invalid raw texture internal format: " + line);
            return null;
         } else {
            int width = false;
            int height = 0;
            int depth = 0;
            int width;
            switch(type) {
            case TEXTURE_1D:
               width = cp.parseInt((String)params.poll(), -1);
               break;
            case TEXTURE_2D:
               width = cp.parseInt((String)params.poll(), -1);
               height = cp.parseInt((String)params.poll(), -1);
               break;
            case TEXTURE_3D:
               width = cp.parseInt((String)params.poll(), -1);
               height = cp.parseInt((String)params.poll(), -1);
               depth = cp.parseInt((String)params.poll(), -1);
               break;
            case TEXTURE_RECTANGLE:
               width = cp.parseInt((String)params.poll(), -1);
               height = cp.parseInt((String)params.poll(), -1);
               break;
            default:
               SMCLog.warning("Invalid raw texture type: " + type);
               return null;
            }

            if (width >= 0 && height >= 0 && depth >= 0) {
               PixelFormat pixelFormat = (PixelFormat)cp.parseEnum((String)params.poll(), PixelFormat.values(), "pixel format");
               if (pixelFormat == null) {
                  SMCLog.warning("Invalid raw texture pixel format: " + line);
                  return null;
               } else {
                  PixelType pixelType = (PixelType)cp.parseEnum((String)params.poll(), PixelType.values(), "pixel type");
                  if (pixelType == null) {
                     SMCLog.warning("Invalid raw texture pixel type: " + line);
                     return null;
                  } else if (!params.isEmpty()) {
                     SMCLog.warning("Invalid raw texture, too many parameters: " + line);
                     return null;
                  } else {
                     return loadCustomTextureRaw(textureUnit, line, path, type, internalFormat, width, height, depth, pixelFormat, pixelType);
                  }
               }
            } else {
               SMCLog.warning("Invalid raw texture size: " + line);
               return null;
            }
         }
      }
   }

   private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line, String path, TextureType type, InternalFormat internalFormat, int width, int height, int depth, PixelFormat pixelFormat, PixelType pixelType) {
      try {
         String pathFull = "shaders/" + StrUtils.removePrefix(path, "/");
         InputStream in = shaderPack.getResourceAsStream(pathFull);
         if (in == null) {
            SMCLog.warning("Raw texture not found: " + path);
            return null;
         } else {
            byte[] bytes = Config.readAll(in);
            IOUtils.closeQuietly(in);
            ByteBuffer bb = GLAllocation.func_74524_c(bytes.length);
            bb.put(bytes);
            bb.flip();
            CustomTextureRaw ctr = new CustomTextureRaw(type, internalFormat, width, height, depth, pixelFormat, pixelType, bb, textureUnit);
            return ctr;
         }
      } catch (IOException var15) {
         SMCLog.warning("Error loading raw texture: " + path);
         SMCLog.warning("" + var15.getClass().getName() + ": " + var15.getMessage());
         return null;
      }
   }

   private static ICustomTexture loadCustomTextureShaders(int textureUnit, String path) {
      path = path.trim();
      if (path.indexOf(46) < 0) {
         path = path + ".png";
      }

      try {
         String pathFull = "shaders/" + StrUtils.removePrefix(path, "/");
         InputStream in = shaderPack.getResourceAsStream(pathFull);
         if (in == null) {
            SMCLog.warning("Texture not found: " + path);
            return null;
         } else {
            IOUtils.closeQuietly(in);
            SimpleShaderTexture tex = new SimpleShaderTexture(pathFull);
            tex.func_110551_a(mc.func_110442_L());
            CustomTexture ct = new CustomTexture(textureUnit, pathFull, tex);
            return ct;
         }
      } catch (IOException var6) {
         SMCLog.warning("Error loading texture: " + path);
         SMCLog.warning("" + var6.getClass().getName() + ": " + var6.getMessage());
         return null;
      }
   }

   private static int getTextureIndex(int stage, String name) {
      if (stage == 0) {
         label225: {
            if (name.equals("texture")) {
               return 0;
            }

            if (name.equals("lightmap")) {
               return 1;
            }

            if (name.equals("normals")) {
               return 2;
            }

            if (name.equals("specular")) {
               return 3;
            }

            if (!name.equals("shadowtex0") && !name.equals("watershadow")) {
               if (name.equals("shadow")) {
                  return waterShadowEnabled ? 5 : 4;
               }

               if (name.equals("shadowtex1")) {
                  return 5;
               }

               if (name.equals("depthtex0")) {
                  return 6;
               }

               if (name.equals("gaux1")) {
                  return 7;
               }

               if (name.equals("gaux2")) {
                  return 8;
               }

               if (name.equals("gaux3")) {
                  return 9;
               }

               if (name.equals("gaux4")) {
                  return 10;
               }

               if (name.equals("depthtex1")) {
                  return 12;
               }

               if (!name.equals("shadowcolor0") && !name.equals("shadowcolor")) {
                  if (name.equals("shadowcolor1")) {
                     return 14;
                  }

                  if (name.equals("noisetex")) {
                     return 15;
                  }
                  break label225;
               }

               return 13;
            }

            return 4;
         }
      }

      if (stage == 1 || stage == 2) {
         if (name.equals("colortex0") || name.equals("colortex0")) {
            return 0;
         }

         if (name.equals("colortex1") || name.equals("gdepth")) {
            return 1;
         }

         if (name.equals("colortex2") || name.equals("gnormal")) {
            return 2;
         }

         if (name.equals("colortex3") || name.equals("composite")) {
            return 3;
         }

         if (name.equals("shadowtex0") || name.equals("watershadow")) {
            return 4;
         }

         if (name.equals("shadow")) {
            return waterShadowEnabled ? 5 : 4;
         }

         if (name.equals("shadowtex1")) {
            return 5;
         }

         if (name.equals("depthtex0") || name.equals("gdepthtex")) {
            return 6;
         }

         if (name.equals("colortex4") || name.equals("gaux1")) {
            return 7;
         }

         if (name.equals("colortex5") || name.equals("gaux2")) {
            return 8;
         }

         if (name.equals("colortex6") || name.equals("gaux3")) {
            return 9;
         }

         if (name.equals("colortex7") || name.equals("gaux4")) {
            return 10;
         }

         if (name.equals("depthtex1")) {
            return 11;
         }

         if (name.equals("depthtex2")) {
            return 12;
         }

         if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
            return 13;
         }

         if (name.equals("shadowcolor1")) {
            return 14;
         }

         if (name.equals("noisetex")) {
            return 15;
         }
      }

      return -1;
   }

   private static void bindCustomTextures(ICustomTexture[] cts) {
      if (cts != null) {
         for(int i = 0; i < cts.length; ++i) {
            ICustomTexture ct = cts[i];
            GlStateManager.func_179138_g('\u84c0' + ct.getTextureUnit());
            int texId = ct.getTextureId();
            int target = ct.getTarget();
            if (target == 3553) {
               GlStateManager.func_179144_i(texId);
            } else {
               GL11.glBindTexture(target, texId);
            }
         }

      }
   }

   private static void resetCustomTextures() {
      deleteCustomTextures(customTexturesGbuffers);
      deleteCustomTextures(customTexturesComposite);
      deleteCustomTextures(customTexturesDeferred);
      customTexturesGbuffers = null;
      customTexturesComposite = null;
      customTexturesDeferred = null;
   }

   private static void deleteCustomTextures(ICustomTexture[] cts) {
      if (cts != null) {
         for(int i = 0; i < cts.length; ++i) {
            ICustomTexture ct = cts[i];
            ct.deleteTexture();
         }

      }
   }

   public static ShaderOption[] getShaderPackOptions(String screenName) {
      ShaderOption[] ops = (ShaderOption[])shaderPackOptions.clone();
      if (shaderPackGuiScreens == null) {
         if (shaderPackProfiles != null) {
            ShaderOptionProfile optionProfile = new ShaderOptionProfile(shaderPackProfiles, ops);
            ops = (ShaderOption[])((ShaderOption[])Config.addObjectToArray(ops, optionProfile, 0));
         }

         ops = getVisibleOptions(ops);
         return ops;
      } else {
         String key = screenName != null ? "screen." + screenName : "screen";
         ScreenShaderOptions sso = (ScreenShaderOptions)shaderPackGuiScreens.get(key);
         if (sso == null) {
            return new ShaderOption[0];
         } else {
            ShaderOption[] sos = sso.getShaderOptions();
            List<ShaderOption> list = new ArrayList();

            for(int i = 0; i < sos.length; ++i) {
               ShaderOption so = sos[i];
               if (so == null) {
                  list.add((Object)null);
               } else if (so instanceof ShaderOptionRest) {
                  ShaderOption[] restOps = getShaderOptionsRest(shaderPackGuiScreens, ops);
                  list.addAll(Arrays.asList(restOps));
               } else {
                  list.add(so);
               }
            }

            ShaderOption[] sosExp = (ShaderOption[])((ShaderOption[])list.toArray(new ShaderOption[list.size()]));
            return sosExp;
         }
      }
   }

   public static int getShaderPackColumns(String screenName, int def) {
      String key = screenName != null ? "screen." + screenName : "screen";
      if (shaderPackGuiScreens == null) {
         return def;
      } else {
         ScreenShaderOptions sso = (ScreenShaderOptions)shaderPackGuiScreens.get(key);
         return sso == null ? def : sso.getColumns();
      }
   }

   private static ShaderOption[] getShaderOptionsRest(Map<String, ScreenShaderOptions> mapScreens, ShaderOption[] ops) {
      Set<String> setNames = new HashSet();
      Set<String> keys = mapScreens.keySet();
      Iterator it = keys.iterator();

      while(it.hasNext()) {
         String key = (String)it.next();
         ScreenShaderOptions sso = (ScreenShaderOptions)mapScreens.get(key);
         ShaderOption[] sos = sso.getShaderOptions();

         for(int v = 0; v < sos.length; ++v) {
            ShaderOption so = sos[v];
            if (so != null) {
               setNames.add(so.getName());
            }
         }
      }

      List<ShaderOption> list = new ArrayList();

      for(int i = 0; i < ops.length; ++i) {
         ShaderOption so = ops[i];
         if (so.isVisible()) {
            String name = so.getName();
            if (!setNames.contains(name)) {
               list.add(so);
            }
         }
      }

      ShaderOption[] sos = (ShaderOption[])((ShaderOption[])list.toArray(new ShaderOption[list.size()]));
      return sos;
   }

   public static ShaderOption getShaderOption(String name) {
      return ShaderUtils.getShaderOption(name, shaderPackOptions);
   }

   public static ShaderOption[] getShaderPackOptions() {
      return shaderPackOptions;
   }

   public static boolean isShaderPackOptionSlider(String name) {
      return shaderPackOptionSliders == null ? false : shaderPackOptionSliders.contains(name);
   }

   private static ShaderOption[] getVisibleOptions(ShaderOption[] ops) {
      List<ShaderOption> list = new ArrayList();

      for(int i = 0; i < ops.length; ++i) {
         ShaderOption so = ops[i];
         if (so.isVisible()) {
            list.add(so);
         }
      }

      ShaderOption[] sos = (ShaderOption[])((ShaderOption[])list.toArray(new ShaderOption[list.size()]));
      return sos;
   }

   public static void saveShaderPackOptions() {
      saveShaderPackOptions(shaderPackOptions, shaderPack);
   }

   private static void saveShaderPackOptions(ShaderOption[] sos, IShaderPack sp) {
      Properties props = new PropertiesOrdered();
      if (shaderPackOptions != null) {
         for(int i = 0; i < sos.length; ++i) {
            ShaderOption so = sos[i];
            if (so.isChanged() && so.isEnabled()) {
               props.setProperty(so.getName(), so.getValue());
            }
         }
      }

      try {
         saveOptionProperties(sp, props);
      } catch (IOException var5) {
         Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
         var5.printStackTrace();
      }

   }

   private static void saveOptionProperties(IShaderPack sp, Properties props) throws IOException {
      String path = "shaderpacks/" + sp.getName() + ".txt";
      File propFile = new File(Minecraft.func_71410_x().field_71412_D, path);
      if (props.isEmpty()) {
         propFile.delete();
      } else {
         FileOutputStream fos = new FileOutputStream(propFile);
         props.store(fos, (String)null);
         fos.flush();
         fos.close();
      }
   }

   private static ShaderOption[] loadShaderPackOptions() {
      try {
         String[] programNames = programs.getProgramNames();
         ShaderOption[] sos = ShaderPackParser.parseShaderPackOptions(shaderPack, programNames, shaderPackDimensions);
         Properties props = loadOptionProperties(shaderPack);

         for(int i = 0; i < sos.length; ++i) {
            ShaderOption so = sos[i];
            String val = props.getProperty(so.getName());
            if (val != null) {
               so.resetValue();
               if (!so.setValue(val)) {
                  Config.warn("[Shaders] Invalid value, option: " + so.getName() + ", value: " + val);
               }
            }
         }

         return sos;
      } catch (IOException var6) {
         Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
         var6.printStackTrace();
         return null;
      }
   }

   private static Properties loadOptionProperties(IShaderPack sp) throws IOException {
      Properties props = new PropertiesOrdered();
      String path = "shaderpacks/" + sp.getName() + ".txt";
      File propFile = new File(Minecraft.func_71410_x().field_71412_D, path);
      if (propFile.exists() && propFile.isFile() && propFile.canRead()) {
         FileInputStream fis = new FileInputStream(propFile);
         props.load(fis);
         fis.close();
         return props;
      } else {
         return props;
      }
   }

   public static ShaderOption[] getChangedOptions(ShaderOption[] ops) {
      List<ShaderOption> list = new ArrayList();

      for(int i = 0; i < ops.length; ++i) {
         ShaderOption op = ops[i];
         if (op.isEnabled() && op.isChanged()) {
            list.add(op);
         }
      }

      ShaderOption[] cops = (ShaderOption[])((ShaderOption[])list.toArray(new ShaderOption[list.size()]));
      return cops;
   }

   private static String applyOptions(String line, ShaderOption[] ops) {
      if (ops != null && ops.length > 0) {
         for(int i = 0; i < ops.length; ++i) {
            ShaderOption op = ops[i];
            if (op.matchesLine(line)) {
               line = op.getSourceLine();
               break;
            }
         }

         return line;
      } else {
         return line;
      }
   }

   public static ArrayList listOfShaders() {
      ArrayList<String> list = new ArrayList();
      list.add("OFF");
      list.add("(internal)");
      int countFixed = list.size();

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }

         File[] listOfFiles = shaderPacksDir.listFiles();

         for(int i = 0; i < listOfFiles.length; ++i) {
            File file = listOfFiles[i];
            String name = file.getName();
            if (file.isDirectory()) {
               if (!name.equals("debug")) {
                  File subDir = new File(file, "shaders");
                  if (subDir.exists() && subDir.isDirectory()) {
                     list.add(name);
                  }
               }
            } else if (file.isFile() && name.toLowerCase().endsWith(".zip")) {
               list.add(name);
            }
         }
      } catch (Exception var7) {
      }

      List<String> sortList = list.subList(countFixed, list.size());
      Collections.sort(sortList, String.CASE_INSENSITIVE_ORDER);
      return list;
   }

   public static int checkFramebufferStatus(String location) {
      int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (status != 36053) {
         System.err.format("FramebufferStatus 0x%04X at %s\n", status, location);
      }

      return status;
   }

   public static int checkGLError(String location) {
      int errorCode = GlStateManager.glGetError();
      if (errorCode != 0 && GlErrors.isEnabled(errorCode)) {
         String errorText = Config.getGlErrorString(errorCode);
         String shadersInfo = getErrorInfo(errorCode, location);
         String messageLog = String.format("OpenGL error: %s (%s)%s, at: %s", errorCode, errorText, shadersInfo, location);
         SMCLog.severe(messageLog);
         if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlErrorShaders", 10000L)) {
            String messageChat = I18n.func_135052_a("of.message.openglError", errorCode, errorText);
            printChat(messageChat);
         }
      }

      return errorCode;
   }

   private static String getErrorInfo(int errorCode, String location) {
      StringBuilder sb = new StringBuilder();
      String programRealName;
      if (errorCode == 1286) {
         int statusCode = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         String statusText = getFramebufferStatusText(statusCode);
         programRealName = ", fbStatus: " + statusCode + " (" + statusText + ")";
         sb.append(programRealName);
      }

      String programName = activeProgram.getName();
      if (programName.isEmpty()) {
         programName = "none";
      }

      sb.append(", program: " + programName);
      Program activeProgramReal = getProgramById(activeProgramID);
      if (activeProgramReal != activeProgram) {
         programRealName = activeProgramReal.getName();
         if (programRealName.isEmpty()) {
            programRealName = "none";
         }

         sb.append(" (" + programRealName + ")");
      }

      if (location.equals("setDrawBuffers")) {
         sb.append(", drawBuffers: " + activeProgram.getDrawBufSettings());
      }

      return sb.toString();
   }

   private static Program getProgramById(int programID) {
      for(int i = 0; i < ProgramsAll.length; ++i) {
         Program pi = ProgramsAll[i];
         if (pi.getId() == programID) {
            return pi;
         }
      }

      return ProgramNone;
   }

   private static String getFramebufferStatusText(int fbStatusCode) {
      switch(fbStatusCode) {
      case 33305:
         return "Undefined";
      case 36053:
         return "Complete";
      case 36054:
         return "Incomplete attachment";
      case 36055:
         return "Incomplete missing attachment";
      case 36059:
         return "Incomplete draw buffer";
      case 36060:
         return "Incomplete read buffer";
      case 36061:
         return "Unsupported";
      case 36182:
         return "Incomplete multisample";
      case 36264:
         return "Incomplete layer targets";
      default:
         return "Unknown";
      }
   }

   private static void printChat(String str) {
      mc.field_71456_v.func_146158_b().func_146227_a(new ChatComponentText(str));
   }

   private static void printChatAndLogError(String str) {
      SMCLog.severe(str);
      mc.field_71456_v.func_146158_b().func_146227_a(new ChatComponentText(str));
   }

   public static void printIntBuffer(String title, IntBuffer buf) {
      StringBuilder sb = new StringBuilder(128);
      sb.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
      int lim = buf.limit();

      for(int i = 0; i < lim; ++i) {
         sb.append(" ").append(buf.get(i));
      }

      sb.append("]");
      SMCLog.info(sb.toString());
   }

   public static void startup(Minecraft mc) {
      checkShadersModInstalled();
      Shaders.mc = mc;
      mc = Minecraft.func_71410_x();
      capabilities = GLContext.getCapabilities();
      glVersionString = GL11.glGetString(7938);
      glVendorString = GL11.glGetString(7936);
      glRendererString = GL11.glGetString(7937);
      SMCLog.info("OpenGL Version: " + glVersionString);
      SMCLog.info("Vendor:  " + glVendorString);
      SMCLog.info("Renderer: " + glRendererString);
      SMCLog.info("Capabilities: " + (capabilities.OpenGL20 ? " 2.0 " : " - ") + (capabilities.OpenGL21 ? " 2.1 " : " - ") + (capabilities.OpenGL30 ? " 3.0 " : " - ") + (capabilities.OpenGL32 ? " 3.2 " : " - ") + (capabilities.OpenGL40 ? " 4.0 " : " - "));
      SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
      SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
      SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
      hasGlGenMipmap = capabilities.OpenGL30;
      loadConfig();
   }

   public static void updateBlockLightLevel() {
      if (isOldLighting()) {
         blockLightLevel05 = 0.5F;
         blockLightLevel06 = 0.6F;
         blockLightLevel08 = 0.8F;
      } else {
         blockLightLevel05 = 1.0F;
         blockLightLevel06 = 1.0F;
         blockLightLevel08 = 1.0F;
      }

   }

   public static boolean isOldHandLight() {
      if (!configOldHandLight.isDefault()) {
         return configOldHandLight.isTrue();
      } else {
         return !shaderPackOldHandLight.isDefault() ? shaderPackOldHandLight.isTrue() : true;
      }
   }

   public static boolean isDynamicHandLight() {
      return !shaderPackDynamicHandLight.isDefault() ? shaderPackDynamicHandLight.isTrue() : true;
   }

   public static boolean isOldLighting() {
      if (!configOldLighting.isDefault()) {
         return configOldLighting.isTrue();
      } else {
         return !shaderPackOldLighting.isDefault() ? shaderPackOldLighting.isTrue() : true;
      }
   }

   public static boolean isRenderShadowTranslucent() {
      return !shaderPackShadowTranslucent.isFalse();
   }

   public static boolean isUnderwaterOverlay() {
      return !shaderPackUnderwaterOverlay.isFalse();
   }

   public static boolean isSun() {
      return !shaderPackSun.isFalse();
   }

   public static boolean isMoon() {
      return !shaderPackMoon.isFalse();
   }

   public static boolean isVignette() {
      return !shaderPackVignette.isFalse();
   }

   public static boolean isRenderBackFace(EnumWorldBlockLayer blockLayerIn) {
      switch(blockLayerIn) {
      case SOLID:
         return shaderPackBackFaceSolid.isTrue();
      case CUTOUT:
         return shaderPackBackFaceCutout.isTrue();
      case CUTOUT_MIPPED:
         return shaderPackBackFaceCutoutMipped.isTrue();
      case TRANSLUCENT:
         return shaderPackBackFaceTranslucent.isTrue();
      default:
         return false;
      }
   }

   public static boolean isRainDepth() {
      return shaderPackRainDepth.isTrue();
   }

   public static boolean isBeaconBeamDepth() {
      return shaderPackBeaconBeamDepth.isTrue();
   }

   public static boolean isSeparateAo() {
      return shaderPackSeparateAo.isTrue();
   }

   public static boolean isFrustumCulling() {
      return !shaderPackFrustumCulling.isFalse();
   }

   public static void init() {
      boolean firstInit;
      if (!isInitializedOnce) {
         isInitializedOnce = true;
         firstInit = true;
      } else {
         firstInit = false;
      }

      if (!isShaderPackInitialized) {
         checkGLError("Shaders.init pre");
         if (getShaderPackName() != null) {
         }

         if (!capabilities.OpenGL20) {
            printChatAndLogError("No OpenGL 2.0");
         }

         if (!capabilities.GL_EXT_framebuffer_object) {
            printChatAndLogError("No EXT_framebuffer_object");
         }

         dfbDrawBuffers.position(0).limit(8);
         dfbColorTextures.position(0).limit(16);
         dfbDepthTextures.position(0).limit(3);
         sfbDrawBuffers.position(0).limit(8);
         sfbDepthTextures.position(0).limit(2);
         sfbColorTextures.position(0).limit(8);
         usedColorBuffers = 4;
         usedDepthBuffers = 1;
         usedShadowColorBuffers = 0;
         usedShadowDepthBuffers = 0;
         usedColorAttachs = 1;
         usedDrawBuffers = 1;
         Arrays.fill(gbuffersFormat, 6408);
         Arrays.fill(gbuffersClear, true);
         Arrays.fill(gbuffersClearColor, (Object)null);
         Arrays.fill(shadowHardwareFilteringEnabled, false);
         Arrays.fill(shadowMipmapEnabled, false);
         Arrays.fill(shadowFilterNearest, false);
         Arrays.fill(shadowColorMipmapEnabled, false);
         Arrays.fill(shadowColorFilterNearest, false);
         centerDepthSmoothEnabled = false;
         noiseTextureEnabled = false;
         sunPathRotation = 0.0F;
         shadowIntervalSize = 2.0F;
         shadowMapWidth = 1024;
         shadowMapHeight = 1024;
         spShadowMapWidth = 1024;
         spShadowMapHeight = 1024;
         shadowMapFOV = 90.0F;
         shadowMapHalfPlane = 160.0F;
         shadowMapIsOrtho = true;
         shadowDistanceRenderMul = -1.0F;
         aoLevel = -1.0F;
         useEntityAttrib = false;
         useMidTexCoordAttrib = false;
         useTangentAttrib = false;
         waterShadowEnabled = false;
         hasGeometryShaders = false;
         updateBlockLightLevel();
         Smoother.resetValues();
         shaderUniforms.reset();
         if (customUniforms != null) {
            customUniforms.reset();
         }

         ShaderProfile activeProfile = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
         String worldPrefix = "";
         int maxDrawBuffers;
         if (currentWorld != null) {
            maxDrawBuffers = currentWorld.field_73011_w.func_177502_q();
            if (shaderPackDimensions.contains(maxDrawBuffers)) {
               worldPrefix = "world" + maxDrawBuffers + "/";
            }
         }

         for(maxDrawBuffers = 0; maxDrawBuffers < ProgramsAll.length; ++maxDrawBuffers) {
            Program p = ProgramsAll[maxDrawBuffers];
            p.resetId();
            p.resetConfiguration();
            if (p.getProgramStage() != ProgramStage.NONE) {
               String programName = p.getName();
               String programPath = worldPrefix + programName;
               boolean enabled = true;
               if (shaderPackProgramConditions.containsKey(programPath)) {
                  enabled = enabled && ((IExpressionBool)shaderPackProgramConditions.get(programPath)).eval();
               }

               if (activeProfile != null) {
                  enabled = enabled && !activeProfile.isProgramDisabled(programPath);
               }

               if (!enabled) {
                  SMCLog.info("Program disabled: " + programPath);
                  programName = "<disabled>";
                  programPath = worldPrefix + programName;
               }

               String programFullPath = "/shaders/" + programPath;
               String programFullPathVertex = programFullPath + ".vsh";
               String programFullPathGeometry = programFullPath + ".gsh";
               String programFullPathFragment = programFullPath + ".fsh";
               setupProgram(p, programFullPathVertex, programFullPathGeometry, programFullPathFragment);
               int pr = p.getId();
               if (pr > 0) {
                  SMCLog.info("Program loaded: " + programPath);
               }

               initDrawBuffers(p);
               updateToggleBuffers(p);
            }
         }

         hasDeferredPrograms = false;

         for(maxDrawBuffers = 0; maxDrawBuffers < ProgramsDeferred.length; ++maxDrawBuffers) {
            if (ProgramsDeferred[maxDrawBuffers].getId() != 0) {
               hasDeferredPrograms = true;
               break;
            }
         }

         usedColorAttachs = usedColorBuffers;
         shadowPassInterval = usedShadowDepthBuffers > 0 ? 1 : 0;
         shouldSkipDefaultShadow = usedShadowDepthBuffers > 0;
         SMCLog.info("usedColorBuffers: " + usedColorBuffers);
         SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
         SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
         SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
         SMCLog.info("usedColorAttachs: " + usedColorAttachs);
         SMCLog.info("usedDrawBuffers: " + usedDrawBuffers);
         dfbDrawBuffers.position(0).limit(usedDrawBuffers);
         dfbColorTextures.position(0).limit(usedColorBuffers * 2);
         dfbColorTexturesFlip.reset();

         for(maxDrawBuffers = 0; maxDrawBuffers < usedDrawBuffers; ++maxDrawBuffers) {
            dfbDrawBuffers.put(maxDrawBuffers, '\u8ce0' + maxDrawBuffers);
         }

         maxDrawBuffers = GL11.glGetInteger(34852);
         if (usedDrawBuffers > maxDrawBuffers) {
            printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + maxDrawBuffers);
         }

         sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);

         int i;
         for(i = 0; i < usedShadowColorBuffers; ++i) {
            sfbDrawBuffers.put(i, '\u8ce0' + i);
         }

         for(i = 0; i < ProgramsAll.length; ++i) {
            Program pi = ProgramsAll[i];

            Program pn;
            for(pn = pi; pn.getId() == 0 && pn.getProgramBackup() != pn; pn = pn.getProgramBackup()) {
            }

            if (pn != pi && pi != ProgramShadow) {
               pi.copyFrom(pn);
            }
         }

         resize();
         resizeShadow();
         if (noiseTextureEnabled) {
            setupNoiseTexture();
         }

         if (defaultTexture == null) {
            defaultTexture = ShadersTex.createDefaultTexture();
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179114_b(-90.0F, 0.0F, 1.0F, 0.0F);
         preCelestialRotate();
         postCelestialRotate();
         GlStateManager.func_179121_F();
         isShaderPackInitialized = true;
         loadEntityDataMap();
         resetDisplayLists();
         if (!firstInit) {
         }

         checkGLError("Shaders.init");
      }

   }

   private static void initDrawBuffers(Program p) {
      int maxDrawBuffers = GL11.glGetInteger(34852);
      Arrays.fill(p.getToggleColorTextures(), false);
      if (p == ProgramFinal) {
         p.setDrawBuffers((IntBuffer)null);
      } else if (p.getId() == 0) {
         if (p == ProgramShadow) {
            p.setDrawBuffers(drawBuffersNone);
         } else {
            p.setDrawBuffers(drawBuffersColorAtt0);
         }

      } else {
         String str = p.getDrawBufSettings();
         if (str == null) {
            if (p != ProgramShadow && p != ProgramShadowSolid && p != ProgramShadowCutout) {
               p.setDrawBuffers(dfbDrawBuffers);
               usedDrawBuffers = usedColorBuffers;
               Arrays.fill(p.getToggleColorTextures(), 0, usedColorBuffers, true);
            } else {
               p.setDrawBuffers(sfbDrawBuffers);
            }

         } else {
            IntBuffer intbuf = p.getDrawBuffersBuffer();
            int numDB = str.length();
            usedDrawBuffers = Math.max(usedDrawBuffers, numDB);
            numDB = Math.min(numDB, maxDrawBuffers);
            p.setDrawBuffers(intbuf);
            intbuf.limit(numDB);

            for(int i = 0; i < numDB; ++i) {
               int drawBuffer = getDrawBuffer(p, str, i);
               intbuf.put(i, drawBuffer);
            }

         }
      }
   }

   private static int getDrawBuffer(Program p, String str, int i) {
      int drawBuffer = 0;
      if (i >= str.length()) {
         return drawBuffer;
      } else {
         int ca = str.charAt(i) - 48;
         if (p == ProgramShadow) {
            if (ca >= 0 && ca <= 1) {
               drawBuffer = ca + '\u8ce0';
               usedShadowColorBuffers = Math.max(usedShadowColorBuffers, ca);
            }

            return drawBuffer;
         } else {
            if (ca >= 0 && ca <= 7) {
               p.getToggleColorTextures()[ca] = true;
               drawBuffer = ca + '\u8ce0';
               usedColorAttachs = Math.max(usedColorAttachs, ca);
               usedColorBuffers = Math.max(usedColorBuffers, ca);
            }

            return drawBuffer;
         }
      }
   }

   private static void updateToggleBuffers(Program p) {
      boolean[] toggleBuffers = p.getToggleColorTextures();
      Boolean[] flipBuffers = p.getBuffersFlip();

      for(int i = 0; i < flipBuffers.length; ++i) {
         Boolean flip = flipBuffers[i];
         if (flip != null) {
            toggleBuffers[i] = flip;
         }
      }

   }

   public static void resetDisplayLists() {
      SMCLog.info("Reset model renderers");
      ++countResetDisplayLists;
      SMCLog.info("Reset world renderers");
      mc.field_71438_f.func_72712_a();
   }

   private static void setupProgram(Program program, String vShaderPath, String gShaderPath, String fShaderPath) {
      checkGLError("pre setupProgram");
      int programid = ARBShaderObjects.glCreateProgramObjectARB();
      checkGLError("create");
      if (programid != 0) {
         progUseEntityAttrib = false;
         progUseMidTexCoordAttrib = false;
         progUseTangentAttrib = false;
         int vShader = createVertShader(program, vShaderPath);
         int gShader = createGeomShader(program, gShaderPath);
         int fShader = createFragShader(program, fShaderPath);
         checkGLError("create");
         boolean programid;
         if (vShader == 0 && gShader == 0 && fShader == 0) {
            ARBShaderObjects.glDeleteObjectARB(programid);
            programid = false;
            program.resetId();
         } else {
            if (vShader != 0) {
               ARBShaderObjects.glAttachObjectARB(programid, vShader);
               checkGLError("attach");
            }

            if (gShader != 0) {
               ARBShaderObjects.glAttachObjectARB(programid, gShader);
               checkGLError("attach");
               if (progArbGeometryShader4) {
                  ARBGeometryShader4.glProgramParameteriARB(programid, 36315, 4);
                  ARBGeometryShader4.glProgramParameteriARB(programid, 36316, 5);
                  ARBGeometryShader4.glProgramParameteriARB(programid, 36314, progMaxVerticesOut);
                  checkGLError("arbGeometryShader4");
               }

               hasGeometryShaders = true;
            }

            if (fShader != 0) {
               ARBShaderObjects.glAttachObjectARB(programid, fShader);
               checkGLError("attach");
            }

            if (progUseEntityAttrib) {
               ARBVertexShader.glBindAttribLocationARB(programid, entityAttrib, (CharSequence)"mc_Entity");
               checkGLError("mc_Entity");
            }

            if (progUseMidTexCoordAttrib) {
               ARBVertexShader.glBindAttribLocationARB(programid, midTexCoordAttrib, (CharSequence)"mc_midTexCoord");
               checkGLError("mc_midTexCoord");
            }

            if (progUseTangentAttrib) {
               ARBVertexShader.glBindAttribLocationARB(programid, tangentAttrib, (CharSequence)"at_tangent");
               checkGLError("at_tangent");
            }

            ARBShaderObjects.glLinkProgramARB(programid);
            if (GL20.glGetProgrami(programid, 35714) != 1) {
               SMCLog.severe("Error linking program: " + programid + " (" + program.getName() + ")");
            }

            printLogInfo(programid, program.getName());
            if (vShader != 0) {
               ARBShaderObjects.glDetachObjectARB(programid, vShader);
               ARBShaderObjects.glDeleteObjectARB(vShader);
            }

            if (gShader != 0) {
               ARBShaderObjects.glDetachObjectARB(programid, gShader);
               ARBShaderObjects.glDeleteObjectARB(gShader);
            }

            if (fShader != 0) {
               ARBShaderObjects.glDetachObjectARB(programid, fShader);
               ARBShaderObjects.glDeleteObjectARB(fShader);
            }

            program.setId(programid);
            program.setRef(programid);
            useProgram(program);
            ARBShaderObjects.glValidateProgramARB(programid);
            useProgram(ProgramNone);
            printLogInfo(programid, program.getName());
            int valid = GL20.glGetProgrami(programid, 35715);
            if (valid != 1) {
               String Q = "\"";
               printChatAndLogError("[Shaders] Error: Invalid program " + Q + program.getName() + Q);
               ARBShaderObjects.glDeleteObjectARB(programid);
               programid = false;
               program.resetId();
            }
         }
      }

   }

   private static int createVertShader(Program program, String filename) {
      int vertShader = ARBShaderObjects.glCreateShaderObjectARB(35633);
      if (vertShader == 0) {
         return 0;
      } else {
         StringBuilder vertexCode = new StringBuilder(131072);
         BufferedReader reader = null;

         try {
            reader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var10) {
            ARBShaderObjects.glDeleteObjectARB(vertShader);
            return 0;
         }

         ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
         List<String> listFiles = new ArrayList();
         if (reader != null) {
            try {
               reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
               MacroState macroState = new MacroState();

               while(true) {
                  String line = reader.readLine();
                  if (line == null) {
                     reader.close();
                     break;
                  }

                  line = applyOptions(line, activeOptions);
                  vertexCode.append(line).append('\n');
                  if (macroState.processLine(line)) {
                     ShaderLine sl = ShaderParser.parseLine(line);
                     if (sl != null) {
                        if (sl.isAttribute("mc_Entity")) {
                           useEntityAttrib = true;
                           progUseEntityAttrib = true;
                        } else if (sl.isAttribute("mc_midTexCoord")) {
                           useMidTexCoordAttrib = true;
                           progUseMidTexCoordAttrib = true;
                        } else if (sl.isAttribute("at_tangent")) {
                           useTangentAttrib = true;
                           progUseTangentAttrib = true;
                        }

                        if (sl.isConstInt("countInstances")) {
                           program.setCountInstances(sl.getValueInt());
                           SMCLog.info("countInstances: " + program.getCountInstances());
                        }
                     }
                  }
               }
            } catch (Exception var11) {
               SMCLog.severe("Couldn't read " + filename + "!");
               var11.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(vertShader);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, vertexCode.toString());
         }

         ARBShaderObjects.glShaderSourceARB(vertShader, (CharSequence)vertexCode);
         ARBShaderObjects.glCompileShaderARB(vertShader);
         if (GL20.glGetShaderi(vertShader, 35713) != 1) {
            SMCLog.severe("Error compiling vertex shader: " + filename);
         }

         printShaderLogInfo(vertShader, filename, listFiles);
         return vertShader;
      }
   }

   private static int createGeomShader(Program program, String filename) {
      int geomShader = ARBShaderObjects.glCreateShaderObjectARB(36313);
      if (geomShader == 0) {
         return 0;
      } else {
         StringBuilder geomCode = new StringBuilder(131072);
         BufferedReader reader = null;

         try {
            reader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var11) {
            ARBShaderObjects.glDeleteObjectARB(geomShader);
            return 0;
         }

         ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
         List<String> listFiles = new ArrayList();
         progArbGeometryShader4 = false;
         progMaxVerticesOut = 3;
         if (reader != null) {
            try {
               reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
               MacroState macroState = new MacroState();

               label63:
               while(true) {
                  ShaderLine sl;
                  do {
                     String line;
                     do {
                        line = reader.readLine();
                        if (line == null) {
                           reader.close();
                           break label63;
                        }

                        line = applyOptions(line, activeOptions);
                        geomCode.append(line).append('\n');
                     } while(!macroState.processLine(line));

                     sl = ShaderParser.parseLine(line);
                  } while(sl == null);

                  if (sl.isExtension("GL_ARB_geometry_shader4")) {
                     String val = Config.normalize(sl.getValue());
                     if (val.equals("enable") || val.equals("require") || val.equals("warn")) {
                        progArbGeometryShader4 = true;
                     }
                  }

                  if (sl.isConstInt("maxVerticesOut")) {
                     progMaxVerticesOut = sl.getValueInt();
                  }
               }
            } catch (Exception var12) {
               SMCLog.severe("Couldn't read " + filename + "!");
               var12.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(geomShader);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, geomCode.toString());
         }

         ARBShaderObjects.glShaderSourceARB(geomShader, (CharSequence)geomCode);
         ARBShaderObjects.glCompileShaderARB(geomShader);
         if (GL20.glGetShaderi(geomShader, 35713) != 1) {
            SMCLog.severe("Error compiling geometry shader: " + filename);
         }

         printShaderLogInfo(geomShader, filename, listFiles);
         return geomShader;
      }
   }

   private static int createFragShader(Program program, String filename) {
      int fragShader = ARBShaderObjects.glCreateShaderObjectARB(35632);
      if (fragShader == 0) {
         return 0;
      } else {
         StringBuilder fragCode = new StringBuilder(131072);
         BufferedReader reader = null;

         try {
            reader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var14) {
            ARBShaderObjects.glDeleteObjectARB(fragShader);
            return 0;
         }

         ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
         List<String> listFiles = new ArrayList();
         if (reader != null) {
            try {
               reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0, listFiles, 0);
               MacroState macroState = new MacroState();

               label267:
               while(true) {
                  while(true) {
                     while(true) {
                        ShaderLine sl;
                        do {
                           String line;
                           do {
                              line = reader.readLine();
                              if (line == null) {
                                 reader.close();
                                 break label267;
                              }

                              line = applyOptions(line, activeOptions);
                              fragCode.append(line).append('\n');
                           } while(!macroState.processLine(line));

                           sl = ShaderParser.parseLine(line);
                        } while(sl == null);

                        String val;
                        int bufferindex;
                        if (sl.isUniform()) {
                           val = sl.getName();
                           if ((bufferindex = ShaderParser.getShadowDepthIndex(val)) >= 0) {
                              usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, bufferindex + 1);
                           } else if ((bufferindex = ShaderParser.getShadowColorIndex(val)) >= 0) {
                              usedShadowColorBuffers = Math.max(usedShadowColorBuffers, bufferindex + 1);
                           } else if ((bufferindex = ShaderParser.getDepthIndex(val)) >= 0) {
                              usedDepthBuffers = Math.max(usedDepthBuffers, bufferindex + 1);
                           } else if (val.equals("gdepth") && gbuffersFormat[1] == 6408) {
                              gbuffersFormat[1] = 34836;
                           } else if ((bufferindex = ShaderParser.getColorIndex(val)) >= 0) {
                              usedColorBuffers = Math.max(usedColorBuffers, bufferindex + 1);
                           } else if (val.equals("centerDepthSmooth")) {
                              centerDepthSmoothEnabled = true;
                           }
                        } else if (!sl.isConstInt("shadowMapResolution") && !sl.isProperty("SHADOWRES")) {
                           if (!sl.isConstFloat("shadowMapFov") && !sl.isProperty("SHADOWFOV")) {
                              if (!sl.isConstFloat("shadowDistance") && !sl.isProperty("SHADOWHPL")) {
                                 if (sl.isConstFloat("shadowDistanceRenderMul")) {
                                    shadowDistanceRenderMul = sl.getValueFloat();
                                    SMCLog.info("Shadow distance render mul: " + shadowDistanceRenderMul);
                                 } else if (sl.isConstFloat("shadowIntervalSize")) {
                                    shadowIntervalSize = sl.getValueFloat();
                                    SMCLog.info("Shadow map interval size: " + shadowIntervalSize);
                                 } else if (sl.isConstBool("generateShadowMipmap", true)) {
                                    Arrays.fill(shadowMipmapEnabled, true);
                                    SMCLog.info("Generate shadow mipmap");
                                 } else if (sl.isConstBool("generateShadowColorMipmap", true)) {
                                    Arrays.fill(shadowColorMipmapEnabled, true);
                                    SMCLog.info("Generate shadow color mipmap");
                                 } else if (sl.isConstBool("shadowHardwareFiltering", true)) {
                                    Arrays.fill(shadowHardwareFilteringEnabled, true);
                                    SMCLog.info("Hardware shadow filtering enabled.");
                                 } else if (sl.isConstBool("shadowHardwareFiltering0", true)) {
                                    shadowHardwareFilteringEnabled[0] = true;
                                    SMCLog.info("shadowHardwareFiltering0");
                                 } else if (sl.isConstBool("shadowHardwareFiltering1", true)) {
                                    shadowHardwareFilteringEnabled[1] = true;
                                    SMCLog.info("shadowHardwareFiltering1");
                                 } else if (sl.isConstBool("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                                    shadowMipmapEnabled[0] = true;
                                    SMCLog.info("shadowtex0Mipmap");
                                 } else if (sl.isConstBool("shadowtex1Mipmap", true)) {
                                    shadowMipmapEnabled[1] = true;
                                    SMCLog.info("shadowtex1Mipmap");
                                 } else if (sl.isConstBool("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                                    shadowColorMipmapEnabled[0] = true;
                                    SMCLog.info("shadowcolor0Mipmap");
                                 } else if (sl.isConstBool("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                                    shadowColorMipmapEnabled[1] = true;
                                    SMCLog.info("shadowcolor1Mipmap");
                                 } else if (sl.isConstBool("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                                    shadowFilterNearest[0] = true;
                                    SMCLog.info("shadowtex0Nearest");
                                 } else if (sl.isConstBool("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                                    shadowFilterNearest[1] = true;
                                    SMCLog.info("shadowtex1Nearest");
                                 } else if (sl.isConstBool("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                                    shadowColorFilterNearest[0] = true;
                                    SMCLog.info("shadowcolor0Nearest");
                                 } else if (sl.isConstBool("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                                    shadowColorFilterNearest[1] = true;
                                    SMCLog.info("shadowcolor1Nearest");
                                 } else if (!sl.isConstFloat("wetnessHalflife") && !sl.isProperty("WETNESSHL")) {
                                    if (!sl.isConstFloat("drynessHalflife") && !sl.isProperty("DRYNESSHL")) {
                                       if (sl.isConstFloat("eyeBrightnessHalflife")) {
                                          eyeBrightnessHalflife = sl.getValueFloat();
                                          SMCLog.info("Eye brightness halflife: " + eyeBrightnessHalflife);
                                       } else if (sl.isConstFloat("centerDepthHalflife")) {
                                          centerDepthSmoothHalflife = sl.getValueFloat();
                                          SMCLog.info("Center depth halflife: " + centerDepthSmoothHalflife);
                                       } else if (sl.isConstFloat("sunPathRotation")) {
                                          sunPathRotation = sl.getValueFloat();
                                          SMCLog.info("Sun path rotation: " + sunPathRotation);
                                       } else if (sl.isConstFloat("ambientOcclusionLevel")) {
                                          aoLevel = Config.limit(sl.getValueFloat(), 0.0F, 1.0F);
                                          SMCLog.info("AO Level: " + aoLevel);
                                       } else if (sl.isConstInt("superSamplingLevel")) {
                                          int ssaa = sl.getValueInt();
                                          if (ssaa > 1) {
                                             SMCLog.info("Super sampling level: " + ssaa + "x");
                                             superSamplingLevel = ssaa;
                                          } else {
                                             superSamplingLevel = 1;
                                          }
                                       } else if (sl.isConstInt("noiseTextureResolution")) {
                                          noiseTextureResolution = sl.getValueInt();
                                          noiseTextureEnabled = true;
                                          SMCLog.info("Noise texture enabled");
                                          SMCLog.info("Noise texture resolution: " + noiseTextureResolution);
                                       } else {
                                          int compositeMipmapSetting;
                                          if (sl.isConstIntSuffix("Format")) {
                                             val = StrUtils.removeSuffix(sl.getName(), "Format");
                                             String value = sl.getValue();
                                             compositeMipmapSetting = getBufferIndexFromString(val);
                                             int format = getTextureFormatFromString(value);
                                             if (compositeMipmapSetting >= 0 && format != 0) {
                                                gbuffersFormat[compositeMipmapSetting] = format;
                                                SMCLog.info("%s format: %s", val, value);
                                             }
                                          } else if (sl.isConstBoolSuffix("Clear", false)) {
                                             if (ShaderParser.isComposite(filename) || ShaderParser.isDeferred(filename)) {
                                                val = StrUtils.removeSuffix(sl.getName(), "Clear");
                                                bufferindex = getBufferIndexFromString(val);
                                                if (bufferindex >= 0) {
                                                   gbuffersClear[bufferindex] = false;
                                                   SMCLog.info("%s clear disabled", val);
                                                }
                                             }
                                          } else if (sl.isConstVec4Suffix("ClearColor")) {
                                             if (ShaderParser.isComposite(filename) || ShaderParser.isDeferred(filename)) {
                                                val = StrUtils.removeSuffix(sl.getName(), "ClearColor");
                                                bufferindex = getBufferIndexFromString(val);
                                                if (bufferindex >= 0) {
                                                   Vector4f col = sl.getValueVec4();
                                                   if (col != null) {
                                                      gbuffersClearColor[bufferindex] = col;
                                                      SMCLog.info("%s clear color: %s %s %s %s", val, col.getX(), col.getY(), col.getZ(), col.getW());
                                                   } else {
                                                      SMCLog.warning("Invalid color value: " + sl.getValue());
                                                   }
                                                }
                                             }
                                          } else if (sl.isProperty("GAUX4FORMAT", "RGBA32F")) {
                                             gbuffersFormat[7] = 34836;
                                             SMCLog.info("gaux4 format : RGB32AF");
                                          } else if (sl.isProperty("GAUX4FORMAT", "RGB32F")) {
                                             gbuffersFormat[7] = 34837;
                                             SMCLog.info("gaux4 format : RGB32F");
                                          } else if (sl.isProperty("GAUX4FORMAT", "RGB16")) {
                                             gbuffersFormat[7] = 32852;
                                             SMCLog.info("gaux4 format : RGB16");
                                          } else if (sl.isConstBoolSuffix("MipmapEnabled", true)) {
                                             if (ShaderParser.isComposite(filename) || ShaderParser.isDeferred(filename) || ShaderParser.isFinal(filename)) {
                                                val = StrUtils.removeSuffix(sl.getName(), "MipmapEnabled");
                                                bufferindex = getBufferIndexFromString(val);
                                                if (bufferindex >= 0) {
                                                   compositeMipmapSetting = program.getCompositeMipmapSetting();
                                                   compositeMipmapSetting |= 1 << bufferindex;
                                                   program.setCompositeMipmapSetting(compositeMipmapSetting);
                                                   SMCLog.info("%s mipmap enabled", val);
                                                }
                                             }
                                          } else if (sl.isProperty("DRAWBUFFERS")) {
                                             val = sl.getValue();
                                             if (ShaderParser.isValidDrawBuffers(val)) {
                                                program.setDrawBufSettings(val);
                                             } else {
                                                SMCLog.warning("Invalid draw buffers: " + val);
                                             }
                                          }
                                       }
                                    } else {
                                       drynessHalfLife = sl.getValueFloat();
                                       SMCLog.info("Dryness halflife: " + drynessHalfLife);
                                    }
                                 } else {
                                    wetnessHalfLife = sl.getValueFloat();
                                    SMCLog.info("Wetness halflife: " + wetnessHalfLife);
                                 }
                              } else {
                                 shadowMapHalfPlane = sl.getValueFloat();
                                 shadowMapIsOrtho = true;
                                 SMCLog.info("Shadow map distance: " + shadowMapHalfPlane);
                              }
                           } else {
                              shadowMapFOV = sl.getValueFloat();
                              shadowMapIsOrtho = false;
                              SMCLog.info("Shadow map field of view: " + shadowMapFOV);
                           }
                        } else {
                           spShadowMapWidth = spShadowMapHeight = sl.getValueInt();
                           shadowMapWidth = shadowMapHeight = Math.round((float)spShadowMapWidth * configShadowResMul);
                           SMCLog.info("Shadow map resolution: " + spShadowMapWidth);
                        }
                     }
                  }
               }
            } catch (Exception var15) {
               SMCLog.severe("Couldn't read " + filename + "!");
               var15.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(fragShader);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, fragCode.toString());
         }

         ARBShaderObjects.glShaderSourceARB(fragShader, (CharSequence)fragCode);
         ARBShaderObjects.glCompileShaderARB(fragShader);
         if (GL20.glGetShaderi(fragShader, 35713) != 1) {
            SMCLog.severe("Error compiling fragment shader: " + filename);
         }

         printShaderLogInfo(fragShader, filename, listFiles);
         return fragShader;
      }
   }

   private static Reader getShaderReader(String filename) {
      return new InputStreamReader(shaderPack.getResourceAsStream(filename));
   }

   public static void saveShader(String filename, String code) {
      try {
         File file = new File(shaderPacksDir, "debug/" + filename);
         file.getParentFile().mkdirs();
         Config.writeFile(file, code);
      } catch (IOException var3) {
         Config.warn("Error saving: " + filename);
         var3.printStackTrace();
      }

   }

   private static void clearDirectory(File dir) {
      if (dir.exists()) {
         if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
               for(int i = 0; i < files.length; ++i) {
                  File file = files[i];
                  if (file.isDirectory()) {
                     clearDirectory(file);
                  }

                  file.delete();
               }

            }
         }
      }
   }

   private static boolean printLogInfo(int obj, String name) {
      IntBuffer iVal = BufferUtils.createIntBuffer(1);
      ARBShaderObjects.glGetObjectParameterARB(obj, 35716, (IntBuffer)iVal);
      int length = iVal.get();
      if (length > 1) {
         ByteBuffer infoLog = BufferUtils.createByteBuffer(length);
         iVal.flip();
         ARBShaderObjects.glGetInfoLogARB(obj, iVal, infoLog);
         byte[] infoBytes = new byte[length];
         infoLog.get(infoBytes);
         if (infoBytes[length - 1] == 0) {
            infoBytes[length - 1] = 10;
         }

         String out = new String(infoBytes, Charsets.US_ASCII);
         out = StrUtils.trim(out, " \n\r\t");
         SMCLog.info("Info log: " + name + "\n" + out);
         return false;
      } else {
         return true;
      }
   }

   private static boolean printShaderLogInfo(int shader, String name, List<String> listFiles) {
      IntBuffer iVal = BufferUtils.createIntBuffer(1);
      int length = GL20.glGetShaderi(shader, 35716);
      if (length <= 1) {
         return true;
      } else {
         for(int i = 0; i < listFiles.size(); ++i) {
            String path = (String)listFiles.get(i);
            SMCLog.info("File: " + (i + 1) + " = " + path);
         }

         String log = GL20.glGetShaderInfoLog(shader, length);
         log = StrUtils.trim(log, " \n\r\t");
         SMCLog.info("Shader info log: " + name + "\n" + log);
         return false;
      }
   }

   public static void setDrawBuffers(IntBuffer drawBuffers) {
      if (drawBuffers == null) {
         drawBuffers = drawBuffersNone;
      }

      if (activeDrawBuffers != drawBuffers) {
         activeDrawBuffers = drawBuffers;
         GL20.glDrawBuffers(drawBuffers);
         checkGLError("setDrawBuffers");
      }

   }

   public static void useProgram(Program program) {
      checkGLError("pre-useProgram");
      if (isShadowPass) {
         program = ProgramShadow;
      } else if (isEntitiesGlowing) {
         program = ProgramEntitiesGlowing;
      }

      if (activeProgram != program) {
         updateAlphaBlend(activeProgram, program);
         activeProgram = program;
         int programID = program.getId();
         activeProgramID = programID;
         ARBShaderObjects.glUseProgramObjectARB(programID);
         if (checkGLError("useProgram") != 0) {
            program.setId(0);
            programID = program.getId();
            activeProgramID = programID;
            ARBShaderObjects.glUseProgramObjectARB(programID);
         }

         shaderUniforms.setProgram(programID);
         if (customUniforms != null) {
            customUniforms.setProgram(programID);
         }

         if (programID != 0) {
            IntBuffer drawBuffers = program.getDrawBuffers();
            if (isRenderingDfb) {
               setDrawBuffers(drawBuffers);
            }

            activeCompositeMipmapSetting = program.getCompositeMipmapSetting();
            switch(program.getProgramStage()) {
            case GBUFFERS:
               setProgramUniform1i(uniform_texture, 0);
               setProgramUniform1i(uniform_lightmap, 1);
               setProgramUniform1i(uniform_normals, 2);
               setProgramUniform1i(uniform_specular, 3);
               setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
               setProgramUniform1i(uniform_watershadow, 4);
               setProgramUniform1i(uniform_shadowtex0, 4);
               setProgramUniform1i(uniform_shadowtex1, 5);
               setProgramUniform1i(uniform_depthtex0, 6);
               if (customTexturesGbuffers != null || hasDeferredPrograms) {
                  setProgramUniform1i(uniform_gaux1, 7);
                  setProgramUniform1i(uniform_gaux2, 8);
                  setProgramUniform1i(uniform_gaux3, 9);
                  setProgramUniform1i(uniform_gaux4, 10);
               }

               setProgramUniform1i(uniform_depthtex1, 11);
               setProgramUniform1i(uniform_shadowcolor, 13);
               setProgramUniform1i(uniform_shadowcolor0, 13);
               setProgramUniform1i(uniform_shadowcolor1, 14);
               setProgramUniform1i(uniform_noisetex, 15);
               break;
            case DEFERRED:
            case COMPOSITE:
               setProgramUniform1i(uniform_gcolor, 0);
               setProgramUniform1i(uniform_gdepth, 1);
               setProgramUniform1i(uniform_gnormal, 2);
               setProgramUniform1i(uniform_composite, 3);
               setProgramUniform1i(uniform_gaux1, 7);
               setProgramUniform1i(uniform_gaux2, 8);
               setProgramUniform1i(uniform_gaux3, 9);
               setProgramUniform1i(uniform_gaux4, 10);
               setProgramUniform1i(uniform_colortex0, 0);
               setProgramUniform1i(uniform_colortex1, 1);
               setProgramUniform1i(uniform_colortex2, 2);
               setProgramUniform1i(uniform_colortex3, 3);
               setProgramUniform1i(uniform_colortex4, 7);
               setProgramUniform1i(uniform_colortex5, 8);
               setProgramUniform1i(uniform_colortex6, 9);
               setProgramUniform1i(uniform_colortex7, 10);
               setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
               setProgramUniform1i(uniform_watershadow, 4);
               setProgramUniform1i(uniform_shadowtex0, 4);
               setProgramUniform1i(uniform_shadowtex1, 5);
               setProgramUniform1i(uniform_gdepthtex, 6);
               setProgramUniform1i(uniform_depthtex0, 6);
               setProgramUniform1i(uniform_depthtex1, 11);
               setProgramUniform1i(uniform_depthtex2, 12);
               setProgramUniform1i(uniform_shadowcolor, 13);
               setProgramUniform1i(uniform_shadowcolor0, 13);
               setProgramUniform1i(uniform_shadowcolor1, 14);
               setProgramUniform1i(uniform_noisetex, 15);
               break;
            case SHADOW:
               setProgramUniform1i(uniform_tex, 0);
               setProgramUniform1i(uniform_texture, 0);
               setProgramUniform1i(uniform_lightmap, 1);
               setProgramUniform1i(uniform_normals, 2);
               setProgramUniform1i(uniform_specular, 3);
               setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
               setProgramUniform1i(uniform_watershadow, 4);
               setProgramUniform1i(uniform_shadowtex0, 4);
               setProgramUniform1i(uniform_shadowtex1, 5);
               if (customTexturesGbuffers != null) {
                  setProgramUniform1i(uniform_gaux1, 7);
                  setProgramUniform1i(uniform_gaux2, 8);
                  setProgramUniform1i(uniform_gaux3, 9);
                  setProgramUniform1i(uniform_gaux4, 10);
               }

               setProgramUniform1i(uniform_shadowcolor, 13);
               setProgramUniform1i(uniform_shadowcolor0, 13);
               setProgramUniform1i(uniform_shadowcolor1, 14);
               setProgramUniform1i(uniform_noisetex, 15);
            }

            ItemStack stack = mc.field_71439_g != null ? mc.field_71439_g.func_70694_bm() : null;
            Item item = stack != null ? stack.func_77973_b() : null;
            int itemID = -1;
            Block block = null;
            if (item != null) {
               itemID = Item.field_150901_e.func_148757_b(item);
               block = (Block)Block.field_149771_c.func_148754_a(itemID);
               itemID = ItemAliases.getItemAliasId(itemID);
            }

            int blockLight = block != null ? block.func_149750_m() : 0;
            setProgramUniform1i(uniform_heldItemId, itemID);
            setProgramUniform1i(uniform_heldBlockLightValue, blockLight);
            setProgramUniform1i(uniform_fogMode, fogEnabled ? fogMode : 0);
            setProgramUniform1f(uniform_fogDensity, fogEnabled ? fogDensity : 0.0F);
            setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
            setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
            setProgramUniform1i(uniform_worldTime, (int)(worldTime % 24000L));
            setProgramUniform1i(uniform_worldDay, (int)(worldTime / 24000L));
            setProgramUniform1i(uniform_moonPhase, moonPhase);
            setProgramUniform1i(uniform_frameCounter, frameCounter);
            setProgramUniform1f(uniform_frameTime, frameTime);
            setProgramUniform1f(uniform_frameTimeCounter, frameTimeCounter);
            setProgramUniform1f(uniform_sunAngle, sunAngle);
            setProgramUniform1f(uniform_shadowAngle, shadowAngle);
            setProgramUniform1f(uniform_rainStrength, rainStrength);
            setProgramUniform1f(uniform_aspectRatio, (float)renderWidth / (float)renderHeight);
            setProgramUniform1f(uniform_viewWidth, (float)renderWidth);
            setProgramUniform1f(uniform_viewHeight, (float)renderHeight);
            setProgramUniform1f(uniform_near, 0.05F);
            setProgramUniform1f(uniform_far, (float)(mc.field_71474_y.field_151451_c * 16));
            setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
            setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
            setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
            setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
            setProgramUniform3f(uniform_previousCameraPosition, (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
            setProgramUniform3f(uniform_cameraPosition, (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
            setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
            setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
            setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
            setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
            if (usedShadowDepthBuffers > 0) {
               setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
               setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
               setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
               setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
            }

            setProgramUniform1f(uniform_wetness, wetness);
            setProgramUniform1f(uniform_eyeAltitude, eyePosY);
            setProgramUniform2i(uniform_eyeBrightness, eyeBrightness & '\uffff', eyeBrightness >> 16);
            setProgramUniform2i(uniform_eyeBrightnessSmooth, Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
            setProgramUniform2i(uniform_terrainTextureSize, terrainTextureSize[0], terrainTextureSize[1]);
            setProgramUniform1i(uniform_terrainIconSize, terrainIconSize);
            setProgramUniform1i(uniform_isEyeInWater, isEyeInWater);
            setProgramUniform1f(uniform_nightVision, nightVision);
            setProgramUniform1f(uniform_blindness, blindness);
            setProgramUniform1f(uniform_screenBrightness, mc.field_71474_y.field_151452_as);
            setProgramUniform1i(uniform_hideGUI, mc.field_71474_y.field_74320_O ? 1 : 0);
            setProgramUniform1f(uniform_centerDepthSmooth, centerDepthSmooth);
            setProgramUniform2i(uniform_atlasSize, atlasSizeX, atlasSizeY);
            if (customUniforms != null) {
               customUniforms.update();
            }

            checkGLError("end useProgram");
         }
      }
   }

   private static void updateAlphaBlend(Program programOld, Program programNew) {
      if (programOld.getAlphaState() != null) {
         GlStateManager.unlockAlpha();
      }

      if (programOld.getBlendState() != null) {
         GlStateManager.unlockBlend();
      }

      GlAlphaState alphaNew = programNew.getAlphaState();
      if (alphaNew != null) {
         GlStateManager.lockAlpha(alphaNew);
      }

      GlBlendState blendNew = programNew.getBlendState();
      if (blendNew != null) {
         GlStateManager.lockBlend(blendNew);
      }

   }

   private static void setProgramUniform1i(ShaderUniform1i su, int value) {
      su.setValue(value);
   }

   private static void setProgramUniform2i(ShaderUniform2i su, int i0, int i1) {
      su.setValue(i0, i1);
   }

   private static void setProgramUniform1f(ShaderUniform1f su, float value) {
      su.setValue(value);
   }

   private static void setProgramUniform3f(ShaderUniform3f su, float f0, float f1, float f2) {
      su.setValue(f0, f1, f2);
   }

   private static void setProgramUniformMatrix4ARB(ShaderUniformM4 su, boolean transpose, FloatBuffer matrix) {
      su.setValue(transpose, matrix);
   }

   public static int getBufferIndexFromString(String name) {
      if (!name.equals("colortex0") && !name.equals("gcolor")) {
         if (!name.equals("colortex1") && !name.equals("gdepth")) {
            if (!name.equals("colortex2") && !name.equals("gnormal")) {
               if (!name.equals("colortex3") && !name.equals("composite")) {
                  if (!name.equals("colortex4") && !name.equals("gaux1")) {
                     if (!name.equals("colortex5") && !name.equals("gaux2")) {
                        if (!name.equals("colortex6") && !name.equals("gaux3")) {
                           return !name.equals("colortex7") && !name.equals("gaux4") ? -1 : 7;
                        } else {
                           return 6;
                        }
                     } else {
                        return 5;
                     }
                  } else {
                     return 4;
                  }
               } else {
                  return 3;
               }
            } else {
               return 2;
            }
         } else {
            return 1;
         }
      } else {
         return 0;
      }
   }

   private static int getTextureFormatFromString(String par) {
      par = par.trim();

      for(int i = 0; i < formatNames.length; ++i) {
         String name = formatNames[i];
         if (par.equals(name)) {
            return formatIds[i];
         }
      }

      return 0;
   }

   private static void setupNoiseTexture() {
      if (noiseTexture == null && noiseTexturePath != null) {
         noiseTexture = loadCustomTexture(15, noiseTexturePath);
      }

      if (noiseTexture == null) {
         noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
      }

   }

   private static void loadEntityDataMap() {
      mapBlockToEntityData = new IdentityHashMap(300);
      if (mapBlockToEntityData.isEmpty()) {
         Iterator it = Block.field_149771_c.func_148742_b().iterator();

         while(it.hasNext()) {
            ResourceLocation key = (ResourceLocation)it.next();
            Block block = (Block)Block.field_149771_c.func_82594_a(key);
            int id = Block.field_149771_c.func_148757_b(block);
            mapBlockToEntityData.put(block, id);
         }
      }

      BufferedReader reader = null;

      try {
         reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
      } catch (Exception var8) {
      }

      if (reader != null) {
         String line;
         try {
            while((line = reader.readLine()) != null) {
               Matcher m = patternLoadEntityDataMap.matcher(line);
               if (m.matches()) {
                  String name = m.group(1);
                  String value = m.group(2);
                  int id = Integer.parseInt(value);
                  Block block = Block.func_149684_b(name);
                  if (block != null) {
                     mapBlockToEntityData.put(block, id);
                  } else {
                     SMCLog.warning("Unknown block name %s", name);
                  }
               } else {
                  SMCLog.warning("unmatched %s\n", line);
               }
            }
         } catch (Exception var9) {
            SMCLog.warning("Error parsing mc_Entity_x.txt");
         }
      }

      if (reader != null) {
         try {
            reader.close();
         } catch (Exception var7) {
         }
      }

   }

   private static IntBuffer fillIntBufferZero(IntBuffer buf) {
      int limit = buf.limit();

      for(int i = buf.position(); i < limit; ++i) {
         buf.put(i, 0);
      }

      return buf;
   }

   public static void uninit() {
      if (isShaderPackInitialized) {
         checkGLError("Shaders.uninit pre");

         for(int i = 0; i < ProgramsAll.length; ++i) {
            Program pi = ProgramsAll[i];
            if (pi.getRef() != 0) {
               ARBShaderObjects.glDeleteObjectARB(pi.getRef());
               checkGLError("del programRef");
            }

            pi.setRef(0);
            pi.setId(0);
            pi.setDrawBufSettings((String)null);
            pi.setDrawBuffers((IntBuffer)null);
            pi.setCompositeMipmapSetting(0);
         }

         hasDeferredPrograms = false;
         if (dfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
            dfb = 0;
            checkGLError("del dfb");
         }

         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            sfb = 0;
            checkGLError("del sfb");
         }

         if (dfbDepthTextures != null) {
            GlStateManager.deleteTextures(dfbDepthTextures);
            fillIntBufferZero(dfbDepthTextures);
            checkGLError("del dfbDepthTextures");
         }

         if (dfbColorTextures != null) {
            GlStateManager.deleteTextures(dfbColorTextures);
            fillIntBufferZero(dfbColorTextures);
            checkGLError("del dfbTextures");
         }

         if (sfbDepthTextures != null) {
            GlStateManager.deleteTextures(sfbDepthTextures);
            fillIntBufferZero(sfbDepthTextures);
            checkGLError("del shadow depth");
         }

         if (sfbColorTextures != null) {
            GlStateManager.deleteTextures(sfbColorTextures);
            fillIntBufferZero(sfbColorTextures);
            checkGLError("del shadow color");
         }

         if (dfbDrawBuffers != null) {
            fillIntBufferZero(dfbDrawBuffers);
         }

         if (noiseTexture != null) {
            noiseTexture.deleteTexture();
            noiseTexture = null;
         }

         SMCLog.info("Uninit");
         shadowPassInterval = 0;
         shouldSkipDefaultShadow = false;
         isShaderPackInitialized = false;
         checkGLError("Shaders.uninit");
      }

   }

   public static void scheduleResize() {
      renderDisplayHeight = 0;
   }

   public static void scheduleResizeShadow() {
      needResizeShadow = true;
   }

   private static void resize() {
      renderDisplayWidth = mc.field_71443_c;
      renderDisplayHeight = mc.field_71440_d;
      renderWidth = Math.round((float)renderDisplayWidth * configRenderResMul);
      renderHeight = Math.round((float)renderDisplayHeight * configRenderResMul);
      setupFrameBuffer();
   }

   private static void resizeShadow() {
      needResizeShadow = false;
      shadowMapWidth = Math.round((float)spShadowMapWidth * configShadowResMul);
      shadowMapHeight = Math.round((float)spShadowMapHeight * configShadowResMul);
      setupShadowFrameBuffer();
   }

   private static void setupFrameBuffer() {
      if (dfb != 0) {
         EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
         GlStateManager.deleteTextures(dfbDepthTextures);
         GlStateManager.deleteTextures(dfbColorTextures);
      }

      dfb = EXTFramebufferObject.glGenFramebuffersEXT();
      GL11.glGenTextures((IntBuffer)dfbDepthTextures.clear().limit(usedDepthBuffers));
      GL11.glGenTextures((IntBuffer)dfbColorTextures.clear().limit(16));
      dfbDepthTextures.position(0);
      dfbColorTextures.position(0);
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
      GL20.glDrawBuffers(0);
      GL11.glReadBuffer(0);

      int status;
      for(status = 0; status < usedDepthBuffers; ++status) {
         GlStateManager.func_179144_i(dfbDepthTextures.get(status));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 34891, 6409);
         GL11.glTexImage2D(3553, 0, 6402, renderWidth, renderHeight, 0, 6402, 5126, (FloatBuffer)((FloatBuffer)null));
      }

      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
      GL20.glDrawBuffers(dfbDrawBuffers);
      GL11.glReadBuffer(0);
      checkGLError("FT d");

      for(status = 0; status < usedColorBuffers; ++status) {
         GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(status));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[status], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[status]), 33639, (ByteBuffer)((ByteBuffer)null));
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + status, 3553, dfbColorTexturesFlip.getA(status), 0);
         checkGLError("FT c");
      }

      for(status = 0; status < usedColorBuffers; ++status) {
         GlStateManager.func_179144_i(dfbColorTexturesFlip.getB(status));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[status], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[status]), 33639, (ByteBuffer)((ByteBuffer)null));
         checkGLError("FT ca");
      }

      status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (status == 36058) {
         printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");

         for(int i = 0; i < usedColorBuffers; ++i) {
            GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(i));
            GL11.glTexImage2D(3553, 0, 6408, renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)((ByteBuffer)null));
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
            checkGLError("FT c");
         }

         status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (status == 36053) {
            SMCLog.info("complete");
         }
      }

      GlStateManager.func_179144_i(0);
      if (status != 36053) {
         printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + status + ")");
      } else {
         SMCLog.info("Framebuffer created.");
      }

   }

   private static int getPixelFormat(int internalFormat) {
      switch(internalFormat) {
      case 33333:
      case 33334:
      case 33339:
      case 33340:
      case 36208:
      case 36209:
      case 36226:
      case 36227:
         return 36251;
      default:
         return 32993;
      }
   }

   private static void setupShadowFrameBuffer() {
      if (usedShadowDepthBuffers != 0) {
         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            GlStateManager.deleteTextures(sfbDepthTextures);
            GlStateManager.deleteTextures(sfbColorTextures);
         }

         sfb = EXTFramebufferObject.glGenFramebuffersEXT();
         EXTFramebufferObject.glBindFramebufferEXT(36160, sfb);
         GL11.glDrawBuffer(0);
         GL11.glReadBuffer(0);
         GL11.glGenTextures((IntBuffer)sfbDepthTextures.clear().limit(usedShadowDepthBuffers));
         GL11.glGenTextures((IntBuffer)sfbColorTextures.clear().limit(usedShadowColorBuffers));
         sfbDepthTextures.position(0);
         sfbColorTextures.position(0);

         int status;
         int filter;
         for(status = 0; status < usedShadowDepthBuffers; ++status) {
            GlStateManager.func_179144_i(sfbDepthTextures.get(status));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            filter = shadowFilterNearest[status] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, filter);
            GL11.glTexParameteri(3553, 10240, filter);
            if (shadowHardwareFilteringEnabled[status]) {
               GL11.glTexParameteri(3553, 34892, 34894);
            }

            GL11.glTexImage2D(3553, 0, 6402, shadowMapWidth, shadowMapHeight, 0, 6402, 5126, (FloatBuffer)((FloatBuffer)null));
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         checkGLError("FT sd");

         for(status = 0; status < usedShadowColorBuffers; ++status) {
            GlStateManager.func_179144_i(sfbColorTextures.get(status));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            filter = shadowColorFilterNearest[status] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, filter);
            GL11.glTexParameteri(3553, 10240, filter);
            GL11.glTexImage2D(3553, 0, 6408, shadowMapWidth, shadowMapHeight, 0, 32993, 33639, (ByteBuffer)((ByteBuffer)null));
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + status, 3553, sfbColorTextures.get(status), 0);
            checkGLError("FT sc");
         }

         GlStateManager.func_179144_i(0);
         if (usedShadowColorBuffers > 0) {
            GL20.glDrawBuffers(sfbDrawBuffers);
         }

         status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (status != 36053) {
            printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + status + ")");
         } else {
            SMCLog.info("Shadow framebuffer created.");
         }

      }
   }

   public static void beginRender(Minecraft minecraft, float partialTicks, long finishTimeNano) {
      checkGLError("pre beginRender");
      checkWorldChanged(mc.field_71441_e);
      mc = minecraft;
      mc.field_71424_I.func_76320_a("init");
      entityRenderer = mc.field_71460_t;
      if (!isShaderPackInitialized) {
         try {
            init();
         } catch (IllegalStateException var8) {
            if (Config.normalize(var8.getMessage()).equals("Function is not supported")) {
               printChatAndLogError("[Shaders] Error: " + var8.getMessage());
               var8.printStackTrace();
               setShaderPack("OFF");
               return;
            }
         }
      }

      if (mc.field_71443_c != renderDisplayWidth || mc.field_71440_d != renderDisplayHeight) {
         resize();
      }

      if (needResizeShadow) {
         resizeShadow();
      }

      worldTime = mc.field_71441_e.func_72820_D();
      diffWorldTime = (worldTime - lastWorldTime) % 24000L;
      if (diffWorldTime < 0L) {
         diffWorldTime += 24000L;
      }

      lastWorldTime = worldTime;
      moonPhase = mc.field_71441_e.func_72853_d();
      ++frameCounter;
      if (frameCounter >= 720720) {
         frameCounter = 0;
      }

      systemTime = System.currentTimeMillis();
      if (lastSystemTime == 0L) {
         lastSystemTime = systemTime;
      }

      diffSystemTime = systemTime - lastSystemTime;
      lastSystemTime = systemTime;
      frameTime = (float)diffSystemTime / 1000.0F;
      frameTimeCounter += frameTime;
      frameTimeCounter %= 3600.0F;
      rainStrength = minecraft.field_71441_e.func_72867_j(partialTicks);
      float fadeScalar = (float)diffSystemTime * 0.01F;
      float fadeScalar = (float)Math.exp(Math.log(0.5D) * (double)fadeScalar / (double)(wetness < rainStrength ? drynessHalfLife : wetnessHalfLife));
      wetness = wetness * fadeScalar + rainStrength * (1.0F - fadeScalar);
      Entity renderViewEntity = mc.func_175606_aa();
      if (renderViewEntity != null) {
         isSleeping = renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).func_70608_bn();
         eyePosY = (float)renderViewEntity.field_70163_u * partialTicks + (float)renderViewEntity.field_70137_T * (1.0F - partialTicks);
         eyeBrightness = renderViewEntity.func_70070_b(partialTicks);
         fadeScalar = (float)diffSystemTime * 0.01F;
         float temp2 = (float)Math.exp(Math.log(0.5D) * (double)fadeScalar / (double)eyeBrightnessHalflife);
         eyeBrightnessFadeX = eyeBrightnessFadeX * temp2 + (float)(eyeBrightness & '\uffff') * (1.0F - temp2);
         eyeBrightnessFadeY = eyeBrightnessFadeY * temp2 + (float)(eyeBrightness >> 16) * (1.0F - temp2);
         Block cameraBlock = ActiveRenderInfo.func_180786_a(mc.field_71441_e, renderViewEntity, partialTicks);
         Material cameraPosMaterial = cameraBlock.func_149688_o();
         if (cameraPosMaterial == Material.field_151586_h) {
            isEyeInWater = 1;
         } else if (cameraPosMaterial == Material.field_151587_i) {
            isEyeInWater = 2;
         } else {
            isEyeInWater = 0;
         }

         if (mc.field_71439_g != null) {
            nightVision = 0.0F;
            if (mc.field_71439_g.func_70644_a(Potion.field_76439_r)) {
               nightVision = Config.getMinecraft().field_71460_t.func_180438_a(mc.field_71439_g, partialTicks);
            }

            blindness = 0.0F;
            if (mc.field_71439_g.func_70644_a(Potion.field_76440_q)) {
               int blindnessTicks = mc.field_71439_g.func_70660_b(Potion.field_76440_q).func_76459_b();
               blindness = Config.limit((float)blindnessTicks / 20.0F, 0.0F, 1.0F);
            }
         }

         Vec3 skyColorV = mc.field_71441_e.func_72833_a(renderViewEntity, partialTicks);
         skyColorV = CustomColors.getWorldSkyColor(skyColorV, currentWorld, renderViewEntity, partialTicks);
         skyColorR = (float)skyColorV.field_72450_a;
         skyColorG = (float)skyColorV.field_72448_b;
         skyColorB = (float)skyColorV.field_72449_c;
      }

      isRenderingWorld = true;
      isCompositeRendered = false;
      isShadowPass = false;
      isHandRenderedMain = false;
      isHandRenderedOff = false;
      skipRenderHandMain = false;
      skipRenderHandOff = false;
      bindGbuffersTextures();
      previousCameraPositionX = cameraPositionX;
      previousCameraPositionY = cameraPositionY;
      previousCameraPositionZ = cameraPositionZ;
      previousProjection.position(0);
      projection.position(0);
      previousProjection.put(projection);
      previousProjection.position(0);
      projection.position(0);
      previousModelView.position(0);
      modelView.position(0);
      previousModelView.put(modelView);
      previousModelView.position(0);
      modelView.position(0);
      checkGLError("beginRender");
      ShadersRender.renderShadowMap(entityRenderer, 0, partialTicks, finishTimeNano);
      mc.field_71424_I.func_76319_b();
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);

      for(int i = 0; i < usedColorBuffers; ++i) {
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
      }

      checkGLError("end beginRender");
   }

   private static void bindGbuffersTextures() {
      if (usedShadowDepthBuffers >= 1) {
         GlStateManager.func_179138_g(33988);
         GlStateManager.func_179144_i(sfbDepthTextures.get(0));
         if (usedShadowDepthBuffers >= 2) {
            GlStateManager.func_179138_g(33989);
            GlStateManager.func_179144_i(sfbDepthTextures.get(1));
         }
      }

      GlStateManager.func_179138_g(33984);

      int i;
      for(i = 0; i < usedColorBuffers; ++i) {
         GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(i));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
         GlStateManager.func_179144_i(dfbColorTexturesFlip.getB(i));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
      }

      GlStateManager.func_179144_i(0);

      for(i = 0; i < 4 && 4 + i < usedColorBuffers; ++i) {
         GlStateManager.func_179138_g('\u84c7' + i);
         GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(4 + i));
      }

      GlStateManager.func_179138_g(33990);
      GlStateManager.func_179144_i(dfbDepthTextures.get(0));
      if (usedDepthBuffers >= 2) {
         GlStateManager.func_179138_g(33995);
         GlStateManager.func_179144_i(dfbDepthTextures.get(1));
         if (usedDepthBuffers >= 3) {
            GlStateManager.func_179138_g(33996);
            GlStateManager.func_179144_i(dfbDepthTextures.get(2));
         }
      }

      for(i = 0; i < usedShadowColorBuffers; ++i) {
         GlStateManager.func_179138_g('\u84cd' + i);
         GlStateManager.func_179144_i(sfbColorTextures.get(i));
      }

      if (noiseTextureEnabled) {
         GlStateManager.func_179138_g('\u84c0' + noiseTexture.getTextureUnit());
         GlStateManager.func_179144_i(noiseTexture.getTextureId());
      }

      bindCustomTextures(customTexturesGbuffers);
      GlStateManager.func_179138_g(33984);
   }

   public static void checkWorldChanged(World world) {
      if (currentWorld != world) {
         World oldWorld = currentWorld;
         currentWorld = world;
         setCameraOffset(mc.func_175606_aa());
         int dimIdOld = getDimensionId(oldWorld);
         int dimIdNew = getDimensionId(world);
         if (dimIdNew != dimIdOld) {
            boolean dimShadersOld = shaderPackDimensions.contains(dimIdOld);
            boolean dimShadersNew = shaderPackDimensions.contains(dimIdNew);
            if (dimShadersOld || dimShadersNew) {
               uninit();
            }
         }

         Smoother.resetValues();
      }
   }

   private static int getDimensionId(World world) {
      return world == null ? Integer.MIN_VALUE : world.field_73011_w.func_177502_q();
   }

   public static void beginRenderPass(int pass, float partialTicks, long finishTimeNano) {
      if (!isShadowPass) {
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         activeDrawBuffers = null;
         ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
         useProgram(ProgramTextured);
         checkGLError("end beginRenderPass");
      }
   }

   public static void setViewport(int vx, int vy, int vw, int vh) {
      GlStateManager.func_179135_a(true, true, true, true);
      if (isShadowPass) {
         GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      } else {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         isRenderingDfb = true;
         GlStateManager.func_179089_o();
         GlStateManager.func_179126_j();
         setDrawBuffers(drawBuffersNone);
         useProgram(ProgramTextured);
         checkGLError("beginRenderPass");
      }

   }

   public static void setFogMode(int value) {
      fogMode = value;
      if (fogEnabled) {
         setProgramUniform1i(uniform_fogMode, value);
      }

   }

   public static void setFogColor(float r, float g, float b) {
      fogColorR = r;
      fogColorG = g;
      fogColorB = b;
      setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
   }

   public static void setClearColor(float red, float green, float blue, float alpha) {
      GlStateManager.func_179082_a(red, green, blue, alpha);
      clearColorR = red;
      clearColorG = green;
      clearColorB = blue;
   }

   public static void clearRenderBuffer() {
      if (isShadowPass) {
         checkGLError("shadow clear pre");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL20.glDrawBuffers(ProgramShadow.getDrawBuffers());
         checkFramebufferStatus("shadow clear");
         GL11.glClear(16640);
         checkGLError("shadow clear");
      } else {
         checkGLError("clear pre");
         Vector4f col;
         if (gbuffersClear[0]) {
            col = gbuffersClearColor[0];
            if (col != null) {
               GL11.glClearColor(col.getX(), col.getY(), col.getZ(), col.getW());
            }

            if (dfbColorTexturesFlip.isChanged(0)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getB(0), 0);
               GL20.glDrawBuffers(36064);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getA(0), 0);
            }

            GL20.glDrawBuffers(36064);
            GL11.glClear(16384);
         }

         if (gbuffersClear[1]) {
            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            col = gbuffersClearColor[1];
            if (col != null) {
               GL11.glClearColor(col.getX(), col.getY(), col.getZ(), col.getW());
            }

            if (dfbColorTexturesFlip.isChanged(1)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getB(1), 0);
               GL20.glDrawBuffers(36065);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getA(1), 0);
            }

            GL20.glDrawBuffers(36065);
            GL11.glClear(16384);
         }

         for(int i = 2; i < usedColorBuffers; ++i) {
            if (gbuffersClear[i]) {
               GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
               Vector4f col = gbuffersClearColor[i];
               if (col != null) {
                  GL11.glClearColor(col.getX(), col.getY(), col.getZ(), col.getW());
               }

               if (dfbColorTexturesFlip.isChanged(i)) {
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getB(i), 0);
                  GL20.glDrawBuffers('\u8ce0' + i);
                  GL11.glClear(16384);
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
               }

               GL20.glDrawBuffers('\u8ce0' + i);
               GL11.glClear(16384);
            }
         }

         setDrawBuffers(dfbDrawBuffers);
         checkFramebufferStatus("clear");
         checkGLError("clear");
      }
   }

   public static void setCamera(float partialTicks) {
      Entity viewEntity = mc.func_175606_aa();
      double x = viewEntity.field_70142_S + (viewEntity.field_70165_t - viewEntity.field_70142_S) * (double)partialTicks;
      double y = viewEntity.field_70137_T + (viewEntity.field_70163_u - viewEntity.field_70137_T) * (double)partialTicks;
      double z = viewEntity.field_70136_U + (viewEntity.field_70161_v - viewEntity.field_70136_U) * (double)partialTicks;
      updateCameraOffset(viewEntity);
      cameraPositionX = x - (double)cameraOffsetX;
      cameraPositionY = y;
      cameraPositionZ = z - (double)cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
      SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
      projection.position(0);
      projectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
      SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
      modelView.position(0);
      modelViewInverse.position(0);
      checkGLError("setCamera");
   }

   private static void updateCameraOffset(Entity viewEntity) {
      double adx = Math.abs(cameraPositionX - previousCameraPositionX);
      double adz = Math.abs(cameraPositionZ - previousCameraPositionZ);
      double apx = Math.abs(cameraPositionX);
      double apz = Math.abs(cameraPositionZ);
      if (adx > 1000.0D || adz > 1000.0D || apx > 1000000.0D || apz > 1000000.0D) {
         setCameraOffset(viewEntity);
      }

   }

   private static void setCameraOffset(Entity viewEntity) {
      if (viewEntity == null) {
         cameraOffsetX = 0;
         cameraOffsetZ = 0;
      } else {
         cameraOffsetX = (int)viewEntity.field_70165_t / 1000 * 1000;
         cameraOffsetZ = (int)viewEntity.field_70161_v / 1000 * 1000;
      }
   }

   public static void setCameraShadow(float partialTicks) {
      Entity viewEntity = mc.func_175606_aa();
      double x = viewEntity.field_70142_S + (viewEntity.field_70165_t - viewEntity.field_70142_S) * (double)partialTicks;
      double y = viewEntity.field_70137_T + (viewEntity.field_70163_u - viewEntity.field_70137_T) * (double)partialTicks;
      double z = viewEntity.field_70136_U + (viewEntity.field_70161_v - viewEntity.field_70136_U) * (double)partialTicks;
      updateCameraOffset(viewEntity);
      cameraPositionX = x - (double)cameraOffsetX;
      cameraPositionY = y;
      cameraPositionZ = z - (double)cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
      SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
      projection.position(0);
      projectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
      SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
      modelView.position(0);
      modelViewInverse.position(0);
      GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      if (shadowMapIsOrtho) {
         GL11.glOrtho((double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, 0.05000000074505806D, 256.0D);
      } else {
         GLU.gluPerspective(shadowMapFOV, (float)shadowMapWidth / (float)shadowMapHeight, 0.05F, 256.0F);
      }

      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -100.0F);
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      celestialAngle = mc.field_71441_e.func_72826_c(partialTicks);
      sunAngle = celestialAngle < 0.75F ? celestialAngle + 0.25F : celestialAngle - 0.75F;
      float angle = celestialAngle * -360.0F;
      float angleInterval = shadowAngleInterval > 0.0F ? angle % shadowAngleInterval - shadowAngleInterval * 0.5F : 0.0F;
      if ((double)sunAngle <= 0.5D) {
         GL11.glRotatef(angle - angleInterval, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle;
      } else {
         GL11.glRotatef(angle + 180.0F - angleInterval, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle - 0.5F;
      }

      float raSun;
      float x1;
      if (shadowMapIsOrtho) {
         raSun = shadowIntervalSize;
         x1 = raSun / 2.0F;
         GL11.glTranslatef((float)x % raSun - x1, (float)y % raSun - x1, (float)z % raSun - x1);
      }

      raSun = sunAngle * 6.2831855F;
      x1 = (float)Math.cos((double)raSun);
      float y1 = (float)Math.sin((double)raSun);
      float raTilt = sunPathRotation * 6.2831855F;
      float x2 = x1;
      float y2 = y1 * (float)Math.cos((double)raTilt);
      float z2 = y1 * (float)Math.sin((double)raTilt);
      if ((double)sunAngle > 0.5D) {
         x2 = -x1;
         y2 = -y2;
         z2 = -z2;
      }

      shadowLightPositionVector[0] = x2;
      shadowLightPositionVector[1] = y2;
      shadowLightPositionVector[2] = z2;
      shadowLightPositionVector[3] = 0.0F;
      GL11.glGetFloat(2983, (FloatBuffer)shadowProjection.position(0));
      SMath.invertMat4FBFA((FloatBuffer)shadowProjectionInverse.position(0), (FloatBuffer)shadowProjection.position(0), faShadowProjectionInverse, faShadowProjection);
      shadowProjection.position(0);
      shadowProjectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)shadowModelView.position(0));
      SMath.invertMat4FBFA((FloatBuffer)shadowModelViewInverse.position(0), (FloatBuffer)shadowModelView.position(0), faShadowModelViewInverse, faShadowModelView);
      shadowModelView.position(0);
      shadowModelViewInverse.position(0);
      setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
      setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
      setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
      setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
      setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
      setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
      setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
      setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
      mc.field_71474_y.field_74330_P = 1;
      checkGLError("setCamera");
   }

   public static void preCelestialRotate() {
      GL11.glRotatef(sunPathRotation * 1.0F, 0.0F, 0.0F, 1.0F);
      checkGLError("preCelestialRotate");
   }

   public static void postCelestialRotate() {
      FloatBuffer modelView = tempMatrixDirectBuffer;
      modelView.clear();
      GL11.glGetFloat(2982, modelView);
      modelView.get(tempMat, 0, 16);
      SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
      SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
      System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
      setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
      setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
      setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }

      checkGLError("postCelestialRotate");
   }

   public static void setUpPosition() {
      FloatBuffer modelView = tempMatrixDirectBuffer;
      modelView.clear();
      GL11.glGetFloat(2982, modelView);
      modelView.get(tempMat, 0, 16);
      SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
      setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }

   }

   public static void genCompositeMipmap() {
      if (hasGlGenMipmap) {
         for(int i = 0; i < usedColorBuffers; ++i) {
            if ((activeCompositeMipmapSetting & 1 << i) != 0) {
               GlStateManager.func_179138_g('\u84c0' + colorTextureImageUnit[i]);
               GL11.glTexParameteri(3553, 10241, 9987);
               GL30.glGenerateMipmap(3553);
            }
         }

         GlStateManager.func_179138_g(33984);
      }

   }

   public static void drawComposite() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      drawCompositeQuad();
      int countInstances = activeProgram.getCountInstances();
      if (countInstances > 1) {
         for(int i = 1; i < countInstances; ++i) {
            uniform_instanceId.setValue(i);
            drawCompositeQuad();
         }

         uniform_instanceId.setValue(0);
      }

   }

   private static void drawCompositeQuad() {
      if (!canRenderQuads()) {
         GL11.glBegin(5);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glEnd();
      } else {
         GL11.glBegin(7);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glEnd();
      }
   }

   public static void renderDeferred() {
      if (!isShadowPass) {
         boolean buffersChanged = checkBufferFlip(ProgramDeferredPre);
         if (hasDeferredPrograms) {
            checkGLError("pre-render Deferred");
            renderComposites(ProgramsDeferred, false);
            buffersChanged = true;
         }

         if (buffersChanged) {
            bindGbuffersTextures();

            for(int i = 0; i < usedColorBuffers; ++i) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
            }

            if (ProgramWater.getDrawBuffers() != null) {
               setDrawBuffers(ProgramWater.getDrawBuffers());
            } else {
               setDrawBuffers(dfbDrawBuffers);
            }

            GlStateManager.func_179138_g(33984);
            mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
         }

      }
   }

   public static void renderCompositeFinal() {
      if (!isShadowPass) {
         checkBufferFlip(ProgramCompositePre);
         checkGLError("pre-render CompositeFinal");
         renderComposites(ProgramsComposite, true);
      }
   }

   private static boolean checkBufferFlip(Program program) {
      boolean flipped = false;
      Boolean[] buffersFlip = program.getBuffersFlip();

      for(int i = 0; i < usedColorBuffers; ++i) {
         if (Config.isTrue(buffersFlip[i])) {
            dfbColorTexturesFlip.flip(i);
            flipped = true;
         }
      }

      return flipped;
   }

   private static void renderComposites(Program[] ps, boolean renderFinal) {
      if (!isShadowPass) {
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179098_w();
         GlStateManager.func_179118_c();
         GlStateManager.func_179084_k();
         GlStateManager.func_179126_j();
         GlStateManager.func_179143_c(519);
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179140_f();
         if (usedShadowDepthBuffers >= 1) {
            GlStateManager.func_179138_g(33988);
            GlStateManager.func_179144_i(sfbDepthTextures.get(0));
            if (usedShadowDepthBuffers >= 2) {
               GlStateManager.func_179138_g(33989);
               GlStateManager.func_179144_i(sfbDepthTextures.get(1));
            }
         }

         int cp;
         for(cp = 0; cp < usedColorBuffers; ++cp) {
            GlStateManager.func_179138_g('\u84c0' + colorTextureImageUnit[cp]);
            GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(cp));
         }

         GlStateManager.func_179138_g(33990);
         GlStateManager.func_179144_i(dfbDepthTextures.get(0));
         if (usedDepthBuffers >= 2) {
            GlStateManager.func_179138_g(33995);
            GlStateManager.func_179144_i(dfbDepthTextures.get(1));
            if (usedDepthBuffers >= 3) {
               GlStateManager.func_179138_g(33996);
               GlStateManager.func_179144_i(dfbDepthTextures.get(2));
            }
         }

         for(cp = 0; cp < usedShadowColorBuffers; ++cp) {
            GlStateManager.func_179138_g('\u84cd' + cp);
            GlStateManager.func_179144_i(sfbColorTextures.get(cp));
         }

         if (noiseTextureEnabled) {
            GlStateManager.func_179138_g('\u84c0' + noiseTexture.getTextureUnit());
            GlStateManager.func_179144_i(noiseTexture.getTextureId());
         }

         if (renderFinal) {
            bindCustomTextures(customTexturesComposite);
         } else {
            bindCustomTextures(customTexturesDeferred);
         }

         GlStateManager.func_179138_g(33984);

         for(cp = 0; cp < usedColorBuffers; ++cp) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + cp, 3553, dfbColorTexturesFlip.getB(cp), 0);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
         GL20.glDrawBuffers(dfbDrawBuffers);
         checkGLError("pre-composite");

         for(cp = 0; cp < ps.length; ++cp) {
            Program program = ps[cp];
            if (program.getId() != 0) {
               useProgram(program);
               checkGLError(program.getName());
               if (activeCompositeMipmapSetting != 0) {
                  genCompositeMipmap();
               }

               preDrawComposite();
               drawComposite();
               postDrawComposite();

               for(int i = 0; i < usedColorBuffers; ++i) {
                  if (program.getToggleColorTextures()[i]) {
                     dfbColorTexturesFlip.flip(i);
                     GlStateManager.func_179138_g('\u84c0' + colorTextureImageUnit[i]);
                     GlStateManager.func_179144_i(dfbColorTexturesFlip.getA(i));
                     EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '\u8ce0' + i, 3553, dfbColorTexturesFlip.getB(i), 0);
                  }
               }

               GlStateManager.func_179138_g(33984);
            }
         }

         checkGLError("composite");
         if (renderFinal) {
            renderFinal();
            isCompositeRendered = true;
         }

         GlStateManager.func_179145_e();
         GlStateManager.func_179098_w();
         GlStateManager.func_179141_d();
         GlStateManager.func_179147_l();
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179132_a(true);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         useProgram(ProgramNone);
      }
   }

   private static void preDrawComposite() {
      RenderScale rs = activeProgram.getRenderScale();
      if (rs != null) {
         int x = (int)((float)renderWidth * rs.getOffsetX());
         int y = (int)((float)renderHeight * rs.getOffsetY());
         int w = (int)((float)renderWidth * rs.getScale());
         int h = (int)((float)renderHeight * rs.getScale());
         GL11.glViewport(x, y, w, h);
      }

   }

   private static void postDrawComposite() {
      RenderScale rs = activeProgram.getRenderScale();
      if (rs != null) {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
      }

   }

   private static void renderFinal() {
      isRenderingDfb = false;
      mc.func_147110_a().func_147610_a(true);
      OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, mc.func_147110_a().field_147617_g, 0);
      GL11.glViewport(0, 0, mc.field_71443_c, mc.field_71440_d);
      if (EntityRenderer.field_78517_a) {
         boolean maskR = EntityRenderer.field_78515_b != 0;
         GlStateManager.func_179135_a(maskR, !maskR, !maskR, true);
      }

      GlStateManager.func_179132_a(true);
      GL11.glClearColor(clearColorR, clearColorG, clearColorB, 1.0F);
      GL11.glClear(16640);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179098_w();
      GlStateManager.func_179118_c();
      GlStateManager.func_179084_k();
      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(519);
      GlStateManager.func_179132_a(false);
      checkGLError("pre-final");
      useProgram(ProgramFinal);
      checkGLError("final");
      if (activeCompositeMipmapSetting != 0) {
         genCompositeMipmap();
      }

      drawComposite();
      checkGLError("renderCompositeFinal");
   }

   public static void endRender() {
      if (isShadowPass) {
         checkGLError("shadow endRender");
      } else {
         if (!isCompositeRendered) {
            renderCompositeFinal();
         }

         isRenderingWorld = false;
         GlStateManager.func_179135_a(true, true, true, true);
         useProgram(ProgramNone);
         RenderHelper.func_74518_a();
         checkGLError("endRender end");
      }
   }

   public static void beginSky() {
      isRenderingSky = true;
      fogEnabled = true;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(ProgramSkyTextured);
      pushEntity(-2, 0);
   }

   public static void setSkyColor(Vec3 v3color) {
      skyColorR = (float)v3color.field_72450_a;
      skyColorG = (float)v3color.field_72448_b;
      skyColorB = (float)v3color.field_72449_c;
      setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
   }

   public static void drawHorizon() {
      WorldRenderer tess = Tessellator.func_178181_a().func_178180_c();
      float farDistance = (float)(mc.field_71474_y.field_151451_c * 16);
      double xzq = (double)farDistance * 0.9238D;
      double xzp = (double)farDistance * 0.3826D;
      double xzn = -xzp;
      double xzm = -xzq;
      double top = 16.0D;
      double bot = -cameraPositionY;
      tess.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      tess.func_181662_b(xzn, bot, xzm).func_181675_d();
      tess.func_181662_b(xzn, top, xzm).func_181675_d();
      tess.func_181662_b(xzm, top, xzn).func_181675_d();
      tess.func_181662_b(xzm, bot, xzn).func_181675_d();
      tess.func_181662_b(xzm, bot, xzn).func_181675_d();
      tess.func_181662_b(xzm, top, xzn).func_181675_d();
      tess.func_181662_b(xzm, top, xzp).func_181675_d();
      tess.func_181662_b(xzm, bot, xzp).func_181675_d();
      tess.func_181662_b(xzm, bot, xzp).func_181675_d();
      tess.func_181662_b(xzm, top, xzp).func_181675_d();
      tess.func_181662_b(xzn, top, xzq).func_181675_d();
      tess.func_181662_b(xzn, bot, xzq).func_181675_d();
      tess.func_181662_b(xzn, bot, xzq).func_181675_d();
      tess.func_181662_b(xzn, top, xzq).func_181675_d();
      tess.func_181662_b(xzp, top, xzq).func_181675_d();
      tess.func_181662_b(xzp, bot, xzq).func_181675_d();
      tess.func_181662_b(xzp, bot, xzq).func_181675_d();
      tess.func_181662_b(xzp, top, xzq).func_181675_d();
      tess.func_181662_b(xzq, top, xzp).func_181675_d();
      tess.func_181662_b(xzq, bot, xzp).func_181675_d();
      tess.func_181662_b(xzq, bot, xzp).func_181675_d();
      tess.func_181662_b(xzq, top, xzp).func_181675_d();
      tess.func_181662_b(xzq, top, xzn).func_181675_d();
      tess.func_181662_b(xzq, bot, xzn).func_181675_d();
      tess.func_181662_b(xzq, bot, xzn).func_181675_d();
      tess.func_181662_b(xzq, top, xzn).func_181675_d();
      tess.func_181662_b(xzp, top, xzm).func_181675_d();
      tess.func_181662_b(xzp, bot, xzm).func_181675_d();
      tess.func_181662_b(xzp, bot, xzm).func_181675_d();
      tess.func_181662_b(xzp, top, xzm).func_181675_d();
      tess.func_181662_b(xzn, top, xzm).func_181675_d();
      tess.func_181662_b(xzn, bot, xzm).func_181675_d();
      Tessellator.func_178181_a().func_78381_a();
   }

   public static void preSkyList() {
      setUpPosition();
      GL11.glColor3f(fogColorR, fogColorG, fogColorB);
      drawHorizon();
      GL11.glColor3f(skyColorR, skyColorG, skyColorB);
   }

   public static void endSky() {
      isRenderingSky = false;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      popEntity();
   }

   public static void beginUpdateChunks() {
      checkGLError("beginUpdateChunks1");
      checkFramebufferStatus("beginUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("beginUpdateChunks2");
      checkFramebufferStatus("beginUpdateChunks2");
   }

   public static void endUpdateChunks() {
      checkGLError("endUpdateChunks1");
      checkFramebufferStatus("endUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("endUpdateChunks2");
      checkFramebufferStatus("endUpdateChunks2");
   }

   public static boolean shouldRenderClouds(GameSettings gs) {
      if (!shaderPackLoaded) {
         return true;
      } else {
         checkGLError("shouldRenderClouds");
         return isShadowPass ? configCloudShadow : gs.field_74345_l > 0;
      }
   }

   public static void beginClouds() {
      fogEnabled = true;
      pushEntity(-3, 0);
      useProgram(ProgramClouds);
   }

   public static void endClouds() {
      disableFog();
      popEntity();
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
   }

   public static void beginEntities() {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
      }

   }

   public static void nextEntity(Entity entity) {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
         setEntityId(entity);
      }

   }

   public static void setEntityId(Entity entity) {
      if (uniform_entityId.isDefined()) {
         int id = EntityUtils.getEntityIdByClass(entity);
         int idAlias = EntityAliases.getEntityAliasId(id);
         if (idAlias >= 0) {
            id = idAlias;
         }

         uniform_entityId.setValue(id);
      }

   }

   public static void beginSpiderEyes() {
      if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
         useProgram(ProgramSpiderEyes);
         GlStateManager.func_179141_d();
         GlStateManager.func_179092_a(516, 0.0F);
         GlStateManager.func_179112_b(770, 771);
      }

   }

   public static void endSpiderEyes() {
      if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
         useProgram(ProgramEntities);
         GlStateManager.func_179118_c();
      }

   }

   public static void endEntities() {
      if (isRenderingWorld) {
         setEntityId((Entity)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void beginEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = true;
      }

   }

   public static void endEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = false;
      }

   }

   public static void setEntityColor(float r, float g, float b, float a) {
      if (isRenderingWorld && !isShadowPass) {
         uniform_entityColor.setValue(r, g, b, a);
      }

   }

   public static void beginLivingDamage() {
      if (isRenderingWorld) {
         ShadersTex.bindTexture(defaultTexture);
         if (!isShadowPass) {
            setDrawBuffers(drawBuffersColorAtt0);
         }
      }

   }

   public static void endLivingDamage() {
      if (isRenderingWorld && !isShadowPass) {
         setDrawBuffers(ProgramEntities.getDrawBuffers());
      }

   }

   public static void beginBlockEntities() {
      if (isRenderingWorld) {
         checkGLError("beginBlockEntities");
         useProgram(ProgramBlock);
      }

   }

   public static void nextBlockEntity(TileEntity tileEntity) {
      if (isRenderingWorld) {
         checkGLError("nextBlockEntity");
         useProgram(ProgramBlock);
         setBlockEntityId(tileEntity);
      }

   }

   public static void setBlockEntityId(TileEntity tileEntity) {
      if (uniform_blockEntityId.isDefined()) {
         int blockId = getBlockEntityId(tileEntity);
         uniform_blockEntityId.setValue(blockId);
      }

   }

   private static int getBlockEntityId(TileEntity tileEntity) {
      if (tileEntity == null) {
         return -1;
      } else {
         Block block = tileEntity.func_145838_q();
         if (block == null) {
            return 0;
         } else {
            int blockId = Block.func_149682_b(block);
            int metadata = tileEntity.func_145832_p();
            int blockAliasId = BlockAliases.getBlockAliasId(blockId, metadata);
            if (blockAliasId >= 0) {
               blockId = blockAliasId;
            }

            return blockId;
         }
      }
   }

   public static void endBlockEntities() {
      if (isRenderingWorld) {
         checkGLError("endBlockEntities");
         setBlockEntityId((TileEntity)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
         ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
      }

   }

   public static void beginLitParticles() {
      useProgram(ProgramTexturedLit);
   }

   public static void beginParticles() {
      useProgram(ProgramTextured);
   }

   public static void endParticles() {
      useProgram(ProgramTexturedLit);
   }

   public static void readCenterDepth() {
      if (!isShadowPass && centerDepthSmoothEnabled) {
         tempDirectFloatBuffer.clear();
         GL11.glReadPixels(renderWidth / 2, renderHeight / 2, 1, 1, 6402, 5126, (FloatBuffer)tempDirectFloatBuffer);
         centerDepth = tempDirectFloatBuffer.get(0);
         float fadeScalar = (float)diffSystemTime * 0.01F;
         float fadeFactor = (float)Math.exp(Math.log(0.5D) * (double)fadeScalar / (double)centerDepthSmoothHalflife);
         centerDepthSmooth = centerDepthSmooth * fadeFactor + centerDepth * (1.0F - fadeFactor);
      }

   }

   public static void beginWeather() {
      if (!isShadowPass) {
         if (usedDepthBuffers >= 3) {
            GlStateManager.func_179138_g(33996);
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
            GlStateManager.func_179138_g(33984);
         }

         GlStateManager.func_179126_j();
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 771);
         GlStateManager.func_179141_d();
         useProgram(ProgramWeather);
      }

   }

   public static void endWeather() {
      GlStateManager.func_179084_k();
      useProgram(ProgramTexturedLit);
   }

   public static void preWater() {
      if (usedDepthBuffers >= 2) {
         GlStateManager.func_179138_g(33995);
         checkGLError("pre copy depth");
         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
         checkGLError("copy depth");
         GlStateManager.func_179138_g(33984);
      }

      ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
   }

   public static void beginWater() {
      if (isRenderingWorld) {
         if (!isShadowPass) {
            renderDeferred();
            useProgram(ProgramWater);
            GlStateManager.func_179147_l();
            GlStateManager.func_179132_a(true);
         } else {
            GlStateManager.func_179132_a(true);
         }
      }

   }

   public static void endWater() {
      if (isRenderingWorld) {
         if (isShadowPass) {
         }

         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void applyHandDepth() {
      if ((double)configHandDepthMul != 1.0D) {
         GL11.glScaled(1.0D, 1.0D, (double)configHandDepthMul);
      }

   }

   public static void beginHand(boolean translucent) {
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      if (translucent) {
         useProgram(ProgramHandWater);
      } else {
         useProgram(ProgramHand);
      }

      checkGLError("beginHand");
      checkFramebufferStatus("beginHand");
   }

   public static void endHand() {
      checkGLError("pre endHand");
      checkFramebufferStatus("pre endHand");
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GlStateManager.func_179112_b(770, 771);
      checkGLError("endHand");
   }

   public static void beginFPOverlay() {
      GlStateManager.func_179140_f();
      GlStateManager.func_179084_k();
   }

   public static void endFPOverlay() {
   }

   public static void glEnableWrapper(int cap) {
      GL11.glEnable(cap);
      if (cap == 3553) {
         enableTexture2D();
      } else if (cap == 2912) {
         enableFog();
      }

   }

   public static void glDisableWrapper(int cap) {
      GL11.glDisable(cap);
      if (cap == 3553) {
         disableTexture2D();
      } else if (cap == 2912) {
         disableFog();
      }

   }

   public static void sglEnableT2D(int cap) {
      GL11.glEnable(cap);
      enableTexture2D();
   }

   public static void sglDisableT2D(int cap) {
      GL11.glDisable(cap);
      disableTexture2D();
   }

   public static void sglEnableFog(int cap) {
      GL11.glEnable(cap);
      enableFog();
   }

   public static void sglDisableFog(int cap) {
      GL11.glDisable(cap);
      disableFog();
   }

   public static void enableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyTextured);
      } else if (activeProgram == ProgramBasic) {
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void disableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyBasic);
      } else if (activeProgram == ProgramTextured || activeProgram == ProgramTexturedLit) {
         useProgram(ProgramBasic);
      }

   }

   public static void beginLeash() {
      programStackLeash.push(activeProgram);
      useProgram(ProgramBasic);
   }

   public static void endLeash() {
      useProgram(programStackLeash.pop());
   }

   public static void enableFog() {
      fogEnabled = true;
      setProgramUniform1i(uniform_fogMode, fogMode);
      setProgramUniform1f(uniform_fogDensity, fogDensity);
   }

   public static void disableFog() {
      fogEnabled = false;
      setProgramUniform1i(uniform_fogMode, 0);
   }

   public static void setFogDensity(float value) {
      fogDensity = value;
      if (fogEnabled) {
         setProgramUniform1f(uniform_fogDensity, value);
      }

   }

   public static void sglFogi(int pname, int param) {
      GL11.glFogi(pname, param);
      if (pname == 2917) {
         fogMode = param;
         if (fogEnabled) {
            setProgramUniform1i(uniform_fogMode, fogMode);
         }
      }

   }

   public static void enableLightmap() {
      lightmapEnabled = true;
      if (activeProgram == ProgramTextured) {
         useProgram(ProgramTexturedLit);
      }

   }

   public static void disableLightmap() {
      lightmapEnabled = false;
      if (activeProgram == ProgramTexturedLit) {
         useProgram(ProgramTextured);
      }

   }

   public static int getEntityData() {
      return entityData[entityDataIndex * 2];
   }

   public static int getEntityData2() {
      return entityData[entityDataIndex * 2 + 1];
   }

   public static int setEntityData1(int data1) {
      entityData[entityDataIndex * 2] = entityData[entityDataIndex * 2] & '\uffff' | data1 << 16;
      return data1;
   }

   public static int setEntityData2(int data2) {
      entityData[entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & -65536 | data2 & '\uffff';
      return data2;
   }

   public static void pushEntity(int data0, int data1) {
      ++entityDataIndex;
      entityData[entityDataIndex * 2] = data0 & '\uffff' | data1 << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void pushEntity(int data0) {
      ++entityDataIndex;
      entityData[entityDataIndex * 2] = data0 & '\uffff';
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void pushEntity(Block block) {
      ++entityDataIndex;
      int blockRenderType = block.func_149645_b();
      entityData[entityDataIndex * 2] = Block.field_149771_c.func_148757_b(block) & '\uffff' | blockRenderType << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void popEntity() {
      entityData[entityDataIndex * 2] = 0;
      entityData[entityDataIndex * 2 + 1] = 0;
      --entityDataIndex;
   }

   public static void mcProfilerEndSection() {
      mc.field_71424_I.func_76319_b();
   }

   public static String getShaderPackName() {
      if (shaderPack == null) {
         return null;
      } else {
         return shaderPack instanceof ShaderPackNone ? null : shaderPack.getName();
      }
   }

   public static InputStream getShaderPackResourceStream(String path) {
      return shaderPack == null ? null : shaderPack.getResourceAsStream(path);
   }

   public static void nextAntialiasingLevel() {
      configAntialiasingLevel += 2;
      configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
      if (configAntialiasingLevel > 4) {
         configAntialiasingLevel = 0;
      }

      configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
   }

   public static void checkShadersModInstalled() {
      try {
         Class var0 = Class.forName("shadersmod.transform.SMCClassTransformer");
      } catch (Throwable var1) {
         return;
      }

      throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
   }

   public static void resourcesReloaded() {
      loadShaderPackResources();
      if (shaderPackLoaded) {
         BlockAliases.resourcesReloaded();
         ItemAliases.resourcesReloaded();
         EntityAliases.resourcesReloaded();
      }

   }

   private static void loadShaderPackResources() {
      shaderPackResources = new HashMap();
      if (shaderPackLoaded) {
         List<String> listFiles = new ArrayList();
         String PREFIX = "/shaders/lang/";
         String EN_US = "en_US";
         String SUFFIX = ".lang";
         listFiles.add(PREFIX + EN_US + SUFFIX);
         if (!Config.getGameSettings().field_151455_aw.equals(EN_US)) {
            listFiles.add(PREFIX + Config.getGameSettings().field_151455_aw + SUFFIX);
         }

         try {
            Iterator it = listFiles.iterator();

            while(true) {
               InputStream in;
               do {
                  if (!it.hasNext()) {
                     return;
                  }

                  String file = (String)it.next();
                  in = shaderPack.getResourceAsStream(file);
               } while(in == null);

               Properties props = new PropertiesOrdered();
               Lang.loadLocaleData(in, props);
               in.close();
               Set keys = props.keySet();
               Iterator itp = keys.iterator();

               while(itp.hasNext()) {
                  String key = (String)itp.next();
                  String value = props.getProperty(key);
                  shaderPackResources.put(key, value);
               }
            }
         } catch (IOException var12) {
            var12.printStackTrace();
         }
      }
   }

   public static String translate(String key, String def) {
      String str = (String)shaderPackResources.get(key);
      return str == null ? def : str;
   }

   public static boolean isProgramPath(String path) {
      if (path == null) {
         return false;
      } else if (path.length() <= 0) {
         return false;
      } else {
         int pos = path.lastIndexOf("/");
         if (pos >= 0) {
            path = path.substring(pos + 1);
         }

         Program p = getProgram(path);
         return p != null;
      }
   }

   public static Program getProgram(String name) {
      return programs.getProgram(name);
   }

   public static void setItemToRenderMain(ItemStack itemToRenderMain) {
      itemToRenderMainTranslucent = isTranslucentBlock(itemToRenderMain);
   }

   public static void setItemToRenderOff(ItemStack itemToRenderOff) {
      itemToRenderOffTranslucent = isTranslucentBlock(itemToRenderOff);
   }

   public static boolean isItemToRenderMainTranslucent() {
      return itemToRenderMainTranslucent;
   }

   public static boolean isItemToRenderOffTranslucent() {
      return itemToRenderOffTranslucent;
   }

   public static boolean isBothHandsRendered() {
      return isHandRenderedMain && isHandRenderedOff;
   }

   private static boolean isTranslucentBlock(ItemStack stack) {
      if (stack == null) {
         return false;
      } else {
         Item item = stack.func_77973_b();
         if (item == null) {
            return false;
         } else if (!(item instanceof ItemBlock)) {
            return false;
         } else {
            ItemBlock itemBlock = (ItemBlock)item;
            Block block = itemBlock.func_179223_d();
            if (block == null) {
               return false;
            } else {
               EnumWorldBlockLayer blockRenderLayer = block.func_180664_k();
               return blockRenderLayer == EnumWorldBlockLayer.TRANSLUCENT;
            }
         }
      }
   }

   public static boolean isSkipRenderHand() {
      return skipRenderHandMain;
   }

   public static boolean isRenderBothHands() {
      return !skipRenderHandMain && !skipRenderHandOff;
   }

   public static void setSkipRenderHands(boolean skipMain, boolean skipOff) {
      skipRenderHandMain = skipMain;
      skipRenderHandOff = skipOff;
   }

   public static void setHandsRendered(boolean handMain, boolean handOff) {
      isHandRenderedMain = handMain;
      isHandRenderedOff = handOff;
   }

   public static boolean isHandRenderedMain() {
      return isHandRenderedMain;
   }

   public static boolean isHandRenderedOff() {
      return isHandRenderedOff;
   }

   public static float getShadowRenderDistance() {
      return shadowDistanceRenderMul < 0.0F ? -1.0F : shadowMapHalfPlane * shadowDistanceRenderMul;
   }

   public static void setRenderingFirstPersonHand(boolean flag) {
      isRenderingFirstPersonHand = flag;
   }

   public static boolean isRenderingFirstPersonHand() {
      return isRenderingFirstPersonHand;
   }

   public static void beginBeacon() {
      if (isRenderingWorld) {
         useProgram(ProgramBeaconBeam);
      }

   }

   public static void endBeacon() {
      if (isRenderingWorld) {
         useProgram(ProgramBlock);
      }

   }

   public static World getCurrentWorld() {
      return currentWorld;
   }

   public static BlockPos getCameraPosition() {
      return new BlockPos(cameraPositionX, cameraPositionY, cameraPositionZ);
   }

   public static boolean isCustomUniforms() {
      return customUniforms != null;
   }

   public static boolean canRenderQuads() {
      return hasGeometryShaders ? capabilities.GL_NV_geometry_shader4 : true;
   }

   static {
      uniform_entityColor = shaderUniforms.make4f("entityColor");
      uniform_entityId = shaderUniforms.make1i("entityId");
      uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
      uniform_texture = shaderUniforms.make1i("texture");
      uniform_lightmap = shaderUniforms.make1i("lightmap");
      uniform_normals = shaderUniforms.make1i("normals");
      uniform_specular = shaderUniforms.make1i("specular");
      uniform_shadow = shaderUniforms.make1i("shadow");
      uniform_watershadow = shaderUniforms.make1i("watershadow");
      uniform_shadowtex0 = shaderUniforms.make1i("shadowtex0");
      uniform_shadowtex1 = shaderUniforms.make1i("shadowtex1");
      uniform_depthtex0 = shaderUniforms.make1i("depthtex0");
      uniform_depthtex1 = shaderUniforms.make1i("depthtex1");
      uniform_shadowcolor = shaderUniforms.make1i("shadowcolor");
      uniform_shadowcolor0 = shaderUniforms.make1i("shadowcolor0");
      uniform_shadowcolor1 = shaderUniforms.make1i("shadowcolor1");
      uniform_noisetex = shaderUniforms.make1i("noisetex");
      uniform_gcolor = shaderUniforms.make1i("gcolor");
      uniform_gdepth = shaderUniforms.make1i("gdepth");
      uniform_gnormal = shaderUniforms.make1i("gnormal");
      uniform_composite = shaderUniforms.make1i("composite");
      uniform_gaux1 = shaderUniforms.make1i("gaux1");
      uniform_gaux2 = shaderUniforms.make1i("gaux2");
      uniform_gaux3 = shaderUniforms.make1i("gaux3");
      uniform_gaux4 = shaderUniforms.make1i("gaux4");
      uniform_colortex0 = shaderUniforms.make1i("colortex0");
      uniform_colortex1 = shaderUniforms.make1i("colortex1");
      uniform_colortex2 = shaderUniforms.make1i("colortex2");
      uniform_colortex3 = shaderUniforms.make1i("colortex3");
      uniform_colortex4 = shaderUniforms.make1i("colortex4");
      uniform_colortex5 = shaderUniforms.make1i("colortex5");
      uniform_colortex6 = shaderUniforms.make1i("colortex6");
      uniform_colortex7 = shaderUniforms.make1i("colortex7");
      uniform_gdepthtex = shaderUniforms.make1i("gdepthtex");
      uniform_depthtex2 = shaderUniforms.make1i("depthtex2");
      uniform_tex = shaderUniforms.make1i("tex");
      uniform_heldItemId = shaderUniforms.make1i("heldItemId");
      uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
      uniform_heldItemId2 = shaderUniforms.make1i("heldItemId2");
      uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
      uniform_fogMode = shaderUniforms.make1i("fogMode");
      uniform_fogDensity = shaderUniforms.make1f("fogDensity");
      uniform_fogColor = shaderUniforms.make3f("fogColor");
      uniform_skyColor = shaderUniforms.make3f("skyColor");
      uniform_worldTime = shaderUniforms.make1i("worldTime");
      uniform_worldDay = shaderUniforms.make1i("worldDay");
      uniform_moonPhase = shaderUniforms.make1i("moonPhase");
      uniform_frameCounter = shaderUniforms.make1i("frameCounter");
      uniform_frameTime = shaderUniforms.make1f("frameTime");
      uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
      uniform_sunAngle = shaderUniforms.make1f("sunAngle");
      uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
      uniform_rainStrength = shaderUniforms.make1f("rainStrength");
      uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
      uniform_viewWidth = shaderUniforms.make1f("viewWidth");
      uniform_viewHeight = shaderUniforms.make1f("viewHeight");
      uniform_near = shaderUniforms.make1f("near");
      uniform_far = shaderUniforms.make1f("far");
      uniform_sunPosition = shaderUniforms.make3f("sunPosition");
      uniform_moonPosition = shaderUniforms.make3f("moonPosition");
      uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
      uniform_upPosition = shaderUniforms.make3f("upPosition");
      uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
      uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
      uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
      uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
      uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
      uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
      uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
      uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
      uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
      uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
      uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
      uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
      uniform_wetness = shaderUniforms.make1f("wetness");
      uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
      uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
      uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
      uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
      uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
      uniform_isEyeInWater = shaderUniforms.make1i("isEyeInWater");
      uniform_nightVision = shaderUniforms.make1f("nightVision");
      uniform_blindness = shaderUniforms.make1f("blindness");
      uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
      uniform_hideGUI = shaderUniforms.make1i("hideGUI");
      uniform_centerDepthSmooth = shaderUniforms.make1f("centerDepthSmooth");
      uniform_atlasSize = shaderUniforms.make2i("atlasSize");
      uniform_blendFunc = shaderUniforms.make4i("blendFunc");
      uniform_instanceId = shaderUniforms.make1i("instanceId");
      shadowPassInterval = 0;
      needResizeShadow = false;
      shadowMapWidth = 1024;
      shadowMapHeight = 1024;
      spShadowMapWidth = 1024;
      spShadowMapHeight = 1024;
      shadowMapFOV = 90.0F;
      shadowMapHalfPlane = 160.0F;
      shadowMapIsOrtho = true;
      shadowDistanceRenderMul = -1.0F;
      shadowPassCounter = 0;
      shouldSkipDefaultShadow = false;
      waterShadowEnabled = false;
      usedColorBuffers = 0;
      usedDepthBuffers = 0;
      usedShadowColorBuffers = 0;
      usedShadowDepthBuffers = 0;
      usedColorAttachs = 0;
      usedDrawBuffers = 0;
      dfb = 0;
      sfb = 0;
      gbuffersFormat = new int[8];
      gbuffersClear = new boolean[8];
      gbuffersClearColor = new Vector4f[8];
      programs = new Programs();
      ProgramNone = programs.getProgramNone();
      ProgramShadow = programs.makeShadow("shadow", ProgramNone);
      ProgramShadowSolid = programs.makeShadow("shadow_solid", ProgramShadow);
      ProgramShadowCutout = programs.makeShadow("shadow_cutout", ProgramShadow);
      ProgramBasic = programs.makeGbuffers("gbuffers_basic", ProgramNone);
      ProgramTextured = programs.makeGbuffers("gbuffers_textured", ProgramBasic);
      ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
      ProgramSkyBasic = programs.makeGbuffers("gbuffers_skybasic", ProgramBasic);
      ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
      ProgramClouds = programs.makeGbuffers("gbuffers_clouds", ProgramTextured);
      ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", ProgramTexturedLit);
      ProgramTerrainSolid = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
      ProgramTerrainCutoutMip = programs.makeGbuffers("gbuffers_terrain_cutout_mip", ProgramTerrain);
      ProgramTerrainCutout = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
      ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
      ProgramBlock = programs.makeGbuffers("gbuffers_block", ProgramTerrain);
      ProgramBeaconBeam = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
      ProgramItem = programs.makeGbuffers("gbuffers_item", ProgramTexturedLit);
      ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
      ProgramEntitiesGlowing = programs.makeGbuffers("gbuffers_entities_glowing", ProgramEntities);
      ProgramArmorGlint = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
      ProgramSpiderEyes = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
      ProgramHand = programs.makeGbuffers("gbuffers_hand", ProgramTexturedLit);
      ProgramWeather = programs.makeGbuffers("gbuffers_weather", ProgramTexturedLit);
      ProgramDeferredPre = programs.makeVirtual("deferred_pre");
      ProgramsDeferred = programs.makeDeferreds("deferred", 16);
      ProgramDeferred = ProgramsDeferred[0];
      ProgramWater = programs.makeGbuffers("gbuffers_water", ProgramTerrain);
      ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
      ProgramCompositePre = programs.makeVirtual("composite_pre");
      ProgramsComposite = programs.makeComposites("composite", 16);
      ProgramComposite = ProgramsComposite[0];
      ProgramFinal = programs.makeComposite("final");
      ProgramCount = programs.getCount();
      ProgramsAll = programs.getPrograms();
      activeProgram = ProgramNone;
      activeProgramID = 0;
      programStackLeash = new ProgramStack();
      hasDeferredPrograms = false;
      activeDrawBuffers = null;
      activeCompositeMipmapSetting = 0;
      loadedShaders = null;
      shadersConfig = null;
      defaultTexture = null;
      shadowHardwareFilteringEnabled = new boolean[2];
      shadowMipmapEnabled = new boolean[2];
      shadowFilterNearest = new boolean[2];
      shadowColorMipmapEnabled = new boolean[8];
      shadowColorFilterNearest = new boolean[8];
      configTweakBlockDamage = false;
      configCloudShadow = false;
      configHandDepthMul = 0.125F;
      configRenderResMul = 1.0F;
      configShadowResMul = 1.0F;
      configTexMinFilB = 0;
      configTexMinFilN = 0;
      configTexMinFilS = 0;
      configTexMagFilB = 0;
      configTexMagFilN = 0;
      configTexMagFilS = 0;
      configShadowClipFrustrum = true;
      configNormalMap = true;
      configSpecularMap = true;
      configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
      configOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
      configAntialiasingLevel = 0;
      texMinFilDesc = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
      texMagFilDesc = new String[]{"Nearest", "Linear"};
      texMinFilValue = new int[]{9728, 9984, 9986};
      texMagFilValue = new int[]{9728, 9729};
      shaderPack = null;
      shaderPackLoaded = false;
      shaderPacksDir = new File(Minecraft.func_71410_x().field_71412_D, "shaderpacks");
      configFile = new File(Minecraft.func_71410_x().field_71412_D, "optionsshaders.txt");
      shaderPackOptions = null;
      shaderPackOptionSliders = null;
      shaderPackProfiles = null;
      shaderPackGuiScreens = null;
      shaderPackProgramConditions = new HashMap();
      shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
      shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
      shaderPackOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
      shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
      shaderPackShadowTranslucent = new PropertyDefaultTrueFalse("shadowTranslucent", "Shadow Translucent", 0);
      shaderPackUnderwaterOverlay = new PropertyDefaultTrueFalse("underwaterOverlay", "Underwater Overlay", 0);
      shaderPackSun = new PropertyDefaultTrueFalse("sun", "Sun", 0);
      shaderPackMoon = new PropertyDefaultTrueFalse("moon", "Moon", 0);
      shaderPackVignette = new PropertyDefaultTrueFalse("vignette", "Vignette", 0);
      shaderPackBackFaceSolid = new PropertyDefaultTrueFalse("backFace.solid", "Back-face Solid", 0);
      shaderPackBackFaceCutout = new PropertyDefaultTrueFalse("backFace.cutout", "Back-face Cutout", 0);
      shaderPackBackFaceCutoutMipped = new PropertyDefaultTrueFalse("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
      shaderPackBackFaceTranslucent = new PropertyDefaultTrueFalse("backFace.translucent", "Back-face Translucent", 0);
      shaderPackRainDepth = new PropertyDefaultTrueFalse("rain.depth", "Rain Depth", 0);
      shaderPackBeaconBeamDepth = new PropertyDefaultTrueFalse("beacon.beam.depth", "Rain Depth", 0);
      shaderPackSeparateAo = new PropertyDefaultTrueFalse("separateAo", "Separate AO", 0);
      shaderPackFrustumCulling = new PropertyDefaultTrueFalse("frustum.culling", "Frustum Culling", 0);
      shaderPackResources = new HashMap();
      currentWorld = null;
      shaderPackDimensions = new ArrayList();
      customTexturesGbuffers = null;
      customTexturesComposite = null;
      customTexturesDeferred = null;
      noiseTexturePath = null;
      customUniforms = null;
      STAGE_NAMES = new String[]{"gbuffers", "composite", "deferred"};
      saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
      blockLightLevel05 = 0.5F;
      blockLightLevel06 = 0.6F;
      blockLightLevel08 = 0.8F;
      aoLevel = -1.0F;
      sunPathRotation = 0.0F;
      shadowAngleInterval = 0.0F;
      fogMode = 0;
      fogDensity = 0.0F;
      shadowIntervalSize = 2.0F;
      terrainIconSize = 16;
      terrainTextureSize = new int[2];
      noiseTextureEnabled = false;
      noiseTextureResolution = 256;
      colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
      bigBufferSize = (285 + 8 * ProgramCount) * 4;
      bigBuffer = (ByteBuffer)BufferUtils.createByteBuffer(bigBufferSize).limit(0);
      faProjection = new float[16];
      faProjectionInverse = new float[16];
      faModelView = new float[16];
      faModelViewInverse = new float[16];
      faShadowProjection = new float[16];
      faShadowProjectionInverse = new float[16];
      faShadowModelView = new float[16];
      faShadowModelViewInverse = new float[16];
      projection = nextFloatBuffer(16);
      projectionInverse = nextFloatBuffer(16);
      modelView = nextFloatBuffer(16);
      modelViewInverse = nextFloatBuffer(16);
      shadowProjection = nextFloatBuffer(16);
      shadowProjectionInverse = nextFloatBuffer(16);
      shadowModelView = nextFloatBuffer(16);
      shadowModelViewInverse = nextFloatBuffer(16);
      previousProjection = nextFloatBuffer(16);
      previousModelView = nextFloatBuffer(16);
      tempMatrixDirectBuffer = nextFloatBuffer(16);
      tempDirectFloatBuffer = nextFloatBuffer(16);
      dfbColorTextures = nextIntBuffer(16);
      dfbDepthTextures = nextIntBuffer(3);
      sfbColorTextures = nextIntBuffer(8);
      sfbDepthTextures = nextIntBuffer(2);
      dfbDrawBuffers = nextIntBuffer(8);
      sfbDrawBuffers = nextIntBuffer(8);
      drawBuffersNone = (IntBuffer)nextIntBuffer(8).limit(0);
      drawBuffersColorAtt0 = (IntBuffer)nextIntBuffer(8).put(36064).position(0).limit(1);
      dfbColorTexturesFlip = new FlipTextures(dfbColorTextures, 8);
      formatNames = new String[]{"R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R16F", "RG16F", "RGB16F", "RGBA16F", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI", "R3_G3_B2", "RGB5_A1", "RGB10_A2", "R11F_G11F_B10F", "RGB9_E5"};
      formatIds = new int[]{33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33325, 33327, 34843, 34842, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208, 10768, 32855, 32857, 35898, 35901};
      patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
      entityData = new int[32];
      entityDataIndex = 0;
   }
}
