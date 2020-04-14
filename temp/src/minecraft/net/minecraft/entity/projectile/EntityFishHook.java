package net.minecraft.entity.projectile;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFishHook extends Entity {
   private static final List<WeightedRandomFishable> field_146039_d;
   private static final List<WeightedRandomFishable> field_146041_e;
   private static final List<WeightedRandomFishable> field_146036_f;
   private int field_146037_g;
   private int field_146048_h;
   private int field_146050_i;
   private Block field_146046_j;
   private boolean field_146051_au;
   public int field_146044_a;
   public EntityPlayer field_146042_b;
   private int field_146049_av;
   private int field_146047_aw;
   private int field_146045_ax;
   private int field_146040_ay;
   private int field_146038_az;
   private float field_146054_aA;
   public Entity field_146043_c;
   private int field_146055_aB;
   private double field_146056_aC;
   private double field_146057_aD;
   private double field_146058_aE;
   private double field_146059_aF;
   private double field_146060_aG;
   private double field_146061_aH;
   private double field_146052_aI;
   private double field_146053_aJ;

   public static List<WeightedRandomFishable> func_174855_j() {
      return field_146036_f;
   }

   public EntityFishHook(World p_i1764_1_) {
      super(p_i1764_1_);
      this.field_146037_g = -1;
      this.field_146048_h = -1;
      this.field_146050_i = -1;
      this.func_70105_a(0.25F, 0.25F);
      this.field_70158_ak = true;
   }

   public EntityFishHook(World p_i1765_1_, double p_i1765_2_, double p_i1765_4_, double p_i1765_6_, EntityPlayer p_i1765_8_) {
      this(p_i1765_1_);
      this.func_70107_b(p_i1765_2_, p_i1765_4_, p_i1765_6_);
      this.field_70158_ak = true;
      this.field_146042_b = p_i1765_8_;
      p_i1765_8_.field_71104_cf = this;
   }

   public EntityFishHook(World p_i1766_1_, EntityPlayer p_i1766_2_) {
      super(p_i1766_1_);
      this.field_146037_g = -1;
      this.field_146048_h = -1;
      this.field_146050_i = -1;
      this.field_70158_ak = true;
      this.field_146042_b = p_i1766_2_;
      this.field_146042_b.field_71104_cf = this;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70012_b(p_i1766_2_.field_70165_t, p_i1766_2_.field_70163_u + (double)p_i1766_2_.func_70047_e(), p_i1766_2_.field_70161_v, p_i1766_2_.field_70177_z, p_i1766_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float lvt_3_1_ = 0.4F;
      this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * lvt_3_1_);
      this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * lvt_3_1_);
      this.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * lvt_3_1_);
      this.func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
   }

   protected void func_70088_a() {
   }

   public boolean func_70112_a(double p_70112_1_) {
      double lvt_3_1_ = this.func_174813_aQ().func_72320_b() * 4.0D;
      if (Double.isNaN(lvt_3_1_)) {
         lvt_3_1_ = 4.0D;
      }

      lvt_3_1_ *= 64.0D;
      return p_70112_1_ < lvt_3_1_ * lvt_3_1_;
   }

   public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_) {
      float lvt_9_1_ = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
      p_146035_1_ /= (double)lvt_9_1_;
      p_146035_3_ /= (double)lvt_9_1_;
      p_146035_5_ /= (double)lvt_9_1_;
      p_146035_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_1_ *= (double)p_146035_7_;
      p_146035_3_ *= (double)p_146035_7_;
      p_146035_5_ *= (double)p_146035_7_;
      this.field_70159_w = p_146035_1_;
      this.field_70181_x = p_146035_3_;
      this.field_70179_y = p_146035_5_;
      float lvt_10_1_ = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
      this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_146035_1_, p_146035_5_) * 180.0D / 3.1415927410125732D);
      this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_146035_3_, (double)lvt_10_1_) * 180.0D / 3.1415927410125732D);
      this.field_146049_av = 0;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.field_146056_aC = p_180426_1_;
      this.field_146057_aD = p_180426_3_;
      this.field_146058_aE = p_180426_5_;
      this.field_146059_aF = (double)p_180426_7_;
      this.field_146060_aG = (double)p_180426_8_;
      this.field_146055_aB = p_180426_9_;
      this.field_70159_w = this.field_146061_aH;
      this.field_70181_x = this.field_146052_aI;
      this.field_70179_y = this.field_146053_aJ;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_146061_aH = this.field_70159_w = p_70016_1_;
      this.field_146052_aI = this.field_70181_x = p_70016_3_;
      this.field_146053_aJ = this.field_70179_y = p_70016_5_;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_146055_aB > 0) {
         double lvt_1_1_ = this.field_70165_t + (this.field_146056_aC - this.field_70165_t) / (double)this.field_146055_aB;
         double lvt_3_1_ = this.field_70163_u + (this.field_146057_aD - this.field_70163_u) / (double)this.field_146055_aB;
         double lvt_5_1_ = this.field_70161_v + (this.field_146058_aE - this.field_70161_v) / (double)this.field_146055_aB;
         double lvt_7_1_ = MathHelper.func_76138_g(this.field_146059_aF - (double)this.field_70177_z);
         this.field_70177_z = (float)((double)this.field_70177_z + lvt_7_1_ / (double)this.field_146055_aB);
         this.field_70125_A = (float)((double)this.field_70125_A + (this.field_146060_aG - (double)this.field_70125_A) / (double)this.field_146055_aB);
         --this.field_146055_aB;
         this.func_70107_b(lvt_1_1_, lvt_3_1_, lvt_5_1_);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
      } else {
         if (!this.field_70170_p.field_72995_K) {
            ItemStack lvt_1_2_ = this.field_146042_b.func_71045_bC();
            if (this.field_146042_b.field_70128_L || !this.field_146042_b.func_70089_S() || lvt_1_2_ == null || lvt_1_2_.func_77973_b() != Items.field_151112_aM || this.func_70068_e(this.field_146042_b) > 1024.0D) {
               this.func_70106_y();
               this.field_146042_b.field_71104_cf = null;
               return;
            }

            if (this.field_146043_c != null) {
               if (!this.field_146043_c.field_70128_L) {
                  this.field_70165_t = this.field_146043_c.field_70165_t;
                  double var10002 = (double)this.field_146043_c.field_70131_O;
                  this.field_70163_u = this.field_146043_c.func_174813_aQ().field_72338_b + var10002 * 0.8D;
                  this.field_70161_v = this.field_146043_c.field_70161_v;
                  return;
               }

               this.field_146043_c = null;
            }
         }

         if (this.field_146044_a > 0) {
            --this.field_146044_a;
         }

         if (this.field_146051_au) {
            if (this.field_70170_p.func_180495_p(new BlockPos(this.field_146037_g, this.field_146048_h, this.field_146050_i)).func_177230_c() == this.field_146046_j) {
               ++this.field_146049_av;
               if (this.field_146049_av == 1200) {
                  this.func_70106_y();
               }

               return;
            }

            this.field_146051_au = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_146049_av = 0;
            this.field_146047_aw = 0;
         } else {
            ++this.field_146047_aw;
         }

         Vec3 lvt_1_3_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 lvt_2_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         MovingObjectPosition lvt_3_2_ = this.field_70170_p.func_72933_a(lvt_1_3_, lvt_2_1_);
         lvt_1_3_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         lvt_2_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if (lvt_3_2_ != null) {
            lvt_2_1_ = new Vec3(lvt_3_2_.field_72307_f.field_72450_a, lvt_3_2_.field_72307_f.field_72448_b, lvt_3_2_.field_72307_f.field_72449_c);
         }

         Entity lvt_4_1_ = null;
         List<Entity> lvt_5_2_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
         double lvt_6_1_ = 0.0D;

         double lvt_13_4_;
         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_5_2_.size(); ++lvt_8_1_) {
            Entity lvt_9_1_ = (Entity)lvt_5_2_.get(lvt_8_1_);
            if (lvt_9_1_.func_70067_L() && (lvt_9_1_ != this.field_146042_b || this.field_146047_aw >= 5)) {
               float lvt_10_1_ = 0.3F;
               AxisAlignedBB lvt_11_1_ = lvt_9_1_.func_174813_aQ().func_72314_b((double)lvt_10_1_, (double)lvt_10_1_, (double)lvt_10_1_);
               MovingObjectPosition lvt_12_1_ = lvt_11_1_.func_72327_a(lvt_1_3_, lvt_2_1_);
               if (lvt_12_1_ != null) {
                  lvt_13_4_ = lvt_1_3_.func_72436_e(lvt_12_1_.field_72307_f);
                  if (lvt_13_4_ < lvt_6_1_ || lvt_6_1_ == 0.0D) {
                     lvt_4_1_ = lvt_9_1_;
                     lvt_6_1_ = lvt_13_4_;
                  }
               }
            }
         }

         if (lvt_4_1_ != null) {
            lvt_3_2_ = new MovingObjectPosition(lvt_4_1_);
         }

         if (lvt_3_2_ != null) {
            if (lvt_3_2_.field_72308_g != null) {
               if (lvt_3_2_.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.field_146042_b), 0.0F)) {
                  this.field_146043_c = lvt_3_2_.field_72308_g;
               }
            } else {
               this.field_146051_au = true;
            }
         }

         if (!this.field_146051_au) {
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            float lvt_8_2_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);

            for(this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)lvt_8_2_) * 180.0D / 3.1415927410125732D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
            }

            while(this.field_70125_A - this.field_70127_C >= 180.0F) {
               this.field_70127_C += 360.0F;
            }

            while(this.field_70177_z - this.field_70126_B < -180.0F) {
               this.field_70126_B -= 360.0F;
            }

            while(this.field_70177_z - this.field_70126_B >= 180.0F) {
               this.field_70126_B += 360.0F;
            }

            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
            float lvt_9_2_ = 0.92F;
            if (this.field_70122_E || this.field_70123_F) {
               lvt_9_2_ = 0.5F;
            }

            int lvt_10_2_ = 5;
            double lvt_11_2_ = 0.0D;

            double lvt_19_3_;
            for(int lvt_13_2_ = 0; lvt_13_2_ < lvt_10_2_; ++lvt_13_2_) {
               AxisAlignedBB lvt_14_1_ = this.func_174813_aQ();
               double lvt_15_1_ = lvt_14_1_.field_72337_e - lvt_14_1_.field_72338_b;
               double lvt_17_1_ = lvt_14_1_.field_72338_b + lvt_15_1_ * (double)lvt_13_2_ / (double)lvt_10_2_;
               lvt_19_3_ = lvt_14_1_.field_72338_b + lvt_15_1_ * (double)(lvt_13_2_ + 1) / (double)lvt_10_2_;
               AxisAlignedBB lvt_21_1_ = new AxisAlignedBB(lvt_14_1_.field_72340_a, lvt_17_1_, lvt_14_1_.field_72339_c, lvt_14_1_.field_72336_d, lvt_19_3_, lvt_14_1_.field_72334_f);
               if (this.field_70170_p.func_72830_b(lvt_21_1_, Material.field_151586_h)) {
                  lvt_11_2_ += 1.0D / (double)lvt_10_2_;
               }
            }

            if (!this.field_70170_p.field_72995_K && lvt_11_2_ > 0.0D) {
               WorldServer lvt_13_3_ = (WorldServer)this.field_70170_p;
               int lvt_14_2_ = 1;
               BlockPos lvt_15_2_ = (new BlockPos(this)).func_177984_a();
               if (this.field_70146_Z.nextFloat() < 0.25F && this.field_70170_p.func_175727_C(lvt_15_2_)) {
                  lvt_14_2_ = 2;
               }

               if (this.field_70146_Z.nextFloat() < 0.5F && !this.field_70170_p.func_175678_i(lvt_15_2_)) {
                  --lvt_14_2_;
               }

               if (this.field_146045_ax > 0) {
                  --this.field_146045_ax;
                  if (this.field_146045_ax <= 0) {
                     this.field_146040_ay = 0;
                     this.field_146038_az = 0;
                  }
               } else {
                  float lvt_16_3_;
                  float lvt_18_2_;
                  double lvt_23_2_;
                  Block lvt_25_2_;
                  float lvt_17_3_;
                  double lvt_21_3_;
                  if (this.field_146038_az > 0) {
                     this.field_146038_az -= lvt_14_2_;
                     if (this.field_146038_az <= 0) {
                        this.field_70181_x -= 0.20000000298023224D;
                        this.func_85030_a("random.splash", 0.25F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
                        lvt_16_3_ = (float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b);
                        lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t, (double)(lvt_16_3_ + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D);
                        lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_WAKE, this.field_70165_t, (double)(lvt_16_3_ + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D);
                        this.field_146045_ax = MathHelper.func_76136_a(this.field_70146_Z, 10, 30);
                     } else {
                        this.field_146054_aA = (float)((double)this.field_146054_aA + this.field_70146_Z.nextGaussian() * 4.0D);
                        lvt_16_3_ = this.field_146054_aA * 0.017453292F;
                        lvt_17_3_ = MathHelper.func_76126_a(lvt_16_3_);
                        lvt_18_2_ = MathHelper.func_76134_b(lvt_16_3_);
                        lvt_19_3_ = this.field_70165_t + (double)(lvt_17_3_ * (float)this.field_146038_az * 0.1F);
                        lvt_21_3_ = (double)((float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) + 1.0F);
                        lvt_23_2_ = this.field_70161_v + (double)(lvt_18_2_ * (float)this.field_146038_az * 0.1F);
                        lvt_25_2_ = lvt_13_3_.func_180495_p(new BlockPos((int)lvt_19_3_, (int)lvt_21_3_ - 1, (int)lvt_23_2_)).func_177230_c();
                        if (lvt_25_2_ == Blocks.field_150355_j || lvt_25_2_ == Blocks.field_150358_i) {
                           if (this.field_70146_Z.nextFloat() < 0.15F) {
                              lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_BUBBLE, lvt_19_3_, lvt_21_3_ - 0.10000000149011612D, lvt_23_2_, 1, (double)lvt_17_3_, 0.1D, (double)lvt_18_2_, 0.0D);
                           }

                           float lvt_26_1_ = lvt_17_3_ * 0.04F;
                           float lvt_27_1_ = lvt_18_2_ * 0.04F;
                           lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_WAKE, lvt_19_3_, lvt_21_3_, lvt_23_2_, 0, (double)lvt_27_1_, 0.01D, (double)(-lvt_26_1_), 1.0D);
                           lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_WAKE, lvt_19_3_, lvt_21_3_, lvt_23_2_, 0, (double)(-lvt_27_1_), 0.01D, (double)lvt_26_1_, 1.0D);
                        }
                     }
                  } else if (this.field_146040_ay > 0) {
                     this.field_146040_ay -= lvt_14_2_;
                     lvt_16_3_ = 0.15F;
                     if (this.field_146040_ay < 20) {
                        lvt_16_3_ = (float)((double)lvt_16_3_ + (double)(20 - this.field_146040_ay) * 0.05D);
                     } else if (this.field_146040_ay < 40) {
                        lvt_16_3_ = (float)((double)lvt_16_3_ + (double)(40 - this.field_146040_ay) * 0.02D);
                     } else if (this.field_146040_ay < 60) {
                        lvt_16_3_ = (float)((double)lvt_16_3_ + (double)(60 - this.field_146040_ay) * 0.01D);
                     }

                     if (this.field_70146_Z.nextFloat() < lvt_16_3_) {
                        lvt_17_3_ = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F) * 0.017453292F;
                        lvt_18_2_ = MathHelper.func_151240_a(this.field_70146_Z, 25.0F, 60.0F);
                        lvt_19_3_ = this.field_70165_t + (double)(MathHelper.func_76126_a(lvt_17_3_) * lvt_18_2_ * 0.1F);
                        lvt_21_3_ = (double)((float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) + 1.0F);
                        lvt_23_2_ = this.field_70161_v + (double)(MathHelper.func_76134_b(lvt_17_3_) * lvt_18_2_ * 0.1F);
                        lvt_25_2_ = lvt_13_3_.func_180495_p(new BlockPos((int)lvt_19_3_, (int)lvt_21_3_ - 1, (int)lvt_23_2_)).func_177230_c();
                        if (lvt_25_2_ == Blocks.field_150355_j || lvt_25_2_ == Blocks.field_150358_i) {
                           lvt_13_3_.func_175739_a(EnumParticleTypes.WATER_SPLASH, lvt_19_3_, lvt_21_3_, lvt_23_2_, 2 + this.field_70146_Z.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
                        }
                     }

                     if (this.field_146040_ay <= 0) {
                        this.field_146054_aA = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F);
                        this.field_146038_az = MathHelper.func_76136_a(this.field_70146_Z, 20, 80);
                     }
                  } else {
                     this.field_146040_ay = MathHelper.func_76136_a(this.field_70146_Z, 100, 900);
                     this.field_146040_ay -= EnchantmentHelper.func_151387_h(this.field_146042_b) * 20 * 5;
                  }
               }

               if (this.field_146045_ax > 0) {
                  this.field_70181_x -= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2D;
               }
            }

            lvt_13_4_ = lvt_11_2_ * 2.0D - 1.0D;
            this.field_70181_x += 0.03999999910593033D * lvt_13_4_;
            if (lvt_11_2_ > 0.0D) {
               lvt_9_2_ = (float)((double)lvt_9_2_ * 0.9D);
               this.field_70181_x *= 0.8D;
            }

            this.field_70159_w *= (double)lvt_9_2_;
            this.field_70181_x *= (double)lvt_9_2_;
            this.field_70179_y *= (double)lvt_9_2_;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         }
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74777_a("xTile", (short)this.field_146037_g);
      p_70014_1_.func_74777_a("yTile", (short)this.field_146048_h);
      p_70014_1_.func_74777_a("zTile", (short)this.field_146050_i);
      ResourceLocation lvt_2_1_ = (ResourceLocation)Block.field_149771_c.func_177774_c(this.field_146046_j);
      p_70014_1_.func_74778_a("inTile", lvt_2_1_ == null ? "" : lvt_2_1_.toString());
      p_70014_1_.func_74774_a("shake", (byte)this.field_146044_a);
      p_70014_1_.func_74774_a("inGround", (byte)(this.field_146051_au ? 1 : 0));
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_146037_g = p_70037_1_.func_74765_d("xTile");
      this.field_146048_h = p_70037_1_.func_74765_d("yTile");
      this.field_146050_i = p_70037_1_.func_74765_d("zTile");
      if (p_70037_1_.func_150297_b("inTile", 8)) {
         this.field_146046_j = Block.func_149684_b(p_70037_1_.func_74779_i("inTile"));
      } else {
         this.field_146046_j = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
      }

      this.field_146044_a = p_70037_1_.func_74771_c("shake") & 255;
      this.field_146051_au = p_70037_1_.func_74771_c("inGround") == 1;
   }

   public int func_146034_e() {
      if (this.field_70170_p.field_72995_K) {
         return 0;
      } else {
         int lvt_1_1_ = 0;
         if (this.field_146043_c != null) {
            double lvt_2_1_ = this.field_146042_b.field_70165_t - this.field_70165_t;
            double lvt_4_1_ = this.field_146042_b.field_70163_u - this.field_70163_u;
            double lvt_6_1_ = this.field_146042_b.field_70161_v - this.field_70161_v;
            double lvt_8_1_ = (double)MathHelper.func_76133_a(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_);
            double lvt_10_1_ = 0.1D;
            Entity var10000 = this.field_146043_c;
            var10000.field_70159_w += lvt_2_1_ * lvt_10_1_;
            var10000 = this.field_146043_c;
            var10000.field_70181_x += lvt_4_1_ * lvt_10_1_ + (double)MathHelper.func_76133_a(lvt_8_1_) * 0.08D;
            var10000 = this.field_146043_c;
            var10000.field_70179_y += lvt_6_1_ * lvt_10_1_;
            lvt_1_1_ = 3;
         } else if (this.field_146045_ax > 0) {
            EntityItem lvt_2_2_ = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_146033_f());
            double lvt_3_1_ = this.field_146042_b.field_70165_t - this.field_70165_t;
            double lvt_5_1_ = this.field_146042_b.field_70163_u - this.field_70163_u;
            double lvt_7_1_ = this.field_146042_b.field_70161_v - this.field_70161_v;
            double lvt_9_1_ = (double)MathHelper.func_76133_a(lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_ + lvt_7_1_ * lvt_7_1_);
            double lvt_11_1_ = 0.1D;
            lvt_2_2_.field_70159_w = lvt_3_1_ * lvt_11_1_;
            lvt_2_2_.field_70181_x = lvt_5_1_ * lvt_11_1_ + (double)MathHelper.func_76133_a(lvt_9_1_) * 0.08D;
            lvt_2_2_.field_70179_y = lvt_7_1_ * lvt_11_1_;
            this.field_70170_p.func_72838_d(lvt_2_2_);
            this.field_146042_b.field_70170_p.func_72838_d(new EntityXPOrb(this.field_146042_b.field_70170_p, this.field_146042_b.field_70165_t, this.field_146042_b.field_70163_u + 0.5D, this.field_146042_b.field_70161_v + 0.5D, this.field_70146_Z.nextInt(6) + 1));
            lvt_1_1_ = 1;
         }

         if (this.field_146051_au) {
            lvt_1_1_ = 2;
         }

         this.func_70106_y();
         this.field_146042_b.field_71104_cf = null;
         return lvt_1_1_;
      }
   }

   private ItemStack func_146033_f() {
      float lvt_1_1_ = this.field_70170_p.field_73012_v.nextFloat();
      int lvt_2_1_ = EnchantmentHelper.func_151386_g(this.field_146042_b);
      int lvt_3_1_ = EnchantmentHelper.func_151387_h(this.field_146042_b);
      float lvt_4_1_ = 0.1F - (float)lvt_2_1_ * 0.025F - (float)lvt_3_1_ * 0.01F;
      float lvt_5_1_ = 0.05F + (float)lvt_2_1_ * 0.01F - (float)lvt_3_1_ * 0.01F;
      lvt_4_1_ = MathHelper.func_76131_a(lvt_4_1_, 0.0F, 1.0F);
      lvt_5_1_ = MathHelper.func_76131_a(lvt_5_1_, 0.0F, 1.0F);
      if (lvt_1_1_ < lvt_4_1_) {
         this.field_146042_b.func_71029_a(StatList.field_151183_A);
         return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146039_d)).func_150708_a(this.field_70146_Z);
      } else {
         lvt_1_1_ -= lvt_4_1_;
         if (lvt_1_1_ < lvt_5_1_) {
            this.field_146042_b.func_71029_a(StatList.field_151184_B);
            return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146041_e)).func_150708_a(this.field_70146_Z);
         } else {
            float var10000 = lvt_1_1_ - lvt_5_1_;
            this.field_146042_b.func_71029_a(StatList.field_75933_B);
            return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146036_f)).func_150708_a(this.field_70146_Z);
         }
      }
   }

   public void func_70106_y() {
      super.func_70106_y();
      if (this.field_146042_b != null) {
         this.field_146042_b.field_71104_cf = null;
      }

   }

   static {
      field_146039_d = Arrays.asList((new WeightedRandomFishable(new ItemStack(Items.field_151021_T), 10)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.field_151116_aA), 10), new WeightedRandomFishable(new ItemStack(Items.field_151103_aS), 10), new WeightedRandomFishable(new ItemStack(Items.field_151068_bn), 10), new WeightedRandomFishable(new ItemStack(Items.field_151007_F), 5), (new WeightedRandomFishable(new ItemStack(Items.field_151112_aM), 2)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.field_151054_z), 10), new WeightedRandomFishable(new ItemStack(Items.field_151055_y), 5), new WeightedRandomFishable(new ItemStack(Items.field_151100_aR, 10, EnumDyeColor.BLACK.func_176767_b()), 1), new WeightedRandomFishable(new ItemStack(Blocks.field_150479_bC), 10), new WeightedRandomFishable(new ItemStack(Items.field_151078_bh), 10));
      field_146041_e = Arrays.asList(new WeightedRandomFishable(new ItemStack(Blocks.field_150392_bi), 1), new WeightedRandomFishable(new ItemStack(Items.field_151057_cb), 1), new WeightedRandomFishable(new ItemStack(Items.field_151141_av), 1), (new WeightedRandomFishable(new ItemStack(Items.field_151031_f), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.field_151112_aM), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.field_151122_aG), 1)).func_150707_a());
      field_146036_f = Arrays.asList(new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.COD.func_150976_a()), 60), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 25), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 2), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()), 13));
   }
}
