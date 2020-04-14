package net.optifine.config;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemLocator implements IObjectLocator {
   public Object getObject(ResourceLocation loc) {
      Item item = Item.func_111206_d(loc.toString());
      return item;
   }
}
