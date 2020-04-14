package net.minecraft.client.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityList;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import tv.twitch.chat.ChatUserInfo;

public abstract class GuiScreen extends Gui implements GuiYesNoCallback {
   private static final Logger field_175287_a = LogManager.getLogger();
   private static final Set<String> field_175284_f = Sets.newHashSet((Object[])("http", "https"));
   private static final Splitter field_175285_g = Splitter.on('\n');
   protected Minecraft field_146297_k;
   protected RenderItem field_146296_j;
   public int field_146294_l;
   public int field_146295_m;
   protected List<GuiButton> field_146292_n = Lists.newArrayList();
   protected List<GuiLabel> field_146293_o = Lists.newArrayList();
   public boolean field_146291_p;
   protected FontRenderer field_146289_q;
   private GuiButton field_146290_a;
   private int field_146287_f;
   private long field_146288_g;
   private int field_146298_h;
   private URI field_175286_t;

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      int lvt_4_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < this.field_146292_n.size(); ++lvt_4_2_) {
         ((GuiButton)this.field_146292_n.get(lvt_4_2_)).func_146112_a(this.field_146297_k, p_73863_1_, p_73863_2_);
      }

      for(lvt_4_2_ = 0; lvt_4_2_ < this.field_146293_o.size(); ++lvt_4_2_) {
         ((GuiLabel)this.field_146293_o.get(lvt_4_2_)).func_146159_a(this.field_146297_k, p_73863_1_, p_73863_2_);
      }

   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (p_73869_2_ == 1) {
         this.field_146297_k.func_147108_a((GuiScreen)null);
         if (this.field_146297_k.field_71462_r == null) {
            this.field_146297_k.func_71381_h();
         }
      }

   }

   public static String func_146277_j() {
      try {
         Transferable lvt_0_1_ = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);
         if (lvt_0_1_ != null && lvt_0_1_.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String)lvt_0_1_.getTransferData(DataFlavor.stringFlavor);
         }
      } catch (Exception var1) {
      }

      return "";
   }

   public static void func_146275_d(String p_146275_0_) {
      if (!StringUtils.isEmpty(p_146275_0_)) {
         try {
            StringSelection lvt_1_1_ = new StringSelection(p_146275_0_);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(lvt_1_1_, (ClipboardOwner)null);
         } catch (Exception var2) {
         }

      }
   }

   protected void func_146285_a(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_) {
      List<String> lvt_4_1_ = p_146285_1_.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.size(); ++lvt_5_1_) {
         if (lvt_5_1_ == 0) {
            lvt_4_1_.set(lvt_5_1_, p_146285_1_.func_77953_t().field_77937_e + (String)lvt_4_1_.get(lvt_5_1_));
         } else {
            lvt_4_1_.set(lvt_5_1_, EnumChatFormatting.GRAY + (String)lvt_4_1_.get(lvt_5_1_));
         }
      }

      this.func_146283_a(lvt_4_1_, p_146285_2_, p_146285_3_);
   }

   protected void func_146279_a(String p_146279_1_, int p_146279_2_, int p_146279_3_) {
      this.func_146283_a(Arrays.asList(p_146279_1_), p_146279_2_, p_146279_3_);
   }

   protected void func_146283_a(List<String> p_146283_1_, int p_146283_2_, int p_146283_3_) {
      if (!p_146283_1_.isEmpty()) {
         GlStateManager.func_179101_C();
         RenderHelper.func_74518_a();
         GlStateManager.func_179140_f();
         GlStateManager.func_179097_i();
         int lvt_4_1_ = 0;
         Iterator lvt_5_1_ = p_146283_1_.iterator();

         while(lvt_5_1_.hasNext()) {
            String lvt_6_1_ = (String)lvt_5_1_.next();
            int lvt_7_1_ = this.field_146289_q.func_78256_a(lvt_6_1_);
            if (lvt_7_1_ > lvt_4_1_) {
               lvt_4_1_ = lvt_7_1_;
            }
         }

         int lvt_5_2_ = p_146283_2_ + 12;
         int lvt_6_2_ = p_146283_3_ - 12;
         int lvt_8_1_ = 8;
         if (p_146283_1_.size() > 1) {
            lvt_8_1_ += 2 + (p_146283_1_.size() - 1) * 10;
         }

         if (lvt_5_2_ + lvt_4_1_ > this.field_146294_l) {
            lvt_5_2_ -= 28 + lvt_4_1_;
         }

         if (lvt_6_2_ + lvt_8_1_ + 6 > this.field_146295_m) {
            lvt_6_2_ = this.field_146295_m - lvt_8_1_ - 6;
         }

         this.field_73735_i = 300.0F;
         this.field_146296_j.field_77023_b = 300.0F;
         int lvt_9_1_ = -267386864;
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ - 4, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ - 3, lvt_9_1_, lvt_9_1_);
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ + lvt_8_1_ + 3, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ + lvt_8_1_ + 4, lvt_9_1_, lvt_9_1_);
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ - 3, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ + lvt_8_1_ + 3, lvt_9_1_, lvt_9_1_);
         this.func_73733_a(lvt_5_2_ - 4, lvt_6_2_ - 3, lvt_5_2_ - 3, lvt_6_2_ + lvt_8_1_ + 3, lvt_9_1_, lvt_9_1_);
         this.func_73733_a(lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ - 3, lvt_5_2_ + lvt_4_1_ + 4, lvt_6_2_ + lvt_8_1_ + 3, lvt_9_1_, lvt_9_1_);
         int lvt_10_1_ = 1347420415;
         int lvt_11_1_ = (lvt_10_1_ & 16711422) >> 1 | lvt_10_1_ & -16777216;
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ - 3 + 1, lvt_5_2_ - 3 + 1, lvt_6_2_ + lvt_8_1_ + 3 - 1, lvt_10_1_, lvt_11_1_);
         this.func_73733_a(lvt_5_2_ + lvt_4_1_ + 2, lvt_6_2_ - 3 + 1, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ + lvt_8_1_ + 3 - 1, lvt_10_1_, lvt_11_1_);
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ - 3, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ - 3 + 1, lvt_10_1_, lvt_10_1_);
         this.func_73733_a(lvt_5_2_ - 3, lvt_6_2_ + lvt_8_1_ + 2, lvt_5_2_ + lvt_4_1_ + 3, lvt_6_2_ + lvt_8_1_ + 3, lvt_11_1_, lvt_11_1_);

         for(int lvt_12_1_ = 0; lvt_12_1_ < p_146283_1_.size(); ++lvt_12_1_) {
            String lvt_13_1_ = (String)p_146283_1_.get(lvt_12_1_);
            this.field_146289_q.func_175063_a(lvt_13_1_, (float)lvt_5_2_, (float)lvt_6_2_, -1);
            if (lvt_12_1_ == 0) {
               lvt_6_2_ += 2;
            }

            lvt_6_2_ += 10;
         }

         this.field_73735_i = 0.0F;
         this.field_146296_j.field_77023_b = 0.0F;
         GlStateManager.func_179145_e();
         GlStateManager.func_179126_j();
         RenderHelper.func_74519_b();
         GlStateManager.func_179091_B();
      }
   }

   protected void func_175272_a(IChatComponent p_175272_1_, int p_175272_2_, int p_175272_3_) {
      if (p_175272_1_ != null && p_175272_1_.func_150256_b().func_150210_i() != null) {
         HoverEvent lvt_4_1_ = p_175272_1_.func_150256_b().func_150210_i();
         if (lvt_4_1_.func_150701_a() == HoverEvent.Action.SHOW_ITEM) {
            ItemStack lvt_5_1_ = null;

            try {
               NBTBase lvt_6_1_ = JsonToNBT.func_180713_a(lvt_4_1_.func_150702_b().func_150260_c());
               if (lvt_6_1_ instanceof NBTTagCompound) {
                  lvt_5_1_ = ItemStack.func_77949_a((NBTTagCompound)lvt_6_1_);
               }
            } catch (NBTException var11) {
            }

            if (lvt_5_1_ != null) {
               this.func_146285_a(lvt_5_1_, p_175272_2_, p_175272_3_);
            } else {
               this.func_146279_a(EnumChatFormatting.RED + "Invalid Item!", p_175272_2_, p_175272_3_);
            }
         } else {
            String lvt_8_2_;
            if (lvt_4_1_.func_150701_a() == HoverEvent.Action.SHOW_ENTITY) {
               if (this.field_146297_k.field_71474_y.field_82882_x) {
                  try {
                     NBTBase lvt_5_2_ = JsonToNBT.func_180713_a(lvt_4_1_.func_150702_b().func_150260_c());
                     if (lvt_5_2_ instanceof NBTTagCompound) {
                        List<String> lvt_6_2_ = Lists.newArrayList();
                        NBTTagCompound lvt_7_1_ = (NBTTagCompound)lvt_5_2_;
                        lvt_6_2_.add(lvt_7_1_.func_74779_i("name"));
                        if (lvt_7_1_.func_150297_b("type", 8)) {
                           lvt_8_2_ = lvt_7_1_.func_74779_i("type");
                           lvt_6_2_.add("Type: " + lvt_8_2_ + " (" + EntityList.func_180122_a(lvt_8_2_) + ")");
                        }

                        lvt_6_2_.add(lvt_7_1_.func_74779_i("id"));
                        this.func_146283_a(lvt_6_2_, p_175272_2_, p_175272_3_);
                     } else {
                        this.func_146279_a(EnumChatFormatting.RED + "Invalid Entity!", p_175272_2_, p_175272_3_);
                     }
                  } catch (NBTException var10) {
                     this.func_146279_a(EnumChatFormatting.RED + "Invalid Entity!", p_175272_2_, p_175272_3_);
                  }
               }
            } else if (lvt_4_1_.func_150701_a() == HoverEvent.Action.SHOW_TEXT) {
               this.func_146283_a(field_175285_g.splitToList(lvt_4_1_.func_150702_b().func_150254_d()), p_175272_2_, p_175272_3_);
            } else if (lvt_4_1_.func_150701_a() == HoverEvent.Action.SHOW_ACHIEVEMENT) {
               StatBase lvt_5_4_ = StatList.func_151177_a(lvt_4_1_.func_150702_b().func_150260_c());
               if (lvt_5_4_ != null) {
                  IChatComponent lvt_6_3_ = lvt_5_4_.func_150951_e();
                  IChatComponent lvt_7_2_ = new ChatComponentTranslation("stats.tooltip.type." + (lvt_5_4_.func_75967_d() ? "achievement" : "statistic"), new Object[0]);
                  lvt_7_2_.func_150256_b().func_150217_b(true);
                  lvt_8_2_ = lvt_5_4_ instanceof Achievement ? ((Achievement)lvt_5_4_).func_75989_e() : null;
                  List<String> lvt_9_1_ = Lists.newArrayList((Object[])(lvt_6_3_.func_150254_d(), lvt_7_2_.func_150254_d()));
                  if (lvt_8_2_ != null) {
                     lvt_9_1_.addAll(this.field_146289_q.func_78271_c(lvt_8_2_, 150));
                  }

                  this.func_146283_a(lvt_9_1_, p_175272_2_, p_175272_3_);
               } else {
                  this.func_146279_a(EnumChatFormatting.RED + "Invalid statistic/achievement!", p_175272_2_, p_175272_3_);
               }
            }
         }

         GlStateManager.func_179140_f();
      }
   }

   protected void func_175274_a(String p_175274_1_, boolean p_175274_2_) {
   }

   protected boolean func_175276_a(IChatComponent p_175276_1_) {
      if (p_175276_1_ == null) {
         return false;
      } else {
         ClickEvent lvt_2_1_ = p_175276_1_.func_150256_b().func_150235_h();
         if (func_146272_n()) {
            if (p_175276_1_.func_150256_b().func_179986_j() != null) {
               this.func_175274_a(p_175276_1_.func_150256_b().func_179986_j(), false);
            }
         } else if (lvt_2_1_ != null) {
            URI lvt_3_1_;
            if (lvt_2_1_.func_150669_a() == ClickEvent.Action.OPEN_URL) {
               if (!this.field_146297_k.field_71474_y.field_74359_p) {
                  return false;
               }

               try {
                  lvt_3_1_ = new URI(lvt_2_1_.func_150668_b());
                  String lvt_4_1_ = lvt_3_1_.getScheme();
                  if (lvt_4_1_ == null) {
                     throw new URISyntaxException(lvt_2_1_.func_150668_b(), "Missing protocol");
                  }

                  if (!field_175284_f.contains(lvt_4_1_.toLowerCase())) {
                     throw new URISyntaxException(lvt_2_1_.func_150668_b(), "Unsupported protocol: " + lvt_4_1_.toLowerCase());
                  }

                  if (this.field_146297_k.field_71474_y.field_74358_q) {
                     this.field_175286_t = lvt_3_1_;
                     this.field_146297_k.func_147108_a(new GuiConfirmOpenLink(this, lvt_2_1_.func_150668_b(), 31102009, false));
                  } else {
                     this.func_175282_a(lvt_3_1_);
                  }
               } catch (URISyntaxException var5) {
                  field_175287_a.error((String)("Can't open url for " + lvt_2_1_), (Throwable)var5);
               }
            } else if (lvt_2_1_.func_150669_a() == ClickEvent.Action.OPEN_FILE) {
               lvt_3_1_ = (new File(lvt_2_1_.func_150668_b())).toURI();
               this.func_175282_a(lvt_3_1_);
            } else if (lvt_2_1_.func_150669_a() == ClickEvent.Action.SUGGEST_COMMAND) {
               this.func_175274_a(lvt_2_1_.func_150668_b(), true);
            } else if (lvt_2_1_.func_150669_a() == ClickEvent.Action.RUN_COMMAND) {
               this.func_175281_b(lvt_2_1_.func_150668_b(), false);
            } else if (lvt_2_1_.func_150669_a() == ClickEvent.Action.TWITCH_USER_INFO) {
               ChatUserInfo lvt_3_4_ = this.field_146297_k.func_152346_Z().func_152926_a(lvt_2_1_.func_150668_b());
               if (lvt_3_4_ != null) {
                  this.field_146297_k.func_147108_a(new GuiTwitchUserMode(this.field_146297_k.func_152346_Z(), lvt_3_4_));
               } else {
                  field_175287_a.error("Tried to handle twitch user but couldn't find them!");
               }
            } else {
               field_175287_a.error("Don't know how to handle " + lvt_2_1_);
            }

            return true;
         }

         return false;
      }
   }

   public void func_175275_f(String p_175275_1_) {
      this.func_175281_b(p_175275_1_, true);
   }

   public void func_175281_b(String p_175281_1_, boolean p_175281_2_) {
      if (p_175281_2_) {
         this.field_146297_k.field_71456_v.func_146158_b().func_146239_a(p_175281_1_);
      }

      this.field_146297_k.field_71439_g.func_71165_d(p_175281_1_);
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      if (p_73864_3_ == 0) {
         for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_146292_n.size(); ++lvt_4_1_) {
            GuiButton lvt_5_1_ = (GuiButton)this.field_146292_n.get(lvt_4_1_);
            if (lvt_5_1_.func_146116_c(this.field_146297_k, p_73864_1_, p_73864_2_)) {
               this.field_146290_a = lvt_5_1_;
               lvt_5_1_.func_146113_a(this.field_146297_k.func_147118_V());
               this.func_146284_a(lvt_5_1_);
            }
         }
      }

   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      if (this.field_146290_a != null && p_146286_3_ == 0) {
         this.field_146290_a.func_146118_a(p_146286_1_, p_146286_2_);
         this.field_146290_a = null;
      }

   }

   protected void func_146273_a(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
   }

   public void func_146280_a(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_) {
      this.field_146297_k = p_146280_1_;
      this.field_146296_j = p_146280_1_.func_175599_af();
      this.field_146289_q = p_146280_1_.field_71466_p;
      this.field_146294_l = p_146280_2_;
      this.field_146295_m = p_146280_3_;
      this.field_146292_n.clear();
      this.func_73866_w_();
   }

   public void a(int p_a_1_, int p_a_2_) {
      this.field_146294_l = p_a_1_;
      this.field_146295_m = p_a_2_;
   }

   public void func_73866_w_() {
   }

   public void func_146269_k() throws IOException {
      if (Mouse.isCreated()) {
         while(Mouse.next()) {
            this.func_146274_d();
         }
      }

      if (Keyboard.isCreated()) {
         while(Keyboard.next()) {
            this.func_146282_l();
         }
      }

   }

   public void func_146274_d() throws IOException {
      int lvt_1_1_ = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
      int lvt_2_1_ = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
      int lvt_3_1_ = Mouse.getEventButton();
      if (Mouse.getEventButtonState()) {
         if (this.field_146297_k.field_71474_y.field_85185_A && this.field_146298_h++ > 0) {
            return;
         }

         this.field_146287_f = lvt_3_1_;
         this.field_146288_g = Minecraft.func_71386_F();
         this.func_73864_a(lvt_1_1_, lvt_2_1_, this.field_146287_f);
      } else if (lvt_3_1_ != -1) {
         if (this.field_146297_k.field_71474_y.field_85185_A && --this.field_146298_h > 0) {
            return;
         }

         this.field_146287_f = -1;
         this.func_146286_b(lvt_1_1_, lvt_2_1_, lvt_3_1_);
      } else if (this.field_146287_f != -1 && this.field_146288_g > 0L) {
         long lvt_4_1_ = Minecraft.func_71386_F() - this.field_146288_g;
         this.func_146273_a(lvt_1_1_, lvt_2_1_, this.field_146287_f, lvt_4_1_);
      }

   }

   public void func_146282_l() throws IOException {
      if (Keyboard.getEventKeyState()) {
         this.func_73869_a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
      }

      this.field_146297_k.func_152348_aa();
   }

   public void func_73876_c() {
   }

   public void func_146281_b() {
   }

   public void func_146276_q_() {
      this.func_146270_b(0);
   }

   public void func_146270_b(int p_146270_1_) {
      if (this.field_146297_k.field_71441_e != null) {
         this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, -1072689136, -804253680);
      } else {
         this.func_146278_c(p_146270_1_);
      }

   }

   public void func_146278_c(int p_146278_1_) {
      GlStateManager.func_179140_f();
      GlStateManager.func_179106_n();
      Tessellator lvt_2_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_3_1_ = lvt_2_1_.func_178180_c();
      this.field_146297_k.func_110434_K().func_110577_a(field_110325_k);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      float lvt_4_1_ = 32.0F;
      lvt_3_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      lvt_3_1_.func_181662_b(0.0D, (double)this.field_146295_m, 0.0D).func_181673_a(0.0D, (double)((float)this.field_146295_m / 32.0F + (float)p_146278_1_)).func_181669_b(64, 64, 64, 255).func_181675_d();
      lvt_3_1_.func_181662_b((double)this.field_146294_l, (double)this.field_146295_m, 0.0D).func_181673_a((double)((float)this.field_146294_l / 32.0F), (double)((float)this.field_146295_m / 32.0F + (float)p_146278_1_)).func_181669_b(64, 64, 64, 255).func_181675_d();
      lvt_3_1_.func_181662_b((double)this.field_146294_l, 0.0D, 0.0D).func_181673_a((double)((float)this.field_146294_l / 32.0F), (double)p_146278_1_).func_181669_b(64, 64, 64, 255).func_181675_d();
      lvt_3_1_.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, (double)p_146278_1_).func_181669_b(64, 64, 64, 255).func_181675_d();
      lvt_2_1_.func_78381_a();
   }

   public boolean func_73868_f() {
      return true;
   }

   public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
      if (p_73878_2_ == 31102009) {
         if (p_73878_1_) {
            this.func_175282_a(this.field_175286_t);
         }

         this.field_175286_t = null;
         this.field_146297_k.func_147108_a(this);
      }

   }

   private void func_175282_a(URI p_175282_1_) {
      try {
         Class<?> lvt_2_1_ = Class.forName("java.awt.Desktop");
         Object lvt_3_1_ = lvt_2_1_.getMethod("getDesktop").invoke((Object)null);
         lvt_2_1_.getMethod("browse", URI.class).invoke(lvt_3_1_, p_175282_1_);
      } catch (Throwable var4) {
         field_175287_a.error("Couldn't open link", var4);
      }

   }

   public static boolean func_146271_m() {
      if (Minecraft.field_142025_a) {
         return Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220);
      } else {
         return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
      }
   }

   public static boolean func_146272_n() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

   public static boolean func_175283_s() {
      return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
   }

   public static boolean func_175277_d(int p_175277_0_) {
      return p_175277_0_ == 45 && func_146271_m() && !func_146272_n() && !func_175283_s();
   }

   public static boolean func_175279_e(int p_175279_0_) {
      return p_175279_0_ == 47 && func_146271_m() && !func_146272_n() && !func_175283_s();
   }

   public static boolean func_175280_f(int p_175280_0_) {
      return p_175280_0_ == 46 && func_146271_m() && !func_146272_n() && !func_175283_s();
   }

   public static boolean func_175278_g(int p_175278_0_) {
      return p_175278_0_ == 30 && func_146271_m() && !func_146272_n() && !func_175283_s();
   }

   public void func_175273_b(Minecraft p_175273_1_, int p_175273_2_, int p_175273_3_) {
      this.func_146280_a(p_175273_1_, p_175273_2_, p_175273_3_);
   }
}
