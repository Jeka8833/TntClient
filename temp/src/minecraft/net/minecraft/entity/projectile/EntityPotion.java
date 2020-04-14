package net.minecraft.entity.projectile;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPotion extends EntityThrowable {
   private ItemStack field_70197_d;

   public EntityPotion(World p_i1788_1_) {
      super(p_i1788_1_);
   }

   public EntityPotion(World p_i1789_1_, EntityLivingBase p_i1789_2_, int p_i1789_3_) {
      this(p_i1789_1_, p_i1789_2_, new ItemStack(Items.field_151068_bn, 1, p_i1789_3_));
   }

   public EntityPotion(World p_i1790_1_, EntityLivingBase p_i1790_2_, ItemStack p_i1790_3_) {
      super(p_i1790_1_, p_i1790_2_);
      this.field_70197_d = p_i1790_3_;
   }

   public EntityPotion(World p_i1791_1_, double p_i1791_2_, double p_i1791_4_, double p_i1791_6_, int p_i1791_8_) {
      this(p_i1791_1_, p_i1791_2_, p_i1791_4_, p_i1791_6_, new ItemStack(Items.field_151068_bn, 1, p_i1791_8_));
   }

   public EntityPotion(World p_i1792_1_, double p_i1792_2_, double p_i1792_4_, double p_i1792_6_, ItemStack p_i1792_8_) {
      super(p_i1792_1_, p_i1792_2_, p_i1792_4_, p_i1792_6_);
      this.field_70197_d = p_i1792_8_;
   }

   protected float func_70185_h() {
      return 0.05F;
   }

   protected float func_70182_d() {
      return 0.5F;
   }

   protected float func_70183_g() {
      return -20.0F;
   }

   public void func_82340_a(int p_82340_1_) {
      if (this.field_70197_d == null) {
         this.field_70197_d = new ItemStack(Items.field_151068_bn, 1, 0);
      }

      this.field_70197_d.func_77964_b(p_82340_1_);
   }

   public int func_70196_i() {
      if (this.field_70197_d == null) {
         this.field_70197_d = new ItemStack(Items.field_151068_bn, 1, 0);
      }

      return this.field_70197_d.func_77960_j();
   }

   protected void func_70184_a(MovingObjectPosition p_70184_1_) {
      if (!this.field_70170_p.field_72995_K) {
         List<PotionEffect> lvt_2_1_ = Items.field_151068_bn.func_77832_l(this.field_70197_d);
         if (lvt_2_1_ != null && !lvt_2_1_.isEmpty()) {
            AxisAlignedBB lvt_3_1_ = this.func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D);
            List<EntityLivingBase> lvt_4_1_ = this.field_70170_p.func_72872_a(EntityLivingBase.class, lvt_3_1_);
            if (!lvt_4_1_.isEmpty()) {
               Iterator lvt_5_1_ = lvt_4_1_.iterator();

               label45:
               while(true) {
                  EntityLivingBase lvt_6_1_;
                  double lvt_7_1_;
                  do {
                     if (!lvt_5_1_.hasNext()) {
                        break label45;
                     }

                     lvt_6_1_ = (EntityLivingBase)lvt_5_1_.next();
                     lvt_7_1_ = this.func_70068_e(lvt_6_1_);
                  } while(lvt_7_1_ >= 16.0D);

                  double lvt_9_1_ = 1.0D - Math.sqrt(lvt_7_1_) / 4.0D;
                  if (lvt_6_1_ == p_70184_1_.field_72308_g) {
                     lvt_9_1_ = 1.0D;
                  }

                  Iterator lvt_11_1_ = lvt_2_1_.iterator();

                  while(lvt_11_1_.hasNext()) {
                     PotionEffect lvt_12_1_ = (PotionEffect)lvt_11_1_.next();
                     int lvt_13_1_ = lvt_12_1_.func_76456_a();
                     if (Potion.field_76425_a[lvt_13_1_].func_76403_b()) {
                        Potion.field_76425_a[lvt_13_1_].func_180793_a(this, this.func_85052_h(), lvt_6_1_, lvt_12_1_.func_76458_c(), lvt_9_1_);
                     } else {
                        int lvt_14_1_ = (int)(lvt_9_1_ * (double)lvt_12_1_.func_76459_b() + 0.5D);
                        if (lvt_14_1_ > 20) {
                           lvt_6_1_.func_70690_d(new PotionEffect(lvt_13_1_, lvt_14_1_, lvt_12_1_.func_76458_c()));
                        }
                     }
                  }
               }
            }
         }

         this.field_70170_p.func_175718_b(2002, new BlockPos(this), this.func_70196_i());
         this.func_70106_y();
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if (p_70037_1_.func_150297_b("Potion", 10)) {
         this.field_70197_d = ItemStack.func_77949_a(p_70037_1_.func_74775_l("Potion"));
      } else {
         this.func_82340_a(p_70037_1_.func_74762_e("potionValue"));
      }

      if (this.field_70197_d == null) {
         this.func_70106_y();
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      if (this.field_70197_d != null) {
         p_70014_1_.func_74782_a("Potion", this.field_70197_d.func_77955_b(new NBTTagCompound()));
      }

   }
}
