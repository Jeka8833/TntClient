package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Explosion {
   private final boolean field_77286_a;
   private final boolean field_82755_b;
   private final Random field_77290_i;
   private final World field_77287_j;
   private final double field_77284_b;
   private final double field_77285_c;
   private final double field_77282_d;
   private final Entity field_77283_e;
   private final float field_77280_f;
   private final List<BlockPos> field_77281_g;
   private final Map<EntityPlayer, Vec3> field_77288_k;

   public Explosion(World p_i45752_1_, Entity p_i45752_2_, double p_i45752_3_, double p_i45752_5_, double p_i45752_7_, float p_i45752_9_, List<BlockPos> p_i45752_10_) {
      this(p_i45752_1_, p_i45752_2_, p_i45752_3_, p_i45752_5_, p_i45752_7_, p_i45752_9_, false, true, p_i45752_10_);
   }

   public Explosion(World p_i45753_1_, Entity p_i45753_2_, double p_i45753_3_, double p_i45753_5_, double p_i45753_7_, float p_i45753_9_, boolean p_i45753_10_, boolean p_i45753_11_, List<BlockPos> p_i45753_12_) {
      this(p_i45753_1_, p_i45753_2_, p_i45753_3_, p_i45753_5_, p_i45753_7_, p_i45753_9_, p_i45753_10_, p_i45753_11_);
      this.field_77281_g.addAll(p_i45753_12_);
   }

   public Explosion(World p_i45754_1_, Entity p_i45754_2_, double p_i45754_3_, double p_i45754_5_, double p_i45754_7_, float p_i45754_9_, boolean p_i45754_10_, boolean p_i45754_11_) {
      this.field_77290_i = new Random();
      this.field_77281_g = Lists.newArrayList();
      this.field_77288_k = Maps.newHashMap();
      this.field_77287_j = p_i45754_1_;
      this.field_77283_e = p_i45754_2_;
      this.field_77280_f = p_i45754_9_;
      this.field_77284_b = p_i45754_3_;
      this.field_77285_c = p_i45754_5_;
      this.field_77282_d = p_i45754_7_;
      this.field_77286_a = p_i45754_10_;
      this.field_82755_b = p_i45754_11_;
   }

   public void func_77278_a() {
      Set<BlockPos> lvt_1_1_ = Sets.newHashSet();
      int lvt_2_1_ = true;

      int lvt_4_1_;
      int lvt_5_1_;
      for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
         for(lvt_4_1_ = 0; lvt_4_1_ < 16; ++lvt_4_1_) {
            for(lvt_5_1_ = 0; lvt_5_1_ < 16; ++lvt_5_1_) {
               if (lvt_3_1_ == 0 || lvt_3_1_ == 15 || lvt_4_1_ == 0 || lvt_4_1_ == 15 || lvt_5_1_ == 0 || lvt_5_1_ == 15) {
                  double lvt_6_1_ = (double)((float)lvt_3_1_ / 15.0F * 2.0F - 1.0F);
                  double lvt_8_1_ = (double)((float)lvt_4_1_ / 15.0F * 2.0F - 1.0F);
                  double lvt_10_1_ = (double)((float)lvt_5_1_ / 15.0F * 2.0F - 1.0F);
                  double lvt_12_1_ = Math.sqrt(lvt_6_1_ * lvt_6_1_ + lvt_8_1_ * lvt_8_1_ + lvt_10_1_ * lvt_10_1_);
                  lvt_6_1_ /= lvt_12_1_;
                  lvt_8_1_ /= lvt_12_1_;
                  lvt_10_1_ /= lvt_12_1_;
                  float lvt_14_1_ = this.field_77280_f * (0.7F + this.field_77287_j.field_73012_v.nextFloat() * 0.6F);
                  double lvt_15_1_ = this.field_77284_b;
                  double lvt_17_1_ = this.field_77285_c;
                  double lvt_19_1_ = this.field_77282_d;

                  for(float var21 = 0.3F; lvt_14_1_ > 0.0F; lvt_14_1_ -= 0.22500001F) {
                     BlockPos lvt_22_1_ = new BlockPos(lvt_15_1_, lvt_17_1_, lvt_19_1_);
                     IBlockState lvt_23_1_ = this.field_77287_j.func_180495_p(lvt_22_1_);
                     if (lvt_23_1_.func_177230_c().func_149688_o() != Material.field_151579_a) {
                        float lvt_24_1_ = this.field_77283_e != null ? this.field_77283_e.func_180428_a(this, this.field_77287_j, lvt_22_1_, lvt_23_1_) : lvt_23_1_.func_177230_c().func_149638_a((Entity)null);
                        lvt_14_1_ -= (lvt_24_1_ + 0.3F) * 0.3F;
                     }

                     if (lvt_14_1_ > 0.0F && (this.field_77283_e == null || this.field_77283_e.func_174816_a(this, this.field_77287_j, lvt_22_1_, lvt_23_1_, lvt_14_1_))) {
                        lvt_1_1_.add(lvt_22_1_);
                     }

                     lvt_15_1_ += lvt_6_1_ * 0.30000001192092896D;
                     lvt_17_1_ += lvt_8_1_ * 0.30000001192092896D;
                     lvt_19_1_ += lvt_10_1_ * 0.30000001192092896D;
                  }
               }
            }
         }
      }

      this.field_77281_g.addAll(lvt_1_1_);
      float lvt_3_2_ = this.field_77280_f * 2.0F;
      lvt_4_1_ = MathHelper.func_76128_c(this.field_77284_b - (double)lvt_3_2_ - 1.0D);
      lvt_5_1_ = MathHelper.func_76128_c(this.field_77284_b + (double)lvt_3_2_ + 1.0D);
      int lvt_6_2_ = MathHelper.func_76128_c(this.field_77285_c - (double)lvt_3_2_ - 1.0D);
      int lvt_7_1_ = MathHelper.func_76128_c(this.field_77285_c + (double)lvt_3_2_ + 1.0D);
      int lvt_8_2_ = MathHelper.func_76128_c(this.field_77282_d - (double)lvt_3_2_ - 1.0D);
      int lvt_9_1_ = MathHelper.func_76128_c(this.field_77282_d + (double)lvt_3_2_ + 1.0D);
      List<Entity> lvt_10_2_ = this.field_77287_j.func_72839_b(this.field_77283_e, new AxisAlignedBB((double)lvt_4_1_, (double)lvt_6_2_, (double)lvt_8_2_, (double)lvt_5_1_, (double)lvt_7_1_, (double)lvt_9_1_));
      Vec3 lvt_11_1_ = new Vec3(this.field_77284_b, this.field_77285_c, this.field_77282_d);

      for(int lvt_12_2_ = 0; lvt_12_2_ < lvt_10_2_.size(); ++lvt_12_2_) {
         Entity lvt_13_1_ = (Entity)lvt_10_2_.get(lvt_12_2_);
         if (!lvt_13_1_.func_180427_aV()) {
            double lvt_14_2_ = lvt_13_1_.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / (double)lvt_3_2_;
            if (lvt_14_2_ <= 1.0D) {
               double lvt_16_1_ = lvt_13_1_.field_70165_t - this.field_77284_b;
               double lvt_18_1_ = lvt_13_1_.field_70163_u + (double)lvt_13_1_.func_70047_e() - this.field_77285_c;
               double lvt_20_1_ = lvt_13_1_.field_70161_v - this.field_77282_d;
               double lvt_22_2_ = (double)MathHelper.func_76133_a(lvt_16_1_ * lvt_16_1_ + lvt_18_1_ * lvt_18_1_ + lvt_20_1_ * lvt_20_1_);
               if (lvt_22_2_ != 0.0D) {
                  lvt_16_1_ /= lvt_22_2_;
                  lvt_18_1_ /= lvt_22_2_;
                  lvt_20_1_ /= lvt_22_2_;
                  double lvt_24_2_ = (double)this.field_77287_j.func_72842_a(lvt_11_1_, lvt_13_1_.func_174813_aQ());
                  double lvt_26_1_ = (1.0D - lvt_14_2_) * lvt_24_2_;
                  lvt_13_1_.func_70097_a(DamageSource.func_94539_a(this), (float)((int)((lvt_26_1_ * lvt_26_1_ + lvt_26_1_) / 2.0D * 8.0D * (double)lvt_3_2_ + 1.0D)));
                  double lvt_28_1_ = EnchantmentProtection.func_92092_a(lvt_13_1_, lvt_26_1_);
                  lvt_13_1_.field_70159_w += lvt_16_1_ * lvt_28_1_;
                  lvt_13_1_.field_70181_x += lvt_18_1_ * lvt_28_1_;
                  lvt_13_1_.field_70179_y += lvt_20_1_ * lvt_28_1_;
                  if (lvt_13_1_ instanceof EntityPlayer && !((EntityPlayer)lvt_13_1_).field_71075_bZ.field_75102_a) {
                     this.field_77288_k.put((EntityPlayer)lvt_13_1_, new Vec3(lvt_16_1_ * lvt_26_1_, lvt_18_1_ * lvt_26_1_, lvt_20_1_ * lvt_26_1_));
                  }
               }
            }
         }
      }

   }

   public void func_77279_a(boolean p_77279_1_) {
      this.field_77287_j.func_72908_a(this.field_77284_b, this.field_77285_c, this.field_77282_d, "random.explode", 4.0F, (1.0F + (this.field_77287_j.field_73012_v.nextFloat() - this.field_77287_j.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
      if (this.field_77280_f >= 2.0F && this.field_82755_b) {
         this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D);
      } else {
         this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D);
      }

      Iterator lvt_2_2_;
      BlockPos lvt_3_1_;
      if (this.field_82755_b) {
         lvt_2_2_ = this.field_77281_g.iterator();

         while(lvt_2_2_.hasNext()) {
            lvt_3_1_ = (BlockPos)lvt_2_2_.next();
            Block lvt_4_1_ = this.field_77287_j.func_180495_p(lvt_3_1_).func_177230_c();
            if (p_77279_1_) {
               double lvt_5_1_ = (double)((float)lvt_3_1_.func_177958_n() + this.field_77287_j.field_73012_v.nextFloat());
               double lvt_7_1_ = (double)((float)lvt_3_1_.func_177956_o() + this.field_77287_j.field_73012_v.nextFloat());
               double lvt_9_1_ = (double)((float)lvt_3_1_.func_177952_p() + this.field_77287_j.field_73012_v.nextFloat());
               double lvt_11_1_ = lvt_5_1_ - this.field_77284_b;
               double lvt_13_1_ = lvt_7_1_ - this.field_77285_c;
               double lvt_15_1_ = lvt_9_1_ - this.field_77282_d;
               double lvt_17_1_ = (double)MathHelper.func_76133_a(lvt_11_1_ * lvt_11_1_ + lvt_13_1_ * lvt_13_1_ + lvt_15_1_ * lvt_15_1_);
               lvt_11_1_ /= lvt_17_1_;
               lvt_13_1_ /= lvt_17_1_;
               lvt_15_1_ /= lvt_17_1_;
               double lvt_19_1_ = 0.5D / (lvt_17_1_ / (double)this.field_77280_f + 0.1D);
               lvt_19_1_ *= (double)(this.field_77287_j.field_73012_v.nextFloat() * this.field_77287_j.field_73012_v.nextFloat() + 0.3F);
               lvt_11_1_ *= lvt_19_1_;
               lvt_13_1_ *= lvt_19_1_;
               lvt_15_1_ *= lvt_19_1_;
               this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, (lvt_5_1_ + this.field_77284_b * 1.0D) / 2.0D, (lvt_7_1_ + this.field_77285_c * 1.0D) / 2.0D, (lvt_9_1_ + this.field_77282_d * 1.0D) / 2.0D, lvt_11_1_, lvt_13_1_, lvt_15_1_);
               this.field_77287_j.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_15_1_);
            }

            if (lvt_4_1_.func_149688_o() != Material.field_151579_a) {
               if (lvt_4_1_.func_149659_a(this)) {
                  lvt_4_1_.func_180653_a(this.field_77287_j, lvt_3_1_, this.field_77287_j.func_180495_p(lvt_3_1_), 1.0F / this.field_77280_f, 0);
               }

               this.field_77287_j.func_180501_a(lvt_3_1_, Blocks.field_150350_a.func_176223_P(), 3);
               lvt_4_1_.func_180652_a(this.field_77287_j, lvt_3_1_, this);
            }
         }
      }

      if (this.field_77286_a) {
         lvt_2_2_ = this.field_77281_g.iterator();

         while(lvt_2_2_.hasNext()) {
            lvt_3_1_ = (BlockPos)lvt_2_2_.next();
            if (this.field_77287_j.func_180495_p(lvt_3_1_).func_177230_c().func_149688_o() == Material.field_151579_a && this.field_77287_j.func_180495_p(lvt_3_1_.func_177977_b()).func_177230_c().func_149730_j() && this.field_77290_i.nextInt(3) == 0) {
               this.field_77287_j.func_175656_a(lvt_3_1_, Blocks.field_150480_ab.func_176223_P());
            }
         }
      }

   }

   public Map<EntityPlayer, Vec3> func_77277_b() {
      return this.field_77288_k;
   }

   public EntityLivingBase func_94613_c() {
      if (this.field_77283_e == null) {
         return null;
      } else if (this.field_77283_e instanceof EntityTNTPrimed) {
         return ((EntityTNTPrimed)this.field_77283_e).func_94083_c();
      } else {
         return this.field_77283_e instanceof EntityLivingBase ? (EntityLivingBase)this.field_77283_e : null;
      }
   }

   public void func_180342_d() {
      this.field_77281_g.clear();
   }

   public List<BlockPos> func_180343_e() {
      return this.field_77281_g;
   }
}
