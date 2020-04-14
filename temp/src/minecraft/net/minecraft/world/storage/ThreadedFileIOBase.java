package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class ThreadedFileIOBase implements Runnable {
   private static final ThreadedFileIOBase field_75741_a = new ThreadedFileIOBase();
   private List<IThreadedFileIO> field_75739_b = Collections.synchronizedList(Lists.newArrayList());
   private volatile long field_75740_c;
   private volatile long field_75737_d;
   private volatile boolean field_75738_e;

   private ThreadedFileIOBase() {
      Thread lvt_1_1_ = new Thread(this, "File IO Thread");
      lvt_1_1_.setPriority(1);
      lvt_1_1_.start();
   }

   public static ThreadedFileIOBase func_178779_a() {
      return field_75741_a;
   }

   public void run() {
      while(true) {
         this.func_75736_b();
      }
   }

   private void func_75736_b() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75739_b.size(); ++lvt_1_1_) {
         IThreadedFileIO lvt_2_1_ = (IThreadedFileIO)this.field_75739_b.get(lvt_1_1_);
         boolean lvt_3_1_ = lvt_2_1_.func_75814_c();
         if (!lvt_3_1_) {
            this.field_75739_b.remove(lvt_1_1_--);
            ++this.field_75737_d;
         }

         try {
            Thread.sleep(this.field_75738_e ? 0L : 10L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if (this.field_75739_b.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }
      }

   }

   public void func_75735_a(IThreadedFileIO p_75735_1_) {
      if (!this.field_75739_b.contains(p_75735_1_)) {
         ++this.field_75740_c;
         this.field_75739_b.add(p_75735_1_);
      }
   }

   public void func_75734_a() throws InterruptedException {
      this.field_75738_e = true;

      while(this.field_75740_c != this.field_75737_d) {
         Thread.sleep(10L);
      }

      this.field_75738_e = false;
   }
}
