package net.optifine.shaders;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorAxis implements Iterator<BlockPos> {
   private double yDelta;
   private double zDelta;
   private int xStart;
   private int xEnd;
   private double yStart;
   private double yEnd;
   private double zStart;
   private double zEnd;
   private int xNext;
   private double yNext;
   private double zNext;
   private BlockPosM pos = new BlockPosM(0, 0, 0);
   private boolean hasNext = false;

   public IteratorAxis(BlockPos posStart, BlockPos posEnd, double yDelta, double zDelta) {
      this.yDelta = yDelta;
      this.zDelta = zDelta;
      this.xStart = posStart.func_177958_n();
      this.xEnd = posEnd.func_177958_n();
      this.yStart = (double)posStart.func_177956_o();
      this.yEnd = (double)posEnd.func_177956_o() - 0.5D;
      this.zStart = (double)posStart.func_177952_p();
      this.zEnd = (double)posEnd.func_177952_p() - 0.5D;
      this.xNext = this.xStart;
      this.yNext = this.yStart;
      this.zNext = this.zStart;
      this.hasNext = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
   }

   public boolean hasNext() {
      return this.hasNext;
   }

   public BlockPos next() {
      if (!this.hasNext) {
         throw new NoSuchElementException();
      } else {
         this.pos.setXyz((double)this.xNext, this.yNext, this.zNext);
         this.nextPos();
         this.hasNext = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
         return this.pos;
      }
   }

   private void nextPos() {
      ++this.zNext;
      if (this.zNext >= this.zEnd) {
         this.zNext = this.zStart;
         ++this.yNext;
         if (this.yNext >= this.yEnd) {
            this.yNext = this.yStart;
            this.yStart += this.yDelta;
            this.yEnd += this.yDelta;
            this.yNext = this.yStart;
            this.zStart += this.zDelta;
            this.zEnd += this.zDelta;
            this.zNext = this.zStart;
            ++this.xNext;
            if (this.xNext >= this.xEnd) {
               ;
            }
         }
      }
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   public static void main(String[] args) throws Exception {
      BlockPos posStart = new BlockPos(-2, 10, 20);
      BlockPos posEnd = new BlockPos(2, 12, 22);
      double yDelta = -0.5D;
      double zDelta = 0.5D;
      IteratorAxis it = new IteratorAxis(posStart, posEnd, yDelta, zDelta);
      System.out.println("Start: " + posStart + ", end: " + posEnd + ", yDelta: " + yDelta + ", zDelta: " + zDelta);

      while(it.hasNext()) {
         BlockPos blockPos = it.next();
         System.out.println("" + blockPos);
      }

   }
}
