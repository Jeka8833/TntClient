package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.Vec3;

public class EntityAIPlay extends EntityAIBase {
   private EntityVillager field_75262_a;
   private EntityLivingBase field_75260_b;
   private double field_75261_c;
   private int field_75259_d;

   public EntityAIPlay(EntityVillager p_i1646_1_, double p_i1646_2_) {
      this.field_75262_a = p_i1646_1_;
      this.field_75261_c = p_i1646_2_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      if (this.field_75262_a.func_70874_b() >= 0) {
         return false;
      } else if (this.field_75262_a.func_70681_au().nextInt(400) != 0) {
         return false;
      } else {
         List<EntityVillager> lvt_1_1_ = this.field_75262_a.field_70170_p.func_72872_a(EntityVillager.class, this.field_75262_a.func_174813_aQ().func_72314_b(6.0D, 3.0D, 6.0D));
         double lvt_2_1_ = Double.MAX_VALUE;
         Iterator lvt_4_1_ = lvt_1_1_.iterator();

         while(lvt_4_1_.hasNext()) {
            EntityVillager lvt_5_1_ = (EntityVillager)lvt_4_1_.next();
            if (lvt_5_1_ != this.field_75262_a && !lvt_5_1_.func_70945_p() && lvt_5_1_.func_70874_b() < 0) {
               double lvt_6_1_ = lvt_5_1_.func_70068_e(this.field_75262_a);
               if (lvt_6_1_ <= lvt_2_1_) {
                  lvt_2_1_ = lvt_6_1_;
                  this.field_75260_b = lvt_5_1_;
               }
            }
         }

         if (this.field_75260_b == null) {
            Vec3 lvt_4_2_ = RandomPositionGenerator.func_75463_a(this.field_75262_a, 16, 3);
            if (lvt_4_2_ == null) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean func_75253_b() {
      return this.field_75259_d > 0;
   }

   public void func_75249_e() {
      if (this.field_75260_b != null) {
         this.field_75262_a.func_70939_f(true);
      }

      this.field_75259_d = 1000;
   }

   public void func_75251_c() {
      this.field_75262_a.func_70939_f(false);
      this.field_75260_b = null;
   }

   public void func_75246_d() {
      --this.field_75259_d;
      if (this.field_75260_b != null) {
         if (this.field_75262_a.func_70068_e(this.field_75260_b) > 4.0D) {
            this.field_75262_a.func_70661_as().func_75497_a(this.field_75260_b, this.field_75261_c);
         }
      } else if (this.field_75262_a.func_70661_as().func_75500_f()) {
         Vec3 lvt_1_1_ = RandomPositionGenerator.func_75463_a(this.field_75262_a, 16, 3);
         if (lvt_1_1_ == null) {
            return;
         }

         this.field_75262_a.func_70661_as().func_75492_a(lvt_1_1_.field_72450_a, lvt_1_1_.field_72448_b, lvt_1_1_.field_72449_c, this.field_75261_c);
      }

   }
}
