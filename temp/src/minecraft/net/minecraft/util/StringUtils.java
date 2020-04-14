package net.minecraft.util;

import java.util.regex.Pattern;

public class StringUtils {
   private static final Pattern field_76339_a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

   public static String func_76337_a(int p_76337_0_) {
      int lvt_1_1_ = p_76337_0_ / 20;
      int lvt_2_1_ = lvt_1_1_ / 60;
      lvt_1_1_ %= 60;
      return lvt_1_1_ < 10 ? lvt_2_1_ + ":0" + lvt_1_1_ : lvt_2_1_ + ":" + lvt_1_1_;
   }

   public static String func_76338_a(String p_76338_0_) {
      return field_76339_a.matcher(p_76338_0_).replaceAll("");
   }

   public static boolean func_151246_b(String p_151246_0_) {
      return org.apache.commons.lang3.StringUtils.isEmpty(p_151246_0_);
   }
}
