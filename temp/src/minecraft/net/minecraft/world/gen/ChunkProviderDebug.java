package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderDebug implements IChunkProvider {
   private static final List<IBlockState> field_177464_a = Lists.newArrayList();
   private static final int field_177462_b;
   private static final int field_181039_c;
   private final World field_177463_c;

   public ChunkProviderDebug(World p_i45638_1_) {
      this.field_177463_c = p_i45638_1_;
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      ChunkPrimer lvt_3_1_ = new ChunkPrimer();

      int lvt_7_2_;
      for(int lvt_4_1_ = 0; lvt_4_1_ < 16; ++lvt_4_1_) {
         for(int lvt_5_1_ = 0; lvt_5_1_ < 16; ++lvt_5_1_) {
            int lvt_6_1_ = p_73154_1_ * 16 + lvt_4_1_;
            lvt_7_2_ = p_73154_2_ * 16 + lvt_5_1_;
            lvt_3_1_.func_177855_a(lvt_4_1_, 60, lvt_5_1_, Blocks.field_180401_cv.func_176223_P());
            IBlockState lvt_8_1_ = func_177461_b(lvt_6_1_, lvt_7_2_);
            if (lvt_8_1_ != null) {
               lvt_3_1_.func_177855_a(lvt_4_1_, 70, lvt_5_1_, lvt_8_1_);
            }
         }
      }

      Chunk lvt_4_2_ = new Chunk(this.field_177463_c, lvt_3_1_, p_73154_1_, p_73154_2_);
      lvt_4_2_.func_76603_b();
      BiomeGenBase[] lvt_5_2_ = this.field_177463_c.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] lvt_6_2_ = lvt_4_2_.func_76605_m();

      for(lvt_7_2_ = 0; lvt_7_2_ < lvt_6_2_.length; ++lvt_7_2_) {
         lvt_6_2_[lvt_7_2_] = (byte)lvt_5_2_[lvt_7_2_].field_76756_M;
      }

      lvt_4_2_.func_76603_b();
      return lvt_4_2_;
   }

   public static IBlockState func_177461_b(int p_177461_0_, int p_177461_1_) {
      IBlockState lvt_2_1_ = null;
      if (p_177461_0_ > 0 && p_177461_1_ > 0 && p_177461_0_ % 2 != 0 && p_177461_1_ % 2 != 0) {
         p_177461_0_ /= 2;
         p_177461_1_ /= 2;
         if (p_177461_0_ <= field_177462_b && p_177461_1_ <= field_181039_c) {
            int lvt_3_1_ = MathHelper.func_76130_a(p_177461_0_ * field_177462_b + p_177461_1_);
            if (lvt_3_1_ < field_177464_a.size()) {
               lvt_2_1_ = (IBlockState)field_177464_a.get(lvt_3_1_);
            }
         }
      }

      return lvt_2_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      return false;
   }

   public boolean func_73157_c() {
      return true;
   }

   public String func_73148_d() {
      return "DebugLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      BiomeGenBase lvt_3_1_ = this.field_177463_c.func_180494_b(p_177458_2_);
      return lvt_3_1_.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }

   static {
      Iterator lvt_0_1_ = Block.field_149771_c.iterator();

      while(lvt_0_1_.hasNext()) {
         Block lvt_1_1_ = (Block)lvt_0_1_.next();
         field_177464_a.addAll(lvt_1_1_.func_176194_O().func_177619_a());
      }

      field_177462_b = MathHelper.func_76123_f(MathHelper.func_76129_c((float)field_177464_a.size()));
      field_181039_c = MathHelper.func_76123_f((float)field_177464_a.size() / (float)field_177462_b);
   }
}
