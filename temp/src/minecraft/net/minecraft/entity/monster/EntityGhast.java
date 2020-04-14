package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob {
   private int field_92014_j = 1;

   public EntityGhast(World p_i1735_1_) {
      super(p_i1735_1_);
      this.func_70105_a(4.0F, 4.0F);
      this.field_70178_ae = true;
      this.field_70728_aV = 5;
      this.field_70765_h = new EntityGhast.GhastMoveHelper(this);
      this.field_70714_bg.func_75776_a(5, new EntityGhast.AIRandomFly(this));
      this.field_70714_bg.func_75776_a(7, new EntityGhast.AILookAround(this));
      this.field_70714_bg.func_75776_a(7, new EntityGhast.AIFireballAttack(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIFindEntityNearestPlayer(this));
   }

   public boolean func_110182_bF() {
      return this.field_70180_af.func_75683_a(16) != 0;
   }

   public void func_175454_a(boolean p_175454_1_) {
      this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(p_175454_1_ ? 1 : 0)));
   }

   public int func_175453_cd() {
      return this.field_92014_j;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
         this.func_70106_y();
      }

   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else if ("fireball".equals(p_70097_1_.func_76355_l()) && p_70097_1_.func_76346_g() instanceof EntityPlayer) {
         super.func_70097_a(p_70097_1_, 1000.0F);
         ((EntityPlayer)p_70097_1_.func_76346_g()).func_71029_a(AchievementList.field_76028_y);
         return true;
      } else {
         return super.func_70097_a(p_70097_1_, p_70097_2_);
      }
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, (byte)0);
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(100.0D);
   }

   protected String func_70639_aQ() {
      return "mob.ghast.moan";
   }

   protected String func_70621_aR() {
      return "mob.ghast.scream";
   }

   protected String func_70673_aS() {
      return "mob.ghast.death";
   }

   protected Item func_146068_u() {
      return Items.field_151016_H;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int lvt_3_1_ = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + p_70628_2_);

      int lvt_4_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_1_; ++lvt_4_2_) {
         this.func_145779_a(Items.field_151073_bk, 1);
      }

      lvt_3_1_ = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_1_; ++lvt_4_2_) {
         this.func_145779_a(Items.field_151016_H, 1);
      }

   }

   protected float func_70599_aP() {
      return 10.0F;
   }

   public boolean func_70601_bi() {
      return this.field_70146_Z.nextInt(20) == 0 && super.func_70601_bi() && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
   }

   public int func_70641_bl() {
      return 1;
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("ExplosionPower", this.field_92014_j);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if (p_70037_1_.func_150297_b("ExplosionPower", 99)) {
         this.field_92014_j = p_70037_1_.func_74762_e("ExplosionPower");
      }

   }

   public float func_70047_e() {
      return 2.6F;
   }

   static class AIFireballAttack extends EntityAIBase {
      private EntityGhast field_179470_b;
      public int field_179471_a;

      public AIFireballAttack(EntityGhast p_i45837_1_) {
         this.field_179470_b = p_i45837_1_;
      }

      public boolean func_75250_a() {
         return this.field_179470_b.func_70638_az() != null;
      }

      public void func_75249_e() {
         this.field_179471_a = 0;
      }

      public void func_75251_c() {
         this.field_179470_b.func_175454_a(false);
      }

      public void func_75246_d() {
         EntityLivingBase lvt_1_1_ = this.field_179470_b.func_70638_az();
         double lvt_2_1_ = 64.0D;
         if (lvt_1_1_.func_70068_e(this.field_179470_b) < lvt_2_1_ * lvt_2_1_ && this.field_179470_b.func_70685_l(lvt_1_1_)) {
            World lvt_4_1_ = this.field_179470_b.field_70170_p;
            ++this.field_179471_a;
            if (this.field_179471_a == 10) {
               lvt_4_1_.func_180498_a((EntityPlayer)null, 1007, new BlockPos(this.field_179470_b), 0);
            }

            if (this.field_179471_a == 20) {
               double lvt_5_1_ = 4.0D;
               Vec3 lvt_7_1_ = this.field_179470_b.func_70676_i(1.0F);
               double lvt_8_1_ = lvt_1_1_.field_70165_t - (this.field_179470_b.field_70165_t + lvt_7_1_.field_72450_a * lvt_5_1_);
               double lvt_10_1_ = lvt_1_1_.func_174813_aQ().field_72338_b + (double)(lvt_1_1_.field_70131_O / 2.0F) - (0.5D + this.field_179470_b.field_70163_u + (double)(this.field_179470_b.field_70131_O / 2.0F));
               double lvt_12_1_ = lvt_1_1_.field_70161_v - (this.field_179470_b.field_70161_v + lvt_7_1_.field_72449_c * lvt_5_1_);
               lvt_4_1_.func_180498_a((EntityPlayer)null, 1008, new BlockPos(this.field_179470_b), 0);
               EntityLargeFireball lvt_14_1_ = new EntityLargeFireball(lvt_4_1_, this.field_179470_b, lvt_8_1_, lvt_10_1_, lvt_12_1_);
               lvt_14_1_.field_92057_e = this.field_179470_b.func_175453_cd();
               lvt_14_1_.field_70165_t = this.field_179470_b.field_70165_t + lvt_7_1_.field_72450_a * lvt_5_1_;
               lvt_14_1_.field_70163_u = this.field_179470_b.field_70163_u + (double)(this.field_179470_b.field_70131_O / 2.0F) + 0.5D;
               lvt_14_1_.field_70161_v = this.field_179470_b.field_70161_v + lvt_7_1_.field_72449_c * lvt_5_1_;
               lvt_4_1_.func_72838_d(lvt_14_1_);
               this.field_179471_a = -40;
            }
         } else if (this.field_179471_a > 0) {
            --this.field_179471_a;
         }

         this.field_179470_b.func_175454_a(this.field_179471_a > 10);
      }
   }

   static class AILookAround extends EntityAIBase {
      private EntityGhast field_179472_a;

      public AILookAround(EntityGhast p_i45839_1_) {
         this.field_179472_a = p_i45839_1_;
         this.func_75248_a(2);
      }

      public boolean func_75250_a() {
         return true;
      }

      public void func_75246_d() {
         if (this.field_179472_a.func_70638_az() == null) {
            this.field_179472_a.field_70761_aq = this.field_179472_a.field_70177_z = -((float)MathHelper.func_181159_b(this.field_179472_a.field_70159_w, this.field_179472_a.field_70179_y)) * 180.0F / 3.1415927F;
         } else {
            EntityLivingBase lvt_1_1_ = this.field_179472_a.func_70638_az();
            double lvt_2_1_ = 64.0D;
            if (lvt_1_1_.func_70068_e(this.field_179472_a) < lvt_2_1_ * lvt_2_1_) {
               double lvt_4_1_ = lvt_1_1_.field_70165_t - this.field_179472_a.field_70165_t;
               double lvt_6_1_ = lvt_1_1_.field_70161_v - this.field_179472_a.field_70161_v;
               this.field_179472_a.field_70761_aq = this.field_179472_a.field_70177_z = -((float)MathHelper.func_181159_b(lvt_4_1_, lvt_6_1_)) * 180.0F / 3.1415927F;
            }
         }

      }
   }

   static class AIRandomFly extends EntityAIBase {
      private EntityGhast field_179454_a;

      public AIRandomFly(EntityGhast p_i45836_1_) {
         this.field_179454_a = p_i45836_1_;
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         EntityMoveHelper lvt_1_1_ = this.field_179454_a.func_70605_aq();
         if (!lvt_1_1_.func_75640_a()) {
            return true;
         } else {
            double lvt_2_1_ = lvt_1_1_.func_179917_d() - this.field_179454_a.field_70165_t;
            double lvt_4_1_ = lvt_1_1_.func_179919_e() - this.field_179454_a.field_70163_u;
            double lvt_6_1_ = lvt_1_1_.func_179918_f() - this.field_179454_a.field_70161_v;
            double lvt_8_1_ = lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_;
            return lvt_8_1_ < 1.0D || lvt_8_1_ > 3600.0D;
         }
      }

      public boolean func_75253_b() {
         return false;
      }

      public void func_75249_e() {
         Random lvt_1_1_ = this.field_179454_a.func_70681_au();
         double lvt_2_1_ = this.field_179454_a.field_70165_t + (double)((lvt_1_1_.nextFloat() * 2.0F - 1.0F) * 16.0F);
         double lvt_4_1_ = this.field_179454_a.field_70163_u + (double)((lvt_1_1_.nextFloat() * 2.0F - 1.0F) * 16.0F);
         double lvt_6_1_ = this.field_179454_a.field_70161_v + (double)((lvt_1_1_.nextFloat() * 2.0F - 1.0F) * 16.0F);
         this.field_179454_a.func_70605_aq().func_75642_a(lvt_2_1_, lvt_4_1_, lvt_6_1_, 1.0D);
      }
   }

   static class GhastMoveHelper extends EntityMoveHelper {
      private EntityGhast field_179927_g;
      private int field_179928_h;

      public GhastMoveHelper(EntityGhast p_i45838_1_) {
         super(p_i45838_1_);
         this.field_179927_g = p_i45838_1_;
      }

      public void func_75641_c() {
         if (this.field_75643_f) {
            double lvt_1_1_ = this.field_75646_b - this.field_179927_g.field_70165_t;
            double lvt_3_1_ = this.field_75647_c - this.field_179927_g.field_70163_u;
            double lvt_5_1_ = this.field_75644_d - this.field_179927_g.field_70161_v;
            double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
            if (this.field_179928_h-- <= 0) {
               this.field_179928_h += this.field_179927_g.func_70681_au().nextInt(5) + 2;
               lvt_7_1_ = (double)MathHelper.func_76133_a(lvt_7_1_);
               if (this.func_179926_b(this.field_75646_b, this.field_75647_c, this.field_75644_d, lvt_7_1_)) {
                  EntityGhast var10000 = this.field_179927_g;
                  var10000.field_70159_w += lvt_1_1_ / lvt_7_1_ * 0.1D;
                  var10000 = this.field_179927_g;
                  var10000.field_70181_x += lvt_3_1_ / lvt_7_1_ * 0.1D;
                  var10000 = this.field_179927_g;
                  var10000.field_70179_y += lvt_5_1_ / lvt_7_1_ * 0.1D;
               } else {
                  this.field_75643_f = false;
               }
            }

         }
      }

      private boolean func_179926_b(double p_179926_1_, double p_179926_3_, double p_179926_5_, double p_179926_7_) {
         double lvt_9_1_ = (p_179926_1_ - this.field_179927_g.field_70165_t) / p_179926_7_;
         double lvt_11_1_ = (p_179926_3_ - this.field_179927_g.field_70163_u) / p_179926_7_;
         double lvt_13_1_ = (p_179926_5_ - this.field_179927_g.field_70161_v) / p_179926_7_;
         AxisAlignedBB lvt_15_1_ = this.field_179927_g.func_174813_aQ();

         for(int lvt_16_1_ = 1; (double)lvt_16_1_ < p_179926_7_; ++lvt_16_1_) {
            lvt_15_1_ = lvt_15_1_.func_72317_d(lvt_9_1_, lvt_11_1_, lvt_13_1_);
            if (!this.field_179927_g.field_70170_p.func_72945_a(this.field_179927_g, lvt_15_1_).isEmpty()) {
               return false;
            }
         }

         return true;
      }
   }
}
