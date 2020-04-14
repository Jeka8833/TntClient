package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityBreakingFX extends EntityFX {
   protected EntityBreakingFX(World p_i1195_1_, double p_i1195_2_, double p_i1195_4_, double p_i1195_6_, Item p_i1195_8_) {
      this(p_i1195_1_, p_i1195_2_, p_i1195_4_, p_i1195_6_, p_i1195_8_, 0);
   }

   protected EntityBreakingFX(World p_i1197_1_, double p_i1197_2_, double p_i1197_4_, double p_i1197_6_, double p_i1197_8_, double p_i1197_10_, double p_i1197_12_, Item p_i1197_14_, int p_i1197_15_) {
      this(p_i1197_1_, p_i1197_2_, p_i1197_4_, p_i1197_6_, p_i1197_14_, p_i1197_15_);
      this.field_70159_w *= 0.10000000149011612D;
      this.field_70181_x *= 0.10000000149011612D;
      this.field_70179_y *= 0.10000000149011612D;
      this.field_70159_w += p_i1197_8_;
      this.field_70181_x += p_i1197_10_;
      this.field_70179_y += p_i1197_12_;
   }

   protected EntityBreakingFX(World p_i1196_1_, double p_i1196_2_, double p_i1196_4_, double p_i1196_6_, Item p_i1196_8_, int p_i1196_9_) {
      super(p_i1196_1_, p_i1196_2_, p_i1196_4_, p_i1196_6_, 0.0D, 0.0D, 0.0D);
      this.func_180435_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(p_i1196_8_, p_i1196_9_));
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70545_g = Blocks.field_150433_aE.field_149763_I;
      this.field_70544_f /= 2.0F;
   }

   public int func_70537_b() {
      return 1;
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float lvt_9_1_ = ((float)this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
      float lvt_10_1_ = lvt_9_1_ + 0.015609375F;
      float lvt_11_1_ = ((float)this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
      float lvt_12_1_ = lvt_11_1_ + 0.015609375F;
      float lvt_13_1_ = 0.1F * this.field_70544_f;
      if (this.field_70550_a != null) {
         lvt_9_1_ = this.field_70550_a.func_94214_a((double)(this.field_70548_b / 4.0F * 16.0F));
         lvt_10_1_ = this.field_70550_a.func_94214_a((double)((this.field_70548_b + 1.0F) / 4.0F * 16.0F));
         lvt_11_1_ = this.field_70550_a.func_94207_b((double)(this.field_70549_c / 4.0F * 16.0F));
         lvt_12_1_ = this.field_70550_a.func_94207_b((double)((this.field_70549_c + 1.0F) / 4.0F * 16.0F));
      }

      float lvt_14_1_ = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_180434_3_ - field_70556_an);
      float lvt_15_1_ = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_180434_3_ - field_70554_ao);
      float lvt_16_1_ = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_180434_3_ - field_70555_ap);
      int lvt_17_1_ = this.func_70070_b(p_180434_3_);
      int lvt_18_1_ = lvt_17_1_ >> 16 & '\uffff';
      int lvt_19_1_ = lvt_17_1_ & '\uffff';
      p_180434_1_.func_181662_b((double)(lvt_14_1_ - p_180434_4_ * lvt_13_1_ - p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ - p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ - p_180434_6_ * lvt_13_1_ - p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_9_1_, (double)lvt_12_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ - p_180434_4_ * lvt_13_1_ + p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ + p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ - p_180434_6_ * lvt_13_1_ + p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_9_1_, (double)lvt_11_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ + p_180434_4_ * lvt_13_1_ + p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ + p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ + p_180434_6_ * lvt_13_1_ + p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_10_1_, (double)lvt_11_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
      p_180434_1_.func_181662_b((double)(lvt_14_1_ + p_180434_4_ * lvt_13_1_ - p_180434_7_ * lvt_13_1_), (double)(lvt_15_1_ - p_180434_5_ * lvt_13_1_), (double)(lvt_16_1_ + p_180434_6_ * lvt_13_1_ - p_180434_8_ * lvt_13_1_)).func_181673_a((double)lvt_10_1_, (double)lvt_12_1_).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_181671_a(lvt_18_1_, lvt_19_1_).func_181675_d();
   }

   public static class SnowballFactory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityBreakingFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, Items.field_151126_ay);
      }
   }

   public static class SlimeFactory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityBreakingFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, Items.field_151123_aH);
      }
   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         int lvt_16_1_ = p_178902_15_.length > 1 ? p_178902_15_[1] : 0;
         return new EntityBreakingFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, Item.func_150899_d(p_178902_15_[0]), lvt_16_1_);
      }
   }
}
