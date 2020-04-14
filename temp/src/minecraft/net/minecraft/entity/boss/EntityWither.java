package net.minecraft.entity.boss;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob {
   private float[] field_82220_d = new float[2];
   private float[] field_82221_e = new float[2];
   private float[] field_82217_f = new float[2];
   private float[] field_82218_g = new float[2];
   private int[] field_82223_h = new int[2];
   private int[] field_82224_i = new int[2];
   private int field_82222_j;
   private static final Predicate<Entity> field_82219_bJ = new Predicate<Entity>() {
      public boolean apply(Entity p_apply_1_) {
         return p_apply_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_apply_1_).func_70668_bt() != EnumCreatureAttribute.UNDEAD;
      }
   };

   public EntityWither(World p_i1701_1_) {
      super(p_i1701_1_);
      this.func_70606_j(this.func_110138_aP());
      this.func_70105_a(0.9F, 3.5F);
      this.field_70178_ae = true;
      ((PathNavigateGround)this.func_70661_as()).func_179693_d(true);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, field_82219_bJ));
      this.field_70728_aV = 50;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(17, new Integer(0));
      this.field_70180_af.func_75682_a(18, new Integer(0));
      this.field_70180_af.func_75682_a(19, new Integer(0));
      this.field_70180_af.func_75682_a(20, new Integer(0));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("Invul", this.func_82212_n());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_82215_s(p_70037_1_.func_74762_e("Invul"));
   }

   protected String func_70639_aQ() {
      return "mob.wither.idle";
   }

   protected String func_70621_aR() {
      return "mob.wither.hurt";
   }

   protected String func_70673_aS() {
      return "mob.wither.death";
   }

   public void func_70636_d() {
      this.field_70181_x *= 0.6000000238418579D;
      double lvt_4_2_;
      double lvt_6_2_;
      double lvt_8_2_;
      if (!this.field_70170_p.field_72995_K && this.func_82203_t(0) > 0) {
         Entity lvt_1_1_ = this.field_70170_p.func_73045_a(this.func_82203_t(0));
         if (lvt_1_1_ != null) {
            if (this.field_70163_u < lvt_1_1_.field_70163_u || !this.func_82205_o() && this.field_70163_u < lvt_1_1_.field_70163_u + 5.0D) {
               if (this.field_70181_x < 0.0D) {
                  this.field_70181_x = 0.0D;
               }

               this.field_70181_x += (0.5D - this.field_70181_x) * 0.6000000238418579D;
            }

            double lvt_2_1_ = lvt_1_1_.field_70165_t - this.field_70165_t;
            lvt_4_2_ = lvt_1_1_.field_70161_v - this.field_70161_v;
            lvt_6_2_ = lvt_2_1_ * lvt_2_1_ + lvt_4_2_ * lvt_4_2_;
            if (lvt_6_2_ > 9.0D) {
               lvt_8_2_ = (double)MathHelper.func_76133_a(lvt_6_2_);
               this.field_70159_w += (lvt_2_1_ / lvt_8_2_ * 0.5D - this.field_70159_w) * 0.6000000238418579D;
               this.field_70179_y += (lvt_4_2_ / lvt_8_2_ * 0.5D - this.field_70179_y) * 0.6000000238418579D;
            }
         }
      }

      if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.05000000074505806D) {
         this.field_70177_z = (float)MathHelper.func_181159_b(this.field_70179_y, this.field_70159_w) * 57.295776F - 90.0F;
      }

      super.func_70636_d();

      int lvt_1_3_;
      for(lvt_1_3_ = 0; lvt_1_3_ < 2; ++lvt_1_3_) {
         this.field_82218_g[lvt_1_3_] = this.field_82221_e[lvt_1_3_];
         this.field_82217_f[lvt_1_3_] = this.field_82220_d[lvt_1_3_];
      }

      int lvt_2_4_;
      for(lvt_1_3_ = 0; lvt_1_3_ < 2; ++lvt_1_3_) {
         lvt_2_4_ = this.func_82203_t(lvt_1_3_ + 1);
         Entity lvt_3_1_ = null;
         if (lvt_2_4_ > 0) {
            lvt_3_1_ = this.field_70170_p.func_73045_a(lvt_2_4_);
         }

         if (lvt_3_1_ != null) {
            lvt_4_2_ = this.func_82214_u(lvt_1_3_ + 1);
            lvt_6_2_ = this.func_82208_v(lvt_1_3_ + 1);
            lvt_8_2_ = this.func_82213_w(lvt_1_3_ + 1);
            double lvt_10_1_ = lvt_3_1_.field_70165_t - lvt_4_2_;
            double lvt_12_1_ = lvt_3_1_.field_70163_u + (double)lvt_3_1_.func_70047_e() - lvt_6_2_;
            double lvt_14_1_ = lvt_3_1_.field_70161_v - lvt_8_2_;
            double lvt_16_1_ = (double)MathHelper.func_76133_a(lvt_10_1_ * lvt_10_1_ + lvt_14_1_ * lvt_14_1_);
            float lvt_18_1_ = (float)(MathHelper.func_181159_b(lvt_14_1_, lvt_10_1_) * 180.0D / 3.1415927410125732D) - 90.0F;
            float lvt_19_1_ = (float)(-(MathHelper.func_181159_b(lvt_12_1_, lvt_16_1_) * 180.0D / 3.1415927410125732D));
            this.field_82220_d[lvt_1_3_] = this.func_82204_b(this.field_82220_d[lvt_1_3_], lvt_19_1_, 40.0F);
            this.field_82221_e[lvt_1_3_] = this.func_82204_b(this.field_82221_e[lvt_1_3_], lvt_18_1_, 10.0F);
         } else {
            this.field_82221_e[lvt_1_3_] = this.func_82204_b(this.field_82221_e[lvt_1_3_], this.field_70761_aq, 10.0F);
         }
      }

      boolean lvt_1_4_ = this.func_82205_o();

      for(lvt_2_4_ = 0; lvt_2_4_ < 3; ++lvt_2_4_) {
         double lvt_3_2_ = this.func_82214_u(lvt_2_4_);
         double lvt_5_1_ = this.func_82208_v(lvt_2_4_);
         double lvt_7_1_ = this.func_82213_w(lvt_2_4_);
         this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_3_2_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, lvt_5_1_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, lvt_7_1_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
         if (lvt_1_4_ && this.field_70170_p.field_73012_v.nextInt(4) == 0) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB, lvt_3_2_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, lvt_5_1_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, lvt_7_1_ + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
         }
      }

      if (this.func_82212_n() > 0) {
         for(lvt_2_4_ = 0; lvt_2_4_ < 3; ++lvt_2_4_) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB, this.field_70165_t + this.field_70146_Z.nextGaussian() * 1.0D, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 3.3F), this.field_70161_v + this.field_70146_Z.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
         }
      }

   }

   protected void func_70619_bc() {
      int lvt_1_2_;
      if (this.func_82212_n() > 0) {
         lvt_1_2_ = this.func_82212_n() - 1;
         if (lvt_1_2_ <= 0) {
            this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v, 7.0F, false, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
            this.field_70170_p.func_175669_a(1013, new BlockPos(this), 0);
         }

         this.func_82215_s(lvt_1_2_);
         if (this.field_70173_aa % 10 == 0) {
            this.func_70691_i(10.0F);
         }

      } else {
         super.func_70619_bc();

         int lvt_2_3_;
         for(lvt_1_2_ = 1; lvt_1_2_ < 3; ++lvt_1_2_) {
            if (this.field_70173_aa >= this.field_82223_h[lvt_1_2_ - 1]) {
               this.field_82223_h[lvt_1_2_ - 1] = this.field_70173_aa + 10 + this.field_70146_Z.nextInt(10);
               if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL || this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
                  int[] var10000 = this.field_82224_i;
                  int var10001 = lvt_1_2_ - 1;
                  int var10003 = var10000[lvt_1_2_ - 1];
                  var10000[var10001] = var10000[lvt_1_2_ - 1] + 1;
                  if (var10003 > 15) {
                     float lvt_2_1_ = 10.0F;
                     float lvt_3_1_ = 5.0F;
                     double lvt_4_1_ = MathHelper.func_82716_a(this.field_70146_Z, this.field_70165_t - (double)lvt_2_1_, this.field_70165_t + (double)lvt_2_1_);
                     double lvt_6_1_ = MathHelper.func_82716_a(this.field_70146_Z, this.field_70163_u - (double)lvt_3_1_, this.field_70163_u + (double)lvt_3_1_);
                     double lvt_8_1_ = MathHelper.func_82716_a(this.field_70146_Z, this.field_70161_v - (double)lvt_2_1_, this.field_70161_v + (double)lvt_2_1_);
                     this.func_82209_a(lvt_1_2_ + 1, lvt_4_1_, lvt_6_1_, lvt_8_1_, true);
                     this.field_82224_i[lvt_1_2_ - 1] = 0;
                  }
               }

               lvt_2_3_ = this.func_82203_t(lvt_1_2_);
               if (lvt_2_3_ > 0) {
                  Entity lvt_3_2_ = this.field_70170_p.func_73045_a(lvt_2_3_);
                  if (lvt_3_2_ != null && lvt_3_2_.func_70089_S() && this.func_70068_e(lvt_3_2_) <= 900.0D && this.func_70685_l(lvt_3_2_)) {
                     if (lvt_3_2_ instanceof EntityPlayer && ((EntityPlayer)lvt_3_2_).field_71075_bZ.field_75102_a) {
                        this.func_82211_c(lvt_1_2_, 0);
                     } else {
                        this.func_82216_a(lvt_1_2_ + 1, (EntityLivingBase)lvt_3_2_);
                        this.field_82223_h[lvt_1_2_ - 1] = this.field_70173_aa + 40 + this.field_70146_Z.nextInt(20);
                        this.field_82224_i[lvt_1_2_ - 1] = 0;
                     }
                  } else {
                     this.func_82211_c(lvt_1_2_, 0);
                  }
               } else {
                  List<EntityLivingBase> lvt_3_3_ = this.field_70170_p.func_175647_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(20.0D, 8.0D, 20.0D), Predicates.and(field_82219_bJ, EntitySelectors.field_180132_d));

                  for(int lvt_4_2_ = 0; lvt_4_2_ < 10 && !lvt_3_3_.isEmpty(); ++lvt_4_2_) {
                     EntityLivingBase lvt_5_1_ = (EntityLivingBase)lvt_3_3_.get(this.field_70146_Z.nextInt(lvt_3_3_.size()));
                     if (lvt_5_1_ != this && lvt_5_1_.func_70089_S() && this.func_70685_l(lvt_5_1_)) {
                        if (lvt_5_1_ instanceof EntityPlayer) {
                           if (!((EntityPlayer)lvt_5_1_).field_71075_bZ.field_75102_a) {
                              this.func_82211_c(lvt_1_2_, lvt_5_1_.func_145782_y());
                           }
                        } else {
                           this.func_82211_c(lvt_1_2_, lvt_5_1_.func_145782_y());
                        }
                        break;
                     }

                     lvt_3_3_.remove(lvt_5_1_);
                  }
               }
            }
         }

         if (this.func_70638_az() != null) {
            this.func_82211_c(0, this.func_70638_az().func_145782_y());
         } else {
            this.func_82211_c(0, 0);
         }

         if (this.field_82222_j > 0) {
            --this.field_82222_j;
            if (this.field_82222_j == 0 && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
               lvt_1_2_ = MathHelper.func_76128_c(this.field_70163_u);
               lvt_2_3_ = MathHelper.func_76128_c(this.field_70165_t);
               int lvt_3_4_ = MathHelper.func_76128_c(this.field_70161_v);
               boolean lvt_4_3_ = false;

               for(int lvt_5_2_ = -1; lvt_5_2_ <= 1; ++lvt_5_2_) {
                  for(int lvt_6_2_ = -1; lvt_6_2_ <= 1; ++lvt_6_2_) {
                     for(int lvt_7_1_ = 0; lvt_7_1_ <= 3; ++lvt_7_1_) {
                        int lvt_8_2_ = lvt_2_3_ + lvt_5_2_;
                        int lvt_9_1_ = lvt_1_2_ + lvt_7_1_;
                        int lvt_10_1_ = lvt_3_4_ + lvt_6_2_;
                        BlockPos lvt_11_1_ = new BlockPos(lvt_8_2_, lvt_9_1_, lvt_10_1_);
                        Block lvt_12_1_ = this.field_70170_p.func_180495_p(lvt_11_1_).func_177230_c();
                        if (lvt_12_1_.func_149688_o() != Material.field_151579_a && func_181033_a(lvt_12_1_)) {
                           lvt_4_3_ = this.field_70170_p.func_175655_b(lvt_11_1_, true) || lvt_4_3_;
                        }
                     }
                  }
               }

               if (lvt_4_3_) {
                  this.field_70170_p.func_180498_a((EntityPlayer)null, 1012, new BlockPos(this), 0);
               }
            }
         }

         if (this.field_70173_aa % 20 == 0) {
            this.func_70691_i(1.0F);
         }

      }
   }

   public static boolean func_181033_a(Block p_181033_0_) {
      return p_181033_0_ != Blocks.field_150357_h && p_181033_0_ != Blocks.field_150384_bq && p_181033_0_ != Blocks.field_150378_br && p_181033_0_ != Blocks.field_150483_bI && p_181033_0_ != Blocks.field_180401_cv;
   }

   public void func_82206_m() {
      this.func_82215_s(220);
      this.func_70606_j(this.func_110138_aP() / 3.0F);
   }

   public void func_70110_aj() {
   }

   public int func_70658_aO() {
      return 4;
   }

   private double func_82214_u(int p_82214_1_) {
      if (p_82214_1_ <= 0) {
         return this.field_70165_t;
      } else {
         float lvt_2_1_ = (this.field_70761_aq + (float)(180 * (p_82214_1_ - 1))) / 180.0F * 3.1415927F;
         float lvt_3_1_ = MathHelper.func_76134_b(lvt_2_1_);
         return this.field_70165_t + (double)lvt_3_1_ * 1.3D;
      }
   }

   private double func_82208_v(int p_82208_1_) {
      return p_82208_1_ <= 0 ? this.field_70163_u + 3.0D : this.field_70163_u + 2.2D;
   }

   private double func_82213_w(int p_82213_1_) {
      if (p_82213_1_ <= 0) {
         return this.field_70161_v;
      } else {
         float lvt_2_1_ = (this.field_70761_aq + (float)(180 * (p_82213_1_ - 1))) / 180.0F * 3.1415927F;
         float lvt_3_1_ = MathHelper.func_76126_a(lvt_2_1_);
         return this.field_70161_v + (double)lvt_3_1_ * 1.3D;
      }
   }

   private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
      float lvt_4_1_ = MathHelper.func_76142_g(p_82204_2_ - p_82204_1_);
      if (lvt_4_1_ > p_82204_3_) {
         lvt_4_1_ = p_82204_3_;
      }

      if (lvt_4_1_ < -p_82204_3_) {
         lvt_4_1_ = -p_82204_3_;
      }

      return p_82204_1_ + lvt_4_1_;
   }

   private void func_82216_a(int p_82216_1_, EntityLivingBase p_82216_2_) {
      this.func_82209_a(p_82216_1_, p_82216_2_.field_70165_t, p_82216_2_.field_70163_u + (double)p_82216_2_.func_70047_e() * 0.5D, p_82216_2_.field_70161_v, p_82216_1_ == 0 && this.field_70146_Z.nextFloat() < 0.001F);
   }

   private void func_82209_a(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_) {
      this.field_70170_p.func_180498_a((EntityPlayer)null, 1014, new BlockPos(this), 0);
      double lvt_9_1_ = this.func_82214_u(p_82209_1_);
      double lvt_11_1_ = this.func_82208_v(p_82209_1_);
      double lvt_13_1_ = this.func_82213_w(p_82209_1_);
      double lvt_15_1_ = p_82209_2_ - lvt_9_1_;
      double lvt_17_1_ = p_82209_4_ - lvt_11_1_;
      double lvt_19_1_ = p_82209_6_ - lvt_13_1_;
      EntityWitherSkull lvt_21_1_ = new EntityWitherSkull(this.field_70170_p, this, lvt_15_1_, lvt_17_1_, lvt_19_1_);
      if (p_82209_8_) {
         lvt_21_1_.func_82343_e(true);
      }

      lvt_21_1_.field_70163_u = lvt_11_1_;
      lvt_21_1_.field_70165_t = lvt_9_1_;
      lvt_21_1_.field_70161_v = lvt_13_1_;
      this.field_70170_p.func_72838_d(lvt_21_1_);
   }

   public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {
      this.func_82216_a(0, p_82196_1_);
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else if (p_70097_1_ != DamageSource.field_76369_e && !(p_70097_1_.func_76346_g() instanceof EntityWither)) {
         if (this.func_82212_n() > 0 && p_70097_1_ != DamageSource.field_76380_i) {
            return false;
         } else {
            Entity lvt_3_2_;
            if (this.func_82205_o()) {
               lvt_3_2_ = p_70097_1_.func_76364_f();
               if (lvt_3_2_ instanceof EntityArrow) {
                  return false;
               }
            }

            lvt_3_2_ = p_70097_1_.func_76346_g();
            if (lvt_3_2_ != null && !(lvt_3_2_ instanceof EntityPlayer) && lvt_3_2_ instanceof EntityLivingBase && ((EntityLivingBase)lvt_3_2_).func_70668_bt() == this.func_70668_bt()) {
               return false;
            } else {
               if (this.field_82222_j <= 0) {
                  this.field_82222_j = 20;
               }

               for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_82224_i.length; ++lvt_4_1_) {
                  int[] var10000 = this.field_82224_i;
                  var10000[lvt_4_1_] += 3;
               }

               return super.func_70097_a(p_70097_1_, p_70097_2_);
            }
         }
      } else {
         return false;
      }
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      EntityItem lvt_3_1_ = this.func_145779_a(Items.field_151156_bN, 1);
      if (lvt_3_1_ != null) {
         lvt_3_1_.func_174873_u();
      }

      if (!this.field_70170_p.field_72995_K) {
         Iterator lvt_4_1_ = this.field_70170_p.func_72872_a(EntityPlayer.class, this.func_174813_aQ().func_72314_b(50.0D, 100.0D, 50.0D)).iterator();

         while(lvt_4_1_.hasNext()) {
            EntityPlayer lvt_5_1_ = (EntityPlayer)lvt_4_1_.next();
            lvt_5_1_.func_71029_a(AchievementList.field_150964_J);
         }
      }

   }

   protected void func_70623_bb() {
      this.field_70708_bq = 0;
   }

   public int func_70070_b(float p_70070_1_) {
      return 15728880;
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
   }

   public void func_70690_d(PotionEffect p_70690_1_) {
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6000000238418579D);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
   }

   public float func_82207_a(int p_82207_1_) {
      return this.field_82221_e[p_82207_1_];
   }

   public float func_82210_r(int p_82210_1_) {
      return this.field_82220_d[p_82210_1_];
   }

   public int func_82212_n() {
      return this.field_70180_af.func_75679_c(20);
   }

   public void func_82215_s(int p_82215_1_) {
      this.field_70180_af.func_75692_b(20, p_82215_1_);
   }

   public int func_82203_t(int p_82203_1_) {
      return this.field_70180_af.func_75679_c(17 + p_82203_1_);
   }

   public void func_82211_c(int p_82211_1_, int p_82211_2_) {
      this.field_70180_af.func_75692_b(17 + p_82211_1_, p_82211_2_);
   }

   public boolean func_82205_o() {
      return this.func_110143_aJ() <= this.func_110138_aP() / 2.0F;
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void func_70078_a(Entity p_70078_1_) {
      this.field_70154_o = null;
   }
}
