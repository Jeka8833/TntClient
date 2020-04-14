package net.minecraft.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item {
   public ItemMonsterPlacer() {
      this.func_77627_a(true);
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      String lvt_2_1_ = ("" + StatCollector.func_74838_a(this.func_77658_a() + ".name")).trim();
      String lvt_3_1_ = EntityList.func_75617_a(p_77653_1_.func_77960_j());
      if (lvt_3_1_ != null) {
         lvt_2_1_ = lvt_2_1_ + " " + StatCollector.func_74838_a("entity." + lvt_3_1_ + ".name");
      }

      return lvt_2_1_;
   }

   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      EntityList.EntityEggInfo lvt_3_1_ = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(p_82790_1_.func_77960_j());
      if (lvt_3_1_ != null) {
         return p_82790_2_ == 0 ? lvt_3_1_.field_75611_b : lvt_3_1_.field_75612_c;
      } else {
         return 16777215;
      }
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (p_180614_3_.field_72995_K) {
         return true;
      } else if (!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         IBlockState lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_);
         if (lvt_9_1_.func_177230_c() == Blocks.field_150474_ac) {
            TileEntity lvt_10_1_ = p_180614_3_.func_175625_s(p_180614_4_);
            if (lvt_10_1_ instanceof TileEntityMobSpawner) {
               MobSpawnerBaseLogic lvt_11_1_ = ((TileEntityMobSpawner)lvt_10_1_).func_145881_a();
               lvt_11_1_.func_98272_a(EntityList.func_75617_a(p_180614_1_.func_77960_j()));
               lvt_10_1_.func_70296_d();
               p_180614_3_.func_175689_h(p_180614_4_);
               if (!p_180614_2_.field_71075_bZ.field_75098_d) {
                  --p_180614_1_.field_77994_a;
               }

               return true;
            }
         }

         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
         double lvt_10_2_ = 0.0D;
         if (p_180614_5_ == EnumFacing.UP && lvt_9_1_ instanceof BlockFence) {
            lvt_10_2_ = 0.5D;
         }

         Entity lvt_12_1_ = func_77840_a(p_180614_3_, p_180614_1_.func_77960_j(), (double)p_180614_4_.func_177958_n() + 0.5D, (double)p_180614_4_.func_177956_o() + lvt_10_2_, (double)p_180614_4_.func_177952_p() + 0.5D);
         if (lvt_12_1_ != null) {
            if (lvt_12_1_ instanceof EntityLivingBase && p_180614_1_.func_82837_s()) {
               lvt_12_1_.func_96094_a(p_180614_1_.func_82833_r());
            }

            if (!p_180614_2_.field_71075_bZ.field_75098_d) {
               --p_180614_1_.field_77994_a;
            }
         }

         return true;
      }
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if (p_77659_2_.field_72995_K) {
         return p_77659_1_;
      } else {
         MovingObjectPosition lvt_4_1_ = this.func_77621_a(p_77659_2_, p_77659_3_, true);
         if (lvt_4_1_ == null) {
            return p_77659_1_;
         } else {
            if (lvt_4_1_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos lvt_5_1_ = lvt_4_1_.func_178782_a();
               if (!p_77659_2_.func_175660_a(p_77659_3_, lvt_5_1_)) {
                  return p_77659_1_;
               }

               if (!p_77659_3_.func_175151_a(lvt_5_1_, lvt_4_1_.field_178784_b, p_77659_1_)) {
                  return p_77659_1_;
               }

               if (p_77659_2_.func_180495_p(lvt_5_1_).func_177230_c() instanceof BlockLiquid) {
                  Entity lvt_6_1_ = func_77840_a(p_77659_2_, p_77659_1_.func_77960_j(), (double)lvt_5_1_.func_177958_n() + 0.5D, (double)lvt_5_1_.func_177956_o() + 0.5D, (double)lvt_5_1_.func_177952_p() + 0.5D);
                  if (lvt_6_1_ != null) {
                     if (lvt_6_1_ instanceof EntityLivingBase && p_77659_1_.func_82837_s()) {
                        ((EntityLiving)lvt_6_1_).func_96094_a(p_77659_1_.func_82833_r());
                     }

                     if (!p_77659_3_.field_71075_bZ.field_75098_d) {
                        --p_77659_1_.field_77994_a;
                     }

                     p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
                  }
               }
            }

            return p_77659_1_;
         }
      }
   }

   public static Entity func_77840_a(World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_) {
      if (!EntityList.field_75627_a.containsKey(p_77840_1_)) {
         return null;
      } else {
         Entity lvt_8_1_ = null;

         for(int lvt_9_1_ = 0; lvt_9_1_ < 1; ++lvt_9_1_) {
            lvt_8_1_ = EntityList.func_75616_a(p_77840_1_, p_77840_0_);
            if (lvt_8_1_ instanceof EntityLivingBase) {
               EntityLiving lvt_10_1_ = (EntityLiving)lvt_8_1_;
               lvt_8_1_.func_70012_b(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.func_76142_g(p_77840_0_.field_73012_v.nextFloat() * 360.0F), 0.0F);
               lvt_10_1_.field_70759_as = lvt_10_1_.field_70177_z;
               lvt_10_1_.field_70761_aq = lvt_10_1_.field_70177_z;
               lvt_10_1_.func_180482_a(p_77840_0_.func_175649_E(new BlockPos(lvt_10_1_)), (IEntityLivingData)null);
               p_77840_0_.func_72838_d(lvt_8_1_);
               lvt_10_1_.func_70642_aH();
            }
         }

         return lvt_8_1_;
      }
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      Iterator lvt_4_1_ = EntityList.field_75627_a.values().iterator();

      while(lvt_4_1_.hasNext()) {
         EntityList.EntityEggInfo lvt_5_1_ = (EntityList.EntityEggInfo)lvt_4_1_.next();
         p_150895_3_.add(new ItemStack(p_150895_1_, 1, lvt_5_1_.field_75613_a));
      }

   }
}
