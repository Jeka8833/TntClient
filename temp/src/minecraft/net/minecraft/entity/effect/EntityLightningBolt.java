package net.minecraft.entity.effect;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightningBolt extends EntityWeatherEffect {
   private int field_70262_b;
   public long field_70264_a;
   private int field_70263_c;

   public EntityLightningBolt(World p_i1703_1_, double p_i1703_2_, double p_i1703_4_, double p_i1703_6_) {
      super(p_i1703_1_);
      this.func_70012_b(p_i1703_2_, p_i1703_4_, p_i1703_6_, 0.0F, 0.0F);
      this.field_70262_b = 2;
      this.field_70264_a = this.field_70146_Z.nextLong();
      this.field_70263_c = this.field_70146_Z.nextInt(3) + 1;
      BlockPos lvt_8_1_ = new BlockPos(this);
      if (!p_i1703_1_.field_72995_K && p_i1703_1_.func_82736_K().func_82766_b("doFireTick") && (p_i1703_1_.func_175659_aa() == EnumDifficulty.NORMAL || p_i1703_1_.func_175659_aa() == EnumDifficulty.HARD) && p_i1703_1_.func_175697_a(lvt_8_1_, 10)) {
         if (p_i1703_1_.func_180495_p(lvt_8_1_).func_177230_c().func_149688_o() == Material.field_151579_a && Blocks.field_150480_ab.func_176196_c(p_i1703_1_, lvt_8_1_)) {
            p_i1703_1_.func_175656_a(lvt_8_1_, Blocks.field_150480_ab.func_176223_P());
         }

         for(int lvt_9_1_ = 0; lvt_9_1_ < 4; ++lvt_9_1_) {
            BlockPos lvt_10_1_ = lvt_8_1_.func_177982_a(this.field_70146_Z.nextInt(3) - 1, this.field_70146_Z.nextInt(3) - 1, this.field_70146_Z.nextInt(3) - 1);
            if (p_i1703_1_.func_180495_p(lvt_10_1_).func_177230_c().func_149688_o() == Material.field_151579_a && Blocks.field_150480_ab.func_176196_c(p_i1703_1_, lvt_10_1_)) {
               p_i1703_1_.func_175656_a(lvt_10_1_, Blocks.field_150480_ab.func_176223_P());
            }
         }
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (this.field_70262_b == 2) {
         this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 10000.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.explode", 2.0F, 0.5F + this.field_70146_Z.nextFloat() * 0.2F);
      }

      --this.field_70262_b;
      if (this.field_70262_b < 0) {
         if (this.field_70263_c == 0) {
            this.func_70106_y();
         } else if (this.field_70262_b < -this.field_70146_Z.nextInt(10)) {
            --this.field_70263_c;
            this.field_70262_b = 1;
            this.field_70264_a = this.field_70146_Z.nextLong();
            BlockPos lvt_1_1_ = new BlockPos(this);
            if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_82736_K().func_82766_b("doFireTick") && this.field_70170_p.func_175697_a(lvt_1_1_, 10) && this.field_70170_p.func_180495_p(lvt_1_1_).func_177230_c().func_149688_o() == Material.field_151579_a && Blocks.field_150480_ab.func_176196_c(this.field_70170_p, lvt_1_1_)) {
               this.field_70170_p.func_175656_a(lvt_1_1_, Blocks.field_150480_ab.func_176223_P());
            }
         }
      }

      if (this.field_70262_b >= 0) {
         if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175702_c(2);
         } else {
            double lvt_1_2_ = 3.0D;
            List<Entity> lvt_3_1_ = this.field_70170_p.func_72839_b(this, new AxisAlignedBB(this.field_70165_t - lvt_1_2_, this.field_70163_u - lvt_1_2_, this.field_70161_v - lvt_1_2_, this.field_70165_t + lvt_1_2_, this.field_70163_u + 6.0D + lvt_1_2_, this.field_70161_v + lvt_1_2_));

            for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.size(); ++lvt_4_1_) {
               Entity lvt_5_1_ = (Entity)lvt_3_1_.get(lvt_4_1_);
               lvt_5_1_.func_70077_a(this);
            }
         }
      }

   }

   protected void func_70088_a() {
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
   }
}
