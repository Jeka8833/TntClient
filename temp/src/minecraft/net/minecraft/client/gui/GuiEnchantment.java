package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import org.lwjgl.util.glu.Project;

public class GuiEnchantment extends GuiContainer {
   private static final ResourceLocation field_147078_C = new ResourceLocation("textures/gui/container/enchanting_table.png");
   private static final ResourceLocation field_147070_D = new ResourceLocation("textures/entity/enchanting_table_book.png");
   private static final ModelBook field_147072_E = new ModelBook();
   private final InventoryPlayer field_175379_F;
   private Random field_147074_F = new Random();
   private ContainerEnchantment field_147075_G;
   public int field_147073_u;
   public float field_147071_v;
   public float field_147069_w;
   public float field_147082_x;
   public float field_147081_y;
   public float field_147080_z;
   public float field_147076_A;
   ItemStack field_147077_B;
   private final IWorldNameable field_175380_I;

   public GuiEnchantment(InventoryPlayer p_i45502_1_, World p_i45502_2_, IWorldNameable p_i45502_3_) {
      super(new ContainerEnchantment(p_i45502_1_, p_i45502_2_));
      this.field_175379_F = p_i45502_1_;
      this.field_147075_G = (ContainerEnchantment)this.field_147002_h;
      this.field_175380_I = p_i45502_3_;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      this.field_146289_q.func_78276_b(this.field_175380_I.func_145748_c_().func_150260_c(), 12, 5, 4210752);
      this.field_146289_q.func_78276_b(this.field_175379_F.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   public void func_73876_c() {
      super.func_73876_c();
      this.func_147068_g();
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      int lvt_4_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_5_1_ = (this.field_146295_m - this.field_147000_g) / 2;

      for(int lvt_6_1_ = 0; lvt_6_1_ < 3; ++lvt_6_1_) {
         int lvt_7_1_ = p_73864_1_ - (lvt_4_1_ + 60);
         int lvt_8_1_ = p_73864_2_ - (lvt_5_1_ + 14 + 19 * lvt_6_1_);
         if (lvt_7_1_ >= 0 && lvt_8_1_ >= 0 && lvt_7_1_ < 108 && lvt_8_1_ < 19 && this.field_147075_G.func_75140_a(this.field_146297_k.field_71439_g, lvt_6_1_)) {
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147075_G.field_75152_c, lvt_6_1_);
         }
      }

   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
      int lvt_4_1_ = (this.field_146294_l - this.field_146999_f) / 2;
      int lvt_5_1_ = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(lvt_4_1_, lvt_5_1_, 0, 0, this.field_146999_f, this.field_147000_g);
      GlStateManager.func_179094_E();
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179094_E();
      GlStateManager.func_179096_D();
      ScaledResolution lvt_6_1_ = new ScaledResolution(this.field_146297_k);
      GlStateManager.func_179083_b((lvt_6_1_.func_78326_a() - 320) / 2 * lvt_6_1_.func_78325_e(), (lvt_6_1_.func_78328_b() - 240) / 2 * lvt_6_1_.func_78325_e(), 320 * lvt_6_1_.func_78325_e(), 240 * lvt_6_1_.func_78325_e());
      GlStateManager.func_179109_b(-0.34F, 0.23F, 0.0F);
      Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
      float lvt_7_1_ = 1.0F;
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179096_D();
      RenderHelper.func_74519_b();
      GlStateManager.func_179109_b(0.0F, 3.3F, -16.0F);
      GlStateManager.func_179152_a(lvt_7_1_, lvt_7_1_, lvt_7_1_);
      float lvt_8_1_ = 5.0F;
      GlStateManager.func_179152_a(lvt_8_1_, lvt_8_1_, lvt_8_1_);
      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147070_D);
      GlStateManager.func_179114_b(20.0F, 1.0F, 0.0F, 0.0F);
      float lvt_9_1_ = this.field_147076_A + (this.field_147080_z - this.field_147076_A) * p_146976_1_;
      GlStateManager.func_179109_b((1.0F - lvt_9_1_) * 0.2F, (1.0F - lvt_9_1_) * 0.1F, (1.0F - lvt_9_1_) * 0.25F);
      GlStateManager.func_179114_b(-(1.0F - lvt_9_1_) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(180.0F, 1.0F, 0.0F, 0.0F);
      float lvt_10_1_ = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * p_146976_1_ + 0.25F;
      float lvt_11_1_ = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * p_146976_1_ + 0.75F;
      lvt_10_1_ = (lvt_10_1_ - (float)MathHelper.func_76140_b((double)lvt_10_1_)) * 1.6F - 0.3F;
      lvt_11_1_ = (lvt_11_1_ - (float)MathHelper.func_76140_b((double)lvt_11_1_)) * 1.6F - 0.3F;
      if (lvt_10_1_ < 0.0F) {
         lvt_10_1_ = 0.0F;
      }

      if (lvt_11_1_ < 0.0F) {
         lvt_11_1_ = 0.0F;
      }

      if (lvt_10_1_ > 1.0F) {
         lvt_10_1_ = 1.0F;
      }

      if (lvt_11_1_ > 1.0F) {
         lvt_11_1_ = 1.0F;
      }

      GlStateManager.func_179091_B();
      field_147072_E.func_78088_a((Entity)null, 0.0F, lvt_10_1_, lvt_11_1_, lvt_9_1_, 0.0F, 0.0625F);
      GlStateManager.func_179101_C();
      RenderHelper.func_74518_a();
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179083_b(0, 0, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
      GlStateManager.func_179121_F();
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179121_F();
      RenderHelper.func_74518_a();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      EnchantmentNameParts.func_178176_a().func_148335_a((long)this.field_147075_G.field_178149_f);
      int lvt_12_1_ = this.field_147075_G.func_178147_e();

      for(int lvt_13_1_ = 0; lvt_13_1_ < 3; ++lvt_13_1_) {
         int lvt_14_1_ = lvt_4_1_ + 60;
         int lvt_15_1_ = lvt_14_1_ + 20;
         int lvt_16_1_ = 86;
         String lvt_17_1_ = EnchantmentNameParts.func_178176_a().func_148334_a();
         this.field_73735_i = 0.0F;
         this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
         int lvt_18_1_ = this.field_147075_G.field_75167_g[lvt_13_1_];
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         if (lvt_18_1_ == 0) {
            this.func_73729_b(lvt_14_1_, lvt_5_1_ + 14 + 19 * lvt_13_1_, 0, 185, 108, 19);
         } else {
            String lvt_19_1_ = "" + lvt_18_1_;
            FontRenderer lvt_20_1_ = this.field_146297_k.field_71464_q;
            int lvt_21_1_ = 6839882;
            if ((lvt_12_1_ < lvt_13_1_ + 1 || this.field_146297_k.field_71439_g.field_71068_ca < lvt_18_1_) && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) {
               this.func_73729_b(lvt_14_1_, lvt_5_1_ + 14 + 19 * lvt_13_1_, 0, 185, 108, 19);
               this.func_73729_b(lvt_14_1_ + 1, lvt_5_1_ + 15 + 19 * lvt_13_1_, 16 * lvt_13_1_, 239, 16, 16);
               lvt_20_1_.func_78279_b(lvt_17_1_, lvt_15_1_, lvt_5_1_ + 16 + 19 * lvt_13_1_, lvt_16_1_, (lvt_21_1_ & 16711422) >> 1);
               lvt_21_1_ = 4226832;
            } else {
               int lvt_22_1_ = p_146976_2_ - (lvt_4_1_ + 60);
               int lvt_23_1_ = p_146976_3_ - (lvt_5_1_ + 14 + 19 * lvt_13_1_);
               if (lvt_22_1_ >= 0 && lvt_23_1_ >= 0 && lvt_22_1_ < 108 && lvt_23_1_ < 19) {
                  this.func_73729_b(lvt_14_1_, lvt_5_1_ + 14 + 19 * lvt_13_1_, 0, 204, 108, 19);
                  lvt_21_1_ = 16777088;
               } else {
                  this.func_73729_b(lvt_14_1_, lvt_5_1_ + 14 + 19 * lvt_13_1_, 0, 166, 108, 19);
               }

               this.func_73729_b(lvt_14_1_ + 1, lvt_5_1_ + 15 + 19 * lvt_13_1_, 16 * lvt_13_1_, 223, 16, 16);
               lvt_20_1_.func_78279_b(lvt_17_1_, lvt_15_1_, lvt_5_1_ + 16 + 19 * lvt_13_1_, lvt_16_1_, lvt_21_1_);
               lvt_21_1_ = 8453920;
            }

            lvt_20_1_ = this.field_146297_k.field_71466_p;
            lvt_20_1_.func_175063_a(lvt_19_1_, (float)(lvt_15_1_ + 86 - lvt_20_1_.func_78256_a(lvt_19_1_)), (float)(lvt_5_1_ + 16 + 19 * lvt_13_1_ + 7), lvt_21_1_);
         }
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      boolean lvt_4_1_ = this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d;
      int lvt_5_1_ = this.field_147075_G.func_178147_e();

      for(int lvt_6_1_ = 0; lvt_6_1_ < 3; ++lvt_6_1_) {
         int lvt_7_1_ = this.field_147075_G.field_75167_g[lvt_6_1_];
         int lvt_8_1_ = this.field_147075_G.field_178151_h[lvt_6_1_];
         int lvt_9_1_ = lvt_6_1_ + 1;
         if (this.func_146978_c(60, 14 + 19 * lvt_6_1_, 108, 17, p_73863_1_, p_73863_2_) && lvt_7_1_ > 0 && lvt_8_1_ >= 0) {
            List<String> lvt_10_1_ = Lists.newArrayList();
            String lvt_11_2_;
            if (lvt_8_1_ >= 0 && Enchantment.func_180306_c(lvt_8_1_ & 255) != null) {
               lvt_11_2_ = Enchantment.func_180306_c(lvt_8_1_ & 255).func_77316_c((lvt_8_1_ & '\uff00') >> 8);
               lvt_10_1_.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC.toString() + I18n.func_135052_a("container.enchant.clue", lvt_11_2_));
            }

            if (!lvt_4_1_) {
               if (lvt_8_1_ >= 0) {
                  lvt_10_1_.add("");
               }

               if (this.field_146297_k.field_71439_g.field_71068_ca < lvt_7_1_) {
                  lvt_10_1_.add(EnumChatFormatting.RED.toString() + "Level Requirement: " + this.field_147075_G.field_75167_g[lvt_6_1_]);
               } else {
                  lvt_11_2_ = "";
                  if (lvt_9_1_ == 1) {
                     lvt_11_2_ = I18n.func_135052_a("container.enchant.lapis.one");
                  } else {
                     lvt_11_2_ = I18n.func_135052_a("container.enchant.lapis.many", lvt_9_1_);
                  }

                  if (lvt_5_1_ >= lvt_9_1_) {
                     lvt_10_1_.add(EnumChatFormatting.GRAY.toString() + "" + lvt_11_2_);
                  } else {
                     lvt_10_1_.add(EnumChatFormatting.RED.toString() + "" + lvt_11_2_);
                  }

                  if (lvt_9_1_ == 1) {
                     lvt_11_2_ = I18n.func_135052_a("container.enchant.level.one");
                  } else {
                     lvt_11_2_ = I18n.func_135052_a("container.enchant.level.many", lvt_9_1_);
                  }

                  lvt_10_1_.add(EnumChatFormatting.GRAY.toString() + "" + lvt_11_2_);
               }
            }

            this.func_146283_a(lvt_10_1_, p_73863_1_, p_73863_2_);
            break;
         }
      }

   }

   public void func_147068_g() {
      ItemStack lvt_1_1_ = this.field_147002_h.func_75139_a(0).func_75211_c();
      if (!ItemStack.func_77989_b(lvt_1_1_, this.field_147077_B)) {
         this.field_147077_B = lvt_1_1_;

         do {
            this.field_147082_x += (float)(this.field_147074_F.nextInt(4) - this.field_147074_F.nextInt(4));
         } while(this.field_147071_v <= this.field_147082_x + 1.0F && this.field_147071_v >= this.field_147082_x - 1.0F);
      }

      ++this.field_147073_u;
      this.field_147069_w = this.field_147071_v;
      this.field_147076_A = this.field_147080_z;
      boolean lvt_2_1_ = false;

      for(int lvt_3_1_ = 0; lvt_3_1_ < 3; ++lvt_3_1_) {
         if (this.field_147075_G.field_75167_g[lvt_3_1_] != 0) {
            lvt_2_1_ = true;
         }
      }

      if (lvt_2_1_) {
         this.field_147080_z += 0.2F;
      } else {
         this.field_147080_z -= 0.2F;
      }

      this.field_147080_z = MathHelper.func_76131_a(this.field_147080_z, 0.0F, 1.0F);
      float lvt_3_2_ = (this.field_147082_x - this.field_147071_v) * 0.4F;
      float lvt_4_1_ = 0.2F;
      lvt_3_2_ = MathHelper.func_76131_a(lvt_3_2_, -lvt_4_1_, lvt_4_1_);
      this.field_147081_y += (lvt_3_2_ - this.field_147081_y) * 0.9F;
      this.field_147071_v += this.field_147081_y;
   }
}
