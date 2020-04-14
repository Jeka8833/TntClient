package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntitySilverfish extends EntityMob {
   private EntitySilverfish.AISummonSilverfish field_175460_b;

   public EntitySilverfish(World p_i1740_1_) {
      super(p_i1740_1_);
      this.func_70105_a(0.4F, 0.3F);
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(3, this.field_175460_b = new EntitySilverfish.AISummonSilverfish(this));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
      this.field_70714_bg.func_75776_a(5, new EntitySilverfish.AIHideInStone(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
   }

   public double func_70033_W() {
      return 0.2D;
   }

   public float func_70047_e() {
      return 0.1F;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected String func_70639_aQ() {
      return "mob.silverfish.say";
   }

   protected String func_70621_aR() {
      return "mob.silverfish.hit";
   }

   protected String func_70673_aS() {
      return "mob.silverfish.kill";
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         if (p_70097_1_ instanceof EntityDamageSource || p_70097_1_ == DamageSource.field_76376_m) {
            this.field_175460_b.func_179462_f();
         }

         return super.func_70097_a(p_70097_1_, p_70097_2_);
      }
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
   }

   protected Item func_146068_u() {
      return null;
   }

   public void func_70071_h_() {
      this.field_70761_aq = this.field_70177_z;
      super.func_70071_h_();
   }

   public float func_180484_a(BlockPos p_180484_1_) {
      return this.field_70170_p.func_180495_p(p_180484_1_.func_177977_b()).func_177230_c() == Blocks.field_150348_b ? 10.0F : super.func_180484_a(p_180484_1_);
   }

   protected boolean func_70814_o() {
      return true;
   }

   public boolean func_70601_bi() {
      if (super.func_70601_bi()) {
         EntityPlayer lvt_1_1_ = this.field_70170_p.func_72890_a(this, 5.0D);
         return lvt_1_1_ == null;
      } else {
         return false;
      }
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   static class AIHideInStone extends EntityAIWander {
      private final EntitySilverfish field_179485_a;
      private EnumFacing field_179483_b;
      private boolean field_179484_c;

      public AIHideInStone(EntitySilverfish p_i45827_1_) {
         super(p_i45827_1_, 1.0D, 10);
         this.field_179485_a = p_i45827_1_;
         this.func_75248_a(1);
      }

      public boolean func_75250_a() {
         if (this.field_179485_a.func_70638_az() != null) {
            return false;
         } else if (!this.field_179485_a.func_70661_as().func_75500_f()) {
            return false;
         } else {
            Random lvt_1_1_ = this.field_179485_a.func_70681_au();
            if (lvt_1_1_.nextInt(10) == 0) {
               this.field_179483_b = EnumFacing.func_176741_a(lvt_1_1_);
               BlockPos lvt_2_1_ = (new BlockPos(this.field_179485_a.field_70165_t, this.field_179485_a.field_70163_u + 0.5D, this.field_179485_a.field_70161_v)).func_177972_a(this.field_179483_b);
               IBlockState lvt_3_1_ = this.field_179485_a.field_70170_p.func_180495_p(lvt_2_1_);
               if (BlockSilverfish.func_176377_d(lvt_3_1_)) {
                  this.field_179484_c = true;
                  return true;
               }
            }

            this.field_179484_c = false;
            return super.func_75250_a();
         }
      }

      public boolean func_75253_b() {
         return this.field_179484_c ? false : super.func_75253_b();
      }

      public void func_75249_e() {
         if (!this.field_179484_c) {
            super.func_75249_e();
         } else {
            World lvt_1_1_ = this.field_179485_a.field_70170_p;
            BlockPos lvt_2_1_ = (new BlockPos(this.field_179485_a.field_70165_t, this.field_179485_a.field_70163_u + 0.5D, this.field_179485_a.field_70161_v)).func_177972_a(this.field_179483_b);
            IBlockState lvt_3_1_ = lvt_1_1_.func_180495_p(lvt_2_1_);
            if (BlockSilverfish.func_176377_d(lvt_3_1_)) {
               lvt_1_1_.func_180501_a(lvt_2_1_, Blocks.field_150418_aU.func_176223_P().func_177226_a(BlockSilverfish.field_176378_a, BlockSilverfish.EnumType.func_176878_a(lvt_3_1_)), 3);
               this.field_179485_a.func_70656_aK();
               this.field_179485_a.func_70106_y();
            }

         }
      }
   }

   static class AISummonSilverfish extends EntityAIBase {
      private EntitySilverfish field_179464_a;
      private int field_179463_b;

      public AISummonSilverfish(EntitySilverfish p_i45826_1_) {
         this.field_179464_a = p_i45826_1_;
      }

      public void func_179462_f() {
         if (this.field_179463_b == 0) {
            this.field_179463_b = 20;
         }

      }

      public boolean func_75250_a() {
         return this.field_179463_b > 0;
      }

      public void func_75246_d() {
         --this.field_179463_b;
         if (this.field_179463_b <= 0) {
            World lvt_1_1_ = this.field_179464_a.field_70170_p;
            Random lvt_2_1_ = this.field_179464_a.func_70681_au();
            BlockPos lvt_3_1_ = new BlockPos(this.field_179464_a);

            for(int lvt_4_1_ = 0; lvt_4_1_ <= 5 && lvt_4_1_ >= -5; lvt_4_1_ = lvt_4_1_ <= 0 ? 1 - lvt_4_1_ : 0 - lvt_4_1_) {
               for(int lvt_5_1_ = 0; lvt_5_1_ <= 10 && lvt_5_1_ >= -10; lvt_5_1_ = lvt_5_1_ <= 0 ? 1 - lvt_5_1_ : 0 - lvt_5_1_) {
                  for(int lvt_6_1_ = 0; lvt_6_1_ <= 10 && lvt_6_1_ >= -10; lvt_6_1_ = lvt_6_1_ <= 0 ? 1 - lvt_6_1_ : 0 - lvt_6_1_) {
                     BlockPos lvt_7_1_ = lvt_3_1_.func_177982_a(lvt_5_1_, lvt_4_1_, lvt_6_1_);
                     IBlockState lvt_8_1_ = lvt_1_1_.func_180495_p(lvt_7_1_);
                     if (lvt_8_1_.func_177230_c() == Blocks.field_150418_aU) {
                        if (lvt_1_1_.func_82736_K().func_82766_b("mobGriefing")) {
                           lvt_1_1_.func_175655_b(lvt_7_1_, true);
                        } else {
                           lvt_1_1_.func_180501_a(lvt_7_1_, ((BlockSilverfish.EnumType)lvt_8_1_.func_177229_b(BlockSilverfish.field_176378_a)).func_176883_d(), 3);
                        }

                        if (lvt_2_1_.nextBoolean()) {
                           return;
                        }
                     }
                  }
               }
            }
         }

      }
   }
}
