package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindEntityNearest extends EntityAIBase {
   private static final Logger field_179444_a = LogManager.getLogger();
   private EntityLiving field_179442_b;
   private final Predicate<EntityLivingBase> field_179443_c;
   private final EntityAINearestAttackableTarget.Sorter field_179440_d;
   private EntityLivingBase field_179441_e;
   private Class<? extends EntityLivingBase> field_179439_f;

   public EntityAIFindEntityNearest(EntityLiving p_i45884_1_, Class<? extends EntityLivingBase> p_i45884_2_) {
      this.field_179442_b = p_i45884_1_;
      this.field_179439_f = p_i45884_2_;
      if (p_i45884_1_ instanceof EntityCreature) {
         field_179444_a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
      }

      this.field_179443_c = new Predicate<EntityLivingBase>() {
         public boolean apply(EntityLivingBase p_apply_1_) {
            double lvt_2_1_ = EntityAIFindEntityNearest.this.func_179438_f();
            if (p_apply_1_.func_70093_af()) {
               lvt_2_1_ *= 0.800000011920929D;
            }

            if (p_apply_1_.func_82150_aj()) {
               return false;
            } else {
               return (double)p_apply_1_.func_70032_d(EntityAIFindEntityNearest.this.field_179442_b) > lvt_2_1_ ? false : EntityAITarget.func_179445_a(EntityAIFindEntityNearest.this.field_179442_b, p_apply_1_, false, true);
            }
         }
      };
      this.field_179440_d = new EntityAINearestAttackableTarget.Sorter(p_i45884_1_);
   }

   public boolean func_75250_a() {
      double lvt_1_1_ = this.func_179438_f();
      List<EntityLivingBase> lvt_3_1_ = this.field_179442_b.field_70170_p.func_175647_a(this.field_179439_f, this.field_179442_b.func_174813_aQ().func_72314_b(lvt_1_1_, 4.0D, lvt_1_1_), this.field_179443_c);
      Collections.sort(lvt_3_1_, this.field_179440_d);
      if (lvt_3_1_.isEmpty()) {
         return false;
      } else {
         this.field_179441_e = (EntityLivingBase)lvt_3_1_.get(0);
         return true;
      }
   }

   public boolean func_75253_b() {
      EntityLivingBase lvt_1_1_ = this.field_179442_b.func_70638_az();
      if (lvt_1_1_ == null) {
         return false;
      } else if (!lvt_1_1_.func_70089_S()) {
         return false;
      } else {
         double lvt_2_1_ = this.func_179438_f();
         if (this.field_179442_b.func_70068_e(lvt_1_1_) > lvt_2_1_ * lvt_2_1_) {
            return false;
         } else {
            return !(lvt_1_1_ instanceof EntityPlayerMP) || !((EntityPlayerMP)lvt_1_1_).field_71134_c.func_73083_d();
         }
      }
   }

   public void func_75249_e() {
      this.field_179442_b.func_70624_b(this.field_179441_e);
      super.func_75249_e();
   }

   public void func_75251_c() {
      this.field_179442_b.func_70624_b((EntityLivingBase)null);
      super.func_75249_e();
   }

   protected double func_179438_f() {
      IAttributeInstance lvt_1_1_ = this.field_179442_b.func_110148_a(SharedMonsterAttributes.field_111265_b);
      return lvt_1_1_ == null ? 16.0D : lvt_1_1_.func_111126_e();
   }
}
