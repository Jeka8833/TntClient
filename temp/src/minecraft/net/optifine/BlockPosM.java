package net.optifine;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public class BlockPosM extends BlockPos {
   private int mx;
   private int my;
   private int mz;
   private int level;
   private BlockPosM[] facings;
   private boolean needsUpdate;

   public BlockPosM(int x, int y, int z) {
      this(x, y, z, 0);
   }

   public BlockPosM(double xIn, double yIn, double zIn) {
      this(MathHelper.func_76128_c(xIn), MathHelper.func_76128_c(yIn), MathHelper.func_76128_c(zIn));
   }

   public BlockPosM(int x, int y, int z, int level) {
      super(0, 0, 0);
      this.mx = x;
      this.my = y;
      this.mz = z;
      this.level = level;
   }

   public int func_177958_n() {
      return this.mx;
   }

   public int func_177956_o() {
      return this.my;
   }

   public int func_177952_p() {
      return this.mz;
   }

   public void setXyz(int x, int y, int z) {
      this.mx = x;
      this.my = y;
      this.mz = z;
      this.needsUpdate = true;
   }

   public void setXyz(double xIn, double yIn, double zIn) {
      this.setXyz(MathHelper.func_76128_c(xIn), MathHelper.func_76128_c(yIn), MathHelper.func_76128_c(zIn));
   }

   public BlockPosM set(Vec3i vec) {
      this.setXyz(vec.func_177958_n(), vec.func_177956_o(), vec.func_177952_p());
      return this;
   }

   public BlockPosM set(int xIn, int yIn, int zIn) {
      this.setXyz(xIn, yIn, zIn);
      return this;
   }

   public BlockPos offsetMutable(EnumFacing facing) {
      return this.func_177972_a(facing);
   }

   public BlockPos func_177972_a(EnumFacing facing) {
      if (this.level <= 0) {
         return super.func_177967_a(facing, 1);
      } else {
         if (this.facings == null) {
            this.facings = new BlockPosM[EnumFacing.field_82609_l.length];
         }

         if (this.needsUpdate) {
            this.update();
         }

         int index = facing.func_176745_a();
         BlockPosM bpm = this.facings[index];
         if (bpm == null) {
            int nx = this.mx + facing.func_82601_c();
            int ny = this.my + facing.func_96559_d();
            int nz = this.mz + facing.func_82599_e();
            bpm = new BlockPosM(nx, ny, nz, this.level - 1);
            this.facings[index] = bpm;
         }

         return bpm;
      }
   }

   public BlockPos func_177967_a(EnumFacing facing, int n) {
      return n == 1 ? this.func_177972_a(facing) : super.func_177967_a(facing, n);
   }

   private void update() {
      for(int i = 0; i < 6; ++i) {
         BlockPosM bpm = this.facings[i];
         if (bpm != null) {
            EnumFacing facing = EnumFacing.field_82609_l[i];
            int nx = this.mx + facing.func_82601_c();
            int ny = this.my + facing.func_96559_d();
            int nz = this.mz + facing.func_82599_e();
            bpm.setXyz(nx, ny, nz);
         }
      }

      this.needsUpdate = false;
   }

   public BlockPos toImmutable() {
      return new BlockPos(this.mx, this.my, this.mz);
   }

   public static Iterable getAllInBoxMutable(BlockPos from, BlockPos to) {
      final BlockPos posFrom = new BlockPos(Math.min(from.func_177958_n(), to.func_177958_n()), Math.min(from.func_177956_o(), to.func_177956_o()), Math.min(from.func_177952_p(), to.func_177952_p()));
      final BlockPos posTo = new BlockPos(Math.max(from.func_177958_n(), to.func_177958_n()), Math.max(from.func_177956_o(), to.func_177956_o()), Math.max(from.func_177952_p(), to.func_177952_p()));
      return new Iterable() {
         public Iterator iterator() {
            return new AbstractIterator() {
               private BlockPosM theBlockPosM = null;

               protected BlockPosM computeNext0() {
                  if (this.theBlockPosM == null) {
                     this.theBlockPosM = new BlockPosM(posFrom.func_177958_n(), posFrom.func_177956_o(), posFrom.func_177952_p(), 3);
                     return this.theBlockPosM;
                  } else if (this.theBlockPosM.equals(posTo)) {
                     return (BlockPosM)this.endOfData();
                  } else {
                     int bx = this.theBlockPosM.func_177958_n();
                     int by = this.theBlockPosM.func_177956_o();
                     int bz = this.theBlockPosM.func_177952_p();
                     if (bx < posTo.func_177958_n()) {
                        ++bx;
                     } else if (by < posTo.func_177956_o()) {
                        bx = posFrom.func_177958_n();
                        ++by;
                     } else if (bz < posTo.func_177952_p()) {
                        bx = posFrom.func_177958_n();
                        by = posFrom.func_177956_o();
                        ++bz;
                     }

                     this.theBlockPosM.setXyz(bx, by, bz);
                     return this.theBlockPosM;
                  }
               }

               protected Object computeNext() {
                  return this.computeNext0();
               }
            };
         }
      };
   }
}
