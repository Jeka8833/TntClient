package net.optifine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.profiler.Profiler;
import net.minecraft.src.Config;
import org.lwjgl.opengl.GL11;

public class Lagometer {
   private static Minecraft mc;
   private static GameSettings gameSettings;
   private static Profiler profiler;
   public static boolean active = false;
   public static Lagometer.TimerNano timerTick = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerScheduledExecutables = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerChunkUpload = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerChunkUpdate = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerVisibility = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerTerrain = new Lagometer.TimerNano();
   public static Lagometer.TimerNano timerServer = new Lagometer.TimerNano();
   private static long[] timesFrame = new long[512];
   private static long[] timesTick = new long[512];
   private static long[] timesScheduledExecutables = new long[512];
   private static long[] timesChunkUpload = new long[512];
   private static long[] timesChunkUpdate = new long[512];
   private static long[] timesVisibility = new long[512];
   private static long[] timesTerrain = new long[512];
   private static long[] timesServer = new long[512];
   private static boolean[] gcs = new boolean[512];
   private static int numRecordedFrameTimes = 0;
   private static long prevFrameTimeNano = -1L;
   private static long renderTimeNano = 0L;
   private static long memTimeStartMs = System.currentTimeMillis();
   private static long memStart = getMemoryUsed();
   private static long memTimeLast;
   private static long memLast;
   private static long memTimeDiffMs;
   private static long memDiff;
   private static int memMbSec;

   public static boolean updateMemoryAllocation() {
      long timeNowMs = System.currentTimeMillis();
      long memNow = getMemoryUsed();
      boolean gc = false;
      if (memNow < memLast) {
         double memDiffMb = (double)memDiff / 1000000.0D;
         double timeDiffSec = (double)memTimeDiffMs / 1000.0D;
         int mbSec = (int)(memDiffMb / timeDiffSec);
         if (mbSec > 0) {
            memMbSec = mbSec;
         }

         memTimeStartMs = timeNowMs;
         memStart = memNow;
         memTimeDiffMs = 0L;
         memDiff = 0L;
         gc = true;
      } else {
         memTimeDiffMs = timeNowMs - memTimeStartMs;
         memDiff = memNow - memStart;
      }

      memTimeLast = timeNowMs;
      memLast = memNow;
      return gc;
   }

   private static long getMemoryUsed() {
      Runtime r = Runtime.getRuntime();
      return r.totalMemory() - r.freeMemory();
   }

   public static void updateLagometer() {
      if (mc == null) {
         mc = Minecraft.func_71410_x();
         gameSettings = mc.field_71474_y;
         profiler = mc.field_71424_I;
      }

      if (gameSettings.field_74329_Q && (gameSettings.ofLagometer || gameSettings.field_74332_R)) {
         active = true;
         long timeNowNano = System.nanoTime();
         if (prevFrameTimeNano == -1L) {
            prevFrameTimeNano = timeNowNano;
         } else {
            int frameIndex = numRecordedFrameTimes & timesFrame.length - 1;
            ++numRecordedFrameTimes;
            boolean gc = updateMemoryAllocation();
            timesFrame[frameIndex] = timeNowNano - prevFrameTimeNano - renderTimeNano;
            timesTick[frameIndex] = timerTick.timeNano;
            timesScheduledExecutables[frameIndex] = timerScheduledExecutables.timeNano;
            timesChunkUpload[frameIndex] = timerChunkUpload.timeNano;
            timesChunkUpdate[frameIndex] = timerChunkUpdate.timeNano;
            timesVisibility[frameIndex] = timerVisibility.timeNano;
            timesTerrain[frameIndex] = timerTerrain.timeNano;
            timesServer[frameIndex] = timerServer.timeNano;
            gcs[frameIndex] = gc;
            timerTick.reset();
            timerScheduledExecutables.reset();
            timerVisibility.reset();
            timerChunkUpdate.reset();
            timerChunkUpload.reset();
            timerTerrain.reset();
            timerServer.reset();
            prevFrameTimeNano = System.nanoTime();
         }
      } else {
         active = false;
         prevFrameTimeNano = -1L;
      }
   }

