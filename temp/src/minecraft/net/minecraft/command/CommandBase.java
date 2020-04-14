package net.minecraft.command;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class CommandBase implements ICommand {
   private static IAdminCommand field_71533_a;

   public int func_82362_a() {
      return 4;
   }

   public List<String> func_71514_a() {
      return Collections.emptyList();
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return p_71519_1_.func_70003_b(this.func_82362_a(), this.func_71517_b());
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return null;
   }

   public static int func_175755_a(String p_175755_0_) throws NumberInvalidException {
      try {
         return Integer.parseInt(p_175755_0_);
      } catch (NumberFormatException var2) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175755_0_});
      }
   }

   public static int func_180528_a(String p_180528_0_, int p_180528_1_) throws NumberInvalidException {
      return func_175764_a(p_180528_0_, p_180528_1_, Integer.MAX_VALUE);
   }

   public static int func_175764_a(String p_175764_0_, int p_175764_1_, int p_175764_2_) throws NumberInvalidException {
      int lvt_3_1_ = func_175755_a(p_175764_0_);
      if (lvt_3_1_ < p_175764_1_) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{lvt_3_1_, p_175764_1_});
      } else if (lvt_3_1_ > p_175764_2_) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{lvt_3_1_, p_175764_2_});
      } else {
         return lvt_3_1_;
      }
   }

   public static long func_175766_b(String p_175766_0_) throws NumberInvalidException {
      try {
         return Long.parseLong(p_175766_0_);
      } catch (NumberFormatException var2) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175766_0_});
      }
   }

   public static long func_175760_a(String p_175760_0_, long p_175760_1_, long p_175760_3_) throws NumberInvalidException {
      long lvt_5_1_ = func_175766_b(p_175760_0_);
      if (lvt_5_1_ < p_175760_1_) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{lvt_5_1_, p_175760_1_});
      } else if (lvt_5_1_ > p_175760_3_) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{lvt_5_1_, p_175760_3_});
      } else {
         return lvt_5_1_;
      }
   }

   public static BlockPos func_175757_a(ICommandSender p_175757_0_, String[] p_175757_1_, int p_175757_2_, boolean p_175757_3_) throws NumberInvalidException {
      BlockPos lvt_4_1_ = p_175757_0_.func_180425_c();
      return new BlockPos(func_175769_b((double)lvt_4_1_.func_177958_n(), p_175757_1_[p_175757_2_], -30000000, 30000000, p_175757_3_), func_175769_b((double)lvt_4_1_.func_177956_o(), p_175757_1_[p_175757_2_ + 1], 0, 256, false), func_175769_b((double)lvt_4_1_.func_177952_p(), p_175757_1_[p_175757_2_ + 2], -30000000, 30000000, p_175757_3_));
   }

   public static double func_175765_c(String p_175765_0_) throws NumberInvalidException {
      try {
         double lvt_1_1_ = Double.parseDouble(p_175765_0_);
         if (!Doubles.isFinite(lvt_1_1_)) {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175765_0_});
         } else {
            return lvt_1_1_;
         }
      } catch (NumberFormatException var3) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175765_0_});
      }
   }

   public static double func_180526_a(String p_180526_0_, double p_180526_1_) throws NumberInvalidException {
      return func_175756_a(p_180526_0_, p_180526_1_, Double.MAX_VALUE);
   }

   public static double func_175756_a(String p_175756_0_, double p_175756_1_, double p_175756_3_) throws NumberInvalidException {
      double lvt_5_1_ = func_175765_c(p_175756_0_);
      if (lvt_5_1_ < p_175756_1_) {
         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{lvt_5_1_, p_175756_1_});
      } else if (lvt_5_1_ > p_175756_3_) {
         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{lvt_5_1_, p_175756_3_});
      } else {
         return lvt_5_1_;
      }
   }

   public static boolean func_180527_d(String p_180527_0_) throws CommandException {
      if (!p_180527_0_.equals("true") && !p_180527_0_.equals("1")) {
         if (!p_180527_0_.equals("false") && !p_180527_0_.equals("0")) {
            throw new CommandException("commands.generic.boolean.invalid", new Object[]{p_180527_0_});
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public static EntityPlayerMP func_71521_c(ICommandSender p_71521_0_) throws PlayerNotFoundException {
      if (p_71521_0_ instanceof EntityPlayerMP) {
         return (EntityPlayerMP)p_71521_0_;
      } else {
         throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
      }
   }

   public static EntityPlayerMP func_82359_c(ICommandSender p_82359_0_, String p_82359_1_) throws PlayerNotFoundException {
      EntityPlayerMP lvt_2_1_ = PlayerSelector.func_82386_a(p_82359_0_, p_82359_1_);
      if (lvt_2_1_ == null) {
         try {
            lvt_2_1_ = MinecraftServer.func_71276_C().func_71203_ab().func_177451_a(UUID.fromString(p_82359_1_));
         } catch (IllegalArgumentException var4) {
         }
      }

      if (lvt_2_1_ == null) {
         lvt_2_1_ = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(p_82359_1_);
      }

      if (lvt_2_1_ == null) {
         throw new PlayerNotFoundException();
      } else {
         return lvt_2_1_;
      }
   }

   public static Entity func_175768_b(ICommandSender p_175768_0_, String p_175768_1_) throws EntityNotFoundException {
      return func_175759_a(p_175768_0_, p_175768_1_, Entity.class);
   }

   public static <T extends Entity> T func_175759_a(ICommandSender p_175759_0_, String p_175759_1_, Class<? extends T> p_175759_2_) throws EntityNotFoundException {
      Entity lvt_3_1_ = PlayerSelector.func_179652_a(p_175759_0_, p_175759_1_, p_175759_2_);
      MinecraftServer lvt_4_1_ = MinecraftServer.func_71276_C();
      if (lvt_3_1_ == null) {
         lvt_3_1_ = lvt_4_1_.func_71203_ab().func_152612_a(p_175759_1_);
      }

      if (lvt_3_1_ == null) {
         try {
            UUID lvt_5_1_ = UUID.fromString(p_175759_1_);
            lvt_3_1_ = lvt_4_1_.func_175576_a(lvt_5_1_);
            if (lvt_3_1_ == null) {
               lvt_3_1_ = lvt_4_1_.func_71203_ab().func_177451_a(lvt_5_1_);
            }
         } catch (IllegalArgumentException var6) {
            throw new EntityNotFoundException("commands.generic.entity.invalidUuid", new Object[0]);
         }
      }

      if (lvt_3_1_ != null && p_175759_2_.isAssignableFrom(lvt_3_1_.getClass())) {
         return (Entity)lvt_3_1_;
      } else {
         throw new EntityNotFoundException();
      }
   }

   public static List<Entity> func_175763_c(ICommandSender p_175763_0_, String p_175763_1_) throws EntityNotFoundException {
      return (List)(PlayerSelector.func_82378_b(p_175763_1_) ? PlayerSelector.func_179656_b(p_175763_0_, p_175763_1_, Entity.class) : Lists.newArrayList((Object[])(func_175768_b(p_175763_0_, p_175763_1_))));
   }

   public static String func_96332_d(ICommandSender p_96332_0_, String p_96332_1_) throws PlayerNotFoundException {
      try {
         return func_82359_c(p_96332_0_, p_96332_1_).func_70005_c_();
      } catch (PlayerNotFoundException var3) {
         if (PlayerSelector.func_82378_b(p_96332_1_)) {
            throw var3;
         } else {
            return p_96332_1_;
         }
      }
   }

   public static String func_175758_e(ICommandSender p_175758_0_, String p_175758_1_) throws EntityNotFoundException {
      try {
         return func_82359_c(p_175758_0_, p_175758_1_).func_70005_c_();
      } catch (PlayerNotFoundException var5) {
         try {
            return func_175768_b(p_175758_0_, p_175758_1_).func_110124_au().toString();
         } catch (EntityNotFoundException var4) {
            if (PlayerSelector.func_82378_b(p_175758_1_)) {
               throw var4;
            } else {
               return p_175758_1_;
            }
         }
      }
   }

   public static IChatComponent func_147178_a(ICommandSender p_147178_0_, String[] p_147178_1_, int p_147178_2_) throws CommandException, PlayerNotFoundException {
      return func_147176_a(p_147178_0_, p_147178_1_, p_147178_2_, false);
   }

   public static IChatComponent func_147176_a(ICommandSender p_147176_0_, String[] p_147176_1_, int p_147176_2_, boolean p_147176_3_) throws PlayerNotFoundException {
      IChatComponent lvt_4_1_ = new ChatComponentText("");

      for(int lvt_5_1_ = p_147176_2_; lvt_5_1_ < p_147176_1_.length; ++lvt_5_1_) {
         if (lvt_5_1_ > p_147176_2_) {
            lvt_4_1_.func_150258_a(" ");
         }

         IChatComponent lvt_6_1_ = new ChatComponentText(p_147176_1_[lvt_5_1_]);
         if (p_147176_3_) {
            IChatComponent lvt_7_1_ = PlayerSelector.func_150869_b(p_147176_0_, p_147176_1_[lvt_5_1_]);
            if (lvt_7_1_ == null) {
               if (PlayerSelector.func_82378_b(p_147176_1_[lvt_5_1_])) {
                  throw new PlayerNotFoundException();
               }
            } else {
               lvt_6_1_ = lvt_7_1_;
            }
         }

         lvt_4_1_.func_150257_a((IChatComponent)lvt_6_1_);
      }

      return lvt_4_1_;
   }

   public static String func_180529_a(String[] p_180529_0_, int p_180529_1_) {
      StringBuilder lvt_2_1_ = new StringBuilder();

      for(int lvt_3_1_ = p_180529_1_; lvt_3_1_ < p_180529_0_.length; ++lvt_3_1_) {
         if (lvt_3_1_ > p_180529_1_) {
            lvt_2_1_.append(" ");
         }

         String lvt_4_1_ = p_180529_0_[lvt_3_1_];
         lvt_2_1_.append(lvt_4_1_);
      }

      return lvt_2_1_.toString();
   }

   public static CommandBase.CoordinateArg func_175770_a(double p_175770_0_, String p_175770_2_, boolean p_175770_3_) throws NumberInvalidException {
      return func_175767_a(p_175770_0_, p_175770_2_, -30000000, 30000000, p_175770_3_);
   }

   public static CommandBase.CoordinateArg func_175767_a(double p_175767_0_, String p_175767_2_, int p_175767_3_, int p_175767_4_, boolean p_175767_5_) throws NumberInvalidException {
      boolean lvt_6_1_ = p_175767_2_.startsWith("~");
      if (lvt_6_1_ && Double.isNaN(p_175767_0_)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175767_0_});
      } else {
         double lvt_7_1_ = 0.0D;
         if (!lvt_6_1_ || p_175767_2_.length() > 1) {
            boolean lvt_9_1_ = p_175767_2_.contains(".");
            if (lvt_6_1_) {
               p_175767_2_ = p_175767_2_.substring(1);
            }

            lvt_7_1_ += func_175765_c(p_175767_2_);
            if (!lvt_9_1_ && !lvt_6_1_ && p_175767_5_) {
               lvt_7_1_ += 0.5D;
            }
         }

         if (p_175767_3_ != 0 || p_175767_4_ != 0) {
            if (lvt_7_1_ < (double)p_175767_3_) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{lvt_7_1_, p_175767_3_});
            }

            if (lvt_7_1_ > (double)p_175767_4_) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{lvt_7_1_, p_175767_4_});
            }
         }

         return new CommandBase.CoordinateArg(lvt_7_1_ + (lvt_6_1_ ? p_175767_0_ : 0.0D), lvt_7_1_, lvt_6_1_);
      }
   }

   public static double func_175761_b(double p_175761_0_, String p_175761_2_, boolean p_175761_3_) throws NumberInvalidException {
      return func_175769_b(p_175761_0_, p_175761_2_, -30000000, 30000000, p_175761_3_);
   }

   public static double func_175769_b(double p_175769_0_, String p_175769_2_, int p_175769_3_, int p_175769_4_, boolean p_175769_5_) throws NumberInvalidException {
      boolean lvt_6_1_ = p_175769_2_.startsWith("~");
      if (lvt_6_1_ && Double.isNaN(p_175769_0_)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175769_0_});
      } else {
         double lvt_7_1_ = lvt_6_1_ ? p_175769_0_ : 0.0D;
         if (!lvt_6_1_ || p_175769_2_.length() > 1) {
            boolean lvt_9_1_ = p_175769_2_.contains(".");
            if (lvt_6_1_) {
               p_175769_2_ = p_175769_2_.substring(1);
            }

            lvt_7_1_ += func_175765_c(p_175769_2_);
            if (!lvt_9_1_ && !lvt_6_1_ && p_175769_5_) {
               lvt_7_1_ += 0.5D;
            }
         }

         if (p_175769_3_ != 0 || p_175769_4_ != 0) {
            if (lvt_7_1_ < (double)p_175769_3_) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{lvt_7_1_, p_175769_3_});
            }

            if (lvt_7_1_ > (double)p_175769_4_) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{lvt_7_1_, p_175769_4_});
            }
         }

         return lvt_7_1_;
      }
   }

   public static Item func_147179_f(ICommandSender p_147179_0_, String p_147179_1_) throws NumberInvalidException {
      ResourceLocation lvt_2_1_ = new ResourceLocation(p_147179_1_);
      Item lvt_3_1_ = (Item)Item.field_150901_e.func_82594_a(lvt_2_1_);
      if (lvt_3_1_ == null) {
         throw new NumberInvalidException("commands.give.item.notFound", new Object[]{lvt_2_1_});
      } else {
         return lvt_3_1_;
      }
   }

   public static Block func_147180_g(ICommandSender p_147180_0_, String p_147180_1_) throws NumberInvalidException {
      ResourceLocation lvt_2_1_ = new ResourceLocation(p_147180_1_);
      if (!Block.field_149771_c.func_148741_d(lvt_2_1_)) {
         throw new NumberInvalidException("commands.give.block.notFound", new Object[]{lvt_2_1_});
      } else {
         Block lvt_3_1_ = (Block)Block.field_149771_c.func_82594_a(lvt_2_1_);
         if (lvt_3_1_ == null) {
            throw new NumberInvalidException("commands.give.block.notFound", new Object[]{lvt_2_1_});
         } else {
            return lvt_3_1_;
         }
      }
   }

   public static String func_71527_a(Object[] p_71527_0_) {
      StringBuilder lvt_1_1_ = new StringBuilder();

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_71527_0_.length; ++lvt_2_1_) {
         String lvt_3_1_ = p_71527_0_[lvt_2_1_].toString();
         if (lvt_2_1_ > 0) {
            if (lvt_2_1_ == p_71527_0_.length - 1) {
               lvt_1_1_.append(" and ");
            } else {
               lvt_1_1_.append(", ");
            }
         }

         lvt_1_1_.append(lvt_3_1_);
      }

      return lvt_1_1_.toString();
   }

   public static IChatComponent func_180530_a(List<IChatComponent> p_180530_0_) {
      IChatComponent lvt_1_1_ = new ChatComponentText("");

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_180530_0_.size(); ++lvt_2_1_) {
         if (lvt_2_1_ > 0) {
            if (lvt_2_1_ == p_180530_0_.size() - 1) {
               lvt_1_1_.func_150258_a(" and ");
            } else if (lvt_2_1_ > 0) {
               lvt_1_1_.func_150258_a(", ");
            }
         }

         lvt_1_1_.func_150257_a((IChatComponent)p_180530_0_.get(lvt_2_1_));
      }

      return lvt_1_1_;
   }

   public static String func_96333_a(Collection<String> p_96333_0_) {
      return func_71527_a(p_96333_0_.toArray(new String[p_96333_0_.size()]));
   }

   public static List<String> func_175771_a(String[] p_175771_0_, int p_175771_1_, BlockPos p_175771_2_) {
      if (p_175771_2_ == null) {
         return null;
      } else {
         int lvt_4_1_ = p_175771_0_.length - 1;
         String lvt_3_4_;
         if (lvt_4_1_ == p_175771_1_) {
            lvt_3_4_ = Integer.toString(p_175771_2_.func_177958_n());
         } else if (lvt_4_1_ == p_175771_1_ + 1) {
            lvt_3_4_ = Integer.toString(p_175771_2_.func_177956_o());
         } else {
            if (lvt_4_1_ != p_175771_1_ + 2) {
               return null;
            }

            lvt_3_4_ = Integer.toString(p_175771_2_.func_177952_p());
         }

         return Lists.newArrayList((Object[])(lvt_3_4_));
      }
   }

   public static List<String> func_181043_b(String[] p_181043_0_, int p_181043_1_, BlockPos p_181043_2_) {
      if (p_181043_2_ == null) {
         return null;
      } else {
         int lvt_4_1_ = p_181043_0_.length - 1;
         String lvt_3_3_;
         if (lvt_4_1_ == p_181043_1_) {
            lvt_3_3_ = Integer.toString(p_181043_2_.func_177958_n());
         } else {
            if (lvt_4_1_ != p_181043_1_ + 1) {
               return null;
            }

            lvt_3_3_ = Integer.toString(p_181043_2_.func_177952_p());
         }

         return Lists.newArrayList((Object[])(lvt_3_3_));
      }
   }

   public static boolean func_71523_a(String p_71523_0_, String p_71523_1_) {
      return p_71523_1_.regionMatches(true, 0, p_71523_0_, 0, p_71523_0_.length());
   }

   public static List<String> func_71530_a(String[] p_71530_0_, String... p_71530_1_) {
      return func_175762_a(p_71530_0_, Arrays.asList(p_71530_1_));
   }

   public static List<String> func_175762_a(String[] p_175762_0_, Collection<?> p_175762_1_) {
      String lvt_2_1_ = p_175762_0_[p_175762_0_.length - 1];
      List<String> lvt_3_1_ = Lists.newArrayList();
      if (!p_175762_1_.isEmpty()) {
         Iterator lvt_4_2_ = Iterables.transform(p_175762_1_, Functions.toStringFunction()).iterator();

         while(lvt_4_2_.hasNext()) {
            String lvt_5_1_ = (String)lvt_4_2_.next();
            if (func_71523_a(lvt_2_1_, lvt_5_1_)) {
               lvt_3_1_.add(lvt_5_1_);
            }
         }

         if (lvt_3_1_.isEmpty()) {
            lvt_4_2_ = p_175762_1_.iterator();

            while(lvt_4_2_.hasNext()) {
               Object lvt_5_2_ = lvt_4_2_.next();
               if (lvt_5_2_ instanceof ResourceLocation && func_71523_a(lvt_2_1_, ((ResourceLocation)lvt_5_2_).func_110623_a())) {
                  lvt_3_1_.add(String.valueOf(lvt_5_2_));
               }
            }
         }
      }

      return lvt_3_1_;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return false;
   }

   public static void func_152373_a(ICommandSender p_152373_0_, ICommand p_152373_1_, String p_152373_2_, Object... p_152373_3_) {
      func_152374_a(p_152373_0_, p_152373_1_, 0, p_152373_2_, p_152373_3_);
   }

   public static void func_152374_a(ICommandSender p_152374_0_, ICommand p_152374_1_, int p_152374_2_, String p_152374_3_, Object... p_152374_4_) {
      if (field_71533_a != null) {
         field_71533_a.func_152372_a(p_152374_0_, p_152374_1_, p_152374_2_, p_152374_3_, p_152374_4_);
      }

   }

   public static void func_71529_a(IAdminCommand p_71529_0_) {
      field_71533_a = p_71529_0_;
   }

   public int compareTo(ICommand p_compareTo_1_) {
      return this.func_71517_b().compareTo(p_compareTo_1_.func_71517_b());
   }

   public static class CoordinateArg {
      private final double field_179633_a;
      private final double field_179631_b;
      private final boolean field_179632_c;

      protected CoordinateArg(double p_i46051_1_, double p_i46051_3_, boolean p_i46051_5_) {
         this.field_179633_a = p_i46051_1_;
         this.field_179631_b = p_i46051_3_;
         this.field_179632_c = p_i46051_5_;
      }

      public double func_179628_a() {
         return this.field_179633_a;
      }

      public double func_179629_b() {
         return this.field_179631_b;
      }

      public boolean func_179630_c() {
         return this.field_179632_c;
      }
   }
}
