package net.minecraft.entity.projectile;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityThrowable extends Entity implements IProjectile {
   private int field_145788_c = -1;
   private int field_145786_d = -1;
   private int field_145787_e = -1;
   private Block field_174853_f;
   protected boolean field_174854_a;
   public int field_70191_b;
   private EntityLivingBase field_70192_c;
   private String field_85053_h;
   private int field_70194_h;
   private int field_70195_i;

   public EntityThrowable(World p_i1776_1_) {
      super(p_i1776_1_);
      this.func_70105_a(0.25F, 0.25F);
   }

   protected void func_70088_a() {
   }

   public boolean func_70112_a(double p_70112_1_) {
      double lvt_3_1_ = this.func_174813_aQ().func_72320_b() * 4.0D;
      if (Double.isNaN(lvt_3_1_)) {
         lvt_3_1_ = 4.0D;
      }

      lvt_3_1_ *= 64.0D;
      return p_70112_1_ < lvt_3_1_ * lvt_3_1_;
   }

   public EntityThrowable(World p_i1777_1_, EntityLivingBase p_i1777_2_) {
      super(p_i1777_1_);
      this.field_70192_c = p_i1777_2_;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70012_b(p_i1777_2_.field_70165_t, p_i1777_2_.field_70163_u + (double)p_i1777_2_.func_70047_e(), p_i1777_2_.field_70161_v, p_i1777_2_.field_70177_z, p_i1777_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float lvt_3_1_ = 0.4F;
      this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * lvt_3_1_);
      this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * lvt_3_1_);
      this.field_70181_x = (double)(-MathHelper.func_76126_a((this.field_70125_A + this.func_70183_g()) / 180.0F * 3.1415927F) * lvt_3_1_);
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), 1.0F);
   }

   public EntityThrowable(World p_i1778_1_, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
      super(p_i1778_1_);
      this.field_70194_h = 0;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70107_b(p_i1778_2_, p_i1778_4_, p_i1778_6_);
   }

   protected float func_70182_d() {
      return 1.5F;
   }

   protected float func_70183_g() {
      return 0.0F;
   }

   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
      float lvt_9_1_ = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
      p_70186_1_ /= (double)lvt_9_1_;
      p_70186_3_ /= (double)lvt_9_1_;
      p_70186_5_ /= (double)lvt_9_1_;
      p_70186_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_1_ *= (double)p_70186_7_;
      p_70186_3_ *= (double)p_70186_7_;
      p_70186_5_ *= (double)p_70186_7_;
      this.field_70159_w = p_70186_1_;
      this.field_70181_x = p_70186_3_;
      this.field_70179_y = p_70186_5_;
      float lvt_10_1_ = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
      this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70186_1_, p_70186_5_) * 180.0D / 3.1415927410125732D);
      this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70186_3_, (double)lvt_10_1_) * 180.0D / 3.1415927410125732D);
      this.field_70194_h = 0;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float lvt_7_1_ = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
         this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70016_1_, p_70016_5_) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70016_3_, (double)lvt_7_1_) * 180.0D / 3.1415927410125732D);
      }

   }

   public void func_70071_h_() {
      this.field_70142_S = this.field_70165_t;
      this.field_70137_T = this.field_70163_u;
      this.field_70136_U = this.field_70161_v;
      super.func_70071_h_();
      if (this.field_70191_b > 0) {
         --this.field_70191_b;
      }

      if (this.field_174854_a) {
         if (this.field_70170_p.func_180495_p(new BlockPos(this.field_145788_c, this.field_145786_d, this.field_145787_e)).func_177230_c() == this.field_174853_f) {
            ++this.field_70194_h;
            if (this.field_70194_h == 1200) {
               this.func_70106_y();
            }

            return;
         }

         this.field_174854_a = false;
         this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70194_h = 0;
         this.field_70195_i = 0;
      } else {
         ++this.field_70195_i;
      }

      Vec3 lvt_1_1_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Vec3 lvt_2_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      MovingObjectPosition lvt_3_1_ = this.field_70170_p.func_72933_a(lvt_1_1_, lvt_2_1_);
      lvt_1_1_ = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      lvt_2_1_ = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      if (lvt_3_1_ != null) {
         lvt_2_1_ = new Vec3(lvt_3_1_.field_72307_f.field_72450_a, lvt_3_1_.field_72307_f.field_72448_b, lvt_3_1_.field_72307_f.field_72449_c);
      }

      if (!this.field_70170_p.field_72995_K) {
         Entity lvt_4_1_ = null;
         List<Entity> lvt_5_1_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
         double lvt_6_1_ = 0.0D;
         EntityLivingBase lvt_8_1_ = this.func_85052_h();

         for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_5_1_.size(); ++lvt_9_1_) {
            Entity lvt_10_1_ = (Entity)lvt_5_1_.get(lvt_9_1_);
            if (lvt_10_1_.func_70067_L() && (lvt_10_1_ != lvt_8_1_ || this.field_70195_i >= 5)) {
               float lvt_11_1_ = 0.3F;
               AxisAlignedBB lvt_12_1_ = lvt_10_1_.func_174813_aQ().func_72314_b((double)lvt_11_1_, (double)lvt_11_1_, (double)lvt_11_1_);
               MovingObjectPosition lvt_13_1_ = lvt_12_1_.func_72327_a(lvt_1_1_, lvt_2_1_);
               if (lvt_13_1_ != null) {
                  double lvt_14_1_ = lvt_1_1_.func_72436_e(lvt_13_1_.field_72307_f);
                  if (lvt_14_1_ < lvt_6_1_ || lvt_6_1_ == 0.0D) {
                     lvt_4_1_ = lvt_10_1_;
                     lvt_6_1_ = lvt_14_1_;
                  }
               }
            }
         }

         if (lvt_4_1_ != null) {
            lvt_3_1_ = new MovingObjectPosition(lvt_4_1_);
         }
      }

      if (lvt_3_1_ != null) {
         if (lvt_3_1_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_70170_p.func_180495_p(lvt_3_1_.func_178782_a()).func_177230_c() == Blocks.field_150427_aO) {
            this.func_181015_d(lvt_3_1_.func_178782_a());
         } else {
            this.func_70184_a(lvt_3_1_);
         }
      }

      this.field_70165_t += this.field_70159_w;
      this.field_70163_u += this.field_70181_x;
      this.field_70161_v += this.field_70179_y;
      float lvt_4_2_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);

      for(this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)lvt_4_2_) * 180.0D / 3.1415927410125732D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
      }

      while(this.field_70125_A - this.field_70127_C >= 180.0F) {
         this.field_70127_C += 360.0F;
      }

      while(this.field_70177_z - this.field_70126_B < -180.0F) {
         this.field_70126_B -= 360.0F;
      }

      while(this.field_70177_z - this.field_70126_B >= 180.0F) {
         this.field_70126_B += 360.0F;
      }

      this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
      this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
      float lvt_5_2_ = 0.99F;
      float lvt_6_2_ = this.func_70185_h();
      if (this.func_70090_H()) {
         for(int lvt_7_1_ = 0; lvt_7_1_ < 4; ++lvt_7_1_) {
            float lvt_8_2_ = 0.25F;
            this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * (double)lvt_8_2_, this.field_70163_u - this.field_70181_x * (double)lvt_8_2_, this.field_70161_v - this.field_70179_y * (double)lvt_8_2_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
         }

         lvt_5_2_ = 0.8F;
      }

      this.field_70159_w *= (double)lvt_5_2_;
      this.field_70181_x *= (double)lvt_5_2_;
      this.field_70179_y *= (double)lvt_5_2_;
      this.field_70181_x -= (double)lvt_6_2_;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   protected float func_70185_h() {
      return 0.03F;
   }

   protected abstract void func_70184_a(MovingObjectPosition var1);

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74777_a("xTile", (short)this.field_145788_c);
      p_70014_1_.func_74777_a("yTile", (short)this.field_145786_d);
      p_70014_1_.func_74777_a("zTile", (short)this.field_145787_e);
      ResourceLocation lvt_2_1_ = (ResourceLocation)Block.field_149771_c.func_177774_c(this.field_174853_f);
      p_70014_1_.func_74778_a("inTile", lvt_2_1_ == null ? "" : lvt_2_1_.toString());
      p_70014_1_.func_74774_a("shake", (byte)this.field_70191_b);
      p_70014_1_.func_74774_a("inGround", (byte)(this.field_174854_a ? 1 : 0));
      if ((this.field_85053_h == null || this.field_85053_h.length() == 0) && this.field_70192_c instanceof EntityPlayer) {
         this.field_85053_h = this.field_70192_c.func_70005_c_();
      }

      p_70014_1_.func_74778_a("ownerName", this.field_85053_h == null ? "" : this.field_85053_h);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_145788_c = p_70037_1_.func_74765_d("xTile");
      this.field_145786_d = p_70037_1_.func_74765_d("yTile");
      this.field_145787_e = p_70037_1_.func_74765_d("zTile");
      if (p_70037_1_.func_150297_b("inTile", 8)) {
         this.field_174853_f = Block.func_149684_b(p_70037_1_.func_74779_i("inTile"));
      } else {
         this.field_174853_f = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
      }

      this.field_70191_b = p_70037_1_.func_74771_c("shake") & 255;
      this.field_174854_a = p_70037_1_.func_74771_c("inGround") == 1;
      this.field_70192_c = null;
      this.field_85053_h = p_70037_1_.func_74779_i("ownerName");
      if (this.field_85053_h != null && this.field_85053_h.length() == 0) {
         this.field_85053_h = null;
      }

      this.field_70192_c = this.func_85052_h();
   }

   public EntityLivingBase func_85052_h() {
      if (this.field_70192_c == null && this.field_85053_h != null && this.field_85053_h.length() > 0) {
         this.field_70192_c = this.field_70170_p.func_72924_a(this.field_85053_h);
         if (this.field_70192_c == null && this.field_70170_p instanceof WorldServer) {
            try {
               Entity lvt_1_1_ = ((WorldServer)this.field_70170_p).func_175733_a(UUID.fromString(this.field_85053_h));
               if (lvt_1_1_ instanceof EntityLivingBase) {
                  this.field_70192_c = (EntityLivingBase)lvt_1_1_;
               }
            } catch (Throwable var2) {
               this.field_70192_c = null;
            }
         }
      }

      return this.field_70192_c;
   }
}
