package net.minecraft.nbt;

import com.google.common.collect.Maps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public class NBTTagCompound extends NBTBase {
   private Map<String, NBTBase> field_74784_a = Maps.newHashMap();

   void func_74734_a(DataOutput p_74734_1_) throws IOException {
      Iterator lvt_2_1_ = this.field_74784_a.keySet().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         NBTBase lvt_4_1_ = (NBTBase)this.field_74784_a.get(lvt_3_1_);
         func_150298_a(lvt_3_1_, lvt_4_1_, p_74734_1_);
      }

      p_74734_1_.writeByte(0);
   }

   void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException {
      p_152446_3_.func_152450_a(384L);
      if (p_152446_2_ > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.field_74784_a.clear();

         byte lvt_4_1_;
         while((lvt_4_1_ = func_152447_a(p_152446_1_, p_152446_3_)) != 0) {
            String lvt_5_1_ = func_152448_b(p_152446_1_, p_152446_3_);
            p_152446_3_.func_152450_a((long)(224 + 16 * lvt_5_1_.length()));
            NBTBase lvt_6_1_ = func_152449_a(lvt_4_1_, lvt_5_1_, p_152446_1_, p_152446_2_ + 1, p_152446_3_);
            if (this.field_74784_a.put(lvt_5_1_, lvt_6_1_) != null) {
               p_152446_3_.func_152450_a(288L);
            }
         }

      }
   }

   public Set<String> func_150296_c() {
      return this.field_74784_a.keySet();
   }

   public byte func_74732_a() {
      return 10;
   }

   public void func_74782_a(String p_74782_1_, NBTBase p_74782_2_) {
      this.field_74784_a.put(p_74782_1_, p_74782_2_);
   }

   public void func_74774_a(String p_74774_1_, byte p_74774_2_) {
      this.field_74784_a.put(p_74774_1_, new NBTTagByte(p_74774_2_));
   }

   public void func_74777_a(String p_74777_1_, short p_74777_2_) {
      this.field_74784_a.put(p_74777_1_, new NBTTagShort(p_74777_2_));
   }

   public void func_74768_a(String p_74768_1_, int p_74768_2_) {
      this.field_74784_a.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   }

   public void func_74772_a(String p_74772_1_, long p_74772_2_) {
      this.field_74784_a.put(p_74772_1_, new NBTTagLong(p_74772_2_));
   }

   public void func_74776_a(String p_74776_1_, float p_74776_2_) {
      this.field_74784_a.put(p_74776_1_, new NBTTagFloat(p_74776_2_));
   }

   public void func_74780_a(String p_74780_1_, double p_74780_2_) {
      this.field_74784_a.put(p_74780_1_, new NBTTagDouble(p_74780_2_));
   }

   public void func_74778_a(String p_74778_1_, String p_74778_2_) {
      this.field_74784_a.put(p_74778_1_, new NBTTagString(p_74778_2_));
   }

   public void func_74773_a(String p_74773_1_, byte[] p_74773_2_) {
      this.field_74784_a.put(p_74773_1_, new NBTTagByteArray(p_74773_2_));
   }

   public void func_74783_a(String p_74783_1_, int[] p_74783_2_) {
      this.field_74784_a.put(p_74783_1_, new NBTTagIntArray(p_74783_2_));
   }

   public void func_74757_a(String p_74757_1_, boolean p_74757_2_) {
      this.func_74774_a(p_74757_1_, (byte)(p_74757_2_ ? 1 : 0));
   }

   public NBTBase func_74781_a(String p_74781_1_) {
      return (NBTBase)this.field_74784_a.get(p_74781_1_);
   }

   public byte func_150299_b(String p_150299_1_) {
      NBTBase lvt_2_1_ = (NBTBase)this.field_74784_a.get(p_150299_1_);
      return lvt_2_1_ != null ? lvt_2_1_.func_74732_a() : 0;
   }

   public boolean func_74764_b(String p_74764_1_) {
      return this.field_74784_a.containsKey(p_74764_1_);
   }

   public boolean func_150297_b(String p_150297_1_, int p_150297_2_) {
      int lvt_3_1_ = this.func_150299_b(p_150297_1_);
      if (lvt_3_1_ == p_150297_2_) {
         return true;
      } else if (p_150297_2_ != 99) {
         if (lvt_3_1_ > 0) {
         }

         return false;
      } else {
         return lvt_3_1_ == 1 || lvt_3_1_ == 2 || lvt_3_1_ == 3 || lvt_3_1_ == 4 || lvt_3_1_ == 5 || lvt_3_1_ == 6;
      }
   }

   public byte func_74771_c(String p_74771_1_) {
      try {
         return !this.func_150297_b(p_74771_1_, 99) ? 0 : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74771_1_)).func_150290_f();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public short func_74765_d(String p_74765_1_) {
      try {
         return !this.func_150297_b(p_74765_1_, 99) ? 0 : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74765_1_)).func_150289_e();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public int func_74762_e(String p_74762_1_) {
      try {
         return !this.func_150297_b(p_74762_1_, 99) ? 0 : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74762_1_)).func_150287_d();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public long func_74763_f(String p_74763_1_) {
      try {
         return !this.func_150297_b(p_74763_1_, 99) ? 0L : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74763_1_)).func_150291_c();
      } catch (ClassCastException var3) {
         return 0L;
      }
   }

   public float func_74760_g(String p_74760_1_) {
      try {
         return !this.func_150297_b(p_74760_1_, 99) ? 0.0F : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74760_1_)).func_150288_h();
      } catch (ClassCastException var3) {
         return 0.0F;
      }
   }

   public double func_74769_h(String p_74769_1_) {
      try {
         return !this.func_150297_b(p_74769_1_, 99) ? 0.0D : ((NBTBase.NBTPrimitive)this.field_74784_a.get(p_74769_1_)).func_150286_g();
      } catch (ClassCastException var3) {
         return 0.0D;
      }
   }

   public String func_74779_i(String p_74779_1_) {
      try {
         return !this.func_150297_b(p_74779_1_, 8) ? "" : ((NBTBase)this.field_74784_a.get(p_74779_1_)).func_150285_a_();
      } catch (ClassCastException var3) {
         return "";
      }
   }

   public byte[] func_74770_j(String p_74770_1_) {
      try {
         return !this.func_150297_b(p_74770_1_, 7) ? new byte[0] : ((NBTTagByteArray)this.field_74784_a.get(p_74770_1_)).func_150292_c();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.func_82581_a(p_74770_1_, 7, var3));
      }
   }

   public int[] func_74759_k(String p_74759_1_) {
      try {
         return !this.func_150297_b(p_74759_1_, 11) ? new int[0] : ((NBTTagIntArray)this.field_74784_a.get(p_74759_1_)).func_150302_c();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.func_82581_a(p_74759_1_, 11, var3));
      }
   }

   public NBTTagCompound func_74775_l(String p_74775_1_) {
      try {
         return !this.func_150297_b(p_74775_1_, 10) ? new NBTTagCompound() : (NBTTagCompound)this.field_74784_a.get(p_74775_1_);
      } catch (ClassCastException var3) {
         throw new ReportedException(this.func_82581_a(p_74775_1_, 10, var3));
      }
   }

   public NBTTagList func_150295_c(String p_150295_1_, int p_150295_2_) {
      try {
         if (this.func_150299_b(p_150295_1_) != 9) {
            return new NBTTagList();
         } else {
            NBTTagList lvt_3_1_ = (NBTTagList)this.field_74784_a.get(p_150295_1_);
            return lvt_3_1_.func_74745_c() > 0 && lvt_3_1_.func_150303_d() != p_150295_2_ ? new NBTTagList() : lvt_3_1_;
         }
      } catch (ClassCastException var4) {
         throw new ReportedException(this.func_82581_a(p_150295_1_, 9, var4));
      }
   }

   public boolean func_74767_n(String p_74767_1_) {
      return this.func_74771_c(p_74767_1_) != 0;
   }

   public void func_82580_o(String p_82580_1_) {
      this.field_74784_a.remove(p_82580_1_);
   }

   public String toString() {
      StringBuilder lvt_1_1_ = new StringBuilder("{");

      Entry lvt_3_1_;
      for(Iterator lvt_2_1_ = this.field_74784_a.entrySet().iterator(); lvt_2_1_.hasNext(); lvt_1_1_.append((String)lvt_3_1_.getKey()).append(':').append(lvt_3_1_.getValue())) {
         lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (lvt_1_1_.length() != 1) {
            lvt_1_1_.append(',');
         }
      }

      return lvt_1_1_.append('}').toString();
   }

   public boolean func_82582_d() {
      return this.field_74784_a.isEmpty();
   }

   private CrashReport func_82581_a(final String p_82581_1_, final int p_82581_2_, ClassCastException p_82581_3_) {
      CrashReport lvt_4_1_ = CrashReport.func_85055_a(p_82581_3_, "Reading NBT data");
      CrashReportCategory lvt_5_1_ = lvt_4_1_.func_85057_a("Corrupt NBT tag", 1);
      lvt_5_1_.func_71500_a("Tag type found", new Callable<String>() {
         public String call() throws Exception {
            return NBTBase.field_82578_b[((NBTBase)NBTTagCompound.this.field_74784_a.get(p_82581_1_)).func_74732_a()];
         }
      });
      lvt_5_1_.func_71500_a("Tag type expected", new Callable<String>() {
         public String call() throws Exception {
            return NBTBase.field_82578_b[p_82581_2_];
         }
      });
      lvt_5_1_.func_71507_a("Tag name", p_82581_1_);
      return lvt_4_1_;
   }

   public NBTBase func_74737_b() {
      NBTTagCompound lvt_1_1_ = new NBTTagCompound();
      Iterator lvt_2_1_ = this.field_74784_a.keySet().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         lvt_1_1_.func_74782_a(lvt_3_1_, ((NBTBase)this.field_74784_a.get(lvt_3_1_)).func_74737_b());
      }

      return lvt_1_1_;
   }

   public boolean equals(Object p_equals_1_) {
      if (super.equals(p_equals_1_)) {
         NBTTagCompound lvt_2_1_ = (NBTTagCompound)p_equals_1_;
         return this.field_74784_a.entrySet().equals(lvt_2_1_.field_74784_a.entrySet());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.field_74784_a.hashCode();
   }

   private static void func_150298_a(String p_150298_0_, NBTBase p_150298_1_, DataOutput p_150298_2_) throws IOException {
      p_150298_2_.writeByte(p_150298_1_.func_74732_a());
      if (p_150298_1_.func_74732_a() != 0) {
         p_150298_2_.writeUTF(p_150298_0_);
         p_150298_1_.func_74734_a(p_150298_2_);
      }
   }

   private static byte func_152447_a(DataInput p_152447_0_, NBTSizeTracker p_152447_1_) throws IOException {
      return p_152447_0_.readByte();
   }

   private static String func_152448_b(DataInput p_152448_0_, NBTSizeTracker p_152448_1_) throws IOException {
      return p_152448_0_.readUTF();
   }

   static NBTBase func_152449_a(byte p_152449_0_, String p_152449_1_, DataInput p_152449_2_, int p_152449_3_, NBTSizeTracker p_152449_4_) throws IOException {
      NBTBase lvt_5_1_ = NBTBase.func_150284_a(p_152449_0_);

      try {
         lvt_5_1_.func_152446_a(p_152449_2_, p_152449_3_, p_152449_4_);
         return lvt_5_1_;
      } catch (IOException var9) {
         CrashReport lvt_7_1_ = CrashReport.func_85055_a(var9, "Loading NBT data");
         CrashReportCategory lvt_8_1_ = lvt_7_1_.func_85058_a("NBT Tag");
         lvt_8_1_.func_71507_a("Tag name", p_152449_1_);
         lvt_8_1_.func_71507_a("Tag type", p_152449_0_);
         throw new ReportedException(lvt_7_1_);
      }
   }

   public void func_179237_a(NBTTagCompound p_179237_1_) {
      Iterator lvt_2_1_ = p_179237_1_.field_74784_a.keySet().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         NBTBase lvt_4_1_ = (NBTBase)p_179237_1_.field_74784_a.get(lvt_3_1_);
         if (lvt_4_1_.func_74732_a() == 10) {
            if (this.func_150297_b(lvt_3_1_, 10)) {
               NBTTagCompound lvt_5_1_ = this.func_74775_l(lvt_3_1_);
               lvt_5_1_.func_179237_a((NBTTagCompound)lvt_4_1_);
            } else {
               this.func_74782_a(lvt_3_1_, lvt_4_1_.func_74737_b());
            }
         } else {
            this.func_74782_a(lvt_3_1_, lvt_4_1_.func_74737_b());
         }
      }

   }
}
