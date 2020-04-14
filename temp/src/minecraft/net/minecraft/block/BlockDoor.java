package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor extends Block {
   public static final PropertyDirection field_176520_a;
   public static final PropertyBool field_176519_b;
   public static final PropertyEnum<BlockDoor.EnumHingePosition> field_176521_M;
   public static final PropertyBool field_176522_N;
   public static final PropertyEnum<BlockDoor.EnumDoorHalf> field_176523_O;

   protected BlockDoor(Material p_i45402_1_) {
      super(p_i45402_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176520_a, EnumFacing.NORTH).func_177226_a(field_176519_b, false).func_177226_a(field_176521_M, BlockDoor.EnumHingePosition.LEFT).func_177226_a(field_176522_N, false).func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.LOWER));
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a((this.func_149739_a() + ".name").replaceAll("tile", "item"));
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return func_176516_g(func_176515_e(p_176205_1_, p_176205_2_));
   }

   public boolean func_149686_d() {
      return false;
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      this.func_180654_a(p_180646_1_, p_180646_2_);
      return super.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_150011_b(func_176515_e(p_180654_1_, p_180654_2_));
   }

   private void func_150011_b(int p_150011_1_) {
      float lvt_2_1_ = 0.1875F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      EnumFacing lvt_3_1_ = func_176511_f(p_150011_1_);
      boolean lvt_4_1_ = func_176516_g(p_150011_1_);
      boolean lvt_5_1_ = func_176513_j(p_150011_1_);
      if (lvt_4_1_) {
         if (lvt_3_1_ == EnumFacing.EAST) {
            if (!lvt_5_1_) {
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lvt_2_1_);
            } else {
               this.func_149676_a(0.0F, 0.0F, 1.0F - lvt_2_1_, 1.0F, 1.0F, 1.0F);
            }
         } else if (lvt_3_1_ == EnumFacing.SOUTH) {
            if (!lvt_5_1_) {
               this.func_149676_a(1.0F - lvt_2_1_, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.func_149676_a(0.0F, 0.0F, 0.0F, lvt_2_1_, 1.0F, 1.0F);
            }
         } else if (lvt_3_1_ == EnumFacing.WEST) {
            if (!lvt_5_1_) {
               this.func_149676_a(0.0F, 0.0F, 1.0F - lvt_2_1_, 1.0F, 1.0F, 1.0F);
            } else {
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lvt_2_1_);
            }
         } else if (lvt_3_1_ == EnumFacing.NORTH) {
            if (!lvt_5_1_) {
               this.func_149676_a(0.0F, 0.0F, 0.0F, lvt_2_1_, 1.0F, 1.0F);
            } else {
               this.func_149676_a(1.0F - lvt_2_1_, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      } else if (lvt_3_1_ == EnumFacing.EAST) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, lvt_2_1_, 1.0F, 1.0F);
      } else if (lvt_3_1_ == EnumFacing.SOUTH) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lvt_2_1_);
      } else if (lvt_3_1_ == EnumFacing.WEST) {
         this.func_149676_a(1.0F - lvt_2_1_, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else if (lvt_3_1_ == EnumFacing.NORTH) {
         this.func_149676_a(0.0F, 0.0F, 1.0F - lvt_2_1_, 1.0F, 1.0F, 1.0F);
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (this.field_149764_J == Material.field_151573_f) {
         return true;
      } else {
         BlockPos lvt_9_1_ = p_180639_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER ? p_180639_2_ : p_180639_2_.func_177977_b();
         IBlockState lvt_10_1_ = p_180639_2_.equals(lvt_9_1_) ? p_180639_3_ : p_180639_1_.func_180495_p(lvt_9_1_);
         if (lvt_10_1_.func_177230_c() != this) {
            return false;
         } else {
            p_180639_3_ = lvt_10_1_.func_177231_a(field_176519_b);
            p_180639_1_.func_180501_a(lvt_9_1_, p_180639_3_, 2);
            p_180639_1_.func_175704_b(lvt_9_1_, p_180639_2_);
            p_180639_1_.func_180498_a(p_180639_4_, (Boolean)p_180639_3_.func_177229_b(field_176519_b) ? 1003 : 1006, p_180639_2_, 0);
            return true;
         }
      }
   }

   public void func_176512_a(World p_176512_1_, BlockPos p_176512_2_, boolean p_176512_3_) {
      IBlockState lvt_4_1_ = p_176512_1_.func_180495_p(p_176512_2_);
      if (lvt_4_1_.func_177230_c() == this) {
         BlockPos lvt_5_1_ = lvt_4_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER ? p_176512_2_ : p_176512_2_.func_177977_b();
         IBlockState lvt_6_1_ = p_176512_2_ == lvt_5_1_ ? lvt_4_1_ : p_176512_1_.func_180495_p(lvt_5_1_);
         if (lvt_6_1_.func_177230_c() == this && (Boolean)lvt_6_1_.func_177229_b(field_176519_b) != p_176512_3_) {
            p_176512_1_.func_180501_a(lvt_5_1_, lvt_6_1_.func_177226_a(field_176519_b, p_176512_3_), 2);
            p_176512_1_.func_175704_b(lvt_5_1_, p_176512_2_);
            p_176512_1_.func_180498_a((EntityPlayer)null, p_176512_3_ ? 1003 : 1006, p_176512_2_, 0);
         }

      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (p_176204_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER) {
         BlockPos lvt_5_1_ = p_176204_2_.func_177977_b();
         IBlockState lvt_6_1_ = p_176204_1_.func_180495_p(lvt_5_1_);
         if (lvt_6_1_.func_177230_c() != this) {
            p_176204_1_.func_175698_g(p_176204_2_);
         } else if (p_176204_4_ != this) {
            this.func_176204_a(p_176204_1_, lvt_5_1_, lvt_6_1_, p_176204_4_);
         }
      } else {
         boolean lvt_5_2_ = false;
         BlockPos lvt_6_2_ = p_176204_2_.func_177984_a();
         IBlockState lvt_7_1_ = p_176204_1_.func_180495_p(lvt_6_2_);
         if (lvt_7_1_.func_177230_c() != this) {
            p_176204_1_.func_175698_g(p_176204_2_);
            lvt_5_2_ = true;
         }

         if (!World.func_175683_a(p_176204_1_, p_176204_2_.func_177977_b())) {
            p_176204_1_.func_175698_g(p_176204_2_);
            lvt_5_2_ = true;
            if (lvt_7_1_.func_177230_c() == this) {
               p_176204_1_.func_175698_g(lvt_6_2_);
            }
         }

         if (lvt_5_2_) {
            if (!p_176204_1_.field_72995_K) {
               this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            }
         } else {
            boolean lvt_8_1_ = p_176204_1_.func_175640_z(p_176204_2_) || p_176204_1_.func_175640_z(lvt_6_2_);
            if ((lvt_8_1_ || p_176204_4_.func_149744_f()) && p_176204_4_ != this && lvt_8_1_ != (Boolean)lvt_7_1_.func_177229_b(field_176522_N)) {
               p_176204_1_.func_180501_a(lvt_6_2_, lvt_7_1_.func_177226_a(field_176522_N, lvt_8_1_), 2);
               if (lvt_8_1_ != (Boolean)p_176204_3_.func_177229_b(field_176519_b)) {
                  p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176519_b, lvt_8_1_), 2);
                  p_176204_1_.func_175704_b(p_176204_2_, p_176204_2_);
                  p_176204_1_.func_180498_a((EntityPlayer)null, lvt_8_1_ ? 1003 : 1006, p_176204_2_, 0);
               }
            }
         }
      }

   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return p_180660_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER ? null : this.func_176509_j();
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      this.func_180654_a(p_180636_1_, p_180636_2_);
      return super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      if (p_176196_2_.func_177956_o() >= 255) {
         return false;
      } else {
         return World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b()) && super.func_176196_c(p_176196_1_, p_176196_2_) && super.func_176196_c(p_176196_1_, p_176196_2_.func_177984_a());
      }
   }

   public int func_149656_h() {
      return 1;
   }

   public static int func_176515_e(IBlockAccess p_176515_0_, BlockPos p_176515_1_) {
      IBlockState lvt_2_1_ = p_176515_0_.func_180495_p(p_176515_1_);
      int lvt_3_1_ = lvt_2_1_.func_177230_c().func_176201_c(lvt_2_1_);
      boolean lvt_4_1_ = func_176518_i(lvt_3_1_);
      IBlockState lvt_5_1_ = p_176515_0_.func_180495_p(p_176515_1_.func_177977_b());
      int lvt_6_1_ = lvt_5_1_.func_177230_c().func_176201_c(lvt_5_1_);
      int lvt_7_1_ = lvt_4_1_ ? lvt_6_1_ : lvt_3_1_;
      IBlockState lvt_8_1_ = p_176515_0_.func_180495_p(p_176515_1_.func_177984_a());
      int lvt_9_1_ = lvt_8_1_.func_177230_c().func_176201_c(lvt_8_1_);
      int lvt_10_1_ = lvt_4_1_ ? lvt_3_1_ : lvt_9_1_;
      boolean lvt_11_1_ = (lvt_10_1_ & 1) != 0;
      boolean lvt_12_1_ = (lvt_10_1_ & 2) != 0;
      return func_176510_b(lvt_7_1_) | (lvt_4_1_ ? 8 : 0) | (lvt_11_1_ ? 16 : 0) | (lvt_12_1_ ? 32 : 0);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return this.func_176509_j();
   }

   private Item func_176509_j() {
      if (this == Blocks.field_150454_av) {
         return Items.field_151139_aw;
      } else if (this == Blocks.field_180414_ap) {
         return Items.field_179569_ar;
      } else if (this == Blocks.field_180412_aq) {
         return Items.field_179568_as;
      } else if (this == Blocks.field_180411_ar) {
         return Items.field_179567_at;
      } else if (this == Blocks.field_180410_as) {
         return Items.field_179572_au;
      } else {
         return this == Blocks.field_180409_at ? Items.field_179571_av : Items.field_179570_aq;
      }
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      BlockPos lvt_5_1_ = p_176208_2_.func_177977_b();
      if (p_176208_4_.field_71075_bZ.field_75098_d && p_176208_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER && p_176208_1_.func_180495_p(lvt_5_1_).func_177230_c() == this) {
         p_176208_1_.func_175698_g(lvt_5_1_);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      IBlockState lvt_4_1_;
      if (p_176221_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER) {
         lvt_4_1_ = p_176221_2_.func_180495_p(p_176221_3_.func_177984_a());
         if (lvt_4_1_.func_177230_c() == this) {
            p_176221_1_ = p_176221_1_.func_177226_a(field_176521_M, lvt_4_1_.func_177229_b(field_176521_M)).func_177226_a(field_176522_N, lvt_4_1_.func_177229_b(field_176522_N));
         }
      } else {
         lvt_4_1_ = p_176221_2_.func_180495_p(p_176221_3_.func_177977_b());
         if (lvt_4_1_.func_177230_c() == this) {
            p_176221_1_ = p_176221_1_.func_177226_a(field_176520_a, lvt_4_1_.func_177229_b(field_176520_a)).func_177226_a(field_176519_b, lvt_4_1_.func_177229_b(field_176519_b));
         }
      }

      return p_176221_1_;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return (p_176203_1_ & 8) > 0 ? this.func_176223_P().func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.UPPER).func_177226_a(field_176521_M, (p_176203_1_ & 1) > 0 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT).func_177226_a(field_176522_N, (p_176203_1_ & 2) > 0) : this.func_176223_P().func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.LOWER).func_177226_a(field_176520_a, EnumFacing.func_176731_b(p_176203_1_ & 3).func_176735_f()).func_177226_a(field_176519_b, (p_176203_1_ & 4) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_;
      if (p_176201_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER) {
         lvt_2_1_ = lvt_2_1_ | 8;
         if (p_176201_1_.func_177229_b(field_176521_M) == BlockDoor.EnumHingePosition.RIGHT) {
            lvt_2_1_ |= 1;
         }

         if ((Boolean)p_176201_1_.func_177229_b(field_176522_N)) {
            lvt_2_1_ |= 2;
         }
      } else {
         lvt_2_1_ = lvt_2_1_ | ((EnumFacing)p_176201_1_.func_177229_b(field_176520_a)).func_176746_e().func_176736_b();
         if ((Boolean)p_176201_1_.func_177229_b(field_176519_b)) {
            lvt_2_1_ |= 4;
         }
      }

      return lvt_2_1_;
   }

   protected static int func_176510_b(int p_176510_0_) {
      return p_176510_0_ & 7;
   }

   public static boolean func_176514_f(IBlockAccess p_176514_0_, BlockPos p_176514_1_) {
      return func_176516_g(func_176515_e(p_176514_0_, p_176514_1_));
   }

   public static EnumFacing func_176517_h(IBlockAccess p_176517_0_, BlockPos p_176517_1_) {
      return func_176511_f(func_176515_e(p_176517_0_, p_176517_1_));
   }

   public static EnumFacing func_176511_f(int p_176511_0_) {
      return EnumFacing.func_176731_b(p_176511_0_ & 3).func_176735_f();
   }

   protected static boolean func_176516_g(int p_176516_0_) {
      return (p_176516_0_ & 4) != 0;
   }

   protected static boolean func_176518_i(int p_176518_0_) {
      return (p_176518_0_ & 8) != 0;
   }

   protected static boolean func_176513_j(int p_176513_0_) {
      return (p_176513_0_ & 16) != 0;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176523_O, field_176520_a, field_176519_b, field_176521_M, field_176522_N});
   }

   static {
      field_176520_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
      field_176519_b = PropertyBool.func_177716_a("open");
      field_176521_M = PropertyEnum.func_177709_a("hinge", BlockDoor.EnumHingePosition.class);
      field_176522_N = PropertyBool.func_177716_a("powered");
      field_176523_O = PropertyEnum.func_177709_a("half", BlockDoor.EnumDoorHalf.class);
   }

   public static enum EnumHingePosition implements IStringSerializable {
      LEFT,
      RIGHT;

      public String toString() {
         return this.func_176610_l();
      }

      public String func_176610_l() {
         return this == LEFT ? "left" : "right";
      }
   }

   public static enum EnumDoorHalf implements IStringSerializable {
      UPPER,
      LOWER;

      public String toString() {
         return this.func_176610_l();
      }

      public String func_176610_l() {
         return this == UPPER ? "upper" : "lower";
      }
   }
}
