package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class S27PacketExplosion implements Packet<INetHandlerPlayClient> {
   private double field_149158_a;
   private double field_149156_b;
   private double field_149157_c;
   private float field_149154_d;
   private List<BlockPos> field_149155_e;
   private float field_149152_f;
   private float field_149153_g;
   private float field_149159_h;

   public S27PacketExplosion() {
   }

   public S27PacketExplosion(double p_i45193_1_, double p_i45193_3_, double p_i45193_5_, float p_i45193_7_, List<BlockPos> p_i45193_8_, Vec3 p_i45193_9_) {
      this.field_149158_a = p_i45193_1_;
      this.field_149156_b = p_i45193_3_;
      this.field_149157_c = p_i45193_5_;
      this.field_149154_d = p_i45193_7_;
      this.field_149155_e = Lists.newArrayList((Iterable)p_i45193_8_);
      if (p_i45193_9_ != null) {
         this.field_149152_f = (float)p_i45193_9_.field_72450_a;
         this.field_149153_g = (float)p_i45193_9_.field_72448_b;
         this.field_149159_h = (float)p_i45193_9_.field_72449_c;
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149158_a = (double)p_148837_1_.readFloat();
      this.field_149156_b = (double)p_148837_1_.readFloat();
      this.field_149157_c = (double)p_148837_1_.readFloat();
      this.field_149154_d = p_148837_1_.readFloat();
      int lvt_2_1_ = p_148837_1_.readInt();
      this.field_149155_e = Lists.newArrayListWithCapacity(lvt_2_1_);
      int lvt_3_1_ = (int)this.field_149158_a;
      int lvt_4_1_ = (int)this.field_149156_b;
      int lvt_5_1_ = (int)this.field_149157_c;

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_2_1_; ++lvt_6_1_) {
         int lvt_7_1_ = p_148837_1_.readByte() + lvt_3_1_;
         int lvt_8_1_ = p_148837_1_.readByte() + lvt_4_1_;
         int lvt_9_1_ = p_148837_1_.readByte() + lvt_5_1_;
         this.field_149155_e.add(new BlockPos(lvt_7_1_, lvt_8_1_, lvt_9_1_));
      }

      this.field_149152_f = p_148837_1_.readFloat();
      this.field_149153_g = p_148837_1_.readFloat();
      this.field_149159_h = p_148837_1_.readFloat();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeFloat((float)this.field_149158_a);
      p_148840_1_.writeFloat((float)this.field_149156_b);
      p_148840_1_.writeFloat((float)this.field_149157_c);
      p_148840_1_.writeFloat(this.field_149154_d);
      p_148840_1_.writeInt(this.field_149155_e.size());
      int lvt_2_1_ = (int)this.field_149158_a;
      int lvt_3_1_ = (int)this.field_149156_b;
      int lvt_4_1_ = (int)this.field_149157_c;
      Iterator lvt_5_1_ = this.field_149155_e.iterator();

      while(lvt_5_1_.hasNext()) {
         BlockPos lvt_6_1_ = (BlockPos)lvt_5_1_.next();
         int lvt_7_1_ = lvt_6_1_.func_177958_n() - lvt_2_1_;
         int lvt_8_1_ = lvt_6_1_.func_177956_o() - lvt_3_1_;
         int lvt_9_1_ = lvt_6_1_.func_177952_p() - lvt_4_1_;
         p_148840_1_.writeByte(lvt_7_1_);
         p_148840_1_.writeByte(lvt_8_1_);
         p_148840_1_.writeByte(lvt_9_1_);
      }

      p_148840_1_.writeFloat(this.field_149152_f);
      p_148840_1_.writeFloat(this.field_149153_g);
      p_148840_1_.writeFloat(this.field_149159_h);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147283_a(this);
   }

   public float func_149149_c() {
      return this.field_149152_f;
   }

   public float func_149144_d() {
      return this.field_149153_g;
   }

   public float func_149147_e() {
      return this.field_149159_h;
   }

   public double func_149148_f() {
      return this.field_149158_a;
   }

   public double func_149143_g() {
      return this.field_149156_b;
   }

   public double func_149145_h() {
      return this.field_149157_c;
   }

   public float func_149146_i() {
      return this.field_149154_d;
   }

   public List<BlockPos> func_149150_j() {
      return this.field_149155_e;
   }
}
