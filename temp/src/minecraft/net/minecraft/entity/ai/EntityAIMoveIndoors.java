package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveIndoors extends EntityAIBase {
   private EntityCreature field_75424_a;
   private VillageDoorInfo field_75422_b;
   private int field_75423_c = -1;
   private int field_75421_d = -1;

   public EntityAIMoveIndoors(EntityCreature p_i1637_1_) {
      this.field_75424_a = p_i1637_1_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      BlockPos lvt_1_1_ = new BlockPos(this.field_75424_a);
      if ((!this.field_75424_a.field_70170_p.func_72935_r() || this.field_75424_a.field_70170_p.func_72896_J() && !this.field_75424_a.field_70170_p.func_180494_b(lvt_1_1_).func_76738_d()) && !this.field_75424_a.field_70170_p.field_73011_w.func_177495_o()) {
         if (this.field_75424_a.func_70681_au().nextInt(50) != 0) {
            return false;
         } else if (this.field_75423_c != -1 && this.field_75424_a.func_70092_e((double)this.field_75423_c, this.field_75424_a.field_70163_u, (double)this.field_75421_d) < 4.0D) {
            return false;
         } else {
            Village lvt_2_1_ = this.field_75424_a.field_70170_p.func_175714_ae().func_176056_a(lvt_1_1_, 14);
            if (lvt_2_1_ == null) {
               return false;
            } else {
               this.field_75422_b = lvt_2_1_.func_179863_c(lvt_1_1_);
               return this.field_75422_b != null;
            }
         }
      } else {
         return false;
      }
   }

   public boolean func_75253_b() {
      return !this.field_75424_a.func_70661_as().func_75500_f();
   }

   public void func_75249_e() {
      this.field_75423_c = -1;
      BlockPos lvt_1_1_ = this.field_75422_b.func_179856_e();
      int lvt_2_1_ = lvt_1_1_.func_177958_n();
      int lvt_3_1_ = lvt_1_1_.func_177956_o();
      int lvt_4_1_ = lvt_1_1_.func_177952_p();
      if (this.field_75424_a.func_174818_b(lvt_1_1_) > 256.0D) {
         Vec3 lvt_5_1_ = RandomPositionGenerator.func_75464_a(this.field_75424_a, 14, 3, new Vec3((double)lvt_2_1_ + 0.5D, (double)lvt_3_1_, (double)lvt_4_1_ + 0.5D));
         if (lvt_5_1_ != null) {
            this.field_75424_a.func_70661_as().func_75492_a(lvt_5_1_.field_72450_a, lvt_5_1_.field_72448_b, lvt_5_1_.field_72449_c, 1.0D);
         }
      } else {
         this.field_75424_a.func_70661_as().func_75492_a((double)lvt_2_1_ + 0.5D, (double)lvt_3_1_, (double)lvt_4_1_ + 0.5D, 1.0D);
      }

   }

   public void func_75251_c() {
      this.field_75423_c = this.field_75422_b.func_179856_e().func_177958_n();
      this.field_75421_d = this.field_75422_b.func_179856_e().func_177952_p();
      this.field_75422_b = null;
   }
}
