package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public abstract class MobSpawnerBaseLogic {
   private int field_98286_b = 20;
   private String field_98288_a = "Pig";
   private final List<MobSpawnerBaseLogic.WeightedRandomMinecart> field_98285_e = Lists.newArrayList();
   private MobSpawnerBaseLogic.WeightedRandomMinecart field_98282_f;
   private double field_98287_c;
   private double field_98284_d;
   private int field_98283_g = 200;
   private int field_98293_h = 800;
   private int field_98294_i = 4;
   private Entity field_98291_j;
   private int field_98292_k = 6;
   private int field_98289_l = 16;
   private int field_98290_m = 4;

   private String func_98276_e() {
      if (this.func_98269_i() == null) {
         if (this.field_98288_a != null && this.field_98288_a.equals("Minecart")) {
            this.field_98288_a = "MinecartRideable";
         }

         return this.field_98288_a;
      } else {
         return this.func_98269_i().field_98223_c;
      }
   }

   public void func_98272_a(String p_98272_1_) {
      this.field_98288_a = p_98272_1_;
   }

   private boolean func_98279_f() {
      BlockPos lvt_1_1_ = this.func_177221_b();
      return this.func_98271_a().func_175636_b((double)lvt_1_1_.func_177958_n() + 0.5D, (double)lvt_1_1_.func_177956_o() + 0.5D, (double)lvt_1_1_.func_177952_p() + 0.5D, (double)this.field_98289_l);
   }

   public void func_98278_g() {
      if (this.func_98279_f()) {
         BlockPos lvt_1_1_ = this.func_177221_b();
         double lvt_6_2_;
         if (this.func_98271_a().field_72995_K) {
            double lvt_2_1_ = (double)((float)lvt_1_1_.func_177958_n() + this.func_98271_a().field_73012_v.nextFloat());
            double lvt_4_1_ = (double)((float)lvt_1_1_.func_177956_o() + this.func_98271_a().field_73012_v.nextFloat());
            lvt_6_2_ = (double)((float)lvt_1_1_.func_177952_p() + this.func_98271_a().field_73012_v.nextFloat());
            this.func_98271_a().func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_2_1_, lvt_4_1_, lvt_6_2_, 0.0D, 0.0D, 0.0D);
            this.func_98271_a().func_175688_a(EnumParticleTypes.FLAME, lvt_2_1_, lvt_4_1_, lvt_6_2_, 0.0D, 0.0D, 0.0D);
            if (this.field_98286_b > 0) {
               --this.field_98286_b;
            }

            this.field_98284_d = this.field_98287_c;
            this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.field_98286_b + 200.0F))) % 360.0D;
         } else {
            if (this.field_98286_b == -1) {
               this.func_98273_j();
            }

            if (this.field_98286_b > 0) {
               --this.field_98286_b;
               return;
            }

            boolean lvt_2_2_ = false;
            int lvt_3_1_ = 0;

            while(true) {
               if (lvt_3_1_ >= this.field_98294_i) {
                  if (lvt_2_2_) {
                     this.func_98273_j();
                  }
                  break;
               }

               Entity lvt_4_2_ = EntityList.func_75620_a(this.func_98276_e(), this.func_98271_a());
               if (lvt_4_2_ == null) {
                  return;
               }

               int lvt_5_1_ = this.func_98271_a().func_72872_a(lvt_4_2_.getClass(), (new AxisAlignedBB((double)lvt_1_1_.func_177958_n(), (double)lvt_1_1_.func_177956_o(), (double)lvt_1_1_.func_177952_p(), (double)(lvt_1_1_.func_177958_n() + 1), (double)(lvt_1_1_.func_177956_o() + 1), (double)(lvt_1_1_.func_177952_p() + 1))).func_72314_b((double)this.field_98290_m, (double)this.field_98290_m, (double)this.field_98290_m)).size();
               if (lvt_5_1_ >= this.field_98292_k) {
                  this.func_98273_j();
                  return;
               }

               lvt_6_2_ = (double)lvt_1_1_.func_177958_n() + (this.func_98271_a().field_73012_v.nextDouble() - this.func_98271_a().field_73012_v.nextDouble()) * (double)this.field_98290_m + 0.5D;
               double lvt_8_1_ = (double)(lvt_1_1_.func_177956_o() + this.func_98271_a().field_73012_v.nextInt(3) - 1);
               double lvt_10_1_ = (double)lvt_1_1_.func_177952_p() + (this.func_98271_a().field_73012_v.nextDouble() - this.func_98271_a().field_73012_v.nextDouble()) * (double)this.field_98290_m + 0.5D;
               EntityLiving lvt_12_1_ = lvt_4_2_ instanceof EntityLiving ? (EntityLiving)lvt_4_2_ : null;
               lvt_4_2_.func_70012_b(lvt_6_2_, lvt_8_1_, lvt_10_1_, this.func_98271_a().field_73012_v.nextFloat() * 360.0F, 0.0F);
               if (lvt_12_1_ == null || lvt_12_1_.func_70601_bi() && lvt_12_1_.func_70058_J()) {
                  this.func_180613_a(lvt_4_2_, true);
                  this.func_98271_a().func_175718_b(2004, lvt_1_1_, 0);
                  if (lvt_12_1_ != null) {
                     lvt_12_1_.func_70656_aK();
                  }

                  lvt_2_2_ = true;
               }

               ++lvt_3_1_;
            }
         }

      }
   }

   private Entity func_180613_a(Entity p_180613_1_, boolean p_180613_2_) {
      if (this.func_98269_i() != null) {
         NBTTagCompound lvt_3_1_ = new NBTTagCompound();
         p_180613_1_.func_70039_c(lvt_3_1_);
         Iterator lvt_4_1_ = this.func_98269_i().field_98222_b.func_150296_c().iterator();

         while(lvt_4_1_.hasNext()) {
            String lvt_5_1_ = (String)lvt_4_1_.next();
            NBTBase lvt_6_1_ = this.func_98269_i().field_98222_b.func_74781_a(lvt_5_1_);
            lvt_3_1_.func_74782_a(lvt_5_1_, lvt_6_1_.func_74737_b());
         }

         p_180613_1_.func_70020_e(lvt_3_1_);
         if (p_180613_1_.field_70170_p != null && p_180613_2_) {
            p_180613_1_.field_70170_p.func_72838_d(p_180613_1_);
         }

         NBTTagCompound lvt_5_2_;
         for(Entity lvt_4_2_ = p_180613_1_; lvt_3_1_.func_150297_b("Riding", 10); lvt_3_1_ = lvt_5_2_) {
            lvt_5_2_ = lvt_3_1_.func_74775_l("Riding");
            Entity lvt_6_2_ = EntityList.func_75620_a(lvt_5_2_.func_74779_i("id"), p_180613_1_.field_70170_p);
            if (lvt_6_2_ != null) {
               NBTTagCompound lvt_7_1_ = new NBTTagCompound();
               lvt_6_2_.func_70039_c(lvt_7_1_);
               Iterator lvt_8_1_ = lvt_5_2_.func_150296_c().iterator();

               while(lvt_8_1_.hasNext()) {
                  String lvt_9_1_ = (String)lvt_8_1_.next();
                  NBTBase lvt_10_1_ = lvt_5_2_.func_74781_a(lvt_9_1_);
                  lvt_7_1_.func_74782_a(lvt_9_1_, lvt_10_1_.func_74737_b());
               }

               lvt_6_2_.func_70020_e(lvt_7_1_);
               lvt_6_2_.func_70012_b(lvt_4_2_.field_70165_t, lvt_4_2_.field_70163_u, lvt_4_2_.field_70161_v, lvt_4_2_.field_70177_z, lvt_4_2_.field_70125_A);
               if (p_180613_1_.field_70170_p != null && p_180613_2_) {
                  p_180613_1_.field_70170_p.func_72838_d(lvt_6_2_);
               }

               lvt_4_2_.func_70078_a(lvt_6_2_);
            }

            lvt_4_2_ = lvt_6_2_;
         }
      } else if (p_180613_1_ instanceof EntityLivingBase && p_180613_1_.field_70170_p != null && p_180613_2_) {
         if (p_180613_1_ instanceof EntityLiving) {
            ((EntityLiving)p_180613_1_).func_180482_a(p_180613_1_.field_70170_p.func_175649_E(new BlockPos(p_180613_1_)), (IEntityLivingData)null);
         }

         p_180613_1_.field_70170_p.func_72838_d(p_180613_1_);
      }

      return p_180613_1_;
   }

   private void func_98273_j() {
      if (this.field_98293_h <= this.field_98283_g) {
         this.field_98286_b = this.field_98283_g;
      } else {
         int var10003 = this.field_98293_h - this.field_98283_g;
         this.field_98286_b = this.field_98283_g + this.func_98271_a().field_73012_v.nextInt(var10003);
      }

      if (this.field_98285_e.size() > 0) {
         this.func_98277_a((MobSpawnerBaseLogic.WeightedRandomMinecart)WeightedRandom.func_76271_a(this.func_98271_a().field_73012_v, this.field_98285_e));
      }

      this.func_98267_a(1);
   }

   public void func_98270_a(NBTTagCompound p_98270_1_) {
      this.field_98288_a = p_98270_1_.func_74779_i("EntityId");
      this.field_98286_b = p_98270_1_.func_74765_d("Delay");
      this.field_98285_e.clear();
      if (p_98270_1_.func_150297_b("SpawnPotentials", 9)) {
         NBTTagList lvt_2_1_ = p_98270_1_.func_150295_c("SpawnPotentials", 10);

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
            this.field_98285_e.add(new MobSpawnerBaseLogic.WeightedRandomMinecart(lvt_2_1_.func_150305_b(lvt_3_1_)));
         }
      }

      if (p_98270_1_.func_150297_b("SpawnData", 10)) {
         this.func_98277_a(new MobSpawnerBaseLogic.WeightedRandomMinecart(p_98270_1_.func_74775_l("SpawnData"), this.field_98288_a));
      } else {
         this.func_98277_a((MobSpawnerBaseLogic.WeightedRandomMinecart)null);
      }

      if (p_98270_1_.func_150297_b("MinSpawnDelay", 99)) {
         this.field_98283_g = p_98270_1_.func_74765_d("MinSpawnDelay");
         this.field_98293_h = p_98270_1_.func_74765_d("MaxSpawnDelay");
         this.field_98294_i = p_98270_1_.func_74765_d("SpawnCount");
      }

      if (p_98270_1_.func_150297_b("MaxNearbyEntities", 99)) {
         this.field_98292_k = p_98270_1_.func_74765_d("MaxNearbyEntities");
         this.field_98289_l = p_98270_1_.func_74765_d("RequiredPlayerRange");
      }

      if (p_98270_1_.func_150297_b("SpawnRange", 99)) {
         this.field_98290_m = p_98270_1_.func_74765_d("SpawnRange");
      }

      if (this.func_98271_a() != null) {
         this.field_98291_j = null;
      }

   }

   public void func_98280_b(NBTTagCompound p_98280_1_) {
      String lvt_2_1_ = this.func_98276_e();
      if (!StringUtils.func_151246_b(lvt_2_1_)) {
         p_98280_1_.func_74778_a("EntityId", lvt_2_1_);
         p_98280_1_.func_74777_a("Delay", (short)this.field_98286_b);
         p_98280_1_.func_74777_a("MinSpawnDelay", (short)this.field_98283_g);
         p_98280_1_.func_74777_a("MaxSpawnDelay", (short)this.field_98293_h);
         p_98280_1_.func_74777_a("SpawnCount", (short)this.field_98294_i);
         p_98280_1_.func_74777_a("MaxNearbyEntities", (short)this.field_98292_k);
         p_98280_1_.func_74777_a("RequiredPlayerRange", (short)this.field_98289_l);
         p_98280_1_.func_74777_a("SpawnRange", (short)this.field_98290_m);
         if (this.func_98269_i() != null) {
            p_98280_1_.func_74782_a("SpawnData", this.func_98269_i().field_98222_b.func_74737_b());
         }

         if (this.func_98269_i() != null || this.field_98285_e.size() > 0) {
            NBTTagList lvt_3_1_ = new NBTTagList();
            if (this.field_98285_e.size() > 0) {
               Iterator lvt_4_1_ = this.field_98285_e.iterator();

               while(lvt_4_1_.hasNext()) {
                  MobSpawnerBaseLogic.WeightedRandomMinecart lvt_5_1_ = (MobSpawnerBaseLogic.WeightedRandomMinecart)lvt_4_1_.next();
                  lvt_3_1_.func_74742_a(lvt_5_1_.func_98220_a());
               }
            } else {
               lvt_3_1_.func_74742_a(this.func_98269_i().func_98220_a());
            }

            p_98280_1_.func_74782_a("SpawnPotentials", lvt_3_1_);
         }

      }
   }

   public Entity func_180612_a(World p_180612_1_) {
      if (this.field_98291_j == null) {
         Entity lvt_2_1_ = EntityList.func_75620_a(this.func_98276_e(), p_180612_1_);
         if (lvt_2_1_ != null) {
            lvt_2_1_ = this.func_180613_a(lvt_2_1_, false);
            this.field_98291_j = lvt_2_1_;
         }
      }

      return this.field_98291_j;
   }

   public boolean func_98268_b(int p_98268_1_) {
      if (p_98268_1_ == 1 && this.func_98271_a().field_72995_K) {
         this.field_98286_b = this.field_98283_g;
         return true;
      } else {
         return false;
      }
   }

   private MobSpawnerBaseLogic.WeightedRandomMinecart func_98269_i() {
      return this.field_98282_f;
   }

   public void func_98277_a(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_) {
      this.field_98282_f = p_98277_1_;
   }

   public abstract void func_98267_a(int var1);

   public abstract World func_98271_a();

   public abstract BlockPos func_177221_b();

   public double func_177222_d() {
      return this.field_98287_c;
   }

   public double func_177223_e() {
      return this.field_98284_d;
   }

   public class WeightedRandomMinecart extends WeightedRandom.Item {
      private final NBTTagCompound field_98222_b;
      private final String field_98223_c;

      public WeightedRandomMinecart(NBTTagCompound p_i1945_2_) {
         this(p_i1945_2_.func_74775_l("Properties"), p_i1945_2_.func_74779_i("Type"), p_i1945_2_.func_74762_e("Weight"));
      }

      public WeightedRandomMinecart(NBTTagCompound p_i1946_2_, String p_i1946_3_) {
         this(p_i1946_2_, p_i1946_3_, 1);
      }

      private WeightedRandomMinecart(NBTTagCompound p_i45757_2_, String p_i45757_3_, int p_i45757_4_) {
         super(p_i45757_4_);
         if (p_i45757_3_.equals("Minecart")) {
            if (p_i45757_2_ != null) {
               p_i45757_3_ = EntityMinecart.EnumMinecartType.func_180038_a(p_i45757_2_.func_74762_e("Type")).func_180040_b();
            } else {
               p_i45757_3_ = "MinecartRideable";
            }
         }

         this.field_98222_b = p_i45757_2_;
         this.field_98223_c = p_i45757_3_;
      }

      public NBTTagCompound func_98220_a() {
         NBTTagCompound lvt_1_1_ = new NBTTagCompound();
         lvt_1_1_.func_74782_a("Properties", this.field_98222_b);
         lvt_1_1_.func_74778_a("Type", this.field_98223_c);
         lvt_1_1_.func_74768_a("Weight", this.field_76292_a);
         return lvt_1_1_;
      }
   }
}