   public static void showLagometer(ScaledResolution scaledResolution) {
      if (gameSettings != null) {
         if (gameSettings.ofLagometer || gameSettings.field_74332_R) {
            long timeRenderStartNano = System.nanoTime();
            GlStateManager.func_179086_m(256);
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179094_E();
            GlStateManager.func_179142_g();
            GlStateManager.func_179096_D();
            GlStateManager.func_179130_a(0.0D, (double)mc.field_71443_c, (double)mc.field_71440_d, 0.0D, 1000.0D, 3000.0D);
            GlStateManager.func_179128_n(5888);
            GlStateManager.func_179094_E();
            GlStateManager.func_179096_D();
            GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
            GL11.glLineWidth(1.0F);
            GlStateManager.func_179090_x();
            Tessellator tess = Tessellator.func_178181_a();
            WorldRenderer tessellator = tess.func_178180_c();
            tessellator.func_181668_a(1, DefaultVertexFormats.field_181706_f);

            int frameNum;
            int lum;
            float baseHeight;
            for(frameNum = 0; frameNum < timesFrame.length; ++frameNum) {
               lum = (frameNum - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
               lum += 155;
               baseHeight = (float)mc.field_71440_d;
               long heightFrame = 0L;
               if (gcs[frameNum]) {
                  renderTime(frameNum, timesFrame[frameNum], lum, lum / 2, 0, baseHeight, tessellator);
               } else {
                  renderTime(frameNum, timesFrame[frameNum], lum, lum, lum, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesServer[frameNum], lum / 2, lum / 2, lum / 2, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesTerrain[frameNum], 0, lum, 0, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesVisibility[frameNum], lum, lum, 0, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesChunkUpdate[frameNum], lum, 0, 0, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesChunkUpload[frameNum], lum, 0, lum, baseHeight, tessellator);
                  baseHeight -= (float)renderTime(frameNum, timesScheduledExecutables[frameNum], 0, 0, lum, baseHeight, tessellator);
                  float var10000 = baseHeight - (float)renderTime(frameNum, timesTick[frameNum], 0, lum, lum, baseHeight, tessellator);
               }
            }

            renderTimeDivider(0, timesFrame.length, 33333333L, 196, 196, 196, (float)mc.field_71440_d, tessellator);
            renderTimeDivider(0, timesFrame.length, 16666666L, 196, 196, 196, (float)mc.field_71440_d, tessellator);
            tess.func_78381_a();
            GlStateManager.func_179098_w();
            frameNum = mc.field_71440_d - 80;
            lum = mc.field_71440_d - 160;
            mc.field_71466_p.func_78276_b("30", 2, lum + 1, -8947849);
            mc.field_71466_p.func_78276_b("30", 1, lum, -3881788);
            mc.field_71466_p.func_78276_b("60", 2, frameNum + 1, -8947849);
            mc.field_71466_p.func_78276_b("60", 1, frameNum, -3881788);
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
            GlStateManager.func_179121_F();
            GlStateManager.func_179098_w();
            baseHeight = 1.0F - (float)((double)(System.currentTimeMillis() - memTimeStartMs) / 1000.0D);
            baseHeight = Config.limit(baseHeight, 0.0F, 1.0F);
            int memColR = (int)(170.0F + baseHeight * 85.0F);
            int memColG = (int)(100.0F + baseHeight * 55.0F);
            int memColB = (int)(10.0F + baseHeight * 10.0F);
            int colMem = memColR << 16 | memColG << 8 | memColB;
            int posX = 512 / scaledResolution.func_78325_e() + 2;
            int posY = mc.field_71440_d / scaledResolution.func_78325_e() - 8;
            GuiIngame var15 = mc.field_71456_v;
            GuiIngame.func_73734_a(posX - 1, posY - 1, posX + 50, posY + 10, -1605349296);
            mc.field_71466_p.func_78276_b(" " + memMbSec + " MB/s", posX, posY, colMem);
            renderTimeNano = System.nanoTime() - timeRenderStartNano;
         }
      }
   }

   private static long renderTime(int frameNum, long time, int r, int g, int b, float baseHeight, WorldRenderer tessellator) {
      long heightTime = time / 200000L;
      if (heightTime < 3L) {
         return 0L;
      } else {
         tessellator.func_181662_b((double)((float)frameNum + 0.5F), (double)(baseHeight - (float)heightTime + 0.5F), 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
         tessellator.func_181662_b((double)((float)frameNum + 0.5F), (double)(baseHeight + 0.5F), 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
         return heightTime;
      }
   }

   private static long renderTimeDivider(int frameStart, int frameEnd, long time, int r, int g, int b, float baseHeight, WorldRenderer tessellator) {
      long heightTime = time / 200000L;
      if (heightTime < 3L) {
         return 0L;
      } else {
         tessellator.func_181662_b((double)((float)frameStart + 0.5F), (double)(baseHeight - (float)heightTime + 0.5F), 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
         tessellator.func_181662_b((double)((float)frameEnd + 0.5F), (double)(baseHeight - (float)heightTime + 0.5F), 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
         return heightTime;
      }
   }

   public static boolean isActive() {
      return active;
   }

   static {
      memTimeLast = memTimeStartMs;
      memLast = memStart;
      memTimeDiffMs = 1L;
      memDiff = 0L;
      memMbSec = 0;
   }

   public static class TimerNano {
      public long timeStartNano = 0L;
      public long timeNano = 0L;

      public void start() {
         if (Lagometer.active) {
            if (this.timeStartNano == 0L) {
               this.timeStartNano = System.nanoTime();
            }

         }
      }

      public void end() {
         if (Lagometer.active) {
            if (this.timeStartNano != 0L) {
               this.timeNano += System.nanoTime() - this.timeStartNano;
               this.timeStartNano = 0L;
            }

         }
      }

      private void reset() {
         this.timeNano = 0L;
         this.timeStartNano = 0L;
      }
   }
}
