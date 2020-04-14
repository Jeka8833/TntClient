package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.world.WorldSavedData;

public class MapStorage {
   private ISaveHandler field_75751_a;
   protected Map<String, WorldSavedData> field_75749_b = Maps.newHashMap();
   private List<WorldSavedData> field_75750_c = Lists.newArrayList();
   private Map<String, Short> field_75748_d = Maps.newHashMap();

   public MapStorage(ISaveHandler p_i2162_1_) {
      this.field_75751_a = p_i2162_1_;
      this.func_75746_b();
   }

   public WorldSavedData func_75742_a(Class<? extends WorldSavedData> p_75742_1_, String p_75742_2_) {
      WorldSavedData lvt_3_1_ = (WorldSavedData)this.field_75749_b.get(p_75742_2_);
      if (lvt_3_1_ != null) {
         return lvt_3_1_;
      } else {
         if (this.field_75751_a != null) {
            try {
               File lvt_4_1_ = this.field_75751_a.func_75758_b(p_75742_2_);
               if (lvt_4_1_ != null && lvt_4_1_.exists()) {
                  try {
                     lvt_3_1_ = (WorldSavedData)p_75742_1_.getConstructor(String.class).newInstance(p_75742_2_);
                  } catch (Exception var7) {
                     throw new RuntimeException("Failed to instantiate " + p_75742_1_.toString(), var7);
                  }

                  FileInputStream lvt_5_2_ = new FileInputStream(lvt_4_1_);
                  NBTTagCompound lvt_6_1_ = CompressedStreamTools.func_74796_a(lvt_5_2_);
                  lvt_5_2_.close();
                  lvt_3_1_.func_76184_a(lvt_6_1_.func_74775_l("data"));
               }
            } catch (Exception var8) {
               var8.printStackTrace();
            }
         }

         if (lvt_3_1_ != null) {
            this.field_75749_b.put(p_75742_2_, lvt_3_1_);
            this.field_75750_c.add(lvt_3_1_);
         }

         return lvt_3_1_;
      }
   }

   public void func_75745_a(String p_75745_1_, WorldSavedData p_75745_2_) {
      if (this.field_75749_b.containsKey(p_75745_1_)) {
         this.field_75750_c.remove(this.field_75749_b.remove(p_75745_1_));
      }

      this.field_75749_b.put(p_75745_1_, p_75745_2_);
      this.field_75750_c.add(p_75745_2_);
   }

   public void func_75744_a() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75750_c.size(); ++lvt_1_1_) {
         WorldSavedData lvt_2_1_ = (WorldSavedData)this.field_75750_c.get(lvt_1_1_);
         if (lvt_2_1_.func_76188_b()) {
            this.func_75747_a(lvt_2_1_);
            lvt_2_1_.func_76186_a(false);
         }
      }

   }

   private void func_75747_a(WorldSavedData p_75747_1_) {
      if (this.field_75751_a != null) {
         try {
            File lvt_2_1_ = this.field_75751_a.func_75758_b(p_75747_1_.field_76190_i);
            if (lvt_2_1_ != null) {
               NBTTagCompound lvt_3_1_ = new NBTTagCompound();
               p_75747_1_.func_76187_b(lvt_3_1_);
               NBTTagCompound lvt_4_1_ = new NBTTagCompound();
               lvt_4_1_.func_74782_a("data", lvt_3_1_);
               FileOutputStream lvt_5_1_ = new FileOutputStream(lvt_2_1_);
               CompressedStreamTools.func_74799_a(lvt_4_1_, lvt_5_1_);
               lvt_5_1_.close();
            }
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }
   }

   private void func_75746_b() {
      try {
         this.field_75748_d.clear();
         if (this.field_75751_a == null) {
            return;
         }

         File lvt_1_1_ = this.field_75751_a.func_75758_b("idcounts");
         if (lvt_1_1_ != null && lvt_1_1_.exists()) {
            DataInputStream lvt_2_1_ = new DataInputStream(new FileInputStream(lvt_1_1_));
            NBTTagCompound lvt_3_1_ = CompressedStreamTools.func_74794_a(lvt_2_1_);
            lvt_2_1_.close();
            Iterator lvt_4_1_ = lvt_3_1_.func_150296_c().iterator();

            while(lvt_4_1_.hasNext()) {
               String lvt_5_1_ = (String)lvt_4_1_.next();
               NBTBase lvt_6_1_ = lvt_3_1_.func_74781_a(lvt_5_1_);
               if (lvt_6_1_ instanceof NBTTagShort) {
                  NBTTagShort lvt_7_1_ = (NBTTagShort)lvt_6_1_;
                  short lvt_9_1_ = lvt_7_1_.func_150289_e();
                  this.field_75748_d.put(lvt_5_1_, lvt_9_1_);
               }
            }
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public int func_75743_a(String p_75743_1_) {
      Short lvt_2_1_ = (Short)this.field_75748_d.get(p_75743_1_);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = Short.valueOf((short)0);
      } else {
         lvt_2_1_ = (short)(lvt_2_1_ + 1);
      }

      this.field_75748_d.put(p_75743_1_, lvt_2_1_);
      if (this.field_75751_a == null) {
         return lvt_2_1_;
      } else {
         try {
            File lvt_3_1_ = this.field_75751_a.func_75758_b("idcounts");
            if (lvt_3_1_ != null) {
               NBTTagCompound lvt_4_1_ = new NBTTagCompound();
               Iterator lvt_5_1_ = this.field_75748_d.keySet().iterator();

               while(lvt_5_1_.hasNext()) {
                  String lvt_6_1_ = (String)lvt_5_1_.next();
                  short lvt_7_1_ = (Short)this.field_75748_d.get(lvt_6_1_);
                  lvt_4_1_.func_74777_a(lvt_6_1_, lvt_7_1_);
               }

               DataOutputStream lvt_5_2_ = new DataOutputStream(new FileOutputStream(lvt_3_1_));
               CompressedStreamTools.func_74800_a(lvt_4_1_, lvt_5_2_);
               lvt_5_2_.close();
            }
         } catch (Exception var8) {
            var8.printStackTrace();
         }

         return lvt_2_1_;
      }
   }
}
