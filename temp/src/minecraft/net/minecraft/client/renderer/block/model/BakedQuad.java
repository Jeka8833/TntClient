package net.minecraft.client.renderer.block.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.IVertexConsumer;
import net.minecraftforge.client.model.pipeline.IVertexProducer;
import net.optifine.model.QuadBounds;
import net.optifine.reflect.Reflector;

public class BakedQuad implements IVertexProducer {
   protected int[] field_178215_a;
   protected final int field_178213_b;
   protected EnumFacing field_178214_c;
   protected TextureAtlasSprite sprite;
   private int[] vertexDataSingle = null;
   private QuadBounds quadBounds;
   private boolean quadEmissiveChecked;
   private BakedQuad quadEmissive;

   public BakedQuad(int[] p_i3_1_, int p_i3_2_, EnumFacing p_i3_3_, TextureAtlasSprite p_i3_4_) {
      this.field_178215_a = p_i3_1_;
      this.field_178213_b = p_i3_2_;
      this.field_178214_c = p_i3_3_;
      this.sprite = p_i3_4_;
      this.fixVertexData();
   }

   public BakedQuad(int[] p_i46232_1_, int p_i46232_2_, EnumFacing p_i46232_3_) {
      this.field_178215_a = p_i46232_1_;
      this.field_178213_b = p_i46232_2_;
      this.field_178214_c = p_i46232_3_;
      this.fixVertexData();
   }

   public TextureAtlasSprite getSprite() {
      if (this.sprite == null) {
         this.sprite = getSpriteByUv(this.func_178209_a());
      }

      return this.sprite;
   }

   public int[] func_178209_a() {
      this.fixVertexData();
      return this.field_178215_a;
   }

   public boolean func_178212_b() {
      return this.field_178213_b != -1;
   }

   public int func_178211_c() {
      return this.field_178213_b;
   }

   public EnumFacing func_178210_d() {
      if (this.field_178214_c == null) {
         this.field_178214_c = FaceBakery.func_178410_a(this.func_178209_a());
      }

      return this.field_178214_c;
   }

   public int[] getVertexDataSingle() {
      if (this.vertexDataSingle == null) {
         this.vertexDataSingle = makeVertexDataSingle(this.func_178209_a(), this.getSprite());
      }

      return this.vertexDataSingle;
   }

   private static int[] makeVertexDataSingle(int[] p_makeVertexDataSingle_0_, TextureAtlasSprite p_makeVertexDataSingle_1_) {
      int[] vdSingle = (int[])p_makeVertexDataSingle_0_.clone();
      int ku = p_makeVertexDataSingle_1_.sheetWidth / p_makeVertexDataSingle_1_.func_94211_a();
      int kv = p_makeVertexDataSingle_1_.sheetHeight / p_makeVertexDataSingle_1_.func_94216_b();
      int step = vdSingle.length / 4;

      for(int i = 0; i < 4; ++i) {
         int pos = i * step;
         float tu = Float.intBitsToFloat(vdSingle[pos + 4]);
         float tv = Float.intBitsToFloat(vdSingle[pos + 4 + 1]);
         float u = p_makeVertexDataSingle_1_.toSingleU(tu);
         float v = p_makeVertexDataSingle_1_.toSingleV(tv);
         vdSingle[pos + 4] = Float.floatToRawIntBits(u);
         vdSingle[pos + 4 + 1] = Float.floatToRawIntBits(v);
      }

      return vdSingle;
   }

   public void pipe(IVertexConsumer p_pipe_1_) {
      Reflector.callVoid(Reflector.LightUtil_putBakedQuad, p_pipe_1_, this);
   }

   private static TextureAtlasSprite getSpriteByUv(int[] p_getSpriteByUv_0_) {
      float uMin = 1.0F;
      float vMin = 1.0F;
      float uMax = 0.0F;
      float vMax = 0.0F;
      int step = p_getSpriteByUv_0_.length / 4;

      for(int i = 0; i < 4; ++i) {
         int pos = i * step;
         float tu = Float.intBitsToFloat(p_getSpriteByUv_0_[pos + 4]);
         float tv = Float.intBitsToFloat(p_getSpriteByUv_0_[pos + 4 + 1]);
         uMin = Math.min(uMin, tu);
         vMin = Math.min(vMin, tv);
         uMax = Math.max(uMax, tu);
         vMax = Math.max(vMax, tv);
      }

      float uMid = (uMin + uMax) / 2.0F;
      float vMid = (vMin + vMax) / 2.0F;
      TextureAtlasSprite spriteUv = Minecraft.func_71410_x().func_147117_R().getIconByUV((double)uMid, (double)vMid);
      return spriteUv;
   }

   protected void fixVertexData() {
      if (Config.isShaders()) {
         if (this.field_178215_a.length == 28) {
            this.field_178215_a = expandVertexData(this.field_178215_a);
         }
      } else if (this.field_178215_a.length == 56) {
         this.field_178215_a = compactVertexData(this.field_178215_a);
      }

   }

   private static int[] expandVertexData(int[] p_expandVertexData_0_) {
      int step = p_expandVertexData_0_.length / 4;
      int stepNew = step * 2;
      int[] vdNew = new int[stepNew * 4];

      for(int i = 0; i < 4; ++i) {
         System.arraycopy(p_expandVertexData_0_, i * step, vdNew, i * stepNew, step);
      }

      return vdNew;
   }

   private static int[] compactVertexData(int[] p_compactVertexData_0_) {
      int step = p_compactVertexData_0_.length / 4;
      int stepNew = step / 2;
      int[] vdNew = new int[stepNew * 4];

      for(int i = 0; i < 4; ++i) {
         System.arraycopy(p_compactVertexData_0_, i * step, vdNew, i * stepNew, stepNew);
      }

      return vdNew;
   }

   public QuadBounds getQuadBounds() {
      if (this.quadBounds == null) {
         this.quadBounds = new QuadBounds(this.func_178209_a());
      }

      return this.quadBounds;
   }

   public float getMidX() {
      QuadBounds qb = this.getQuadBounds();
      return (qb.getMaxX() + qb.getMinX()) / 2.0F;
   }

   public double getMidY() {
      QuadBounds qb = this.getQuadBounds();
      return (double)((qb.getMaxY() + qb.getMinY()) / 2.0F);
   }

   public double getMidZ() {
      QuadBounds qb = this.getQuadBounds();
      return (double)((qb.getMaxZ() + qb.getMinZ()) / 2.0F);
   }

   public boolean isFaceQuad() {
      QuadBounds qb = this.getQuadBounds();
      return qb.isFaceQuad(this.field_178214_c);
   }

   public boolean isFullQuad() {
      QuadBounds qb = this.getQuadBounds();
      return qb.isFullQuad(this.field_178214_c);
   }

   public boolean isFullFaceQuad() {
      return this.isFullQuad() && this.isFaceQuad();
   }

   public BakedQuad getQuadEmissive() {
      if (this.quadEmissiveChecked) {
         return this.quadEmissive;
      } else {
         if (this.quadEmissive == null && this.sprite != null && this.sprite.spriteEmissive != null) {
            this.quadEmissive = new BreakingFour(this, this.sprite.spriteEmissive);
         }

         this.quadEmissiveChecked = true;
         return this.quadEmissive;
      }
   }

   public String toString() {
      return "vertex: " + this.field_178215_a.length / 7 + ", tint: " + this.field_178213_b + ", facing: " + this.field_178214_c + ", sprite: " + this.sprite;
   }
}
