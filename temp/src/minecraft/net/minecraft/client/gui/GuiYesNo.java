package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.resources.I18n;

public class GuiYesNo extends GuiScreen {
   protected GuiYesNoCallback field_146355_a;
   protected String field_146351_f;
   private String field_146354_r;
   private final List<String> field_175298_s = Lists.newArrayList();
   protected String field_146352_g;
   protected String field_146356_h;
   protected int field_146357_i;
   private int field_146353_s;

   public GuiYesNo(GuiYesNoCallback p_i1082_1_, String p_i1082_2_, String p_i1082_3_, int p_i1082_4_) {
      this.field_146355_a = p_i1082_1_;
      this.field_146351_f = p_i1082_2_;
      this.field_146354_r = p_i1082_3_;
      this.field_146357_i = p_i1082_4_;
      this.field_146352_g = I18n.func_135052_a("gui.yes");
      this.field_146356_h = I18n.func_135052_a("gui.no");
   }

   public GuiYesNo(GuiYesNoCallback p_i1083_1_, String p_i1083_2_, String p_i1083_3_, String p_i1083_4_, String p_i1083_5_, int p_i1083_6_) {
      this.field_146355_a = p_i1083_1_;
      this.field_146351_f = p_i1083_2_;
      this.field_146354_r = p_i1083_3_;
      this.field_146352_g = p_i1083_4_;
      this.field_146356_h = p_i1083_5_;
      this.field_146357_i = p_i1083_6_;
   }

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiOptionButton(0, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96, this.field_146352_g));
      this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 - 155 + 160, this.field_146295_m / 6 + 96, this.field_146356_h));
      this.field_175298_s.clear();
      this.field_175298_s.addAll(this.field_146289_q.func_78271_c(this.field_146354_r, this.field_146294_l - 50));
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      this.field_146355_a.func_73878_a(p_146284_1_.field_146127_k == 0, this.field_146357_i);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_146351_f, this.field_146294_l / 2, 70, 16777215);
      int lvt_4_1_ = 90;

      for(Iterator lvt_5_1_ = this.field_175298_s.iterator(); lvt_5_1_.hasNext(); lvt_4_1_ += this.field_146289_q.field_78288_b) {
         String lvt_6_1_ = (String)lvt_5_1_.next();
         this.func_73732_a(this.field_146289_q, lvt_6_1_, this.field_146294_l / 2, lvt_4_1_, 16777215);
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public void func_146350_a(int p_146350_1_) {
      this.field_146353_s = p_146350_1_;

      GuiButton lvt_3_1_;
      for(Iterator lvt_2_1_ = this.field_146292_n.iterator(); lvt_2_1_.hasNext(); lvt_3_1_.field_146124_l = false) {
         lvt_3_1_ = (GuiButton)lvt_2_1_.next();
      }

   }

   public void func_73876_c() {
      super.func_73876_c();
      GuiButton lvt_2_1_;
      if (--this.field_146353_s == 0) {
         for(Iterator lvt_1_1_ = this.field_146292_n.iterator(); lvt_1_1_.hasNext(); lvt_2_1_.field_146124_l = true) {
            lvt_2_1_ = (GuiButton)lvt_1_1_.next();
         }
      }

   }
}
