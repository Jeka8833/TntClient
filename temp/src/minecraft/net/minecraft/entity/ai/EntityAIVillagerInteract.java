package net.minecraft.entity.ai;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class EntityAIVillagerInteract extends EntityAIWatchClosest2 {
   private int field_179478_e;
   private EntityVillager field_179477_f;

   public EntityAIVillagerInteract(EntityVillager p_i45886_1_) {
      super(p_i45886_1_, EntityVillager.class, 3.0F, 0.02F);
      this.field_179477_f = p_i45886_1_;
   }

   public void func_75249_e() {
      super.func_75249_e();
      if (this.field_179477_f.func_175555_cq() && this.field_75334_a instanceof EntityVillager && ((EntityVillager)this.field_75334_a).func_175557_cr()) {
         this.field_179478_e = 10;
      } else {
         this.field_179478_e = 0;
      }

   }

   public void func_75246_d() {
      super.func_75246_d();
      if (this.field_179478_e > 0) {
         --this.field_179478_e;
         if (this.field_179478_e == 0) {
            InventoryBasic lvt_1_1_ = this.field_179477_f.func_175551_co();

            for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_.func_70302_i_(); ++lvt_2_1_) {
               ItemStack lvt_3_1_ = lvt_1_1_.func_70301_a(lvt_2_1_);
               ItemStack lvt_4_1_ = null;
               if (lvt_3_1_ != null) {
                  Item lvt_5_1_ = lvt_3_1_.func_77973_b();
                  int lvt_6_2_;
                  if ((lvt_5_1_ == Items.field_151025_P || lvt_5_1_ == Items.field_151174_bG || lvt_5_1_ == Items.field_151172_bF) && lvt_3_1_.field_77994_a > 3) {
                     lvt_6_2_ = lvt_3_1_.field_77994_a / 2;
                     lvt_3_1_.field_77994_a -= lvt_6_2_;
                     lvt_4_1_ = new ItemStack(lvt_5_1_, lvt_6_2_, lvt_3_1_.func_77960_j());
                  } else if (lvt_5_1_ == Items.field_151015_O && lvt_3_1_.field_77994_a > 5) {
                     lvt_6_2_ = lvt_3_1_.field_77994_a / 2 / 3 * 3;
                     int lvt_7_1_ = lvt_6_2_ / 3;
                     lvt_3_1_.field_77994_a -= lvt_6_2_;
                     lvt_4_1_ = new ItemStack(Items.field_151025_P, lvt_7_1_, 0);
                  }

                  if (lvt_3_1_.field_77994_a <= 0) {
                     lvt_1_1_.func_70299_a(lvt_2_1_, (ItemStack)null);
                  }
               }

               if (lvt_4_1_ != null) {
                  double lvt_5_2_ = this.field_179477_f.field_70163_u - 0.30000001192092896D + (double)this.field_179477_f.func_70047_e();
                  EntityItem lvt_7_2_ = new EntityItem(this.field_179477_f.field_70170_p, this.field_179477_f.field_70165_t, lvt_5_2_, this.field_179477_f.field_70161_v, lvt_4_1_);
                  float lvt_8_1_ = 0.3F;
                  float lvt_9_1_ = this.field_179477_f.field_70759_as;
                  float lvt_10_1_ = this.field_179477_f.field_70125_A;
                  lvt_7_2_.field_70159_w = (double)(-MathHelper.func_76126_a(lvt_9_1_ / 180.0F * 3.1415927F) * MathHelper.func_76134_b(lvt_10_1_ / 180.0F * 3.1415927F) * lvt_8_1_);
                  lvt_7_2_.field_70179_y = (double)(MathHelper.func_76134_b(lvt_9_1_ / 180.0F * 3.1415927F) * MathHelper.func_76134_b(lvt_10_1_ / 180.0F * 3.1415927F) * lvt_8_1_);
                  lvt_7_2_.field_70181_x = (double)(-MathHelper.func_76126_a(lvt_10_1_ / 180.0F * 3.1415927F) * lvt_8_1_ + 0.1F);
                  lvt_7_2_.func_174869_p();
                  this.field_179477_f.field_70170_p.func_72838_d(lvt_7_2_);
                  break;
               }
            }
         }
      }

   }
}
