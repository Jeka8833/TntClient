package net.minecraft.util;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtil {
   public static final ListeningExecutorService field_180193_a = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool((new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("Downloader %d").build()));
   private static final AtomicInteger field_151228_a = new AtomicInteger(0);
   private static final Logger field_151227_b = LogManager.getLogger();

   public static String func_76179_a(Map<String, Object> p_76179_0_) {
      StringBuilder lvt_1_1_ = new StringBuilder();
      Iterator lvt_2_1_ = p_76179_0_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, Object> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (lvt_1_1_.length() > 0) {
            lvt_1_1_.append('&');
         }

         try {
            lvt_1_1_.append(URLEncoder.encode((String)lvt_3_1_.getKey(), "UTF-8"));
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
         }

         if (lvt_3_1_.getValue() != null) {
            lvt_1_1_.append('=');

            try {
               lvt_1_1_.append(URLEncoder.encode(lvt_3_1_.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException var5) {
               var5.printStackTrace();
            }
         }
      }

      return lvt_1_1_.toString();
   }

   public static String func_151226_a(URL p_151226_0_, Map<String, Object> p_151226_1_, boolean p_151226_2_) {
      return func_151225_a(p_151226_0_, func_76179_a(p_151226_1_), p_151226_2_);
   }

   private static String func_151225_a(URL p_151225_0_, String p_151225_1_, boolean p_151225_2_) {
      try {
         Proxy lvt_3_1_ = MinecraftServer.func_71276_C() == null ? null : MinecraftServer.func_71276_C().func_110454_ao();
         if (lvt_3_1_ == null) {
            lvt_3_1_ = Proxy.NO_PROXY;
         }

         HttpURLConnection lvt_4_1_ = (HttpURLConnection)p_151225_0_.openConnection(lvt_3_1_);
         lvt_4_1_.setRequestMethod("POST");
         lvt_4_1_.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         lvt_4_1_.setRequestProperty("Content-Length", "" + p_151225_1_.getBytes().length);
         lvt_4_1_.setRequestProperty("Content-Language", "en-US");
         lvt_4_1_.setUseCaches(false);
         lvt_4_1_.setDoInput(true);
         lvt_4_1_.setDoOutput(true);
         DataOutputStream lvt_5_1_ = new DataOutputStream(lvt_4_1_.getOutputStream());
         lvt_5_1_.writeBytes(p_151225_1_);
         lvt_5_1_.flush();
         lvt_5_1_.close();
         BufferedReader lvt_6_1_ = new BufferedReader(new InputStreamReader(lvt_4_1_.getInputStream()));
         StringBuffer lvt_8_1_ = new StringBuffer();

         String lvt_7_1_;
         while((lvt_7_1_ = lvt_6_1_.readLine()) != null) {
            lvt_8_1_.append(lvt_7_1_);
            lvt_8_1_.append('\r');
         }

         lvt_6_1_.close();
         return lvt_8_1_.toString();
      } catch (Exception var9) {
         if (!p_151225_2_) {
            field_151227_b.error((String)("Could not post to " + p_151225_0_), (Throwable)var9);
         }

         return "";
      }
   }

   public static ListenableFuture<Object> func_180192_a(final File p_180192_0_, final String p_180192_1_, final Map<String, String> p_180192_2_, final int p_180192_3_, final IProgressUpdate p_180192_4_, final Proxy p_180192_5_) {
      ListenableFuture<?> lvt_6_1_ = field_180193_a.submit(new Runnable() {
         public void run() {
            HttpURLConnection lvt_1_1_ = null;
            InputStream lvt_2_1_ = null;
            OutputStream lvt_3_1_ = null;
            if (p_180192_4_ != null) {
               p_180192_4_.func_73721_b("Downloading Resource Pack");
               p_180192_4_.func_73719_c("Making Request...");
            }

            try {
               try {
                  byte[] lvt_4_1_ = new byte[4096];
                  URL lvt_5_1_ = new URL(p_180192_1_);
                  lvt_1_1_ = (HttpURLConnection)lvt_5_1_.openConnection(p_180192_5_);
                  float lvt_6_1_ = 0.0F;
                  float lvt_7_1_ = (float)p_180192_2_.entrySet().size();
                  Iterator lvt_8_1_ = p_180192_2_.entrySet().iterator();

                  while(lvt_8_1_.hasNext()) {
                     Entry<String, String> lvt_9_1_ = (Entry)lvt_8_1_.next();
                     lvt_1_1_.setRequestProperty((String)lvt_9_1_.getKey(), (String)lvt_9_1_.getValue());
                     if (p_180192_4_ != null) {
                        p_180192_4_.func_73718_a((int)(++lvt_6_1_ / lvt_7_1_ * 100.0F));
                     }
                  }

                  lvt_2_1_ = lvt_1_1_.getInputStream();
                  lvt_7_1_ = (float)lvt_1_1_.getContentLength();
                  int lvt_8_2_ = lvt_1_1_.getContentLength();
                  if (p_180192_4_ != null) {
                     p_180192_4_.func_73719_c(String.format("Downloading file (%.2f MB)...", lvt_7_1_ / 1000.0F / 1000.0F));
                  }

                  if (p_180192_0_.exists()) {
                     long lvt_9_2_ = p_180192_0_.length();
                     if (lvt_9_2_ == (long)lvt_8_2_) {
                        if (p_180192_4_ != null) {
                           p_180192_4_.func_146586_a();
                        }

                        return;
                     }

                     HttpUtil.field_151227_b.warn("Deleting " + p_180192_0_ + " as it does not match what we currently have (" + lvt_8_2_ + " vs our " + lvt_9_2_ + ").");
                     FileUtils.deleteQuietly(p_180192_0_);
                  } else if (p_180192_0_.getParentFile() != null) {
                     p_180192_0_.getParentFile().mkdirs();
                  }

                  lvt_3_1_ = new DataOutputStream(new FileOutputStream(p_180192_0_));
                  if (p_180192_3_ > 0 && lvt_7_1_ > (float)p_180192_3_) {
                     if (p_180192_4_ != null) {
                        p_180192_4_.func_146586_a();
                     }

                     throw new IOException("Filesize is bigger than maximum allowed (file is " + lvt_6_1_ + ", limit is " + p_180192_3_ + ")");
                  }

                  boolean var21 = false;

                  int lvt_9_3_;
                  while((lvt_9_3_ = lvt_2_1_.read(lvt_4_1_)) >= 0) {
                     lvt_6_1_ += (float)lvt_9_3_;
                     if (p_180192_4_ != null) {
                        p_180192_4_.func_73718_a((int)(lvt_6_1_ / lvt_7_1_ * 100.0F));
                     }

                     if (p_180192_3_ > 0 && lvt_6_1_ > (float)p_180192_3_) {
                        if (p_180192_4_ != null) {
                           p_180192_4_.func_146586_a();
                        }

                        throw new IOException("Filesize was bigger than maximum allowed (got >= " + lvt_6_1_ + ", limit was " + p_180192_3_ + ")");
                     }

                     if (Thread.interrupted()) {
                        HttpUtil.field_151227_b.error("INTERRUPTED");
                        if (p_180192_4_ != null) {
                           p_180192_4_.func_146586_a();
                        }

                        return;
                     }

                     lvt_3_1_.write(lvt_4_1_, 0, lvt_9_3_);
                  }

                  if (p_180192_4_ != null) {
                     p_180192_4_.func_146586_a();
                     return;
                  }
               } catch (Throwable var16) {
                  var16.printStackTrace();
                  if (lvt_1_1_ != null) {
                     InputStream lvt_5_2_ = lvt_1_1_.getErrorStream();

                     try {
                        HttpUtil.field_151227_b.error(IOUtils.toString(lvt_5_2_));
                     } catch (IOException var15) {
                        var15.printStackTrace();
                     }
                  }

                  if (p_180192_4_ != null) {
                     p_180192_4_.func_146586_a();
                     return;
                  }
               }

            } finally {
               IOUtils.closeQuietly(lvt_2_1_);
               IOUtils.closeQuietly((OutputStream)lvt_3_1_);
            }
         }
      });
      return lvt_6_1_;
   }

   public static int func_76181_a() throws IOException {
      ServerSocket lvt_0_1_ = null;
      boolean var1 = true;

      int lvt_1_1_;
      try {
         lvt_0_1_ = new ServerSocket(0);
         lvt_1_1_ = lvt_0_1_.getLocalPort();
      } finally {
         try {
            if (lvt_0_1_ != null) {
               lvt_0_1_.close();
            }
         } catch (IOException var8) {
         }

      }

      return lvt_1_1_;
   }

   public static String func_152755_a(URL p_152755_0_) throws IOException {
      HttpURLConnection lvt_1_1_ = (HttpURLConnection)p_152755_0_.openConnection();
      lvt_1_1_.setRequestMethod("GET");
      BufferedReader lvt_2_1_ = new BufferedReader(new InputStreamReader(lvt_1_1_.getInputStream()));
      StringBuilder lvt_4_1_ = new StringBuilder();

      String lvt_3_1_;
      while((lvt_3_1_ = lvt_2_1_.readLine()) != null) {
         lvt_4_1_.append(lvt_3_1_);
         lvt_4_1_.append('\r');
      }

      lvt_2_1_.close();
      return lvt_4_1_.toString();
   }
}
