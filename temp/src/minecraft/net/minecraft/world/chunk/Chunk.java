package net.minecraft.world.chunk;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.ChunkProviderDebug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chunk {
   private static final Logger field_150817_t = LogManager.getLogger();
   private final ExtendedBlockStorage[] field_76652_q;
   private final byte[] field_76651_r;
   private final int[] field_76638_b;
   private final boolean[] field_76639_c;
   private boolean field_76636_d;
   private final World field_76637_e;
   private final int[] field_76634_f;
   public final int field_76635_g;
   public final int field_76647_h;
   private boolean field_76650_s;
   private final Map<BlockPos, TileEntity> field_150816_i;
   private final ClassInheritanceMultiMap<Entity>[] field_76645_j;
   private boolean field_76646_k;
   private boolean field_150814_l;
   private boolean field_150815_m;
   private boolean field_76643_l;
   private boolean field_76644_m;
   private long field_76641_n;
   private int field_82912_p;
   private long field_111204_q;
   private int field_76649_t;
   private ConcurrentLinkedQueue<BlockPos> field_177447_w;

   public Chunk(World p_i1995_1_, int p_i1995_2_, int p_i1995_3_) {
      this.field_76652_q = new ExtendedBlockStorage[16];
      this.field_76651_r = new byte[256];
      this.field_76638_b = new int[256];
      this.field_76639_c = new boolean[256];
      this.field_150816_i = Maps.newHashMap();
      this.field_76649_t = 4096;
      this.field_177447_w = Queues.newConcurrentLinkedQueue();
      this.field_76645_j = (ClassInheritanceMultiMap[])(new ClassInheritanceMultiMap[16]);
      this.field_76637_e = p_i1995_1_;
      this.field_76635_g = p_i1995_2_;
      this.field_76647_h = p_i1995_3_;
      this.field_76634_f = new int[256];

      for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_76645_j.length; ++lvt_4_1_) {
         this.field_76645_j[lvt_4_1_] = new ClassInheritanceMultiMap(Entity.class);
      }

      Arrays.fill(this.field_76638_b, -999);
      Arrays.fill(this.field_76651_r, (byte)-1);
   }

   public Chunk(World p_i45645_1_, ChunkPrimer p_i45645_2_, int p_i45645_3_, int p_i45645_4_) {
      this(p_i45645_1_, p_i45645_3_, p_i45645_4_);
      int lvt_5_1_ = 256;
      boolean lvt_6_1_ = !p_i45645_1_.field_73011_w.func_177495_o();

      for(int lvt_7_1_ = 0; lvt_7_1_ < 16; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < 16; ++lvt_8_1_) {
            for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_5_1_; ++lvt_9_1_) {
               int lvt_10_1_ = lvt_7_1_ * lvt_5_1_ * 16 | lvt_8_1_ * lvt_5_1_ | lvt_9_1_;
               IBlockState lvt_11_1_ = p_i45645_2_.func_177858_a(lvt_10_1_);
               if (lvt_11_1_.func_177230_c().func_149688_o() != Material.field_151579_a) {
                  int lvt_12_1_ = lvt_9_1_ >> 4;
                  if (this.field_76652_q[lvt_12_1_] == null) {
                     this.field_76652_q[lvt_12_1_] = new ExtendedBlockStorage(lvt_12_1_ << 4, lvt_6_1_);
                  }

                  this.field_76652_q[lvt_12_1_].func_177484_a(lvt_7_1_, lvt_9_1_ & 15, lvt_8_1_, lvt_11_1_);
               }
            }
         }
      }

   }

   public boolean func_76600_a(int p_76600_1_, int p_76600_2_) {
      return p_76600_1_ == this.field_76635_g && p_76600_2_ == this.field_76647_h;
   }

   public int func_177433_f(BlockPos p_177433_1_) {
      return this.func_76611_b(p_177433_1_.func_177958_n() & 15, p_177433_1_.func_177952_p() & 15);
   }

   public int func_76611_b(int p_76611_1_, int p_76611_2_) {
      return this.field_76634_f[p_76611_2_ << 4 | p_76611_1_];
   }

   public int func_76625_h() {
      for(int lvt_1_1_ = this.field_76652_q.length - 1; lvt_1_1_ >= 0; --lvt_1_1_) {
         if (this.field_76652_q[lvt_1_1_] != null) {
            return this.field_76652_q[lvt_1_1_].func_76662_d();
         }
      }

      return 0;
   }

   public ExtendedBlockStorage[] func_76587_i() {
      return this.field_76652_q;
   }

   protected void func_76590_a() {
      int lvt_1_1_ = this.func_76625_h();
      this.field_82912_p = Integer.MAX_VALUE;

      for(int lvt_2_1_ = 0; lvt_2_1_ < 16; ++lvt_2_1_) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
            this.field_76638_b[lvt_2_1_ + (lvt_3_1_ << 4)] = -999;

            for(int lvt_4_1_ = lvt_1_1_ + 16; lvt_4_1_ > 0; --lvt_4_1_) {
               Block lvt_5_1_ = this.func_150810_a(lvt_2_1_, lvt_4_1_ - 1, lvt_3_1_);
               if (lvt_5_1_.func_149717_k() != 0) {
                  this.field_76634_f[lvt_3_1_ << 4 | lvt_2_1_] = lvt_4_1_;
                  if (lvt_4_1_ < this.field_82912_p) {
                     this.field_82912_p = lvt_4_1_;
                  }
                  break;
               }
            }
         }
      }

      this.field_76643_l = true;
   }

   public void func_76603_b() {
      int lvt_1_1_ = this.func_76625_h();
      this.field_82912_p = Integer.MAX_VALUE;

      for(int lvt_2_1_ = 0; lvt_2_1_ < 16; ++lvt_2_1_) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
            this.field_76638_b[lvt_2_1_ + (lvt_3_1_ << 4)] = -999;

            int lvt_4_2_;
            for(lvt_4_2_ = lvt_1_1_ + 16; lvt_4_2_ > 0; --lvt_4_2_) {
               if (this.func_150808_b(lvt_2_1_, lvt_4_2_ - 1, lvt_3_1_) != 0) {
                  this.field_76634_f[lvt_3_1_ << 4 | lvt_2_1_] = lvt_4_2_;
                  if (lvt_4_2_ < this.field_82912_p) {
                     this.field_82912_p = lvt_4_2_;
                  }
                  break;
               }
            }

            if (!this.field_76637_e.field_73011_w.func_177495_o()) {
               lvt_4_2_ = 15;
               int lvt_5_1_ = lvt_1_1_ + 16 - 1;

               do {
                  int lvt_6_1_ = this.func_150808_b(lvt_2_1_, lvt_5_1_, lvt_3_1_);
                  if (lvt_6_1_ == 0 && lvt_4_2_ != 15) {
                     lvt_6_1_ = 1;
                  }

                  lvt_4_2_ -= lvt_6_1_;
                  if (lvt_4_2_ > 0) {
                     ExtendedBlockStorage lvt_7_1_ = this.field_76652_q[lvt_5_1_ >> 4];
                     if (lvt_7_1_ != null) {
                        lvt_7_1_.func_76657_c(lvt_2_1_, lvt_5_1_ & 15, lvt_3_1_, lvt_4_2_);
                        this.field_76637_e.func_175679_n(new BlockPos((this.field_76635_g << 4) + lvt_2_1_, lvt_5_1_, (this.field_76647_h << 4) + lvt_3_1_));
                     }
                  }

                  --lvt_5_1_;
               } while(lvt_5_1_ > 0 && lvt_4_2_ > 0);
            }
         }
      }

      this.field_76643_l = true;
   }

   private void func_76595_e(int p_76595_1_, int p_76595_2_) {
      this.field_76639_c[p_76595_1_ + p_76595_2_ * 16] = true;
      this.field_76650_s = true;
   }

   private void func_150803_c(boolean p_150803_1_) {
      this.field_76637_e.field_72984_F.func_76320_a("recheckGaps");
      if (this.field_76637_e.func_175697_a(new BlockPos(this.field_76635_g * 16 + 8, 0, this.field_76647_h * 16 + 8), 16)) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < 16; ++lvt_2_1_) {
            for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
               if (this.field_76639_c[lvt_2_1_ + lvt_3_1_ * 16]) {
                  this.field_76639_c[lvt_2_1_ + lvt_3_1_ * 16] = false;
                  int lvt_4_1_ = this.func_76611_b(lvt_2_1_, lvt_3_1_);
                  int lvt_5_1_ = this.field_76635_g * 16 + lvt_2_1_;
                  int lvt_6_1_ = this.field_76647_h * 16 + lvt_3_1_;
                  int lvt_7_1_ = Integer.MAX_VALUE;

                  Iterator lvt_8_2_;
                  EnumFacing lvt_9_2_;
                  for(lvt_8_2_ = EnumFacing.Plane.HORIZONTAL.iterator(); lvt_8_2_.hasNext(); lvt_7_1_ = Math.min(lvt_7_1_, this.field_76637_e.func_82734_g(lvt_5_1_ + lvt_9_2_.func_82601_c(), lvt_6_1_ + lvt_9_2_.func_82599_e()))) {
                     lvt_9_2_ = (EnumFacing)lvt_8_2_.next();
                  }

                  this.func_76599_g(lvt_5_1_, lvt_6_1_, lvt_7_1_);
                  lvt_8_2_ = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(lvt_8_2_.hasNext()) {
                     lvt_9_2_ = (EnumFacing)lvt_8_2_.next();
                     this.func_76599_g(lvt_5_1_ + lvt_9_2_.func_82601_c(), lvt_6_1_ + lvt_9_2_.func_82599_e(), lvt_4_1_);
                  }

                  if (p_150803_1_) {
                     this.field_76637_e.field_72984_F.func_76319_b();
                     return;
                  }
               }
            }
         }

         this.field_76650_s = false;
      }

      this.field_76637_e.field_72984_F.func_76319_b();
   }

   private void func_76599_g(int p_76599_1_, int p_76599_2_, int p_76599_3_) {
      int lvt_4_1_ = this.field_76637_e.func_175645_m(new BlockPos(p_76599_1_, 0, p_76599_2_)).func_177956_o();
      if (lvt_4_1_ > p_76599_3_) {
         this.func_76609_d(p_76599_1_, p_76599_2_, p_76599_3_, lvt_4_1_ + 1);
      } else if (lvt_4_1_ < p_76599_3_) {
         this.func_76609_d(p_76599_1_, p_76599_2_, lvt_4_1_, p_76599_3_ + 1);
      }

   }

   private void func_76609_d(int p_76609_1_, int p_76609_2_, int p_76609_3_, int p_76609_4_) {
      if (p_76609_4_ > p_76609_3_ && this.field_76637_e.func_175697_a(new BlockPos(p_76609_1_, 0, p_76609_2_), 16)) {
         for(int lvt_5_1_ = p_76609_3_; lvt_5_1_ < p_76609_4_; ++lvt_5_1_) {
            this.field_76637_e.func_180500_c(EnumSkyBlock.SKY, new BlockPos(p_76609_1_, lvt_5_1_, p_76609_2_));
         }

         this.field_76643_l = true;
      }

   }

   private void func_76615_h(int p_76615_1_, int p_76615_2_, int p_76615_3_) {
      int lvt_4_1_ = this.field_76634_f[p_76615_3_ << 4 | p_76615_1_] & 255;
      int lvt_5_1_ = lvt_4_1_;
      if (p_76615_2_ > lvt_4_1_) {
         lvt_5_1_ = p_76615_2_;
      }

      while(lvt_5_1_ > 0 && this.func_150808_b(p_76615_1_, lvt_5_1_ - 1, p_76615_3_) == 0) {
         --lvt_5_1_;
      }

      if (lvt_5_1_ != lvt_4_1_) {
         this.field_76637_e.func_72975_g(p_76615_1_ + this.field_76635_g * 16, p_76615_3_ + this.field_76647_h * 16, lvt_5_1_, lvt_4_1_);
         this.field_76634_f[p_76615_3_ << 4 | p_76615_1_] = lvt_5_1_;
         int lvt_6_1_ = this.field_76635_g * 16 + p_76615_1_;
         int lvt_7_1_ = this.field_76647_h * 16 + p_76615_3_;
         int lvt_8_3_;
         int lvt_9_4_;
         if (!this.field_76637_e.field_73011_w.func_177495_o()) {
            ExtendedBlockStorage lvt_9_2_;
            if (lvt_5_1_ < lvt_4_1_) {
               for(lvt_8_3_ = lvt_5_1_; lvt_8_3_ < lvt_4_1_; ++lvt_8_3_) {
                  lvt_9_2_ = this.field_76652_q[lvt_8_3_ >> 4];
                  if (lvt_9_2_ != null) {
                     lvt_9_2_.func_76657_c(p_76615_1_, lvt_8_3_ & 15, p_76615_3_, 15);
                     this.field_76637_e.func_175679_n(new BlockPos((this.field_76635_g << 4) + p_76615_1_, lvt_8_3_, (this.field_76647_h << 4) + p_76615_3_));
                  }
               }
            } else {
               for(lvt_8_3_ = lvt_4_1_; lvt_8_3_ < lvt_5_1_; ++lvt_8_3_) {
                  lvt_9_2_ = this.field_76652_q[lvt_8_3_ >> 4];
                  if (lvt_9_2_ != null) {
                     lvt_9_2_.func_76657_c(p_76615_1_, lvt_8_3_ & 15, p_76615_3_, 0);
                     this.field_76637_e.func_175679_n(new BlockPos((this.field_76635_g << 4) + p_76615_1_, lvt_8_3_, (this.field_76647_h << 4) + p_76615_3_));
                  }
               }
            }

            lvt_8_3_ = 15;

            while(lvt_5_1_ > 0 && lvt_8_3_ > 0) {
               --lvt_5_1_;
               lvt_9_4_ = this.func_150808_b(p_76615_1_, lvt_5_1_, p_76615_3_);
               if (lvt_9_4_ == 0) {
                  lvt_9_4_ = 1;
               }

               lvt_8_3_ -= lvt_9_4_;
               if (lvt_8_3_ < 0) {
                  lvt_8_3_ = 0;
               }

               ExtendedBlockStorage lvt_10_1_ = this.field_76652_q[lvt_5_1_ >> 4];
               if (lvt_10_1_ != null) {
                  lvt_10_1_.func_76657_c(p_76615_1_, lvt_5_1_ & 15, p_76615_3_, lvt_8_3_);
               }
            }
         }

         lvt_8_3_ = this.field_76634_f[p_76615_3_ << 4 | p_76615_1_];
         lvt_9_4_ = lvt_4_1_;
         int lvt_10_2_ = lvt_8_3_;
         if (lvt_8_3_ < lvt_4_1_) {
            lvt_9_4_ = lvt_8_3_;
            lvt_10_2_ = lvt_4_1_;
         }

         if (lvt_8_3_ < this.field_82912_p) {
            this.field_82912_p = lvt_8_3_;
         }

         if (!this.field_76637_e.field_73011_w.func_177495_o()) {
            Iterator lvt_11_2_ = EnumFacing.Plane.HORIZONTAL.iterator();

            while(lvt_11_2_.hasNext()) {
               EnumFacing lvt_12_1_ = (EnumFacing)lvt_11_2_.next();
               this.func_76609_d(lvt_6_1_ + lvt_12_1_.func_82601_c(), lvt_7_1_ + lvt_12_1_.func_82599_e(), lvt_9_4_, lvt_10_2_);
            }

            this.func_76609_d(lvt_6_1_, lvt_7_1_, lvt_9_4_, lvt_10_2_);
         }

         this.field_76643_l = true;
      }
   }

   public int func_177437_b(BlockPos p_177437_1_) {
      return this.func_177428_a(p_177437_1_).func_149717_k();
   }

   private int func_150808_b(int p_150808_1_, int p_150808_2_, int p_150808_3_) {
      return this.func_150810_a(p_150808_1_, p_150808_2_, p_150808_3_).func_149717_k();
   }

   private Block func_150810_a(int p_150810_1_, int p_150810_2_, int p_150810_3_) {
      Block lvt_4_1_ = Blocks.field_150350_a;
      if (p_150810_2_ >= 0 && p_150810_2_ >> 4 < this.field_76652_q.length) {
         ExtendedBlockStorage lvt_5_1_ = this.field_76652_q[p_150810_2_ >> 4];
         if (lvt_5_1_ != null) {
            try {
               lvt_4_1_ = lvt_5_1_.func_150819_a(p_150810_1_, p_150810_2_ & 15, p_150810_3_);
            } catch (Throwable var8) {
               CrashReport lvt_7_1_ = CrashReport.func_85055_a(var8, "Getting block");
               throw new ReportedException(lvt_7_1_);
            }
         }
      }

      return lvt_4_1_;
   }

   public Block func_177438_a(final int p_177438_1_, final int p_177438_2_, final int p_177438_3_) {
      try {
         return this.func_150810_a(p_177438_1_ & 15, p_177438_2_, p_177438_3_ & 15);
      } catch (ReportedException var6) {
         CrashReportCategory lvt_5_1_ = var6.func_71575_a().func_85058_a("Block being got");
         lvt_5_1_.func_71500_a("Location", new Callable<String>() {
            public String call() throws Exception {
               return CrashReportCategory.func_180522_a(new BlockPos(Chunk.this.field_76635_g * 16 + p_177438_1_, p_177438_2_, Chunk.this.field_76647_h * 16 + p_177438_3_));
            }
         });
         throw var6;
      }
   }

   public Block func_177428_a(final BlockPos p_177428_1_) {
      try {
         return this.func_150810_a(p_177428_1_.func_177958_n() & 15, p_177428_1_.func_177956_o(), p_177428_1_.func_177952_p() & 15);
      } catch (ReportedException var4) {
         CrashReportCategory lvt_3_1_ = var4.func_71575_a().func_85058_a("Block being got");
         lvt_3_1_.func_71500_a("Location", new Callable<String>() {
            public String call() throws Exception {
               return CrashReportCategory.func_180522_a(p_177428_1_);
            }
         });
         throw var4;
      }
   }

   public IBlockState func_177435_g(final BlockPos p_177435_1_) {
      if (this.field_76637_e.func_175624_G() == WorldType.field_180272_g) {
         IBlockState lvt_2_1_ = null;
         if (p_177435_1_.func_177956_o() == 60) {
            lvt_2_1_ = Blocks.field_180401_cv.func_176223_P();
         }

         if (p_177435_1_.func_177956_o() == 70) {
            lvt_2_1_ = ChunkProviderDebug.func_177461_b(p_177435_1_.func_177958_n(), p_177435_1_.func_177952_p());
         }

         return lvt_2_1_ == null ? Blocks.field_150350_a.func_176223_P() : lvt_2_1_;
      } else {
         try {
            if (p_177435_1_.func_177956_o() >= 0 && p_177435_1_.func_177956_o() >> 4 < this.field_76652_q.length) {
               ExtendedBlockStorage lvt_2_2_ = this.field_76652_q[p_177435_1_.func_177956_o() >> 4];
               if (lvt_2_2_ != null) {
                  int lvt_3_1_ = p_177435_1_.func_177958_n() & 15;
                  int lvt_4_1_ = p_177435_1_.func_177956_o() & 15;
                  int lvt_5_1_ = p_177435_1_.func_177952_p() & 15;
                  return lvt_2_2_.func_177485_a(lvt_3_1_, lvt_4_1_, lvt_5_1_);
               }
            }

            return Blocks.field_150350_a.func_176223_P();
         } catch (Throwable var6) {
            CrashReport lvt_3_2_ = CrashReport.func_85055_a(var6, "Getting block state");
            CrashReportCategory lvt_4_2_ = lvt_3_2_.func_85058_a("Block being got");
            lvt_4_2_.func_71500_a("Location", new Callable<String>() {
               public String call() throws Exception {
                  return CrashReportCategory.func_180522_a(p_177435_1_);
               }
            });
            throw new ReportedException(lvt_3_2_);
         }
      }
   }

   private int func_76628_c(int p_76628_1_, int p_76628_2_, int p_76628_3_) {
      if (p_76628_2_ >> 4 >= this.field_76652_q.length) {
         return 0;
      } else {
         ExtendedBlockStorage lvt_4_1_ = this.field_76652_q[p_76628_2_ >> 4];
         return lvt_4_1_ != null ? lvt_4_1_.func_76665_b(p_76628_1_, p_76628_2_ & 15, p_76628_3_) : 0;
      }
   }

   public int func_177418_c(BlockPos p_177418_1_) {
      return this.func_76628_c(p_177418_1_.func_177958_n() & 15, p_177418_1_.func_177956_o(), p_177418_1_.func_177952_p() & 15);
   }

   public IBlockState func_177436_a(BlockPos p_177436_1_, IBlockState p_177436_2_) {
      int lvt_3_1_ = p_177436_1_.func_177958_n() & 15;
      int lvt_4_1_ = p_177436_1_.func_177956_o();
      int lvt_5_1_ = p_177436_1_.func_177952_p() & 15;
      int lvt_6_1_ = lvt_5_1_ << 4 | lvt_3_1_;
      if (lvt_4_1_ >= this.field_76638_b[lvt_6_1_] - 1) {
         this.field_76638_b[lvt_6_1_] = -999;
      }

      int lvt_7_1_ = this.field_76634_f[lvt_6_1_];
      IBlockState lvt_8_1_ = this.func_177435_g(p_177436_1_);
      if (lvt_8_1_ == p_177436_2_) {
         return null;
      } else {
         Block lvt_9_1_ = p_177436_2_.func_177230_c();
         Block lvt_10_1_ = lvt_8_1_.func_177230_c();
         ExtendedBlockStorage lvt_11_1_ = this.field_76652_q[lvt_4_1_ >> 4];
         boolean lvt_12_1_ = false;
         if (lvt_11_1_ == null) {
            if (lvt_9_1_ == Blocks.field_150350_a) {
               return null;
            }

            lvt_11_1_ = this.field_76652_q[lvt_4_1_ >> 4] = new ExtendedBlockStorage(lvt_4_1_ >> 4 << 4, !this.field_76637_e.field_73011_w.func_177495_o());
            lvt_12_1_ = lvt_4_1_ >= lvt_7_1_;
         }

         lvt_11_1_.func_177484_a(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_, p_177436_2_);
         if (lvt_10_1_ != lvt_9_1_) {
            if (!this.field_76637_e.field_72995_K) {
               lvt_10_1_.func_180663_b(this.field_76637_e, p_177436_1_, lvt_8_1_);
            } else if (lvt_10_1_ instanceof ITileEntityProvider) {
               this.field_76637_e.func_175713_t(p_177436_1_);
            }
         }

         if (lvt_11_1_.func_150819_a(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_) != lvt_9_1_) {
            return null;
         } else {
            if (lvt_12_1_) {
               this.func_76603_b();
            } else {
               int lvt_13_1_ = lvt_9_1_.func_149717_k();
               int lvt_14_1_ = lvt_10_1_.func_149717_k();
               if (lvt_13_1_ > 0) {
                  if (lvt_4_1_ >= lvt_7_1_) {
                     this.func_76615_h(lvt_3_1_, lvt_4_1_ + 1, lvt_5_1_);
                  }
               } else if (lvt_4_1_ == lvt_7_1_ - 1) {
                  this.func_76615_h(lvt_3_1_, lvt_4_1_, lvt_5_1_);
               }

               if (lvt_13_1_ != lvt_14_1_ && (lvt_13_1_ < lvt_14_1_ || this.func_177413_a(EnumSkyBlock.SKY, p_177436_1_) > 0 || this.func_177413_a(EnumSkyBlock.BLOCK, p_177436_1_) > 0)) {
                  this.func_76595_e(lvt_3_1_, lvt_5_1_);
               }
            }

            TileEntity lvt_13_3_;
            if (lvt_10_1_ instanceof ITileEntityProvider) {
               lvt_13_3_ = this.func_177424_a(p_177436_1_, Chunk.EnumCreateEntityType.CHECK);
               if (lvt_13_3_ != null) {
                  lvt_13_3_.func_145836_u();
               }
            }

            if (!this.field_76637_e.field_72995_K && lvt_10_1_ != lvt_9_1_) {
               lvt_9_1_.func_176213_c(this.field_76637_e, p_177436_1_, p_177436_2_);
            }

            if (lvt_9_1_ instanceof ITileEntityProvider) {
               lvt_13_3_ = this.func_177424_a(p_177436_1_, Chunk.EnumCreateEntityType.CHECK);
               if (lvt_13_3_ == null) {
                  lvt_13_3_ = ((ITileEntityProvider)lvt_9_1_).func_149915_a(this.field_76637_e, lvt_9_1_.func_176201_c(p_177436_2_));
                  this.field_76637_e.func_175690_a(p_177436_1_, lvt_13_3_);
               }

               if (lvt_13_3_ != null) {
                  lvt_13_3_.func_145836_u();
               }
            }

            this.field_76643_l = true;
            return lvt_8_1_;
         }
      }
   }

   public int func_177413_a(EnumSkyBlock p_177413_1_, BlockPos p_177413_2_) {
      int lvt_3_1_ = p_177413_2_.func_177958_n() & 15;
      int lvt_4_1_ = p_177413_2_.func_177956_o();
      int lvt_5_1_ = p_177413_2_.func_177952_p() & 15;
      ExtendedBlockStorage lvt_6_1_ = this.field_76652_q[lvt_4_1_ >> 4];
      if (lvt_6_1_ == null) {
         return this.func_177444_d(p_177413_2_) ? p_177413_1_.field_77198_c : 0;
      } else if (p_177413_1_ == EnumSkyBlock.SKY) {
         return this.field_76637_e.field_73011_w.func_177495_o() ? 0 : lvt_6_1_.func_76670_c(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_);
      } else {
         return p_177413_1_ == EnumSkyBlock.BLOCK ? lvt_6_1_.func_76674_d(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_) : p_177413_1_.field_77198_c;
      }
   }

   public void func_177431_a(EnumSkyBlock p_177431_1_, BlockPos p_177431_2_, int p_177431_3_) {
      int lvt_4_1_ = p_177431_2_.func_177958_n() & 15;
      int lvt_5_1_ = p_177431_2_.func_177956_o();
      int lvt_6_1_ = p_177431_2_.func_177952_p() & 15;
      ExtendedBlockStorage lvt_7_1_ = this.field_76652_q[lvt_5_1_ >> 4];
      if (lvt_7_1_ == null) {
         lvt_7_1_ = this.field_76652_q[lvt_5_1_ >> 4] = new ExtendedBlockStorage(lvt_5_1_ >> 4 << 4, !this.field_76637_e.field_73011_w.func_177495_o());
         this.func_76603_b();
      }

      this.field_76643_l = true;
      if (p_177431_1_ == EnumSkyBlock.SKY) {
         if (!this.field_76637_e.field_73011_w.func_177495_o()) {
            lvt_7_1_.func_76657_c(lvt_4_1_, lvt_5_1_ & 15, lvt_6_1_, p_177431_3_);
         }
      } else if (p_177431_1_ == EnumSkyBlock.BLOCK) {
         lvt_7_1_.func_76677_d(lvt_4_1_, lvt_5_1_ & 15, lvt_6_1_, p_177431_3_);
      }

   }

   public int func_177443_a(BlockPos p_177443_1_, int p_177443_2_) {
      int lvt_3_1_ = p_177443_1_.func_177958_n() & 15;
      int lvt_4_1_ = p_177443_1_.func_177956_o();
      int lvt_5_1_ = p_177443_1_.func_177952_p() & 15;
      ExtendedBlockStorage lvt_6_1_ = this.field_76652_q[lvt_4_1_ >> 4];
      if (lvt_6_1_ == null) {
         return !this.field_76637_e.field_73011_w.func_177495_o() && p_177443_2_ < EnumSkyBlock.SKY.field_77198_c ? EnumSkyBlock.SKY.field_77198_c - p_177443_2_ : 0;
      } else {
         int lvt_7_1_ = this.field_76637_e.field_73011_w.func_177495_o() ? 0 : lvt_6_1_.func_76670_c(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_);
         lvt_7_1_ -= p_177443_2_;
         int lvt_8_1_ = lvt_6_1_.func_76674_d(lvt_3_1_, lvt_4_1_ & 15, lvt_5_1_);
         if (lvt_8_1_ > lvt_7_1_) {
            lvt_7_1_ = lvt_8_1_;
         }

         return lvt_7_1_;
      }
   }

   public void func_76612_a(Entity p_76612_1_) {
      this.field_76644_m = true;
      int lvt_2_1_ = MathHelper.func_76128_c(p_76612_1_.field_70165_t / 16.0D);
      int lvt_3_1_ = MathHelper.func_76128_c(p_76612_1_.field_70161_v / 16.0D);
      if (lvt_2_1_ != this.field_76635_g || lvt_3_1_ != this.field_76647_h) {
         field_150817_t.warn("Wrong location! (" + lvt_2_1_ + ", " + lvt_3_1_ + ") should be (" + this.field_76635_g + ", " + this.field_76647_h + "), " + p_76612_1_, p_76612_1_);
         p_76612_1_.func_70106_y();
      }

      int lvt_4_1_ = MathHelper.func_76128_c(p_76612_1_.field_70163_u / 16.0D);
      if (lvt_4_1_ < 0) {
         lvt_4_1_ = 0;
      }

      if (lvt_4_1_ >= this.field_76645_j.length) {
         lvt_4_1_ = this.field_76645_j.length - 1;
      }

      p_76612_1_.field_70175_ag = true;
      p_76612_1_.field_70176_ah = this.field_76635_g;
      p_76612_1_.field_70162_ai = lvt_4_1_;
      p_76612_1_.field_70164_aj = this.field_76647_h;
      this.field_76645_j[lvt_4_1_].add(p_76612_1_);
   }

   public void func_76622_b(Entity p_76622_1_) {
      this.func_76608_a(p_76622_1_, p_76622_1_.field_70162_ai);
   }

   public void func_76608_a(Entity p_76608_1_, int p_76608_2_) {
      if (p_76608_2_ < 0) {
         p_76608_2_ = 0;
      }

      if (p_76608_2_ >= this.field_76645_j.length) {
         p_76608_2_ = this.field_76645_j.length - 1;
      }

      this.field_76645_j[p_76608_2_].remove(p_76608_1_);
   }

   public boolean func_177444_d(BlockPos p_177444_1_) {
      int lvt_2_1_ = p_177444_1_.func_177958_n() & 15;
      int lvt_3_1_ = p_177444_1_.func_177956_o();
      int lvt_4_1_ = p_177444_1_.func_177952_p() & 15;
      return lvt_3_1_ >= this.field_76634_f[lvt_4_1_ << 4 | lvt_2_1_];
   }

   private TileEntity func_177422_i(BlockPos p_177422_1_) {
      Block lvt_2_1_ = this.func_177428_a(p_177422_1_);
      return !lvt_2_1_.func_149716_u() ? null : ((ITileEntityProvider)lvt_2_1_).func_149915_a(this.field_76637_e, this.func_177418_c(p_177422_1_));
   }

   public TileEntity func_177424_a(BlockPos p_177424_1_, Chunk.EnumCreateEntityType p_177424_2_) {
      TileEntity lvt_3_1_ = (TileEntity)this.field_150816_i.get(p_177424_1_);
      if (lvt_3_1_ == null) {
         if (p_177424_2_ == Chunk.EnumCreateEntityType.IMMEDIATE) {
            lvt_3_1_ = this.func_177422_i(p_177424_1_);
            this.field_76637_e.func_175690_a(p_177424_1_, lvt_3_1_);
         } else if (p_177424_2_ == Chunk.EnumCreateEntityType.QUEUED) {
            this.field_177447_w.add(p_177424_1_);
         }
      } else if (lvt_3_1_.func_145837_r()) {
         this.field_150816_i.remove(p_177424_1_);
         return null;
      }

      return lvt_3_1_;
   }

   public void func_150813_a(TileEntity p_150813_1_) {
      this.func_177426_a(p_150813_1_.func_174877_v(), p_150813_1_);
      if (this.field_76636_d) {
         this.field_76637_e.func_175700_a(p_150813_1_);
      }

   }

   public void func_177426_a(BlockPos p_177426_1_, TileEntity p_177426_2_) {
      p_177426_2_.func_145834_a(this.field_76637_e);
      p_177426_2_.func_174878_a(p_177426_1_);
      if (this.func_177428_a(p_177426_1_) instanceof ITileEntityProvider) {
         if (this.field_150816_i.containsKey(p_177426_1_)) {
            ((TileEntity)this.field_150816_i.get(p_177426_1_)).func_145843_s();
         }

         p_177426_2_.func_145829_t();
         this.field_150816_i.put(p_177426_1_, p_177426_2_);
      }
   }

   public void func_177425_e(BlockPos p_177425_1_) {
      if (this.field_76636_d) {
         TileEntity lvt_2_1_ = (TileEntity)this.field_150816_i.remove(p_177425_1_);
         if (lvt_2_1_ != null) {
            lvt_2_1_.func_145843_s();
         }
      }

   }

   public void func_76631_c() {
      this.field_76636_d = true;
      this.field_76637_e.func_147448_a(this.field_150816_i.values());

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_76645_j.length; ++lvt_1_1_) {
         Iterator lvt_2_1_ = this.field_76645_j[lvt_1_1_].iterator();

         while(lvt_2_1_.hasNext()) {
            Entity lvt_3_1_ = (Entity)lvt_2_1_.next();
            lvt_3_1_.func_110123_P();
         }

         this.field_76637_e.func_175650_b(this.field_76645_j[lvt_1_1_]);
      }

   }

   public void func_76623_d() {
      this.field_76636_d = false;
      Iterator lvt_1_1_ = this.field_150816_i.values().iterator();

      while(lvt_1_1_.hasNext()) {
         TileEntity lvt_2_1_ = (TileEntity)lvt_1_1_.next();
         this.field_76637_e.func_147457_a(lvt_2_1_);
      }

      for(int lvt_1_2_ = 0; lvt_1_2_ < this.field_76645_j.length; ++lvt_1_2_) {
         this.field_76637_e.func_175681_c(this.field_76645_j[lvt_1_2_]);
      }

   }

   public void func_76630_e() {
      this.field_76643_l = true;
   }

   public void func_177414_a(Entity p_177414_1_, AxisAlignedBB p_177414_2_, List<Entity> p_177414_3_, Predicate<? super Entity> p_177414_4_) {
      int lvt_5_1_ = MathHelper.func_76128_c((p_177414_2_.field_72338_b - 2.0D) / 16.0D);
      int lvt_6_1_ = MathHelper.func_76128_c((p_177414_2_.field_72337_e + 2.0D) / 16.0D);
      lvt_5_1_ = MathHelper.func_76125_a(lvt_5_1_, 0, this.field_76645_j.length - 1);
      lvt_6_1_ = MathHelper.func_76125_a(lvt_6_1_, 0, this.field_76645_j.length - 1);

      label68:
      for(int lvt_7_1_ = lvt_5_1_; lvt_7_1_ <= lvt_6_1_; ++lvt_7_1_) {
         if (!this.field_76645_j[lvt_7_1_].isEmpty()) {
            Iterator lvt_8_1_ = this.field_76645_j[lvt_7_1_].iterator();

            while(true) {
               Entity lvt_9_1_;
               Entity[] lvt_10_1_;
               do {
                  do {
                     do {
                        if (!lvt_8_1_.hasNext()) {
                           continue label68;
                        }

                        lvt_9_1_ = (Entity)lvt_8_1_.next();
                     } while(!lvt_9_1_.func_174813_aQ().func_72326_a(p_177414_2_));
                  } while(lvt_9_1_ == p_177414_1_);

                  if (p_177414_4_ == null || p_177414_4_.apply(lvt_9_1_)) {
                     p_177414_3_.add(lvt_9_1_);
                  }

                  lvt_10_1_ = lvt_9_1_.func_70021_al();
               } while(lvt_10_1_ == null);

               for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_1_.length; ++lvt_11_1_) {
                  lvt_9_1_ = lvt_10_1_[lvt_11_1_];
                  if (lvt_9_1_ != p_177414_1_ && lvt_9_1_.func_174813_aQ().func_72326_a(p_177414_2_) && (p_177414_4_ == null || p_177414_4_.apply(lvt_9_1_))) {
                     p_177414_3_.add(lvt_9_1_);
                  }
               }
            }
         }
      }

   }

   public <T extends Entity> void func_177430_a(Class<? extends T> p_177430_1_, AxisAlignedBB p_177430_2_, List<T> p_177430_3_, Predicate<? super T> p_177430_4_) {
      int lvt_5_1_ = MathHelper.func_76128_c((p_177430_2_.field_72338_b - 2.0D) / 16.0D);
      int lvt_6_1_ = MathHelper.func_76128_c((p_177430_2_.field_72337_e + 2.0D) / 16.0D);
      lvt_5_1_ = MathHelper.func_76125_a(lvt_5_1_, 0, this.field_76645_j.length - 1);
      lvt_6_1_ = MathHelper.func_76125_a(lvt_6_1_, 0, this.field_76645_j.length - 1);

      label33:
      for(int lvt_7_1_ = lvt_5_1_; lvt_7_1_ <= lvt_6_1_; ++lvt_7_1_) {
         Iterator lvt_8_1_ = this.field_76645_j[lvt_7_1_].func_180215_b(p_177430_1_).iterator();

         while(true) {
            Entity lvt_9_1_;
            do {
               do {
                  if (!lvt_8_1_.hasNext()) {
                     continue label33;
                  }

                  lvt_9_1_ = (Entity)lvt_8_1_.next();
               } while(!lvt_9_1_.func_174813_aQ().func_72326_a(p_177430_2_));
            } while(p_177430_4_ != null && !p_177430_4_.apply(lvt_9_1_));

            p_177430_3_.add(lvt_9_1_);
         }
      }

   }

   public boolean func_76601_a(boolean p_76601_1_) {
      if (p_76601_1_) {
         if (this.field_76644_m && this.field_76637_e.func_82737_E() != this.field_76641_n || this.field_76643_l) {
            return true;
         }
      } else if (this.field_76644_m && this.field_76637_e.func_82737_E() >= this.field_76641_n + 600L) {
         return true;
      }

      return this.field_76643_l;
   }

   public Random func_76617_a(long p_76617_1_) {
      return new Random(this.field_76637_e.func_72905_C() + (long)(this.field_76635_g * this.field_76635_g * 4987142) + (long)(this.field_76635_g * 5947611) + (long)(this.field_76647_h * this.field_76647_h) * 4392871L + (long)(this.field_76647_h * 389711) ^ p_76617_1_);
   }

   public boolean func_76621_g() {
      return false;
   }

   public void func_76624_a(IChunkProvider p_76624_1_, IChunkProvider p_76624_2_, int p_76624_3_, int p_76624_4_) {
      boolean lvt_5_1_ = p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ - 1);
      boolean lvt_6_1_ = p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_);
      boolean lvt_7_1_ = p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ + 1);
      boolean lvt_8_1_ = p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_);
      boolean lvt_9_1_ = p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_ - 1);
      boolean lvt_10_1_ = p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_ + 1);
      boolean lvt_11_1_ = p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_ + 1);
      boolean lvt_12_1_ = p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_ - 1);
      if (lvt_6_1_ && lvt_7_1_ && lvt_10_1_) {
         if (!this.field_76646_k) {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_, p_76624_4_);
         } else {
            p_76624_1_.func_177460_a(p_76624_2_, this, p_76624_3_, p_76624_4_);
         }
      }

      Chunk lvt_13_3_;
      if (lvt_8_1_ && lvt_7_1_ && lvt_11_1_) {
         lvt_13_3_ = p_76624_1_.func_73154_d(p_76624_3_ - 1, p_76624_4_);
         if (!lvt_13_3_.field_76646_k) {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_ - 1, p_76624_4_);
         } else {
            p_76624_1_.func_177460_a(p_76624_2_, lvt_13_3_, p_76624_3_ - 1, p_76624_4_);
         }
      }

      if (lvt_5_1_ && lvt_6_1_ && lvt_12_1_) {
         lvt_13_3_ = p_76624_1_.func_73154_d(p_76624_3_, p_76624_4_ - 1);
         if (!lvt_13_3_.field_76646_k) {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_, p_76624_4_ - 1);
         } else {
            p_76624_1_.func_177460_a(p_76624_2_, lvt_13_3_, p_76624_3_, p_76624_4_ - 1);
         }
      }

      if (lvt_9_1_ && lvt_5_1_ && lvt_8_1_) {
         lvt_13_3_ = p_76624_1_.func_73154_d(p_76624_3_ - 1, p_76624_4_ - 1);
         if (!lvt_13_3_.field_76646_k) {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_ - 1, p_76624_4_ - 1);
         } else {
            p_76624_1_.func_177460_a(p_76624_2_, lvt_13_3_, p_76624_3_ - 1, p_76624_4_ - 1);
         }
      }

   }

   public BlockPos func_177440_h(BlockPos p_177440_1_) {
      int lvt_2_1_ = p_177440_1_.func_177958_n() & 15;
      int lvt_3_1_ = p_177440_1_.func_177952_p() & 15;
      int lvt_4_1_ = lvt_2_1_ | lvt_3_1_ << 4;
      BlockPos lvt_5_1_ = new BlockPos(p_177440_1_.func_177958_n(), this.field_76638_b[lvt_4_1_], p_177440_1_.func_177952_p());
      if (lvt_5_1_.func_177956_o() == -999) {
         int lvt_6_1_ = this.func_76625_h() + 15;
         lvt_5_1_ = new BlockPos(p_177440_1_.func_177958_n(), lvt_6_1_, p_177440_1_.func_177952_p());
         int lvt_7_1_ = -1;

         while(true) {
            while(lvt_5_1_.func_177956_o() > 0 && lvt_7_1_ == -1) {
               Block lvt_8_1_ = this.func_177428_a(lvt_5_1_);
               Material lvt_9_1_ = lvt_8_1_.func_149688_o();
               if (!lvt_9_1_.func_76230_c() && !lvt_9_1_.func_76224_d()) {
                  lvt_5_1_ = lvt_5_1_.func_177977_b();
               } else {
                  lvt_7_1_ = lvt_5_1_.func_177956_o() + 1;
               }
            }

            this.field_76638_b[lvt_4_1_] = lvt_7_1_;
            break;
         }
      }

      return new BlockPos(p_177440_1_.func_177958_n(), this.field_76638_b[lvt_4_1_], p_177440_1_.func_177952_p());
   }

   public void func_150804_b(boolean p_150804_1_) {
      if (this.field_76650_s && !this.field_76637_e.field_73011_w.func_177495_o() && !p_150804_1_) {
         this.func_150803_c(this.field_76637_e.field_72995_K);
      }

      this.field_150815_m = true;
      if (!this.field_150814_l && this.field_76646_k) {
         this.func_150809_p();
      }

      while(!this.field_177447_w.isEmpty()) {
         BlockPos lvt_2_1_ = (BlockPos)this.field_177447_w.poll();
         if (this.func_177424_a(lvt_2_1_, Chunk.EnumCreateEntityType.CHECK) == null && this.func_177428_a(lvt_2_1_).func_149716_u()) {
            TileEntity lvt_3_1_ = this.func_177422_i(lvt_2_1_);
            this.field_76637_e.func_175690_a(lvt_2_1_, lvt_3_1_);
            this.field_76637_e.func_175704_b(lvt_2_1_, lvt_2_1_);
         }
      }

   }

   public boolean func_150802_k() {
      return this.field_150815_m && this.field_76646_k && this.field_150814_l;
   }

   public ChunkCoordIntPair func_76632_l() {
      return new ChunkCoordIntPair(this.field_76635_g, this.field_76647_h);
   }

   public boolean func_76606_c(int p_76606_1_, int p_76606_2_) {
      if (p_76606_1_ < 0) {
         p_76606_1_ = 0;
      }

      if (p_76606_2_ >= 256) {
         p_76606_2_ = 255;
      }

      for(int lvt_3_1_ = p_76606_1_; lvt_3_1_ <= p_76606_2_; lvt_3_1_ += 16) {
         ExtendedBlockStorage lvt_4_1_ = this.field_76652_q[lvt_3_1_ >> 4];
         if (lvt_4_1_ != null && !lvt_4_1_.func_76663_a()) {
            return false;
         }
      }

      return true;
   }

   public void func_76602_a(ExtendedBlockStorage[] p_76602_1_) {
      if (this.field_76652_q.length != p_76602_1_.length) {
         field_150817_t.warn("Could not set level chunk sections, array length is " + p_76602_1_.length + " instead of " + this.field_76652_q.length);
      } else {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_76652_q.length; ++lvt_2_1_) {
            this.field_76652_q[lvt_2_1_] = p_76602_1_[lvt_2_1_];
         }

      }
   }

   public void func_177439_a(byte[] p_177439_1_, int p_177439_2_, boolean p_177439_3_) {
      int lvt_4_1_ = 0;
      boolean lvt_5_1_ = !this.field_76637_e.field_73011_w.func_177495_o();

      int lvt_6_4_;
      for(lvt_6_4_ = 0; lvt_6_4_ < this.field_76652_q.length; ++lvt_6_4_) {
         if ((p_177439_2_ & 1 << lvt_6_4_) != 0) {
            if (this.field_76652_q[lvt_6_4_] == null) {
               this.field_76652_q[lvt_6_4_] = new ExtendedBlockStorage(lvt_6_4_ << 4, lvt_5_1_);
            }

            char[] lvt_7_1_ = this.field_76652_q[lvt_6_4_].func_177487_g();

            for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_.length; ++lvt_8_1_) {
               lvt_7_1_[lvt_8_1_] = (char)((p_177439_1_[lvt_4_1_ + 1] & 255) << 8 | p_177439_1_[lvt_4_1_] & 255);
               lvt_4_1_ += 2;
            }
         } else if (p_177439_3_ && this.field_76652_q[lvt_6_4_] != null) {
            this.field_76652_q[lvt_6_4_] = null;
         }
      }

      NibbleArray lvt_7_3_;
      for(lvt_6_4_ = 0; lvt_6_4_ < this.field_76652_q.length; ++lvt_6_4_) {
         if ((p_177439_2_ & 1 << lvt_6_4_) != 0 && this.field_76652_q[lvt_6_4_] != null) {
            lvt_7_3_ = this.field_76652_q[lvt_6_4_].func_76661_k();
            System.arraycopy(p_177439_1_, lvt_4_1_, lvt_7_3_.func_177481_a(), 0, lvt_7_3_.func_177481_a().length);
            lvt_4_1_ += lvt_7_3_.func_177481_a().length;
         }
      }

      if (lvt_5_1_) {
         for(lvt_6_4_ = 0; lvt_6_4_ < this.field_76652_q.length; ++lvt_6_4_) {
            if ((p_177439_2_ & 1 << lvt_6_4_) != 0 && this.field_76652_q[lvt_6_4_] != null) {
               lvt_7_3_ = this.field_76652_q[lvt_6_4_].func_76671_l();
               System.arraycopy(p_177439_1_, lvt_4_1_, lvt_7_3_.func_177481_a(), 0, lvt_7_3_.func_177481_a().length);
               lvt_4_1_ += lvt_7_3_.func_177481_a().length;
            }
         }
      }

      if (p_177439_3_) {
         System.arraycopy(p_177439_1_, lvt_4_1_, this.field_76651_r, 0, this.field_76651_r.length);
         int var10000 = lvt_4_1_ + this.field_76651_r.length;
      }

      for(lvt_6_4_ = 0; lvt_6_4_ < this.field_76652_q.length; ++lvt_6_4_) {
         if (this.field_76652_q[lvt_6_4_] != null && (p_177439_2_ & 1 << lvt_6_4_) != 0) {
            this.field_76652_q[lvt_6_4_].func_76672_e();
         }
      }

      this.field_150814_l = true;
      this.field_76646_k = true;
      this.func_76590_a();
      Iterator lvt_6_5_ = this.field_150816_i.values().iterator();

      while(lvt_6_5_.hasNext()) {
         TileEntity lvt_7_4_ = (TileEntity)lvt_6_5_.next();
         lvt_7_4_.func_145836_u();
      }

   }

   public BiomeGenBase func_177411_a(BlockPos p_177411_1_, WorldChunkManager p_177411_2_) {
      int lvt_3_1_ = p_177411_1_.func_177958_n() & 15;
      int lvt_4_1_ = p_177411_1_.func_177952_p() & 15;
      int lvt_5_1_ = this.field_76651_r[lvt_4_1_ << 4 | lvt_3_1_] & 255;
      BiomeGenBase lvt_6_2_;
      if (lvt_5_1_ == 255) {
         lvt_6_2_ = p_177411_2_.func_180300_a(p_177411_1_, BiomeGenBase.field_76772_c);
         lvt_5_1_ = lvt_6_2_.field_76756_M;
         this.field_76651_r[lvt_4_1_ << 4 | lvt_3_1_] = (byte)(lvt_5_1_ & 255);
      }

      lvt_6_2_ = BiomeGenBase.func_150568_d(lvt_5_1_);
      return lvt_6_2_ == null ? BiomeGenBase.field_76772_c : lvt_6_2_;
   }

   public byte[] func_76605_m() {
      return this.field_76651_r;
   }

   public void func_76616_a(byte[] p_76616_1_) {
      if (this.field_76651_r.length != p_76616_1_.length) {
         field_150817_t.warn("Could not set level chunk biomes, array length is " + p_76616_1_.length + " instead of " + this.field_76651_r.length);
      } else {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_76651_r.length; ++lvt_2_1_) {
            this.field_76651_r[lvt_2_1_] = p_76616_1_[lvt_2_1_];
         }

      }
   }

   public void func_76613_n() {
      this.field_76649_t = 0;
   }

   public void func_76594_o() {
      BlockPos lvt_1_1_ = new BlockPos(this.field_76635_g << 4, 0, this.field_76647_h << 4);

      for(int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
         if (this.field_76649_t >= 4096) {
            return;
         }

         int lvt_3_1_ = this.field_76649_t % 16;
         int lvt_4_1_ = this.field_76649_t / 16 % 16;
         int lvt_5_1_ = this.field_76649_t / 256;
         ++this.field_76649_t;

         for(int lvt_6_1_ = 0; lvt_6_1_ < 16; ++lvt_6_1_) {
            BlockPos lvt_7_1_ = lvt_1_1_.func_177982_a(lvt_4_1_, (lvt_3_1_ << 4) + lvt_6_1_, lvt_5_1_);
            boolean lvt_8_1_ = lvt_6_1_ == 0 || lvt_6_1_ == 15 || lvt_4_1_ == 0 || lvt_4_1_ == 15 || lvt_5_1_ == 0 || lvt_5_1_ == 15;
            if (this.field_76652_q[lvt_3_1_] == null && lvt_8_1_ || this.field_76652_q[lvt_3_1_] != null && this.field_76652_q[lvt_3_1_].func_150819_a(lvt_4_1_, lvt_6_1_, lvt_5_1_).func_149688_o() == Material.field_151579_a) {
               EnumFacing[] lvt_9_1_ = EnumFacing.values();
               int lvt_10_1_ = lvt_9_1_.length;

               for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_1_; ++lvt_11_1_) {
                  EnumFacing lvt_12_1_ = lvt_9_1_[lvt_11_1_];
                  BlockPos lvt_13_1_ = lvt_7_1_.func_177972_a(lvt_12_1_);
                  if (this.field_76637_e.func_180495_p(lvt_13_1_).func_177230_c().func_149750_m() > 0) {
                     this.field_76637_e.func_175664_x(lvt_13_1_);
                  }
               }

               this.field_76637_e.func_175664_x(lvt_7_1_);
            }
         }
      }

   }

   public void func_150809_p() {
      this.field_76646_k = true;
      this.field_150814_l = true;
      BlockPos lvt_1_1_ = new BlockPos(this.field_76635_g << 4, 0, this.field_76647_h << 4);
      if (!this.field_76637_e.field_73011_w.func_177495_o()) {
         if (this.field_76637_e.func_175707_a(lvt_1_1_.func_177982_a(-1, 0, -1), lvt_1_1_.func_177982_a(16, this.field_76637_e.func_181545_F(), 16))) {
            label44:
            for(int lvt_2_1_ = 0; lvt_2_1_ < 16; ++lvt_2_1_) {
               for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
                  if (!this.func_150811_f(lvt_2_1_, lvt_3_1_)) {
                     this.field_150814_l = false;
                     break label44;
                  }
               }
            }

            if (this.field_150814_l) {
               Iterator lvt_2_2_ = EnumFacing.Plane.HORIZONTAL.iterator();

               while(lvt_2_2_.hasNext()) {
                  EnumFacing lvt_3_2_ = (EnumFacing)lvt_2_2_.next();
                  int lvt_4_1_ = lvt_3_2_.func_176743_c() == EnumFacing.AxisDirection.POSITIVE ? 16 : 1;
                  this.field_76637_e.func_175726_f(lvt_1_1_.func_177967_a(lvt_3_2_, lvt_4_1_)).func_180700_a(lvt_3_2_.func_176734_d());
               }

               this.func_177441_y();
            }
         } else {
            this.field_150814_l = false;
         }
      }

   }

   private void func_177441_y() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_76639_c.length; ++lvt_1_1_) {
         this.field_76639_c[lvt_1_1_] = true;
      }

      this.func_150803_c(false);
   }

   private void func_180700_a(EnumFacing p_180700_1_) {
      if (this.field_76646_k) {
         int lvt_2_4_;
         if (p_180700_1_ == EnumFacing.EAST) {
            for(lvt_2_4_ = 0; lvt_2_4_ < 16; ++lvt_2_4_) {
               this.func_150811_f(15, lvt_2_4_);
            }
         } else if (p_180700_1_ == EnumFacing.WEST) {
            for(lvt_2_4_ = 0; lvt_2_4_ < 16; ++lvt_2_4_) {
               this.func_150811_f(0, lvt_2_4_);
            }
         } else if (p_180700_1_ == EnumFacing.SOUTH) {
            for(lvt_2_4_ = 0; lvt_2_4_ < 16; ++lvt_2_4_) {
               this.func_150811_f(lvt_2_4_, 15);
            }
         } else if (p_180700_1_ == EnumFacing.NORTH) {
            for(lvt_2_4_ = 0; lvt_2_4_ < 16; ++lvt_2_4_) {
               this.func_150811_f(lvt_2_4_, 0);
            }
         }

      }
   }

   private boolean func_150811_f(int p_150811_1_, int p_150811_2_) {
      int lvt_3_1_ = this.func_76625_h();
      boolean lvt_4_1_ = false;
      boolean lvt_5_1_ = false;
      BlockPos.MutableBlockPos lvt_6_1_ = new BlockPos.MutableBlockPos((this.field_76635_g << 4) + p_150811_1_, 0, (this.field_76647_h << 4) + p_150811_2_);

      int lvt_7_2_;
      for(lvt_7_2_ = lvt_3_1_ + 16 - 1; lvt_7_2_ > this.field_76637_e.func_181545_F() || lvt_7_2_ > 0 && !lvt_5_1_; --lvt_7_2_) {
         lvt_6_1_.func_181079_c(lvt_6_1_.func_177958_n(), lvt_7_2_, lvt_6_1_.func_177952_p());
         int lvt_8_1_ = this.func_177437_b(lvt_6_1_);
         if (lvt_8_1_ == 255 && lvt_6_1_.func_177956_o() < this.field_76637_e.func_181545_F()) {
            lvt_5_1_ = true;
         }

         if (!lvt_4_1_ && lvt_8_1_ > 0) {
            lvt_4_1_ = true;
         } else if (lvt_4_1_ && lvt_8_1_ == 0 && !this.field_76637_e.func_175664_x(lvt_6_1_)) {
            return false;
         }
      }

      for(lvt_7_2_ = lvt_6_1_.func_177956_o(); lvt_7_2_ > 0; --lvt_7_2_) {
         lvt_6_1_.func_181079_c(lvt_6_1_.func_177958_n(), lvt_7_2_, lvt_6_1_.func_177952_p());
         if (this.func_177428_a(lvt_6_1_).func_149750_m() > 0) {
            this.field_76637_e.func_175664_x(lvt_6_1_);
         }
      }

      return true;
   }

   public boolean func_177410_o() {
      return this.field_76636_d;
   }

   public void func_177417_c(boolean p_177417_1_) {
      this.field_76636_d = p_177417_1_;
   }

   public World func_177412_p() {
      return this.field_76637_e;
   }

   public int[] func_177445_q() {
      return this.field_76634_f;
   }

   public void func_177420_a(int[] p_177420_1_) {
      if (this.field_76634_f.length != p_177420_1_.length) {
         field_150817_t.warn("Could not set level chunk heightmap, array length is " + p_177420_1_.length + " instead of " + this.field_76634_f.length);
      } else {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_76634_f.length; ++lvt_2_1_) {
            this.field_76634_f[lvt_2_1_] = p_177420_1_[lvt_2_1_];
         }

      }
   }

   public Map<BlockPos, TileEntity> func_177434_r() {
      return this.field_150816_i;
   }

   public ClassInheritanceMultiMap<Entity>[] func_177429_s() {
      return this.field_76645_j;
   }

   public boolean func_177419_t() {
      return this.field_76646_k;
   }

   public void func_177446_d(boolean p_177446_1_) {
      this.field_76646_k = p_177446_1_;
   }

   public boolean func_177423_u() {
      return this.field_150814_l;
   }

   public void func_177421_e(boolean p_177421_1_) {
      this.field_150814_l = p_177421_1_;
   }

   public void func_177427_f(boolean p_177427_1_) {
      this.field_76643_l = p_177427_1_;
   }

   public void func_177409_g(boolean p_177409_1_) {
      this.field_76644_m = p_177409_1_;
   }

   public void func_177432_b(long p_177432_1_) {
      this.field_76641_n = p_177432_1_;
   }

   public int func_177442_v() {
      return this.field_82912_p;
   }

   public long func_177416_w() {
      return this.field_111204_q;
   }

   public void func_177415_c(long p_177415_1_) {
      this.field_111204_q = p_177415_1_;
   }

   public static enum EnumCreateEntityType {
      IMMEDIATE,
      QUEUED,
      CHECK;
   }
}
