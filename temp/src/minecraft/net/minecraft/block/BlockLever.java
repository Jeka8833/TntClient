package net.minecraft.block;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLever extends Block {
   public static final PropertyEnum<BlockLever.EnumOrientation> field_176360_a = PropertyEnum.func_177709_a("facing", BlockLever.EnumOrientation.class);
   public static final PropertyBool field_176359_b = PropertyBool.func_177716_a("powered");

   protected BlockLever() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176360_a, BlockLever.EnumOrientation.NORTH).func_177226_a(field_176359_b, false));
      this.func_149647_a(CreativeTabs.field_78028_d);
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
      return func_181090_a(p_176198_1_, p_176198_2_, p_176198_3_.func_176734_d());
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      EnumFacing[] lvt_3_1_ = EnumFacing.values();
      int lvt_4_1_ = lvt_3_1_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         EnumFacing lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         if (func_181090_a(p_176196_1_, p_176196_2_, lvt_6_1_)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean func_181090_a(World p_181090_0_, BlockPos p_181090_1_, EnumFacing p_181090_2_) {
      return BlockButton.func_181088_a(p_181090_0_, p_181090_1_, p_181090_2_);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState lvt_9_1_ = this.func_176223_P().func_177226_a(field_176359_b, false);
      if (func_181090_a(p_180642_1_, p_180642_2_, p_180642_3_.func_176734_d())) {
         return lvt_9_1_.func_177226_a(field_176360_a, BlockLever.EnumOrientation.func_176856_a(p_180642_3_, p_180642_8_.func_174811_aO()));
      } else {
         Iterator lvt_10_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing lvt_11_1_;
         do {
            if (!lvt_10_1_.hasNext()) {
               if (World.func_175683_a(p_180642_1_, p_180642_2_.func_177977_b())) {
                  return lvt_9_1_.func_177226_a(field_176360_a, BlockLever.EnumOrientation.func_176856_a(EnumFacing.UP, p_180642_8_.func_174811_aO()));
               }

               return lvt_9_1_;
            }

            lvt_11_1_ = (EnumFacing)lvt_10_1_.next();
         } while(lvt_11_1_ == p_180642_3_ || !func_181090_a(p_180642_1_, p_180642_2_, lvt_11_1_.func_176734_d()));

         return lvt_9_1_.func_177226_a(field_176360_a, BlockLever.EnumOrientation.func_176856_a(lvt_11_1_, p_180642_8_.func_174811_aO()));
      }
   }

   public static int func_176357_a(EnumFacing p_176357_0_) {
      switch(p_176357_0_) {
      case DOWN:
         return 0;
      case UP:
         return 5;
      case NORTH:
         return 4;
      case SOUTH:
         return 3;
      case WEST:
         return 2;
      case EAST:
         return 1;
      default:
         return -1;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (this.func_181091_e(p_176204_1_, p_176204_2_, p_176204_3_) && !func_181090_a(p_176204_1_, p_176204_2_, ((BlockLever.EnumOrientation)p_176204_3_.func_177229_b(field_176360_a)).func_176852_c().func_176734_d())) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   private boolean func_181091_e(World p_181091_1_, BlockPos p_181091_2_, IBlockState p_181091_3_) {
      if (this.func_176196_c(p_181091_1_, p_181091_2_)) {
         return true;
      } else {
         this.func_176226_b(p_181091_1_, p_181091_2_, p_181091_3_, 0);
         p_181091_1_.func_175698_g(p_181091_2_);
         return false;
      }
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      float lvt_3_1_ = 0.1875F;
      switch((BlockLever.EnumOrientation)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176360_a)) {
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
         break;
      case UP_Z:
      case UP_X:
         lvt_3_1_ = 0.25F;
         this.func_149676_a(0.5F - lvt_3_1_, 0.0F, 0.5F - lvt_3_1_, 0.5F + lvt_3_1_, 0.6F, 0.5F + lvt_3_1_);
         break;
      case DOWN_X:
      case DOWN_Z:
         lvt_3_1_ = 0.25F;
         this.func_149676_a(0.5F - lvt_3_1_, 0.4F, 0.5F - lvt_3_1_, 0.5F + lvt_3_1_, 1.0F, 0.5F + lvt_3_1_);
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         p_180639_3_ = p_180639_3_.func_177231_a(field_176359_b);
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 3);
         p_180639_1_.func_72908_a((double)p_180639_2_.func_177958_n() + 0.5D, (double)p_180639_2_.func_177956_o() + 0.5D, (double)p_180639_2_.func_177952_p() + 0.5D, "random.click", 0.3F, (Boolean)p_180639_3_.func_177229_b(field_176359_b) ? 0.6F : 0.5F);
         p_180639_1_.func_175685_c(p_180639_2_, this);
         EnumFacing lvt_9_1_ = ((BlockLever.EnumOrientation)p_180639_3_.func_177229_b(field_176360_a)).func_176852_c();
         p_180639_1_.func_175685_c(p_180639_2_.func_177972_a(lvt_9_1_.func_176734_d()), this);
         return true;
      }
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if ((Boolean)p_180663_3_.func_177229_b(field_176359_b)) {
         p_180663_1_.func_175685_c(p_180663_2_, this);
         EnumFacing lvt_4_1_ = ((BlockLever.EnumOrientation)p_180663_3_.func_177229_b(field_176360_a)).func_176852_c();
         p_180663_1_.func_175685_c(p_180663_2_.func_177972_a(lvt_4_1_.func_176734_d()), this);
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      return (Boolean)p_180656_3_.func_177229_b(field_176359_b) ? 15 : 0;
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      if (!(Boolean)p_176211_3_.func_177229_b(field_176359_b)) {
         return 0;
      } else {
         return ((BlockLever.EnumOrientation)p_176211_3_.func_177229_b(field_176360_a)).func_176852_c() == p_176211_4_ ? 15 : 0;
      }
   }

   public boolean func_149744_f() {
      return true;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176360_a, BlockLever.EnumOrientation.func_176853_a(p_176203_1_ & 7)).func_177226_a(field_176359_b, (p_176203_1_ & 8) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((BlockLever.EnumOrientation)p_176201_1_.func_177229_b(field_176360_a)).func_176855_a();
      if ((Boolean)p_176201_1_.func_177229_b(field_176359_b)) {
         lvt_2_1_ |= 8;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176360_a, field_176359_b});
   }

   public static enum EnumOrientation implements IStringSerializable {
      DOWN_X(0, "down_x", EnumFacing.DOWN),
      EAST(1, "east", EnumFacing.EAST),
      WEST(2, "west", EnumFacing.WEST),
      SOUTH(3, "south", EnumFacing.SOUTH),
      NORTH(4, "north", EnumFacing.NORTH),
      UP_Z(5, "up_z", EnumFacing.UP),
      UP_X(6, "up_x", EnumFacing.UP),
      DOWN_Z(7, "down_z", EnumFacing.DOWN);

      private static final BlockLever.EnumOrientation[] field_176869_i = new BlockLever.EnumOrientation[values().length];
      private final int field_176866_j;
      private final String field_176867_k;
      private final EnumFacing field_176864_l;

      private EnumOrientation(int p_i45709_3_, String p_i45709_4_, EnumFacing p_i45709_5_) {
         this.field_176866_j = p_i45709_3_;
         this.field_176867_k = p_i45709_4_;
         this.field_176864_l = p_i45709_5_;
      }

      public int func_176855_a() {
         return this.field_176866_j;
      }

      public EnumFacing func_176852_c() {
         return this.field_176864_l;
      }

      public String toString() {
         return this.field_176867_k;
      }

      public static BlockLever.EnumOrientation func_176853_a(int p_176853_0_) {
         if (p_176853_0_ < 0 || p_176853_0_ >= field_176869_i.length) {
            p_176853_0_ = 0;
         }

         return field_176869_i[p_176853_0_];
      }

      public static BlockLever.EnumOrientation func_176856_a(EnumFacing p_176856_0_, EnumFacing p_176856_1_) {
         switch(p_176856_0_) {
         case DOWN:
            switch(p_176856_1_.func_176740_k()) {
            case X:
               return DOWN_X;
            case Z:
               return DOWN_Z;
            default:
               throw new IllegalArgumentException("Invalid entityFacing " + p_176856_1_ + " for facing " + p_176856_0_);
            }
         case UP:
            switch(p_176856_1_.func_176740_k()) {
            case X:
               return UP_X;
            case Z:
               return UP_Z;
            default:
               throw new IllegalArgumentException("Invalid entityFacing " + p_176856_1_ + " for facing " + p_176856_0_);
            }
         case NORTH:
            return NORTH;
         case SOUTH:
            return SOUTH;
         case WEST:
            return WEST;
         case EAST:
            return EAST;
         default:
            throw new IllegalArgumentException("Invalid facing: " + p_176856_0_);
         }
      }

      public String func_176610_l() {
         return this.field_176867_k;
      }

      static {
         BlockLever.EnumOrientation[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            BlockLever.EnumOrientation lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_176869_i[lvt_3_1_.func_176855_a()] = lvt_3_1_;
         }

      }
   }
}
