package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandResultStats {
   private static final int field_179676_a = CommandResultStats.Type.values().length;
   private static final String[] field_179674_b;
   private String[] field_179675_c;
   private String[] field_179673_d;

   public CommandResultStats() {
      this.field_179675_c = field_179674_b;
      this.field_179673_d = field_179674_b;
   }

   public void func_179672_a(final ICommandSender p_179672_1_, CommandResultStats.Type p_179672_2_, int p_179672_3_) {
      String lvt_4_1_ = this.field_179675_c[p_179672_2_.func_179636_a()];
      if (lvt_4_1_ != null) {
         ICommandSender lvt_5_1_ = new ICommandSender() {
            public String func_70005_c_() {
               return p_179672_1_.func_70005_c_();
            }

            public IChatComponent func_145748_c_() {
               return p_179672_1_.func_145748_c_();
            }

            public void func_145747_a(IChatComponent p_145747_1_) {
               p_179672_1_.func_145747_a(p_145747_1_);
            }

            public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
               return true;
            }

            public BlockPos func_180425_c() {
               return p_179672_1_.func_180425_c();
            }

            public Vec3 func_174791_d() {
               return p_179672_1_.func_174791_d();
            }

            public World func_130014_f_() {
               return p_179672_1_.func_130014_f_();
            }

            public Entity func_174793_f() {
               return p_179672_1_.func_174793_f();
            }

            public boolean func_174792_t_() {
               return p_179672_1_.func_174792_t_();
            }

            public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
               p_179672_1_.func_174794_a(p_174794_1_, p_174794_2_);
            }
         };

         String lvt_6_2_;
         try {
            lvt_6_2_ = CommandBase.func_175758_e(lvt_5_1_, lvt_4_1_);
         } catch (EntityNotFoundException var11) {
            return;
         }

         String lvt_7_2_ = this.field_179673_d[p_179672_2_.func_179636_a()];
         if (lvt_7_2_ != null) {
            Scoreboard lvt_8_1_ = p_179672_1_.func_130014_f_().func_96441_U();
            ScoreObjective lvt_9_1_ = lvt_8_1_.func_96518_b(lvt_7_2_);
            if (lvt_9_1_ != null) {
               if (lvt_8_1_.func_178819_b(lvt_6_2_, lvt_9_1_)) {
                  Score lvt_10_1_ = lvt_8_1_.func_96529_a(lvt_6_2_, lvt_9_1_);
                  lvt_10_1_.func_96647_c(p_179672_3_);
               }
            }
         }
      }
   }

   public void func_179668_a(NBTTagCompound p_179668_1_) {
      if (p_179668_1_.func_150297_b("CommandStats", 10)) {
         NBTTagCompound lvt_2_1_ = p_179668_1_.func_74775_l("CommandStats");
         CommandResultStats.Type[] lvt_3_1_ = CommandResultStats.Type.values();
         int lvt_4_1_ = lvt_3_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            CommandResultStats.Type lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            String lvt_7_1_ = lvt_6_1_.func_179637_b() + "Name";
            String lvt_8_1_ = lvt_6_1_.func_179637_b() + "Objective";
            if (lvt_2_1_.func_150297_b(lvt_7_1_, 8) && lvt_2_1_.func_150297_b(lvt_8_1_, 8)) {
               String lvt_9_1_ = lvt_2_1_.func_74779_i(lvt_7_1_);
               String lvt_10_1_ = lvt_2_1_.func_74779_i(lvt_8_1_);
               func_179667_a(this, lvt_6_1_, lvt_9_1_, lvt_10_1_);
            }
         }

      }
   }

   public void func_179670_b(NBTTagCompound p_179670_1_) {
      NBTTagCompound lvt_2_1_ = new NBTTagCompound();
      CommandResultStats.Type[] lvt_3_1_ = CommandResultStats.Type.values();
      int lvt_4_1_ = lvt_3_1_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         CommandResultStats.Type lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         String lvt_7_1_ = this.field_179675_c[lvt_6_1_.func_179636_a()];
         String lvt_8_1_ = this.field_179673_d[lvt_6_1_.func_179636_a()];
         if (lvt_7_1_ != null && lvt_8_1_ != null) {
            lvt_2_1_.func_74778_a(lvt_6_1_.func_179637_b() + "Name", lvt_7_1_);
            lvt_2_1_.func_74778_a(lvt_6_1_.func_179637_b() + "Objective", lvt_8_1_);
         }
      }

      if (!lvt_2_1_.func_82582_d()) {
         p_179670_1_.func_74782_a("CommandStats", lvt_2_1_);
      }

   }

   public static void func_179667_a(CommandResultStats p_179667_0_, CommandResultStats.Type p_179667_1_, String p_179667_2_, String p_179667_3_) {
      if (p_179667_2_ != null && p_179667_2_.length() != 0 && p_179667_3_ != null && p_179667_3_.length() != 0) {
         if (p_179667_0_.field_179675_c == field_179674_b || p_179667_0_.field_179673_d == field_179674_b) {
            p_179667_0_.field_179675_c = new String[field_179676_a];
            p_179667_0_.field_179673_d = new String[field_179676_a];
         }

         p_179667_0_.field_179675_c[p_179667_1_.func_179636_a()] = p_179667_2_;
         p_179667_0_.field_179673_d[p_179667_1_.func_179636_a()] = p_179667_3_;
      } else {
         func_179669_a(p_179667_0_, p_179667_1_);
      }
   }

   private static void func_179669_a(CommandResultStats p_179669_0_, CommandResultStats.Type p_179669_1_) {
      if (p_179669_0_.field_179675_c != field_179674_b && p_179669_0_.field_179673_d != field_179674_b) {
         p_179669_0_.field_179675_c[p_179669_1_.func_179636_a()] = null;
         p_179669_0_.field_179673_d[p_179669_1_.func_179636_a()] = null;
         boolean lvt_2_1_ = true;
         CommandResultStats.Type[] lvt_3_1_ = CommandResultStats.Type.values();
         int lvt_4_1_ = lvt_3_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            CommandResultStats.Type lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            if (p_179669_0_.field_179675_c[lvt_6_1_.func_179636_a()] != null && p_179669_0_.field_179673_d[lvt_6_1_.func_179636_a()] != null) {
               lvt_2_1_ = false;
               break;
            }
         }

         if (lvt_2_1_) {
            p_179669_0_.field_179675_c = field_179674_b;
            p_179669_0_.field_179673_d = field_179674_b;
         }

      }
   }

   public void func_179671_a(CommandResultStats p_179671_1_) {
      CommandResultStats.Type[] lvt_2_1_ = CommandResultStats.Type.values();
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         CommandResultStats.Type lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         func_179667_a(this, lvt_5_1_, p_179671_1_.field_179675_c[lvt_5_1_.func_179636_a()], p_179671_1_.field_179673_d[lvt_5_1_.func_179636_a()]);
      }

   }

   static {
      field_179674_b = new String[field_179676_a];
   }

   public static enum Type {
      SUCCESS_COUNT(0, "SuccessCount"),
      AFFECTED_BLOCKS(1, "AffectedBlocks"),
      AFFECTED_ENTITIES(2, "AffectedEntities"),
      AFFECTED_ITEMS(3, "AffectedItems"),
      QUERY_RESULT(4, "QueryResult");

      final int field_179639_f;
      final String field_179640_g;

      private Type(int p_i46050_3_, String p_i46050_4_) {
         this.field_179639_f = p_i46050_3_;
         this.field_179640_g = p_i46050_4_;
      }

      public int func_179636_a() {
         return this.field_179639_f;
      }

      public String func_179637_b() {
         return this.field_179640_g;
      }

      public static String[] func_179634_c() {
         String[] lvt_0_1_ = new String[values().length];
         int lvt_1_1_ = 0;
         CommandResultStats.Type[] lvt_2_1_ = values();
         int lvt_3_1_ = lvt_2_1_.length;

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
            CommandResultStats.Type lvt_5_1_ = lvt_2_1_[lvt_4_1_];
            lvt_0_1_[lvt_1_1_++] = lvt_5_1_.func_179637_b();
         }

         return lvt_0_1_;
      }

      public static CommandResultStats.Type func_179635_a(String p_179635_0_) {
         CommandResultStats.Type[] lvt_1_1_ = values();
         int lvt_2_1_ = lvt_1_1_.length;

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
            CommandResultStats.Type lvt_4_1_ = lvt_1_1_[lvt_3_1_];
            if (lvt_4_1_.func_179637_b().equals(p_179635_0_)) {
               return lvt_4_1_;
            }
         }

         return null;
      }
   }
}
