package net.minecraft.client.resources;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceIndex {
   private static final Logger field_152783_a = LogManager.getLogger();
   private final Map<String, File> field_152784_b = Maps.newHashMap();

   public ResourceIndex(File p_i1047_1_, String p_i1047_2_) {
      if (p_i1047_2_ != null) {
         File lvt_3_1_ = new File(p_i1047_1_, "objects");
         File lvt_4_1_ = new File(p_i1047_1_, "indexes/" + p_i1047_2_ + ".json");
         BufferedReader lvt_5_1_ = null;

         try {
            lvt_5_1_ = Files.newReader(lvt_4_1_, Charsets.UTF_8);
            JsonObject lvt_6_1_ = (new JsonParser()).parse((Reader)lvt_5_1_).getAsJsonObject();
            JsonObject lvt_7_1_ = JsonUtils.func_151218_a(lvt_6_1_, "objects", (JsonObject)null);
            if (lvt_7_1_ != null) {
               Iterator lvt_8_1_ = lvt_7_1_.entrySet().iterator();

               while(lvt_8_1_.hasNext()) {
                  Entry<String, JsonElement> lvt_9_1_ = (Entry)lvt_8_1_.next();
                  JsonObject lvt_10_1_ = (JsonObject)lvt_9_1_.getValue();
                  String lvt_11_1_ = (String)lvt_9_1_.getKey();
                  String[] lvt_12_1_ = lvt_11_1_.split("/", 2);
                  String lvt_13_1_ = lvt_12_1_.length == 1 ? lvt_12_1_[0] : lvt_12_1_[0] + ":" + lvt_12_1_[1];
                  String lvt_14_1_ = JsonUtils.func_151200_h(lvt_10_1_, "hash");
                  File lvt_15_1_ = new File(lvt_3_1_, lvt_14_1_.substring(0, 2) + "/" + lvt_14_1_);
                  this.field_152784_b.put(lvt_13_1_, lvt_15_1_);
               }
            }
         } catch (JsonParseException var20) {
            field_152783_a.error("Unable to parse resource index file: " + lvt_4_1_);
         } catch (FileNotFoundException var21) {
            field_152783_a.error("Can't find the resource index file: " + lvt_4_1_);
         } finally {
            IOUtils.closeQuietly((Reader)lvt_5_1_);
         }

      }
   }

   public Map<String, File> func_152782_a() {
      return this.field_152784_b;
   }
}
