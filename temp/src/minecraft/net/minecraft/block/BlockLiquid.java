package net.minecraft.block;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public abstract class BlockLiquid extends Block {
   public static final PropertyInteger field_176367_b = PropertyInteger.func_177719_a("level", 0, 15);

   protected BlockLiquid(Material p_i45413_1_) {
      super(p_i45413_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176367_b, 0));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.func_149675_a(true);
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return this.field_149764_J != Material.field_151587_i;
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return this.field_149764_J == Material.field_151586_h ? BiomeColorHelper.func_180288_c(p_180662_1_, p_180662_2_) : 16777215;
   }

   public static float func_149801_b(int p_149801_0_) {
      if (p_149801_0_ >= 8) {
         p_149801_0_ = 0;
      }

      return (float)(p_149801_0_ + 1) / 9.0F;
   }

   protected int func_176362_e(IBlockAccess p_176362_1_, BlockPos p_176362_2_) {
      return p_176362_1_.func_180495_p(p_176362_2_).func_177230_c().func_149688_o() == this.field_149764_J ? (Integer)p_176362_1_.func_180495_p(p_176362_2_).func_177229_b(field_176367_b) : -1;
   }

   protected int func_176366_f(IBlockAccess p_176366_1_, BlockPos p_176366_2_) {
      int lvt_3_1_ = this.func_176362_e(p_176366_1_, p_176366_2_);
      return lvt_3_1_ >= 8 ? 0 : lvt_3_1_;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176209_a(IBlockState p_176209_1_, boolean p_176209_2_) {
      return p_176209_2_ && (Integer)p_176209_1_.func_177229_b(field_176367_b) == 0;
   }

   public boolean func_176212_b(IBlockAccess p_176212_1_, BlockPos p_176212_2_, EnumFacing p_176212_3_) {
      Material lvt_4_1_ = p_176212_1_.func_180495_p(p_176212_2_).func_177230_c().func_149688_o();
      if (lvt_4_1_ == this.field_149764_J) {
         return false;
      } else if (p_176212_3_ == EnumFacing.UP) {
         return true;
      } else {
         return lvt_4_1_ == Material.field_151588_w ? false : super.func_176212_b(p_176212_1_, p_176212_2_, p_176212_3_);
      }
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      if (p_176225_1_.func_180495_p(p_176225_2_).func_177230_c().func_149688_o() == this.field_149764_J) {
         return false;
      } else {
         return p_176225_3_ == EnumFacing.UP ? true : super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
      }
   }

   public boolean func_176364_g(IBlockAccess p_176364_1_, BlockPos p_176364_2_) {
      for(int lvt_3_1_ = -1; lvt_3_1_ <= 1; ++lvt_3_1_) {
         for(int lvt_4_1_ = -1; lvt_4_1_ <= 1; ++lvt_4_1_) {
            IBlockState lvt_5_1_ = p_176364_1_.func_180495_p(p_176364_2_.func_177982_a(lvt_3_1_, 0, lvt_4_1_));
            Block lvt_6_1_ = lvt_5_1_.func_177230_c();
            Material lvt_7_1_ = lvt_6_1_.func_149688_o();
            if (lvt_7_1_ != this.field_149764_J && !lvt_6_1_.func_149730_j()) {
               return true;
            }
         }
      }

      return false;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public int func_149645_b() {
      return 1;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   protected Vec3 func_180687_h(IBlockAccess p_180687_1_, BlockPos p_180687_2_) {
      Vec3 lvt_3_1_ = new Vec3(0.0D, 0.0D, 0.0D);
      int lvt_4_1_ = this.func_176366_f(p_180687_1_, p_180687_2_);
      Iterator lvt_5_2_ = EnumFacing.Plane.HORIZONTAL.iterator();

      EnumFacing lvt_6_2_;
      BlockPos lvt_7_2_;
      while(lvt_5_2_.hasNext()) {
         lvt_6_2_ = (EnumFacing)lvt_5_2_.next();
         lvt_7_2_ = p_180687_2_.func_177972_a(lvt_6_2_);
         int lvt_8_1_ = this.func_176366_f(p_180687_1_, lvt_7_2_);
         int lvt_9_1_;
         if (lvt_8_1_ < 0) {
            if (!p_180687_1_.func_180495_p(lvt_7_2_).func_177230_c().func_149688_o().func_76230_c()) {
               lvt_8_1_ = this.func_176366_f(p_180687_1_, lvt_7_2_.func_177977_b());
               if (lvt_8_1_ >= 0) {
                  lvt_9_1_ = lvt_8_1_ - (lvt_4_1_ - 8);
                  lvt_3_1_ = lvt_3_1_.func_72441_c((double)((lvt_7_2_.func_177958_n() - p_180687_2_.func_177958_n()) * lvt_9_1_), (double)((lvt_7_2_.func_177956_o() - p_180687_2_.func_177956_o()) * lvt_9_1_), (double)((lvt_7_2_.func_177952_p() - p_180687_2_.func_177952_p()) * lvt_9_1_));
               }
            }
         } else if (lvt_8_1_ >= 0) {
            lvt_9_1_ = lvt_8_1_ - lvt_4_1_;
            lvt_3_1_ = lvt_3_1_.func_72441_c((double)((lvt_7_2_.func_177958_n() - p_180687_2_.func_177958_n()) * lvt_9_1_), (double)((lvt_7_2_.func_177956_o() - p_180687_2_.func_177956_o()) * lvt_9_1_), (double)((lvt_7_2_.func_177952_p() - p_180687_2_.func_177952_p()) * lvt_9_1_));
         }
      }

      if ((Integer)p_180687_1_.func_180495_p(p_180687_2_).func_177229_b(field_176367_b) >= 8) {
         lvt_5_2_ = EnumFacing.Plane.HORIZONTAL.iterator();

         do {
            if (!lvt_5_2_.hasNext()) {
               return lvt_3_1_.func_72432_b();
            }

            lvt_6_2_ = (EnumFacing)lvt_5_2_.next();
            lvt_7_2_ = p_180687_2_.func_177972_a(lvt_6_2_);
         } while(!this.func_176212_b(p_180687_1_, lvt_7_2_, lvt_6_2_) && !this.func_176212_b(p_180687_1_, lvt_7_2_.func_177984_a(), lvt_6_2_));

         lvt_3_1_ = lvt_3_1_.func_72432_b().func_72441_c(0.0D, -6.0D, 0.0D);
      }

      return lvt_3_1_.func_72432_b();
   }

   public Vec3 func_176197_a(World p_176197_1_, BlockPos p_176197_2_, Entity p_176197_3_, Vec3 p_176197_4_) {
      return p_176197_4_.func_178787_e(this.func_180687_h(p_176197_1_, p_176197_2_));
   }

   public int func_149738_a(World p_149738_1_) {
      if (this.field_149764_J == Material.field_151586_h) {
         return 5;
      } else if (this.field_149764_J == Material.field_151587_i) {
         return p_149738_1_.field_73011_w.func_177495_o() ? 10 : 30;
      } else {
         return 0;
      }
   }

   public int func_176207_c(IBlockAccess p_176207_1_, BlockPos p_176207_2_) {
      int lvt_3_1_ = p_176207_1_.func_175626_b(p_176207_2_, 0);
      int lvt_4_1_ = p_176207_1_.func_175626_b(p_176207_2_.func_177984_a(), 0);
      int lvt_5_1_ = lvt_3_1_ & 255;
      int lvt_6_1_ = lvt_4_1_ & 255;
      int lvt_7_1_ = lvt_3_1_ >> 16 & 255;
      int lvt_8_1_ = lvt_4_1_ >> 16 & 255;
      return (lvt_5_1_ > lvt_6_1_ ? lvt_5_1_ : lvt_6_1_) | (lvt_7_1_ > lvt_8_1_ ? lvt_7_1_ : lvt_8_1_) << 16;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return this.field_149764_J == Material.field_151586_h ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.SOLID;
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      double lvt_5_1_ = (double)p_180655_2_.func_177958_n();
      double lvt_7_1_ = (double)p_180655_2_.func_177956_o();
      double lvt_9_1_ = (double)p_180655_2_.func_177952_p();
      if (this.field_149764_J == Material.field_151586_h) {
         int lvt_11_1_ = (Integer)p_180655_3_.func_177229_b(field_176367_b);
         if (lvt_11_1_ > 0 && lvt_11_1_ < 8) {
            if (p_180655_4_.nextInt(64) == 0) {
               p_180655_1_.func_72980_b(lvt_5_1_ + 0.5D, lvt_7_1_ + 0.5D, lvt_9_1_ + 0.5D, "liquid.water", p_180655_4_.nextFloat() * 0.25F + 0.75F, p_180655_4_.nextFloat() * 1.0F + 0.5F, false);
            }
         } else if (p_180655_4_.nextInt(10) == 0) {
            p_180655_1_.func_175688_a(EnumParticleTypes.SUSPENDED, lvt_5_1_ + (double)p_180655_4_.nextFloat(), lvt_7_1_ + (double)p_180655_4_.nextFloat(), lvt_9_1_ + (double)p_180655_4_.nextFloat(), 0.0D, 0.0D, 0.0D);
         }
      }

      if (this.field_149764_J == Material.field_151587_i && p_180655_1_.func_180495_p(p_180655_2_.func_177984_a()).func_177230_c().func_149688_o() == Material.field_151579_a && !p_180655_1_.func_180495_p(p_180655_2_.func_177984_a()).func_177230_c().func_149662_c()) {
         if (p_180655_4_.nextInt(100) == 0) {
            double lvt_11_2_ = lvt_5_1_ + (double)p_180655_4_.nextFloat();
            double lvt_13_1_ = lvt_7_1_ + this.field_149756_F;
            double lvt_15_1_ = lvt_9_1_ + (double)p_180655_4_.nextFloat();
            p_180655_1_.func_175688_a(EnumParticleTypes.LAVA, lvt_11_2_, lvt_13_1_, lvt_15_1_, 0.0D, 0.0D, 0.0D);
            p_180655_1_.func_72980_b(lvt_11_2_, lvt_13_1_, lvt_15_1_, "liquid.lavapop", 0.2F + p_180655_4_.nextFloat() * 0.2F, 0.9F + p_180655_4_.nextFloat() * 0.15F, false);
         }

         if (p_180655_4_.nextInt(200) == 0) {
            p_180655_1_.func_72980_b(lvt_5_1_, lvt_7_1_, lvt_9_1_, "liquid.lava", 0.2F + p_180655_4_.nextFloat() * 0.2F, 0.9F + p_180655_4_.nextFloat() * 0.15F, false);
         }
      }

      if (p_180655_4_.nextInt(10) == 0 && World.func_175683_a(p_180655_1_, p_180655_2_.func_177977_b())) {
         Material lvt_11_3_ = p_180655_1_.func_180495_p(p_180655_2_.func_177979_c(2)).func_177230_c().func_149688_o();
         if (!lvt_11_3_.func_76230_c() && !lvt_11_3_.func_76224_d()) {
            double lvt_12_1_ = lvt_5_1_ + (double)p_180655_4_.nextFloat();
            double lvt_14_1_ = lvt_7_1_ - 1.05D;
            double lvt_16_1_ = lvt_9_1_ + (double)p_180655_4_.nextFloat();
            if (this.field_149764_J == Material.field_151586_h) {
               p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_WATER, lvt_12_1_, lvt_14_1_, lvt_16_1_, 0.0D, 0.0D, 0.0D);
            } else {
               p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_LAVA, lvt_12_1_, lvt_14_1_, lvt_16_1_, 0.0D, 0.0D, 0.0D);
            }
         }
      }

   }

   public static double func_180689_a(IBlockAccess p_180689_0_, BlockPos p_180689_1_, Material p_180689_2_) {
      Vec3 lvt_3_1_ = func_176361_a(p_180689_2_).func_180687_h(p_180689_0_, p_180689_1_);
      return lvt_3_1_.field_72450_a == 0.0D && lvt_3_1_.field_72449_c == 0.0D ? -1000.0D : MathHelper.func_181159_b(lvt_3_1_.field_72449_c, lvt_3_1_.field_72450_a) - 1.5707963267948966D;
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176365_e(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176365_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   public boolean func_176365_e(World p_176365_1_, BlockPos p_176365_2_, IBlockState p_176365_3_) {
      if (this.field_149764_J == Material.field_151587_i) {
         boolean lvt_4_1_ = false;
         EnumFacing[] lvt_5_1_ = EnumFacing.values();
         int lvt_6_1_ = lvt_5_1_.length;

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
            EnumFacing lvt_8_1_ = lvt_5_1_[lvt_7_1_];
            if (lvt_8_1_ != EnumFacing.DOWN && p_176365_1_.func_180495_p(p_176365_2_.func_177972_a(lvt_8_1_)).func_177230_c().func_149688_o() == Material.field_151586_h) {
               lvt_4_1_ = true;
               break;
            }
         }

         if (lvt_4_1_) {
            Integer lvt_5_2_ = (Integer)p_176365_3_.func_177229_b(field_176367_b);
            if (lvt_5_2_ == 0) {
               p_176365_1_.func_175656_a(p_176365_2_, Blocks.field_150343_Z.func_176223_P());
               this.func_180688_d(p_176365_1_, p_176365_2_);
               return true;
            }

            if (lvt_5_2_ <= 4) {
               p_176365_1_.func_175656_a(p_176365_2_, Blocks.field_150347_e.func_176223_P());
               this.func_180688_d(p_176365_1_, p_176365_2_);
               return true;
            }
         }
      }

      return false;
   }

   protected void func_180688_d(World p_180688_1_, BlockPos p_180688_2_) {
      double lvt_3_1_ = (double)p_180688_2_.func_177958_n();
      double lvt_5_1_ = (double)p_180688_2_.func_177956_o();
      double lvt_7_1_ = (double)p_180688_2_.func_177952_p();
      p_180688_1_.func_72908_a(lvt_3_1_ + 0.5D, lvt_5_1_ + 0.5D, lvt_7_1_ + 0.5D, "random.fizz", 0.5F, 2.6F + (p_180688_1_.field_73012_v.nextFloat() - p_180688_1_.field_73012_v.nextFloat()) * 0.8F);

      for(int lvt_9_1_ = 0; lvt_9_1_ < 8; ++lvt_9_1_) {
         p_180688_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_3_1_ + Math.random(), lvt_5_1_ + 1.2D, lvt_7_1_ + Math.random(), 0.0D, 0.0D, 0.0D);
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176367_b, p_176203_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return (Integer)p_176201_1_.func_177229_b(field_176367_b);
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176367_b});
   }

   public static BlockDynamicLiquid func_176361_a(Material p_176361_0_) {
      if (p_176361_0_ == Material.field_151586_h) {
         return Blocks.field_150358_i;
      } else if (p_176361_0_ == Material.field_151587_i) {
         return Blocks.field_150356_k;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   public static BlockStaticLiquid func_176363_b(Material p_176363_0_) {
      if (p_176363_0_ == Material.field_151586_h) {
         return Blocks.field_150355_j;
      } else if (p_176363_0_ == Material.field_151587_i) {
         return Blocks.field_150353_l;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }
}
