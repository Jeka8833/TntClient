package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class EntityAIMoveTowardsRestriction extends EntityAIBase {
   private EntityCreature field_75436_a;
   private double field_75434_b;
   private double field_75435_c;
   private double field_75432_d;
   private double field_75433_e;

   public EntityAIMoveTowardsRestriction(EntityCreature p_i2347_1_, double p_i2347_2_) {
      this.field_75436_a = p_i2347_1_;
      this.field_75433_e = p_i2347_2_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      if (this.field_75436_a.func_110173_bK()) {
         return false;
      } else {
         BlockPos lvt_1_1_ = this.field_75436_a.func_180486_cf();
         Vec3 lvt_2_1_ = RandomPositionGenerator.func_75464_a(this.field_75436_a, 16, 7, new Vec3((double)lvt_1_1_.func_177958_n(), (double)lvt_1_1_.func_177956_o(), (double)lvt_1_1_.func_177952_p()));
         if (lvt_2_1_ == null) {
            return false;
         } else {
            this.field_75434_b = lvt_2_1_.field_72450_a;
            this.field_75435_c = lvt_2_1_.field_72448_b;
            this.field_75432_d = lvt_2_1_.field_72449_c;
            return true;
         }
      }
   }

   public boolean func_75253_b() {
      return !this.field_75436_a.func_70661_as().func_75500_f();
   }

   public void func_75249_e() {
      this.field_75436_a.func_70661_as().func_75492_a(this.field_75434_b, this.field_75435_c, this.field_75432_d, this.field_75433_e);
   }
}
