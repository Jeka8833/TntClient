package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ModifiableAttributeInstance implements IAttributeInstance {
   private final BaseAttributeMap field_111138_a;
   private final IAttribute field_111136_b;
   private final Map<Integer, Set<AttributeModifier>> field_111137_c = Maps.newHashMap();
   private final Map<String, Set<AttributeModifier>> field_111134_d = Maps.newHashMap();
   private final Map<UUID, AttributeModifier> field_111135_e = Maps.newHashMap();
   private double field_111132_f;
   private boolean field_111133_g = true;
   private double field_111139_h;

   public ModifiableAttributeInstance(BaseAttributeMap p_i1608_1_, IAttribute p_i1608_2_) {
      this.field_111138_a = p_i1608_1_;
      this.field_111136_b = p_i1608_2_;
      this.field_111132_f = p_i1608_2_.func_111110_b();

      for(int lvt_3_1_ = 0; lvt_3_1_ < 3; ++lvt_3_1_) {
         this.field_111137_c.put(lvt_3_1_, Sets.newHashSet());
      }

   }

   public IAttribute func_111123_a() {
      return this.field_111136_b;
   }

   public double func_111125_b() {
      return this.field_111132_f;
   }

   public void func_111128_a(double p_111128_1_) {
      if (p_111128_1_ != this.func_111125_b()) {
         this.field_111132_f = p_111128_1_;
         this.func_111131_f();
      }
   }

   public Collection<AttributeModifier> func_111130_a(int p_111130_1_) {
      return (Collection)this.field_111137_c.get(p_111130_1_);
   }

   public Collection<AttributeModifier> func_111122_c() {
      Set<AttributeModifier> lvt_1_1_ = Sets.newHashSet();

      for(int lvt_2_1_ = 0; lvt_2_1_ < 3; ++lvt_2_1_) {
         lvt_1_1_.addAll(this.func_111130_a(lvt_2_1_));
      }

      return lvt_1_1_;
   }

   public AttributeModifier func_111127_a(UUID p_111127_1_) {
      return (AttributeModifier)this.field_111135_e.get(p_111127_1_);
   }

   public boolean func_180374_a(AttributeModifier p_180374_1_) {
      return this.field_111135_e.get(p_180374_1_.func_111167_a()) != null;
   }

   public void func_111121_a(AttributeModifier p_111121_1_) {
      if (this.func_111127_a(p_111121_1_.func_111167_a()) != null) {
         throw new IllegalArgumentException("Modifier is already applied on this attribute!");
      } else {
         Set<AttributeModifier> lvt_2_1_ = (Set)this.field_111134_d.get(p_111121_1_.func_111166_b());
         if (lvt_2_1_ == null) {
            lvt_2_1_ = Sets.newHashSet();
            this.field_111134_d.put(p_111121_1_.func_111166_b(), lvt_2_1_);
         }

         ((Set)this.field_111137_c.get(p_111121_1_.func_111169_c())).add(p_111121_1_);
         ((Set)lvt_2_1_).add(p_111121_1_);
         this.field_111135_e.put(p_111121_1_.func_111167_a(), p_111121_1_);
         this.func_111131_f();
      }
   }

   protected void func_111131_f() {
      this.field_111133_g = true;
      this.field_111138_a.func_180794_a(this);
   }

   public void func_111124_b(AttributeModifier p_111124_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < 3; ++lvt_2_1_) {
         Set<AttributeModifier> lvt_3_1_ = (Set)this.field_111137_c.get(lvt_2_1_);
         lvt_3_1_.remove(p_111124_1_);
      }

      Set<AttributeModifier> lvt_2_2_ = (Set)this.field_111134_d.get(p_111124_1_.func_111166_b());
      if (lvt_2_2_ != null) {
         lvt_2_2_.remove(p_111124_1_);
         if (lvt_2_2_.isEmpty()) {
            this.field_111134_d.remove(p_111124_1_.func_111166_b());
         }
      }

      this.field_111135_e.remove(p_111124_1_.func_111167_a());
      this.func_111131_f();
   }

   public void func_142049_d() {
      Collection<AttributeModifier> lvt_1_1_ = this.func_111122_c();
      if (lvt_1_1_ != null) {
         Collection<AttributeModifier> lvt_1_1_ = Lists.newArrayList((Iterable)lvt_1_1_);
         Iterator lvt_2_1_ = lvt_1_1_.iterator();

         while(lvt_2_1_.hasNext()) {
            AttributeModifier lvt_3_1_ = (AttributeModifier)lvt_2_1_.next();
            this.func_111124_b(lvt_3_1_);
         }

      }
   }

   public double func_111126_e() {
      if (this.field_111133_g) {
         this.field_111139_h = this.func_111129_g();
         this.field_111133_g = false;
      }

      return this.field_111139_h;
   }

   private double func_111129_g() {
      double lvt_1_1_ = this.func_111125_b();

      AttributeModifier lvt_4_1_;
      for(Iterator lvt_3_1_ = this.func_180375_b(0).iterator(); lvt_3_1_.hasNext(); lvt_1_1_ += lvt_4_1_.func_111164_d()) {
         lvt_4_1_ = (AttributeModifier)lvt_3_1_.next();
      }

      double lvt_3_2_ = lvt_1_1_;

      Iterator lvt_5_2_;
      AttributeModifier lvt_6_2_;
      for(lvt_5_2_ = this.func_180375_b(1).iterator(); lvt_5_2_.hasNext(); lvt_3_2_ += lvt_1_1_ * lvt_6_2_.func_111164_d()) {
         lvt_6_2_ = (AttributeModifier)lvt_5_2_.next();
      }

      for(lvt_5_2_ = this.func_180375_b(2).iterator(); lvt_5_2_.hasNext(); lvt_3_2_ *= 1.0D + lvt_6_2_.func_111164_d()) {
         lvt_6_2_ = (AttributeModifier)lvt_5_2_.next();
      }

      return this.field_111136_b.func_111109_a(lvt_3_2_);
   }

   private Collection<AttributeModifier> func_180375_b(int p_180375_1_) {
      Set<AttributeModifier> lvt_2_1_ = Sets.newHashSet((Iterable)this.func_111130_a(p_180375_1_));

      for(IAttribute lvt_3_1_ = this.field_111136_b.func_180372_d(); lvt_3_1_ != null; lvt_3_1_ = lvt_3_1_.func_180372_d()) {
         IAttributeInstance lvt_4_1_ = this.field_111138_a.func_111151_a(lvt_3_1_);
         if (lvt_4_1_ != null) {
            lvt_2_1_.addAll(lvt_4_1_.func_111130_a(p_180375_1_));
         }
      }

      return lvt_2_1_;
   }
}
