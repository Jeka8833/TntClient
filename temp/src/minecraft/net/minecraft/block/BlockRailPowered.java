package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRailPowered extends BlockRailBase {
   public static final PropertyEnum<BlockRailBase.EnumRailDirection> field_176568_b = PropertyEnum.func_177708_a("shape", BlockRailBase.EnumRailDirection.class, new Predicate<BlockRailBase.EnumRailDirection>() {
      public boolean apply(BlockRailBase.EnumRailDirection p_apply_1_) {
         return p_apply_1_ != BlockRailBase.EnumRailDirection.NORTH_EAST && p_apply_1_ != BlockRailBase.EnumRailDirection.NORTH_WEST && p_apply_1_ != BlockRailBase.EnumRailDirection.SOUTH_EAST && p_apply_1_ != BlockRailBase.EnumRailDirection.SOUTH_WEST;
      }
   });
   public static final PropertyBool field_176569_M = PropertyBool.func_177716_a("powered");

   protected BlockRailPowered() {
      super(true);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176568_b, BlockRailBase.EnumRailDirection.NORTH_SOUTH).func_177226_a(field_176569_M, false));
   }

   protected boolean func_176566_a(World p_176566_1_, BlockPos p_176566_2_, IBlockState p_176566_3_, boolean p_176566_4_, int p_176566_5_) {
      if (p_176566_5_ >= 8) {
         return false;
      } else {
         int lvt_6_1_ = p_176566_2_.func_177958_n();
         int lvt_7_1_ = p_176566_2_.func_177956_o();
         int lvt_8_1_ = p_176566_2_.func_177952_p();
         boolean lvt_9_1_ = true;
         BlockRailBase.EnumRailDirection lvt_10_1_ = (BlockRailBase.EnumRailDirection)p_176566_3_.func_177229_b(field_176568_b);
         switch(lvt_10_1_) {
         case NORTH_SOUTH:
            if (p_176566_4_) {
               ++lvt_8_1_;
            } else {
               --lvt_8_1_;
            }
            break;
         case EAST_WEST:
            if (p_176566_4_) {
               --lvt_6_1_;
            } else {
               ++lvt_6_1_;
            }
            break;
         case ASCENDING_EAST:
            if (p_176566_4_) {
               --lvt_6_1_;
            } else {
               ++lvt_6_1_;
               ++lvt_7_1_;
               lvt_9_1_ = false;
            }

            lvt_10_1_ = BlockRailBase.EnumRailDirection.EAST_WEST;
            break;
         case ASCENDING_WEST:
            if (p_176566_4_) {
               --lvt_6_1_;
               ++lvt_7_1_;
               lvt_9_1_ = false;
            } else {
               ++lvt_6_1_;
            }

            lvt_10_1_ = BlockRailBase.EnumRailDirection.EAST_WEST;
            break;
         case ASCENDING_NORTH:
            if (p_176566_4_) {
               ++lvt_8_1_;
            } else {
               --lvt_8_1_;
               ++lvt_7_1_;
               lvt_9_1_ = false;
            }

            lvt_10_1_ = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            break;
         case ASCENDING_SOUTH:
            if (p_176566_4_) {
               ++lvt_8_1_;
               ++lvt_7_1_;
               lvt_9_1_ = false;
            } else {
               --lvt_8_1_;
            }

            lvt_10_1_ = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if (this.func_176567_a(p_176566_1_, new BlockPos(lvt_6_1_, lvt_7_1_, lvt_8_1_), p_176566_4_, p_176566_5_, lvt_10_1_)) {
            return true;
         } else {
            return lvt_9_1_ && this.func_176567_a(p_176566_1_, new BlockPos(lvt_6_1_, lvt_7_1_ - 1, lvt_8_1_), p_176566_4_, p_176566_5_, lvt_10_1_);
         }
      }
   }

   protected boolean func_176567_a(World p_176567_1_, BlockPos p_176567_2_, boolean p_176567_3_, int p_176567_4_, BlockRailBase.EnumRailDirection p_176567_5_) {
      IBlockState lvt_6_1_ = p_176567_1_.func_180495_p(p_176567_2_);
      if (lvt_6_1_.func_177230_c() != this) {
         return false;
      } else {
         BlockRailBase.EnumRailDirection lvt_7_1_ = (BlockRailBase.EnumRailDirection)lvt_6_1_.func_177229_b(field_176568_b);
         if (p_176567_5_ == BlockRailBase.EnumRailDirection.EAST_WEST && (lvt_7_1_ == BlockRailBase.EnumRailDirection.NORTH_SOUTH || lvt_7_1_ == BlockRailBase.EnumRailDirection.ASCENDING_NORTH || lvt_7_1_ == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH)) {
            return false;
         } else if (p_176567_5_ == BlockRailBase.EnumRailDirection.NORTH_SOUTH && (lvt_7_1_ == BlockRailBase.EnumRailDirection.EAST_WEST || lvt_7_1_ == BlockRailBase.EnumRailDirection.ASCENDING_EAST || lvt_7_1_ == BlockRailBase.EnumRailDirection.ASCENDING_WEST)) {
            return false;
         } else if ((Boolean)lvt_6_1_.func_177229_b(field_176569_M)) {
            return p_176567_1_.func_175640_z(p_176567_2_) ? true : this.func_176566_a(p_176567_1_, p_176567_2_, lvt_6_1_, p_176567_3_, p_176567_4_ + 1);
         } else {
            return false;
         }
      }
   }

   protected void func_176561_b(World p_176561_1_, BlockPos p_176561_2_, IBlockState p_176561_3_, Block p_176561_4_) {
      boolean lvt_5_1_ = (Boolean)p_176561_3_.func_177229_b(field_176569_M);
      boolean lvt_6_1_ = p_176561_1_.func_175640_z(p_176561_2_) || this.func_176566_a(p_176561_1_, p_176561_2_, p_176561_3_, true, 0) || this.func_176566_a(p_176561_1_, p_176561_2_, p_176561_3_, false, 0);
      if (lvt_6_1_ != lvt_5_1_) {
         p_176561_1_.func_180501_a(p_176561_2_, p_176561_3_.func_177226_a(field_176569_M, lvt_6_1_), 3);
         p_176561_1_.func_175685_c(p_176561_2_.func_177977_b(), this);
         if (((BlockRailBase.EnumRailDirection)p_176561_3_.func_177229_b(field_176568_b)).func_177018_c()) {
            p_176561_1_.func_175685_c(p_176561_2_.func_177984_a(), this);
         }
      }

   }

   public IProperty<BlockRailBase.EnumRailDirection> func_176560_l() {
      return field_176568_b;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176568_b, BlockRailBase.EnumRailDirection.func_177016_a(p_176203_1_ & 7)).func_177226_a(field_176569_M, (p_176203_1_ & 8) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((BlockRailBase.EnumRailDirection)p_176201_1_.func_177229_b(field_176568_b)).func_177015_a();
      if ((Boolean)p_176201_1_.func_177229_b(field_176569_M)) {
         lvt_2_1_ |= 8;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176568_b, field_176569_M});
   }
}
