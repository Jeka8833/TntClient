package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWolf extends EntityTameable {
   private float field_70926_e;
   private float field_70924_f;
   private boolean field_70925_g;
   private boolean field_70928_h;
   private float field_70929_i;
   private float field_70927_j;

   public EntityWolf(World p_i1696_1_) {
      super(p_i1696_1_);
      this.func_70105_a(0.6F, 0.8F);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, this.field_70911_d);
      this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.field_70714_bg.func_75776_a(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
      this.field_70714_bg.func_75776_a(6, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(8, new EntityAIBeg(this, 8.0F));
      this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
      this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
      this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70715_bh.func_75776_a(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate<Entity>() {
         public boolean apply(Entity p_apply_1_) {
            return p_apply_1_ instanceof EntitySheep || p_apply_1_ instanceof EntityRabbit;
         }
      }));
      this.field_70715_bh.func_75776_a(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
      this.func_70903_f(false);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
      if (this.func_70909_n()) {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
      } else {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
      }

      this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
   }

   public void func_70624_b(EntityLivingBase p_70624_1_) {
      super.func_70624_b(p_70624_1_);
      if (p_70624_1_ == null) {
         this.func_70916_h(false);
      } else if (!this.func_70909_n()) {
         this.func_70916_h(true);
      }

   }

   protected void func_70619_bc() {
      this.field_70180_af.func_75692_b(18, this.func_110143_aJ());
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(18, new Float(this.func_110143_aJ()));
      this.field_70180_af.func_75682_a(19, new Byte((byte)0));
      this.field_70180_af.func_75682_a(20, new Byte((byte)EnumDyeColor.RED.func_176765_a()));
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.wolf.step", 0.15F, 1.0F);
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("Angry", this.func_70919_bu());
      p_70014_1_.func_74774_a("CollarColor", (byte)this.func_175546_cu().func_176767_b());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_70916_h(p_70037_1_.func_74767_n("Angry"));
      if (p_70037_1_.func_150297_b("CollarColor", 99)) {
         this.func_175547_a(EnumDyeColor.func_176766_a(p_70037_1_.func_74771_c("CollarColor")));
      }

   }

   protected String func_70639_aQ() {
      if (this.func_70919_bu()) {
         return "mob.wolf.growl";
      } else if (this.field_70146_Z.nextInt(3) == 0) {
         return this.func_70909_n() && this.field_70180_af.func_111145_d(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting";
      } else {
         return "mob.wolf.bark";
      }
   }

   protected String func_70621_aR() {
      return "mob.wolf.hurt";
   }

   protected String func_70673_aS() {
      return "mob.wolf.death";
   }

   protected float func_70599_aP() {
      return 0.4F;
   }

   protected Item func_146068_u() {
      return Item.func_150899_d(-1);
   }

   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K && this.field_70925_g && !this.field_70928_h && !this.func_70781_l() && this.field_70122_E) {
         this.field_70928_h = true;
         this.field_70929_i = 0.0F;
         this.field_70927_j = 0.0F;
         this.field_70170_p.func_72960_a(this, (byte)8);
      }

      if (!this.field_70170_p.field_72995_K && this.func_70638_az() == null && this.func_70919_bu()) {
         this.func_70916_h(false);
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.field_70924_f = this.field_70926_e;
      if (this.func_70922_bv()) {
         this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
      } else {
         this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
      }

      if (this.func_70026_G()) {
         this.field_70925_g = true;
         this.field_70928_h = false;
         this.field_70929_i = 0.0F;
         this.field_70927_j = 0.0F;
      } else if ((this.field_70925_g || this.field_70928_h) && this.field_70928_h) {
         if (this.field_70929_i == 0.0F) {
            this.func_85030_a("mob.wolf.shake", this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
         }

         this.field_70927_j = this.field_70929_i;
         this.field_70929_i += 0.05F;
         if (this.field_70927_j >= 2.0F) {
            this.field_70925_g = false;
            this.field_70928_h = false;
            this.field_70927_j = 0.0F;
            this.field_70929_i = 0.0F;
         }

         if (this.field_70929_i > 0.4F) {
            float lvt_1_1_ = (float)this.func_174813_aQ().field_72338_b;
            int lvt_2_1_ = (int)(MathHelper.func_76126_a((this.field_70929_i - 0.4F) * 3.1415927F) * 7.0F);

            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
               float lvt_4_1_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
               float lvt_5_1_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, this.field_70165_t + (double)lvt_4_1_, (double)(lvt_1_1_ + 0.8F), this.field_70161_v + (double)lvt_5_1_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }
         }
      }

   }

   public boolean func_70921_u() {
      return this.field_70925_g;
   }

   public float func_70915_j(float p_70915_1_) {
      return 0.75F + (this.field_70927_j + (this.field_70929_i - this.field_70927_j) * p_70915_1_) / 2.0F * 0.25F;
   }

   public float func_70923_f(float p_70923_1_, float p_70923_2_) {
      float lvt_3_1_ = (this.field_70927_j + (this.field_70929_i - this.field_70927_j) * p_70923_1_ + p_70923_2_) / 1.8F;
      if (lvt_3_1_ < 0.0F) {
         lvt_3_1_ = 0.0F;
      } else if (lvt_3_1_ > 1.0F) {
         lvt_3_1_ = 1.0F;
      }

      return MathHelper.func_76126_a(lvt_3_1_ * 3.1415927F) * MathHelper.func_76126_a(lvt_3_1_ * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
   }

   public float func_70917_k(float p_70917_1_) {
      return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * p_70917_1_) * 0.15F * 3.1415927F;
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.8F;
   }

   public int func_70646_bf() {
      return this.func_70906_o() ? 20 : super.func_70646_bf();
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         Entity lvt_3_1_ = p_70097_1_.func_76346_g();
         this.field_70911_d.func_75270_a(false);
         if (lvt_3_1_ != null && !(lvt_3_1_ instanceof EntityPlayer) && !(lvt_3_1_ instanceof EntityArrow)) {
            p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
         }

         return super.func_70097_a(p_70097_1_, p_70097_2_);
      }
   }

   public boolean func_70652_k(Entity p_70652_1_) {
      boolean lvt_2_1_ = p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), (float)((int)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e()));
      if (lvt_2_1_) {
         this.func_174815_a(this, p_70652_1_);
      }

      return lvt_2_1_;
   }

   public void func_70903_f(boolean p_70903_1_) {
      super.func_70903_f(p_70903_1_);
      if (p_70903_1_) {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
      } else {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
      }

      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack lvt_2_1_ = p_70085_1_.field_71071_by.func_70448_g();
      if (this.func_70909_n()) {
         if (lvt_2_1_ != null) {
            if (lvt_2_1_.func_77973_b() instanceof ItemFood) {
               ItemFood lvt_3_1_ = (ItemFood)lvt_2_1_.func_77973_b();
               if (lvt_3_1_.func_77845_h() && this.field_70180_af.func_111145_d(18) < 20.0F) {
                  if (!p_70085_1_.field_71075_bZ.field_75098_d) {
                     --lvt_2_1_.field_77994_a;
                  }

                  this.func_70691_i((float)lvt_3_1_.func_150905_g(lvt_2_1_));
                  if (lvt_2_1_.field_77994_a <= 0) {
                     p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
                  }

                  return true;
               }
            } else if (lvt_2_1_.func_77973_b() == Items.field_151100_aR) {
               EnumDyeColor lvt_3_2_ = EnumDyeColor.func_176766_a(lvt_2_1_.func_77960_j());
               if (lvt_3_2_ != this.func_175546_cu()) {
                  this.func_175547_a(lvt_3_2_);
                  if (!p_70085_1_.field_71075_bZ.field_75098_d && --lvt_2_1_.field_77994_a <= 0) {
                     p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if (this.func_152114_e(p_70085_1_) && !this.field_70170_p.field_72995_K && !this.func_70877_b(lvt_2_1_)) {
            this.field_70911_d.func_75270_a(!this.func_70906_o());
            this.field_70703_bu = false;
            this.field_70699_by.func_75499_g();
            this.func_70624_b((EntityLivingBase)null);
         }
      } else if (lvt_2_1_ != null && lvt_2_1_.func_77973_b() == Items.field_151103_aS && !this.func_70919_bu()) {
         if (!p_70085_1_.field_71075_bZ.field_75098_d) {
            --lvt_2_1_.field_77994_a;
         }

         if (lvt_2_1_.field_77994_a <= 0) {
            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
         }

         if (!this.field_70170_p.field_72995_K) {
            if (this.field_70146_Z.nextInt(3) == 0) {
               this.func_70903_f(true);
               this.field_70699_by.func_75499_g();
               this.func_70624_b((EntityLivingBase)null);
               this.field_70911_d.func_75270_a(true);
               this.func_70606_j(20.0F);
               this.func_152115_b(p_70085_1_.func_110124_au().toString());
               this.func_70908_e(true);
               this.field_70170_p.func_72960_a(this, (byte)7);
            } else {
               this.func_70908_e(false);
               this.field_70170_p.func_72960_a(this, (byte)6);
            }
         }

         return true;
      }

      return super.func_70085_c(p_70085_1_);
   }

   public void func_70103_a(byte p_70103_1_) {
      if (p_70103_1_ == 8) {
         this.field_70928_h = true;
         this.field_70929_i = 0.0F;
         this.field_70927_j = 0.0F;
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public float func_70920_v() {
      if (this.func_70919_bu()) {
         return 1.5393804F;
      } else {
         return this.func_70909_n() ? (0.55F - (20.0F - this.field_70180_af.func_111145_d(18)) * 0.02F) * 3.1415927F : 0.62831855F;
      }
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      if (p_70877_1_ == null) {
         return false;
      } else {
         return !(p_70877_1_.func_77973_b() instanceof ItemFood) ? false : ((ItemFood)p_70877_1_.func_77973_b()).func_77845_h();
      }
   }

   public int func_70641_bl() {
      return 8;
   }

   public boolean func_70919_bu() {
      return (this.field_70180_af.func_75683_a(16) & 2) != 0;
   }

   public void func_70916_h(boolean p_70916_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(16);
      if (p_70916_1_) {
         this.field_70180_af.func_75692_b(16, (byte)(lvt_2_1_ | 2));
      } else {
         this.field_70180_af.func_75692_b(16, (byte)(lvt_2_1_ & -3));
      }

   }

   public EnumDyeColor func_175546_cu() {
      return EnumDyeColor.func_176766_a(this.field_70180_af.func_75683_a(20) & 15);
   }

   public void func_175547_a(EnumDyeColor p_175547_1_) {
      this.field_70180_af.func_75692_b(20, (byte)(p_175547_1_.func_176767_b() & 15));
   }

   public EntityWolf func_90011_a(EntityAgeable p_90011_1_) {
      EntityWolf lvt_2_1_ = new EntityWolf(this.field_70170_p);
      String lvt_3_1_ = this.func_152113_b();
      if (lvt_3_1_ != null && lvt_3_1_.trim().length() > 0) {
         lvt_2_1_.func_152115_b(lvt_3_1_);
         lvt_2_1_.func_70903_f(true);
      }

      return lvt_2_1_;
   }

   public void func_70918_i(boolean p_70918_1_) {
      if (p_70918_1_) {
         this.field_70180_af.func_75692_b(19, (byte)1);
      } else {
         this.field_70180_af.func_75692_b(19, (byte)0);
      }

   }

   public boolean func_70878_b(EntityAnimal p_70878_1_) {
      if (p_70878_1_ == this) {
         return false;
      } else if (!this.func_70909_n()) {
         return false;
      } else if (!(p_70878_1_ instanceof EntityWolf)) {
         return false;
      } else {
         EntityWolf lvt_2_1_ = (EntityWolf)p_70878_1_;
         if (!lvt_2_1_.func_70909_n()) {
            return false;
         } else if (lvt_2_1_.func_70906_o()) {
            return false;
         } else {
            return this.func_70880_s() && lvt_2_1_.func_70880_s();
         }
      }
   }

   public boolean func_70922_bv() {
      return this.field_70180_af.func_75683_a(19) == 1;
   }

   protected boolean func_70692_ba() {
      return !this.func_70909_n() && this.field_70173_aa > 2400;
   }

   public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
         if (p_142018_1_ instanceof EntityWolf) {
            EntityWolf lvt_3_1_ = (EntityWolf)p_142018_1_;
            if (lvt_3_1_.func_70909_n() && lvt_3_1_.func_70902_q() == p_142018_2_) {
               return false;
            }
         }

         if (p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer)p_142018_2_).func_96122_a((EntityPlayer)p_142018_1_)) {
            return false;
         } else {
            return !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).func_110248_bS();
         }
      } else {
         return false;
      }
   }

   public boolean func_110164_bC() {
      return !this.func_70919_bu() && super.func_110164_bC();
   }
}
