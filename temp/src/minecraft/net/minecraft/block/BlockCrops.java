package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops extends BlockBush implements IGrowable {
   public static final PropertyInteger field_176488_a = PropertyInteger.func_177719_a("age", 0, 7);

   protected BlockCrops() {
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176488_a, 0));
      this.func_149675_a(true);
      float lvt_1_1_ = 0.5F;
      this.func_149676_a(0.5F - lvt_1_1_, 0.0F, 0.5F - lvt_1_1_, 0.5F + lvt_1_1_, 0.25F, 0.5F + lvt_1_1_);
      this.func_149647_a((CreativeTabs)null);
      this.func_149711_c(0.0F);
      this.func_149672_a(field_149779_h);
      this.func_149649_H();
   }

   protected boolean func_149854_a(Block p_149854_1_) {
      return p_149854_1_ == Blocks.field_150458_ak;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      super.func_180650_b(p_180650_1_, p_180650_2_, p_180650_3_, p_180650_4_);
      if (p_180650_1_.func_175671_l(p_180650_2_.func_177984_a()) >= 9) {
         int lvt_5_1_ = (Integer)p_180650_3_.func_177229_b(field_176488_a);
         if (lvt_5_1_ < 7) {
            float lvt_6_1_ = func_180672_a(this, p_180650_1_, p_180650_2_);
            if (p_180650_4_.nextInt((int)(25.0F / lvt_6_1_) + 1) == 0) {
               p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176488_a, lvt_5_1_ + 1), 2);
            }
         }
      }

   }

   public void func_176487_g(World p_176487_1_, BlockPos p_176487_2_, IBlockState p_176487_3_) {
      int lvt_4_1_ = (Integer)p_176487_3_.func_177229_b(field_176488_a) + MathHelper.func_76136_a(p_176487_1_.field_73012_v, 2, 5);
      if (lvt_4_1_ > 7) {
         lvt_4_1_ = 7;
      }

      p_176487_1_.func_180501_a(p_176487_2_, p_176487_3_.func_177226_a(field_176488_a, lvt_4_1_), 2);
   }

   protected static float func_180672_a(Block p_180672_0_, World p_180672_1_, BlockPos p_180672_2_) {
      float lvt_3_1_ = 1.0F;
      BlockPos lvt_4_1_ = p_180672_2_.func_177977_b();

      for(int lvt_5_1_ = -1; lvt_5_1_ <= 1; ++lvt_5_1_) {
         for(int lvt_6_1_ = -1; lvt_6_1_ <= 1; ++lvt_6_1_) {
            float lvt_7_1_ = 0.0F;
            IBlockState lvt_8_1_ = p_180672_1_.func_180495_p(lvt_4_1_.func_177982_a(lvt_5_1_, 0, lvt_6_1_));
            if (lvt_8_1_.func_177230_c() == Blocks.field_150458_ak) {
               lvt_7_1_ = 1.0F;
               if ((Integer)lvt_8_1_.func_177229_b(BlockFarmland.field_176531_a) > 0) {
                  lvt_7_1_ = 3.0F;
               }
            }

            if (lvt_5_1_ != 0 || lvt_6_1_ != 0) {
               lvt_7_1_ /= 4.0F;
            }

            lvt_3_1_ += lvt_7_1_;
         }
      }

      BlockPos lvt_5_2_ = p_180672_2_.func_177978_c();
      BlockPos lvt_6_2_ = p_180672_2_.func_177968_d();
      BlockPos lvt_7_2_ = p_180672_2_.func_177976_e();
      BlockPos lvt_8_2_ = p_180672_2_.func_177974_f();
      boolean lvt_9_1_ = p_180672_0_ == p_180672_1_.func_180495_p(lvt_7_2_).func_177230_c() || p_180672_0_ == p_180672_1_.func_180495_p(lvt_8_2_).func_177230_c();
      boolean lvt_10_1_ = p_180672_0_ == p_180672_1_.func_180495_p(lvt_5_2_).func_177230_c() || p_180672_0_ == p_180672_1_.func_180495_p(lvt_6_2_).func_177230_c();
      if (lvt_9_1_ && lvt_10_1_) {
         lvt_3_1_ /= 2.0F;
      } else {
         boolean lvt_11_1_ = p_180672_0_ == p_180672_1_.func_180495_p(lvt_7_2_.func_177978_c()).func_177230_c() || p_180672_0_ == p_180672_1_.func_180495_p(lvt_8_2_.func_177978_c()).func_177230_c() || p_180672_0_ == p_180672_1_.func_180495_p(lvt_8_2_.func_177968_d()).func_177230_c() || p_180672_0_ == p_180672_1_.func_180495_p(lvt_7_2_.func_177968_d()).func_177230_c();
         if (lvt_11_1_) {
            lvt_3_1_ /= 2.0F;
         }
      }

      return lvt_3_1_;
   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      return (p_180671_1_.func_175699_k(p_180671_2_) >= 8 || p_180671_1_.func_175678_i(p_180671_2_)) && this.func_149854_a(p_180671_1_.func_180495_p(p_180671_2_.func_177977_b()).func_177230_c());
   }

   protected Item func_149866_i() {
      return Items.field_151014_N;
   }

   protected Item func_149865_P() {
      return Items.field_151015_O;
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, 0);
      if (!p_180653_1_.field_72995_K) {
         int lvt_6_1_ = (Integer)p_180653_3_.func_177229_b(field_176488_a);
         if (lvt_6_1_ >= 7) {
            int lvt_7_1_ = 3 + p_180653_5_;

            for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
               if (p_180653_1_.field_73012_v.nextInt(15) <= lvt_6_1_) {
                  func_180635_a(p_180653_1_, p_180653_2_, new ItemStack(this.func_149866_i(), 1, 0));
               }
            }
         }

      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return (Integer)p_180660_1_.func_177229_b(field_176488_a) == 7 ? this.func_149865_P() : this.func_149866_i();
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return this.func_149866_i();
   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return (Integer)p_176473_3_.func_177229_b(field_176488_a) < 7;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return true;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      this.func_176487_g(p_176474_1_, p_176474_3_, p_176474_4_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176488_a, p_176203_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return (Integer)p_176201_1_.func_177229_b(field_176488_a);
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176488_a});
   }
}
