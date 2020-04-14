package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class ItemEmptyMap extends ItemMapBase {
   protected ItemEmptyMap() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      ItemStack lvt_4_1_ = new ItemStack(Items.field_151098_aY, 1, p_77659_2_.func_72841_b("map"));
      String lvt_5_1_ = "map_" + lvt_4_1_.func_77960_j();
      MapData lvt_6_1_ = new MapData(lvt_5_1_);
      p_77659_2_.func_72823_a(lvt_5_1_, lvt_6_1_);
      lvt_6_1_.field_76197_d = 0;
      lvt_6_1_.func_176054_a(p_77659_3_.field_70165_t, p_77659_3_.field_70161_v, lvt_6_1_.field_76197_d);
      lvt_6_1_.field_76200_c = (byte)p_77659_2_.field_73011_w.func_177502_q();
      lvt_6_1_.func_76185_a();
      --p_77659_1_.field_77994_a;
      if (p_77659_1_.field_77994_a <= 0) {
         return lvt_4_1_;
      } else {
         if (!p_77659_3_.field_71071_by.func_70441_a(lvt_4_1_.func_77946_l())) {
            p_77659_3_.func_71019_a(lvt_4_1_, false);
         }

         p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
         return p_77659_1_;
      }
   }
}
