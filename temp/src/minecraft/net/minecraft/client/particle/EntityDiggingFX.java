package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityDiggingFX extends EntityFX {
   private IBlockState field_174847_a;
   private BlockPos field_181019_az;

   protected EntityDiggingFX(World p_i46280_1_, double p_i46280_2_, double p_i46280_4_, double p_i46280_6_, double p_i46280_8_, double p_i46280_10_, double p_i46280_12_, IBlockState p_i46280_14_) {
      super(p_i46280_1_, p_i46280_2_, p_i46280_4_, p_i46280_6_, p_i46280_8_, p_i46280_10_, p_i46280_12_);
      this.field_174847_a = p_i46280_14_;
      this.func_180435_a(Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(p_i46280_14_));
      this.field_70545_g = p_i46280_14_.func_177230_c().field_149763_I;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.6F;
      this.field_70544_f /= 2.0F;
   }

   public EntityDiggingFX func_174846_a(BlockPos p_174846_1_) {
      this.field_181019_az = p_174846_1_;
      if (this.field_174847_a.func_177230_c() == Blocks.field_150349_c) {
         return this;
      } else {
         int lvt_2_1_ = this.field_174847_a.func_177230_c().func_176202_d(this.field_70170_p, p_174846_1_);
         this.field_70552_h *= (float)(lvt_2_1_ >> 16 & 255) / 255.0F;
         this.field_70553_i *= (float)(lvt_2_1_ >> 8 & 255) / 255.0F;
         this.field_70551_j *= (float)(lvt_2_1_ & 255) / 255.0F;
         return this;
      }
   }

   public EntityDiggingFX func_174845_l() {
      this.field_181019_az = new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Block lvt_1_1_ = this.field_174847_a.func_177230_c();
      if (lvt_1_1_ == Blocks.field_150349_c) {
         return this;
      } else {
         int lvt_2_1_ = lvt_1_1_.func_180644_h(this.field_174847_a);
         this.field_70552_h *= (float)(lvt_2_1_ >> 16 & 255) / 255.0F;
         this.field_70553_i *= (float)(lvt_2_1_ >> 8 & 255) / 255.0F;
         this.field_70551_j *= (float)(lvt_2_1_ & 255) / 255.0F;
         return this;
      }
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

   public int func_70070_b(float p_70070_1_) {
      int lvt_2_1_ = super.func_70070_b(p_70070_1_);
      int lvt_3_1_ = 0;
      if (this.field_70170_p.func_175667_e(this.field_181019_az)) {
         lvt_3_1_ = this.field_70170_p.func_175626_b(this.field_181019_az, 0);
      }

      return lvt_2_1_ == 0 ? lvt_3_1_ : lvt_2_1_;
   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return (new EntityDiggingFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, Block.func_176220_d(p_178902_15_[0]))).func_174845_l();
      }
   }
}
