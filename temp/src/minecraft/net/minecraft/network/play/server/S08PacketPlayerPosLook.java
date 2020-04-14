package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S08PacketPlayerPosLook implements Packet<INetHandlerPlayClient> {
   private double field_148940_a;
   private double field_148938_b;
   private double field_148939_c;
   private float field_148936_d;
   private float field_148937_e;
   private Set<S08PacketPlayerPosLook.EnumFlags> field_179835_f;

   public S08PacketPlayerPosLook() {
   }

   public S08PacketPlayerPosLook(double p_i45993_1_, double p_i45993_3_, double p_i45993_5_, float p_i45993_7_, float p_i45993_8_, Set<S08PacketPlayerPosLook.EnumFlags> p_i45993_9_) {
      this.field_148940_a = p_i45993_1_;
      this.field_148938_b = p_i45993_3_;
      this.field_148939_c = p_i45993_5_;
      this.field_148936_d = p_i45993_7_;
      this.field_148937_e = p_i45993_8_;
      this.field_179835_f = p_i45993_9_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148940_a = p_148837_1_.readDouble();
      this.field_148938_b = p_148837_1_.readDouble();
      this.field_148939_c = p_148837_1_.readDouble();
      this.field_148936_d = p_148837_1_.readFloat();
      this.field_148937_e = p_148837_1_.readFloat();
      this.field_179835_f = S08PacketPlayerPosLook.EnumFlags.func_180053_a(p_148837_1_.readUnsignedByte());
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeDouble(this.field_148940_a);
      p_148840_1_.writeDouble(this.field_148938_b);
      p_148840_1_.writeDouble(this.field_148939_c);
      p_148840_1_.writeFloat(this.field_148936_d);
      p_148840_1_.writeFloat(this.field_148937_e);
      p_148840_1_.writeByte(S08PacketPlayerPosLook.EnumFlags.func_180056_a(this.field_179835_f));
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147258_a(this);
   }

   public double func_148932_c() {
      return this.field_148940_a;
   }

   public double func_148928_d() {
      return this.field_148938_b;
   }

   public double func_148933_e() {
      return this.field_148939_c;
   }

   public float func_148931_f() {
      return this.field_148936_d;
   }

   public float func_148930_g() {
      return this.field_148937_e;
   }

   public Set<S08PacketPlayerPosLook.EnumFlags> func_179834_f() {
      return this.field_179835_f;
   }

   public static enum EnumFlags {
      X(0),
      Y(1),
      Z(2),
      Y_ROT(3),
      X_ROT(4);

      private int field_180058_f;

      private EnumFlags(int p_i45992_3_) {
         this.field_180058_f = p_i45992_3_;
      }

      private int func_180055_a() {
         return 1 << this.field_180058_f;
      }

      private boolean func_180054_b(int p_180054_1_) {
         return (p_180054_1_ & this.func_180055_a()) == this.func_180055_a();
      }

      public static Set<S08PacketPlayerPosLook.EnumFlags> func_180053_a(int p_180053_0_) {
         Set<S08PacketPlayerPosLook.EnumFlags> lvt_1_1_ = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);
         S08PacketPlayerPosLook.EnumFlags[] lvt_2_1_ = values();
         int lvt_3_1_ = lvt_2_1_.length;

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
            S08PacketPlayerPosLook.EnumFlags lvt_5_1_ = lvt_2_1_[lvt_4_1_];
            if (lvt_5_1_.func_180054_b(p_180053_0_)) {
               lvt_1_1_.add(lvt_5_1_);
            }
         }

         return lvt_1_1_;
      }

      public static int func_180056_a(Set<S08PacketPlayerPosLook.EnumFlags> p_180056_0_) {
         int lvt_1_1_ = 0;

         S08PacketPlayerPosLook.EnumFlags lvt_3_1_;
         for(Iterator lvt_2_1_ = p_180056_0_.iterator(); lvt_2_1_.hasNext(); lvt_1_1_ |= lvt_3_1_.func_180055_a()) {
            lvt_3_1_ = (S08PacketPlayerPosLook.EnumFlags)lvt_2_1_.next();
         }

         return lvt_1_1_;
      }
   }
}
