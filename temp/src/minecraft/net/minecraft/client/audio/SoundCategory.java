package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SoundCategory {
   MASTER("master", 0),
   MUSIC("music", 1),
   RECORDS("record", 2),
   WEATHER("weather", 3),
   BLOCKS("block", 4),
   MOBS("hostile", 5),
   ANIMALS("neutral", 6),
   PLAYERS("player", 7),
   AMBIENT("ambient", 8);

   private static final Map<String, SoundCategory> field_147168_j = Maps.newHashMap();
   private static final Map<Integer, SoundCategory> field_147169_k = Maps.newHashMap();
   private final String field_147166_l;
   private final int field_147167_m;

   private SoundCategory(String p_i45126_3_, int p_i45126_4_) {
      this.field_147166_l = p_i45126_3_;
      this.field_147167_m = p_i45126_4_;
   }

   public String func_147155_a() {
      return this.field_147166_l;
   }

   public int func_147156_b() {
      return this.field_147167_m;
   }

   public static SoundCategory func_147154_a(String p_147154_0_) {
      return (SoundCategory)field_147168_j.get(p_147154_0_);
   }

   static {
      SoundCategory[] lvt_0_1_ = values();
      int lvt_1_1_ = lvt_0_1_.length;

      for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
         SoundCategory lvt_3_1_ = lvt_0_1_[lvt_2_1_];
         if (field_147168_j.containsKey(lvt_3_1_.func_147155_a()) || field_147169_k.containsKey(lvt_3_1_.func_147156_b())) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + lvt_3_1_);
         }

         field_147168_j.put(lvt_3_1_.func_147155_a(), lvt_3_1_);
         field_147169_k.put(lvt_3_1_.func_147156_b(), lvt_3_1_);
      }

   }
}
