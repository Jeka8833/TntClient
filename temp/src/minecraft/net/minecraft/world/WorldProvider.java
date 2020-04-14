package net.minecraft.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderDebug;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;

public abstract class WorldProvider {
   public static final float[] field_111203_a = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
   protected World field_76579_a;
   private WorldType field_76577_b;
   private String field_82913_c;
   protected WorldChunkManager field_76578_c;
   protected boolean field_76575_d;
   protected boolean field_76576_e;
   protected final float[] field_76573_f = new float[16];
   protected int field_76574_g;
   private final float[] field_76580_h = new float[4];

   public final void func_76558_a(World p_76558_1_) {
      this.field_76579_a = p_76558_1_;
      this.field_76577_b = p_76558_1_.func_72912_H().func_76067_t();
      this.field_82913_c = p_76558_1_.func_72912_H().func_82571_y();
      this.func_76572_b();
      this.func_76556_a();
   }

   protected void func_76556_a() {
      float lvt_1_1_ = 0.0F;

      for(int lvt_2_1_ = 0; lvt_2_1_ <= 15; ++lvt_2_1_) {
         float lvt_3_1_ = 1.0F - (float)lvt_2_1_ / 15.0F;
         this.field_76573_f[lvt_2_1_] = (1.0F - lvt_3_1_) / (lvt_3_1_ * 3.0F + 1.0F) * (1.0F - lvt_1_1_) + lvt_1_1_;
      }

   }

   protected void func_76572_b() {
      WorldType lvt_1_1_ = this.field_76579_a.func_72912_H().func_76067_t();
      if (lvt_1_1_ == WorldType.field_77138_c) {
         FlatGeneratorInfo lvt_2_1_ = FlatGeneratorInfo.func_82651_a(this.field_76579_a.func_72912_H().func_82571_y());
         this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.func_180276_a(lvt_2_1_.func_82648_a(), BiomeGenBase.field_180279_ad), 0.5F);
      } else if (lvt_1_1_ == WorldType.field_180272_g) {
         this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.field_76772_c, 0.0F);
      } else {
         this.field_76578_c = new WorldChunkManager(this.field_76579_a);
      }

   }

   public IChunkProvider func_76555_c() {
      if (this.field_76577_b == WorldType.field_77138_c) {
         return new ChunkProviderFlat(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r(), this.field_82913_c);
      } else if (this.field_76577_b == WorldType.field_180272_g) {
         return new ChunkProviderDebug(this.field_76579_a);
      } else {
         return this.field_76577_b == WorldType.field_180271_f ? new ChunkProviderGenerate(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r(), this.field_82913_c) : new ChunkProviderGenerate(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r(), this.field_82913_c);
      }
   }

   public boolean func_76566_a(int p_76566_1_, int p_76566_2_) {
      return this.field_76579_a.func_175703_c(new BlockPos(p_76566_1_, 0, p_76566_2_)) == Blocks.field_150349_c;
   }

   public float func_76563_a(long p_76563_1_, float p_76563_3_) {
      int lvt_4_1_ = (int)(p_76563_1_ % 24000L);
      float lvt_5_1_ = ((float)lvt_4_1_ + p_76563_3_) / 24000.0F - 0.25F;
      if (lvt_5_1_ < 0.0F) {
         ++lvt_5_1_;
      }

      if (lvt_5_1_ > 1.0F) {
         --lvt_5_1_;
      }

      float lvt_6_1_ = lvt_5_1_;
      lvt_5_1_ = 1.0F - (float)((Math.cos((double)lvt_5_1_ * 3.141592653589793D) + 1.0D) / 2.0D);
      lvt_5_1_ = lvt_6_1_ + (lvt_5_1_ - lvt_6_1_) / 3.0F;
      return lvt_5_1_;
   }

   public int func_76559_b(long p_76559_1_) {
      return (int)(p_76559_1_ / 24000L % 8L + 8L) % 8;
   }

   public boolean func_76569_d() {
      return true;
   }

   public float[] func_76560_a(float p_76560_1_, float p_76560_2_) {
      float lvt_3_1_ = 0.4F;
      float lvt_4_1_ = MathHelper.func_76134_b(p_76560_1_ * 3.1415927F * 2.0F) - 0.0F;
      float lvt_5_1_ = -0.0F;
      if (lvt_4_1_ >= lvt_5_1_ - lvt_3_1_ && lvt_4_1_ <= lvt_5_1_ + lvt_3_1_) {
         float lvt_6_1_ = (lvt_4_1_ - lvt_5_1_) / lvt_3_1_ * 0.5F + 0.5F;
         float lvt_7_1_ = 1.0F - (1.0F - MathHelper.func_76126_a(lvt_6_1_ * 3.1415927F)) * 0.99F;
         lvt_7_1_ *= lvt_7_1_;
         this.field_76580_h[0] = lvt_6_1_ * 0.3F + 0.7F;
         this.field_76580_h[1] = lvt_6_1_ * lvt_6_1_ * 0.7F + 0.2F;
         this.field_76580_h[2] = lvt_6_1_ * lvt_6_1_ * 0.0F + 0.2F;
         this.field_76580_h[3] = lvt_7_1_;
         return this.field_76580_h;
      } else {
         return null;
      }
   }

   public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
      float lvt_3_1_ = MathHelper.func_76134_b(p_76562_1_ * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      lvt_3_1_ = MathHelper.func_76131_a(lvt_3_1_, 0.0F, 1.0F);
      float lvt_4_1_ = 0.7529412F;
      float lvt_5_1_ = 0.84705883F;
      float lvt_6_1_ = 1.0F;
      lvt_4_1_ *= lvt_3_1_ * 0.94F + 0.06F;
      lvt_5_1_ *= lvt_3_1_ * 0.94F + 0.06F;
      lvt_6_1_ *= lvt_3_1_ * 0.91F + 0.09F;
      return new Vec3((double)lvt_4_1_, (double)lvt_5_1_, (double)lvt_6_1_);
   }

   public boolean func_76567_e() {
      return true;
   }

   public static WorldProvider func_76570_a(int p_76570_0_) {
      if (p_76570_0_ == -1) {
         return new WorldProviderHell();
      } else if (p_76570_0_ == 0) {
         return new WorldProviderSurface();
      } else {
         return p_76570_0_ == 1 ? new WorldProviderEnd() : null;
      }
   }

   public float func_76571_f() {
      return 128.0F;
   }

   public boolean func_76561_g() {
      return true;
   }

   public BlockPos func_177496_h() {
      return null;
   }

   public int func_76557_i() {
      return this.field_76577_b == WorldType.field_77138_c ? 4 : this.field_76579_a.func_181545_F() + 1;
   }

   public double func_76565_k() {
      return this.field_76577_b == WorldType.field_77138_c ? 1.0D : 0.03125D;
   }

   public boolean func_76568_b(int p_76568_1_, int p_76568_2_) {
      return false;
   }

   public abstract String func_80007_l();

   public abstract String func_177498_l();

   public WorldChunkManager func_177499_m() {
      return this.field_76578_c;
   }

   public boolean func_177500_n() {
      return this.field_76575_d;
   }

   public boolean func_177495_o() {
      return this.field_76576_e;
   }

   public float[] func_177497_p() {
      return this.field_76573_f;
   }

   public int func_177502_q() {
      return this.field_76574_g;
   }

   public WorldBorder func_177501_r() {
      return new WorldBorder();
   }
}
