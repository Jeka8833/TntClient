package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.CustomItems;
import net.optifine.EmissiveTextures;
import net.optifine.SmartAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.ShadersTex;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureMap extends AbstractTexture implements ITickableTextureObject {
   private static final boolean ENABLE_SKIP = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
   private static final Logger field_147635_d = LogManager.getLogger();
   public static final ResourceLocation field_174945_f = new ResourceLocation("missingno");
   public static final ResourceLocation field_110575_b = new ResourceLocation("textures/atlas/blocks.png");
   private final List<TextureAtlasSprite> field_94258_i;
   private final Map<String, TextureAtlasSprite> field_110574_e;
   private final Map<String, TextureAtlasSprite> field_94252_e;
   private final String field_94254_c;
   private final IIconCreator field_174946_m;
   private int field_147636_j;
   private final TextureAtlasSprite field_94249_f;
   private boolean skipFirst;
   private TextureAtlasSprite[] iconGrid;
   private int iconGridSize;
   private int iconGridCountX;
   private int iconGridCountY;
   private double iconGridSizeU;
   private double iconGridSizeV;
   private CounterInt counterIndexInMap;
   public int atlasWidth;
   public int atlasHeight;
   private int countAnimationsActive;
   private int frameCountAnimations;

   public TextureMap(String p_i46099_1_) {
      this(p_i46099_1_, (IIconCreator)null);
   }

   public TextureMap(String p_i5_1_, boolean p_i5_2_) {
      this(p_i5_1_, (IIconCreator)null, p_i5_2_);
   }

   public TextureMap(String p_i46100_1_, IIconCreator p_i46100_2_) {
      this(p_i46100_1_, p_i46100_2_, false);
   }

   public TextureMap(String p_i6_1_, IIconCreator p_i6_2_, boolean p_i6_3_) {
      this.skipFirst = false;
      this.iconGrid = null;
      this.iconGridSize = -1;
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGridSizeU = -1.0D;
      this.iconGridSizeV = -1.0D;
      this.counterIndexInMap = new CounterInt(0);
      this.atlasWidth = 0;
      this.atlasHeight = 0;
      this.field_94258_i = Lists.newArrayList();
      this.field_110574_e = Maps.newHashMap();
      this.field_94252_e = Maps.newHashMap();
      this.field_94249_f = new TextureAtlasSprite("missingno");
      this.field_94254_c = p_i6_1_;
      this.field_174946_m = p_i6_2_;
      this.skipFirst = p_i6_3_ && ENABLE_SKIP;
   }

   private void func_110569_e() {
      int size = this.getMinSpriteSize();
      int[] aint = this.getMissingImageData(size);
      this.field_94249_f.func_110966_b(size);
      this.field_94249_f.func_110969_c(size);
      int[][] aint1 = new int[this.field_147636_j + 1][];
      aint1[0] = aint;
      this.field_94249_f.func_110968_a(Lists.newArrayList((Object[])(aint1)));
      this.field_94249_f.setIndexInMap(this.counterIndexInMap.nextValue());
   }

   public void func_110551_a(IResourceManager p_110551_1_) throws IOException {
      ShadersTex.resManager = p_110551_1_;
      if (this.field_174946_m != null) {
         this.func_174943_a(p_110551_1_, this.field_174946_m);
      }

   }

   public void func_174943_a(IResourceManager p_174943_1_, IIconCreator p_174943_2_) {
      this.field_110574_e.clear();
      this.counterIndexInMap.reset();
      p_174943_2_.func_177059_a(this);
      if (this.field_147636_j >= 4) {
         this.field_147636_j = this.detectMaxMipmapLevel(this.field_110574_e, p_174943_1_);
         Config.log("Mipmap levels: " + this.field_147636_j);
      }

      this.func_110569_e();
      this.func_147631_c();
      this.func_110571_b(p_174943_1_);
   }

   public void func_110571_b(IResourceManager p_110571_1_) {
      ShadersTex.resManager = p_110571_1_;
      Config.dbg("Multitexture: " + Config.isMultiTexture());
      if (Config.isMultiTexture()) {
         Iterator it = this.field_94252_e.values().iterator();

         while(it.hasNext()) {
            TextureAtlasSprite ts = (TextureAtlasSprite)it.next();
            ts.deleteSpriteTexture();
         }
      }

      ConnectedTextures.updateIcons(this);
      CustomItems.updateIcons(this);
      BetterGrass.updateIcons(this);
      int i = TextureUtils.getGLMaximumTextureSize();
      Stitcher stitcher = new Stitcher(i, i, true, 0, this.field_147636_j);
      this.field_94252_e.clear();
      this.field_94258_i.clear();
      int j = Integer.MAX_VALUE;
      Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, this);
      int minSpriteSize = this.getMinSpriteSize();
      this.iconGridSize = minSpriteSize;
      int k = 1 << this.field_147636_j;
      int countCustomLoader = 0;
      int countCustomLoaderSkipped = 0;
      Iterator i$ = this.field_110574_e.entrySet().iterator();

      while(true) {
         if (i$.hasNext()) {
            Entry<String, TextureAtlasSprite> entry = (Entry)i$.next();
            if (!this.skipFirst) {
               TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)entry.getValue();
               ResourceLocation resourcelocation = new ResourceLocation(textureatlassprite.func_94215_i());
               ResourceLocation resourcelocation1 = this.func_147634_a(resourcelocation, 0);
               textureatlassprite.updateIndexInMap(this.counterIndexInMap);
               if (textureatlassprite.hasCustomLoader(p_110571_1_, resourcelocation)) {
                  if (!textureatlassprite.load(p_110571_1_, resourcelocation)) {
                     j = Math.min(j, Math.min(textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b()));
                     stitcher.func_110934_a(textureatlassprite);
                     Config.detail("Custom loader (skipped): " + textureatlassprite);
                     ++countCustomLoaderSkipped;
                  }

                  Config.detail("Custom loader: " + textureatlassprite);
                  ++countCustomLoader;
                  continue;
               }

               try {
                  IResource iresource = p_110571_1_.func_110536_a(resourcelocation1);
                  BufferedImage[] abufferedimage = new BufferedImage[1 + this.field_147636_j];
                  abufferedimage[0] = TextureUtil.func_177053_a(iresource.func_110527_b());
                  int ws = abufferedimage[0].getWidth();
                  int hs = abufferedimage[0].getHeight();
                  if (ws < 1 || hs < 1) {
                     Config.warn("Invalid sprite size: " + textureatlassprite);
                     continue;
                  }

                  if (ws < minSpriteSize || this.field_147636_j > 0) {
                     int ws2 = this.field_147636_j > 0 ? TextureUtils.scaleToGrid(ws, minSpriteSize) : TextureUtils.scaleToMin(ws, minSpriteSize);
                     if (ws2 != ws) {
                        if (!TextureUtils.isPowerOfTwo(ws)) {
                           Config.log("Scaled non power of 2: " + textureatlassprite.func_94215_i() + ", " + ws + " -> " + ws2);
                        } else {
                           Config.log("Scaled too small texture: " + textureatlassprite.func_94215_i() + ", " + ws + " -> " + ws2);
                        }

                        int hs2 = hs * ws2 / ws;
                        abufferedimage[0] = TextureUtils.scaleImage(abufferedimage[0], ws2);
                     }
                  }

                  TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.func_110526_a("texture");
                  if (texturemetadatasection != null) {
                     List<Integer> list = texturemetadatasection.func_148535_c();
                     int i2;
                     if (!list.isEmpty()) {
                        int l = abufferedimage[0].getWidth();
                        i2 = abufferedimage[0].getHeight();
                        if (MathHelper.func_151236_b(l) != l || MathHelper.func_151236_b(i2) != i2) {
                           throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                     }

                     Iterator iterator = list.iterator();

                     while(iterator.hasNext()) {
                        i2 = (Integer)iterator.next();
                        if (i2 > 0 && i2 < abufferedimage.length - 1 && abufferedimage[i2] == null) {
                           ResourceLocation resourcelocation2 = this.func_147634_a(resourcelocation, i2);

                           try {
                              abufferedimage[i2] = TextureUtil.func_177053_a(p_110571_1_.func_110536_a(resourcelocation2).func_110527_b());
                           } catch (IOException var29) {
                              field_147635_d.error("Unable to load miplevel {} from: {}", i2, resourcelocation2, var29);
                           }
                        }
                     }
                  }

                  AnimationMetadataSection animationmetadatasection = (AnimationMetadataSection)iresource.func_110526_a("animation");
                  textureatlassprite.func_180598_a(abufferedimage, animationmetadatasection);
               } catch (RuntimeException var30) {
                  field_147635_d.error((String)("Unable to parse metadata from " + resourcelocation1), (Throwable)var30);
                  ReflectorForge.FMLClientHandler_trackBrokenTexture(resourcelocation1, var30.getMessage());
                  continue;
               } catch (IOException var31) {
                  field_147635_d.error("Using missing texture, unable to load " + resourcelocation1 + ", " + var31.getClass().getName());
                  ReflectorForge.FMLClientHandler_trackMissingTexture(resourcelocation1);
                  continue;
               }

               j = Math.min(j, Math.min(textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b()));
               int l1 = Math.min(Integer.lowestOneBit(textureatlassprite.func_94211_a()), Integer.lowestOneBit(textureatlassprite.func_94216_b()));
               if (l1 < k) {
                  field_147635_d.warn("Texture {} with size {}x{} limits mip level from {} to {}", resourcelocation1, textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b(), MathHelper.func_151239_c(k), MathHelper.func_151239_c(l1));
                  k = l1;
               }

               stitcher.func_110934_a(textureatlassprite);
               continue;
            }
         }

         if (countCustomLoader > 0) {
            Config.dbg("Custom loader sprites: " + countCustomLoader);
         }

         if (countCustomLoaderSkipped > 0) {
            Config.dbg("Custom loader sprites (skipped): " + countCustomLoaderSkipped);
         }

         int j1 = Math.min(j, k);
         int k1 = MathHelper.func_151239_c(j1);
         if (k1 < 0) {
            k1 = 0;
         }

         if (k1 < this.field_147636_j) {
            field_147635_d.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", this.field_94254_c, this.field_147636_j, k1, j1);
            this.field_147636_j = k1;
         }

         Iterator i$ = this.field_110574_e.values().iterator();

         while(i$.hasNext()) {
            final TextureAtlasSprite textureatlassprite1 = (TextureAtlasSprite)i$.next();
            if (this.skipFirst) {
               break;
            }

            try {
               textureatlassprite1.func_147963_d(this.field_147636_j);
            } catch (Throwable var28) {
               CrashReport crashreport = CrashReport.func_85055_a(var28, "Applying mipmap");
               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Sprite being mipmapped");
               crashreportcategory.func_71500_a("Sprite name", new Callable<String>() {
                  public String call() throws Exception {
                     return textureatlassprite1.func_94215_i();
                  }
               });
               crashreportcategory.func_71500_a("Sprite size", new Callable<String>() {
                  public String call() throws Exception {
                     return textureatlassprite1.func_94211_a() + " x " + textureatlassprite1.func_94216_b();
                  }
               });
               crashreportcategory.func_71500_a("Sprite frames", new Callable<String>() {
                  public String call() throws Exception {
                     return textureatlassprite1.func_110970_k() + " frames";
                  }
               });
               crashreportcategory.func_71507_a("Mipmap levels", this.field_147636_j);
               throw new ReportedException(crashreport);
            }
         }

         this.field_94249_f.func_147963_d(this.field_147636_j);
         stitcher.func_110934_a(this.field_94249_f);
         this.skipFirst = false;

         try {
            stitcher.func_94305_f();
         } catch (StitcherException var27) {
            throw var27;
         }

         field_147635_d.info("Created: {}x{} {}-atlas", stitcher.func_110935_a(), stitcher.func_110936_b(), this.field_94254_c);
         if (Config.isShaders()) {
            ShadersTex.allocateTextureMap(this.func_110552_b(), this.field_147636_j, stitcher.func_110935_a(), stitcher.func_110936_b(), stitcher, this);
         } else {
            TextureUtil.func_180600_a(this.func_110552_b(), this.field_147636_j, stitcher.func_110935_a(), stitcher.func_110936_b());
         }

         Map<String, TextureAtlasSprite> map = Maps.newHashMap(this.field_110574_e);
         Iterator i$ = stitcher.func_94309_g().iterator();

         TextureAtlasSprite textureatlassprite2;
         while(i$.hasNext()) {
            textureatlassprite2 = (TextureAtlasSprite)i$.next();
            if (Config.isShaders()) {
               ShadersTex.setIconName(ShadersTex.setSprite(textureatlassprite2).func_94215_i());
            }

            String s = textureatlassprite2.func_94215_i();
            map.remove(s);
            this.field_94252_e.put(s, textureatlassprite2);

            try {
               if (Config.isShaders()) {
                  ShadersTex.uploadTexSubForLoadAtlas(textureatlassprite2.func_147965_a(0), textureatlassprite2.func_94211_a(), textureatlassprite2.func_94216_b(), textureatlassprite2.func_130010_a(), textureatlassprite2.func_110967_i(), false, false);
               } else {
                  TextureUtil.func_147955_a(textureatlassprite2.func_147965_a(0), textureatlassprite2.func_94211_a(), textureatlassprite2.func_94216_b(), textureatlassprite2.func_130010_a(), textureatlassprite2.func_110967_i(), false, false);
               }
            } catch (Throwable var26) {
               CrashReport crashreport1 = CrashReport.func_85055_a(var26, "Stitching texture atlas");
               CrashReportCategory crashreportcategory1 = crashreport1.func_85058_a("Texture being stitched together");
               crashreportcategory1.func_71507_a("Atlas path", this.field_94254_c);
               crashreportcategory1.func_71507_a("Sprite", textureatlassprite2);
               throw new ReportedException(crashreport1);
            }

            if (textureatlassprite2.func_130098_m()) {
               textureatlassprite2.setAnimationIndex(this.field_94258_i.size());
               this.field_94258_i.add(textureatlassprite2);
            }
         }

         i$ = map.values().iterator();

         while(i$.hasNext()) {
            textureatlassprite2 = (TextureAtlasSprite)i$.next();
            textureatlassprite2.func_94217_a(this.field_94249_f);
         }

         Config.log("Animated sprites: " + this.field_94258_i.size());
         if (Config.isMultiTexture()) {
            int sheetWidth = stitcher.func_110935_a();
            int sheetHeight = stitcher.func_110936_b();
            List listSprites = stitcher.func_94309_g();
            Iterator it = listSprites.iterator();

            while(it.hasNext()) {
               TextureAtlasSprite tas = (TextureAtlasSprite)it.next();
               tas.sheetWidth = sheetWidth;
               tas.sheetHeight = sheetHeight;
               tas.mipmapLevels = this.field_147636_j;
               TextureAtlasSprite ss = tas.spriteSingle;
               if (ss != null) {
                  if (ss.func_94211_a() <= 0) {
                     ss.func_110966_b(tas.func_94211_a());
                     ss.func_110969_c(tas.func_94216_b());
                     ss.func_110971_a(tas.func_94211_a(), tas.func_94216_b(), 0, 0, false);
                     ss.func_130103_l();
                     List<int[][]> frameDatas = tas.getFramesTextureData();
                     ss.func_110968_a(frameDatas);
                     ss.setAnimationMetadata(tas.getAnimationMetadata());
                  }

                  ss.sheetWidth = sheetWidth;
                  ss.sheetHeight = sheetHeight;
                  ss.mipmapLevels = this.field_147636_j;
                  ss.setAnimationIndex(tas.getAnimationIndex());
                  tas.bindSpriteTexture();
                  boolean texBlur = false;
                  boolean texClamp = true;

                  try {
                     TextureUtil.func_147955_a(ss.func_147965_a(0), ss.func_94211_a(), ss.func_94216_b(), ss.func_130010_a(), ss.func_110967_i(), texBlur, texClamp);
                  } catch (Exception var25) {
                     Config.dbg("Error uploading sprite single: " + ss + ", parent: " + tas);
                     var25.printStackTrace();
                  }
               }
            }

            Config.getMinecraft().func_110434_K().func_110577_a(field_110575_b);
         }

         Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPost, this);
         this.updateIconGrid(stitcher.func_110935_a(), stitcher.func_110936_b());
         if (Config.equals(System.getProperty("saveTextureMap"), "true")) {
            Config.dbg("Exporting texture map: " + this.field_94254_c);
            TextureUtils.saveGlTexture("debug/" + this.field_94254_c.replaceAll("/", "_"), this.func_110552_b(), this.field_147636_j, stitcher.func_110935_a(), stitcher.func_110936_b());
         }

         return;
      }
   }

   public ResourceLocation completeResourceLocation(ResourceLocation p_completeResourceLocation_1_) {
      return this.func_147634_a(p_completeResourceLocation_1_, 0);
   }

   public ResourceLocation func_147634_a(ResourceLocation p_147634_1_, int p_147634_2_) {
      if (this.isAbsoluteLocation(p_147634_1_)) {
         return new ResourceLocation(p_147634_1_.func_110624_b(), p_147634_1_.func_110623_a() + ".png");
      } else {
         return p_147634_2_ == 0 ? new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/%s%s", this.field_94254_c, p_147634_1_.func_110623_a(), ".png")) : new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/mipmaps/%s.%d%s", this.field_94254_c, p_147634_1_.func_110623_a(), p_147634_2_, ".png"));
      }
   }

   public TextureAtlasSprite func_110572_b(String p_110572_1_) {
      TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)this.field_94252_e.get(p_110572_1_);
      if (textureatlassprite == null) {
         textureatlassprite = this.field_94249_f;
      }

      return textureatlassprite;
   }

   public void func_94248_c() {
      if (Config.isShaders()) {
         ShadersTex.updatingTex = this.getMultiTexID();
      }

      boolean hasNormal = false;
      boolean hasSpecular = false;
      TextureUtil.func_94277_a(this.func_110552_b());
      int countActive = 0;
      Iterator i$ = this.field_94258_i.iterator();

      TextureAtlasSprite textureatlassprite;
      while(i$.hasNext()) {
         textureatlassprite = (TextureAtlasSprite)i$.next();
         if (this.isTerrainAnimationActive(textureatlassprite)) {
            textureatlassprite.func_94219_l();
            if (textureatlassprite.isAnimationActive()) {
               ++countActive;
            }

            if (textureatlassprite.spriteNormal != null) {
               hasNormal = true;
            }

            if (textureatlassprite.spriteSpecular != null) {
               hasSpecular = true;
            }
         }
      }

      if (Config.isMultiTexture()) {
         i$ = this.field_94258_i.iterator();

         label148:
         while(true) {
            TextureAtlasSprite spriteSingle;
            do {
               do {
                  if (!i$.hasNext()) {
                     TextureUtil.func_94277_a(this.func_110552_b());
                     break label148;
                  }

                  textureatlassprite = (TextureAtlasSprite)i$.next();
               } while(!this.isTerrainAnimationActive(textureatlassprite));

               spriteSingle = textureatlassprite.spriteSingle;
            } while(spriteSingle == null);

            if (textureatlassprite == TextureUtils.iconClock || textureatlassprite == TextureUtils.iconCompass) {
               spriteSingle.field_110973_g = textureatlassprite.field_110973_g;
            }

            textureatlassprite.bindSpriteTexture();
            spriteSingle.func_94219_l();
            if (spriteSingle.isAnimationActive()) {
               ++countActive;
            }
         }
      }

      if (Config.isShaders()) {
         if (hasNormal) {
            TextureUtil.func_94277_a(this.getMultiTexID().norm);
            i$ = this.field_94258_i.iterator();

            label127:
            while(true) {
               do {
                  do {
                     if (!i$.hasNext()) {
                        break label127;
                     }

                     textureatlassprite = (TextureAtlasSprite)i$.next();
                  } while(textureatlassprite.spriteNormal == null);
               } while(!this.isTerrainAnimationActive(textureatlassprite));

               if (textureatlassprite == TextureUtils.iconClock || textureatlassprite == TextureUtils.iconCompass) {
                  textureatlassprite.spriteNormal.field_110973_g = textureatlassprite.field_110973_g;
               }

               textureatlassprite.spriteNormal.func_94219_l();
               if (textureatlassprite.spriteNormal.isAnimationActive()) {
                  ++countActive;
               }
            }
         }

         if (hasSpecular) {
            TextureUtil.func_94277_a(this.getMultiTexID().spec);
            i$ = this.field_94258_i.iterator();

            label106:
            while(true) {
               do {
                  do {
                     if (!i$.hasNext()) {
                        break label106;
                     }

                     textureatlassprite = (TextureAtlasSprite)i$.next();
                  } while(textureatlassprite.spriteSpecular == null);
               } while(!this.isTerrainAnimationActive(textureatlassprite));

               if (textureatlassprite == TextureUtils.iconClock || textureatlassprite == TextureUtils.iconCompass) {
                  textureatlassprite.spriteNormal.field_110973_g = textureatlassprite.field_110973_g;
               }

               textureatlassprite.spriteSpecular.func_94219_l();
               if (textureatlassprite.spriteSpecular.isAnimationActive()) {
                  ++countActive;
               }
            }
         }

         if (hasNormal || hasSpecular) {
            TextureUtil.func_94277_a(this.func_110552_b());
         }
      }

      int frameCount = Config.getMinecraft().field_71460_t.field_175084_ae;
      if (frameCount != this.frameCountAnimations) {
         this.countAnimationsActive = countActive;
         this.frameCountAnimations = frameCount;
      }

      if (SmartAnimations.isActive()) {
         SmartAnimations.resetSpritesRendered();
      }

      if (Config.isShaders()) {
         ShadersTex.updatingTex = null;
      }

   }

   public TextureAtlasSprite func_174942_a(ResourceLocation p_174942_1_) {
      if (p_174942_1_ == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)this.field_110574_e.get(p_174942_1_.toString());
         if (textureatlassprite == null) {
            textureatlassprite = TextureAtlasSprite.func_176604_a(p_174942_1_);
            this.field_110574_e.put(p_174942_1_.toString(), textureatlassprite);
            textureatlassprite.updateIndexInMap(this.counterIndexInMap);
            if (Config.isEmissiveTextures()) {
               this.checkEmissive(p_174942_1_, textureatlassprite);
            }
         }

         return textureatlassprite;
      }
   }

   public void func_110550_d() {
      this.func_94248_c();
   }

   public void func_147633_a(int p_147633_1_) {
      this.field_147636_j = p_147633_1_;
   }

   public TextureAtlasSprite func_174944_f() {
      return this.field_94249_f;
   }

   public TextureAtlasSprite getTextureExtry(String p_getTextureExtry_1_) {
      return (TextureAtlasSprite)this.field_110574_e.get(p_getTextureExtry_1_);
   }

   public boolean setTextureEntry(String p_setTextureEntry_1_, TextureAtlasSprite p_setTextureEntry_2_) {
      if (!this.field_110574_e.containsKey(p_setTextureEntry_1_)) {
         this.field_110574_e.put(p_setTextureEntry_1_, p_setTextureEntry_2_);
         p_setTextureEntry_2_.updateIndexInMap(this.counterIndexInMap);
         return true;
      } else {
         return false;
      }
   }

   public boolean setTextureEntry(TextureAtlasSprite p_setTextureEntry_1_) {
      return this.setTextureEntry(p_setTextureEntry_1_.func_94215_i(), p_setTextureEntry_1_);
   }

   public String getBasePath() {
      return this.field_94254_c;
   }

   public int getMipmapLevels() {
      return this.field_147636_j;
   }

   private boolean isAbsoluteLocation(ResourceLocation p_isAbsoluteLocation_1_) {
      String path = p_isAbsoluteLocation_1_.func_110623_a();
      return this.isAbsoluteLocationPath(path);
   }

   private boolean isAbsoluteLocationPath(String p_isAbsoluteLocationPath_1_) {
      String path = p_isAbsoluteLocationPath_1_.toLowerCase();
      return path.startsWith("mcpatcher/") || path.startsWith("optifine/");
   }

   public TextureAtlasSprite getSpriteSafe(String p_getSpriteSafe_1_) {
      ResourceLocation loc = new ResourceLocation(p_getSpriteSafe_1_);
      return (TextureAtlasSprite)this.field_110574_e.get(loc.toString());
   }

   public TextureAtlasSprite getRegisteredSprite(ResourceLocation p_getRegisteredSprite_1_) {
      return (TextureAtlasSprite)this.field_110574_e.get(p_getRegisteredSprite_1_.toString());
   }

   private boolean isTerrainAnimationActive(TextureAtlasSprite p_isTerrainAnimationActive_1_) {
      if (p_isTerrainAnimationActive_1_ != TextureUtils.iconWaterStill && p_isTerrainAnimationActive_1_ != TextureUtils.iconWaterFlow) {
         if (p_isTerrainAnimationActive_1_ != TextureUtils.iconLavaStill && p_isTerrainAnimationActive_1_ != TextureUtils.iconLavaFlow) {
            if (p_isTerrainAnimationActive_1_ != TextureUtils.iconFireLayer0 && p_isTerrainAnimationActive_1_ != TextureUtils.iconFireLayer1) {
               if (p_isTerrainAnimationActive_1_ == TextureUtils.iconPortal) {
                  return Config.isAnimatedPortal();
               } else {
                  return p_isTerrainAnimationActive_1_ != TextureUtils.iconClock && p_isTerrainAnimationActive_1_ != TextureUtils.iconCompass ? Config.isAnimatedTerrain() : true;
               }
            } else {
               return Config.isAnimatedFire();
            }
         } else {
            return Config.isAnimatedLava();
         }
      } else {
         return Config.isAnimatedWater();
      }
   }

   public int getCountRegisteredSprites() {
      return this.counterIndexInMap.getValue();
   }

   private int detectMaxMipmapLevel(Map p_detectMaxMipmapLevel_1_, IResourceManager p_detectMaxMipmapLevel_2_) {
      int minSize = this.detectMinimumSpriteSize(p_detectMaxMipmapLevel_1_, p_detectMaxMipmapLevel_2_, 20);
      if (minSize < 16) {
         minSize = 16;
      }

      minSize = MathHelper.func_151236_b(minSize);
      if (minSize > 16) {
         Config.log("Sprite size: " + minSize);
      }

      int minLevel = MathHelper.func_151239_c(minSize);
      if (minLevel < 4) {
         minLevel = 4;
      }

      return minLevel;
   }

   private int detectMinimumSpriteSize(Map p_detectMinimumSpriteSize_1_, IResourceManager p_detectMinimumSpriteSize_2_, int p_detectMinimumSpriteSize_3_) {
      Map mapSizeCounts = new HashMap();
      Set entrySetSprites = p_detectMinimumSpriteSize_1_.entrySet();
      Iterator it = entrySetSprites.iterator();

      int width;
      while(it.hasNext()) {
         Entry entry = (Entry)it.next();
         TextureAtlasSprite sprite = (TextureAtlasSprite)entry.getValue();
         ResourceLocation loc = new ResourceLocation(sprite.func_94215_i());
         ResourceLocation locComplete = this.completeResourceLocation(loc);
         if (!sprite.hasCustomLoader(p_detectMinimumSpriteSize_2_, loc)) {
            try {
               IResource res = p_detectMinimumSpriteSize_2_.func_110536_a(locComplete);
               if (res != null) {
                  InputStream in = res.func_110527_b();
                  if (in != null) {
                     Dimension dim = TextureUtils.getImageSize(in, "png");
                     if (dim != null) {
                        width = dim.width;
                        int width2 = MathHelper.func_151236_b(width);
                        if (!mapSizeCounts.containsKey(width2)) {
                           mapSizeCounts.put(width2, 1);
                        } else {
                           int count = (Integer)mapSizeCounts.get(width2);
                           mapSizeCounts.put(width2, count + 1);
                        }
                     }
                  }
               }
            } catch (Exception var17) {
            }
         }
      }

      int countSprites = 0;
      Set setSizes = mapSizeCounts.keySet();
      Set setSizesSorted = new TreeSet(setSizes);

      int countScale;
      int countScaleMax;
      for(Iterator it = setSizesSorted.iterator(); it.hasNext(); countSprites += countScaleMax) {
         countScale = (Integer)it.next();
         countScaleMax = (Integer)mapSizeCounts.get(countScale);
      }

      int minSize = 16;
      countScale = 0;
      countScaleMax = countSprites * p_detectMinimumSpriteSize_3_ / 100;
      Iterator it = setSizesSorted.iterator();

      do {
         if (!it.hasNext()) {
            return minSize;
         }

         int size = (Integer)it.next();
         width = (Integer)mapSizeCounts.get(size);
         countScale += width;
         if (size > minSize) {
            minSize = size;
         }
      } while(countScale <= countScaleMax);

      return minSize;
   }

   private int getMinSpriteSize() {
      int minSize = 1 << this.field_147636_j;
      if (minSize < 8) {
         minSize = 8;
      }

      return minSize;
   }

   private int[] getMissingImageData(int p_getMissingImageData_1_) {
      BufferedImage bi = new BufferedImage(16, 16, 2);
      bi.setRGB(0, 0, 16, 16, TextureUtil.field_110999_b, 0, 16);
      BufferedImage bi2 = TextureUtils.scaleImage(bi, p_getMissingImageData_1_);
      int[] data = new int[p_getMissingImageData_1_ * p_getMissingImageData_1_];
      bi2.getRGB(0, 0, p_getMissingImageData_1_, p_getMissingImageData_1_, data, 0, p_getMissingImageData_1_);
      return data;
   }

   public boolean isTextureBound() {
      int boundTexId = GlStateManager.getBoundTexture();
      int texId = this.func_110552_b();
      return boundTexId == texId;
   }

   private void updateIconGrid(int p_updateIconGrid_1_, int p_updateIconGrid_2_) {
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGrid = null;
      if (this.iconGridSize > 0) {
         this.iconGridCountX = p_updateIconGrid_1_ / this.iconGridSize;
         this.iconGridCountY = p_updateIconGrid_2_ / this.iconGridSize;
         this.iconGrid = new TextureAtlasSprite[this.iconGridCountX * this.iconGridCountY];
         this.iconGridSizeU = 1.0D / (double)this.iconGridCountX;
         this.iconGridSizeV = 1.0D / (double)this.iconGridCountY;
         Iterator it = this.field_94252_e.values().iterator();

         while(it.hasNext()) {
            TextureAtlasSprite ts = (TextureAtlasSprite)it.next();
            double deltaU = 0.5D / (double)p_updateIconGrid_1_;
            double deltaV = 0.5D / (double)p_updateIconGrid_2_;
            double uMin = (double)Math.min(ts.func_94209_e(), ts.func_94212_f()) + deltaU;
            double vMin = (double)Math.min(ts.func_94206_g(), ts.func_94210_h()) + deltaV;
            double uMax = (double)Math.max(ts.func_94209_e(), ts.func_94212_f()) - deltaU;
            double vMax = (double)Math.max(ts.func_94206_g(), ts.func_94210_h()) - deltaV;
            int iuMin = (int)(uMin / this.iconGridSizeU);
            int ivMin = (int)(vMin / this.iconGridSizeV);
            int iuMax = (int)(uMax / this.iconGridSizeU);
            int ivMax = (int)(vMax / this.iconGridSizeV);

            for(int iu = iuMin; iu <= iuMax; ++iu) {
               if (iu >= 0 && iu < this.iconGridCountX) {
                  for(int iv = ivMin; iv <= ivMax; ++iv) {
                     if (iv >= 0 && iv < this.iconGridCountX) {
                        int index = iv * this.iconGridCountX + iu;
                        this.iconGrid[index] = ts;
                     } else {
                        Config.warn("Invalid grid V: " + iv + ", icon: " + ts.func_94215_i());
                     }
                  }
               } else {
                  Config.warn("Invalid grid U: " + iu + ", icon: " + ts.func_94215_i());
               }
            }
         }

      }
   }

   public TextureAtlasSprite getIconByUV(double p_getIconByUV_1_, double p_getIconByUV_3_) {
      if (this.iconGrid == null) {
         return null;
      } else {
         int iu = (int)(p_getIconByUV_1_ / this.iconGridSizeU);
         int iv = (int)(p_getIconByUV_3_ / this.iconGridSizeV);
         int index = iv * this.iconGridCountX + iu;
         return index >= 0 && index <= this.iconGrid.length ? this.iconGrid[index] : null;
      }
   }

   private void checkEmissive(ResourceLocation p_checkEmissive_1_, TextureAtlasSprite p_checkEmissive_2_) {
      String suffixEm = EmissiveTextures.getSuffixEmissive();
      if (suffixEm != null) {
         if (!p_checkEmissive_1_.func_110623_a().endsWith(suffixEm)) {
            ResourceLocation locSpriteEm = new ResourceLocation(p_checkEmissive_1_.func_110624_b(), p_checkEmissive_1_.func_110623_a() + suffixEm);
            ResourceLocation locPngEm = this.completeResourceLocation(locSpriteEm);
            if (Config.hasResource(locPngEm)) {
               TextureAtlasSprite spriteEmissive = this.func_174942_a(locSpriteEm);
               spriteEmissive.isEmissive = true;
               p_checkEmissive_2_.spriteEmissive = spriteEmissive;
            }
         }
      }
   }

   public int getCountAnimations() {
      return this.field_94258_i.size();
   }

   public int getCountAnimationsActive() {
      return this.countAnimationsActive;
   }
}
