package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class FlatGeneratorInfo {
   private final List<FlatLayerInfo> field_82655_a = Lists.newArrayList();
   private final Map<String, Map<String, String>> field_82653_b = Maps.newHashMap();
   private int field_82654_c;

   public int func_82648_a() {
      return this.field_82654_c;
   }

   public void func_82647_a(int p_82647_1_) {
      this.field_82654_c = p_82647_1_;
   }

   public Map<String, Map<String, String>> func_82644_b() {
      return this.field_82653_b;
   }

   public List<FlatLayerInfo> func_82650_c() {
      return this.field_82655_a;
   }

   public void func_82645_d() {
      int lvt_1_1_ = 0;

      FlatLayerInfo lvt_3_1_;
      for(Iterator lvt_2_1_ = this.field_82655_a.iterator(); lvt_2_1_.hasNext(); lvt_1_1_ += lvt_3_1_.func_82657_a()) {
         lvt_3_1_ = (FlatLayerInfo)lvt_2_1_.next();
         lvt_3_1_.func_82660_d(lvt_1_1_);
      }

   }

   public String toString() {
      StringBuilder lvt_1_1_ = new StringBuilder();
      lvt_1_1_.append(3);
      lvt_1_1_.append(";");

      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_82655_a.size(); ++lvt_2_2_) {
         if (lvt_2_2_ > 0) {
            lvt_1_1_.append(",");
         }

         lvt_1_1_.append(((FlatLayerInfo)this.field_82655_a.get(lvt_2_2_)).toString());
      }

      lvt_1_1_.append(";");
      lvt_1_1_.append(this.field_82654_c);
      if (!this.field_82653_b.isEmpty()) {
         lvt_1_1_.append(";");
         lvt_2_2_ = 0;
         Iterator lvt_3_1_ = this.field_82653_b.entrySet().iterator();

         while(true) {
            Map lvt_5_1_;
            do {
               if (!lvt_3_1_.hasNext()) {
                  return lvt_1_1_.toString();
               }

               Entry<String, Map<String, String>> lvt_4_1_ = (Entry)lvt_3_1_.next();
               if (lvt_2_2_++ > 0) {
                  lvt_1_1_.append(",");
               }

               lvt_1_1_.append(((String)lvt_4_1_.getKey()).toLowerCase());
               lvt_5_1_ = (Map)lvt_4_1_.getValue();
            } while(lvt_5_1_.isEmpty());

            lvt_1_1_.append("(");
            int lvt_6_1_ = 0;
            Iterator lvt_7_1_ = lvt_5_1_.entrySet().iterator();

            while(lvt_7_1_.hasNext()) {
               Entry<String, String> lvt_8_1_ = (Entry)lvt_7_1_.next();
               if (lvt_6_1_++ > 0) {
                  lvt_1_1_.append(" ");
               }

               lvt_1_1_.append((String)lvt_8_1_.getKey());
               lvt_1_1_.append("=");
               lvt_1_1_.append((String)lvt_8_1_.getValue());
            }

            lvt_1_1_.append(")");
         }
      } else {
         lvt_1_1_.append(";");
         return lvt_1_1_.toString();
      }
   }

   private static FlatLayerInfo func_180715_a(int p_180715_0_, String p_180715_1_, int p_180715_2_) {
      String[] lvt_3_1_ = p_180715_0_ >= 3 ? p_180715_1_.split("\\*", 2) : p_180715_1_.split("x", 2);
      int lvt_4_1_ = 1;
      int lvt_5_1_ = 0;
      if (lvt_3_1_.length == 2) {
         try {
            lvt_4_1_ = Integer.parseInt(lvt_3_1_[0]);
            if (p_180715_2_ + lvt_4_1_ >= 256) {
               lvt_4_1_ = 256 - p_180715_2_;
            }

            if (lvt_4_1_ < 0) {
               lvt_4_1_ = 0;
            }
         } catch (Throwable var8) {
            return null;
         }
      }

      Block lvt_6_2_ = null;

      try {
         String lvt_7_1_ = lvt_3_1_[lvt_3_1_.length - 1];
         if (p_180715_0_ < 3) {
            lvt_3_1_ = lvt_7_1_.split(":", 2);
            if (lvt_3_1_.length > 1) {
               lvt_5_1_ = Integer.parseInt(lvt_3_1_[1]);
            }

            lvt_6_2_ = Block.func_149729_e(Integer.parseInt(lvt_3_1_[0]));
         } else {
            lvt_3_1_ = lvt_7_1_.split(":", 3);
            lvt_6_2_ = lvt_3_1_.length > 1 ? Block.func_149684_b(lvt_3_1_[0] + ":" + lvt_3_1_[1]) : null;
            if (lvt_6_2_ != null) {
               lvt_5_1_ = lvt_3_1_.length > 2 ? Integer.parseInt(lvt_3_1_[2]) : 0;
            } else {
               lvt_6_2_ = Block.func_149684_b(lvt_3_1_[0]);
               if (lvt_6_2_ != null) {
                  lvt_5_1_ = lvt_3_1_.length > 1 ? Integer.parseInt(lvt_3_1_[1]) : 0;
               }
            }

            if (lvt_6_2_ == null) {
               return null;
            }
         }

         if (lvt_6_2_ == Blocks.field_150350_a) {
            lvt_5_1_ = 0;
         }

         if (lvt_5_1_ < 0 || lvt_5_1_ > 15) {
            lvt_5_1_ = 0;
         }
      } catch (Throwable var9) {
         return null;
      }

      FlatLayerInfo lvt_7_3_ = new FlatLayerInfo(p_180715_0_, lvt_4_1_, lvt_6_2_, lvt_5_1_);
      lvt_7_3_.func_82660_d(p_180715_2_);
      return lvt_7_3_;
   }

   private static List<FlatLayerInfo> func_180716_a(int p_180716_0_, String p_180716_1_) {
      if (p_180716_1_ != null && p_180716_1_.length() >= 1) {
         List<FlatLayerInfo> lvt_2_1_ = Lists.newArrayList();
         String[] lvt_3_1_ = p_180716_1_.split(",");
         int lvt_4_1_ = 0;
         String[] lvt_5_1_ = lvt_3_1_;
         int lvt_6_1_ = lvt_3_1_.length;

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
            String lvt_8_1_ = lvt_5_1_[lvt_7_1_];
            FlatLayerInfo lvt_9_1_ = func_180715_a(p_180716_0_, lvt_8_1_, lvt_4_1_);
            if (lvt_9_1_ == null) {
               return null;
            }

            lvt_2_1_.add(lvt_9_1_);
            lvt_4_1_ += lvt_9_1_.func_82657_a();
         }

         return lvt_2_1_;
      } else {
         return null;
      }
   }

   public static FlatGeneratorInfo func_82651_a(String p_82651_0_) {
      if (p_82651_0_ == null) {
         return func_82649_e();
      } else {
         String[] lvt_1_1_ = p_82651_0_.split(";", -1);
         int lvt_2_1_ = lvt_1_1_.length == 1 ? 0 : MathHelper.func_82715_a(lvt_1_1_[0], 0);
         if (lvt_2_1_ >= 0 && lvt_2_1_ <= 3) {
            FlatGeneratorInfo lvt_3_1_ = new FlatGeneratorInfo();
            int lvt_4_1_ = lvt_1_1_.length == 1 ? 0 : 1;
            List<FlatLayerInfo> lvt_5_1_ = func_180716_a(lvt_2_1_, lvt_1_1_[lvt_4_1_++]);
            if (lvt_5_1_ != null && !lvt_5_1_.isEmpty()) {
               lvt_3_1_.func_82650_c().addAll(lvt_5_1_);
               lvt_3_1_.func_82645_d();
               int lvt_6_1_ = BiomeGenBase.field_76772_c.field_76756_M;
               if (lvt_2_1_ > 0 && lvt_1_1_.length > lvt_4_1_) {
                  lvt_6_1_ = MathHelper.func_82715_a(lvt_1_1_[lvt_4_1_++], lvt_6_1_);
               }

               lvt_3_1_.func_82647_a(lvt_6_1_);
               if (lvt_2_1_ > 0 && lvt_1_1_.length > lvt_4_1_) {
                  String[] lvt_7_1_ = lvt_1_1_[lvt_4_1_++].toLowerCase().split(",");
                  String[] lvt_8_1_ = lvt_7_1_;
                  int lvt_9_1_ = lvt_7_1_.length;

                  for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_9_1_; ++lvt_10_1_) {
                     String lvt_11_1_ = lvt_8_1_[lvt_10_1_];
                     String[] lvt_12_1_ = lvt_11_1_.split("\\(", 2);
                     Map<String, String> lvt_13_1_ = Maps.newHashMap();
                     if (lvt_12_1_[0].length() > 0) {
                        lvt_3_1_.func_82644_b().put(lvt_12_1_[0], lvt_13_1_);
                        if (lvt_12_1_.length > 1 && lvt_12_1_[1].endsWith(")") && lvt_12_1_[1].length() > 1) {
                           String[] lvt_14_1_ = lvt_12_1_[1].substring(0, lvt_12_1_[1].length() - 1).split(" ");

                           for(int lvt_15_1_ = 0; lvt_15_1_ < lvt_14_1_.length; ++lvt_15_1_) {
                              String[] lvt_16_1_ = lvt_14_1_[lvt_15_1_].split("=", 2);
                              if (lvt_16_1_.length == 2) {
                                 lvt_13_1_.put(lvt_16_1_[0], lvt_16_1_[1]);
                              }
                           }
                        }
                     }
                  }
               } else {
                  lvt_3_1_.func_82644_b().put("village", Maps.newHashMap());
               }

               return lvt_3_1_;
            } else {
               return func_82649_e();
            }
         } else {
            return func_82649_e();
         }
      }
   }

   public static FlatGeneratorInfo func_82649_e() {
      FlatGeneratorInfo lvt_0_1_ = new FlatGeneratorInfo();
      lvt_0_1_.func_82647_a(BiomeGenBase.field_76772_c.field_76756_M);
      lvt_0_1_.func_82650_c().add(new FlatLayerInfo(1, Blocks.field_150357_h));
      lvt_0_1_.func_82650_c().add(new FlatLayerInfo(2, Blocks.field_150346_d));
      lvt_0_1_.func_82650_c().add(new FlatLayerInfo(1, Blocks.field_150349_c));
      lvt_0_1_.func_82645_d();
      lvt_0_1_.func_82644_b().put("village", Maps.newHashMap());
      return lvt_0_1_;
   }
}
