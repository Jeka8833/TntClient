package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class EntityAIControlledByPlayer extends EntityAIBase {
   private final EntityLiving field_82640_a;
   private final float field_82638_b;
   private float field_82639_c;
   private boolean field_82636_d;
   private int field_82637_e;
   private int field_82635_f;

   public EntityAIControlledByPlayer(EntityLiving p_i1620_1_, float p_i1620_2_) {
      this.field_82640_a = p_i1620_1_;
      this.field_82638_b = p_i1620_2_;
      this.func_75248_a(7);
   }

   public void func_75249_e() {
      this.field_82639_c = 0.0F;
   }

   public void func_75251_c() {
      this.field_82636_d = false;
      this.field_82639_c = 0.0F;
   }

   public boolean func_75250_a() {
      return this.field_82640_a.func_70089_S() && this.field_82640_a.field_70153_n != null && this.field_82640_a.field_70153_n instanceof EntityPlayer && (this.field_82636_d || this.field_82640_a.func_82171_bF());
   }

   public void func_75246_d() {
      EntityPlayer lvt_1_1_ = (EntityPlayer)this.field_82640_a.field_70153_n;
      EntityCreature lvt_2_1_ = (EntityCreature)this.field_82640_a;
      float lvt_3_1_ = MathHelper.func_76142_g(lvt_1_1_.field_70177_z - this.field_82640_a.field_70177_z) * 0.5F;
      if (lvt_3_1_ > 5.0F) {
         lvt_3_1_ = 5.0F;
      }

      if (lvt_3_1_ < -5.0F) {
         lvt_3_1_ = -5.0F;
      }

      this.field_82640_a.field_70177_z = MathHelper.func_76142_g(this.field_82640_a.field_70177_z + lvt_3_1_);
      if (this.field_82639_c < this.field_82638_b) {
         this.field_82639_c += (this.field_82638_b - this.field_82639_c) * 0.01F;
      }

      if (this.field_82639_c > this.field_82638_b) {
         this.field_82639_c = this.field_82638_b;
      }

      int lvt_4_1_ = MathHelper.func_76128_c(this.field_82640_a.field_70165_t);
      int lvt_5_1_ = MathHelper.func_76128_c(this.field_82640_a.field_70163_u);
      int lvt_6_1_ = MathHelper.func_76128_c(this.field_82640_a.field_70161_v);
      float lvt_7_1_ = this.field_82639_c;
      if (this.field_82636_d) {
         if (this.field_82637_e++ > this.field_82635_f) {
            this.field_82636_d = false;
         }

         lvt_7_1_ += lvt_7_1_ * 1.15F * MathHelper.func_76126_a((float)this.field_82637_e / (float)this.field_82635_f * 3.1415927F);
      }

      float lvt_8_1_ = 0.91F;
      if (this.field_82640_a.field_70122_E) {
         lvt_8_1_ = this.field_82640_a.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76141_d((float)lvt_4_1_), MathHelper.func_76141_d((float)lvt_5_1_) - 1, MathHelper.func_76141_d((float)lvt_6_1_))).func_177230_c().field_149765_K * 0.91F;
      }

      float lvt_9_1_ = 0.16277136F / (lvt_8_1_ * lvt_8_1_ * lvt_8_1_);
      float lvt_10_1_ = MathHelper.func_76126_a(lvt_2_1_.field_70177_z * 3.1415927F / 180.0F);
      float lvt_11_1_ = MathHelper.func_76134_b(lvt_2_1_.field_70177_z * 3.1415927F / 180.0F);
      float lvt_12_1_ = lvt_2_1_.func_70689_ay() * lvt_9_1_;
      float lvt_13_1_ = Math.max(lvt_7_1_, 1.0F);
      lvt_13_1_ = lvt_12_1_ / lvt_13_1_;
      float lvt_14_1_ = lvt_7_1_ * lvt_13_1_;
      float lvt_15_1_ = -(lvt_14_1_ * lvt_10_1_);
      float lvt_16_1_ = lvt_14_1_ * lvt_11_1_;
      if (MathHelper.func_76135_e(lvt_15_1_) > MathHelper.func_76135_e(lvt_16_1_)) {
         if (lvt_15_1_ < 0.0F) {
            lvt_15_1_ -= this.field_82640_a.field_70130_N / 2.0F;
         }

         if (lvt_15_1_ > 0.0F) {
            lvt_15_1_ += this.field_82640_a.field_70130_N / 2.0F;
         }

         lvt_16_1_ = 0.0F;
      } else {
         lvt_15_1_ = 0.0F;
         if (lvt_16_1_ < 0.0F) {
            lvt_16_1_ -= this.field_82640_a.field_70130_N / 2.0F;
         }

         if (lvt_16_1_ > 0.0F) {
            lvt_16_1_ += this.field_82640_a.field_70130_N / 2.0F;
         }
      }

      int lvt_17_1_ = MathHelper.func_76128_c(this.field_82640_a.field_70165_t + (double)lvt_15_1_);
      int lvt_18_1_ = MathHelper.func_76128_c(this.field_82640_a.field_70161_v + (double)lvt_16_1_);
      int lvt_19_1_ = MathHelper.func_76141_d(this.field_82640_a.field_70130_N + 1.0F);
      int lvt_20_1_ = MathHelper.func_76141_d(this.field_82640_a.field_70131_O + lvt_1_1_.field_70131_O + 1.0F);
      int lvt_21_1_ = MathHelper.func_76141_d(this.field_82640_a.field_70130_N + 1.0F);
      if (lvt_4_1_ != lvt_17_1_ || lvt_6_1_ != lvt_18_1_) {
         Block lvt_22_1_ = this.field_82640_a.field_70170_p.func_180495_p(new BlockPos(lvt_4_1_, lvt_5_1_, lvt_6_1_)).func_177230_c();
         boolean lvt_23_1_ = !this.func_151498_a(lvt_22_1_) && (lvt_22_1_.func_149688_o() != Material.field_151579_a || !this.func_151498_a(this.field_82640_a.field_70170_p.func_180495_p(new BlockPos(lvt_4_1_, lvt_5_1_ - 1, lvt_6_1_)).func_177230_c()));
         if (lvt_23_1_ && 0 == WalkNodeProcessor.func_176170_a(this.field_82640_a.field_70170_p, this.field_82640_a, lvt_17_1_, lvt_5_1_, lvt_18_1_, lvt_19_1_, lvt_20_1_, lvt_21_1_, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(this.field_82640_a.field_70170_p, this.field_82640_a, lvt_4_1_, lvt_5_1_ + 1, lvt_6_1_, lvt_19_1_, lvt_20_1_, lvt_21_1_, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(this.field_82640_a.field_70170_p, this.field_82640_a, lvt_17_1_, lvt_5_1_ + 1, lvt_18_1_, lvt_19_1_, lvt_20_1_, lvt_21_1_, false, false, true)) {
            lvt_2_1_.func_70683_ar().func_75660_a();
         }
      }

      if (!lvt_1_1_.field_71075_bZ.field_75098_d && this.field_82639_c >= this.field_82638_b * 0.5F && this.field_82640_a.func_70681_au().nextFloat() < 0.006F && !this.field_82636_d) {
         ItemStack lvt_22_2_ = lvt_1_1_.func_70694_bm();
         if (lvt_22_2_ != null && lvt_22_2_.func_77973_b() == Items.field_151146_bM) {
            lvt_22_2_.func_77972_a(1, lvt_1_1_);
            if (lvt_22_2_.field_77994_a == 0) {
               ItemStack lvt_23_2_ = new ItemStack(Items.field_151112_aM);
               lvt_23_2_.func_77982_d(lvt_22_2_.func_77978_p());
               lvt_1_1_.field_71071_by.field_70462_a[lvt_1_1_.field_71071_by.field_70461_c] = lvt_23_2_;
            }
         }
      }

      this.field_82640_a.func_70612_e(0.0F, lvt_7_1_);
   }

   private boolean func_151498_a(Block p_151498_1_) {
      return p_151498_1_ instanceof BlockStairs || p_151498_1_ instanceof BlockSlab;
   }

   public boolean func_82634_f() {
      return this.field_82636_d;
   }

   public void func_82632_g() {
      this.field_82636_d = true;
      this.field_82637_e = 0;
      this.field_82635_f = this.field_82640_a.func_70681_au().nextInt(841) + 140;
   }

   public boolean func_82633_h() {
      return !this.func_82634_f() && this.field_82639_c > this.field_82638_b * 0.3F;
   }
}
