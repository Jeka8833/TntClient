package net.minecraft.util;

public class RegistryDefaulted<K, V> extends RegistrySimple<K, V> {
   private final V field_82597_b;

   public RegistryDefaulted(V p_i1366_1_) {
      this.field_82597_b = p_i1366_1_;
   }

   public V func_82594_a(K p_82594_1_) {
      V lvt_2_1_ = super.func_82594_a(p_82594_1_);
      return lvt_2_1_ == null ? this.field_82597_b : lvt_2_1_;
   }
}
