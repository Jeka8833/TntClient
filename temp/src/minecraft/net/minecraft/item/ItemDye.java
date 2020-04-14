package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemDye extends Item {
   public static final int[] field_150922_c = new int[]{1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

   public ItemDye() {
      this.func_77627_a(true);
      this.func_77656_e(0);
      this.func_77637_a(CreativeTabs.field_78035_l);
   }

   public String func_77667_c(ItemStack p_77667_1_) {
      int lvt_2_1_ = p_77667_1_.func_77960_j();
      return super.func_77658_a() + "." + EnumDyeColor.func_176766_a(lvt_2_1_).func_176762_d();
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         EnumDyeColor lvt_9_1_ = EnumDyeColor.func_176766_a(p_180614_1_.func_77960_j());
         if (lvt_9_1_ == EnumDyeColor.WHITE) {
            if (func_179234_a(p_180614_1_, p_180614_3_, p_180614_4_)) {
               if (!p_180614_3_.field_72995_K) {
                  p_180614_3_.func_175718_b(2005, p_180614_4_, 0);
               }

               return true;
            }
         } else if (lvt_9_1_ == EnumDyeColor.BROWN) {
            IBlockState lvt_10_1_ = p_180614_3_.func_180495_p(p_180614_4_);
            Block lvt_11_1_ = lvt_10_1_.func_177230_c();
            if (lvt_11_1_ == Blocks.field_150364_r && lvt_10_1_.func_177229_b(BlockPlanks.field_176383_a) == BlockPlanks.EnumType.JUNGLE) {
               if (p_180614_5_ == EnumFacing.DOWN) {
                  return false;
               }

               if (p_180614_5_ == EnumFacing.UP) {
                  return false;
               }

               p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
               if (p_180614_3_.func_175623_d(p_180614_4_)) {
                  IBlockState lvt_12_1_ = Blocks.field_150375_by.func_180642_a(p_180614_3_, p_180614_4_, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_, 0, p_180614_2_);
                  p_180614_3_.func_180501_a(p_180614_4_, lvt_12_1_, 2);
                  if (!p_180614_2_.field_71075_bZ.field_75098_d) {
                     --p_180614_1_.field_77994_a;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   public static boolean func_179234_a(ItemStack p_179234_0_, World p_179234_1_, BlockPos p_179234_2_) {
      IBlockState lvt_3_1_ = p_179234_1_.func_180495_p(p_179234_2_);
      if (lvt_3_1_.func_177230_c() instanceof IGrowable) {
         IGrowable lvt_4_1_ = (IGrowable)lvt_3_1_.func_177230_c();
         if (lvt_4_1_.func_176473_a(p_179234_1_, p_179234_2_, lvt_3_1_, p_179234_1_.field_72995_K)) {
            if (!p_179234_1_.field_72995_K) {
               if (lvt_4_1_.func_180670_a(p_179234_1_, p_179234_1_.field_73012_v, p_179234_2_, lvt_3_1_)) {
                  lvt_4_1_.func_176474_b(p_179234_1_, p_179234_1_.field_73012_v, p_179234_2_, lvt_3_1_);
               }

               --p_179234_0_.field_77994_a;
            }

            return true;
         }
      }

      return false;
   }

   public static void func_180617_a(World p_180617_0_, BlockPos p_180617_1_, int p_180617_2_) {
      if (p_180617_2_ == 0) {
         p_180617_2_ = 15;
      }

      Block lvt_3_1_ = p_180617_0_.func_180495_p(p_180617_1_).func_177230_c();
      if (lvt_3_1_.func_149688_o() != Material.field_151579_a) {
         lvt_3_1_.func_180654_a(p_180617_0_, p_180617_1_);

         for(int lvt_4_1_ = 0; lvt_4_1_ < p_180617_2_; ++lvt_4_1_) {
            double lvt_5_1_ = field_77697_d.nextGaussian() * 0.02D;
            double lvt_7_1_ = field_77697_d.nextGaussian() * 0.02D;
            double lvt_9_1_ = field_77697_d.nextGaussian() * 0.02D;
            p_180617_0_.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)p_180617_1_.func_177958_n() + field_77697_d.nextFloat()), (double)p_180617_1_.func_177956_o() + (double)field_77697_d.nextFloat() * lvt_3_1_.func_149669_A(), (double)((float)p_180617_1_.func_177952_p() + field_77697_d.nextFloat()), lvt_5_1_, lvt_7_1_, lvt_9_1_);
         }

      }
   }

   public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_) {
      if (p_111207_3_ instanceof EntitySheep) {
         EntitySheep lvt_4_1_ = (EntitySheep)p_111207_3_;
         EnumDyeColor lvt_5_1_ = EnumDyeColor.func_176766_a(p_111207_1_.func_77960_j());
         if (!lvt_4_1_.func_70892_o() && lvt_4_1_.func_175509_cj() != lvt_5_1_) {
            lvt_4_1_.func_175512_b(lvt_5_1_);
            --p_111207_1_.field_77994_a;
         }

         return true;
      } else {
         return false;
      }
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < 16; ++lvt_4_1_) {
         p_150895_3_.add(new ItemStack(p_150895_1_, 1, lvt_4_1_));
      }

   }
}
