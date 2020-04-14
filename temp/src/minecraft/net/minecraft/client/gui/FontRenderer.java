package net.minecraft.client.gui;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.render.GlBlendState;
import net.optifine.util.FontUtils;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

public class FontRenderer implements IResourceManagerReloadListener {
   private static final ResourceLocation[] field_111274_c = new ResourceLocation[256];
   private final int[] field_78286_d = new int[256];
   public int field_78288_b = 9;
   public Random field_78289_c = new Random();
   private byte[] field_78287_e = new byte[65536];
   private int[] field_78285_g = new int[32];
   private ResourceLocation field_111273_g;
   private final TextureManager field_78298_i;
   private float field_78295_j;
   private float field_78296_k;
   private boolean field_78293_l;
   private boolean field_78294_m;
   private float field_78291_n;
   private float field_78292_o;
   private float field_78306_p;
   private float field_78305_q;
   private int field_78304_r;
   private boolean field_78303_s;
   private boolean field_78302_t;
   private boolean field_78301_u;
   private boolean field_78300_v;
   private boolean field_78299_w;
   public GameSettings gameSettings;
   public ResourceLocation locationFontTextureBase;
   public float offsetBold = 1.0F;
   private float[] charWidthFloat = new float[256];
   private boolean blend = false;
   private GlBlendState oldBlendState = new GlBlendState();

