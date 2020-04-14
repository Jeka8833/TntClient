package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class CrashReportCategory {
   private final CrashReport field_85078_a;
   private final String field_85076_b;
   private final List<CrashReportCategory.Entry> field_85077_c = Lists.newArrayList();
   private StackTraceElement[] field_85075_d = new StackTraceElement[0];

   public CrashReportCategory(CrashReport p_i1353_1_, String p_i1353_2_) {
      this.field_85078_a = p_i1353_1_;
      this.field_85076_b = p_i1353_2_;
   }

   public static String func_85074_a(double p_85074_0_, double p_85074_2_, double p_85074_4_) {
      return String.format("%.2f,%.2f,%.2f - %s", p_85074_0_, p_85074_2_, p_85074_4_, func_180522_a(new BlockPos(p_85074_0_, p_85074_2_, p_85074_4_)));
   }

   public static String func_180522_a(BlockPos p_180522_0_) {
      int lvt_1_1_ = p_180522_0_.func_177958_n();
      int lvt_2_1_ = p_180522_0_.func_177956_o();
      int lvt_3_1_ = p_180522_0_.func_177952_p();
      StringBuilder lvt_4_1_ = new StringBuilder();

      try {
         lvt_4_1_.append(String.format("World: (%d,%d,%d)", lvt_1_1_, lvt_2_1_, lvt_3_1_));
      } catch (Throwable var17) {
         lvt_4_1_.append("(Error finding world loc)");
      }

      lvt_4_1_.append(", ");

      int lvt_5_4_;
      int lvt_6_2_;
      int lvt_7_2_;
      int lvt_8_2_;
      int lvt_9_2_;
      int lvt_10_2_;
      int lvt_11_2_;
      int lvt_12_2_;
      int lvt_13_2_;
      try {
         lvt_5_4_ = lvt_1_1_ >> 4;
         lvt_6_2_ = lvt_3_1_ >> 4;
         lvt_7_2_ = lvt_1_1_ & 15;
         lvt_8_2_ = lvt_2_1_ >> 4;
         lvt_9_2_ = lvt_3_1_ & 15;
         lvt_10_2_ = lvt_5_4_ << 4;
         lvt_11_2_ = lvt_6_2_ << 4;
         lvt_12_2_ = (lvt_5_4_ + 1 << 4) - 1;
         lvt_13_2_ = (lvt_6_2_ + 1 << 4) - 1;
         lvt_4_1_.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", lvt_7_2_, lvt_8_2_, lvt_9_2_, lvt_5_4_, lvt_6_2_, lvt_10_2_, lvt_11_2_, lvt_12_2_, lvt_13_2_));
      } catch (Throwable var16) {
         lvt_4_1_.append("(Error finding chunk loc)");
      }

      lvt_4_1_.append(", ");

      try {
         lvt_5_4_ = lvt_1_1_ >> 9;
         lvt_6_2_ = lvt_3_1_ >> 9;
         lvt_7_2_ = lvt_5_4_ << 5;
         lvt_8_2_ = lvt_6_2_ << 5;
         lvt_9_2_ = (lvt_5_4_ + 1 << 5) - 1;
         lvt_10_2_ = (lvt_6_2_ + 1 << 5) - 1;
         lvt_11_2_ = lvt_5_4_ << 9;
         lvt_12_2_ = lvt_6_2_ << 9;
         lvt_13_2_ = (lvt_5_4_ + 1 << 9) - 1;
         int lvt_14_1_ = (lvt_6_2_ + 1 << 9) - 1;
         lvt_4_1_.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", lvt_5_4_, lvt_6_2_, lvt_7_2_, lvt_8_2_, lvt_9_2_, lvt_10_2_, lvt_11_2_, lvt_12_2_, lvt_13_2_, lvt_14_1_));
      } catch (Throwable var15) {
         lvt_4_1_.append("(Error finding world loc)");
      }

      return lvt_4_1_.toString();
   }

   public void func_71500_a(String p_71500_1_, Callable<String> p_71500_2_) {
      try {
         this.func_71507_a(p_71500_1_, p_71500_2_.call());
      } catch (Throwable var4) {
         this.func_71499_a(p_71500_1_, var4);
      }

   }

   public void func_71507_a(String p_71507_1_, Object p_71507_2_) {
      this.field_85077_c.add(new CrashReportCategory.Entry(p_71507_1_, p_71507_2_));
   }

   public void func_71499_a(String p_71499_1_, Throwable p_71499_2_) {
      this.func_71507_a(p_71499_1_, p_71499_2_);
   }

   public int func_85073_a(int p_85073_1_) {
      StackTraceElement[] lvt_2_1_ = Thread.currentThread().getStackTrace();
      if (lvt_2_1_.length <= 0) {
         return 0;
      } else {
         this.field_85075_d = new StackTraceElement[lvt_2_1_.length - 3 - p_85073_1_];
         System.arraycopy(lvt_2_1_, 3 + p_85073_1_, this.field_85075_d, 0, this.field_85075_d.length);
         return this.field_85075_d.length;
      }
   }

   public boolean func_85069_a(StackTraceElement p_85069_1_, StackTraceElement p_85069_2_) {
      if (this.field_85075_d.length != 0 && p_85069_1_ != null) {
         StackTraceElement lvt_3_1_ = this.field_85075_d[0];
         if (lvt_3_1_.isNativeMethod() == p_85069_1_.isNativeMethod() && lvt_3_1_.getClassName().equals(p_85069_1_.getClassName()) && lvt_3_1_.getFileName().equals(p_85069_1_.getFileName()) && lvt_3_1_.getMethodName().equals(p_85069_1_.getMethodName())) {
            if (p_85069_2_ != null != this.field_85075_d.length > 1) {
               return false;
            } else if (p_85069_2_ != null && !this.field_85075_d[1].equals(p_85069_2_)) {
               return false;
            } else {
               this.field_85075_d[0] = p_85069_1_;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void func_85070_b(int p_85070_1_) {
      StackTraceElement[] lvt_2_1_ = new StackTraceElement[this.field_85075_d.length - p_85070_1_];
      System.arraycopy(this.field_85075_d, 0, lvt_2_1_, 0, lvt_2_1_.length);
      this.field_85075_d = lvt_2_1_;
   }

   public void func_85072_a(StringBuilder p_85072_1_) {
      p_85072_1_.append("-- ").append(this.field_85076_b).append(" --\n");
      p_85072_1_.append("Details:");
      Iterator lvt_2_1_ = this.field_85077_c.iterator();

      while(lvt_2_1_.hasNext()) {
         CrashReportCategory.Entry lvt_3_1_ = (CrashReportCategory.Entry)lvt_2_1_.next();
         p_85072_1_.append("\n\t");
         p_85072_1_.append(lvt_3_1_.func_85089_a());
         p_85072_1_.append(": ");
         p_85072_1_.append(lvt_3_1_.func_85090_b());
      }

      if (this.field_85075_d != null && this.field_85075_d.length > 0) {
         p_85072_1_.append("\nStacktrace:");
         StackTraceElement[] lvt_2_2_ = this.field_85075_d;
         int lvt_3_2_ = lvt_2_2_.length;

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_2_; ++lvt_4_1_) {
            StackTraceElement lvt_5_1_ = lvt_2_2_[lvt_4_1_];
            p_85072_1_.append("\n\tat ");
            p_85072_1_.append(lvt_5_1_.toString());
         }
      }

   }

   public StackTraceElement[] func_147152_a() {
      return this.field_85075_d;
   }

   public static void func_180523_a(CrashReportCategory p_180523_0_, final BlockPos p_180523_1_, final Block p_180523_2_, final int p_180523_3_) {
      final int lvt_4_1_ = Block.func_149682_b(p_180523_2_);
      p_180523_0_.func_71500_a("Block type", new Callable<String>() {
         public String call() throws Exception {
            try {
               return String.format("ID #%d (%s // %s)", lvt_4_1_, p_180523_2_.func_149739_a(), p_180523_2_.getClass().getCanonicalName());
            } catch (Throwable var2) {
               return "ID #" + lvt_4_1_;
            }
         }
      });
      p_180523_0_.func_71500_a("Block data value", new Callable<String>() {
         public String call() throws Exception {
            if (p_180523_3_ < 0) {
               return "Unknown? (Got " + p_180523_3_ + ")";
            } else {
               String lvt_1_1_ = String.format("%4s", Integer.toBinaryString(p_180523_3_)).replace(" ", "0");
               return String.format("%1$d / 0x%1$X / 0b%2$s", p_180523_3_, lvt_1_1_);
            }
         }
      });
      p_180523_0_.func_71500_a("Block location", new Callable<String>() {
         public String call() throws Exception {
            return CrashReportCategory.func_180522_a(p_180523_1_);
         }
      });
   }

   public static void func_175750_a(CrashReportCategory p_175750_0_, final BlockPos p_175750_1_, final IBlockState p_175750_2_) {
      p_175750_0_.func_71500_a("Block", new Callable<String>() {
         public String call() throws Exception {
            return p_175750_2_.toString();
         }
      });
      p_175750_0_.func_71500_a("Block location", new Callable<String>() {
         public String call() throws Exception {
            return CrashReportCategory.func_180522_a(p_175750_1_);
         }
      });
   }

   static class Entry {
      private final String field_85092_a;
      private final String field_85091_b;

      public Entry(String p_i1352_1_, Object p_i1352_2_) {
         this.field_85092_a = p_i1352_1_;
         if (p_i1352_2_ == null) {
            this.field_85091_b = "~~NULL~~";
         } else if (p_i1352_2_ instanceof Throwable) {
            Throwable lvt_3_1_ = (Throwable)p_i1352_2_;
            this.field_85091_b = "~~ERROR~~ " + lvt_3_1_.getClass().getSimpleName() + ": " + lvt_3_1_.getMessage();
         } else {
            this.field_85091_b = p_i1352_2_.toString();
         }

      }

      public String func_85089_a() {
         return this.field_85092_a;
      }

      public String func_85090_b() {
         return this.field_85091_b;
      }
   }
}
