package net.optifine.config;

import net.minecraft.enchantment.Enchantment;

public class ParserEnchantmentId implements IParserInt {
   public int parse(String str, int defVal) {
      Enchantment en = Enchantment.func_180305_b(str);
      return en == null ? defVal : en.field_77352_x;
   }
}
