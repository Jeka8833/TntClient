package net.minecraft.block.state;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.Cartesian;
import net.minecraft.util.MapPopulator;

public class BlockState {
   private static final Joiner field_177628_a = Joiner.on(", ");
   private static final Function<IProperty, String> field_177626_b = new Function<IProperty, String>() {
      public String apply(IProperty p_apply_1_) {
         return p_apply_1_ == null ? "<NULL>" : p_apply_1_.func_177701_a();
      }
   };
   private final Block field_177627_c;
   private final ImmutableList<IProperty> field_177624_d;
   private final ImmutableList<IBlockState> field_177625_e;

   public BlockState(Block p_i45663_1_, IProperty... p_i45663_2_) {
      this.field_177627_c = p_i45663_1_;
      Arrays.sort(p_i45663_2_, new Comparator<IProperty>() {
         public int compare(IProperty p_compare_1_, IProperty p_compare_2_) {
            return p_compare_1_.func_177701_a().compareTo(p_compare_2_.func_177701_a());
         }
      });
      this.field_177624_d = ImmutableList.copyOf((Object[])p_i45663_2_);
      Map<Map<IProperty, Comparable>, BlockState.StateImplementation> lvt_3_1_ = Maps.newLinkedHashMap();
      List<BlockState.StateImplementation> lvt_4_1_ = Lists.newArrayList();
      Iterable<List<Comparable>> lvt_5_1_ = Cartesian.func_179321_a(this.func_177620_e());
      Iterator lvt_6_2_ = lvt_5_1_.iterator();

      while(lvt_6_2_.hasNext()) {
         List<Comparable> lvt_7_1_ = (List)lvt_6_2_.next();
         Map<IProperty, Comparable> lvt_8_1_ = MapPopulator.func_179400_b(this.field_177624_d, lvt_7_1_);
         BlockState.StateImplementation lvt_9_1_ = new BlockState.StateImplementation(p_i45663_1_, ImmutableMap.copyOf(lvt_8_1_));
         lvt_3_1_.put(lvt_8_1_, lvt_9_1_);
         lvt_4_1_.add(lvt_9_1_);
      }

      lvt_6_2_ = lvt_4_1_.iterator();

      while(lvt_6_2_.hasNext()) {
         BlockState.StateImplementation lvt_7_2_ = (BlockState.StateImplementation)lvt_6_2_.next();
         lvt_7_2_.func_177235_a(lvt_3_1_);
      }

      this.field_177625_e = ImmutableList.copyOf((Collection)lvt_4_1_);
   }

   public ImmutableList<IBlockState> func_177619_a() {
      return this.field_177625_e;
   }

   private List<Iterable<Comparable>> func_177620_e() {
      List<Iterable<Comparable>> lvt_1_1_ = Lists.newArrayList();

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_177624_d.size(); ++lvt_2_1_) {
         lvt_1_1_.add(((IProperty)this.field_177624_d.get(lvt_2_1_)).func_177700_c());
      }

      return lvt_1_1_;
   }

   public IBlockState func_177621_b() {
      return (IBlockState)this.field_177625_e.get(0);
   }

   public Block func_177622_c() {
      return this.field_177627_c;
   }

   public Collection<IProperty> func_177623_d() {
      return this.field_177624_d;
   }

   public String toString() {
      return Objects.toStringHelper((Object)this).add("block", Block.field_149771_c.func_177774_c(this.field_177627_c)).add("properties", Iterables.transform(this.field_177624_d, field_177626_b)).toString();
   }

   static class StateImplementation extends BlockStateBase {
      private final Block field_177239_a;
      private final ImmutableMap<IProperty, Comparable> field_177237_b;
      private ImmutableTable<IProperty, Comparable, IBlockState> field_177238_c;

      private StateImplementation(Block p_i45660_1_, ImmutableMap<IProperty, Comparable> p_i45660_2_) {
         this.field_177239_a = p_i45660_1_;
         this.field_177237_b = p_i45660_2_;
      }

      public Collection<IProperty> func_177227_a() {
         return Collections.unmodifiableCollection(this.field_177237_b.keySet());
      }

      public <T extends Comparable<T>> T func_177229_b(IProperty<T> p_177229_1_) {
         if (!this.field_177237_b.containsKey(p_177229_1_)) {
            throw new IllegalArgumentException("Cannot get property " + p_177229_1_ + " as it does not exist in " + this.field_177239_a.func_176194_O());
         } else {
            return (Comparable)p_177229_1_.func_177699_b().cast(this.field_177237_b.get(p_177229_1_));
         }
      }

      public <T extends Comparable<T>, V extends T> IBlockState func_177226_a(IProperty<T> p_177226_1_, V p_177226_2_) {
         if (!this.field_177237_b.containsKey(p_177226_1_)) {
            throw new IllegalArgumentException("Cannot set property " + p_177226_1_ + " as it does not exist in " + this.field_177239_a.func_176194_O());
         } else if (!p_177226_1_.func_177700_c().contains(p_177226_2_)) {
            throw new IllegalArgumentException("Cannot set property " + p_177226_1_ + " to " + p_177226_2_ + " on block " + Block.field_149771_c.func_177774_c(this.field_177239_a) + ", it is not an allowed value");
         } else {
            return (IBlockState)(this.field_177237_b.get(p_177226_1_) == p_177226_2_ ? this : (IBlockState)this.field_177238_c.get(p_177226_1_, p_177226_2_));
         }
      }

      public ImmutableMap<IProperty, Comparable> func_177228_b() {
         return this.field_177237_b;
      }

      public Block func_177230_c() {
         return this.field_177239_a;
      }

      public boolean equals(Object p_equals_1_) {
         return this == p_equals_1_;
      }

      public int hashCode() {
         return this.field_177237_b.hashCode();
      }

      public void func_177235_a(Map<Map<IProperty, Comparable>, BlockState.StateImplementation> p_177235_1_) {
         if (this.field_177238_c != null) {
            throw new IllegalStateException();
         } else {
            Table<IProperty, Comparable, IBlockState> lvt_2_1_ = HashBasedTable.create();
            Iterator lvt_3_1_ = this.field_177237_b.keySet().iterator();

            while(lvt_3_1_.hasNext()) {
               IProperty<? extends Comparable> lvt_4_1_ = (IProperty)lvt_3_1_.next();
               Iterator lvt_5_1_ = lvt_4_1_.func_177700_c().iterator();

               while(lvt_5_1_.hasNext()) {
                  Comparable lvt_6_1_ = (Comparable)lvt_5_1_.next();
                  if (lvt_6_1_ != this.field_177237_b.get(lvt_4_1_)) {
                     lvt_2_1_.put(lvt_4_1_, lvt_6_1_, p_177235_1_.get(this.func_177236_b(lvt_4_1_, lvt_6_1_)));
                  }
               }
            }

            this.field_177238_c = ImmutableTable.copyOf(lvt_2_1_);
         }
      }

      private Map<IProperty, Comparable> func_177236_b(IProperty p_177236_1_, Comparable p_177236_2_) {
         Map<IProperty, Comparable> lvt_3_1_ = Maps.newHashMap(this.field_177237_b);
         lvt_3_1_.put(p_177236_1_, p_177236_2_);
         return lvt_3_1_;
      }
   }
}
