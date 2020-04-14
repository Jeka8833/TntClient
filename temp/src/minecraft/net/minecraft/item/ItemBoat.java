package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {
   public ItemBoat() {
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78029_e);
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      float lvt_4_1_ = 1.0F;
      float lvt_5_1_ = p_77659_3_.field_70127_C + (p_77659_3_.field_70125_A - p_77659_3_.field_70127_C) * lvt_4_1_;
      float lvt_6_1_ = p_77659_3_.field_70126_B + (p_77659_3_.field_70177_z - p_77659_3_.field_70126_B) * lvt_4_1_;
      double lvt_7_1_ = p_77659_3_.field_70169_q + (p_77659_3_.field_70165_t - p_77659_3_.field_70169_q) * (double)lvt_4_1_;
      double lvt_9_1_ = p_77659_3_.field_70167_r + (p_77659_3_.field_70163_u - p_77659_3_.field_70167_r) * (double)lvt_4_1_ + (double)p_77659_3_.func_70047_e();
      double lvt_11_1_ = p_77659_3_.field_70166_s + (p_77659_3_.field_70161_v - p_77659_3_.field_70166_s) * (double)lvt_4_1_;
      Vec3 lvt_13_1_ = new Vec3(lvt_7_1_, lvt_9_1_, lvt_11_1_);
      float lvt_14_1_ = MathHelper.func_76134_b(-lvt_6_1_ * 0.017453292F - 3.1415927F);
      float lvt_15_1_ = MathHelper.func_76126_a(-lvt_6_1_ * 0.017453292F - 3.1415927F);
      float lvt_16_1_ = -MathHelper.func_76134_b(-lvt_5_1_ * 0.017453292F);
      float lvt_17_1_ = MathHelper.func_76126_a(-lvt_5_1_ * 0.017453292F);
      float lvt_18_1_ = lvt_15_1_ * lvt_16_1_;
      float lvt_20_1_ = lvt_14_1_ * lvt_16_1_;
      double lvt_21_1_ = 5.0D;
      Vec3 lvt_23_1_ = lvt_13_1_.func_72441_c((double)lvt_18_1_ * lvt_21_1_, (double)lvt_17_1_ * lvt_21_1_, (double)lvt_20_1_ * lvt_21_1_);
      MovingObjectPosition lvt_24_1_ = p_77659_2_.func_72901_a(lvt_13_1_, lvt_23_1_, true);
      if (lvt_24_1_ == null) {
         return p_77659_1_;
      } else {
         Vec3 lvt_25_1_ = p_77659_3_.func_70676_i(lvt_4_1_);
         boolean lvt_26_1_ = false;
         float lvt_27_1_ = 1.0F;
         List<Entity> lvt_28_1_ = p_77659_2_.func_72839_b(p_77659_3_, p_77659_3_.func_174813_aQ().func_72321_a(lvt_25_1_.field_72450_a * lvt_21_1_, lvt_25_1_.field_72448_b * lvt_21_1_, lvt_25_1_.field_72449_c * lvt_21_1_).func_72314_b((double)lvt_27_1_, (double)lvt_27_1_, (double)lvt_27_1_));

         for(int lvt_29_1_ = 0; lvt_29_1_ < lvt_28_1_.size(); ++lvt_29_1_) {
            Entity lvt_30_1_ = (Entity)lvt_28_1_.get(lvt_29_1_);
            if (lvt_30_1_.func_70067_L()) {
               float lvt_31_1_ = lvt_30_1_.func_70111_Y();
               AxisAlignedBB lvt_32_1_ = lvt_30_1_.func_174813_aQ().func_72314_b((double)lvt_31_1_, (double)lvt_31_1_, (double)lvt_31_1_);
               if (lvt_32_1_.func_72318_a(lvt_13_1_)) {
                  lvt_26_1_ = true;
               }
            }
         }

         if (lvt_26_1_) {
            return p_77659_1_;
         } else {
            if (lvt_24_1_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos lvt_29_2_ = lvt_24_1_.func_178782_a();
               if (p_77659_2_.func_180495_p(lvt_29_2_).func_177230_c() == Blocks.field_150431_aC) {
                  lvt_29_2_ = lvt_29_2_.func_177977_b();
               }

               EntityBoat lvt_30_2_ = new EntityBoat(p_77659_2_, (double)((float)lvt_29_2_.func_177958_n() + 0.5F), (double)((float)lvt_29_2_.func_177956_o() + 1.0F), (double)((float)lvt_29_2_.func_177952_p() + 0.5F));
               lvt_30_2_.field_70177_z = (float)(((MathHelper.func_76128_c((double)(p_77659_3_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
               if (!p_77659_2_.func_72945_a(lvt_30_2_, lvt_30_2_.func_174813_aQ().func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                  return p_77659_1_;
               }

               if (!p_77659_2_.field_72995_K) {
                  p_77659_2_.func_72838_d(lvt_30_2_);
               }

               if (!p_77659_3_.field_71075_bZ.field_75098_d) {
                  --p_77659_1_.field_77994_a;
               }

               p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
            }

            return p_77659_1_;
         }
      }
   }
}
