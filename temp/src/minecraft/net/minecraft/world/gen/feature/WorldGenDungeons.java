package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenDungeons extends WorldGenerator {
   private static final Logger field_175918_a = LogManager.getLogger();
   private static final String[] field_175916_b = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};
   private static final List<WeightedRandomChestContent> field_175917_c;

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = true;
      int lvt_5_1_ = p_180709_2_.nextInt(2) + 2;
      int lvt_6_1_ = -lvt_5_1_ - 1;
      int lvt_7_1_ = lvt_5_1_ + 1;
      int lvt_8_1_ = true;
      int lvt_9_1_ = true;
      int lvt_10_1_ = p_180709_2_.nextInt(2) + 2;
      int lvt_11_1_ = -lvt_10_1_ - 1;
      int lvt_12_1_ = lvt_10_1_ + 1;
      int lvt_13_1_ = 0;

      int lvt_14_2_;
      int lvt_15_2_;
      int lvt_16_2_;
      BlockPos lvt_17_2_;
      for(lvt_14_2_ = lvt_6_1_; lvt_14_2_ <= lvt_7_1_; ++lvt_14_2_) {
         for(lvt_15_2_ = -1; lvt_15_2_ <= 4; ++lvt_15_2_) {
            for(lvt_16_2_ = lvt_11_1_; lvt_16_2_ <= lvt_12_1_; ++lvt_16_2_) {
               lvt_17_2_ = p_180709_3_.func_177982_a(lvt_14_2_, lvt_15_2_, lvt_16_2_);
               Material lvt_18_1_ = p_180709_1_.func_180495_p(lvt_17_2_).func_177230_c().func_149688_o();
               boolean lvt_19_1_ = lvt_18_1_.func_76220_a();
               if (lvt_15_2_ == -1 && !lvt_19_1_) {
                  return false;
               }

               if (lvt_15_2_ == 4 && !lvt_19_1_) {
                  return false;
               }

               if ((lvt_14_2_ == lvt_6_1_ || lvt_14_2_ == lvt_7_1_ || lvt_16_2_ == lvt_11_1_ || lvt_16_2_ == lvt_12_1_) && lvt_15_2_ == 0 && p_180709_1_.func_175623_d(lvt_17_2_) && p_180709_1_.func_175623_d(lvt_17_2_.func_177984_a())) {
                  ++lvt_13_1_;
               }
            }
         }
      }

      if (lvt_13_1_ >= 1 && lvt_13_1_ <= 5) {
         for(lvt_14_2_ = lvt_6_1_; lvt_14_2_ <= lvt_7_1_; ++lvt_14_2_) {
            for(lvt_15_2_ = 3; lvt_15_2_ >= -1; --lvt_15_2_) {
               for(lvt_16_2_ = lvt_11_1_; lvt_16_2_ <= lvt_12_1_; ++lvt_16_2_) {
                  lvt_17_2_ = p_180709_3_.func_177982_a(lvt_14_2_, lvt_15_2_, lvt_16_2_);
                  if (lvt_14_2_ != lvt_6_1_ && lvt_15_2_ != -1 && lvt_16_2_ != lvt_11_1_ && lvt_14_2_ != lvt_7_1_ && lvt_15_2_ != 4 && lvt_16_2_ != lvt_12_1_) {
                     if (p_180709_1_.func_180495_p(lvt_17_2_).func_177230_c() != Blocks.field_150486_ae) {
                        p_180709_1_.func_175698_g(lvt_17_2_);
                     }
                  } else if (lvt_17_2_.func_177956_o() >= 0 && !p_180709_1_.func_180495_p(lvt_17_2_.func_177977_b()).func_177230_c().func_149688_o().func_76220_a()) {
                     p_180709_1_.func_175698_g(lvt_17_2_);
                  } else if (p_180709_1_.func_180495_p(lvt_17_2_).func_177230_c().func_149688_o().func_76220_a() && p_180709_1_.func_180495_p(lvt_17_2_).func_177230_c() != Blocks.field_150486_ae) {
                     if (lvt_15_2_ == -1 && p_180709_2_.nextInt(4) != 0) {
                        p_180709_1_.func_180501_a(lvt_17_2_, Blocks.field_150341_Y.func_176223_P(), 2);
                     } else {
                        p_180709_1_.func_180501_a(lvt_17_2_, Blocks.field_150347_e.func_176223_P(), 2);
                     }
                  }
               }
            }
         }

         for(lvt_14_2_ = 0; lvt_14_2_ < 2; ++lvt_14_2_) {
            for(lvt_15_2_ = 0; lvt_15_2_ < 3; ++lvt_15_2_) {
               lvt_16_2_ = p_180709_3_.func_177958_n() + p_180709_2_.nextInt(lvt_5_1_ * 2 + 1) - lvt_5_1_;
               int lvt_17_3_ = p_180709_3_.func_177956_o();
               int lvt_18_2_ = p_180709_3_.func_177952_p() + p_180709_2_.nextInt(lvt_10_1_ * 2 + 1) - lvt_10_1_;
               BlockPos lvt_19_2_ = new BlockPos(lvt_16_2_, lvt_17_3_, lvt_18_2_);
               if (p_180709_1_.func_175623_d(lvt_19_2_)) {
                  int lvt_20_1_ = 0;
                  Iterator lvt_21_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(lvt_21_1_.hasNext()) {
                     EnumFacing lvt_22_1_ = (EnumFacing)lvt_21_1_.next();
                     if (p_180709_1_.func_180495_p(lvt_19_2_.func_177972_a(lvt_22_1_)).func_177230_c().func_149688_o().func_76220_a()) {
                        ++lvt_20_1_;
                     }
                  }

                  if (lvt_20_1_ == 1) {
                     p_180709_1_.func_180501_a(lvt_19_2_, Blocks.field_150486_ae.func_176458_f(p_180709_1_, lvt_19_2_, Blocks.field_150486_ae.func_176223_P()), 2);
                     List<WeightedRandomChestContent> lvt_21_2_ = WeightedRandomChestContent.func_177629_a(field_175917_c, Items.field_151134_bR.func_92114_b(p_180709_2_));
                     TileEntity lvt_22_2_ = p_180709_1_.func_175625_s(lvt_19_2_);
                     if (lvt_22_2_ instanceof TileEntityChest) {
                        WeightedRandomChestContent.func_177630_a(p_180709_2_, lvt_21_2_, (TileEntityChest)lvt_22_2_, 8);
                     }
                     break;
                  }
               }
            }
         }

         p_180709_1_.func_180501_a(p_180709_3_, Blocks.field_150474_ac.func_176223_P(), 2);
         TileEntity lvt_14_4_ = p_180709_1_.func_175625_s(p_180709_3_);
         if (lvt_14_4_ instanceof TileEntityMobSpawner) {
            ((TileEntityMobSpawner)lvt_14_4_).func_145881_a().func_98272_a(this.func_76543_b(p_180709_2_));
         } else {
            field_175918_a.error("Failed to fetch mob spawner entity at (" + p_180709_3_.func_177958_n() + ", " + p_180709_3_.func_177956_o() + ", " + p_180709_3_.func_177952_p() + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String func_76543_b(Random p_76543_1_) {
      return field_175916_b[p_76543_1_.nextInt(field_175916_b.length)];
   }

   static {
      field_175917_c = Lists.newArrayList((Object[])(new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151015_O, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151016_H, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151007_F, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151133_ar, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151153_ao, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151137_ax, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151096_cd, 0, 1, 1, 4), new WeightedRandomChestContent(Items.field_151093_ce, 0, 1, 1, 4), new WeightedRandomChestContent(Items.field_151057_cb, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 2), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 1)));
   }
}
