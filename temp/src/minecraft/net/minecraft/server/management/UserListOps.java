package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class UserListOps extends UserList<GameProfile, UserListOpsEntry> {
   public UserListOps(File p_i1152_1_) {
      super(p_i1152_1_);
   }

   protected UserListEntry<GameProfile> func_152682_a(JsonObject p_152682_1_) {
      return new UserListOpsEntry(p_152682_1_);
   }

   public String[] func_152685_a() {
      String[] lvt_1_1_ = new String[this.func_152688_e().size()];
      int lvt_2_1_ = 0;

      UserListOpsEntry lvt_4_1_;
      for(Iterator lvt_3_1_ = this.func_152688_e().values().iterator(); lvt_3_1_.hasNext(); lvt_1_1_[lvt_2_1_++] = ((GameProfile)lvt_4_1_.func_152640_f()).getName()) {
         lvt_4_1_ = (UserListOpsEntry)lvt_3_1_.next();
      }

      return lvt_1_1_;
   }

   public boolean func_183026_b(GameProfile p_183026_1_) {
      UserListOpsEntry lvt_2_1_ = (UserListOpsEntry)this.func_152683_b(p_183026_1_);
      return lvt_2_1_ != null ? lvt_2_1_.func_183024_b() : false;
   }

   protected String func_152681_a(GameProfile p_152681_1_) {
      return p_152681_1_.getId().toString();
   }

   public GameProfile func_152700_a(String p_152700_1_) {
      Iterator lvt_2_1_ = this.func_152688_e().values().iterator();

      UserListOpsEntry lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            return null;
         }

         lvt_3_1_ = (UserListOpsEntry)lvt_2_1_.next();
      } while(!p_152700_1_.equalsIgnoreCase(((GameProfile)lvt_3_1_.func_152640_f()).getName()));

      return (GameProfile)lvt_3_1_.func_152640_f();
   }
}
