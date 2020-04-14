package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class EntityAIRunAroundLikeCrazy extends EntityAIBase {
   private EntityHorse field_111180_a;
   private double field_111178_b;
   private double field_111179_c;
   private double field_111176_d;
   private double field_111177_e;

   public EntityAIRunAroundLikeCrazy(EntityHorse p_i1653_1_, double p_i1653_2_) {
      this.field_111180_a = p_i1653_1_;
      this.field_111178_b = p_i1653_2_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      if (!this.field_111180_a.func_110248_bS() && this.field_111180_a.field_70153_n != null) {
         Vec3 lvt_1_1_ = RandomPositionGenerator.func_75463_a(this.field_111180_a, 5, 4);
         if (lvt_1_1_ == null) {
            return false;
         } else {
            this.field_111179_c = lvt_1_1_.field_72450_a;
            this.field_111176_d = lvt_1_1_.field_72448_b;
            this.field_111177_e = lvt_1_1_.field_72449_c;
            return true;
         }
      } else {
         return false;
      }
   }

   public void func_75249_e() {
      this.field_111180_a.func_70661_as().func_75492_a(this.field_111179_c, this.field_111176_d, this.field_111177_e, this.field_111178_b);
   }

   public boolean func_75253_b() {
      return !this.field_111180_a.func_70661_as().func_75500_f() && this.field_111180_a.field_70153_n != null;
   }

   public void func_75246_d() {
      if (this.field_111180_a.func_70681_au().nextInt(50) == 0) {
         if (this.field_111180_a.field_70153_n instanceof EntityPlayer) {
            int lvt_1_1_ = this.field_111180_a.func_110252_cg();
            int lvt_2_1_ = this.field_111180_a.func_110218_cm();
            if (lvt_2_1_ > 0 && this.field_111180_a.func_70681_au().nextInt(lvt_2_1_) < lvt_1_1_) {
               this.field_111180_a.func_110263_g((EntityPlayer)this.field_111180_a.field_70153_n);
               this.field_111180_a.field_70170_p.func_72960_a(this.field_111180_a, (byte)7);
               return;
            }

            this.field_111180_a.func_110198_t(5);
         }

         this.field_111180_a.field_70153_n.func_70078_a((Entity)null);
         this.field_111180_a.field_70153_n = null;
         this.field_111180_a.func_110231_cz();
         this.field_111180_a.field_70170_p.func_72960_a(this.field_111180_a, (byte)6);
      }

   }
}
