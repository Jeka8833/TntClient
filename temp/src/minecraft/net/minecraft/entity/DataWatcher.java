package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotations;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {
   private final Entity field_151511_a;
   private boolean field_92086_a = true;
   private static final Map<Class<?>, Integer> field_75697_a = Maps.newHashMap();
   private final Map<Integer, DataWatcher.WatchableObject> field_75695_b = Maps.newHashMap();
   private boolean field_75696_c;
   private ReadWriteLock field_75694_d = new ReentrantReadWriteLock();
   public BiomeGenBase spawnBiome;
   public BlockPos spawnPosition;

   public DataWatcher(Entity p_i45313_1_) {
      this.spawnBiome = BiomeGenBase.field_76772_c;
      this.spawnPosition = BlockPos.field_177992_a;
      this.field_151511_a = p_i45313_1_;
   }

   public <T> void func_75682_a(int p_75682_1_, T p_75682_2_) {
      Integer integer = (Integer)field_75697_a.get(p_75682_2_.getClass());
      if (integer == null) {
         throw new IllegalArgumentException("Unknown data type: " + p_75682_2_.getClass());
      } else if (p_75682_1_ > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + p_75682_1_ + "! (Max is " + 31 + ")");
      } else if (this.field_75695_b.containsKey(p_75682_1_)) {
         throw new IllegalArgumentException("Duplicate id value for " + p_75682_1_ + "!");
      } else {
         DataWatcher.WatchableObject datawatcher$watchableobject = new DataWatcher.WatchableObject(integer, p_75682_1_, p_75682_2_);
         this.field_75694_d.writeLock().lock();
         this.field_75695_b.put(p_75682_1_, datawatcher$watchableobject);
         this.field_75694_d.writeLock().unlock();
         this.field_92086_a = false;
      }
   }

   public void func_82709_a(int p_82709_1_, int p_82709_2_) {
      DataWatcher.WatchableObject datawatcher$watchableobject = new DataWatcher.WatchableObject(p_82709_2_, p_82709_1_, (Object)null);
      this.field_75694_d.writeLock().lock();
      this.field_75695_b.put(p_82709_1_, datawatcher$watchableobject);
      this.field_75694_d.writeLock().unlock();
      this.field_92086_a = false;
   }

   public byte func_75683_a(int p_75683_1_) {
      return (Byte)this.func_75691_i(p_75683_1_).func_75669_b();
   }

   public short func_75693_b(int p_75693_1_) {
      return (Short)this.func_75691_i(p_75693_1_).func_75669_b();
   }

   public int func_75679_c(int p_75679_1_) {
      return (Integer)this.func_75691_i(p_75679_1_).func_75669_b();
   }

   public float func_111145_d(int p_111145_1_) {
      return (Float)this.func_75691_i(p_111145_1_).func_75669_b();
   }

   public String func_75681_e(int p_75681_1_) {
      return (String)this.func_75691_i(p_75681_1_).func_75669_b();
   }

   public ItemStack func_82710_f(int p_82710_1_) {
      return (ItemStack)this.func_75691_i(p_82710_1_).func_75669_b();
   }

   private DataWatcher.WatchableObject func_75691_i(int p_75691_1_) {
      this.field_75694_d.readLock().lock();

      DataWatcher.WatchableObject datawatcher$watchableobject;
      try {
         datawatcher$watchableobject = (DataWatcher.WatchableObject)this.field_75695_b.get(p_75691_1_);
      } catch (Throwable var6) {
         CrashReport crashreport = CrashReport.func_85055_a(var6, "Getting synched entity data");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Synched entity data");
         crashreportcategory.func_71507_a("Data ID", p_75691_1_);
         throw new ReportedException(crashreport);
      }

      this.field_75694_d.readLock().unlock();
      return datawatcher$watchableobject;
   }

   public Rotations func_180115_h(int p_180115_1_) {
      return (Rotations)this.func_75691_i(p_180115_1_).func_75669_b();
   }

   public <T> void func_75692_b(int p_75692_1_, T p_75692_2_) {
      DataWatcher.WatchableObject datawatcher$watchableobject = this.func_75691_i(p_75692_1_);
      if (ObjectUtils.notEqual(p_75692_2_, datawatcher$watchableobject.func_75669_b())) {
         datawatcher$watchableobject.func_75673_a(p_75692_2_);
         this.field_151511_a.func_145781_i(p_75692_1_);
         datawatcher$watchableobject.func_75671_a(true);
         this.field_75696_c = true;
      }

   }

   public void func_82708_h(int p_82708_1_) {
      this.func_75691_i(p_82708_1_).field_75675_d = true;
      this.field_75696_c = true;
   }

   public boolean func_75684_a() {
      return this.field_75696_c;
   }

   public static void func_151507_a(List<DataWatcher.WatchableObject> p_151507_0_, PacketBuffer p_151507_1_) throws IOException {
      if (p_151507_0_ != null) {
         Iterator i$ = p_151507_0_.iterator();

         while(i$.hasNext()) {
            DataWatcher.WatchableObject datawatcher$watchableobject = (DataWatcher.WatchableObject)i$.next();
            func_151510_a(p_151507_1_, datawatcher$watchableobject);
         }
      }

      p_151507_1_.writeByte(127);
   }

   public List<DataWatcher.WatchableObject> func_75688_b() {
      List<DataWatcher.WatchableObject> list = null;
      if (this.field_75696_c) {
         this.field_75694_d.readLock().lock();
         Iterator i$ = this.field_75695_b.values().iterator();

         while(i$.hasNext()) {
            DataWatcher.WatchableObject datawatcher$watchableobject = (DataWatcher.WatchableObject)i$.next();
            if (datawatcher$watchableobject.func_75670_d()) {
               datawatcher$watchableobject.func_75671_a(false);
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(datawatcher$watchableobject);
            }
         }

         this.field_75694_d.readLock().unlock();
      }

      this.field_75696_c = false;
      return list;
   }

   public void func_151509_a(PacketBuffer p_151509_1_) throws IOException {
      this.field_75694_d.readLock().lock();
      Iterator i$ = this.field_75695_b.values().iterator();

      while(i$.hasNext()) {
         DataWatcher.WatchableObject datawatcher$watchableobject = (DataWatcher.WatchableObject)i$.next();
         func_151510_a(p_151509_1_, datawatcher$watchableobject);
      }

      this.field_75694_d.readLock().unlock();
      p_151509_1_.writeByte(127);
   }

   public List<DataWatcher.WatchableObject> func_75685_c() {
      List<DataWatcher.WatchableObject> list = null;
      this.field_75694_d.readLock().lock();

      DataWatcher.WatchableObject datawatcher$watchableobject;
      for(Iterator i$ = this.field_75695_b.values().iterator(); i$.hasNext(); list.add(datawatcher$watchableobject)) {
         datawatcher$watchableobject = (DataWatcher.WatchableObject)i$.next();
         if (list == null) {
            list = Lists.newArrayList();
         }
      }

      this.field_75694_d.readLock().unlock();
      return list;
   }

   private static void func_151510_a(PacketBuffer p_151510_0_, DataWatcher.WatchableObject p_151510_1_) throws IOException {
      int i = (p_151510_1_.func_75674_c() << 5 | p_151510_1_.func_75672_a() & 31) & 255;
      p_151510_0_.writeByte(i);
      switch(p_151510_1_.func_75674_c()) {
      case 0:
         p_151510_0_.writeByte((Byte)p_151510_1_.func_75669_b());
         break;
      case 1:
         p_151510_0_.writeShort((Short)p_151510_1_.func_75669_b());
         break;
      case 2:
         p_151510_0_.writeInt((Integer)p_151510_1_.func_75669_b());
         break;
      case 3:
         p_151510_0_.writeFloat((Float)p_151510_1_.func_75669_b());
         break;
      case 4:
         p_151510_0_.func_180714_a((String)p_151510_1_.func_75669_b());
         break;
      case 5:
         ItemStack itemstack = (ItemStack)p_151510_1_.func_75669_b();
         p_151510_0_.func_150788_a(itemstack);
         break;
      case 6:
         BlockPos blockpos = (BlockPos)p_151510_1_.func_75669_b();
         p_151510_0_.writeInt(blockpos.func_177958_n());
         p_151510_0_.writeInt(blockpos.func_177956_o());
         p_151510_0_.writeInt(blockpos.func_177952_p());
         break;
      case 7:
         Rotations rotations = (Rotations)p_151510_1_.func_75669_b();
         p_151510_0_.writeFloat(rotations.func_179415_b());
         p_151510_0_.writeFloat(rotations.func_179416_c());
         p_151510_0_.writeFloat(rotations.func_179413_d());
      }

   }

   public static List<DataWatcher.WatchableObject> func_151508_b(PacketBuffer p_151508_0_) throws IOException {
      List<DataWatcher.WatchableObject> list = null;

      for(byte i = p_151508_0_.readByte(); i != 127; i = p_151508_0_.readByte()) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         int j = (i & 224) >> 5;
         int k = i & 31;
         DataWatcher.WatchableObject datawatcher$watchableobject = null;
         switch(j) {
         case 0:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.readByte());
            break;
         case 1:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.readShort());
            break;
         case 2:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.readInt());
            break;
         case 3:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.readFloat());
            break;
         case 4:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.func_150789_c(32767));
            break;
         case 5:
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, p_151508_0_.func_150791_c());
            break;
         case 6:
            int l = p_151508_0_.readInt();
            int i1 = p_151508_0_.readInt();
            int j1 = p_151508_0_.readInt();
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, new BlockPos(l, i1, j1));
            break;
         case 7:
            float f = p_151508_0_.readFloat();
            float f1 = p_151508_0_.readFloat();
            float f2 = p_151508_0_.readFloat();
            datawatcher$watchableobject = new DataWatcher.WatchableObject(j, k, new Rotations(f, f1, f2));
         }

         list.add(datawatcher$watchableobject);
      }

      return list;
   }

   public void func_75687_a(List<DataWatcher.WatchableObject> p_75687_1_) {
      this.field_75694_d.writeLock().lock();
      Iterator i$ = p_75687_1_.iterator();

      while(i$.hasNext()) {
         DataWatcher.WatchableObject datawatcher$watchableobject = (DataWatcher.WatchableObject)i$.next();
         DataWatcher.WatchableObject datawatcher$watchableobject1 = (DataWatcher.WatchableObject)this.field_75695_b.get(datawatcher$watchableobject.func_75672_a());
         if (datawatcher$watchableobject1 != null) {
            datawatcher$watchableobject1.func_75673_a(datawatcher$watchableobject.func_75669_b());
            this.field_151511_a.func_145781_i(datawatcher$watchableobject.func_75672_a());
         }
      }

      this.field_75694_d.writeLock().unlock();
      this.field_75696_c = true;
   }

   public boolean func_92085_d() {
      return this.field_92086_a;
   }

   public void func_111144_e() {
      this.field_75696_c = false;
   }

   static {
      field_75697_a.put(Byte.class, 0);
      field_75697_a.put(Short.class, 1);
      field_75697_a.put(Integer.class, 2);
      field_75697_a.put(Float.class, 3);
      field_75697_a.put(String.class, 4);
      field_75697_a.put(ItemStack.class, 5);
      field_75697_a.put(BlockPos.class, 6);
      field_75697_a.put(Rotations.class, 7);
   }

   public static class WatchableObject {
      private final int field_75678_a;
      private final int field_75676_b;
      private Object field_75677_c;
      private boolean field_75675_d;

      public WatchableObject(int p_i1603_1_, int p_i1603_2_, Object p_i1603_3_) {
         this.field_75676_b = p_i1603_2_;
         this.field_75677_c = p_i1603_3_;
         this.field_75678_a = p_i1603_1_;
         this.field_75675_d = true;
      }

      public int func_75672_a() {
         return this.field_75676_b;
      }

      public void func_75673_a(Object p_75673_1_) {
         this.field_75677_c = p_75673_1_;
      }

      public Object func_75669_b() {
         return this.field_75677_c;
      }

      public int func_75674_c() {
         return this.field_75678_a;
      }

      public boolean func_75670_d() {
         return this.field_75675_d;
      }

      public void func_75671_a(boolean p_75671_1_) {
         this.field_75675_d = p_75671_1_;
      }
   }
}
