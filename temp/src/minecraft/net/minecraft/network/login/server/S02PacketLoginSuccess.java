package net.minecraft.network.login.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S02PacketLoginSuccess implements Packet<INetHandlerLoginClient> {
   private GameProfile field_149602_a;

   public S02PacketLoginSuccess() {
   }

   public S02PacketLoginSuccess(GameProfile p_i45267_1_) {
      this.field_149602_a = p_i45267_1_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      String lvt_2_1_ = p_148837_1_.func_150789_c(36);
      String lvt_3_1_ = p_148837_1_.func_150789_c(16);
      UUID lvt_4_1_ = UUID.fromString(lvt_2_1_);
      this.field_149602_a = new GameProfile(lvt_4_1_, lvt_3_1_);
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      UUID lvt_2_1_ = this.field_149602_a.getId();
      p_148840_1_.func_180714_a(lvt_2_1_ == null ? "" : lvt_2_1_.toString());
      p_148840_1_.func_180714_a(this.field_149602_a.getName());
   }

   public void func_148833_a(INetHandlerLoginClient p_148833_1_) {
      p_148833_1_.func_147390_a(this);
   }

   public GameProfile func_179730_a() {
      return this.field_149602_a;
   }
}
