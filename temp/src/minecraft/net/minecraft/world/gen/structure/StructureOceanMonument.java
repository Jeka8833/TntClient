package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureOceanMonument extends MapGenStructure {
   private int field_175800_f;
   private int field_175801_g;
   public static final List<BiomeGenBase> field_175802_d;
   private static final List<BiomeGenBase.SpawnListEntry> field_175803_h;

   public StructureOceanMonument() {
      this.field_175800_f = 32;
      this.field_175801_g = 5;
   }

   public StructureOceanMonument(Map<String, String> p_i45608_1_) {
      this();
      Iterator lvt_2_1_ = p_i45608_1_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, String> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (((String)lvt_3_1_.getKey()).equals("spacing")) {
            this.field_175800_f = MathHelper.func_82714_a((String)lvt_3_1_.getValue(), this.field_175800_f, 1);
         } else if (((String)lvt_3_1_.getKey()).equals("separation")) {
            this.field_175801_g = MathHelper.func_82714_a((String)lvt_3_1_.getValue(), this.field_175801_g, 1);
         }
      }

   }

   public String func_143025_a() {
      return "Monument";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int lvt_3_1_ = p_75047_1_;
      int lvt_4_1_ = p_75047_2_;
      if (p_75047_1_ < 0) {
         p_75047_1_ -= this.field_175800_f - 1;
      }

      if (p_75047_2_ < 0) {
         p_75047_2_ -= this.field_175800_f - 1;
      }

      int lvt_5_1_ = p_75047_1_ / this.field_175800_f;
      int lvt_6_1_ = p_75047_2_ / this.field_175800_f;
      Random lvt_7_1_ = this.field_75039_c.func_72843_D(lvt_5_1_, lvt_6_1_, 10387313);
      lvt_5_1_ *= this.field_175800_f;
      lvt_6_1_ *= this.field_175800_f;
      lvt_5_1_ += (lvt_7_1_.nextInt(this.field_175800_f - this.field_175801_g) + lvt_7_1_.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      lvt_6_1_ += (lvt_7_1_.nextInt(this.field_175800_f - this.field_175801_g) + lvt_7_1_.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      if (lvt_3_1_ == lvt_5_1_ && lvt_4_1_ == lvt_6_1_) {
         if (this.field_75039_c.func_72959_q().func_180300_a(new BlockPos(lvt_3_1_ * 16 + 8, 64, lvt_4_1_ * 16 + 8), (BiomeGenBase)null) != BiomeGenBase.field_150575_M) {
            return false;
         }

         boolean lvt_8_1_ = this.field_75039_c.func_72959_q().func_76940_a(lvt_3_1_ * 16 + 8, lvt_4_1_ * 16 + 8, 29, field_175802_d);
         if (lvt_8_1_) {
            return true;
         }
      }

      return false;
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new StructureOceanMonument.StartMonument(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public List<BiomeGenBase.SpawnListEntry> func_175799_b() {
      return field_175803_h;
   }

   static {
      field_175802_d = Arrays.asList(BiomeGenBase.field_76771_b, BiomeGenBase.field_150575_M, BiomeGenBase.field_76781_i, BiomeGenBase.field_76776_l, BiomeGenBase.field_76777_m);
      field_175803_h = Lists.newArrayList();
      field_175803_h.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
   }

   public static class StartMonument extends StructureStart {
      private Set<ChunkCoordIntPair> field_175791_c = Sets.newHashSet();
      private boolean field_175790_d;

      public StartMonument() {
      }

      public StartMonument(World p_i45607_1_, Random p_i45607_2_, int p_i45607_3_, int p_i45607_4_) {
         super(p_i45607_3_, p_i45607_4_);
         this.func_175789_b(p_i45607_1_, p_i45607_2_, p_i45607_3_, p_i45607_4_);
      }

      private void func_175789_b(World p_175789_1_, Random p_175789_2_, int p_175789_3_, int p_175789_4_) {
         p_175789_2_.setSeed(p_175789_1_.func_72905_C());
         long lvt_5_1_ = p_175789_2_.nextLong();
         long lvt_7_1_ = p_175789_2_.nextLong();
         long lvt_9_1_ = (long)p_175789_3_ * lvt_5_1_;
         long lvt_11_1_ = (long)p_175789_4_ * lvt_7_1_;
         p_175789_2_.setSeed(lvt_9_1_ ^ lvt_11_1_ ^ p_175789_1_.func_72905_C());
         int lvt_13_1_ = p_175789_3_ * 16 + 8 - 29;
         int lvt_14_1_ = p_175789_4_ * 16 + 8 - 29;
         EnumFacing lvt_15_1_ = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_175789_2_);
         this.field_75075_a.add(new StructureOceanMonumentPieces.MonumentBuilding(p_175789_2_, lvt_13_1_, lvt_14_1_, lvt_15_1_));
         this.func_75072_c();
         this.field_175790_d = true;
      }

      public void func_75068_a(World p_75068_1_, Random p_75068_2_, StructureBoundingBox p_75068_3_) {
         if (!this.field_175790_d) {
            this.field_75075_a.clear();
            this.func_175789_b(p_75068_1_, p_75068_2_, this.func_143019_e(), this.func_143018_f());
         }

         super.func_75068_a(p_75068_1_, p_75068_2_, p_75068_3_);
      }

      public boolean func_175788_a(ChunkCoordIntPair p_175788_1_) {
         return this.field_175791_c.contains(p_175788_1_) ? false : super.func_175788_a(p_175788_1_);
      }

      public void func_175787_b(ChunkCoordIntPair p_175787_1_) {
         super.func_175787_b(p_175787_1_);
         this.field_175791_c.add(p_175787_1_);
      }

      public void func_143022_a(NBTTagCompound p_143022_1_) {
         super.func_143022_a(p_143022_1_);
         NBTTagList lvt_2_1_ = new NBTTagList();
         Iterator lvt_3_1_ = this.field_175791_c.iterator();

         while(lvt_3_1_.hasNext()) {
            ChunkCoordIntPair lvt_4_1_ = (ChunkCoordIntPair)lvt_3_1_.next();
            NBTTagCompound lvt_5_1_ = new NBTTagCompound();
            lvt_5_1_.func_74768_a("X", lvt_4_1_.field_77276_a);
            lvt_5_1_.func_74768_a("Z", lvt_4_1_.field_77275_b);
            lvt_2_1_.func_74742_a(lvt_5_1_);
         }

         p_143022_1_.func_74782_a("Processed", lvt_2_1_);
      }

      public void func_143017_b(NBTTagCompound p_143017_1_) {
         super.func_143017_b(p_143017_1_);
         if (p_143017_1_.func_150297_b("Processed", 9)) {
            NBTTagList lvt_2_1_ = p_143017_1_.func_150295_c("Processed", 10);

            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
               NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
               this.field_175791_c.add(new ChunkCoordIntPair(lvt_4_1_.func_74762_e("X"), lvt_4_1_.func_74762_e("Z")));
            }
         }

      }
   }
}
