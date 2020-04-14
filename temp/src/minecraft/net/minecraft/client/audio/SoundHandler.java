package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements IResourceManagerReloadListener, ITickable {
   private static final Logger field_147698_b = LogManager.getLogger();
   private static final Gson field_147699_c = (new GsonBuilder()).registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
   private static final ParameterizedType field_147696_d = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class, SoundList.class};
      }

      public Type getRawType() {
         return Map.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };
   public static final SoundPoolEntry field_147700_a = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0D, 0.0D, false);
   private final SoundRegistry field_147697_e = new SoundRegistry();
   private final SoundManager field_147694_f;
   private final IResourceManager field_147695_g;

   public SoundHandler(IResourceManager p_i45122_1_, GameSettings p_i45122_2_) {
      this.field_147695_g = p_i45122_1_;
      this.field_147694_f = new SoundManager(this, p_i45122_2_);
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.field_147694_f.func_148596_a();
      this.field_147697_e.func_148763_c();
      Iterator lvt_2_1_ = p_110549_1_.func_135055_a().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();

         try {
            List<IResource> lvt_4_1_ = p_110549_1_.func_135056_b(new ResourceLocation(lvt_3_1_, "sounds.json"));
            Iterator lvt_5_1_ = lvt_4_1_.iterator();

            while(lvt_5_1_.hasNext()) {
               IResource lvt_6_1_ = (IResource)lvt_5_1_.next();

               try {
                  Map<String, SoundList> lvt_7_1_ = this.func_175085_a(lvt_6_1_.func_110527_b());
                  Iterator lvt_8_1_ = lvt_7_1_.entrySet().iterator();

                  while(lvt_8_1_.hasNext()) {
                     Entry<String, SoundList> lvt_9_1_ = (Entry)lvt_8_1_.next();
                     this.func_147693_a(new ResourceLocation(lvt_3_1_, (String)lvt_9_1_.getKey()), (SoundList)lvt_9_1_.getValue());
                  }
               } catch (RuntimeException var10) {
                  field_147698_b.warn((String)"Invalid sounds.json", (Throwable)var10);
               }
            }
         } catch (IOException var11) {
         }
      }

   }

   protected Map<String, SoundList> func_175085_a(InputStream p_175085_1_) {
      Map var2;
      try {
         var2 = (Map)field_147699_c.fromJson((Reader)(new InputStreamReader(p_175085_1_)), (Type)field_147696_d);
      } finally {
         IOUtils.closeQuietly(p_175085_1_);
      }

      return var2;
   }

   private void func_147693_a(ResourceLocation p_147693_1_, SoundList p_147693_2_) {
      boolean lvt_4_1_ = !this.field_147697_e.func_148741_d(p_147693_1_);
      SoundEventAccessorComposite lvt_3_2_;
      if (!lvt_4_1_ && !p_147693_2_.func_148574_b()) {
         lvt_3_2_ = (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(p_147693_1_);
      } else {
         if (!lvt_4_1_) {
            field_147698_b.debug("Replaced sound event location {}", p_147693_1_);
         }

         lvt_3_2_ = new SoundEventAccessorComposite(p_147693_1_, 1.0D, 1.0D, p_147693_2_.func_148573_c());
         this.field_147697_e.func_148762_a(lvt_3_2_);
      }

      Iterator lvt_5_1_ = p_147693_2_.func_148570_a().iterator();

      while(lvt_5_1_.hasNext()) {
         final SoundList.SoundEntry lvt_6_1_ = (SoundList.SoundEntry)lvt_5_1_.next();
         String lvt_7_1_ = lvt_6_1_.func_148556_a();
         ResourceLocation lvt_8_1_ = new ResourceLocation(lvt_7_1_);
         final String lvt_9_1_ = lvt_7_1_.contains(":") ? lvt_8_1_.func_110624_b() : p_147693_1_.func_110624_b();
         Object lvt_10_3_;
         switch(lvt_6_1_.func_148563_e()) {
         case FILE:
            ResourceLocation lvt_11_1_ = new ResourceLocation(lvt_9_1_, "sounds/" + lvt_8_1_.func_110623_a() + ".ogg");
            InputStream lvt_12_1_ = null;

            try {
               lvt_12_1_ = this.field_147695_g.func_110536_a(lvt_11_1_).func_110527_b();
            } catch (FileNotFoundException var18) {
               field_147698_b.warn("File {} does not exist, cannot add it to event {}", lvt_11_1_, p_147693_1_);
               continue;
            } catch (IOException var19) {
               field_147698_b.warn((String)("Could not load sound file " + lvt_11_1_ + ", cannot add it to event " + p_147693_1_), (Throwable)var19);
               continue;
            } finally {
               IOUtils.closeQuietly(lvt_12_1_);
            }

            lvt_10_3_ = new SoundEventAccessor(new SoundPoolEntry(lvt_11_1_, (double)lvt_6_1_.func_148560_c(), (double)lvt_6_1_.func_148558_b(), lvt_6_1_.func_148552_f()), lvt_6_1_.func_148555_d());
            break;
         case SOUND_EVENT:
            lvt_10_3_ = new ISoundEventAccessor<SoundPoolEntry>() {
               final ResourceLocation field_148726_a = new ResourceLocation(lvt_9_1_, lvt_6_1_.func_148556_a());

               public int func_148721_a() {
                  SoundEventAccessorComposite lvt_1_1_ = (SoundEventAccessorComposite)SoundHandler.this.field_147697_e.func_82594_a(this.field_148726_a);
                  return lvt_1_1_ == null ? 0 : lvt_1_1_.func_148721_a();
               }

               public SoundPoolEntry func_148720_g() {
                  SoundEventAccessorComposite lvt_1_1_ = (SoundEventAccessorComposite)SoundHandler.this.field_147697_e.func_82594_a(this.field_148726_a);
                  return lvt_1_1_ == null ? SoundHandler.field_147700_a : lvt_1_1_.func_148720_g();
               }
            };
            break;
         default:
            throw new IllegalStateException("IN YOU FACE");
         }

         lvt_3_2_.func_148727_a((ISoundEventAccessor)lvt_10_3_);
      }

   }

   public SoundEventAccessorComposite func_147680_a(ResourceLocation p_147680_1_) {
      return (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(p_147680_1_);
   }

   public void func_147682_a(ISound p_147682_1_) {
      this.field_147694_f.func_148611_c(p_147682_1_);
   }

   public void func_147681_a(ISound p_147681_1_, int p_147681_2_) {
      this.field_147694_f.func_148599_a(p_147681_1_, p_147681_2_);
   }

   public void func_147691_a(EntityPlayer p_147691_1_, float p_147691_2_) {
      this.field_147694_f.func_148615_a(p_147691_1_, p_147691_2_);
   }

   public void func_147689_b() {
      this.field_147694_f.func_148610_e();
   }

   public void func_147690_c() {
      this.field_147694_f.func_148614_c();
   }

   public void func_147685_d() {
      this.field_147694_f.func_148613_b();
   }

   public void func_73660_a() {
      this.field_147694_f.func_148605_d();
   }

   public void func_147687_e() {
      this.field_147694_f.func_148604_f();
   }

   public void func_147684_a(SoundCategory p_147684_1_, float p_147684_2_) {
      if (p_147684_1_ == SoundCategory.MASTER && p_147684_2_ <= 0.0F) {
         this.func_147690_c();
      }

      this.field_147694_f.func_148601_a(p_147684_1_, p_147684_2_);
   }

   public void func_147683_b(ISound p_147683_1_) {
      this.field_147694_f.func_148602_b(p_147683_1_);
   }

   public SoundEventAccessorComposite func_147686_a(SoundCategory... p_147686_1_) {
      List<SoundEventAccessorComposite> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = this.field_147697_e.func_148742_b().iterator();

      while(lvt_3_1_.hasNext()) {
         ResourceLocation lvt_4_1_ = (ResourceLocation)lvt_3_1_.next();
         SoundEventAccessorComposite lvt_5_1_ = (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(lvt_4_1_);
         if (ArrayUtils.contains(p_147686_1_, lvt_5_1_.func_148728_d())) {
            lvt_2_1_.add(lvt_5_1_);
         }
      }

      if (lvt_2_1_.isEmpty()) {
         return null;
      } else {
         return (SoundEventAccessorComposite)lvt_2_1_.get((new Random()).nextInt(lvt_2_1_.size()));
      }
   }

   public boolean func_147692_c(ISound p_147692_1_) {
      return this.field_147694_f.func_148597_a(p_147692_1_);
   }
}
