package net.minecraft.village;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;

public class VillageCollection extends WorldSavedData {
   private World field_75556_a;
   private final List<BlockPos> field_75554_b = Lists.newArrayList();
   private final List<VillageDoorInfo> field_75555_c = Lists.newArrayList();
   private final List<Village> field_75552_d = Lists.newArrayList();
   private int field_75553_e;

   public VillageCollection(String p_i1677_1_) {
      super(p_i1677_1_);
   }

   public VillageCollection(World p_i1678_1_) {
      super(func_176062_a(p_i1678_1_.field_73011_w));
      this.field_75556_a = p_i1678_1_;
      this.func_76185_a();
   }

   public void func_82566_a(World p_82566_1_) {
      this.field_75556_a = p_82566_1_;
      Iterator lvt_2_1_ = this.field_75552_d.iterator();

      while(lvt_2_1_.hasNext()) {
         Village lvt_3_1_ = (Village)lvt_2_1_.next();
         lvt_3_1_.func_82691_a(p_82566_1_);
      }

   }

   public void func_176060_a(BlockPos p_176060_1_) {
      if (this.field_75554_b.size() <= 64) {
         if (!this.func_176057_e(p_176060_1_)) {
            this.field_75554_b.add(p_176060_1_);
         }

      }
   }

   public void func_75544_a() {
      ++this.field_75553_e;
      Iterator lvt_1_1_ = this.field_75552_d.iterator();

      while(lvt_1_1_.hasNext()) {
         Village lvt_2_1_ = (Village)lvt_1_1_.next();
         lvt_2_1_.func_75560_a(this.field_75553_e);
      }

      this.func_75549_c();
      this.func_75543_d();
      this.func_75545_e();
      if (this.field_75553_e % 400 == 0) {
         this.func_76185_a();
      }

   }

   private void func_75549_c() {
      Iterator lvt_1_1_ = this.field_75552_d.iterator();

      while(lvt_1_1_.hasNext()) {
         Village lvt_2_1_ = (Village)lvt_1_1_.next();
         if (lvt_2_1_.func_75566_g()) {
            lvt_1_1_.remove();
            this.func_76185_a();
         }
      }

   }

   public List<Village> func_75540_b() {
      return this.field_75552_d;
   }

   public Village func_176056_a(BlockPos p_176056_1_, int p_176056_2_) {
      Village lvt_3_1_ = null;
      double lvt_4_1_ = 3.4028234663852886E38D;
      Iterator lvt_6_1_ = this.field_75552_d.iterator();

      while(lvt_6_1_.hasNext()) {
         Village lvt_7_1_ = (Village)lvt_6_1_.next();
         double lvt_8_1_ = lvt_7_1_.func_180608_a().func_177951_i(p_176056_1_);
         if (lvt_8_1_ < lvt_4_1_) {
            float lvt_10_1_ = (float)(p_176056_2_ + lvt_7_1_.func_75568_b());
            if (lvt_8_1_ <= (double)(lvt_10_1_ * lvt_10_1_)) {
               lvt_3_1_ = lvt_7_1_;
               lvt_4_1_ = lvt_8_1_;
            }
         }
      }

      return lvt_3_1_;
   }

   private void func_75543_d() {
      if (!this.field_75554_b.isEmpty()) {
         this.func_180609_b((BlockPos)this.field_75554_b.remove(0));
      }
   }

