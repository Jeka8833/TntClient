package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenNetherBridge extends MapGenStructure {
   private List<BiomeGenBase.SpawnListEntry> field_75060_e = Lists.newArrayList();

   public MapGenNetherBridge() {
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   public String func_143025_a() {
      return "Fortress";
   }

   public List<BiomeGenBase.SpawnListEntry> func_75059_a() {
      return this.field_75060_e;
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int lvt_3_1_ = p_75047_1_ >> 4;
      int lvt_4_1_ = p_75047_2_ >> 4;
      this.field_75038_b.setSeed((long)(lvt_3_1_ ^ lvt_4_1_ << 4) ^ this.field_75039_c.func_72905_C());
      this.field_75038_b.nextInt();
      if (this.field_75038_b.nextInt(3) != 0) {
         return false;
      } else if (p_75047_1_ != (lvt_3_1_ << 4) + 4 + this.field_75038_b.nextInt(8)) {
         return false;
      } else {
         return p_75047_2_ == (lvt_4_1_ << 4) + 4 + this.field_75038_b.nextInt(8);
      }
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new MapGenNetherBridge.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World p_i2040_1_, Random p_i2040_2_, int p_i2040_3_, int p_i2040_4_) {
         super(p_i2040_3_, p_i2040_4_);
         StructureNetherBridgePieces.Start lvt_5_1_ = new StructureNetherBridgePieces.Start(p_i2040_2_, (p_i2040_3_ << 4) + 2, (p_i2040_4_ << 4) + 2);
         this.field_75075_a.add(lvt_5_1_);
         lvt_5_1_.func_74861_a(lvt_5_1_, this.field_75075_a, p_i2040_2_);
         List lvt_6_1_ = lvt_5_1_.field_74967_d;

         while(!lvt_6_1_.isEmpty()) {
            int lvt_7_1_ = p_i2040_2_.nextInt(lvt_6_1_.size());
            StructureComponent lvt_8_1_ = (StructureComponent)lvt_6_1_.remove(lvt_7_1_);
            lvt_8_1_.func_74861_a(lvt_5_1_, this.field_75075_a, p_i2040_2_);
         }

         this.func_75072_c();
         this.func_75070_a(p_i2040_1_, p_i2040_2_, 48, 70);
      }
   }
}
