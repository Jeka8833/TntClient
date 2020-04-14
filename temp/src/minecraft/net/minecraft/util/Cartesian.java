package net.minecraft.util;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Cartesian {
   public static <T> Iterable<T[]> func_179318_a(Class<T> p_179318_0_, Iterable<? extends Iterable<? extends T>> p_179318_1_) {
      return new Cartesian.Product(p_179318_0_, (Iterable[])func_179322_b(Iterable.class, p_179318_1_));
   }

   public static <T> Iterable<List<T>> func_179321_a(Iterable<? extends Iterable<? extends T>> p_179321_0_) {
      return func_179323_b(func_179318_a(Object.class, p_179321_0_));
   }

   private static <T> Iterable<List<T>> func_179323_b(Iterable<Object[]> p_179323_0_) {
      return Iterables.transform(p_179323_0_, new Cartesian.GetList());
   }

   private static <T> T[] func_179322_b(Class<? super T> p_179322_0_, Iterable<? extends T> p_179322_1_) {
      List<T> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = p_179322_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         T lvt_4_1_ = lvt_3_1_.next();
         lvt_2_1_.add(lvt_4_1_);
      }

      return (Object[])lvt_2_1_.toArray(func_179319_b(p_179322_0_, lvt_2_1_.size()));
   }

   private static <T> T[] func_179319_b(Class<? super T> p_179319_0_, int p_179319_1_) {
      return (Object[])((Object[])Array.newInstance(p_179319_0_, p_179319_1_));
   }

   static class Product<T> implements Iterable<T[]> {
      private final Class<T> field_179429_a;
      private final Iterable<? extends T>[] field_179428_b;

      private Product(Class<T> p_i46020_1_, Iterable<? extends T>[] p_i46020_2_) {
         this.field_179429_a = p_i46020_1_;
         this.field_179428_b = p_i46020_2_;
      }

      public Iterator<T[]> iterator() {
         return (Iterator)(this.field_179428_b.length <= 0 ? Collections.singletonList((Object[])Cartesian.func_179319_b(this.field_179429_a, 0)).iterator() : new Cartesian.Product.ProductIterator(this.field_179429_a, this.field_179428_b));
      }

      static class ProductIterator<T> extends UnmodifiableIterator<T[]> {
         private int field_179426_a;
         private final Iterable<? extends T>[] field_179424_b;
         private final Iterator<? extends T>[] field_179425_c;
         private final T[] field_179423_d;

         private ProductIterator(Class<T> p_i46018_1_, Iterable<? extends T>[] p_i46018_2_) {
            this.field_179426_a = -2;
            this.field_179424_b = p_i46018_2_;
            this.field_179425_c = (Iterator[])Cartesian.func_179319_b(Iterator.class, this.field_179424_b.length);

            for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_179424_b.length; ++lvt_3_1_) {
               this.field_179425_c[lvt_3_1_] = p_i46018_2_[lvt_3_1_].iterator();
            }

            this.field_179423_d = Cartesian.func_179319_b(p_i46018_1_, this.field_179425_c.length);
         }

         private void func_179422_b() {
            this.field_179426_a = -1;
            Arrays.fill(this.field_179425_c, (Object)null);
            Arrays.fill(this.field_179423_d, (Object)null);
         }

         public boolean hasNext() {
            if (this.field_179426_a == -2) {
               this.field_179426_a = 0;
               Iterator[] lvt_1_1_ = this.field_179425_c;
               int lvt_2_1_ = lvt_1_1_.length;

               for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
                  Iterator<? extends T> lvt_4_1_ = lvt_1_1_[lvt_3_1_];
                  if (!lvt_4_1_.hasNext()) {
                     this.func_179422_b();
                     break;
                  }
               }

               return true;
            } else {
               if (this.field_179426_a >= this.field_179425_c.length) {
                  for(this.field_179426_a = this.field_179425_c.length - 1; this.field_179426_a >= 0; --this.field_179426_a) {
                     Iterator<? extends T> lvt_1_2_ = this.field_179425_c[this.field_179426_a];
                     if (lvt_1_2_.hasNext()) {
                        break;
                     }

                     if (this.field_179426_a == 0) {
                        this.func_179422_b();
                        break;
                     }

                     lvt_1_2_ = this.field_179424_b[this.field_179426_a].iterator();
                     this.field_179425_c[this.field_179426_a] = lvt_1_2_;
                     if (!lvt_1_2_.hasNext()) {
                        this.func_179422_b();
                        break;
                     }
                  }
               }

               return this.field_179426_a >= 0;
            }
         }

         public T[] next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               while(this.field_179426_a < this.field_179425_c.length) {
                  this.field_179423_d[this.field_179426_a] = this.field_179425_c[this.field_179426_a].next();
                  ++this.field_179426_a;
               }

               return (Object[])this.field_179423_d.clone();
            }
         }
      }
   }

   static class GetList<T> implements Function<Object[], List<T>> {
      private GetList() {
      }

      public List<T> apply(Object[] p_apply_1_) {
         return Arrays.asList((Object[])p_apply_1_);
      }
   }
}
