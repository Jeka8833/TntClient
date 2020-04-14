package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityParticleEmitter extends EntityFX {
   private Entity field_174851_a;
   private int field_174852_ax;
   private int field_174850_ay;
   private EnumParticleTypes field_174849_az;

   public EntityParticleEmitter(World p_i46279_1_, Entity p_i46279_2_, EnumParticleTypes p_i46279_3_) {
      super(p_i46279_1_, p_i46279_2_.field_70165_t, p_i46279_2_.func_174813_aQ().field_72338_b + (double)(p_i46279_2_.field_70131_O / 2.0F), p_i46279_2_.field_70161_v, p_i46279_2_.field_70159_w, p_i46279_2_.field_70181_x, p_i46279_2_.field_70179_y);
      this.field_174851_a = p_i46279_2_;
      this.field_174850_ay = 3;
      this.field_174849_az = p_i46279_3_;
      this.func_70071_h_();
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
   }

   public void func_70071_h_() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < 16; ++lvt_1_1_) {
         double lvt_2_1_ = (double)(this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
         double lvt_4_1_ = (double)(this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
         double lvt_6_1_ = (double)(this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
         if (lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_ <= 1.0D) {
            double lvt_8_1_ = this.field_174851_a.field_70165_t + lvt_2_1_ * (double)this.field_174851_a.field_70130_N / 4.0D;
            double lvt_10_1_ = this.field_174851_a.func_174813_aQ().field_72338_b + (double)(this.field_174851_a.field_70131_O / 2.0F) + lvt_4_1_ * (double)this.field_174851_a.field_70131_O / 4.0D;
            double lvt_12_1_ = this.field_174851_a.field_70161_v + lvt_6_1_ * (double)this.field_174851_a.field_70130_N / 4.0D;
            this.field_70170_p.func_175682_a(this.field_174849_az, false, lvt_8_1_, lvt_10_1_, lvt_12_1_, lvt_2_1_, lvt_4_1_ + 0.2D, lvt_6_1_);
         }
      }

      ++this.field_174852_ax;
      if (this.field_174852_ax >= this.field_174850_ay) {
         this.func_70106_y();
      }

   }

   public int func_70537_b() {
      return 3;
   }
}
