package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.SmartAnimations;
import net.optifine.shaders.Shaders;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;

public class TextureAtlasSprite {
   private final String field_110984_i;
   protected List<int[][]> field_110976_a = Lists.newArrayList();
   protected int[][] field_176605_b;
   private AnimationMetadataSection field_110982_k;
   protected boolean field_130222_e;
   protected int field_110975_c;
   protected int field_110974_d;
   protected int field_130223_c;
   protected int field_130224_d;
   private float field_110979_l;
   private float field_110980_m;
   private float field_110977_n;
   private float field_110978_o;
   protected int field_110973_g;
   protected int field_110983_h;
   private static String field_176607_p = "builtin/clock";
   private static String field_176606_q = "builtin/compass";
   private int indexInMap = -1;
   public float baseU;
   public float baseV;
   public int sheetWidth;
   public int sheetHeight;
   public int glSpriteTextureId = -1;
   public TextureAtlasSprite spriteSingle = null;
   public boolean isSpriteSingle = false;
   public int mipmapLevels = 0;
   public TextureAtlasSprite spriteNormal = null;
   public TextureAtlasSprite spriteSpecular = null;
   public boolean isShadersSprite = false;
   public boolean isEmissive = false;
   public TextureAtlasSprite spriteEmissive = null;
   private int animationIndex = -1;
   private boolean animationActive = false;

   private TextureAtlasSprite(String p_i7_1_, boolean p_i7_2_) {
      this.field_110984_i = p_i7_1_;
      this.isSpriteSingle = p_i7_2_;
   }

   public TextureAtlasSprite(String p_i1282_1_) {
      this.field_110984_i = p_i1282_1_;
      if (Config.isMultiTexture()) {
         this.spriteSingle = new TextureAtlasSprite(this.func_94215_i() + ".spriteSingle", true);
      }

   }

   protected static TextureAtlasSprite func_176604_a(ResourceLocation p_176604_0_) {
      String s = p_176604_0_.toString();
      return (TextureAtlasSprite)(field_176607_p.equals(s) ? new TextureClock(s) : (field_176606_q.equals(s) ? new TextureCompass(s) : new TextureAtlasSprite(s)));
   }

   public static void func_176602_a(String p_176602_0_) {
      field_176607_p = p_176602_0_;
   }

   public static void func_176603_b(String p_176603_0_) {
      field_176606_q = p_176603_0_;
   }

   public void func_110971_a(int p_110971_1_, int p_110971_2_, int p_110971_3_, int p_110971_4_, boolean p_110971_5_) {
      this.field_110975_c = p_110971_3_;
      this.field_110974_d = p_110971_4_;
      this.field_130222_e = p_110971_5_;
      float f = (float)(0.009999999776482582D / (double)p_110971_1_);
      float f1 = (float)(0.009999999776482582D / (double)p_110971_2_);
      this.field_110979_l = (float)p_110971_3_ / (float)((double)p_110971_1_) + f;
      this.field_110980_m = (float)(p_110971_3_ + this.field_130223_c) / (float)((double)p_110971_1_) - f;
      this.field_110977_n = (float)p_110971_4_ / (float)p_110971_2_ + f1;
      this.field_110978_o = (float)(p_110971_4_ + this.field_130224_d) / (float)p_110971_2_ - f1;
      this.baseU = Math.min(this.field_110979_l, this.field_110980_m);
      this.baseV = Math.min(this.field_110977_n, this.field_110978_o);
      if (this.spriteSingle != null) {
         this.spriteSingle.func_110971_a(this.field_130223_c, this.field_130224_d, 0, 0, false);
      }

      if (this.spriteNormal != null) {
         this.spriteNormal.func_94217_a(this);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.func_94217_a(this);
      }

   }

   public void func_94217_a(TextureAtlasSprite p_94217_1_) {
      this.field_110975_c = p_94217_1_.field_110975_c;
      this.field_110974_d = p_94217_1_.field_110974_d;
      this.field_130223_c = p_94217_1_.field_130223_c;
      this.field_130224_d = p_94217_1_.field_130224_d;
      this.field_130222_e = p_94217_1_.field_130222_e;
      this.field_110979_l = p_94217_1_.field_110979_l;
      this.field_110980_m = p_94217_1_.field_110980_m;
      this.field_110977_n = p_94217_1_.field_110977_n;
      this.field_110978_o = p_94217_1_.field_110978_o;
      if (p_94217_1_ != Config.getTextureMap().func_174944_f()) {
         this.indexInMap = p_94217_1_.indexInMap;
      }

      this.baseU = p_94217_1_.baseU;
      this.baseV = p_94217_1_.baseV;
      this.sheetWidth = p_94217_1_.sheetWidth;
      this.sheetHeight = p_94217_1_.sheetHeight;
      this.glSpriteTextureId = p_94217_1_.glSpriteTextureId;
      this.mipmapLevels = p_94217_1_.mipmapLevels;
      if (this.spriteSingle != null) {
         this.spriteSingle.func_110971_a(this.field_130223_c, this.field_130224_d, 0, 0, false);
      }

      this.animationIndex = p_94217_1_.animationIndex;
   }

