package net.minecraft.block;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockChest extends BlockContainer {
   public static final PropertyDirection field_176459_a;
   public final int field_149956_a;

   protected BlockChest(int p_i45397_1_) {
      super(Material.field_151575_d);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176459_a, EnumFacing.NORTH));
      this.field_149956_a = p_i45397_1_;
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public int func_149645_b() {
      return 2;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      if (p_180654_1_.func_180495_p(p_180654_2_.func_177978_c()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
      } else if (p_180654_1_.func_180495_p(p_180654_2_.func_177968_d()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
      } else if (p_180654_1_.func_180495_p(p_180654_2_.func_177976_e()).func_177230_c() == this) {
         this.func_149676_a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      } else if (p_180654_1_.func_180495_p(p_180654_2_.func_177974_f()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
      } else {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176455_e(p_176213_1_, p_176213_2_, p_176213_3_);
      Iterator lvt_4_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      while(lvt_4_1_.hasNext()) {
         EnumFacing lvt_5_1_ = (EnumFacing)lvt_4_1_.next();
         BlockPos lvt_6_1_ = p_176213_2_.func_177972_a(lvt_5_1_);
         IBlockState lvt_7_1_ = p_176213_1_.func_180495_p(lvt_6_1_);
         if (lvt_7_1_.func_177230_c() == this) {
            this.func_176455_e(p_176213_1_, lvt_6_1_, lvt_7_1_);
         }
      }

   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176459_a, p_180642_8_.func_174811_aO());
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      EnumFacing lvt_6_1_ = EnumFacing.func_176731_b(MathHelper.func_76128_c((double)(p_180633_4_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3).func_176734_d();
      p_180633_3_ = p_180633_3_.func_177226_a(field_176459_a, lvt_6_1_);
      BlockPos lvt_7_1_ = p_180633_2_.func_177978_c();
      BlockPos lvt_8_1_ = p_180633_2_.func_177968_d();
      BlockPos lvt_9_1_ = p_180633_2_.func_177976_e();
      BlockPos lvt_10_1_ = p_180633_2_.func_177974_f();
      boolean lvt_11_1_ = this == p_180633_1_.func_180495_p(lvt_7_1_).func_177230_c();
      boolean lvt_12_1_ = this == p_180633_1_.func_180495_p(lvt_8_1_).func_177230_c();
      boolean lvt_13_1_ = this == p_180633_1_.func_180495_p(lvt_9_1_).func_177230_c();
      boolean lvt_14_1_ = this == p_180633_1_.func_180495_p(lvt_10_1_).func_177230_c();
      if (!lvt_11_1_ && !lvt_12_1_ && !lvt_13_1_ && !lvt_14_1_) {
         p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
      } else if (lvt_6_1_.func_176740_k() == EnumFacing.Axis.X && (lvt_11_1_ || lvt_12_1_)) {
         if (lvt_11_1_) {
            p_180633_1_.func_180501_a(lvt_7_1_, p_180633_3_, 3);
         } else {
            p_180633_1_.func_180501_a(lvt_8_1_, p_180633_3_, 3);
         }

         p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
      } else if (lvt_6_1_.func_176740_k() == EnumFacing.Axis.Z && (lvt_13_1_ || lvt_14_1_)) {
         if (lvt_13_1_) {
            p_180633_1_.func_180501_a(lvt_9_1_, p_180633_3_, 3);
         } else {
            p_180633_1_.func_180501_a(lvt_10_1_, p_180633_3_, 3);
         }

         p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
      }

      if (p_180633_5_.func_82837_s()) {
         TileEntity lvt_15_1_ = p_180633_1_.func_175625_s(p_180633_2_);
         if (lvt_15_1_ instanceof TileEntityChest) {
            ((TileEntityChest)lvt_15_1_).func_145976_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public IBlockState func_176455_e(World p_176455_1_, BlockPos p_176455_2_, IBlockState p_176455_3_) {
      if (p_176455_1_.field_72995_K) {
         return p_176455_3_;
      } else {
         IBlockState lvt_4_1_ = p_176455_1_.func_180495_p(p_176455_2_.func_177978_c());
         IBlockState lvt_5_1_ = p_176455_1_.func_180495_p(p_176455_2_.func_177968_d());
         IBlockState lvt_6_1_ = p_176455_1_.func_180495_p(p_176455_2_.func_177976_e());
         IBlockState lvt_7_1_ = p_176455_1_.func_180495_p(p_176455_2_.func_177974_f());
         EnumFacing lvt_8_1_ = (EnumFacing)p_176455_3_.func_177229_b(field_176459_a);
         Block lvt_9_1_ = lvt_4_1_.func_177230_c();
         Block lvt_10_1_ = lvt_5_1_.func_177230_c();
         Block lvt_11_1_ = lvt_6_1_.func_177230_c();
         Block lvt_12_1_ = lvt_7_1_.func_177230_c();
         if (lvt_9_1_ != this && lvt_10_1_ != this) {
            boolean lvt_13_2_ = lvt_9_1_.func_149730_j();
            boolean lvt_14_2_ = lvt_10_1_.func_149730_j();
            if (lvt_11_1_ == this || lvt_12_1_ == this) {
               BlockPos lvt_15_2_ = lvt_11_1_ == this ? p_176455_2_.func_177976_e() : p_176455_2_.func_177974_f();
               IBlockState lvt_16_3_ = p_176455_1_.func_180495_p(lvt_15_2_.func_177978_c());
               IBlockState lvt_17_2_ = p_176455_1_.func_180495_p(lvt_15_2_.func_177968_d());
               lvt_8_1_ = EnumFacing.SOUTH;
               EnumFacing lvt_18_3_;
               if (lvt_11_1_ == this) {
                  lvt_18_3_ = (EnumFacing)lvt_6_1_.func_177229_b(field_176459_a);
               } else {
                  lvt_18_3_ = (EnumFacing)lvt_7_1_.func_177229_b(field_176459_a);
               }

               if (lvt_18_3_ == EnumFacing.NORTH) {
                  lvt_8_1_ = EnumFacing.NORTH;
               }

               Block lvt_19_1_ = lvt_16_3_.func_177230_c();
               Block lvt_20_1_ = lvt_17_2_.func_177230_c();
               if ((lvt_13_2_ || lvt_19_1_.func_149730_j()) && !lvt_14_2_ && !lvt_20_1_.func_149730_j()) {
                  lvt_8_1_ = EnumFacing.SOUTH;
               }

               if ((lvt_14_2_ || lvt_20_1_.func_149730_j()) && !lvt_13_2_ && !lvt_19_1_.func_149730_j()) {
                  lvt_8_1_ = EnumFacing.NORTH;
               }
            }
         } else {
            BlockPos lvt_13_1_ = lvt_9_1_ == this ? p_176455_2_.func_177978_c() : p_176455_2_.func_177968_d();
            IBlockState lvt_14_1_ = p_176455_1_.func_180495_p(lvt_13_1_.func_177976_e());
            IBlockState lvt_15_1_ = p_176455_1_.func_180495_p(lvt_13_1_.func_177974_f());
            lvt_8_1_ = EnumFacing.EAST;
            EnumFacing lvt_16_2_;
            if (lvt_9_1_ == this) {
               lvt_16_2_ = (EnumFacing)lvt_4_1_.func_177229_b(field_176459_a);
            } else {
               lvt_16_2_ = (EnumFacing)lvt_5_1_.func_177229_b(field_176459_a);
            }

            if (lvt_16_2_ == EnumFacing.WEST) {
               lvt_8_1_ = EnumFacing.WEST;
            }

            Block lvt_17_1_ = lvt_14_1_.func_177230_c();
            Block lvt_18_1_ = lvt_15_1_.func_177230_c();
            if ((lvt_11_1_.func_149730_j() || lvt_17_1_.func_149730_j()) && !lvt_12_1_.func_149730_j() && !lvt_18_1_.func_149730_j()) {
               lvt_8_1_ = EnumFacing.EAST;
            }

            if ((lvt_12_1_.func_149730_j() || lvt_18_1_.func_149730_j()) && !lvt_11_1_.func_149730_j() && !lvt_17_1_.func_149730_j()) {
               lvt_8_1_ = EnumFacing.WEST;
            }
         }

         p_176455_3_ = p_176455_3_.func_177226_a(field_176459_a, lvt_8_1_);
         p_176455_1_.func_180501_a(p_176455_2_, p_176455_3_, 3);
         return p_176455_3_;
      }
   }

   public IBlockState func_176458_f(World p_176458_1_, BlockPos p_176458_2_, IBlockState p_176458_3_) {
      EnumFacing lvt_4_1_ = null;
      Iterator lvt_5_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      while(lvt_5_1_.hasNext()) {
         EnumFacing lvt_6_1_ = (EnumFacing)lvt_5_1_.next();
         IBlockState lvt_7_1_ = p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(lvt_6_1_));
         if (lvt_7_1_.func_177230_c() == this) {
            return p_176458_3_;
         }

         if (lvt_7_1_.func_177230_c().func_149730_j()) {
            if (lvt_4_1_ != null) {
               lvt_4_1_ = null;
               break;
            }

            lvt_4_1_ = lvt_6_1_;
         }
      }

      if (lvt_4_1_ != null) {
         return p_176458_3_.func_177226_a(field_176459_a, lvt_4_1_.func_176734_d());
      } else {
         EnumFacing lvt_5_2_ = (EnumFacing)p_176458_3_.func_177229_b(field_176459_a);
         if (p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(lvt_5_2_)).func_177230_c().func_149730_j()) {
            lvt_5_2_ = lvt_5_2_.func_176734_d();
         }

         if (p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(lvt_5_2_)).func_177230_c().func_149730_j()) {
            lvt_5_2_ = lvt_5_2_.func_176746_e();
         }

         if (p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(lvt_5_2_)).func_177230_c().func_149730_j()) {
            lvt_5_2_ = lvt_5_2_.func_176734_d();
         }

         return p_176458_3_.func_177226_a(field_176459_a, lvt_5_2_);
      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      int lvt_3_1_ = 0;
      BlockPos lvt_4_1_ = p_176196_2_.func_177976_e();
      BlockPos lvt_5_1_ = p_176196_2_.func_177974_f();
      BlockPos lvt_6_1_ = p_176196_2_.func_177978_c();
      BlockPos lvt_7_1_ = p_176196_2_.func_177968_d();
      if (p_176196_1_.func_180495_p(lvt_4_1_).func_177230_c() == this) {
         if (this.func_176454_e(p_176196_1_, lvt_4_1_)) {
            return false;
         }

         ++lvt_3_1_;
      }

      if (p_176196_1_.func_180495_p(lvt_5_1_).func_177230_c() == this) {
         if (this.func_176454_e(p_176196_1_, lvt_5_1_)) {
            return false;
         }

         ++lvt_3_1_;
      }

      if (p_176196_1_.func_180495_p(lvt_6_1_).func_177230_c() == this) {
         if (this.func_176454_e(p_176196_1_, lvt_6_1_)) {
            return false;
         }

         ++lvt_3_1_;
      }

      if (p_176196_1_.func_180495_p(lvt_7_1_).func_177230_c() == this) {
         if (this.func_176454_e(p_176196_1_, lvt_7_1_)) {
            return false;
         }

         ++lvt_3_1_;
      }

      return lvt_3_1_ <= 1;
   }

   private boolean func_176454_e(World p_176454_1_, BlockPos p_176454_2_) {
      if (p_176454_1_.func_180495_p(p_176454_2_).func_177230_c() != this) {
         return false;
      } else {
         Iterator lvt_3_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing lvt_4_1_;
         do {
            if (!lvt_3_1_.hasNext()) {
               return false;
            }

            lvt_4_1_ = (EnumFacing)lvt_3_1_.next();
         } while(p_176454_1_.func_180495_p(p_176454_2_.func_177972_a(lvt_4_1_)).func_177230_c() != this);

         return true;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      TileEntity lvt_5_1_ = p_176204_1_.func_175625_s(p_176204_2_);
      if (lvt_5_1_ instanceof TileEntityChest) {
         lvt_5_1_.func_145836_u();
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      TileEntity lvt_4_1_ = p_180663_1_.func_175625_s(p_180663_2_);
      if (lvt_4_1_ instanceof IInventory) {
         InventoryHelper.func_180175_a(p_180663_1_, p_180663_2_, (IInventory)lvt_4_1_);
         p_180663_1_.func_175666_e(p_180663_2_, this);
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         ILockableContainer lvt_9_1_ = this.func_180676_d(p_180639_1_, p_180639_2_);
         if (lvt_9_1_ != null) {
            p_180639_4_.func_71007_a(lvt_9_1_);
            if (this.field_149956_a == 0) {
               p_180639_4_.func_71029_a(StatList.field_181723_aa);
            } else if (this.field_149956_a == 1) {
               p_180639_4_.func_71029_a(StatList.field_181737_U);
            }
         }

         return true;
      }
   }

   public ILockableContainer func_180676_d(World p_180676_1_, BlockPos p_180676_2_) {
      TileEntity lvt_3_1_ = p_180676_1_.func_175625_s(p_180676_2_);
      if (!(lvt_3_1_ instanceof TileEntityChest)) {
         return null;
      } else {
         ILockableContainer lvt_4_1_ = (TileEntityChest)lvt_3_1_;
         if (this.func_176457_m(p_180676_1_, p_180676_2_)) {
            return null;
         } else {
            Iterator lvt_5_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

            while(true) {
               while(true) {
                  EnumFacing lvt_6_1_;
                  TileEntity lvt_9_1_;
                  do {
                     BlockPos lvt_7_1_;
                     Block lvt_8_1_;
                     do {
                        if (!lvt_5_1_.hasNext()) {
                           return (ILockableContainer)lvt_4_1_;
                        }

                        lvt_6_1_ = (EnumFacing)lvt_5_1_.next();
                        lvt_7_1_ = p_180676_2_.func_177972_a(lvt_6_1_);
                        lvt_8_1_ = p_180676_1_.func_180495_p(lvt_7_1_).func_177230_c();
                     } while(lvt_8_1_ != this);

                     if (this.func_176457_m(p_180676_1_, lvt_7_1_)) {
                        return null;
                     }

                     lvt_9_1_ = p_180676_1_.func_175625_s(lvt_7_1_);
                  } while(!(lvt_9_1_ instanceof TileEntityChest));

                  if (lvt_6_1_ != EnumFacing.WEST && lvt_6_1_ != EnumFacing.NORTH) {
                     lvt_4_1_ = new InventoryLargeChest("container.chestDouble", (ILockableContainer)lvt_4_1_, (TileEntityChest)lvt_9_1_);
                  } else {
                     lvt_4_1_ = new InventoryLargeChest("container.chestDouble", (TileEntityChest)lvt_9_1_, (ILockableContainer)lvt_4_1_);
                  }
               }
            }
         }
      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityChest();
   }

   public boolean func_149744_f() {
      return this.field_149956_a == 1;
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      if (!this.func_149744_f()) {
         return 0;
      } else {
         int lvt_5_1_ = 0;
         TileEntity lvt_6_1_ = p_180656_1_.func_175625_s(p_180656_2_);
         if (lvt_6_1_ instanceof TileEntityChest) {
            lvt_5_1_ = ((TileEntityChest)lvt_6_1_).field_145987_o;
         }

         return MathHelper.func_76125_a(lvt_5_1_, 0, 15);
      }
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      return p_176211_4_ == EnumFacing.UP ? this.func_180656_a(p_176211_1_, p_176211_2_, p_176211_3_, p_176211_4_) : 0;
   }

   private boolean func_176457_m(World p_176457_1_, BlockPos p_176457_2_) {
      return this.func_176456_n(p_176457_1_, p_176457_2_) || this.func_176453_o(p_176457_1_, p_176457_2_);
   }

   private boolean func_176456_n(World p_176456_1_, BlockPos p_176456_2_) {
      return p_176456_1_.func_180495_p(p_176456_2_.func_177984_a()).func_177230_c().func_149721_r();
   }

   private boolean func_176453_o(World p_176453_1_, BlockPos p_176453_2_) {
      Iterator lvt_3_1_ = p_176453_1_.func_72872_a(EntityOcelot.class, new AxisAlignedBB((double)p_176453_2_.func_177958_n(), (double)(p_176453_2_.func_177956_o() + 1), (double)p_176453_2_.func_177952_p(), (double)(p_176453_2_.func_177958_n() + 1), (double)(p_176453_2_.func_177956_o() + 2), (double)(p_176453_2_.func_177952_p() + 1))).iterator();

      EntityOcelot lvt_5_1_;
      do {
         if (!lvt_3_1_.hasNext()) {
            return false;
         }

         Entity lvt_4_1_ = (Entity)lvt_3_1_.next();
         lvt_5_1_ = (EntityOcelot)lvt_4_1_;
      } while(!lvt_5_1_.func_70906_o());

      return true;
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return Container.func_94526_b(this.func_180676_d(p_180641_1_, p_180641_2_));
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing lvt_2_1_ = EnumFacing.func_82600_a(p_176203_1_);
      if (lvt_2_1_.func_176740_k() == EnumFacing.Axis.Y) {
         lvt_2_1_ = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176459_a, lvt_2_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176459_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176459_a});
   }

   static {
      field_176459_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   }
}
