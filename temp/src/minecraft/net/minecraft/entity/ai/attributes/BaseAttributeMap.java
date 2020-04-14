package net.minecraft.entity.ai.attributes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.management.LowerStringMap;

public abstract class BaseAttributeMap {
   protected final Map<IAttribute, IAttributeInstance> field_111154_a = Maps.newHashMap();
   protected final Map<String, IAttributeInstance> field_111153_b = new LowerStringMap();
   protected final Multimap<IAttribute, IAttribute> field_180377_c = HashMultimap.create();

   public IAttributeInstance func_111151_a(IAttribute p_111151_1_) {
      return (IAttributeInstance)this.field_111154_a.get(p_111151_1_);
   }

   public IAttributeInstance func_111152_a(String p_111152_1_) {
      return (IAttributeInstance)this.field_111153_b.get(p_111152_1_);
   }

   public IAttributeInstance func_111150_b(IAttribute p_111150_1_) {
      if (this.field_111153_b.containsKey(p_111150_1_.func_111108_a())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         IAttributeInstance lvt_2_1_ = this.func_180376_c(p_111150_1_);
         this.field_111153_b.put(p_111150_1_.func_111108_a(), lvt_2_1_);
         this.field_111154_a.put(p_111150_1_, lvt_2_1_);

         for(IAttribute lvt_3_1_ = p_111150_1_.func_180372_d(); lvt_3_1_ != null; lvt_3_1_ = lvt_3_1_.func_180372_d()) {
            this.field_180377_c.put(lvt_3_1_, p_111150_1_);
         }

         return lvt_2_1_;
      }
   }

   protected abstract IAttributeInstance func_180376_c(IAttribute var1);

   public Collection<IAttributeInstance> func_111146_a() {
      return this.field_111153_b.values();
   }

   public void func_180794_a(IAttributeInstance p_180794_1_) {
   }

   public void func_111148_a(Multimap<String, AttributeModifier> p_111148_1_) {
      Iterator lvt_2_1_ = p_111148_1_.entries().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, AttributeModifier> lvt_3_1_ = (Entry)lvt_2_1_.next();
         IAttributeInstance lvt_4_1_ = this.func_111152_a((String)lvt_3_1_.getKey());
         if (lvt_4_1_ != null) {
            lvt_4_1_.func_111124_b((AttributeModifier)lvt_3_1_.getValue());
         }
      }

   }

   public void func_111147_b(Multimap<String, AttributeModifier> p_111147_1_) {
      Iterator lvt_2_1_ = p_111147_1_.entries().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, AttributeModifier> lvt_3_1_ = (Entry)lvt_2_1_.next();
         IAttributeInstance lvt_4_1_ = this.func_111152_a((String)lvt_3_1_.getKey());
         if (lvt_4_1_ != null) {
            lvt_4_1_.func_111124_b((AttributeModifier)lvt_3_1_.getValue());
            lvt_4_1_.func_111121_a((AttributeModifier)lvt_3_1_.getValue());
         }
      }

   }
}