   public int func_130010_a() {
      return this.field_110975_c;
   }

   public int func_110967_i() {
      return this.field_110974_d;
   }

   public int func_94211_a() {
      return this.field_130223_c;
   }

   public int func_94216_b() {
      return this.field_130224_d;
   }

   public float func_94209_e() {
      return this.field_110979_l;
   }

   public float func_94212_f() {
      return this.field_110980_m;
   }

   public float func_94214_a(double p_94214_1_) {
      float f = this.field_110980_m - this.field_110979_l;
      return this.field_110979_l + f * (float)p_94214_1_ / 16.0F;
   }

   public float func_94206_g() {
      return this.field_110977_n;
   }

   public float func_94210_h() {
      return this.field_110978_o;
   }

   public float func_94207_b(double p_94207_1_) {
      float f = this.field_110978_o - this.field_110977_n;
      return this.field_110977_n + f * ((float)p_94207_1_ / 16.0F);
   }

   public String func_94215_i() {
      return this.field_110984_i;
   }

   public void func_94219_l() {
      if (this.field_110982_k != null) {
         this.animationActive = SmartAnimations.isActive() ? SmartAnimations.isSpriteRendered(this.animationIndex) : true;
         ++this.field_110983_h;
         if (this.field_110983_h >= this.field_110982_k.func_110472_a(this.field_110973_g)) {
            int i = this.field_110982_k.func_110468_c(this.field_110973_g);
            int j = this.field_110982_k.func_110473_c() == 0 ? this.field_110976_a.size() : this.field_110982_k.func_110473_c();
            this.field_110973_g = (this.field_110973_g + 1) % j;
            this.field_110983_h = 0;
            int k = this.field_110982_k.func_110468_c(this.field_110973_g);
            boolean texBlur = false;
            boolean texClamp = this.isSpriteSingle;
            if (!this.animationActive) {
               return;
            }

            if (i != k && k >= 0 && k < this.field_110976_a.size()) {
               TextureUtil.func_147955_a((int[][])((int[][])this.field_110976_a.get(k)), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, texBlur, texClamp);
            }
         } else if (this.field_110982_k.func_177219_e()) {
            if (!this.animationActive) {
               return;
            }

            this.func_180599_n();
         }

      }
   }

