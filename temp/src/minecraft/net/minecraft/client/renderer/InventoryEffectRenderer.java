package net.minecraft.client.renderer;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public abstract class InventoryEffectRenderer extends GuiContainer {
   private boolean field_147045_u;

   public InventoryEffectRenderer(Container p_i1089_1_) {
      super(p_i1089_1_);
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.func_175378_g();
   }

   protected void func_175378_g() {
      if (!this.field_146297_k.field_71439_g.func_70651_bq().isEmpty()) {
         this.field_147003_i = 160 + (this.field_146294_l - this.field_146999_f - 200) / 2;
         this.field_147045_u = true;
      } else {
         this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
         this.field_147045_u = false;
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      if (this.field_147045_u) {
         this.func_147044_g();
      }

   }

   private void func_147044_g() {
      int lvt_1_1_ = this.field_147003_i - 124;
      int lvt_2_1_ = this.field_147009_r;
      int lvt_3_1_ = true;
      Collection<PotionEffect> lvt_4_1_ = this.field_146297_k.field_71439_g.func_70651_bq();
      if (!lvt_4_1_.isEmpty()) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179140_f();
         int lvt_5_1_ = 33;
         if (lvt_4_1_.size() > 5) {
            lvt_5_1_ = 132 / (lvt_4_1_.size() - 1);
         }

         for(Iterator lvt_6_1_ = this.field_146297_k.field_71439_g.func_70651_bq().iterator(); lvt_6_1_.hasNext(); lvt_2_1_ += lvt_5_1_) {
            PotionEffect lvt_7_1_ = (PotionEffect)lvt_6_1_.next();
            Potion lvt_8_1_ = Potion.field_76425_a[lvt_7_1_.func_76456_a()];
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146297_k.func_110434_K().func_110577_a(field_147001_a);
            this.func_73729_b(lvt_1_1_, lvt_2_1_, 0, 166, 140, 32);
            if (lvt_8_1_.func_76400_d()) {
               int lvt_9_1_ = lvt_8_1_.func_76392_e();
               this.func_73729_b(lvt_1_1_ + 6, lvt_2_1_ + 7, 0 + lvt_9_1_ % 8 * 18, 198 + lvt_9_1_ / 8 * 18, 18, 18);
            }

            String lvt_9_2_ = I18n.func_135052_a(lvt_8_1_.func_76393_a());
            if (lvt_7_1_.func_76458_c() == 1) {
               lvt_9_2_ = lvt_9_2_ + " " + I18n.func_135052_a("enchantment.level.2");
            } else if (lvt_7_1_.func_76458_c() == 2) {
               lvt_9_2_ = lvt_9_2_ + " " + I18n.func_135052_a("enchantment.level.3");
            } else if (lvt_7_1_.func_76458_c() == 3) {
               lvt_9_2_ = lvt_9_2_ + " " + I18n.func_135052_a("enchantment.level.4");
            }

            this.field_146289_q.func_175063_a(lvt_9_2_, (float)(lvt_1_1_ + 10 + 18), (float)(lvt_2_1_ + 6), 16777215);
            String lvt_10_1_ = Potion.func_76389_a(lvt_7_1_);
            this.field_146289_q.func_175063_a(lvt_10_1_, (float)(lvt_1_1_ + 10 + 18), (float)(lvt_2_1_ + 6 + 10), 8355711);
         }

      }
   }
}
