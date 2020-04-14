package net.minecraft.item;

import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Rotations;
import net.minecraft.world.World;

public class ItemArmorStand extends Item {
   public ItemArmorStand() {
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (p_180614_5_ == EnumFacing.DOWN) {
         return false;
      } else {
         boolean lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_).func_177230_c().func_176200_f(p_180614_3_, p_180614_4_);
         BlockPos lvt_10_1_ = lvt_9_1_ ? p_180614_4_ : p_180614_4_.func_177972_a(p_180614_5_);
         if (!p_180614_2_.func_175151_a(lvt_10_1_, p_180614_5_, p_180614_1_)) {
            return false;
         } else {
            BlockPos lvt_11_1_ = lvt_10_1_.func_177984_a();
            boolean lvt_12_1_ = !p_180614_3_.func_175623_d(lvt_10_1_) && !p_180614_3_.func_180495_p(lvt_10_1_).func_177230_c().func_176200_f(p_180614_3_, lvt_10_1_);
            lvt_12_1_ |= !p_180614_3_.func_175623_d(lvt_11_1_) && !p_180614_3_.func_180495_p(lvt_11_1_).func_177230_c().func_176200_f(p_180614_3_, lvt_11_1_);
            if (lvt_12_1_) {
               return false;
            } else {
               double lvt_13_1_ = (double)lvt_10_1_.func_177958_n();
               double lvt_15_1_ = (double)lvt_10_1_.func_177956_o();
               double lvt_17_1_ = (double)lvt_10_1_.func_177952_p();
               List<Entity> lvt_19_1_ = p_180614_3_.func_72839_b((Entity)null, AxisAlignedBB.func_178781_a(lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_13_1_ + 1.0D, lvt_15_1_ + 2.0D, lvt_17_1_ + 1.0D));
               if (lvt_19_1_.size() > 0) {
                  return false;
               } else {
                  if (!p_180614_3_.field_72995_K) {
                     p_180614_3_.func_175698_g(lvt_10_1_);
                     p_180614_3_.func_175698_g(lvt_11_1_);
                     EntityArmorStand lvt_20_1_ = new EntityArmorStand(p_180614_3_, lvt_13_1_ + 0.5D, lvt_15_1_, lvt_17_1_ + 0.5D);
                     float lvt_21_1_ = (float)MathHelper.func_76141_d((MathHelper.func_76142_g(p_180614_2_.field_70177_z - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                     lvt_20_1_.func_70012_b(lvt_13_1_ + 0.5D, lvt_15_1_, lvt_17_1_ + 0.5D, lvt_21_1_, 0.0F);
                     this.func_179221_a(lvt_20_1_, p_180614_3_.field_73012_v);
                     NBTTagCompound lvt_22_1_ = p_180614_1_.func_77978_p();
                     if (lvt_22_1_ != null && lvt_22_1_.func_150297_b("EntityTag", 10)) {
                        NBTTagCompound lvt_23_1_ = new NBTTagCompound();
                        lvt_20_1_.func_70039_c(lvt_23_1_);
                        lvt_23_1_.func_179237_a(lvt_22_1_.func_74775_l("EntityTag"));
                        lvt_20_1_.func_70020_e(lvt_23_1_);
                     }

                     p_180614_3_.func_72838_d(lvt_20_1_);
                  }

                  --p_180614_1_.field_77994_a;
                  return true;
               }
            }
         }
      }
   }

   private void func_179221_a(EntityArmorStand p_179221_1_, Random p_179221_2_) {
      Rotations lvt_3_1_ = p_179221_1_.func_175418_s();
      float lvt_5_1_ = p_179221_2_.nextFloat() * 5.0F;
      float lvt_6_1_ = p_179221_2_.nextFloat() * 20.0F - 10.0F;
      Rotations lvt_4_1_ = new Rotations(lvt_3_1_.func_179415_b() + lvt_5_1_, lvt_3_1_.func_179416_c() + lvt_6_1_, lvt_3_1_.func_179413_d());
      p_179221_1_.func_175415_a(lvt_4_1_);
      lvt_3_1_ = p_179221_1_.func_175408_t();
      lvt_5_1_ = p_179221_2_.nextFloat() * 10.0F - 5.0F;
      lvt_4_1_ = new Rotations(lvt_3_1_.func_179415_b(), lvt_3_1_.func_179416_c() + lvt_5_1_, lvt_3_1_.func_179413_d());
      p_179221_1_.func_175424_b(lvt_4_1_);
   }
}