   private void func_180599_n() {
      double d0 = 1.0D - (double)this.field_110983_h / (double)this.field_110982_k.func_110472_a(this.field_110973_g);
      int i = this.field_110982_k.func_110468_c(this.field_110973_g);
      int j = this.field_110982_k.func_110473_c() == 0 ? this.field_110976_a.size() : this.field_110982_k.func_110473_c();
      int k = this.field_110982_k.func_110468_c((this.field_110973_g + 1) % j);
      if (i != k && k >= 0 && k < this.field_110976_a.size()) {
         int[][] aint = (int[][])((int[][])this.field_110976_a.get(i));
         int[][] aint1 = (int[][])((int[][])this.field_110976_a.get(k));
         if (this.field_176605_b == null || this.field_176605_b.length != aint.length) {
            this.field_176605_b = new int[aint.length][];
         }

         for(int l = 0; l < aint.length; ++l) {
            if (this.field_176605_b[l] == null) {
               this.field_176605_b[l] = new int[aint[l].length];
            }

            if (l < aint1.length && aint1[l].length == aint[l].length) {
               for(int i1 = 0; i1 < aint[l].length; ++i1) {
                  int j1 = aint[l][i1];
                  int k1 = aint1[l][i1];
                  int l1 = (int)((double)((j1 & 16711680) >> 16) * d0 + (double)((k1 & 16711680) >> 16) * (1.0D - d0));
                  int i2 = (int)((double)((j1 & '\uff00') >> 8) * d0 + (double)((k1 & '\uff00') >> 8) * (1.0D - d0));
                  int j2 = (int)((double)(j1 & 255) * d0 + (double)(k1 & 255) * (1.0D - d0));
                  this.field_176605_b[l][i1] = j1 & -16777216 | l1 << 16 | i2 << 8 | j2;
               }
            }
         }

         TextureUtil.func_147955_a(this.field_176605_b, this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
      }

   }

   public int[][] func_147965_a(int p_147965_1_) {
      return (int[][])((int[][])this.field_110976_a.get(p_147965_1_));
   }

   public int func_110970_k() {
      return this.field_110976_a.size();
   }

   public void func_110966_b(int p_110966_1_) {
      this.field_130223_c = p_110966_1_;
      if (this.spriteSingle != null) {
         this.spriteSingle.func_110966_b(this.field_130223_c);
      }

   }

   public void func_110969_c(int p_110969_1_) {
      this.field_130224_d = p_110969_1_;
      if (this.spriteSingle != null) {
         this.spriteSingle.func_110969_c(this.field_130224_d);
      }

   }

   public void func_180598_a(BufferedImage[] p_180598_1_, AnimationMetadataSection p_180598_2_) throws IOException {
      this.func_130102_n();
      int i = p_180598_1_[0].getWidth();
      int j = p_180598_1_[0].getHeight();
      this.field_130223_c = i;
      this.field_130224_d = j;
      if (this.spriteSingle != null) {
         this.spriteSingle.field_130223_c = this.field_130223_c;
         this.spriteSingle.field_130224_d = this.field_130224_d;
      }

      int[][] aint = new int[p_180598_1_.length][];

      int x;
      for(x = 0; x < p_180598_1_.length; ++x) {
         BufferedImage bufferedimage = p_180598_1_[x];
         if (bufferedimage != null) {
            if (this.field_130223_c >> x != bufferedimage.getWidth()) {
               bufferedimage = TextureUtils.scaleImage(bufferedimage, this.field_130223_c >> x);
            }

            if (x > 0 && (bufferedimage.getWidth() != i >> x || bufferedimage.getHeight() != j >> x)) {
               throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", x, bufferedimage.getWidth(), bufferedimage.getHeight(), i >> x, j >> x));
            }

            aint[x] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), aint[x], 0, bufferedimage.getWidth());
         }
      }

      int di;
      if (p_180598_2_ == null) {
         if (j != i) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.field_110976_a.add(aint);
      } else {
         x = j / i;
         int k1 = i;
         di = i;
         this.field_130224_d = this.field_130223_c;
         int i1;
         if (p_180598_2_.func_110473_c() > 0) {
            Iterator iterator = p_180598_2_.func_130073_e().iterator();

            while(iterator.hasNext()) {
               i1 = (Integer)iterator.next();
               if (i1 >= x) {
                  throw new RuntimeException("invalid frameindex " + i1);
               }

               this.func_130099_d(i1);
               this.field_110976_a.set(i1, func_147962_a(aint, k1, di, i1));
            }

            this.field_110982_k = p_180598_2_;
         } else {
            List<AnimationFrame> list = Lists.newArrayList();

            for(i1 = 0; i1 < x; ++i1) {
               this.field_110976_a.add(func_147962_a(aint, k1, di, i1));
               list.add(new AnimationFrame(i1, -1));
            }

            this.field_110982_k = new AnimationMetadataSection(list, this.field_130223_c, this.field_130224_d, p_180598_2_.func_110469_d(), p_180598_2_.func_177219_e());
         }
      }

