package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {
   private boolean field_75312_a;
   private int field_142052_b;
   private final Class[] field_179447_c;

   public EntityAIHurtByTarget(EntityCreature p_i45885_1_, boolean p_i45885_2_, Class... p_i45885_3_) {
      super(p_i45885_1_, false);
      this.field_75312_a = p_i45885_2_;
      this.field_179447_c = p_i45885_3_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      int lvt_1_1_ = this.field_75299_d.func_142015_aE();
      return lvt_1_1_ != this.field_142052_b && this.func_75296_a(this.field_75299_d.func_70643_av(), false);
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
      this.field_142052_b = this.field_75299_d.func_142015_aE();
      if (this.field_75312_a) {
         double lvt_1_1_ = this.func_111175_f();
         List<EntityCreature> lvt_3_1_ = this.field_75299_d.field_70170_p.func_72872_a(this.field_75299_d.getClass(), (new AxisAlignedBB(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D)).func_72314_b(lvt_1_1_, 10.0D, lvt_1_1_));
         Iterator lvt_4_1_ = lvt_3_1_.iterator();

         label46:
         while(true) {
            EntityCreature lvt_5_1_;
            do {
               do {
                  do {
                     if (!lvt_4_1_.hasNext()) {
                        break label46;
                     }

                     lvt_5_1_ = (EntityCreature)lvt_4_1_.next();
                  } while(this.field_75299_d == lvt_5_1_);
               } while(lvt_5_1_.func_70638_az() != null);
            } while(lvt_5_1_.func_142014_c(this.field_75299_d.func_70643_av()));

            boolean lvt_6_1_ = false;
            Class[] lvt_7_1_ = this.field_179447_c;
            int lvt_8_1_ = lvt_7_1_.length;

            for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
               Class lvt_10_1_ = lvt_7_1_[lvt_9_1_];
               if (lvt_5_1_.getClass() == lvt_10_1_) {
                  lvt_6_1_ = true;
                  break;
               }
            }

            if (!lvt_6_1_) {
               this.func_179446_a(lvt_5_1_, this.field_75299_d.func_70643_av());
            }
         }
      }

      super.func_75249_e();
   }

   protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
      p_179446_1_.func_70624_b(p_179446_2_);
   }
}
