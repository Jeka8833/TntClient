package net.minecraft.world;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class ChunkCache implements IBlockAccess {
   protected int field_72818_a;
   protected int field_72816_b;
   protected Chunk[][] field_72817_c;
   protected boolean field_72814_d;
   protected World field_72815_e;

   public ChunkCache(World p_i45746_1_, BlockPos p_i45746_2_, BlockPos p_i45746_3_, int p_i45746_4_) {
      this.field_72815_e = p_i45746_1_;
      this.field_72818_a = p_i45746_2_.func_177958_n() - p_i45746_4_ >> 4;
      this.field_72816_b = p_i45746_2_.func_177952_p() - p_i45746_4_ >> 4;
      int lvt_5_1_ = p_i45746_3_.func_177958_n() + p_i45746_4_ >> 4;
      int lvt_6_1_ = p_i45746_3_.func_177952_p() + p_i45746_4_ >> 4;
      this.field_72817_c = new Chunk[lvt_5_1_ - this.field_72818_a + 1][lvt_6_1_ - this.field_72816_b + 1];
      this.field_72814_d = true;

      int lvt_7_2_;
      int lvt_8_2_;
      for(lvt_7_2_ = this.field_72818_a; lvt_7_2_ <= lvt_5_1_; ++lvt_7_2_) {
         for(lvt_8_2_ = this.field_72816_b; lvt_8_2_ <= lvt_6_1_; ++lvt_8_2_) {
            this.field_72817_c[lvt_7_2_ - this.field_72818_a][lvt_8_2_ - this.field_72816_b] = p_i45746_1_.func_72964_e(lvt_7_2_, lvt_8_2_);
         }
      }

      for(lvt_7_2_ = p_i45746_2_.func_177958_n() >> 4; lvt_7_2_ <= p_i45746_3_.func_177958_n() >> 4; ++lvt_7_2_) {
         for(lvt_8_2_ = p_i45746_2_.func_177952_p() >> 4; lvt_8_2_ <= p_i45746_3_.func_177952_p() >> 4; ++lvt_8_2_) {
            Chunk lvt_9_1_ = this.field_72817_c[lvt_7_2_ - this.field_72818_a][lvt_8_2_ - this.field_72816_b];
            if (lvt_9_1_ != null && !lvt_9_1_.func_76606_c(p_i45746_2_.func_177956_o(), p_i45746_3_.func_177956_o())) {
               this.field_72814_d = false;
            }
         }
      }

   }

   public boolean func_72806_N() {
      return this.field_72814_d;
   }

   public TileEntity func_175625_s(BlockPos p_175625_1_) {
      int lvt_2_1_ = (p_175625_1_.func_177958_n() >> 4) - this.field_72818_a;
      int lvt_3_1_ = (p_175625_1_.func_177952_p() >> 4) - this.field_72816_b;
      return this.field_72817_c[lvt_2_1_][lvt_3_1_].func_177424_a(p_175625_1_, Chunk.EnumCreateEntityType.IMMEDIATE);
   }

   public int func_175626_b(BlockPos p_175626_1_, int p_175626_2_) {
      int lvt_3_1_ = this.func_175629_a(EnumSkyBlock.SKY, p_175626_1_);
      int lvt_4_1_ = this.func_175629_a(EnumSkyBlock.BLOCK, p_175626_1_);
      if (lvt_4_1_ < p_175626_2_) {
         lvt_4_1_ = p_175626_2_;
      }

      return lvt_3_1_ << 20 | lvt_4_1_ << 4;
   }

   public IBlockState func_180495_p(BlockPos p_180495_1_) {
      if (p_180495_1_.func_177956_o() >= 0 && p_180495_1_.func_177956_o() < 256) {
         int lvt_2_1_ = (p_180495_1_.func_177958_n() >> 4) - this.field_72818_a;
         int lvt_3_1_ = (p_180495_1_.func_177952_p() >> 4) - this.field_72816_b;
         if (lvt_2_1_ >= 0 && lvt_2_1_ < this.field_72817_c.length && lvt_3_1_ >= 0 && lvt_3_1_ < this.field_72817_c[lvt_2_1_].length) {
            Chunk lvt_4_1_ = this.field_72817_c[lvt_2_1_][lvt_3_1_];
            if (lvt_4_1_ != null) {
               return lvt_4_1_.func_177435_g(p_180495_1_);
            }
         }
      }

      return Blocks.field_150350_a.func_176223_P();
   }

   public BiomeGenBase func_180494_b(BlockPos p_180494_1_) {
      return this.field_72815_e.func_180494_b(p_180494_1_);
   }

   private int func_175629_a(EnumSkyBlock p_175629_1_, BlockPos p_175629_2_) {
      if (p_175629_1_ == EnumSkyBlock.SKY && this.field_72815_e.field_73011_w.func_177495_o()) {
         return 0;
      } else if (p_175629_2_.func_177956_o() >= 0 && p_175629_2_.func_177956_o() < 256) {
         int lvt_3_1_;
         if (this.func_180495_p(p_175629_2_).func_177230_c().func_149710_n()) {
            lvt_3_1_ = 0;
            EnumFacing[] lvt_4_1_ = EnumFacing.values();
            int lvt_5_1_ = lvt_4_1_.length;

            for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
               EnumFacing lvt_7_1_ = lvt_4_1_[lvt_6_1_];
               int lvt_8_1_ = this.func_175628_b(p_175629_1_, p_175629_2_.func_177972_a(lvt_7_1_));
               if (lvt_8_1_ > lvt_3_1_) {
                  lvt_3_1_ = lvt_8_1_;
               }

               if (lvt_3_1_ >= 15) {
                  return lvt_3_1_;
               }
            }

            return lvt_3_1_;
         } else {
            lvt_3_1_ = (p_175629_2_.func_177958_n() >> 4) - this.field_72818_a;
            int lvt_4_2_ = (p_175629_2_.func_177952_p() >> 4) - this.field_72816_b;
            return this.field_72817_c[lvt_3_1_][lvt_4_2_].func_177413_a(p_175629_1_, p_175629_2_);
         }
      } else {
         return p_175629_1_.field_77198_c;
      }
   }

   public boolean func_175623_d(BlockPos p_175623_1_) {
      return this.func_180495_p(p_175623_1_).func_177230_c().func_149688_o() == Material.field_151579_a;
   }

   public int func_175628_b(EnumSkyBlock p_175628_1_, BlockPos p_175628_2_) {
      if (p_175628_2_.func_177956_o() >= 0 && p_175628_2_.func_177956_o() < 256) {
         int lvt_3_1_ = (p_175628_2_.func_177958_n() >> 4) - this.field_72818_a;
         int lvt_4_1_ = (p_175628_2_.func_177952_p() >> 4) - this.field_72816_b;
         return this.field_72817_c[lvt_3_1_][lvt_4_1_].func_177413_a(p_175628_1_, p_175628_2_);
      } else {
         return p_175628_1_.field_77198_c;
      }
   }

   public int func_175627_a(BlockPos p_175627_1_, EnumFacing p_175627_2_) {
      IBlockState lvt_3_1_ = this.func_180495_p(p_175627_1_);
      return lvt_3_1_.func_177230_c().func_176211_b(this, p_175627_1_, lvt_3_1_, p_175627_2_);
   }

   public WorldType func_175624_G() {
      return this.field_72815_e.func_175624_G();
   }
}
