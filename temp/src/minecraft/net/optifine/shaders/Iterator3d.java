package net.optifine.shaders;

import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.optifine.BlockPosM;

public class Iterator3d implements Iterator<BlockPos> {
   private IteratorAxis iteratorAxis;
   private BlockPosM blockPos = new BlockPosM(0, 0, 0);
   private int axis = 0;
   private int kX;
   private int kY;
   private int kZ;
   private static final int AXIS_X = 0;
   private static final int AXIS_Y = 1;
   private static final int AXIS_Z = 2;

   public Iterator3d(BlockPos posStart, BlockPos posEnd, int width, int height) {
      boolean revX = posStart.func_177958_n() > posEnd.func_177958_n();
      boolean revY = posStart.func_177956_o() > posEnd.func_177956_o();
      boolean revZ = posStart.func_177952_p() > posEnd.func_177952_p();
      posStart = this.reverseCoord(posStart, revX, revY, revZ);
      posEnd = this.reverseCoord(posEnd, revX, revY, revZ);
      this.kX = revX ? -1 : 1;
      this.kY = revY ? -1 : 1;
      this.kZ = revZ ? -1 : 1;
      Vec3 vec = new Vec3((double)(posEnd.func_177958_n() - posStart.func_177958_n()), (double)(posEnd.func_177956_o() - posStart.func_177956_o()), (double)(posEnd.func_177952_p() - posStart.func_177952_p()));
      Vec3 vecN = vec.func_72432_b();
      Vec3 vecX = new Vec3(1.0D, 0.0D, 0.0D);
      double dotX = vecN.func_72430_b(vecX);
      double dotXabs = Math.abs(dotX);
      Vec3 vecY = new Vec3(0.0D, 1.0D, 0.0D);
      double dotY = vecN.func_72430_b(vecY);
      double dotYabs = Math.abs(dotY);
      Vec3 vecZ = new Vec3(0.0D, 0.0D, 1.0D);
      double dotZ = vecN.func_72430_b(vecZ);
      double dotZabs = Math.abs(dotZ);
      BlockPos pos1;
      BlockPos pos2;
      int countX;
      double deltaY;
      double deltaZ;
      if (dotZabs >= dotYabs && dotZabs >= dotXabs) {
         this.axis = 2;
         pos1 = new BlockPos(posStart.func_177952_p(), posStart.func_177956_o() - width, posStart.func_177958_n() - height);
         pos2 = new BlockPos(posEnd.func_177952_p(), posStart.func_177956_o() + width + 1, posStart.func_177958_n() + height + 1);
         countX = posEnd.func_177952_p() - posStart.func_177952_p();
         deltaY = (double)(posEnd.func_177956_o() - posStart.func_177956_o()) / (1.0D * (double)countX);
         deltaZ = (double)(posEnd.func_177958_n() - posStart.func_177958_n()) / (1.0D * (double)countX);
         this.iteratorAxis = new IteratorAxis(pos1, pos2, deltaY, deltaZ);
      } else if (dotYabs >= dotXabs && dotYabs >= dotZabs) {
         this.axis = 1;
         pos1 = new BlockPos(posStart.func_177956_o(), posStart.func_177958_n() - width, posStart.func_177952_p() - height);
         pos2 = new BlockPos(posEnd.func_177956_o(), posStart.func_177958_n() + width + 1, posStart.func_177952_p() + height + 1);
         countX = posEnd.func_177956_o() - posStart.func_177956_o();
         deltaY = (double)(posEnd.func_177958_n() - posStart.func_177958_n()) / (1.0D * (double)countX);
         deltaZ = (double)(posEnd.func_177952_p() - posStart.func_177952_p()) / (1.0D * (double)countX);
         this.iteratorAxis = new IteratorAxis(pos1, pos2, deltaY, deltaZ);
      } else {
         this.axis = 0;
         pos1 = new BlockPos(posStart.func_177958_n(), posStart.func_177956_o() - width, posStart.func_177952_p() - height);
         pos2 = new BlockPos(posEnd.func_177958_n(), posStart.func_177956_o() + width + 1, posStart.func_177952_p() + height + 1);
         countX = posEnd.func_177958_n() - posStart.func_177958_n();
         deltaY = (double)(posEnd.func_177956_o() - posStart.func_177956_o()) / (1.0D * (double)countX);
         deltaZ = (double)(posEnd.func_177952_p() - posStart.func_177952_p()) / (1.0D * (double)countX);
         this.iteratorAxis = new IteratorAxis(pos1, pos2, deltaY, deltaZ);
      }

   }

   private BlockPos reverseCoord(BlockPos pos, boolean revX, boolean revY, boolean revZ) {
      if (revX) {
         pos = new BlockPos(-pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
      }

      if (revY) {
         pos = new BlockPos(pos.func_177958_n(), -pos.func_177956_o(), pos.func_177952_p());
      }

      if (revZ) {
         pos = new BlockPos(pos.func_177958_n(), pos.func_177956_o(), -pos.func_177952_p());
      }

      return pos;
   }

   public boolean hasNext() {
      return this.iteratorAxis.hasNext();
   }

   public BlockPos next() {
      BlockPos pos = this.iteratorAxis.next();
      switch(this.axis) {
      case 0:
         this.blockPos.setXyz(pos.func_177958_n() * this.kX, pos.func_177956_o() * this.kY, pos.func_177952_p() * this.kZ);
         return this.blockPos;
      case 1:
         this.blockPos.setXyz(pos.func_177956_o() * this.kX, pos.func_177958_n() * this.kY, pos.func_177952_p() * this.kZ);
         return this.blockPos;
      case 2:
         this.blockPos.setXyz(pos.func_177952_p() * this.kX, pos.func_177956_o() * this.kY, pos.func_177958_n() * this.kZ);
         return this.blockPos;
      default:
         this.blockPos.setXyz(pos.func_177958_n() * this.kX, pos.func_177956_o() * this.kY, pos.func_177952_p() * this.kZ);
         return this.blockPos;
      }
   }

   public void remove() {
      throw new RuntimeException("Not supported");
   }

   public static void main(String[] args) {
      BlockPos posStart = new BlockPos(10, 20, 30);
      BlockPos posEnd = new BlockPos(30, 40, 20);
      Iterator3d it = new Iterator3d(posStart, posEnd, 1, 1);

      while(it.hasNext()) {
         BlockPos blockPos = it.next();
         System.out.println("" + blockPos);
      }

   }
}
