package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class StateMap extends StateMapperBase {
   private final IProperty<?> field_178142_a;
   private final String field_178141_c;
   private final List<IProperty<?>> field_178140_d;

   private StateMap(IProperty<?> p_i46210_1_, String p_i46210_2_, List<IProperty<?>> p_i46210_3_) {
      this.field_178142_a = p_i46210_1_;
      this.field_178141_c = p_i46210_2_;
      this.field_178140_d = p_i46210_3_;
   }

   protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
      Map<IProperty, Comparable> lvt_2_1_ = Maps.newLinkedHashMap(p_178132_1_.func_177228_b());
      String lvt_3_2_;
      if (this.field_178142_a == null) {
         lvt_3_2_ = ((ResourceLocation)Block.field_149771_c.func_177774_c(p_178132_1_.func_177230_c())).toString();
      } else {
         lvt_3_2_ = this.field_178142_a.func_177702_a((Comparable)lvt_2_1_.remove(this.field_178142_a));
      }

      if (this.field_178141_c != null) {
         lvt_3_2_ = lvt_3_2_ + this.field_178141_c;
      }

      Iterator lvt_4_1_ = this.field_178140_d.iterator();

      while(lvt_4_1_.hasNext()) {
         IProperty<?> lvt_5_1_ = (IProperty)lvt_4_1_.next();
         lvt_2_1_.remove(lvt_5_1_);
      }

      return new ModelResourceLocation(lvt_3_2_, this.func_178131_a(lvt_2_1_));
   }

   public static class Builder {
      private IProperty<?> field_178445_a;
      private String field_178443_b;
      private final List<IProperty<?>> field_178444_c = Lists.newArrayList();

      public StateMap.Builder func_178440_a(IProperty<?> p_178440_1_) {
         this.field_178445_a = p_178440_1_;
         return this;
      }

      public StateMap.Builder func_178439_a(String p_178439_1_) {
         this.field_178443_b = p_178439_1_;
         return this;
      }

      public StateMap.Builder func_178442_a(IProperty<?>... p_178442_1_) {
         Collections.addAll(this.field_178444_c, p_178442_1_);
         return this;
      }

      public StateMap func_178441_a() {
         return new StateMap(this.field_178445_a, this.field_178443_b, this.field_178444_c);
      }
   }
}
