package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITarget extends EntityAIBase {
   protected final EntityCreature field_75299_d;
   protected boolean field_75297_f;
   private boolean field_75303_a;
   private int field_75301_b;
   private int field_75302_c;
   private int field_75298_g;

   public EntityAITarget(EntityCreature p_i1669_1_, boolean p_i1669_2_) {
      this(p_i1669_1_, p_i1669_2_, false);
   }

   public EntityAITarget(EntityCreature p_i1670_1_, boolean p_i1670_2_, boolean p_i1670_3_) {
      this.field_75299_d = p_i1670_1_;
      this.field_75297_f = p_i1670_2_;
      this.field_75303_a = p_i1670_3_;
   }

   public boolean func_75253_b() {
      EntityLivingBase lvt_1_1_ = this.field_75299_d.func_70638_az();
      if (lvt_1_1_ == null) {
         return false;
      } else if (!lvt_1_1_.func_70089_S()) {
         return false;
      } else {
         Team lvt_2_1_ = this.field_75299_d.func_96124_cp();
         Team lvt_3_1_ = lvt_1_1_.func_96124_cp();
         if (lvt_2_1_ != null && lvt_3_1_ == lvt_2_1_) {
            return false;
         } else {
            double lvt_4_1_ = this.func_111175_f();
            if (this.field_75299_d.func_70068_e(lvt_1_1_) > lvt_4_1_ * lvt_4_1_) {
               return false;
            } else {
               if (this.field_75297_f) {
                  if (this.field_75299_d.func_70635_at().func_75522_a(lvt_1_1_)) {
                     this.field_75298_g = 0;
                  } else if (++this.field_75298_g > 60) {
                     return false;
                  }
               }

               return !(lvt_1_1_ instanceof EntityPlayer) || !((EntityPlayer)lvt_1_1_).field_71075_bZ.field_75102_a;
            }
         }
      }
   }

   protected double func_111175_f() {
      IAttributeInstance lvt_1_1_ = this.field_75299_d.func_110148_a(SharedMonsterAttributes.field_111265_b);
      return lvt_1_1_ == null ? 16.0D : lvt_1_1_.func_111126_e();
   }

   public void func_75249_e() {
      this.field_75301_b = 0;
      this.field_75302_c = 0;
      this.field_75298_g = 0;
   }

   public void func_75251_c() {
      this.field_75299_d.func_70624_b((EntityLivingBase)null);
   }

   public static boolean func_179445_a(EntityLiving p_179445_0_, EntityLivingBase p_179445_1_, boolean p_179445_2_, boolean p_179445_3_) {
      if (p_179445_1_ == null) {
         return false;
      } else if (p_179445_1_ == p_179445_0_) {
         return false;
      } else if (!p_179445_1_.func_70089_S()) {
         return false;
      } else if (!p_179445_0_.func_70686_a(p_179445_1_.getClass())) {
         return false;
      } else {
         Team lvt_4_1_ = p_179445_0_.func_96124_cp();
         Team lvt_5_1_ = p_179445_1_.func_96124_cp();
         if (lvt_4_1_ != null && lvt_5_1_ == lvt_4_1_) {
            return false;
         } else {
            if (p_179445_0_ instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)p_179445_0_).func_152113_b())) {
               if (p_179445_1_ instanceof IEntityOwnable && ((IEntityOwnable)p_179445_0_).func_152113_b().equals(((IEntityOwnable)p_179445_1_).func_152113_b())) {
                  return false;
               }

               if (p_179445_1_ == ((IEntityOwnable)p_179445_0_).func_70902_q()) {
                  return false;
               }
            } else if (p_179445_1_ instanceof EntityPlayer && !p_179445_2_ && ((EntityPlayer)p_179445_1_).field_71075_bZ.field_75102_a) {
               return false;
            }

            return !p_179445_3_ || p_179445_0_.func_70635_at().func_75522_a(p_179445_1_);
         }
      }
   }

   protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_) {
      if (!func_179445_a(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f)) {
         return false;
      } else if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_))) {
         return false;
      } else {
         if (this.field_75303_a) {
            if (--this.field_75302_c <= 0) {
               this.field_75301_b = 0;
            }

            if (this.field_75301_b == 0) {
               this.field_75301_b = this.func_75295_a(p_75296_1_) ? 1 : 2;
            }

            if (this.field_75301_b == 2) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean func_75295_a(EntityLivingBase p_75295_1_) {
      this.field_75302_c = 10 + this.field_75299_d.func_70681_au().nextInt(5);
      PathEntity lvt_2_1_ = this.field_75299_d.func_70661_as().func_75494_a(p_75295_1_);
      if (lvt_2_1_ == null) {
         return false;
      } else {
         PathPoint lvt_3_1_ = lvt_2_1_.func_75870_c();
         if (lvt_3_1_ == null) {
            return false;
         } else {
            int lvt_4_1_ = lvt_3_1_.field_75839_a - MathHelper.func_76128_c(p_75295_1_.field_70165_t);
            int lvt_5_1_ = lvt_3_1_.field_75838_c - MathHelper.func_76128_c(p_75295_1_.field_70161_v);
            return (double)(lvt_4_1_ * lvt_4_1_ + lvt_5_1_ * lvt_5_1_) <= 2.25D;
         }
      }
   }
}
