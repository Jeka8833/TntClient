package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
   EntityTameable field_75314_a;
   EntityLivingBase field_75313_b;
   private int field_142050_e;

   public EntityAIOwnerHurtTarget(EntityTameable p_i1668_1_) {
      super(p_i1668_1_, false);
      this.field_75314_a = p_i1668_1_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      if (!this.field_75314_a.func_70909_n()) {
         return false;
      } else {
         EntityLivingBase lvt_1_1_ = this.field_75314_a.func_70902_q();
         if (lvt_1_1_ == null) {
            return false;
         } else {
            this.field_75313_b = lvt_1_1_.func_110144_aD();
            int lvt_2_1_ = lvt_1_1_.func_142013_aG();
            return lvt_2_1_ != this.field_142050_e && this.func_75296_a(this.field_75313_b, false) && this.field_75314_a.func_142018_a(this.field_75313_b, lvt_1_1_);
         }
      }
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75313_b);
      EntityLivingBase lvt_1_1_ = this.field_75314_a.func_70902_q();
      if (lvt_1_1_ != null) {
         this.field_142050_e = lvt_1_1_.func_142013_aG();
      }

      super.func_75249_e();
   }
}
