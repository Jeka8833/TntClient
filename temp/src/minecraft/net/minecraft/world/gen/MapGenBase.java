package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenBase {
   protected int field_75040_a = 8;
   protected Random field_75038_b = new Random();
   protected World field_75039_c;

   public void func_175792_a(IChunkProvider p_175792_1_, World p_175792_2_, int p_175792_3_, int p_175792_4_, ChunkPrimer p_175792_5_) {
      int lvt_6_1_ = this.field_75040_a;
      this.field_75039_c = p_175792_2_;
      this.field_75038_b.setSeed(p_175792_2_.func_72905_C());
      long lvt_7_1_ = this.field_75038_b.nextLong();
      long lvt_9_1_ = this.field_75038_b.nextLong();

      for(int lvt_11_1_ = p_175792_3_ - lvt_6_1_; lvt_11_1_ <= p_175792_3_ + lvt_6_1_; ++lvt_11_1_) {
         for(int lvt_12_1_ = p_175792_4_ - lvt_6_1_; lvt_12_1_ <= p_175792_4_ + lvt_6_1_; ++lvt_12_1_) {
            long lvt_13_1_ = (long)lvt_11_1_ * lvt_7_1_;
            long lvt_15_1_ = (long)lvt_12_1_ * lvt_9_1_;
            this.field_75038_b.setSeed(lvt_13_1_ ^ lvt_15_1_ ^ p_175792_2_.func_72905_C());
            this.func_180701_a(p_175792_2_, lvt_11_1_, lvt_12_1_, p_175792_3_, p_175792_4_, p_175792_5_);
         }
      }

   }

   protected void func_180701_a(World p_180701_1_, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
   }
}
