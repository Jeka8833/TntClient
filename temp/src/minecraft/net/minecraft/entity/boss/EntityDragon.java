package net.minecraft.entity.boss;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {
   public double field_70980_b;
   public double field_70981_c;
   public double field_70978_d;
   public double[][] field_70979_e = new double[64][3];
   public int field_70976_f = -1;
   public EntityDragonPart[] field_70977_g;
   public EntityDragonPart field_70986_h;
   public EntityDragonPart field_70987_i;
   public EntityDragonPart field_70985_j;
   public EntityDragonPart field_70984_by;
   public EntityDragonPart field_70982_bz;
   public EntityDragonPart field_70983_bA;
   public EntityDragonPart field_70990_bB;
   public float field_70991_bC;
   public float field_70988_bD;
   public boolean field_70989_bE;
   public boolean field_70994_bF;
   private Entity field_70993_bI;
   public int field_70995_bG;
   public EntityEnderCrystal field_70992_bH;

   public EntityDragon(World p_i1700_1_) {
      super(p_i1700_1_);
      this.field_70977_g = new EntityDragonPart[]{this.field_70986_h = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.field_70987_i = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.field_70985_j = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70984_by = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70982_bz = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70983_bA = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.field_70990_bB = new EntityDragonPart(this, "wing", 4.0F, 4.0F)};
      this.func_70606_j(this.func_110138_aP());
      this.func_70105_a(16.0F, 8.0F);
      this.field_70145_X = true;
      this.field_70178_ae = true;
      this.field_70981_c = 100.0D;
      this.field_70158_ak = true;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
   }

   public double[] func_70974_a(int p_70974_1_, float p_70974_2_) {
      if (this.func_110143_aJ() <= 0.0F) {
         p_70974_2_ = 0.0F;
      }

      p_70974_2_ = 1.0F - p_70974_2_;
      int lvt_3_1_ = this.field_70976_f - p_70974_1_ * 1 & 63;
      int lvt_4_1_ = this.field_70976_f - p_70974_1_ * 1 - 1 & 63;
      double[] lvt_5_1_ = new double[3];
      double lvt_6_1_ = this.field_70979_e[lvt_3_1_][0];
      double lvt_8_1_ = MathHelper.func_76138_g(this.field_70979_e[lvt_4_1_][0] - lvt_6_1_);
      lvt_5_1_[0] = lvt_6_1_ + lvt_8_1_ * (double)p_70974_2_;
      lvt_6_1_ = this.field_70979_e[lvt_3_1_][1];
      lvt_8_1_ = this.field_70979_e[lvt_4_1_][1] - lvt_6_1_;
      lvt_5_1_[1] = lvt_6_1_ + lvt_8_1_ * (double)p_70974_2_;
      lvt_5_1_[2] = this.field_70979_e[lvt_3_1_][2] + (this.field_70979_e[lvt_4_1_][2] - this.field_70979_e[lvt_3_1_][2]) * (double)p_70974_2_;
      return lvt_5_1_;
   }

   public void func_70636_d() {
      float lvt_1_3_;
      float lvt_2_6_;
      if (this.field_70170_p.field_72995_K) {
         lvt_1_3_ = MathHelper.func_76134_b(this.field_70988_bD * 3.1415927F * 2.0F);
         lvt_2_6_ = MathHelper.func_76134_b(this.field_70991_bC * 3.1415927F * 2.0F);
         if (lvt_2_6_ <= -0.3F && lvt_1_3_ >= -0.3F && !this.func_174814_R()) {
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.wings", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false);
         }
      }

      this.field_70991_bC = this.field_70988_bD;
      float lvt_3_2_;
      if (this.func_110143_aJ() <= 0.0F) {
         lvt_1_3_ = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         lvt_2_6_ = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
         lvt_3_2_ = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_70165_t + (double)lvt_1_3_, this.field_70163_u + 2.0D + (double)lvt_2_6_, this.field_70161_v + (double)lvt_3_2_, 0.0D, 0.0D, 0.0D);
      } else {
         this.func_70969_j();
         lvt_1_3_ = 0.2F / (MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 10.0F + 1.0F);
         lvt_1_3_ *= (float)Math.pow(2.0D, this.field_70181_x);
         if (this.field_70994_bF) {
            this.field_70988_bD += lvt_1_3_ * 0.5F;
         } else {
            this.field_70988_bD += lvt_1_3_;
         }

         this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
         if (this.func_175446_cd()) {
            this.field_70988_bD = 0.5F;
         } else {
            if (this.field_70976_f < 0) {
               for(int lvt_2_3_ = 0; lvt_2_3_ < this.field_70979_e.length; ++lvt_2_3_) {
                  this.field_70979_e[lvt_2_3_][0] = (double)this.field_70177_z;
                  this.field_70979_e[lvt_2_3_][1] = this.field_70163_u;
               }
            }

            if (++this.field_70976_f == this.field_70979_e.length) {
               this.field_70976_f = 0;
            }

            this.field_70979_e[this.field_70976_f][0] = (double)this.field_70177_z;
            this.field_70979_e[this.field_70976_f][1] = this.field_70163_u;
            double lvt_4_2_;
            double lvt_6_2_;
            double lvt_8_2_;
            double lvt_2_5_;
            float lvt_10_2_;
            if (this.field_70170_p.field_72995_K) {
               if (this.field_70716_bi > 0) {
                  lvt_2_5_ = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double)this.field_70716_bi;
                  lvt_4_2_ = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double)this.field_70716_bi;
                  lvt_6_2_ = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double)this.field_70716_bi;
                  lvt_8_2_ = MathHelper.func_76138_g(this.field_70712_bm - (double)this.field_70177_z);
                  this.field_70177_z = (float)((double)this.field_70177_z + lvt_8_2_ / (double)this.field_70716_bi);
                  this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70705_bn - (double)this.field_70125_A) / (double)this.field_70716_bi);
                  --this.field_70716_bi;
                  this.func_70107_b(lvt_2_5_, lvt_4_2_, lvt_6_2_);
                  this.func_70101_b(this.field_70177_z, this.field_70125_A);
               }
            } else {
               lvt_2_5_ = this.field_70980_b - this.field_70165_t;
               lvt_4_2_ = this.field_70981_c - this.field_70163_u;
               lvt_6_2_ = this.field_70978_d - this.field_70161_v;
               lvt_8_2_ = lvt_2_5_ * lvt_2_5_ + lvt_4_2_ * lvt_4_2_ + lvt_6_2_ * lvt_6_2_;
               double lvt_16_2_;
               if (this.field_70993_bI != null) {
                  this.field_70980_b = this.field_70993_bI.field_70165_t;
                  this.field_70978_d = this.field_70993_bI.field_70161_v;
                  double lvt_10_1_ = this.field_70980_b - this.field_70165_t;
                  double lvt_12_1_ = this.field_70978_d - this.field_70161_v;
                  double lvt_14_1_ = Math.sqrt(lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_);
                  lvt_16_2_ = 0.4000000059604645D + lvt_14_1_ / 80.0D - 1.0D;
                  if (lvt_16_2_ > 10.0D) {
                     lvt_16_2_ = 10.0D;
                  }

                  this.field_70981_c = this.field_70993_bI.func_174813_aQ().field_72338_b + lvt_16_2_;
               } else {
                  this.field_70980_b += this.field_70146_Z.nextGaussian() * 2.0D;
                  this.field_70978_d += this.field_70146_Z.nextGaussian() * 2.0D;
               }

               if (this.field_70989_bE || lvt_8_2_ < 100.0D || lvt_8_2_ > 22500.0D || this.field_70123_F || this.field_70124_G) {
                  this.func_70967_k();
               }

               lvt_4_2_ /= (double)MathHelper.func_76133_a(lvt_2_5_ * lvt_2_5_ + lvt_6_2_ * lvt_6_2_);
               lvt_10_2_ = 0.6F;
               lvt_4_2_ = MathHelper.func_151237_a(lvt_4_2_, (double)(-lvt_10_2_), (double)lvt_10_2_);
               this.field_70181_x += lvt_4_2_ * 0.10000000149011612D;
               this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
               double lvt_11_1_ = 180.0D - MathHelper.func_181159_b(lvt_2_5_, lvt_6_2_) * 180.0D / 3.1415927410125732D;
               double lvt_13_1_ = MathHelper.func_76138_g(lvt_11_1_ - (double)this.field_70177_z);
               if (lvt_13_1_ > 50.0D) {
                  lvt_13_1_ = 50.0D;
               }

               if (lvt_13_1_ < -50.0D) {
                  lvt_13_1_ = -50.0D;
               }

               Vec3 lvt_15_1_ = (new Vec3(this.field_70980_b - this.field_70165_t, this.field_70981_c - this.field_70163_u, this.field_70978_d - this.field_70161_v)).func_72432_b();
               lvt_16_2_ = (double)(-MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F));
               Vec3 lvt_18_1_ = (new Vec3((double)MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F), this.field_70181_x, lvt_16_2_)).func_72432_b();
               float lvt_19_1_ = ((float)lvt_18_1_.func_72430_b(lvt_15_1_) + 0.5F) / 1.5F;
               if (lvt_19_1_ < 0.0F) {
                  lvt_19_1_ = 0.0F;
               }

               this.field_70704_bt *= 0.8F;
               float lvt_20_1_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0F + 1.0F;
               double lvt_21_1_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0D + 1.0D;
               if (lvt_21_1_ > 40.0D) {
                  lvt_21_1_ = 40.0D;
               }

               this.field_70704_bt = (float)((double)this.field_70704_bt + lvt_13_1_ * (0.699999988079071D / lvt_21_1_ / (double)lvt_20_1_));
               this.field_70177_z += this.field_70704_bt * 0.1F;
               float lvt_23_1_ = (float)(2.0D / (lvt_21_1_ + 1.0D));
               float lvt_24_1_ = 0.06F;
               this.func_70060_a(0.0F, -1.0F, lvt_24_1_ * (lvt_19_1_ * lvt_23_1_ + (1.0F - lvt_23_1_)));
               if (this.field_70994_bF) {
                  this.func_70091_d(this.field_70159_w * 0.800000011920929D, this.field_70181_x * 0.800000011920929D, this.field_70179_y * 0.800000011920929D);
               } else {
                  this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
               }

               Vec3 lvt_25_1_ = (new Vec3(this.field_70159_w, this.field_70181_x, this.field_70179_y)).func_72432_b();
               float lvt_26_1_ = ((float)lvt_25_1_.func_72430_b(lvt_18_1_) + 1.0F) / 2.0F;
               lvt_26_1_ = 0.8F + 0.15F * lvt_26_1_;
               this.field_70159_w *= (double)lvt_26_1_;
               this.field_70179_y *= (double)lvt_26_1_;
               this.field_70181_x *= 0.9100000262260437D;
            }

            this.field_70761_aq = this.field_70177_z;
            this.field_70986_h.field_70130_N = this.field_70986_h.field_70131_O = 3.0F;
            this.field_70985_j.field_70130_N = this.field_70985_j.field_70131_O = 2.0F;
            this.field_70984_by.field_70130_N = this.field_70984_by.field_70131_O = 2.0F;
            this.field_70982_bz.field_70130_N = this.field_70982_bz.field_70131_O = 2.0F;
            this.field_70987_i.field_70131_O = 3.0F;
            this.field_70987_i.field_70130_N = 5.0F;
            this.field_70983_bA.field_70131_O = 2.0F;
            this.field_70983_bA.field_70130_N = 4.0F;
            this.field_70990_bB.field_70131_O = 3.0F;
            this.field_70990_bB.field_70130_N = 4.0F;
            lvt_2_6_ = (float)(this.func_70974_a(5, 1.0F)[1] - this.func_70974_a(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
            lvt_3_2_ = MathHelper.func_76134_b(lvt_2_6_);
            float lvt_4_3_ = -MathHelper.func_76126_a(lvt_2_6_);
            float lvt_5_1_ = this.field_70177_z * 3.1415927F / 180.0F;
            float lvt_6_3_ = MathHelper.func_76126_a(lvt_5_1_);
            float lvt_7_1_ = MathHelper.func_76134_b(lvt_5_1_);
            this.field_70987_i.func_70071_h_();
            this.field_70987_i.func_70012_b(this.field_70165_t + (double)(lvt_6_3_ * 0.5F), this.field_70163_u, this.field_70161_v - (double)(lvt_7_1_ * 0.5F), 0.0F, 0.0F);
            this.field_70983_bA.func_70071_h_();
            this.field_70983_bA.func_70012_b(this.field_70165_t + (double)(lvt_7_1_ * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v + (double)(lvt_6_3_ * 4.5F), 0.0F, 0.0F);
            this.field_70990_bB.func_70071_h_();
            this.field_70990_bB.func_70012_b(this.field_70165_t - (double)(lvt_7_1_ * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v - (double)(lvt_6_3_ * 4.5F), 0.0F, 0.0F);
            if (!this.field_70170_p.field_72995_K && this.field_70737_aN == 0) {
               this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70983_bA.func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
               this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70990_bB.func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
               this.func_70971_b(this.field_70170_p.func_72839_b(this, this.field_70986_h.func_174813_aQ().func_72314_b(1.0D, 1.0D, 1.0D)));
            }

            double[] lvt_8_3_ = this.func_70974_a(5, 1.0F);
            double[] lvt_9_1_ = this.func_70974_a(0, 1.0F);
            lvt_10_2_ = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
            float lvt_11_2_ = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
            this.field_70986_h.func_70071_h_();
            this.field_70986_h.func_70012_b(this.field_70165_t + (double)(lvt_10_2_ * 5.5F * lvt_3_2_), this.field_70163_u + (lvt_9_1_[1] - lvt_8_3_[1]) * 1.0D + (double)(lvt_4_3_ * 5.5F), this.field_70161_v - (double)(lvt_11_2_ * 5.5F * lvt_3_2_), 0.0F, 0.0F);

            for(int lvt_9_2_ = 0; lvt_9_2_ < 3; ++lvt_9_2_) {
               EntityDragonPart lvt_10_4_ = null;
               if (lvt_9_2_ == 0) {
                  lvt_10_4_ = this.field_70985_j;
               }

               if (lvt_9_2_ == 1) {
                  lvt_10_4_ = this.field_70984_by;
               }

               if (lvt_9_2_ == 2) {
                  lvt_10_4_ = this.field_70982_bz;
               }

               double[] lvt_11_3_ = this.func_70974_a(12 + lvt_9_2_ * 2, 1.0F);
               float lvt_12_2_ = this.field_70177_z * 3.1415927F / 180.0F + this.func_70973_b(lvt_11_3_[0] - lvt_8_3_[0]) * 3.1415927F / 180.0F * 1.0F;
               float lvt_13_2_ = MathHelper.func_76126_a(lvt_12_2_);
               float lvt_14_2_ = MathHelper.func_76134_b(lvt_12_2_);
               float lvt_15_2_ = 1.5F;
               float lvt_16_3_ = (float)(lvt_9_2_ + 1) * 2.0F;
               lvt_10_4_.func_70071_h_();
               lvt_10_4_.func_70012_b(this.field_70165_t - (double)((lvt_6_3_ * lvt_15_2_ + lvt_13_2_ * lvt_16_3_) * lvt_3_2_), this.field_70163_u + (lvt_11_3_[1] - lvt_8_3_[1]) * 1.0D - (double)((lvt_16_3_ + lvt_15_2_) * lvt_4_3_) + 1.5D, this.field_70161_v + (double)((lvt_7_1_ * lvt_15_2_ + lvt_14_2_ * lvt_16_3_) * lvt_3_2_), 0.0F, 0.0F);
            }

            if (!this.field_70170_p.field_72995_K) {
               this.field_70994_bF = this.func_70972_a(this.field_70986_h.func_174813_aQ()) | this.func_70972_a(this.field_70987_i.func_174813_aQ());
            }

         }
      }
   }

   private void func_70969_j() {
      if (this.field_70992_bH != null) {
         if (this.field_70992_bH.field_70128_L) {
            if (!this.field_70170_p.field_72995_K) {
               this.func_70965_a(this.field_70986_h, DamageSource.func_94539_a((Explosion)null), 10.0F);
            }

            this.field_70992_bH = null;
         } else if (this.field_70173_aa % 10 == 0 && this.func_110143_aJ() < this.func_110138_aP()) {
            this.func_70606_j(this.func_110143_aJ() + 1.0F);
         }
      }

      if (this.field_70146_Z.nextInt(10) == 0) {
         float lvt_1_1_ = 32.0F;
         List<EntityEnderCrystal> lvt_2_1_ = this.field_70170_p.func_72872_a(EntityEnderCrystal.class, this.func_174813_aQ().func_72314_b((double)lvt_1_1_, (double)lvt_1_1_, (double)lvt_1_1_));
         EntityEnderCrystal lvt_3_1_ = null;
         double lvt_4_1_ = Double.MAX_VALUE;
         Iterator lvt_6_1_ = lvt_2_1_.iterator();

         while(lvt_6_1_.hasNext()) {
            EntityEnderCrystal lvt_7_1_ = (EntityEnderCrystal)lvt_6_1_.next();
            double lvt_8_1_ = lvt_7_1_.func_70068_e(this);
            if (lvt_8_1_ < lvt_4_1_) {
               lvt_4_1_ = lvt_8_1_;
               lvt_3_1_ = lvt_7_1_;
            }
         }

         this.field_70992_bH = lvt_3_1_;
      }

   }

   private void func_70970_a(List<Entity> p_70970_1_) {
      double lvt_2_1_ = (this.field_70987_i.func_174813_aQ().field_72340_a + this.field_70987_i.func_174813_aQ().field_72336_d) / 2.0D;
      double lvt_4_1_ = (this.field_70987_i.func_174813_aQ().field_72339_c + this.field_70987_i.func_174813_aQ().field_72334_f) / 2.0D;
      Iterator lvt_6_1_ = p_70970_1_.iterator();

      while(lvt_6_1_.hasNext()) {
         Entity lvt_7_1_ = (Entity)lvt_6_1_.next();
         if (lvt_7_1_ instanceof EntityLivingBase) {
            double lvt_8_1_ = lvt_7_1_.field_70165_t - lvt_2_1_;
            double lvt_10_1_ = lvt_7_1_.field_70161_v - lvt_4_1_;
            double lvt_12_1_ = lvt_8_1_ * lvt_8_1_ + lvt_10_1_ * lvt_10_1_;
            lvt_7_1_.func_70024_g(lvt_8_1_ / lvt_12_1_ * 4.0D, 0.20000000298023224D, lvt_10_1_ / lvt_12_1_ * 4.0D);
         }
      }

   }

   private void func_70971_b(List<Entity> p_70971_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_70971_1_.size(); ++lvt_2_1_) {
         Entity lvt_3_1_ = (Entity)p_70971_1_.get(lvt_2_1_);
         if (lvt_3_1_ instanceof EntityLivingBase) {
            lvt_3_1_.func_70097_a(DamageSource.func_76358_a(this), 10.0F);
            this.func_174815_a(this, lvt_3_1_);
         }
      }

   }

   private void func_70967_k() {
      this.field_70989_bE = false;
      List<EntityPlayer> lvt_1_1_ = Lists.newArrayList((Iterable)this.field_70170_p.field_73010_i);
      Iterator lvt_2_1_ = lvt_1_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         if (((EntityPlayer)lvt_2_1_.next()).func_175149_v()) {
            lvt_2_1_.remove();
         }
      }

      if (this.field_70146_Z.nextInt(2) == 0 && !lvt_1_1_.isEmpty()) {
         this.field_70993_bI = (Entity)lvt_1_1_.get(this.field_70146_Z.nextInt(lvt_1_1_.size()));
      } else {
         boolean lvt_3_1_;
         do {
            this.field_70980_b = 0.0D;
            this.field_70981_c = (double)(70.0F + this.field_70146_Z.nextFloat() * 50.0F);
            this.field_70978_d = 0.0D;
            this.field_70980_b += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
            this.field_70978_d += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
            double lvt_4_1_ = this.field_70165_t - this.field_70980_b;
            double lvt_6_1_ = this.field_70163_u - this.field_70981_c;
            double lvt_8_1_ = this.field_70161_v - this.field_70978_d;
            lvt_3_1_ = lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_ + lvt_8_1_ * lvt_8_1_ > 100.0D;
         } while(!lvt_3_1_);

         this.field_70993_bI = null;
      }

   }

   private float func_70973_b(double p_70973_1_) {
      return (float)MathHelper.func_76138_g(p_70973_1_);
   }

   private boolean func_70972_a(AxisAlignedBB p_70972_1_) {
      int lvt_2_1_ = MathHelper.func_76128_c(p_70972_1_.field_72340_a);
      int lvt_3_1_ = MathHelper.func_76128_c(p_70972_1_.field_72338_b);
      int lvt_4_1_ = MathHelper.func_76128_c(p_70972_1_.field_72339_c);
      int lvt_5_1_ = MathHelper.func_76128_c(p_70972_1_.field_72336_d);
      int lvt_6_1_ = MathHelper.func_76128_c(p_70972_1_.field_72337_e);
      int lvt_7_1_ = MathHelper.func_76128_c(p_70972_1_.field_72334_f);
      boolean lvt_8_1_ = false;
      boolean lvt_9_1_ = false;

      for(int lvt_10_1_ = lvt_2_1_; lvt_10_1_ <= lvt_5_1_; ++lvt_10_1_) {
         for(int lvt_11_1_ = lvt_3_1_; lvt_11_1_ <= lvt_6_1_; ++lvt_11_1_) {
            for(int lvt_12_1_ = lvt_4_1_; lvt_12_1_ <= lvt_7_1_; ++lvt_12_1_) {
               BlockPos lvt_13_1_ = new BlockPos(lvt_10_1_, lvt_11_1_, lvt_12_1_);
               Block lvt_14_1_ = this.field_70170_p.func_180495_p(lvt_13_1_).func_177230_c();
               if (lvt_14_1_.func_149688_o() != Material.field_151579_a) {
                  if (lvt_14_1_ != Blocks.field_180401_cv && lvt_14_1_ != Blocks.field_150343_Z && lvt_14_1_ != Blocks.field_150377_bs && lvt_14_1_ != Blocks.field_150357_h && lvt_14_1_ != Blocks.field_150483_bI && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
                     lvt_9_1_ = this.field_70170_p.func_175698_g(lvt_13_1_) || lvt_9_1_;
                  } else {
                     lvt_8_1_ = true;
                  }
               }
            }
         }
      }

      if (lvt_9_1_) {
         double lvt_10_2_ = p_70972_1_.field_72340_a + (p_70972_1_.field_72336_d - p_70972_1_.field_72340_a) * (double)this.field_70146_Z.nextFloat();
         double lvt_12_2_ = p_70972_1_.field_72338_b + (p_70972_1_.field_72337_e - p_70972_1_.field_72338_b) * (double)this.field_70146_Z.nextFloat();
         double lvt_14_2_ = p_70972_1_.field_72339_c + (p_70972_1_.field_72334_f - p_70972_1_.field_72339_c) * (double)this.field_70146_Z.nextFloat();
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, lvt_10_2_, lvt_12_2_, lvt_14_2_, 0.0D, 0.0D, 0.0D);
      }

      return lvt_8_1_;
   }

   public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
      if (p_70965_1_ != this.field_70986_h) {
         p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
      }

      float lvt_4_1_ = this.field_70177_z * 3.1415927F / 180.0F;
      float lvt_5_1_ = MathHelper.func_76126_a(lvt_4_1_);
      float lvt_6_1_ = MathHelper.func_76134_b(lvt_4_1_);
      this.field_70980_b = this.field_70165_t + (double)(lvt_5_1_ * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
      this.field_70981_c = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 3.0F) + 1.0D;
      this.field_70978_d = this.field_70161_v - (double)(lvt_6_1_ * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
      this.field_70993_bI = null;
      if (p_70965_2_.func_76346_g() instanceof EntityPlayer || p_70965_2_.func_94541_c()) {
         this.func_82195_e(p_70965_2_, p_70965_3_);
      }

      return true;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (p_70097_1_ instanceof EntityDamageSource && ((EntityDamageSource)p_70097_1_).func_180139_w()) {
         this.func_82195_e(p_70097_1_, p_70097_2_);
      }

      return false;
   }

   protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_) {
      return super.func_70097_a(p_82195_1_, p_82195_2_);
   }

   public void func_174812_G() {
      this.func_70106_y();
   }

   protected void func_70609_aI() {
      ++this.field_70995_bG;
      if (this.field_70995_bG >= 180 && this.field_70995_bG <= 200) {
         float lvt_1_1_ = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         float lvt_2_1_ = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
         float lvt_3_1_ = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.field_70165_t + (double)lvt_1_1_, this.field_70163_u + 2.0D + (double)lvt_2_1_, this.field_70161_v + (double)lvt_3_1_, 0.0D, 0.0D, 0.0D);
      }

      boolean lvt_1_2_ = this.field_70170_p.func_82736_K().func_82766_b("doMobLoot");
      int lvt_2_3_;
      int lvt_3_3_;
      if (!this.field_70170_p.field_72995_K) {
         if (this.field_70995_bG > 150 && this.field_70995_bG % 5 == 0 && lvt_1_2_) {
            lvt_2_3_ = 1000;

            while(lvt_2_3_ > 0) {
               lvt_3_3_ = EntityXPOrb.func_70527_a(lvt_2_3_);
               lvt_2_3_ -= lvt_3_3_;
               this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, lvt_3_3_));
            }
         }

         if (this.field_70995_bG == 1) {
            this.field_70170_p.func_175669_a(1018, new BlockPos(this), 0);
         }
      }

      this.func_70091_d(0.0D, 0.10000000149011612D, 0.0D);
      this.field_70761_aq = this.field_70177_z += 20.0F;
      if (this.field_70995_bG == 200 && !this.field_70170_p.field_72995_K) {
         if (lvt_1_2_) {
            lvt_2_3_ = 2000;

            while(lvt_2_3_ > 0) {
               lvt_3_3_ = EntityXPOrb.func_70527_a(lvt_2_3_);
               lvt_2_3_ -= lvt_3_3_;
               this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, lvt_3_3_));
            }
         }

         this.func_175499_a(new BlockPos(this.field_70165_t, 64.0D, this.field_70161_v));
         this.func_70106_y();
      }

   }

   private void func_175499_a(BlockPos p_175499_1_) {
      int lvt_2_1_ = true;
      double lvt_3_1_ = 12.25D;
      double lvt_5_1_ = 6.25D;

      for(int lvt_7_1_ = -1; lvt_7_1_ <= 32; ++lvt_7_1_) {
         for(int lvt_8_1_ = -4; lvt_8_1_ <= 4; ++lvt_8_1_) {
            for(int lvt_9_1_ = -4; lvt_9_1_ <= 4; ++lvt_9_1_) {
               double lvt_10_1_ = (double)(lvt_8_1_ * lvt_8_1_ + lvt_9_1_ * lvt_9_1_);
               if (lvt_10_1_ <= 12.25D) {
                  BlockPos lvt_12_1_ = p_175499_1_.func_177982_a(lvt_8_1_, lvt_7_1_, lvt_9_1_);
                  if (lvt_7_1_ < 0) {
                     if (lvt_10_1_ <= 6.25D) {
                        this.field_70170_p.func_175656_a(lvt_12_1_, Blocks.field_150357_h.func_176223_P());
                     }
                  } else if (lvt_7_1_ > 0) {
                     this.field_70170_p.func_175656_a(lvt_12_1_, Blocks.field_150350_a.func_176223_P());
                  } else if (lvt_10_1_ > 6.25D) {
                     this.field_70170_p.func_175656_a(lvt_12_1_, Blocks.field_150357_h.func_176223_P());
                  } else {
                     this.field_70170_p.func_175656_a(lvt_12_1_, Blocks.field_150384_bq.func_176223_P());
                  }
               }
            }
         }
      }

      this.field_70170_p.func_175656_a(p_175499_1_, Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(p_175499_1_.func_177984_a(), Blocks.field_150357_h.func_176223_P());
      BlockPos lvt_7_2_ = p_175499_1_.func_177981_b(2);
      this.field_70170_p.func_175656_a(lvt_7_2_, Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(lvt_7_2_.func_177976_e(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.EAST));
      this.field_70170_p.func_175656_a(lvt_7_2_.func_177974_f(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.WEST));
      this.field_70170_p.func_175656_a(lvt_7_2_.func_177978_c(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.SOUTH));
      this.field_70170_p.func_175656_a(lvt_7_2_.func_177968_d(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.NORTH));
      this.field_70170_p.func_175656_a(p_175499_1_.func_177981_b(3), Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(p_175499_1_.func_177981_b(4), Blocks.field_150380_bt.func_176223_P());
   }

   protected void func_70623_bb() {
   }

   public Entity[] func_70021_al() {
      return this.field_70977_g;
   }

   public boolean func_70067_L() {
      return false;
   }

   public World func_82194_d() {
      return this.field_70170_p;
   }

   protected String func_70639_aQ() {
      return "mob.enderdragon.growl";
   }

   protected String func_70621_aR() {
      return "mob.enderdragon.hit";
   }

   protected float func_70599_aP() {
      return 5.0F;
   }
}
