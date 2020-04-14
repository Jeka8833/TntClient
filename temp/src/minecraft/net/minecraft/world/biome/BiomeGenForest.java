package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

public class BiomeGenForest extends BiomeGenBase {
   private int field_150632_aF;
   protected static final WorldGenForest field_150629_aC = new WorldGenForest(false, true);
   protected static final WorldGenForest field_150630_aD = new WorldGenForest(false, false);
   protected static final WorldGenCanopyTree field_150631_aE = new WorldGenCanopyTree(false);

   public BiomeGenForest(int p_i45377_1_, int p_i45377_2_) {
      super(p_i45377_1_);
      this.field_150632_aF = p_i45377_2_;
      this.field_76760_I.field_76832_z = 10;
      this.field_76760_I.field_76803_B = 2;
      if (this.field_150632_aF == 1) {
         this.field_76760_I.field_76832_z = 6;
         this.field_76760_I.field_76802_A = 100;
         this.field_76760_I.field_76803_B = 1;
      }

      this.func_76733_a(5159473);
      this.func_76732_a(0.7F, 0.8F);
      if (this.field_150632_aF == 2) {
         this.field_150609_ah = 353825;
         this.field_76790_z = 3175492;
         this.func_76732_a(0.6F, 0.6F);
      }

      if (this.field_150632_aF == 0) {
         this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
      }

      if (this.field_150632_aF == 3) {
         this.field_76760_I.field_76832_z = -999;
      }

   }

   protected BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_) {
      if (this.field_150632_aF == 2) {
         this.field_150609_ah = 353825;
         this.field_76790_z = p_150557_1_;
         if (p_150557_2_) {
            this.field_150609_ah = (this.field_150609_ah & 16711422) >> 1;
         }

         return this;
      } else {
         return super.func_150557_a(p_150557_1_, p_150557_2_);
      }
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      if (this.field_150632_aF == 3 && p_150567_1_.nextInt(3) > 0) {
         return field_150631_aE;
      } else {
         return (WorldGenAbstractTree)(this.field_150632_aF != 2 && p_150567_1_.nextInt(5) != 0 ? this.field_76757_N : field_150630_aD);
      }
   }

   public BlockFlower.EnumFlowerType func_180623_a(Random p_180623_1_, BlockPos p_180623_2_) {
      if (this.field_150632_aF == 1) {
         double lvt_3_1_ = MathHelper.func_151237_a((1.0D + field_180281_af.func_151601_a((double)p_180623_2_.func_177958_n() / 48.0D, (double)p_180623_2_.func_177952_p() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
         BlockFlower.EnumFlowerType lvt_5_1_ = BlockFlower.EnumFlowerType.values()[(int)(lvt_3_1_ * (double)BlockFlower.EnumFlowerType.values().length)];
         return lvt_5_1_ == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : lvt_5_1_;
      } else {
         return super.func_180623_a(p_180623_1_, p_180623_2_);
      }
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      int lvt_4_1_;
      int lvt_5_1_;
      int lvt_6_2_;
      int lvt_7_2_;
      if (this.field_150632_aF == 3) {
         for(lvt_4_1_ = 0; lvt_4_1_ < 4; ++lvt_4_1_) {
            for(lvt_5_1_ = 0; lvt_5_1_ < 4; ++lvt_5_1_) {
               lvt_6_2_ = lvt_4_1_ * 4 + 1 + 8 + p_180624_2_.nextInt(3);
               lvt_7_2_ = lvt_5_1_ * 4 + 1 + 8 + p_180624_2_.nextInt(3);
               BlockPos lvt_8_1_ = p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(lvt_6_2_, 0, lvt_7_2_));
               if (p_180624_2_.nextInt(20) == 0) {
                  WorldGenBigMushroom lvt_9_1_ = new WorldGenBigMushroom();
                  lvt_9_1_.func_180709_b(p_180624_1_, p_180624_2_, lvt_8_1_);
               } else {
                  WorldGenAbstractTree lvt_9_2_ = this.func_150567_a(p_180624_2_);
                  lvt_9_2_.func_175904_e();
                  if (lvt_9_2_.func_180709_b(p_180624_1_, p_180624_2_, lvt_8_1_)) {
                     lvt_9_2_.func_180711_a(p_180624_1_, p_180624_2_, lvt_8_1_);
                  }
               }
            }
         }
      }

      lvt_4_1_ = p_180624_2_.nextInt(5) - 3;
      if (this.field_150632_aF == 1) {
         lvt_4_1_ += 2;
      }

      for(lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         lvt_6_2_ = p_180624_2_.nextInt(3);
         if (lvt_6_2_ == 0) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SYRINGA);
         } else if (lvt_6_2_ == 1) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.ROSE);
         } else if (lvt_6_2_ == 2) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.PAEONIA);
         }

         for(lvt_7_2_ = 0; lvt_7_2_ < 5; ++lvt_7_2_) {
            int lvt_8_2_ = p_180624_2_.nextInt(16) + 8;
            int lvt_9_3_ = p_180624_2_.nextInt(16) + 8;
            int lvt_10_1_ = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(lvt_8_2_, 0, lvt_9_3_)).func_177956_o() + 32);
            if (field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, new BlockPos(p_180624_3_.func_177958_n() + lvt_8_2_, lvt_10_1_, p_180624_3_.func_177952_p() + lvt_9_3_))) {
               break;
            }
         }
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   public int func_180627_b(BlockPos p_180627_1_) {
      int lvt_2_1_ = super.func_180627_b(p_180627_1_);
      return this.field_150632_aF == 3 ? (lvt_2_1_ & 16711422) + 2634762 >> 1 : lvt_2_1_;
   }

   protected BiomeGenBase func_180277_d(int p_180277_1_) {
      if (this.field_76756_M == BiomeGenBase.field_76767_f.field_76756_M) {
         BiomeGenForest lvt_2_1_ = new BiomeGenForest(p_180277_1_, 1);
         lvt_2_1_.func_150570_a(new BiomeGenBase.Height(this.field_76748_D, this.field_76749_E + 0.2F));
         lvt_2_1_.func_76735_a("Flower Forest");
         lvt_2_1_.func_150557_a(6976549, true);
         lvt_2_1_.func_76733_a(8233509);
         return lvt_2_1_;
      } else {
         return this.field_76756_M != BiomeGenBase.field_150583_P.field_76756_M && this.field_76756_M != BiomeGenBase.field_150582_Q.field_76756_M ? new BiomeGenMutated(p_180277_1_, this) {
            public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
               this.field_150611_aD.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
            }
         } : new BiomeGenMutated(p_180277_1_, this) {
            public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
               return p_150567_1_.nextBoolean() ? BiomeGenForest.field_150629_aC : BiomeGenForest.field_150630_aD;
            }
         };
      }
   }
}
