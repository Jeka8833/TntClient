package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityDamageSourceIndirect extends EntityDamageSource {
   private Entity field_76387_p;

   public EntityDamageSourceIndirect(String p_i1568_1_, Entity p_i1568_2_, Entity p_i1568_3_) {
      super(p_i1568_1_, p_i1568_2_);
      this.field_76387_p = p_i1568_3_;
   }

   public Entity func_76364_f() {
      return this.field_76386_o;
   }

   public Entity func_76346_g() {
      return this.field_76387_p;
   }

   public IChatComponent func_151519_b(EntityLivingBase p_151519_1_) {
      IChatComponent lvt_2_1_ = this.field_76387_p == null ? this.field_76386_o.func_145748_c_() : this.field_76387_p.func_145748_c_();
      ItemStack lvt_3_1_ = this.field_76387_p instanceof EntityLivingBase ? ((EntityLivingBase)this.field_76387_p).func_70694_bm() : null;
      String lvt_4_1_ = "death.attack." + this.field_76373_n;
      String lvt_5_1_ = lvt_4_1_ + ".item";
      return lvt_3_1_ != null && lvt_3_1_.func_82837_s() && StatCollector.func_94522_b(lvt_5_1_) ? new ChatComponentTranslation(lvt_5_1_, new Object[]{p_151519_1_.func_145748_c_(), lvt_2_1_, lvt_3_1_.func_151000_E()}) : new ChatComponentTranslation(lvt_4_1_, new Object[]{p_151519_1_.func_145748_c_(), lvt_2_1_});
   }
}
