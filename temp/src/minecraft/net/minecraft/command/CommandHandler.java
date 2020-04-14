package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler implements ICommandManager {
   private static final Logger field_147175_a = LogManager.getLogger();
   private final Map<String, ICommand> field_71562_a = Maps.newHashMap();
   private final Set<ICommand> field_71561_b = Sets.newHashSet();

   public int func_71556_a(ICommandSender p_71556_1_, String p_71556_2_) {
      p_71556_2_ = p_71556_2_.trim();
      if (p_71556_2_.startsWith("/")) {
         p_71556_2_ = p_71556_2_.substring(1);
      }

      String[] lvt_3_1_ = p_71556_2_.split(" ");
      String lvt_4_1_ = lvt_3_1_[0];
      lvt_3_1_ = func_71559_a(lvt_3_1_);
      ICommand lvt_5_1_ = (ICommand)this.field_71562_a.get(lvt_4_1_);
      int lvt_6_1_ = this.func_82370_a(lvt_5_1_, lvt_3_1_);
      int lvt_7_1_ = 0;
      ChatComponentTranslation lvt_8_3_;
      if (lvt_5_1_ == null) {
         lvt_8_3_ = new ChatComponentTranslation("commands.generic.notFound", new Object[0]);
         lvt_8_3_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_71556_1_.func_145747_a(lvt_8_3_);
      } else if (lvt_5_1_.func_71519_b(p_71556_1_)) {
         if (lvt_6_1_ > -1) {
            List<Entity> lvt_8_2_ = PlayerSelector.func_179656_b(p_71556_1_, lvt_3_1_[lvt_6_1_], Entity.class);
            String lvt_9_1_ = lvt_3_1_[lvt_6_1_];
            p_71556_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_8_2_.size());
            Iterator lvt_10_1_ = lvt_8_2_.iterator();

            while(lvt_10_1_.hasNext()) {
               Entity lvt_11_1_ = (Entity)lvt_10_1_.next();
               lvt_3_1_[lvt_6_1_] = lvt_11_1_.func_110124_au().toString();
               if (this.func_175786_a(p_71556_1_, lvt_3_1_, lvt_5_1_, p_71556_2_)) {
                  ++lvt_7_1_;
               }
            }

            lvt_3_1_[lvt_6_1_] = lvt_9_1_;
         } else {
            p_71556_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, 1);
            if (this.func_175786_a(p_71556_1_, lvt_3_1_, lvt_5_1_, p_71556_2_)) {
               ++lvt_7_1_;
            }
         }
      } else {
         lvt_8_3_ = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
         lvt_8_3_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_71556_1_.func_145747_a(lvt_8_3_);
      }

      p_71556_1_.func_174794_a(CommandResultStats.Type.SUCCESS_COUNT, lvt_7_1_);
      return lvt_7_1_;
   }

   protected boolean func_175786_a(ICommandSender p_175786_1_, String[] p_175786_2_, ICommand p_175786_3_, String p_175786_4_) {
      ChatComponentTranslation lvt_6_3_;
      try {
         p_175786_3_.func_71515_b(p_175786_1_, p_175786_2_);
         return true;
      } catch (WrongUsageException var7) {
         lvt_6_3_ = new ChatComponentTranslation("commands.generic.usage", new Object[]{new ChatComponentTranslation(var7.getMessage(), var7.func_74844_a())});
         lvt_6_3_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(lvt_6_3_);
      } catch (CommandException var8) {
         lvt_6_3_ = new ChatComponentTranslation(var8.getMessage(), var8.func_74844_a());
         lvt_6_3_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(lvt_6_3_);
      } catch (Throwable var9) {
         lvt_6_3_ = new ChatComponentTranslation("commands.generic.exception", new Object[0]);
         lvt_6_3_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(lvt_6_3_);
         field_147175_a.warn("Couldn't process command: '" + p_175786_4_ + "'");
      }

      return false;
   }

   public ICommand func_71560_a(ICommand p_71560_1_) {
      this.field_71562_a.put(p_71560_1_.func_71517_b(), p_71560_1_);
      this.field_71561_b.add(p_71560_1_);
      Iterator lvt_2_1_ = p_71560_1_.func_71514_a().iterator();

      while(true) {
         String lvt_3_1_;
         ICommand lvt_4_1_;
         do {
            if (!lvt_2_1_.hasNext()) {
               return p_71560_1_;
            }

            lvt_3_1_ = (String)lvt_2_1_.next();
            lvt_4_1_ = (ICommand)this.field_71562_a.get(lvt_3_1_);
         } while(lvt_4_1_ != null && lvt_4_1_.func_71517_b().equals(lvt_3_1_));

         this.field_71562_a.put(lvt_3_1_, p_71560_1_);
      }
   }

   private static String[] func_71559_a(String[] p_71559_0_) {
      String[] lvt_1_1_ = new String[p_71559_0_.length - 1];
      System.arraycopy(p_71559_0_, 1, lvt_1_1_, 0, p_71559_0_.length - 1);
      return lvt_1_1_;
   }

   public List<String> func_180524_a(ICommandSender p_180524_1_, String p_180524_2_, BlockPos p_180524_3_) {
      String[] lvt_4_1_ = p_180524_2_.split(" ", -1);
      String lvt_5_1_ = lvt_4_1_[0];
      if (lvt_4_1_.length == 1) {
         List<String> lvt_6_1_ = Lists.newArrayList();
         Iterator lvt_7_1_ = this.field_71562_a.entrySet().iterator();

         while(lvt_7_1_.hasNext()) {
            Entry<String, ICommand> lvt_8_1_ = (Entry)lvt_7_1_.next();
            if (CommandBase.func_71523_a(lvt_5_1_, (String)lvt_8_1_.getKey()) && ((ICommand)lvt_8_1_.getValue()).func_71519_b(p_180524_1_)) {
               lvt_6_1_.add(lvt_8_1_.getKey());
            }
         }

         return lvt_6_1_;
      } else {
         if (lvt_4_1_.length > 1) {
            ICommand lvt_6_2_ = (ICommand)this.field_71562_a.get(lvt_5_1_);
            if (lvt_6_2_ != null && lvt_6_2_.func_71519_b(p_180524_1_)) {
               return lvt_6_2_.func_180525_a(p_180524_1_, func_71559_a(lvt_4_1_), p_180524_3_);
            }
         }

         return null;
      }
   }

   public List<ICommand> func_71557_a(ICommandSender p_71557_1_) {
      List<ICommand> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = this.field_71561_b.iterator();

      while(lvt_3_1_.hasNext()) {
         ICommand lvt_4_1_ = (ICommand)lvt_3_1_.next();
         if (lvt_4_1_.func_71519_b(p_71557_1_)) {
            lvt_2_1_.add(lvt_4_1_);
         }
      }

      return lvt_2_1_;
   }

   public Map<String, ICommand> func_71555_a() {
      return this.field_71562_a;
   }

   private int func_82370_a(ICommand p_82370_1_, String[] p_82370_2_) {
      if (p_82370_1_ == null) {
         return -1;
      } else {
         for(int lvt_3_1_ = 0; lvt_3_1_ < p_82370_2_.length; ++lvt_3_1_) {
            if (p_82370_1_.func_82358_a(p_82370_2_, lvt_3_1_) && PlayerSelector.func_82377_a(p_82370_2_[lvt_3_1_])) {
               return lvt_3_1_;
            }
         }

         return -1;
      }
   }
}
