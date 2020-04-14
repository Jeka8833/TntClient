package net.minecraft.client.renderer;

import com.google.common.primitives.Floats;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.optifine.SmartAnimations;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {
   private ByteBuffer field_179001_a;
   public IntBuffer field_178999_b;
   private ShortBuffer field_181676_c;
   public FloatBuffer field_179000_c;
   public int field_178997_d;
   private VertexFormatElement field_181677_f;
   private int field_181678_g;
   private boolean field_78939_q;
   public int field_179006_k;
   private double field_179004_l;
   private double field_179005_m;
   private double field_179002_n;
   private VertexFormat field_179011_q;
   private boolean field_179010_r;
   private EnumWorldBlockLayer blockLayer = null;
   private boolean[] drawnIcons = new boolean[256];
   private TextureAtlasSprite[] quadSprites = null;
   private TextureAtlasSprite[] quadSpritesPrev = null;
   private TextureAtlasSprite quadSprite = null;
   public SVertexBuilder sVertexBuilder;
   public RenderEnv renderEnv = null;
   public BitSet animatedSprites = null;
   public BitSet animatedSpritesCached = new BitSet();
   private boolean modeTriangles = false;
   private ByteBuffer byteBufferTriangles;

   public WorldRenderer(int p_i46275_1_) {
      this.field_179001_a = GLAllocation.func_74524_c(p_i46275_1_ * 4);
      this.field_178999_b = this.field_179001_a.asIntBuffer();
      this.field_181676_c = this.field_179001_a.asShortBuffer();
      this.field_179000_c = this.field_179001_a.asFloatBuffer();
      SVertexBuilder.initVertexBuilder(this);
   }

   private void func_181670_b(int p_181670_1_) {
      if (p_181670_1_ > this.field_178999_b.remaining()) {
         int i = this.field_179001_a.capacity();
         int j = i % 2097152;
         int k = j + (((this.field_178999_b.position() + p_181670_1_) * 4 - j) / 2097152 + 1) * 2097152;
         LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + i + " bytes, new size " + k + " bytes.");
         int l = this.field_178999_b.position();
         ByteBuffer bytebuffer = GLAllocation.func_74524_c(k);
         this.field_179001_a.position(0);
         bytebuffer.put(this.field_179001_a);
         bytebuffer.rewind();
         this.field_179001_a = bytebuffer;
         this.field_179000_c = this.field_179001_a.asFloatBuffer();
         this.field_178999_b = this.field_179001_a.asIntBuffer();
         this.field_178999_b.position(l);
         this.field_181676_c = this.field_179001_a.asShortBuffer();
         this.field_181676_c.position(l << 1);
         if (this.quadSprites != null) {
            TextureAtlasSprite[] sprites = this.quadSprites;
            int quadSize = this.getBufferQuadSize();
            this.quadSprites = new TextureAtlasSprite[quadSize];
            System.arraycopy(sprites, 0, this.quadSprites, 0, Math.min(sprites.length, this.quadSprites.length));
            this.quadSpritesPrev = null;
         }
      }

   }

   public void func_181674_a(float p_181674_1_, float p_181674_2_, float p_181674_3_) {
      int i = this.field_178997_d / 4;
      final float[] afloat = new float[i];

      for(int j = 0; j < i; ++j) {
         afloat[j] = func_181665_a(this.field_179000_c, (float)((double)p_181674_1_ + this.field_179004_l), (float)((double)p_181674_2_ + this.field_179005_m), (float)((double)p_181674_3_ + this.field_179002_n), this.field_179011_q.func_181719_f(), j * this.field_179011_q.func_177338_f());
      }

      Integer[] ainteger = new Integer[i];

      for(int k = 0; k < ainteger.length; ++k) {
         ainteger[k] = k;
      }

      Arrays.sort(ainteger, new Comparator<Integer>() {
         public int compare(Integer p_compare_1_, Integer p_compare_2_) {
            return Floats.compare(afloat[p_compare_2_], afloat[p_compare_1_]);
         }
      });
      BitSet bitset = new BitSet();
      int l = this.field_179011_q.func_177338_f();
      int[] aint = new int[l];

      int i1;
      int ix;
      int k1;
      for(int l1 = 0; (l1 = bitset.nextClearBit(l1)) < ainteger.length; ++l1) {
         i1 = ainteger[l1];
         if (i1 != l1) {
            this.field_178999_b.limit(i1 * l + l);
            this.field_178999_b.position(i1 * l);
            this.field_178999_b.get(aint);
            ix = i1;

            for(k1 = ainteger[i1]; ix != l1; k1 = ainteger[k1]) {
               this.field_178999_b.limit(k1 * l + l);
               this.field_178999_b.position(k1 * l);
               IntBuffer intbuffer = this.field_178999_b.slice();
               this.field_178999_b.limit(ix * l + l);
               this.field_178999_b.position(ix * l);
               this.field_178999_b.put(intbuffer);
               bitset.set(ix);
               ix = k1;
            }

            this.field_178999_b.limit(l1 * l + l);
            this.field_178999_b.position(l1 * l);
            this.field_178999_b.put(aint);
         }

         bitset.set(l1);
      }

      this.field_178999_b.limit(this.field_178999_b.capacity());
      this.field_178999_b.position(this.func_181664_j());
      if (this.quadSprites != null) {
         TextureAtlasSprite[] quadSpritesSorted = new TextureAtlasSprite[this.field_178997_d / 4];
         i1 = this.field_179011_q.func_177338_f() / 4 * 4;

         for(ix = 0; ix < ainteger.length; ++ix) {
            k1 = ainteger[ix];
            quadSpritesSorted[ix] = this.quadSprites[k1];
         }

         System.arraycopy(quadSpritesSorted, 0, this.quadSprites, 0, quadSpritesSorted.length);
      }

   }

   public WorldRenderer.State func_181672_a() {
      this.field_178999_b.rewind();
      int i = this.func_181664_j();
      this.field_178999_b.limit(i);
      int[] aint = new int[i];
      this.field_178999_b.get(aint);
      this.field_178999_b.limit(this.field_178999_b.capacity());
      this.field_178999_b.position(i);
      TextureAtlasSprite[] quadSpritesCopy = null;
      if (this.quadSprites != null) {
         int countQuads = this.field_178997_d / 4;
         quadSpritesCopy = new TextureAtlasSprite[countQuads];
         System.arraycopy(this.quadSprites, 0, quadSpritesCopy, 0, countQuads);
      }

      return new WorldRenderer.State(aint, new VertexFormat(this.field_179011_q), quadSpritesCopy);
   }

   public int func_181664_j() {
      return this.field_178997_d * this.field_179011_q.func_181719_f();
   }

   private static float func_181665_a(FloatBuffer p_181665_0_, float p_181665_1_, float p_181665_2_, float p_181665_3_, int p_181665_4_, int p_181665_5_) {
      float f = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 0);
      float f1 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 1);
      float f2 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 2);
      float f3 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 0);
      float f4 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 1);
      float f5 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 2);
      float f6 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 0);
      float f7 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 1);
      float f8 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 2);
      float f9 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 0);
      float f10 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 1);
      float f11 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 2);
      float f12 = (f + f3 + f6 + f9) * 0.25F - p_181665_1_;
      float f13 = (f1 + f4 + f7 + f10) * 0.25F - p_181665_2_;
      float f14 = (f2 + f5 + f8 + f11) * 0.25F - p_181665_3_;
      return f12 * f12 + f13 * f13 + f14 * f14;
   }

   public void func_178993_a(WorldRenderer.State p_178993_1_) {
      this.field_178999_b.clear();
      this.func_181670_b(p_178993_1_.func_179013_a().length);
      this.field_178999_b.put(p_178993_1_.func_179013_a());
      this.field_178997_d = p_178993_1_.func_179014_c();
      this.field_179011_q = new VertexFormat(p_178993_1_.func_179016_d());
      if (p_178993_1_.stateQuadSprites != null) {
         if (this.quadSprites == null) {
            this.quadSprites = this.quadSpritesPrev;
         }

         if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize()) {
            this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
         }

         TextureAtlasSprite[] src = p_178993_1_.stateQuadSprites;
         System.arraycopy(src, 0, this.quadSprites, 0, src.length);
      } else {
         if (this.quadSprites != null) {
            this.quadSpritesPrev = this.quadSprites;
         }

         this.quadSprites = null;
      }

   }

   public void func_178965_a() {
      this.field_178997_d = 0;
      this.field_181677_f = null;
      this.field_181678_g = 0;
      this.quadSprite = null;
      if (SmartAnimations.isActive()) {
         if (this.animatedSprites == null) {
            this.animatedSprites = this.animatedSpritesCached;
         }

         this.animatedSprites.clear();
      } else if (this.animatedSprites != null) {
         this.animatedSprites = null;
      }

      this.modeTriangles = false;
   }

   public void func_181668_a(int p_181668_1_, VertexFormat p_181668_2_) {
      if (this.field_179010_r) {
         throw new IllegalStateException("Already building!");
      } else {
         this.field_179010_r = true;
         this.func_178965_a();
         this.field_179006_k = p_181668_1_;
         this.field_179011_q = p_181668_2_;
         this.field_181677_f = p_181668_2_.func_177348_c(this.field_181678_g);
         this.field_78939_q = false;
         this.field_179001_a.limit(this.field_179001_a.capacity());
         if (Config.isShaders()) {
            SVertexBuilder.endSetVertexFormat(this);
         }

         if (Config.isMultiTexture()) {
            if (this.blockLayer != null) {
               if (this.quadSprites == null) {
                  this.quadSprites = this.quadSpritesPrev;
               }

               if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize()) {
                  this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
               }
            }
         } else {
            if (this.quadSprites != null) {
               this.quadSpritesPrev = this.quadSprites;
            }

            this.quadSprites = null;
         }

      }
   }

   public WorldRenderer func_181673_a(double p_181673_1_, double p_181673_3_) {
      if (this.quadSprite != null && this.quadSprites != null) {
         p_181673_1_ = (double)this.quadSprite.toSingleU((float)p_181673_1_);
         p_181673_3_ = (double)this.quadSprite.toSingleV((float)p_181673_3_);
         this.quadSprites[this.field_178997_d / 4] = this.quadSprite;
      }

      int i = this.field_178997_d * this.field_179011_q.func_177338_f() + this.field_179011_q.func_181720_d(this.field_181678_g);
      switch(this.field_181677_f.func_177367_b()) {
      case FLOAT:
         this.field_179001_a.putFloat(i, (float)p_181673_1_);
         this.field_179001_a.putFloat(i + 4, (float)p_181673_3_);
         break;
      case UINT:
      case INT:
         this.field_179001_a.putInt(i, (int)p_181673_1_);
         this.field_179001_a.putInt(i + 4, (int)p_181673_3_);
         break;
      case USHORT:
      case SHORT:
         this.field_179001_a.putShort(i, (short)((int)p_181673_3_));
         this.field_179001_a.putShort(i + 2, (short)((int)p_181673_1_));
         break;
      case UBYTE:
      case BYTE:
         this.field_179001_a.put(i, (byte)((int)p_181673_3_));
         this.field_179001_a.put(i + 1, (byte)((int)p_181673_1_));
      }

      this.func_181667_k();
      return this;
   }

   public WorldRenderer func_181671_a(int p_181671_1_, int p_181671_2_) {
      int i = this.field_178997_d * this.field_179011_q.func_177338_f() + this.field_179011_q.func_181720_d(this.field_181678_g);
      switch(this.field_181677_f.func_177367_b()) {
      case FLOAT:
         this.field_179001_a.putFloat(i, (float)p_181671_1_);
         this.field_179001_a.putFloat(i + 4, (float)p_181671_2_);
         break;
      case UINT:
      case INT:
         this.field_179001_a.putInt(i, p_181671_1_);
         this.field_179001_a.putInt(i + 4, p_181671_2_);
         break;
      case USHORT:
      case SHORT:
         this.field_179001_a.putShort(i, (short)p_181671_2_);
         this.field_179001_a.putShort(i + 2, (short)p_181671_1_);
         break;
      case UBYTE:
      case BYTE:
         this.field_179001_a.put(i, (byte)p_181671_2_);
         this.field_179001_a.put(i + 1, (byte)p_181671_1_);
      }

      this.func_181667_k();
      return this;
   }

   public void func_178962_a(int p_178962_1_, int p_178962_2_, int p_178962_3_, int p_178962_4_) {
      int i = (this.field_178997_d - 4) * this.field_179011_q.func_181719_f() + this.field_179011_q.func_177344_b(1) / 4;
      int j = this.field_179011_q.func_177338_f() >> 2;
      this.field_178999_b.put(i, p_178962_1_);
      this.field_178999_b.put(i + j, p_178962_2_);
      this.field_178999_b.put(i + j * 2, p_178962_3_);
      this.field_178999_b.put(i + j * 3, p_178962_4_);
   }

   public void func_178987_a(double p_178987_1_, double p_178987_3_, double p_178987_5_) {
      int i = this.field_179011_q.func_181719_f();
      int j = (this.field_178997_d - 4) * i;

      for(int k = 0; k < 4; ++k) {
         int l = j + k * i;
         int i1 = l + 1;
         int j1 = i1 + 1;
         this.field_178999_b.put(l, Float.floatToRawIntBits((float)(p_178987_1_ + this.field_179004_l) + Float.intBitsToFloat(this.field_178999_b.get(l))));
         this.field_178999_b.put(i1, Float.floatToRawIntBits((float)(p_178987_3_ + this.field_179005_m) + Float.intBitsToFloat(this.field_178999_b.get(i1))));
         this.field_178999_b.put(j1, Float.floatToRawIntBits((float)(p_178987_5_ + this.field_179002_n) + Float.intBitsToFloat(this.field_178999_b.get(j1))));
      }

   }

   public int func_78909_a(int p_78909_1_) {
      return ((this.field_178997_d - p_78909_1_) * this.field_179011_q.func_177338_f() + this.field_179011_q.func_177340_e()) / 4;
   }

   public void func_178978_a(float p_178978_1_, float p_178978_2_, float p_178978_3_, int p_178978_4_) {
      int i = this.func_78909_a(p_178978_4_);
      int j = -1;
      if (!this.field_78939_q) {
         j = this.field_178999_b.get(i);
         int k;
         int l;
         int i1;
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            k = (int)((float)(j & 255) * p_178978_1_);
            l = (int)((float)(j >> 8 & 255) * p_178978_2_);
            i1 = (int)((float)(j >> 16 & 255) * p_178978_3_);
            j &= -16777216;
            j = j | i1 << 16 | l << 8 | k;
         } else {
            k = (int)((float)(j >> 24 & 255) * p_178978_1_);
            l = (int)((float)(j >> 16 & 255) * p_178978_2_);
            i1 = (int)((float)(j >> 8 & 255) * p_178978_3_);
            j &= 255;
            j = j | k << 24 | l << 16 | i1 << 8;
         }
      }

      this.field_178999_b.put(i, j);
   }

   private void func_178988_b(int p_178988_1_, int p_178988_2_) {
      int i = this.func_78909_a(p_178988_2_);
      int j = p_178988_1_ >> 16 & 255;
      int k = p_178988_1_ >> 8 & 255;
      int l = p_178988_1_ & 255;
      int i1 = p_178988_1_ >> 24 & 255;
      this.func_178972_a(i, j, k, l, i1);
   }

   public void func_178994_b(float p_178994_1_, float p_178994_2_, float p_178994_3_, int p_178994_4_) {
      int i = this.func_78909_a(p_178994_4_);
      int j = MathHelper.func_76125_a((int)(p_178994_1_ * 255.0F), 0, 255);
      int k = MathHelper.func_76125_a((int)(p_178994_2_ * 255.0F), 0, 255);
      int l = MathHelper.func_76125_a((int)(p_178994_3_ * 255.0F), 0, 255);
      this.func_178972_a(i, j, k, l, 255);
   }

   public void func_178972_a(int p_178972_1_, int p_178972_2_, int p_178972_3_, int p_178972_4_, int p_178972_5_) {
      if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
         this.field_178999_b.put(p_178972_1_, p_178972_5_ << 24 | p_178972_4_ << 16 | p_178972_3_ << 8 | p_178972_2_);
      } else {
         this.field_178999_b.put(p_178972_1_, p_178972_2_ << 24 | p_178972_3_ << 16 | p_178972_4_ << 8 | p_178972_5_);
      }

   }

   public void func_78914_f() {
      this.field_78939_q = true;
   }

   public WorldRenderer func_181666_a(float p_181666_1_, float p_181666_2_, float p_181666_3_, float p_181666_4_) {
      return this.func_181669_b((int)(p_181666_1_ * 255.0F), (int)(p_181666_2_ * 255.0F), (int)(p_181666_3_ * 255.0F), (int)(p_181666_4_ * 255.0F));
   }

   public WorldRenderer func_181669_b(int p_181669_1_, int p_181669_2_, int p_181669_3_, int p_181669_4_) {
      if (this.field_78939_q) {
         return this;
      } else {
         int i = this.field_178997_d * this.field_179011_q.func_177338_f() + this.field_179011_q.func_181720_d(this.field_181678_g);
         switch(this.field_181677_f.func_177367_b()) {
         case FLOAT:
            this.field_179001_a.putFloat(i, (float)p_181669_1_ / 255.0F);
            this.field_179001_a.putFloat(i + 4, (float)p_181669_2_ / 255.0F);
            this.field_179001_a.putFloat(i + 8, (float)p_181669_3_ / 255.0F);
            this.field_179001_a.putFloat(i + 12, (float)p_181669_4_ / 255.0F);
            break;
         case UINT:
         case INT:
            this.field_179001_a.putFloat(i, (float)p_181669_1_);
            this.field_179001_a.putFloat(i + 4, (float)p_181669_2_);
            this.field_179001_a.putFloat(i + 8, (float)p_181669_3_);
            this.field_179001_a.putFloat(i + 12, (float)p_181669_4_);
            break;
         case USHORT:
         case SHORT:
            this.field_179001_a.putShort(i, (short)p_181669_1_);
            this.field_179001_a.putShort(i + 2, (short)p_181669_2_);
            this.field_179001_a.putShort(i + 4, (short)p_181669_3_);
            this.field_179001_a.putShort(i + 6, (short)p_181669_4_);
            break;
         case UBYTE:
         case BYTE:
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
               this.field_179001_a.put(i, (byte)p_181669_1_);
               this.field_179001_a.put(i + 1, (byte)p_181669_2_);
               this.field_179001_a.put(i + 2, (byte)p_181669_3_);
               this.field_179001_a.put(i + 3, (byte)p_181669_4_);
            } else {
               this.field_179001_a.put(i, (byte)p_181669_4_);
               this.field_179001_a.put(i + 1, (byte)p_181669_3_);
               this.field_179001_a.put(i + 2, (byte)p_181669_2_);
               this.field_179001_a.put(i + 3, (byte)p_181669_1_);
            }
         }

         this.func_181667_k();
         return this;
      }
   }

   public void func_178981_a(int[] p_178981_1_) {
      if (Config.isShaders()) {
         SVertexBuilder.beginAddVertexData(this, p_178981_1_);
      }

      this.func_181670_b(p_178981_1_.length);
      this.field_178999_b.position(this.func_181664_j());
      this.field_178999_b.put(p_178981_1_);
      this.field_178997_d += p_178981_1_.length / this.field_179011_q.func_181719_f();
      if (Config.isShaders()) {
         SVertexBuilder.endAddVertexData(this);
      }

   }

   public void func_181675_d() {
      ++this.field_178997_d;
      this.func_181670_b(this.field_179011_q.func_181719_f());
      this.field_181678_g = 0;
      this.field_181677_f = this.field_179011_q.func_177348_c(this.field_181678_g);
      if (Config.isShaders()) {
         SVertexBuilder.endAddVertex(this);
      }

   }

   public WorldRenderer func_181662_b(double p_181662_1_, double p_181662_3_, double p_181662_5_) {
      if (Config.isShaders()) {
         SVertexBuilder.beginAddVertex(this);
      }

      int i = this.field_178997_d * this.field_179011_q.func_177338_f() + this.field_179011_q.func_181720_d(this.field_181678_g);
      switch(this.field_181677_f.func_177367_b()) {
      case FLOAT:
         this.field_179001_a.putFloat(i, (float)(p_181662_1_ + this.field_179004_l));
         this.field_179001_a.putFloat(i + 4, (float)(p_181662_3_ + this.field_179005_m));
         this.field_179001_a.putFloat(i + 8, (float)(p_181662_5_ + this.field_179002_n));
         break;
      case UINT:
      case INT:
         this.field_179001_a.putInt(i, Float.floatToRawIntBits((float)(p_181662_1_ + this.field_179004_l)));
         this.field_179001_a.putInt(i + 4, Float.floatToRawIntBits((float)(p_181662_3_ + this.field_179005_m)));
         this.field_179001_a.putInt(i + 8, Float.floatToRawIntBits((float)(p_181662_5_ + this.field_179002_n)));
         break;
      case USHORT:
      case SHORT:
         this.field_179001_a.putShort(i, (short)((int)(p_181662_1_ + this.field_179004_l)));
         this.field_179001_a.putShort(i + 2, (short)((int)(p_181662_3_ + this.field_179005_m)));
         this.field_179001_a.putShort(i + 4, (short)((int)(p_181662_5_ + this.field_179002_n)));
         break;
      case UBYTE:
      case BYTE:
         this.field_179001_a.put(i, (byte)((int)(p_181662_1_ + this.field_179004_l)));
         this.field_179001_a.put(i + 1, (byte)((int)(p_181662_3_ + this.field_179005_m)));
         this.field_179001_a.put(i + 2, (byte)((int)(p_181662_5_ + this.field_179002_n)));
      }

      this.func_181667_k();
      return this;
   }

   public void func_178975_e(float p_178975_1_, float p_178975_2_, float p_178975_3_) {
      int i = (byte)((int)(p_178975_1_ * 127.0F)) & 255;
      int j = (byte)((int)(p_178975_2_ * 127.0F)) & 255;
      int k = (byte)((int)(p_178975_3_ * 127.0F)) & 255;
      int l = i | j << 8 | k << 16;
      int i1 = this.field_179011_q.func_177338_f() >> 2;
      int j1 = (this.field_178997_d - 4) * i1 + this.field_179011_q.func_177342_c() / 4;
      this.field_178999_b.put(j1, l);
      this.field_178999_b.put(j1 + i1, l);
      this.field_178999_b.put(j1 + i1 * 2, l);
      this.field_178999_b.put(j1 + i1 * 3, l);
   }

   private void func_181667_k() {
      ++this.field_181678_g;
      this.field_181678_g %= this.field_179011_q.func_177345_h();
      this.field_181677_f = this.field_179011_q.func_177348_c(this.field_181678_g);
      if (this.field_181677_f.func_177375_c() == VertexFormatElement.EnumUsage.PADDING) {
         this.func_181667_k();
      }

   }

   public WorldRenderer func_181663_c(float p_181663_1_, float p_181663_2_, float p_181663_3_) {
      int i = this.field_178997_d * this.field_179011_q.func_177338_f() + this.field_179011_q.func_181720_d(this.field_181678_g);
      switch(this.field_181677_f.func_177367_b()) {
      case FLOAT:
         this.field_179001_a.putFloat(i, p_181663_1_);
         this.field_179001_a.putFloat(i + 4, p_181663_2_);
         this.field_179001_a.putFloat(i + 8, p_181663_3_);
         break;
      case UINT:
      case INT:
         this.field_179001_a.putInt(i, (int)p_181663_1_);
         this.field_179001_a.putInt(i + 4, (int)p_181663_2_);
         this.field_179001_a.putInt(i + 8, (int)p_181663_3_);
         break;
      case USHORT:
      case SHORT:
         this.field_179001_a.putShort(i, (short)((int)(p_181663_1_ * 32767.0F) & '\uffff'));
         this.field_179001_a.putShort(i + 2, (short)((int)(p_181663_2_ * 32767.0F) & '\uffff'));
         this.field_179001_a.putShort(i + 4, (short)((int)(p_181663_3_ * 32767.0F) & '\uffff'));
         break;
      case UBYTE:
      case BYTE:
         this.field_179001_a.put(i, (byte)((int)(p_181663_1_ * 127.0F) & 255));
         this.field_179001_a.put(i + 1, (byte)((int)(p_181663_2_ * 127.0F) & 255));
         this.field_179001_a.put(i + 2, (byte)((int)(p_181663_3_ * 127.0F) & 255));
      }

      this.func_181667_k();
      return this;
   }

   public void func_178969_c(double p_178969_1_, double p_178969_3_, double p_178969_5_) {
      this.field_179004_l = p_178969_1_;
      this.field_179005_m = p_178969_3_;
      this.field_179002_n = p_178969_5_;
   }

   public void func_178977_d() {
      if (!this.field_179010_r) {
         throw new IllegalStateException("Not building!");
      } else {
         this.field_179010_r = false;
         this.field_179001_a.position(0);
         this.field_179001_a.limit(this.func_181664_j() * 4);
      }
   }

   public ByteBuffer func_178966_f() {
      return this.modeTriangles ? this.byteBufferTriangles : this.field_179001_a;
   }

   public VertexFormat func_178973_g() {
      return this.field_179011_q;
   }

   public int func_178989_h() {
      return this.modeTriangles ? this.field_178997_d / 4 * 6 : this.field_178997_d;
   }

   public int func_178979_i() {
      return this.modeTriangles ? 4 : this.field_179006_k;
   }

   public void func_178968_d(int p_178968_1_) {
      for(int i = 0; i < 4; ++i) {
         this.func_178988_b(p_178968_1_, i + 1);
      }

   }

   public void func_178990_f(float p_178990_1_, float p_178990_2_, float p_178990_3_) {
      for(int i = 0; i < 4; ++i) {
         this.func_178994_b(p_178990_1_, p_178990_2_, p_178990_3_, i + 1);
      }

   }

   public void putSprite(TextureAtlasSprite p_putSprite_1_) {
      if (this.animatedSprites != null && p_putSprite_1_ != null && p_putSprite_1_.getAnimationIndex() >= 0) {
         this.animatedSprites.set(p_putSprite_1_.getAnimationIndex());
      }

      if (this.quadSprites != null) {
         int countQuads = this.field_178997_d / 4;
         this.quadSprites[countQuads - 1] = p_putSprite_1_;
      }

   }

   public void setSprite(TextureAtlasSprite p_setSprite_1_) {
      if (this.animatedSprites != null && p_setSprite_1_ != null && p_setSprite_1_.getAnimationIndex() >= 0) {
         this.animatedSprites.set(p_setSprite_1_.getAnimationIndex());
      }

      if (this.quadSprites != null) {
         this.quadSprite = p_setSprite_1_;
      }

   }

   public boolean isMultiTexture() {
      return this.quadSprites != null;
   }

   public void drawMultiTexture() {
      if (this.quadSprites != null) {
         int maxTextureIndex = Config.getMinecraft().func_147117_R().getCountRegisteredSprites();
         if (this.drawnIcons.length <= maxTextureIndex) {
            this.drawnIcons = new boolean[maxTextureIndex + 1];
         }

         Arrays.fill(this.drawnIcons, false);
         int texSwitch = 0;
         int grassOverlayIndex = -1;
         int countQuads = this.field_178997_d / 4;

         for(int i = 0; i < countQuads; ++i) {
            TextureAtlasSprite icon = this.quadSprites[i];
            if (icon != null) {
               int iconIndex = icon.getIndexInMap();
               if (!this.drawnIcons[iconIndex]) {
                  if (icon == TextureUtils.iconGrassSideOverlay) {
                     if (grassOverlayIndex < 0) {
                        grassOverlayIndex = i;
                     }
                  } else {
                     i = this.drawForIcon(icon, i) - 1;
                     ++texSwitch;
                     if (this.blockLayer != EnumWorldBlockLayer.TRANSLUCENT) {
                        this.drawnIcons[iconIndex] = true;
                     }
                  }
               }
            }
         }

         if (grassOverlayIndex >= 0) {
            this.drawForIcon(TextureUtils.iconGrassSideOverlay, grassOverlayIndex);
            ++texSwitch;
         }

         if (texSwitch > 0) {
         }

      }
   }

   private int drawForIcon(TextureAtlasSprite p_drawForIcon_1_, int p_drawForIcon_2_) {
      GL11.glBindTexture(3553, p_drawForIcon_1_.glSpriteTextureId);
      int firstRegionEnd = -1;
      int lastPos = -1;
      int countQuads = this.field_178997_d / 4;

      for(int i = p_drawForIcon_2_; i < countQuads; ++i) {
         TextureAtlasSprite ts = this.quadSprites[i];
         if (ts == p_drawForIcon_1_) {
            if (lastPos < 0) {
               lastPos = i;
            }
         } else if (lastPos >= 0) {
            this.draw(lastPos, i);
            if (this.blockLayer == EnumWorldBlockLayer.TRANSLUCENT) {
               return i;
            }

            lastPos = -1;
            if (firstRegionEnd < 0) {
               firstRegionEnd = i;
            }
         }
      }

      if (lastPos >= 0) {
         this.draw(lastPos, countQuads);
      }

      if (firstRegionEnd < 0) {
         firstRegionEnd = countQuads;
      }

      return firstRegionEnd;
   }

   private void draw(int p_draw_1_, int p_draw_2_) {
      int vxQuadCount = p_draw_2_ - p_draw_1_;
      if (vxQuadCount > 0) {
         int startVertex = p_draw_1_ * 4;
         int vxCount = vxQuadCount * 4;
         GL11.glDrawArrays(this.field_179006_k, startVertex, vxCount);
      }
   }

   public void setBlockLayer(EnumWorldBlockLayer p_setBlockLayer_1_) {
      this.blockLayer = p_setBlockLayer_1_;
      if (p_setBlockLayer_1_ == null) {
         if (this.quadSprites != null) {
            this.quadSpritesPrev = this.quadSprites;
         }

         this.quadSprites = null;
         this.quadSprite = null;
      }

   }

   private int getBufferQuadSize() {
      int quadSize = this.field_178999_b.capacity() * 4 / (this.field_179011_q.func_181719_f() * 4);
      return quadSize;
   }

   public RenderEnv getRenderEnv(IBlockState p_getRenderEnv_1_, BlockPos p_getRenderEnv_2_) {
      if (this.renderEnv == null) {
         this.renderEnv = new RenderEnv(p_getRenderEnv_1_, p_getRenderEnv_2_);
         return this.renderEnv;
      } else {
         this.renderEnv.reset(p_getRenderEnv_1_, p_getRenderEnv_2_);
         return this.renderEnv;
      }
   }

   public boolean isDrawing() {
      return this.field_179010_r;
   }

   public double getXOffset() {
      return this.field_179004_l;
   }

   public double getYOffset() {
      return this.field_179005_m;
   }

   public double getZOffset() {
      return this.field_179002_n;
   }

   public EnumWorldBlockLayer getBlockLayer() {
      return this.blockLayer;
   }

   public void putColorMultiplierRgba(float p_putColorMultiplierRgba_1_, float p_putColorMultiplierRgba_2_, float p_putColorMultiplierRgba_3_, float p_putColorMultiplierRgba_4_, int p_putColorMultiplierRgba_5_) {
      int index = this.func_78909_a(p_putColorMultiplierRgba_5_);
      int col = -1;
      if (!this.field_78939_q) {
         col = this.field_178999_b.get(index);
         int r;
         int g;
         int b;
         int a;
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            r = (int)((float)(col & 255) * p_putColorMultiplierRgba_1_);
            g = (int)((float)(col >> 8 & 255) * p_putColorMultiplierRgba_2_);
            b = (int)((float)(col >> 16 & 255) * p_putColorMultiplierRgba_3_);
            a = (int)((float)(col >> 24 & 255) * p_putColorMultiplierRgba_4_);
            col = a << 24 | b << 16 | g << 8 | r;
         } else {
            r = (int)((float)(col >> 24 & 255) * p_putColorMultiplierRgba_1_);
            g = (int)((float)(col >> 16 & 255) * p_putColorMultiplierRgba_2_);
            b = (int)((float)(col >> 8 & 255) * p_putColorMultiplierRgba_3_);
            a = (int)((float)(col & 255) * p_putColorMultiplierRgba_4_);
            col = r << 24 | g << 16 | b << 8 | a;
         }
      }

      this.field_178999_b.put(index, col);
   }

   public void quadsToTriangles() {
      if (this.field_179006_k == 7) {
         if (this.byteBufferTriangles == null) {
            this.byteBufferTriangles = GLAllocation.func_74524_c(this.field_179001_a.capacity() * 2);
         }

         if (this.byteBufferTriangles.capacity() < this.field_179001_a.capacity() * 2) {
            this.byteBufferTriangles = GLAllocation.func_74524_c(this.field_179001_a.capacity() * 2);
         }

         int vertexSize = this.field_179011_q.func_177338_f();
         int limit = this.field_179001_a.limit();
         this.field_179001_a.rewind();
         this.byteBufferTriangles.clear();

         for(int v = 0; v < this.field_178997_d; v += 4) {
            this.field_179001_a.limit((v + 3) * vertexSize);
            this.field_179001_a.position(v * vertexSize);
            this.byteBufferTriangles.put(this.field_179001_a);
            this.field_179001_a.limit((v + 1) * vertexSize);
            this.field_179001_a.position(v * vertexSize);
            this.byteBufferTriangles.put(this.field_179001_a);
            this.field_179001_a.limit((v + 2 + 2) * vertexSize);
            this.field_179001_a.position((v + 2) * vertexSize);
            this.byteBufferTriangles.put(this.field_179001_a);
         }

         this.field_179001_a.limit(limit);
         this.field_179001_a.rewind();
         this.byteBufferTriangles.flip();
         this.modeTriangles = true;
      }
   }

   public boolean isColorDisabled() {
      return this.field_78939_q;
   }

   public class State {
      private final int[] field_179019_b;
      private final VertexFormat field_179018_e;
      private TextureAtlasSprite[] stateQuadSprites;

      public State(int[] p_i1_2_, VertexFormat p_i1_3_, TextureAtlasSprite[] p_i1_4_) {
         this.field_179019_b = p_i1_2_;
         this.field_179018_e = p_i1_3_;
         this.stateQuadSprites = p_i1_4_;
      }

      public State(int[] p_i46453_2_, VertexFormat p_i46453_3_) {
         this.field_179019_b = p_i46453_2_;
         this.field_179018_e = p_i46453_3_;
      }

      public int[] func_179013_a() {
         return this.field_179019_b;
      }

      public int func_179014_c() {
         return this.field_179019_b.length / this.field_179018_e.func_181719_f();
      }

      public VertexFormat func_179016_d() {
         return this.field_179018_e;
      }
   }
}
