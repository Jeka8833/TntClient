package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.management.LowerStringMap;

public class ServersideAttributeMap extends BaseAttributeMap {
   private final Set<IAttributeInstance> field_111162_d = Sets.newHashSet();
   protected final Map<String, IAttributeInstance> field_111163_c = new LowerStringMap();

   public ModifiableAttributeInstance func_111151_a(IAttribute p_111151_1_) {
      return (ModifiableAttributeInstance)super.func_111151_a(p_111151_1_);
   }

   public ModifiableAttributeInstance func_111152_a(String p_111152_1_) {
      IAttributeInstance lvt_2_1_ = super.func_111152_a(p_111152_1_);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = (IAttributeInstance)this.field_111163_c.get(p_111152_1_);
      }

      return (ModifiableAttributeInstance)lvt_2_1_;
   }

   public IAttributeInstance func_111150_b(IAttribute p_111150_1_) {
      IAttributeInstance lvt_2_1_ = super.func_111150_b(p_111150_1_);
      if (p_111150_1_ instanceof RangedAttribute && ((RangedAttribute)p_111150_1_).func_111116_f() != null) {
         this.field_111163_c.put(((RangedAttribute)p_111150_1_).func_111116_f(), lvt_2_1_);
      }

      return lvt_2_1_;
   }

   protected IAttributeInstance func_180376_c(IAttribute p_180376_1_) {
      return new ModifiableAttributeInstance(this, p_180376_1_);
   }

   public void func_180794_a(IAttributeInstance p_180794_1_) {
      if (p_180794_1_.func_111123_a().func_111111_c()) {
         this.field_111162_d.add(p_180794_1_);
      }

      Iterator lvt_2_1_ = this.field_180377_c.get(p_180794_1_.func_111123_a()).iterator();

      while(lvt_2_1_.hasNext()) {
         IAttribute lvt_3_1_ = (IAttribute)lvt_2_1_.next();
         ModifiableAttributeInstance lvt_4_1_ = this.func_111151_a(lvt_3_1_);
         if (lvt_4_1_ != null) {
            lvt_4_1_.func_111131_f();
         }
      }

   }

   public Set<IAttributeInstance> func_111161_b() {
      return this.field_111162_d;
   }

   public Collection<IAttributeInstance> func_111160_c() {
      Set<IAttributeInstance> lvt_1_1_ = Sets.newHashSet();
      Iterator lvt_2_1_ = this.func_111146_a().iterator();

      while(lvt_2_1_.hasNext()) {
         IAttributeInstance lvt_3_1_ = (IAttributeInstance)lvt_2_1_.next();
         if (lvt_3_1_.func_111123_a().func_111111_c()) {
            lvt_1_1_.add(lvt_3_1_);
         }
      }

      return lvt_1_1_;
   }
}
