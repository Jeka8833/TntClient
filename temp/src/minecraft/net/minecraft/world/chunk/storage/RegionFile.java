package net.minecraft.world.chunk.storage;

import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.server.MinecraftServer;

public class RegionFile {
   private static final byte[] field_76720_a = new byte[4096];
   private final File field_76718_b;
   private RandomAccessFile field_76719_c;
   private final int[] field_76716_d = new int[1024];
   private final int[] field_76717_e = new int[1024];
   private List<Boolean> field_76714_f;
   private int field_76715_g;
   private long field_76721_h;

   public RegionFile(File p_i2001_1_) {
      this.field_76718_b = p_i2001_1_;
      this.field_76715_g = 0;

      try {
         if (p_i2001_1_.exists()) {
            this.field_76721_h = p_i2001_1_.lastModified();
         }

         this.field_76719_c = new RandomAccessFile(p_i2001_1_, "rw");
         int lvt_2_4_;
         if (this.field_76719_c.length() < 4096L) {
            for(lvt_2_4_ = 0; lvt_2_4_ < 1024; ++lvt_2_4_) {
               this.field_76719_c.writeInt(0);
            }

            for(lvt_2_4_ = 0; lvt_2_4_ < 1024; ++lvt_2_4_) {
               this.field_76719_c.writeInt(0);
            }

            this.field_76715_g += 8192;
         }

         if ((this.field_76719_c.length() & 4095L) != 0L) {
            for(lvt_2_4_ = 0; (long)lvt_2_4_ < (this.field_76719_c.length() & 4095L); ++lvt_2_4_) {
               this.field_76719_c.write(0);
            }
         }

         lvt_2_4_ = (int)this.field_76719_c.length() / 4096;
         this.field_76714_f = Lists.newArrayListWithCapacity(lvt_2_4_);

         int lvt_3_3_;
         for(lvt_3_3_ = 0; lvt_3_3_ < lvt_2_4_; ++lvt_3_3_) {
            this.field_76714_f.add(true);
         }

         this.field_76714_f.set(0, false);
         this.field_76714_f.set(1, false);
         this.field_76719_c.seek(0L);

         int lvt_4_1_;
         for(lvt_3_3_ = 0; lvt_3_3_ < 1024; ++lvt_3_3_) {
            lvt_4_1_ = this.field_76719_c.readInt();
            this.field_76716_d[lvt_3_3_] = lvt_4_1_;
            if (lvt_4_1_ != 0 && (lvt_4_1_ >> 8) + (lvt_4_1_ & 255) <= this.field_76714_f.size()) {
               for(int lvt_5_1_ = 0; lvt_5_1_ < (lvt_4_1_ & 255); ++lvt_5_1_) {
                  this.field_76714_f.set((lvt_4_1_ >> 8) + lvt_5_1_, false);
               }
            }
         }

         for(lvt_3_3_ = 0; lvt_3_3_ < 1024; ++lvt_3_3_) {
            lvt_4_1_ = this.field_76719_c.readInt();
            this.field_76717_e[lvt_3_3_] = lvt_4_1_;
         }
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   public synchronized DataInputStream func_76704_a(int p_76704_1_, int p_76704_2_) {
      if (this.func_76705_d(p_76704_1_, p_76704_2_)) {
         return null;
      } else {
         try {
            int lvt_3_1_ = this.func_76707_e(p_76704_1_, p_76704_2_);
            if (lvt_3_1_ == 0) {
               return null;
            } else {
               int lvt_4_1_ = lvt_3_1_ >> 8;
               int lvt_5_1_ = lvt_3_1_ & 255;
               if (lvt_4_1_ + lvt_5_1_ > this.field_76714_f.size()) {
                  return null;
               } else {
                  this.field_76719_c.seek((long)(lvt_4_1_ * 4096));
                  int lvt_6_1_ = this.field_76719_c.readInt();
                  if (lvt_6_1_ > 4096 * lvt_5_1_) {
                     return null;
                  } else if (lvt_6_1_ <= 0) {
                     return null;
                  } else {
                     byte lvt_7_1_ = this.field_76719_c.readByte();
                     byte[] lvt_8_2_;
                     if (lvt_7_1_ == 1) {
                        lvt_8_2_ = new byte[lvt_6_1_ - 1];
                        this.field_76719_c.read(lvt_8_2_);
                        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(lvt_8_2_))));
                     } else if (lvt_7_1_ == 2) {
                        lvt_8_2_ = new byte[lvt_6_1_ - 1];
                        this.field_76719_c.read(lvt_8_2_);
                        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(lvt_8_2_))));
                     } else {
                        return null;
                     }
                  }
               }
            }
         } catch (IOException var9) {
            return null;
         }
      }
   }

   public DataOutputStream func_76710_b(int p_76710_1_, int p_76710_2_) {
      return this.func_76705_d(p_76710_1_, p_76710_2_) ? null : new DataOutputStream(new DeflaterOutputStream(new RegionFile.ChunkBuffer(p_76710_1_, p_76710_2_)));
   }

   protected synchronized void func_76706_a(int p_76706_1_, int p_76706_2_, byte[] p_76706_3_, int p_76706_4_) {
      try {
         int lvt_5_1_ = this.func_76707_e(p_76706_1_, p_76706_2_);
         int lvt_6_1_ = lvt_5_1_ >> 8;
         int lvt_7_1_ = lvt_5_1_ & 255;
         int lvt_8_1_ = (p_76706_4_ + 5) / 4096 + 1;
         if (lvt_8_1_ >= 256) {
            return;
         }

         if (lvt_6_1_ != 0 && lvt_7_1_ == lvt_8_1_) {
            this.func_76712_a(lvt_6_1_, p_76706_3_, p_76706_4_);
         } else {
            int lvt_9_2_;
            for(lvt_9_2_ = 0; lvt_9_2_ < lvt_7_1_; ++lvt_9_2_) {
               this.field_76714_f.set(lvt_6_1_ + lvt_9_2_, true);
            }

            lvt_9_2_ = this.field_76714_f.indexOf(true);
            int lvt_10_1_ = 0;
            int lvt_11_1_;
            if (lvt_9_2_ != -1) {
               for(lvt_11_1_ = lvt_9_2_; lvt_11_1_ < this.field_76714_f.size(); ++lvt_11_1_) {
                  if (lvt_10_1_ != 0) {
                     if ((Boolean)this.field_76714_f.get(lvt_11_1_)) {
                        ++lvt_10_1_;
                     } else {
                        lvt_10_1_ = 0;
                     }
                  } else if ((Boolean)this.field_76714_f.get(lvt_11_1_)) {
                     lvt_9_2_ = lvt_11_1_;
                     lvt_10_1_ = 1;
                  }

                  if (lvt_10_1_ >= lvt_8_1_) {
                     break;
                  }
               }
            }

            if (lvt_10_1_ >= lvt_8_1_) {
               lvt_6_1_ = lvt_9_2_;
               this.func_76711_a(p_76706_1_, p_76706_2_, lvt_9_2_ << 8 | lvt_8_1_);

               for(lvt_11_1_ = 0; lvt_11_1_ < lvt_8_1_; ++lvt_11_1_) {
                  this.field_76714_f.set(lvt_6_1_ + lvt_11_1_, false);
               }

               this.func_76712_a(lvt_6_1_, p_76706_3_, p_76706_4_);
            } else {
               this.field_76719_c.seek(this.field_76719_c.length());
               lvt_6_1_ = this.field_76714_f.size();

               for(lvt_11_1_ = 0; lvt_11_1_ < lvt_8_1_; ++lvt_11_1_) {
                  this.field_76719_c.write(field_76720_a);
                  this.field_76714_f.add(false);
               }

               this.field_76715_g += 4096 * lvt_8_1_;
               this.func_76712_a(lvt_6_1_, p_76706_3_, p_76706_4_);
               this.func_76711_a(p_76706_1_, p_76706_2_, lvt_6_1_ << 8 | lvt_8_1_);
            }
         }

         this.func_76713_b(p_76706_1_, p_76706_2_, (int)(MinecraftServer.func_130071_aq() / 1000L));
      } catch (IOException var12) {
         var12.printStackTrace();
      }

   }

   private void func_76712_a(int p_76712_1_, byte[] p_76712_2_, int p_76712_3_) throws IOException {
      this.field_76719_c.seek((long)(p_76712_1_ * 4096));
      this.field_76719_c.writeInt(p_76712_3_ + 1);
      this.field_76719_c.writeByte(2);
      this.field_76719_c.write(p_76712_2_, 0, p_76712_3_);
   }

   private boolean func_76705_d(int p_76705_1_, int p_76705_2_) {
      return p_76705_1_ < 0 || p_76705_1_ >= 32 || p_76705_2_ < 0 || p_76705_2_ >= 32;
   }

   private int func_76707_e(int p_76707_1_, int p_76707_2_) {
      return this.field_76716_d[p_76707_1_ + p_76707_2_ * 32];
   }

   public boolean func_76709_c(int p_76709_1_, int p_76709_2_) {
      return this.func_76707_e(p_76709_1_, p_76709_2_) != 0;
   }

   private void func_76711_a(int p_76711_1_, int p_76711_2_, int p_76711_3_) throws IOException {
      this.field_76716_d[p_76711_1_ + p_76711_2_ * 32] = p_76711_3_;
      this.field_76719_c.seek((long)((p_76711_1_ + p_76711_2_ * 32) * 4));
      this.field_76719_c.writeInt(p_76711_3_);
   }

   private void func_76713_b(int p_76713_1_, int p_76713_2_, int p_76713_3_) throws IOException {
      this.field_76717_e[p_76713_1_ + p_76713_2_ * 32] = p_76713_3_;
      this.field_76719_c.seek((long)(4096 + (p_76713_1_ + p_76713_2_ * 32) * 4));
      this.field_76719_c.writeInt(p_76713_3_);
   }

   public void func_76708_c() throws IOException {
      if (this.field_76719_c != null) {
         this.field_76719_c.close();
      }

   }

   class ChunkBuffer extends ByteArrayOutputStream {
      private int field_76722_b;
      private int field_76723_c;

      public ChunkBuffer(int p_i2000_2_, int p_i2000_3_) {
         super(8096);
         this.field_76722_b = p_i2000_2_;
         this.field_76723_c = p_i2000_3_;
      }

      public void close() throws IOException {
         RegionFile.this.func_76706_a(this.field_76722_b, this.field_76723_c, this.buf, this.count);
      }
   }
}
