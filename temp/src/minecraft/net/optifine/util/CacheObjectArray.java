package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import net.minecraft.block.state.IBlockState;
import net.minecraft.src.Config;

public class CacheObjectArray {
   private static ArrayDeque<int[]> arrays = new ArrayDeque();
   private static int maxCacheSize = 10;

   private static synchronized int[] allocateArray(int size) {
      int[] ints = (int[])arrays.pollLast();
      if (ints == null || ints.length < size) {
         ints = new int[size];
      }

      return ints;
   }

   public static synchronized void freeArray(int[] ints) {
      if (arrays.size() < maxCacheSize) {
         arrays.add(ints);
      }
   }

   public static void main(String[] args) throws Exception {
      int size = 4096;
      int count = 500000;
      testNew(size, count);
      testClone(size, count);
      testNewObj(size, count);
      testCloneObj(size, count);
      testNewObjDyn(IBlockState.class, size, count);
      long timeNew = testNew(size, count);
      long timeClone = testClone(size, count);
      long timeNewObj = testNewObj(size, count);
      long timeCloneObj = testCloneObj(size, count);
      long timeNewObjDyn = testNewObjDyn(IBlockState.class, size, count);
      Config.dbg("New: " + timeNew);
      Config.dbg("Clone: " + timeClone);
      Config.dbg("NewObj: " + timeNewObj);
      Config.dbg("CloneObj: " + timeCloneObj);
      Config.dbg("NewObjDyn: " + timeNewObjDyn);
   }

   private static long testClone(int size, int count) {
      long timeStart = System.currentTimeMillis();
      int[] template = new int[size];

      for(int i = 0; i < count; ++i) {
         int[] var6 = (int[])template.clone();
      }

      long timeEnd = System.currentTimeMillis();
      return timeEnd - timeStart;
   }

   private static long testNew(int size, int count) {
      long timeStart = System.currentTimeMillis();

      for(int i = 0; i < count; ++i) {
         int[] var5 = (int[])((int[])Array.newInstance(Integer.TYPE, size));
      }

      long timeEnd = System.currentTimeMillis();
      return timeEnd - timeStart;
   }

   private static long testCloneObj(int size, int count) {
      long timeStart = System.currentTimeMillis();
      IBlockState[] template = new IBlockState[size];

      for(int i = 0; i < count; ++i) {
         IBlockState[] var6 = (IBlockState[])template.clone();
      }

      long timeEnd = System.currentTimeMillis();
      return timeEnd - timeStart;
   }

   private static long testNewObj(int size, int count) {
      long timeStart = System.currentTimeMillis();

      for(int i = 0; i < count; ++i) {
         IBlockState[] var5 = new IBlockState[size];
      }

      long timeEnd = System.currentTimeMillis();
      return timeEnd - timeStart;
   }

   private static long testNewObjDyn(Class cls, int size, int count) {
      long timeStart = System.currentTimeMillis();

      for(int i = 0; i < count; ++i) {
         Object[] var6 = (Object[])((Object[])Array.newInstance(cls, size));
      }

      long timeEnd = System.currentTimeMillis();
      return timeEnd - timeStart;
   }
}
