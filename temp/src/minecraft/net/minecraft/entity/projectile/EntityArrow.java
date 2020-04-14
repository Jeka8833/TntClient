package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements IProjectile {
   private int field_145791_d = -1;
   private int field_145792_e = -1;
   private int field_145789_f = -1;
   private Block field_145790_g;
   private int field_70253_h;
   private boolean field_70254_i;
   public int field_70251_a;
   public int field_70249_b;
   public Entity field_70250_c;
   private int field_70252_j;
   private int field_70257_an;
   private double field_70255_ao = 2.0D;
   private int field_70256_ap;

   public EntityArrow(World p_i1753_1_) {
      super(p_i1753_1_);
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.5F, 0.5F);
   }

   public EntityArrow(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
      super(p_i1754_1_);
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.5F, 0.5F);
      this.func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
   }

   public EntityArrow(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
      super(p_i1755_1_);
      this.field_70155_l = 10.0D;
      this.field_70250_c = p_i1755_2_;
      if (p_i1755_2_ instanceof EntityPlayer) {
         this.field_70251_a = 1;
      }

      this.field_70163_u = p_i1755_2_.field_70163_u + (double)p_i1755_2_.func_70047_e() - 0.10000000149011612D;
      double lvt_6_1_ = p_i1755_3_.field_70165_t - p_i1755_2_.field_70165_t;
      double lvt_8_1_ = p_i1755_3_.func_174813_aQ().field_72338_b + (double)(p_i1755_3_.field_70131_O / 3.0F) - this.field_70163_u;
      double lvt_10_1_ = p_i1755_3_.field_70161_v - p_i1755_2_.field_70161_v;
      double lvt_12_1_ = (double)MathHelper.func_76133_a(lvt_6_1_ * lvt_6_1_ + lvt_10_1_ * lvt_10_1_);
      if (lvt_12_1_ >= 1.0E-7D) {
         float lvt_14_1_ = (float)(MathHelper.func_181159_b(lvt_10_1_, lvt_6_1_) * 180.0D / 3.1415927410125732D) - 90.0F;
         float lvt_15_1_ = (float)(-(MathHelper.func_181159_b(lvt_8_1_, lvt_12_1_) * 180.0D / 3.1415927410125732D));
         double lvt_16_1_ = lvt_6_1_ / lvt_12_1_;
         double lvt_18_1_ = lvt_10_1_ / lvt_12_1_;
         this.func_70012_b(p_i1755_2_.field_70165_t + lvt_16_1_, this.field_70163_u, p_i1755_2_.field_70161_v + lvt_18_1_, lvt_14_1_, lvt_15_1_);
         float lvt_20_1_ = (float)(lvt_12_1_ * 0.20000000298023224D);
         this.func_70186_c(lvt_6_1_, lvt_8_1_ + (double)lvt_20_1_, lvt_10_1_, p_i1755_4_, p_i1755_5_);
      }
   }

   public EntityArrow(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
      super(p_i1756_1_);
      this.field_70155_l = 10.0D;
      this.field_70250_c = p_i1756_2_;
      if (p_i1756_2_ instanceof EntityPlayer) {
         this.field_70251_a = 1;
      }

      this.func_70105_a(0.5F, 0.5F);
      this.func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + (double)p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, p_i1756_2_.field_70177_z, p_i1756_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
      this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
      this.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5F, 1.0F);
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(16, (byte)0);
   }

   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
      float lvt_9_1_ = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
      p_70186_1_ /= (double)lvt_9_1_;
      p_70186_3_ /= (double)lvt_9_1_;
      p_70186_5_ /= (double)lvt_9_1_;
      p_70186_1_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_3_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_5_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_1_ *= (double)p_70186_7_;
      p_70186_3_ *= (double)p_70186_7_;
      p_70186_5_ *= (double)p_70186_7_;
      this.field_70159_w = p_70186_1_;
      this.field_70181_x = p_70186_3_;
      this.field_70179_y = p_70186_5_;
      float lvt_10_1_ = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
      this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70186_1_, p_70186_5_) * 180.0D / 3.1415927410125732D);
      this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70186_3_, (double)lvt_10_1_) * 180.0D / 3.1415927410125732D);
      this.field_70252_j = 0;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      this.func_70101_b(p_180426_7_, p_180426_8_);
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float lvt_7_1_ = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
         this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70016_1_, p_70016_5_) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70016_3_, (double)lvt_7_1_) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A;
         this.field_70126_B = this.field_70177_z;
         this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         this.field_70252_j = 0;
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float lvt_1_1_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)lvt_1_1_) * 180.0D / 3.1415927410125732D);
      }

      BlockPos lvt_1_2_ = new BlockPos(this.field_145791_d, this.field_145792_e, this.field_145789_f);
      IBlockState lvt_2_1_ = this.field_70170_p.func_180495_p(lvt_1_2_);
      Block lvt_3_1_ = lvt_2_1_.func_177230_c();
      if (lvt_3_1_.func_149688_o() != Material.field_151579_a) {
         lvt_3_1_.func_180654_a(this.field_70170_p, lvt_1_2_);
         AxisAlignedBB lvt_4_1_ = lvt_3_1_.func_180640_a(this.field_70170_p, lvt_1_2_, lvt_2_1_);
         if (lvt_4_1_ != null && lvt_4_1_.func_72318_a(new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
            this.field_70254_i = true;
         }
      }

      if (this.field_70249_b > 0) {
         --this.field_70249_b;
      }

      if (this.field_70254_i) {
         int lvt_4_2_ = lvt_3_1_.func_176201_c(lvt_2_1_);
         if (lvt_3_1_ == this.field_145790_g && lvt_4_2_ == this.field_70253_h) {
            ++this.field_70252_j;
            if (this.field_70252_j >= 1200) {
               this.func_70106_y();
            }
         } else {
            this.field_70254_i = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70252_j = 0;
            this.field_70257_an = 0;
         }

      } else {
         ++this.field_70257_an;
         Vec3 lvt_4_3_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 lvt_5_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         MovingObjectPosition lvt_6_1_ = this.field_70170_p.func_147447_a(lvt_4_3_, lvt_5_1_, false, true, false);
         lvt_4_3_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         lvt_5_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if (lvt_6_1_ != null) {
            lvt_5_1_ = new Vec3(lvt_6_1_.field_72307_f.field_72450_a, lvt_6_1_.field_72307_f.field_72448_b, lvt_6_1_.field_72307_f.field_72449_c);
         }

         Entity lvt_7_1_ = null;
         List<Entity> lvt_8_1_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
         double lvt_9_1_ = 0.0D;

         int lvt_11_5_;
         float lvt_13_5_;
         for(lvt_11_5_ = 0; lvt_11_5_ < lvt_8_1_.size(); ++lvt_11_5_) {
            Entity lvt_12_1_ = (Entity)lvt_8_1_.get(lvt_11_5_);
            if (lvt_12_1_.func_70067_L() && (lvt_12_1_ != this.field_70250_c || this.field_70257_an >= 5)) {
               lvt_13_5_ = 0.3F;
               AxisAlignedBB lvt_14_1_ = lvt_12_1_.func_174813_aQ().func_72314_b((double)lvt_13_5_, (double)lvt_13_5_, (double)lvt_13_5_);
               MovingObjectPosition lvt_15_1_ = lvt_14_1_.func_72327_a(lvt_4_3_, lvt_5_1_);
               if (lvt_15_1_ != null) {
                  double lvt_16_1_ = lvt_4_3_.func_72436_e(lvt_15_1_.field_72307_f);
                  if (lvt_16_1_ < lvt_9_1_ || lvt_9_1_ == 0.0D) {
                     lvt_7_1_ = lvt_12_1_;
                     lvt_9_1_ = lvt_16_1_;
                  }
               }
            }
         }

         if (lvt_7_1_ != null) {
            lvt_6_1_ = new MovingObjectPosition(lvt_7_1_);
         }

         if (lvt_6_1_ != null && lvt_6_1_.field_72308_g != null && lvt_6_1_.field_72308_g instanceof EntityPlayer) {
            EntityPlayer lvt_11_2_ = (EntityPlayer)lvt_6_1_.field_72308_g;
            if (lvt_11_2_.field_71075_bZ.field_75102_a || this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(lvt_11_2_)) {
               lvt_6_1_ = null;
            }
         }

         float lvt_11_6_;
         float lvt_15_2_;
         if (lvt_6_1_ != null) {
            if (lvt_6_1_.field_72308_g != null) {
               lvt_11_6_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               int lvt_12_2_ = MathHelper.func_76143_f((double)lvt_11_6_ * this.field_70255_ao);
               if (this.func_70241_g()) {
                  lvt_12_2_ += this.field_70146_Z.nextInt(lvt_12_2_ / 2 + 2);
               }

               DamageSource lvt_13_3_;
               if (this.field_70250_c == null) {
                  lvt_13_3_ = DamageSource.func_76353_a(this, this);
               } else {
                  lvt_13_3_ = DamageSource.func_76353_a(this, this.field_70250_c);
               }

               if (this.func_70027_ad() && !(lvt_6_1_.field_72308_g instanceof EntityEnderman)) {
                  lvt_6_1_.field_72308_g.func_70015_d(5);
               }

               if (lvt_6_1_.field_72308_g.func_70097_a(lvt_13_3_, (float)lvt_12_2_)) {
                  if (lvt_6_1_.field_72308_g instanceof EntityLivingBase) {
                     EntityLivingBase lvt_14_2_ = (EntityLivingBase)lvt_6_1_.field_72308_g;
                     if (!this.field_70170_p.field_72995_K) {
                        lvt_14_2_.func_85034_r(lvt_14_2_.func_85035_bI() + 1);
                     }

                     if (this.field_70256_ap > 0) {
                        lvt_15_2_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                        if (lvt_15_2_ > 0.0F) {
                           lvt_6_1_.field_72308_g.func_70024_g(this.field_70159_w * (double)this.field_70256_ap * 0.6000000238418579D / (double)lvt_15_2_, 0.1D, this.field_70179_y * (double)this.field_70256_ap * 0.6000000238418579D / (double)lvt_15_2_);
                        }
                     }

                     if (this.field_70250_c instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a(lvt_14_2_, this.field_70250_c);
                        EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, lvt_14_2_);
                     }

                     if (this.field_70250_c != null && lvt_6_1_.field_72308_g != this.field_70250_c && lvt_6_1_.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
                  if (!(lvt_6_1_.field_72308_g instanceof EntityEnderman)) {
                     this.func_70106_y();
                  }
               } else {
                  this.field_70159_w *= -0.10000000149011612D;
                  this.field_70181_x *= -0.10000000149011612D;
                  this.field_70179_y *= -0.10000000149011612D;
                  this.field_70177_z += 180.0F;
                  this.field_70126_B += 180.0F;
                  this.field_70257_an = 0;
               }
            } else {
               BlockPos lvt_11_4_ = lvt_6_1_.func_178782_a();
               this.field_145791_d = lvt_11_4_.func_177958_n();
               this.field_145792_e = lvt_11_4_.func_177956_o();
               this.field_145789_f = lvt_11_4_.func_177952_p();
               IBlockState lvt_12_3_ = this.field_70170_p.func_180495_p(lvt_11_4_);
               this.field_145790_g = lvt_12_3_.func_177230_c();
               this.field_70253_h = this.field_145790_g.func_176201_c(lvt_12_3_);
               this.field_70159_w = (double)((float)(lvt_6_1_.field_72307_f.field_72450_a - this.field_70165_t));
               this.field_70181_x = (double)((float)(lvt_6_1_.field_72307_f.field_72448_b - this.field_70163_u));
               this.field_70179_y = (double)((float)(lvt_6_1_.field_72307_f.field_72449_c - this.field_70161_v));
               lvt_13_5_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               this.field_70165_t -= this.field_70159_w / (double)lvt_13_5_ * 0.05000000074505806D;
               this.field_70163_u -= this.field_70181_x / (double)lvt_13_5_ * 0.05000000074505806D;
               this.field_70161_v -= this.field_70179_y / (double)lvt_13_5_ * 0.05000000074505806D;
               this.func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
               this.field_70254_i = true;
               this.field_70249_b = 7;
               this.func_70243_d(false);
               if (this.field_145790_g.func_149688_o() != Material.field_151579_a) {
                  this.field_145790_g.func_180634_a(this.field_70170_p, lvt_11_4_, lvt_12_3_, this);
               }
            }
         }

         if (this.func_70241_g()) {
            for(lvt_11_5_ = 0; lvt_11_5_ < 4; ++lvt_11_5_) {
               this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, this.field_70165_t + this.field_70159_w * (double)lvt_11_5_ / 4.0D, this.field_70163_u + this.field_70181_x * (double)lvt_11_5_ / 4.0D, this.field_70161_v + this.field_70179_y * (double)lvt_11_5_ / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);
            }
         }

         this.field_70165_t += this.field_70159_w;
         this.field_70163_u += this.field_70181_x;
         this.field_70161_v += this.field_70179_y;
         lvt_11_6_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);

         for(this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)lvt_11_6_) * 180.0D / 3.1415927410125732D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
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
         float lvt_12_4_ = 0.99F;
         lvt_13_5_ = 0.05F;
         if (this.func_70090_H()) {
            for(int lvt_14_3_ = 0; lvt_14_3_ < 4; ++lvt_14_3_) {
               lvt_15_2_ = 0.25F;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * (double)lvt_15_2_, this.field_70163_u - this.field_70181_x * (double)lvt_15_2_, this.field_70161_v - this.field_70179_y * (double)lvt_15_2_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }

            lvt_12_4_ = 0.6F;
         }

         if (this.func_70026_G()) {
            this.func_70066_B();
         }

         this.field_70159_w *= (double)lvt_12_4_;
         this.field_70181_x *= (double)lvt_12_4_;
         this.field_70179_y *= (double)lvt_12_4_;
         this.field_70181_x -= (double)lvt_13_5_;
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_145775_I();
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74777_a("xTile", (short)this.field_145791_d);
      p_70014_1_.func_74777_a("yTile", (short)this.field_145792_e);
      p_70014_1_.func_74777_a("zTile", (short)this.field_145789_f);
      p_70014_1_.func_74777_a("life", (short)this.field_70252_j);
      ResourceLocation lvt_2_1_ = (ResourceLocation)Block.field_149771_c.func_177774_c(this.field_145790_g);
      p_70014_1_.func_74778_a("inTile", lvt_2_1_ == null ? "" : lvt_2_1_.toString());
      p_70014_1_.func_74774_a("inData", (byte)this.field_70253_h);
      p_70014_1_.func_74774_a("shake", (byte)this.field_70249_b);
      p_70014_1_.func_74774_a("inGround", (byte)(this.field_70254_i ? 1 : 0));
      p_70014_1_.func_74774_a("pickup", (byte)this.field_70251_a);
      p_70014_1_.func_74780_a("damage", this.field_70255_ao);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_145791_d = p_70037_1_.func_74765_d("xTile");
      this.field_145792_e = p_70037_1_.func_74765_d("yTile");
      this.field_145789_f = p_70037_1_.func_74765_d("zTile");
      this.field_70252_j = p_70037_1_.func_74765_d("life");
      if (p_70037_1_.func_150297_b("inTile", 8)) {
         this.field_145790_g = Block.func_149684_b(p_70037_1_.func_74779_i("inTile"));
      } else {
         this.field_145790_g = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
      }

      this.field_70253_h = p_70037_1_.func_74771_c("inData") & 255;
      this.field_70249_b = p_70037_1_.func_74771_c("shake") & 255;
      this.field_70254_i = p_70037_1_.func_74771_c("inGround") == 1;
      if (p_70037_1_.func_150297_b("damage", 99)) {
         this.field_70255_ao = p_70037_1_.func_74769_h("damage");
      }

      if (p_70037_1_.func_150297_b("pickup", 99)) {
         this.field_70251_a = p_70037_1_.func_74771_c("pickup");
      } else if (p_70037_1_.func_150297_b("player", 99)) {
         this.field_70251_a = p_70037_1_.func_74767_n("player") ? 1 : 0;
      }

   }

   public void func_70100_b_(EntityPlayer p_70100_1_) {
      if (!this.field_70170_p.field_72995_K && this.field_70254_i && this.field_70249_b <= 0) {
         boolean lvt_2_1_ = this.field_70251_a == 1 || this.field_70251_a == 2 && p_70100_1_.field_71075_bZ.field_75098_d;
         if (this.field_70251_a == 1 && !p_70100_1_.field_71071_by.func_70441_a(new ItemStack(Items.field_151032_g, 1))) {
            lvt_2_1_ = false;
         }

         if (lvt_2_1_) {
            this.func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            p_70100_1_.func_71001_a(this, 1);
            this.func_70106_y();
         }

      }
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public void func_70239_b(double p_70239_1_) {
      this.field_70255_ao = p_70239_1_;
   }

   public double func_70242_d() {
      return this.field_70255_ao;
   }

   public void func_70240_a(int p_70240_1_) {
      this.field_70256_ap = p_70240_1_;
   }

   public boolean func_70075_an() {
      return false;
   }

   public float func_70047_e() {
      return 0.0F;
   }

   public void func_70243_d(boolean p_70243_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(16);
      if (p_70243_1_) {
         this.field_70180_af.func_75692_b(16, (byte)(lvt_2_1_ | 1));
      } else {
         this.field_70180_af.func_75692_b(16, (byte)(lvt_2_1_ & -2));
      }

   }

   public boolean func_70241_g() {
      byte lvt_1_1_ = this.field_70180_af.func_75683_a(16);
      return (lvt_1_1_ & 1) != 0;
   }
}