   private void func_75545_e() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75555_c.size(); ++lvt_1_1_) {
         VillageDoorInfo lvt_2_1_ = (VillageDoorInfo)this.field_75555_c.get(lvt_1_1_);
         Village lvt_3_1_ = this.func_176056_a(lvt_2_1_.func_179852_d(), 32);
         if (lvt_3_1_ == null) {
            lvt_3_1_ = new Village(this.field_75556_a);
            this.field_75552_d.add(lvt_3_1_);
            this.func_76185_a();
         }

         lvt_3_1_.func_75576_a(lvt_2_1_);
      }

      this.field_75555_c.clear();
   }

   private void func_180609_b(BlockPos p_180609_1_) {
      int lvt_2_1_ = 16;
      int lvt_3_1_ = 4;
      int lvt_4_1_ = 16;

      for(int lvt_5_1_ = -lvt_2_1_; lvt_5_1_ < lvt_2_1_; ++lvt_5_1_) {
         for(int lvt_6_1_ = -lvt_3_1_; lvt_6_1_ < lvt_3_1_; ++lvt_6_1_) {
            for(int lvt_7_1_ = -lvt_4_1_; lvt_7_1_ < lvt_4_1_; ++lvt_7_1_) {
               BlockPos lvt_8_1_ = p_180609_1_.func_177982_a(lvt_5_1_, lvt_6_1_, lvt_7_1_);
               if (this.func_176058_f(lvt_8_1_)) {
                  VillageDoorInfo lvt_9_1_ = this.func_176055_c(lvt_8_1_);
                  if (lvt_9_1_ == null) {
                     this.func_176059_d(lvt_8_1_);
                  } else {
                     lvt_9_1_.func_179849_a(this.field_75553_e);
                  }
               }
            }
         }
      }

   }

   private VillageDoorInfo func_176055_c(BlockPos p_176055_1_) {
      Iterator lvt_2_2_ = this.field_75555_c.iterator();

      VillageDoorInfo lvt_3_1_;
      do {
         if (!lvt_2_2_.hasNext()) {
            lvt_2_2_ = this.field_75552_d.iterator();

            VillageDoorInfo lvt_4_1_;
            do {
               if (!lvt_2_2_.hasNext()) {
                  return null;
               }

               Village lvt_3_2_ = (Village)lvt_2_2_.next();
               lvt_4_1_ = lvt_3_2_.func_179864_e(p_176055_1_);
            } while(lvt_4_1_ == null);

            return lvt_4_1_;
         }

         lvt_3_1_ = (VillageDoorInfo)lvt_2_2_.next();
      } while(lvt_3_1_.func_179852_d().func_177958_n() != p_176055_1_.func_177958_n() || lvt_3_1_.func_179852_d().func_177952_p() != p_176055_1_.func_177952_p() || Math.abs(lvt_3_1_.func_179852_d().func_177956_o() - p_176055_1_.func_177956_o()) > 1);

      return lvt_3_1_;
   }

   private void func_176059_d(BlockPos p_176059_1_) {
      EnumFacing lvt_2_1_ = BlockDoor.func_176517_h(this.field_75556_a, p_176059_1_);
      EnumFacing lvt_3_1_ = lvt_2_1_.func_176734_d();
      int lvt_4_1_ = this.func_176061_a(p_176059_1_, lvt_2_1_, 5);
      int lvt_5_1_ = this.func_176061_a(p_176059_1_, lvt_3_1_, lvt_4_1_ + 1);
      if (lvt_4_1_ != lvt_5_1_) {
         this.field_75555_c.add(new VillageDoorInfo(p_176059_1_, lvt_4_1_ < lvt_5_1_ ? lvt_2_1_ : lvt_3_1_, this.field_75553_e));
      }

   }

   private int func_176061_a(BlockPos p_176061_1_, EnumFacing p_176061_2_, int p_176061_3_) {
      int lvt_4_1_ = 0;

      for(int lvt_5_1_ = 1; lvt_5_1_ <= 5; ++lvt_5_1_) {
         if (this.field_75556_a.func_175678_i(p_176061_1_.func_177967_a(p_176061_2_, lvt_5_1_))) {
            ++lvt_4_1_;
            if (lvt_4_1_ >= p_176061_3_) {
               return lvt_4_1_;
            }
         }
      }

      return lvt_4_1_;
   }

   private boolean func_176057_e(BlockPos p_176057_1_) {
      Iterator lvt_2_1_ = this.field_75554_b.iterator();

      BlockPos lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            return false;
         }

         lvt_3_1_ = (BlockPos)lvt_2_1_.next();
      } while(!lvt_3_1_.equals(p_176057_1_));

      return true;
   }

   private boolean func_176058_f(BlockPos p_176058_1_) {
      Block lvt_2_1_ = this.field_75556_a.func_180495_p(p_176058_1_).func_177230_c();
      if (lvt_2_1_ instanceof BlockDoor) {
         return lvt_2_1_.func_149688_o() == Material.field_151575_d;
      } else {
         return false;
      }
   }

   public void func_76184_a(NBTTagCompound p_76184_1_) {
      this.field_75553_e = p_76184_1_.func_74762_e("Tick");
      NBTTagList lvt_2_1_ = p_76184_1_.func_150295_c("Villages", 10);

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
         Village lvt_5_1_ = new Village();
         lvt_5_1_.func_82690_a(lvt_4_1_);
         this.field_75552_d.add(lvt_5_1_);
      }

   }

   public void func_76187_b(NBTTagCompound p_76187_1_) {
      p_76187_1_.func_74768_a("Tick", this.field_75553_e);
      NBTTagList lvt_2_1_ = new NBTTagList();
      Iterator lvt_3_1_ = this.field_75552_d.iterator();

      while(lvt_3_1_.hasNext()) {
         Village lvt_4_1_ = (Village)lvt_3_1_.next();
         NBTTagCompound lvt_5_1_ = new NBTTagCompound();
         lvt_4_1_.func_82689_b(lvt_5_1_);
         lvt_2_1_.func_74742_a(lvt_5_1_);
      }

      p_76187_1_.func_74782_a("Villages", lvt_2_1_);
   }

   public static String func_176062_a(WorldProvider p_176062_0_) {
      return "villages" + p_176062_0_.func_177498_l();
   }
}
