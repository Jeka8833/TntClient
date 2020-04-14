package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public abstract class BlockLeaves extends BlockLeavesBase {
   public static final PropertyBool field_176237_a = PropertyBool.func_177716_a("decayable");
   public static final PropertyBool field_176236_b = PropertyBool.func_177716_a("check_decay");
   int[] field_150128_a;
   protected int field_150127_b;
   protected boolean field_176238_O;

   public BlockLeaves() {
      super(Material.field_151584_j, false);
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_149711_c(0.2F);
      this.func_149713_g(1);
      this.func_149672_a(field_149779_h);
   }

   public int func_149635_D() {
      return ColorizerFoliage.func_77470_a(0.5D, 1.0D);
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return BiomeColorHelper.func_180287_b(p_180662_1_, p_180662_2_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      int lvt_4_1_ = 1;
      int lvt_5_1_ = lvt_4_1_ + 1;
      int lvt_6_1_ = p_180663_2_.func_177958_n();
      int lvt_7_1_ = p_180663_2_.func_177956_o();
      int lvt_8_1_ = p_180663_2_.func_177952_p();
      if (p_180663_1_.func_175707_a(new BlockPos(lvt_6_1_ - lvt_5_1_, lvt_7_1_ - lvt_5_1_, lvt_8_1_ - lvt_5_1_), new BlockPos(lvt_6_1_ + lvt_5_1_, lvt_7_1_ + lvt_5_1_, lvt_8_1_ + lvt_5_1_))) {
         for(int lvt_9_1_ = -lvt_4_1_; lvt_9_1_ <= lvt_4_1_; ++lvt_9_1_) {
            for(int lvt_10_1_ = -lvt_4_1_; lvt_10_1_ <= lvt_4_1_; ++lvt_10_1_) {
               for(int lvt_11_1_ = -lvt_4_1_; lvt_11_1_ <= lvt_4_1_; ++lvt_11_1_) {
                  BlockPos lvt_12_1_ = p_180663_2_.func_177982_a(lvt_9_1_, lvt_10_1_, lvt_11_1_);
                  IBlockState lvt_13_1_ = p_180663_1_.func_180495_p(lvt_12_1_);
                  if (lvt_13_1_.func_177230_c().func_149688_o() == Material.field_151584_j && !(Boolean)lvt_13_1_.func_177229_b(field_176236_b)) {
                     p_180663_1_.func_180501_a(lvt_12_1_, lvt_13_1_.func_177226_a(field_176236_b, true), 4);
                  }
               }
            }
         }
      }

   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (!p_180650_1_.field_72995_K) {
         if ((Boolean)p_180650_3_.func_177229_b(field_176236_b) && (Boolean)p_180650_3_.func_177229_b(field_176237_a)) {
            int lvt_5_1_ = 4;
            int lvt_6_1_ = lvt_5_1_ + 1;
            int lvt_7_1_ = p_180650_2_.func_177958_n();
            int lvt_8_1_ = p_180650_2_.func_177956_o();
            int lvt_9_1_ = p_180650_2_.func_177952_p();
            int lvt_10_1_ = 32;
            int lvt_11_1_ = lvt_10_1_ * lvt_10_1_;
            int lvt_12_1_ = lvt_10_1_ / 2;
            if (this.field_150128_a == null) {
               this.field_150128_a = new int[lvt_10_1_ * lvt_10_1_ * lvt_10_1_];
            }

            if (p_180650_1_.func_175707_a(new BlockPos(lvt_7_1_ - lvt_6_1_, lvt_8_1_ - lvt_6_1_, lvt_9_1_ - lvt_6_1_), new BlockPos(lvt_7_1_ + lvt_6_1_, lvt_8_1_ + lvt_6_1_, lvt_9_1_ + lvt_6_1_))) {
               BlockPos.MutableBlockPos lvt_13_1_ = new BlockPos.MutableBlockPos();
               int lvt_14_2_ = -lvt_5_1_;

               label116:
               while(true) {
                  int lvt_15_2_;
                  int lvt_16_2_;
                  if (lvt_14_2_ > lvt_5_1_) {
                     lvt_14_2_ = 1;

                     while(true) {
                        if (lvt_14_2_ > 4) {
                           break label116;
                        }

                        for(lvt_15_2_ = -lvt_5_1_; lvt_15_2_ <= lvt_5_1_; ++lvt_15_2_) {
                           for(lvt_16_2_ = -lvt_5_1_; lvt_16_2_ <= lvt_5_1_; ++lvt_16_2_) {
                              for(int lvt_17_2_ = -lvt_5_1_; lvt_17_2_ <= lvt_5_1_; ++lvt_17_2_) {
                                 if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] == lvt_14_2_ - 1) {
                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_ - 1) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_ - 1) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] = lvt_14_2_;
                                    }

                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_ + 1) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_ + 1) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] = lvt_14_2_;
                                    }

                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_ - 1) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_ - 1) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] = lvt_14_2_;
                                    }

                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_ + 1) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_ + 1) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_] = lvt_14_2_;
                                    }

                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + (lvt_17_2_ + lvt_12_1_ - 1)] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + (lvt_17_2_ + lvt_12_1_ - 1)] = lvt_14_2_;
                                    }

                                    if (this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_ + 1] == -2) {
                                       this.field_150128_a[(lvt_15_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_16_2_ + lvt_12_1_) * lvt_10_1_ + lvt_17_2_ + lvt_12_1_ + 1] = lvt_14_2_;
                                    }
                                 }
                              }
                           }
                        }

                        ++lvt_14_2_;
                     }
                  }

                  for(lvt_15_2_ = -lvt_5_1_; lvt_15_2_ <= lvt_5_1_; ++lvt_15_2_) {
                     for(lvt_16_2_ = -lvt_5_1_; lvt_16_2_ <= lvt_5_1_; ++lvt_16_2_) {
                        Block lvt_17_1_ = p_180650_1_.func_180495_p(lvt_13_1_.func_181079_c(lvt_7_1_ + lvt_14_2_, lvt_8_1_ + lvt_15_2_, lvt_9_1_ + lvt_16_2_)).func_177230_c();
                        if (lvt_17_1_ != Blocks.field_150364_r && lvt_17_1_ != Blocks.field_150363_s) {
                           if (lvt_17_1_.func_149688_o() == Material.field_151584_j) {
                              this.field_150128_a[(lvt_14_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_15_2_ + lvt_12_1_) * lvt_10_1_ + lvt_16_2_ + lvt_12_1_] = -2;
                           } else {
                              this.field_150128_a[(lvt_14_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_15_2_ + lvt_12_1_) * lvt_10_1_ + lvt_16_2_ + lvt_12_1_] = -1;
                           }
                        } else {
                           this.field_150128_a[(lvt_14_2_ + lvt_12_1_) * lvt_11_1_ + (lvt_15_2_ + lvt_12_1_) * lvt_10_1_ + lvt_16_2_ + lvt_12_1_] = 0;
                        }
                     }
                  }

                  ++lvt_14_2_;
               }
            }

            int lvt_13_2_ = this.field_150128_a[lvt_12_1_ * lvt_11_1_ + lvt_12_1_ * lvt_10_1_ + lvt_12_1_];
            if (lvt_13_2_ >= 0) {
               p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176236_b, false), 4);
            } else {
               this.func_176235_d(p_180650_1_, p_180650_2_);
            }
         }

      }
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if (p_180655_1_.func_175727_C(p_180655_2_.func_177984_a()) && !World.func_175683_a(p_180655_1_, p_180655_2_.func_177977_b()) && p_180655_4_.nextInt(15) == 1) {
         double lvt_5_1_ = (double)((float)p_180655_2_.func_177958_n() + p_180655_4_.nextFloat());
         double lvt_7_1_ = (double)p_180655_2_.func_177956_o() - 0.05D;
         double lvt_9_1_ = (double)((float)p_180655_2_.func_177952_p() + p_180655_4_.nextFloat());
         p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_WATER, lvt_5_1_, lvt_7_1_, lvt_9_1_, 0.0D, 0.0D, 0.0D);
      }

   }

   private void func_176235_d(World p_176235_1_, BlockPos p_176235_2_) {
      this.func_176226_b(p_176235_1_, p_176235_2_, p_176235_1_.func_180495_p(p_176235_2_), 0);
      p_176235_1_.func_175698_g(p_176235_2_);
   }

   public int func_149745_a(Random p_149745_1_) {
      return p_149745_1_.nextInt(20) == 0 ? 1 : 0;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150345_g);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      if (!p_180653_1_.field_72995_K) {
         int lvt_6_1_ = this.func_176232_d(p_180653_3_);
         if (p_180653_5_ > 0) {
            lvt_6_1_ -= 2 << p_180653_5_;
            if (lvt_6_1_ < 10) {
               lvt_6_1_ = 10;
            }
         }

         if (p_180653_1_.field_73012_v.nextInt(lvt_6_1_) == 0) {
            Item lvt_7_1_ = this.func_180660_a(p_180653_3_, p_180653_1_.field_73012_v, p_180653_5_);
            func_180635_a(p_180653_1_, p_180653_2_, new ItemStack(lvt_7_1_, 1, this.func_180651_a(p_180653_3_)));
         }

         lvt_6_1_ = 200;
         if (p_180653_5_ > 0) {
            lvt_6_1_ -= 10 << p_180653_5_;
            if (lvt_6_1_ < 40) {
               lvt_6_1_ = 40;
            }
         }

         this.func_176234_a(p_180653_1_, p_180653_2_, p_180653_3_, lvt_6_1_);
      }

   }

   protected void func_176234_a(World p_176234_1_, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_) {
   }

   protected int func_176232_d(IBlockState p_176232_1_) {
      return 20;
   }

   public boolean func_149662_c() {
      return !this.field_150121_P;
   }

   public void func_150122_b(boolean p_150122_1_) {
      this.field_176238_O = p_150122_1_;
      this.field_150121_P = p_150122_1_;
      this.field_150127_b = p_150122_1_ ? 0 : 1;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return this.field_176238_O ? EnumWorldBlockLayer.CUTOUT_MIPPED : EnumWorldBlockLayer.SOLID;
   }

   public boolean func_176214_u() {
      return false;
   }

   public abstract BlockPlanks.EnumType func_176233_b(int var1);
}
