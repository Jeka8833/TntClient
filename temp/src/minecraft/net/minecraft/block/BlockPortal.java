package net.minecraft.block;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortal extends BlockBreakable {
   public static final PropertyEnum<EnumFacing.Axis> field_176550_a;

   public BlockPortal() {
      super(Material.field_151567_E, false);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176550_a, EnumFacing.Axis.X));
      this.func_149675_a(true);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      super.func_180650_b(p_180650_1_, p_180650_2_, p_180650_3_, p_180650_4_);
      if (p_180650_1_.field_73011_w.func_76569_d() && p_180650_1_.func_82736_K().func_82766_b("doMobSpawning") && p_180650_4_.nextInt(2000) < p_180650_1_.func_175659_aa().func_151525_a()) {
         int lvt_5_1_ = p_180650_2_.func_177956_o();

         BlockPos lvt_6_1_;
         for(lvt_6_1_ = p_180650_2_; !World.func_175683_a(p_180650_1_, lvt_6_1_) && lvt_6_1_.func_177956_o() > 0; lvt_6_1_ = lvt_6_1_.func_177977_b()) {
         }

         if (lvt_5_1_ > 0 && !p_180650_1_.func_180495_p(lvt_6_1_.func_177984_a()).func_177230_c().func_149721_r()) {
            Entity lvt_7_1_ = ItemMonsterPlacer.func_77840_a(p_180650_1_, 57, (double)lvt_6_1_.func_177958_n() + 0.5D, (double)lvt_6_1_.func_177956_o() + 1.1D, (double)lvt_6_1_.func_177952_p() + 0.5D);
            if (lvt_7_1_ != null) {
               lvt_7_1_.field_71088_bW = lvt_7_1_.func_82147_ab();
            }
         }
      }

   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      EnumFacing.Axis lvt_3_1_ = (EnumFacing.Axis)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176550_a);
      float lvt_4_1_ = 0.125F;
      float lvt_5_1_ = 0.125F;
      if (lvt_3_1_ == EnumFacing.Axis.X) {
         lvt_4_1_ = 0.5F;
      }

      if (lvt_3_1_ == EnumFacing.Axis.Z) {
         lvt_5_1_ = 0.5F;
      }

      this.func_149676_a(0.5F - lvt_4_1_, 0.0F, 0.5F - lvt_5_1_, 0.5F + lvt_4_1_, 1.0F, 0.5F + lvt_5_1_);
   }

   public static int func_176549_a(EnumFacing.Axis p_176549_0_) {
      if (p_176549_0_ == EnumFacing.Axis.X) {
         return 1;
      } else {
         return p_176549_0_ == EnumFacing.Axis.Z ? 2 : 0;
      }
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176548_d(World p_176548_1_, BlockPos p_176548_2_) {
      BlockPortal.Size lvt_3_1_ = new BlockPortal.Size(p_176548_1_, p_176548_2_, EnumFacing.Axis.X);
      if (lvt_3_1_.func_150860_b() && lvt_3_1_.field_150864_e == 0) {
         lvt_3_1_.func_150859_c();
         return true;
      } else {
         BlockPortal.Size lvt_4_1_ = new BlockPortal.Size(p_176548_1_, p_176548_2_, EnumFacing.Axis.Z);
         if (lvt_4_1_.func_150860_b() && lvt_4_1_.field_150864_e == 0) {
            lvt_4_1_.func_150859_c();
            return true;
         } else {
            return false;
         }
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing.Axis lvt_5_1_ = (EnumFacing.Axis)p_176204_3_.func_177229_b(field_176550_a);
      BlockPortal.Size lvt_6_2_;
      if (lvt_5_1_ == EnumFacing.Axis.X) {
         lvt_6_2_ = new BlockPortal.Size(p_176204_1_, p_176204_2_, EnumFacing.Axis.X);
         if (!lvt_6_2_.func_150860_b() || lvt_6_2_.field_150864_e < lvt_6_2_.field_150868_h * lvt_6_2_.field_150862_g) {
            p_176204_1_.func_175656_a(p_176204_2_, Blocks.field_150350_a.func_176223_P());
         }
      } else if (lvt_5_1_ == EnumFacing.Axis.Z) {
         lvt_6_2_ = new BlockPortal.Size(p_176204_1_, p_176204_2_, EnumFacing.Axis.Z);
         if (!lvt_6_2_.func_150860_b() || lvt_6_2_.field_150864_e < lvt_6_2_.field_150868_h * lvt_6_2_.field_150862_g) {
            p_176204_1_.func_175656_a(p_176204_2_, Blocks.field_150350_a.func_176223_P());
         }
      }

   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      EnumFacing.Axis lvt_4_1_ = null;
      IBlockState lvt_5_1_ = p_176225_1_.func_180495_p(p_176225_2_);
      if (p_176225_1_.func_180495_p(p_176225_2_).func_177230_c() == this) {
         lvt_4_1_ = (EnumFacing.Axis)lvt_5_1_.func_177229_b(field_176550_a);
         if (lvt_4_1_ == null) {
            return false;
         }

         if (lvt_4_1_ == EnumFacing.Axis.Z && p_176225_3_ != EnumFacing.EAST && p_176225_3_ != EnumFacing.WEST) {
            return false;
         }

         if (lvt_4_1_ == EnumFacing.Axis.X && p_176225_3_ != EnumFacing.SOUTH && p_176225_3_ != EnumFacing.NORTH) {
            return false;
         }
      }

      boolean lvt_6_1_ = p_176225_1_.func_180495_p(p_176225_2_.func_177976_e()).func_177230_c() == this && p_176225_1_.func_180495_p(p_176225_2_.func_177985_f(2)).func_177230_c() != this;
      boolean lvt_7_1_ = p_176225_1_.func_180495_p(p_176225_2_.func_177974_f()).func_177230_c() == this && p_176225_1_.func_180495_p(p_176225_2_.func_177965_g(2)).func_177230_c() != this;
      boolean lvt_8_1_ = p_176225_1_.func_180495_p(p_176225_2_.func_177978_c()).func_177230_c() == this && p_176225_1_.func_180495_p(p_176225_2_.func_177964_d(2)).func_177230_c() != this;
      boolean lvt_9_1_ = p_176225_1_.func_180495_p(p_176225_2_.func_177968_d()).func_177230_c() == this && p_176225_1_.func_180495_p(p_176225_2_.func_177970_e(2)).func_177230_c() != this;
      boolean lvt_10_1_ = lvt_6_1_ || lvt_7_1_ || lvt_4_1_ == EnumFacing.Axis.X;
      boolean lvt_11_1_ = lvt_8_1_ || lvt_9_1_ || lvt_4_1_ == EnumFacing.Axis.Z;
      if (lvt_10_1_ && p_176225_3_ == EnumFacing.WEST) {
         return true;
      } else if (lvt_10_1_ && p_176225_3_ == EnumFacing.EAST) {
         return true;
      } else if (lvt_11_1_ && p_176225_3_ == EnumFacing.NORTH) {
         return true;
      } else {
         return lvt_11_1_ && p_176225_3_ == EnumFacing.SOUTH;
      }
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   public void func_180634_a(World p_180634_1_, BlockPos p_180634_2_, IBlockState p_180634_3_, Entity p_180634_4_) {
      if (p_180634_4_.field_70154_o == null && p_180634_4_.field_70153_n == null) {
         p_180634_4_.func_181015_d(p_180634_2_);
      }

   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if (p_180655_4_.nextInt(100) == 0) {
         p_180655_1_.func_72980_b((double)p_180655_2_.func_177958_n() + 0.5D, (double)p_180655_2_.func_177956_o() + 0.5D, (double)p_180655_2_.func_177952_p() + 0.5D, "portal.portal", 0.5F, p_180655_4_.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int lvt_5_1_ = 0; lvt_5_1_ < 4; ++lvt_5_1_) {
         double lvt_6_1_ = (double)((float)p_180655_2_.func_177958_n() + p_180655_4_.nextFloat());
         double lvt_8_1_ = (double)((float)p_180655_2_.func_177956_o() + p_180655_4_.nextFloat());
         double lvt_10_1_ = (double)((float)p_180655_2_.func_177952_p() + p_180655_4_.nextFloat());
         double lvt_12_1_ = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
         double lvt_14_1_ = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
         double lvt_16_1_ = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
         int lvt_18_1_ = p_180655_4_.nextInt(2) * 2 - 1;
         if (p_180655_1_.func_180495_p(p_180655_2_.func_177976_e()).func_177230_c() != this && p_180655_1_.func_180495_p(p_180655_2_.func_177974_f()).func_177230_c() != this) {
            lvt_6_1_ = (double)p_180655_2_.func_177958_n() + 0.5D + 0.25D * (double)lvt_18_1_;
            lvt_12_1_ = (double)(p_180655_4_.nextFloat() * 2.0F * (float)lvt_18_1_);
         } else {
            lvt_10_1_ = (double)p_180655_2_.func_177952_p() + 0.5D + 0.25D * (double)lvt_18_1_;
            lvt_16_1_ = (double)(p_180655_4_.nextFloat() * 2.0F * (float)lvt_18_1_);
         }

         p_180655_1_.func_175688_a(EnumParticleTypes.PORTAL, lvt_6_1_, lvt_8_1_, lvt_10_1_, lvt_12_1_, lvt_14_1_, lvt_16_1_);
      }

   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return null;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176550_a, (p_176203_1_ & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return func_176549_a((EnumFacing.Axis)p_176201_1_.func_177229_b(field_176550_a));
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176550_a});
   }

   public BlockPattern.PatternHelper func_181089_f(World p_181089_1_, BlockPos p_181089_2_) {
      EnumFacing.Axis lvt_3_1_ = EnumFacing.Axis.Z;
      BlockPortal.Size lvt_4_1_ = new BlockPortal.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.X);
      LoadingCache<BlockPos, BlockWorldState> lvt_5_1_ = BlockPattern.func_181627_a(p_181089_1_, true);
      if (!lvt_4_1_.func_150860_b()) {
         lvt_3_1_ = EnumFacing.Axis.X;
         lvt_4_1_ = new BlockPortal.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.Z);
      }

      if (!lvt_4_1_.func_150860_b()) {
         return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, lvt_5_1_, 1, 1, 1);
      } else {
         int[] lvt_6_1_ = new int[EnumFacing.AxisDirection.values().length];
         EnumFacing lvt_7_1_ = lvt_4_1_.field_150866_c.func_176735_f();
         BlockPos lvt_8_1_ = lvt_4_1_.field_150861_f.func_177981_b(lvt_4_1_.func_181100_a() - 1);
         EnumFacing.AxisDirection[] lvt_9_1_ = EnumFacing.AxisDirection.values();
         int lvt_10_1_ = lvt_9_1_.length;

         int lvt_11_2_;
         for(lvt_11_2_ = 0; lvt_11_2_ < lvt_10_1_; ++lvt_11_2_) {
            EnumFacing.AxisDirection lvt_12_1_ = lvt_9_1_[lvt_11_2_];
            BlockPattern.PatternHelper lvt_13_1_ = new BlockPattern.PatternHelper(lvt_7_1_.func_176743_c() == lvt_12_1_ ? lvt_8_1_ : lvt_8_1_.func_177967_a(lvt_4_1_.field_150866_c, lvt_4_1_.func_181101_b() - 1), EnumFacing.func_181076_a(lvt_12_1_, lvt_3_1_), EnumFacing.UP, lvt_5_1_, lvt_4_1_.func_181101_b(), lvt_4_1_.func_181100_a(), 1);

            for(int lvt_14_1_ = 0; lvt_14_1_ < lvt_4_1_.func_181101_b(); ++lvt_14_1_) {
               for(int lvt_15_1_ = 0; lvt_15_1_ < lvt_4_1_.func_181100_a(); ++lvt_15_1_) {
                  BlockWorldState lvt_16_1_ = lvt_13_1_.func_177670_a(lvt_14_1_, lvt_15_1_, 1);
                  if (lvt_16_1_.func_177509_a() != null && lvt_16_1_.func_177509_a().func_177230_c().func_149688_o() != Material.field_151579_a) {
                     ++lvt_6_1_[lvt_12_1_.ordinal()];
                  }
               }
            }
         }

         EnumFacing.AxisDirection lvt_9_2_ = EnumFacing.AxisDirection.POSITIVE;
         EnumFacing.AxisDirection[] lvt_10_2_ = EnumFacing.AxisDirection.values();
         lvt_11_2_ = lvt_10_2_.length;

         for(int lvt_12_2_ = 0; lvt_12_2_ < lvt_11_2_; ++lvt_12_2_) {
            EnumFacing.AxisDirection lvt_13_2_ = lvt_10_2_[lvt_12_2_];
            if (lvt_6_1_[lvt_13_2_.ordinal()] < lvt_6_1_[lvt_9_2_.ordinal()]) {
               lvt_9_2_ = lvt_13_2_;
            }
         }

         return new BlockPattern.PatternHelper(lvt_7_1_.func_176743_c() == lvt_9_2_ ? lvt_8_1_ : lvt_8_1_.func_177967_a(lvt_4_1_.field_150866_c, lvt_4_1_.func_181101_b() - 1), EnumFacing.func_181076_a(lvt_9_2_, lvt_3_1_), EnumFacing.UP, lvt_5_1_, lvt_4_1_.func_181101_b(), lvt_4_1_.func_181100_a(), 1);
      }
   }

   static {
      field_176550_a = PropertyEnum.func_177706_a("axis", EnumFacing.Axis.class, EnumFacing.Axis.X, EnumFacing.Axis.Z);
   }

   public static class Size {
      private final World field_150867_a;
      private final EnumFacing.Axis field_150865_b;
      private final EnumFacing field_150866_c;
      private final EnumFacing field_150863_d;
      private int field_150864_e = 0;
      private BlockPos field_150861_f;
      private int field_150862_g;
      private int field_150868_h;

      public Size(World p_i45694_1_, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
         this.field_150867_a = p_i45694_1_;
         this.field_150865_b = p_i45694_3_;
         if (p_i45694_3_ == EnumFacing.Axis.X) {
            this.field_150863_d = EnumFacing.EAST;
            this.field_150866_c = EnumFacing.WEST;
         } else {
            this.field_150863_d = EnumFacing.NORTH;
            this.field_150866_c = EnumFacing.SOUTH;
         }

         for(BlockPos lvt_4_1_ = p_i45694_2_; p_i45694_2_.func_177956_o() > lvt_4_1_.func_177956_o() - 21 && p_i45694_2_.func_177956_o() > 0 && this.func_150857_a(p_i45694_1_.func_180495_p(p_i45694_2_.func_177977_b()).func_177230_c()); p_i45694_2_ = p_i45694_2_.func_177977_b()) {
         }

         int lvt_5_1_ = this.func_180120_a(p_i45694_2_, this.field_150863_d) - 1;
         if (lvt_5_1_ >= 0) {
            this.field_150861_f = p_i45694_2_.func_177967_a(this.field_150863_d, lvt_5_1_);
            this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);
            if (this.field_150868_h < 2 || this.field_150868_h > 21) {
               this.field_150861_f = null;
               this.field_150868_h = 0;
            }
         }

         if (this.field_150861_f != null) {
            this.field_150862_g = this.func_150858_a();
         }

      }

      protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
         int lvt_3_1_;
         for(lvt_3_1_ = 0; lvt_3_1_ < 22; ++lvt_3_1_) {
            BlockPos lvt_4_1_ = p_180120_1_.func_177967_a(p_180120_2_, lvt_3_1_);
            if (!this.func_150857_a(this.field_150867_a.func_180495_p(lvt_4_1_).func_177230_c()) || this.field_150867_a.func_180495_p(lvt_4_1_.func_177977_b()).func_177230_c() != Blocks.field_150343_Z) {
               break;
            }
         }

         Block lvt_4_2_ = this.field_150867_a.func_180495_p(p_180120_1_.func_177967_a(p_180120_2_, lvt_3_1_)).func_177230_c();
         return lvt_4_2_ == Blocks.field_150343_Z ? lvt_3_1_ : 0;
      }

      public int func_181100_a() {
         return this.field_150862_g;
      }

      public int func_181101_b() {
         return this.field_150868_h;
      }

      protected int func_150858_a() {
         int lvt_1_1_;
         label56:
         for(this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g) {
            for(lvt_1_1_ = 0; lvt_1_1_ < this.field_150868_h; ++lvt_1_1_) {
               BlockPos lvt_2_1_ = this.field_150861_f.func_177967_a(this.field_150866_c, lvt_1_1_).func_177981_b(this.field_150862_g);
               Block lvt_3_1_ = this.field_150867_a.func_180495_p(lvt_2_1_).func_177230_c();
               if (!this.func_150857_a(lvt_3_1_)) {
                  break label56;
               }

               if (lvt_3_1_ == Blocks.field_150427_aO) {
                  ++this.field_150864_e;
               }

               if (lvt_1_1_ == 0) {
                  lvt_3_1_ = this.field_150867_a.func_180495_p(lvt_2_1_.func_177972_a(this.field_150863_d)).func_177230_c();
                  if (lvt_3_1_ != Blocks.field_150343_Z) {
                     break label56;
                  }
               } else if (lvt_1_1_ == this.field_150868_h - 1) {
                  lvt_3_1_ = this.field_150867_a.func_180495_p(lvt_2_1_.func_177972_a(this.field_150866_c)).func_177230_c();
                  if (lvt_3_1_ != Blocks.field_150343_Z) {
                     break label56;
                  }
               }
            }
         }

         for(lvt_1_1_ = 0; lvt_1_1_ < this.field_150868_h; ++lvt_1_1_) {
            if (this.field_150867_a.func_180495_p(this.field_150861_f.func_177967_a(this.field_150866_c, lvt_1_1_).func_177981_b(this.field_150862_g)).func_177230_c() != Blocks.field_150343_Z) {
               this.field_150862_g = 0;
               break;
            }
         }

         if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
            return this.field_150862_g;
         } else {
            this.field_150861_f = null;
            this.field_150868_h = 0;
            this.field_150862_g = 0;
            return 0;
         }
      }

      protected boolean func_150857_a(Block p_150857_1_) {
         return p_150857_1_.field_149764_J == Material.field_151579_a || p_150857_1_ == Blocks.field_150480_ab || p_150857_1_ == Blocks.field_150427_aO;
      }

      public boolean func_150860_b() {
         return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
      }

      public void func_150859_c() {
         for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_150868_h; ++lvt_1_1_) {
            BlockPos lvt_2_1_ = this.field_150861_f.func_177967_a(this.field_150866_c, lvt_1_1_);

            for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_150862_g; ++lvt_3_1_) {
               this.field_150867_a.func_180501_a(lvt_2_1_.func_177981_b(lvt_3_1_), Blocks.field_150427_aO.func_176223_P().func_177226_a(BlockPortal.field_176550_a, this.field_150865_b), 2);
            }
         }

      }
   }
}
