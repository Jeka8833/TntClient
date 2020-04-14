package net.optifine;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.optifine.http.FileUploadThread;
import net.optifine.http.IFileUploadListener;
import net.optifine.shaders.Shaders;

public class CrashReporter {
   public static void onCrashReport(CrashReport crashReport, CrashReportCategory category) {
      try {
         Throwable cause = crashReport.func_71505_b();
         if (cause == null) {
            return;
         }

         if (cause.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         extendCrashReport(category);
         if (cause.getClass() == Throwable.class) {
            return;
         }

         GameSettings settings = Config.getGameSettings();
         if (settings == null) {
            return;
         }

         if (!settings.field_74355_t) {
            return;
         }

         String url = "http://optifine.net/crashReport";
         String reportStr = makeReport(crashReport);
         byte[] content = reportStr.getBytes("ASCII");
         IFileUploadListener listener = new IFileUploadListener() {
            public void fileUploadFinished(String url, byte[] content, Throwable exception) {
            }
         };
         Map headers = new HashMap();
         headers.put("OF-Version", Config.getVersion());
         headers.put("OF-Summary", makeSummary(crashReport));
         FileUploadThread fut = new FileUploadThread(url, headers, content, listener);
         fut.setPriority(10);
         fut.start();
         Thread.sleep(1000L);
      } catch (Exception var10) {
         Config.dbg(var10.getClass().getName() + ": " + var10.getMessage());
      }

   }

   private static String makeReport(CrashReport crashReport) {
      StringBuffer sb = new StringBuffer();
      sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
      sb.append("Summary: " + makeSummary(crashReport) + "\n");
      sb.append("\n");
      sb.append(crashReport.func_71502_e());
      sb.append("\n");
      return sb.toString();
   }

   private static String makeSummary(CrashReport crashReport) {
      Throwable t = crashReport.func_71505_b();
      if (t == null) {
         return "Unknown";
      } else {
         StackTraceElement[] traces = t.getStackTrace();
         String firstTrace = "unknown";
         if (traces.length > 0) {
            firstTrace = traces[0].toString().trim();
         }

         String sum = t.getClass().getName() + ": " + t.getMessage() + " (" + crashReport.func_71501_a() + ")" + " [" + firstTrace + "]";
         return sum;
      }
   }

   public static void extendCrashReport(CrashReportCategory cat) {
      cat.func_71507_a("OptiFine Version", Config.getVersion());
      cat.func_71507_a("OptiFine Build", Config.getBuild());
      if (Config.getGameSettings() != null) {
         cat.func_71507_a("Render Distance Chunks", "" + Config.getChunkViewDistance());
         cat.func_71507_a("Mipmaps", "" + Config.getMipmapLevels());
         cat.func_71507_a("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
         cat.func_71507_a("Antialiasing", "" + Config.getAntialiasingLevel());
         cat.func_71507_a("Multitexture", "" + Config.isMultiTexture());
      }

      cat.func_71507_a("Shaders", "" + Shaders.getShaderPackName());
      cat.func_71507_a("OpenGlVersion", "" + Config.openGlVersion);
      cat.func_71507_a("OpenGlRenderer", "" + Config.openGlRenderer);
      cat.func_71507_a("OpenGlVendor", "" + Config.openGlVendor);
      cat.func_71507_a("CpuCount", "" + Config.getAvailableProcessors());
   }
}
