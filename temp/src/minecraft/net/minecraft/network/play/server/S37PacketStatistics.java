package net.minecraft.network.play.server;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class S37PacketStatistics implements Packet<INetHandlerPlayClient> {
   private Map<StatBase, Integer> field_148976_a;

   public S37PacketStatistics() {
   }

   public S37PacketStatistics(Map<StatBase, Integer> p_i45173_1_) {
      this.field_148976_a = p_i45173_1_;
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147293_a(this);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      int lvt_2_1_ = p_148837_1_.func_150792_a();
      this.field_148976_a = Maps.newHashMap();

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         StatBase lvt_4_1_ = StatList.func_151177_a(p_148837_1_.func_150789_c(32767));
         int lvt_5_1_ = p_148837_1_.func_150792_a();
         if (lvt_4_1_ != null) {
            this.field_148976_a.put(lvt_4_1_, lvt_5_1_);
         }
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_148976_a.size());
      Iterator lvt_2_1_ = this.field_148976_a.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<StatBase, Integer> lvt_3_1_ = (Entry)lvt_2_1_.next();
         p_148840_1_.func_180714_a(((StatBase)lvt_3_1_.getKey()).field_75975_e);
         p_148840_1_.func_150787_b((Integer)lvt_3_1_.getValue());
      }

   }

   public Map<StatBase, Integer> func_148974_c() {
      return this.field_148976_a;
   }
}
