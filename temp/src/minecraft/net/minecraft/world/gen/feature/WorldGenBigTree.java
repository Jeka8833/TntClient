package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenBigTree extends WorldGenAbstractTree {
   private Random field_175949_k;
   private World field_175946_l;
   private BlockPos field_175947_m;
   int field_76504_e;
   int field_76501_f;
   double field_76502_g;
   double field_175944_d;
   double field_175945_e;
   double field_76513_k;
   int field_175943_g;
   int field_175950_h;
   int field_76508_n;
   List<WorldGenBigTree.FoliageCoordinates> field_175948_j;

   public WorldGenBigTree(boolean p_i2008_1_) {
      super(p_i2008_1_);
      this.field_175947_m = BlockPos.field_177992_a;
      this.field_76502_g = 0.618D;
      this.field_175944_d = 0.381D;
      this.field_175945_e = 1.0D;
      this.field_76513_k = 1.0D;
      this.field_175943_g = 1;
      this.field_175950_h = 12;
      this.field_76508_n = 4;
   }

   void func_76489_a() {
      this.field_76501_f = (int)((double)this.field_76504_e * this.field_76502_g);
      if (this.field_76501_f >= this.field_76504_e) {
         this.field_76501_f = this.field_76504_e - 1;
      }

      int lvt_1_1_ = (int)(1.382D + Math.pow(this.field_76513_k * (double)this.field_76504_e / 13.0D, 2.0D));
      if (lvt_1_1_ < 1) {
         lvt_1_1_ = 1;
      }

      int lvt_2_1_ = this.field_175947_m.func_177956_o() + this.field_76501_f;
      int lvt_3_1_ = this.field_76504_e - this.field_76508_n;
      this.field_175948_j = Lists.newArrayList();
      this.field_175948_j.add(new WorldGenBigTree.FoliageCoordinates(this.field_175947_m.func_177981_b(lvt_3_1_), lvt_2_1_));

      for(; lvt_3_1_ >= 0; --lvt_3_1_) {
         float lvt_4_1_ = this.func_76490_a(lvt_3_1_);
         if (lvt_4_1_ >= 0.0F) {
            for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_1_1_; ++lvt_5_1_) {
               double lvt_6_1_ = this.field_175945_e * (double)lvt_4_1_ * ((double)this.field_175949_k.nextFloat() + 0.328D);
               double lvt_8_1_ = (double)(this.field_175949_k.nextFloat() * 2.0F) * 3.141592653589793D;
               double lvt_10_1_ = lvt_6_1_ * Math.sin(lvt_8_1_) + 0.5D;
               double lvt_12_1_ = lvt_6_1_ * Math.cos(lvt_8_1_) + 0.5D;
               BlockPos lvt_14_1_ = this.field_175947_m.func_177963_a(lvt_10_1_, (double)(lvt_3_1_ - 1), lvt_12_1_);
               BlockPos lvt_15_1_ = lvt_14_1_.func_177981_b(this.field_76508_n);
               if (this.func_175936_a(lvt_14_1_, lvt_15_1_) == -1) {
                  int lvt_16_1_ = this.field_175947_m.func_177958_n() - lvt_14_1_.func_177958_n();
                  int lvt_17_1_ = this.field_175947_m.func_177952_p() - lvt_14_1_.func_177952_p();
                  double lvt_18_1_ = (double)lvt_14_1_.func_177956_o() - Math.sqrt((double)(lvt_16_1_ * lvt_16_1_ + lvt_17_1_ * lvt_17_1_)) * this.field_175944_d;
                  int lvt_20_1_ = lvt_18_1_ > (double)lvt_2_1_ ? lvt_2_1_ : (int)lvt_18_1_;
                  BlockPos lvt_21_1_ = new BlockPos(this.field_175947_m.func_177958_n(), lvt_20_1_, this.field_175947_m.func_177952_p());
                  if (this.func_175936_a(lvt_21_1_, lvt_14_1_) == -1) {
                     this.field_175948_j.add(new WorldGenBigTree.FoliageCoordinates(lvt_14_1_, lvt_21_1_.func_177956_o()));
                  }
               }
            }
         }
      }

   }

   void func_181631_a(BlockPos p_181631_1_, float p_181631_2_, IBlockState p_181631_3_) {
      int lvt_4_1_ = (int)((double)p_181631_2_ + 0.618D);

      for(int lvt_5_1_ = -lvt_4_1_; lvt_5_1_ <= lvt_4_1_; ++lvt_5_1_) {
         for(int lvt_6_1_ = -lvt_4_1_; lvt_6_1_ <= lvt_4_1_; ++lvt_6_1_) {
            if (Math.pow((double)Math.abs(lvt_5_1_) + 0.5D, 2.0D) + Math.pow((double)Math.abs(lvt_6_1_) + 0.5D, 2.0D) <= (double)(p_181631_2_ * p_181631_2_)) {
               BlockPos lvt_7_1_ = p_181631_1_.func_177982_a(lvt_5_1_, 0, lvt_6_1_);
               Material lvt_8_1_ = this.field_175946_l.func_180495_p(lvt_7_1_).func_177230_c().func_149688_o();
               if (lvt_8_1_ == Material.field_151579_a || lvt_8_1_ == Material.field_151584_j) {
                  this.func_175903_a(this.field_175946_l, lvt_7_1_, p_181631_3_);
               }
            }
         }
      }

   }

   float func_76490_a(int p_76490_1_) {
      if ((float)p_76490_1_ < (float)this.field_76504_e * 0.3F) {
         return -1.0F;
      } else {
         float lvt_2_1_ = (float)this.field_76504_e / 2.0F;
         float lvt_3_1_ = lvt_2_1_ - (float)p_76490_1_;
         float lvt_4_1_ = MathHelper.func_76129_c(lvt_2_1_ * lvt_2_1_ - lvt_3_1_ * lvt_3_1_);
         if (lvt_3_1_ == 0.0F) {
            lvt_4_1_ = lvt_2_1_;
         } else if (Math.abs(lvt_3_1_) >= lvt_2_1_) {
            return 0.0F;
         }

         return lvt_4_1_ * 0.5F;
      }
   }

   float func_76495_b(int p_76495_1_) {
      if (p_76495_1_ >= 0 && p_76495_1_ < this.field_76508_n) {
         return p_76495_1_ != 0 && p_76495_1_ != this.field_76508_n - 1 ? 3.0F : 2.0F;
      } else {
         return -1.0F;
      }
   }

   void func_175940_a(BlockPos p_175940_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_76508_n; ++lvt_2_1_) {
         this.func_181631_a(p_175940_1_.func_177981_b(lvt_2_1_), this.func_76495_b(lvt_2_1_), Blocks.field_150362_t.func_176223_P().func_177226_a(BlockLeaves.field_176236_b, false));
      }

   }

   void func_175937_a(BlockPos p_175937_1_, BlockPos p_175937_2_, Block p_175937_3_) {
      BlockPos lvt_4_1_ = p_175937_2_.func_177982_a(-p_175937_1_.func_177958_n(), -p_175937_1_.func_177956_o(), -p_175937_1_.func_177952_p());
      int lvt_5_1_ = this.func_175935_b(lvt_4_1_);
      float lvt_6_1_ = (float)lvt_4_1_.func_177958_n() / (float)lvt_5_1_;
      float lvt_7_1_ = (float)lvt_4_1_.func_177956_o() / (float)lvt_5_1_;
      float lvt_8_1_ = (float)lvt_4_1_.func_177952_p() / (float)lvt_5_1_;

      for(int lvt_9_1_ = 0; lvt_9_1_ <= lvt_5_1_; ++lvt_9_1_) {
         BlockPos lvt_10_1_ = p_175937_1_.func_177963_a((double)(0.5F + (float)lvt_9_1_ * lvt_6_1_), (double)(0.5F + (float)lvt_9_1_ * lvt_7_1_), (double)(0.5F + (float)lvt_9_1_ * lvt_8_1_));
         BlockLog.EnumAxis lvt_11_1_ = this.func_175938_b(p_175937_1_, lvt_10_1_);
         this.func_175903_a(this.field_175946_l, lvt_10_1_, p_175937_3_.func_176223_P().func_177226_a(BlockLog.field_176299_a, lvt_11_1_));
      }

   }

   private int func_175935_b(BlockPos p_175935_1_) {
      int lvt_2_1_ = MathHelper.func_76130_a(p_175935_1_.func_177958_n());
      int lvt_3_1_ = MathHelper.func_76130_a(p_175935_1_.func_177956_o());
      int lvt_4_1_ = MathHelper.func_76130_a(p_175935_1_.func_177952_p());
      if (lvt_4_1_ > lvt_2_1_ && lvt_4_1_ > lvt_3_1_) {
         return lvt_4_1_;
      } else {
         return lvt_3_1_ > lvt_2_1_ ? lvt_3_1_ : lvt_2_1_;
      }
   }

   private BlockLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {
      BlockLog.EnumAxis lvt_3_1_ = BlockLog.EnumAxis.Y;
      int lvt_4_1_ = Math.abs(p_175938_2_.func_177958_n() - p_175938_1_.func_177958_n());
      int lvt_5_1_ = Math.abs(p_175938_2_.func_177952_p() - p_175938_1_.func_177952_p());
      int lvt_6_1_ = Math.max(lvt_4_1_, lvt_5_1_);
      if (lvt_6_1_ > 0) {
         if (lvt_4_1_ == lvt_6_1_) {
            lvt_3_1_ = BlockLog.EnumAxis.X;
         } else if (lvt_5_1_ == lvt_6_1_) {
            lvt_3_1_ = BlockLog.EnumAxis.Z;
         }
      }

      return lvt_3_1_;
   }

   void func_175941_b() {
      Iterator lvt_1_1_ = this.field_175948_j.iterator();

      while(lvt_1_1_.hasNext()) {
         WorldGenBigTree.FoliageCoordinates lvt_2_1_ = (WorldGenBigTree.FoliageCoordinates)lvt_1_1_.next();
         this.func_175940_a(lvt_2_1_);
      }

   }

   boolean func_76493_c(int p_76493_1_) {
      return (double)p_76493_1_ >= (double)this.field_76504_e * 0.2D;
   }

   void func_175942_c() {
      BlockPos lvt_1_1_ = this.field_175947_m;
      BlockPos lvt_2_1_ = this.field_175947_m.func_177981_b(this.field_76501_f);
      Block lvt_3_1_ = Blocks.field_150364_r;
      this.func_175937_a(lvt_1_1_, lvt_2_1_, lvt_3_1_);
      if (this.field_175943_g == 2) {
         this.func_175937_a(lvt_1_1_.func_177974_f(), lvt_2_1_.func_177974_f(), lvt_3_1_);
         this.func_175937_a(lvt_1_1_.func_177974_f().func_177968_d(), lvt_2_1_.func_177974_f().func_177968_d(), lvt_3_1_);
         this.func_175937_a(lvt_1_1_.func_177968_d(), lvt_2_1_.func_177968_d(), lvt_3_1_);
      }

   }

   void func_175939_d() {
      Iterator lvt_1_1_ = this.field_175948_j.iterator();

      while(lvt_1_1_.hasNext()) {
         WorldGenBigTree.FoliageCoordinates lvt_2_1_ = (WorldGenBigTree.FoliageCoordinates)lvt_1_1_.next();
         int lvt_3_1_ = lvt_2_1_.func_177999_q();
         BlockPos lvt_4_1_ = new BlockPos(this.field_175947_m.func_177958_n(), lvt_3_1_, this.field_175947_m.func_177952_p());
         if (!lvt_4_1_.equals(lvt_2_1_) && this.func_76493_c(lvt_3_1_ - this.field_175947_m.func_177956_o())) {
            this.func_175937_a(lvt_4_1_, lvt_2_1_, Blocks.field_150364_r);
         }
      }

   }

   int func_175936_a(BlockPos p_175936_1_, BlockPos p_175936_2_) {
      BlockPos lvt_3_1_ = p_175936_2_.func_177982_a(-p_175936_1_.func_177958_n(), -p_175936_1_.func_177956_o(), -p_175936_1_.func_177952_p());
      int lvt_4_1_ = this.func_175935_b(lvt_3_1_);
      float lvt_5_1_ = (float)lvt_3_1_.func_177958_n() / (float)lvt_4_1_;
      float lvt_6_1_ = (float)lvt_3_1_.func_177956_o() / (float)lvt_4_1_;
      float lvt_7_1_ = (float)lvt_3_1_.func_177952_p() / (float)lvt_4_1_;
      if (lvt_4_1_ == 0) {
         return -1;
      } else {
         for(int lvt_8_1_ = 0; lvt_8_1_ <= lvt_4_1_; ++lvt_8_1_) {
            BlockPos lvt_9_1_ = p_175936_1_.func_177963_a((double)(0.5F + (float)lvt_8_1_ * lvt_5_1_), (double)(0.5F + (float)lvt_8_1_ * lvt_6_1_), (double)(0.5F + (float)lvt_8_1_ * lvt_7_1_));
            if (!this.func_150523_a(this.field_175946_l.func_180495_p(lvt_9_1_).func_177230_c())) {
               return lvt_8_1_;
            }
         }

         return -1;
      }
   }

   public void func_175904_e() {
      this.field_76508_n = 5;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      this.field_175946_l = p_180709_1_;
      this.field_175947_m = p_180709_3_;
      this.field_175949_k = new Random(p_180709_2_.nextLong());
      if (this.field_76504_e == 0) {
         this.field_76504_e = 5 + this.field_175949_k.nextInt(this.field_175950_h);
      }

      if (!this.func_76497_e()) {
         return false;
      } else {
         this.func_76489_a();
         this.func_175941_b();
         this.func_175942_c();
         this.func_175939_d();
         return true;
      }
   }

   private boolean func_76497_e() {
      Block lvt_1_1_ = this.field_175946_l.func_180495_p(this.field_175947_m.func_177977_b()).func_177230_c();
      if (lvt_1_1_ != Blocks.field_150346_d && lvt_1_1_ != Blocks.field_150349_c && lvt_1_1_ != Blocks.field_150458_ak) {
         return false;
      } else {
         int lvt_2_1_ = this.func_175936_a(this.field_175947_m, this.field_175947_m.func_177981_b(this.field_76504_e - 1));
         if (lvt_2_1_ == -1) {
            return true;
         } else if (lvt_2_1_ < 6) {
            return false;
         } else {
            this.field_76504_e = lvt_2_1_;
            return true;
         }
      }
   }

   static class FoliageCoordinates extends BlockPos {
      private final int field_178000_b;

      public FoliageCoordinates(BlockPos p_i45635_1_, int p_i45635_2_) {
         super(p_i45635_1_.func_177958_n(), p_i45635_1_.func_177956_o(), p_i45635_1_.func_177952_p());
         this.field_178000_b = p_i45635_2_;
      }

      public int func_177999_q() {
         return this.field_178000_b;
      }
   }
}
