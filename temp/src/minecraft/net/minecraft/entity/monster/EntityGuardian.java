package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGuardian extends EntityMob {
   private float field_175482_b;
   private float field_175484_c;
   private float field_175483_bk;
   private float field_175485_bl;
   private float field_175486_bm;
   private EntityLivingBase field_175478_bn;
   private int field_175479_bo;
   private boolean field_175480_bp;
   private EntityAIWander field_175481_bq;

   public EntityGuardian(World p_i45835_1_) {
      super(p_i45835_1_);
      this.field_70728_aV = 10;
      this.func_70105_a(0.85F, 0.85F);
      this.field_70714_bg.func_75776_a(4, new EntityGuardian.AIGuardianAttack(this));
      EntityAIMoveTowardsRestriction lvt_2_1_;
      this.field_70714_bg.func_75776_a(5, lvt_2_1_ = new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.field_70714_bg.func_75776_a(7, this.field_175481_bq = new EntityAIWander(this, 1.0D, 80));
      this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityGuardian.class, 12.0F, 0.01F));
      this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
      this.field_175481_bq.func_75248_a(3);
      lvt_2_1_.func_75248_a(3);
      this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new EntityGuardian.GuardianTargetSelector(this)));
      this.field_70765_h = new EntityGuardian.GuardianMoveHelper(this);
      this.field_175484_c = this.field_175482_b = this.field_70146_Z.nextFloat();
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_175467_a(p_70037_1_.func_74767_n("Elder"));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("Elder", this.func_175461_cl());
   }

   protected PathNavigate func_175447_b(World p_175447_1_) {
      return new PathNavigateSwimmer(this, p_175447_1_);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, 0);
      this.field_70180_af.func_75682_a(17, 0);
   }

   private boolean func_175468_a(int p_175468_1_) {
      return (this.field_70180_af.func_75679_c(16) & p_175468_1_) != 0;
   }

   private void func_175473_a(int p_175473_1_, boolean p_175473_2_) {
      int lvt_3_1_ = this.field_70180_af.func_75679_c(16);
      if (p_175473_2_) {
         this.field_70180_af.func_75692_b(16, lvt_3_1_ | p_175473_1_);
      } else {
         this.field_70180_af.func_75692_b(16, lvt_3_1_ & ~p_175473_1_);
      }

   }

   public boolean func_175472_n() {
      return this.func_175468_a(2);
   }

   private void func_175476_l(boolean p_175476_1_) {
      this.func_175473_a(2, p_175476_1_);
   }

   public int func_175464_ck() {
      return this.func_175461_cl() ? 60 : 80;
   }

   public boolean func_175461_cl() {
      return this.func_175468_a(4);
   }

   public void func_175467_a(boolean p_175467_1_) {
      this.func_175473_a(4, p_175467_1_);
      if (p_175467_1_) {
         this.func_70105_a(1.9975F, 1.9975F);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
         this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0D);
         this.func_110163_bv();
         this.field_175481_bq.func_179479_b(400);
      }

   }

   public void func_175465_cm() {
      this.func_175467_a(true);
      this.field_175486_bm = this.field_175485_bl = 1.0F;
   }

   private void func_175463_b(int p_175463_1_) {
      this.field_70180_af.func_75692_b(17, p_175463_1_);
   }

   public boolean func_175474_cn() {
      return this.field_70180_af.func_75679_c(17) != 0;
   }

   public EntityLivingBase func_175466_co() {
      if (!this.func_175474_cn()) {
         return null;
      } else if (this.field_70170_p.field_72995_K) {
         if (this.field_175478_bn != null) {
            return this.field_175478_bn;
         } else {
            Entity lvt_1_1_ = this.field_70170_p.func_73045_a(this.field_70180_af.func_75679_c(17));
            if (lvt_1_1_ instanceof EntityLivingBase) {
               this.field_175478_bn = (EntityLivingBase)lvt_1_1_;
               return this.field_175478_bn;
            } else {
               return null;
            }
         }
      } else {
         return this.func_70638_az();
      }
   }

   public void func_145781_i(int p_145781_1_) {
      super.func_145781_i(p_145781_1_);
      if (p_145781_1_ == 16) {
         if (this.func_175461_cl() && this.field_70130_N < 1.0F) {
            this.func_70105_a(1.9975F, 1.9975F);
         }
      } else if (p_145781_1_ == 17) {
         this.field_175479_bo = 0;
         this.field_175478_bn = null;
      }

   }

   public int func_70627_aG() {
      return 160;
   }

   protected String func_70639_aQ() {
      if (!this.func_70090_H()) {
         return "mob.guardian.land.idle";
      } else {
         return this.func_175461_cl() ? "mob.guardian.elder.idle" : "mob.guardian.idle";
      }
   }

   protected String func_70621_aR() {
      if (!this.func_70090_H()) {
         return "mob.guardian.land.hit";
      } else {
         return this.func_175461_cl() ? "mob.guardian.elder.hit" : "mob.guardian.hit";
      }
   }

   protected String func_70673_aS() {
      if (!this.func_70090_H()) {
         return "mob.guardian.land.death";
      } else {
         return this.func_175461_cl() ? "mob.guardian.elder.death" : "mob.guardian.death";
      }
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.5F;
   }

   public float func_180484_a(BlockPos p_180484_1_) {
      return this.field_70170_p.func_180495_p(p_180484_1_).func_177230_c().func_149688_o() == Material.field_151586_h ? 10.0F + this.field_70170_p.func_175724_o(p_180484_1_) - 0.5F : super.func_180484_a(p_180484_1_);
   }

   public void func_70636_d() {
      if (this.field_70170_p.field_72995_K) {
         this.field_175484_c = this.field_175482_b;
         if (!this.func_70090_H()) {
            this.field_175483_bk = 2.0F;
            if (this.field_70181_x > 0.0D && this.field_175480_bp && !this.func_174814_R()) {
               this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.guardian.flop", 1.0F, 1.0F, false);
            }

            this.field_175480_bp = this.field_70181_x < 0.0D && this.field_70170_p.func_175677_d((new BlockPos(this)).func_177977_b(), false);
         } else if (this.func_175472_n()) {
            if (this.field_175483_bk < 0.5F) {
               this.field_175483_bk = 4.0F;
            } else {
               this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
            }
         } else {
            this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
         }

         this.field_175482_b += this.field_175483_bk;
         this.field_175486_bm = this.field_175485_bl;
         if (!this.func_70090_H()) {
            this.field_175485_bl = this.field_70146_Z.nextFloat();
         } else if (this.func_175472_n()) {
            this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
         } else {
            this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
         }

         if (this.func_175472_n() && this.func_70090_H()) {
            Vec3 lvt_1_1_ = this.func_70676_i(0.0F);

            for(int lvt_2_1_ = 0; lvt_2_1_ < 2; ++lvt_2_1_) {
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N - lvt_1_1_.field_72450_a * 1.5D, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - lvt_1_1_.field_72448_b * 1.5D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N - lvt_1_1_.field_72449_c * 1.5D, 0.0D, 0.0D, 0.0D);
            }
         }

         if (this.func_175474_cn()) {
            if (this.field_175479_bo < this.func_175464_ck()) {
               ++this.field_175479_bo;
            }

            EntityLivingBase lvt_1_2_ = this.func_175466_co();
            if (lvt_1_2_ != null) {
               this.func_70671_ap().func_75651_a(lvt_1_2_, 90.0F, 90.0F);
               this.func_70671_ap().func_75649_a();
               double lvt_2_2_ = (double)this.func_175477_p(0.0F);
               double lvt_4_1_ = lvt_1_2_.field_70165_t - this.field_70165_t;
               double lvt_6_1_ = lvt_1_2_.field_70163_u + (double)(lvt_1_2_.field_70131_O * 0.5F) - (this.field_70163_u + (double)this.func_70047_e());
               double lvt_8_1_ = lvt_1_2_.field_70161_v - this.field_70161_v;
               double lvt_10_1_ = Math.sqrt(lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_ + lvt_8_1_ * lvt_8_1_);
               lvt_4_1_ /= lvt_10_1_;
               lvt_6_1_ /= lvt_10_1_;
               lvt_8_1_ /= lvt_10_1_;
               double lvt_12_1_ = this.field_70146_Z.nextDouble();

               while(lvt_12_1_ < lvt_10_1_) {
                  lvt_12_1_ += 1.8D - lvt_2_2_ + this.field_70146_Z.nextDouble() * (1.7D - lvt_2_2_);
                  this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t + lvt_4_1_ * lvt_12_1_, this.field_70163_u + lvt_6_1_ * lvt_12_1_ + (double)this.func_70047_e(), this.field_70161_v + lvt_8_1_ * lvt_12_1_, 0.0D, 0.0D, 0.0D);
               }
            }
         }
      }

      if (this.field_70171_ac) {
         this.func_70050_g(300);
      } else if (this.field_70122_E) {
         this.field_70181_x += 0.5D;
         this.field_70159_w += (double)((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.field_70179_y += (double)((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.field_70177_z = this.field_70146_Z.nextFloat() * 360.0F;
         this.field_70122_E = false;
         this.field_70160_al = true;
      }

      if (this.func_175474_cn()) {
         this.field_70177_z = this.field_70759_as;
      }

      super.func_70636_d();
   }

   public float func_175471_a(float p_175471_1_) {
      return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
   }

   public float func_175469_o(float p_175469_1_) {
      return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
   }

   public float func_175477_p(float p_175477_1_) {
      return ((float)this.field_175479_bo + p_175477_1_) / (float)this.func_175464_ck();
   }

   protected void func_70619_bc() {
      super.func_70619_bc();
      if (this.func_175461_cl()) {
         int lvt_1_1_ = true;
         int lvt_2_1_ = true;
         int lvt_3_1_ = true;
         int lvt_4_1_ = true;
         if ((this.field_70173_aa + this.func_145782_y()) % 1200 == 0) {
            Potion lvt_5_1_ = Potion.field_76419_f;
            List<EntityPlayerMP> lvt_6_1_ = this.field_70170_p.func_175661_b(EntityPlayerMP.class, new Predicate<EntityPlayerMP>() {
               public boolean apply(EntityPlayerMP p_apply_1_) {
                  return EntityGuardian.this.func_70068_e(p_apply_1_) < 2500.0D && p_apply_1_.field_71134_c.func_180239_c();
               }
            });
            Iterator lvt_7_1_ = lvt_6_1_.iterator();

            label30:
            while(true) {
               EntityPlayerMP lvt_8_1_;
               do {
                  if (!lvt_7_1_.hasNext()) {
                     break label30;
                  }

                  lvt_8_1_ = (EntityPlayerMP)lvt_7_1_.next();
               } while(lvt_8_1_.func_70644_a(lvt_5_1_) && lvt_8_1_.func_70660_b(lvt_5_1_).func_76458_c() >= 2 && lvt_8_1_.func_70660_b(lvt_5_1_).func_76459_b() >= 1200);

               lvt_8_1_.field_71135_a.func_147359_a(new S2BPacketChangeGameState(10, 0.0F));
               lvt_8_1_.func_70690_d(new PotionEffect(lvt_5_1_.field_76415_H, 6000, 2));
            }
         }

         if (!this.func_110175_bO()) {
            this.func_175449_a(new BlockPos(this), 16);
         }
      }

   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int lvt_3_1_ = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(p_70628_2_ + 1);
      if (lvt_3_1_ > 0) {
         this.func_70099_a(new ItemStack(Items.field_179562_cC, lvt_3_1_, 0), 1.0F);
      }

      if (this.field_70146_Z.nextInt(3 + p_70628_2_) > 1) {
         this.func_70099_a(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.COD.func_150976_a()), 1.0F);
      } else if (this.field_70146_Z.nextInt(3 + p_70628_2_) > 1) {
         this.func_70099_a(new ItemStack(Items.field_179563_cD, 1, 0), 1.0F);
      }

      if (p_70628_1_ && this.func_175461_cl()) {
         this.func_70099_a(new ItemStack(Blocks.field_150360_v, 1, 1), 1.0F);
      }

   }

   protected void func_82164_bB() {
      ItemStack lvt_1_1_ = ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, EntityFishHook.func_174855_j())).func_150708_a(this.field_70146_Z);
      this.func_70099_a(lvt_1_1_, 1.0F);
   }

   protected boolean func_70814_o() {
      return true;
   }

   public boolean func_70058_J() {
      return this.field_70170_p.func_72917_a(this.func_174813_aQ(), this) && this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty();
   }

   public boolean func_70601_bi() {
      return (this.field_70146_Z.nextInt(20) == 0 || !this.field_70170_p.func_175710_j(new BlockPos(this))) && super.func_70601_bi();
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (!this.func_175472_n() && !p_70097_1_.func_82725_o() && p_70097_1_.func_76364_f() instanceof EntityLivingBase) {
         EntityLivingBase lvt_3_1_ = (EntityLivingBase)p_70097_1_.func_76364_f();
         if (!p_70097_1_.func_94541_c()) {
            lvt_3_1_.func_70097_a(DamageSource.func_92087_a(this), 2.0F);
            lvt_3_1_.func_85030_a("damage.thorns", 0.5F, 1.0F);
         }
      }

      this.field_175481_bq.func_179480_f();
      return super.func_70097_a(p_70097_1_, p_70097_2_);
   }

   public int func_70646_bf() {
      return 180;
   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      if (this.func_70613_aW()) {
         if (this.func_70090_H()) {
            this.func_70060_a(p_70612_1_, p_70612_2_, 0.1F);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.8999999761581421D;
            this.field_70181_x *= 0.8999999761581421D;
            this.field_70179_y *= 0.8999999761581421D;
            if (!this.func_175472_n() && this.func_70638_az() == null) {
               this.field_70181_x -= 0.005D;
            }
         } else {
            super.func_70612_e(p_70612_1_, p_70612_2_);
         }
      } else {
         super.func_70612_e(p_70612_1_, p_70612_2_);
      }

   }

   static class GuardianMoveHelper extends EntityMoveHelper {
      private EntityGuardian field_179930_g;

      public GuardianMoveHelper(EntityGuardian p_i45831_1_) {
         super(p_i45831_1_);
         this.field_179930_g = p_i45831_1_;
      }

      public void func_75641_c() {
         if (this.field_75643_f && !this.field_179930_g.func_70661_as().func_75500_f()) {
            double lvt_1_1_ = this.field_75646_b - this.field_179930_g.field_70165_t;
            double lvt_3_1_ = this.field_75647_c - this.field_179930_g.field_70163_u;
            double lvt_5_1_ = this.field_75644_d - this.field_179930_g.field_70161_v;
            double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
            lvt_7_1_ = (double)MathHelper.func_76133_a(lvt_7_1_);
            lvt_3_1_ /= lvt_7_1_;
            float lvt_9_1_ = (float)(MathHelper.func_181159_b(lvt_5_1_, lvt_1_1_) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.field_179930_g.field_70177_z = this.func_75639_a(this.field_179930_g.field_70177_z, lvt_9_1_, 30.0F);
            this.field_179930_g.field_70761_aq = this.field_179930_g.field_70177_z;
            float lvt_10_1_ = (float)(this.field_75645_e * this.field_179930_g.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
            this.field_179930_g.func_70659_e(this.field_179930_g.func_70689_ay() + (lvt_10_1_ - this.field_179930_g.func_70689_ay()) * 0.125F);
            double lvt_11_1_ = Math.sin((double)(this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.5D) * 0.05D;
            double lvt_13_1_ = Math.cos((double)(this.field_179930_g.field_70177_z * 3.1415927F / 180.0F));
            double lvt_15_1_ = Math.sin((double)(this.field_179930_g.field_70177_z * 3.1415927F / 180.0F));
            EntityGuardian var10000 = this.field_179930_g;
            var10000.field_70159_w += lvt_11_1_ * lvt_13_1_;
            var10000 = this.field_179930_g;
            var10000.field_70179_y += lvt_11_1_ * lvt_15_1_;
            lvt_11_1_ = Math.sin((double)(this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.75D) * 0.05D;
            var10000 = this.field_179930_g;
            var10000.field_70181_x += lvt_11_1_ * (lvt_15_1_ + lvt_13_1_) * 0.25D;
            var10000 = this.field_179930_g;
            var10000.field_70181_x += (double)this.field_179930_g.func_70689_ay() * lvt_3_1_ * 0.1D;
            EntityLookHelper lvt_17_1_ = this.field_179930_g.func_70671_ap();
            double lvt_18_1_ = this.field_179930_g.field_70165_t + lvt_1_1_ / lvt_7_1_ * 2.0D;
            double lvt_20_1_ = (double)this.field_179930_g.func_70047_e() + this.field_179930_g.field_70163_u + lvt_3_1_ / lvt_7_1_ * 1.0D;
            double lvt_22_1_ = this.field_179930_g.field_70161_v + lvt_5_1_ / lvt_7_1_ * 2.0D;
            double lvt_24_1_ = lvt_17_1_.func_180423_e();
            double lvt_26_1_ = lvt_17_1_.func_180422_f();
            double lvt_28_1_ = lvt_17_1_.func_180421_g();
            if (!lvt_17_1_.func_180424_b()) {
               lvt_24_1_ = lvt_18_1_;
               lvt_26_1_ = lvt_20_1_;
               lvt_28_1_ = lvt_22_1_;
            }

            this.field_179930_g.func_70671_ap().func_75650_a(lvt_24_1_ + (lvt_18_1_ - lvt_24_1_) * 0.125D, lvt_26_1_ + (lvt_20_1_ - lvt_26_1_) * 0.125D, lvt_28_1_ + (lvt_22_1_ - lvt_28_1_) * 0.125D, 10.0F, 40.0F);
            this.field_179930_g.func_175476_l(true);
         } else {
            this.field_179930_g.func_70659_e(0.0F);
            this.field_179930_g.func_175476_l(false);
         }
      }
   }

   static class AIGuardianAttack extends EntityAIBase {
      private EntityGuardian field_179456_a;
      private int field_179455_b;

      public AIGuardianAttack(EntityGuardian p_i45833_1_) {
         this.field_179456_a = p_i45833_1_;
         this.func_75248_a(3);
      }

      public boolean func_75250_a() {
         EntityLivingBase lvt_1_1_ = this.field_179456_a.func_70638_az();
         return lvt_1_1_ != null && lvt_1_1_.func_70089_S();
      }

      public boolean func_75253_b() {
         return super.func_75253_b() && (this.field_179456_a.func_175461_cl() || this.field_179456_a.func_70068_e(this.field_179456_a.func_70638_az()) > 9.0D);
      }

      public void func_75249_e() {
         this.field_179455_b = -10;
         this.field_179456_a.func_70661_as().func_75499_g();
         this.field_179456_a.func_70671_ap().func_75651_a(this.field_179456_a.func_70638_az(), 90.0F, 90.0F);
         this.field_179456_a.field_70160_al = true;
      }

      public void func_75251_c() {
         this.field_179456_a.func_175463_b(0);
         this.field_179456_a.func_70624_b((EntityLivingBase)null);
         this.field_179456_a.field_175481_bq.func_179480_f();
      }

      public void func_75246_d() {
         EntityLivingBase lvt_1_1_ = this.field_179456_a.func_70638_az();
         this.field_179456_a.func_70661_as().func_75499_g();
         this.field_179456_a.func_70671_ap().func_75651_a(lvt_1_1_, 90.0F, 90.0F);
         if (!this.field_179456_a.func_70685_l(lvt_1_1_)) {
            this.field_179456_a.func_70624_b((EntityLivingBase)null);
         } else {
            ++this.field_179455_b;
            if (this.field_179455_b == 0) {
               this.field_179456_a.func_175463_b(this.field_179456_a.func_70638_az().func_145782_y());
               this.field_179456_a.field_70170_p.func_72960_a(this.field_179456_a, (byte)21);
            } else if (this.field_179455_b >= this.field_179456_a.func_175464_ck()) {
               float lvt_2_1_ = 1.0F;
               if (this.field_179456_a.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
                  lvt_2_1_ += 2.0F;
               }

               if (this.field_179456_a.func_175461_cl()) {
                  lvt_2_1_ += 2.0F;
               }

               lvt_1_1_.func_70097_a(DamageSource.func_76354_b(this.field_179456_a, this.field_179456_a), lvt_2_1_);
               lvt_1_1_.func_70097_a(DamageSource.func_76358_a(this.field_179456_a), (float)this.field_179456_a.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
               this.field_179456_a.func_70624_b((EntityLivingBase)null);
            } else if (this.field_179455_b >= 60 && this.field_179455_b % 20 == 0) {
            }

            super.func_75246_d();
         }
      }
   }

   static class GuardianTargetSelector implements Predicate<EntityLivingBase> {
      private EntityGuardian field_179916_a;

      public GuardianTargetSelector(EntityGuardian p_i45832_1_) {
         this.field_179916_a = p_i45832_1_;
      }

      public boolean apply(EntityLivingBase p_apply_1_) {
         return (p_apply_1_ instanceof EntityPlayer || p_apply_1_ instanceof EntitySquid) && p_apply_1_.func_70068_e(this.field_179916_a) > 9.0D;
      }
   }
}
