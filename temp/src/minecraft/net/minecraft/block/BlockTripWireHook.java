package net.minecraft.block;

import com.google.common.base.Objects;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripWireHook extends Block {
   public static final PropertyDirection field_176264_a;
   public static final PropertyBool field_176263_b;
   public static final PropertyBool field_176265_M;
   public static final PropertyBool field_176266_N;

   public BlockTripWireHook() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176264_a, EnumFacing.NORTH).func_177226_a(field_176263_b, false).func_177226_a(field_176265_M, false).func_177226_a(field_176266_N, false));
      this.func_149647_a(CreativeTabs.field_78028_d);
      this.func_149675_a(true);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176266_N, !World.func_175683_a(p_176221_2_, p_176221_3_.func_177977_b()));
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      return p_176198_3_.func_176740_k().func_176722_c() && p_176198_1_.func_180495_p(p_176198_2_.func_177972_a(p_176198_3_.func_176734_d())).func_177230_c().func_149721_r();
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      Iterator lvt_3_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      EnumFacing lvt_4_1_;
      do {
         if (!lvt_3_1_.hasNext()) {
            return false;
         }

         lvt_4_1_ = (EnumFacing)lvt_3_1_.next();
      } while(!p_176196_1_.func_180495_p(p_176196_2_.func_177972_a(lvt_4_1_)).func_177230_c().func_149721_r());

      return true;
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState lvt_9_1_ = this.func_176223_P().func_177226_a(field_176263_b, false).func_177226_a(field_176265_M, false).func_177226_a(field_176266_N, false);
      if (p_180642_3_.func_176740_k().func_176722_c()) {
         lvt_9_1_ = lvt_9_1_.func_177226_a(field_176264_a, p_180642_3_);
      }

      return lvt_9_1_;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      this.func_176260_a(p_180633_1_, p_180633_2_, p_180633_3_, false, false, -1, (IBlockState)null);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (p_176204_4_ != this) {
         if (this.func_176261_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
            EnumFacing lvt_5_1_ = (EnumFacing)p_176204_3_.func_177229_b(field_176264_a);
            if (!p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(lvt_5_1_.func_176734_d())).func_177230_c().func_149721_r()) {
               this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
               p_176204_1_.func_175698_g(p_176204_2_);
            }
         }

      }
   }

   public void func_176260_a(World p_176260_1_, BlockPos p_176260_2_, IBlockState p_176260_3_, boolean p_176260_4_, boolean p_176260_5_, int p_176260_6_, IBlockState p_176260_7_) {
      EnumFacing lvt_8_1_ = (EnumFacing)p_176260_3_.func_177229_b(field_176264_a);
      boolean lvt_9_1_ = (Boolean)p_176260_3_.func_177229_b(field_176265_M);
      boolean lvt_10_1_ = (Boolean)p_176260_3_.func_177229_b(field_176263_b);
      boolean lvt_11_1_ = !World.func_175683_a(p_176260_1_, p_176260_2_.func_177977_b());
      boolean lvt_12_1_ = !p_176260_4_;
      boolean lvt_13_1_ = false;
      int lvt_14_1_ = 0;
      IBlockState[] lvt_15_1_ = new IBlockState[42];

      BlockPos lvt_17_2_;
      for(int lvt_16_1_ = 1; lvt_16_1_ < 42; ++lvt_16_1_) {
         lvt_17_2_ = p_176260_2_.func_177967_a(lvt_8_1_, lvt_16_1_);
         IBlockState lvt_18_1_ = p_176260_1_.func_180495_p(lvt_17_2_);
         if (lvt_18_1_.func_177230_c() == Blocks.field_150479_bC) {
            if (lvt_18_1_.func_177229_b(field_176264_a) == lvt_8_1_.func_176734_d()) {
               lvt_14_1_ = lvt_16_1_;
            }
            break;
         }

         if (lvt_18_1_.func_177230_c() != Blocks.field_150473_bD && lvt_16_1_ != p_176260_6_) {
            lvt_15_1_[lvt_16_1_] = null;
            lvt_12_1_ = false;
         } else {
            if (lvt_16_1_ == p_176260_6_) {
               lvt_18_1_ = (IBlockState)Objects.firstNonNull(p_176260_7_, lvt_18_1_);
            }

            boolean lvt_19_1_ = !(Boolean)lvt_18_1_.func_177229_b(BlockTripWire.field_176295_N);
            boolean lvt_20_1_ = (Boolean)lvt_18_1_.func_177229_b(BlockTripWire.field_176293_a);
            boolean lvt_21_1_ = (Boolean)lvt_18_1_.func_177229_b(BlockTripWire.field_176290_b);
            lvt_12_1_ &= lvt_21_1_ == lvt_11_1_;
            lvt_13_1_ |= lvt_19_1_ && lvt_20_1_;
            lvt_15_1_[lvt_16_1_] = lvt_18_1_;
            if (lvt_16_1_ == p_176260_6_) {
               p_176260_1_.func_175684_a(p_176260_2_, this, this.func_149738_a(p_176260_1_));
               lvt_12_1_ &= lvt_19_1_;
            }
         }
      }

      lvt_12_1_ &= lvt_14_1_ > 1;
      lvt_13_1_ &= lvt_12_1_;
      IBlockState lvt_16_2_ = this.func_176223_P().func_177226_a(field_176265_M, lvt_12_1_).func_177226_a(field_176263_b, lvt_13_1_);
      if (lvt_14_1_ > 0) {
         lvt_17_2_ = p_176260_2_.func_177967_a(lvt_8_1_, lvt_14_1_);
         EnumFacing lvt_18_2_ = lvt_8_1_.func_176734_d();
         p_176260_1_.func_180501_a(lvt_17_2_, lvt_16_2_.func_177226_a(field_176264_a, lvt_18_2_), 3);
         this.func_176262_b(p_176260_1_, lvt_17_2_, lvt_18_2_);
         this.func_180694_a(p_176260_1_, lvt_17_2_, lvt_12_1_, lvt_13_1_, lvt_9_1_, lvt_10_1_);
      }

      this.func_180694_a(p_176260_1_, p_176260_2_, lvt_12_1_, lvt_13_1_, lvt_9_1_, lvt_10_1_);
      if (!p_176260_4_) {
         p_176260_1_.func_180501_a(p_176260_2_, lvt_16_2_.func_177226_a(field_176264_a, lvt_8_1_), 3);
         if (p_176260_5_) {
            this.func_176262_b(p_176260_1_, p_176260_2_, lvt_8_1_);
         }
      }

      if (lvt_9_1_ != lvt_12_1_) {
         for(int lvt_17_3_ = 1; lvt_17_3_ < lvt_14_1_; ++lvt_17_3_) {
            BlockPos lvt_18_3_ = p_176260_2_.func_177967_a(lvt_8_1_, lvt_17_3_);
            IBlockState lvt_19_2_ = lvt_15_1_[lvt_17_3_];
            if (lvt_19_2_ != null && p_176260_1_.func_180495_p(lvt_18_3_).func_177230_c() != Blocks.field_150350_a) {
               p_176260_1_.func_180501_a(lvt_18_3_, lvt_19_2_.func_177226_a(field_176265_M, lvt_12_1_), 3);
            }
         }
      }

   }

   public void func_180645_a(World p_180645_1_, BlockPos p_180645_2_, IBlockState p_180645_3_, Random p_180645_4_) {
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      this.func_176260_a(p_180650_1_, p_180650_2_, p_180650_3_, false, true, -1, (IBlockState)null);
   }

   private void func_180694_a(World p_180694_1_, BlockPos p_180694_2_, boolean p_180694_3_, boolean p_180694_4_, boolean p_180694_5_, boolean p_180694_6_) {
      if (p_180694_4_ && !p_180694_6_) {
         p_180694_1_.func_72908_a((double)p_180694_2_.func_177958_n() + 0.5D, (double)p_180694_2_.func_177956_o() + 0.1D, (double)p_180694_2_.func_177952_p() + 0.5D, "random.click", 0.4F, 0.6F);
      } else if (!p_180694_4_ && p_180694_6_) {
         p_180694_1_.func_72908_a((double)p_180694_2_.func_177958_n() + 0.5D, (double)p_180694_2_.func_177956_o() + 0.1D, (double)p_180694_2_.func_177952_p() + 0.5D, "random.click", 0.4F, 0.5F);
      } else if (p_180694_3_ && !p_180694_5_) {
         p_180694_1_.func_72908_a((double)p_180694_2_.func_177958_n() + 0.5D, (double)p_180694_2_.func_177956_o() + 0.1D, (double)p_180694_2_.func_177952_p() + 0.5D, "random.click", 0.4F, 0.7F);
      } else if (!p_180694_3_ && p_180694_5_) {
         p_180694_1_.func_72908_a((double)p_180694_2_.func_177958_n() + 0.5D, (double)p_180694_2_.func_177956_o() + 0.1D, (double)p_180694_2_.func_177952_p() + 0.5D, "random.bowhit", 0.4F, 1.2F / (p_180694_1_.field_73012_v.nextFloat() * 0.2F + 0.9F));
      }

   }

   private void func_176262_b(World p_176262_1_, BlockPos p_176262_2_, EnumFacing p_176262_3_) {
      p_176262_1_.func_175685_c(p_176262_2_, this);
      p_176262_1_.func_175685_c(p_176262_2_.func_177972_a(p_176262_3_.func_176734_d()), this);
   }

   private boolean func_176261_e(World p_176261_1_, BlockPos p_176261_2_, IBlockState p_176261_3_) {
      if (!this.func_176196_c(p_176261_1_, p_176261_2_)) {
         this.func_176226_b(p_176261_1_, p_176261_2_, p_176261_3_, 0);
         p_176261_1_.func_175698_g(p_176261_2_);
         return false;
      } else {
         return true;
      }
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      float lvt_3_1_ = 0.1875F;
      switch((EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176264_a)) {
      case EAST:
         this.func_149676_a(0.0F, 0.2F, 0.5F - lvt_3_1_, lvt_3_1_ * 2.0F, 0.8F, 0.5F + lvt_3_1_);
         break;
      case WEST:
         this.func_149676_a(1.0F - lvt_3_1_ * 2.0F, 0.2F, 0.5F - lvt_3_1_, 1.0F, 0.8F, 0.5F + lvt_3_1_);
         break;
      case SOUTH:
         this.func_149676_a(0.5F - lvt_3_1_, 0.2F, 0.0F, 0.5F + lvt_3_1_, 0.8F, lvt_3_1_ * 2.0F);
         break;
      case NORTH:
         this.func_149676_a(0.5F - lvt_3_1_, 0.2F, 1.0F - lvt_3_1_ * 2.0F, 0.5F + lvt_3_1_, 0.8F, 1.0F);
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      boolean lvt_4_1_ = (Boolean)p_180663_3_.func_177229_b(field_176265_M);
      boolean lvt_5_1_ = (Boolean)p_180663_3_.func_177229_b(field_176263_b);
      if (lvt_4_1_ || lvt_5_1_) {
         this.func_176260_a(p_180663_1_, p_180663_2_, p_180663_3_, true, false, -1, (IBlockState)null);
      }

      if (lvt_5_1_) {
         p_180663_1_.func_175685_c(p_180663_2_, this);
         p_180663_1_.func_175685_c(p_180663_2_.func_177972_a(((EnumFacing)p_180663_3_.func_177229_b(field_176264_a)).func_176734_d()), this);
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      return (Boolean)p_180656_3_.func_177229_b(field_176263_b) ? 15 : 0;
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      if (!(Boolean)p_176211_3_.func_177229_b(field_176263_b)) {
         return 0;
      } else {
         return p_176211_3_.func_177229_b(field_176264_a) == p_176211_4_ ? 15 : 0;
      }
   }

   public boolean func_149744_f() {
      return true;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176264_a, EnumFacing.func_176731_b(p_176203_1_ & 3)).func_177226_a(field_176263_b, (p_176203_1_ & 8) > 0).func_177226_a(field_176265_M, (p_176203_1_ & 4) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((EnumFacing)p_176201_1_.func_177229_b(field_176264_a)).func_176736_b();
      if ((Boolean)p_176201_1_.func_177229_b(field_176263_b)) {
         lvt_2_1_ |= 8;
      }

      if ((Boolean)p_176201_1_.func_177229_b(field_176265_M)) {
         lvt_2_1_ |= 4;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176264_a, field_176263_b, field_176265_M, field_176266_N});
   }

   static {
      field_176264_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
      field_176263_b = PropertyBool.func_177716_a("powered");
      field_176265_M = PropertyBool.func_177716_a("attached");
      field_176266_N = PropertyBool.func_177716_a("suspended");
   }
}
