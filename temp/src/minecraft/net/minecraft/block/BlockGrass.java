package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public class BlockGrass extends Block implements IGrowable {
   public static final PropertyBool field_176498_a = PropertyBool.func_177716_a("snowy");

   protected BlockGrass() {
      super(Material.field_151577_b);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176498_a, false));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      Block lvt_4_1_ = p_176221_2_.func_180495_p(p_176221_3_.func_177984_a()).func_177230_c();
      return p_176221_1_.func_177226_a(field_176498_a, lvt_4_1_ == Blocks.field_150433_aE || lvt_4_1_ == Blocks.field_150431_aC);
   }

   public int func_149635_D() {
      return ColorizerGrass.func_77480_a(0.5D, 1.0D);
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return this.func_149635_D();
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return BiomeColorHelper.func_180286_a(p_180662_1_, p_180662_2_);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (!p_180650_1_.field_72995_K) {
         if (p_180650_1_.func_175671_l(p_180650_2_.func_177984_a()) < 4 && p_180650_1_.func_180495_p(p_180650_2_.func_177984_a()).func_177230_c().func_149717_k() > 2) {
            p_180650_1_.func_175656_a(p_180650_2_, Blocks.field_150346_d.func_176223_P());
         } else {
            if (p_180650_1_.func_175671_l(p_180650_2_.func_177984_a()) >= 9) {
               for(int lvt_5_1_ = 0; lvt_5_1_ < 4; ++lvt_5_1_) {
                  BlockPos lvt_6_1_ = p_180650_2_.func_177982_a(p_180650_4_.nextInt(3) - 1, p_180650_4_.nextInt(5) - 3, p_180650_4_.nextInt(3) - 1);
                  Block lvt_7_1_ = p_180650_1_.func_180495_p(lvt_6_1_.func_177984_a()).func_177230_c();
                  IBlockState lvt_8_1_ = p_180650_1_.func_180495_p(lvt_6_1_);
                  if (lvt_8_1_.func_177230_c() == Blocks.field_150346_d && lvt_8_1_.func_177229_b(BlockDirt.field_176386_a) == BlockDirt.DirtType.DIRT && p_180650_1_.func_175671_l(lvt_6_1_.func_177984_a()) >= 4 && lvt_7_1_.func_149717_k() <= 2) {
                     p_180650_1_.func_175656_a(lvt_6_1_, Blocks.field_150349_c.func_176223_P());
                  }
               }
            }

         }
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Blocks.field_150346_d.func_180660_a(Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT), p_180660_2_, p_180660_3_);
   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return true;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return true;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      BlockPos lvt_5_1_ = p_176474_3_.func_177984_a();

      label38:
      for(int lvt_6_1_ = 0; lvt_6_1_ < 128; ++lvt_6_1_) {
         BlockPos lvt_7_1_ = lvt_5_1_;

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_6_1_ / 16; ++lvt_8_1_) {
            lvt_7_1_ = lvt_7_1_.func_177982_a(p_176474_2_.nextInt(3) - 1, (p_176474_2_.nextInt(3) - 1) * p_176474_2_.nextInt(3) / 2, p_176474_2_.nextInt(3) - 1);
            if (p_176474_1_.func_180495_p(lvt_7_1_.func_177977_b()).func_177230_c() != Blocks.field_150349_c || p_176474_1_.func_180495_p(lvt_7_1_).func_177230_c().func_149721_r()) {
               continue label38;
            }
         }

         if (p_176474_1_.func_180495_p(lvt_7_1_).func_177230_c().field_149764_J == Material.field_151579_a) {
            if (p_176474_2_.nextInt(8) == 0) {
               BlockFlower.EnumFlowerType lvt_8_2_ = p_176474_1_.func_180494_b(lvt_7_1_).func_180623_a(p_176474_2_, lvt_7_1_);
               BlockFlower lvt_9_1_ = lvt_8_2_.func_176964_a().func_180346_a();
               IBlockState lvt_10_1_ = lvt_9_1_.func_176223_P().func_177226_a(lvt_9_1_.func_176494_l(), lvt_8_2_);
               if (lvt_9_1_.func_180671_f(p_176474_1_, lvt_7_1_, lvt_10_1_)) {
                  p_176474_1_.func_180501_a(lvt_7_1_, lvt_10_1_, 3);
               }
            } else {
               IBlockState lvt_8_3_ = Blocks.field_150329_H.func_176223_P().func_177226_a(BlockTallGrass.field_176497_a, BlockTallGrass.EnumType.GRASS);
               if (Blocks.field_150329_H.func_180671_f(p_176474_1_, lvt_7_1_, lvt_8_3_)) {
                  p_176474_1_.func_180501_a(lvt_7_1_, lvt_8_3_, 3);
               }
            }
         }
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return 0;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176498_a});
   }
}
