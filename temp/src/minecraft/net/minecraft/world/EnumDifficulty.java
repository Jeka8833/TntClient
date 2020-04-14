package net.minecraft.world;

public enum EnumDifficulty {
   PEACEFUL(0, "options.difficulty.peaceful"),
   EASY(1, "options.difficulty.easy"),
   NORMAL(2, "options.difficulty.normal"),
   HARD(3, "options.difficulty.hard");

   private static final EnumDifficulty[] field_151530_e = new EnumDifficulty[values().length];
   private final int field_151527_f;
   private final String field_151528_g;

   private EnumDifficulty(int p_i45312_3_, String p_i45312_4_) {
      this.field_151527_f = p_i45312_3_;
      this.field_151528_g = p_i45312_4_;
   }

   public int func_151525_a() {
      return this.field_151527_f;
   }

   public static EnumDifficulty func_151523_a(int p_151523_0_) {
      return field_151530_e[p_151523_0_ % field_151530_e.length];
   }

   public String func_151526_b() {
      return this.field_151528_g;
   }

   static {
      EnumDifficulty[] lvt_0_1_ = values();
      int lvt_1_1_ = lvt_0_1_.length;

      for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
         EnumDifficulty lvt_3_1_ = lvt_0_1_[lvt_2_1_];
         field_151530_e[lvt_3_1_.field_151527_f] = lvt_3_1_;
      }

   }
}
