package net.minecraft.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.src.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenShotHelper {
   private static final Logger field_148261_a = LogManager.getLogger();
   private static final DateFormat field_74295_a = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private static IntBuffer field_74293_b;
   private static int[] field_74294_c;

   public static IChatComponent func_148260_a(File p_148260_0_, int p_148260_1_, int p_148260_2_, Framebuffer p_148260_3_) {
      return func_148259_a(p_148260_0_, (String)null, p_148260_1_, p_148260_2_, p_148260_3_);
   }

   public static IChatComponent func_148259_a(File p_148259_0_, String p_148259_1_, int p_148259_2_, int p_148259_3_, Framebuffer p_148259_4_) {
      try {
         File file1 = new File(p_148259_0_, "screenshots");
         file1.mkdir();
         Minecraft mc = Minecraft.func_71410_x();
         int guiScaleOld = Config.getGameSettings().field_74362_aa;
         ScaledResolution sr = new ScaledResolution(mc);
         int guiScale = sr.func_78325_e();
         int mul = Config.getScreenshotSize();
         boolean resize = OpenGlHelper.func_148822_b() && mul > 1;
         if (resize) {
            Config.getGameSettings().field_74362_aa = guiScale * mul;
            resize(p_148259_2_ * mul, p_148259_3_ * mul);
            GlStateManager.func_179094_E();
            GlStateManager.func_179086_m(16640);
            mc.func_147110_a().func_147610_a(true);
            mc.field_71460_t.func_181560_a(Config.renderPartialTicks, System.nanoTime());
         }

         if (OpenGlHelper.func_148822_b()) {
            p_148259_2_ = p_148259_4_.field_147622_a;
            p_148259_3_ = p_148259_4_.field_147620_b;
         }

         int i = p_148259_2_ * p_148259_3_;
         if (field_74293_b == null || field_74293_b.capacity() < i) {
            field_74293_b = BufferUtils.createIntBuffer(i);
            field_74294_c = new int[i];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         field_74293_b.clear();
         if (OpenGlHelper.func_148822_b()) {
            GlStateManager.func_179144_i(p_148259_4_.field_147617_g);
            GL11.glGetTexImage(3553, 0, 32993, 33639, (IntBuffer)field_74293_b);
         } else {
            GL11.glReadPixels(0, 0, p_148259_2_, p_148259_3_, 32993, 33639, (IntBuffer)field_74293_b);
         }

         field_74293_b.get(field_74294_c);
         TextureUtil.func_147953_a(field_74294_c, p_148259_2_, p_148259_3_);
         BufferedImage bufferedimage = null;
         if (OpenGlHelper.func_148822_b()) {
            bufferedimage = new BufferedImage(p_148259_4_.field_147621_c, p_148259_4_.field_147618_d, 1);
            int j = p_148259_4_.field_147620_b - p_148259_4_.field_147618_d;

            for(int k = j; k < p_148259_4_.field_147620_b; ++k) {
               for(int l = 0; l < p_148259_4_.field_147621_c; ++l) {
                  bufferedimage.setRGB(l, k - j, field_74294_c[k * p_148259_4_.field_147622_a + l]);
               }
            }
         } else {
            bufferedimage = new BufferedImage(p_148259_2_, p_148259_3_, 1);
            bufferedimage.setRGB(0, 0, p_148259_2_, p_148259_3_, field_74294_c, 0, p_148259_2_);
         }

         if (resize) {
            mc.func_147110_a().func_147609_e();
            GlStateManager.func_179121_F();
            Config.getGameSettings().field_74362_aa = guiScaleOld;
            resize(p_148259_2_, p_148259_3_);
         }

         File file2;
         if (p_148259_1_ == null) {
            file2 = func_74290_a(file1);
         } else {
            file2 = new File(file1, p_148259_1_);
         }

         file2 = file2.getCanonicalFile();
         ImageIO.write(bufferedimage, "png", file2);
         IChatComponent ichatcomponent = new ChatComponentText(file2.getName());
         ichatcomponent.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_FILE, file2.getAbsolutePath()));
         ichatcomponent.func_150256_b().func_150228_d(true);
         return new ChatComponentTranslation("screenshot.success", new Object[]{ichatcomponent});
      } catch (Exception var17) {
         field_148261_a.warn((String)"Couldn't save screenshot", (Throwable)var17);
         return new ChatComponentTranslation("screenshot.failure", new Object[]{var17.getMessage()});
      }
   }

   private static File func_74290_a(File p_74290_0_) {
      String s = field_74295_a.format(new Date()).toString();
      int i = 1;

      while(true) {
         File file1 = new File(p_74290_0_, s + (i == 1 ? "" : "_" + i) + ".png");
         if (!file1.exists()) {
            return file1;
         }

         ++i;
      }
   }

   private static void resize(int p_resize_0_, int p_resize_1_) {
      Minecraft mc = Minecraft.func_71410_x();
      mc.field_71443_c = Math.max(1, p_resize_0_);
      mc.field_71440_d = Math.max(1, p_resize_1_);
      if (mc.field_71462_r != null) {
         ScaledResolution sr = new ScaledResolution(mc);
         mc.field_71462_r.func_175273_b(mc, sr.func_78326_a(), sr.func_78328_b());
      }

      updateFramebufferSize();
   }

   private static void updateFramebufferSize() {
      Minecraft mc = Minecraft.func_71410_x();
      mc.func_147110_a().func_147613_a(mc.field_71443_c, mc.field_71440_d);
      if (mc.field_71460_t != null) {
         mc.field_71460_t.func_147704_a(mc.field_71443_c, mc.field_71440_d);
      }

   }
}
