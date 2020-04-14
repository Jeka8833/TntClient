package net.minecraft.world.pathfinder;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor {
   protected IBlockAccess field_176169_a;
   protected IntHashMap<PathPoint> field_176167_b = new IntHashMap();
   protected int field_176168_c;
   protected int field_176165_d;
   protected int field_176166_e;

   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
      this.field_176169_a = p_176162_1_;
      this.field_176167_b.func_76046_c();
      this.field_176168_c = MathHelper.func_76141_d(p_176162_2_.field_70130_N + 1.0F);
      this.field_176165_d = MathHelper.func_76141_d(p_176162_2_.field_70131_O + 1.0F);
      this.field_176166_e = MathHelper.func_76141_d(p_176162_2_.field_70130_N + 1.0F);
   }

   public void func_176163_a() {
   }

   protected PathPoint func_176159_a(int p_176159_1_, int p_176159_2_, int p_176159_3_) {
      int lvt_4_1_ = PathPoint.func_75830_a(p_176159_1_, p_176159_2_, p_176159_3_);
      PathPoint lvt_5_1_ = (PathPoint)this.field_176167_b.func_76041_a(lvt_4_1_);
      if (lvt_5_1_ == null) {
         lvt_5_1_ = new PathPoint(p_176159_1_, p_176159_2_, p_176159_3_);
         this.field_176167_b.func_76038_a(lvt_4_1_, lvt_5_1_);
      }

      return lvt_5_1_;
   }

   public abstract PathPoint func_176161_a(Entity var1);

   public abstract PathPoint func_176160_a(Entity var1, double var2, double var4, double var6);

   public abstract int func_176164_a(PathPoint[] var1, Entity var2, PathPoint var3, PathPoint var4, float var5);
}
