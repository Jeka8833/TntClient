package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveThroughVillage extends EntityAIBase {
   private EntityCreature field_75420_a;
   private double field_75418_b;
   private PathEntity field_75419_c;
   private VillageDoorInfo field_75416_d;
   private boolean field_75417_e;
   private List<VillageDoorInfo> field_75415_f = Lists.newArrayList();

   public EntityAIMoveThroughVillage(EntityCreature p_i1638_1_, double p_i1638_2_, boolean p_i1638_4_) {
      this.field_75420_a = p_i1638_1_;
      this.field_75418_b = p_i1638_2_;
      this.field_75417_e = p_i1638_4_;
      this.func_75248_a(1);
      if (!(p_i1638_1_.func_70661_as() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
      }
   }

   public boolean func_75250_a() {
      this.func_75414_f();
      if (this.field_75417_e && this.field_75420_a.field_70170_p.func_72935_r()) {
         return false;
      } else {
         Village lvt_1_1_ = this.field_75420_a.field_70170_p.func_175714_ae().func_176056_a(new BlockPos(this.field_75420_a), 0);
         if (lvt_1_1_ == null) {
            return false;
         } else {
            this.field_75416_d = this.func_75412_a(lvt_1_1_);
            if (this.field_75416_d == null) {
               return false;
            } else {
               PathNavigateGround lvt_2_1_ = (PathNavigateGround)this.field_75420_a.func_70661_as();
               boolean lvt_3_1_ = lvt_2_1_.func_179686_g();
               lvt_2_1_.func_179688_b(false);
               this.field_75419_c = lvt_2_1_.func_179680_a(this.field_75416_d.func_179852_d());
               lvt_2_1_.func_179688_b(lvt_3_1_);
               if (this.field_75419_c != null) {
                  return true;
               } else {
                  Vec3 lvt_4_1_ = RandomPositionGenerator.func_75464_a(this.field_75420_a, 10, 7, new Vec3((double)this.field_75416_d.func_179852_d().func_177958_n(), (double)this.field_75416_d.func_179852_d().func_177956_o(), (double)this.field_75416_d.func_179852_d().func_177952_p()));
                  if (lvt_4_1_ == null) {
                     return false;
                  } else {
                     lvt_2_1_.func_179688_b(false);
                     this.field_75419_c = this.field_75420_a.func_70661_as().func_75488_a(lvt_4_1_.field_72450_a, lvt_4_1_.field_72448_b, lvt_4_1_.field_72449_c);
                     lvt_2_1_.func_179688_b(lvt_3_1_);
                     return this.field_75419_c != null;
                  }
               }
            }
         }
      }
   }

   public boolean func_75253_b() {
      if (this.field_75420_a.func_70661_as().func_75500_f()) {
         return false;
      } else {
         float lvt_1_1_ = this.field_75420_a.field_70130_N + 4.0F;
         return this.field_75420_a.func_174818_b(this.field_75416_d.func_179852_d()) > (double)(lvt_1_1_ * lvt_1_1_);
      }
   }

   public void func_75249_e() {
      this.field_75420_a.func_70661_as().func_75484_a(this.field_75419_c, this.field_75418_b);
   }

   public void func_75251_c() {
      if (this.field_75420_a.func_70661_as().func_75500_f() || this.field_75420_a.func_174818_b(this.field_75416_d.func_179852_d()) < 16.0D) {
         this.field_75415_f.add(this.field_75416_d);
      }

   }

   private VillageDoorInfo func_75412_a(Village p_75412_1_) {
      VillageDoorInfo lvt_2_1_ = null;
      int lvt_3_1_ = Integer.MAX_VALUE;
      List<VillageDoorInfo> lvt_4_1_ = p_75412_1_.func_75558_f();
      Iterator lvt_5_1_ = lvt_4_1_.iterator();

      while(lvt_5_1_.hasNext()) {
         VillageDoorInfo lvt_6_1_ = (VillageDoorInfo)lvt_5_1_.next();
         int lvt_7_1_ = lvt_6_1_.func_75474_b(MathHelper.func_76128_c(this.field_75420_a.field_70165_t), MathHelper.func_76128_c(this.field_75420_a.field_70163_u), MathHelper.func_76128_c(this.field_75420_a.field_70161_v));
         if (lvt_7_1_ < lvt_3_1_ && !this.func_75413_a(lvt_6_1_)) {
            lvt_2_1_ = lvt_6_1_;
            lvt_3_1_ = lvt_7_1_;
         }
      }

      return lvt_2_1_;
   }

   private boolean func_75413_a(VillageDoorInfo p_75413_1_) {
      Iterator lvt_2_1_ = this.field_75415_f.iterator();

      VillageDoorInfo lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            return false;
         }

         lvt_3_1_ = (VillageDoorInfo)lvt_2_1_.next();
      } while(!p_75413_1_.func_179852_d().equals(lvt_3_1_.func_179852_d()));

      return true;
   }

   private void func_75414_f() {
      if (this.field_75415_f.size() > 15) {
         this.field_75415_f.remove(0);
      }

   }
}
