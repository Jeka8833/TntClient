package net.minecraft.block;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static final PropertyBool field_176277_a = PropertyBool.func_177716_a("up");
   public static final PropertyBool field_176273_b = PropertyBool.func_177716_a("north");
   public static final PropertyBool field_176278_M = PropertyBool.func_177716_a("east");
   public static final PropertyBool field_176279_N = PropertyBool.func_177716_a("south");
   public static final PropertyBool field_176280_O = PropertyBool.func_177716_a("west");
   public static final PropertyBool[] field_176274_P;

   public BlockVine() {
      super(Material.field_151582_l);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176277_a, false).func_177226_a(field_176273_b, false).func_177226_a(field_176278_M, false).func_177226_a(field_176279_N, false).func_177226_a(field_176280_O, false));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176277_a, p_176221_2_.func_180495_p(p_176221_3_.func_177984_a()).func_177230_c().func_149637_q());
   }

   public void func_149683_g() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176200_f(World p_176200_1_, BlockPos p_176200_2_) {
      return true;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      float lvt_3_1_ = 0.0625F;
      float lvt_4_1_ = 1.0F;
      float lvt_5_1_ = 1.0F;
      float lvt_6_1_ = 1.0F;
      float lvt_7_1_ = 0.0F;
      float lvt_8_1_ = 0.0F;
      float lvt_9_1_ = 0.0F;
      boolean lvt_10_1_ = false;
      if ((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176280_O)) {
         lvt_7_1_ = Math.max(lvt_7_1_, 0.0625F);
         lvt_4_1_ = 0.0F;
         lvt_5_1_ = 0.0F;
         lvt_8_1_ = 1.0F;
         lvt_6_1_ = 0.0F;
         lvt_9_1_ = 1.0F;
         lvt_10_1_ = true;
      }

      if ((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176278_M)) {
         lvt_4_1_ = Math.min(lvt_4_1_, 0.9375F);
         lvt_7_1_ = 1.0F;
         lvt_5_1_ = 0.0F;
         lvt_8_1_ = 1.0F;
         lvt_6_1_ = 0.0F;
         lvt_9_1_ = 1.0F;
         lvt_10_1_ = true;
      }

      if ((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176273_b)) {
         lvt_9_1_ = Math.max(lvt_9_1_, 0.0625F);
         lvt_6_1_ = 0.0F;
         lvt_4_1_ = 0.0F;
         lvt_7_1_ = 1.0F;
         lvt_5_1_ = 0.0F;
         lvt_8_1_ = 1.0F;
         lvt_10_1_ = true;
      }

      if ((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176279_N)) {
         lvt_6_1_ = Math.min(lvt_6_1_, 0.9375F);
         lvt_9_1_ = 1.0F;
         lvt_4_1_ = 0.0F;
         lvt_7_1_ = 1.0F;
         lvt_5_1_ = 0.0F;
         lvt_8_1_ = 1.0F;
         lvt_10_1_ = true;
      }

      if (!lvt_10_1_ && this.func_150093_a(p_180654_1_.func_180495_p(p_180654_2_.func_177984_a()).func_177230_c())) {
         lvt_5_1_ = Math.min(lvt_5_1_, 0.9375F);
         lvt_8_1_ = 1.0F;
         lvt_4_1_ = 0.0F;
         lvt_7_1_ = 1.0F;
         lvt_6_1_ = 0.0F;
         lvt_9_1_ = 1.0F;
      }

      this.func_149676_a(lvt_4_1_, lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_, lvt_9_1_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      switch(p_176198_3_) {
      case UP:
         return this.func_150093_a(p_176198_1_.func_180495_p(p_176198_2_.func_177984_a()).func_177230_c());
      case NORTH:
      case SOUTH:
      case EAST:
      case WEST:
         return this.func_150093_a(p_176198_1_.func_180495_p(p_176198_2_.func_177972_a(p_176198_3_.func_176734_d())).func_177230_c());
      default:
         return false;
      }
   }

   private boolean func_150093_a(Block p_150093_1_) {
      return p_150093_1_.func_149686_d() && p_150093_1_.field_149764_J.func_76230_c();
   }

   private boolean func_176269_e(World p_176269_1_, BlockPos p_176269_2_, IBlockState p_176269_3_) {
      IBlockState lvt_4_1_ = p_176269_3_;
      Iterator lvt_5_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         PropertyBool lvt_7_1_;
         IBlockState lvt_8_1_;
         do {
            EnumFacing lvt_6_1_;
            do {
               do {
                  if (!lvt_5_1_.hasNext()) {
                     if (func_176268_d(p_176269_3_) == 0) {
                        return false;
                     }

                     if (lvt_4_1_ != p_176269_3_) {
                        p_176269_1_.func_180501_a(p_176269_2_, p_176269_3_, 2);
                     }

                     return true;
                  }

                  lvt_6_1_ = (EnumFacing)lvt_5_1_.next();
                  lvt_7_1_ = func_176267_a(lvt_6_1_);
               } while(!(Boolean)p_176269_3_.func_177229_b(lvt_7_1_));
            } while(this.func_150093_a(p_176269_1_.func_180495_p(p_176269_2_.func_177972_a(lvt_6_1_)).func_177230_c()));

            lvt_8_1_ = p_176269_1_.func_180495_p(p_176269_2_.func_177984_a());
         } while(lvt_8_1_.func_177230_c() == this && (Boolean)lvt_8_1_.func_177229_b(lvt_7_1_));

         p_176269_3_ = p_176269_3_.func_177226_a(lvt_7_1_, false);
      }
   }

   public int func_149635_D() {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return p_180662_1_.func_180494_b(p_180662_2_).func_180625_c(p_180662_2_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (!p_176204_1_.field_72995_K && !this.func_176269_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (!p_180650_1_.field_72995_K) {
         if (p_180650_1_.field_73012_v.nextInt(4) == 0) {
            int lvt_5_1_ = 4;
            int lvt_6_1_ = 5;
            boolean lvt_7_1_ = false;

            label191:
            for(int lvt_8_1_ = -lvt_5_1_; lvt_8_1_ <= lvt_5_1_; ++lvt_8_1_) {
               for(int lvt_9_1_ = -lvt_5_1_; lvt_9_1_ <= lvt_5_1_; ++lvt_9_1_) {
                  for(int lvt_10_1_ = -1; lvt_10_1_ <= 1; ++lvt_10_1_) {
                     if (p_180650_1_.func_180495_p(p_180650_2_.func_177982_a(lvt_8_1_, lvt_10_1_, lvt_9_1_)).func_177230_c() == this) {
                        --lvt_6_1_;
                        if (lvt_6_1_ <= 0) {
                           lvt_7_1_ = true;
                           break label191;
                        }
                     }
                  }
               }
            }

            EnumFacing lvt_8_2_ = EnumFacing.func_176741_a(p_180650_4_);
            BlockPos lvt_9_2_ = p_180650_2_.func_177984_a();
            EnumFacing lvt_12_2_;
            if (lvt_8_2_ == EnumFacing.UP && p_180650_2_.func_177956_o() < 255 && p_180650_1_.func_175623_d(lvt_9_2_)) {
               if (!lvt_7_1_) {
                  IBlockState lvt_10_2_ = p_180650_3_;
                  Iterator lvt_11_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(true) {
                     do {
                        if (!lvt_11_1_.hasNext()) {
                           if ((Boolean)lvt_10_2_.func_177229_b(field_176273_b) || (Boolean)lvt_10_2_.func_177229_b(field_176278_M) || (Boolean)lvt_10_2_.func_177229_b(field_176279_N) || (Boolean)lvt_10_2_.func_177229_b(field_176280_O)) {
                              p_180650_1_.func_180501_a(lvt_9_2_, lvt_10_2_, 2);
                           }

                           return;
                        }

                        lvt_12_2_ = (EnumFacing)lvt_11_1_.next();
                     } while(!p_180650_4_.nextBoolean() && this.func_150093_a(p_180650_1_.func_180495_p(lvt_9_2_.func_177972_a(lvt_12_2_)).func_177230_c()));

                     lvt_10_2_ = lvt_10_2_.func_177226_a(func_176267_a(lvt_12_2_), false);
                  }
               }
            } else {
               BlockPos lvt_10_3_;
               if (lvt_8_2_.func_176740_k().func_176722_c() && !(Boolean)p_180650_3_.func_177229_b(func_176267_a(lvt_8_2_))) {
                  if (!lvt_7_1_) {
                     lvt_10_3_ = p_180650_2_.func_177972_a(lvt_8_2_);
                     Block lvt_11_2_ = p_180650_1_.func_180495_p(lvt_10_3_).func_177230_c();
                     if (lvt_11_2_.field_149764_J == Material.field_151579_a) {
                        lvt_12_2_ = lvt_8_2_.func_176746_e();
                        EnumFacing lvt_13_1_ = lvt_8_2_.func_176735_f();
                        boolean lvt_14_1_ = (Boolean)p_180650_3_.func_177229_b(func_176267_a(lvt_12_2_));
                        boolean lvt_15_1_ = (Boolean)p_180650_3_.func_177229_b(func_176267_a(lvt_13_1_));
                        BlockPos lvt_16_1_ = lvt_10_3_.func_177972_a(lvt_12_2_);
                        BlockPos lvt_17_1_ = lvt_10_3_.func_177972_a(lvt_13_1_);
                        if (lvt_14_1_ && this.func_150093_a(p_180650_1_.func_180495_p(lvt_16_1_).func_177230_c())) {
                           p_180650_1_.func_180501_a(lvt_10_3_, this.func_176223_P().func_177226_a(func_176267_a(lvt_12_2_), true), 2);
                        } else if (lvt_15_1_ && this.func_150093_a(p_180650_1_.func_180495_p(lvt_17_1_).func_177230_c())) {
                           p_180650_1_.func_180501_a(lvt_10_3_, this.func_176223_P().func_177226_a(func_176267_a(lvt_13_1_), true), 2);
                        } else if (lvt_14_1_ && p_180650_1_.func_175623_d(lvt_16_1_) && this.func_150093_a(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(lvt_12_2_)).func_177230_c())) {
                           p_180650_1_.func_180501_a(lvt_16_1_, this.func_176223_P().func_177226_a(func_176267_a(lvt_8_2_.func_176734_d()), true), 2);
                        } else if (lvt_15_1_ && p_180650_1_.func_175623_d(lvt_17_1_) && this.func_150093_a(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(lvt_13_1_)).func_177230_c())) {
                           p_180650_1_.func_180501_a(lvt_17_1_, this.func_176223_P().func_177226_a(func_176267_a(lvt_8_2_.func_176734_d()), true), 2);
                        } else if (this.func_150093_a(p_180650_1_.func_180495_p(lvt_10_3_.func_177984_a()).func_177230_c())) {
                           p_180650_1_.func_180501_a(lvt_10_3_, this.func_176223_P(), 2);
                        }
                     } else if (lvt_11_2_.field_149764_J.func_76218_k() && lvt_11_2_.func_149686_d()) {
                        p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(func_176267_a(lvt_8_2_), true), 2);
                     }

                  }
               } else {
                  if (p_180650_2_.func_177956_o() > 1) {
                     lvt_10_3_ = p_180650_2_.func_177977_b();
                     IBlockState lvt_11_3_ = p_180650_1_.func_180495_p(lvt_10_3_);
                     Block lvt_12_3_ = lvt_11_3_.func_177230_c();
                     IBlockState lvt_13_3_;
                     Iterator lvt_14_3_;
                     EnumFacing lvt_15_2_;
                     if (lvt_12_3_.field_149764_J == Material.field_151579_a) {
                        lvt_13_3_ = p_180650_3_;
                        lvt_14_3_ = EnumFacing.Plane.HORIZONTAL.iterator();

                        while(lvt_14_3_.hasNext()) {
                           lvt_15_2_ = (EnumFacing)lvt_14_3_.next();
                           if (p_180650_4_.nextBoolean()) {
                              lvt_13_3_ = lvt_13_3_.func_177226_a(func_176267_a(lvt_15_2_), false);
                           }
                        }

                        if ((Boolean)lvt_13_3_.func_177229_b(field_176273_b) || (Boolean)lvt_13_3_.func_177229_b(field_176278_M) || (Boolean)lvt_13_3_.func_177229_b(field_176279_N) || (Boolean)lvt_13_3_.func_177229_b(field_176280_O)) {
                           p_180650_1_.func_180501_a(lvt_10_3_, lvt_13_3_, 2);
                        }
                     } else if (lvt_12_3_ == this) {
                        lvt_13_3_ = lvt_11_3_;
                        lvt_14_3_ = EnumFacing.Plane.HORIZONTAL.iterator();

                        while(lvt_14_3_.hasNext()) {
                           lvt_15_2_ = (EnumFacing)lvt_14_3_.next();
                           PropertyBool lvt_16_2_ = func_176267_a(lvt_15_2_);
                           if (p_180650_4_.nextBoolean() && (Boolean)p_180650_3_.func_177229_b(lvt_16_2_)) {
                              lvt_13_3_ = lvt_13_3_.func_177226_a(lvt_16_2_, true);
                           }
                        }

                        if ((Boolean)lvt_13_3_.func_177229_b(field_176273_b) || (Boolean)lvt_13_3_.func_177229_b(field_176278_M) || (Boolean)lvt_13_3_.func_177229_b(field_176279_N) || (Boolean)lvt_13_3_.func_177229_b(field_176280_O)) {
                           p_180650_1_.func_180501_a(lvt_10_3_, lvt_13_3_, 2);
                        }
                     }
                  }

               }
            }
         }
      }
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState lvt_9_1_ = this.func_176223_P().func_177226_a(field_176277_a, false).func_177226_a(field_176273_b, false).func_177226_a(field_176278_M, false).func_177226_a(field_176279_N, false).func_177226_a(field_176280_O, false);
      return p_180642_3_.func_176740_k().func_176722_c() ? lvt_9_1_.func_177226_a(func_176267_a(p_180642_3_.func_176734_d()), true) : lvt_9_1_;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      if (!p_180657_1_.field_72995_K && p_180657_2_.func_71045_bC() != null && p_180657_2_.func_71045_bC().func_77973_b() == Items.field_151097_aZ) {
         p_180657_2_.func_71029_a(StatList.field_75934_C[Block.func_149682_b(this)]);
         func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Blocks.field_150395_bd, 1, 0));
      } else {
         super.func_180657_a(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176279_N, (p_176203_1_ & 1) > 0).func_177226_a(field_176280_O, (p_176203_1_ & 2) > 0).func_177226_a(field_176273_b, (p_176203_1_ & 4) > 0).func_177226_a(field_176278_M, (p_176203_1_ & 8) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      if ((Boolean)p_176201_1_.func_177229_b(field_176279_N)) {
         lvt_2_1_ |= 1;
      }

      if ((Boolean)p_176201_1_.func_177229_b(field_176280_O)) {
         lvt_2_1_ |= 2;
      }

      if ((Boolean)p_176201_1_.func_177229_b(field_176273_b)) {
         lvt_2_1_ |= 4;
      }

      if ((Boolean)p_176201_1_.func_177229_b(field_176278_M)) {
         lvt_2_1_ |= 8;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176277_a, field_176273_b, field_176278_M, field_176279_N, field_176280_O});
   }

   public static PropertyBool func_176267_a(EnumFacing p_176267_0_) {
      switch(p_176267_0_) {
      case UP:
         return field_176277_a;
      case NORTH:
         return field_176273_b;
      case SOUTH:
         return field_176279_N;
      case EAST:
         return field_176278_M;
      case WEST:
         return field_176280_O;
      default:
         throw new IllegalArgumentException(p_176267_0_ + " is an invalid choice");
      }
   }

   public static int func_176268_d(IBlockState p_176268_0_) {
      int lvt_1_1_ = 0;
      PropertyBool[] lvt_2_1_ = field_176274_P;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         PropertyBool lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if ((Boolean)p_176268_0_.func_177229_b(lvt_5_1_)) {
            ++lvt_1_1_;
         }
      }

      return lvt_1_1_;
   }

   static {
      field_176274_P = new PropertyBool[]{field_176277_a, field_176273_b, field_176279_N, field_176280_O, field_176278_M};
   }
}
