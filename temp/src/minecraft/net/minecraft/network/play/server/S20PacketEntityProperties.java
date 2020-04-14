package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S20PacketEntityProperties implements Packet<INetHandlerPlayClient> {
   private int field_149445_a;
   private final List<S20PacketEntityProperties.Snapshot> field_149444_b = Lists.newArrayList();

   public S20PacketEntityProperties() {
   }

   public S20PacketEntityProperties(int p_i45236_1_, Collection<IAttributeInstance> p_i45236_2_) {
      this.field_149445_a = p_i45236_1_;
      Iterator lvt_3_1_ = p_i45236_2_.iterator();

      while(lvt_3_1_.hasNext()) {
         IAttributeInstance lvt_4_1_ = (IAttributeInstance)lvt_3_1_.next();
         this.field_149444_b.add(new S20PacketEntityProperties.Snapshot(lvt_4_1_.func_111123_a().func_111108_a(), lvt_4_1_.func_111125_b(), lvt_4_1_.func_111122_c()));
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149445_a = p_148837_1_.func_150792_a();
      int lvt_2_1_ = p_148837_1_.readInt();

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         String lvt_4_1_ = p_148837_1_.func_150789_c(64);
         double lvt_5_1_ = p_148837_1_.readDouble();
         List<AttributeModifier> lvt_7_1_ = Lists.newArrayList();
         int lvt_8_1_ = p_148837_1_.func_150792_a();

         for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
            UUID lvt_10_1_ = p_148837_1_.func_179253_g();
            lvt_7_1_.add(new AttributeModifier(lvt_10_1_, "Unknown synced attribute modifier", p_148837_1_.readDouble(), p_148837_1_.readByte()));
         }

         this.field_149444_b.add(new S20PacketEntityProperties.Snapshot(lvt_4_1_, lvt_5_1_, lvt_7_1_));
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_149445_a);
      p_148840_1_.writeInt(this.field_149444_b.size());
      Iterator lvt_2_1_ = this.field_149444_b.iterator();

      while(lvt_2_1_.hasNext()) {
         S20PacketEntityProperties.Snapshot lvt_3_1_ = (S20PacketEntityProperties.Snapshot)lvt_2_1_.next();
         p_148840_1_.func_180714_a(lvt_3_1_.func_151409_a());
         p_148840_1_.writeDouble(lvt_3_1_.func_151410_b());
         p_148840_1_.func_150787_b(lvt_3_1_.func_151408_c().size());
         Iterator lvt_4_1_ = lvt_3_1_.func_151408_c().iterator();

         while(lvt_4_1_.hasNext()) {
            AttributeModifier lvt_5_1_ = (AttributeModifier)lvt_4_1_.next();
            p_148840_1_.func_179252_a(lvt_5_1_.func_111167_a());
            p_148840_1_.writeDouble(lvt_5_1_.func_111164_d());
            p_148840_1_.writeByte(lvt_5_1_.func_111169_c());
         }
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147290_a(this);
   }

   public int func_149442_c() {
      return this.field_149445_a;
   }

   public List<S20PacketEntityProperties.Snapshot> func_149441_d() {
      return this.field_149444_b;
   }

   public class Snapshot {
      private final String field_151412_b;
      private final double field_151413_c;
      private final Collection<AttributeModifier> field_151411_d;

      public Snapshot(String p_i45235_2_, double p_i45235_3_, Collection<AttributeModifier> p_i45235_5_) {
         this.field_151412_b = p_i45235_2_;
         this.field_151413_c = p_i45235_3_;
         this.field_151411_d = p_i45235_5_;
      }

      public String func_151409_a() {
         return this.field_151412_b;
      }

      public double func_151410_b() {
         return this.field_151413_c;
      }

      public Collection<AttributeModifier> func_151408_c() {
         return this.field_151411_d;
      }
   }
}
