package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging {
   public EntityLeashKnot(World p_i1592_1_) {
      super(p_i1592_1_);
   }

   public EntityLeashKnot(World p_i45851_1_, BlockPos p_i45851_2_) {
      super(p_i45851_1_, p_i45851_2_);
      this.func_70107_b((double)p_i45851_2_.func_177958_n() + 0.5D, (double)p_i45851_2_.func_177956_o() + 0.5D, (double)p_i45851_2_.func_177952_p() + 0.5D);
      float lvt_3_1_ = 0.125F;
      float lvt_4_1_ = 0.1875F;
      float lvt_5_1_ = 0.25F;
      this.func_174826_a(new AxisAlignedBB(this.field_70165_t - 0.1875D, this.field_70163_u - 0.25D + 0.125D, this.field_70161_v - 0.1875D, this.field_70165_t + 0.1875D, this.field_70163_u + 0.25D + 0.125D, this.field_70161_v + 0.1875D));
   }

   protected void func_70088_a() {
      super.func_70088_a();
   }

   public void func_174859_a(EnumFacing p_174859_1_) {
   }

   public int func_82329_d() {
      return 9;
   }

   public int func_82330_g() {
      return 9;
   }

   public float func_70047_e() {
      return -0.0625F;
   }

   public boolean func_70112_a(double p_70112_1_) {
      return p_70112_1_ < 1024.0D;
   }

   public void func_110128_b(Entity p_110128_1_) {
   }

   public boolean func_70039_c(NBTTagCompound p_70039_1_) {
      return false;
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      ItemStack lvt_2_1_ = p_130002_1_.func_70694_bm();
      boolean lvt_3_1_ = false;
      double lvt_4_2_;
      List lvt_6_2_;
      Iterator lvt_7_2_;
      EntityLiving lvt_8_2_;
      if (lvt_2_1_ != null && lvt_2_1_.func_77973_b() == Items.field_151058_ca && !this.field_70170_p.field_72995_K) {
         lvt_4_2_ = 7.0D;
         lvt_6_2_ = this.field_70170_p.func_72872_a(EntityLiving.class, new AxisAlignedBB(this.field_70165_t - lvt_4_2_, this.field_70163_u - lvt_4_2_, this.field_70161_v - lvt_4_2_, this.field_70165_t + lvt_4_2_, this.field_70163_u + lvt_4_2_, this.field_70161_v + lvt_4_2_));
         lvt_7_2_ = lvt_6_2_.iterator();

         while(lvt_7_2_.hasNext()) {
            lvt_8_2_ = (EntityLiving)lvt_7_2_.next();
            if (lvt_8_2_.func_110167_bD() && lvt_8_2_.func_110166_bE() == p_130002_1_) {
               lvt_8_2_.func_110162_b(this, true);
               lvt_3_1_ = true;
            }
         }
      }

      if (!this.field_70170_p.field_72995_K && !lvt_3_1_) {
         this.func_70106_y();
         if (p_130002_1_.field_71075_bZ.field_75098_d) {
            lvt_4_2_ = 7.0D;
            lvt_6_2_ = this.field_70170_p.func_72872_a(EntityLiving.class, new AxisAlignedBB(this.field_70165_t - lvt_4_2_, this.field_70163_u - lvt_4_2_, this.field_70161_v - lvt_4_2_, this.field_70165_t + lvt_4_2_, this.field_70163_u + lvt_4_2_, this.field_70161_v + lvt_4_2_));
            lvt_7_2_ = lvt_6_2_.iterator();

            while(lvt_7_2_.hasNext()) {
               lvt_8_2_ = (EntityLiving)lvt_7_2_.next();
               if (lvt_8_2_.func_110167_bD() && lvt_8_2_.func_110166_bE() == this) {
                  lvt_8_2_.func_110160_i(true, false);
               }
            }
         }
      }

      return true;
   }

   public boolean func_70518_d() {
      return this.field_70170_p.func_180495_p(this.field_174861_a).func_177230_c() instanceof BlockFence;
   }

   public static EntityLeashKnot func_174862_a(World p_174862_0_, BlockPos p_174862_1_) {
      EntityLeashKnot lvt_2_1_ = new EntityLeashKnot(p_174862_0_, p_174862_1_);
      lvt_2_1_.field_98038_p = true;
      p_174862_0_.func_72838_d(lvt_2_1_);
      return lvt_2_1_;
   }

   public static EntityLeashKnot func_174863_b(World p_174863_0_, BlockPos p_174863_1_) {
      int lvt_2_1_ = p_174863_1_.func_177958_n();
      int lvt_3_1_ = p_174863_1_.func_177956_o();
      int lvt_4_1_ = p_174863_1_.func_177952_p();
      List<EntityLeashKnot> lvt_5_1_ = p_174863_0_.func_72872_a(EntityLeashKnot.class, new AxisAlignedBB((double)lvt_2_1_ - 1.0D, (double)lvt_3_1_ - 1.0D, (double)lvt_4_1_ - 1.0D, (double)lvt_2_1_ + 1.0D, (double)lvt_3_1_ + 1.0D, (double)lvt_4_1_ + 1.0D));
      Iterator lvt_6_1_ = lvt_5_1_.iterator();

      EntityLeashKnot lvt_7_1_;
      do {
         if (!lvt_6_1_.hasNext()) {
            return null;
         }

         lvt_7_1_ = (EntityLeashKnot)lvt_6_1_.next();
      } while(!lvt_7_1_.func_174857_n().equals(p_174863_1_));

      return lvt_7_1_;
   }
}
