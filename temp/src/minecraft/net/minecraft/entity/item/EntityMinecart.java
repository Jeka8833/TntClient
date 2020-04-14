package net.minecraft.entity.item;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity implements IWorldNameable {
   private boolean field_70499_f;
   private String field_94102_c;
   private static final int[][][] field_70500_g = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
   private int field_70510_h;
   private double field_70511_i;
   private double field_70509_j;
   private double field_70514_an;
   private double field_70512_ao;
   private double field_70513_ap;
   private double field_70508_aq;
   private double field_70507_ar;
   private double field_70506_as;

   public EntityMinecart(World p_i1712_1_) {
      super(p_i1712_1_);
      this.field_70156_m = true;
      this.func_70105_a(0.98F, 0.7F);
   }

   public static EntityMinecart func_180458_a(World p_180458_0_, double p_180458_1_, double p_180458_3_, double p_180458_5_, EntityMinecart.EnumMinecartType p_180458_7_) {
      switch(p_180458_7_) {
      case CHEST:
         return new EntityMinecartChest(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      case FURNACE:
         return new EntityMinecartFurnace(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      case TNT:
         return new EntityMinecartTNT(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      case SPAWNER:
         return new EntityMinecartMobSpawner(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      case HOPPER:
         return new EntityMinecartHopper(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      case COMMAND_BLOCK:
         return new EntityMinecartCommandBlock(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      default:
         return new EntityMinecartEmpty(p_180458_0_, p_180458_1_, p_180458_3_, p_180458_5_);
      }
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(17, new Integer(0));
      this.field_70180_af.func_75682_a(18, new Integer(1));
      this.field_70180_af.func_75682_a(19, new Float(0.0F));
      this.field_70180_af.func_75682_a(20, new Integer(0));
      this.field_70180_af.func_75682_a(21, new Integer(6));
      this.field_70180_af.func_75682_a(22, (byte)0);
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return p_70114_1_.func_70104_M() ? p_70114_1_.func_174813_aQ() : null;
   }

   public AxisAlignedBB func_70046_E() {
      return null;
   }

   public boolean func_70104_M() {
      return true;
   }

   public EntityMinecart(World p_i1713_1_, double p_i1713_2_, double p_i1713_4_, double p_i1713_6_) {
      this(p_i1713_1_);
      this.func_70107_b(p_i1713_2_, p_i1713_4_, p_i1713_6_);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = p_i1713_2_;
      this.field_70167_r = p_i1713_4_;
      this.field_70166_s = p_i1713_6_;
   }

   public double func_70042_X() {
      return 0.0D;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         if (this.func_180431_b(p_70097_1_)) {
            return false;
         } else {
            this.func_70494_i(-this.func_70493_k());
            this.func_70497_h(10);
            this.func_70018_K();
            this.func_70492_c(this.func_70491_i() + p_70097_2_ * 10.0F);
            boolean lvt_3_1_ = p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d;
            if (lvt_3_1_ || this.func_70491_i() > 40.0F) {
               if (this.field_70153_n != null) {
                  this.field_70153_n.func_70078_a((Entity)null);
               }

               if (lvt_3_1_ && !this.func_145818_k_()) {
                  this.func_70106_y();
               } else {
                  this.func_94095_a(p_70097_1_);
               }
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void func_94095_a(DamageSource p_94095_1_) {
      this.func_70106_y();
      if (this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
         ItemStack lvt_2_1_ = new ItemStack(Items.field_151143_au, 1);
         if (this.field_94102_c != null) {
            lvt_2_1_.func_151001_c(this.field_94102_c);
         }

         this.func_70099_a(lvt_2_1_, 0.0F);
      }

   }

   public void func_70057_ab() {
      this.func_70494_i(-this.func_70493_k());
      this.func_70497_h(10);
      this.func_70492_c(this.func_70491_i() + this.func_70491_i() * 10.0F);
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_70106_y() {
      super.func_70106_y();
   }

   public void func_70071_h_() {
      if (this.func_70496_j() > 0) {
         this.func_70497_h(this.func_70496_j() - 1);
      }

      if (this.func_70491_i() > 0.0F) {
         this.func_70492_c(this.func_70491_i() - 1.0F);
      }

      if (this.field_70163_u < -64.0D) {
         this.func_70076_C();
      }

      int lvt_2_2_;
      if (!this.field_70170_p.field_72995_K && this.field_70170_p instanceof WorldServer) {
         this.field_70170_p.field_72984_F.func_76320_a("portal");
         MinecraftServer lvt_1_1_ = ((WorldServer)this.field_70170_p).func_73046_m();
         lvt_2_2_ = this.func_82145_z();
         if (this.field_71087_bX) {
            if (lvt_1_1_.func_71255_r()) {
               if (this.field_70154_o == null && this.field_82153_h++ >= lvt_2_2_) {
                  this.field_82153_h = lvt_2_2_;
                  this.field_71088_bW = this.func_82147_ab();
                  byte lvt_3_2_;
                  if (this.field_70170_p.field_73011_w.func_177502_q() == -1) {
                     lvt_3_2_ = 0;
                  } else {
                     lvt_3_2_ = -1;
                  }

                  this.func_71027_c(lvt_3_2_);
               }

               this.field_71087_bX = false;
            }
         } else {
            if (this.field_82153_h > 0) {
               this.field_82153_h -= 4;
            }

            if (this.field_82153_h < 0) {
               this.field_82153_h = 0;
            }
         }

         if (this.field_71088_bW > 0) {
            --this.field_71088_bW;
         }

         this.field_70170_p.field_72984_F.func_76319_b();
      }

      if (this.field_70170_p.field_72995_K) {
         if (this.field_70510_h > 0) {
            double lvt_1_2_ = this.field_70165_t + (this.field_70511_i - this.field_70165_t) / (double)this.field_70510_h;
            double lvt_3_3_ = this.field_70163_u + (this.field_70509_j - this.field_70163_u) / (double)this.field_70510_h;
            double lvt_5_1_ = this.field_70161_v + (this.field_70514_an - this.field_70161_v) / (double)this.field_70510_h;
            double lvt_7_1_ = MathHelper.func_76138_g(this.field_70512_ao - (double)this.field_70177_z);
            this.field_70177_z = (float)((double)this.field_70177_z + lvt_7_1_ / (double)this.field_70510_h);
            this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70513_ap - (double)this.field_70125_A) / (double)this.field_70510_h);
            --this.field_70510_h;
            this.func_70107_b(lvt_1_2_, lvt_3_3_, lvt_5_1_);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
         } else {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
         }

      } else {
         this.field_70169_q = this.field_70165_t;
         this.field_70167_r = this.field_70163_u;
         this.field_70166_s = this.field_70161_v;
         this.field_70181_x -= 0.03999999910593033D;
         int lvt_1_3_ = MathHelper.func_76128_c(this.field_70165_t);
         lvt_2_2_ = MathHelper.func_76128_c(this.field_70163_u);
         int lvt_3_4_ = MathHelper.func_76128_c(this.field_70161_v);
         if (BlockRailBase.func_176562_d(this.field_70170_p, new BlockPos(lvt_1_3_, lvt_2_2_ - 1, lvt_3_4_))) {
            --lvt_2_2_;
         }

         BlockPos lvt_4_1_ = new BlockPos(lvt_1_3_, lvt_2_2_, lvt_3_4_);
         IBlockState lvt_5_2_ = this.field_70170_p.func_180495_p(lvt_4_1_);
         if (BlockRailBase.func_176563_d(lvt_5_2_)) {
            this.func_180460_a(lvt_4_1_, lvt_5_2_);
            if (lvt_5_2_.func_177230_c() == Blocks.field_150408_cc) {
               this.func_96095_a(lvt_1_3_, lvt_2_2_, lvt_3_4_, (Boolean)lvt_5_2_.func_177229_b(BlockRailPowered.field_176569_M));
            }
         } else {
            this.func_180459_n();
         }

         this.func_145775_I();
         this.field_70125_A = 0.0F;
         double lvt_6_1_ = this.field_70169_q - this.field_70165_t;
         double lvt_8_1_ = this.field_70166_s - this.field_70161_v;
         if (lvt_6_1_ * lvt_6_1_ + lvt_8_1_ * lvt_8_1_ > 0.001D) {
            this.field_70177_z = (float)(MathHelper.func_181159_b(lvt_8_1_, lvt_6_1_) * 180.0D / 3.141592653589793D);
            if (this.field_70499_f) {
               this.field_70177_z += 180.0F;
            }
         }

         double lvt_10_1_ = (double)MathHelper.func_76142_g(this.field_70177_z - this.field_70126_B);
         if (lvt_10_1_ < -170.0D || lvt_10_1_ >= 170.0D) {
            this.field_70177_z += 180.0F;
            this.field_70499_f = !this.field_70499_f;
         }

         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         Iterator lvt_12_1_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D)).iterator();

         while(lvt_12_1_.hasNext()) {
            Entity lvt_13_1_ = (Entity)lvt_12_1_.next();
            if (lvt_13_1_ != this.field_70153_n && lvt_13_1_.func_70104_M() && lvt_13_1_ instanceof EntityMinecart) {
               lvt_13_1_.func_70108_f(this);
            }
         }

         if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
            if (this.field_70153_n.field_70154_o == this) {
               this.field_70153_n.field_70154_o = null;
            }

            this.field_70153_n = null;
         }

         this.func_70072_I();
      }
   }

   protected double func_174898_m() {
      return 0.4D;
   }

   public void func_96095_a(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
   }

   protected void func_180459_n() {
      double lvt_1_1_ = this.func_174898_m();
      this.field_70159_w = MathHelper.func_151237_a(this.field_70159_w, -lvt_1_1_, lvt_1_1_);
      this.field_70179_y = MathHelper.func_151237_a(this.field_70179_y, -lvt_1_1_, lvt_1_1_);
      if (this.field_70122_E) {
         this.field_70159_w *= 0.5D;
         this.field_70181_x *= 0.5D;
         this.field_70179_y *= 0.5D;
      }

      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      if (!this.field_70122_E) {
         this.field_70159_w *= 0.949999988079071D;
         this.field_70181_x *= 0.949999988079071D;
         this.field_70179_y *= 0.949999988079071D;
      }

   }

   protected void func_180460_a(BlockPos p_180460_1_, IBlockState p_180460_2_) {
      this.field_70143_R = 0.0F;
      Vec3 lvt_3_1_ = this.func_70489_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70163_u = (double)p_180460_1_.func_177956_o();
      boolean lvt_4_1_ = false;
      boolean lvt_5_1_ = false;
      BlockRailBase lvt_6_1_ = (BlockRailBase)p_180460_2_.func_177230_c();
      if (lvt_6_1_ == Blocks.field_150318_D) {
         lvt_4_1_ = (Boolean)p_180460_2_.func_177229_b(BlockRailPowered.field_176569_M);
         lvt_5_1_ = !lvt_4_1_;
      }

      double lvt_7_1_ = 0.0078125D;
      BlockRailBase.EnumRailDirection lvt_9_1_ = (BlockRailBase.EnumRailDirection)p_180460_2_.func_177229_b(lvt_6_1_.func_176560_l());
      switch(lvt_9_1_) {
      case ASCENDING_EAST:
         this.field_70159_w -= 0.0078125D;
         ++this.field_70163_u;
         break;
      case ASCENDING_WEST:
         this.field_70159_w += 0.0078125D;
         ++this.field_70163_u;
         break;
      case ASCENDING_NORTH:
         this.field_70179_y += 0.0078125D;
         ++this.field_70163_u;
         break;
      case ASCENDING_SOUTH:
         this.field_70179_y -= 0.0078125D;
         ++this.field_70163_u;
      }

      int[][] lvt_10_1_ = field_70500_g[lvt_9_1_.func_177015_a()];
      double lvt_11_1_ = (double)(lvt_10_1_[1][0] - lvt_10_1_[0][0]);
      double lvt_13_1_ = (double)(lvt_10_1_[1][2] - lvt_10_1_[0][2]);
      double lvt_15_1_ = Math.sqrt(lvt_11_1_ * lvt_11_1_ + lvt_13_1_ * lvt_13_1_);
      double lvt_17_1_ = this.field_70159_w * lvt_11_1_ + this.field_70179_y * lvt_13_1_;
      if (lvt_17_1_ < 0.0D) {
         lvt_11_1_ = -lvt_11_1_;
         lvt_13_1_ = -lvt_13_1_;
      }

      double lvt_19_1_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      if (lvt_19_1_ > 2.0D) {
         lvt_19_1_ = 2.0D;
      }

      this.field_70159_w = lvt_19_1_ * lvt_11_1_ / lvt_15_1_;
      this.field_70179_y = lvt_19_1_ * lvt_13_1_ / lvt_15_1_;
      double lvt_21_3_;
      double lvt_23_2_;
      double lvt_25_2_;
      double lvt_27_1_;
      if (this.field_70153_n instanceof EntityLivingBase) {
         lvt_21_3_ = (double)((EntityLivingBase)this.field_70153_n).field_70701_bs;
         if (lvt_21_3_ > 0.0D) {
            lvt_23_2_ = -Math.sin((double)(this.field_70153_n.field_70177_z * 3.1415927F / 180.0F));
            lvt_25_2_ = Math.cos((double)(this.field_70153_n.field_70177_z * 3.1415927F / 180.0F));
            lvt_27_1_ = this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y;
            if (lvt_27_1_ < 0.01D) {
               this.field_70159_w += lvt_23_2_ * 0.1D;
               this.field_70179_y += lvt_25_2_ * 0.1D;
               lvt_5_1_ = false;
            }
         }
      }

      if (lvt_5_1_) {
         lvt_21_3_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         if (lvt_21_3_ < 0.03D) {
            this.field_70159_w *= 0.0D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.0D;
         } else {
            this.field_70159_w *= 0.5D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.5D;
         }
      }

      lvt_21_3_ = 0.0D;
      lvt_23_2_ = (double)p_180460_1_.func_177958_n() + 0.5D + (double)lvt_10_1_[0][0] * 0.5D;
      lvt_25_2_ = (double)p_180460_1_.func_177952_p() + 0.5D + (double)lvt_10_1_[0][2] * 0.5D;
      lvt_27_1_ = (double)p_180460_1_.func_177958_n() + 0.5D + (double)lvt_10_1_[1][0] * 0.5D;
      double lvt_29_1_ = (double)p_180460_1_.func_177952_p() + 0.5D + (double)lvt_10_1_[1][2] * 0.5D;
      lvt_11_1_ = lvt_27_1_ - lvt_23_2_;
      lvt_13_1_ = lvt_29_1_ - lvt_25_2_;
      double lvt_31_2_;
      double lvt_33_2_;
      if (lvt_11_1_ == 0.0D) {
         this.field_70165_t = (double)p_180460_1_.func_177958_n() + 0.5D;
         lvt_21_3_ = this.field_70161_v - (double)p_180460_1_.func_177952_p();
      } else if (lvt_13_1_ == 0.0D) {
         this.field_70161_v = (double)p_180460_1_.func_177952_p() + 0.5D;
         lvt_21_3_ = this.field_70165_t - (double)p_180460_1_.func_177958_n();
      } else {
         lvt_31_2_ = this.field_70165_t - lvt_23_2_;
         lvt_33_2_ = this.field_70161_v - lvt_25_2_;
         lvt_21_3_ = (lvt_31_2_ * lvt_11_1_ + lvt_33_2_ * lvt_13_1_) * 2.0D;
      }

      this.field_70165_t = lvt_23_2_ + lvt_11_1_ * lvt_21_3_;
      this.field_70161_v = lvt_25_2_ + lvt_13_1_ * lvt_21_3_;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      lvt_31_2_ = this.field_70159_w;
      lvt_33_2_ = this.field_70179_y;
      if (this.field_70153_n != null) {
         lvt_31_2_ *= 0.75D;
         lvt_33_2_ *= 0.75D;
      }

      double lvt_35_1_ = this.func_174898_m();
      lvt_31_2_ = MathHelper.func_151237_a(lvt_31_2_, -lvt_35_1_, lvt_35_1_);
      lvt_33_2_ = MathHelper.func_151237_a(lvt_33_2_, -lvt_35_1_, lvt_35_1_);
      this.func_70091_d(lvt_31_2_, 0.0D, lvt_33_2_);
      if (lvt_10_1_[0][1] != 0 && MathHelper.func_76128_c(this.field_70165_t) - p_180460_1_.func_177958_n() == lvt_10_1_[0][0] && MathHelper.func_76128_c(this.field_70161_v) - p_180460_1_.func_177952_p() == lvt_10_1_[0][2]) {
         this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)lvt_10_1_[0][1], this.field_70161_v);
      } else if (lvt_10_1_[1][1] != 0 && MathHelper.func_76128_c(this.field_70165_t) - p_180460_1_.func_177958_n() == lvt_10_1_[1][0] && MathHelper.func_76128_c(this.field_70161_v) - p_180460_1_.func_177952_p() == lvt_10_1_[1][2]) {
         this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)lvt_10_1_[1][1], this.field_70161_v);
      }

      this.func_94101_h();
      Vec3 lvt_37_1_ = this.func_70489_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      if (lvt_37_1_ != null && lvt_3_1_ != null) {
         double lvt_38_1_ = (lvt_3_1_.field_72448_b - lvt_37_1_.field_72448_b) * 0.05D;
         lvt_19_1_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         if (lvt_19_1_ > 0.0D) {
            this.field_70159_w = this.field_70159_w / lvt_19_1_ * (lvt_19_1_ + lvt_38_1_);
            this.field_70179_y = this.field_70179_y / lvt_19_1_ * (lvt_19_1_ + lvt_38_1_);
         }

         this.func_70107_b(this.field_70165_t, lvt_37_1_.field_72448_b, this.field_70161_v);
      }

      int lvt_38_2_ = MathHelper.func_76128_c(this.field_70165_t);
      int lvt_39_1_ = MathHelper.func_76128_c(this.field_70161_v);
      if (lvt_38_2_ != p_180460_1_.func_177958_n() || lvt_39_1_ != p_180460_1_.func_177952_p()) {
         lvt_19_1_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70159_w = lvt_19_1_ * (double)(lvt_38_2_ - p_180460_1_.func_177958_n());
         this.field_70179_y = lvt_19_1_ * (double)(lvt_39_1_ - p_180460_1_.func_177952_p());
      }

      if (lvt_4_1_) {
         double lvt_40_1_ = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         if (lvt_40_1_ > 0.01D) {
            double lvt_42_1_ = 0.06D;
            this.field_70159_w += this.field_70159_w / lvt_40_1_ * lvt_42_1_;
            this.field_70179_y += this.field_70179_y / lvt_40_1_ * lvt_42_1_;
         } else if (lvt_9_1_ == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (this.field_70170_p.func_180495_p(p_180460_1_.func_177976_e()).func_177230_c().func_149721_r()) {
               this.field_70159_w = 0.02D;
            } else if (this.field_70170_p.func_180495_p(p_180460_1_.func_177974_f()).func_177230_c().func_149721_r()) {
               this.field_70159_w = -0.02D;
            }
         } else if (lvt_9_1_ == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (this.field_70170_p.func_180495_p(p_180460_1_.func_177978_c()).func_177230_c().func_149721_r()) {
               this.field_70179_y = 0.02D;
            } else if (this.field_70170_p.func_180495_p(p_180460_1_.func_177968_d()).func_177230_c().func_149721_r()) {
               this.field_70179_y = -0.02D;
            }
         }
      }

   }

   protected void func_94101_h() {
      if (this.field_70153_n != null) {
         this.field_70159_w *= 0.996999979019165D;
         this.field_70181_x *= 0.0D;
         this.field_70179_y *= 0.996999979019165D;
      } else {
         this.field_70159_w *= 0.9599999785423279D;
         this.field_70181_x *= 0.0D;
         this.field_70179_y *= 0.9599999785423279D;
      }

   }

   public void func_70107_b(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
      this.field_70165_t = p_70107_1_;
      this.field_70163_u = p_70107_3_;
      this.field_70161_v = p_70107_5_;
      float lvt_7_1_ = this.field_70130_N / 2.0F;
      float lvt_8_1_ = this.field_70131_O;
      this.func_174826_a(new AxisAlignedBB(p_70107_1_ - (double)lvt_7_1_, p_70107_3_, p_70107_5_ - (double)lvt_7_1_, p_70107_1_ + (double)lvt_7_1_, p_70107_3_ + (double)lvt_8_1_, p_70107_5_ + (double)lvt_7_1_));
   }

   public Vec3 func_70495_a(double p_70495_1_, double p_70495_3_, double p_70495_5_, double p_70495_7_) {
      int lvt_9_1_ = MathHelper.func_76128_c(p_70495_1_);
      int lvt_10_1_ = MathHelper.func_76128_c(p_70495_3_);
      int lvt_11_1_ = MathHelper.func_76128_c(p_70495_5_);
      if (BlockRailBase.func_176562_d(this.field_70170_p, new BlockPos(lvt_9_1_, lvt_10_1_ - 1, lvt_11_1_))) {
         --lvt_10_1_;
      }

      IBlockState lvt_12_1_ = this.field_70170_p.func_180495_p(new BlockPos(lvt_9_1_, lvt_10_1_, lvt_11_1_));
      if (BlockRailBase.func_176563_d(lvt_12_1_)) {
         BlockRailBase.EnumRailDirection lvt_13_1_ = (BlockRailBase.EnumRailDirection)lvt_12_1_.func_177229_b(((BlockRailBase)lvt_12_1_.func_177230_c()).func_176560_l());
         p_70495_3_ = (double)lvt_10_1_;
         if (lvt_13_1_.func_177018_c()) {
            p_70495_3_ = (double)(lvt_10_1_ + 1);
         }

         int[][] lvt_14_1_ = field_70500_g[lvt_13_1_.func_177015_a()];
         double lvt_15_1_ = (double)(lvt_14_1_[1][0] - lvt_14_1_[0][0]);
         double lvt_17_1_ = (double)(lvt_14_1_[1][2] - lvt_14_1_[0][2]);
         double lvt_19_1_ = Math.sqrt(lvt_15_1_ * lvt_15_1_ + lvt_17_1_ * lvt_17_1_);
         lvt_15_1_ /= lvt_19_1_;
         lvt_17_1_ /= lvt_19_1_;
         p_70495_1_ += lvt_15_1_ * p_70495_7_;
         p_70495_5_ += lvt_17_1_ * p_70495_7_;
         if (lvt_14_1_[0][1] != 0 && MathHelper.func_76128_c(p_70495_1_) - lvt_9_1_ == lvt_14_1_[0][0] && MathHelper.func_76128_c(p_70495_5_) - lvt_11_1_ == lvt_14_1_[0][2]) {
            p_70495_3_ += (double)lvt_14_1_[0][1];
         } else if (lvt_14_1_[1][1] != 0 && MathHelper.func_76128_c(p_70495_1_) - lvt_9_1_ == lvt_14_1_[1][0] && MathHelper.func_76128_c(p_70495_5_) - lvt_11_1_ == lvt_14_1_[1][2]) {
            p_70495_3_ += (double)lvt_14_1_[1][1];
         }

         return this.func_70489_a(p_70495_1_, p_70495_3_, p_70495_5_);
      } else {
         return null;
      }
   }

   public Vec3 func_70489_a(double p_70489_1_, double p_70489_3_, double p_70489_5_) {
      int lvt_7_1_ = MathHelper.func_76128_c(p_70489_1_);
      int lvt_8_1_ = MathHelper.func_76128_c(p_70489_3_);
      int lvt_9_1_ = MathHelper.func_76128_c(p_70489_5_);
      if (BlockRailBase.func_176562_d(this.field_70170_p, new BlockPos(lvt_7_1_, lvt_8_1_ - 1, lvt_9_1_))) {
         --lvt_8_1_;
      }

      IBlockState lvt_10_1_ = this.field_70170_p.func_180495_p(new BlockPos(lvt_7_1_, lvt_8_1_, lvt_9_1_));
      if (BlockRailBase.func_176563_d(lvt_10_1_)) {
         BlockRailBase.EnumRailDirection lvt_11_1_ = (BlockRailBase.EnumRailDirection)lvt_10_1_.func_177229_b(((BlockRailBase)lvt_10_1_.func_177230_c()).func_176560_l());
         int[][] lvt_12_1_ = field_70500_g[lvt_11_1_.func_177015_a()];
         double lvt_13_1_ = 0.0D;
         double lvt_15_1_ = (double)lvt_7_1_ + 0.5D + (double)lvt_12_1_[0][0] * 0.5D;
         double lvt_17_1_ = (double)lvt_8_1_ + 0.0625D + (double)lvt_12_1_[0][1] * 0.5D;
         double lvt_19_1_ = (double)lvt_9_1_ + 0.5D + (double)lvt_12_1_[0][2] * 0.5D;
         double lvt_21_1_ = (double)lvt_7_1_ + 0.5D + (double)lvt_12_1_[1][0] * 0.5D;
         double lvt_23_1_ = (double)lvt_8_1_ + 0.0625D + (double)lvt_12_1_[1][1] * 0.5D;
         double lvt_25_1_ = (double)lvt_9_1_ + 0.5D + (double)lvt_12_1_[1][2] * 0.5D;
         double lvt_27_1_ = lvt_21_1_ - lvt_15_1_;
         double lvt_29_1_ = (lvt_23_1_ - lvt_17_1_) * 2.0D;
         double lvt_31_1_ = lvt_25_1_ - lvt_19_1_;
         if (lvt_27_1_ == 0.0D) {
            p_70489_1_ = (double)lvt_7_1_ + 0.5D;
            lvt_13_1_ = p_70489_5_ - (double)lvt_9_1_;
         } else if (lvt_31_1_ == 0.0D) {
            p_70489_5_ = (double)lvt_9_1_ + 0.5D;
            lvt_13_1_ = p_70489_1_ - (double)lvt_7_1_;
         } else {
            double lvt_33_1_ = p_70489_1_ - lvt_15_1_;
            double lvt_35_1_ = p_70489_5_ - lvt_19_1_;
            lvt_13_1_ = (lvt_33_1_ * lvt_27_1_ + lvt_35_1_ * lvt_31_1_) * 2.0D;
         }

         p_70489_1_ = lvt_15_1_ + lvt_27_1_ * lvt_13_1_;
         p_70489_3_ = lvt_17_1_ + lvt_29_1_ * lvt_13_1_;
         p_70489_5_ = lvt_19_1_ + lvt_31_1_ * lvt_13_1_;
         if (lvt_29_1_ < 0.0D) {
            ++p_70489_3_;
         }

         if (lvt_29_1_ > 0.0D) {
            p_70489_3_ += 0.5D;
         }

         return new Vec3(p_70489_1_, p_70489_3_, p_70489_5_);
      } else {
         return null;
      }
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      if (p_70037_1_.func_74767_n("CustomDisplayTile")) {
         int lvt_2_1_ = p_70037_1_.func_74762_e("DisplayData");
         Block lvt_3_1_;
         if (p_70037_1_.func_150297_b("DisplayTile", 8)) {
            lvt_3_1_ = Block.func_149684_b(p_70037_1_.func_74779_i("DisplayTile"));
            if (lvt_3_1_ == null) {
               this.func_174899_a(Blocks.field_150350_a.func_176223_P());
            } else {
               this.func_174899_a(lvt_3_1_.func_176203_a(lvt_2_1_));
            }
         } else {
            lvt_3_1_ = Block.func_149729_e(p_70037_1_.func_74762_e("DisplayTile"));
            if (lvt_3_1_ == null) {
               this.func_174899_a(Blocks.field_150350_a.func_176223_P());
            } else {
               this.func_174899_a(lvt_3_1_.func_176203_a(lvt_2_1_));
            }
         }

         this.func_94086_l(p_70037_1_.func_74762_e("DisplayOffset"));
      }

      if (p_70037_1_.func_150297_b("CustomName", 8) && p_70037_1_.func_74779_i("CustomName").length() > 0) {
         this.field_94102_c = p_70037_1_.func_74779_i("CustomName");
      }

   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      if (this.func_94100_s()) {
         p_70014_1_.func_74757_a("CustomDisplayTile", true);
         IBlockState lvt_2_1_ = this.func_174897_t();
         ResourceLocation lvt_3_1_ = (ResourceLocation)Block.field_149771_c.func_177774_c(lvt_2_1_.func_177230_c());
         p_70014_1_.func_74778_a("DisplayTile", lvt_3_1_ == null ? "" : lvt_3_1_.toString());
         p_70014_1_.func_74768_a("DisplayData", lvt_2_1_.func_177230_c().func_176201_c(lvt_2_1_));
         p_70014_1_.func_74768_a("DisplayOffset", this.func_94099_q());
      }

      if (this.field_94102_c != null && this.field_94102_c.length() > 0) {
         p_70014_1_.func_74778_a("CustomName", this.field_94102_c);
      }

   }

   public void func_70108_f(Entity p_70108_1_) {
      if (!this.field_70170_p.field_72995_K) {
         if (!p_70108_1_.field_70145_X && !this.field_70145_X) {
            if (p_70108_1_ != this.field_70153_n) {
               if (p_70108_1_ instanceof EntityLivingBase && !(p_70108_1_ instanceof EntityPlayer) && !(p_70108_1_ instanceof EntityIronGolem) && this.func_180456_s() == EntityMinecart.EnumMinecartType.RIDEABLE && this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.01D && this.field_70153_n == null && p_70108_1_.field_70154_o == null) {
                  p_70108_1_.func_70078_a(this);
               }

               double lvt_2_1_ = p_70108_1_.field_70165_t - this.field_70165_t;
               double lvt_4_1_ = p_70108_1_.field_70161_v - this.field_70161_v;
               double lvt_6_1_ = lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_;
               if (lvt_6_1_ >= 9.999999747378752E-5D) {
                  lvt_6_1_ = (double)MathHelper.func_76133_a(lvt_6_1_);
                  lvt_2_1_ /= lvt_6_1_;
                  lvt_4_1_ /= lvt_6_1_;
                  double lvt_8_1_ = 1.0D / lvt_6_1_;
                  if (lvt_8_1_ > 1.0D) {
                     lvt_8_1_ = 1.0D;
                  }

                  lvt_2_1_ *= lvt_8_1_;
                  lvt_4_1_ *= lvt_8_1_;
                  lvt_2_1_ *= 0.10000000149011612D;
                  lvt_4_1_ *= 0.10000000149011612D;
                  lvt_2_1_ *= (double)(1.0F - this.field_70144_Y);
                  lvt_4_1_ *= (double)(1.0F - this.field_70144_Y);
                  lvt_2_1_ *= 0.5D;
                  lvt_4_1_ *= 0.5D;
                  if (p_70108_1_ instanceof EntityMinecart) {
                     double lvt_10_1_ = p_70108_1_.field_70165_t - this.field_70165_t;
                     double lvt_12_1_ = p_70108_1_.field_70161_v - this.field_70161_v;
                     Vec3 lvt_14_1_ = (new Vec3(lvt_10_1_, 0.0D, lvt_12_1_)).func_72432_b();
                     Vec3 lvt_15_1_ = (new Vec3((double)MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F), 0.0D, (double)MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F))).func_72432_b();
                     double lvt_16_1_ = Math.abs(lvt_14_1_.func_72430_b(lvt_15_1_));
                     if (lvt_16_1_ < 0.800000011920929D) {
                        return;
                     }

                     double lvt_18_1_ = p_70108_1_.field_70159_w + this.field_70159_w;
                     double lvt_20_1_ = p_70108_1_.field_70179_y + this.field_70179_y;
                     if (((EntityMinecart)p_70108_1_).func_180456_s() == EntityMinecart.EnumMinecartType.FURNACE && this.func_180456_s() != EntityMinecart.EnumMinecartType.FURNACE) {
                        this.field_70159_w *= 0.20000000298023224D;
                        this.field_70179_y *= 0.20000000298023224D;
                        this.func_70024_g(p_70108_1_.field_70159_w - lvt_2_1_, 0.0D, p_70108_1_.field_70179_y - lvt_4_1_);
                        p_70108_1_.field_70159_w *= 0.949999988079071D;
                        p_70108_1_.field_70179_y *= 0.949999988079071D;
                     } else if (((EntityMinecart)p_70108_1_).func_180456_s() != EntityMinecart.EnumMinecartType.FURNACE && this.func_180456_s() == EntityMinecart.EnumMinecartType.FURNACE) {
                        p_70108_1_.field_70159_w *= 0.20000000298023224D;
                        p_70108_1_.field_70179_y *= 0.20000000298023224D;
                        p_70108_1_.func_70024_g(this.field_70159_w + lvt_2_1_, 0.0D, this.field_70179_y + lvt_4_1_);
                        this.field_70159_w *= 0.949999988079071D;
                        this.field_70179_y *= 0.949999988079071D;
                     } else {
                        lvt_18_1_ /= 2.0D;
                        lvt_20_1_ /= 2.0D;
                        this.field_70159_w *= 0.20000000298023224D;
                        this.field_70179_y *= 0.20000000298023224D;
                        this.func_70024_g(lvt_18_1_ - lvt_2_1_, 0.0D, lvt_20_1_ - lvt_4_1_);
                        p_70108_1_.field_70159_w *= 0.20000000298023224D;
                        p_70108_1_.field_70179_y *= 0.20000000298023224D;
                        p_70108_1_.func_70024_g(lvt_18_1_ + lvt_2_1_, 0.0D, lvt_20_1_ + lvt_4_1_);
                     }
                  } else {
                     this.func_70024_g(-lvt_2_1_, 0.0D, -lvt_4_1_);
                     p_70108_1_.func_70024_g(lvt_2_1_ / 4.0D, 0.0D, lvt_4_1_ / 4.0D);
                  }
               }

            }
         }
      }
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.field_70511_i = p_180426_1_;
      this.field_70509_j = p_180426_3_;
      this.field_70514_an = p_180426_5_;
      this.field_70512_ao = (double)p_180426_7_;
      this.field_70513_ap = (double)p_180426_8_;
      this.field_70510_h = p_180426_9_ + 2;
      this.field_70159_w = this.field_70508_aq;
      this.field_70181_x = this.field_70507_ar;
      this.field_70179_y = this.field_70506_as;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70508_aq = this.field_70159_w = p_70016_1_;
      this.field_70507_ar = this.field_70181_x = p_70016_3_;
      this.field_70506_as = this.field_70179_y = p_70016_5_;
   }

   public void func_70492_c(float p_70492_1_) {
      this.field_70180_af.func_75692_b(19, p_70492_1_);
   }

   public float func_70491_i() {
      return this.field_70180_af.func_111145_d(19);
   }

   public void func_70497_h(int p_70497_1_) {
      this.field_70180_af.func_75692_b(17, p_70497_1_);
   }

   public int func_70496_j() {
      return this.field_70180_af.func_75679_c(17);
   }

   public void func_70494_i(int p_70494_1_) {
      this.field_70180_af.func_75692_b(18, p_70494_1_);
   }

   public int func_70493_k() {
      return this.field_70180_af.func_75679_c(18);
   }

   public abstract EntityMinecart.EnumMinecartType func_180456_s();

   public IBlockState func_174897_t() {
      return !this.func_94100_s() ? this.func_180457_u() : Block.func_176220_d(this.func_70096_w().func_75679_c(20));
   }

   public IBlockState func_180457_u() {
      return Blocks.field_150350_a.func_176223_P();
   }

   public int func_94099_q() {
      return !this.func_94100_s() ? this.func_94085_r() : this.func_70096_w().func_75679_c(21);
   }

   public int func_94085_r() {
      return 6;
   }

   public void func_174899_a(IBlockState p_174899_1_) {
      this.func_70096_w().func_75692_b(20, Block.func_176210_f(p_174899_1_));
      this.func_94096_e(true);
   }

   public void func_94086_l(int p_94086_1_) {
      this.func_70096_w().func_75692_b(21, p_94086_1_);
      this.func_94096_e(true);
   }

   public boolean func_94100_s() {
      return this.func_70096_w().func_75683_a(22) == 1;
   }

   public void func_94096_e(boolean p_94096_1_) {
      this.func_70096_w().func_75692_b(22, (byte)(p_94096_1_ ? 1 : 0));
   }

   public void func_96094_a(String p_96094_1_) {
      this.field_94102_c = p_96094_1_;
   }

   public String func_70005_c_() {
      return this.field_94102_c != null ? this.field_94102_c : super.func_70005_c_();
   }

   public boolean func_145818_k_() {
      return this.field_94102_c != null;
   }

   public String func_95999_t() {
      return this.field_94102_c;
   }

   public IChatComponent func_145748_c_() {
      if (this.func_145818_k_()) {
         ChatComponentText lvt_1_1_ = new ChatComponentText(this.field_94102_c);
         lvt_1_1_.func_150256_b().func_150209_a(this.func_174823_aP());
         lvt_1_1_.func_150256_b().func_179989_a(this.func_110124_au().toString());
         return lvt_1_1_;
      } else {
         ChatComponentTranslation lvt_1_2_ = new ChatComponentTranslation(this.func_70005_c_(), new Object[0]);
         lvt_1_2_.func_150256_b().func_150209_a(this.func_174823_aP());
         lvt_1_2_.func_150256_b().func_179989_a(this.func_110124_au().toString());
         return lvt_1_2_;
      }
   }

   public static enum EnumMinecartType {
      RIDEABLE(0, "MinecartRideable"),
      CHEST(1, "MinecartChest"),
      FURNACE(2, "MinecartFurnace"),
      TNT(3, "MinecartTNT"),
      SPAWNER(4, "MinecartSpawner"),
      HOPPER(5, "MinecartHopper"),
      COMMAND_BLOCK(6, "MinecartCommandBlock");

      private static final Map<Integer, EntityMinecart.EnumMinecartType> field_180051_h = Maps.newHashMap();
      private final int field_180052_i;
      private final String field_180049_j;

      private EnumMinecartType(int p_i45847_3_, String p_i45847_4_) {
         this.field_180052_i = p_i45847_3_;
         this.field_180049_j = p_i45847_4_;
      }

      public int func_180039_a() {
         return this.field_180052_i;
      }

      public String func_180040_b() {
         return this.field_180049_j;
      }

      public static EntityMinecart.EnumMinecartType func_180038_a(int p_180038_0_) {
         EntityMinecart.EnumMinecartType lvt_1_1_ = (EntityMinecart.EnumMinecartType)field_180051_h.get(p_180038_0_);
         return lvt_1_1_ == null ? RIDEABLE : lvt_1_1_;
      }

      static {
         EntityMinecart.EnumMinecartType[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            EntityMinecart.EnumMinecartType lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_180051_h.put(lvt_3_1_.func_180039_a(), lvt_3_1_);
         }

      }
   }
}
