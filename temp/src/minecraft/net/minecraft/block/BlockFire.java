package net.minecraft.block;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class BlockFire extends Block {
   public static final PropertyInteger field_176543_a = PropertyInteger.func_177719_a("age", 0, 15);
   public static final PropertyBool field_176540_b = PropertyBool.func_177716_a("flip");
   public static final PropertyBool field_176544_M = PropertyBool.func_177716_a("alt");
   public static final PropertyBool field_176545_N = PropertyBool.func_177716_a("north");
   public static final PropertyBool field_176546_O = PropertyBool.func_177716_a("east");
   public static final PropertyBool field_176541_P = PropertyBool.func_177716_a("south");
   public static final PropertyBool field_176539_Q = PropertyBool.func_177716_a("west");
   public static final PropertyInteger field_176542_R = PropertyInteger.func_177719_a("upper", 0, 2);
   private final Map<Block, Integer> field_149849_a = Maps.newIdentityHashMap();
   private final Map<Block, Integer> field_149848_b = Maps.newIdentityHashMap();

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      int lvt_4_1_ = p_176221_3_.func_177958_n();
      int lvt_5_1_ = p_176221_3_.func_177956_o();
      int lvt_6_1_ = p_176221_3_.func_177952_p();
      if (!World.func_175683_a(p_176221_2_, p_176221_3_.func_177977_b()) && !Blocks.field_150480_ab.func_176535_e(p_176221_2_, p_176221_3_.func_177977_b())) {
         boolean lvt_7_1_ = (lvt_4_1_ + lvt_5_1_ + lvt_6_1_ & 1) == 1;
         boolean lvt_8_1_ = (lvt_4_1_ / 2 + lvt_5_1_ / 2 + lvt_6_1_ / 2 & 1) == 1;
         int lvt_9_1_ = 0;
         if (this.func_176535_e(p_176221_2_, p_176221_3_.func_177984_a())) {
            lvt_9_1_ = lvt_7_1_ ? 1 : 2;
         }

         return p_176221_1_.func_177226_a(field_176545_N, this.func_176535_e(p_176221_2_, p_176221_3_.func_177978_c())).func_177226_a(field_176546_O, this.func_176535_e(p_176221_2_, p_176221_3_.func_177974_f())).func_177226_a(field_176541_P, this.func_176535_e(p_176221_2_, p_176221_3_.func_177968_d())).func_177226_a(field_176539_Q, this.func_176535_e(p_176221_2_, p_176221_3_.func_177976_e())).func_177226_a(field_176542_R, lvt_9_1_).func_177226_a(field_176540_b, lvt_8_1_).func_177226_a(field_176544_M, lvt_7_1_);
      } else {
         return this.func_176223_P();
      }
   }

   protected BlockFire() {
      super(Material.field_151581_o);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176543_a, 0).func_177226_a(field_176540_b, false).func_177226_a(field_176544_M, false).func_177226_a(field_176545_N, false).func_177226_a(field_176546_O, false).func_177226_a(field_176541_P, false).func_177226_a(field_176539_Q, false).func_177226_a(field_176542_R, 0));
      this.func_149675_a(true);
   }

   public static void func_149843_e() {
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150344_f, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150373_bw, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150376_bx, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180390_bo, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180391_bp, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180392_bq, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180386_br, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180385_bs, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180387_bt, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180407_aO, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180408_aP, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180404_aQ, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180403_aR, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180406_aS, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_180405_aT, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150476_ad, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150487_bG, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150485_bF, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150481_bH, 5, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150364_r, 5, 5);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150363_s, 5, 5);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150362_t, 30, 60);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150361_u, 30, 60);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150342_X, 30, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150335_W, 15, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150329_H, 60, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150398_cm, 60, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150327_N, 60, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150328_O, 60, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150330_I, 60, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150325_L, 30, 60);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150395_bd, 15, 100);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150402_ci, 5, 5);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150407_cf, 60, 20);
      Blocks.field_150480_ab.func_180686_a(Blocks.field_150404_cg, 60, 20);
   }

   public void func_180686_a(Block p_180686_1_, int p_180686_2_, int p_180686_3_) {
      this.field_149849_a.put(p_180686_1_, p_180686_2_);
      this.field_149848_b.put(p_180686_1_, p_180686_3_);
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

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public int func_149738_a(World p_149738_1_) {
      return 30;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (p_180650_1_.func_82736_K().func_82766_b("doFireTick")) {
         if (!this.func_176196_c(p_180650_1_, p_180650_2_)) {
            p_180650_1_.func_175698_g(p_180650_2_);
         }

         Block lvt_5_1_ = p_180650_1_.func_180495_p(p_180650_2_.func_177977_b()).func_177230_c();
         boolean lvt_6_1_ = lvt_5_1_ == Blocks.field_150424_aL;
         if (p_180650_1_.field_73011_w instanceof WorldProviderEnd && lvt_5_1_ == Blocks.field_150357_h) {
            lvt_6_1_ = true;
         }

         if (!lvt_6_1_ && p_180650_1_.func_72896_J() && this.func_176537_d(p_180650_1_, p_180650_2_)) {
            p_180650_1_.func_175698_g(p_180650_2_);
         } else {
            int lvt_7_1_ = (Integer)p_180650_3_.func_177229_b(field_176543_a);
            if (lvt_7_1_ < 15) {
               p_180650_3_ = p_180650_3_.func_177226_a(field_176543_a, lvt_7_1_ + p_180650_4_.nextInt(3) / 2);
               p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_, 4);
            }

            p_180650_1_.func_175684_a(p_180650_2_, this, this.func_149738_a(p_180650_1_) + p_180650_4_.nextInt(10));
            if (!lvt_6_1_) {
               if (!this.func_176533_e(p_180650_1_, p_180650_2_)) {
                  if (!World.func_175683_a(p_180650_1_, p_180650_2_.func_177977_b()) || lvt_7_1_ > 3) {
                     p_180650_1_.func_175698_g(p_180650_2_);
                  }

                  return;
               }

               if (!this.func_176535_e(p_180650_1_, p_180650_2_.func_177977_b()) && lvt_7_1_ == 15 && p_180650_4_.nextInt(4) == 0) {
                  p_180650_1_.func_175698_g(p_180650_2_);
                  return;
               }
            }

            boolean lvt_8_1_ = p_180650_1_.func_180502_D(p_180650_2_);
            int lvt_9_1_ = 0;
            if (lvt_8_1_) {
               lvt_9_1_ = -50;
            }

            this.func_176536_a(p_180650_1_, p_180650_2_.func_177974_f(), 300 + lvt_9_1_, p_180650_4_, lvt_7_1_);
            this.func_176536_a(p_180650_1_, p_180650_2_.func_177976_e(), 300 + lvt_9_1_, p_180650_4_, lvt_7_1_);
            this.func_176536_a(p_180650_1_, p_180650_2_.func_177977_b(), 250 + lvt_9_1_, p_180650_4_, lvt_7_1_);
            this.func_176536_a(p_180650_1_, p_180650_2_.func_177984_a(), 250 + lvt_9_1_, p_180650_4_, lvt_7_1_);
            this.func_176536_a(p_180650_1_, p_180650_2_.func_177978_c(), 300 + lvt_9_1_, p_180650_4_, lvt_7_1_);
            this.func_176536_a(p_180650_1_, p_180650_2_.func_177968_d(), 300 + lvt_9_1_, p_180650_4_, lvt_7_1_);

            for(int lvt_10_1_ = -1; lvt_10_1_ <= 1; ++lvt_10_1_) {
               for(int lvt_11_1_ = -1; lvt_11_1_ <= 1; ++lvt_11_1_) {
                  for(int lvt_12_1_ = -1; lvt_12_1_ <= 4; ++lvt_12_1_) {
                     if (lvt_10_1_ != 0 || lvt_12_1_ != 0 || lvt_11_1_ != 0) {
                        int lvt_13_1_ = 100;
                        if (lvt_12_1_ > 1) {
                           lvt_13_1_ += (lvt_12_1_ - 1) * 100;
                        }

                        BlockPos lvt_14_1_ = p_180650_2_.func_177982_a(lvt_10_1_, lvt_12_1_, lvt_11_1_);
                        int lvt_15_1_ = this.func_176538_m(p_180650_1_, lvt_14_1_);
                        if (lvt_15_1_ > 0) {
                           int lvt_16_1_ = (lvt_15_1_ + 40 + p_180650_1_.func_175659_aa().func_151525_a() * 7) / (lvt_7_1_ + 30);
                           if (lvt_8_1_) {
                              lvt_16_1_ /= 2;
                           }

                           if (lvt_16_1_ > 0 && p_180650_4_.nextInt(lvt_13_1_) <= lvt_16_1_ && (!p_180650_1_.func_72896_J() || !this.func_176537_d(p_180650_1_, lvt_14_1_))) {
                              int lvt_17_1_ = lvt_7_1_ + p_180650_4_.nextInt(5) / 4;
                              if (lvt_17_1_ > 15) {
                                 lvt_17_1_ = 15;
                              }

                              p_180650_1_.func_180501_a(lvt_14_1_, p_180650_3_.func_177226_a(field_176543_a, lvt_17_1_), 3);
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   protected boolean func_176537_d(World p_176537_1_, BlockPos p_176537_2_) {
      return p_176537_1_.func_175727_C(p_176537_2_) || p_176537_1_.func_175727_C(p_176537_2_.func_177976_e()) || p_176537_1_.func_175727_C(p_176537_2_.func_177974_f()) || p_176537_1_.func_175727_C(p_176537_2_.func_177978_c()) || p_176537_1_.func_175727_C(p_176537_2_.func_177968_d());
   }

   public boolean func_149698_L() {
      return false;
   }

   private int func_176532_c(Block p_176532_1_) {
      Integer lvt_2_1_ = (Integer)this.field_149848_b.get(p_176532_1_);
      return lvt_2_1_ == null ? 0 : lvt_2_1_;
   }

   private int func_176534_d(Block p_176534_1_) {
      Integer lvt_2_1_ = (Integer)this.field_149849_a.get(p_176534_1_);
      return lvt_2_1_ == null ? 0 : lvt_2_1_;
   }

   private void func_176536_a(World p_176536_1_, BlockPos p_176536_2_, int p_176536_3_, Random p_176536_4_, int p_176536_5_) {
      int lvt_6_1_ = this.func_176532_c(p_176536_1_.func_180495_p(p_176536_2_).func_177230_c());
      if (p_176536_4_.nextInt(p_176536_3_) < lvt_6_1_) {
         IBlockState lvt_7_1_ = p_176536_1_.func_180495_p(p_176536_2_);
         if (p_176536_4_.nextInt(p_176536_5_ + 10) < 5 && !p_176536_1_.func_175727_C(p_176536_2_)) {
            int lvt_8_1_ = p_176536_5_ + p_176536_4_.nextInt(5) / 4;
            if (lvt_8_1_ > 15) {
               lvt_8_1_ = 15;
            }

            p_176536_1_.func_180501_a(p_176536_2_, this.func_176223_P().func_177226_a(field_176543_a, lvt_8_1_), 3);
         } else {
            p_176536_1_.func_175698_g(p_176536_2_);
         }

         if (lvt_7_1_.func_177230_c() == Blocks.field_150335_W) {
            Blocks.field_150335_W.func_176206_d(p_176536_1_, p_176536_2_, lvt_7_1_.func_177226_a(BlockTNT.field_176246_a, true));
         }
      }

   }

   private boolean func_176533_e(World p_176533_1_, BlockPos p_176533_2_) {
      EnumFacing[] lvt_3_1_ = EnumFacing.values();
      int lvt_4_1_ = lvt_3_1_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         EnumFacing lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         if (this.func_176535_e(p_176533_1_, p_176533_2_.func_177972_a(lvt_6_1_))) {
            return true;
         }
      }

      return false;
   }

   private int func_176538_m(World p_176538_1_, BlockPos p_176538_2_) {
      if (!p_176538_1_.func_175623_d(p_176538_2_)) {
         return 0;
      } else {
         int lvt_3_1_ = 0;
         EnumFacing[] lvt_4_1_ = EnumFacing.values();
         int lvt_5_1_ = lvt_4_1_.length;

         for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
            EnumFacing lvt_7_1_ = lvt_4_1_[lvt_6_1_];
            lvt_3_1_ = Math.max(this.func_176534_d(p_176538_1_.func_180495_p(p_176538_2_.func_177972_a(lvt_7_1_)).func_177230_c()), lvt_3_1_);
         }

         return lvt_3_1_;
      }
   }

   public boolean func_149703_v() {
      return false;
   }

   public boolean func_176535_e(IBlockAccess p_176535_1_, BlockPos p_176535_2_) {
      return this.func_176534_d(p_176535_1_.func_180495_p(p_176535_2_).func_177230_c()) > 0;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b()) || this.func_176533_e(p_176196_1_, p_176196_2_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (!World.func_175683_a(p_176204_1_, p_176204_2_.func_177977_b()) && !this.func_176533_e(p_176204_1_, p_176204_2_)) {
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if (p_176213_1_.field_73011_w.func_177502_q() > 0 || !Blocks.field_150427_aO.func_176548_d(p_176213_1_, p_176213_2_)) {
         if (!World.func_175683_a(p_176213_1_, p_176213_2_.func_177977_b()) && !this.func_176533_e(p_176213_1_, p_176213_2_)) {
            p_176213_1_.func_175698_g(p_176213_2_);
         } else {
            p_176213_1_.func_175684_a(p_176213_2_, this, this.func_149738_a(p_176213_1_) + p_176213_1_.field_73012_v.nextInt(10));
         }
      }
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if (p_180655_4_.nextInt(24) == 0) {
         p_180655_1_.func_72980_b((double)((float)p_180655_2_.func_177958_n() + 0.5F), (double)((float)p_180655_2_.func_177956_o() + 0.5F), (double)((float)p_180655_2_.func_177952_p() + 0.5F), "fire.fire", 1.0F + p_180655_4_.nextFloat(), p_180655_4_.nextFloat() * 0.7F + 0.3F, false);
      }

      int lvt_5_6_;
      double lvt_6_6_;
      double lvt_8_6_;
      double lvt_10_6_;
      if (!World.func_175683_a(p_180655_1_, p_180655_2_.func_177977_b()) && !Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177977_b())) {
         if (Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177976_e())) {
            for(lvt_5_6_ = 0; lvt_5_6_ < 2; ++lvt_5_6_) {
               lvt_6_6_ = (double)p_180655_2_.func_177958_n() + p_180655_4_.nextDouble() * 0.10000000149011612D;
               lvt_8_6_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble();
               lvt_10_6_ = (double)p_180655_2_.func_177952_p() + p_180655_4_.nextDouble();
               p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177974_f())) {
            for(lvt_5_6_ = 0; lvt_5_6_ < 2; ++lvt_5_6_) {
               lvt_6_6_ = (double)(p_180655_2_.func_177958_n() + 1) - p_180655_4_.nextDouble() * 0.10000000149011612D;
               lvt_8_6_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble();
               lvt_10_6_ = (double)p_180655_2_.func_177952_p() + p_180655_4_.nextDouble();
               p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177978_c())) {
            for(lvt_5_6_ = 0; lvt_5_6_ < 2; ++lvt_5_6_) {
               lvt_6_6_ = (double)p_180655_2_.func_177958_n() + p_180655_4_.nextDouble();
               lvt_8_6_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble();
               lvt_10_6_ = (double)p_180655_2_.func_177952_p() + p_180655_4_.nextDouble() * 0.10000000149011612D;
               p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177968_d())) {
            for(lvt_5_6_ = 0; lvt_5_6_ < 2; ++lvt_5_6_) {
               lvt_6_6_ = (double)p_180655_2_.func_177958_n() + p_180655_4_.nextDouble();
               lvt_8_6_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble();
               lvt_10_6_ = (double)(p_180655_2_.func_177952_p() + 1) - p_180655_4_.nextDouble() * 0.10000000149011612D;
               p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
            }
         }

         if (Blocks.field_150480_ab.func_176535_e(p_180655_1_, p_180655_2_.func_177984_a())) {
            for(lvt_5_6_ = 0; lvt_5_6_ < 2; ++lvt_5_6_) {
               lvt_6_6_ = (double)p_180655_2_.func_177958_n() + p_180655_4_.nextDouble();
               lvt_8_6_ = (double)(p_180655_2_.func_177956_o() + 1) - p_180655_4_.nextDouble() * 0.10000000149011612D;
               lvt_10_6_ = (double)p_180655_2_.func_177952_p() + p_180655_4_.nextDouble();
               p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
            }
         }
      } else {
         for(lvt_5_6_ = 0; lvt_5_6_ < 3; ++lvt_5_6_) {
            lvt_6_6_ = (double)p_180655_2_.func_177958_n() + p_180655_4_.nextDouble();
            lvt_8_6_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble() * 0.5D + 0.5D;
            lvt_10_6_ = (double)p_180655_2_.func_177952_p() + p_180655_4_.nextDouble();
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, lvt_6_6_, lvt_8_6_, lvt_10_6_, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      return MapColor.field_151656_f;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176543_a, p_176203_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return (Integer)p_176201_1_.func_177229_b(field_176543_a);
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176543_a, field_176545_N, field_176546_O, field_176541_P, field_176539_Q, field_176542_R, field_176540_b, field_176544_M});
   }
}
