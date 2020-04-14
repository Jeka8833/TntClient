package net.minecraft.village;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;

public class VillageSiege {
   private World field_75537_a;
   private boolean field_75535_b;
   private int field_75536_c = -1;
   private int field_75533_d;
   private int field_75534_e;
   private Village field_75531_f;
   private int field_75532_g;
   private int field_75538_h;
   private int field_75539_i;

   public VillageSiege(World p_i1676_1_) {
      this.field_75537_a = p_i1676_1_;
   }

   public void func_75528_a() {
      if (this.field_75537_a.func_72935_r()) {
         this.field_75536_c = 0;
      } else if (this.field_75536_c != 2) {
         if (this.field_75536_c == 0) {
            float lvt_1_1_ = this.field_75537_a.func_72826_c(0.0F);
            if ((double)lvt_1_1_ < 0.5D || (double)lvt_1_1_ > 0.501D) {
               return;
            }

            this.field_75536_c = this.field_75537_a.field_73012_v.nextInt(10) == 0 ? 1 : 2;
            this.field_75535_b = false;
            if (this.field_75536_c == 2) {
               return;
            }
         }

         if (this.field_75536_c != -1) {
            if (!this.field_75535_b) {
               if (!this.func_75529_b()) {
                  return;
               }

               this.field_75535_b = true;
            }

            if (this.field_75534_e > 0) {
               --this.field_75534_e;
            } else {
               this.field_75534_e = 2;
               if (this.field_75533_d > 0) {
                  this.func_75530_c();
                  --this.field_75533_d;
               } else {
                  this.field_75536_c = 2;
               }

            }
         }
      }
   }

   private boolean func_75529_b() {
      List<EntityPlayer> lvt_1_1_ = this.field_75537_a.field_73010_i;
      Iterator lvt_2_1_ = lvt_1_1_.iterator();

      Vec3 lvt_7_2_;
      do {
         do {
            do {
               do {
                  do {
                     EntityPlayer lvt_3_1_;
                     do {
                        if (!lvt_2_1_.hasNext()) {
                           return false;
                        }

                        lvt_3_1_ = (EntityPlayer)lvt_2_1_.next();
                     } while(lvt_3_1_.func_175149_v());

                     this.field_75531_f = this.field_75537_a.func_175714_ae().func_176056_a(new BlockPos(lvt_3_1_), 1);
                  } while(this.field_75531_f == null);
               } while(this.field_75531_f.func_75567_c() < 10);
            } while(this.field_75531_f.func_75561_d() < 20);
         } while(this.field_75531_f.func_75562_e() < 20);

         BlockPos lvt_4_1_ = this.field_75531_f.func_180608_a();
         float lvt_5_1_ = (float)this.field_75531_f.func_75568_b();
         boolean lvt_6_1_ = false;

         for(int lvt_7_1_ = 0; lvt_7_1_ < 10; ++lvt_7_1_) {
            float lvt_8_1_ = this.field_75537_a.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
            this.field_75532_g = lvt_4_1_.func_177958_n() + (int)((double)(MathHelper.func_76134_b(lvt_8_1_) * lvt_5_1_) * 0.9D);
            this.field_75538_h = lvt_4_1_.func_177956_o();
            this.field_75539_i = lvt_4_1_.func_177952_p() + (int)((double)(MathHelper.func_76126_a(lvt_8_1_) * lvt_5_1_) * 0.9D);
            lvt_6_1_ = false;
            Iterator lvt_9_1_ = this.field_75537_a.func_175714_ae().func_75540_b().iterator();

            while(lvt_9_1_.hasNext()) {
               Village lvt_10_1_ = (Village)lvt_9_1_.next();
               if (lvt_10_1_ != this.field_75531_f && lvt_10_1_.func_179866_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i))) {
                  lvt_6_1_ = true;
                  break;
               }
            }

            if (!lvt_6_1_) {
               break;
            }
         }

         if (lvt_6_1_) {
            return false;
         }

         lvt_7_2_ = this.func_179867_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i));
      } while(lvt_7_2_ == null);

      this.field_75534_e = 0;
      this.field_75533_d = 20;
      return true;
   }

   private boolean func_75530_c() {
      Vec3 lvt_1_1_ = this.func_179867_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i));
      if (lvt_1_1_ == null) {
         return false;
      } else {
         EntityZombie lvt_2_2_;
         try {
            lvt_2_2_ = new EntityZombie(this.field_75537_a);
            lvt_2_2_.func_180482_a(this.field_75537_a.func_175649_E(new BlockPos(lvt_2_2_)), (IEntityLivingData)null);
            lvt_2_2_.func_82229_g(false);
         } catch (Exception var4) {
            var4.printStackTrace();
            return false;
         }

         lvt_2_2_.func_70012_b(lvt_1_1_.field_72450_a, lvt_1_1_.field_72448_b, lvt_1_1_.field_72449_c, this.field_75537_a.field_73012_v.nextFloat() * 360.0F, 0.0F);
         this.field_75537_a.func_72838_d(lvt_2_2_);
         BlockPos lvt_3_2_ = this.field_75531_f.func_180608_a();
         lvt_2_2_.func_175449_a(lvt_3_2_, this.field_75531_f.func_75568_b());
         return true;
      }
   }

   private Vec3 func_179867_a(BlockPos p_179867_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
         BlockPos lvt_3_1_ = p_179867_1_.func_177982_a(this.field_75537_a.field_73012_v.nextInt(16) - 8, this.field_75537_a.field_73012_v.nextInt(6) - 3, this.field_75537_a.field_73012_v.nextInt(16) - 8);
         if (this.field_75531_f.func_179866_a(lvt_3_1_) && SpawnerAnimals.func_180267_a(EntityLiving.SpawnPlacementType.ON_GROUND, this.field_75537_a, lvt_3_1_)) {
            return new Vec3((double)lvt_3_1_.func_177958_n(), (double)lvt_3_1_.func_177956_o(), (double)lvt_3_1_.func_177952_p());
         }
      }

      return null;
   }
}
