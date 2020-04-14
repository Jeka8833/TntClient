package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockPumpkin extends BlockDirectional {
   private BlockPattern field_176394_a;
   private BlockPattern field_176393_b;
   private BlockPattern field_176395_M;
   private BlockPattern field_176396_O;
   private static final Predicate<IBlockState> field_181085_Q = new Predicate<IBlockState>() {
      public boolean apply(IBlockState p_apply_1_) {
         return p_apply_1_ != null && (p_apply_1_.func_177230_c() == Blocks.field_150423_aK || p_apply_1_.func_177230_c() == Blocks.field_150428_aP);
      }
   };

   protected BlockPumpkin() {
      super(Material.field_151572_C, MapColor.field_151676_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176387_N, EnumFacing.NORTH));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      super.func_176213_c(p_176213_1_, p_176213_2_, p_176213_3_);
      this.func_180673_e(p_176213_1_, p_176213_2_);
   }

   public boolean func_176390_d(World p_176390_1_, BlockPos p_176390_2_) {
      return this.func_176392_j().func_177681_a(p_176390_1_, p_176390_2_) != null || this.func_176389_S().func_177681_a(p_176390_1_, p_176390_2_) != null;
   }

   private void func_180673_e(World p_180673_1_, BlockPos p_180673_2_) {
      BlockPattern.PatternHelper lvt_3_1_;
      int lvt_4_3_;
      int lvt_6_4_;
      if ((lvt_3_1_ = this.func_176391_l().func_177681_a(p_180673_1_, p_180673_2_)) != null) {
         for(lvt_4_3_ = 0; lvt_4_3_ < this.func_176391_l().func_177685_b(); ++lvt_4_3_) {
            BlockWorldState lvt_5_1_ = lvt_3_1_.func_177670_a(0, lvt_4_3_, 0);
            p_180673_1_.func_180501_a(lvt_5_1_.func_177508_d(), Blocks.field_150350_a.func_176223_P(), 2);
         }

         EntitySnowman lvt_4_2_ = new EntitySnowman(p_180673_1_);
         BlockPos lvt_5_2_ = lvt_3_1_.func_177670_a(0, 2, 0).func_177508_d();
         lvt_4_2_.func_70012_b((double)lvt_5_2_.func_177958_n() + 0.5D, (double)lvt_5_2_.func_177956_o() + 0.05D, (double)lvt_5_2_.func_177952_p() + 0.5D, 0.0F, 0.0F);
         p_180673_1_.func_72838_d(lvt_4_2_);

         for(lvt_6_4_ = 0; lvt_6_4_ < 120; ++lvt_6_4_) {
            p_180673_1_.func_175688_a(EnumParticleTypes.SNOW_SHOVEL, (double)lvt_5_2_.func_177958_n() + p_180673_1_.field_73012_v.nextDouble(), (double)lvt_5_2_.func_177956_o() + p_180673_1_.field_73012_v.nextDouble() * 2.5D, (double)lvt_5_2_.func_177952_p() + p_180673_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
         }

         for(lvt_6_4_ = 0; lvt_6_4_ < this.func_176391_l().func_177685_b(); ++lvt_6_4_) {
            BlockWorldState lvt_7_1_ = lvt_3_1_.func_177670_a(0, lvt_6_4_, 0);
            p_180673_1_.func_175722_b(lvt_7_1_.func_177508_d(), Blocks.field_150350_a);
         }
      } else if ((lvt_3_1_ = this.func_176388_T().func_177681_a(p_180673_1_, p_180673_2_)) != null) {
         for(lvt_4_3_ = 0; lvt_4_3_ < this.func_176388_T().func_177684_c(); ++lvt_4_3_) {
            for(int lvt_5_3_ = 0; lvt_5_3_ < this.func_176388_T().func_177685_b(); ++lvt_5_3_) {
               p_180673_1_.func_180501_a(lvt_3_1_.func_177670_a(lvt_4_3_, lvt_5_3_, 0).func_177508_d(), Blocks.field_150350_a.func_176223_P(), 2);
            }
         }

         BlockPos lvt_4_4_ = lvt_3_1_.func_177670_a(1, 2, 0).func_177508_d();
         EntityIronGolem lvt_5_4_ = new EntityIronGolem(p_180673_1_);
         lvt_5_4_.func_70849_f(true);
         lvt_5_4_.func_70012_b((double)lvt_4_4_.func_177958_n() + 0.5D, (double)lvt_4_4_.func_177956_o() + 0.05D, (double)lvt_4_4_.func_177952_p() + 0.5D, 0.0F, 0.0F);
         p_180673_1_.func_72838_d(lvt_5_4_);

         for(lvt_6_4_ = 0; lvt_6_4_ < 120; ++lvt_6_4_) {
            p_180673_1_.func_175688_a(EnumParticleTypes.SNOWBALL, (double)lvt_4_4_.func_177958_n() + p_180673_1_.field_73012_v.nextDouble(), (double)lvt_4_4_.func_177956_o() + p_180673_1_.field_73012_v.nextDouble() * 3.9D, (double)lvt_4_4_.func_177952_p() + p_180673_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
         }

         for(lvt_6_4_ = 0; lvt_6_4_ < this.func_176388_T().func_177684_c(); ++lvt_6_4_) {
            for(int lvt_7_2_ = 0; lvt_7_2_ < this.func_176388_T().func_177685_b(); ++lvt_7_2_) {
               BlockWorldState lvt_8_1_ = lvt_3_1_.func_177670_a(lvt_6_4_, lvt_7_2_, 0);
               p_180673_1_.func_175722_b(lvt_8_1_.func_177508_d(), Blocks.field_150350_a);
            }
         }
      }

   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return p_176196_1_.func_180495_p(p_176196_2_).func_177230_c().field_149764_J.func_76222_j() && World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b());
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176387_N, p_180642_8_.func_174811_aO().func_176734_d());
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176387_N, EnumFacing.func_176731_b(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176387_N)).func_176736_b();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176387_N});
   }

   protected BlockPattern func_176392_j() {
      if (this.field_176394_a == null) {
         this.field_176394_a = FactoryBlockPattern.func_177660_a().func_177659_a(" ", "#", "#").func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150433_aE))).func_177661_b();
      }

      return this.field_176394_a;
   }

   protected BlockPattern func_176391_l() {
      if (this.field_176393_b == null) {
         this.field_176393_b = FactoryBlockPattern.func_177660_a().func_177659_a("^", "#", "#").func_177662_a('^', BlockWorldState.func_177510_a(field_181085_Q)).func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150433_aE))).func_177661_b();
      }

      return this.field_176393_b;
   }

   protected BlockPattern func_176389_S() {
      if (this.field_176395_M == null) {
         this.field_176395_M = FactoryBlockPattern.func_177660_a().func_177659_a("~ ~", "###", "~#~").func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150339_S))).func_177662_a('~', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150350_a))).func_177661_b();
      }

      return this.field_176395_M;
   }

   protected BlockPattern func_176388_T() {
      if (this.field_176396_O == null) {
         this.field_176396_O = FactoryBlockPattern.func_177660_a().func_177659_a("~^~", "###", "~#~").func_177662_a('^', BlockWorldState.func_177510_a(field_181085_Q)).func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150339_S))).func_177662_a('~', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150350_a))).func_177661_b();
      }

      return this.field_176396_O;
   }
}
