package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public abstract class StructureStart {
   protected LinkedList<StructureComponent> field_75075_a = new LinkedList();
   protected StructureBoundingBox field_75074_b;
   private int field_143024_c;
   private int field_143023_d;

   public StructureStart() {
   }

   public StructureStart(int p_i43002_1_, int p_i43002_2_) {
      this.field_143024_c = p_i43002_1_;
      this.field_143023_d = p_i43002_2_;
   }

   public StructureBoundingBox func_75071_a() {
      return this.field_75074_b;
   }

   public LinkedList<StructureComponent> func_75073_b() {
      return this.field_75075_a;
   }

   public void func_75068_a(World p_75068_1_, Random p_75068_2_, StructureBoundingBox p_75068_3_) {
      Iterator lvt_4_1_ = this.field_75075_a.iterator();

      while(lvt_4_1_.hasNext()) {
         StructureComponent lvt_5_1_ = (StructureComponent)lvt_4_1_.next();
         if (lvt_5_1_.func_74874_b().func_78884_a(p_75068_3_) && !lvt_5_1_.func_74875_a(p_75068_1_, p_75068_2_, p_75068_3_)) {
            lvt_4_1_.remove();
         }
      }

   }

   protected void func_75072_c() {
      this.field_75074_b = StructureBoundingBox.func_78887_a();
      Iterator lvt_1_1_ = this.field_75075_a.iterator();

      while(lvt_1_1_.hasNext()) {
         StructureComponent lvt_2_1_ = (StructureComponent)lvt_1_1_.next();
         this.field_75074_b.func_78888_b(lvt_2_1_.func_74874_b());
      }

   }

   public NBTTagCompound func_143021_a(int p_143021_1_, int p_143021_2_) {
      NBTTagCompound lvt_3_1_ = new NBTTagCompound();
      lvt_3_1_.func_74778_a("id", MapGenStructureIO.func_143033_a(this));
      lvt_3_1_.func_74768_a("ChunkX", p_143021_1_);
      lvt_3_1_.func_74768_a("ChunkZ", p_143021_2_);
      lvt_3_1_.func_74782_a("BB", this.field_75074_b.func_151535_h());
      NBTTagList lvt_4_1_ = new NBTTagList();
      Iterator lvt_5_1_ = this.field_75075_a.iterator();

      while(lvt_5_1_.hasNext()) {
         StructureComponent lvt_6_1_ = (StructureComponent)lvt_5_1_.next();
         lvt_4_1_.func_74742_a(lvt_6_1_.func_143010_b());
      }

      lvt_3_1_.func_74782_a("Children", lvt_4_1_);
      this.func_143022_a(lvt_3_1_);
      return lvt_3_1_;
   }

   public void func_143022_a(NBTTagCompound p_143022_1_) {
   }

   public void func_143020_a(World p_143020_1_, NBTTagCompound p_143020_2_) {
      this.field_143024_c = p_143020_2_.func_74762_e("ChunkX");
      this.field_143023_d = p_143020_2_.func_74762_e("ChunkZ");
      if (p_143020_2_.func_74764_b("BB")) {
         this.field_75074_b = new StructureBoundingBox(p_143020_2_.func_74759_k("BB"));
      }

      NBTTagList lvt_3_1_ = p_143020_2_.func_150295_c("Children", 10);

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.func_74745_c(); ++lvt_4_1_) {
         this.field_75075_a.add(MapGenStructureIO.func_143032_b(lvt_3_1_.func_150305_b(lvt_4_1_), p_143020_1_));
      }

      this.func_143017_b(p_143020_2_);
   }

   public void func_143017_b(NBTTagCompound p_143017_1_) {
   }

   protected void func_75067_a(World p_75067_1_, Random p_75067_2_, int p_75067_3_) {
      int lvt_4_1_ = p_75067_1_.func_181545_F() - p_75067_3_;
      int lvt_5_1_ = this.field_75074_b.func_78882_c() + 1;
      if (lvt_5_1_ < lvt_4_1_) {
         lvt_5_1_ += p_75067_2_.nextInt(lvt_4_1_ - lvt_5_1_);
      }

      int lvt_6_1_ = lvt_5_1_ - this.field_75074_b.field_78894_e;
      this.field_75074_b.func_78886_a(0, lvt_6_1_, 0);
      Iterator lvt_7_1_ = this.field_75075_a.iterator();

      while(lvt_7_1_.hasNext()) {
         StructureComponent lvt_8_1_ = (StructureComponent)lvt_7_1_.next();
         lvt_8_1_.func_181138_a(0, lvt_6_1_, 0);
      }

   }

   protected void func_75070_a(World p_75070_1_, Random p_75070_2_, int p_75070_3_, int p_75070_4_) {
      int lvt_5_1_ = p_75070_4_ - p_75070_3_ + 1 - this.field_75074_b.func_78882_c();
      int lvt_6_1_ = true;
      int lvt_6_1_;
      if (lvt_5_1_ > 1) {
         lvt_6_1_ = p_75070_3_ + p_75070_2_.nextInt(lvt_5_1_);
      } else {
         lvt_6_1_ = p_75070_3_;
      }

      int lvt_7_1_ = lvt_6_1_ - this.field_75074_b.field_78895_b;
      this.field_75074_b.func_78886_a(0, lvt_7_1_, 0);
      Iterator lvt_8_1_ = this.field_75075_a.iterator();

      while(lvt_8_1_.hasNext()) {
         StructureComponent lvt_9_1_ = (StructureComponent)lvt_8_1_.next();
         lvt_9_1_.func_181138_a(0, lvt_7_1_, 0);
      }

   }

   public boolean func_75069_d() {
      return true;
   }

   public boolean func_175788_a(ChunkCoordIntPair p_175788_1_) {
      return true;
   }

   public void func_175787_b(ChunkCoordIntPair p_175787_1_) {
   }

   public int func_143019_e() {
      return this.field_143024_c;
   }

   public int func_143018_f() {
      return this.field_143023_d;
   }
}
