package net.minecraft.util;

import java.lang.reflect.Array;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeBoundList<T> {
   private final T[] field_152759_a;
   private final Class<? extends T> field_152760_b;
   private final ReadWriteLock field_152761_c = new ReentrantReadWriteLock();
   private int field_152762_d;
   private int field_152763_e;

   public ThreadSafeBoundList(Class<? extends T> p_i1126_1_, int p_i1126_2_) {
      this.field_152760_b = p_i1126_1_;
      this.field_152759_a = (Object[])((Object[])Array.newInstance(p_i1126_1_, p_i1126_2_));
   }

   public T func_152757_a(T p_152757_1_) {
      this.field_152761_c.writeLock().lock();
      this.field_152759_a[this.field_152763_e] = p_152757_1_;
      this.field_152763_e = (this.field_152763_e + 1) % this.func_152758_b();
      if (this.field_152762_d < this.func_152758_b()) {
         ++this.field_152762_d;
      }

      this.field_152761_c.writeLock().unlock();
      return p_152757_1_;
   }

   public int func_152758_b() {
      this.field_152761_c.readLock().lock();
      int lvt_1_1_ = this.field_152759_a.length;
      this.field_152761_c.readLock().unlock();
      return lvt_1_1_;
   }

   public T[] func_152756_c() {
      T[] lvt_1_1_ = (Object[])((Object[])Array.newInstance(this.field_152760_b, this.field_152762_d));
      this.field_152761_c.readLock().lock();

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_152762_d; ++lvt_2_1_) {
         int lvt_3_1_ = (this.field_152763_e - this.field_152762_d + lvt_2_1_) % this.func_152758_b();
         if (lvt_3_1_ < 0) {
            lvt_3_1_ += this.func_152758_b();
         }

         lvt_1_1_[lvt_2_1_] = this.field_152759_a[lvt_3_1_];
      }

      this.field_152761_c.readLock().unlock();
      return lvt_1_1_;
   }
}
