package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.NodeProcessor;

public class PathFinder {
   private Path field_75866_b = new Path();
   private PathPoint[] field_75864_d = new PathPoint[32];
   private NodeProcessor field_176190_c;

   public PathFinder(NodeProcessor p_i45557_1_) {
      this.field_176190_c = p_i45557_1_;
   }

   public PathEntity func_176188_a(IBlockAccess p_176188_1_, Entity p_176188_2_, Entity p_176188_3_, float p_176188_4_) {
      return this.func_176189_a(p_176188_1_, p_176188_2_, p_176188_3_.field_70165_t, p_176188_3_.func_174813_aQ().field_72338_b, p_176188_3_.field_70161_v, p_176188_4_);
   }

   public PathEntity func_180782_a(IBlockAccess p_180782_1_, Entity p_180782_2_, BlockPos p_180782_3_, float p_180782_4_) {
      return this.func_176189_a(p_180782_1_, p_180782_2_, (double)((float)p_180782_3_.func_177958_n() + 0.5F), (double)((float)p_180782_3_.func_177956_o() + 0.5F), (double)((float)p_180782_3_.func_177952_p() + 0.5F), p_180782_4_);
   }

   private PathEntity func_176189_a(IBlockAccess p_176189_1_, Entity p_176189_2_, double p_176189_3_, double p_176189_5_, double p_176189_7_, float p_176189_9_) {
      this.field_75866_b.func_75848_a();
      this.field_176190_c.func_176162_a(p_176189_1_, p_176189_2_);
      PathPoint lvt_10_1_ = this.field_176190_c.func_176161_a(p_176189_2_);
      PathPoint lvt_11_1_ = this.field_176190_c.func_176160_a(p_176189_2_, p_176189_3_, p_176189_5_, p_176189_7_);
      PathEntity lvt_12_1_ = this.func_176187_a(p_176189_2_, lvt_10_1_, lvt_11_1_, p_176189_9_);
      this.field_176190_c.func_176163_a();
      return lvt_12_1_;
   }

   private PathEntity func_176187_a(Entity p_176187_1_, PathPoint p_176187_2_, PathPoint p_176187_3_, float p_176187_4_) {
      p_176187_2_.field_75836_e = 0.0F;
      p_176187_2_.field_75833_f = p_176187_2_.func_75832_b(p_176187_3_);
      p_176187_2_.field_75834_g = p_176187_2_.field_75833_f;
      this.field_75866_b.func_75848_a();
      this.field_75866_b.func_75849_a(p_176187_2_);
      PathPoint lvt_5_1_ = p_176187_2_;

      while(!this.field_75866_b.func_75845_e()) {
         PathPoint lvt_6_1_ = this.field_75866_b.func_75844_c();
         if (lvt_6_1_.equals(p_176187_3_)) {
            return this.func_75853_a(p_176187_2_, p_176187_3_);
         }

         if (lvt_6_1_.func_75832_b(p_176187_3_) < lvt_5_1_.func_75832_b(p_176187_3_)) {
            lvt_5_1_ = lvt_6_1_;
         }

         lvt_6_1_.field_75842_i = true;
         int lvt_7_1_ = this.field_176190_c.func_176164_a(this.field_75864_d, p_176187_1_, lvt_6_1_, p_176187_3_, p_176187_4_);

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
            PathPoint lvt_9_1_ = this.field_75864_d[lvt_8_1_];
            float lvt_10_1_ = lvt_6_1_.field_75836_e + lvt_6_1_.func_75832_b(lvt_9_1_);
            if (lvt_10_1_ < p_176187_4_ * 2.0F && (!lvt_9_1_.func_75831_a() || lvt_10_1_ < lvt_9_1_.field_75836_e)) {
               lvt_9_1_.field_75841_h = lvt_6_1_;
               lvt_9_1_.field_75836_e = lvt_10_1_;
               lvt_9_1_.field_75833_f = lvt_9_1_.func_75832_b(p_176187_3_);
               if (lvt_9_1_.func_75831_a()) {
                  this.field_75866_b.func_75850_a(lvt_9_1_, lvt_9_1_.field_75836_e + lvt_9_1_.field_75833_f);
               } else {
                  lvt_9_1_.field_75834_g = lvt_9_1_.field_75836_e + lvt_9_1_.field_75833_f;
                  this.field_75866_b.func_75849_a(lvt_9_1_);
               }
            }
         }
      }

      if (lvt_5_1_ == p_176187_2_) {
         return null;
      } else {
         return this.func_75853_a(p_176187_2_, lvt_5_1_);
      }
   }

   private PathEntity func_75853_a(PathPoint p_75853_1_, PathPoint p_75853_2_) {
      int lvt_3_1_ = 1;

      PathPoint lvt_4_1_;
      for(lvt_4_1_ = p_75853_2_; lvt_4_1_.field_75841_h != null; lvt_4_1_ = lvt_4_1_.field_75841_h) {
         ++lvt_3_1_;
      }

      PathPoint[] lvt_5_1_ = new PathPoint[lvt_3_1_];
      lvt_4_1_ = p_75853_2_;
      --lvt_3_1_;

      for(lvt_5_1_[lvt_3_1_] = p_75853_2_; lvt_4_1_.field_75841_h != null; lvt_5_1_[lvt_3_1_] = lvt_4_1_) {
         lvt_4_1_ = lvt_4_1_.field_75841_h;
         --lvt_3_1_;
      }

      return new PathEntity(lvt_5_1_);
   }
}
