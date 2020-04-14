package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {
   private boolean field_70279_a;
   private double field_70276_b;
   private int field_70277_c;
   private double field_70274_d;
   private double field_70275_e;
   private double field_70272_f;
   private double field_70273_g;
   private double field_70281_h;
   private double field_70282_i;
   private double field_70280_j;
   private double field_70278_an;

   public EntityBoat(World p_i1704_1_) {
      super(p_i1704_1_);
      this.field_70279_a = true;
      this.field_70276_b = 0.07D;
      this.field_70156_m = true;
      this.func_70105_a(1.5F, 0.6F);
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(17, new Integer(0));
      this.field_70180_af.func_75682_a(18, new Integer(1));
      this.field_70180_af.func_75682_a(19, new Float(0.0F));
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return p_70114_1_.func_174813_aQ();
   }

   public AxisAlignedBB func_70046_E() {
      return this.func_174813_aQ();
   }

   public boolean func_70104_M() {
      return true;
   }

   public EntityBoat(World p_i1705_1_, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_) {
      this(p_i1705_1_);
      this.func_70107_b(p_i1705_2_, p_i1705_4_, p_i1705_6_);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = p_i1705_2_;
      this.field_70167_r = p_i1705_4_;
      this.field_70166_s = p_i1705_6_;
   }

   public double func_70042_X() {
      return -0.3D;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         if (this.field_70153_n != null && this.field_70153_n == p_70097_1_.func_76346_g() && p_70097_1_ instanceof EntityDamageSourceIndirect) {
            return false;
         } else {
            this.func_70269_c(-this.func_70267_i());
            this.func_70265_b(10);
            this.func_70266_a(this.func_70271_g() + p_70097_2_ * 10.0F);
            this.func_70018_K();
            boolean lvt_3_1_ = p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d;
            if (lvt_3_1_ || this.func_70271_g() > 40.0F) {
               if (this.field_70153_n != null) {
                  this.field_70153_n.func_70078_a(this);
               }

               if (!lvt_3_1_ && this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  this.func_145778_a(Items.field_151124_az, 1, 0.0F);
               }

               this.func_70106_y();
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void func_70057_ab() {
      this.func_70269_c(-this.func_70267_i());
      this.func_70265_b(10);
      this.func_70266_a(this.func_70271_g() * 11.0F);
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      if (p_180426_10_ && this.field_70153_n != null) {
         this.field_70169_q = this.field_70165_t = p_180426_1_;
         this.field_70167_r = this.field_70163_u = p_180426_3_;
         this.field_70166_s = this.field_70161_v = p_180426_5_;
         this.field_70177_z = p_180426_7_;
         this.field_70125_A = p_180426_8_;
         this.field_70277_c = 0;
         this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
         this.field_70159_w = this.field_70282_i = 0.0D;
         this.field_70181_x = this.field_70280_j = 0.0D;
         this.field_70179_y = this.field_70278_an = 0.0D;
      } else {
         if (this.field_70279_a) {
            this.field_70277_c = p_180426_9_ + 5;
         } else {
            double lvt_11_1_ = p_180426_1_ - this.field_70165_t;
            double lvt_13_1_ = p_180426_3_ - this.field_70163_u;
            double lvt_15_1_ = p_180426_5_ - this.field_70161_v;
            double lvt_17_1_ = lvt_11_1_ * lvt_11_1_ + lvt_13_1_ * lvt_13_1_ + lvt_15_1_ * lvt_15_1_;
            if (lvt_17_1_ <= 1.0D) {
               return;
            }

            this.field_70277_c = 3;
         }

         this.field_70274_d = p_180426_1_;
         this.field_70275_e = p_180426_3_;
         this.field_70272_f = p_180426_5_;
         this.field_70273_g = (double)p_180426_7_;
         this.field_70281_h = (double)p_180426_8_;
         this.field_70159_w = this.field_70282_i;
         this.field_70181_x = this.field_70280_j;
         this.field_70179_y = this.field_70278_an;
      }
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70282_i = this.field_70159_w = p_70016_1_;
      this.field_70280_j = this.field_70181_x = p_70016_3_;
      this.field_70278_an = this.field_70179_y = p_70016_5_;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.func_70268_h() > 0) {
         this.func_70265_b(this.func_70268_h() - 1);
      }

      if (this.func_70271_g() > 0.0F) {
         this.func_70266_a(this.func_70271_g() - 1.0F);
      }

      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      int lvt_1_1_ = 5;
      double lvt_2_1_ = 0.0D;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_1_1_; ++lvt_4_1_) {
         double lvt_5_1_ = this.func_174813_aQ().field_72338_b + (this.func_174813_aQ().field_72337_e - this.func_174813_aQ().field_72338_b) * (double)(lvt_4_1_ + 0) / (double)lvt_1_1_ - 0.125D;
         double lvt_7_1_ = this.func_174813_aQ().field_72338_b + (this.func_174813_aQ().field_72337_e - this.func_174813_aQ().field_72338_b) * (double)(lvt_4_1_ + 1) / (double)lvt_1_1_ - 0.125D;
         AxisAlignedBB lvt_9_1_ = new AxisAlignedBB(this.func_174813_aQ().field_72340_a, lvt_5_1_, this.func_174813_aQ().field_72339_c, this.func_174813_aQ().field_72336_d, lvt_7_1_, this.func_174813_aQ().field_72334_f);
         if (this.field_70170_p.func_72830_b(lvt_9_1_, Material.field_151586_h)) {
            lvt_2_1_ += 1.0D / (double)lvt_1_1_;
         }
      }

      double lvt_4_2_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      double lvt_6_6_;
      double lvt_8_8_;
      int lvt_10_4_;
      if (lvt_4_2_ > 0.2975D) {
         lvt_6_6_ = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D);
         lvt_8_8_ = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D);

         for(lvt_10_4_ = 0; (double)lvt_10_4_ < 1.0D + lvt_4_2_ * 60.0D; ++lvt_10_4_) {
            double lvt_11_1_ = (double)(this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
            double lvt_13_1_ = (double)(this.field_70146_Z.nextInt(2) * 2 - 1) * 0.7D;
            double lvt_15_1_;
            double lvt_17_1_;
            if (this.field_70146_Z.nextBoolean()) {
               lvt_15_1_ = this.field_70165_t - lvt_6_6_ * lvt_11_1_ * 0.8D + lvt_8_8_ * lvt_13_1_;
               lvt_17_1_ = this.field_70161_v - lvt_8_8_ * lvt_11_1_ * 0.8D - lvt_6_6_ * lvt_13_1_;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, lvt_15_1_, this.field_70163_u - 0.125D, lvt_17_1_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            } else {
               lvt_15_1_ = this.field_70165_t + lvt_6_6_ + lvt_8_8_ * lvt_11_1_ * 0.7D;
               lvt_17_1_ = this.field_70161_v + lvt_8_8_ - lvt_6_6_ * lvt_11_1_ * 0.7D;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, lvt_15_1_, this.field_70163_u - 0.125D, lvt_17_1_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }
         }
      }

      double lvt_10_5_;
      double lvt_12_3_;
      if (this.field_70170_p.field_72995_K && this.field_70279_a) {
         if (this.field_70277_c > 0) {
            lvt_6_6_ = this.field_70165_t + (this.field_70274_d - this.field_70165_t) / (double)this.field_70277_c;
            lvt_8_8_ = this.field_70163_u + (this.field_70275_e - this.field_70163_u) / (double)this.field_70277_c;
            lvt_10_5_ = this.field_70161_v + (this.field_70272_f - this.field_70161_v) / (double)this.field_70277_c;
            lvt_12_3_ = MathHelper.func_76138_g(this.field_70273_g - (double)this.field_70177_z);
            this.field_70177_z = (float)((double)this.field_70177_z + lvt_12_3_ / (double)this.field_70277_c);
            this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70281_h - (double)this.field_70125_A) / (double)this.field_70277_c);
            --this.field_70277_c;
            this.func_70107_b(lvt_6_6_, lvt_8_8_, lvt_10_5_);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
         } else {
            lvt_6_6_ = this.field_70165_t + this.field_70159_w;
            lvt_8_8_ = this.field_70163_u + this.field_70181_x;
            lvt_10_5_ = this.field_70161_v + this.field_70179_y;
            this.func_70107_b(lvt_6_6_, lvt_8_8_, lvt_10_5_);
            if (this.field_70122_E) {
               this.field_70159_w *= 0.5D;
               this.field_70181_x *= 0.5D;
               this.field_70179_y *= 0.5D;
            }

            this.field_70159_w *= 0.9900000095367432D;
            this.field_70181_x *= 0.949999988079071D;
            this.field_70179_y *= 0.9900000095367432D;
         }

      } else {
         if (lvt_2_1_ < 1.0D) {
            lvt_6_6_ = lvt_2_1_ * 2.0D - 1.0D;
            this.field_70181_x += 0.03999999910593033D * lvt_6_6_;
         } else {
            if (this.field_70181_x < 0.0D) {
               this.field_70181_x /= 2.0D;
            }

            this.field_70181_x += 0.007000000216066837D;
         }

         if (this.field_70153_n instanceof EntityLivingBase) {
            EntityLivingBase lvt_6_5_ = (EntityLivingBase)this.field_70153_n;
            float lvt_7_2_ = this.field_70153_n.field_70177_z + -lvt_6_5_.field_70702_br * 90.0F;
            this.field_70159_w += -Math.sin((double)(lvt_7_2_ * 3.1415927F / 180.0F)) * this.field_70276_b * (double)lvt_6_5_.field_70701_bs * 0.05000000074505806D;
            this.field_70179_y += Math.cos((double)(lvt_7_2_ * 3.1415927F / 180.0F)) * this.field_70276_b * (double)lvt_6_5_.field_70701_bs * 0.05000000074505806D;
         }

         lvt_6_6_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         if (lvt_6_6_ > 0.35D) {
            lvt_8_8_ = 0.35D / lvt_6_6_;
            this.field_70159_w *= lvt_8_8_;
            this.field_70179_y *= lvt_8_8_;
            lvt_6_6_ = 0.35D;
         }

         if (lvt_6_6_ > lvt_4_2_ && this.field_70276_b < 0.35D) {
            this.field_70276_b += (0.35D - this.field_70276_b) / 35.0D;
            if (this.field_70276_b > 0.35D) {
               this.field_70276_b = 0.35D;
            }
         } else {
            this.field_70276_b -= (this.field_70276_b - 0.07D) / 35.0D;
            if (this.field_70276_b < 0.07D) {
               this.field_70276_b = 0.07D;
            }
         }

         int lvt_8_7_;
         for(lvt_8_7_ = 0; lvt_8_7_ < 4; ++lvt_8_7_) {
            int lvt_9_2_ = MathHelper.func_76128_c(this.field_70165_t + ((double)(lvt_8_7_ % 2) - 0.5D) * 0.8D);
            lvt_10_4_ = MathHelper.func_76128_c(this.field_70161_v + ((double)(lvt_8_7_ / 2) - 0.5D) * 0.8D);

            for(int lvt_11_2_ = 0; lvt_11_2_ < 2; ++lvt_11_2_) {
               int lvt_12_2_ = MathHelper.func_76128_c(this.field_70163_u) + lvt_11_2_;
               BlockPos lvt_13_2_ = new BlockPos(lvt_9_2_, lvt_12_2_, lvt_10_4_);
               Block lvt_14_1_ = this.field_70170_p.func_180495_p(lvt_13_2_).func_177230_c();
               if (lvt_14_1_ == Blocks.field_150431_aC) {
                  this.field_70170_p.func_175698_g(lvt_13_2_);
                  this.field_70123_F = false;
               } else if (lvt_14_1_ == Blocks.field_150392_bi) {
                  this.field_70170_p.func_175655_b(lvt_13_2_, true);
                  this.field_70123_F = false;
               }
            }
         }

         if (this.field_70122_E) {
            this.field_70159_w *= 0.5D;
            this.field_70181_x *= 0.5D;
            this.field_70179_y *= 0.5D;
         }

         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         if (this.field_70123_F && lvt_4_2_ > 0.2975D) {
            if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
               this.func_70106_y();
               if (this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  for(lvt_8_7_ = 0; lvt_8_7_ < 3; ++lvt_8_7_) {
                     this.func_145778_a(Item.func_150898_a(Blocks.field_150344_f), 1, 0.0F);
                  }

                  for(lvt_8_7_ = 0; lvt_8_7_ < 2; ++lvt_8_7_) {
                     this.func_145778_a(Items.field_151055_y, 1, 0.0F);
                  }
               }
            }
         } else {
            this.field_70159_w *= 0.9900000095367432D;
            this.field_70181_x *= 0.949999988079071D;
            this.field_70179_y *= 0.9900000095367432D;
         }

         this.field_70125_A = 0.0F;
         lvt_8_8_ = (double)this.field_70177_z;
         lvt_10_5_ = this.field_70169_q - this.field_70165_t;
         lvt_12_3_ = this.field_70166_s - this.field_70161_v;
         if (lvt_10_5_ * lvt_10_5_ + lvt_12_3_ * lvt_12_3_ > 0.001D) {
            lvt_8_8_ = (double)((float)(MathHelper.func_181159_b(lvt_12_3_, lvt_10_5_) * 180.0D / 3.141592653589793D));
         }

         double lvt_14_2_ = MathHelper.func_76138_g(lvt_8_8_ - (double)this.field_70177_z);
         if (lvt_14_2_ > 20.0D) {
            lvt_14_2_ = 20.0D;
         }

         if (lvt_14_2_ < -20.0D) {
            lvt_14_2_ = -20.0D;
         }

         this.field_70177_z = (float)((double)this.field_70177_z + lvt_14_2_);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         if (!this.field_70170_p.field_72995_K) {
            List<Entity> lvt_16_1_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            if (lvt_16_1_ != null && !lvt_16_1_.isEmpty()) {
               for(int lvt_17_3_ = 0; lvt_17_3_ < lvt_16_1_.size(); ++lvt_17_3_) {
                  Entity lvt_18_1_ = (Entity)lvt_16_1_.get(lvt_17_3_);
                  if (lvt_18_1_ != this.field_70153_n && lvt_18_1_.func_70104_M() && lvt_18_1_ instanceof EntityBoat) {
                     lvt_18_1_.func_70108_f(this);
                  }
               }
            }

            if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
               this.field_70153_n = null;
            }

         }
      }
   }

   public void func_70043_V() {
      if (this.field_70153_n != null) {
         double lvt_1_1_ = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         double lvt_3_1_ = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         this.field_70153_n.func_70107_b(this.field_70165_t + lvt_1_1_, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + lvt_3_1_);
      }
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != p_130002_1_) {
         return true;
      } else {
         if (!this.field_70170_p.field_72995_K) {
            p_130002_1_.func_70078_a(this);
         }

         return true;
      }
   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
      if (p_180433_3_) {
         if (this.field_70143_R > 3.0F) {
            this.func_180430_e(this.field_70143_R, 1.0F);
            if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
               this.func_70106_y();
               if (this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  int lvt_6_2_;
                  for(lvt_6_2_ = 0; lvt_6_2_ < 3; ++lvt_6_2_) {
                     this.func_145778_a(Item.func_150898_a(Blocks.field_150344_f), 1, 0.0F);
                  }

                  for(lvt_6_2_ = 0; lvt_6_2_ < 2; ++lvt_6_2_) {
                     this.func_145778_a(Items.field_151055_y, 1, 0.0F);
                  }
               }
            }

            this.field_70143_R = 0.0F;
         }
      } else if (this.field_70170_p.func_180495_p((new BlockPos(this)).func_177977_b()).func_177230_c().func_149688_o() != Material.field_151586_h && p_180433_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_180433_1_);
      }

   }

   public void func_70266_a(float p_70266_1_) {
      this.field_70180_af.func_75692_b(19, p_70266_1_);
   }

   public float func_70271_g() {
      return this.field_70180_af.func_111145_d(19);
   }

   public void func_70265_b(int p_70265_1_) {
      this.field_70180_af.func_75692_b(17, p_70265_1_);
   }

   public int func_70268_h() {
      return this.field_70180_af.func_75679_c(17);
   }

   public void func_70269_c(int p_70269_1_) {
      this.field_70180_af.func_75692_b(18, p_70269_1_);
   }

   public int func_70267_i() {
      return this.field_70180_af.func_75679_c(18);
   }

   public void func_70270_d(boolean p_70270_1_) {
      this.field_70279_a = p_70270_1_;
   }
}