      if (!this.isShadersSprite) {
         if (Config.isShaders()) {
            this.loadShadersSprites();
         }

         for(x = 0; x < this.field_110976_a.size(); ++x) {
            int[][] datas = (int[][])((int[][])this.field_110976_a.get(x));
            if (datas != null && !this.field_110984_i.startsWith("minecraft:blocks/leaves_")) {
               for(di = 0; di < datas.length; ++di) {
                  int[] data = datas[di];
                  this.fixTransparentColor(data);
               }
            }
         }

         if (this.spriteSingle != null) {
            this.spriteSingle.func_180598_a(p_180598_1_, p_180598_2_);
         }

      }
   }

   public void func_147963_d(int p_147963_1_) {
      List<int[][]> list = Lists.newArrayList();

      for(int i = 0; i < this.field_110976_a.size(); ++i) {
         final int[][] aint = (int[][])((int[][])this.field_110976_a.get(i));
         if (aint != null) {
            try {
               list.add(TextureUtil.func_147949_a(p_147963_1_, this.field_130223_c, aint));
            } catch (Throwable var8) {
               CrashReport crashreport = CrashReport.func_85055_a(var8, "Generating mipmaps for frame");
               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Frame being iterated");
               crashreportcategory.func_71507_a("Frame index", i);
               crashreportcategory.func_71500_a("Frame sizes", new Callable<String>() {
                  public String call() throws Exception {
                     StringBuilder stringbuilder = new StringBuilder();
                     int[][] arr$ = aint;
                     int len$ = arr$.length;

                     for(int i$ = 0; i$ < len$; ++i$) {
                        int[] aint1 = arr$[i$];
                        if (stringbuilder.length() > 0) {
                           stringbuilder.append(", ");
                        }

                        stringbuilder.append(aint1 == null ? "null" : aint1.length);
                     }

                     return stringbuilder.toString();
                  }
               });
               throw new ReportedException(crashreport);
            }
         }
      }

      this.func_110968_a(list);
      if (this.spriteSingle != null) {
         this.spriteSingle.func_147963_d(p_147963_1_);
      }

   }

   private void func_130099_d(int p_130099_1_) {
      if (this.field_110976_a.size() <= p_130099_1_) {
         for(int i = this.field_110976_a.size(); i <= p_130099_1_; ++i) {
            this.field_110976_a.add((int[][])((int[][])null));
         }
      }

      if (this.spriteSingle != null) {
         this.spriteSingle.func_130099_d(p_130099_1_);
      }

   }

   private static int[][] func_147962_a(int[][] p_147962_0_, int p_147962_1_, int p_147962_2_, int p_147962_3_) {
      int[][] aint = new int[p_147962_0_.length][];

      for(int i = 0; i < p_147962_0_.length; ++i) {
         int[] aint1 = p_147962_0_[i];
         if (aint1 != null) {
            aint[i] = new int[(p_147962_1_ >> i) * (p_147962_2_ >> i)];
            System.arraycopy(aint1, p_147962_3_ * aint[i].length, aint[i], 0, aint[i].length);
         }
      }

      return aint;
   }

   public void func_130103_l() {
      this.field_110976_a.clear();
      if (this.spriteSingle != null) {
         this.spriteSingle.func_130103_l();
      }

   }

   public boolean func_130098_m() {
      return this.field_110982_k != null;
   }

   public void func_110968_a(List<int[][]> p_110968_1_) {
      this.field_110976_a = p_110968_1_;
      if (this.spriteSingle != null) {
         this.spriteSingle.func_110968_a(p_110968_1_);
      }

   }

   private void func_130102_n() {
      this.field_110982_k = null;
      this.func_110968_a(Lists.newArrayList());
      this.field_110973_g = 0;
      this.field_110983_h = 0;
      if (this.spriteSingle != null) {
         this.spriteSingle.func_130102_n();
      }

   }

   public String toString() {
      return "TextureAtlasSprite{name='" + this.field_110984_i + '\'' + ", frameCount=" + this.field_110976_a.size() + ", rotated=" + this.field_130222_e + ", x=" + this.field_110975_c + ", y=" + this.field_110974_d + ", height=" + this.field_130224_d + ", width=" + this.field_130223_c + ", u0=" + this.field_110979_l + ", u1=" + this.field_110980_m + ", v0=" + this.field_110977_n + ", v1=" + this.field_110978_o + '}';
   }

   public boolean hasCustomLoader(IResourceManager p_hasCustomLoader_1_, ResourceLocation p_hasCustomLoader_2_) {
      return false;
   }

   public boolean load(IResourceManager p_load_1_, ResourceLocation p_load_2_) {
      return true;
   }

   public int getIndexInMap() {
      return this.indexInMap;
   }

   public void setIndexInMap(int p_setIndexInMap_1_) {
      this.indexInMap = p_setIndexInMap_1_;
   }

   public void updateIndexInMap(CounterInt p_updateIndexInMap_1_) {
      if (this.indexInMap < 0) {
         this.indexInMap = p_updateIndexInMap_1_.nextValue();
      }

   }

   public int getAnimationIndex() {
      return this.animationIndex;
   }

   public void setAnimationIndex(int p_setAnimationIndex_1_) {
      this.animationIndex = p_setAnimationIndex_1_;
      if (this.spriteNormal != null) {
         this.spriteNormal.setAnimationIndex(p_setAnimationIndex_1_);
      }

      if (this.spriteSpecular != null) {
         this.spriteSpecular.setAnimationIndex(p_setAnimationIndex_1_);
      }

   }

   public boolean isAnimationActive() {
      return this.animationActive;
   }

   private void fixTransparentColor(int[] p_fixTransparentColor_1_) {
      if (p_fixTransparentColor_1_ != null) {
         long redSum = 0L;
         long greenSum = 0L;
         long blueSum = 0L;
         long count = 0L;

         int redAvg;
         int greenAvg;
         int blueAvg;
         int colAvg;
         int i;
         int col;
         for(redAvg = 0; redAvg < p_fixTransparentColor_1_.length; ++redAvg) {
            greenAvg = p_fixTransparentColor_1_[redAvg];
            blueAvg = greenAvg >> 24 & 255;
            if (blueAvg >= 16) {
               colAvg = greenAvg >> 16 & 255;
               i = greenAvg >> 8 & 255;
               col = greenAvg & 255;
               redSum += (long)colAvg;
               greenSum += (long)i;
               blueSum += (long)col;
               ++count;
            }
         }

         if (count > 0L) {
            redAvg = (int)(redSum / count);
            greenAvg = (int)(greenSum / count);
            blueAvg = (int)(blueSum / count);
            colAvg = redAvg << 16 | greenAvg << 8 | blueAvg;

            for(i = 0; i < p_fixTransparentColor_1_.length; ++i) {
               col = p_fixTransparentColor_1_[i];
               int alpha = col >> 24 & 255;
               if (alpha <= 16) {
                  p_fixTransparentColor_1_[i] = colAvg;
               }
            }

         }
      }
   }

   public double getSpriteU16(float p_getSpriteU16_1_) {
      float dU = this.field_110980_m - this.field_110979_l;
      return (double)((p_getSpriteU16_1_ - this.field_110979_l) / dU * 16.0F);
   }

   public double getSpriteV16(float p_getSpriteV16_1_) {
      float dV = this.field_110978_o - this.field_110977_n;
      return (double)((p_getSpriteV16_1_ - this.field_110977_n) / dV * 16.0F);
   }

   public void bindSpriteTexture() {
      if (this.glSpriteTextureId < 0) {
         this.glSpriteTextureId = TextureUtil.func_110996_a();
         TextureUtil.func_180600_a(this.glSpriteTextureId, this.mipmapLevels, this.field_130223_c, this.field_130224_d);
         TextureUtils.applyAnisotropicLevel();
      }

      TextureUtils.bindTexture(this.glSpriteTextureId);
   }

   public void deleteSpriteTexture() {
      if (this.glSpriteTextureId >= 0) {
         TextureUtil.func_147942_a(this.glSpriteTextureId);
         this.glSpriteTextureId = -1;
      }
   }

   public float toSingleU(float p_toSingleU_1_) {
      p_toSingleU_1_ -= this.baseU;
      float ku = (float)this.sheetWidth / (float)this.field_130223_c;
      p_toSingleU_1_ *= ku;
      return p_toSingleU_1_;
   }

   public float toSingleV(float p_toSingleV_1_) {
      p_toSingleV_1_ -= this.baseV;
      float kv = (float)this.sheetHeight / (float)this.field_130224_d;
      p_toSingleV_1_ *= kv;
      return p_toSingleV_1_;
   }

   public List<int[][]> getFramesTextureData() {
      List<int[][]> datas = new ArrayList();
      datas.addAll(this.field_110976_a);
      return datas;
   }

   public AnimationMetadataSection getAnimationMetadata() {
      return this.field_110982_k;
   }

   public void setAnimationMetadata(AnimationMetadataSection p_setAnimationMetadata_1_) {
      this.field_110982_k = p_setAnimationMetadata_1_;
   }

   private void loadShadersSprites() {
      String nameSpecular;
      ResourceLocation locSpecular;
      if (Shaders.configNormalMap) {
         nameSpecular = this.field_110984_i + "_n";
         locSpecular = new ResourceLocation(nameSpecular);
         locSpecular = Config.getTextureMap().completeResourceLocation(locSpecular);
         if (Config.hasResource(locSpecular)) {
            this.spriteNormal = new TextureAtlasSprite(nameSpecular);
            this.spriteNormal.isShadersSprite = true;
            this.spriteNormal.func_94217_a(this);
            this.spriteNormal.func_147963_d(this.mipmapLevels);
         }
      }

      if (Shaders.configSpecularMap) {
         nameSpecular = this.field_110984_i + "_s";
         locSpecular = new ResourceLocation(nameSpecular);
         locSpecular = Config.getTextureMap().completeResourceLocation(locSpecular);
         if (Config.hasResource(locSpecular)) {
            this.spriteSpecular = new TextureAtlasSprite(nameSpecular);
            this.spriteSpecular.isShadersSprite = true;
            this.spriteSpecular.func_94217_a(this);
            this.spriteSpecular.func_147963_d(this.mipmapLevels);
         }
      }

   }
}
