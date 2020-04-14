package net.minecraft.entity.monster;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZombie extends EntityMob {
   protected static final IAttribute field_110186_bp = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).func_111117_a("Spawn Reinforcements Chance");
   private static final UUID field_110187_bq = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier field_110188_br;
   private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor(this);
   private int field_82234_d;
   private boolean field_146076_bu = false;
   private float field_146074_bv = -1.0F;
   private float field_146073_bw;

   public EntityZombie(World p_i1745_1_) {
      super(p_i1745_1_);
      ((PathNavigateGround)this.func_70661_as()).func_179688_b(true);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
      this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.func_175456_n();
      this.func_70105_a(0.6F, 1.95F);
   }

   protected void func_175456_n() {
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0D, true));
      this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(35.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
      this.func_110140_aT().func_111150_b(field_110186_bp).func_111128_a(this.field_70146_Z.nextDouble() * 0.10000000149011612D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.func_70096_w().func_75682_a(12, (byte)0);
      this.func_70096_w().func_75682_a(13, (byte)0);
      this.func_70096_w().func_75682_a(14, (byte)0);
   }

   public int func_70658_aO() {
      int lvt_1_1_ = super.func_70658_aO() + 2;
      if (lvt_1_1_ > 20) {
         lvt_1_1_ = 20;
      }

      return lvt_1_1_;
   }

   public boolean func_146072_bX() {
      return this.field_146076_bu;
   }

   public void func_146070_a(boolean p_146070_1_) {
      if (this.field_146076_bu != p_146070_1_) {
         this.field_146076_bu = p_146070_1_;
         if (p_146070_1_) {
            this.field_70714_bg.func_75776_a(1, this.field_146075_bs);
         } else {
            this.field_70714_bg.func_85156_a(this.field_146075_bs);
         }
      }

   }

   public boolean func_70631_g_() {
      return this.func_70096_w().func_75683_a(12) == 1;
   }

   protected int func_70693_a(EntityPlayer p_70693_1_) {
      if (this.func_70631_g_()) {
         this.field_70728_aV = (int)((float)this.field_70728_aV * 2.5F);
      }

      return super.func_70693_a(p_70693_1_);
   }

   public void func_82227_f(boolean p_82227_1_) {
      this.func_70096_w().func_75692_b(12, (byte)(p_82227_1_ ? 1 : 0));
      if (this.field_70170_p != null && !this.field_70170_p.field_72995_K) {
         IAttributeInstance lvt_2_1_ = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
         lvt_2_1_.func_111124_b(field_110188_br);
         if (p_82227_1_) {
            lvt_2_1_.func_111121_a(field_110188_br);
         }
      }

      this.func_146071_k(p_82227_1_);
   }

   public boolean func_82231_m() {
      return this.func_70096_w().func_75683_a(13) == 1;
   }

   public void func_82229_g(boolean p_82229_1_) {
      this.func_70096_w().func_75692_b(13, (byte)(p_82229_1_ ? 1 : 0));
   }

   public void func_70636_d() {
      if (this.field_70170_p.func_72935_r() && !this.field_70170_p.field_72995_K && !this.func_70631_g_()) {
         float lvt_1_1_ = this.func_70013_c(1.0F);
         BlockPos lvt_2_1_ = new BlockPos(this.field_70165_t, (double)Math.round(this.field_70163_u), this.field_70161_v);
         if (lvt_1_1_ > 0.5F && this.field_70146_Z.nextFloat() * 30.0F < (lvt_1_1_ - 0.4F) * 2.0F && this.field_70170_p.func_175678_i(lvt_2_1_)) {
            boolean lvt_3_1_ = true;
            ItemStack lvt_4_1_ = this.func_71124_b(4);
            if (lvt_4_1_ != null) {
               if (lvt_4_1_.func_77984_f()) {
                  lvt_4_1_.func_77964_b(lvt_4_1_.func_77952_i() + this.field_70146_Z.nextInt(2));
                  if (lvt_4_1_.func_77952_i() >= lvt_4_1_.func_77958_k()) {
                     this.func_70669_a(lvt_4_1_);
                     this.func_70062_b(4, (ItemStack)null);
                  }
               }

               lvt_3_1_ = false;
            }

            if (lvt_3_1_) {
               this.func_70015_d(8);
            }
         }
      }

      if (this.func_70115_ae() && this.func_70638_az() != null && this.field_70154_o instanceof EntityChicken) {
         ((EntityLiving)this.field_70154_o).func_70661_as().func_75484_a(this.func_70661_as().func_75505_d(), 1.5D);
      }

      super.func_70636_d();
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (super.func_70097_a(p_70097_1_, p_70097_2_)) {
         EntityLivingBase lvt_3_1_ = this.func_70638_az();
         if (lvt_3_1_ == null && p_70097_1_.func_76346_g() instanceof EntityLivingBase) {
            lvt_3_1_ = (EntityLivingBase)p_70097_1_.func_76346_g();
         }

         if (lvt_3_1_ != null && this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD && (double)this.field_70146_Z.nextFloat() < this.func_110148_a(field_110186_bp).func_111126_e()) {
            int lvt_4_1_ = MathHelper.func_76128_c(this.field_70165_t);
            int lvt_5_1_ = MathHelper.func_76128_c(this.field_70163_u);
            int lvt_6_1_ = MathHelper.func_76128_c(this.field_70161_v);
            EntityZombie lvt_7_1_ = new EntityZombie(this.field_70170_p);

            for(int lvt_8_1_ = 0; lvt_8_1_ < 50; ++lvt_8_1_) {
               int lvt_9_1_ = lvt_4_1_ + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
               int lvt_10_1_ = lvt_5_1_ + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
               int lvt_11_1_ = lvt_6_1_ + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
               if (World.func_175683_a(this.field_70170_p, new BlockPos(lvt_9_1_, lvt_10_1_ - 1, lvt_11_1_)) && this.field_70170_p.func_175671_l(new BlockPos(lvt_9_1_, lvt_10_1_, lvt_11_1_)) < 10) {
                  lvt_7_1_.func_70107_b((double)lvt_9_1_, (double)lvt_10_1_, (double)lvt_11_1_);
                  if (!this.field_70170_p.func_175636_b((double)lvt_9_1_, (double)lvt_10_1_, (double)lvt_11_1_, 7.0D) && this.field_70170_p.func_72917_a(lvt_7_1_.func_174813_aQ(), lvt_7_1_) && this.field_70170_p.func_72945_a(lvt_7_1_, lvt_7_1_.func_174813_aQ()).isEmpty() && !this.field_70170_p.func_72953_d(lvt_7_1_.func_174813_aQ())) {
                     this.field_70170_p.func_72838_d(lvt_7_1_);
                     lvt_7_1_.func_70624_b(lvt_3_1_);
                     lvt_7_1_.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(lvt_7_1_)), (IEntityLivingData)null);
                     this.func_110148_a(field_110186_bp).func_111121_a(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                     lvt_7_1_.func_110148_a(field_110186_bp).func_111121_a(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                     break;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void func_70071_h_() {
      if (!this.field_70170_p.field_72995_K && this.func_82230_o()) {
         int lvt_1_1_ = this.func_82233_q();
         this.field_82234_d -= lvt_1_1_;
         if (this.field_82234_d <= 0) {
            this.func_82232_p();
         }
      }

      super.func_70071_h_();
   }

   public boolean func_70652_k(Entity p_70652_1_) {
      boolean lvt_2_1_ = super.func_70652_k(p_70652_1_);
      if (lvt_2_1_) {
         int lvt_3_1_ = this.field_70170_p.func_175659_aa().func_151525_a();
         if (this.func_70694_bm() == null && this.func_70027_ad() && this.field_70146_Z.nextFloat() < (float)lvt_3_1_ * 0.3F) {
            p_70652_1_.func_70015_d(2 * lvt_3_1_);
         }
      }

      return lvt_2_1_;
   }

   protected String func_70639_aQ() {
      return "mob.zombie.say";
   }

   protected String func_70621_aR() {
      return "mob.zombie.hurt";
   }

   protected String func_70673_aS() {
      return "mob.zombie.death";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.zombie.step", 0.15F, 1.0F);
   }

   protected Item func_146068_u() {
      return Items.field_151078_bh;
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.UNDEAD;
   }

   protected void func_82164_bB() {
      switch(this.field_70146_Z.nextInt(3)) {
      case 0:
         this.func_145779_a(Items.field_151042_j, 1);
         break;
      case 1:
         this.func_145779_a(Items.field_151172_bF, 1);
         break;
      case 2:
         this.func_145779_a(Items.field_151174_bG, 1);
      }

   }

   protected void func_180481_a(DifficultyInstance p_180481_1_) {
      super.func_180481_a(p_180481_1_);
      if (this.field_70146_Z.nextFloat() < (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
         int lvt_2_1_ = this.field_70146_Z.nextInt(3);
         if (lvt_2_1_ == 0) {
            this.func_70062_b(0, new ItemStack(Items.field_151040_l));
         } else {
            this.func_70062_b(0, new ItemStack(Items.field_151037_a));
         }
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      if (this.func_70631_g_()) {
         p_70014_1_.func_74757_a("IsBaby", true);
      }

      if (this.func_82231_m()) {
         p_70014_1_.func_74757_a("IsVillager", true);
      }

      p_70014_1_.func_74768_a("ConversionTime", this.func_82230_o() ? this.field_82234_d : -1);
      p_70014_1_.func_74757_a("CanBreakDoors", this.func_146072_bX());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if (p_70037_1_.func_74767_n("IsBaby")) {
         this.func_82227_f(true);
      }

      if (p_70037_1_.func_74767_n("IsVillager")) {
         this.func_82229_g(true);
      }

      if (p_70037_1_.func_150297_b("ConversionTime", 99) && p_70037_1_.func_74762_e("ConversionTime") > -1) {
         this.func_82228_a(p_70037_1_.func_74762_e("ConversionTime"));
      }

      this.func_146070_a(p_70037_1_.func_74767_n("CanBreakDoors"));
   }

   public void func_70074_a(EntityLivingBase p_70074_1_) {
      super.func_70074_a(p_70074_1_);
      if ((this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL || this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) && p_70074_1_ instanceof EntityVillager) {
         if (this.field_70170_p.func_175659_aa() != EnumDifficulty.HARD && this.field_70146_Z.nextBoolean()) {
            return;
         }

         EntityLiving lvt_2_1_ = (EntityLiving)p_70074_1_;
         EntityZombie lvt_3_1_ = new EntityZombie(this.field_70170_p);
         lvt_3_1_.func_82149_j(p_70074_1_);
         this.field_70170_p.func_72900_e(p_70074_1_);
         lvt_3_1_.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(lvt_3_1_)), (IEntityLivingData)null);
         lvt_3_1_.func_82229_g(true);
         if (p_70074_1_.func_70631_g_()) {
            lvt_3_1_.func_82227_f(true);
         }

         lvt_3_1_.func_94061_f(lvt_2_1_.func_175446_cd());
         if (lvt_2_1_.func_145818_k_()) {
            lvt_3_1_.func_96094_a(lvt_2_1_.func_95999_t());
            lvt_3_1_.func_174805_g(lvt_2_1_.func_174833_aM());
         }

         this.field_70170_p.func_72838_d(lvt_3_1_);
         this.field_70170_p.func_180498_a((EntityPlayer)null, 1016, new BlockPos((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v), 0);
      }

   }

   public float func_70047_e() {
      float lvt_1_1_ = 1.74F;
      if (this.func_70631_g_()) {
         lvt_1_1_ = (float)((double)lvt_1_1_ - 0.81D);
      }

      return lvt_1_1_;
   }

   protected boolean func_175448_a(ItemStack p_175448_1_) {
      return p_175448_1_.func_77973_b() == Items.field_151110_aK && this.func_70631_g_() && this.func_70115_ae() ? false : super.func_175448_a(p_175448_1_);
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      IEntityLivingData p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      float lvt_3_1_ = p_180482_1_.func_180170_c();
      this.func_98053_h(this.field_70146_Z.nextFloat() < 0.55F * lvt_3_1_);
      if (p_180482_2_ == null) {
         p_180482_2_ = new EntityZombie.GroupData(this.field_70170_p.field_73012_v.nextFloat() < 0.05F, this.field_70170_p.field_73012_v.nextFloat() < 0.05F);
      }

      if (p_180482_2_ instanceof EntityZombie.GroupData) {
         EntityZombie.GroupData lvt_4_1_ = (EntityZombie.GroupData)p_180482_2_;
         if (lvt_4_1_.field_142046_b) {
            this.func_82229_g(true);
         }

         if (lvt_4_1_.field_142048_a) {
            this.func_82227_f(true);
            if ((double)this.field_70170_p.field_73012_v.nextFloat() < 0.05D) {
               List<EntityChicken> lvt_5_1_ = this.field_70170_p.func_175647_a(EntityChicken.class, this.func_174813_aQ().func_72314_b(5.0D, 3.0D, 5.0D), EntitySelectors.field_152785_b);
               if (!lvt_5_1_.isEmpty()) {
                  EntityChicken lvt_6_1_ = (EntityChicken)lvt_5_1_.get(0);
                  lvt_6_1_.func_152117_i(true);
                  this.func_70078_a(lvt_6_1_);
               }
            } else if ((double)this.field_70170_p.field_73012_v.nextFloat() < 0.05D) {
               EntityChicken lvt_5_2_ = new EntityChicken(this.field_70170_p);
               lvt_5_2_.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
               lvt_5_2_.func_180482_a(p_180482_1_, (IEntityLivingData)null);
               lvt_5_2_.func_152117_i(true);
               this.field_70170_p.func_72838_d(lvt_5_2_);
               this.func_70078_a(lvt_5_2_);
            }
         }
      }

      this.func_146070_a(this.field_70146_Z.nextFloat() < lvt_3_1_ * 0.1F);
      this.func_180481_a(p_180482_1_);
      this.func_180483_b(p_180482_1_);
      if (this.func_71124_b(4) == null) {
         Calendar lvt_4_2_ = this.field_70170_p.func_83015_S();
         if (lvt_4_2_.get(2) + 1 == 10 && lvt_4_2_.get(5) == 31 && this.field_70146_Z.nextFloat() < 0.25F) {
            this.func_70062_b(4, new ItemStack(this.field_70146_Z.nextFloat() < 0.1F ? Blocks.field_150428_aP : Blocks.field_150423_aK));
            this.field_82174_bp[4] = 0.0F;
         }
      }

      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111121_a(new AttributeModifier("Random spawn bonus", this.field_70146_Z.nextDouble() * 0.05000000074505806D, 0));
      double lvt_4_3_ = this.field_70146_Z.nextDouble() * 1.5D * (double)lvt_3_1_;
      if (lvt_4_3_ > 1.0D) {
         this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111121_a(new AttributeModifier("Random zombie-spawn bonus", lvt_4_3_, 2));
      }

      if (this.field_70146_Z.nextFloat() < lvt_3_1_ * 0.05F) {
         this.func_110148_a(field_110186_bp).func_111121_a(new AttributeModifier("Leader zombie bonus", this.field_70146_Z.nextDouble() * 0.25D + 0.5D, 0));
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111121_a(new AttributeModifier("Leader zombie bonus", this.field_70146_Z.nextDouble() * 3.0D + 1.0D, 2));
         this.func_146070_a(true);
      }

      return (IEntityLivingData)p_180482_2_;
   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack lvt_2_1_ = p_70085_1_.func_71045_bC();
      if (lvt_2_1_ != null && lvt_2_1_.func_77973_b() == Items.field_151153_ao && lvt_2_1_.func_77960_j() == 0 && this.func_82231_m() && this.func_70644_a(Potion.field_76437_t)) {
         if (!p_70085_1_.field_71075_bZ.field_75098_d) {
            --lvt_2_1_.field_77994_a;
         }

         if (lvt_2_1_.field_77994_a <= 0) {
            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
         }

         if (!this.field_70170_p.field_72995_K) {
            this.func_82228_a(this.field_70146_Z.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void func_82228_a(int p_82228_1_) {
      this.field_82234_d = p_82228_1_;
      this.func_70096_w().func_75692_b(14, (byte)1);
      this.func_82170_o(Potion.field_76437_t.field_76415_H);
      this.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, p_82228_1_, Math.min(this.field_70170_p.func_175659_aa().func_151525_a() - 1, 0)));
      this.field_70170_p.func_72960_a(this, (byte)16);
   }

   public void func_70103_a(byte p_70103_1_) {
      if (p_70103_1_ == 16) {
         if (!this.func_174814_R()) {
            this.field_70170_p.func_72980_b(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "mob.zombie.remedy", 1.0F + this.field_70146_Z.nextFloat(), this.field_70146_Z.nextFloat() * 0.7F + 0.3F, false);
         }
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   protected boolean func_70692_ba() {
      return !this.func_82230_o();
   }

   public boolean func_82230_o() {
      return this.func_70096_w().func_75683_a(14) == 1;
   }

   protected void func_82232_p() {
      EntityVillager lvt_1_1_ = new EntityVillager(this.field_70170_p);
      lvt_1_1_.func_82149_j(this);
      lvt_1_1_.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(lvt_1_1_)), (IEntityLivingData)null);
      lvt_1_1_.func_82187_q();
      if (this.func_70631_g_()) {
         lvt_1_1_.func_70873_a(-24000);
      }

      this.field_70170_p.func_72900_e(this);
      lvt_1_1_.func_94061_f(this.func_175446_cd());
      if (this.func_145818_k_()) {
         lvt_1_1_.func_96094_a(this.func_95999_t());
         lvt_1_1_.func_174805_g(this.func_174833_aM());
      }

      this.field_70170_p.func_72838_d(lvt_1_1_);
      lvt_1_1_.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
      this.field_70170_p.func_180498_a((EntityPlayer)null, 1017, new BlockPos((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v), 0);
   }

   protected int func_82233_q() {
      int lvt_1_1_ = 1;
      if (this.field_70146_Z.nextFloat() < 0.01F) {
         int lvt_2_1_ = 0;
         BlockPos.MutableBlockPos lvt_3_1_ = new BlockPos.MutableBlockPos();

         for(int lvt_4_1_ = (int)this.field_70165_t - 4; lvt_4_1_ < (int)this.field_70165_t + 4 && lvt_2_1_ < 14; ++lvt_4_1_) {
            for(int lvt_5_1_ = (int)this.field_70163_u - 4; lvt_5_1_ < (int)this.field_70163_u + 4 && lvt_2_1_ < 14; ++lvt_5_1_) {
               for(int lvt_6_1_ = (int)this.field_70161_v - 4; lvt_6_1_ < (int)this.field_70161_v + 4 && lvt_2_1_ < 14; ++lvt_6_1_) {
                  Block lvt_7_1_ = this.field_70170_p.func_180495_p(lvt_3_1_.func_181079_c(lvt_4_1_, lvt_5_1_, lvt_6_1_)).func_177230_c();
                  if (lvt_7_1_ == Blocks.field_150411_aY || lvt_7_1_ == Blocks.field_150324_C) {
                     if (this.field_70146_Z.nextFloat() < 0.3F) {
                        ++lvt_1_1_;
                     }

                     ++lvt_2_1_;
                  }
               }
            }
         }
      }

      return lvt_1_1_;
   }

   public void func_146071_k(boolean p_146071_1_) {
      this.func_146069_a(p_146071_1_ ? 0.5F : 1.0F);
   }

   protected final void func_70105_a(float p_70105_1_, float p_70105_2_) {
      boolean lvt_3_1_ = this.field_146074_bv > 0.0F && this.field_146073_bw > 0.0F;
      this.field_146074_bv = p_70105_1_;
      this.field_146073_bw = p_70105_2_;
      if (!lvt_3_1_) {
         this.func_146069_a(1.0F);
      }

   }

   protected final void func_146069_a(float p_146069_1_) {
      super.func_70105_a(this.field_146074_bv * p_146069_1_, this.field_146073_bw * p_146069_1_);
   }

   public double func_70033_W() {
      return this.func_70631_g_() ? 0.0D : -0.35D;
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      if (p_70645_1_.func_76346_g() instanceof EntityCreeper && !(this instanceof EntityPigZombie) && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70830_n() && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70650_aV()) {
         ((EntityCreeper)p_70645_1_.func_76346_g()).func_175493_co();
         this.func_70099_a(new ItemStack(Items.field_151144_bL, 1, 2), 0.0F);
      }

   }

   static {
      field_110188_br = new AttributeModifier(field_110187_bq, "Baby speed boost", 0.5D, 1);
   }

   class GroupData implements IEntityLivingData {
      public boolean field_142048_a;
      public boolean field_142046_b;

      private GroupData(boolean p_i2348_2_, boolean p_i2348_3_) {
         this.field_142048_a = false;
         this.field_142046_b = false;
         this.field_142048_a = p_i2348_2_;
         this.field_142046_b = p_i2348_3_;
      }
   }
}
