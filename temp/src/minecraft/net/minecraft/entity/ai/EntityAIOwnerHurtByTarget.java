package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtByTarget extends EntityAITarget {
   EntityTameable field_75316_a;
   EntityLivingBase field_75315_b;
   private int field_142051_e;

   public EntityAIOwnerHurtByTarget(EntityTameable p_i1667_1_) {
      super(p_i1667_1_, false);
      this.field_75316_a = p_i1667_1_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      if (!this.field_75316_a.func_70909_n()) {
         return false;
      } else {
         EntityLivingBase lvt_1_1_ = this.field_75316_a.func_70902_q();
         if (lvt_1_1_ == null) {
            return false;
         } else {
            this.field_75315_b = lvt_1_1_.func_70643_av();
            int lvt_2_1_ = lvt_1_1_.func_142015_aE();
            return lvt_2_1_ != this.field_142051_e && this.func_75296_a(this.field_75315_b, false) && this.field_75316_a.func_142018_a(this.field_75315_b, lvt_1_1_);
         }
      }
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75315_b);
      EntityLivingBase lvt_1_1_ = this.field_75316_a.func_70902_q();
      if (lvt_1_1_ != null) {
         this.field_142051_e = lvt_1_1_.func_142015_aE();
      }

      super.func_75249_e();
   }
}
