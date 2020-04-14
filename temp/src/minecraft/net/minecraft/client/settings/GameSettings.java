package net.minecraft.client.settings;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.optifine.ClearWater;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import net.optifine.util.KeyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameSettings {
   private static final Logger field_151450_ay = LogManager.getLogger();
   private static final Gson field_151449_az = new Gson();
   private static final ParameterizedType field_74367_ae = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };
   private static final String[] field_74364_ag = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
   private static final String[] field_98303_au = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   private static final String[] field_152391_aS = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   private static final String[] field_152392_aT = new String[]{"options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"};
   private static final String[] field_152393_aU = new String[]{"options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"};
   private static final String[] field_152394_aV = new String[]{"options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"};
   private static final String[] field_181149_aW = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
   private static final String[] field_178882_aU = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   public float field_74341_c = 0.5F;
   public boolean field_74338_d;
   public int field_151451_c = -1;
   public boolean field_74336_f = true;
   public boolean field_74337_g;
   public boolean field_151448_g = true;
   public int field_74350_i = 120;
   public int field_74345_l = 2;
   public boolean field_74347_j = true;
   public int field_74348_k = 2;
   public List<String> field_151453_l = Lists.newArrayList();
   public List<String> field_183018_l = Lists.newArrayList();
   public EntityPlayer.EnumChatVisibility field_74343_n;
   public boolean field_74344_o;
   public boolean field_74359_p;
   public boolean field_74358_q;
   public float field_74357_r;
   public boolean field_74355_t;
   public boolean field_74353_u;
   public boolean field_74352_v;
   public boolean field_178881_t;
   public boolean field_178880_u;
   public boolean field_178879_v;
   public boolean field_80005_w;
   public boolean field_82882_x;
   public boolean field_82881_y;
   private final Set<EnumPlayerModelParts> field_151446_aD;
   public boolean field_85185_A;
   public int field_92118_B;
   public int field_92119_C;
   public boolean field_92117_D;
   public float field_96691_E;
   public float field_96692_F;
   public float field_96693_G;
   public float field_96694_H;
   public boolean field_151441_H;
   public int field_151442_I;
   private Map<SoundCategory, Float> field_74354_ai;
   public float field_152400_J;
   public float field_152401_K;
   public float field_152402_L;
   public float field_152403_M;
   public float field_152404_N;
   public int field_152405_O;
   public boolean field_152406_P;
   public String field_152407_Q;
   public int field_152408_R;
   public int field_152409_S;
   public int field_152410_T;
   public boolean field_181150_U;
   public boolean field_181151_V;
   public boolean field_74351_w;
   public KeyBinding field_74370_x;
   public KeyBinding field_74368_y;
   public KeyBinding field_74366_z;
   public KeyBinding field_74314_A;
   public KeyBinding field_74311_E;
   public KeyBinding field_151444_V;
   public KeyBinding field_151445_Q;
   public KeyBinding field_74313_G;
   public KeyBinding field_74316_C;
   public KeyBinding field_74312_F;
   public KeyBinding field_74322_I;
   public KeyBinding field_74310_D;
   public KeyBinding field_74321_H;
   public KeyBinding field_74323_J;
   public KeyBinding field_151447_Z;
   public KeyBinding field_151457_aa;
   public KeyBinding field_151458_ab;
   public KeyBinding field_152395_am;
   public KeyBinding field_178883_an;
   public KeyBinding field_152396_an;
   public KeyBinding field_152397_ao;
   public KeyBinding field_152398_ap;
   public KeyBinding field_152399_aq;
   public KeyBinding field_151456_ac;
   public KeyBinding[] field_74324_K;
   public KeyBinding[] field_74317_L;
   protected Minecraft field_74318_M;
   private File bc;
   public EnumDifficulty field_74319_N;
   public boolean field_74320_O;
   public int field_74330_P;
   public boolean field_74329_Q;
   public boolean field_181657_aC;
   public boolean field_74332_R;
   public String field_74326_T;
   public boolean field_74325_U;
   public boolean field_74334_X;
   public float field_74333_Y;
   public float field_151452_as;
   public float field_74335_Z;
   public int field_74362_aa;
   public int field_74363_ab;
   public String field_151455_aw;
   public boolean field_151454_ax;
   public int ofFogType;
   public float ofFogStart;
   public int ofMipmapType;
   public boolean ofOcclusionFancy;
   public boolean ofSmoothFps;
   public boolean ofSmoothWorld;
   public boolean ofLazyChunkLoading;
   public boolean ofRenderRegions;
   public boolean ofSmartAnimations;
   public float ofAoLevel;
   public int ofAaLevel;
   public int ofAfLevel;
   public int ofClouds;
   public float ofCloudsHeight;
   public int ofTrees;
   public int ofRain;
   public int ofDroppedItems;
   public int ofBetterGrass;
   public int ofAutoSaveTicks;
   public boolean ofLagometer;
   public boolean ofProfiler;
   public boolean ofShowFps;
   public boolean ofWeather;
   public boolean ofSky;
   public boolean ofStars;
   public boolean ofSunMoon;
   public int ofVignette;
   public int ofChunkUpdates;
   public boolean ofChunkUpdatesDynamic;
   public int ofTime;
   public boolean ofClearWater;
   public boolean ofBetterSnow;
   public String ofFullscreenMode;
   public boolean ofSwampColors;
   public boolean ofRandomEntities;
   public boolean ofSmoothBiomes;
   public boolean ofCustomFonts;
   public boolean ofCustomColors;
   public boolean ofCustomSky;
   public boolean ofShowCapes;
   public int ofConnectedTextures;
   public boolean ofCustomItems;
   public boolean ofNaturalTextures;
   public boolean ofEmissiveTextures;
   public boolean ofFastMath;
   public boolean ofFastRender;
   public int ofTranslucentBlocks;
   public boolean ofDynamicFov;
   public boolean ofAlternateBlocks;
   public int ofDynamicLights;
   public boolean ofCustomEntityModels;
   public boolean ofCustomGuis;
   public boolean ofShowGlErrors;
   public int ofScreenshotSize;
   public int ofAnimatedWater;
   public int ofAnimatedLava;
   public boolean ofAnimatedFire;
   public boolean ofAnimatedPortal;
   public boolean ofAnimatedRedstone;
   public boolean ofAnimatedExplosion;
   public boolean ofAnimatedFlame;
   public boolean ofAnimatedSmoke;
   public boolean ofVoidParticles;
   public boolean ofWaterParticles;
   public boolean ofRainSplash;
   public boolean ofPortalParticles;
   public boolean ofPotionParticles;
   public boolean ofFireworkParticles;
   public boolean ofDrippingWaterLava;
   public boolean ofAnimatedTerrain;
   public boolean ofAnimatedTextures;
   public static final int DEFAULT = 0;
   public static final int FAST = 1;
   public static final int FANCY = 2;
   public static final int OFF = 3;
   public static final int SMART = 4;
   public static final int ANIM_ON = 0;
   public static final int ANIM_GENERATED = 1;
   public static final int ANIM_OFF = 2;
   public static final String DEFAULT_STR = "Default";
   private static final int[] OF_TREES_VALUES = new int[]{0, 1, 4, 2};
   private static final int[] OF_DYNAMIC_LIGHTS = new int[]{3, 1, 2};
   private static final String[] KEYS_DYNAMIC_LIGHTS = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   public KeyBinding ofKeyBindZoom;
   private File optionsFileOF;

   public GameSettings(Minecraft p_i46326_1_, File p_i46326_2_) {
      this.field_74343_n = EntityPlayer.EnumChatVisibility.FULL;
      this.field_74344_o = true;
      this.field_74359_p = true;
      this.field_74358_q = true;
      this.field_74357_r = 1.0F;
      this.field_74355_t = true;
      this.field_74352_v = true;
      this.field_178881_t = false;
      this.field_178880_u = true;
      this.field_178879_v = false;
      this.field_82881_y = true;
      this.field_151446_aD = Sets.newHashSet((Object[])EnumPlayerModelParts.values());
      this.field_92117_D = true;
      this.field_96691_E = 1.0F;
      this.field_96692_F = 1.0F;
      this.field_96693_G = 0.44366196F;
      this.field_96694_H = 1.0F;
      this.field_151441_H = true;
      this.field_151442_I = 4;
      this.field_74354_ai = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.field_181150_U = true;
      this.field_181151_V = true;
      this.field_74351_w = true;
      this.field_74370_x = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.field_74368_y = new KeyBinding("key.left", 30, "key.categories.movement");
      this.field_74366_z = new KeyBinding("key.back", 31, "key.categories.movement");
      this.field_74314_A = new KeyBinding("key.right", 32, "key.categories.movement");
      this.field_74311_E = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.field_151444_V = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.field_151445_Q = new KeyBinding("key.sprint", 29, "key.categories.movement");
      this.field_74313_G = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.field_74316_C = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.field_74312_F = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.field_74322_I = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.field_74310_D = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.field_74321_H = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.field_74323_J = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.field_151447_Z = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.field_151457_aa = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.field_151458_ab = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_178883_an = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.spectatorOutlines", 0, "key.categories.misc");
      this.field_152397_ao = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_151456_ac = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.field_74324_K = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmoothFps = false;
      this.ofSmoothWorld = Config.isSingleProcessor();
      this.ofLazyChunkLoading = Config.isSingleProcessor();
      this.ofRenderRegions = false;
      this.ofSmartAnimations = false;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofDroppedItems = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofProfiler = false;
      this.ofShowFps = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofCustomItems = true;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofScreenshotSize = 1;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      this.field_74317_L = (KeyBinding[])((KeyBinding[])ArrayUtils.addAll((Object[])(new KeyBinding[]{this.field_74322_I, this.field_74316_C, this.field_74370_x, this.field_74368_y, this.field_74366_z, this.field_74314_A, this.field_74311_E, this.field_151444_V, this.field_151445_Q, this.field_74312_F, this.field_74313_G, this.field_74321_H, this.field_74323_J, this.field_74310_D, this.field_151447_Z, this.field_151457_aa, this.field_151458_ab, this.field_152395_am, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_151456_ac, this.field_178883_an, this.field_152396_an}), (Object[])this.field_74324_K));
      this.field_74319_N = EnumDifficulty.NORMAL;
      this.field_74326_T = "";
      this.field_74333_Y = 70.0F;
      this.field_151455_aw = "en_US";
      this.field_151454_ax = false;
      this.field_74318_M = p_i46326_1_;
      this.bc = new File(p_i46326_2_, "options.txt");
      if (p_i46326_1_.func_147111_S() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
         GameSettings.Options.RENDER_DISTANCE.func_148263_a(32.0F);
         long MB = 1000000L;
         if (Runtime.getRuntime().maxMemory() >= 1500L * MB) {
            GameSettings.Options.RENDER_DISTANCE.func_148263_a(48.0F);
         }

         if (Runtime.getRuntime().maxMemory() >= 2500L * MB) {
            GameSettings.Options.RENDER_DISTANCE.func_148263_a(64.0F);
         }
      } else {
         GameSettings.Options.RENDER_DISTANCE.func_148263_a(16.0F);
      }

      this.field_151451_c = p_i46326_1_.func_147111_S() ? 12 : 8;
      this.optionsFileOF = new File(p_i46326_2_, "optionsof.txt");
      this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
      this.ofKeyBindZoom = new KeyBinding("of.key.zoom", 46, "key.categories.misc");
      this.field_74317_L = (KeyBinding[])((KeyBinding[])ArrayUtils.add(this.field_74317_L, this.ofKeyBindZoom));
      KeyUtils.fixKeyConflicts(this.field_74317_L, new KeyBinding[]{this.ofKeyBindZoom});
      this.field_151451_c = 8;
      this.func_74300_a();
      Config.initGameSettings(this);
   }

   public GameSettings() {
      this.field_74343_n = EntityPlayer.EnumChatVisibility.FULL;
      this.field_74344_o = true;
      this.field_74359_p = true;
      this.field_74358_q = true;
      this.field_74357_r = 1.0F;
      this.field_74355_t = true;
      this.field_74352_v = true;
      this.field_178881_t = false;
      this.field_178880_u = true;
      this.field_178879_v = false;
      this.field_82881_y = true;
      this.field_151446_aD = Sets.newHashSet((Object[])EnumPlayerModelParts.values());
      this.field_92117_D = true;
      this.field_96691_E = 1.0F;
      this.field_96692_F = 1.0F;
      this.field_96693_G = 0.44366196F;
      this.field_96694_H = 1.0F;
      this.field_151441_H = true;
      this.field_151442_I = 4;
      this.field_74354_ai = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.field_181150_U = true;
      this.field_181151_V = true;
      this.field_74351_w = true;
      this.field_74370_x = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.field_74368_y = new KeyBinding("key.left", 30, "key.categories.movement");
      this.field_74366_z = new KeyBinding("key.back", 31, "key.categories.movement");
      this.field_74314_A = new KeyBinding("key.right", 32, "key.categories.movement");
      this.field_74311_E = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.field_151444_V = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.field_151445_Q = new KeyBinding("key.sprint", 29, "key.categories.movement");
      this.field_74313_G = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.field_74316_C = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.field_74312_F = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.field_74322_I = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.field_74310_D = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.field_74321_H = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.field_74323_J = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.field_151447_Z = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.field_151457_aa = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.field_151458_ab = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_178883_an = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.spectatorOutlines", 0, "key.categories.misc");
      this.field_152397_ao = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_151456_ac = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.field_74324_K = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmoothFps = false;
      this.ofSmoothWorld = Config.isSingleProcessor();
      this.ofLazyChunkLoading = Config.isSingleProcessor();
      this.ofRenderRegions = false;
      this.ofSmartAnimations = false;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofDroppedItems = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofProfiler = false;
      this.ofShowFps = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofCustomItems = true;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofScreenshotSize = 1;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      this.field_74317_L = (KeyBinding[])((KeyBinding[])ArrayUtils.addAll((Object[])(new KeyBinding[]{this.field_74322_I, this.field_74316_C, this.field_74370_x, this.field_74368_y, this.field_74366_z, this.field_74314_A, this.field_74311_E, this.field_151444_V, this.field_151445_Q, this.field_74312_F, this.field_74313_G, this.field_74321_H, this.field_74323_J, this.field_74310_D, this.field_151447_Z, this.field_151457_aa, this.field_151458_ab, this.field_152395_am, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_151456_ac, this.field_178883_an, this.field_152396_an}), (Object[])this.field_74324_K));
      this.field_74319_N = EnumDifficulty.NORMAL;
      this.field_74326_T = "";
      this.field_74333_Y = 70.0F;
      this.field_151455_aw = "en_US";
      this.field_151454_ax = false;
   }

   public static String func_74298_c(int p_74298_0_) {
      return p_74298_0_ < 0 ? I18n.func_135052_a("key.mouseButton", p_74298_0_ + 101) : (p_74298_0_ < 256 ? Keyboard.getKeyName(p_74298_0_) : String.format("%c", (char)(p_74298_0_ - 256)).toUpperCase());
   }

   public static boolean func_100015_a(KeyBinding p_100015_0_) {
      return p_100015_0_.func_151463_i() == 0 ? false : (p_100015_0_.func_151463_i() < 0 ? Mouse.isButtonDown(p_100015_0_.func_151463_i() + 100) : Keyboard.isKeyDown(p_100015_0_.func_151463_i()));
   }

   public void func_151440_a(KeyBinding p_151440_1_, int p_151440_2_) {
      p_151440_1_.func_151462_b(p_151440_2_);
      this.func_74303_b();
   }

   public void func_74304_a(GameSettings.Options p_74304_1_, float p_74304_2_) {
      this.setOptionFloatValueOF(p_74304_1_, p_74304_2_);
      if (p_74304_1_ == GameSettings.Options.SENSITIVITY) {
         this.field_74341_c = p_74304_2_;
      }

      if (p_74304_1_ == GameSettings.Options.FOV) {
         this.field_74333_Y = p_74304_2_;
      }

      if (p_74304_1_ == GameSettings.Options.GAMMA) {
         this.field_151452_as = p_74304_2_;
      }

      if (p_74304_1_ == GameSettings.Options.FRAMERATE_LIMIT) {
         this.field_74350_i = (int)p_74304_2_;
         this.field_74352_v = false;
         if (this.field_74350_i <= 0) {
            this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
            this.field_74352_v = true;
         }

         this.updateVSync();
      }

      if (p_74304_1_ == GameSettings.Options.CHAT_OPACITY) {
         this.field_74357_r = p_74304_2_;
         this.field_74318_M.field_71456_v.func_146158_b().func_146245_b();
      }

      if (p_74304_1_ == GameSettings.Options.CHAT_HEIGHT_FOCUSED) {
         this.field_96694_H = p_74304_2_;
         this.field_74318_M.field_71456_v.func_146158_b().func_146245_b();
      }

      if (p_74304_1_ == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED) {
         this.field_96693_G = p_74304_2_;
         this.field_74318_M.field_71456_v.func_146158_b().func_146245_b();
      }

      if (p_74304_1_ == GameSettings.Options.CHAT_WIDTH) {
         this.field_96692_F = p_74304_2_;
         this.field_74318_M.field_71456_v.func_146158_b().func_146245_b();
      }

      if (p_74304_1_ == GameSettings.Options.CHAT_SCALE) {
         this.field_96691_E = p_74304_2_;
         this.field_74318_M.field_71456_v.func_146158_b().func_146245_b();
      }

      if (p_74304_1_ == GameSettings.Options.MIPMAP_LEVELS) {
         int i = this.field_151442_I;
         this.field_151442_I = (int)p_74304_2_;
         if ((float)i != p_74304_2_) {
            this.field_74318_M.func_147117_R().func_147633_a(this.field_151442_I);
            this.field_74318_M.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            this.field_74318_M.func_147117_R().func_174937_a(false, this.field_151442_I > 0);
            this.field_74318_M.func_175603_A();
         }
      }

      if (p_74304_1_ == GameSettings.Options.BLOCK_ALTERNATIVES) {
         this.field_178880_u = !this.field_178880_u;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_74304_1_ == GameSettings.Options.RENDER_DISTANCE) {
         this.field_151451_c = (int)p_74304_2_;
         this.field_74318_M.field_71438_f.func_174979_m();
      }

      if (p_74304_1_ == GameSettings.Options.STREAM_BYTES_PER_PIXEL) {
         this.field_152400_J = p_74304_2_;
      }

      if (p_74304_1_ == GameSettings.Options.STREAM_VOLUME_MIC) {
         this.field_152401_K = p_74304_2_;
         this.field_74318_M.func_152346_Z().func_152915_s();
      }

      if (p_74304_1_ == GameSettings.Options.STREAM_VOLUME_SYSTEM) {
         this.field_152402_L = p_74304_2_;
         this.field_74318_M.func_152346_Z().func_152915_s();
      }

      if (p_74304_1_ == GameSettings.Options.STREAM_KBPS) {
         this.field_152403_M = p_74304_2_;
      }

      if (p_74304_1_ == GameSettings.Options.STREAM_FPS) {
         this.field_152404_N = p_74304_2_;
      }

   }

   public void func_74306_a(GameSettings.Options p_74306_1_, int p_74306_2_) {
      this.setOptionValueOF(p_74306_1_, p_74306_2_);
      if (p_74306_1_ == GameSettings.Options.INVERT_MOUSE) {
         this.field_74338_d = !this.field_74338_d;
      }

      if (p_74306_1_ == GameSettings.Options.GUI_SCALE) {
         this.field_74362_aa += p_74306_2_;
         if (GuiScreen.func_146272_n()) {
            this.field_74362_aa = 0;
         }

         DisplayMode mode = Config.getLargestDisplayMode();
         int maxScaleWidth = mode.getWidth() / 320;
         int maxScaleHeight = mode.getHeight() / 240;
         int maxGuiScale = Math.min(maxScaleWidth, maxScaleHeight);
         if (this.field_74362_aa < 0) {
            this.field_74362_aa = maxGuiScale - 1;
         }

         if (this.field_74318_M.func_152349_b() && this.field_74362_aa % 2 != 0) {
            this.field_74362_aa += p_74306_2_;
         }

         if (this.field_74362_aa < 0 || this.field_74362_aa >= maxGuiScale) {
            this.field_74362_aa = 0;
         }
      }

      if (p_74306_1_ == GameSettings.Options.PARTICLES) {
         this.field_74363_ab = (this.field_74363_ab + p_74306_2_) % 3;
      }

      if (p_74306_1_ == GameSettings.Options.VIEW_BOBBING) {
         this.field_74336_f = !this.field_74336_f;
      }

      if (p_74306_1_ == GameSettings.Options.RENDER_CLOUDS) {
         this.field_74345_l = (this.field_74345_l + p_74306_2_) % 3;
      }

      if (p_74306_1_ == GameSettings.Options.FORCE_UNICODE_FONT) {
         this.field_151454_ax = !this.field_151454_ax;
         this.field_74318_M.field_71466_p.func_78264_a(this.field_74318_M.func_135016_M().func_135042_a() || this.field_151454_ax);
      }

      if (p_74306_1_ == GameSettings.Options.FBO_ENABLE) {
         this.field_151448_g = !this.field_151448_g;
      }

      if (p_74306_1_ == GameSettings.Options.ANAGLYPH) {
         if (!this.field_74337_g && Config.isShaders()) {
            Config.showGuiMessage(Lang.get("of.message.an.shaders1"), Lang.get("of.message.an.shaders2"));
            return;
         }

         this.field_74337_g = !this.field_74337_g;
         this.field_74318_M.func_110436_a();
      }

      if (p_74306_1_ == GameSettings.Options.GRAPHICS) {
         this.field_74347_j = !this.field_74347_j;
         this.updateRenderClouds();
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_74306_1_ == GameSettings.Options.AMBIENT_OCCLUSION) {
         this.field_74348_k = (this.field_74348_k + p_74306_2_) % 3;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_74306_1_ == GameSettings.Options.CHAT_VISIBILITY) {
         this.field_74343_n = EntityPlayer.EnumChatVisibility.func_151426_a((this.field_74343_n.func_151428_a() + p_74306_2_) % 3);
      }

      if (p_74306_1_ == GameSettings.Options.STREAM_COMPRESSION) {
         this.field_152405_O = (this.field_152405_O + p_74306_2_) % 3;
      }

      if (p_74306_1_ == GameSettings.Options.STREAM_SEND_METADATA) {
         this.field_152406_P = !this.field_152406_P;
      }

      if (p_74306_1_ == GameSettings.Options.STREAM_CHAT_ENABLED) {
         this.field_152408_R = (this.field_152408_R + p_74306_2_) % 3;
      }

      if (p_74306_1_ == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
         this.field_152409_S = (this.field_152409_S + p_74306_2_) % 3;
      }

      if (p_74306_1_ == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
         this.field_152410_T = (this.field_152410_T + p_74306_2_) % 2;
      }

      if (p_74306_1_ == GameSettings.Options.CHAT_COLOR) {
         this.field_74344_o = !this.field_74344_o;
      }

      if (p_74306_1_ == GameSettings.Options.CHAT_LINKS) {
         this.field_74359_p = !this.field_74359_p;
      }

      if (p_74306_1_ == GameSettings.Options.CHAT_LINKS_PROMPT) {
         this.field_74358_q = !this.field_74358_q;
      }

      if (p_74306_1_ == GameSettings.Options.SNOOPER_ENABLED) {
         this.field_74355_t = !this.field_74355_t;
      }

      if (p_74306_1_ == GameSettings.Options.TOUCHSCREEN) {
         this.field_85185_A = !this.field_85185_A;
      }

      if (p_74306_1_ == GameSettings.Options.USE_FULLSCREEN) {
         this.field_74353_u = !this.field_74353_u;
         if (this.field_74318_M.func_71372_G() != this.field_74353_u) {
            this.field_74318_M.func_71352_k();
         }
      }

      if (p_74306_1_ == GameSettings.Options.ENABLE_VSYNC) {
         this.field_74352_v = !this.field_74352_v;
         Display.setVSyncEnabled(this.field_74352_v);
      }

      if (p_74306_1_ == GameSettings.Options.USE_VBO) {
         this.field_178881_t = !this.field_178881_t;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_74306_1_ == GameSettings.Options.BLOCK_ALTERNATIVES) {
         this.field_178880_u = !this.field_178880_u;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_74306_1_ == GameSettings.Options.REDUCED_DEBUG_INFO) {
         this.field_178879_v = !this.field_178879_v;
      }

      if (p_74306_1_ == GameSettings.Options.ENTITY_SHADOWS) {
         this.field_181151_V = !this.field_181151_V;
      }

      if (p_74306_1_ == GameSettings.Options.field_74385_A) {
         this.field_74351_w = !this.field_74351_w;
      }

      this.func_74303_b();
   }

   public float func_74296_a(GameSettings.Options p_74296_1_) {
      float valOF = this.getOptionFloatValueOF(p_74296_1_);
      if (valOF != Float.MAX_VALUE) {
         return valOF;
      } else {
         return p_74296_1_ == GameSettings.Options.FOV ? this.field_74333_Y : (p_74296_1_ == GameSettings.Options.GAMMA ? this.field_151452_as : (p_74296_1_ == GameSettings.Options.SATURATION ? this.field_74335_Z : (p_74296_1_ == GameSettings.Options.SENSITIVITY ? this.field_74341_c : (p_74296_1_ == GameSettings.Options.CHAT_OPACITY ? this.field_74357_r : (p_74296_1_ == GameSettings.Options.CHAT_HEIGHT_FOCUSED ? this.field_96694_H : (p_74296_1_ == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED ? this.field_96693_G : (p_74296_1_ == GameSettings.Options.CHAT_SCALE ? this.field_96691_E : (p_74296_1_ == GameSettings.Options.CHAT_WIDTH ? this.field_96692_F : (p_74296_1_ == GameSettings.Options.FRAMERATE_LIMIT ? (float)this.field_74350_i : (p_74296_1_ == GameSettings.Options.MIPMAP_LEVELS ? (float)this.field_151442_I : (p_74296_1_ == GameSettings.Options.RENDER_DISTANCE ? (float)this.field_151451_c : (p_74296_1_ == GameSettings.Options.STREAM_BYTES_PER_PIXEL ? this.field_152400_J : (p_74296_1_ == GameSettings.Options.STREAM_VOLUME_MIC ? this.field_152401_K : (p_74296_1_ == GameSettings.Options.STREAM_VOLUME_SYSTEM ? this.field_152402_L : (p_74296_1_ == GameSettings.Options.STREAM_KBPS ? this.field_152403_M : (p_74296_1_ == GameSettings.Options.STREAM_FPS ? this.field_152404_N : 0.0F))))))))))))))));
      }
   }

   public boolean func_74308_b(GameSettings.Options p_74308_1_) {
      switch(p_74308_1_) {
      case INVERT_MOUSE:
         return this.field_74338_d;
      case VIEW_BOBBING:
         return this.field_74336_f;
      case ANAGLYPH:
         return this.field_74337_g;
      case FBO_ENABLE:
         return this.field_151448_g;
      case CHAT_COLOR:
         return this.field_74344_o;
      case CHAT_LINKS:
         return this.field_74359_p;
      case CHAT_LINKS_PROMPT:
         return this.field_74358_q;
      case SNOOPER_ENABLED:
         return this.field_74355_t;
      case USE_FULLSCREEN:
         return this.field_74353_u;
      case ENABLE_VSYNC:
         return this.field_74352_v;
      case USE_VBO:
         return this.field_178881_t;
      case TOUCHSCREEN:
         return this.field_85185_A;
      case STREAM_SEND_METADATA:
         return this.field_152406_P;
      case FORCE_UNICODE_FONT:
         return this.field_151454_ax;
      case BLOCK_ALTERNATIVES:
         return this.field_178880_u;
      case REDUCED_DEBUG_INFO:
         return this.field_178879_v;
      case ENTITY_SHADOWS:
         return this.field_181151_V;
      case field_74385_A:
         return this.field_74351_w;
      default:
         return false;
      }
   }

   private static String func_74299_a(String[] p_74299_0_, int p_74299_1_) {
      if (p_74299_1_ < 0 || p_74299_1_ >= p_74299_0_.length) {
         p_74299_1_ = 0;
      }

      return I18n.func_135052_a(p_74299_0_[p_74299_1_]);
   }

   public String func_74297_c(GameSettings.Options p_74297_1_) {
      String strOF = this.getKeyBindingOF(p_74297_1_);
      if (strOF != null) {
         return strOF;
      } else {
         String s = I18n.func_135052_a(p_74297_1_.func_74378_d()) + ": ";
         if (p_74297_1_.func_74380_a()) {
            float f1 = this.func_74296_a(p_74297_1_);
            float f = p_74297_1_.func_148266_c(f1);
            if (p_74297_1_ == GameSettings.Options.MIPMAP_LEVELS && (double)f1 >= 4.0D) {
               return s + Lang.get("of.general.max");
            } else {
               return p_74297_1_ == GameSettings.Options.SENSITIVITY ? (f == 0.0F ? s + I18n.func_135052_a("options.sensitivity.min") : (f == 1.0F ? s + I18n.func_135052_a("options.sensitivity.max") : s + (int)(f * 200.0F) + "%")) : (p_74297_1_ == GameSettings.Options.FOV ? (f1 == 70.0F ? s + I18n.func_135052_a("options.fov.min") : (f1 == 110.0F ? s + I18n.func_135052_a("options.fov.max") : s + (int)f1)) : (p_74297_1_ == GameSettings.Options.FRAMERATE_LIMIT ? (f1 == p_74297_1_.$VALUES ? s + I18n.func_135052_a("options.framerateLimit.max") : s + (int)f1 + " fps") : (p_74297_1_ == GameSettings.Options.RENDER_CLOUDS ? (f1 == p_74297_1_.field_148272_O ? s + I18n.func_135052_a("options.cloudHeight.min") : s + ((int)f1 + 128)) : (p_74297_1_ == GameSettings.Options.GAMMA ? (f == 0.0F ? s + I18n.func_135052_a("options.gamma.min") : (f == 1.0F ? s + I18n.func_135052_a("options.gamma.max") : s + "+" + (int)(f * 100.0F) + "%")) : (p_74297_1_ == GameSettings.Options.SATURATION ? s + (int)(f * 400.0F) + "%" : (p_74297_1_ == GameSettings.Options.CHAT_OPACITY ? s + (int)(f * 90.0F + 10.0F) + "%" : (p_74297_1_ == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED ? s + GuiNewChat.func_146243_b(f) + "px" : (p_74297_1_ == GameSettings.Options.CHAT_HEIGHT_FOCUSED ? s + GuiNewChat.func_146243_b(f) + "px" : (p_74297_1_ == GameSettings.Options.CHAT_WIDTH ? s + GuiNewChat.func_146233_a(f) + "px" : (p_74297_1_ == GameSettings.Options.RENDER_DISTANCE ? s + (int)f1 + " chunks" : (p_74297_1_ == GameSettings.Options.MIPMAP_LEVELS ? (f1 == 0.0F ? s + I18n.func_135052_a("options.off") : s + (int)f1) : (p_74297_1_ == GameSettings.Options.STREAM_FPS ? s + TwitchStream.func_152948_a(f) + " fps" : (p_74297_1_ == GameSettings.Options.STREAM_KBPS ? s + TwitchStream.func_152946_b(f) + " Kbps" : (p_74297_1_ == GameSettings.Options.STREAM_BYTES_PER_PIXEL ? s + String.format("%.3f bpp", TwitchStream.func_152947_c(f)) : (f == 0.0F ? s + I18n.func_135052_a("options.off") : s + (int)(f * 100.0F) + "%")))))))))))))));
            }
         } else if (p_74297_1_.func_74382_b()) {
            boolean flag = this.func_74308_b(p_74297_1_);
            return flag ? s + I18n.func_135052_a("options.on") : s + I18n.func_135052_a("options.off");
         } else if (p_74297_1_ == GameSettings.Options.GUI_SCALE) {
            return this.field_74362_aa >= field_74364_ag.length ? s + this.field_74362_aa + "x" : s + func_74299_a(field_74364_ag, this.field_74362_aa);
         } else if (p_74297_1_ == GameSettings.Options.CHAT_VISIBILITY) {
            return s + I18n.func_135052_a(this.field_74343_n.func_151429_b());
         } else if (p_74297_1_ == GameSettings.Options.PARTICLES) {
            return s + func_74299_a(field_98303_au, this.field_74363_ab);
         } else if (p_74297_1_ == GameSettings.Options.AMBIENT_OCCLUSION) {
            return s + func_74299_a(field_152391_aS, this.field_74348_k);
         } else if (p_74297_1_ == GameSettings.Options.STREAM_COMPRESSION) {
            return s + func_74299_a(field_152392_aT, this.field_152405_O);
         } else if (p_74297_1_ == GameSettings.Options.STREAM_CHAT_ENABLED) {
            return s + func_74299_a(field_152393_aU, this.field_152408_R);
         } else if (p_74297_1_ == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
            return s + func_74299_a(field_152394_aV, this.field_152409_S);
         } else if (p_74297_1_ == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
            return s + func_74299_a(field_181149_aW, this.field_152410_T);
         } else if (p_74297_1_ == GameSettings.Options.RENDER_CLOUDS) {
            return s + func_74299_a(field_178882_aU, this.field_74345_l);
         } else if (p_74297_1_ == GameSettings.Options.GRAPHICS) {
            if (this.field_74347_j) {
               return s + I18n.func_135052_a("options.graphics.fancy");
            } else {
               String s1 = "options.graphics.fast";
               return s + I18n.func_135052_a("options.graphics.fast");
            }
         } else {
            return s;
         }
      }
   }

   public void func_74300_a() {
      FileInputStream is = null;

      label695: {
         try {
            if (this.bc.exists()) {
               BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(is = new FileInputStream(this.bc)));
               String s = "";
               this.field_74354_ai.clear();

               while((s = bufferedreader.readLine()) != null) {
                  try {
                     String[] astring = s.split(":");
                     if (astring[0].equals("mouseSensitivity")) {
                        this.field_74341_c = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("fov")) {
                        this.field_74333_Y = this.func_74305_a(astring[1]) * 40.0F + 70.0F;
                     }

                     if (astring[0].equals("gamma")) {
                        this.field_151452_as = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("saturation")) {
                        this.field_74335_Z = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("invertYMouse")) {
                        this.field_74338_d = astring[1].equals("true");
                     }

                     if (astring[0].equals("renderDistance")) {
                        this.field_151451_c = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("guiScale")) {
                        this.field_74362_aa = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("particles")) {
                        this.field_74363_ab = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("bobView")) {
                        this.field_74336_f = astring[1].equals("true");
                     }

                     if (astring[0].equals("anaglyph3d")) {
                        this.field_74337_g = astring[1].equals("true");
                     }

                     if (astring[0].equals("maxFps")) {
                        this.field_74350_i = Integer.parseInt(astring[1]);
                        if (this.field_74352_v) {
                           this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
                        }

                        if (this.field_74350_i <= 0) {
                           this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
                        }
                     }

                     if (astring[0].equals("fboEnable")) {
                        this.field_151448_g = astring[1].equals("true");
                     }

                     if (astring[0].equals("difficulty")) {
                        this.field_74319_N = EnumDifficulty.func_151523_a(Integer.parseInt(astring[1]));
                     }

                     if (astring[0].equals("fancyGraphics")) {
                        this.field_74347_j = astring[1].equals("true");
                        this.updateRenderClouds();
                     }

                     if (astring[0].equals("ao")) {
                        if (astring[1].equals("true")) {
                           this.field_74348_k = 2;
                        } else if (astring[1].equals("false")) {
                           this.field_74348_k = 0;
                        } else {
                           this.field_74348_k = Integer.parseInt(astring[1]);
                        }
                     }

                     if (astring[0].equals("renderClouds")) {
                        if (astring[1].equals("true")) {
                           this.field_74345_l = 2;
                        } else if (astring[1].equals("false")) {
                           this.field_74345_l = 0;
                        } else if (astring[1].equals("fast")) {
                           this.field_74345_l = 1;
                        }
                     }

                     if (astring[0].equals("resourcePacks")) {
                        this.field_151453_l = (List)field_151449_az.fromJson((String)s.substring(s.indexOf(58) + 1), (Type)field_74367_ae);
                        if (this.field_151453_l == null) {
                           this.field_151453_l = Lists.newArrayList();
                        }
                     }

                     if (astring[0].equals("incompatibleResourcePacks")) {
                        this.field_183018_l = (List)field_151449_az.fromJson((String)s.substring(s.indexOf(58) + 1), (Type)field_74367_ae);
                        if (this.field_183018_l == null) {
                           this.field_183018_l = Lists.newArrayList();
                        }
                     }

                     if (astring[0].equals("lastServer") && astring.length >= 2) {
                        this.field_74326_T = s.substring(s.indexOf(58) + 1);
                     }

                     if (astring[0].equals("lang") && astring.length >= 2) {
                        this.field_151455_aw = astring[1];
                     }

                     if (astring[0].equals("chatVisibility")) {
                        this.field_74343_n = EntityPlayer.EnumChatVisibility.func_151426_a(Integer.parseInt(astring[1]));
                     }

                     if (astring[0].equals("chatColors")) {
                        this.field_74344_o = astring[1].equals("true");
                     }

                     if (astring[0].equals("chatLinks")) {
                        this.field_74359_p = astring[1].equals("true");
                     }

                     if (astring[0].equals("chatLinksPrompt")) {
                        this.field_74358_q = astring[1].equals("true");
                     }

                     if (astring[0].equals("chatOpacity")) {
                        this.field_74357_r = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("snooperEnabled")) {
                        this.field_74355_t = astring[1].equals("true");
                     }

                     if (astring[0].equals("fullscreen")) {
                        this.field_74353_u = astring[1].equals("true");
                     }

                     if (astring[0].equals("enableVsync")) {
                        this.field_74352_v = astring[1].equals("true");
                        if (this.field_74352_v) {
                           this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
                        }

                        this.updateVSync();
                     }

                     if (astring[0].equals("useVbo")) {
                        this.field_178881_t = astring[1].equals("true");
                     }

                     if (astring[0].equals("hideServerAddress")) {
                        this.field_80005_w = astring[1].equals("true");
                     }

                     if (astring[0].equals("advancedItemTooltips")) {
                        this.field_82882_x = astring[1].equals("true");
                     }

                     if (astring[0].equals("pauseOnLostFocus")) {
                        this.field_82881_y = astring[1].equals("true");
                     }

                     if (astring[0].equals("touchscreen")) {
                        this.field_85185_A = astring[1].equals("true");
                     }

                     if (astring[0].equals("overrideHeight")) {
                        this.field_92119_C = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("overrideWidth")) {
                        this.field_92118_B = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("heldItemTooltips")) {
                        this.field_92117_D = astring[1].equals("true");
                     }

                     if (astring[0].equals("chatHeightFocused")) {
                        this.field_96694_H = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("chatHeightUnfocused")) {
                        this.field_96693_G = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("chatScale")) {
                        this.field_96691_E = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("chatWidth")) {
                        this.field_96692_F = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("showInventoryAchievementHint")) {
                        this.field_151441_H = astring[1].equals("true");
                     }

                     if (astring[0].equals("mipmapLevels")) {
                        this.field_151442_I = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("streamBytesPerPixel")) {
                        this.field_152400_J = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("streamMicVolume")) {
                        this.field_152401_K = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("streamSystemVolume")) {
                        this.field_152402_L = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("streamKbps")) {
                        this.field_152403_M = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("streamFps")) {
                        this.field_152404_N = this.func_74305_a(astring[1]);
                     }

                     if (astring[0].equals("streamCompression")) {
                        this.field_152405_O = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("streamSendMetadata")) {
                        this.field_152406_P = astring[1].equals("true");
                     }

                     if (astring[0].equals("streamPreferredServer") && astring.length >= 2) {
                        this.field_152407_Q = s.substring(s.indexOf(58) + 1);
                     }

                     if (astring[0].equals("streamChatEnabled")) {
                        this.field_152408_R = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("streamChatUserFilter")) {
                        this.field_152409_S = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("streamMicToggleBehavior")) {
                        this.field_152410_T = Integer.parseInt(astring[1]);
                     }

                     if (astring[0].equals("forceUnicodeFont")) {
                        this.field_151454_ax = astring[1].equals("true");
                     }

                     if (astring[0].equals("allowBlockAlternatives")) {
                        this.field_178880_u = astring[1].equals("true");
                     }

                     if (astring[0].equals("reducedDebugInfo")) {
                        this.field_178879_v = astring[1].equals("true");
                     }

                     if (astring[0].equals("useNativeTransport")) {
                        this.field_181150_U = astring[1].equals("true");
                     }

                     if (astring[0].equals("entityShadows")) {
                        this.field_181151_V = astring[1].equals("true");
                     }

                     if (astring[0].equals("realmsNotifications")) {
                        this.field_74351_w = astring[1].equals("true");
                     }

                     KeyBinding[] arr$ = this.field_74317_L;
                     int len$ = arr$.length;

                     int i$;
                     for(i$ = 0; i$ < len$; ++i$) {
                        KeyBinding keybinding = arr$[i$];
                        if (astring[0].equals("key_" + keybinding.func_151464_g())) {
                           keybinding.func_151462_b(Integer.parseInt(astring[1]));
                        }
                     }

                     SoundCategory[] arr$ = SoundCategory.values();
                     len$ = arr$.length;

                     for(i$ = 0; i$ < len$; ++i$) {
                        SoundCategory soundcategory = arr$[i$];
                        if (astring[0].equals("soundCategory_" + soundcategory.func_147155_a())) {
                           this.field_74354_ai.put(soundcategory, this.func_74305_a(astring[1]));
                        }
                     }

                     EnumPlayerModelParts[] arr$ = EnumPlayerModelParts.values();
                     len$ = arr$.length;

                     for(i$ = 0; i$ < len$; ++i$) {
                        EnumPlayerModelParts enumplayermodelparts = arr$[i$];
                        if (astring[0].equals("modelPart_" + enumplayermodelparts.func_179329_c())) {
                           this.func_178878_a(enumplayermodelparts, astring[1].equals("true"));
                        }
                     }
                  } catch (Exception var13) {
                     field_151450_ay.warn("Skipping bad option: " + s);
                     var13.printStackTrace();
                  }
               }

               KeyBinding.func_74508_b();
               bufferedreader.close();
               break label695;
            }
         } catch (Exception var14) {
            field_151450_ay.error((String)"Failed to load options", (Throwable)var14);
            break label695;
         } finally {
            IOUtils.closeQuietly((InputStream)is);
         }

         return;
      }

      this.loadOfOptions();
   }

   private float func_74305_a(String p_74305_1_) {
      return p_74305_1_.equals("true") ? 1.0F : (p_74305_1_.equals("false") ? 0.0F : Float.parseFloat(p_74305_1_));
   }

   public void func_74303_b() {
      if (Reflector.FMLClientHandler.exists()) {
         Object fml = Reflector.call(Reflector.FMLClientHandler_instance);
         if (fml != null && Reflector.callBoolean(fml, Reflector.FMLClientHandler_isLoading)) {
            return;
         }
      }

      try {
         PrintWriter printwriter = new PrintWriter(new FileWriter(this.bc));
         printwriter.println("invertYMouse:" + this.field_74338_d);
         printwriter.println("mouseSensitivity:" + this.field_74341_c);
         printwriter.println("fov:" + (this.field_74333_Y - 70.0F) / 40.0F);
         printwriter.println("gamma:" + this.field_151452_as);
         printwriter.println("saturation:" + this.field_74335_Z);
         printwriter.println("renderDistance:" + this.field_151451_c);
         printwriter.println("guiScale:" + this.field_74362_aa);
         printwriter.println("particles:" + this.field_74363_ab);
         printwriter.println("bobView:" + this.field_74336_f);
         printwriter.println("anaglyph3d:" + this.field_74337_g);
         printwriter.println("maxFps:" + this.field_74350_i);
         printwriter.println("fboEnable:" + this.field_151448_g);
         printwriter.println("difficulty:" + this.field_74319_N.func_151525_a());
         printwriter.println("fancyGraphics:" + this.field_74347_j);
         printwriter.println("ao:" + this.field_74348_k);
         switch(this.field_74345_l) {
         case 0:
            printwriter.println("renderClouds:false");
            break;
         case 1:
            printwriter.println("renderClouds:fast");
            break;
         case 2:
            printwriter.println("renderClouds:true");
         }

         printwriter.println("resourcePacks:" + field_151449_az.toJson((Object)this.field_151453_l));
         printwriter.println("incompatibleResourcePacks:" + field_151449_az.toJson((Object)this.field_183018_l));
         printwriter.println("lastServer:" + this.field_74326_T);
         printwriter.println("lang:" + this.field_151455_aw);
         printwriter.println("chatVisibility:" + this.field_74343_n.func_151428_a());
         printwriter.println("chatColors:" + this.field_74344_o);
         printwriter.println("chatLinks:" + this.field_74359_p);
         printwriter.println("chatLinksPrompt:" + this.field_74358_q);
         printwriter.println("chatOpacity:" + this.field_74357_r);
         printwriter.println("snooperEnabled:" + this.field_74355_t);
         printwriter.println("fullscreen:" + this.field_74353_u);
         printwriter.println("enableVsync:" + this.field_74352_v);
         printwriter.println("useVbo:" + this.field_178881_t);
         printwriter.println("hideServerAddress:" + this.field_80005_w);
         printwriter.println("advancedItemTooltips:" + this.field_82882_x);
         printwriter.println("pauseOnLostFocus:" + this.field_82881_y);
         printwriter.println("touchscreen:" + this.field_85185_A);
         printwriter.println("overrideWidth:" + this.field_92118_B);
         printwriter.println("overrideHeight:" + this.field_92119_C);
         printwriter.println("heldItemTooltips:" + this.field_92117_D);
         printwriter.println("chatHeightFocused:" + this.field_96694_H);
         printwriter.println("chatHeightUnfocused:" + this.field_96693_G);
         printwriter.println("chatScale:" + this.field_96691_E);
         printwriter.println("chatWidth:" + this.field_96692_F);
         printwriter.println("showInventoryAchievementHint:" + this.field_151441_H);
         printwriter.println("mipmapLevels:" + this.field_151442_I);
         printwriter.println("streamBytesPerPixel:" + this.field_152400_J);
         printwriter.println("streamMicVolume:" + this.field_152401_K);
         printwriter.println("streamSystemVolume:" + this.field_152402_L);
         printwriter.println("streamKbps:" + this.field_152403_M);
         printwriter.println("streamFps:" + this.field_152404_N);
         printwriter.println("streamCompression:" + this.field_152405_O);
         printwriter.println("streamSendMetadata:" + this.field_152406_P);
         printwriter.println("streamPreferredServer:" + this.field_152407_Q);
         printwriter.println("streamChatEnabled:" + this.field_152408_R);
         printwriter.println("streamChatUserFilter:" + this.field_152409_S);
         printwriter.println("streamMicToggleBehavior:" + this.field_152410_T);
         printwriter.println("forceUnicodeFont:" + this.field_151454_ax);
         printwriter.println("allowBlockAlternatives:" + this.field_178880_u);
         printwriter.println("reducedDebugInfo:" + this.field_178879_v);
         printwriter.println("useNativeTransport:" + this.field_181150_U);
         printwriter.println("entityShadows:" + this.field_181151_V);
         printwriter.println("realmsNotifications:" + this.field_74351_w);
         KeyBinding[] arr$ = this.field_74317_L;
         int len$ = arr$.length;

         int i$;
         for(i$ = 0; i$ < len$; ++i$) {
            KeyBinding keybinding = arr$[i$];
            printwriter.println("key_" + keybinding.func_151464_g() + ":" + keybinding.func_151463_i());
         }

         SoundCategory[] arr$ = SoundCategory.values();
         len$ = arr$.length;

         for(i$ = 0; i$ < len$; ++i$) {
            SoundCategory soundcategory = arr$[i$];
            printwriter.println("soundCategory_" + soundcategory.func_147155_a() + ":" + this.func_151438_a(soundcategory));
         }

         EnumPlayerModelParts[] arr$ = EnumPlayerModelParts.values();
         len$ = arr$.length;

         for(i$ = 0; i$ < len$; ++i$) {
            EnumPlayerModelParts enumplayermodelparts = arr$[i$];
            printwriter.println("modelPart_" + enumplayermodelparts.func_179329_c() + ":" + this.field_151446_aD.contains(enumplayermodelparts));
         }

         printwriter.close();
      } catch (Exception var6) {
         field_151450_ay.error((String)"Failed to save options", (Throwable)var6);
      }

      this.saveOfOptions();
      this.func_82879_c();
   }

   public float func_151438_a(SoundCategory p_151438_1_) {
      return this.field_74354_ai.containsKey(p_151438_1_) ? (Float)this.field_74354_ai.get(p_151438_1_) : 1.0F;
   }

   public void func_151439_a(SoundCategory p_151439_1_, float p_151439_2_) {
      this.field_74318_M.func_147118_V().func_147684_a(p_151439_1_, p_151439_2_);
      this.field_74354_ai.put(p_151439_1_, p_151439_2_);
   }

   public void func_82879_c() {
      if (this.field_74318_M.field_71439_g != null) {
         int i = 0;

         EnumPlayerModelParts enumplayermodelparts;
         for(Iterator i$ = this.field_151446_aD.iterator(); i$.hasNext(); i |= enumplayermodelparts.func_179327_a()) {
            enumplayermodelparts = (EnumPlayerModelParts)i$.next();
         }

         this.field_74318_M.field_71439_g.field_71174_a.func_147297_a(new C15PacketClientSettings(this.field_151455_aw, this.field_151451_c, this.field_74343_n, this.field_74344_o, i));
      }

   }

   public Set<EnumPlayerModelParts> func_178876_d() {
      return ImmutableSet.copyOf((Collection)this.field_151446_aD);
   }

   public void func_178878_a(EnumPlayerModelParts p_178878_1_, boolean p_178878_2_) {
      if (p_178878_2_) {
         this.field_151446_aD.add(p_178878_1_);
      } else {
         this.field_151446_aD.remove(p_178878_1_);
      }

      this.func_82879_c();
   }

   public void func_178877_a(EnumPlayerModelParts p_178877_1_) {
      if (!this.func_178876_d().contains(p_178877_1_)) {
         this.field_151446_aD.add(p_178877_1_);
      } else {
         this.field_151446_aD.remove(p_178877_1_);
      }

      this.func_82879_c();
   }

   public int func_181147_e() {
      return this.field_151451_c >= 4 ? this.field_74345_l : 0;
   }

   public boolean func_181148_f() {
      return this.field_181150_U;
   }

   private void setOptionFloatValueOF(GameSettings.Options p_setOptionFloatValueOF_1_, float p_setOptionFloatValueOF_2_) {
      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.CLOUD_HEIGHT) {
         this.ofCloudsHeight = p_setOptionFloatValueOF_2_;
         this.field_74318_M.field_71438_f.resetClouds();
      }

      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.AO_LEVEL) {
         this.ofAoLevel = p_setOptionFloatValueOF_2_;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      int index;
      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.AA_LEVEL) {
         index = (int)p_setOptionFloatValueOF_2_;
         if (index > 0 && Config.isShaders()) {
            Config.showGuiMessage(Lang.get("of.message.aa.shaders1"), Lang.get("of.message.aa.shaders2"));
            return;
         }

         int[] aaLevels = new int[]{0, 2, 4, 6, 8, 12, 16};
         this.ofAaLevel = 0;

         for(int l = 0; l < aaLevels.length; ++l) {
            if (index >= aaLevels[l]) {
               this.ofAaLevel = aaLevels[l];
            }
         }

         this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
      }

      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.AF_LEVEL) {
         index = (int)p_setOptionFloatValueOF_2_;
         if (index > 1 && Config.isShaders()) {
            Config.showGuiMessage(Lang.get("of.message.af.shaders1"), Lang.get("of.message.af.shaders2"));
            return;
         }

         for(this.ofAfLevel = 1; this.ofAfLevel * 2 <= index; this.ofAfLevel *= 2) {
         }

         this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.MIPMAP_TYPE) {
         index = (int)p_setOptionFloatValueOF_2_;
         this.ofMipmapType = Config.limit(index, 0, 3);
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionFloatValueOF_1_ == GameSettings.Options.FULLSCREEN_MODE) {
         index = (int)p_setOptionFloatValueOF_2_ - 1;
         String[] modeNames = Config.getDisplayModeNames();
         if (index < 0 || index >= modeNames.length) {
            this.ofFullscreenMode = "Default";
            return;
         }

         this.ofFullscreenMode = modeNames[index];
      }

   }

   private float getOptionFloatValueOF(GameSettings.Options p_getOptionFloatValueOF_1_) {
      if (p_getOptionFloatValueOF_1_ == GameSettings.Options.CLOUD_HEIGHT) {
         return this.ofCloudsHeight;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.AO_LEVEL) {
         return this.ofAoLevel;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.AA_LEVEL) {
         return (float)this.ofAaLevel;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.AF_LEVEL) {
         return (float)this.ofAfLevel;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.MIPMAP_TYPE) {
         return (float)this.ofMipmapType;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.FRAMERATE_LIMIT) {
         return (float)this.field_74350_i == GameSettings.Options.FRAMERATE_LIMIT.func_148267_f() && this.field_74352_v ? 0.0F : (float)this.field_74350_i;
      } else if (p_getOptionFloatValueOF_1_ == GameSettings.Options.FULLSCREEN_MODE) {
         if (this.ofFullscreenMode.equals("Default")) {
            return 0.0F;
         } else {
            List modeList = Arrays.asList(Config.getDisplayModeNames());
            int index = modeList.indexOf(this.ofFullscreenMode);
            return index < 0 ? 0.0F : (float)(index + 1);
         }
      } else {
         return Float.MAX_VALUE;
      }
   }

   private void setOptionValueOF(GameSettings.Options p_setOptionValueOF_1_, int p_setOptionValueOF_2_) {
      if (p_setOptionValueOF_1_ == GameSettings.Options.FOG_FANCY) {
         switch(this.ofFogType) {
         case 1:
            this.ofFogType = 2;
            if (!Config.isFancyFogAvailable()) {
               this.ofFogType = 3;
            }
            break;
         case 2:
            this.ofFogType = 3;
            break;
         case 3:
            this.ofFogType = 1;
            break;
         default:
            this.ofFogType = 1;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.FOG_START) {
         this.ofFogStart += 0.2F;
         if (this.ofFogStart > 0.81F) {
            this.ofFogStart = 0.2F;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SMOOTH_FPS) {
         this.ofSmoothFps = !this.ofSmoothFps;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SMOOTH_WORLD) {
         this.ofSmoothWorld = !this.ofSmoothWorld;
         Config.updateThreadPriorities();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CLOUDS) {
         ++this.ofClouds;
         if (this.ofClouds > 3) {
            this.ofClouds = 0;
         }

         this.updateRenderClouds();
         this.field_74318_M.field_71438_f.resetClouds();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.TREES) {
         this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.DROPPED_ITEMS) {
         ++this.ofDroppedItems;
         if (this.ofDroppedItems > 2) {
            this.ofDroppedItems = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.RAIN) {
         ++this.ofRain;
         if (this.ofRain > 3) {
            this.ofRain = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_WATER) {
         ++this.ofAnimatedWater;
         if (this.ofAnimatedWater == 1) {
            ++this.ofAnimatedWater;
         }

         if (this.ofAnimatedWater > 2) {
            this.ofAnimatedWater = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_LAVA) {
         ++this.ofAnimatedLava;
         if (this.ofAnimatedLava == 1) {
            ++this.ofAnimatedLava;
         }

         if (this.ofAnimatedLava > 2) {
            this.ofAnimatedLava = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_FIRE) {
         this.ofAnimatedFire = !this.ofAnimatedFire;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_PORTAL) {
         this.ofAnimatedPortal = !this.ofAnimatedPortal;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_REDSTONE) {
         this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_EXPLOSION) {
         this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_FLAME) {
         this.ofAnimatedFlame = !this.ofAnimatedFlame;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_SMOKE) {
         this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.VOID_PARTICLES) {
         this.ofVoidParticles = !this.ofVoidParticles;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.WATER_PARTICLES) {
         this.ofWaterParticles = !this.ofWaterParticles;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.PORTAL_PARTICLES) {
         this.ofPortalParticles = !this.ofPortalParticles;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.POTION_PARTICLES) {
         this.ofPotionParticles = !this.ofPotionParticles;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.FIREWORK_PARTICLES) {
         this.ofFireworkParticles = !this.ofFireworkParticles;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.DRIPPING_WATER_LAVA) {
         this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_TERRAIN) {
         this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ANIMATED_TEXTURES) {
         this.ofAnimatedTextures = !this.ofAnimatedTextures;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.RAIN_SPLASH) {
         this.ofRainSplash = !this.ofRainSplash;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.LAGOMETER) {
         this.ofLagometer = !this.ofLagometer;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SHOW_FPS) {
         this.ofShowFps = !this.ofShowFps;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.AUTOSAVE_TICKS) {
         int step = 900;
         this.ofAutoSaveTicks = Math.max(this.ofAutoSaveTicks / step * step, step);
         this.ofAutoSaveTicks *= 2;
         if (this.ofAutoSaveTicks > 32 * step) {
            this.ofAutoSaveTicks = step;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.BETTER_GRASS) {
         ++this.ofBetterGrass;
         if (this.ofBetterGrass > 3) {
            this.ofBetterGrass = 1;
         }

         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CONNECTED_TEXTURES) {
         ++this.ofConnectedTextures;
         if (this.ofConnectedTextures > 3) {
            this.ofConnectedTextures = 1;
         }

         if (this.ofConnectedTextures == 2) {
            this.field_74318_M.field_71438_f.func_72712_a();
         } else {
            this.field_74318_M.func_110436_a();
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.WEATHER) {
         this.ofWeather = !this.ofWeather;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SKY) {
         this.ofSky = !this.ofSky;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.STARS) {
         this.ofStars = !this.ofStars;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SUN_MOON) {
         this.ofSunMoon = !this.ofSunMoon;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.VIGNETTE) {
         ++this.ofVignette;
         if (this.ofVignette > 2) {
            this.ofVignette = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CHUNK_UPDATES) {
         ++this.ofChunkUpdates;
         if (this.ofChunkUpdates > 5) {
            this.ofChunkUpdates = 1;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         this.ofChunkUpdatesDynamic = !this.ofChunkUpdatesDynamic;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.TIME) {
         ++this.ofTime;
         if (this.ofTime > 2) {
            this.ofTime = 0;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CLEAR_WATER) {
         this.ofClearWater = !this.ofClearWater;
         this.updateWaterOpacity();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.PROFILER) {
         this.ofProfiler = !this.ofProfiler;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.BETTER_SNOW) {
         this.ofBetterSnow = !this.ofBetterSnow;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SWAMP_COLORS) {
         this.ofSwampColors = !this.ofSwampColors;
         CustomColors.updateUseDefaultGrassFoliageColors();
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.RANDOM_ENTITIES) {
         this.ofRandomEntities = !this.ofRandomEntities;
         RandomEntities.update();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SMOOTH_BIOMES) {
         this.ofSmoothBiomes = !this.ofSmoothBiomes;
         CustomColors.updateUseDefaultGrassFoliageColors();
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_FONTS) {
         this.ofCustomFonts = !this.ofCustomFonts;
         this.field_74318_M.field_71466_p.func_110549_a(Config.getResourceManager());
         this.field_74318_M.field_71464_q.func_110549_a(Config.getResourceManager());
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_COLORS) {
         this.ofCustomColors = !this.ofCustomColors;
         CustomColors.update();
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_ITEMS) {
         this.ofCustomItems = !this.ofCustomItems;
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_SKY) {
         this.ofCustomSky = !this.ofCustomSky;
         CustomSky.update();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SHOW_CAPES) {
         this.ofShowCapes = !this.ofShowCapes;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.NATURAL_TEXTURES) {
         this.ofNaturalTextures = !this.ofNaturalTextures;
         NaturalTextures.update();
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.EMISSIVE_TEXTURES) {
         this.ofEmissiveTextures = !this.ofEmissiveTextures;
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.FAST_MATH) {
         this.ofFastMath = !this.ofFastMath;
         MathHelper.fastMath = this.ofFastMath;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.FAST_RENDER) {
         if (!this.ofFastRender && Config.isShaders()) {
            Config.showGuiMessage(Lang.get("of.message.fr.shaders1"), Lang.get("of.message.fr.shaders2"));
            return;
         }

         this.ofFastRender = !this.ofFastRender;
         if (this.ofFastRender) {
            this.field_74318_M.field_71460_t.func_181022_b();
         }

         Config.updateFramebufferSize();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         if (this.ofTranslucentBlocks == 0) {
            this.ofTranslucentBlocks = 1;
         } else if (this.ofTranslucentBlocks == 1) {
            this.ofTranslucentBlocks = 2;
         } else if (this.ofTranslucentBlocks == 2) {
            this.ofTranslucentBlocks = 0;
         } else {
            this.ofTranslucentBlocks = 0;
         }

         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.LAZY_CHUNK_LOADING) {
         this.ofLazyChunkLoading = !this.ofLazyChunkLoading;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.RENDER_REGIONS) {
         this.ofRenderRegions = !this.ofRenderRegions;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SMART_ANIMATIONS) {
         this.ofSmartAnimations = !this.ofSmartAnimations;
         this.field_74318_M.field_71438_f.func_72712_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.DYNAMIC_FOV) {
         this.ofDynamicFov = !this.ofDynamicFov;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ALTERNATE_BLOCKS) {
         this.ofAlternateBlocks = !this.ofAlternateBlocks;
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.DYNAMIC_LIGHTS) {
         this.ofDynamicLights = nextValue(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         DynamicLights.removeLights(this.field_74318_M.field_71438_f);
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SCREENSHOT_SIZE) {
         ++this.ofScreenshotSize;
         if (this.ofScreenshotSize > 4) {
            this.ofScreenshotSize = 1;
         }

         if (!OpenGlHelper.func_148822_b()) {
            this.ofScreenshotSize = 1;
         }
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_ENTITY_MODELS) {
         this.ofCustomEntityModels = !this.ofCustomEntityModels;
         this.field_74318_M.func_110436_a();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.CUSTOM_GUIS) {
         this.ofCustomGuis = !this.ofCustomGuis;
         CustomGuis.update();
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.SHOW_GL_ERRORS) {
         this.ofShowGlErrors = !this.ofShowGlErrors;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         this.field_92117_D = !this.field_92117_D;
      }

      if (p_setOptionValueOF_1_ == GameSettings.Options.ADVANCED_TOOLTIPS) {
         this.field_82882_x = !this.field_82882_x;
      }

   }

   private String getKeyBindingOF(GameSettings.Options p_getKeyBindingOF_1_) {
      String var2 = I18n.func_135052_a(p_getKeyBindingOF_1_.func_74378_d()) + ": ";
      if (var2 == null) {
         var2 = p_getKeyBindingOF_1_.func_74378_d();
      }

      int index;
      if (p_getKeyBindingOF_1_ == GameSettings.Options.RENDER_DISTANCE) {
         index = (int)this.func_74296_a(p_getKeyBindingOF_1_);
         String str = I18n.func_135052_a("options.renderDistance.tiny");
         int baseDist = 2;
         if (index >= 4) {
            str = I18n.func_135052_a("options.renderDistance.short");
            baseDist = 4;
         }

         if (index >= 8) {
            str = I18n.func_135052_a("options.renderDistance.normal");
            baseDist = 8;
         }

         if (index >= 16) {
            str = I18n.func_135052_a("options.renderDistance.far");
            baseDist = 16;
         }

         if (index >= 32) {
            str = Lang.get("of.options.renderDistance.extreme");
            baseDist = 32;
         }

         if (index >= 48) {
            str = Lang.get("of.options.renderDistance.insane");
            baseDist = 48;
         }

         if (index >= 64) {
            str = Lang.get("of.options.renderDistance.ludicrous");
            baseDist = 64;
         }

         int diff = this.field_151451_c - baseDist;
         String descr = str;
         if (diff > 0) {
            descr = str + "+";
         }

         return var2 + index + " " + descr + "";
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FOG_FANCY) {
         switch(this.ofFogType) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         case 3:
            return var2 + Lang.getOff();
         default:
            return var2 + Lang.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FOG_START) {
         return var2 + this.ofFogStart;
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.MIPMAP_TYPE) {
         switch(this.ofMipmapType) {
         case 0:
            return var2 + Lang.get("of.options.mipmap.nearest");
         case 1:
            return var2 + Lang.get("of.options.mipmap.linear");
         case 2:
            return var2 + Lang.get("of.options.mipmap.bilinear");
         case 3:
            return var2 + Lang.get("of.options.mipmap.trilinear");
         default:
            return var2 + "of.options.mipmap.nearest";
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SMOOTH_FPS) {
         return this.ofSmoothFps ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SMOOTH_WORLD) {
         return this.ofSmoothWorld ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CLOUDS) {
         switch(this.ofClouds) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         case 3:
            return var2 + Lang.getOff();
         default:
            return var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.TREES) {
         switch(this.ofTrees) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         case 3:
         default:
            return var2 + Lang.getDefault();
         case 4:
            return var2 + Lang.get("of.general.smart");
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.DROPPED_ITEMS) {
         switch(this.ofDroppedItems) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         default:
            return var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.RAIN) {
         switch(this.ofRain) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         case 3:
            return var2 + Lang.getOff();
         default:
            return var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_WATER) {
         switch(this.ofAnimatedWater) {
         case 1:
            return var2 + Lang.get("of.options.animation.dynamic");
         case 2:
            return var2 + Lang.getOff();
         default:
            return var2 + Lang.getOn();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_LAVA) {
         switch(this.ofAnimatedLava) {
         case 1:
            return var2 + Lang.get("of.options.animation.dynamic");
         case 2:
            return var2 + Lang.getOff();
         default:
            return var2 + Lang.getOn();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_FIRE) {
         return this.ofAnimatedFire ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_PORTAL) {
         return this.ofAnimatedPortal ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_REDSTONE) {
         return this.ofAnimatedRedstone ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_EXPLOSION) {
         return this.ofAnimatedExplosion ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_FLAME) {
         return this.ofAnimatedFlame ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_SMOKE) {
         return this.ofAnimatedSmoke ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.VOID_PARTICLES) {
         return this.ofVoidParticles ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.WATER_PARTICLES) {
         return this.ofWaterParticles ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.PORTAL_PARTICLES) {
         return this.ofPortalParticles ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.POTION_PARTICLES) {
         return this.ofPotionParticles ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FIREWORK_PARTICLES) {
         return this.ofFireworkParticles ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.DRIPPING_WATER_LAVA) {
         return this.ofDrippingWaterLava ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_TERRAIN) {
         return this.ofAnimatedTerrain ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ANIMATED_TEXTURES) {
         return this.ofAnimatedTextures ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.RAIN_SPLASH) {
         return this.ofRainSplash ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.LAGOMETER) {
         return this.ofLagometer ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SHOW_FPS) {
         return this.ofShowFps ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.AUTOSAVE_TICKS) {
         int step = 900;
         if (this.ofAutoSaveTicks <= step) {
            return var2 + Lang.get("of.options.save.45s");
         } else if (this.ofAutoSaveTicks <= 2 * step) {
            return var2 + Lang.get("of.options.save.90s");
         } else if (this.ofAutoSaveTicks <= 4 * step) {
            return var2 + Lang.get("of.options.save.3min");
         } else if (this.ofAutoSaveTicks <= 8 * step) {
            return var2 + Lang.get("of.options.save.6min");
         } else {
            return this.ofAutoSaveTicks <= 16 * step ? var2 + Lang.get("of.options.save.12min") : var2 + Lang.get("of.options.save.24min");
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.BETTER_GRASS) {
         switch(this.ofBetterGrass) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         default:
            return var2 + Lang.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CONNECTED_TEXTURES) {
         switch(this.ofConnectedTextures) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         default:
            return var2 + Lang.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.WEATHER) {
         return this.ofWeather ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SKY) {
         return this.ofSky ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.STARS) {
         return this.ofStars ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SUN_MOON) {
         return this.ofSunMoon ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.VIGNETTE) {
         switch(this.ofVignette) {
         case 1:
            return var2 + Lang.getFast();
         case 2:
            return var2 + Lang.getFancy();
         default:
            return var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CHUNK_UPDATES) {
         return var2 + this.ofChunkUpdates;
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         return this.ofChunkUpdatesDynamic ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.TIME) {
         if (this.ofTime == 1) {
            return var2 + Lang.get("of.options.time.dayOnly");
         } else {
            return this.ofTime == 2 ? var2 + Lang.get("of.options.time.nightOnly") : var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CLEAR_WATER) {
         return this.ofClearWater ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.AA_LEVEL) {
         String suffix = "";
         if (this.ofAaLevel != Config.getAntialiasingLevel()) {
            suffix = " (" + Lang.get("of.general.restart") + ")";
         }

         return this.ofAaLevel == 0 ? var2 + Lang.getOff() + suffix : var2 + this.ofAaLevel + suffix;
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.AF_LEVEL) {
         return this.ofAfLevel == 1 ? var2 + Lang.getOff() : var2 + this.ofAfLevel;
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.PROFILER) {
         return this.ofProfiler ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.BETTER_SNOW) {
         return this.ofBetterSnow ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SWAMP_COLORS) {
         return this.ofSwampColors ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.RANDOM_ENTITIES) {
         return this.ofRandomEntities ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SMOOTH_BIOMES) {
         return this.ofSmoothBiomes ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_FONTS) {
         return this.ofCustomFonts ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_COLORS) {
         return this.ofCustomColors ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_SKY) {
         return this.ofCustomSky ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SHOW_CAPES) {
         return this.ofShowCapes ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_ITEMS) {
         return this.ofCustomItems ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.NATURAL_TEXTURES) {
         return this.ofNaturalTextures ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.EMISSIVE_TEXTURES) {
         return this.ofEmissiveTextures ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FAST_MATH) {
         return this.ofFastMath ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FAST_RENDER) {
         return this.ofFastRender ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         if (this.ofTranslucentBlocks == 1) {
            return var2 + Lang.getFast();
         } else {
            return this.ofTranslucentBlocks == 2 ? var2 + Lang.getFancy() : var2 + Lang.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.LAZY_CHUNK_LOADING) {
         return this.ofLazyChunkLoading ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.RENDER_REGIONS) {
         return this.ofRenderRegions ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SMART_ANIMATIONS) {
         return this.ofSmartAnimations ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.DYNAMIC_FOV) {
         return this.ofDynamicFov ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ALTERNATE_BLOCKS) {
         return this.ofAlternateBlocks ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.DYNAMIC_LIGHTS) {
         index = indexOf(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         return var2 + func_74299_a(KEYS_DYNAMIC_LIGHTS, index);
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SCREENSHOT_SIZE) {
         return this.ofScreenshotSize <= 1 ? var2 + Lang.getDefault() : var2 + this.ofScreenshotSize + "x";
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_ENTITY_MODELS) {
         return this.ofCustomEntityModels ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.CUSTOM_GUIS) {
         return this.ofCustomGuis ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.SHOW_GL_ERRORS) {
         return this.ofShowGlErrors ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FULLSCREEN_MODE) {
         return this.ofFullscreenMode.equals("Default") ? var2 + Lang.getDefault() : var2 + this.ofFullscreenMode;
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         return this.field_92117_D ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.ADVANCED_TOOLTIPS) {
         return this.field_82882_x ? var2 + Lang.getOn() : var2 + Lang.getOff();
      } else if (p_getKeyBindingOF_1_ == GameSettings.Options.FRAMERATE_LIMIT) {
         float var6 = this.func_74296_a(p_getKeyBindingOF_1_);
         if (var6 == 0.0F) {
            return var2 + Lang.get("of.options.framerateLimit.vsync");
         } else {
            return var6 == p_getKeyBindingOF_1_.$VALUES ? var2 + I18n.func_135052_a("options.framerateLimit.max") : var2 + (int)var6 + " fps";
         }
      } else {
         return null;
      }
   }

   public void loadOfOptions() {
      try {
         File ofReadFile = this.optionsFileOF;
         if (!ofReadFile.exists()) {
            ofReadFile = this.bc;
         }

         if (!ofReadFile.exists()) {
            return;
         }

         BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(ofReadFile), "UTF-8"));
         String s = "";

         while((s = bufferedreader.readLine()) != null) {
            try {
               String[] as = s.split(":");
               if (as[0].equals("ofRenderDistanceChunks") && as.length >= 2) {
                  this.field_151451_c = Integer.valueOf(as[1]);
                  this.field_151451_c = Config.limit(this.field_151451_c, 2, 1024);
               }

               if (as[0].equals("ofFogType") && as.length >= 2) {
                  this.ofFogType = Integer.valueOf(as[1]);
                  this.ofFogType = Config.limit(this.ofFogType, 1, 3);
               }

               if (as[0].equals("ofFogStart") && as.length >= 2) {
                  this.ofFogStart = Float.valueOf(as[1]);
                  if (this.ofFogStart < 0.2F) {
                     this.ofFogStart = 0.2F;
                  }

                  if (this.ofFogStart > 0.81F) {
                     this.ofFogStart = 0.8F;
                  }
               }

               if (as[0].equals("ofMipmapType") && as.length >= 2) {
                  this.ofMipmapType = Integer.valueOf(as[1]);
                  this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
               }

               if (as[0].equals("ofOcclusionFancy") && as.length >= 2) {
                  this.ofOcclusionFancy = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSmoothFps") && as.length >= 2) {
                  this.ofSmoothFps = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSmoothWorld") && as.length >= 2) {
                  this.ofSmoothWorld = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAoLevel") && as.length >= 2) {
                  this.ofAoLevel = Float.valueOf(as[1]);
                  this.ofAoLevel = Config.limit(this.ofAoLevel, 0.0F, 1.0F);
               }

               if (as[0].equals("ofClouds") && as.length >= 2) {
                  this.ofClouds = Integer.valueOf(as[1]);
                  this.ofClouds = Config.limit(this.ofClouds, 0, 3);
                  this.updateRenderClouds();
               }

               if (as[0].equals("ofCloudsHeight") && as.length >= 2) {
                  this.ofCloudsHeight = Float.valueOf(as[1]);
                  this.ofCloudsHeight = Config.limit(this.ofCloudsHeight, 0.0F, 1.0F);
               }

               if (as[0].equals("ofTrees") && as.length >= 2) {
                  this.ofTrees = Integer.valueOf(as[1]);
                  this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
               }

               if (as[0].equals("ofDroppedItems") && as.length >= 2) {
                  this.ofDroppedItems = Integer.valueOf(as[1]);
                  this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
               }

               if (as[0].equals("ofRain") && as.length >= 2) {
                  this.ofRain = Integer.valueOf(as[1]);
                  this.ofRain = Config.limit(this.ofRain, 0, 3);
               }

               if (as[0].equals("ofAnimatedWater") && as.length >= 2) {
                  this.ofAnimatedWater = Integer.valueOf(as[1]);
                  this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
               }

               if (as[0].equals("ofAnimatedLava") && as.length >= 2) {
                  this.ofAnimatedLava = Integer.valueOf(as[1]);
                  this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
               }

               if (as[0].equals("ofAnimatedFire") && as.length >= 2) {
                  this.ofAnimatedFire = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedPortal") && as.length >= 2) {
                  this.ofAnimatedPortal = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedRedstone") && as.length >= 2) {
                  this.ofAnimatedRedstone = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedExplosion") && as.length >= 2) {
                  this.ofAnimatedExplosion = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedFlame") && as.length >= 2) {
                  this.ofAnimatedFlame = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedSmoke") && as.length >= 2) {
                  this.ofAnimatedSmoke = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofVoidParticles") && as.length >= 2) {
                  this.ofVoidParticles = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofWaterParticles") && as.length >= 2) {
                  this.ofWaterParticles = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofPortalParticles") && as.length >= 2) {
                  this.ofPortalParticles = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofPotionParticles") && as.length >= 2) {
                  this.ofPotionParticles = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofFireworkParticles") && as.length >= 2) {
                  this.ofFireworkParticles = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofDrippingWaterLava") && as.length >= 2) {
                  this.ofDrippingWaterLava = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedTerrain") && as.length >= 2) {
                  this.ofAnimatedTerrain = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAnimatedTextures") && as.length >= 2) {
                  this.ofAnimatedTextures = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofRainSplash") && as.length >= 2) {
                  this.ofRainSplash = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofLagometer") && as.length >= 2) {
                  this.ofLagometer = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofShowFps") && as.length >= 2) {
                  this.ofShowFps = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAutoSaveTicks") && as.length >= 2) {
                  this.ofAutoSaveTicks = Integer.valueOf(as[1]);
                  this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
               }

               if (as[0].equals("ofBetterGrass") && as.length >= 2) {
                  this.ofBetterGrass = Integer.valueOf(as[1]);
                  this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
               }

               if (as[0].equals("ofConnectedTextures") && as.length >= 2) {
                  this.ofConnectedTextures = Integer.valueOf(as[1]);
                  this.ofConnectedTextures = Config.limit(this.ofConnectedTextures, 1, 3);
               }

               if (as[0].equals("ofWeather") && as.length >= 2) {
                  this.ofWeather = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSky") && as.length >= 2) {
                  this.ofSky = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofStars") && as.length >= 2) {
                  this.ofStars = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSunMoon") && as.length >= 2) {
                  this.ofSunMoon = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofVignette") && as.length >= 2) {
                  this.ofVignette = Integer.valueOf(as[1]);
                  this.ofVignette = Config.limit(this.ofVignette, 0, 2);
               }

               if (as[0].equals("ofChunkUpdates") && as.length >= 2) {
                  this.ofChunkUpdates = Integer.valueOf(as[1]);
                  this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
               }

               if (as[0].equals("ofChunkUpdatesDynamic") && as.length >= 2) {
                  this.ofChunkUpdatesDynamic = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofTime") && as.length >= 2) {
                  this.ofTime = Integer.valueOf(as[1]);
                  this.ofTime = Config.limit(this.ofTime, 0, 2);
               }

               if (as[0].equals("ofClearWater") && as.length >= 2) {
                  this.ofClearWater = Boolean.valueOf(as[1]);
                  this.updateWaterOpacity();
               }

               if (as[0].equals("ofAaLevel") && as.length >= 2) {
                  this.ofAaLevel = Integer.valueOf(as[1]);
                  this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
               }

               if (as[0].equals("ofAfLevel") && as.length >= 2) {
                  this.ofAfLevel = Integer.valueOf(as[1]);
                  this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
               }

               if (as[0].equals("ofProfiler") && as.length >= 2) {
                  this.ofProfiler = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofBetterSnow") && as.length >= 2) {
                  this.ofBetterSnow = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSwampColors") && as.length >= 2) {
                  this.ofSwampColors = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofRandomEntities") && as.length >= 2) {
                  this.ofRandomEntities = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSmoothBiomes") && as.length >= 2) {
                  this.ofSmoothBiomes = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofCustomFonts") && as.length >= 2) {
                  this.ofCustomFonts = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofCustomColors") && as.length >= 2) {
                  this.ofCustomColors = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofCustomItems") && as.length >= 2) {
                  this.ofCustomItems = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofCustomSky") && as.length >= 2) {
                  this.ofCustomSky = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofShowCapes") && as.length >= 2) {
                  this.ofShowCapes = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofNaturalTextures") && as.length >= 2) {
                  this.ofNaturalTextures = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofEmissiveTextures") && as.length >= 2) {
                  this.ofEmissiveTextures = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofLazyChunkLoading") && as.length >= 2) {
                  this.ofLazyChunkLoading = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofRenderRegions") && as.length >= 2) {
                  this.ofRenderRegions = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofSmartAnimations") && as.length >= 2) {
                  this.ofSmartAnimations = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofDynamicFov") && as.length >= 2) {
                  this.ofDynamicFov = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofAlternateBlocks") && as.length >= 2) {
                  this.ofAlternateBlocks = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofDynamicLights") && as.length >= 2) {
                  this.ofDynamicLights = Integer.valueOf(as[1]);
                  this.ofDynamicLights = limit(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
               }

               if (as[0].equals("ofScreenshotSize") && as.length >= 2) {
                  this.ofScreenshotSize = Integer.valueOf(as[1]);
                  this.ofScreenshotSize = Config.limit(this.ofScreenshotSize, 1, 4);
               }

               if (as[0].equals("ofCustomEntityModels") && as.length >= 2) {
                  this.ofCustomEntityModels = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofCustomGuis") && as.length >= 2) {
                  this.ofCustomGuis = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofShowGlErrors") && as.length >= 2) {
                  this.ofShowGlErrors = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofFullscreenMode") && as.length >= 2) {
                  this.ofFullscreenMode = as[1];
               }

               if (as[0].equals("ofFastMath") && as.length >= 2) {
                  this.ofFastMath = Boolean.valueOf(as[1]);
                  MathHelper.fastMath = this.ofFastMath;
               }

               if (as[0].equals("ofFastRender") && as.length >= 2) {
                  this.ofFastRender = Boolean.valueOf(as[1]);
               }

               if (as[0].equals("ofTranslucentBlocks") && as.length >= 2) {
                  this.ofTranslucentBlocks = Integer.valueOf(as[1]);
                  this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 0, 2);
               }

               if (as[0].equals("key_" + this.ofKeyBindZoom.func_151464_g())) {
                  this.ofKeyBindZoom.func_151462_b(Integer.parseInt(as[1]));
               }
            } catch (Exception var5) {
               Config.dbg("Skipping bad option: " + s);
               var5.printStackTrace();
            }
         }

         KeyUtils.fixKeyConflicts(this.field_74317_L, new KeyBinding[]{this.ofKeyBindZoom});
         KeyBinding.func_74508_b();
         bufferedreader.close();
      } catch (Exception var6) {
         Config.warn("Failed to load options");
         var6.printStackTrace();
      }

   }

   public void saveOfOptions() {
      try {
         PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFileOF), "UTF-8"));
         printwriter.println("ofFogType:" + this.ofFogType);
         printwriter.println("ofFogStart:" + this.ofFogStart);
         printwriter.println("ofMipmapType:" + this.ofMipmapType);
         printwriter.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
         printwriter.println("ofSmoothFps:" + this.ofSmoothFps);
         printwriter.println("ofSmoothWorld:" + this.ofSmoothWorld);
         printwriter.println("ofAoLevel:" + this.ofAoLevel);
         printwriter.println("ofClouds:" + this.ofClouds);
         printwriter.println("ofCloudsHeight:" + this.ofCloudsHeight);
         printwriter.println("ofTrees:" + this.ofTrees);
         printwriter.println("ofDroppedItems:" + this.ofDroppedItems);
         printwriter.println("ofRain:" + this.ofRain);
         printwriter.println("ofAnimatedWater:" + this.ofAnimatedWater);
         printwriter.println("ofAnimatedLava:" + this.ofAnimatedLava);
         printwriter.println("ofAnimatedFire:" + this.ofAnimatedFire);
         printwriter.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
         printwriter.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
         printwriter.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
         printwriter.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
         printwriter.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
         printwriter.println("ofVoidParticles:" + this.ofVoidParticles);
         printwriter.println("ofWaterParticles:" + this.ofWaterParticles);
         printwriter.println("ofPortalParticles:" + this.ofPortalParticles);
         printwriter.println("ofPotionParticles:" + this.ofPotionParticles);
         printwriter.println("ofFireworkParticles:" + this.ofFireworkParticles);
         printwriter.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
         printwriter.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
         printwriter.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
         printwriter.println("ofRainSplash:" + this.ofRainSplash);
         printwriter.println("ofLagometer:" + this.ofLagometer);
         printwriter.println("ofShowFps:" + this.ofShowFps);
         printwriter.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
         printwriter.println("ofBetterGrass:" + this.ofBetterGrass);
         printwriter.println("ofConnectedTextures:" + this.ofConnectedTextures);
         printwriter.println("ofWeather:" + this.ofWeather);
         printwriter.println("ofSky:" + this.ofSky);
         printwriter.println("ofStars:" + this.ofStars);
         printwriter.println("ofSunMoon:" + this.ofSunMoon);
         printwriter.println("ofVignette:" + this.ofVignette);
         printwriter.println("ofChunkUpdates:" + this.ofChunkUpdates);
         printwriter.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
         printwriter.println("ofTime:" + this.ofTime);
         printwriter.println("ofClearWater:" + this.ofClearWater);
         printwriter.println("ofAaLevel:" + this.ofAaLevel);
         printwriter.println("ofAfLevel:" + this.ofAfLevel);
         printwriter.println("ofProfiler:" + this.ofProfiler);
         printwriter.println("ofBetterSnow:" + this.ofBetterSnow);
         printwriter.println("ofSwampColors:" + this.ofSwampColors);
         printwriter.println("ofRandomEntities:" + this.ofRandomEntities);
         printwriter.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
         printwriter.println("ofCustomFonts:" + this.ofCustomFonts);
         printwriter.println("ofCustomColors:" + this.ofCustomColors);
         printwriter.println("ofCustomItems:" + this.ofCustomItems);
         printwriter.println("ofCustomSky:" + this.ofCustomSky);
         printwriter.println("ofShowCapes:" + this.ofShowCapes);
         printwriter.println("ofNaturalTextures:" + this.ofNaturalTextures);
         printwriter.println("ofEmissiveTextures:" + this.ofEmissiveTextures);
         printwriter.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
         printwriter.println("ofRenderRegions:" + this.ofRenderRegions);
         printwriter.println("ofSmartAnimations:" + this.ofSmartAnimations);
         printwriter.println("ofDynamicFov:" + this.ofDynamicFov);
         printwriter.println("ofAlternateBlocks:" + this.ofAlternateBlocks);
         printwriter.println("ofDynamicLights:" + this.ofDynamicLights);
         printwriter.println("ofScreenshotSize:" + this.ofScreenshotSize);
         printwriter.println("ofCustomEntityModels:" + this.ofCustomEntityModels);
         printwriter.println("ofCustomGuis:" + this.ofCustomGuis);
         printwriter.println("ofShowGlErrors:" + this.ofShowGlErrors);
         printwriter.println("ofFullscreenMode:" + this.ofFullscreenMode);
         printwriter.println("ofFastMath:" + this.ofFastMath);
         printwriter.println("ofFastRender:" + this.ofFastRender);
         printwriter.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
         printwriter.println("key_" + this.ofKeyBindZoom.func_151464_g() + ":" + this.ofKeyBindZoom.func_151463_i());
         printwriter.close();
      } catch (Exception var2) {
         Config.warn("Failed to save options");
         var2.printStackTrace();
      }

   }

   private void updateRenderClouds() {
      switch(this.ofClouds) {
      case 1:
         this.field_74345_l = 1;
         break;
      case 2:
         this.field_74345_l = 2;
         break;
      case 3:
         this.field_74345_l = 0;
         break;
      default:
         if (this.field_74347_j) {
            this.field_74345_l = 2;
         } else {
            this.field_74345_l = 1;
         }
      }

   }

   public void resetSettings() {
      this.field_151451_c = 8;
      this.field_74336_f = true;
      this.field_74337_g = false;
      this.field_74350_i = (int)GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
      this.field_74352_v = false;
      this.updateVSync();
      this.field_151442_I = 4;
      this.field_74347_j = true;
      this.field_74348_k = 2;
      this.field_74345_l = 2;
      this.field_74333_Y = 70.0F;
      this.field_151452_as = 0.0F;
      this.field_74362_aa = 0;
      this.field_74363_ab = 0;
      this.field_92117_D = true;
      this.field_178881_t = false;
      this.field_151454_ax = false;
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmartAnimations = false;
      this.ofSmoothFps = false;
      Config.updateAvailableProcessors();
      this.ofSmoothWorld = Config.isSingleProcessor();
      this.ofLazyChunkLoading = false;
      this.ofRenderRegions = false;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofScreenshotSize = 1;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofShowFps = false;
      this.ofProfiler = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomItems = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      Shaders.setShaderPack("OFF");
      Shaders.configAntialiasingLevel = 0;
      Shaders.uninit();
      Shaders.storeConfig();
      this.updateWaterOpacity();
      this.field_74318_M.func_110436_a();
      this.func_74303_b();
   }

   public void updateVSync() {
      Display.setVSyncEnabled(this.field_74352_v);
   }

   private void updateWaterOpacity() {
      if (Config.isIntegratedServerRunning()) {
         Config.waterOpacityChanged = true;
      }

      ClearWater.updateWaterOpacity(this, this.field_74318_M.field_71441_e);
   }

   public void setAllAnimations(boolean p_setAllAnimations_1_) {
      int animVal = p_setAllAnimations_1_ ? 0 : 2;
      this.ofAnimatedWater = animVal;
      this.ofAnimatedLava = animVal;
      this.ofAnimatedFire = p_setAllAnimations_1_;
      this.ofAnimatedPortal = p_setAllAnimations_1_;
      this.ofAnimatedRedstone = p_setAllAnimations_1_;
      this.ofAnimatedExplosion = p_setAllAnimations_1_;
      this.ofAnimatedFlame = p_setAllAnimations_1_;
      this.ofAnimatedSmoke = p_setAllAnimations_1_;
      this.ofVoidParticles = p_setAllAnimations_1_;
      this.ofWaterParticles = p_setAllAnimations_1_;
      this.ofRainSplash = p_setAllAnimations_1_;
      this.ofPortalParticles = p_setAllAnimations_1_;
      this.ofPotionParticles = p_setAllAnimations_1_;
      this.ofFireworkParticles = p_setAllAnimations_1_;
      this.field_74363_ab = p_setAllAnimations_1_ ? 0 : 2;
      this.ofDrippingWaterLava = p_setAllAnimations_1_;
      this.ofAnimatedTerrain = p_setAllAnimations_1_;
      this.ofAnimatedTextures = p_setAllAnimations_1_;
   }

   private static int nextValue(int p_nextValue_0_, int[] p_nextValue_1_) {
      int index = indexOf(p_nextValue_0_, p_nextValue_1_);
      if (index < 0) {
         return p_nextValue_1_[0];
      } else {
         ++index;
         if (index >= p_nextValue_1_.length) {
            index = 0;
         }

         return p_nextValue_1_[index];
      }
   }

   private static int limit(int p_limit_0_, int[] p_limit_1_) {
      int index = indexOf(p_limit_0_, p_limit_1_);
      return index < 0 ? p_limit_1_[0] : p_limit_0_;
   }

   private static int indexOf(int p_indexOf_0_, int[] p_indexOf_1_) {
      for(int i = 0; i < p_indexOf_1_.length; ++i) {
         if (p_indexOf_1_[i] == p_indexOf_0_) {
            return i;
         }
      }

      return -1;
   }

   public static enum Options {
      INVERT_MOUSE("options.invertMouse", false, true),
      SENSITIVITY("options.sensitivity", true, false),
      FOV("options.fov", true, false, 30.0F, 110.0F, 1.0F),
      GAMMA("options.gamma", true, false),
      SATURATION("options.saturation", true, false),
      RENDER_DISTANCE("options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
      VIEW_BOBBING("options.viewBobbing", false, true),
      ANAGLYPH("options.anaglyph", false, true),
      FRAMERATE_LIMIT("options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
      FBO_ENABLE("options.fboEnable", false, true),
      RENDER_CLOUDS("options.renderClouds", false, false),
      GRAPHICS("options.graphics", false, false),
      AMBIENT_OCCLUSION("options.ao", false, false),
      GUI_SCALE("options.guiScale", false, false),
      PARTICLES("options.particles", false, false),
      CHAT_VISIBILITY("options.chat.visibility", false, false),
      CHAT_COLOR("options.chat.color", false, true),
      CHAT_LINKS("options.chat.links", false, true),
      CHAT_OPACITY("options.chat.opacity", true, false),
      CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true),
      SNOOPER_ENABLED("options.snooper", false, true),
      USE_FULLSCREEN("options.fullscreen", false, true),
      ENABLE_VSYNC("options.vsync", false, true),
      USE_VBO("options.vbo", false, true),
      TOUCHSCREEN("options.touchscreen", false, true),
      CHAT_SCALE("options.chat.scale", true, false),
      CHAT_WIDTH("options.chat.width", true, false),
      CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
      CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false),
      MIPMAP_LEVELS("options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
      FORCE_UNICODE_FONT("options.forceUnicodeFont", false, true),
      STREAM_BYTES_PER_PIXEL("options.stream.bytesPerPixel", true, false),
      STREAM_VOLUME_MIC("options.stream.micVolumne", true, false),
      STREAM_VOLUME_SYSTEM("options.stream.systemVolume", true, false),
      STREAM_KBPS("options.stream.kbps", true, false),
      STREAM_FPS("options.stream.fps", true, false),
      STREAM_COMPRESSION("options.stream.compression", false, false),
      STREAM_SEND_METADATA("options.stream.sendMetadata", false, true),
      STREAM_CHAT_ENABLED("options.stream.chat.enabled", false, false),
      STREAM_CHAT_USER_FILTER("options.stream.chat.userFilter", false, false),
      STREAM_MIC_TOGGLE_BEHAVIOR("options.stream.micToggleBehavior", false, false),
      BLOCK_ALTERNATIVES("options.blockAlternatives", false, true),
      REDUCED_DEBUG_INFO("options.reducedDebugInfo", false, true),
      ENTITY_SHADOWS("options.entityShadows", false, true),
      field_74385_A("options.realmsNotifications", false, true),
      FOG_FANCY("of.options.FOG_FANCY", false, false),
      FOG_START("of.options.FOG_START", false, false),
      MIPMAP_TYPE("of.options.MIPMAP_TYPE", true, false, 0.0F, 3.0F, 1.0F),
      SMOOTH_FPS("of.options.SMOOTH_FPS", false, false),
      CLOUDS("of.options.CLOUDS", false, false),
      CLOUD_HEIGHT("of.options.CLOUD_HEIGHT", true, false),
      TREES("of.options.TREES", false, false),
      RAIN("of.options.RAIN", false, false),
      ANIMATED_WATER("of.options.ANIMATED_WATER", false, false),
      ANIMATED_LAVA("of.options.ANIMATED_LAVA", false, false),
      ANIMATED_FIRE("of.options.ANIMATED_FIRE", false, false),
      ANIMATED_PORTAL("of.options.ANIMATED_PORTAL", false, false),
      AO_LEVEL("of.options.AO_LEVEL", true, false),
      LAGOMETER("of.options.LAGOMETER", false, false),
      SHOW_FPS("of.options.SHOW_FPS", false, false),
      AUTOSAVE_TICKS("of.options.AUTOSAVE_TICKS", false, false),
      BETTER_GRASS("of.options.BETTER_GRASS", false, false),
      ANIMATED_REDSTONE("of.options.ANIMATED_REDSTONE", false, false),
      ANIMATED_EXPLOSION("of.options.ANIMATED_EXPLOSION", false, false),
      ANIMATED_FLAME("of.options.ANIMATED_FLAME", false, false),
      ANIMATED_SMOKE("of.options.ANIMATED_SMOKE", false, false),
      WEATHER("of.options.WEATHER", false, false),
      SKY("of.options.SKY", false, false),
      STARS("of.options.STARS", false, false),
      SUN_MOON("of.options.SUN_MOON", false, false),
      VIGNETTE("of.options.VIGNETTE", false, false),
      CHUNK_UPDATES("of.options.CHUNK_UPDATES", false, false),
      CHUNK_UPDATES_DYNAMIC("of.options.CHUNK_UPDATES_DYNAMIC", false, false),
      TIME("of.options.TIME", false, false),
      CLEAR_WATER("of.options.CLEAR_WATER", false, false),
      SMOOTH_WORLD("of.options.SMOOTH_WORLD", false, false),
      VOID_PARTICLES("of.options.VOID_PARTICLES", false, false),
      WATER_PARTICLES("of.options.WATER_PARTICLES", false, false),
      RAIN_SPLASH("of.options.RAIN_SPLASH", false, false),
      PORTAL_PARTICLES("of.options.PORTAL_PARTICLES", false, false),
      POTION_PARTICLES("of.options.POTION_PARTICLES", false, false),
      FIREWORK_PARTICLES("of.options.FIREWORK_PARTICLES", false, false),
      PROFILER("of.options.PROFILER", false, false),
      DRIPPING_WATER_LAVA("of.options.DRIPPING_WATER_LAVA", false, false),
      BETTER_SNOW("of.options.BETTER_SNOW", false, false),
      FULLSCREEN_MODE("of.options.FULLSCREEN_MODE", true, false, 0.0F, (float)Config.getDisplayModes().length, 1.0F),
      ANIMATED_TERRAIN("of.options.ANIMATED_TERRAIN", false, false),
      SWAMP_COLORS("of.options.SWAMP_COLORS", false, false),
      RANDOM_ENTITIES("of.options.RANDOM_ENTITIES", false, false),
      SMOOTH_BIOMES("of.options.SMOOTH_BIOMES", false, false),
      CUSTOM_FONTS("of.options.CUSTOM_FONTS", false, false),
      CUSTOM_COLORS("of.options.CUSTOM_COLORS", false, false),
      SHOW_CAPES("of.options.SHOW_CAPES", false, false),
      CONNECTED_TEXTURES("of.options.CONNECTED_TEXTURES", false, false),
      CUSTOM_ITEMS("of.options.CUSTOM_ITEMS", false, false),
      AA_LEVEL("of.options.AA_LEVEL", true, false, 0.0F, 16.0F, 1.0F),
      AF_LEVEL("of.options.AF_LEVEL", true, false, 1.0F, 16.0F, 1.0F),
      ANIMATED_TEXTURES("of.options.ANIMATED_TEXTURES", false, false),
      NATURAL_TEXTURES("of.options.NATURAL_TEXTURES", false, false),
      EMISSIVE_TEXTURES("of.options.EMISSIVE_TEXTURES", false, false),
      HELD_ITEM_TOOLTIPS("of.options.HELD_ITEM_TOOLTIPS", false, false),
      DROPPED_ITEMS("of.options.DROPPED_ITEMS", false, false),
      LAZY_CHUNK_LOADING("of.options.LAZY_CHUNK_LOADING", false, false),
      CUSTOM_SKY("of.options.CUSTOM_SKY", false, false),
      FAST_MATH("of.options.FAST_MATH", false, false),
      FAST_RENDER("of.options.FAST_RENDER", false, false),
      TRANSLUCENT_BLOCKS("of.options.TRANSLUCENT_BLOCKS", false, false),
      DYNAMIC_FOV("of.options.DYNAMIC_FOV", false, false),
      DYNAMIC_LIGHTS("of.options.DYNAMIC_LIGHTS", false, false),
      ALTERNATE_BLOCKS("of.options.ALTERNATE_BLOCKS", false, false),
      CUSTOM_ENTITY_MODELS("of.options.CUSTOM_ENTITY_MODELS", false, false),
      ADVANCED_TOOLTIPS("of.options.ADVANCED_TOOLTIPS", false, false),
      SCREENSHOT_SIZE("of.options.SCREENSHOT_SIZE", false, false),
      CUSTOM_GUIS("of.options.CUSTOM_GUIS", false, false),
      RENDER_REGIONS("of.options.RENDER_REGIONS", false, false),
      SHOW_GL_ERRORS("of.options.SHOW_GL_ERRORS", false, false),
      SMART_ANIMATIONS("of.options.SMART_ANIMATIONS", false, false);

      private final boolean field_74386_B;
      private final boolean field_74387_C;
      private final String field_148270_M;
      private final float field_148271_N;
      private float field_148272_O;
      private float $VALUES;

      public static GameSettings.Options func_74379_a(int p_74379_0_) {
         GameSettings.Options[] arr$ = values();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            GameSettings.Options gamesettings$options = arr$[i$];
            if (gamesettings$options.func_74381_c() == p_74379_0_) {
               return gamesettings$options;
            }
         }

         return null;
      }

      private Options(String p_i1015_3_, boolean p_i1015_4_, boolean p_i1015_5_) {
         this(p_i1015_3_, p_i1015_4_, p_i1015_5_, 0.0F, 1.0F, 0.0F);
      }

      private Options(String p_i45004_3_, boolean p_i45004_4_, boolean p_i45004_5_, float p_i45004_6_, float p_i45004_7_, float p_i45004_8_) {
         this.field_148270_M = p_i45004_3_;
         this.field_74386_B = p_i45004_4_;
         this.field_74387_C = p_i45004_5_;
         this.field_148272_O = p_i45004_6_;
         this.$VALUES = p_i45004_7_;
         this.field_148271_N = p_i45004_8_;
      }

      public boolean func_74380_a() {
         return this.field_74386_B;
      }

      public boolean func_74382_b() {
         return this.field_74387_C;
      }

      public int func_74381_c() {
         return this.ordinal();
      }

      public String func_74378_d() {
         return this.field_148270_M;
      }

      public float func_148267_f() {
         return this.$VALUES;
      }

      public void func_148263_a(float p_148263_1_) {
         this.$VALUES = p_148263_1_;
      }

      public float func_148266_c(float p_148266_1_) {
         return MathHelper.func_76131_a((this.func_148268_e(p_148266_1_) - this.field_148272_O) / (this.$VALUES - this.field_148272_O), 0.0F, 1.0F);
      }

      public float func_148262_d(float p_148262_1_) {
         return this.func_148268_e(this.field_148272_O + (this.$VALUES - this.field_148272_O) * MathHelper.func_76131_a(p_148262_1_, 0.0F, 1.0F));
      }

      public float func_148268_e(float p_148268_1_) {
         p_148268_1_ = this.func_148264_f(p_148268_1_);
         return MathHelper.func_76131_a(p_148268_1_, this.field_148272_O, this.$VALUES);
      }

      protected float func_148264_f(float p_148264_1_) {
         if (this.field_148271_N > 0.0F) {
            p_148264_1_ = this.field_148271_N * (float)Math.round(p_148264_1_ / this.field_148271_N);
         }

         return p_148264_1_;
      }
   }
}
