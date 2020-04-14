package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumWorldBlockLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkRenderWorker implements Runnable {
   private static final Logger field_152478_a = LogManager.getLogger();
   private final ChunkRenderDispatcher field_178477_b;
   private final RegionRenderCacheBuilder field_178478_c;

   public ChunkRenderWorker(ChunkRenderDispatcher p_i46201_1_) {
      this(p_i46201_1_, (RegionRenderCacheBuilder)null);
   }

   public ChunkRenderWorker(ChunkRenderDispatcher p_i46202_1_, RegionRenderCacheBuilder p_i46202_2_) {
      this.field_178477_b = p_i46202_1_;
      this.field_178478_c = p_i46202_2_;
   }

   public void run() {
      while(true) {
         try {
            this.func_178474_a(this.field_178477_b.func_178511_d());
         } catch (InterruptedException var3) {
            field_152478_a.debug("Stopping due to interrupt");
            return;
         } catch (Throwable var4) {
            CrashReport lvt_2_1_ = CrashReport.func_85055_a(var4, "Batching chunks");
            Minecraft.func_71410_x().func_71404_a(Minecraft.func_71410_x().func_71396_d(lvt_2_1_));
            return;
         }
      }
   }

   protected void func_178474_a(final ChunkCompileTaskGenerator p_178474_1_) throws InterruptedException {
      p_178474_1_.func_178540_f().lock();

      label242: {
         try {
            if (p_178474_1_.func_178546_a() == ChunkCompileTaskGenerator.Status.PENDING) {
               p_178474_1_.func_178535_a(ChunkCompileTaskGenerator.Status.COMPILING);
               break label242;
            }

            if (!p_178474_1_.func_178537_h()) {
               field_152478_a.warn("Chunk render task was " + p_178474_1_.func_178546_a() + " when I expected it to be pending; ignoring task");
            }
         } finally {
            p_178474_1_.func_178540_f().unlock();
         }

         return;
      }

      Entity lvt_2_1_ = Minecraft.func_71410_x().func_175606_aa();
      if (lvt_2_1_ == null) {
         p_178474_1_.func_178542_e();
      } else {
         p_178474_1_.func_178541_a(this.func_178475_b());
         float lvt_3_1_ = (float)lvt_2_1_.field_70165_t;
         float lvt_4_1_ = (float)lvt_2_1_.field_70163_u + lvt_2_1_.func_70047_e();
         float lvt_5_1_ = (float)lvt_2_1_.field_70161_v;
         ChunkCompileTaskGenerator.Type lvt_6_1_ = p_178474_1_.func_178538_g();
         if (lvt_6_1_ == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK) {
            p_178474_1_.func_178536_b().func_178581_b(lvt_3_1_, lvt_4_1_, lvt_5_1_, p_178474_1_);
         } else if (lvt_6_1_ == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY) {
            p_178474_1_.func_178536_b().func_178570_a(lvt_3_1_, lvt_4_1_, lvt_5_1_, p_178474_1_);
         }

         p_178474_1_.func_178540_f().lock();

         try {
            if (p_178474_1_.func_178546_a() != ChunkCompileTaskGenerator.Status.COMPILING) {
               if (!p_178474_1_.func_178537_h()) {
                  field_152478_a.warn("Chunk render task was " + p_178474_1_.func_178546_a() + " when I expected it to be compiling; aborting task");
               }

               this.func_178473_b(p_178474_1_);
               return;
            }

            p_178474_1_.func_178535_a(ChunkCompileTaskGenerator.Status.UPLOADING);
         } finally {
            p_178474_1_.func_178540_f().unlock();
         }

         final CompiledChunk lvt_7_1_ = p_178474_1_.func_178544_c();
         ArrayList lvt_8_1_ = Lists.newArrayList();
         if (lvt_6_1_ == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK) {
            EnumWorldBlockLayer[] lvt_9_1_ = EnumWorldBlockLayer.values();
            int lvt_10_1_ = lvt_9_1_.length;

            for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_1_; ++lvt_11_1_) {
               EnumWorldBlockLayer lvt_12_1_ = lvt_9_1_[lvt_11_1_];
               if (lvt_7_1_.func_178492_d(lvt_12_1_)) {
                  lvt_8_1_.add(this.field_178477_b.func_178503_a(lvt_12_1_, p_178474_1_.func_178545_d().func_179038_a(lvt_12_1_), p_178474_1_.func_178536_b(), lvt_7_1_));
               }
            }
         } else if (lvt_6_1_ == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY) {
            lvt_8_1_.add(this.field_178477_b.func_178503_a(EnumWorldBlockLayer.TRANSLUCENT, p_178474_1_.func_178545_d().func_179038_a(EnumWorldBlockLayer.TRANSLUCENT), p_178474_1_.func_178536_b(), lvt_7_1_));
         }

         final ListenableFuture<List<Object>> lvt_9_2_ = Futures.allAsList((Iterable)lvt_8_1_);
         p_178474_1_.func_178539_a(new Runnable() {
            public void run() {
               lvt_9_2_.cancel(false);
            }
         });
         Futures.addCallback(lvt_9_2_, new FutureCallback<List<Object>>() {
            public void onSuccess(List<Object> p_onSuccess_1_) {
               ChunkRenderWorker.this.func_178473_b(p_178474_1_);
               p_178474_1_.func_178540_f().lock();

               try {
                  if (p_178474_1_.func_178546_a() != ChunkCompileTaskGenerator.Status.UPLOADING) {
                     if (!p_178474_1_.func_178537_h()) {
                        ChunkRenderWorker.field_152478_a.warn("Chunk render task was " + p_178474_1_.func_178546_a() + " when I expected it to be uploading; aborting task");
                     }

                     return;
                  }

                  p_178474_1_.func_178535_a(ChunkCompileTaskGenerator.Status.DONE);
               } finally {
                  p_178474_1_.func_178540_f().unlock();
               }

               p_178474_1_.func_178536_b().func_178580_a(lvt_7_1_);
            }

            public void onFailure(Throwable p_onFailure_1_) {
               ChunkRenderWorker.this.func_178473_b(p_178474_1_);
               if (!(p_onFailure_1_ instanceof CancellationException) && !(p_onFailure_1_ instanceof InterruptedException)) {
                  Minecraft.func_71410_x().func_71404_a(CrashReport.func_85055_a(p_onFailure_1_, "Rendering chunk"));
               }

            }
         });
      }
   }

   private RegionRenderCacheBuilder func_178475_b() throws InterruptedException {
      return this.field_178478_c != null ? this.field_178478_c : this.field_178477_b.func_178515_c();
   }

   private void func_178473_b(ChunkCompileTaskGenerator p_178473_1_) {
      if (this.field_178478_c == null) {
         this.field_178477_b.func_178512_a(p_178473_1_.func_178545_d());
      }

   }
}
