package net.minecraft.block.state.pattern;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.state.BlockWorldState;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class FactoryBlockPattern {
   private static final Joiner field_177667_a = Joiner.on(",");
   private final List<String[]> field_177665_b = Lists.newArrayList();
   private final Map<Character, Predicate<BlockWorldState>> field_177666_c = Maps.newHashMap();
   private int field_177663_d;
   private int field_177664_e;

   private FactoryBlockPattern() {
      this.field_177666_c.put(' ', Predicates.alwaysTrue());
   }

   public FactoryBlockPattern func_177659_a(String... p_177659_1_) {
      if (!ArrayUtils.isEmpty((Object[])p_177659_1_) && !StringUtils.isEmpty(p_177659_1_[0])) {
         if (this.field_177665_b.isEmpty()) {
            this.field_177663_d = p_177659_1_.length;
            this.field_177664_e = p_177659_1_[0].length();
         }

         if (p_177659_1_.length != this.field_177663_d) {
            throw new IllegalArgumentException("Expected aisle with height of " + this.field_177663_d + ", but was given one with a height of " + p_177659_1_.length + ")");
         } else {
            String[] lvt_2_1_ = p_177659_1_;
            int lvt_3_1_ = p_177659_1_.length;

            for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
               String lvt_5_1_ = lvt_2_1_[lvt_4_1_];
               if (lvt_5_1_.length() != this.field_177664_e) {
                  throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.field_177664_e + ", found one with " + lvt_5_1_.length() + ")");
               }

               char[] lvt_6_1_ = lvt_5_1_.toCharArray();
               int lvt_7_1_ = lvt_6_1_.length;

               for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
                  char lvt_9_1_ = lvt_6_1_[lvt_8_1_];
                  if (!this.field_177666_c.containsKey(lvt_9_1_)) {
                     this.field_177666_c.put(lvt_9_1_, (Object)null);
                  }
               }
            }

            this.field_177665_b.add(p_177659_1_);
            return this;
         }
      } else {
         throw new IllegalArgumentException("Empty pattern for aisle");
      }
   }

   public static FactoryBlockPattern func_177660_a() {
      return new FactoryBlockPattern();
   }

   public FactoryBlockPattern func_177662_a(char p_177662_1_, Predicate<BlockWorldState> p_177662_2_) {
      this.field_177666_c.put(p_177662_1_, p_177662_2_);
      return this;
   }

   public BlockPattern func_177661_b() {
      return new BlockPattern(this.func_177658_c());
   }

   private Predicate<BlockWorldState>[][][] func_177658_c() {
      this.func_177657_d();
      Predicate<BlockWorldState>[][][] lvt_1_1_ = (Predicate[][][])((Predicate[][][])Array.newInstance(Predicate.class, new int[]{this.field_177665_b.size(), this.field_177663_d, this.field_177664_e}));

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_177665_b.size(); ++lvt_2_1_) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_177663_d; ++lvt_3_1_) {
            for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_177664_e; ++lvt_4_1_) {
               lvt_1_1_[lvt_2_1_][lvt_3_1_][lvt_4_1_] = (Predicate)this.field_177666_c.get(((String[])this.field_177665_b.get(lvt_2_1_))[lvt_3_1_].charAt(lvt_4_1_));
            }
         }
      }

      return lvt_1_1_;
   }

   private void func_177657_d() {
      List<Character> lvt_1_1_ = Lists.newArrayList();
      Iterator lvt_2_1_ = this.field_177666_c.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<Character, Predicate<BlockWorldState>> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (lvt_3_1_.getValue() == null) {
            lvt_1_1_.add(lvt_3_1_.getKey());
         }
      }

      if (!lvt_1_1_.isEmpty()) {
         throw new IllegalStateException("Predicates for character(s) " + field_177667_a.join((Iterable)lvt_1_1_) + " are missing");
      }
   }
}
