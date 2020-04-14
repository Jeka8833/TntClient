package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.IChatComponent;

public class HoverEvent {
   private final HoverEvent.Action field_150704_a;
   private final IChatComponent field_150703_b;

   public HoverEvent(HoverEvent.Action p_i45158_1_, IChatComponent p_i45158_2_) {
      this.field_150704_a = p_i45158_1_;
      this.field_150703_b = p_i45158_2_;
   }

   public HoverEvent.Action func_150701_a() {
      return this.field_150704_a;
   }

   public IChatComponent func_150702_b() {
      return this.field_150703_b;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         HoverEvent lvt_2_1_ = (HoverEvent)p_equals_1_;
         if (this.field_150704_a != lvt_2_1_.field_150704_a) {
            return false;
         } else {
            if (this.field_150703_b != null) {
               if (!this.field_150703_b.equals(lvt_2_1_.field_150703_b)) {
                  return false;
               }
            } else if (lvt_2_1_.field_150703_b != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public String toString() {
      return "HoverEvent{action=" + this.field_150704_a + ", value='" + this.field_150703_b + '\'' + '}';
   }

   public int hashCode() {
      int lvt_1_1_ = this.field_150704_a.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + (this.field_150703_b != null ? this.field_150703_b.hashCode() : 0);
      return lvt_1_1_;
   }

   public static enum Action {
      SHOW_TEXT("show_text", true),
      SHOW_ACHIEVEMENT("show_achievement", true),
      SHOW_ITEM("show_item", true),
      SHOW_ENTITY("show_entity", true);

      private static final Map<String, HoverEvent.Action> field_150690_d = Maps.newHashMap();
      private final boolean field_150691_e;
      private final String field_150688_f;

      private Action(String p_i45157_3_, boolean p_i45157_4_) {
         this.field_150688_f = p_i45157_3_;
         this.field_150691_e = p_i45157_4_;
      }

      public boolean func_150686_a() {
         return this.field_150691_e;
      }

      public String func_150685_b() {
         return this.field_150688_f;
      }

      public static HoverEvent.Action func_150684_a(String p_150684_0_) {
         return (HoverEvent.Action)field_150690_d.get(p_150684_0_);
      }

      static {
         HoverEvent.Action[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            HoverEvent.Action lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_150690_d.put(lvt_3_1_.func_150685_b(), lvt_3_1_);
         }

      }
   }
}
