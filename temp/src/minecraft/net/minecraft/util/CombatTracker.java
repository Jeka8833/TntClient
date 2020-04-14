package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CombatTracker {
   private final List<CombatEntry> field_94556_a = Lists.newArrayList();
   private final EntityLivingBase field_94554_b;
   private int field_94555_c;
   private int field_152775_d;
   private int field_152776_e;
   private boolean field_94552_d;
   private boolean field_94553_e;
   private String field_94551_f;

   public CombatTracker(EntityLivingBase p_i1565_1_) {
      this.field_94554_b = p_i1565_1_;
   }

   public void func_94545_a() {
      this.func_94542_g();
      if (this.field_94554_b.func_70617_f_()) {
         Block lvt_1_1_ = this.field_94554_b.field_70170_p.func_180495_p(new BlockPos(this.field_94554_b.field_70165_t, this.field_94554_b.func_174813_aQ().field_72338_b, this.field_94554_b.field_70161_v)).func_177230_c();
         if (lvt_1_1_ == Blocks.field_150468_ap) {
            this.field_94551_f = "ladder";
         } else if (lvt_1_1_ == Blocks.field_150395_bd) {
            this.field_94551_f = "vines";
         }
      } else if (this.field_94554_b.func_70090_H()) {
         this.field_94551_f = "water";
      }

   }

   public void func_94547_a(DamageSource p_94547_1_, float p_94547_2_, float p_94547_3_) {
      this.func_94549_h();
      this.func_94545_a();
      CombatEntry lvt_4_1_ = new CombatEntry(p_94547_1_, this.field_94554_b.field_70173_aa, p_94547_2_, p_94547_3_, this.field_94551_f, this.field_94554_b.field_70143_R);
      this.field_94556_a.add(lvt_4_1_);
      this.field_94555_c = this.field_94554_b.field_70173_aa;
      this.field_94553_e = true;
      if (lvt_4_1_.func_94559_f() && !this.field_94552_d && this.field_94554_b.func_70089_S()) {
         this.field_94552_d = true;
         this.field_152775_d = this.field_94554_b.field_70173_aa;
         this.field_152776_e = this.field_152775_d;
         this.field_94554_b.func_152111_bt();
      }

   }

   public IChatComponent func_151521_b() {
      if (this.field_94556_a.size() == 0) {
         return new ChatComponentTranslation("death.attack.generic", new Object[]{this.field_94554_b.func_145748_c_()});
      } else {
         CombatEntry lvt_1_1_ = this.func_94544_f();
         CombatEntry lvt_2_1_ = (CombatEntry)this.field_94556_a.get(this.field_94556_a.size() - 1);
         IChatComponent lvt_4_1_ = lvt_2_1_.func_151522_h();
         Entity lvt_5_1_ = lvt_2_1_.func_94560_a().func_76346_g();
         Object lvt_3_7_;
         if (lvt_1_1_ != null && lvt_2_1_.func_94560_a() == DamageSource.field_76379_h) {
            IChatComponent lvt_6_1_ = lvt_1_1_.func_151522_h();
            if (lvt_1_1_.func_94560_a() != DamageSource.field_76379_h && lvt_1_1_.func_94560_a() != DamageSource.field_76380_i) {
               if (lvt_6_1_ == null || lvt_4_1_ != null && lvt_6_1_.equals(lvt_4_1_)) {
                  if (lvt_4_1_ != null) {
                     ItemStack lvt_7_2_ = lvt_5_1_ instanceof EntityLivingBase ? ((EntityLivingBase)lvt_5_1_).func_70694_bm() : null;
                     if (lvt_7_2_ != null && lvt_7_2_.func_82837_s()) {
                        lvt_3_7_ = new ChatComponentTranslation("death.fell.finish.item", new Object[]{this.field_94554_b.func_145748_c_(), lvt_4_1_, lvt_7_2_.func_151000_E()});
                     } else {
                        lvt_3_7_ = new ChatComponentTranslation("death.fell.finish", new Object[]{this.field_94554_b.func_145748_c_(), lvt_4_1_});
                     }
                  } else {
                     lvt_3_7_ = new ChatComponentTranslation("death.fell.killer", new Object[]{this.field_94554_b.func_145748_c_()});
                  }
               } else {
                  Entity lvt_7_1_ = lvt_1_1_.func_94560_a().func_76346_g();
                  ItemStack lvt_8_1_ = lvt_7_1_ instanceof EntityLivingBase ? ((EntityLivingBase)lvt_7_1_).func_70694_bm() : null;
                  if (lvt_8_1_ != null && lvt_8_1_.func_82837_s()) {
                     lvt_3_7_ = new ChatComponentTranslation("death.fell.assist.item", new Object[]{this.field_94554_b.func_145748_c_(), lvt_6_1_, lvt_8_1_.func_151000_E()});
                  } else {
                     lvt_3_7_ = new ChatComponentTranslation("death.fell.assist", new Object[]{this.field_94554_b.func_145748_c_(), lvt_6_1_});
                  }
               }
            } else {
               lvt_3_7_ = new ChatComponentTranslation("death.fell.accident." + this.func_94548_b(lvt_1_1_), new Object[]{this.field_94554_b.func_145748_c_()});
            }
         } else {
            lvt_3_7_ = lvt_2_1_.func_94560_a().func_151519_b(this.field_94554_b);
         }

         return (IChatComponent)lvt_3_7_;
      }
   }

   public EntityLivingBase func_94550_c() {
      EntityLivingBase lvt_1_1_ = null;
      EntityPlayer lvt_2_1_ = null;
      float lvt_3_1_ = 0.0F;
      float lvt_4_1_ = 0.0F;
      Iterator lvt_5_1_ = this.field_94556_a.iterator();

      while(true) {
         CombatEntry lvt_6_1_;
         do {
            do {
               if (!lvt_5_1_.hasNext()) {
                  if (lvt_2_1_ != null && lvt_4_1_ >= lvt_3_1_ / 3.0F) {
                     return lvt_2_1_;
                  }

                  return lvt_1_1_;
               }

               lvt_6_1_ = (CombatEntry)lvt_5_1_.next();
               if (lvt_6_1_.func_94560_a().func_76346_g() instanceof EntityPlayer && (lvt_2_1_ == null || lvt_6_1_.func_94563_c() > lvt_4_1_)) {
                  lvt_4_1_ = lvt_6_1_.func_94563_c();
                  lvt_2_1_ = (EntityPlayer)lvt_6_1_.func_94560_a().func_76346_g();
               }
            } while(!(lvt_6_1_.func_94560_a().func_76346_g() instanceof EntityLivingBase));
         } while(lvt_1_1_ != null && lvt_6_1_.func_94563_c() <= lvt_3_1_);

         lvt_3_1_ = lvt_6_1_.func_94563_c();
         lvt_1_1_ = (EntityLivingBase)lvt_6_1_.func_94560_a().func_76346_g();
      }
   }

   private CombatEntry func_94544_f() {
      CombatEntry lvt_1_1_ = null;
      CombatEntry lvt_2_1_ = null;
      int lvt_3_1_ = 0;
      float lvt_4_1_ = 0.0F;

      for(int lvt_5_1_ = 0; lvt_5_1_ < this.field_94556_a.size(); ++lvt_5_1_) {
         CombatEntry lvt_6_1_ = (CombatEntry)this.field_94556_a.get(lvt_5_1_);
         CombatEntry lvt_7_1_ = lvt_5_1_ > 0 ? (CombatEntry)this.field_94556_a.get(lvt_5_1_ - 1) : null;
         if ((lvt_6_1_.func_94560_a() == DamageSource.field_76379_h || lvt_6_1_.func_94560_a() == DamageSource.field_76380_i) && lvt_6_1_.func_94561_i() > 0.0F && (lvt_1_1_ == null || lvt_6_1_.func_94561_i() > lvt_4_1_)) {
            if (lvt_5_1_ > 0) {
               lvt_1_1_ = lvt_7_1_;
            } else {
               lvt_1_1_ = lvt_6_1_;
            }

            lvt_4_1_ = lvt_6_1_.func_94561_i();
         }

         if (lvt_6_1_.func_94562_g() != null && (lvt_2_1_ == null || lvt_6_1_.func_94563_c() > (float)lvt_3_1_)) {
            lvt_2_1_ = lvt_6_1_;
         }
      }

      if (lvt_4_1_ > 5.0F && lvt_1_1_ != null) {
         return lvt_1_1_;
      } else if (lvt_3_1_ > 5 && lvt_2_1_ != null) {
         return lvt_2_1_;
      } else {
         return null;
      }
   }

   private String func_94548_b(CombatEntry p_94548_1_) {
      return p_94548_1_.func_94562_g() == null ? "generic" : p_94548_1_.func_94562_g();
   }

   public int func_180134_f() {
      return this.field_94552_d ? this.field_94554_b.field_70173_aa - this.field_152775_d : this.field_152776_e - this.field_152775_d;
   }

   private void func_94542_g() {
      this.field_94551_f = null;
   }

   public void func_94549_h() {
      int lvt_1_1_ = this.field_94552_d ? 300 : 100;
      if (this.field_94553_e && (!this.field_94554_b.func_70089_S() || this.field_94554_b.field_70173_aa - this.field_94555_c > lvt_1_1_)) {
         boolean lvt_2_1_ = this.field_94552_d;
         this.field_94553_e = false;
         this.field_94552_d = false;
         this.field_152776_e = this.field_94554_b.field_70173_aa;
         if (lvt_2_1_) {
            this.field_94554_b.func_152112_bu();
         }

         this.field_94556_a.clear();
      }

   }

   public EntityLivingBase func_180135_h() {
      return this.field_94554_b;
   }
}
