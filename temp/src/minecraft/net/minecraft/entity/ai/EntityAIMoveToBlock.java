package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class EntityAIMoveToBlock extends EntityAIBase {
   private final EntityCreature field_179495_c;
   private final double field_179492_d;
   protected int field_179496_a;
   private int field_179493_e;
   private int field_179490_f;
   protected BlockPos field_179494_b;
   private boolean field_179491_g;
   private int field_179497_h;

   public EntityAIMoveToBlock(EntityCreature p_i45888_1_, double p_i45888_2_, int p_i45888_4_) {
      this.field_179494_b = BlockPos.field_177992_a;
      this.field_179495_c = p_i45888_1_;
      this.field_179492_d = p_i45888_2_;
      this.field_179497_h = p_i45888_4_;
      this.func_75248_a(5);
   }

   public boolean func_75250_a() {
      if (this.field_179496_a > 0) {
         --this.field_179496_a;
         return false;
      } else {
         this.field_179496_a = 200 + this.field_179495_c.func_70681_au().nextInt(200);
         return this.func_179489_g();
      }
   }

   public boolean func_75253_b() {
      return this.field_179493_e >= -this.field_179490_f && this.field_179493_e <= 1200 && this.func_179488_a(this.field_179495_c.field_70170_p, this.field_179494_b);
   }

   public void func_75249_e() {
      this.field_179495_c.func_70661_as().func_75492_a((double)((float)this.field_179494_b.func_177958_n()) + 0.5D, (double)(this.field_179494_b.func_177956_o() + 1), (double)((float)this.field_179494_b.func_177952_p()) + 0.5D, this.field_179492_d);
      this.field_179493_e = 0;
      this.field_179490_f = this.field_179495_c.func_70681_au().nextInt(this.field_179495_c.func_70681_au().nextInt(1200) + 1200) + 1200;
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      if (this.field_179495_c.func_174831_c(this.field_179494_b.func_177984_a()) > 1.0D) {
         this.field_179491_g = false;
         ++this.field_179493_e;
         if (this.field_179493_e % 40 == 0) {
            this.field_179495_c.func_70661_as().func_75492_a((double)((float)this.field_179494_b.func_177958_n()) + 0.5D, (double)(this.field_179494_b.func_177956_o() + 1), (double)((float)this.field_179494_b.func_177952_p()) + 0.5D, this.field_179492_d);
         }
      } else {
         this.field_179491_g = true;
         --this.field_179493_e;
      }

   }

   protected boolean func_179487_f() {
      return this.field_179491_g;
   }

   private boolean func_179489_g() {
      int lvt_1_1_ = this.field_179497_h;
      int lvt_2_1_ = true;
      BlockPos lvt_3_1_ = new BlockPos(this.field_179495_c);

      for(int lvt_4_1_ = 0; lvt_4_1_ <= 1; lvt_4_1_ = lvt_4_1_ > 0 ? -lvt_4_1_ : 1 - lvt_4_1_) {
         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_1_1_; ++lvt_5_1_) {
            for(int lvt_6_1_ = 0; lvt_6_1_ <= lvt_5_1_; lvt_6_1_ = lvt_6_1_ > 0 ? -lvt_6_1_ : 1 - lvt_6_1_) {
               for(int lvt_7_1_ = lvt_6_1_ < lvt_5_1_ && lvt_6_1_ > -lvt_5_1_ ? lvt_5_1_ : 0; lvt_7_1_ <= lvt_5_1_; lvt_7_1_ = lvt_7_1_ > 0 ? -lvt_7_1_ : 1 - lvt_7_1_) {
                  BlockPos lvt_8_1_ = lvt_3_1_.func_177982_a(lvt_6_1_, lvt_4_1_ - 1, lvt_7_1_);
                  if (this.field_179495_c.func_180485_d(lvt_8_1_) && this.func_179488_a(this.field_179495_c.field_70170_p, lvt_8_1_)) {
                     this.field_179494_b = lvt_8_1_;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   protected abstract boolean func_179488_a(World var1, BlockPos var2);
}
