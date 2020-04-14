package net.minecraft.network.play.server;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class S38PacketPlayerListItem implements Packet<INetHandlerPlayClient> {
   private S38PacketPlayerListItem.Action field_179770_a;
   private final List<S38PacketPlayerListItem.AddPlayerData> field_179769_b = Lists.newArrayList();

   public S38PacketPlayerListItem() {
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action p_i45967_1_, EntityPlayerMP... p_i45967_2_) {
      this.field_179770_a = p_i45967_1_;
      EntityPlayerMP[] lvt_3_1_ = p_i45967_2_;
      int lvt_4_1_ = p_i45967_2_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         EntityPlayerMP lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(lvt_6_1_.func_146103_bH(), lvt_6_1_.field_71138_i, lvt_6_1_.field_71134_c.func_73081_b(), lvt_6_1_.func_175396_E()));
      }

   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action p_i45968_1_, Iterable<EntityPlayerMP> p_i45968_2_) {
      this.field_179770_a = p_i45968_1_;
      Iterator lvt_3_1_ = p_i45968_2_.iterator();

      while(lvt_3_1_.hasNext()) {
         EntityPlayerMP lvt_4_1_ = (EntityPlayerMP)lvt_3_1_.next();
         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(lvt_4_1_.func_146103_bH(), lvt_4_1_.field_71138_i, lvt_4_1_.field_71134_c.func_73081_b(), lvt_4_1_.func_175396_E()));
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179770_a = (S38PacketPlayerListItem.Action)p_148837_1_.func_179257_a(S38PacketPlayerListItem.Action.class);
      int lvt_2_1_ = p_148837_1_.func_150792_a();

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         GameProfile lvt_4_1_ = null;
         int lvt_5_1_ = 0;
         WorldSettings.GameType lvt_6_1_ = null;
         IChatComponent lvt_7_1_ = null;
         switch(this.field_179770_a) {
         case ADD_PLAYER:
            lvt_4_1_ = new GameProfile(p_148837_1_.func_179253_g(), p_148837_1_.func_150789_c(16));
            int lvt_8_1_ = p_148837_1_.func_150792_a();
            int lvt_9_1_ = 0;

            for(; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
               String lvt_10_1_ = p_148837_1_.func_150789_c(32767);
               String lvt_11_1_ = p_148837_1_.func_150789_c(32767);
               if (p_148837_1_.readBoolean()) {
                  lvt_4_1_.getProperties().put(lvt_10_1_, new Property(lvt_10_1_, lvt_11_1_, p_148837_1_.func_150789_c(32767)));
               } else {
                  lvt_4_1_.getProperties().put(lvt_10_1_, new Property(lvt_10_1_, lvt_11_1_));
               }
            }

            lvt_6_1_ = WorldSettings.GameType.func_77146_a(p_148837_1_.func_150792_a());
            lvt_5_1_ = p_148837_1_.func_150792_a();
            if (p_148837_1_.readBoolean()) {
               lvt_7_1_ = p_148837_1_.func_179258_d();
            }
            break;
         case UPDATE_GAME_MODE:
            lvt_4_1_ = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            lvt_6_1_ = WorldSettings.GameType.func_77146_a(p_148837_1_.func_150792_a());
            break;
         case UPDATE_LATENCY:
            lvt_4_1_ = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            lvt_5_1_ = p_148837_1_.func_150792_a();
            break;
         case UPDATE_DISPLAY_NAME:
            lvt_4_1_ = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            if (p_148837_1_.readBoolean()) {
               lvt_7_1_ = p_148837_1_.func_179258_d();
            }
            break;
         case REMOVE_PLAYER:
            lvt_4_1_ = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
         }

         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(lvt_4_1_, lvt_5_1_, lvt_6_1_, lvt_7_1_));
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179249_a(this.field_179770_a);
      p_148840_1_.func_150787_b(this.field_179769_b.size());
      Iterator lvt_2_1_ = this.field_179769_b.iterator();

      while(true) {
         while(lvt_2_1_.hasNext()) {
            S38PacketPlayerListItem.AddPlayerData lvt_3_1_ = (S38PacketPlayerListItem.AddPlayerData)lvt_2_1_.next();
            switch(this.field_179770_a) {
            case ADD_PLAYER:
               p_148840_1_.func_179252_a(lvt_3_1_.func_179962_a().getId());
               p_148840_1_.func_180714_a(lvt_3_1_.func_179962_a().getName());
               p_148840_1_.func_150787_b(lvt_3_1_.func_179962_a().getProperties().size());
               Iterator lvt_4_1_ = lvt_3_1_.func_179962_a().getProperties().values().iterator();

               while(lvt_4_1_.hasNext()) {
                  Property lvt_5_1_ = (Property)lvt_4_1_.next();
                  p_148840_1_.func_180714_a(lvt_5_1_.getName());
                  p_148840_1_.func_180714_a(lvt_5_1_.getValue());
                  if (lvt_5_1_.hasSignature()) {
                     p_148840_1_.writeBoolean(true);
                     p_148840_1_.func_180714_a(lvt_5_1_.getSignature());
                  } else {
                     p_148840_1_.writeBoolean(false);
                  }
               }

               p_148840_1_.func_150787_b(lvt_3_1_.func_179960_c().func_77148_a());
               p_148840_1_.func_150787_b(lvt_3_1_.func_179963_b());
               if (lvt_3_1_.func_179961_d() == null) {
                  p_148840_1_.writeBoolean(false);
               } else {
                  p_148840_1_.writeBoolean(true);
                  p_148840_1_.func_179256_a(lvt_3_1_.func_179961_d());
               }
               break;
            case UPDATE_GAME_MODE:
               p_148840_1_.func_179252_a(lvt_3_1_.func_179962_a().getId());
               p_148840_1_.func_150787_b(lvt_3_1_.func_179960_c().func_77148_a());
               break;
            case UPDATE_LATENCY:
               p_148840_1_.func_179252_a(lvt_3_1_.func_179962_a().getId());
               p_148840_1_.func_150787_b(lvt_3_1_.func_179963_b());
               break;
            case UPDATE_DISPLAY_NAME:
               p_148840_1_.func_179252_a(lvt_3_1_.func_179962_a().getId());
               if (lvt_3_1_.func_179961_d() == null) {
                  p_148840_1_.writeBoolean(false);
               } else {
                  p_148840_1_.writeBoolean(true);
                  p_148840_1_.func_179256_a(lvt_3_1_.func_179961_d());
               }
               break;
            case REMOVE_PLAYER:
               p_148840_1_.func_179252_a(lvt_3_1_.func_179962_a().getId());
            }
         }

         return;
      }
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147256_a(this);
   }

   public List<S38PacketPlayerListItem.AddPlayerData> func_179767_a() {
      return this.field_179769_b;
   }

   public S38PacketPlayerListItem.Action func_179768_b() {
      return this.field_179770_a;
   }

   public String toString() {
      return Objects.toStringHelper((Object)this).add("action", this.field_179770_a).add("entries", this.field_179769_b).toString();
   }

   public class AddPlayerData {
      private final int field_179966_b;
      private final WorldSettings.GameType field_179967_c;
      private final GameProfile field_179964_d;
      private final IChatComponent field_179965_e;

      public AddPlayerData(GameProfile p_i45965_2_, int p_i45965_3_, WorldSettings.GameType p_i45965_4_, IChatComponent p_i45965_5_) {
         this.field_179964_d = p_i45965_2_;
         this.field_179966_b = p_i45965_3_;
         this.field_179967_c = p_i45965_4_;
         this.field_179965_e = p_i45965_5_;
      }

      public GameProfile func_179962_a() {
         return this.field_179964_d;
      }

      public int func_179963_b() {
         return this.field_179966_b;
      }

      public WorldSettings.GameType func_179960_c() {
         return this.field_179967_c;
      }

      public IChatComponent func_179961_d() {
         return this.field_179965_e;
      }

      public String toString() {
         return Objects.toStringHelper((Object)this).add("latency", this.field_179966_b).add("gameMode", this.field_179967_c).add("profile", this.field_179964_d).add("displayName", this.field_179965_e == null ? null : IChatComponent.Serializer.func_150696_a(this.field_179965_e)).toString();
      }
   }

   public static enum Action {
      ADD_PLAYER,
      UPDATE_GAME_MODE,
      UPDATE_LATENCY,
      UPDATE_DISPLAY_NAME,
      REMOVE_PLAYER;
   }
}
