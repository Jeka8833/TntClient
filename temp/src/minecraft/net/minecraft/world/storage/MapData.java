package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec4b;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MapData extends WorldSavedData {
   public int field_76201_a;
   public int field_76199_b;
   public byte field_76200_c;
   public byte field_76197_d;
   public byte[] field_76198_e = new byte[16384];
   public List<MapData.MapInfo> field_76196_g = Lists.newArrayList();
   private Map<EntityPlayer, MapData.MapInfo> field_76202_j = Maps.newHashMap();
   public Map<String, Vec4b> field_76203_h = Maps.newLinkedHashMap();

   public MapData(String p_i2140_1_) {
      super(p_i2140_1_);
   }

   public void func_176054_a(double p_176054_1_, double p_176054_3_, int p_176054_5_) {
      int lvt_6_1_ = 128 * (1 << p_176054_5_);
      int lvt_7_1_ = MathHelper.func_76128_c((p_176054_1_ + 64.0D) / (double)lvt_6_1_);
      int lvt_8_1_ = MathHelper.func_76128_c((p_176054_3_ + 64.0D) / (double)lvt_6_1_);
      this.field_76201_a = lvt_7_1_ * lvt_6_1_ + lvt_6_1_ / 2 - 64;
      this.field_76199_b = lvt_8_1_ * lvt_6_1_ + lvt_6_1_ / 2 - 64;
   }

   public void func_76184_a(NBTTagCompound p_76184_1_) {
      this.field_76200_c = p_76184_1_.func_74771_c("dimension");
      this.field_76201_a = p_76184_1_.func_74762_e("xCenter");
      this.field_76199_b = p_76184_1_.func_74762_e("zCenter");
      this.field_76197_d = p_76184_1_.func_74771_c("scale");
      this.field_76197_d = (byte)MathHelper.func_76125_a(this.field_76197_d, 0, 4);
      int lvt_2_1_ = p_76184_1_.func_74765_d("width");
      int lvt_3_1_ = p_76184_1_.func_74765_d("height");
      if (lvt_2_1_ == 128 && lvt_3_1_ == 128) {
         this.field_76198_e = p_76184_1_.func_74770_j("colors");
      } else {
         byte[] lvt_4_1_ = p_76184_1_.func_74770_j("colors");
         this.field_76198_e = new byte[16384];
         int lvt_5_1_ = (128 - lvt_2_1_) / 2;
         int lvt_6_1_ = (128 - lvt_3_1_) / 2;

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_3_1_; ++lvt_7_1_) {
            int lvt_8_1_ = lvt_7_1_ + lvt_6_1_;
            if (lvt_8_1_ >= 0 || lvt_8_1_ < 128) {
               for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_2_1_; ++lvt_9_1_) {
                  int lvt_10_1_ = lvt_9_1_ + lvt_5_1_;
                  if (lvt_10_1_ >= 0 || lvt_10_1_ < 128) {
                     this.field_76198_e[lvt_10_1_ + lvt_8_1_ * 128] = lvt_4_1_[lvt_9_1_ + lvt_7_1_ * lvt_2_1_];
                  }
               }
            }
         }
      }

   }

   public void func_76187_b(NBTTagCompound p_76187_1_) {
      p_76187_1_.func_74774_a("dimension", this.field_76200_c);
      p_76187_1_.func_74768_a("xCenter", this.field_76201_a);
      p_76187_1_.func_74768_a("zCenter", this.field_76199_b);
      p_76187_1_.func_74774_a("scale", this.field_76197_d);
      p_76187_1_.func_74777_a("width", (short)128);
      p_76187_1_.func_74777_a("height", (short)128);
      p_76187_1_.func_74773_a("colors", this.field_76198_e);
   }

   public void func_76191_a(EntityPlayer p_76191_1_, ItemStack p_76191_2_) {
      if (!this.field_76202_j.containsKey(p_76191_1_)) {
         MapData.MapInfo lvt_3_1_ = new MapData.MapInfo(p_76191_1_);
         this.field_76202_j.put(p_76191_1_, lvt_3_1_);
         this.field_76196_g.add(lvt_3_1_);
      }

      if (!p_76191_1_.field_71071_by.func_70431_c(p_76191_2_)) {
         this.field_76203_h.remove(p_76191_1_.func_70005_c_());
      }

      for(int lvt_3_2_ = 0; lvt_3_2_ < this.field_76196_g.size(); ++lvt_3_2_) {
         MapData.MapInfo lvt_4_1_ = (MapData.MapInfo)this.field_76196_g.get(lvt_3_2_);
         if (lvt_4_1_.field_76211_a.field_70128_L || !lvt_4_1_.field_76211_a.field_71071_by.func_70431_c(p_76191_2_) && !p_76191_2_.func_82839_y()) {
            this.field_76202_j.remove(lvt_4_1_.field_76211_a);
            this.field_76196_g.remove(lvt_4_1_);
         } else if (!p_76191_2_.func_82839_y() && lvt_4_1_.field_76211_a.field_71093_bK == this.field_76200_c) {
            this.func_82567_a(0, lvt_4_1_.field_76211_a.field_70170_p, lvt_4_1_.field_76211_a.func_70005_c_(), lvt_4_1_.field_76211_a.field_70165_t, lvt_4_1_.field_76211_a.field_70161_v, (double)lvt_4_1_.field_76211_a.field_70177_z);
         }
      }

      if (p_76191_2_.func_82839_y()) {
         EntityItemFrame lvt_3_3_ = p_76191_2_.func_82836_z();
         BlockPos lvt_4_2_ = lvt_3_3_.func_174857_n();
         this.func_82567_a(1, p_76191_1_.field_70170_p, "frame-" + lvt_3_3_.func_145782_y(), (double)lvt_4_2_.func_177958_n(), (double)lvt_4_2_.func_177952_p(), (double)(lvt_3_3_.field_174860_b.func_176736_b() * 90));
      }

      if (p_76191_2_.func_77942_o() && p_76191_2_.func_77978_p().func_150297_b("Decorations", 9)) {
         NBTTagList lvt_3_4_ = p_76191_2_.func_77978_p().func_150295_c("Decorations", 10);

         for(int lvt_4_3_ = 0; lvt_4_3_ < lvt_3_4_.func_74745_c(); ++lvt_4_3_) {
            NBTTagCompound lvt_5_1_ = lvt_3_4_.func_150305_b(lvt_4_3_);
            if (!this.field_76203_h.containsKey(lvt_5_1_.func_74779_i("id"))) {
               this.func_82567_a(lvt_5_1_.func_74771_c("type"), p_76191_1_.field_70170_p, lvt_5_1_.func_74779_i("id"), lvt_5_1_.func_74769_h("x"), lvt_5_1_.func_74769_h("z"), lvt_5_1_.func_74769_h("rot"));
            }
         }
      }

   }

   private void func_82567_a(int p_82567_1_, World p_82567_2_, String p_82567_3_, double p_82567_4_, double p_82567_6_, double p_82567_8_) {
      int lvt_10_1_ = 1 << this.field_76197_d;
      float lvt_11_1_ = (float)(p_82567_4_ - (double)this.field_76201_a) / (float)lvt_10_1_;
      float lvt_12_1_ = (float)(p_82567_6_ - (double)this.field_76199_b) / (float)lvt_10_1_;
      byte lvt_13_1_ = (byte)((int)((double)(lvt_11_1_ * 2.0F) + 0.5D));
      byte lvt_14_1_ = (byte)((int)((double)(lvt_12_1_ * 2.0F) + 0.5D));
      int lvt_16_1_ = 63;
      byte lvt_15_2_;
      if (lvt_11_1_ >= (float)(-lvt_16_1_) && lvt_12_1_ >= (float)(-lvt_16_1_) && lvt_11_1_ <= (float)lvt_16_1_ && lvt_12_1_ <= (float)lvt_16_1_) {
         p_82567_8_ += p_82567_8_ < 0.0D ? -8.0D : 8.0D;
         lvt_15_2_ = (byte)((int)(p_82567_8_ * 16.0D / 360.0D));
         if (this.field_76200_c < 0) {
            int lvt_17_1_ = (int)(p_82567_2_.func_72912_H().func_76073_f() / 10L);
            lvt_15_2_ = (byte)(lvt_17_1_ * lvt_17_1_ * 34187121 + lvt_17_1_ * 121 >> 15 & 15);
         }
      } else {
         if (Math.abs(lvt_11_1_) >= 320.0F || Math.abs(lvt_12_1_) >= 320.0F) {
            this.field_76203_h.remove(p_82567_3_);
            return;
         }

         p_82567_1_ = 6;
         lvt_15_2_ = 0;
         if (lvt_11_1_ <= (float)(-lvt_16_1_)) {
            lvt_13_1_ = (byte)((int)((double)(lvt_16_1_ * 2) + 2.5D));
         }

         if (lvt_12_1_ <= (float)(-lvt_16_1_)) {
            lvt_14_1_ = (byte)((int)((double)(lvt_16_1_ * 2) + 2.5D));
         }

         if (lvt_11_1_ >= (float)lvt_16_1_) {
            lvt_13_1_ = (byte)(lvt_16_1_ * 2 + 1);
         }

         if (lvt_12_1_ >= (float)lvt_16_1_) {
            lvt_14_1_ = (byte)(lvt_16_1_ * 2 + 1);
         }
      }

      this.field_76203_h.put(p_82567_3_, new Vec4b((byte)p_82567_1_, lvt_13_1_, lvt_14_1_, lvt_15_2_));
   }

   public Packet func_176052_a(ItemStack p_176052_1_, World p_176052_2_, EntityPlayer p_176052_3_) {
      MapData.MapInfo lvt_4_1_ = (MapData.MapInfo)this.field_76202_j.get(p_176052_3_);
      return lvt_4_1_ == null ? null : lvt_4_1_.func_176101_a(p_176052_1_);
   }

   public void func_176053_a(int p_176053_1_, int p_176053_2_) {
      super.func_76185_a();
      Iterator lvt_3_1_ = this.field_76196_g.iterator();

      while(lvt_3_1_.hasNext()) {
         MapData.MapInfo lvt_4_1_ = (MapData.MapInfo)lvt_3_1_.next();
         lvt_4_1_.func_176102_a(p_176053_1_, p_176053_2_);
      }

   }

   public MapData.MapInfo func_82568_a(EntityPlayer p_82568_1_) {
      MapData.MapInfo lvt_2_1_ = (MapData.MapInfo)this.field_76202_j.get(p_82568_1_);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = new MapData.MapInfo(p_82568_1_);
         this.field_76202_j.put(p_82568_1_, lvt_2_1_);
         this.field_76196_g.add(lvt_2_1_);
      }

      return lvt_2_1_;
   }

   public class MapInfo {
      public final EntityPlayer field_76211_a;
      private boolean field_176105_d = true;
      private int field_176106_e = 0;
      private int field_176103_f = 0;
      private int field_176104_g = 127;
      private int field_176108_h = 127;
      private int field_176109_i;
      public int field_82569_d;

      public MapInfo(EntityPlayer p_i2138_2_) {
         this.field_76211_a = p_i2138_2_;
      }

      public Packet func_176101_a(ItemStack p_176101_1_) {
         if (this.field_176105_d) {
            this.field_176105_d = false;
            return new S34PacketMaps(p_176101_1_.func_77960_j(), MapData.this.field_76197_d, MapData.this.field_76203_h.values(), MapData.this.field_76198_e, this.field_176106_e, this.field_176103_f, this.field_176104_g + 1 - this.field_176106_e, this.field_176108_h + 1 - this.field_176103_f);
         } else {
            return this.field_176109_i++ % 5 == 0 ? new S34PacketMaps(p_176101_1_.func_77960_j(), MapData.this.field_76197_d, MapData.this.field_76203_h.values(), MapData.this.field_76198_e, 0, 0, 0, 0) : null;
         }
      }

      public void func_176102_a(int p_176102_1_, int p_176102_2_) {
         if (this.field_176105_d) {
            this.field_176106_e = Math.min(this.field_176106_e, p_176102_1_);
            this.field_176103_f = Math.min(this.field_176103_f, p_176102_2_);
            this.field_176104_g = Math.max(this.field_176104_g, p_176102_1_);
            this.field_176108_h = Math.max(this.field_176108_h, p_176102_2_);
         } else {
            this.field_176105_d = true;
            this.field_176106_e = p_176102_1_;
            this.field_176103_f = p_176102_2_;
            this.field_176104_g = p_176102_1_;
            this.field_176108_h = p_176102_2_;
         }

      }
   }
}
