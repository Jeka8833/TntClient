package net.minecraft.block;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDynamicLiquid extends BlockLiquid {
   int field_149815_a;

   protected BlockDynamicLiquid(Material p_i45403_1_) {
      super(p_i45403_1_);
   }

   private void func_180690_f(World p_180690_1_, BlockPos p_180690_2_, IBlockState p_180690_3_) {
      p_180690_1_.func_180501_a(p_180690_2_, func_176363_b(this.field_149764_J).func_176223_P().func_177226_a(field_176367_b, p_180690_3_.func_177229_b(field_176367_b)), 2);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      int lvt_5_1_ = (Integer)p_180650_3_.func_177229_b(field_176367_b);
      int lvt_6_1_ = 1;
      if (this.field_149764_J == Material.field_151587_i && !p_180650_1_.field_73011_w.func_177500_n()) {
         lvt_6_1_ = 2;
      }

      int lvt_7_1_ = this.func_149738_a(p_180650_1_);
      int lvt_10_4_;
      if (lvt_5_1_ > 0) {
         int lvt_8_1_ = -100;
         this.field_149815_a = 0;

         EnumFacing lvt_10_1_;
         for(Iterator lvt_9_1_ = EnumFacing.Plane.HORIZONTAL.iterator(); lvt_9_1_.hasNext(); lvt_8_1_ = this.func_176371_a(p_180650_1_, p_180650_2_.func_177972_a(lvt_10_1_), lvt_8_1_)) {
            lvt_10_1_ = (EnumFacing)lvt_9_1_.next();
         }

         int lvt_9_2_ = lvt_8_1_ + lvt_6_1_;
         if (lvt_9_2_ >= 8 || lvt_8_1_ < 0) {
            lvt_9_2_ = -1;
         }

         if (this.func_176362_e(p_180650_1_, p_180650_2_.func_177984_a()) >= 0) {
            lvt_10_4_ = this.func_176362_e(p_180650_1_, p_180650_2_.func_177984_a());
            if (lvt_10_4_ >= 8) {
               lvt_9_2_ = lvt_10_4_;
            } else {
               lvt_9_2_ = lvt_10_4_ + 8;
            }
         }

         if (this.field_149815_a >= 2 && this.field_149764_J == Material.field_151586_h) {
            IBlockState lvt_10_3_ = p_180650_1_.func_180495_p(p_180650_2_.func_177977_b());
            if (lvt_10_3_.func_177230_c().func_149688_o().func_76220_a()) {
               lvt_9_2_ = 0;
            } else if (lvt_10_3_.func_177230_c().func_149688_o() == this.field_149764_J && (Integer)lvt_10_3_.func_177229_b(field_176367_b) == 0) {
               lvt_9_2_ = 0;
            }
         }

         if (this.field_149764_J == Material.field_151587_i && lvt_5_1_ < 8 && lvt_9_2_ < 8 && lvt_9_2_ > lvt_5_1_ && p_180650_4_.nextInt(4) != 0) {
            lvt_7_1_ *= 4;
         }

         if (lvt_9_2_ == lvt_5_1_) {
            this.func_180690_f(p_180650_1_, p_180650_2_, p_180650_3_);
         } else {
            lvt_5_1_ = lvt_9_2_;
            if (lvt_9_2_ < 0) {
               p_180650_1_.func_175698_g(p_180650_2_);
            } else {
               p_180650_3_ = p_180650_3_.func_177226_a(field_176367_b, lvt_9_2_);
               p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_, 2);
               p_180650_1_.func_175684_a(p_180650_2_, this, lvt_7_1_);
               p_180650_1_.func_175685_c(p_180650_2_, this);
            }
         }
      } else {
         this.func_180690_f(p_180650_1_, p_180650_2_, p_180650_3_);
      }

      IBlockState lvt_8_2_ = p_180650_1_.func_180495_p(p_180650_2_.func_177977_b());
      if (this.func_176373_h(p_180650_1_, p_180650_2_.func_177977_b(), lvt_8_2_)) {
         if (this.field_149764_J == Material.field_151587_i && p_180650_1_.func_180495_p(p_180650_2_.func_177977_b()).func_177230_c().func_149688_o() == Material.field_151586_h) {
            p_180650_1_.func_175656_a(p_180650_2_.func_177977_b(), Blocks.field_150348_b.func_176223_P());
            this.func_180688_d(p_180650_1_, p_180650_2_.func_177977_b());
            return;
         }

         if (lvt_5_1_ >= 8) {
            this.func_176375_a(p_180650_1_, p_180650_2_.func_177977_b(), lvt_8_2_, lvt_5_1_);
         } else {
            this.func_176375_a(p_180650_1_, p_180650_2_.func_177977_b(), lvt_8_2_, lvt_5_1_ + 8);
         }
      } else if (lvt_5_1_ >= 0 && (lvt_5_1_ == 0 || this.func_176372_g(p_180650_1_, p_180650_2_.func_177977_b(), lvt_8_2_))) {
         Set<EnumFacing> lvt_9_3_ = this.func_176376_e(p_180650_1_, p_180650_2_);
         lvt_10_4_ = lvt_5_1_ + lvt_6_1_;
         if (lvt_5_1_ >= 8) {
            lvt_10_4_ = 1;
         }

         if (lvt_10_4_ >= 8) {
            return;
         }

         Iterator lvt_11_1_ = lvt_9_3_.iterator();

         while(lvt_11_1_.hasNext()) {
            EnumFacing lvt_12_1_ = (EnumFacing)lvt_11_1_.next();
            this.func_176375_a(p_180650_1_, p_180650_2_.func_177972_a(lvt_12_1_), p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(lvt_12_1_)), lvt_10_4_);
         }
      }

   }

   private void func_176375_a(World p_176375_1_, BlockPos p_176375_2_, IBlockState p_176375_3_, int p_176375_4_) {
      if (this.func_176373_h(p_176375_1_, p_176375_2_, p_176375_3_)) {
         if (p_176375_3_.func_177230_c() != Blocks.field_150350_a) {
            if (this.field_149764_J == Material.field_151587_i) {
               this.func_180688_d(p_176375_1_, p_176375_2_);
            } else {
               p_176375_3_.func_177230_c().func_176226_b(p_176375_1_, p_176375_2_, p_176375_3_, 0);
            }
         }

         p_176375_1_.func_180501_a(p_176375_2_, this.func_176223_P().func_177226_a(field_176367_b, p_176375_4_), 3);
      }

   }

   private int func_176374_a(World p_176374_1_, BlockPos p_176374_2_, int p_176374_3_, EnumFacing p_176374_4_) {
      int lvt_5_1_ = 1000;
      Iterator lvt_6_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         EnumFacing lvt_7_1_;
         BlockPos lvt_8_1_;
         IBlockState lvt_9_1_;
         do {
            do {
               do {
                  if (!lvt_6_1_.hasNext()) {
                     return lvt_5_1_;
                  }

                  lvt_7_1_ = (EnumFacing)lvt_6_1_.next();
               } while(lvt_7_1_ == p_176374_4_);

               lvt_8_1_ = p_176374_2_.func_177972_a(lvt_7_1_);
               lvt_9_1_ = p_176374_1_.func_180495_p(lvt_8_1_);
            } while(this.func_176372_g(p_176374_1_, lvt_8_1_, lvt_9_1_));
         } while(lvt_9_1_.func_177230_c().func_149688_o() == this.field_149764_J && (Integer)lvt_9_1_.func_177229_b(field_176367_b) <= 0);

         if (!this.func_176372_g(p_176374_1_, lvt_8_1_.func_177977_b(), lvt_9_1_)) {
            return p_176374_3_;
         }

         if (p_176374_3_ < 4) {
            int lvt_10_1_ = this.func_176374_a(p_176374_1_, lvt_8_1_, p_176374_3_ + 1, lvt_7_1_.func_176734_d());
            if (lvt_10_1_ < lvt_5_1_) {
               lvt_5_1_ = lvt_10_1_;
            }
         }
      }
   }

   private Set<EnumFacing> func_176376_e(World p_176376_1_, BlockPos p_176376_2_) {
      int lvt_3_1_ = 1000;
      Set<EnumFacing> lvt_4_1_ = EnumSet.noneOf(EnumFacing.class);
      Iterator lvt_5_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         EnumFacing lvt_6_1_;
         BlockPos lvt_7_1_;
         IBlockState lvt_8_1_;
         do {
            do {
               if (!lvt_5_1_.hasNext()) {
                  return lvt_4_1_;
               }

               lvt_6_1_ = (EnumFacing)lvt_5_1_.next();
               lvt_7_1_ = p_176376_2_.func_177972_a(lvt_6_1_);
               lvt_8_1_ = p_176376_1_.func_180495_p(lvt_7_1_);
            } while(this.func_176372_g(p_176376_1_, lvt_7_1_, lvt_8_1_));
         } while(lvt_8_1_.func_177230_c().func_149688_o() == this.field_149764_J && (Integer)lvt_8_1_.func_177229_b(field_176367_b) <= 0);

         int lvt_9_2_;
         if (this.func_176372_g(p_176376_1_, lvt_7_1_.func_177977_b(), p_176376_1_.func_180495_p(lvt_7_1_.func_177977_b()))) {
            lvt_9_2_ = this.func_176374_a(p_176376_1_, lvt_7_1_, 1, lvt_6_1_.func_176734_d());
         } else {
            lvt_9_2_ = 0;
         }

         if (lvt_9_2_ < lvt_3_1_) {
            lvt_4_1_.clear();
         }

         if (lvt_9_2_ <= lvt_3_1_) {
            lvt_4_1_.add(lvt_6_1_);
            lvt_3_1_ = lvt_9_2_;
         }
      }
   }

   private boolean func_176372_g(World p_176372_1_, BlockPos p_176372_2_, IBlockState p_176372_3_) {
      Block lvt_4_1_ = p_176372_1_.func_180495_p(p_176372_2_).func_177230_c();
      if (!(lvt_4_1_ instanceof BlockDoor) && lvt_4_1_ != Blocks.field_150472_an && lvt_4_1_ != Blocks.field_150468_ap && lvt_4_1_ != Blocks.field_150436_aH) {
         return lvt_4_1_.field_149764_J == Material.field_151567_E ? true : lvt_4_1_.field_149764_J.func_76230_c();
      } else {
         return true;
      }
   }

   protected int func_176371_a(World p_176371_1_, BlockPos p_176371_2_, int p_176371_3_) {
      int lvt_4_1_ = this.func_176362_e(p_176371_1_, p_176371_2_);
      if (lvt_4_1_ < 0) {
         return p_176371_3_;
      } else {
         if (lvt_4_1_ == 0) {
            ++this.field_149815_a;
         }

         if (lvt_4_1_ >= 8) {
            lvt_4_1_ = 0;
         }

         return p_176371_3_ >= 0 && lvt_4_1_ >= p_176371_3_ ? p_176371_3_ : lvt_4_1_;
      }
   }

   private boolean func_176373_h(World p_176373_1_, BlockPos p_176373_2_, IBlockState p_176373_3_) {
      Material lvt_4_1_ = p_176373_3_.func_177230_c().func_149688_o();
      return lvt_4_1_ != this.field_149764_J && lvt_4_1_ != Material.field_151587_i && !this.func_176372_g(p_176373_1_, p_176373_2_, p_176373_3_);
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if (!this.func_176365_e(p_176213_1_, p_176213_2_, p_176213_3_)) {
         p_176213_1_.func_175684_a(p_176213_2_, this, this.func_149738_a(p_176213_1_));
      }

   }
}
