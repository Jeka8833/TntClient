package net.minecraft.client.audio;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.Source;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager {
   private static final Marker field_148623_a = MarkerManager.getMarker("SOUNDS");
   private static final Logger field_148621_b = LogManager.getLogger();
   private final SoundHandler field_148622_c;
   private final GameSettings field_148619_d;
   private SoundManager.SoundSystemStarterThread field_148620_e;
   private boolean field_148617_f;
   private int field_148618_g = 0;
   private final Map<String, ISound> field_148629_h = HashBiMap.create();
   private final Map<ISound, String> field_148630_i;
   private Map<ISound, SoundPoolEntry> field_148627_j;
   private final Multimap<SoundCategory, String> field_148628_k;
   private final List<ITickableSound> field_148625_l;
   private final Map<ISound, Integer> field_148626_m;
   private final Map<String, Integer> field_148624_n;

   public SoundManager(SoundHandler p_i45119_1_, GameSettings p_i45119_2_) {
      this.field_148630_i = ((BiMap)this.field_148629_h).inverse();
      this.field_148627_j = Maps.newHashMap();
      this.field_148628_k = HashMultimap.create();
      this.field_148625_l = Lists.newArrayList();
      this.field_148626_m = Maps.newHashMap();
      this.field_148624_n = Maps.newHashMap();
      this.field_148622_c = p_i45119_1_;
      this.field_148619_d = p_i45119_2_;

      try {
         SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
         SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
      } catch (SoundSystemException var4) {
         field_148621_b.error(field_148623_a, (String)"Error linking with the LibraryJavaSound plug-in", (Throwable)var4);
      }

   }

   public void func_148596_a() {
      this.func_148613_b();
      this.func_148608_i();
   }

   private synchronized void func_148608_i() {
      if (!this.field_148617_f) {
         try {
            (new Thread(new Runnable() {
               public void run() {
                  SoundSystemConfig.setLogger(new SoundSystemLogger() {
                     public void message(String p_message_1_, int p_message_2_) {
                        if (!p_message_1_.isEmpty()) {
                           SoundManager.field_148621_b.info(p_message_1_);
                        }

                     }

                     public void importantMessage(String p_importantMessage_1_, int p_importantMessage_2_) {
                        if (!p_importantMessage_1_.isEmpty()) {
                           SoundManager.field_148621_b.warn(p_importantMessage_1_);
                        }

                     }

                     public void errorMessage(String p_errorMessage_1_, String p_errorMessage_2_, int p_errorMessage_3_) {
                        if (!p_errorMessage_2_.isEmpty()) {
                           SoundManager.field_148621_b.error("Error in class '" + p_errorMessage_1_ + "'");
                           SoundManager.field_148621_b.error(p_errorMessage_2_);
                        }

                     }
                  });
                  SoundManager.this.field_148620_e = SoundManager.this.new SoundSystemStarterThread();
                  SoundManager.this.field_148617_f = true;
                  SoundManager.this.field_148620_e.setMasterVolume(SoundManager.this.field_148619_d.func_151438_a(SoundCategory.MASTER));
                  SoundManager.field_148621_b.info(SoundManager.field_148623_a, "Sound engine started");
               }
            }, "Sound Library Loader")).start();
         } catch (RuntimeException var2) {
            field_148621_b.error(field_148623_a, (String)"Error starting SoundSystem. Turning off sounds & music", (Throwable)var2);
            this.field_148619_d.func_151439_a(SoundCategory.MASTER, 0.0F);
            this.field_148619_d.func_74303_b();
         }

      }
   }

   private float func_148595_a(SoundCategory p_148595_1_) {
      return p_148595_1_ != null && p_148595_1_ != SoundCategory.MASTER ? this.field_148619_d.func_151438_a(p_148595_1_) : 1.0F;
   }

   public void func_148601_a(SoundCategory p_148601_1_, float p_148601_2_) {
      if (this.field_148617_f) {
         if (p_148601_1_ == SoundCategory.MASTER) {
            this.field_148620_e.setMasterVolume(p_148601_2_);
         } else {
            Iterator lvt_3_1_ = this.field_148628_k.get(p_148601_1_).iterator();

            while(lvt_3_1_.hasNext()) {
               String lvt_4_1_ = (String)lvt_3_1_.next();
               ISound lvt_5_1_ = (ISound)this.field_148629_h.get(lvt_4_1_);
               float lvt_6_1_ = this.func_148594_a(lvt_5_1_, (SoundPoolEntry)this.field_148627_j.get(lvt_5_1_), p_148601_1_);
               if (lvt_6_1_ <= 0.0F) {
                  this.func_148602_b(lvt_5_1_);
               } else {
                  this.field_148620_e.setVolume(lvt_4_1_, lvt_6_1_);
               }
            }

         }
      }
   }

   public void func_148613_b() {
      if (this.field_148617_f) {
         this.func_148614_c();
         this.field_148620_e.cleanup();
         this.field_148617_f = false;
      }

   }

   public void func_148614_c() {
      if (this.field_148617_f) {
         Iterator lvt_1_1_ = this.field_148629_h.keySet().iterator();

         while(lvt_1_1_.hasNext()) {
            String lvt_2_1_ = (String)lvt_1_1_.next();
            this.field_148620_e.stop(lvt_2_1_);
         }

         this.field_148629_h.clear();
         this.field_148626_m.clear();
         this.field_148625_l.clear();
         this.field_148628_k.clear();
         this.field_148627_j.clear();
         this.field_148624_n.clear();
      }

   }

   public void func_148605_d() {
      ++this.field_148618_g;
      Iterator lvt_1_2_ = this.field_148625_l.iterator();

      String lvt_3_2_;
      while(lvt_1_2_.hasNext()) {
         ITickableSound lvt_2_1_ = (ITickableSound)lvt_1_2_.next();
         lvt_2_1_.func_73660_a();
         if (lvt_2_1_.func_147667_k()) {
            this.func_148602_b(lvt_2_1_);
         } else {
            lvt_3_2_ = (String)this.field_148630_i.get(lvt_2_1_);
            this.field_148620_e.setVolume(lvt_3_2_, this.func_148594_a(lvt_2_1_, (SoundPoolEntry)this.field_148627_j.get(lvt_2_1_), this.field_148622_c.func_147680_a(lvt_2_1_.func_147650_b()).func_148728_d()));
            this.field_148620_e.setPitch(lvt_3_2_, this.func_148606_a(lvt_2_1_, (SoundPoolEntry)this.field_148627_j.get(lvt_2_1_)));
            this.field_148620_e.setPosition(lvt_3_2_, lvt_2_1_.func_147649_g(), lvt_2_1_.func_147654_h(), lvt_2_1_.func_147651_i());
         }
      }

      lvt_1_2_ = this.field_148629_h.entrySet().iterator();

      ISound lvt_4_1_;
      while(lvt_1_2_.hasNext()) {
         Entry<String, ISound> lvt_2_2_ = (Entry)lvt_1_2_.next();
         lvt_3_2_ = (String)lvt_2_2_.getKey();
         lvt_4_1_ = (ISound)lvt_2_2_.getValue();
         if (!this.field_148620_e.playing(lvt_3_2_)) {
            int lvt_5_1_ = (Integer)this.field_148624_n.get(lvt_3_2_);
            if (lvt_5_1_ <= this.field_148618_g) {
               int lvt_6_1_ = lvt_4_1_.func_147652_d();
               if (lvt_4_1_.func_147657_c() && lvt_6_1_ > 0) {
                  this.field_148626_m.put(lvt_4_1_, this.field_148618_g + lvt_6_1_);
               }

               lvt_1_2_.remove();
               field_148621_b.debug(field_148623_a, "Removed channel {} because it's not playing anymore", lvt_3_2_);
               this.field_148620_e.removeSource(lvt_3_2_);
               this.field_148624_n.remove(lvt_3_2_);
               this.field_148627_j.remove(lvt_4_1_);

               try {
                  this.field_148628_k.remove(this.field_148622_c.func_147680_a(lvt_4_1_.func_147650_b()).func_148728_d(), lvt_3_2_);
               } catch (RuntimeException var8) {
               }

               if (lvt_4_1_ instanceof ITickableSound) {
                  this.field_148625_l.remove(lvt_4_1_);
               }
            }
         }
      }

      Iterator lvt_2_3_ = this.field_148626_m.entrySet().iterator();

      while(lvt_2_3_.hasNext()) {
         Entry<ISound, Integer> lvt_3_3_ = (Entry)lvt_2_3_.next();
         if (this.field_148618_g >= (Integer)lvt_3_3_.getValue()) {
            lvt_4_1_ = (ISound)lvt_3_3_.getKey();
            if (lvt_4_1_ instanceof ITickableSound) {
               ((ITickableSound)lvt_4_1_).func_73660_a();
            }

            this.func_148611_c(lvt_4_1_);
            lvt_2_3_.remove();
         }
      }

   }

   public boolean func_148597_a(ISound p_148597_1_) {
      if (!this.field_148617_f) {
         return false;
      } else {
         String lvt_2_1_ = (String)this.field_148630_i.get(p_148597_1_);
         if (lvt_2_1_ == null) {
            return false;
         } else {
            return this.field_148620_e.playing(lvt_2_1_) || this.field_148624_n.containsKey(lvt_2_1_) && (Integer)this.field_148624_n.get(lvt_2_1_) <= this.field_148618_g;
         }
      }
   }

   public void func_148602_b(ISound p_148602_1_) {
      if (this.field_148617_f) {
         String lvt_2_1_ = (String)this.field_148630_i.get(p_148602_1_);
         if (lvt_2_1_ != null) {
            this.field_148620_e.stop(lvt_2_1_);
         }

      }
   }

   public void func_148611_c(ISound p_148611_1_) {
      if (this.field_148617_f) {
         if (this.field_148620_e.getMasterVolume() <= 0.0F) {
            field_148621_b.debug(field_148623_a, "Skipped playing soundEvent: {}, master volume was zero", p_148611_1_.func_147650_b());
         } else {
            SoundEventAccessorComposite lvt_2_1_ = this.field_148622_c.func_147680_a(p_148611_1_.func_147650_b());
            if (lvt_2_1_ == null) {
               field_148621_b.warn(field_148623_a, "Unable to play unknown soundEvent: {}", p_148611_1_.func_147650_b());
            } else {
               SoundPoolEntry lvt_3_1_ = lvt_2_1_.func_148720_g();
               if (lvt_3_1_ == SoundHandler.field_147700_a) {
                  field_148621_b.warn(field_148623_a, "Unable to play empty soundEvent: {}", lvt_2_1_.func_148729_c());
               } else {
                  float lvt_4_1_ = p_148611_1_.func_147653_e();
                  float lvt_5_1_ = 16.0F;
                  if (lvt_4_1_ > 1.0F) {
                     lvt_5_1_ *= lvt_4_1_;
                  }

                  SoundCategory lvt_6_1_ = lvt_2_1_.func_148728_d();
                  float lvt_7_1_ = this.func_148594_a(p_148611_1_, lvt_3_1_, lvt_6_1_);
                  double lvt_8_1_ = (double)this.func_148606_a(p_148611_1_, lvt_3_1_);
                  ResourceLocation lvt_10_1_ = lvt_3_1_.func_148652_a();
                  if (lvt_7_1_ == 0.0F) {
                     field_148621_b.debug(field_148623_a, "Skipped playing sound {}, volume was zero.", lvt_10_1_);
                  } else {
                     boolean lvt_11_1_ = p_148611_1_.func_147657_c() && p_148611_1_.func_147652_d() == 0;
                     String lvt_12_1_ = MathHelper.func_180182_a(ThreadLocalRandom.current()).toString();
                     if (lvt_3_1_.func_148648_d()) {
                        this.field_148620_e.newStreamingSource(false, lvt_12_1_, func_148612_a(lvt_10_1_), lvt_10_1_.toString(), lvt_11_1_, p_148611_1_.func_147649_g(), p_148611_1_.func_147654_h(), p_148611_1_.func_147651_i(), p_148611_1_.func_147656_j().func_148586_a(), lvt_5_1_);
                     } else {
                        this.field_148620_e.newSource(false, lvt_12_1_, func_148612_a(lvt_10_1_), lvt_10_1_.toString(), lvt_11_1_, p_148611_1_.func_147649_g(), p_148611_1_.func_147654_h(), p_148611_1_.func_147651_i(), p_148611_1_.func_147656_j().func_148586_a(), lvt_5_1_);
                     }

                     field_148621_b.debug(field_148623_a, "Playing sound {} for event {} as channel {}", lvt_3_1_.func_148652_a(), lvt_2_1_.func_148729_c(), lvt_12_1_);
                     this.field_148620_e.setPitch(lvt_12_1_, (float)lvt_8_1_);
                     this.field_148620_e.setVolume(lvt_12_1_, lvt_7_1_);
                     this.field_148620_e.play(lvt_12_1_);
                     this.field_148624_n.put(lvt_12_1_, this.field_148618_g + 20);
                     this.field_148629_h.put(lvt_12_1_, p_148611_1_);
                     this.field_148627_j.put(p_148611_1_, lvt_3_1_);
                     if (lvt_6_1_ != SoundCategory.MASTER) {
                        this.field_148628_k.put(lvt_6_1_, lvt_12_1_);
                     }

                     if (p_148611_1_ instanceof ITickableSound) {
                        this.field_148625_l.add((ITickableSound)p_148611_1_);
                     }

                  }
               }
            }
         }
      }
   }

   private float func_148606_a(ISound p_148606_1_, SoundPoolEntry p_148606_2_) {
      return (float)MathHelper.func_151237_a((double)p_148606_1_.func_147655_f() * p_148606_2_.func_148650_b(), 0.5D, 2.0D);
   }

   private float func_148594_a(ISound p_148594_1_, SoundPoolEntry p_148594_2_, SoundCategory p_148594_3_) {
      return (float)MathHelper.func_151237_a((double)p_148594_1_.func_147653_e() * p_148594_2_.func_148649_c(), 0.0D, 1.0D) * this.func_148595_a(p_148594_3_);
   }

   public void func_148610_e() {
      Iterator lvt_1_1_ = this.field_148629_h.keySet().iterator();

      while(lvt_1_1_.hasNext()) {
         String lvt_2_1_ = (String)lvt_1_1_.next();
         field_148621_b.debug(field_148623_a, "Pausing channel {}", lvt_2_1_);
         this.field_148620_e.pause(lvt_2_1_);
      }

   }

   public void func_148604_f() {
      Iterator lvt_1_1_ = this.field_148629_h.keySet().iterator();

      while(lvt_1_1_.hasNext()) {
         String lvt_2_1_ = (String)lvt_1_1_.next();
         field_148621_b.debug(field_148623_a, "Resuming channel {}", lvt_2_1_);
         this.field_148620_e.play(lvt_2_1_);
      }

   }

   public void func_148599_a(ISound p_148599_1_, int p_148599_2_) {
      this.field_148626_m.put(p_148599_1_, this.field_148618_g + p_148599_2_);
   }

   private static URL func_148612_a(final ResourceLocation p_148612_0_) {
      String lvt_1_1_ = String.format("%s:%s:%s", "mcsounddomain", p_148612_0_.func_110624_b(), p_148612_0_.func_110623_a());
      URLStreamHandler lvt_2_1_ = new URLStreamHandler() {
         protected URLConnection openConnection(URL p_openConnection_1_) {
            return new URLConnection(p_openConnection_1_) {
               public void connect() throws IOException {
               }

               public InputStream getInputStream() throws IOException {
                  return Minecraft.func_71410_x().func_110442_L().func_110536_a(p_148612_0_).func_110527_b();
               }
            };
         }
      };

      try {
         return new URL((URL)null, lvt_1_1_, lvt_2_1_);
      } catch (MalformedURLException var4) {
         throw new Error("TODO: Sanely handle url exception! :D");
      }
   }

   public void func_148615_a(EntityPlayer p_148615_1_, float p_148615_2_) {
      if (this.field_148617_f && p_148615_1_ != null) {
         float lvt_3_1_ = p_148615_1_.field_70127_C + (p_148615_1_.field_70125_A - p_148615_1_.field_70127_C) * p_148615_2_;
         float lvt_4_1_ = p_148615_1_.field_70126_B + (p_148615_1_.field_70177_z - p_148615_1_.field_70126_B) * p_148615_2_;
         double lvt_5_1_ = p_148615_1_.field_70169_q + (p_148615_1_.field_70165_t - p_148615_1_.field_70169_q) * (double)p_148615_2_;
         double lvt_7_1_ = p_148615_1_.field_70167_r + (p_148615_1_.field_70163_u - p_148615_1_.field_70167_r) * (double)p_148615_2_ + (double)p_148615_1_.func_70047_e();
         double lvt_9_1_ = p_148615_1_.field_70166_s + (p_148615_1_.field_70161_v - p_148615_1_.field_70166_s) * (double)p_148615_2_;
         float lvt_11_1_ = MathHelper.func_76134_b((lvt_4_1_ + 90.0F) * 0.017453292F);
         float lvt_12_1_ = MathHelper.func_76126_a((lvt_4_1_ + 90.0F) * 0.017453292F);
         float lvt_13_1_ = MathHelper.func_76134_b(-lvt_3_1_ * 0.017453292F);
         float lvt_14_1_ = MathHelper.func_76126_a(-lvt_3_1_ * 0.017453292F);
         float lvt_15_1_ = MathHelper.func_76134_b((-lvt_3_1_ + 90.0F) * 0.017453292F);
         float lvt_16_1_ = MathHelper.func_76126_a((-lvt_3_1_ + 90.0F) * 0.017453292F);
         float lvt_17_1_ = lvt_11_1_ * lvt_13_1_;
         float lvt_19_1_ = lvt_12_1_ * lvt_13_1_;
         float lvt_20_1_ = lvt_11_1_ * lvt_15_1_;
         float lvt_22_1_ = lvt_12_1_ * lvt_15_1_;
         this.field_148620_e.setListenerPosition((float)lvt_5_1_, (float)lvt_7_1_, (float)lvt_9_1_);
         this.field_148620_e.setListenerOrientation(lvt_17_1_, lvt_14_1_, lvt_19_1_, lvt_20_1_, lvt_16_1_, lvt_22_1_);
      }
   }

   class SoundSystemStarterThread extends SoundSystem {
      private SoundSystemStarterThread() {
      }

      public boolean playing(String p_playing_1_) {
         synchronized(SoundSystemConfig.THREAD_SYNC) {
            if (this.soundLibrary == null) {
               return false;
            } else {
               Source lvt_3_1_ = (Source)this.soundLibrary.getSources().get(p_playing_1_);
               if (lvt_3_1_ == null) {
                  return false;
               } else {
                  return lvt_3_1_.playing() || lvt_3_1_.paused() || lvt_3_1_.preLoad;
               }
            }
         }
      }
   }
}
