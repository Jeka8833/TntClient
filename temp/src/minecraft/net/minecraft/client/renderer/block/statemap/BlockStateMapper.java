package net.minecraft.client.renderer.block.statemap;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class BlockStateMapper {
   private Map<Block, IStateMapper> field_178450_a = Maps.newIdentityHashMap();
   private Set<Block> field_178449_b = Sets.newIdentityHashSet();

   public void func_178447_a(Block p_178447_1_, IStateMapper p_178447_2_) {
      this.field_178450_a.put(p_178447_1_, p_178447_2_);
   }

   public void func_178448_a(Block... p_178448_1_) {
      Collections.addAll(this.field_178449_b, p_178448_1_);
   }

   public Map<IBlockState, ModelResourceLocation> func_178446_a() {
      Map<IBlockState, ModelResourceLocation> lvt_1_1_ = Maps.newIdentityHashMap();
      Iterator lvt_2_1_ = Block.field_149771_c.iterator();

      while(lvt_2_1_.hasNext()) {
         Block lvt_3_1_ = (Block)lvt_2_1_.next();
         if (!this.field_178449_b.contains(lvt_3_1_)) {
            lvt_1_1_.putAll(((IStateMapper)Objects.firstNonNull(this.field_178450_a.get(lvt_3_1_), new DefaultStateMapper())).func_178130_a(lvt_3_1_));
         }
      }

      return lvt_1_1_;
   }
}
