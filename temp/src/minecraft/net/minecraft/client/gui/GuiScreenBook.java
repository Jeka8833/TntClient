package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiScreenBook extends GuiScreen {
   private static final Logger field_146473_a = LogManager.getLogger();
   private static final ResourceLocation field_146466_f = new ResourceLocation("textures/gui/book.png");
   private final EntityPlayer field_146468_g;
   private final ItemStack field_146474_h;
   private final boolean field_146475_i;
   private boolean field_146481_r;
   private boolean field_146480_s;
   private int field_146479_t;
   private int field_146478_u = 192;
   private int field_146477_v = 192;
   private int field_146476_w = 1;
   private int field_146484_x;
   private NBTTagList field_146483_y;
   private String field_146482_z = "";
   private List<IChatComponent> field_175386_A;
   private int field_175387_B = -1;
   private GuiScreenBook.NextPageButton field_146470_A;
   private GuiScreenBook.NextPageButton field_146471_B;
   private GuiButton field_146472_C;
   private GuiButton field_146465_D;
   private GuiButton field_146467_E;
   private GuiButton field_146469_F;

   public GuiScreenBook(EntityPlayer p_i1080_1_, ItemStack p_i1080_2_, boolean p_i1080_3_) {
      this.field_146468_g = p_i1080_1_;
      this.field_146474_h = p_i1080_2_;
      this.field_146475_i = p_i1080_3_;
      if (p_i1080_2_.func_77942_o()) {
         NBTTagCompound lvt_4_1_ = p_i1080_2_.func_77978_p();
         this.field_146483_y = lvt_4_1_.func_150295_c("pages", 8);
         if (this.field_146483_y != null) {
            this.field_146483_y = (NBTTagList)this.field_146483_y.func_74737_b();
            this.field_146476_w = this.field_146483_y.func_74745_c();
            if (this.field_146476_w < 1) {
               this.field_146476_w = 1;
            }
         }
      }

      if (this.field_146483_y == null && p_i1080_3_) {
         this.field_146483_y = new NBTTagList();
         this.field_146483_y.func_74742_a(new NBTTagString(""));
         this.field_146476_w = 1;
      }

   }

   public void func_73876_c() {
      super.func_73876_c();
      ++this.field_146479_t;
   }

   public void func_73866_w_() {
      this.field_146292_n.clear();
      Keyboard.enableRepeatEvents(true);
      if (this.field_146475_i) {
         this.field_146292_n.add(this.field_146465_D = new GuiButton(3, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("book.signButton")));
         this.field_146292_n.add(this.field_146472_C = new GuiButton(0, this.field_146294_l / 2 + 2, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("gui.done")));
         this.field_146292_n.add(this.field_146467_E = new GuiButton(5, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("book.finalizeButton")));
         this.field_146292_n.add(this.field_146469_F = new GuiButton(4, this.field_146294_l / 2 + 2, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("gui.cancel")));
      } else {
         this.field_146292_n.add(this.field_146472_C = new GuiButton(0, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 200, 20, I18n.func_135052_a("gui.done")));
      }

      int lvt_1_1_ = (this.field_146294_l - this.field_146478_u) / 2;
      int lvt_2_1_ = 2;
      this.field_146292_n.add(this.field_146470_A = new GuiScreenBook.NextPageButton(1, lvt_1_1_ + 120, lvt_2_1_ + 154, true));
      this.field_146292_n.add(this.field_146471_B = new GuiScreenBook.NextPageButton(2, lvt_1_1_ + 38, lvt_2_1_ + 154, false));
      this.func_146464_h();
   }

   public void func_146281_b() {
      Keyboard.enableRepeatEvents(false);
   }

   private void func_146464_h() {
      this.field_146470_A.field_146125_m = !this.field_146480_s && (this.field_146484_x < this.field_146476_w - 1 || this.field_146475_i);
      this.field_146471_B.field_146125_m = !this.field_146480_s && this.field_146484_x > 0;
      this.field_146472_C.field_146125_m = !this.field_146475_i || !this.field_146480_s;
      if (this.field_146475_i) {
         this.field_146465_D.field_146125_m = !this.field_146480_s;
         this.field_146469_F.field_146125_m = this.field_146480_s;
         this.field_146467_E.field_146125_m = this.field_146480_s;
         this.field_146467_E.field_146124_l = this.field_146482_z.trim().length() > 0;
      }

   }

   private void func_146462_a(boolean p_146462_1_) throws IOException {
      if (this.field_146475_i && this.field_146481_r) {
         if (this.field_146483_y != null) {
            String lvt_2_3_;
            while(this.field_146483_y.func_74745_c() > 1) {
               lvt_2_3_ = this.field_146483_y.func_150307_f(this.field_146483_y.func_74745_c() - 1);
               if (lvt_2_3_.length() != 0) {
                  break;
               }

               this.field_146483_y.func_74744_a(this.field_146483_y.func_74745_c() - 1);
            }

            if (this.field_146474_h.func_77942_o()) {
               NBTTagCompound lvt_2_2_ = this.field_146474_h.func_77978_p();
               lvt_2_2_.func_74782_a("pages", this.field_146483_y);
            } else {
               this.field_146474_h.func_77983_a("pages", this.field_146483_y);
            }

            lvt_2_3_ = "MC|BEdit";
            if (p_146462_1_) {
               lvt_2_3_ = "MC|BSign";
               this.field_146474_h.func_77983_a("author", new NBTTagString(this.field_146468_g.func_70005_c_()));
               this.field_146474_h.func_77983_a("title", new NBTTagString(this.field_146482_z.trim()));

               for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_146483_y.func_74745_c(); ++lvt_3_1_) {
                  String lvt_4_1_ = this.field_146483_y.func_150307_f(lvt_3_1_);
                  IChatComponent lvt_5_1_ = new ChatComponentText(lvt_4_1_);
                  lvt_4_1_ = IChatComponent.Serializer.func_150696_a(lvt_5_1_);
                  this.field_146483_y.func_150304_a(lvt_3_1_, new NBTTagString(lvt_4_1_));
               }

               this.field_146474_h.func_150996_a(Items.field_151164_bB);
            }

            PacketBuffer lvt_3_2_ = new PacketBuffer(Unpooled.buffer());
            lvt_3_2_.func_150788_a(this.field_146474_h);
            this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload(lvt_2_3_, lvt_3_2_));
         }

      }
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146124_l) {
         if (p_146284_1_.field_146127_k == 0) {
            this.field_146297_k.func_147108_a((GuiScreen)null);
            this.func_146462_a(false);
         } else if (p_146284_1_.field_146127_k == 3 && this.field_146475_i) {
            this.field_146480_s = true;
         } else if (p_146284_1_.field_146127_k == 1) {
            if (this.field_146484_x < this.field_146476_w - 1) {
               ++this.field_146484_x;
            } else if (this.field_146475_i) {
               this.func_146461_i();
               if (this.field_146484_x < this.field_146476_w - 1) {
                  ++this.field_146484_x;
               }
            }
         } else if (p_146284_1_.field_146127_k == 2) {
            if (this.field_146484_x > 0) {
               --this.field_146484_x;
            }
         } else if (p_146284_1_.field_146127_k == 5 && this.field_146480_s) {
            this.func_146462_a(true);
            this.field_146297_k.func_147108_a((GuiScreen)null);
         } else if (p_146284_1_.field_146127_k == 4 && this.field_146480_s) {
            this.field_146480_s = false;
         }

         this.func_146464_h();
      }
   }

   private void func_146461_i() {
      if (this.field_146483_y != null && this.field_146483_y.func_74745_c() < 50) {
         this.field_146483_y.func_74742_a(new NBTTagString(""));
         ++this.field_146476_w;
         this.field_146481_r = true;
      }
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      super.func_73869_a(p_73869_1_, p_73869_2_);
      if (this.field_146475_i) {
         if (this.field_146480_s) {
            this.func_146460_c(p_73869_1_, p_73869_2_);
         } else {
            this.func_146463_b(p_73869_1_, p_73869_2_);
         }

      }
   }

   private void func_146463_b(char p_146463_1_, int p_146463_2_) {
      if (GuiScreen.func_175279_e(p_146463_2_)) {
         this.func_146459_b(GuiScreen.func_146277_j());
      } else {
         switch(p_146463_2_) {
         case 14:
            String lvt_3_1_ = this.func_146456_p();
            if (lvt_3_1_.length() > 0) {
               this.func_146457_a(lvt_3_1_.substring(0, lvt_3_1_.length() - 1));
            }

            return;
         case 28:
         case 156:
            this.func_146459_b("\n");
            return;
         default:
            if (ChatAllowedCharacters.func_71566_a(p_146463_1_)) {
               this.func_146459_b(Character.toString(p_146463_1_));
            }
         }
      }
   }

   private void func_146460_c(char p_146460_1_, int p_146460_2_) throws IOException {
      switch(p_146460_2_) {
      case 14:
         if (!this.field_146482_z.isEmpty()) {
            this.field_146482_z = this.field_146482_z.substring(0, this.field_146482_z.length() - 1);
            this.func_146464_h();
         }

         return;
      case 28:
      case 156:
         if (!this.field_146482_z.isEmpty()) {
            this.func_146462_a(true);
            this.field_146297_k.func_147108_a((GuiScreen)null);
         }

         return;
      default:
         if (this.field_146482_z.length() < 16 && ChatAllowedCharacters.func_71566_a(p_146460_1_)) {
            this.field_146482_z = this.field_146482_z + Character.toString(p_146460_1_);
            this.func_146464_h();
            this.field_146481_r = true;
         }

      }
   }

   private String func_146456_p() {
      return this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c() ? this.field_146483_y.func_150307_f(this.field_146484_x) : "";
   }

   private void func_146457_a(String p_146457_1_) {
      if (this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c()) {
         this.field_146483_y.func_150304_a(this.field_146484_x, new NBTTagString(p_146457_1_));
         this.field_146481_r = true;
      }

   }

   private void func_146459_b(String p_146459_1_) {
      String lvt_2_1_ = this.func_146456_p();
      String lvt_3_1_ = lvt_2_1_ + p_146459_1_;
      int lvt_4_1_ = this.field_146289_q.func_78267_b(lvt_3_1_ + "" + EnumChatFormatting.BLACK + "_", 118);
      if (lvt_4_1_ <= 128 && lvt_3_1_.length() < 256) {
         this.func_146457_a(lvt_3_1_);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146466_f);
      int lvt_4_1_ = (this.field_146294_l - this.field_146478_u) / 2;
      int lvt_5_1_ = 2;
      this.func_73729_b(lvt_4_1_, lvt_5_1_, 0, 0, this.field_146478_u, this.field_146477_v);
      String lvt_6_2_;
      String lvt_7_2_;
      int lvt_8_5_;
      int lvt_9_2_;
      if (this.field_146480_s) {
         lvt_6_2_ = this.field_146482_z;
         if (this.field_146475_i) {
            if (this.field_146479_t / 6 % 2 == 0) {
               lvt_6_2_ = lvt_6_2_ + "" + EnumChatFormatting.BLACK + "_";
            } else {
               lvt_6_2_ = lvt_6_2_ + "" + EnumChatFormatting.GRAY + "_";
            }
         }

         lvt_7_2_ = I18n.func_135052_a("book.editTitle");
         lvt_8_5_ = this.field_146289_q.func_78256_a(lvt_7_2_);
         this.field_146289_q.func_78276_b(lvt_7_2_, lvt_4_1_ + 36 + (116 - lvt_8_5_) / 2, lvt_5_1_ + 16 + 16, 0);
         lvt_9_2_ = this.field_146289_q.func_78256_a(lvt_6_2_);
         this.field_146289_q.func_78276_b(lvt_6_2_, lvt_4_1_ + 36 + (116 - lvt_9_2_) / 2, lvt_5_1_ + 48, 0);
         String lvt_10_1_ = I18n.func_135052_a("book.byAuthor", this.field_146468_g.func_70005_c_());
         int lvt_11_1_ = this.field_146289_q.func_78256_a(lvt_10_1_);
         this.field_146289_q.func_78276_b(EnumChatFormatting.DARK_GRAY + lvt_10_1_, lvt_4_1_ + 36 + (116 - lvt_11_1_) / 2, lvt_5_1_ + 48 + 10, 0);
         String lvt_12_1_ = I18n.func_135052_a("book.finalizeWarning");
         this.field_146289_q.func_78279_b(lvt_12_1_, lvt_4_1_ + 36, lvt_5_1_ + 80, 116, 0);
      } else {
         lvt_6_2_ = I18n.func_135052_a("book.pageIndicator", this.field_146484_x + 1, this.field_146476_w);
         lvt_7_2_ = "";
         if (this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c()) {
            lvt_7_2_ = this.field_146483_y.func_150307_f(this.field_146484_x);
         }

         if (this.field_146475_i) {
            if (this.field_146289_q.func_78260_a()) {
               lvt_7_2_ = lvt_7_2_ + "_";
            } else if (this.field_146479_t / 6 % 2 == 0) {
               lvt_7_2_ = lvt_7_2_ + "" + EnumChatFormatting.BLACK + "_";
            } else {
               lvt_7_2_ = lvt_7_2_ + "" + EnumChatFormatting.GRAY + "_";
            }
         } else if (this.field_175387_B != this.field_146484_x) {
            if (ItemEditableBook.func_77828_a(this.field_146474_h.func_77978_p())) {
               try {
                  IChatComponent lvt_8_2_ = IChatComponent.Serializer.func_150699_a(lvt_7_2_);
                  this.field_175386_A = lvt_8_2_ != null ? GuiUtilRenderComponents.func_178908_a(lvt_8_2_, 116, this.field_146289_q, true, true) : null;
               } catch (JsonParseException var13) {
                  this.field_175386_A = null;
               }
            } else {
               ChatComponentText lvt_8_4_ = new ChatComponentText(EnumChatFormatting.DARK_RED.toString() + "* Invalid book tag *");
               this.field_175386_A = Lists.newArrayList((Iterable)lvt_8_4_);
            }

            this.field_175387_B = this.field_146484_x;
         }

         lvt_8_5_ = this.field_146289_q.func_78256_a(lvt_6_2_);
         this.field_146289_q.func_78276_b(lvt_6_2_, lvt_4_1_ - lvt_8_5_ + this.field_146478_u - 44, lvt_5_1_ + 16, 0);
         if (this.field_175386_A == null) {
            this.field_146289_q.func_78279_b(lvt_7_2_, lvt_4_1_ + 36, lvt_5_1_ + 16 + 16, 116, 0);
         } else {
            lvt_9_2_ = Math.min(128 / this.field_146289_q.field_78288_b, this.field_175386_A.size());

            for(int lvt_10_2_ = 0; lvt_10_2_ < lvt_9_2_; ++lvt_10_2_) {
               IChatComponent lvt_11_2_ = (IChatComponent)this.field_175386_A.get(lvt_10_2_);
               this.field_146289_q.func_78276_b(lvt_11_2_.func_150260_c(), lvt_4_1_ + 36, lvt_5_1_ + 16 + 16 + lvt_10_2_ * this.field_146289_q.field_78288_b, 0);
            }

            IChatComponent lvt_10_3_ = this.func_175385_b(p_73863_1_, p_73863_2_);
            if (lvt_10_3_ != null) {
               this.func_175272_a(lvt_10_3_, p_73863_1_, p_73863_2_);
            }
         }
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      if (p_73864_3_ == 0) {
         IChatComponent lvt_4_1_ = this.func_175385_b(p_73864_1_, p_73864_2_);
         if (this.func_175276_a(lvt_4_1_)) {
            return;
         }
      }

      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   protected boolean func_175276_a(IChatComponent p_175276_1_) {
      ClickEvent lvt_2_1_ = p_175276_1_ == null ? null : p_175276_1_.func_150256_b().func_150235_h();
      if (lvt_2_1_ == null) {
         return false;
      } else if (lvt_2_1_.func_150669_a() == ClickEvent.Action.CHANGE_PAGE) {
         String lvt_3_1_ = lvt_2_1_.func_150668_b();

         try {
            int lvt_4_1_ = Integer.parseInt(lvt_3_1_) - 1;
            if (lvt_4_1_ >= 0 && lvt_4_1_ < this.field_146476_w && lvt_4_1_ != this.field_146484_x) {
               this.field_146484_x = lvt_4_1_;
               this.func_146464_h();
               return true;
            }
         } catch (Throwable var5) {
         }

         return false;
      } else {
         boolean lvt_3_2_ = super.func_175276_a(p_175276_1_);
         if (lvt_3_2_ && lvt_2_1_.func_150669_a() == ClickEvent.Action.RUN_COMMAND) {
            this.field_146297_k.func_147108_a((GuiScreen)null);
         }

         return lvt_3_2_;
      }
   }

   public IChatComponent func_175385_b(int p_175385_1_, int p_175385_2_) {
      if (this.field_175386_A == null) {
         return null;
      } else {
         int lvt_3_1_ = p_175385_1_ - (this.field_146294_l - this.field_146478_u) / 2 - 36;
         int lvt_4_1_ = p_175385_2_ - 2 - 16 - 16;
         if (lvt_3_1_ >= 0 && lvt_4_1_ >= 0) {
            int lvt_5_1_ = Math.min(128 / this.field_146289_q.field_78288_b, this.field_175386_A.size());
            if (lvt_3_1_ <= 116 && lvt_4_1_ < this.field_146297_k.field_71466_p.field_78288_b * lvt_5_1_ + lvt_5_1_) {
               int lvt_6_1_ = lvt_4_1_ / this.field_146297_k.field_71466_p.field_78288_b;
               if (lvt_6_1_ >= 0 && lvt_6_1_ < this.field_175386_A.size()) {
                  IChatComponent lvt_7_1_ = (IChatComponent)this.field_175386_A.get(lvt_6_1_);
                  int lvt_8_1_ = 0;
                  Iterator lvt_9_1_ = lvt_7_1_.iterator();

                  while(lvt_9_1_.hasNext()) {
                     IChatComponent lvt_10_1_ = (IChatComponent)lvt_9_1_.next();
                     if (lvt_10_1_ instanceof ChatComponentText) {
                        lvt_8_1_ += this.field_146297_k.field_71466_p.func_78256_a(((ChatComponentText)lvt_10_1_).func_150265_g());
                        if (lvt_8_1_ > lvt_3_1_) {
                           return lvt_10_1_;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   static class NextPageButton extends GuiButton {
      private final boolean field_146151_o;

      public NextPageButton(int p_i46316_1_, int p_i46316_2_, int p_i46316_3_, boolean p_i46316_4_) {
         super(p_i46316_1_, p_i46316_2_, p_i46316_3_, 23, 13, "");
         this.field_146151_o = p_i46316_4_;
      }

      public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
         if (this.field_146125_m) {
            boolean lvt_4_1_ = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            p_146112_1_.func_110434_K().func_110577_a(GuiScreenBook.field_146466_f);
            int lvt_5_1_ = 0;
            int lvt_6_1_ = 192;
            if (lvt_4_1_) {
               lvt_5_1_ += 23;
            }

            if (!this.field_146151_o) {
               lvt_6_1_ += 13;
            }

            this.func_73729_b(this.field_146128_h, this.field_146129_i, lvt_5_1_, lvt_6_1_, 23, 13);
         }
      }
   }
}
