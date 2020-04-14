package net.minecraft.stats;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatisticsFile extends StatFileWriter {
   private static final Logger field_150889_b = LogManager.getLogger();
   private final MinecraftServer field_150890_c;
   private final File field_150887_d;
   private final Set<StatBase> field_150888_e = Sets.newHashSet();
   private int field_150885_f = -300;
   private boolean field_150886_g = false;

   public StatisticsFile(MinecraftServer p_i45306_1_, File p_i45306_2_) {
      this.field_150890_c = p_i45306_1_;
      this.field_150887_d = p_i45306_2_;
   }

   public void func_150882_a() {
      if (this.field_150887_d.isFile()) {
         try {
            this.field_150875_a.clear();
            this.field_150875_a.putAll(this.func_150881_a(FileUtils.readFileToString(this.field_150887_d)));
         } catch (IOException var2) {
            field_150889_b.error((String)("Couldn't read statistics file " + this.field_150887_d), (Throwable)var2);
         } catch (JsonParseException var3) {
            field_150889_b.error((String)("Couldn't parse statistics file " + this.field_150887_d), (Throwable)var3);
         }
      }

   }

   public void func_150883_b() {
      try {
         FileUtils.writeStringToFile(this.field_150887_d, func_150880_a(this.field_150875_a));
      } catch (IOException var2) {
         field_150889_b.error((String)"Couldn't save stats", (Throwable)var2);
      }

   }

   public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_) {
      int lvt_4_1_ = p_150873_2_.func_75967_d() ? this.func_77444_a(p_150873_2_) : 0;
      super.func_150873_a(p_150873_1_, p_150873_2_, p_150873_3_);
      this.field_150888_e.add(p_150873_2_);
      if (p_150873_2_.func_75967_d() && lvt_4_1_ == 0 && p_150873_3_ > 0) {
         this.field_150886_g = true;
         if (this.field_150890_c.func_147136_ar()) {
            this.field_150890_c.func_71203_ab().func_148539_a(new ChatComponentTranslation("chat.type.achievement", new Object[]{p_150873_1_.func_145748_c_(), p_150873_2_.func_150955_j()}));
         }
      }

      if (p_150873_2_.func_75967_d() && lvt_4_1_ > 0 && p_150873_3_ == 0) {
         this.field_150886_g = true;
         if (this.field_150890_c.func_147136_ar()) {
            this.field_150890_c.func_71203_ab().func_148539_a(new ChatComponentTranslation("chat.type.achievement.taken", new Object[]{p_150873_1_.func_145748_c_(), p_150873_2_.func_150955_j()}));
         }
      }

   }

   public Set<StatBase> func_150878_c() {
      Set<StatBase> lvt_1_1_ = Sets.newHashSet((Iterable)this.field_150888_e);
      this.field_150888_e.clear();
      this.field_150886_g = false;
      return lvt_1_1_;
   }

   public Map<StatBase, TupleIntJsonSerializable> func_150881_a(String p_150881_1_) {
      JsonElement lvt_2_1_ = (new JsonParser()).parse(p_150881_1_);
      if (!lvt_2_1_.isJsonObject()) {
         return Maps.newHashMap();
      } else {
         JsonObject lvt_3_1_ = lvt_2_1_.getAsJsonObject();
         Map<StatBase, TupleIntJsonSerializable> lvt_4_1_ = Maps.newHashMap();
         Iterator lvt_5_1_ = lvt_3_1_.entrySet().iterator();

         while(true) {
            while(lvt_5_1_.hasNext()) {
               Entry<String, JsonElement> lvt_6_1_ = (Entry)lvt_5_1_.next();
               StatBase lvt_7_1_ = StatList.func_151177_a((String)lvt_6_1_.getKey());
               if (lvt_7_1_ != null) {
                  TupleIntJsonSerializable lvt_8_1_ = new TupleIntJsonSerializable();
                  if (((JsonElement)lvt_6_1_.getValue()).isJsonPrimitive() && ((JsonElement)lvt_6_1_.getValue()).getAsJsonPrimitive().isNumber()) {
                     lvt_8_1_.func_151188_a(((JsonElement)lvt_6_1_.getValue()).getAsInt());
                  } else if (((JsonElement)lvt_6_1_.getValue()).isJsonObject()) {
                     JsonObject lvt_9_1_ = ((JsonElement)lvt_6_1_.getValue()).getAsJsonObject();
                     if (lvt_9_1_.has("value") && lvt_9_1_.get("value").isJsonPrimitive() && lvt_9_1_.get("value").getAsJsonPrimitive().isNumber()) {
                        lvt_8_1_.func_151188_a(lvt_9_1_.getAsJsonPrimitive("value").getAsInt());
                     }

                     if (lvt_9_1_.has("progress") && lvt_7_1_.func_150954_l() != null) {
                        try {
                           Constructor<? extends IJsonSerializable> lvt_10_1_ = lvt_7_1_.func_150954_l().getConstructor();
                           IJsonSerializable lvt_11_1_ = (IJsonSerializable)lvt_10_1_.newInstance();
                           lvt_11_1_.func_152753_a(lvt_9_1_.get("progress"));
                           lvt_8_1_.func_151190_a(lvt_11_1_);
                        } catch (Throwable var12) {
                           field_150889_b.warn("Invalid statistic progress in " + this.field_150887_d, var12);
                        }
                     }
                  }

                  lvt_4_1_.put(lvt_7_1_, lvt_8_1_);
               } else {
                  field_150889_b.warn("Invalid statistic in " + this.field_150887_d + ": Don't know what " + (String)lvt_6_1_.getKey() + " is");
               }
            }

            return lvt_4_1_;
         }
      }
   }

   public static String func_150880_a(Map<StatBase, TupleIntJsonSerializable> p_150880_0_) {
      JsonObject lvt_1_1_ = new JsonObject();
      Iterator lvt_2_1_ = p_150880_0_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<StatBase, TupleIntJsonSerializable> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (((TupleIntJsonSerializable)lvt_3_1_.getValue()).func_151187_b() != null) {
            JsonObject lvt_4_1_ = new JsonObject();
            lvt_4_1_.addProperty("value", (Number)((TupleIntJsonSerializable)lvt_3_1_.getValue()).func_151189_a());

            try {
               lvt_4_1_.add("progress", ((TupleIntJsonSerializable)lvt_3_1_.getValue()).func_151187_b().func_151003_a());
            } catch (Throwable var6) {
               field_150889_b.warn("Couldn't save statistic " + ((StatBase)lvt_3_1_.getKey()).func_150951_e() + ": error serializing progress", var6);
            }

            lvt_1_1_.add(((StatBase)lvt_3_1_.getKey()).field_75975_e, lvt_4_1_);
         } else {
            lvt_1_1_.addProperty(((StatBase)lvt_3_1_.getKey()).field_75975_e, (Number)((TupleIntJsonSerializable)lvt_3_1_.getValue()).func_151189_a());
         }
      }

      return lvt_1_1_.toString();
   }

   public void func_150877_d() {
      Iterator lvt_1_1_ = this.field_150875_a.keySet().iterator();

      while(lvt_1_1_.hasNext()) {
         StatBase lvt_2_1_ = (StatBase)lvt_1_1_.next();
         this.field_150888_e.add(lvt_2_1_);
      }

   }

   public void func_150876_a(EntityPlayerMP p_150876_1_) {
      int lvt_2_1_ = this.field_150890_c.func_71259_af();
      Map<StatBase, Integer> lvt_3_1_ = Maps.newHashMap();
      if (this.field_150886_g || lvt_2_1_ - this.field_150885_f > 300) {
         this.field_150885_f = lvt_2_1_;
         Iterator lvt_4_1_ = this.func_150878_c().iterator();

         while(lvt_4_1_.hasNext()) {
            StatBase lvt_5_1_ = (StatBase)lvt_4_1_.next();
            lvt_3_1_.put(lvt_5_1_, this.func_77444_a(lvt_5_1_));
         }
      }

      p_150876_1_.field_71135_a.func_147359_a(new S37PacketStatistics(lvt_3_1_));
   }

   public void func_150884_b(EntityPlayerMP p_150884_1_) {
      Map<StatBase, Integer> lvt_2_1_ = Maps.newHashMap();
      Iterator lvt_3_1_ = AchievementList.field_76007_e.iterator();

      while(lvt_3_1_.hasNext()) {
         Achievement lvt_4_1_ = (Achievement)lvt_3_1_.next();
         if (this.func_77443_a(lvt_4_1_)) {
            lvt_2_1_.put(lvt_4_1_, this.func_77444_a(lvt_4_1_));
            this.field_150888_e.remove(lvt_4_1_);
         }
      }

      p_150884_1_.field_71135_a.func_147359_a(new S37PacketStatistics(lvt_2_1_));
   }

   public boolean func_150879_e() {
      return this.field_150886_g;
   }
}
