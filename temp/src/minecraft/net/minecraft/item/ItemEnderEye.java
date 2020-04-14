package net.minecraft.item;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item {
   public ItemEnderEye() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_);
      if (p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_) && lvt_9_1_.func_177230_c() == Blocks.field_150378_br && !(Boolean)lvt_9_1_.func_177229_b(BlockEndPortalFrame.field_176507_b)) {
         if (p_180614_3_.field_72995_K) {
            return true;
         } else {
            p_180614_3_.func_180501_a(p_180614_4_, lvt_9_1_.func_177226_a(BlockEndPortalFrame.field_176507_b, true), 2);
            p_180614_3_.func_175666_e(p_180614_4_, Blocks.field_150378_br);
            --p_180614_1_.field_77994_a;

            for(int lvt_10_1_ = 0; lvt_10_1_ < 16; ++lvt_10_1_) {
               double lvt_11_1_ = (double)((float)p_180614_4_.func_177958_n() + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
               double lvt_13_1_ = (double)((float)p_180614_4_.func_177956_o() + 0.8125F);
               double lvt_15_1_ = (double)((float)p_180614_4_.func_177952_p() + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
               double lvt_17_1_ = 0.0D;
               double lvt_19_1_ = 0.0D;
               double lvt_21_1_ = 0.0D;
               p_180614_3_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_19_1_, lvt_21_1_);
            }

            EnumFacing lvt_10_2_ = (EnumFacing)lvt_9_1_.func_177229_b(BlockEndPortalFrame.field_176508_a);
            int lvt_11_2_ = 0;
            int lvt_12_1_ = 0;
            boolean lvt_13_2_ = false;
            boolean lvt_14_1_ = true;
            EnumFacing lvt_15_2_ = lvt_10_2_.func_176746_e();

            for(int lvt_16_1_ = -2; lvt_16_1_ <= 2; ++lvt_16_1_) {
               BlockPos lvt_17_2_ = p_180614_4_.func_177967_a(lvt_15_2_, lvt_16_1_);
               IBlockState lvt_18_1_ = p_180614_3_.func_180495_p(lvt_17_2_);
               if (lvt_18_1_.func_177230_c() == Blocks.field_150378_br) {
                  if (!(Boolean)lvt_18_1_.func_177229_b(BlockEndPortalFrame.field_176507_b)) {
                     lvt_14_1_ = false;
                     break;
                  }

                  lvt_12_1_ = lvt_16_1_;
                  if (!lvt_13_2_) {
                     lvt_11_2_ = lvt_16_1_;
                     lvt_13_2_ = true;
                  }
               }
            }

            if (lvt_14_1_ && lvt_12_1_ == lvt_11_2_ + 2) {
               BlockPos lvt_16_2_ = p_180614_4_.func_177967_a(lvt_10_2_, 4);

               int lvt_17_5_;
               for(lvt_17_5_ = lvt_11_2_; lvt_17_5_ <= lvt_12_1_; ++lvt_17_5_) {
                  BlockPos lvt_18_2_ = lvt_16_2_.func_177967_a(lvt_15_2_, lvt_17_5_);
                  IBlockState lvt_19_2_ = p_180614_3_.func_180495_p(lvt_18_2_);
                  if (lvt_19_2_.func_177230_c() != Blocks.field_150378_br || !(Boolean)lvt_19_2_.func_177229_b(BlockEndPortalFrame.field_176507_b)) {
                     lvt_14_1_ = false;
                     break;
                  }
               }

               int lvt_18_4_;
               BlockPos lvt_19_4_;
               for(lvt_17_5_ = lvt_11_2_ - 1; lvt_17_5_ <= lvt_12_1_ + 1; lvt_17_5_ += 4) {
                  lvt_16_2_ = p_180614_4_.func_177967_a(lvt_15_2_, lvt_17_5_);

                  for(lvt_18_4_ = 1; lvt_18_4_ <= 3; ++lvt_18_4_) {
                     lvt_19_4_ = lvt_16_2_.func_177967_a(lvt_10_2_, lvt_18_4_);
                     IBlockState lvt_20_1_ = p_180614_3_.func_180495_p(lvt_19_4_);
                     if (lvt_20_1_.func_177230_c() != Blocks.field_150378_br || !(Boolean)lvt_20_1_.func_177229_b(BlockEndPortalFrame.field_176507_b)) {
                        lvt_14_1_ = false;
                        break;
                     }
                  }
               }

               if (lvt_14_1_) {
                  for(lvt_17_5_ = lvt_11_2_; lvt_17_5_ <= lvt_12_1_; ++lvt_17_5_) {
                     lvt_16_2_ = p_180614_4_.func_177967_a(lvt_15_2_, lvt_17_5_);

                     for(lvt_18_4_ = 1; lvt_18_4_ <= 3; ++lvt_18_4_) {
                        lvt_19_4_ = lvt_16_2_.func_177967_a(lvt_10_2_, lvt_18_4_);
                        p_180614_3_.func_180501_a(lvt_19_4_, Blocks.field_150384_bq.func_176223_P(), 2);
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      MovingObjectPosition lvt_4_1_ = this.func_77621_a(p_77659_2_, p_77659_3_, false);
      if (lvt_4_1_ != null && lvt_4_1_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && p_77659_2_.func_180495_p(lvt_4_1_.func_178782_a()).func_177230_c() == Blocks.field_150378_br) {
         return p_77659_1_;
      } else {
         if (!p_77659_2_.field_72995_K) {
            BlockPos lvt_5_1_ = p_77659_2_.func_180499_a("Stronghold", new BlockPos(p_77659_3_));
            if (lvt_5_1_ != null) {
               EntityEnderEye lvt_6_1_ = new EntityEnderEye(p_77659_2_, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v);
               lvt_6_1_.func_180465_a(lvt_5_1_);
               p_77659_2_.func_72838_d(lvt_6_1_);
               p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
               p_77659_2_.func_180498_a((EntityPlayer)null, 1002, new BlockPos(p_77659_3_), 0);
               if (!p_77659_3_.field_71075_bZ.field_75098_d) {
                  --p_77659_1_.field_77994_a;
               }

               p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
            }
         }

         return p_77659_1_;
      }
   }
}
