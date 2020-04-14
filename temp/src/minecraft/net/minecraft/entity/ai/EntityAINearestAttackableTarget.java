package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;

public class EntityAINearestAttackableTarget<T extends EntityLivingBase> extends EntityAITarget {
   protected final Class<T> field_75307_b;
   private final int field_75308_c;
   protected final EntityAINearestAttackableTarget.Sorter field_75306_g;
   protected Predicate<? super T> field_82643_g;
   protected EntityLivingBase field_75309_a;

   public EntityAINearestAttackableTarget(EntityCreature p_i45878_1_, Class<T> p_i45878_2_, boolean p_i45878_3_) {
      this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
   }

   public EntityAINearestAttackableTarget(EntityCreature p_i45879_1_, Class<T> p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_) {
      this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
   }

   public EntityAINearestAttackableTarget(EntityCreature p_i45880_1_, Class<T> p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate<? super T> p_i45880_6_) {
      super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
      this.field_75307_b = p_i45880_2_;
      this.field_75308_c = p_i45880_3_;
      this.field_75306_g = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
      this.func_75248_a(1);
      this.field_82643_g = new Predicate<T>() {
         public boolean apply(T p_apply_1_) {
            if (p_i45880_6_ != null && !p_i45880_6_.apply(p_apply_1_)) {
               return false;
            } else {
               if (p_apply_1_ instanceof EntityPlayer) {
                  double lvt_2_1_ = EntityAINearestAttackableTarget.this.func_111175_f();
                  if (p_apply_1_.func_70093_af()) {
                     lvt_2_1_ *= 0.800000011920929D;
                  }

                  if (p_apply_1_.func_82150_aj()) {
                     float lvt_4_1_ = ((EntityPlayer)p_apply_1_).func_82243_bO();
                     if (lvt_4_1_ < 0.1F) {
                        lvt_4_1_ = 0.1F;
                     }

                     lvt_2_1_ *= (double)(0.7F * lvt_4_1_);
                  }

                  if ((double)p_apply_1_.func_70032_d(EntityAINearestAttackableTarget.this.field_75299_d) > lvt_2_1_) {
                     return false;
                  }
               }

               return EntityAINearestAttackableTarget.this.func_75296_a(p_apply_1_, false);
            }
         }
      };
   }

   public boolean func_75250_a() {
      if (this.field_75308_c > 0 && this.field_75299_d.func_70681_au().nextInt(this.field_75308_c) != 0) {
         return false;
      } else {
         double lvt_1_1_ = this.func_111175_f();
         List<T> lvt_3_1_ = this.field_75299_d.field_70170_p.func_175647_a(this.field_75307_b, this.field_75299_d.func_174813_aQ().func_72314_b(lvt_1_1_, 4.0D, lvt_1_1_), Predicates.and(this.field_82643_g, EntitySelectors.field_180132_d));
         Collections.sort(lvt_3_1_, this.field_75306_g);
         if (lvt_3_1_.isEmpty()) {
            return false;
         } else {
            this.field_75309_a = (EntityLivingBase)lvt_3_1_.get(0);
            return true;
         }
      }
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75309_a);
      super.func_75249_e();
   }

   public static class Sorter implements Comparator<Entity> {
      private final Entity field_75459_b;

      public Sorter(Entity p_i1662_1_) {
         this.field_75459_b = p_i1662_1_;
      }

      public int compare(Entity p_compare_1_, Entity p_compare_2_) {
         double lvt_3_1_ = this.field_75459_b.func_70068_e(p_compare_1_);
         double lvt_5_1_ = this.field_75459_b.func_70068_e(p_compare_2_);
         if (lvt_3_1_ < lvt_5_1_) {
            return -1;
         } else {
            return lvt_3_1_ > lvt_5_1_ ? 1 : 0;
         }
      }
   }
}
