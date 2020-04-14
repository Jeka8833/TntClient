package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BiomeGenPlains extends BiomeGenBase {
   protected boolean field_150628_aC;

   protected BiomeGenPlains(int p_i1986_1_) {
      super(p_i1986_1_);
      this.func_76732_a(0.8F, 0.4F);
      this.func_150570_a(field_150593_e);
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
      this.field_76760_I.field_76832_z = -999;
      this.field_76760_I.field_76802_A = 4;
      this.field_76760_I.field_76803_B = 10;
   }

   public BlockFlower.EnumFlowerType func_180623_a(Random p_180623_1_, BlockPos p_180623_2_) {
      double lvt_3_1_ = field_180281_af.func_151601_a((double)p_180623_2_.func_177958_n() / 200.0D, (double)p_180623_2_.func_177952_p() / 200.0D);
      int lvt_5_2_;
      if (lvt_3_1_ < -0.8D) {
         lvt_5_2_ = p_180623_1_.nextInt(4);
         switch(lvt_5_2_) {
         case 0:
            return BlockFlower.EnumFlowerType.ORANGE_TULIP;
         case 1:
            return BlockFlower.EnumFlowerType.RED_TULIP;
         case 2:
            return BlockFlower.EnumFlowerType.PINK_TULIP;
         case 3:
         default:
            return BlockFlower.EnumFlowerType.WHITE_TULIP;
         }
      } else if (p_180623_1_.nextInt(3) > 0) {
         lvt_5_2_ = p_180623_1_.nextInt(3);
         if (lvt_5_2_ == 0) {
            return BlockFlower.EnumFlowerType.POPPY;
         } else {
            return lvt_5_2_ == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY;
         }
      } else {
         return BlockFlower.EnumFlowerType.DANDELION;
      }
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      double lvt_4_1_ = field_180281_af.func_151601_a((double)(p_180624_3_.func_177958_n() + 8) / 200.0D, (double)(p_180624_3_.func_177952_p() + 8) / 200.0D);
      int lvt_6_2_;
      int lvt_7_2_;
      int lvt_8_2_;
      int lvt_9_2_;
      if (lvt_4_1_ < -0.8D) {
         this.field_76760_I.field_76802_A = 15;
         this.field_76760_I.field_76803_B = 5;
      } else {
         this.field_76760_I.field_76802_A = 4;
         this.field_76760_I.field_76803_B = 10;
         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);

         for(lvt_6_2_ = 0; lvt_6_2_ < 7; ++lvt_6_2_) {
            lvt_7_2_ = p_180624_2_.nextInt(16) + 8;
            lvt_8_2_ = p_180624_2_.nextInt(16) + 8;
            lvt_9_2_ = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(lvt_7_2_, 0, lvt_8_2_)).func_177956_o() + 32);
            field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, p_180624_3_.func_177982_a(lvt_7_2_, lvt_9_2_, lvt_8_2_));
         }
      }

      if (this.field_150628_aC) {
         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SUNFLOWER);

         for(lvt_6_2_ = 0; lvt_6_2_ < 10; ++lvt_6_2_) {
            lvt_7_2_ = p_180624_2_.nextInt(16) + 8;
            lvt_8_2_ = p_180624_2_.nextInt(16) + 8;
            lvt_9_2_ = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(lvt_7_2_, 0, lvt_8_2_)).func_177956_o() + 32);
            field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, p_180624_3_.func_177982_a(lvt_7_2_, lvt_9_2_, lvt_8_2_));
         }
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   protected BiomeGenBase func_180277_d(int p_180277_1_) {
      BiomeGenPlains lvt_2_1_ = new BiomeGenPlains(p_180277_1_);
      lvt_2_1_.func_76735_a("Sunflower Plains");
      lvt_2_1_.field_150628_aC = true;
      lvt_2_1_.func_76739_b(9286496);
      lvt_2_1_.field_150609_ah = 14273354;
      return lvt_2_1_;
   }
}
