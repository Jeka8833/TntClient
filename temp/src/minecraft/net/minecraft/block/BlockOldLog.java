package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockOldLog extends BlockLog {
   public static final PropertyEnum<BlockPlanks.EnumType> field_176301_b = PropertyEnum.func_177708_a("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>() {
      public boolean apply(BlockPlanks.EnumType p_apply_1_) {
         return p_apply_1_.func_176839_a() < 4;
      }
   });

   public BlockOldLog() {
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176301_b, BlockPlanks.EnumType.OAK).func_177226_a(field_176299_a, BlockLog.EnumAxis.Y));
   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      BlockPlanks.EnumType lvt_2_1_ = (BlockPlanks.EnumType)p_180659_1_.func_177229_b(field_176301_b);
      switch((BlockLog.EnumAxis)p_180659_1_.func_177229_b(field_176299_a)) {
      case X:
      case Z:
      case NONE:
      default:
         switch(lvt_2_1_) {
         case OAK:
         default:
            return BlockPlanks.EnumType.SPRUCE.func_181070_c();
         case SPRUCE:
            return BlockPlanks.EnumType.DARK_OAK.func_181070_c();
         case BIRCH:
            return MapColor.field_151677_p;
         case JUNGLE:
            return BlockPlanks.EnumType.SPRUCE.func_181070_c();
         }
      case Y:
         return lvt_2_1_.func_181070_c();
      }
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockPlanks.EnumType.OAK.func_176839_a()));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()));
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      IBlockState lvt_2_1_ = this.func_176223_P().func_177226_a(field_176301_b, BlockPlanks.EnumType.func_176837_a((p_176203_1_ & 3) % 4));
      switch(p_176203_1_ & 12) {
      case 0:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176299_a, BlockLog.EnumAxis.Y);
         break;
      case 4:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176299_a, BlockLog.EnumAxis.X);
         break;
      case 8:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176299_a, BlockLog.EnumAxis.Z);
         break;
      default:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176299_a, BlockLog.EnumAxis.NONE);
      }

      return lvt_2_1_;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((BlockPlanks.EnumType)p_176201_1_.func_177229_b(field_176301_b)).func_176839_a();
      switch((BlockLog.EnumAxis)p_176201_1_.func_177229_b(field_176299_a)) {
      case X:
         lvt_2_1_ |= 4;
         break;
      case Z:
         lvt_2_1_ |= 8;
         break;
      case NONE:
         lvt_2_1_ |= 12;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176301_b, field_176299_a});
   }

   protected ItemStack func_180643_i(IBlockState p_180643_1_) {
      return new ItemStack(Item.func_150898_a(this), 1, ((BlockPlanks.EnumType)p_180643_1_.func_177229_b(field_176301_b)).func_176839_a());
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      return ((BlockPlanks.EnumType)p_180651_1_.func_177229_b(field_176301_b)).func_176839_a();
   }
}
