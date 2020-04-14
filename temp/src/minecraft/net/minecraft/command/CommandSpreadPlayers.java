package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandSpreadPlayers extends CommandBase {
   public String func_71517_b() {
      return "spreadplayers";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.spreadplayers.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if (p_71515_2_.length < 6) {
         throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
      } else {
         int lvt_3_1_ = 0;
         BlockPos lvt_4_1_ = p_71515_1_.func_180425_c();
         double var10000 = (double)lvt_4_1_.func_177958_n();
         int lvt_3_1_ = lvt_3_1_ + 1;
         double lvt_5_1_ = func_175761_b(var10000, p_71515_2_[lvt_3_1_], true);
         double lvt_7_1_ = func_175761_b((double)lvt_4_1_.func_177952_p(), p_71515_2_[lvt_3_1_++], true);
         double lvt_9_1_ = func_180526_a(p_71515_2_[lvt_3_1_++], 0.0D);
         double lvt_11_1_ = func_180526_a(p_71515_2_[lvt_3_1_++], lvt_9_1_ + 1.0D);
         boolean lvt_13_1_ = func_180527_d(p_71515_2_[lvt_3_1_++]);
         ArrayList lvt_14_1_ = Lists.newArrayList();

         while(lvt_3_1_ < p_71515_2_.length) {
            String lvt_15_1_ = p_71515_2_[lvt_3_1_++];
            if (PlayerSelector.func_82378_b(lvt_15_1_)) {
               List<Entity> lvt_16_1_ = PlayerSelector.func_179656_b(p_71515_1_, lvt_15_1_, Entity.class);
               if (lvt_16_1_.size() == 0) {
                  throw new EntityNotFoundException();
               }

               lvt_14_1_.addAll(lvt_16_1_);
            } else {
               EntityPlayer lvt_16_2_ = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(lvt_15_1_);
               if (lvt_16_2_ == null) {
                  throw new PlayerNotFoundException();
               }

               lvt_14_1_.add(lvt_16_2_);
            }
         }

         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, lvt_14_1_.size());
         if (lvt_14_1_.isEmpty()) {
            throw new EntityNotFoundException();
         } else {
            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.spreadplayers.spreading." + (lvt_13_1_ ? "teams" : "players"), new Object[]{lvt_14_1_.size(), lvt_11_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_}));
            this.func_110669_a(p_71515_1_, lvt_14_1_, new CommandSpreadPlayers.Position(lvt_5_1_, lvt_7_1_), lvt_9_1_, lvt_11_1_, ((Entity)lvt_14_1_.get(0)).field_70170_p, lvt_13_1_);
         }
      }
   }

   private void func_110669_a(ICommandSender p_110669_1_, List<Entity> p_110669_2_, CommandSpreadPlayers.Position p_110669_3_, double p_110669_4_, double p_110669_6_, World p_110669_8_, boolean p_110669_9_) throws CommandException {
      Random lvt_10_1_ = new Random();
      double lvt_11_1_ = p_110669_3_.field_111101_a - p_110669_6_;
      double lvt_13_1_ = p_110669_3_.field_111100_b - p_110669_6_;
      double lvt_15_1_ = p_110669_3_.field_111101_a + p_110669_6_;
      double lvt_17_1_ = p_110669_3_.field_111100_b + p_110669_6_;
      CommandSpreadPlayers.Position[] lvt_19_1_ = this.func_110670_a(lvt_10_1_, p_110669_9_ ? this.func_110667_a(p_110669_2_) : p_110669_2_.size(), lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_);
      int lvt_20_1_ = this.func_110668_a(p_110669_3_, p_110669_4_, p_110669_8_, lvt_10_1_, lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_, lvt_19_1_, p_110669_9_);
      double lvt_21_1_ = this.func_110671_a(p_110669_2_, p_110669_8_, lvt_19_1_, p_110669_9_);
      func_152373_a(p_110669_1_, this, "commands.spreadplayers.success." + (p_110669_9_ ? "teams" : "players"), new Object[]{lvt_19_1_.length, p_110669_3_.field_111101_a, p_110669_3_.field_111100_b});
      if (lvt_19_1_.length > 1) {
         p_110669_1_.func_145747_a(new ChatComponentTranslation("commands.spreadplayers.info." + (p_110669_9_ ? "teams" : "players"), new Object[]{String.format("%.2f", lvt_21_1_), lvt_20_1_}));
      }

   }

   private int func_110667_a(List<Entity> p_110667_1_) {
      Set<Team> lvt_2_1_ = Sets.newHashSet();
      Iterator lvt_3_1_ = p_110667_1_.iterator();

      while(lvt_3_1_.hasNext()) {
         Entity lvt_4_1_ = (Entity)lvt_3_1_.next();
         if (lvt_4_1_ instanceof EntityPlayer) {
            lvt_2_1_.add(((EntityPlayer)lvt_4_1_).func_96124_cp());
         } else {
            lvt_2_1_.add((Object)null);
         }
      }

      return lvt_2_1_.size();
   }

   private int func_110668_a(CommandSpreadPlayers.Position p_110668_1_, double p_110668_2_, World p_110668_4_, Random p_110668_5_, double p_110668_6_, double p_110668_8_, double p_110668_10_, double p_110668_12_, CommandSpreadPlayers.Position[] p_110668_14_, boolean p_110668_15_) throws CommandException {
      boolean lvt_16_1_ = true;
      double lvt_18_1_ = 3.4028234663852886E38D;

      int lvt_17_1_;
      for(lvt_17_1_ = 0; lvt_17_1_ < 10000 && lvt_16_1_; ++lvt_17_1_) {
         lvt_16_1_ = false;
         lvt_18_1_ = 3.4028234663852886E38D;

         int lvt_22_2_;
         CommandSpreadPlayers.Position lvt_23_2_;
         for(int lvt_20_1_ = 0; lvt_20_1_ < p_110668_14_.length; ++lvt_20_1_) {
            CommandSpreadPlayers.Position lvt_21_1_ = p_110668_14_[lvt_20_1_];
            lvt_22_2_ = 0;
            lvt_23_2_ = new CommandSpreadPlayers.Position();

            for(int lvt_24_1_ = 0; lvt_24_1_ < p_110668_14_.length; ++lvt_24_1_) {
               if (lvt_20_1_ != lvt_24_1_) {
                  CommandSpreadPlayers.Position lvt_25_1_ = p_110668_14_[lvt_24_1_];
                  double lvt_26_1_ = lvt_21_1_.func_111099_a(lvt_25_1_);
                  lvt_18_1_ = Math.min(lvt_26_1_, lvt_18_1_);
                  if (lvt_26_1_ < p_110668_2_) {
                     ++lvt_22_2_;
                     lvt_23_2_.field_111101_a += lvt_25_1_.field_111101_a - lvt_21_1_.field_111101_a;
                     lvt_23_2_.field_111100_b += lvt_25_1_.field_111100_b - lvt_21_1_.field_111100_b;
                  }
               }
            }

            if (lvt_22_2_ > 0) {
               lvt_23_2_.field_111101_a /= (double)lvt_22_2_;
               lvt_23_2_.field_111100_b /= (double)lvt_22_2_;
               double lvt_24_2_ = (double)lvt_23_2_.func_111096_b();
               if (lvt_24_2_ > 0.0D) {
                  lvt_23_2_.func_111095_a();
                  lvt_21_1_.func_111094_b(lvt_23_2_);
               } else {
                  lvt_21_1_.func_111097_a(p_110668_5_, p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_);
               }

               lvt_16_1_ = true;
            }

            if (lvt_21_1_.func_111093_a(p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_)) {
               lvt_16_1_ = true;
            }
         }

         if (!lvt_16_1_) {
            CommandSpreadPlayers.Position[] lvt_20_2_ = p_110668_14_;
            int lvt_21_2_ = p_110668_14_.length;

            for(lvt_22_2_ = 0; lvt_22_2_ < lvt_21_2_; ++lvt_22_2_) {
               lvt_23_2_ = lvt_20_2_[lvt_22_2_];
               if (!lvt_23_2_.func_111098_b(p_110668_4_)) {
                  lvt_23_2_.func_111097_a(p_110668_5_, p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_);
                  lvt_16_1_ = true;
               }
            }
         }
      }

      if (lvt_17_1_ >= 10000) {
         throw new CommandException("commands.spreadplayers.failure." + (p_110668_15_ ? "teams" : "players"), new Object[]{p_110668_14_.length, p_110668_1_.field_111101_a, p_110668_1_.field_111100_b, String.format("%.2f", lvt_18_1_)});
      } else {
         return lvt_17_1_;
      }
   }

   private double func_110671_a(List<Entity> p_110671_1_, World p_110671_2_, CommandSpreadPlayers.Position[] p_110671_3_, boolean p_110671_4_) {
      double lvt_5_1_ = 0.0D;
      int lvt_7_1_ = 0;
      Map<Team, CommandSpreadPlayers.Position> lvt_8_1_ = Maps.newHashMap();

      for(int lvt_9_1_ = 0; lvt_9_1_ < p_110671_1_.size(); ++lvt_9_1_) {
         Entity lvt_10_1_ = (Entity)p_110671_1_.get(lvt_9_1_);
         CommandSpreadPlayers.Position lvt_11_2_;
         if (p_110671_4_) {
            Team lvt_12_1_ = lvt_10_1_ instanceof EntityPlayer ? ((EntityPlayer)lvt_10_1_).func_96124_cp() : null;
            if (!lvt_8_1_.containsKey(lvt_12_1_)) {
               lvt_8_1_.put(lvt_12_1_, p_110671_3_[lvt_7_1_++]);
            }

            lvt_11_2_ = (CommandSpreadPlayers.Position)lvt_8_1_.get(lvt_12_1_);
         } else {
            lvt_11_2_ = p_110671_3_[lvt_7_1_++];
         }

         lvt_10_1_.func_70634_a((double)((float)MathHelper.func_76128_c(lvt_11_2_.field_111101_a) + 0.5F), (double)lvt_11_2_.func_111092_a(p_110671_2_), (double)MathHelper.func_76128_c(lvt_11_2_.field_111100_b) + 0.5D);
         double lvt_12_2_ = Double.MAX_VALUE;

         for(int lvt_14_1_ = 0; lvt_14_1_ < p_110671_3_.length; ++lvt_14_1_) {
            if (lvt_11_2_ != p_110671_3_[lvt_14_1_]) {
               double lvt_15_1_ = lvt_11_2_.func_111099_a(p_110671_3_[lvt_14_1_]);
               lvt_12_2_ = Math.min(lvt_15_1_, lvt_12_2_);
            }
         }

         lvt_5_1_ += lvt_12_2_;
      }

      lvt_5_1_ /= (double)p_110671_1_.size();
      return lvt_5_1_;
   }

   private CommandSpreadPlayers.Position[] func_110670_a(Random p_110670_1_, int p_110670_2_, double p_110670_3_, double p_110670_5_, double p_110670_7_, double p_110670_9_) {
      CommandSpreadPlayers.Position[] lvt_11_1_ = new CommandSpreadPlayers.Position[p_110670_2_];

      for(int lvt_12_1_ = 0; lvt_12_1_ < lvt_11_1_.length; ++lvt_12_1_) {
         CommandSpreadPlayers.Position lvt_13_1_ = new CommandSpreadPlayers.Position();
         lvt_13_1_.func_111097_a(p_110670_1_, p_110670_3_, p_110670_5_, p_110670_7_, p_110670_9_);
         lvt_11_1_[lvt_12_1_] = lvt_13_1_;
      }

      return lvt_11_1_;
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length >= 1 && p_180525_2_.length <= 2 ? func_181043_b(p_180525_2_, 0, p_180525_3_) : null;
   }

   static class Position {
      double field_111101_a;
      double field_111100_b;

      Position() {
      }

      Position(double p_i1358_1_, double p_i1358_3_) {
         this.field_111101_a = p_i1358_1_;
         this.field_111100_b = p_i1358_3_;
      }

      double func_111099_a(CommandSpreadPlayers.Position p_111099_1_) {
         double lvt_2_1_ = this.field_111101_a - p_111099_1_.field_111101_a;
         double lvt_4_1_ = this.field_111100_b - p_111099_1_.field_111100_b;
         return Math.sqrt(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_);
      }

      void func_111095_a() {
         double lvt_1_1_ = (double)this.func_111096_b();
         this.field_111101_a /= lvt_1_1_;
         this.field_111100_b /= lvt_1_1_;
      }

      float func_111096_b() {
         return MathHelper.func_76133_a(this.field_111101_a * this.field_111101_a + this.field_111100_b * this.field_111100_b);
      }

      public void func_111094_b(CommandSpreadPlayers.Position p_111094_1_) {
         this.field_111101_a -= p_111094_1_.field_111101_a;
         this.field_111100_b -= p_111094_1_.field_111100_b;
      }

      public boolean func_111093_a(double p_111093_1_, double p_111093_3_, double p_111093_5_, double p_111093_7_) {
         boolean lvt_9_1_ = false;
         if (this.field_111101_a < p_111093_1_) {
            this.field_111101_a = p_111093_1_;
            lvt_9_1_ = true;
         } else if (this.field_111101_a > p_111093_5_) {
            this.field_111101_a = p_111093_5_;
            lvt_9_1_ = true;
         }

         if (this.field_111100_b < p_111093_3_) {
            this.field_111100_b = p_111093_3_;
            lvt_9_1_ = true;
         } else if (this.field_111100_b > p_111093_7_) {
            this.field_111100_b = p_111093_7_;
            lvt_9_1_ = true;
         }

         return lvt_9_1_;
      }

      public int func_111092_a(World p_111092_1_) {
         BlockPos lvt_2_1_ = new BlockPos(this.field_111101_a, 256.0D, this.field_111100_b);

         do {
            if (lvt_2_1_.func_177956_o() <= 0) {
               return 257;
            }

            lvt_2_1_ = lvt_2_1_.func_177977_b();
         } while(p_111092_1_.func_180495_p(lvt_2_1_).func_177230_c().func_149688_o() == Material.field_151579_a);

         return lvt_2_1_.func_177956_o() + 1;
      }

      public boolean func_111098_b(World p_111098_1_) {
         BlockPos lvt_2_1_ = new BlockPos(this.field_111101_a, 256.0D, this.field_111100_b);

         Material lvt_3_1_;
         do {
            if (lvt_2_1_.func_177956_o() <= 0) {
               return false;
            }

            lvt_2_1_ = lvt_2_1_.func_177977_b();
            lvt_3_1_ = p_111098_1_.func_180495_p(lvt_2_1_).func_177230_c().func_149688_o();
         } while(lvt_3_1_ == Material.field_151579_a);

         return !lvt_3_1_.func_76224_d() && lvt_3_1_ != Material.field_151581_o;
      }

      public void func_111097_a(Random p_111097_1_, double p_111097_2_, double p_111097_4_, double p_111097_6_, double p_111097_8_) {
         this.field_111101_a = MathHelper.func_82716_a(p_111097_1_, p_111097_2_, p_111097_6_);
         this.field_111100_b = MathHelper.func_82716_a(p_111097_1_, p_111097_4_, p_111097_8_);
      }
   }
}
