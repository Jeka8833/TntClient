package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAIFollowParent extends EntityAIBase {
   EntityAnimal field_75348_a;
   EntityAnimal field_75346_b;
   double field_75347_c;
   private int field_75345_d;

   public EntityAIFollowParent(EntityAnimal p_i1626_1_, double p_i1626_2_) {
      this.field_75348_a = p_i1626_1_;
      this.field_75347_c = p_i1626_2_;
   }

   public boolean func_75250_a() {
      if (this.field_75348_a.func_70874_b() >= 0) {
         return false;
      } else {
         List<EntityAnimal> lvt_1_1_ = this.field_75348_a.field_70170_p.func_72872_a(this.field_75348_a.getClass(), this.field_75348_a.func_174813_aQ().func_72314_b(8.0D, 4.0D, 8.0D));
         EntityAnimal lvt_2_1_ = null;
         double lvt_3_1_ = Double.MAX_VALUE;
         Iterator lvt_5_1_ = lvt_1_1_.iterator();

         while(lvt_5_1_.hasNext()) {
            EntityAnimal lvt_6_1_ = (EntityAnimal)lvt_5_1_.next();
            if (lvt_6_1_.func_70874_b() >= 0) {
               double lvt_7_1_ = this.field_75348_a.func_70068_e(lvt_6_1_);
               if (lvt_7_1_ <= lvt_3_1_) {
                  lvt_3_1_ = lvt_7_1_;
                  lvt_2_1_ = lvt_6_1_;
               }
            }
         }

         if (lvt_2_1_ == null) {
            return false;
         } else if (lvt_3_1_ < 9.0D) {
            return false;
         } else {
            this.field_75346_b = lvt_2_1_;
            return true;
         }
      }
   }

   public boolean func_75253_b() {
      if (this.field_75348_a.func_70874_b() >= 0) {
         return false;
      } else if (!this.field_75346_b.func_70089_S()) {
         return false;
      } else {
         double lvt_1_1_ = this.field_75348_a.func_70068_e(this.field_75346_b);
         return lvt_1_1_ >= 9.0D && lvt_1_1_ <= 256.0D;
      }
   }

   public void func_75249_e() {
      this.field_75345_d = 0;
   }

   public void func_75251_c() {
      this.field_75346_b = null;
   }

   public void func_75246_d() {
      if (--this.field_75345_d <= 0) {
         this.field_75345_d = 10;
         this.field_75348_a.func_70661_as().func_75497_a(this.field_75346_b, this.field_75347_c);
      }
   }
}
