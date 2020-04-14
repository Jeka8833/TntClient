package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class ItemMap extends ItemMapBase {
   protected ItemMap() {
      this.func_77627_a(true);
   }

   public static MapData func_150912_a(int p_150912_0_, World p_150912_1_) {
      String lvt_2_1_ = "map_" + p_150912_0_;
      MapData lvt_3_1_ = (MapData)p_150912_1_.func_72943_a(MapData.class, lvt_2_1_);
      if (lvt_3_1_ == null) {
         lvt_3_1_ = new MapData(lvt_2_1_);
         p_150912_1_.func_72823_a(lvt_2_1_, lvt_3_1_);
      }

      return lvt_3_1_;
   }

   public MapData func_77873_a(ItemStack p_77873_1_, World p_77873_2_) {
      String lvt_3_1_ = "map_" + p_77873_1_.func_77960_j();
      MapData lvt_4_1_ = (MapData)p_77873_2_.func_72943_a(MapData.class, lvt_3_1_);
      if (lvt_4_1_ == null && !p_77873_2_.field_72995_K) {
         p_77873_1_.func_77964_b(p_77873_2_.func_72841_b("map"));
         lvt_3_1_ = "map_" + p_77873_1_.func_77960_j();
         lvt_4_1_ = new MapData(lvt_3_1_);
         lvt_4_1_.field_76197_d = 3;
         lvt_4_1_.func_176054_a((double)p_77873_2_.func_72912_H().func_76079_c(), (double)p_77873_2_.func_72912_H().func_76074_e(), lvt_4_1_.field_76197_d);
         lvt_4_1_.field_76200_c = (byte)p_77873_2_.field_73011_w.func_177502_q();
         lvt_4_1_.func_76185_a();
         p_77873_2_.func_72823_a(lvt_3_1_, lvt_4_1_);
      }

      return lvt_4_1_;
   }

   public void func_77872_a(World p_77872_1_, Entity p_77872_2_, MapData p_77872_3_) {
      if (p_77872_1_.field_73011_w.func_177502_q() == p_77872_3_.field_76200_c && p_77872_2_ instanceof EntityPlayer) {
         int lvt_4_1_ = 1 << p_77872_3_.field_76197_d;
         int lvt_5_1_ = p_77872_3_.field_76201_a;
         int lvt_6_1_ = p_77872_3_.field_76199_b;
         int lvt_7_1_ = MathHelper.func_76128_c(p_77872_2_.field_70165_t - (double)lvt_5_1_) / lvt_4_1_ + 64;
         int lvt_8_1_ = MathHelper.func_76128_c(p_77872_2_.field_70161_v - (double)lvt_6_1_) / lvt_4_1_ + 64;
         int lvt_9_1_ = 128 / lvt_4_1_;
         if (p_77872_1_.field_73011_w.func_177495_o()) {
            lvt_9_1_ /= 2;
         }

         MapData.MapInfo lvt_10_1_ = p_77872_3_.func_82568_a((EntityPlayer)p_77872_2_);
         ++lvt_10_1_.field_82569_d;
         boolean lvt_11_1_ = false;

         for(int lvt_12_1_ = lvt_7_1_ - lvt_9_1_ + 1; lvt_12_1_ < lvt_7_1_ + lvt_9_1_; ++lvt_12_1_) {
            if ((lvt_12_1_ & 15) == (lvt_10_1_.field_82569_d & 15) || lvt_11_1_) {
               lvt_11_1_ = false;
               double lvt_13_1_ = 0.0D;

               for(int lvt_15_1_ = lvt_8_1_ - lvt_9_1_ - 1; lvt_15_1_ < lvt_8_1_ + lvt_9_1_; ++lvt_15_1_) {
                  if (lvt_12_1_ >= 0 && lvt_15_1_ >= -1 && lvt_12_1_ < 128 && lvt_15_1_ < 128) {
                     int lvt_16_1_ = lvt_12_1_ - lvt_7_1_;
                     int lvt_17_1_ = lvt_15_1_ - lvt_8_1_;
                     boolean lvt_18_1_ = lvt_16_1_ * lvt_16_1_ + lvt_17_1_ * lvt_17_1_ > (lvt_9_1_ - 2) * (lvt_9_1_ - 2);
                     int lvt_19_1_ = (lvt_5_1_ / lvt_4_1_ + lvt_12_1_ - 64) * lvt_4_1_;
                     int lvt_20_1_ = (lvt_6_1_ / lvt_4_1_ + lvt_15_1_ - 64) * lvt_4_1_;
                     Multiset<MapColor> lvt_21_1_ = HashMultiset.create();
                     Chunk lvt_22_1_ = p_77872_1_.func_175726_f(new BlockPos(lvt_19_1_, 0, lvt_20_1_));
                     if (!lvt_22_1_.func_76621_g()) {
                        int lvt_23_1_ = lvt_19_1_ & 15;
                        int lvt_24_1_ = lvt_20_1_ & 15;
                        int lvt_25_1_ = 0;
                        double lvt_26_1_ = 0.0D;
                        if (p_77872_1_.field_73011_w.func_177495_o()) {
                           int lvt_28_1_ = lvt_19_1_ + lvt_20_1_ * 231871;
                           lvt_28_1_ = lvt_28_1_ * lvt_28_1_ * 31287121 + lvt_28_1_ * 11;
                           if ((lvt_28_1_ >> 20 & 1) == 0) {
                              lvt_21_1_.add(Blocks.field_150346_d.func_180659_g(Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT)), 10);
                           } else {
                              lvt_21_1_.add(Blocks.field_150348_b.func_180659_g(Blocks.field_150348_b.func_176223_P().func_177226_a(BlockStone.field_176247_a, BlockStone.EnumType.STONE)), 100);
                           }

                           lvt_26_1_ = 100.0D;
                        } else {
                           BlockPos.MutableBlockPos lvt_28_2_ = new BlockPos.MutableBlockPos();

                           for(int lvt_29_1_ = 0; lvt_29_1_ < lvt_4_1_; ++lvt_29_1_) {
                              for(int lvt_30_1_ = 0; lvt_30_1_ < lvt_4_1_; ++lvt_30_1_) {
                                 int lvt_31_1_ = lvt_22_1_.func_76611_b(lvt_29_1_ + lvt_23_1_, lvt_30_1_ + lvt_24_1_) + 1;
                                 IBlockState lvt_32_1_ = Blocks.field_150350_a.func_176223_P();
                                 if (lvt_31_1_ > 1) {
                                    do {
                                       --lvt_31_1_;
                                       lvt_32_1_ = lvt_22_1_.func_177435_g(lvt_28_2_.func_181079_c(lvt_29_1_ + lvt_23_1_, lvt_31_1_, lvt_30_1_ + lvt_24_1_));
                                    } while(lvt_32_1_.func_177230_c().func_180659_g(lvt_32_1_) == MapColor.field_151660_b && lvt_31_1_ > 0);

                                    if (lvt_31_1_ > 0 && lvt_32_1_.func_177230_c().func_149688_o().func_76224_d()) {
                                       int lvt_33_1_ = lvt_31_1_ - 1;

                                       Block lvt_34_1_;
                                       do {
                                          lvt_34_1_ = lvt_22_1_.func_177438_a(lvt_29_1_ + lvt_23_1_, lvt_33_1_--, lvt_30_1_ + lvt_24_1_);
                                          ++lvt_25_1_;
                                       } while(lvt_33_1_ > 0 && lvt_34_1_.func_149688_o().func_76224_d());
                                    }
                                 }

                                 lvt_26_1_ += (double)lvt_31_1_ / (double)(lvt_4_1_ * lvt_4_1_);
                                 lvt_21_1_.add(lvt_32_1_.func_177230_c().func_180659_g(lvt_32_1_));
                              }
                           }
                        }

                        lvt_25_1_ /= lvt_4_1_ * lvt_4_1_;
                        double lvt_28_3_ = (lvt_26_1_ - lvt_13_1_) * 4.0D / (double)(lvt_4_1_ + 4) + ((double)(lvt_12_1_ + lvt_15_1_ & 1) - 0.5D) * 0.4D;
                        int lvt_30_2_ = 1;
                        if (lvt_28_3_ > 0.6D) {
                           lvt_30_2_ = 2;
                        }

                        if (lvt_28_3_ < -0.6D) {
                           lvt_30_2_ = 0;
                        }

                        MapColor lvt_31_2_ = (MapColor)Iterables.getFirst(Multisets.copyHighestCountFirst(lvt_21_1_), MapColor.field_151660_b);
                        if (lvt_31_2_ == MapColor.field_151662_n) {
                           lvt_28_3_ = (double)lvt_25_1_ * 0.1D + (double)(lvt_12_1_ + lvt_15_1_ & 1) * 0.2D;
                           lvt_30_2_ = 1;
                           if (lvt_28_3_ < 0.5D) {
                              lvt_30_2_ = 2;
                           }

                           if (lvt_28_3_ > 0.9D) {
                              lvt_30_2_ = 0;
                           }
                        }

                        lvt_13_1_ = lvt_26_1_;
                        if (lvt_15_1_ >= 0 && lvt_16_1_ * lvt_16_1_ + lvt_17_1_ * lvt_17_1_ < lvt_9_1_ * lvt_9_1_ && (!lvt_18_1_ || (lvt_12_1_ + lvt_15_1_ & 1) != 0)) {
                           byte lvt_32_2_ = p_77872_3_.field_76198_e[lvt_12_1_ + lvt_15_1_ * 128];
                           byte lvt_33_2_ = (byte)(lvt_31_2_.field_76290_q * 4 + lvt_30_2_);
                           if (lvt_32_2_ != lvt_33_2_) {
                              p_77872_3_.field_76198_e[lvt_12_1_ + lvt_15_1_ * 128] = lvt_33_2_;
                              p_77872_3_.func_176053_a(lvt_12_1_, lvt_15_1_);
                              lvt_11_1_ = true;
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
      if (!p_77663_2_.field_72995_K) {
         MapData lvt_6_1_ = this.func_77873_a(p_77663_1_, p_77663_2_);
         if (p_77663_3_ instanceof EntityPlayer) {
            EntityPlayer lvt_7_1_ = (EntityPlayer)p_77663_3_;
            lvt_6_1_.func_76191_a(lvt_7_1_, p_77663_1_);
         }

         if (p_77663_5_) {
            this.func_77872_a(p_77663_2_, p_77663_3_, lvt_6_1_);
         }

      }
   }

   public Packet func_150911_c(ItemStack p_150911_1_, World p_150911_2_, EntityPlayer p_150911_3_) {
      return this.func_77873_a(p_150911_1_, p_150911_2_).func_176052_a(p_150911_1_, p_150911_2_, p_150911_3_);
   }

   public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
      if (p_77622_1_.func_77942_o() && p_77622_1_.func_77978_p().func_74767_n("map_is_scaling")) {
         MapData lvt_4_1_ = Items.field_151098_aY.func_77873_a(p_77622_1_, p_77622_2_);
         p_77622_1_.func_77964_b(p_77622_2_.func_72841_b("map"));
         MapData lvt_5_1_ = new MapData("map_" + p_77622_1_.func_77960_j());
         lvt_5_1_.field_76197_d = (byte)(lvt_4_1_.field_76197_d + 1);
         if (lvt_5_1_.field_76197_d > 4) {
            lvt_5_1_.field_76197_d = 4;
         }

         lvt_5_1_.func_176054_a((double)lvt_4_1_.field_76201_a, (double)lvt_4_1_.field_76199_b, lvt_5_1_.field_76197_d);
         lvt_5_1_.field_76200_c = lvt_4_1_.field_76200_c;
         lvt_5_1_.func_76185_a();
         p_77622_2_.func_72823_a("map_" + p_77622_1_.func_77960_j(), lvt_5_1_);
      }

   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      MapData lvt_5_1_ = this.func_77873_a(p_77624_1_, p_77624_2_.field_70170_p);
      if (p_77624_4_) {
         if (lvt_5_1_ == null) {
            p_77624_3_.add("Unknown map");
         } else {
            p_77624_3_.add("Scaling at 1:" + (1 << lvt_5_1_.field_76197_d));
            p_77624_3_.add("(Level " + lvt_5_1_.field_76197_d + "/" + 4 + ")");
         }
      }

   }
}
