package net.minecraft.server.management;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class BanList extends UserList<String, IPBanEntry> {
   public BanList(File p_i1490_1_) {
      super(p_i1490_1_);
   }

   protected UserListEntry<String> func_152682_a(JsonObject p_152682_1_) {
      return new IPBanEntry(p_152682_1_);
   }

   public boolean func_152708_a(SocketAddress p_152708_1_) {
      String lvt_2_1_ = this.func_152707_c(p_152708_1_);
      return this.func_152692_d(lvt_2_1_);
   }

   public IPBanEntry func_152709_b(SocketAddress p_152709_1_) {
      String lvt_2_1_ = this.func_152707_c(p_152709_1_);
      return (IPBanEntry)this.func_152683_b(lvt_2_1_);
   }

   private String func_152707_c(SocketAddress p_152707_1_) {
      String lvt_2_1_ = p_152707_1_.toString();
      if (lvt_2_1_.contains("/")) {
         lvt_2_1_ = lvt_2_1_.substring(lvt_2_1_.indexOf(47) + 1);
      }

      if (lvt_2_1_.contains(":")) {
         lvt_2_1_ = lvt_2_1_.substring(0, lvt_2_1_.indexOf(58));
      }

      return lvt_2_1_;
   }
}
