package net.optifine.render;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.util.LinkedList;

public class VboRegion {
   private EnumWorldBlockLayer layer = null;
   private int glBufferId = OpenGlHelper.func_176073_e();
   private int capacity = 4096;
   private int positionTop = 0;
   private int sizeUsed;
   private LinkedList<VboRange> rangeList = new LinkedList();
   private VboRange compactRangeLast = null;
   private IntBuffer bufferIndexVertex;
   private IntBuffer bufferCountVertex;
   private int drawMode;
   private final int vertexBytes;

   public VboRegion(EnumWorldBlockLayer layer) {
      this.bufferIndexVertex = Config.createDirectIntBuffer(this.capacity);
      this.bufferCountVertex = Config.createDirectIntBuffer(this.capacity);
      this.drawMode = 7;
      this.vertexBytes = DefaultVertexFormats.field_176600_a.func_177338_f();
      this.layer = layer;
      this.bindBuffer();
      long capacityBytes = this.toBytes(this.capacity);
      OpenGlHelper.glBufferData(OpenGlHelper.field_176089_P, capacityBytes, OpenGlHelper.field_148826_e);
      this.unbindBuffer();
   }

   public void bufferData(ByteBuffer data, VboRange range) {
      int posOld = range.getPosition();
      int sizeOld = range.getSize();
      int sizeNew = this.toVertex((long)data.limit());
      if (sizeNew <= 0) {
         if (posOld >= 0) {
            range.setPosition(-1);
            range.setSize(0);
            this.rangeList.remove(range.getNode());
            this.sizeUsed -= sizeOld;
         }

      } else {
         if (sizeNew > sizeOld) {
            range.setPosition(this.positionTop);
            range.setSize(sizeNew);
            this.positionTop += sizeNew;
            if (posOld >= 0) {
               this.rangeList.remove(range.getNode());
            }

            this.rangeList.addLast(range.getNode());
         }

         range.setSize(sizeNew);
         this.sizeUsed += sizeNew - sizeOld;
         this.checkVboSize(range.getPositionNext());
         long positionBytes = this.toBytes(range.getPosition());
         this.bindBuffer();
         OpenGlHelper.glBufferSubData(OpenGlHelper.field_176089_P, positionBytes, data);
         this.unbindBuffer();
         if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
         }

      }
   }

   private void compactRanges(int countMax) {
      if (!this.rangeList.isEmpty()) {
         VboRange range = this.compactRangeLast;
         if (range == null || !this.rangeList.contains(range.getNode())) {
            range = (VboRange)this.rangeList.getFirst().getItem();
         }

         int posCompact = range.getPosition();
         VboRange rangePrev = range.getPrev();
         if (rangePrev == null) {
            posCompact = 0;
         } else {
            posCompact = rangePrev.getPositionNext();
         }

         int count = 0;

         while(range != null && count < countMax) {
            ++count;
            if (range.getPosition() == posCompact) {
               posCompact += range.getSize();
               range = range.getNext();
            } else {
               int sizeFree = range.getPosition() - posCompact;
               if (range.getSize() <= sizeFree) {
                  this.copyVboData(range.getPosition(), posCompact, range.getSize());
                  range.setPosition(posCompact);
                  posCompact += range.getSize();
                  range = range.getNext();
               } else {
                  this.checkVboSize(this.positionTop + range.getSize());
                  this.copyVboData(range.getPosition(), this.positionTop, range.getSize());
                  range.setPosition(this.positionTop);
                  this.positionTop += range.getSize();
                  VboRange rangeNext = range.getNext();
                  this.rangeList.remove(range.getNode());
                  this.rangeList.addLast(range.getNode());
                  range = rangeNext;
               }
            }
         }

         if (range == null) {
            this.positionTop = ((VboRange)this.rangeList.getLast().getItem()).getPositionNext();
         }

         this.compactRangeLast = range;
      }
   }

   private void checkRanges() {
      int count = 0;
      int size = 0;

      for(VboRange range = (VboRange)this.rangeList.getFirst().getItem(); range != null; range = range.getNext()) {
         ++count;
         size += range.getSize();
         if (range.getPosition() < 0 || range.getSize() <= 0 || range.getPositionNext() > this.positionTop) {
            throw new RuntimeException("Invalid range: " + range);
         }

         VboRange rangePrev = range.getPrev();
         if (rangePrev != null && range.getPosition() < rangePrev.getPositionNext()) {
            throw new RuntimeException("Invalid range: " + range);
         }

         VboRange rangeNext = range.getNext();
         if (rangeNext != null && range.getPositionNext() > rangeNext.getPosition()) {
            throw new RuntimeException("Invalid range: " + range);
         }
      }

      if (count != this.rangeList.getSize()) {
         throw new RuntimeException("Invalid count: " + count + " <> " + this.rangeList.getSize());
      } else if (size != this.sizeUsed) {
         throw new RuntimeException("Invalid size: " + size + " <> " + this.sizeUsed);
      }
   }

   private void checkVboSize(int sizeMin) {
      if (this.capacity < sizeMin) {
         this.expandVbo(sizeMin);
      }

   }

   private void copyVboData(int posFrom, int posTo, int size) {
      long posFromBytes = this.toBytes(posFrom);
      long posToBytes = this.toBytes(posTo);
      long sizeBytes = this.toBytes(size);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_WRITE_BUFFER, this.glBufferId);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, posFromBytes, posToBytes, sizeBytes);
      Config.checkGlError("Copy VBO range");
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
   }

   private void expandVbo(int sizeMin) {
      int capacityNew;
      for(capacityNew = this.capacity * 6 / 4; capacityNew < sizeMin; capacityNew = capacityNew * 6 / 4) {
      }

      long capacityBytes = this.toBytes(this.capacity);
      long capacityNewBytes = this.toBytes(capacityNew);
      int glBufferIdNew = OpenGlHelper.func_176073_e();
      OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, glBufferIdNew);
      OpenGlHelper.glBufferData(OpenGlHelper.field_176089_P, capacityNewBytes, OpenGlHelper.field_148826_e);
      Config.checkGlError("Expand VBO");
      OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_WRITE_BUFFER, glBufferIdNew);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, 0L, 0L, capacityBytes);
      Config.checkGlError("Copy VBO: " + capacityNewBytes);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.func_176072_g(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
      OpenGlHelper.func_176074_g(this.glBufferId);
      this.bufferIndexVertex = Config.createDirectIntBuffer(capacityNew);
      this.bufferCountVertex = Config.createDirectIntBuffer(capacityNew);
      this.glBufferId = glBufferIdNew;
      this.capacity = capacityNew;
   }

   public void bindBuffer() {
      OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, this.glBufferId);
   }

   public void drawArrays(int drawMode, VboRange range) {
      if (this.drawMode != drawMode) {
         if (this.bufferIndexVertex.position() > 0) {
            throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + drawMode);
         }

         this.drawMode = drawMode;
      }

      this.bufferIndexVertex.put(range.getPosition());
      this.bufferCountVertex.put(range.getSize());
   }

   public void finishDraw(VboRenderList vboRenderList) {
      this.bindBuffer();
      vboRenderList.func_178010_a();
      this.bufferIndexVertex.flip();
      this.bufferCountVertex.flip();
      GlStateManager.glMultiDrawArrays(this.drawMode, this.bufferIndexVertex, this.bufferCountVertex);
      this.bufferIndexVertex.limit(this.bufferIndexVertex.capacity());
      this.bufferCountVertex.limit(this.bufferCountVertex.capacity());
      if (this.positionTop > this.sizeUsed * 11 / 10) {
         this.compactRanges(1);
      }

   }

   public void unbindBuffer() {
      OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
   }

   public void deleteGlBuffers() {
      if (this.glBufferId >= 0) {
         OpenGlHelper.func_176074_g(this.glBufferId);
         this.glBufferId = -1;
      }

   }

   private long toBytes(int vertex) {
      return (long)vertex * (long)this.vertexBytes;
   }

   private int toVertex(long bytes) {
      return (int)(bytes / (long)this.vertexBytes);
   }

   public int getPositionTop() {
      return this.positionTop;
   }
}
