package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFX extends Entity {
   protected int field_94054_b;
   protected int field_94055_c;
   protected float field_70548_b;
   protected float field_70549_c;
   protected int field_70546_d;
   protected int field_70547_e;
   protected float field_70544_f;
   protected float field_70545_g;
   protected float field_70552_h;
   protected float field_70553_i;
   protected float field_70551_j;
   protected float field_82339_as;
   protected TextureAtlasSprite field_70550_a;
   public static double field_70556_an;
   public static double field_70554_ao;
   public static double field_70555_ap;

   protected EntityFX(World p_i46352_1_, double p_i46352_2_, double p_i46352_4_, double p_i46352_6_) {
      super(p_i46352_1_);
      this.field_82339_as = 1.0F;
      this.func_70105_a(0.2F, 0.2F);
      this.func_70107_b(p_i46352_2_, p_i46352_4_, p_i46352_6_);
      this.field_70142_S = this.field_70169_q = p_i46352_2_;
      this.field_70137_T = this.field_70167_r = p_i46352_4_;
      this.field_70136_U = this.field_70166_s = p_i46352_6_;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70548_b = this.field_70146_Z.nextFloat() * 3.0F;
      this.field_70549_c = this.field_70146_Z.nextFloat() * 3.0F;
      this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.field_70547_e = (int)(4.0F / (this.field_70146_Z.nextFloat() * 0.9F + 0.1F));
      this.field_70546_d = 0;
   }

   public EntityFX(World p_i1219_1_, double p_i1219_2_, double p_i1219_4_, double p_i1219_6_, double p_i1219_8_, double p_i1219_10_, double p_i1219_12_) {
      this(p_i1219_1_, p_i1219_2_, p_i1219_4_, p_i1219_6_);
      this.field_70159_w = p_i1219_8_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      this.field_70181_x = p_i1219_10_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      this.field_70179_y = p_i1219_12_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      float lvt_14_1_ = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
      float lvt_15_1_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
      this.field_70159_w = this.field_70159_w / (double)lvt_15_1_ * (double)lvt_14_1_ * 0.4000000059604645D;
      this.field_70181_x = this.field_70181_x / (double)lvt_15_1_ * (double)lvt_14_1_ * 0.4000000059604645D + 0.10000000149011612D;
      this.field_70179_y = this.field_70179_y / (double)lvt_15_1_ * (double)lvt_14_1_ * 0.4000000059604645D;
   }

   public EntityFX func_70543_e(float p_70543_1_) {
      this.field_70159_w *= (double)p_70543_1_;
      this.field_70181_x = (this.field_70181_x - 0.10000000149011612D) * (double)p_70543_1_ + 0.10000000149011612D;
      this.field_70179_y *= (double)p_70543_1_;
      return this;
   }

   public EntityFX func_70541_f(float p_70541_1_) {
      this.func_70105_a(0.2F * p_70541_1_, 0.2F * p_70541_1_);
      this.field_70544_f *= p_70541_1_;
      return this;
   }

   public void func_70538_b(float p_70538_1_, float p_70538_2_, float p_70538_3_) {
      this.field_70552_h = p_70538_1_;
      this.field_70553_i = p_70538_2_;
      this.field_70551_j = p_70538_3_;
   }

   public void func_82338_g(float p_82338_1_) {
      if (this.field_82339_as == 1.0F && p_82338_1_ < 1.0F) {
         Minecraft.func_71410_x().field_71452_i.func_178928_b(this);
      } else if (this.field_82339_as < 1.0F && p_82338_1_ == 1.0F) {
         Minecraft.func_71410_x().field_71452_i.func_178931_c(this);
      }

      this.field_82339_as = p_82338_1_;
   }

   public float func_70534_d() {
      return this.field_70552_h;
   }

   public float func_70542_f() {
      return this.field_70553_i;
   }

   public float func_70535_g() {
      return this.field_70551_j;
   }

   public float func_174838_j() {
      return this.field_82339_as;
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      this.field_70181_x -= 0.04D * (double)this.field_70545_g;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9800000190734863D;
      this.field_70181_x *= 0.9800000190734863D;
      this.field_70179_y *= 0.9800000190734863D;
      if (this.field_70122_E) {
         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
      }

   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float lvt_9_1_ = (float)this.field_94054_b / 16.0F;
      float lvt_10_1_ = lvt_9_1_ + 0.0624375F;
      float lvt_11_1_ = (float)this.field_94055_c / 16.0F;
      float lvt_12_1_ = lvt_11_1_ + 0.0624375F;
      float lvt_13_1_ = 0.1F * this.field_70544_f;
      if (this.field_70550_a != null) {
         lvt_9_1_ = this.field_70550_a.func_94209_e();
         lvt_10_1_ = this.field_70550_a.func_94212_f();
         lvt_11_1_ = this.field_70550_a.func_94206_g();
         lvt_12_1_ = this.field_70550_a.func_94210_h();
      }

      float lvt_14_1_ = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_180434_3_ - field_70556_an);
      float lvt_15_1_ = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_180434_3_ - field_70554_ao);
      float lvt_16_1_ = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_180434_3_ - field_70555_ap);
      int lvt_17_1_ = this.func_70070_b(p_180434_3_);
      int lvt_18_1_ = lvt_17_1_ >> 16 & '\uffff';
      int lvt_19_1_ = lvt_17_1_ & '\uffff';
      p_180434_1_.func_181662_b((double)(lvt_14_1_ - p_180434_4_ * lvt_13_1_ - p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ - p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ - p_180434_6_ * lvt_13_1_ - p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_10_1_, (double)lvt_12_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ - p_180434_4_ * lvt_13_1_ + p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ + p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ - p_180434_6_ * lvt_13_1_ + p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_10_1_, (double)lvt_11_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ + p_180434_4_ * lvt_13_1_ + p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ + p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ + p_180434_6_ * lvt_13_1_ + p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_9_1_, (double)lvt_11_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ + p_180434_4_ * lvt_13_1_ - p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ - p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ + p_180434_6_ * lvt_13_1_ - p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_9_1_, (double)lvt_12_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
   }

   public int func_70537_b() {
      return 0;
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   public void func_180435_a(TextureAtlasSprite p_180435_1_) {
      int lvt_2_1_ = this.func_70537_b();
      if (lvt_2_1_ == 1) {
         this.field_70550_a = p_180435_1_;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public void func_70536_a(int p_70536_1_) {
      if (this.func_70537_b() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.field_94054_b = p_70536_1_ % 16;
         this.field_94055_c = p_70536_1_ / 16;
      }
   }

   public void func_94053_h() {
      ++this.field_94054_b;
   }

   public boolean func_70075_an() {
      return false;
   }

   public String toString() {
      return this.getClass().getSimpleName() + ", Pos (" + this.field_70165_t + "," + this.field_70163_u + "," + this.field_70161_v + "), RGBA (" + this.field_70552_h + "," + this.field_70553_i + "," + this.field_70551_j + "," + this.field_82339_as + "), Age " + this.field_70546_d;
   }
}
