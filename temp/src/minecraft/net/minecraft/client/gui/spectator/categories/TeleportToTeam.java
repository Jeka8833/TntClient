package net.minecraft.client.gui.spectator.categories;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TeleportToTeam implements ISpectatorMenuView, ISpectatorMenuObject {
   private final List<ISpectatorMenuObject> field_178672_a = Lists.newArrayList();

   public TeleportToTeam() {
      Minecraft lvt_1_1_ = Minecraft.func_71410_x();
      Iterator lvt_2_1_ = lvt_1_1_.field_71441_e.func_96441_U().func_96525_g().iterator();

      while(lvt_2_1_.hasNext()) {
         ScorePlayerTeam lvt_3_1_ = (ScorePlayerTeam)lvt_2_1_.next();
         this.field_178672_a.add(new TeleportToTeam.TeamSelectionObject(lvt_3_1_));
      }

   }

   public List<ISpectatorMenuObject> func_178669_a() {
      return this.field_178672_a;
   }

   public IChatComponent func_178670_b() {
      return new ChatComponentText("Select a team to teleport to");
   }

   public void func_178661_a(SpectatorMenu p_178661_1_) {
      p_178661_1_.func_178647_a(this);
   }

   public IChatComponent func_178664_z_() {
      return new ChatComponentText("Teleport to team member");
   }

   public void func_178663_a(float p_178663_1_, int p_178663_2_) {
      Minecraft.func_71410_x().func_110434_K().func_110577_a(GuiSpectator.field_175269_a);
      Gui.func_146110_a(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public boolean func_178662_A_() {
      Iterator lvt_1_1_ = this.field_178672_a.iterator();

      ISpectatorMenuObject lvt_2_1_;
      do {
         if (!lvt_1_1_.hasNext()) {
            return false;
         }

         lvt_2_1_ = (ISpectatorMenuObject)lvt_1_1_.next();
      } while(!lvt_2_1_.func_178662_A_());

      return true;
   }

   class TeamSelectionObject implements ISpectatorMenuObject {
      private final ScorePlayerTeam field_178676_b;
      private final ResourceLocation field_178677_c;
      private final List<NetworkPlayerInfo> field_178675_d;

      public TeamSelectionObject(ScorePlayerTeam p_i45492_2_) {
         this.field_178676_b = p_i45492_2_;
         this.field_178675_d = Lists.newArrayList();
         Iterator lvt_3_1_ = p_i45492_2_.func_96670_d().iterator();

         while(lvt_3_1_.hasNext()) {
            String lvt_4_1_ = (String)lvt_3_1_.next();
            NetworkPlayerInfo lvt_5_1_ = Minecraft.func_71410_x().func_147114_u().func_175104_a(lvt_4_1_);
            if (lvt_5_1_ != null) {
               this.field_178675_d.add(lvt_5_1_);
            }
         }

         if (!this.field_178675_d.isEmpty()) {
            String lvt_3_2_ = ((NetworkPlayerInfo)this.field_178675_d.get((new Random()).nextInt(this.field_178675_d.size()))).func_178845_a().getName();
            this.field_178677_c = AbstractClientPlayer.func_110311_f(lvt_3_2_);
            AbstractClientPlayer.func_110304_a(this.field_178677_c, lvt_3_2_);
         } else {
            this.field_178677_c = DefaultPlayerSkin.func_177335_a();
         }

      }

      public void func_178661_a(SpectatorMenu p_178661_1_) {
         p_178661_1_.func_178647_a(new TeleportToPlayer(this.field_178675_d));
      }

      public IChatComponent func_178664_z_() {
         return new ChatComponentText(this.field_178676_b.func_96669_c());
      }

      public void func_178663_a(float p_178663_1_, int p_178663_2_) {
         int lvt_3_1_ = -1;
         String lvt_4_1_ = FontRenderer.func_78282_e(this.field_178676_b.func_96668_e());
         if (lvt_4_1_.length() >= 2) {
            lvt_3_1_ = Minecraft.func_71410_x().field_71466_p.func_175064_b(lvt_4_1_.charAt(1));
         }

         if (lvt_3_1_ >= 0) {
            float lvt_5_1_ = (float)(lvt_3_1_ >> 16 & 255) / 255.0F;
            float lvt_6_1_ = (float)(lvt_3_1_ >> 8 & 255) / 255.0F;
            float lvt_7_1_ = (float)(lvt_3_1_ & 255) / 255.0F;
            Gui.func_73734_a(1, 1, 15, 15, MathHelper.func_180183_b(lvt_5_1_ * p_178663_1_, lvt_6_1_ * p_178663_1_, lvt_7_1_ * p_178663_1_) | p_178663_2_ << 24);
         }

         Minecraft.func_71410_x().func_110434_K().func_110577_a(this.field_178677_c);
         GlStateManager.func_179131_c(p_178663_1_, p_178663_1_, p_178663_1_, (float)p_178663_2_ / 255.0F);
         Gui.func_152125_a(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
         Gui.func_152125_a(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      }

      public boolean func_178662_A_() {
         return !this.field_178675_d.isEmpty();
      }
   }
}