   public FontRenderer(GameSettings p_i1035_1_, ResourceLocation p_i1035_2_, TextureManager p_i1035_3_, boolean p_i1035_4_) {
      this.gameSettings = p_i1035_1_;
      this.locationFontTextureBase = p_i1035_2_;
      this.field_111273_g = p_i1035_2_;
      this.field_78298_i = p_i1035_3_;
      this.field_78293_l = p_i1035_4_;
      this.field_111273_g = FontUtils.getHdFontLocation(this.locationFontTextureBase);
      this.bindTexture(this.field_111273_g);

      for(int i = 0; i < 32; ++i) {
         int j = (i >> 3 & 1) * 85;
         int k = (i >> 2 & 1) * 170 + j;
         int l = (i >> 1 & 1) * 170 + j;
         int i1 = (i >> 0 & 1) * 170 + j;
         if (i == 6) {
            k += 85;
         }

         if (p_i1035_1_.field_74337_g) {
            int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
            int k1 = (k * 30 + l * 70) / 100;
            int l1 = (k * 30 + i1 * 70) / 100;
            k = j1;
            l = k1;
            i1 = l1;
         }

         if (i >= 16) {
            k /= 4;
            l /= 4;
            i1 /= 4;
         }

         this.field_78285_g[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
      }

      this.func_98306_d();
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.field_111273_g = FontUtils.getHdFontLocation(this.locationFontTextureBase);

      for(int i = 0; i < field_111274_c.length; ++i) {
         field_111274_c[i] = null;
      }

      this.func_111272_d();
      this.func_98306_d();
   }

   private void func_111272_d() {
      BufferedImage bufferedimage;
      try {
         bufferedimage = TextureUtil.func_177053_a(this.getResourceInputStream(this.field_111273_g));
      } catch (IOException var21) {
         throw new RuntimeException(var21);
      }

      Properties props = FontUtils.readFontProperties(this.field_111273_g);
      this.blend = FontUtils.readBoolean(props, "blend", false);
      int imgWidth = bufferedimage.getWidth();
      int imgHeight = bufferedimage.getHeight();
      int charW = imgWidth / 16;
      int charH = imgHeight / 16;
      float kx = (float)imgWidth / 128.0F;
      float boldScaleFactor = Config.limit(kx, 1.0F, 2.0F);
      this.offsetBold = 1.0F / boldScaleFactor;
      float offsetBoldConfig = FontUtils.readFloat(props, "offsetBold", -1.0F);
      if (offsetBoldConfig >= 0.0F) {
         this.offsetBold = offsetBoldConfig;
      }

      int[] ai = new int[imgWidth * imgHeight];
      bufferedimage.getRGB(0, 0, imgWidth, imgHeight, ai, 0, imgWidth);

      int k;
      for(k = 0; k < 256; ++k) {
         int cx = k % 16;
         int cy = k / 16;
         int px = false;

         int px;
         for(px = charW - 1; px >= 0; --px) {
            int x = cx * charW + px;
            boolean flag = true;

            for(int py = 0; py < charH && flag; ++py) {
               int ypos = (cy * charH + py) * imgWidth;
               int col = ai[x + ypos];
               int al = col >> 24 & 255;
               if (al > 16) {
                  flag = false;
               }
            }

            if (!flag) {
               break;
            }
         }

         if (k == 65) {
            k = k;
         }

         if (k == 32) {
            if (charW <= 8) {
               px = (int)(2.0F * kx);
            } else {
               px = (int)(1.5F * kx);
            }
         }

         this.charWidthFloat[k] = (float)(px + 1) / kx + 1.0F;
      }

      FontUtils.readCustomCharWidths(props, this.charWidthFloat);

      for(k = 0; k < this.field_78286_d.length; ++k) {
         this.field_78286_d[k] = Math.round(this.charWidthFloat[k]);
      }

   }

   private void func_98306_d() {
      InputStream inputstream = null;

      try {
         inputstream = this.getResourceInputStream(new ResourceLocation("font/glyph_sizes.bin"));
         inputstream.read(this.field_78287_e);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

   }

   private float func_181559_a(char p_181559_1_, boolean p_181559_2_) {
      if (p_181559_1_ != ' ' && p_181559_1_ != 160) {
         int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_181559_1_);
         return i != -1 && !this.field_78293_l ? this.func_78266_a(i, p_181559_2_) : this.func_78277_a(p_181559_1_, p_181559_2_);
      } else {
         return !this.field_78293_l ? this.charWidthFloat[p_181559_1_] : 4.0F;
      }
   }

   private float func_78266_a(int p_78266_1_, boolean p_78266_2_) {
      int i = p_78266_1_ % 16 * 8;
      int j = p_78266_1_ / 16 * 8;
      int k = p_78266_2_ ? 1 : 0;
      this.bindTexture(this.field_111273_g);
      float l = this.charWidthFloat[p_78266_1_];
      float f = 7.99F;
      GL11.glBegin(5);
      GL11.glTexCoord2f((float)i / 128.0F, (float)j / 128.0F);
      GL11.glVertex3f(this.field_78295_j + (float)k, this.field_78296_k, 0.0F);
      GL11.glTexCoord2f((float)i / 128.0F, ((float)j + 7.99F) / 128.0F);
      GL11.glVertex3f(this.field_78295_j - (float)k, this.field_78296_k + 7.99F, 0.0F);
      GL11.glTexCoord2f(((float)i + f - 1.0F) / 128.0F, (float)j / 128.0F);
      GL11.glVertex3f(this.field_78295_j + f - 1.0F + (float)k, this.field_78296_k, 0.0F);
      GL11.glTexCoord2f(((float)i + f - 1.0F) / 128.0F, ((float)j + 7.99F) / 128.0F);
      GL11.glVertex3f(this.field_78295_j + f - 1.0F - (float)k, this.field_78296_k + 7.99F, 0.0F);
      GL11.glEnd();
      return l;
   }

   private ResourceLocation func_111271_a(int p_111271_1_) {
      if (field_111274_c[p_111271_1_] == null) {
         field_111274_c[p_111271_1_] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", p_111271_1_));
         field_111274_c[p_111271_1_] = FontUtils.getHdFontLocation(field_111274_c[p_111271_1_]);
      }

      return field_111274_c[p_111271_1_];
   }

   private void func_78257_a(int p_78257_1_) {
      this.bindTexture(this.func_111271_a(p_78257_1_));
   }

   private float func_78277_a(char p_78277_1_, boolean p_78277_2_) {
      if (this.field_78287_e[p_78277_1_] == 0) {
         return 0.0F;
      } else {
         int i = p_78277_1_ / 256;
         this.func_78257_a(i);
         int j = this.field_78287_e[p_78277_1_] >>> 4;
         int k = this.field_78287_e[p_78277_1_] & 15;
         float f = (float)j;
         float f1 = (float)(k + 1);
         float f2 = (float)(p_78277_1_ % 16 * 16) + f;
         float f3 = (float)((p_78277_1_ & 255) / 16 * 16);
         float f4 = f1 - f - 0.02F;
         float f5 = p_78277_2_ ? 1.0F : 0.0F;
         GL11.glBegin(5);
         GL11.glTexCoord2f(f2 / 256.0F, f3 / 256.0F);
         GL11.glVertex3f(this.field_78295_j + f5, this.field_78296_k, 0.0F);
         GL11.glTexCoord2f(f2 / 256.0F, (f3 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.field_78295_j - f5, this.field_78296_k + 7.99F, 0.0F);
         GL11.glTexCoord2f((f2 + f4) / 256.0F, f3 / 256.0F);
         GL11.glVertex3f(this.field_78295_j + f4 / 2.0F + f5, this.field_78296_k, 0.0F);
         GL11.glTexCoord2f((f2 + f4) / 256.0F, (f3 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.field_78295_j + f4 / 2.0F - f5, this.field_78296_k + 7.99F, 0.0F);
         GL11.glEnd();
         return (f1 - f) / 2.0F + 1.0F;
      }
   }

   public int func_175063_a(String p_175063_1_, float p_175063_2_, float p_175063_3_, int p_175063_4_) {
      return this.func_175065_a(p_175063_1_, p_175063_2_, p_175063_3_, p_175063_4_, true);
   }

   public int func_78276_b(String p_78276_1_, int p_78276_2_, int p_78276_3_, int p_78276_4_) {
      return this.func_175065_a(p_78276_1_, (float)p_78276_2_, (float)p_78276_3_, p_78276_4_, false);
   }

   public int func_175065_a(String p_175065_1_, float p_175065_2_, float p_175065_3_, int p_175065_4_, boolean p_175065_5_) {
      this.enableAlpha();
      if (this.blend) {
         GlStateManager.getBlendState(this.oldBlendState);
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 771);
      }

      this.func_78265_b();
      int i;
      if (p_175065_5_) {
         i = this.func_180455_b(p_175065_1_, p_175065_2_ + 1.0F, p_175065_3_ + 1.0F, p_175065_4_, true);
         i = Math.max(i, this.func_180455_b(p_175065_1_, p_175065_2_, p_175065_3_, p_175065_4_, false));
      } else {
         i = this.func_180455_b(p_175065_1_, p_175065_2_, p_175065_3_, p_175065_4_, false);
      }

      if (this.blend) {
         GlStateManager.setBlendState(this.oldBlendState);
      }

      return i;
   }

   private String func_147647_b(String p_147647_1_) {
      try {
         Bidi bidi = new Bidi((new ArabicShaping(8)).shape(p_147647_1_), 127);
         bidi.setReorderingMode(0);
         return bidi.writeReordered(2);
      } catch (ArabicShapingException var3) {
         return p_147647_1_;
      }
   }

   private void func_78265_b() {
      this.field_78303_s = false;
      this.field_78302_t = false;
      this.field_78301_u = false;
      this.field_78300_v = false;
      this.field_78299_w = false;
   }

   private void func_78255_a(String p_78255_1_, boolean p_78255_2_) {
      for(int i = 0; i < p_78255_1_.length(); ++i) {
         char c0 = p_78255_1_.charAt(i);
         int i1;
         int j1;
         if (c0 == 167 && i + 1 < p_78255_1_.length()) {
            i1 = "0123456789abcdefklmnor".indexOf(p_78255_1_.toLowerCase(Locale.ENGLISH).charAt(i + 1));
            if (i1 < 16) {
               this.field_78303_s = false;
               this.field_78302_t = false;
               this.field_78299_w = false;
               this.field_78300_v = false;
               this.field_78301_u = false;
               if (i1 < 0 || i1 > 15) {
                  i1 = 15;
               }

               if (p_78255_2_) {
                  i1 += 16;
               }

               j1 = this.field_78285_g[i1];
               if (Config.isCustomColors()) {
                  j1 = CustomColors.getTextColor(i1, j1);
               }

               this.field_78304_r = j1;
               this.setColor((float)(j1 >> 16) / 255.0F, (float)(j1 >> 8 & 255) / 255.0F, (float)(j1 & 255) / 255.0F, this.field_78305_q);
            } else if (i1 == 16) {
               this.field_78303_s = true;
            } else if (i1 == 17) {
               this.field_78302_t = true;
            } else if (i1 == 18) {
               this.field_78299_w = true;
            } else if (i1 == 19) {
               this.field_78300_v = true;
            } else if (i1 == 20) {
               this.field_78301_u = true;
            } else if (i1 == 21) {
               this.field_78303_s = false;
               this.field_78302_t = false;
               this.field_78299_w = false;
               this.field_78300_v = false;
               this.field_78301_u = false;
               this.setColor(this.field_78291_n, this.field_78292_o, this.field_78306_p, this.field_78305_q);
            }

            ++i;
         } else {
            i1 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);
            if (this.field_78303_s && i1 != -1) {
               j1 = this.func_78263_a(c0);

               char c1;
               do {
                  i1 = this.field_78289_c.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".length());
                  c1 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".charAt(i1);
               } while(j1 != this.func_78263_a(c1));

               c0 = c1;
            }

            float f1 = i1 != -1 && !this.field_78293_l ? this.offsetBold : 0.5F;
            boolean flag = (c0 == 0 || i1 == -1 || this.field_78293_l) && p_78255_2_;
            if (flag) {
               this.field_78295_j -= f1;
               this.field_78296_k -= f1;
            }

            float f = this.func_181559_a(c0, this.field_78301_u);
            if (flag) {
               this.field_78295_j += f1;
               this.field_78296_k += f1;
            }

            if (this.field_78302_t) {
               this.field_78295_j += f1;
               if (flag) {
                  this.field_78295_j -= f1;
                  this.field_78296_k -= f1;
               }

               this.func_181559_a(c0, this.field_78301_u);
               this.field_78295_j -= f1;
               if (flag) {
                  this.field_78295_j += f1;
                  this.field_78296_k += f1;
               }

               f += f1;
            }

            this.doDraw(f);
         }
      }

   }

   protected void doDraw(float p_doDraw_1_) {
      Tessellator tessellator1;
      WorldRenderer vertexbuffer1;
      if (this.field_78299_w) {
         tessellator1 = Tessellator.func_178181_a();
         vertexbuffer1 = tessellator1.func_178180_c();
         GlStateManager.func_179090_x();
         vertexbuffer1.func_181668_a(7, DefaultVertexFormats.field_181705_e);
         vertexbuffer1.func_181662_b((double)this.field_78295_j, (double)(this.field_78296_k + (float)(this.field_78288_b / 2)), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + p_doDraw_1_), (double)(this.field_78296_k + (float)(this.field_78288_b / 2)), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + p_doDraw_1_), (double)(this.field_78296_k + (float)(this.field_78288_b / 2) - 1.0F), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)this.field_78295_j, (double)(this.field_78296_k + (float)(this.field_78288_b / 2) - 1.0F), 0.0D).func_181675_d();
         tessellator1.func_78381_a();
         GlStateManager.func_179098_w();
      }

      if (this.field_78300_v) {
         tessellator1 = Tessellator.func_178181_a();
         vertexbuffer1 = tessellator1.func_178180_c();
         GlStateManager.func_179090_x();
         vertexbuffer1.func_181668_a(7, DefaultVertexFormats.field_181705_e);
         int l = this.field_78300_v ? -1 : 0;
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + (float)l), (double)(this.field_78296_k + (float)this.field_78288_b), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + p_doDraw_1_), (double)(this.field_78296_k + (float)this.field_78288_b), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + p_doDraw_1_), (double)(this.field_78296_k + (float)this.field_78288_b - 1.0F), 0.0D).func_181675_d();
         vertexbuffer1.func_181662_b((double)(this.field_78295_j + (float)l), (double)(this.field_78296_k + (float)this.field_78288_b - 1.0F), 0.0D).func_181675_d();
         tessellator1.func_78381_a();
         GlStateManager.func_179098_w();
      }

      this.field_78295_j += p_doDraw_1_;
   }

   private int func_78274_b(String p_78274_1_, int p_78274_2_, int p_78274_3_, int p_78274_4_, int p_78274_5_, boolean p_78274_6_) {
      if (this.field_78294_m) {
         int i = this.func_78256_a(this.func_147647_b(p_78274_1_));
         p_78274_2_ = p_78274_2_ + p_78274_4_ - i;
      }

      return this.func_180455_b(p_78274_1_, (float)p_78274_2_, (float)p_78274_3_, p_78274_5_, p_78274_6_);
   }

   private int func_180455_b(String p_180455_1_, float p_180455_2_, float p_180455_3_, int p_180455_4_, boolean p_180455_5_) {
      if (p_180455_1_ == null) {
         return 0;
      } else {
         if (this.field_78294_m) {
            p_180455_1_ = this.func_147647_b(p_180455_1_);
         }

         if ((p_180455_4_ & -67108864) == 0) {
            p_180455_4_ |= -16777216;
         }

         if (p_180455_5_) {
            p_180455_4_ = (p_180455_4_ & 16579836) >> 2 | p_180455_4_ & -16777216;
         }

         this.field_78291_n = (float)(p_180455_4_ >> 16 & 255) / 255.0F;
         this.field_78292_o = (float)(p_180455_4_ >> 8 & 255) / 255.0F;
         this.field_78306_p = (float)(p_180455_4_ & 255) / 255.0F;
         this.field_78305_q = (float)(p_180455_4_ >> 24 & 255) / 255.0F;
         this.setColor(this.field_78291_n, this.field_78292_o, this.field_78306_p, this.field_78305_q);
         this.field_78295_j = p_180455_2_;
         this.field_78296_k = p_180455_3_;
         this.func_78255_a(p_180455_1_, p_180455_5_);
         return (int)this.field_78295_j;
      }
   }

   public int func_78256_a(String p_78256_1_) {
      if (p_78256_1_ == null) {
         return 0;
      } else {
         float i = 0.0F;
         boolean flag = false;

         for(int j = 0; j < p_78256_1_.length(); ++j) {
            char c0 = p_78256_1_.charAt(j);
            float k = this.getCharWidthFloat(c0);
            if (k < 0.0F && j < p_78256_1_.length() - 1) {
               ++j;
               c0 = p_78256_1_.charAt(j);
               if (c0 != 'l' && c0 != 'L') {
                  if (c0 == 'r' || c0 == 'R') {
                     flag = false;
                  }
               } else {
                  flag = true;
               }

               k = 0.0F;
            }

            i += k;
            if (flag && k > 0.0F) {
               i += this.field_78293_l ? 1.0F : this.offsetBold;
            }
         }

         return Math.round(i);
      }
   }

   public int func_78263_a(char p_78263_1_) {
      return Math.round(this.getCharWidthFloat(p_78263_1_));
   }

   private float getCharWidthFloat(char p_getCharWidthFloat_1_) {
      if (p_getCharWidthFloat_1_ == 167) {
         return -1.0F;
      } else if (p_getCharWidthFloat_1_ != ' ' && p_getCharWidthFloat_1_ != 160) {
         int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_getCharWidthFloat_1_);
         if (p_getCharWidthFloat_1_ > 0 && i != -1 && !this.field_78293_l) {
            return this.charWidthFloat[i];
         } else if (this.field_78287_e[p_getCharWidthFloat_1_] != 0) {
            int j = this.field_78287_e[p_getCharWidthFloat_1_] >>> 4;
            int k = this.field_78287_e[p_getCharWidthFloat_1_] & 15;
            if (k > 7) {
               k = 15;
               j = 0;
            }

            ++k;
            return (float)((k - j) / 2 + 1);
         } else {
            return 0.0F;
         }
      } else {
         return this.charWidthFloat[32];
      }
   }

   public String func_78269_a(String p_78269_1_, int p_78269_2_) {
      return this.func_78262_a(p_78269_1_, p_78269_2_, false);
   }

   public String func_78262_a(String p_78262_1_, int p_78262_2_, boolean p_78262_3_) {
      StringBuilder stringbuilder = new StringBuilder();
      float i = 0.0F;
      int j = p_78262_3_ ? p_78262_1_.length() - 1 : 0;
      int k = p_78262_3_ ? -1 : 1;
      boolean flag = false;
      boolean flag1 = false;

      for(int l = j; l >= 0 && l < p_78262_1_.length() && i < (float)p_78262_2_; l += k) {
         char c0 = p_78262_1_.charAt(l);
         float i1 = this.getCharWidthFloat(c0);
         if (flag) {
            flag = false;
            if (c0 != 'l' && c0 != 'L') {
               if (c0 == 'r' || c0 == 'R') {
                  flag1 = false;
               }
            } else {
               flag1 = true;
            }
         } else if (i1 < 0.0F) {
            flag = true;
         } else {
            i += i1;
            if (flag1) {
               ++i;
            }
         }

         if (i > (float)p_78262_2_) {
            break;
         }

         if (p_78262_3_) {
            stringbuilder.insert(0, c0);
         } else {
            stringbuilder.append(c0);
         }
      }

      return stringbuilder.toString();
   }

   private String func_78273_d(String p_78273_1_) {
      while(p_78273_1_ != null && p_78273_1_.endsWith("\n")) {
         p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
      }

      return p_78273_1_;
   }

   public void func_78279_b(String p_78279_1_, int p_78279_2_, int p_78279_3_, int p_78279_4_, int p_78279_5_) {
      if (this.blend) {
         GlStateManager.getBlendState(this.oldBlendState);
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 771);
      }

      this.func_78265_b();
      this.field_78304_r = p_78279_5_;
      p_78279_1_ = this.func_78273_d(p_78279_1_);
      this.func_78268_b(p_78279_1_, p_78279_2_, p_78279_3_, p_78279_4_, false);
      if (this.blend) {
         GlStateManager.setBlendState(this.oldBlendState);
      }

   }

   private void func_78268_b(String p_78268_1_, int p_78268_2_, int p_78268_3_, int p_78268_4_, boolean p_78268_5_) {
      for(Iterator i$ = this.func_78271_c(p_78268_1_, p_78268_4_).iterator(); i$.hasNext(); p_78268_3_ += this.field_78288_b) {
         String s = (String)i$.next();
         this.func_78274_b(s, p_78268_2_, p_78268_3_, p_78268_4_, this.field_78304_r, p_78268_5_);
      }

   }

   public int func_78267_b(String p_78267_1_, int p_78267_2_) {
      return this.field_78288_b * this.func_78271_c(p_78267_1_, p_78267_2_).size();
   }

   public void func_78264_a(boolean p_78264_1_) {
      this.field_78293_l = p_78264_1_;
   }

   public boolean func_82883_a() {
      return this.field_78293_l;
   }

   public void func_78275_b(boolean p_78275_1_) {
      this.field_78294_m = p_78275_1_;
   }

   public List<String> func_78271_c(String p_78271_1_, int p_78271_2_) {
      return Arrays.asList(this.func_78280_d(p_78271_1_, p_78271_2_).split("\n"));
   }

   String func_78280_d(String p_78280_1_, int p_78280_2_) {
      if (p_78280_1_.length() <= 1) {
         return p_78280_1_;
      } else {
         int i = this.func_78259_e(p_78280_1_, p_78280_2_);
         if (p_78280_1_.length() <= i) {
            return p_78280_1_;
         } else {
            String s = p_78280_1_.substring(0, i);
            char c0 = p_78280_1_.charAt(i);
            boolean flag = c0 == ' ' || c0 == '\n';
            String s1 = func_78282_e(s) + p_78280_1_.substring(i + (flag ? 1 : 0));
            return s + "\n" + this.func_78280_d(s1, p_78280_2_);
         }
      }
   }

   private int func_78259_e(String p_78259_1_, int p_78259_2_) {
      int i = p_78259_1_.length();
      float j = 0.0F;
      int k = 0;
      int l = -1;

      for(boolean flag = false; k < i; ++k) {
         char c0 = p_78259_1_.charAt(k);
         switch(c0) {
         case '\n':
            --k;
            break;
         case ' ':
            l = k;
         default:
            j += (float)this.func_78263_a(c0);
            if (flag) {
               ++j;
            }
            break;
         case '\u00a7':
            if (k < i - 1) {
               ++k;
               char c1 = p_78259_1_.charAt(k);
               if (c1 != 'l' && c1 != 'L') {
                  if (c1 == 'r' || c1 == 'R' || func_78272_b(c1)) {
                     flag = false;
                  }
               } else {
                  flag = true;
               }
            }
         }

         if (c0 == '\n') {
            ++k;
            l = k;
            break;
         }

         if (Math.round(j) > p_78259_2_) {
            break;
         }
      }

      return k != i && l != -1 && l < k ? l : k;
   }

   private static boolean func_78272_b(char p_78272_0_) {
      return p_78272_0_ >= '0' && p_78272_0_ <= '9' || p_78272_0_ >= 'a' && p_78272_0_ <= 'f' || p_78272_0_ >= 'A' && p_78272_0_ <= 'F';
   }

   private static boolean func_78270_c(char p_78270_0_) {
      return p_78270_0_ >= 'k' && p_78270_0_ <= 'o' || p_78270_0_ >= 'K' && p_78270_0_ <= 'O' || p_78270_0_ == 'r' || p_78270_0_ == 'R';
   }

   public static String func_78282_e(String p_78282_0_) {
      String s = "";
      int i = -1;
      int j = p_78282_0_.length();

      while((i = p_78282_0_.indexOf(167, i + 1)) != -1) {
         if (i < j - 1) {
            char c0 = p_78282_0_.charAt(i + 1);
            if (func_78272_b(c0)) {
               s = "\u00a7" + c0;
            } else if (func_78270_c(c0)) {
               s = s + "\u00a7" + c0;
            }
         }
      }

      return s;
   }

   public boolean func_78260_a() {
      return this.field_78294_m;
   }

   public int func_175064_b(char p_175064_1_) {
      int i = "0123456789abcdef".indexOf(p_175064_1_);
      if (i >= 0 && i < this.field_78285_g.length) {
         int color = this.field_78285_g[i];
         if (Config.isCustomColors()) {
            color = CustomColors.getTextColor(i, color);
         }

         return color;
      } else {
         return 16777215;
      }
   }

   protected void setColor(float p_setColor_1_, float p_setColor_2_, float p_setColor_3_, float p_setColor_4_) {
      GlStateManager.func_179131_c(p_setColor_1_, p_setColor_2_, p_setColor_3_, p_setColor_4_);
   }

   protected void enableAlpha() {
      GlStateManager.func_179141_d();
   }

   protected void bindTexture(ResourceLocation p_bindTexture_1_) {
      this.field_78298_i.func_110577_a(p_bindTexture_1_);
   }

   protected InputStream getResourceInputStream(ResourceLocation p_getResourceInputStream_1_) throws IOException {
      return Minecraft.func_71410_x().func_110442_L().func_110536_a(p_getResourceInputStream_1_).func_110527_b();
   }
}
