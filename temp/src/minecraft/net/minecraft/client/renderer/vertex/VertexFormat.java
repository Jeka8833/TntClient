package net.minecraft.client.renderer.vertex;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormat {
   private static final Logger field_177357_a = LogManager.getLogger();
   private final List<VertexFormatElement> field_177355_b;
   private final List<Integer> field_177356_c;
   private int field_177353_d;
   private int field_177354_e;
   private List<Integer> field_177351_f;
   private int field_177352_g;

   public VertexFormat(VertexFormat p_i46097_1_) {
      this();

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_i46097_1_.func_177345_h(); ++lvt_2_1_) {
         this.func_181721_a(p_i46097_1_.func_177348_c(lvt_2_1_));
      }

      this.field_177353_d = p_i46097_1_.func_177338_f();
   }

   public VertexFormat() {
      this.field_177355_b = Lists.newArrayList();
      this.field_177356_c = Lists.newArrayList();
      this.field_177353_d = 0;
      this.field_177354_e = -1;
      this.field_177351_f = Lists.newArrayList();
      this.field_177352_g = -1;
   }

   public void func_177339_a() {
      this.field_177355_b.clear();
      this.field_177356_c.clear();
      this.field_177354_e = -1;
      this.field_177351_f.clear();
      this.field_177352_g = -1;
      this.field_177353_d = 0;
   }

   public VertexFormat func_181721_a(VertexFormatElement p_181721_1_) {
      if (p_181721_1_.func_177374_g() && this.func_177341_i()) {
         field_177357_a.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
         return this;
      } else {
         this.field_177355_b.add(p_181721_1_);
         this.field_177356_c.add(this.field_177353_d);
         switch(p_181721_1_.func_177375_c()) {
         case NORMAL:
            this.field_177352_g = this.field_177353_d;
            break;
         case COLOR:
            this.field_177354_e = this.field_177353_d;
            break;
         case UV:
            this.field_177351_f.add(p_181721_1_.func_177369_e(), this.field_177353_d);
         }

         this.field_177353_d += p_181721_1_.func_177368_f();
         return this;
      }
   }

   public boolean func_177350_b() {
      return this.field_177352_g >= 0;
   }

   public int func_177342_c() {
      return this.field_177352_g;
   }

   public boolean func_177346_d() {
      return this.field_177354_e >= 0;
   }

   public int func_177340_e() {
      return this.field_177354_e;
   }

   public boolean func_177347_a(int p_177347_1_) {
      return this.field_177351_f.size() - 1 >= p_177347_1_;
   }

   public int func_177344_b(int p_177344_1_) {
      return (Integer)this.field_177351_f.get(p_177344_1_);
   }

   public String toString() {
      String lvt_1_1_ = "format: " + this.field_177355_b.size() + " elements: ";

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_177355_b.size(); ++lvt_2_1_) {
         lvt_1_1_ = lvt_1_1_ + ((VertexFormatElement)this.field_177355_b.get(lvt_2_1_)).toString();
         if (lvt_2_1_ != this.field_177355_b.size() - 1) {
            lvt_1_1_ = lvt_1_1_ + " ";
         }
      }

      return lvt_1_1_;
   }

   private boolean func_177341_i() {
      int lvt_1_1_ = 0;

      for(int lvt_2_1_ = this.field_177355_b.size(); lvt_1_1_ < lvt_2_1_; ++lvt_1_1_) {
         VertexFormatElement lvt_3_1_ = (VertexFormatElement)this.field_177355_b.get(lvt_1_1_);
         if (lvt_3_1_.func_177374_g()) {
            return true;
         }
      }

      return false;
   }

   public int func_181719_f() {
      return this.func_177338_f() / 4;
   }

   public int func_177338_f() {
      return this.field_177353_d;
   }

   public List<VertexFormatElement> func_177343_g() {
      return this.field_177355_b;
   }

   public int func_177345_h() {
      return this.field_177355_b.size();
   }

   public VertexFormatElement func_177348_c(int p_177348_1_) {
      return (VertexFormatElement)this.field_177355_b.get(p_177348_1_);
   }

   public int func_181720_d(int p_181720_1_) {
      return (Integer)this.field_177356_c.get(p_181720_1_);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         VertexFormat lvt_2_1_ = (VertexFormat)p_equals_1_;
         if (this.field_177353_d != lvt_2_1_.field_177353_d) {
            return false;
         } else if (!this.field_177355_b.equals(lvt_2_1_.field_177355_b)) {
            return false;
         } else {
            return this.field_177356_c.equals(lvt_2_1_.field_177356_c);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int lvt_1_1_ = this.field_177355_b.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_177356_c.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_177353_d;
      return lvt_1_1_;
   }
}
