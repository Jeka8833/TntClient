package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;

public abstract class EntityAIDoorInteract extends EntityAIBase {
   protected EntityLiving field_75356_a;
   protected BlockPos field_179507_b;
   protected BlockDoor field_151504_e;
   boolean field_75350_f;
   float field_75351_g;
   float field_75357_h;

   public EntityAIDoorInteract(EntityLiving p_i1621_1_) {
      this.field_179507_b = BlockPos.field_177992_a;
      this.field_75356_a = p_i1621_1_;
      if (!(p_i1621_1_.func_70661_as() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   public boolean func_75250_a() {
      if (!this.field_75356_a.field_70123_F) {
         return false;
      } else {
         PathNavigateGround lvt_1_1_ = (PathNavigateGround)this.field_75356_a.func_70661_as();
         PathEntity lvt_2_1_ = lvt_1_1_.func_75505_d();
         if (lvt_2_1_ != null && !lvt_2_1_.func_75879_b() && lvt_1_1_.func_179686_g()) {
            for(int lvt_3_1_ = 0; lvt_3_1_ < Math.min(lvt_2_1_.func_75873_e() + 2, lvt_2_1_.func_75874_d()); ++lvt_3_1_) {
               PathPoint lvt_4_1_ = lvt_2_1_.func_75877_a(lvt_3_1_);
               this.field_179507_b = new BlockPos(lvt_4_1_.field_75839_a, lvt_4_1_.field_75837_b + 1, lvt_4_1_.field_75838_c);
               if (this.field_75356_a.func_70092_e((double)this.field_179507_b.func_177958_n(), this.field_75356_a.field_70163_u, (double)this.field_179507_b.func_177952_p()) <= 2.25D) {
                  this.field_151504_e = this.func_179506_a(this.field_179507_b);
                  if (this.field_151504_e != null) {
                     return true;
                  }
               }
            }

            this.field_179507_b = (new BlockPos(this.field_75356_a)).func_177984_a();
            this.field_151504_e = this.func_179506_a(this.field_179507_b);
            return this.field_151504_e != null;
         } else {
            return false;
         }
      }
   }

   public boolean func_75253_b() {
      return !this.field_75350_f;
   }

   public void func_75249_e() {
      this.field_75350_f = false;
      this.field_75351_g = (float)((double)((float)this.field_179507_b.func_177958_n() + 0.5F) - this.field_75356_a.field_70165_t);
      this.field_75357_h = (float)((double)((float)this.field_179507_b.func_177952_p() + 0.5F) - this.field_75356_a.field_70161_v);
   }

   public void func_75246_d() {
      float lvt_1_1_ = (float)((double)((float)this.field_179507_b.func_177958_n() + 0.5F) - this.field_75356_a.field_70165_t);
      float lvt_2_1_ = (float)((double)((float)this.field_179507_b.func_177952_p() + 0.5F) - this.field_75356_a.field_70161_v);
      float lvt_3_1_ = this.field_75351_g * lvt_1_1_ + this.field_75357_h * lvt_2_1_;
      if (lvt_3_1_ < 0.0F) {
         this.field_75350_f = true;
      }

   }

   private BlockDoor func_179506_a(BlockPos p_179506_1_) {
      Block lvt_2_1_ = this.field_75356_a.field_70170_p.func_180495_p(p_179506_1_).func_177230_c();
      return lvt_2_1_ instanceof BlockDoor && lvt_2_1_.func_149688_o() == Material.field_151575_d ? (BlockDoor)lvt_2_1_ : null;
   }
}
