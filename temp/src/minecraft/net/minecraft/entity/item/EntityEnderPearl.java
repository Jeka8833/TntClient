package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {
   private EntityLivingBase field_181555_c;

   public EntityEnderPearl(World p_i46455_1_) {
      super(p_i46455_1_);
   }

   public EntityEnderPearl(World p_i1783_1_, EntityLivingBase p_i1783_2_) {
      super(p_i1783_1_, p_i1783_2_);
      this.field_181555_c = p_i1783_2_;
   }

   public EntityEnderPearl(World p_i1784_1_, double p_i1784_2_, double p_i1784_4_, double p_i1784_6_) {
      super(p_i1784_1_, p_i1784_2_, p_i1784_4_, p_i1784_6_);
   }

   protected void func_70184_a(MovingObjectPosition p_70184_1_) {
      EntityLivingBase lvt_2_1_ = this.func_85052_h();
      if (p_70184_1_.field_72308_g != null) {
         if (p_70184_1_.field_72308_g == this.field_181555_c) {
            return;
         }

         p_70184_1_.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, lvt_2_1_), 0.0F);
      }

      for(int lvt_3_1_ = 0; lvt_3_1_ < 32; ++lvt_3_1_) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t, this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0D, this.field_70161_v, this.field_70146_Z.nextGaussian(), 0.0D, this.field_70146_Z.nextGaussian());
      }

      if (!this.field_70170_p.field_72995_K) {
         if (lvt_2_1_ instanceof EntityPlayerMP) {
            EntityPlayerMP lvt_3_2_ = (EntityPlayerMP)lvt_2_1_;
            if (lvt_3_2_.field_71135_a.func_147362_b().func_150724_d() && lvt_3_2_.field_70170_p == this.field_70170_p && !lvt_3_2_.func_70608_bn()) {
               if (this.field_70146_Z.nextFloat() < 0.05F && this.field_70170_p.func_82736_K().func_82766_b("doMobSpawning")) {
                  EntityEndermite lvt_4_1_ = new EntityEndermite(this.field_70170_p);
                  lvt_4_1_.func_175496_a(true);
                  lvt_4_1_.func_70012_b(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v, lvt_2_1_.field_70177_z, lvt_2_1_.field_70125_A);
                  this.field_70170_p.func_72838_d(lvt_4_1_);
               }

               if (lvt_2_1_.func_70115_ae()) {
                  lvt_2_1_.func_70078_a((Entity)null);
               }

               lvt_2_1_.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
               lvt_2_1_.field_70143_R = 0.0F;
               lvt_2_1_.func_70097_a(DamageSource.field_76379_h, 5.0F);
            }
         } else if (lvt_2_1_ != null) {
            lvt_2_1_.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            lvt_2_1_.field_70143_R = 0.0F;
         }

         this.func_70106_y();
      }

   }

   public void func_70071_h_() {
      EntityLivingBase lvt_1_1_ = this.func_85052_h();
      if (lvt_1_1_ != null && lvt_1_1_ instanceof EntityPlayer && !lvt_1_1_.func_70089_S()) {
         this.func_70106_y();
      } else {
         super.func_70071_h_();
      }

   }
}
