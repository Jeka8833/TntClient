package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public abstract class StateMapperBase implements IStateMapper {
   protected Map<IBlockState, ModelResourceLocation> field_178133_b = Maps.newLinkedHashMap();

   public String func_178131_a(Map<IProperty, Comparable> p_178131_1_) {
      StringBuilder lvt_2_1_ = new StringBuilder();
      Iterator lvt_3_1_ = p_178131_1_.entrySet().iterator();

      while(lvt_3_1_.hasNext()) {
         Entry<IProperty, Comparable> lvt_4_1_ = (Entry)lvt_3_1_.next();
         if (lvt_2_1_.length() != 0) {
            lvt_2_1_.append(",");
         }

         IProperty lvt_5_1_ = (IProperty)lvt_4_1_.getKey();
         Comparable lvt_6_1_ = (Comparable)lvt_4_1_.getValue();
         lvt_2_1_.append(lvt_5_1_.func_177701_a());
         lvt_2_1_.append("=");
         lvt_2_1_.append(lvt_5_1_.func_177702_a(lvt_6_1_));
      }

      if (lvt_2_1_.length() == 0) {
         lvt_2_1_.append("normal");
      }

      return lvt_2_1_.toString();
   }

   public Map<IBlockState, ModelResourceLocation> func_178130_a(Block p_178130_1_) {
      Iterator lvt_2_1_ = p_178130_1_.func_176194_O().func_177619_a().iterator();

      while(lvt_2_1_.hasNext()) {
         IBlockState lvt_3_1_ = (IBlockState)lvt_2_1_.next();
         this.field_178133_b.put(lvt_3_1_, this.func_178132_a(lvt_3_1_));
      }

      return this.field_178133_b;
   }

   protected abstract ModelResourceLocation func_178132_a(IBlockState var1);
}
