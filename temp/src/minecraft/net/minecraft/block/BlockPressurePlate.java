package net.minecraft.block;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPressurePlate extends BlockBasePressurePlate {
   public static final PropertyBool field_176580_a = PropertyBool.func_177716_a("powered");
   private final BlockPressurePlate.Sensitivity field_150069_a;

   protected BlockPressurePlate(Material p_i45693_1_, BlockPressurePlate.Sensitivity p_i45693_2_) {
      super(p_i45693_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176580_a, false));
      this.field_150069_a = p_i45693_2_;
   }

   protected int func_176576_e(IBlockState p_176576_1_) {
      return (Boolean)p_176576_1_.func_177229_b(field_176580_a) ? 15 : 0;
   }

   protected IBlockState func_176575_a(IBlockState p_176575_1_, int p_176575_2_) {
      return p_176575_1_.func_177226_a(field_176580_a, p_176575_2_ > 0);
   }

   protected int func_180669_e(World p_180669_1_, BlockPos p_180669_2_) {
      AxisAlignedBB lvt_3_1_ = this.func_180667_a(p_180669_2_);
      List lvt_4_3_;
      switch(this.field_150069_a) {
      case EVERYTHING:
         lvt_4_3_ = p_180669_1_.func_72839_b((Entity)null, lvt_3_1_);
         break;
      case MOBS:
         lvt_4_3_ = p_180669_1_.func_72872_a(EntityLivingBase.class, lvt_3_1_);
         break;
      default:
         return 0;
      }

      if (!lvt_4_3_.isEmpty()) {
         Iterator lvt_5_1_ = lvt_4_3_.iterator();

         while(lvt_5_1_.hasNext()) {
            Entity lvt_6_1_ = (Entity)lvt_5_1_.next();
            if (!lvt_6_1_.func_145773_az()) {
               return 15;
            }
         }
      }

      return 0;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176580_a, p_176203_1_ == 1);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return (Boolean)p_176201_1_.func_177229_b(field_176580_a) ? 1 : 0;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176580_a});
   }

   public static enum Sensitivity {
      EVERYTHING,
      MOBS;
   }
}
