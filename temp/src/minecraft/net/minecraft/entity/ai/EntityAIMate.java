package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityAIMate extends EntityAIBase {
   private EntityAnimal field_75390_d;
   World field_75394_a;
   private EntityAnimal field_75391_e;
   int field_75392_b;
   double field_75393_c;

   public EntityAIMate(EntityAnimal p_i1619_1_, double p_i1619_2_) {
      this.field_75390_d = p_i1619_1_;
      this.field_75394_a = p_i1619_1_.field_70170_p;
      this.field_75393_c = p_i1619_2_;
      this.func_75248_a(3);
   }

   public boolean func_75250_a() {
      if (!this.field_75390_d.func_70880_s()) {
         return false;
      } else {
         this.field_75391_e = this.func_75389_f();
         return this.field_75391_e != null;
      }
   }

   public boolean func_75253_b() {
      return this.field_75391_e.func_70089_S() && this.field_75391_e.func_70880_s() && this.field_75392_b < 60;
   }

   public void func_75251_c() {
      this.field_75391_e = null;
      this.field_75392_b = 0;
   }

   public void func_75246_d() {
      this.field_75390_d.func_70671_ap().func_75651_a(this.field_75391_e, 10.0F, (float)this.field_75390_d.func_70646_bf());
      this.field_75390_d.func_70661_as().func_75497_a(this.field_75391_e, this.field_75393_c);
      ++this.field_75392_b;
      if (this.field_75392_b >= 60 && this.field_75390_d.func_70068_e(this.field_75391_e) < 9.0D) {
         this.func_75388_i();
      }

   }

   private EntityAnimal func_75389_f() {
      float lvt_1_1_ = 8.0F;
      List<EntityAnimal> lvt_2_1_ = this.field_75394_a.func_72872_a(this.field_75390_d.getClass(), this.field_75390_d.func_174813_aQ().func_72314_b((double)lvt_1_1_, (double)lvt_1_1_, (double)lvt_1_1_));
      double lvt_3_1_ = Double.MAX_VALUE;
      EntityAnimal lvt_5_1_ = null;
      Iterator lvt_6_1_ = lvt_2_1_.iterator();

      while(lvt_6_1_.hasNext()) {
         EntityAnimal lvt_7_1_ = (EntityAnimal)lvt_6_1_.next();
         if (this.field_75390_d.func_70878_b(lvt_7_1_) && this.field_75390_d.func_70068_e(lvt_7_1_) < lvt_3_1_) {
            lvt_5_1_ = lvt_7_1_;
            lvt_3_1_ = this.field_75390_d.func_70068_e(lvt_7_1_);
         }
      }

      return lvt_5_1_;
   }

   private void func_75388_i() {
      EntityAgeable lvt_1_1_ = this.field_75390_d.func_90011_a(this.field_75391_e);
      if (lvt_1_1_ != null) {
         EntityPlayer lvt_2_1_ = this.field_75390_d.func_146083_cb();
         if (lvt_2_1_ == null && this.field_75391_e.func_146083_cb() != null) {
            lvt_2_1_ = this.field_75391_e.func_146083_cb();
         }

         if (lvt_2_1_ != null) {
            lvt_2_1_.func_71029_a(StatList.field_151186_x);
            if (this.field_75390_d instanceof EntityCow) {
               lvt_2_1_.func_71029_a(AchievementList.field_150962_H);
            }
         }

         this.field_75390_d.func_70873_a(6000);
         this.field_75391_e.func_70873_a(6000);
         this.field_75390_d.func_70875_t();
         this.field_75391_e.func_70875_t();
         lvt_1_1_.func_70873_a(-24000);
         lvt_1_1_.func_70012_b(this.field_75390_d.field_70165_t, this.field_75390_d.field_70163_u, this.field_75390_d.field_70161_v, 0.0F, 0.0F);
         this.field_75394_a.func_72838_d(lvt_1_1_);
         Random lvt_3_1_ = this.field_75390_d.func_70681_au();

         for(int lvt_4_1_ = 0; lvt_4_1_ < 7; ++lvt_4_1_) {
            double lvt_5_1_ = lvt_3_1_.nextGaussian() * 0.02D;
            double lvt_7_1_ = lvt_3_1_.nextGaussian() * 0.02D;
            double lvt_9_1_ = lvt_3_1_.nextGaussian() * 0.02D;
            double lvt_11_1_ = lvt_3_1_.nextDouble() * (double)this.field_75390_d.field_70130_N * 2.0D - (double)this.field_75390_d.field_70130_N;
            double lvt_13_1_ = 0.5D + lvt_3_1_.nextDouble() * (double)this.field_75390_d.field_70131_O;
            double lvt_15_1_ = lvt_3_1_.nextDouble() * (double)this.field_75390_d.field_70130_N * 2.0D - (double)this.field_75390_d.field_70130_N;
            this.field_75394_a.func_175688_a(EnumParticleTypes.HEART, this.field_75390_d.field_70165_t + lvt_11_1_, this.field_75390_d.field_70163_u + lvt_13_1_, this.field_75390_d.field_70161_v + lvt_15_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_);
         }

         if (this.field_75394_a.func_82736_K().func_82766_b("doMobLoot")) {
            this.field_75394_a.func_72838_d(new EntityXPOrb(this.field_75394_a, this.field_75390_d.field_70165_t, this.field_75390_d.field_70163_u, this.field_75390_d.field_70161_v, lvt_3_1_.nextInt(7) + 1));
         }

      }
   }
}
