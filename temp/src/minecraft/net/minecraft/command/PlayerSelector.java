package net.minecraft.command;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class PlayerSelector {
   private static final Pattern field_82389_a = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
   private static final Pattern field_82387_b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
   private static final Pattern field_82388_c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
   private static final Set<String> field_179666_d = Sets.newHashSet((Object[])("x", "y", "z", "dx", "dy", "dz", "rm", "r"));

   public static EntityPlayerMP func_82386_a(ICommandSender p_82386_0_, String p_82386_1_) {
      return (EntityPlayerMP)func_179652_a(p_82386_0_, p_82386_1_, EntityPlayerMP.class);
   }

   public static <T extends Entity> T func_179652_a(ICommandSender p_179652_0_, String p_179652_1_, Class<? extends T> p_179652_2_) {
      List<T> lvt_3_1_ = func_179656_b(p_179652_0_, p_179652_1_, p_179652_2_);
      return lvt_3_1_.size() == 1 ? (Entity)lvt_3_1_.get(0) : null;
   }

   public static IChatComponent func_150869_b(ICommandSender p_150869_0_, String p_150869_1_) {
      List<Entity> lvt_2_1_ = func_179656_b(p_150869_0_, p_150869_1_, Entity.class);
      if (lvt_2_1_.isEmpty()) {
         return null;
      } else {
         List<IChatComponent> lvt_3_1_ = Lists.newArrayList();
         Iterator lvt_4_1_ = lvt_2_1_.iterator();

         while(lvt_4_1_.hasNext()) {
            Entity lvt_5_1_ = (Entity)lvt_4_1_.next();
            lvt_3_1_.add(lvt_5_1_.func_145748_c_());
         }

         return CommandBase.func_180530_a(lvt_3_1_);
      }
   }

   public static <T extends Entity> List<T> func_179656_b(ICommandSender p_179656_0_, String p_179656_1_, Class<? extends T> p_179656_2_) {
      Matcher lvt_3_1_ = field_82389_a.matcher(p_179656_1_);
      if (lvt_3_1_.matches() && p_179656_0_.func_70003_b(1, "@")) {
         Map<String, String> lvt_4_1_ = func_82381_h(lvt_3_1_.group(2));
         if (!func_179655_b(p_179656_0_, lvt_4_1_)) {
            return Collections.emptyList();
         } else {
            String lvt_5_1_ = lvt_3_1_.group(1);
            BlockPos lvt_6_1_ = func_179664_b(lvt_4_1_, p_179656_0_.func_180425_c());
            List<World> lvt_7_1_ = func_179654_a(p_179656_0_, lvt_4_1_);
            List<T> lvt_8_1_ = Lists.newArrayList();
            Iterator lvt_9_1_ = lvt_7_1_.iterator();

            while(lvt_9_1_.hasNext()) {
               World lvt_10_1_ = (World)lvt_9_1_.next();
               if (lvt_10_1_ != null) {
                  List<Predicate<Entity>> lvt_11_1_ = Lists.newArrayList();
                  lvt_11_1_.addAll(func_179663_a(lvt_4_1_, lvt_5_1_));
                  lvt_11_1_.addAll(func_179648_b(lvt_4_1_));
                  lvt_11_1_.addAll(func_179649_c(lvt_4_1_));
                  lvt_11_1_.addAll(func_179659_d(lvt_4_1_));
                  lvt_11_1_.addAll(func_179657_e(lvt_4_1_));
                  lvt_11_1_.addAll(func_179647_f(lvt_4_1_));
                  lvt_11_1_.addAll(func_180698_a(lvt_4_1_, lvt_6_1_));
                  lvt_11_1_.addAll(func_179662_g(lvt_4_1_));
                  lvt_8_1_.addAll(func_179660_a(lvt_4_1_, p_179656_2_, lvt_11_1_, lvt_5_1_, lvt_10_1_, lvt_6_1_));
               }
            }

            return func_179658_a(lvt_8_1_, lvt_4_1_, p_179656_0_, p_179656_2_, lvt_5_1_, lvt_6_1_);
         }
      } else {
         return Collections.emptyList();
      }
   }

   private static List<World> func_179654_a(ICommandSender p_179654_0_, Map<String, String> p_179654_1_) {
      List<World> lvt_2_1_ = Lists.newArrayList();
      if (func_179665_h(p_179654_1_)) {
         lvt_2_1_.add(p_179654_0_.func_130014_f_());
      } else {
         Collections.addAll(lvt_2_1_, MinecraftServer.func_71276_C().field_71305_c);
      }

      return lvt_2_1_;
   }

   private static <T extends Entity> boolean func_179655_b(ICommandSender p_179655_0_, Map<String, String> p_179655_1_) {
      String lvt_2_1_ = func_179651_b(p_179655_1_, "type");
      lvt_2_1_ = lvt_2_1_ != null && lvt_2_1_.startsWith("!") ? lvt_2_1_.substring(1) : lvt_2_1_;
      if (lvt_2_1_ != null && !EntityList.func_180125_b(lvt_2_1_)) {
         ChatComponentTranslation lvt_3_1_ = new ChatComponentTranslation("commands.generic.entity.invalidType", new Object[]{lvt_2_1_});
         lvt_3_1_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_179655_0_.func_145747_a(lvt_3_1_);
         return false;
      } else {
         return true;
      }
   }

   private static List<Predicate<Entity>> func_179663_a(Map<String, String> p_179663_0_, String p_179663_1_) {
      List<Predicate<Entity>> lvt_2_1_ = Lists.newArrayList();
      final String lvt_3_1_ = func_179651_b(p_179663_0_, "type");
      final boolean lvt_4_1_ = lvt_3_1_ != null && lvt_3_1_.startsWith("!");
      if (lvt_4_1_) {
         lvt_3_1_ = lvt_3_1_.substring(1);
      }

      boolean lvt_6_1_ = !p_179663_1_.equals("e");
      boolean lvt_7_1_ = p_179663_1_.equals("r") && lvt_3_1_ != null;
      if ((lvt_3_1_ == null || !p_179663_1_.equals("e")) && !lvt_7_1_) {
         if (lvt_6_1_) {
            lvt_2_1_.add(new Predicate<Entity>() {
               public boolean apply(Entity p_apply_1_) {
                  return p_apply_1_ instanceof EntityPlayer;
               }
            });
         }
      } else {
         lvt_2_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               return EntityList.func_180123_a(p_apply_1_, lvt_3_1_) != lvt_4_1_;
            }
         });
      }

      return lvt_2_1_;
   }

   private static List<Predicate<Entity>> func_179648_b(Map<String, String> p_179648_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final int lvt_2_1_ = func_179653_a(p_179648_0_, "lm", -1);
      final int lvt_3_1_ = func_179653_a(p_179648_0_, "l", -1);
      if (lvt_2_1_ > -1 || lvt_3_1_ > -1) {
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if (!(p_apply_1_ instanceof EntityPlayerMP)) {
                  return false;
               } else {
                  EntityPlayerMP lvt_2_1_x = (EntityPlayerMP)p_apply_1_;
                  return (lvt_2_1_ <= -1 || lvt_2_1_x.field_71068_ca >= lvt_2_1_) && (lvt_3_1_ <= -1 || lvt_2_1_x.field_71068_ca <= lvt_3_1_);
               }
            }
         });
      }

      return lvt_1_1_;
   }

   private static List<Predicate<Entity>> func_179649_c(Map<String, String> p_179649_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final int lvt_2_1_ = func_179653_a(p_179649_0_, "m", WorldSettings.GameType.NOT_SET.func_77148_a());
      if (lvt_2_1_ != WorldSettings.GameType.NOT_SET.func_77148_a()) {
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if (!(p_apply_1_ instanceof EntityPlayerMP)) {
                  return false;
               } else {
                  EntityPlayerMP lvt_2_1_x = (EntityPlayerMP)p_apply_1_;
                  return lvt_2_1_x.field_71134_c.func_73081_b().func_77148_a() == lvt_2_1_;
               }
            }
         });
      }

      return lvt_1_1_;
   }

   private static List<Predicate<Entity>> func_179659_d(Map<String, String> p_179659_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final String lvt_2_1_ = func_179651_b(p_179659_0_, "team");
      final boolean lvt_3_1_ = lvt_2_1_ != null && lvt_2_1_.startsWith("!");
      if (lvt_3_1_) {
         lvt_2_1_ = lvt_2_1_.substring(1);
      }

      if (lvt_2_1_ != null) {
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if (!(p_apply_1_ instanceof EntityLivingBase)) {
                  return false;
               } else {
                  EntityLivingBase lvt_2_1_x = (EntityLivingBase)p_apply_1_;
                  Team lvt_3_1_x = lvt_2_1_x.func_96124_cp();
                  String lvt_4_1_ = lvt_3_1_x == null ? "" : lvt_3_1_x.func_96661_b();
                  return lvt_4_1_.equals(lvt_2_1_) != lvt_3_1_;
               }
            }
         });
      }

      return lvt_1_1_;
   }

   private static List<Predicate<Entity>> func_179657_e(Map<String, String> p_179657_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final Map<String, Integer> lvt_2_1_ = func_96560_a(p_179657_0_);
      if (lvt_2_1_ != null && lvt_2_1_.size() > 0) {
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               Scoreboard lvt_2_1_x = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
               Iterator lvt_3_1_ = lvt_2_1_.entrySet().iterator();

               Entry lvt_4_1_;
               boolean lvt_6_1_;
               int lvt_10_1_;
               do {
                  if (!lvt_3_1_.hasNext()) {
                     return true;
                  }

                  lvt_4_1_ = (Entry)lvt_3_1_.next();
                  String lvt_5_1_ = (String)lvt_4_1_.getKey();
                  lvt_6_1_ = false;
                  if (lvt_5_1_.endsWith("_min") && lvt_5_1_.length() > 4) {
                     lvt_6_1_ = true;
                     lvt_5_1_ = lvt_5_1_.substring(0, lvt_5_1_.length() - 4);
                  }

                  ScoreObjective lvt_7_1_ = lvt_2_1_x.func_96518_b(lvt_5_1_);
                  if (lvt_7_1_ == null) {
                     return false;
                  }

                  String lvt_8_1_ = p_apply_1_ instanceof EntityPlayerMP ? p_apply_1_.func_70005_c_() : p_apply_1_.func_110124_au().toString();
                  if (!lvt_2_1_x.func_178819_b(lvt_8_1_, lvt_7_1_)) {
                     return false;
                  }

                  Score lvt_9_1_ = lvt_2_1_x.func_96529_a(lvt_8_1_, lvt_7_1_);
                  lvt_10_1_ = lvt_9_1_.func_96652_c();
                  if (lvt_10_1_ < (Integer)lvt_4_1_.getValue() && lvt_6_1_) {
                     return false;
                  }
               } while(lvt_10_1_ <= (Integer)lvt_4_1_.getValue() || lvt_6_1_);

               return false;
            }
         });
      }

      return lvt_1_1_;
   }

   private static List<Predicate<Entity>> func_179647_f(Map<String, String> p_179647_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final String lvt_2_1_ = func_179651_b(p_179647_0_, "name");
      final boolean lvt_3_1_ = lvt_2_1_ != null && lvt_2_1_.startsWith("!");
      if (lvt_3_1_) {
         lvt_2_1_ = lvt_2_1_.substring(1);
      }

      if (lvt_2_1_ != null) {
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               return p_apply_1_.func_70005_c_().equals(lvt_2_1_) != lvt_3_1_;
            }
         });
      }

      return lvt_1_1_;
   }

   private static List<Predicate<Entity>> func_180698_a(Map<String, String> p_180698_0_, final BlockPos p_180698_1_) {
      List<Predicate<Entity>> lvt_2_1_ = Lists.newArrayList();
      final int lvt_3_1_ = func_179653_a(p_180698_0_, "rm", -1);
      final int lvt_4_1_ = func_179653_a(p_180698_0_, "r", -1);
      if (p_180698_1_ != null && (lvt_3_1_ >= 0 || lvt_4_1_ >= 0)) {
         final int lvt_5_1_ = lvt_3_1_ * lvt_3_1_;
         final int lvt_6_1_ = lvt_4_1_ * lvt_4_1_;
         lvt_2_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int lvt_2_1_ = (int)p_apply_1_.func_174831_c(p_180698_1_);
               return (lvt_3_1_ < 0 || lvt_2_1_ >= lvt_5_1_) && (lvt_4_1_ < 0 || lvt_2_1_ <= lvt_6_1_);
            }
         });
      }

      return lvt_2_1_;
   }

   private static List<Predicate<Entity>> func_179662_g(Map<String, String> p_179662_0_) {
      List<Predicate<Entity>> lvt_1_1_ = Lists.newArrayList();
      final int lvt_2_2_;
      final int lvt_3_2_;
      if (p_179662_0_.containsKey("rym") || p_179662_0_.containsKey("ry")) {
         lvt_2_2_ = func_179650_a(func_179653_a(p_179662_0_, "rym", 0));
         lvt_3_2_ = func_179650_a(func_179653_a(p_179662_0_, "ry", 359));
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int lvt_2_1_ = PlayerSelector.func_179650_a((int)Math.floor((double)p_apply_1_.field_70177_z));
               if (lvt_2_2_ > lvt_3_2_) {
                  return lvt_2_1_ >= lvt_2_2_ || lvt_2_1_ <= lvt_3_2_;
               } else {
                  return lvt_2_1_ >= lvt_2_2_ && lvt_2_1_ <= lvt_3_2_;
               }
            }
         });
      }

      if (p_179662_0_.containsKey("rxm") || p_179662_0_.containsKey("rx")) {
         lvt_2_2_ = func_179650_a(func_179653_a(p_179662_0_, "rxm", 0));
         lvt_3_2_ = func_179650_a(func_179653_a(p_179662_0_, "rx", 359));
         lvt_1_1_.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int lvt_2_1_ = PlayerSelector.func_179650_a((int)Math.floor((double)p_apply_1_.field_70125_A));
               if (lvt_2_2_ > lvt_3_2_) {
                  return lvt_2_1_ >= lvt_2_2_ || lvt_2_1_ <= lvt_3_2_;
               } else {
                  return lvt_2_1_ >= lvt_2_2_ && lvt_2_1_ <= lvt_3_2_;
               }
            }
         });
      }

      return lvt_1_1_;
   }

   private static <T extends Entity> List<T> func_179660_a(Map<String, String> p_179660_0_, Class<? extends T> p_179660_1_, List<Predicate<Entity>> p_179660_2_, String p_179660_3_, World p_179660_4_, BlockPos p_179660_5_) {
      List<T> lvt_6_1_ = Lists.newArrayList();
      String lvt_7_1_ = func_179651_b(p_179660_0_, "type");
      lvt_7_1_ = lvt_7_1_ != null && lvt_7_1_.startsWith("!") ? lvt_7_1_.substring(1) : lvt_7_1_;
      boolean lvt_8_1_ = !p_179660_3_.equals("e");
      boolean lvt_9_1_ = p_179660_3_.equals("r") && lvt_7_1_ != null;
      int lvt_10_1_ = func_179653_a(p_179660_0_, "dx", 0);
      int lvt_11_1_ = func_179653_a(p_179660_0_, "dy", 0);
      int lvt_12_1_ = func_179653_a(p_179660_0_, "dz", 0);
      int lvt_13_1_ = func_179653_a(p_179660_0_, "r", -1);
      Predicate<Entity> lvt_14_1_ = Predicates.and((Iterable)p_179660_2_);
      Predicate<Entity> lvt_15_1_ = Predicates.and(EntitySelectors.field_94557_a, lvt_14_1_);
      if (p_179660_5_ != null) {
         int lvt_16_1_ = p_179660_4_.field_73010_i.size();
         int lvt_17_1_ = p_179660_4_.field_72996_f.size();
         boolean lvt_18_1_ = lvt_16_1_ < lvt_17_1_ / 16;
         final AxisAlignedBB lvt_19_2_;
         if (!p_179660_0_.containsKey("dx") && !p_179660_0_.containsKey("dy") && !p_179660_0_.containsKey("dz")) {
            if (lvt_13_1_ >= 0) {
               lvt_19_2_ = new AxisAlignedBB((double)(p_179660_5_.func_177958_n() - lvt_13_1_), (double)(p_179660_5_.func_177956_o() - lvt_13_1_), (double)(p_179660_5_.func_177952_p() - lvt_13_1_), (double)(p_179660_5_.func_177958_n() + lvt_13_1_ + 1), (double)(p_179660_5_.func_177956_o() + lvt_13_1_ + 1), (double)(p_179660_5_.func_177952_p() + lvt_13_1_ + 1));
               if (lvt_8_1_ && lvt_18_1_ && !lvt_9_1_) {
                  lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, lvt_15_1_));
               } else {
                  lvt_6_1_.addAll(p_179660_4_.func_175647_a(p_179660_1_, lvt_19_2_, lvt_15_1_));
               }
            } else if (p_179660_3_.equals("a")) {
               lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, lvt_14_1_));
            } else if (!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || lvt_9_1_)) {
               lvt_6_1_.addAll(p_179660_4_.func_175644_a(p_179660_1_, lvt_15_1_));
            } else {
               lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, lvt_15_1_));
            }
         } else {
            lvt_19_2_ = func_179661_a(p_179660_5_, lvt_10_1_, lvt_11_1_, lvt_12_1_);
            if (lvt_8_1_ && lvt_18_1_ && !lvt_9_1_) {
               Predicate<Entity> lvt_20_1_ = new Predicate<Entity>() {
                  public boolean apply(Entity p_apply_1_) {
                     if (p_apply_1_.field_70165_t >= lvt_19_2_.field_72340_a && p_apply_1_.field_70163_u >= lvt_19_2_.field_72338_b && p_apply_1_.field_70161_v >= lvt_19_2_.field_72339_c) {
                        return p_apply_1_.field_70165_t < lvt_19_2_.field_72336_d && p_apply_1_.field_70163_u < lvt_19_2_.field_72337_e && p_apply_1_.field_70161_v < lvt_19_2_.field_72334_f;
                     } else {
                        return false;
                     }
                  }
               };
               lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, Predicates.and(lvt_15_1_, lvt_20_1_)));
            } else {
               lvt_6_1_.addAll(p_179660_4_.func_175647_a(p_179660_1_, lvt_19_2_, lvt_15_1_));
            }
         }
      } else if (p_179660_3_.equals("a")) {
         lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, lvt_14_1_));
      } else if (!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || lvt_9_1_)) {
         lvt_6_1_.addAll(p_179660_4_.func_175644_a(p_179660_1_, lvt_15_1_));
      } else {
         lvt_6_1_.addAll(p_179660_4_.func_175661_b(p_179660_1_, lvt_15_1_));
      }

      return lvt_6_1_;
   }

   private static <T extends Entity> List<T> func_179658_a(List<T> p_179658_0_, Map<String, String> p_179658_1_, ICommandSender p_179658_2_, Class<? extends T> p_179658_3_, String p_179658_4_, final BlockPos p_179658_5_) {
      int lvt_6_1_ = func_179653_a(p_179658_1_, "c", !p_179658_4_.equals("a") && !p_179658_4_.equals("e") ? 1 : 0);
      if (!p_179658_4_.equals("p") && !p_179658_4_.equals("a") && !p_179658_4_.equals("e")) {
         if (p_179658_4_.equals("r")) {
            Collections.shuffle((List)p_179658_0_);
         }
      } else if (p_179658_5_ != null) {
         Collections.sort((List)p_179658_0_, new Comparator<Entity>() {
            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
               return ComparisonChain.start().compare(p_compare_1_.func_174818_b(p_179658_5_), p_compare_2_.func_174818_b(p_179658_5_)).result();
            }
         });
      }

      Entity lvt_7_1_ = p_179658_2_.func_174793_f();
      if (lvt_7_1_ != null && p_179658_3_.isAssignableFrom(lvt_7_1_.getClass()) && lvt_6_1_ == 1 && ((List)p_179658_0_).contains(lvt_7_1_) && !"r".equals(p_179658_4_)) {
         p_179658_0_ = Lists.newArrayList((Object[])(lvt_7_1_));
      }

      if (lvt_6_1_ != 0) {
         if (lvt_6_1_ < 0) {
            Collections.reverse((List)p_179658_0_);
         }

         p_179658_0_ = ((List)p_179658_0_).subList(0, Math.min(Math.abs(lvt_6_1_), ((List)p_179658_0_).size()));
      }

      return (List)p_179658_0_;
   }

   private static AxisAlignedBB func_179661_a(BlockPos p_179661_0_, int p_179661_1_, int p_179661_2_, int p_179661_3_) {
      boolean lvt_4_1_ = p_179661_1_ < 0;
      boolean lvt_5_1_ = p_179661_2_ < 0;
      boolean lvt_6_1_ = p_179661_3_ < 0;
      int lvt_7_1_ = p_179661_0_.func_177958_n() + (lvt_4_1_ ? p_179661_1_ : 0);
      int lvt_8_1_ = p_179661_0_.func_177956_o() + (lvt_5_1_ ? p_179661_2_ : 0);
      int lvt_9_1_ = p_179661_0_.func_177952_p() + (lvt_6_1_ ? p_179661_3_ : 0);
      int lvt_10_1_ = p_179661_0_.func_177958_n() + (lvt_4_1_ ? 0 : p_179661_1_) + 1;
      int lvt_11_1_ = p_179661_0_.func_177956_o() + (lvt_5_1_ ? 0 : p_179661_2_) + 1;
      int lvt_12_1_ = p_179661_0_.func_177952_p() + (lvt_6_1_ ? 0 : p_179661_3_) + 1;
      return new AxisAlignedBB((double)lvt_7_1_, (double)lvt_8_1_, (double)lvt_9_1_, (double)lvt_10_1_, (double)lvt_11_1_, (double)lvt_12_1_);
   }

   public static int func_179650_a(int p_179650_0_) {
      p_179650_0_ %= 360;
      if (p_179650_0_ >= 160) {
         p_179650_0_ -= 360;
      }

      if (p_179650_0_ < 0) {
         p_179650_0_ += 360;
      }

      return p_179650_0_;
   }

   private static BlockPos func_179664_b(Map<String, String> p_179664_0_, BlockPos p_179664_1_) {
      return new BlockPos(func_179653_a(p_179664_0_, "x", p_179664_1_.func_177958_n()), func_179653_a(p_179664_0_, "y", p_179664_1_.func_177956_o()), func_179653_a(p_179664_0_, "z", p_179664_1_.func_177952_p()));
   }

   private static boolean func_179665_h(Map<String, String> p_179665_0_) {
      Iterator lvt_1_1_ = field_179666_d.iterator();

      String lvt_2_1_;
      do {
         if (!lvt_1_1_.hasNext()) {
            return false;
         }

         lvt_2_1_ = (String)lvt_1_1_.next();
      } while(!p_179665_0_.containsKey(lvt_2_1_));

      return true;
   }

   private static int func_179653_a(Map<String, String> p_179653_0_, String p_179653_1_, int p_179653_2_) {
      return p_179653_0_.containsKey(p_179653_1_) ? MathHelper.func_82715_a((String)p_179653_0_.get(p_179653_1_), p_179653_2_) : p_179653_2_;
   }

   private static String func_179651_b(Map<String, String> p_179651_0_, String p_179651_1_) {
      return (String)p_179651_0_.get(p_179651_1_);
   }

   public static Map<String, Integer> func_96560_a(Map<String, String> p_96560_0_) {
      Map<String, Integer> lvt_1_1_ = Maps.newHashMap();
      Iterator lvt_2_1_ = p_96560_0_.keySet().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         if (lvt_3_1_.startsWith("score_") && lvt_3_1_.length() > "score_".length()) {
            lvt_1_1_.put(lvt_3_1_.substring("score_".length()), MathHelper.func_82715_a((String)p_96560_0_.get(lvt_3_1_), 1));
         }
      }

      return lvt_1_1_;
   }

   public static boolean func_82377_a(String p_82377_0_) {
      Matcher lvt_1_1_ = field_82389_a.matcher(p_82377_0_);
      if (!lvt_1_1_.matches()) {
         return false;
      } else {
         Map<String, String> lvt_2_1_ = func_82381_h(lvt_1_1_.group(2));
         String lvt_3_1_ = lvt_1_1_.group(1);
         int lvt_4_1_ = !"a".equals(lvt_3_1_) && !"e".equals(lvt_3_1_) ? 1 : 0;
         return func_179653_a(lvt_2_1_, "c", lvt_4_1_) != 1;
      }
   }

   public static boolean func_82378_b(String p_82378_0_) {
      return field_82389_a.matcher(p_82378_0_).matches();
   }

   private static Map<String, String> func_82381_h(String p_82381_0_) {
      Map<String, String> lvt_1_1_ = Maps.newHashMap();
      if (p_82381_0_ == null) {
         return lvt_1_1_;
      } else {
         int lvt_2_1_ = 0;
         int lvt_3_1_ = -1;

         for(Matcher lvt_4_1_ = field_82387_b.matcher(p_82381_0_); lvt_4_1_.find(); lvt_3_1_ = lvt_4_1_.end()) {
            String lvt_5_1_ = null;
            switch(lvt_2_1_++) {
            case 0:
               lvt_5_1_ = "x";
               break;
            case 1:
               lvt_5_1_ = "y";
               break;
            case 2:
               lvt_5_1_ = "z";
               break;
            case 3:
               lvt_5_1_ = "r";
            }

            if (lvt_5_1_ != null && lvt_4_1_.group(1).length() > 0) {
               lvt_1_1_.put(lvt_5_1_, lvt_4_1_.group(1));
            }
         }

         if (lvt_3_1_ < p_82381_0_.length()) {
            Matcher lvt_5_2_ = field_82388_c.matcher(lvt_3_1_ == -1 ? p_82381_0_ : p_82381_0_.substring(lvt_3_1_));

            while(lvt_5_2_.find()) {
               lvt_1_1_.put(lvt_5_2_.group(1), lvt_5_2_.group(2));
            }
         }

         return lvt_1_1_;
      }
   }
}
