package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity implements ICommandSender {
   private static final AxisAlignedBB field_174836_a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
   private static int field_70152_a;
   private int field_145783_c;
   public double field_70155_l;
   public boolean field_70156_m;
   public Entity field_70153_n;
   public Entity field_70154_o;
   public boolean field_98038_p;
   public World field_70170_p;
   public double field_70169_q;
   public double field_70167_r;
   public double field_70166_s;
   public double field_70165_t;
   public double field_70163_u;
   public double field_70161_v;
   public double field_70159_w;
   public double field_70181_x;
   public double field_70179_y;
   public float field_70177_z;
   public float field_70125_A;
   public float field_70126_B;
   public float field_70127_C;
   private AxisAlignedBB field_70121_D;
   public boolean field_70122_E;
   public boolean field_70123_F;
   public boolean field_70124_G;
   public boolean field_70132_H;
   public boolean field_70133_I;
   protected boolean field_70134_J;
   private boolean field_174835_g;
   public boolean field_70128_L;
   public float field_70130_N;
   public float field_70131_O;
   public float field_70141_P;
   public float field_70140_Q;
   public float field_82151_R;
   public float field_70143_R;
   private int field_70150_b;
   public double field_70142_S;
   public double field_70137_T;
   public double field_70136_U;
   public float field_70138_W;
   public boolean field_70145_X;
   public float field_70144_Y;
   protected Random field_70146_Z;
   public int field_70173_aa;
   public int field_70174_ab;
   private int field_70151_c;
   protected boolean field_70171_ac;
   public int field_70172_ad;
   protected boolean field_70148_d;
   protected boolean field_70178_ae;
   protected DataWatcher field_70180_af;
   private double field_70149_e;
   private double field_70147_f;
   public boolean field_70175_ag;
   public int field_70176_ah;
   public int field_70162_ai;
   public int field_70164_aj;
   public int field_70118_ct;
   public int field_70117_cu;
   public int field_70116_cv;
   public boolean field_70158_ak;
   public boolean field_70160_al;
   public int field_71088_bW;
   protected boolean field_71087_bX;
   protected int field_82153_h;
   public int field_71093_bK;
   protected BlockPos field_181016_an;
   protected Vec3 field_181017_ao;
   protected EnumFacing field_181018_ap;
   private boolean field_83001_bt;
   protected UUID field_96093_i;
   private final CommandResultStats field_174837_as;

   public int func_145782_y() {
      return this.field_145783_c;
   }

   public void func_145769_d(int p_145769_1_) {
      this.field_145783_c = p_145769_1_;
   }

   public void func_174812_G() {
      this.func_70106_y();
   }

   public Entity(World p_i1582_1_) {
      this.field_145783_c = field_70152_a++;
      this.field_70155_l = 1.0D;
      this.field_70121_D = field_174836_a;
      this.field_70130_N = 0.6F;
      this.field_70131_O = 1.8F;
      this.field_70150_b = 1;
      this.field_70146_Z = new Random();
      this.field_70174_ab = 1;
      this.field_70148_d = true;
      this.field_96093_i = MathHelper.func_180182_a(this.field_70146_Z);
      this.field_174837_as = new CommandResultStats();
      this.field_70170_p = p_i1582_1_;
      this.func_70107_b(0.0D, 0.0D, 0.0D);
      if (p_i1582_1_ != null) {
         this.field_71093_bK = p_i1582_1_.field_73011_w.func_177502_q();
      }

      this.field_70180_af = new DataWatcher(this);
      this.field_70180_af.func_75682_a(0, (byte)0);
      this.field_70180_af.func_75682_a(1, (short)300);
      this.field_70180_af.func_75682_a(3, (byte)0);
      this.field_70180_af.func_75682_a(2, "");
      this.field_70180_af.func_75682_a(4, (byte)0);
      this.func_70088_a();
   }

   protected abstract void func_70088_a();

   public DataWatcher func_70096_w() {
      return this.field_70180_af;
   }

   public boolean equals(Object p_equals_1_) {
      if (p_equals_1_ instanceof Entity) {
         return ((Entity)p_equals_1_).field_145783_c == this.field_145783_c;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_145783_c;
   }

   protected void func_70065_x() {
      if (this.field_70170_p != null) {
         while(this.field_70163_u > 0.0D && this.field_70163_u < 256.0D) {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if (this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty()) {
               break;
            }

            ++this.field_70163_u;
         }

         this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
         this.field_70125_A = 0.0F;
      }
   }

   public void func_70106_y() {
      this.field_70128_L = true;
   }

   protected void func_70105_a(float p_70105_1_, float p_70105_2_) {
      if (p_70105_1_ != this.field_70130_N || p_70105_2_ != this.field_70131_O) {
         float lvt_3_1_ = this.field_70130_N;
         this.field_70130_N = p_70105_1_;
         this.field_70131_O = p_70105_2_;
         this.func_174826_a(new AxisAlignedBB(this.func_174813_aQ().field_72340_a, this.func_174813_aQ().field_72338_b, this.func_174813_aQ().field_72339_c, this.func_174813_aQ().field_72340_a + (double)this.field_70130_N, this.func_174813_aQ().field_72338_b + (double)this.field_70131_O, this.func_174813_aQ().field_72339_c + (double)this.field_70130_N));
         if (this.field_70130_N > lvt_3_1_ && !this.field_70148_d && !this.field_70170_p.field_72995_K) {
            this.func_70091_d((double)(lvt_3_1_ - this.field_70130_N), 0.0D, (double)(lvt_3_1_ - this.field_70130_N));
         }
      }

   }

   protected void func_70101_b(float p_70101_1_, float p_70101_2_) {
      this.field_70177_z = p_70101_1_ % 360.0F;
      this.field_70125_A = p_70101_2_ % 360.0F;
   }

   public void func_70107_b(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
      this.field_70165_t = p_70107_1_;
      this.field_70163_u = p_70107_3_;
      this.field_70161_v = p_70107_5_;
      float lvt_7_1_ = this.field_70130_N / 2.0F;
      float lvt_8_1_ = this.field_70131_O;
      this.func_174826_a(new AxisAlignedBB(p_70107_1_ - (double)lvt_7_1_, p_70107_3_, p_70107_5_ - (double)lvt_7_1_, p_70107_1_ + (double)lvt_7_1_, p_70107_3_ + (double)lvt_8_1_, p_70107_5_ + (double)lvt_7_1_));
   }

   public void func_70082_c(float p_70082_1_, float p_70082_2_) {
      float lvt_3_1_ = this.field_70125_A;
      float lvt_4_1_ = this.field_70177_z;
      this.field_70177_z = (float)((double)this.field_70177_z + (double)p_70082_1_ * 0.15D);
      this.field_70125_A = (float)((double)this.field_70125_A - (double)p_70082_2_ * 0.15D);
      this.field_70125_A = MathHelper.func_76131_a(this.field_70125_A, -90.0F, 90.0F);
      this.field_70127_C += this.field_70125_A - lvt_3_1_;
      this.field_70126_B += this.field_70177_z - lvt_4_1_;
   }

   public void func_70071_h_() {
      this.func_70030_z();
   }

   public void func_70030_z() {
      this.field_70170_p.field_72984_F.func_76320_a("entityBaseTick");
      if (this.field_70154_o != null && this.field_70154_o.field_70128_L) {
         this.field_70154_o = null;
      }

      this.field_70141_P = this.field_70140_Q;
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.field_70127_C = this.field_70125_A;
      this.field_70126_B = this.field_70177_z;
      if (!this.field_70170_p.field_72995_K && this.field_70170_p instanceof WorldServer) {
         this.field_70170_p.field_72984_F.func_76320_a("portal");
         MinecraftServer lvt_1_1_ = ((WorldServer)this.field_70170_p).func_73046_m();
         int lvt_2_1_ = this.func_82145_z();
         if (this.field_71087_bX) {
            if (lvt_1_1_.func_71255_r()) {
               if (this.field_70154_o == null && this.field_82153_h++ >= lvt_2_1_) {
                  this.field_82153_h = lvt_2_1_;
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

      this.func_174830_Y();
      this.func_70072_I();
      if (this.field_70170_p.field_72995_K) {
         this.field_70151_c = 0;
      } else if (this.field_70151_c > 0) {
         if (this.field_70178_ae) {
            this.field_70151_c -= 4;
            if (this.field_70151_c < 0) {
               this.field_70151_c = 0;
            }
         } else {
            if (this.field_70151_c % 20 == 0) {
               this.func_70097_a(DamageSource.field_76370_b, 1.0F);
            }

            --this.field_70151_c;
         }
      }

      if (this.func_180799_ab()) {
         this.func_70044_A();
         this.field_70143_R *= 0.5F;
      }

      if (this.field_70163_u < -64.0D) {
         this.func_70076_C();
      }

      if (!this.field_70170_p.field_72995_K) {
         this.func_70052_a(0, this.field_70151_c > 0);
      }

      this.field_70148_d = false;
      this.field_70170_p.field_72984_F.func_76319_b();
   }

   public int func_82145_z() {
      return 0;
   }

   protected void func_70044_A() {
      if (!this.field_70178_ae) {
         this.func_70097_a(DamageSource.field_76371_c, 4.0F);
         this.func_70015_d(15);
      }
   }

   public void func_70015_d(int p_70015_1_) {
      int lvt_2_1_ = p_70015_1_ * 20;
      lvt_2_1_ = EnchantmentProtection.func_92093_a(this, lvt_2_1_);
      if (this.field_70151_c < lvt_2_1_) {
         this.field_70151_c = lvt_2_1_;
      }

   }

   public void func_70066_B() {
      this.field_70151_c = 0;
   }

   protected void func_70076_C() {
      this.func_70106_y();
   }

   public boolean func_70038_c(double p_70038_1_, double p_70038_3_, double p_70038_5_) {
      AxisAlignedBB lvt_7_1_ = this.func_174813_aQ().func_72317_d(p_70038_1_, p_70038_3_, p_70038_5_);
      return this.func_174809_b(lvt_7_1_);
   }

   private boolean func_174809_b(AxisAlignedBB p_174809_1_) {
      return this.field_70170_p.func_72945_a(this, p_174809_1_).isEmpty() && !this.field_70170_p.func_72953_d(p_174809_1_);
   }

   public void func_70091_d(double p_70091_1_, double p_70091_3_, double p_70091_5_) {
      if (this.field_70145_X) {
         this.func_174826_a(this.func_174813_aQ().func_72317_d(p_70091_1_, p_70091_3_, p_70091_5_));
         this.func_174829_m();
      } else {
         this.field_70170_p.field_72984_F.func_76320_a("move");
         double lvt_7_1_ = this.field_70165_t;
         double lvt_9_1_ = this.field_70163_u;
         double lvt_11_1_ = this.field_70161_v;
         if (this.field_70134_J) {
            this.field_70134_J = false;
            p_70091_1_ *= 0.25D;
            p_70091_3_ *= 0.05000000074505806D;
            p_70091_5_ *= 0.25D;
            this.field_70159_w = 0.0D;
            this.field_70181_x = 0.0D;
            this.field_70179_y = 0.0D;
         }

         double lvt_13_1_ = p_70091_1_;
         double lvt_15_1_ = p_70091_3_;
         double lvt_17_1_ = p_70091_5_;
         boolean lvt_19_1_ = this.field_70122_E && this.func_70093_af() && this instanceof EntityPlayer;
         if (lvt_19_1_) {
            double lvt_20_1_;
            for(lvt_20_1_ = 0.05D; p_70091_1_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(p_70091_1_, -1.0D, 0.0D)).isEmpty(); lvt_13_1_ = p_70091_1_) {
               if (p_70091_1_ < lvt_20_1_ && p_70091_1_ >= -lvt_20_1_) {
                  p_70091_1_ = 0.0D;
               } else if (p_70091_1_ > 0.0D) {
                  p_70091_1_ -= lvt_20_1_;
               } else {
                  p_70091_1_ += lvt_20_1_;
               }
            }

            for(; p_70091_5_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(0.0D, -1.0D, p_70091_5_)).isEmpty(); lvt_17_1_ = p_70091_5_) {
               if (p_70091_5_ < lvt_20_1_ && p_70091_5_ >= -lvt_20_1_) {
                  p_70091_5_ = 0.0D;
               } else if (p_70091_5_ > 0.0D) {
                  p_70091_5_ -= lvt_20_1_;
               } else {
                  p_70091_5_ += lvt_20_1_;
               }
            }

            for(; p_70091_1_ != 0.0D && p_70091_5_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(p_70091_1_, -1.0D, p_70091_5_)).isEmpty(); lvt_17_1_ = p_70091_5_) {
               if (p_70091_1_ < lvt_20_1_ && p_70091_1_ >= -lvt_20_1_) {
                  p_70091_1_ = 0.0D;
               } else if (p_70091_1_ > 0.0D) {
                  p_70091_1_ -= lvt_20_1_;
               } else {
                  p_70091_1_ += lvt_20_1_;
               }

               lvt_13_1_ = p_70091_1_;
               if (p_70091_5_ < lvt_20_1_ && p_70091_5_ >= -lvt_20_1_) {
                  p_70091_5_ = 0.0D;
               } else if (p_70091_5_ > 0.0D) {
                  p_70091_5_ -= lvt_20_1_;
               } else {
                  p_70091_5_ += lvt_20_1_;
               }
            }
         }

         List<AxisAlignedBB> lvt_20_2_ = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72321_a(p_70091_1_, p_70091_3_, p_70091_5_));
         AxisAlignedBB lvt_21_1_ = this.func_174813_aQ();

         AxisAlignedBB lvt_23_1_;
         for(Iterator lvt_22_1_ = lvt_20_2_.iterator(); lvt_22_1_.hasNext(); p_70091_3_ = lvt_23_1_.func_72323_b(this.func_174813_aQ(), p_70091_3_)) {
            lvt_23_1_ = (AxisAlignedBB)lvt_22_1_.next();
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, p_70091_3_, 0.0D));
         boolean lvt_22_2_ = this.field_70122_E || lvt_15_1_ != p_70091_3_ && lvt_15_1_ < 0.0D;

         AxisAlignedBB lvt_24_2_;
         Iterator lvt_23_3_;
         for(lvt_23_3_ = lvt_20_2_.iterator(); lvt_23_3_.hasNext(); p_70091_1_ = lvt_24_2_.func_72316_a(this.func_174813_aQ(), p_70091_1_)) {
            lvt_24_2_ = (AxisAlignedBB)lvt_23_3_.next();
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(p_70091_1_, 0.0D, 0.0D));

         for(lvt_23_3_ = lvt_20_2_.iterator(); lvt_23_3_.hasNext(); p_70091_5_ = lvt_24_2_.func_72322_c(this.func_174813_aQ(), p_70091_5_)) {
            lvt_24_2_ = (AxisAlignedBB)lvt_23_3_.next();
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, 0.0D, p_70091_5_));
         if (this.field_70138_W > 0.0F && lvt_22_2_ && (lvt_13_1_ != p_70091_1_ || lvt_17_1_ != p_70091_5_)) {
            double lvt_23_4_ = p_70091_1_;
            double lvt_25_1_ = p_70091_3_;
            double lvt_27_1_ = p_70091_5_;
            AxisAlignedBB lvt_29_1_ = this.func_174813_aQ();
            this.func_174826_a(lvt_21_1_);
            p_70091_3_ = (double)this.field_70138_W;
            List<AxisAlignedBB> lvt_30_1_ = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72321_a(lvt_13_1_, p_70091_3_, lvt_17_1_));
            AxisAlignedBB lvt_31_1_ = this.func_174813_aQ();
            AxisAlignedBB lvt_32_1_ = lvt_31_1_.func_72321_a(lvt_13_1_, 0.0D, lvt_17_1_);
            double lvt_33_1_ = p_70091_3_;

            AxisAlignedBB lvt_36_1_;
            for(Iterator lvt_35_1_ = lvt_30_1_.iterator(); lvt_35_1_.hasNext(); lvt_33_1_ = lvt_36_1_.func_72323_b(lvt_32_1_, lvt_33_1_)) {
               lvt_36_1_ = (AxisAlignedBB)lvt_35_1_.next();
            }

            lvt_31_1_ = lvt_31_1_.func_72317_d(0.0D, lvt_33_1_, 0.0D);
            double lvt_35_2_ = lvt_13_1_;

            AxisAlignedBB lvt_38_1_;
            for(Iterator lvt_37_1_ = lvt_30_1_.iterator(); lvt_37_1_.hasNext(); lvt_35_2_ = lvt_38_1_.func_72316_a(lvt_31_1_, lvt_35_2_)) {
               lvt_38_1_ = (AxisAlignedBB)lvt_37_1_.next();
            }

            lvt_31_1_ = lvt_31_1_.func_72317_d(lvt_35_2_, 0.0D, 0.0D);
            double lvt_37_2_ = lvt_17_1_;

            AxisAlignedBB lvt_40_1_;
            for(Iterator lvt_39_1_ = lvt_30_1_.iterator(); lvt_39_1_.hasNext(); lvt_37_2_ = lvt_40_1_.func_72322_c(lvt_31_1_, lvt_37_2_)) {
               lvt_40_1_ = (AxisAlignedBB)lvt_39_1_.next();
            }

            lvt_31_1_ = lvt_31_1_.func_72317_d(0.0D, 0.0D, lvt_37_2_);
            AxisAlignedBB lvt_39_2_ = this.func_174813_aQ();
            double lvt_40_2_ = p_70091_3_;

            AxisAlignedBB lvt_43_1_;
            for(Iterator lvt_42_1_ = lvt_30_1_.iterator(); lvt_42_1_.hasNext(); lvt_40_2_ = lvt_43_1_.func_72323_b(lvt_39_2_, lvt_40_2_)) {
               lvt_43_1_ = (AxisAlignedBB)lvt_42_1_.next();
            }

            lvt_39_2_ = lvt_39_2_.func_72317_d(0.0D, lvt_40_2_, 0.0D);
            double lvt_42_2_ = lvt_13_1_;

            AxisAlignedBB lvt_45_1_;
            for(Iterator lvt_44_1_ = lvt_30_1_.iterator(); lvt_44_1_.hasNext(); lvt_42_2_ = lvt_45_1_.func_72316_a(lvt_39_2_, lvt_42_2_)) {
               lvt_45_1_ = (AxisAlignedBB)lvt_44_1_.next();
            }

            lvt_39_2_ = lvt_39_2_.func_72317_d(lvt_42_2_, 0.0D, 0.0D);
            double lvt_44_2_ = lvt_17_1_;

            AxisAlignedBB lvt_47_1_;
            for(Iterator lvt_46_1_ = lvt_30_1_.iterator(); lvt_46_1_.hasNext(); lvt_44_2_ = lvt_47_1_.func_72322_c(lvt_39_2_, lvt_44_2_)) {
               lvt_47_1_ = (AxisAlignedBB)lvt_46_1_.next();
            }

            lvt_39_2_ = lvt_39_2_.func_72317_d(0.0D, 0.0D, lvt_44_2_);
            double lvt_46_2_ = lvt_35_2_ * lvt_35_2_ + lvt_37_2_ * lvt_37_2_;
            double lvt_48_1_ = lvt_42_2_ * lvt_42_2_ + lvt_44_2_ * lvt_44_2_;
            if (lvt_46_2_ > lvt_48_1_) {
               p_70091_1_ = lvt_35_2_;
               p_70091_5_ = lvt_37_2_;
               p_70091_3_ = -lvt_33_1_;
               this.func_174826_a(lvt_31_1_);
            } else {
               p_70091_1_ = lvt_42_2_;
               p_70091_5_ = lvt_44_2_;
               p_70091_3_ = -lvt_40_2_;
               this.func_174826_a(lvt_39_2_);
            }

            AxisAlignedBB lvt_51_1_;
            for(Iterator lvt_50_1_ = lvt_30_1_.iterator(); lvt_50_1_.hasNext(); p_70091_3_ = lvt_51_1_.func_72323_b(this.func_174813_aQ(), p_70091_3_)) {
               lvt_51_1_ = (AxisAlignedBB)lvt_50_1_.next();
            }

            this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, p_70091_3_, 0.0D));
            if (lvt_23_4_ * lvt_23_4_ + lvt_27_1_ * lvt_27_1_ >= p_70091_1_ * p_70091_1_ + p_70091_5_ * p_70091_5_) {
               p_70091_1_ = lvt_23_4_;
               p_70091_3_ = lvt_25_1_;
               p_70091_5_ = lvt_27_1_;
               this.func_174826_a(lvt_29_1_);
            }
         }

         this.field_70170_p.field_72984_F.func_76319_b();
         this.field_70170_p.field_72984_F.func_76320_a("rest");
         this.func_174829_m();
         this.field_70123_F = lvt_13_1_ != p_70091_1_ || lvt_17_1_ != p_70091_5_;
         this.field_70124_G = lvt_15_1_ != p_70091_3_;
         this.field_70122_E = this.field_70124_G && lvt_15_1_ < 0.0D;
         this.field_70132_H = this.field_70123_F || this.field_70124_G;
         int lvt_23_5_ = MathHelper.func_76128_c(this.field_70165_t);
         int lvt_24_3_ = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
         int lvt_25_2_ = MathHelper.func_76128_c(this.field_70161_v);
         BlockPos lvt_26_1_ = new BlockPos(lvt_23_5_, lvt_24_3_, lvt_25_2_);
         Block lvt_27_2_ = this.field_70170_p.func_180495_p(lvt_26_1_).func_177230_c();
         if (lvt_27_2_.func_149688_o() == Material.field_151579_a) {
            Block lvt_28_1_ = this.field_70170_p.func_180495_p(lvt_26_1_.func_177977_b()).func_177230_c();
            if (lvt_28_1_ instanceof BlockFence || lvt_28_1_ instanceof BlockWall || lvt_28_1_ instanceof BlockFenceGate) {
               lvt_27_2_ = lvt_28_1_;
               lvt_26_1_ = lvt_26_1_.func_177977_b();
            }
         }

         this.func_180433_a(p_70091_3_, this.field_70122_E, lvt_27_2_, lvt_26_1_);
         if (lvt_13_1_ != p_70091_1_) {
            this.field_70159_w = 0.0D;
         }

         if (lvt_17_1_ != p_70091_5_) {
            this.field_70179_y = 0.0D;
         }

         if (lvt_15_1_ != p_70091_3_) {
            lvt_27_2_.func_176216_a(this.field_70170_p, this);
         }

         if (this.func_70041_e_() && !lvt_19_1_ && this.field_70154_o == null) {
            double lvt_28_2_ = this.field_70165_t - lvt_7_1_;
            double lvt_30_2_ = this.field_70163_u - lvt_9_1_;
            double lvt_32_2_ = this.field_70161_v - lvt_11_1_;
            if (lvt_27_2_ != Blocks.field_150468_ap) {
               lvt_30_2_ = 0.0D;
            }

            if (lvt_27_2_ != null && this.field_70122_E) {
               lvt_27_2_.func_176199_a(this.field_70170_p, lvt_26_1_, this);
            }

            this.field_70140_Q = (float)((double)this.field_70140_Q + (double)MathHelper.func_76133_a(lvt_28_2_ * lvt_28_2_ + lvt_32_2_ * lvt_32_2_) * 0.6D);
            this.field_82151_R = (float)((double)this.field_82151_R + (double)MathHelper.func_76133_a(lvt_28_2_ * lvt_28_2_ + lvt_30_2_ * lvt_30_2_ + lvt_32_2_ * lvt_32_2_) * 0.6D);
            if (this.field_82151_R > (float)this.field_70150_b && lvt_27_2_.func_149688_o() != Material.field_151579_a) {
               this.field_70150_b = (int)this.field_82151_R + 1;
               if (this.func_70090_H()) {
                  float lvt_34_1_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w * 0.20000000298023224D + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y * 0.20000000298023224D) * 0.35F;
                  if (lvt_34_1_ > 1.0F) {
                     lvt_34_1_ = 1.0F;
                  }

                  this.func_85030_a(this.func_145776_H(), lvt_34_1_, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
               }

               this.func_180429_a(lvt_26_1_, lvt_27_2_);
            }
         }

         try {
            this.func_145775_I();
         } catch (Throwable var52) {
            CrashReport lvt_29_2_ = CrashReport.func_85055_a(var52, "Checking entity block collision");
            CrashReportCategory lvt_30_3_ = lvt_29_2_.func_85058_a("Entity being checked for collision");
            this.func_85029_a(lvt_30_3_);
            throw new ReportedException(lvt_29_2_);
         }

         boolean lvt_28_4_ = this.func_70026_G();
         if (this.field_70170_p.func_147470_e(this.func_174813_aQ().func_72331_e(0.001D, 0.001D, 0.001D))) {
            this.func_70081_e(1);
            if (!lvt_28_4_) {
               ++this.field_70151_c;
               if (this.field_70151_c == 0) {
                  this.func_70015_d(8);
               }
            }
         } else if (this.field_70151_c <= 0) {
            this.field_70151_c = -this.field_70174_ab;
         }

         if (lvt_28_4_ && this.field_70151_c > 0) {
            this.func_85030_a("random.fizz", 0.7F, 1.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
            this.field_70151_c = -this.field_70174_ab;
         }

         this.field_70170_p.field_72984_F.func_76319_b();
      }
   }

   private void func_174829_m() {
      this.field_70165_t = (this.func_174813_aQ().field_72340_a + this.func_174813_aQ().field_72336_d) / 2.0D;
      this.field_70163_u = this.func_174813_aQ().field_72338_b;
      this.field_70161_v = (this.func_174813_aQ().field_72339_c + this.func_174813_aQ().field_72334_f) / 2.0D;
   }

   protected String func_145776_H() {
      return "game.neutral.swim";
   }

   protected void func_145775_I() {
      BlockPos lvt_1_1_ = new BlockPos(this.func_174813_aQ().field_72340_a + 0.001D, this.func_174813_aQ().field_72338_b + 0.001D, this.func_174813_aQ().field_72339_c + 0.001D);
      BlockPos lvt_2_1_ = new BlockPos(this.func_174813_aQ().field_72336_d - 0.001D, this.func_174813_aQ().field_72337_e - 0.001D, this.func_174813_aQ().field_72334_f - 0.001D);
      if (this.field_70170_p.func_175707_a(lvt_1_1_, lvt_2_1_)) {
         for(int lvt_3_1_ = lvt_1_1_.func_177958_n(); lvt_3_1_ <= lvt_2_1_.func_177958_n(); ++lvt_3_1_) {
            for(int lvt_4_1_ = lvt_1_1_.func_177956_o(); lvt_4_1_ <= lvt_2_1_.func_177956_o(); ++lvt_4_1_) {
               for(int lvt_5_1_ = lvt_1_1_.func_177952_p(); lvt_5_1_ <= lvt_2_1_.func_177952_p(); ++lvt_5_1_) {
                  BlockPos lvt_6_1_ = new BlockPos(lvt_3_1_, lvt_4_1_, lvt_5_1_);
                  IBlockState lvt_7_1_ = this.field_70170_p.func_180495_p(lvt_6_1_);

                  try {
                     lvt_7_1_.func_177230_c().func_180634_a(this.field_70170_p, lvt_6_1_, lvt_7_1_, this);
                  } catch (Throwable var11) {
                     CrashReport lvt_9_1_ = CrashReport.func_85055_a(var11, "Colliding entity with block");
                     CrashReportCategory lvt_10_1_ = lvt_9_1_.func_85058_a("Block being collided with");
                     CrashReportCategory.func_175750_a(lvt_10_1_, lvt_6_1_, lvt_7_1_);
                     throw new ReportedException(lvt_9_1_);
                  }
               }
            }
         }
      }

   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      Block.SoundType lvt_3_1_ = p_180429_2_.field_149762_H;
      if (this.field_70170_p.func_180495_p(p_180429_1_.func_177984_a()).func_177230_c() == Blocks.field_150431_aC) {
         lvt_3_1_ = Blocks.field_150431_aC.field_149762_H;
         this.func_85030_a(lvt_3_1_.func_150498_e(), lvt_3_1_.func_150497_c() * 0.15F, lvt_3_1_.func_150494_d());
      } else if (!p_180429_2_.func_149688_o().func_76224_d()) {
         this.func_85030_a(lvt_3_1_.func_150498_e(), lvt_3_1_.func_150497_c() * 0.15F, lvt_3_1_.func_150494_d());
      }

   }

   public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_) {
      if (!this.func_174814_R()) {
         this.field_70170_p.func_72956_a(this, p_85030_1_, p_85030_2_, p_85030_3_);
      }

   }

   public boolean func_174814_R() {
      return this.field_70180_af.func_75683_a(4) == 1;
   }

   public void func_174810_b(boolean p_174810_1_) {
      this.field_70180_af.func_75692_b(4, Byte.valueOf((byte)(p_174810_1_ ? 1 : 0)));
   }

   protected boolean func_70041_e_() {
      return true;
   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
      if (p_180433_3_) {
         if (this.field_70143_R > 0.0F) {
            if (p_180433_4_ != null) {
               p_180433_4_.func_180658_a(this.field_70170_p, p_180433_5_, this, this.field_70143_R);
            } else {
               this.func_180430_e(this.field_70143_R, 1.0F);
            }

            this.field_70143_R = 0.0F;
         }
      } else if (p_180433_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_180433_1_);
      }

   }

   public AxisAlignedBB func_70046_E() {
      return null;
   }

   protected void func_70081_e(int p_70081_1_) {
      if (!this.field_70178_ae) {
         this.func_70097_a(DamageSource.field_76372_a, (float)p_70081_1_);
      }

   }

   public final boolean func_70045_F() {
      return this.field_70178_ae;
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      if (this.field_70153_n != null) {
         this.field_70153_n.func_180430_e(p_180430_1_, p_180430_2_);
      }

   }

   public boolean func_70026_G() {
      return this.field_70171_ac || this.field_70170_p.func_175727_C(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v)) || this.field_70170_p.func_175727_C(new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.field_70131_O, this.field_70161_v));
   }

   public boolean func_70090_H() {
      return this.field_70171_ac;
   }

   public boolean func_70072_I() {
      if (this.field_70170_p.func_72918_a(this.func_174813_aQ().func_72314_b(0.0D, -0.4000000059604645D, 0.0D).func_72331_e(0.001D, 0.001D, 0.001D), Material.field_151586_h, this)) {
         if (!this.field_70171_ac && !this.field_70148_d) {
            this.func_71061_d_();
         }

         this.field_70143_R = 0.0F;
         this.field_70171_ac = true;
         this.field_70151_c = 0;
      } else {
         this.field_70171_ac = false;
      }

      return this.field_70171_ac;
   }

   protected void func_71061_d_() {
      float lvt_1_1_ = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w * 0.20000000298023224D + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y * 0.20000000298023224D) * 0.2F;
      if (lvt_1_1_ > 1.0F) {
         lvt_1_1_ = 1.0F;
      }

      this.func_85030_a(this.func_145777_O(), lvt_1_1_, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
      float lvt_2_1_ = (float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b);

      int lvt_3_2_;
      float lvt_4_2_;
      float lvt_5_2_;
      for(lvt_3_2_ = 0; (float)lvt_3_2_ < 1.0F + this.field_70130_N * 20.0F; ++lvt_3_2_) {
         lvt_4_2_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         lvt_5_2_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t + (double)lvt_4_2_, (double)(lvt_2_1_ + 1.0F), this.field_70161_v + (double)lvt_5_2_, this.field_70159_w, this.field_70181_x - (double)(this.field_70146_Z.nextFloat() * 0.2F), this.field_70179_y);
      }

      for(lvt_3_2_ = 0; (float)lvt_3_2_ < 1.0F + this.field_70130_N * 20.0F; ++lvt_3_2_) {
         lvt_4_2_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         lvt_5_2_ = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, this.field_70165_t + (double)lvt_4_2_, (double)(lvt_2_1_ + 1.0F), this.field_70161_v + (double)lvt_5_2_, this.field_70159_w, this.field_70181_x, this.field_70179_y);
      }

   }

   public void func_174830_Y() {
      if (this.func_70051_ag() && !this.func_70090_H()) {
         this.func_174808_Z();
      }

   }

   protected void func_174808_Z() {
      int lvt_1_1_ = MathHelper.func_76128_c(this.field_70165_t);
      int lvt_2_1_ = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
      int lvt_3_1_ = MathHelper.func_76128_c(this.field_70161_v);
      BlockPos lvt_4_1_ = new BlockPos(lvt_1_1_, lvt_2_1_, lvt_3_1_);
      IBlockState lvt_5_1_ = this.field_70170_p.func_180495_p(lvt_4_1_);
      Block lvt_6_1_ = lvt_5_1_.func_177230_c();
      if (lvt_6_1_.func_149645_b() != -1) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, this.func_174813_aQ().field_72338_b + 0.1D, this.field_70161_v + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, -this.field_70159_w * 4.0D, 1.5D, -this.field_70179_y * 4.0D, Block.func_176210_f(lvt_5_1_));
      }

   }

   protected String func_145777_O() {
      return "game.neutral.swim.splash";
   }

   public boolean func_70055_a(Material p_70055_1_) {
      double lvt_2_1_ = this.field_70163_u + (double)this.func_70047_e();
      BlockPos lvt_4_1_ = new BlockPos(this.field_70165_t, lvt_2_1_, this.field_70161_v);
      IBlockState lvt_5_1_ = this.field_70170_p.func_180495_p(lvt_4_1_);
      Block lvt_6_1_ = lvt_5_1_.func_177230_c();
      if (lvt_6_1_.func_149688_o() == p_70055_1_) {
         float lvt_7_1_ = BlockLiquid.func_149801_b(lvt_5_1_.func_177230_c().func_176201_c(lvt_5_1_)) - 0.11111111F;
         float lvt_8_1_ = (float)(lvt_4_1_.func_177956_o() + 1) - lvt_7_1_;
         boolean lvt_9_1_ = lvt_2_1_ < (double)lvt_8_1_;
         return !lvt_9_1_ && this instanceof EntityPlayer ? false : lvt_9_1_;
      } else {
         return false;
      }
   }

   public boolean func_180799_ab() {
      return this.field_70170_p.func_72875_a(this.func_174813_aQ().func_72314_b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.field_151587_i);
   }

   public void func_70060_a(float p_70060_1_, float p_70060_2_, float p_70060_3_) {
      float lvt_4_1_ = p_70060_1_ * p_70060_1_ + p_70060_2_ * p_70060_2_;
      if (lvt_4_1_ >= 1.0E-4F) {
         lvt_4_1_ = MathHelper.func_76129_c(lvt_4_1_);
         if (lvt_4_1_ < 1.0F) {
            lvt_4_1_ = 1.0F;
         }

         lvt_4_1_ = p_70060_3_ / lvt_4_1_;
         p_70060_1_ *= lvt_4_1_;
         p_70060_2_ *= lvt_4_1_;
         float lvt_5_1_ = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
         float lvt_6_1_ = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
         this.field_70159_w += (double)(p_70060_1_ * lvt_6_1_ - p_70060_2_ * lvt_5_1_);
         this.field_70179_y += (double)(p_70060_2_ * lvt_6_1_ + p_70060_1_ * lvt_5_1_);
      }
   }

   public int func_70070_b(float p_70070_1_) {
      BlockPos lvt_2_1_ = new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      return this.field_70170_p.func_175667_e(lvt_2_1_) ? this.field_70170_p.func_175626_b(lvt_2_1_, 0) : 0;
   }

   public float func_70013_c(float p_70013_1_) {
      BlockPos lvt_2_1_ = new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      return this.field_70170_p.func_175667_e(lvt_2_1_) ? this.field_70170_p.func_175724_o(lvt_2_1_) : 0.0F;
   }

   public void func_70029_a(World p_70029_1_) {
      this.field_70170_p = p_70029_1_;
   }

   public void func_70080_a(double p_70080_1_, double p_70080_3_, double p_70080_5_, float p_70080_7_, float p_70080_8_) {
      this.field_70169_q = this.field_70165_t = p_70080_1_;
      this.field_70167_r = this.field_70163_u = p_70080_3_;
      this.field_70166_s = this.field_70161_v = p_70080_5_;
      this.field_70126_B = this.field_70177_z = p_70080_7_;
      this.field_70127_C = this.field_70125_A = p_70080_8_;
      double lvt_9_1_ = (double)(this.field_70126_B - p_70080_7_);
      if (lvt_9_1_ < -180.0D) {
         this.field_70126_B += 360.0F;
      }

      if (lvt_9_1_ >= 180.0D) {
         this.field_70126_B -= 360.0F;
      }

      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.func_70101_b(p_70080_7_, p_70080_8_);
   }

   public void func_174828_a(BlockPos p_174828_1_, float p_174828_2_, float p_174828_3_) {
      this.func_70012_b((double)p_174828_1_.func_177958_n() + 0.5D, (double)p_174828_1_.func_177956_o(), (double)p_174828_1_.func_177952_p() + 0.5D, p_174828_2_, p_174828_3_);
   }

   public void func_70012_b(double p_70012_1_, double p_70012_3_, double p_70012_5_, float p_70012_7_, float p_70012_8_) {
      this.field_70142_S = this.field_70169_q = this.field_70165_t = p_70012_1_;
      this.field_70137_T = this.field_70167_r = this.field_70163_u = p_70012_3_;
      this.field_70136_U = this.field_70166_s = this.field_70161_v = p_70012_5_;
      this.field_70177_z = p_70012_7_;
      this.field_70125_A = p_70012_8_;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public float func_70032_d(Entity p_70032_1_) {
      float lvt_2_1_ = (float)(this.field_70165_t - p_70032_1_.field_70165_t);
      float lvt_3_1_ = (float)(this.field_70163_u - p_70032_1_.field_70163_u);
      float lvt_4_1_ = (float)(this.field_70161_v - p_70032_1_.field_70161_v);
      return MathHelper.func_76129_c(lvt_2_1_ * lvt_2_1_ + lvt_3_1_ * lvt_3_1_ + lvt_4_1_ * lvt_4_1_);
   }

   public double func_70092_e(double p_70092_1_, double p_70092_3_, double p_70092_5_) {
      double lvt_7_1_ = this.field_70165_t - p_70092_1_;
      double lvt_9_1_ = this.field_70163_u - p_70092_3_;
      double lvt_11_1_ = this.field_70161_v - p_70092_5_;
      return lvt_7_1_ * lvt_7_1_ + lvt_9_1_ * lvt_9_1_ + lvt_11_1_ * lvt_11_1_;
   }

   public double func_174818_b(BlockPos p_174818_1_) {
      return p_174818_1_.func_177954_c(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public double func_174831_c(BlockPos p_174831_1_) {
      return p_174831_1_.func_177957_d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public double func_70011_f(double p_70011_1_, double p_70011_3_, double p_70011_5_) {
      double lvt_7_1_ = this.field_70165_t - p_70011_1_;
      double lvt_9_1_ = this.field_70163_u - p_70011_3_;
      double lvt_11_1_ = this.field_70161_v - p_70011_5_;
      return (double)MathHelper.func_76133_a(lvt_7_1_ * lvt_7_1_ + lvt_9_1_ * lvt_9_1_ + lvt_11_1_ * lvt_11_1_);
   }

   public double func_70068_e(Entity p_70068_1_) {
      double lvt_2_1_ = this.field_70165_t - p_70068_1_.field_70165_t;
      double lvt_4_1_ = this.field_70163_u - p_70068_1_.field_70163_u;
      double lvt_6_1_ = this.field_70161_v - p_70068_1_.field_70161_v;
      return lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_;
   }

   public void func_70100_b_(EntityPlayer p_70100_1_) {
   }

   public void func_70108_f(Entity p_70108_1_) {
      if (p_70108_1_.field_70153_n != this && p_70108_1_.field_70154_o != this) {
         if (!p_70108_1_.field_70145_X && !this.field_70145_X) {
            double lvt_2_1_ = p_70108_1_.field_70165_t - this.field_70165_t;
            double lvt_4_1_ = p_70108_1_.field_70161_v - this.field_70161_v;
            double lvt_6_1_ = MathHelper.func_76132_a(lvt_2_1_, lvt_4_1_);
            if (lvt_6_1_ >= 0.009999999776482582D) {
               lvt_6_1_ = (double)MathHelper.func_76133_a(lvt_6_1_);
               lvt_2_1_ /= lvt_6_1_;
               lvt_4_1_ /= lvt_6_1_;
               double lvt_8_1_ = 1.0D / lvt_6_1_;
               if (lvt_8_1_ > 1.0D) {
                  lvt_8_1_ = 1.0D;
               }

               lvt_2_1_ *= lvt_8_1_;
               lvt_4_1_ *= lvt_8_1_;
               lvt_2_1_ *= 0.05000000074505806D;
               lvt_4_1_ *= 0.05000000074505806D;
               lvt_2_1_ *= (double)(1.0F - this.field_70144_Y);
               lvt_4_1_ *= (double)(1.0F - this.field_70144_Y);
               if (this.field_70153_n == null) {
                  this.func_70024_g(-lvt_2_1_, 0.0D, -lvt_4_1_);
               }

               if (p_70108_1_.field_70153_n == null) {
                  p_70108_1_.func_70024_g(lvt_2_1_, 0.0D, lvt_4_1_);
               }
            }

         }
      }
   }

   public void func_70024_g(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
      this.field_70159_w += p_70024_1_;
      this.field_70181_x += p_70024_3_;
      this.field_70179_y += p_70024_5_;
      this.field_70160_al = true;
   }

   protected void func_70018_K() {
      this.field_70133_I = true;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         this.func_70018_K();
         return false;
      }
   }

   public Vec3 func_70676_i(float p_70676_1_) {
      if (p_70676_1_ == 1.0F) {
         return this.func_174806_f(this.field_70125_A, this.field_70177_z);
      } else {
         float lvt_2_1_ = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * p_70676_1_;
         float lvt_3_1_ = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * p_70676_1_;
         return this.func_174806_f(lvt_2_1_, lvt_3_1_);
      }
   }

   protected final Vec3 func_174806_f(float p_174806_1_, float p_174806_2_) {
      float lvt_3_1_ = MathHelper.func_76134_b(-p_174806_2_ * 0.017453292F - 3.1415927F);
      float lvt_4_1_ = MathHelper.func_76126_a(-p_174806_2_ * 0.017453292F - 3.1415927F);
      float lvt_5_1_ = -MathHelper.func_76134_b(-p_174806_1_ * 0.017453292F);
      float lvt_6_1_ = MathHelper.func_76126_a(-p_174806_1_ * 0.017453292F);
      return new Vec3((double)(lvt_4_1_ * lvt_5_1_), (double)lvt_6_1_, (double)(lvt_3_1_ * lvt_5_1_));
   }

   public Vec3 func_174824_e(float p_174824_1_) {
      if (p_174824_1_ == 1.0F) {
         return new Vec3(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      } else {
         double lvt_2_1_ = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_174824_1_;
         double lvt_4_1_ = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_174824_1_ + (double)this.func_70047_e();
         double lvt_6_1_ = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_174824_1_;
         return new Vec3(lvt_2_1_, lvt_4_1_, lvt_6_1_);
      }
   }

   public MovingObjectPosition func_174822_a(double p_174822_1_, float p_174822_3_) {
      Vec3 lvt_4_1_ = this.func_174824_e(p_174822_3_);
      Vec3 lvt_5_1_ = this.func_70676_i(p_174822_3_);
      Vec3 lvt_6_1_ = lvt_4_1_.func_72441_c(lvt_5_1_.field_72450_a * p_174822_1_, lvt_5_1_.field_72448_b * p_174822_1_, lvt_5_1_.field_72449_c * p_174822_1_);
      return this.field_70170_p.func_147447_a(lvt_4_1_, lvt_6_1_, false, false, true);
   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean func_70104_M() {
      return false;
   }

   public void func_70084_c(Entity p_70084_1_, int p_70084_2_) {
   }

   public boolean func_145770_h(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
      double lvt_7_1_ = this.field_70165_t - p_145770_1_;
      double lvt_9_1_ = this.field_70163_u - p_145770_3_;
      double lvt_11_1_ = this.field_70161_v - p_145770_5_;
      double lvt_13_1_ = lvt_7_1_ * lvt_7_1_ + lvt_9_1_ * lvt_9_1_ + lvt_11_1_ * lvt_11_1_;
      return this.func_70112_a(lvt_13_1_);
   }

   public boolean func_70112_a(double p_70112_1_) {
      double lvt_3_1_ = this.func_174813_aQ().func_72320_b();
      if (Double.isNaN(lvt_3_1_)) {
         lvt_3_1_ = 1.0D;
      }

      lvt_3_1_ *= 64.0D * this.field_70155_l;
      return p_70112_1_ < lvt_3_1_ * lvt_3_1_;
   }

   public boolean func_98035_c(NBTTagCompound p_98035_1_) {
      String lvt_2_1_ = this.func_70022_Q();
      if (!this.field_70128_L && lvt_2_1_ != null) {
         p_98035_1_.func_74778_a("id", lvt_2_1_);
         this.func_70109_d(p_98035_1_);
         return true;
      } else {
         return false;
      }
   }

   public boolean func_70039_c(NBTTagCompound p_70039_1_) {
      String lvt_2_1_ = this.func_70022_Q();
      if (!this.field_70128_L && lvt_2_1_ != null && this.field_70153_n == null) {
         p_70039_1_.func_74778_a("id", lvt_2_1_);
         this.func_70109_d(p_70039_1_);
         return true;
      } else {
         return false;
      }
   }

   public void func_70109_d(NBTTagCompound p_70109_1_) {
      try {
         p_70109_1_.func_74782_a("Pos", this.func_70087_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));
         p_70109_1_.func_74782_a("Motion", this.func_70087_a(this.field_70159_w, this.field_70181_x, this.field_70179_y));
         p_70109_1_.func_74782_a("Rotation", this.func_70049_a(this.field_70177_z, this.field_70125_A));
         p_70109_1_.func_74776_a("FallDistance", this.field_70143_R);
         p_70109_1_.func_74777_a("Fire", (short)this.field_70151_c);
         p_70109_1_.func_74777_a("Air", (short)this.func_70086_ai());
         p_70109_1_.func_74757_a("OnGround", this.field_70122_E);
         p_70109_1_.func_74768_a("Dimension", this.field_71093_bK);
         p_70109_1_.func_74757_a("Invulnerable", this.field_83001_bt);
         p_70109_1_.func_74768_a("PortalCooldown", this.field_71088_bW);
         p_70109_1_.func_74772_a("UUIDMost", this.func_110124_au().getMostSignificantBits());
         p_70109_1_.func_74772_a("UUIDLeast", this.func_110124_au().getLeastSignificantBits());
         if (this.func_95999_t() != null && this.func_95999_t().length() > 0) {
            p_70109_1_.func_74778_a("CustomName", this.func_95999_t());
            p_70109_1_.func_74757_a("CustomNameVisible", this.func_174833_aM());
         }

         this.field_174837_as.func_179670_b(p_70109_1_);
         if (this.func_174814_R()) {
            p_70109_1_.func_74757_a("Silent", this.func_174814_R());
         }

         this.func_70014_b(p_70109_1_);
         if (this.field_70154_o != null) {
            NBTTagCompound lvt_2_1_ = new NBTTagCompound();
            if (this.field_70154_o.func_98035_c(lvt_2_1_)) {
               p_70109_1_.func_74782_a("Riding", lvt_2_1_);
            }
         }

      } catch (Throwable var5) {
         CrashReport lvt_3_1_ = CrashReport.func_85055_a(var5, "Saving entity NBT");
         CrashReportCategory lvt_4_1_ = lvt_3_1_.func_85058_a("Entity being saved");
         this.func_85029_a(lvt_4_1_);
         throw new ReportedException(lvt_3_1_);
      }
   }

   public void func_70020_e(NBTTagCompound p_70020_1_) {
      try {
         NBTTagList lvt_2_1_ = p_70020_1_.func_150295_c("Pos", 6);
         NBTTagList lvt_3_1_ = p_70020_1_.func_150295_c("Motion", 6);
         NBTTagList lvt_4_1_ = p_70020_1_.func_150295_c("Rotation", 5);
         this.field_70159_w = lvt_3_1_.func_150309_d(0);
         this.field_70181_x = lvt_3_1_.func_150309_d(1);
         this.field_70179_y = lvt_3_1_.func_150309_d(2);
         if (Math.abs(this.field_70159_w) > 10.0D) {
            this.field_70159_w = 0.0D;
         }

         if (Math.abs(this.field_70181_x) > 10.0D) {
            this.field_70181_x = 0.0D;
         }

         if (Math.abs(this.field_70179_y) > 10.0D) {
            this.field_70179_y = 0.0D;
         }

         this.field_70169_q = this.field_70142_S = this.field_70165_t = lvt_2_1_.func_150309_d(0);
         this.field_70167_r = this.field_70137_T = this.field_70163_u = lvt_2_1_.func_150309_d(1);
         this.field_70166_s = this.field_70136_U = this.field_70161_v = lvt_2_1_.func_150309_d(2);
         this.field_70126_B = this.field_70177_z = lvt_4_1_.func_150308_e(0);
         this.field_70127_C = this.field_70125_A = lvt_4_1_.func_150308_e(1);
         this.func_70034_d(this.field_70177_z);
         this.func_181013_g(this.field_70177_z);
         this.field_70143_R = p_70020_1_.func_74760_g("FallDistance");
         this.field_70151_c = p_70020_1_.func_74765_d("Fire");
         this.func_70050_g(p_70020_1_.func_74765_d("Air"));
         this.field_70122_E = p_70020_1_.func_74767_n("OnGround");
         this.field_71093_bK = p_70020_1_.func_74762_e("Dimension");
         this.field_83001_bt = p_70020_1_.func_74767_n("Invulnerable");
         this.field_71088_bW = p_70020_1_.func_74762_e("PortalCooldown");
         if (p_70020_1_.func_150297_b("UUIDMost", 4) && p_70020_1_.func_150297_b("UUIDLeast", 4)) {
            this.field_96093_i = new UUID(p_70020_1_.func_74763_f("UUIDMost"), p_70020_1_.func_74763_f("UUIDLeast"));
         } else if (p_70020_1_.func_150297_b("UUID", 8)) {
            this.field_96093_i = UUID.fromString(p_70020_1_.func_74779_i("UUID"));
         }

         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         if (p_70020_1_.func_150297_b("CustomName", 8) && p_70020_1_.func_74779_i("CustomName").length() > 0) {
            this.func_96094_a(p_70020_1_.func_74779_i("CustomName"));
         }

         this.func_174805_g(p_70020_1_.func_74767_n("CustomNameVisible"));
         this.field_174837_as.func_179668_a(p_70020_1_);
         this.func_174810_b(p_70020_1_.func_74767_n("Silent"));
         this.func_70037_a(p_70020_1_);
         if (this.func_142008_O()) {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         }

      } catch (Throwable var5) {
         CrashReport lvt_3_2_ = CrashReport.func_85055_a(var5, "Loading entity NBT");
         CrashReportCategory lvt_4_2_ = lvt_3_2_.func_85058_a("Entity being loaded");
         this.func_85029_a(lvt_4_2_);
         throw new ReportedException(lvt_3_2_);
      }
   }

   protected boolean func_142008_O() {
      return true;
   }

   protected final String func_70022_Q() {
      return EntityList.func_75621_b(this);
   }

   protected abstract void func_70037_a(NBTTagCompound var1);

   protected abstract void func_70014_b(NBTTagCompound var1);

   public void func_110123_P() {
   }

   protected NBTTagList func_70087_a(double... p_70087_1_) {
      NBTTagList lvt_2_1_ = new NBTTagList();
      double[] lvt_3_1_ = p_70087_1_;
      int lvt_4_1_ = p_70087_1_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         double lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         lvt_2_1_.func_74742_a(new NBTTagDouble(lvt_6_1_));
      }

      return lvt_2_1_;
   }

   protected NBTTagList func_70049_a(float... p_70049_1_) {
      NBTTagList lvt_2_1_ = new NBTTagList();
      float[] lvt_3_1_ = p_70049_1_;
      int lvt_4_1_ = p_70049_1_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         float lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         lvt_2_1_.func_74742_a(new NBTTagFloat(lvt_6_1_));
      }

      return lvt_2_1_;
   }

   public EntityItem func_145779_a(Item p_145779_1_, int p_145779_2_) {
      return this.func_145778_a(p_145779_1_, p_145779_2_, 0.0F);
   }

   public EntityItem func_145778_a(Item p_145778_1_, int p_145778_2_, float p_145778_3_) {
      return this.func_70099_a(new ItemStack(p_145778_1_, p_145778_2_, 0), p_145778_3_);
   }

   public EntityItem func_70099_a(ItemStack p_70099_1_, float p_70099_2_) {
      if (p_70099_1_.field_77994_a != 0 && p_70099_1_.func_77973_b() != null) {
         EntityItem lvt_3_1_ = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)p_70099_2_, this.field_70161_v, p_70099_1_);
         lvt_3_1_.func_174869_p();
         this.field_70170_p.func_72838_d(lvt_3_1_);
         return lvt_3_1_;
      } else {
         return null;
      }
   }

   public boolean func_70089_S() {
      return !this.field_70128_L;
   }

   public boolean func_70094_T() {
      if (this.field_70145_X) {
         return false;
      } else {
         BlockPos.MutableBlockPos lvt_1_1_ = new BlockPos.MutableBlockPos(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

         for(int lvt_2_1_ = 0; lvt_2_1_ < 8; ++lvt_2_1_) {
            int lvt_3_1_ = MathHelper.func_76128_c(this.field_70163_u + (double)(((float)((lvt_2_1_ >> 0) % 2) - 0.5F) * 0.1F) + (double)this.func_70047_e());
            int lvt_4_1_ = MathHelper.func_76128_c(this.field_70165_t + (double)(((float)((lvt_2_1_ >> 1) % 2) - 0.5F) * this.field_70130_N * 0.8F));
            int lvt_5_1_ = MathHelper.func_76128_c(this.field_70161_v + (double)(((float)((lvt_2_1_ >> 2) % 2) - 0.5F) * this.field_70130_N * 0.8F));
            if (lvt_1_1_.func_177958_n() != lvt_4_1_ || lvt_1_1_.func_177956_o() != lvt_3_1_ || lvt_1_1_.func_177952_p() != lvt_5_1_) {
               lvt_1_1_.func_181079_c(lvt_4_1_, lvt_3_1_, lvt_5_1_);
               if (this.field_70170_p.func_180495_p(lvt_1_1_).func_177230_c().func_176214_u()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      return false;
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return null;
   }

   public void func_70098_U() {
      if (this.field_70154_o.field_70128_L) {
         this.field_70154_o = null;
      } else {
         this.field_70159_w = 0.0D;
         this.field_70181_x = 0.0D;
         this.field_70179_y = 0.0D;
         this.func_70071_h_();
         if (this.field_70154_o != null) {
            this.field_70154_o.func_70043_V();
            this.field_70147_f += (double)(this.field_70154_o.field_70177_z - this.field_70154_o.field_70126_B);

            for(this.field_70149_e += (double)(this.field_70154_o.field_70125_A - this.field_70154_o.field_70127_C); this.field_70147_f >= 180.0D; this.field_70147_f -= 360.0D) {
            }

            while(this.field_70147_f < -180.0D) {
               this.field_70147_f += 360.0D;
            }

            while(this.field_70149_e >= 180.0D) {
               this.field_70149_e -= 360.0D;
            }

            while(this.field_70149_e < -180.0D) {
               this.field_70149_e += 360.0D;
            }

            double lvt_1_1_ = this.field_70147_f * 0.5D;
            double lvt_3_1_ = this.field_70149_e * 0.5D;
            float lvt_5_1_ = 10.0F;
            if (lvt_1_1_ > (double)lvt_5_1_) {
               lvt_1_1_ = (double)lvt_5_1_;
            }

            if (lvt_1_1_ < (double)(-lvt_5_1_)) {
               lvt_1_1_ = (double)(-lvt_5_1_);
            }

            if (lvt_3_1_ > (double)lvt_5_1_) {
               lvt_3_1_ = (double)lvt_5_1_;
            }

            if (lvt_3_1_ < (double)(-lvt_5_1_)) {
               lvt_3_1_ = (double)(-lvt_5_1_);
            }

            this.field_70147_f -= lvt_1_1_;
            this.field_70149_e -= lvt_3_1_;
         }
      }
   }

   public void func_70043_V() {
      if (this.field_70153_n != null) {
         this.field_70153_n.func_70107_b(this.field_70165_t, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v);
      }
   }

   public double func_70033_W() {
      return 0.0D;
   }

   public double func_70042_X() {
      return (double)this.field_70131_O * 0.75D;
   }

   public void func_70078_a(Entity p_70078_1_) {
      this.field_70149_e = 0.0D;
      this.field_70147_f = 0.0D;
      if (p_70078_1_ == null) {
         if (this.field_70154_o != null) {
            this.func_70012_b(this.field_70154_o.field_70165_t, this.field_70154_o.func_174813_aQ().field_72338_b + (double)this.field_70154_o.field_70131_O, this.field_70154_o.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.field_70154_o.field_70153_n = null;
         }

         this.field_70154_o = null;
      } else {
         if (this.field_70154_o != null) {
            this.field_70154_o.field_70153_n = null;
         }

         if (p_70078_1_ != null) {
            for(Entity lvt_2_1_ = p_70078_1_.field_70154_o; lvt_2_1_ != null; lvt_2_1_ = lvt_2_1_.field_70154_o) {
               if (lvt_2_1_ == this) {
                  return;
               }
            }
         }

         this.field_70154_o = p_70078_1_;
         p_70078_1_.field_70153_n = this;
      }
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      this.func_70101_b(p_180426_7_, p_180426_8_);
      List<AxisAlignedBB> lvt_11_1_ = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72331_e(0.03125D, 0.0D, 0.03125D));
      if (!lvt_11_1_.isEmpty()) {
         double lvt_12_1_ = 0.0D;
         Iterator lvt_14_1_ = lvt_11_1_.iterator();

         while(lvt_14_1_.hasNext()) {
            AxisAlignedBB lvt_15_1_ = (AxisAlignedBB)lvt_14_1_.next();
            if (lvt_15_1_.field_72337_e > lvt_12_1_) {
               lvt_12_1_ = lvt_15_1_.field_72337_e;
            }
         }

         p_180426_3_ += lvt_12_1_ - this.func_174813_aQ().field_72338_b;
         this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      }

   }

   public float func_70111_Y() {
      return 0.1F;
   }

   public Vec3 func_70040_Z() {
      return null;
   }

   public void func_181015_d(BlockPos p_181015_1_) {
      if (this.field_71088_bW > 0) {
         this.field_71088_bW = this.func_82147_ab();
      } else {
         if (!this.field_70170_p.field_72995_K && !p_181015_1_.equals(this.field_181016_an)) {
            this.field_181016_an = p_181015_1_;
            BlockPattern.PatternHelper lvt_2_1_ = Blocks.field_150427_aO.func_181089_f(this.field_70170_p, p_181015_1_);
            double lvt_3_1_ = lvt_2_1_.func_177669_b().func_176740_k() == EnumFacing.Axis.X ? (double)lvt_2_1_.func_181117_a().func_177952_p() : (double)lvt_2_1_.func_181117_a().func_177958_n();
            double lvt_5_1_ = lvt_2_1_.func_177669_b().func_176740_k() == EnumFacing.Axis.X ? this.field_70161_v : this.field_70165_t;
            lvt_5_1_ = Math.abs(MathHelper.func_181160_c(lvt_5_1_ - (double)(lvt_2_1_.func_177669_b().func_176746_e().func_176743_c() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0), lvt_3_1_, lvt_3_1_ - (double)lvt_2_1_.func_181118_d()));
            double lvt_7_1_ = MathHelper.func_181160_c(this.field_70163_u - 1.0D, (double)lvt_2_1_.func_181117_a().func_177956_o(), (double)(lvt_2_1_.func_181117_a().func_177956_o() - lvt_2_1_.func_181119_e()));
            this.field_181017_ao = new Vec3(lvt_5_1_, lvt_7_1_, 0.0D);
            this.field_181018_ap = lvt_2_1_.func_177669_b();
         }

         this.field_71087_bX = true;
      }
   }

   public int func_82147_ab() {
      return 300;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
   }

   public void func_70103_a(byte p_70103_1_) {
   }

   public void func_70057_ab() {
   }

   public ItemStack[] func_70035_c() {
      return null;
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
   }

   public boolean func_70027_ad() {
      boolean lvt_1_1_ = this.field_70170_p != null && this.field_70170_p.field_72995_K;
      return !this.field_70178_ae && (this.field_70151_c > 0 || lvt_1_1_ && this.func_70083_f(0));
   }

   public boolean func_70115_ae() {
      return this.field_70154_o != null;
   }

   public boolean func_70093_af() {
      return this.func_70083_f(1);
   }

   public void func_70095_a(boolean p_70095_1_) {
      this.func_70052_a(1, p_70095_1_);
   }

   public boolean func_70051_ag() {
      return this.func_70083_f(3);
   }

   public void func_70031_b(boolean p_70031_1_) {
      this.func_70052_a(3, p_70031_1_);
   }

   public boolean func_82150_aj() {
      return this.func_70083_f(5);
   }

   public boolean func_98034_c(EntityPlayer p_98034_1_) {
      return p_98034_1_.func_175149_v() ? false : this.func_82150_aj();
   }

   public void func_82142_c(boolean p_82142_1_) {
      this.func_70052_a(5, p_82142_1_);
   }

   public boolean func_70113_ah() {
      return this.func_70083_f(4);
   }

   public void func_70019_c(boolean p_70019_1_) {
      this.func_70052_a(4, p_70019_1_);
   }

   protected boolean func_70083_f(int p_70083_1_) {
      return (this.field_70180_af.func_75683_a(0) & 1 << p_70083_1_) != 0;
   }

   protected void func_70052_a(int p_70052_1_, boolean p_70052_2_) {
      byte lvt_3_1_ = this.field_70180_af.func_75683_a(0);
      if (p_70052_2_) {
         this.field_70180_af.func_75692_b(0, (byte)(lvt_3_1_ | 1 << p_70052_1_));
      } else {
         this.field_70180_af.func_75692_b(0, (byte)(lvt_3_1_ & ~(1 << p_70052_1_)));
      }

   }

   public int func_70086_ai() {
      return this.field_70180_af.func_75693_b(1);
   }

   public void func_70050_g(int p_70050_1_) {
      this.field_70180_af.func_75692_b(1, (short)p_70050_1_);
   }

   public void func_70077_a(EntityLightningBolt p_70077_1_) {
      this.func_70097_a(DamageSource.field_180137_b, 5.0F);
      ++this.field_70151_c;
      if (this.field_70151_c == 0) {
         this.func_70015_d(8);
      }

   }

   public void func_70074_a(EntityLivingBase p_70074_1_) {
   }

   protected boolean func_145771_j(double p_145771_1_, double p_145771_3_, double p_145771_5_) {
      BlockPos lvt_7_1_ = new BlockPos(p_145771_1_, p_145771_3_, p_145771_5_);
      double lvt_8_1_ = p_145771_1_ - (double)lvt_7_1_.func_177958_n();
      double lvt_10_1_ = p_145771_3_ - (double)lvt_7_1_.func_177956_o();
      double lvt_12_1_ = p_145771_5_ - (double)lvt_7_1_.func_177952_p();
      List<AxisAlignedBB> lvt_14_1_ = this.field_70170_p.func_147461_a(this.func_174813_aQ());
      if (lvt_14_1_.isEmpty() && !this.field_70170_p.func_175665_u(lvt_7_1_)) {
         return false;
      } else {
         int lvt_15_1_ = 3;
         double lvt_16_1_ = 9999.0D;
         if (!this.field_70170_p.func_175665_u(lvt_7_1_.func_177976_e()) && lvt_8_1_ < lvt_16_1_) {
            lvt_16_1_ = lvt_8_1_;
            lvt_15_1_ = 0;
         }

         if (!this.field_70170_p.func_175665_u(lvt_7_1_.func_177974_f()) && 1.0D - lvt_8_1_ < lvt_16_1_) {
            lvt_16_1_ = 1.0D - lvt_8_1_;
            lvt_15_1_ = 1;
         }

         if (!this.field_70170_p.func_175665_u(lvt_7_1_.func_177984_a()) && 1.0D - lvt_10_1_ < lvt_16_1_) {
            lvt_16_1_ = 1.0D - lvt_10_1_;
            lvt_15_1_ = 3;
         }

         if (!this.field_70170_p.func_175665_u(lvt_7_1_.func_177978_c()) && lvt_12_1_ < lvt_16_1_) {
            lvt_16_1_ = lvt_12_1_;
            lvt_15_1_ = 4;
         }

         if (!this.field_70170_p.func_175665_u(lvt_7_1_.func_177968_d()) && 1.0D - lvt_12_1_ < lvt_16_1_) {
            lvt_16_1_ = 1.0D - lvt_12_1_;
            lvt_15_1_ = 5;
         }

         float lvt_18_1_ = this.field_70146_Z.nextFloat() * 0.2F + 0.1F;
         if (lvt_15_1_ == 0) {
            this.field_70159_w = (double)(-lvt_18_1_);
         }

         if (lvt_15_1_ == 1) {
            this.field_70159_w = (double)lvt_18_1_;
         }

         if (lvt_15_1_ == 3) {
            this.field_70181_x = (double)lvt_18_1_;
         }

         if (lvt_15_1_ == 4) {
            this.field_70179_y = (double)(-lvt_18_1_);
         }

         if (lvt_15_1_ == 5) {
            this.field_70179_y = (double)lvt_18_1_;
         }

         return true;
      }
   }

   public void func_70110_aj() {
      this.field_70134_J = true;
      this.field_70143_R = 0.0F;
   }

   public String func_70005_c_() {
      if (this.func_145818_k_()) {
         return this.func_95999_t();
      } else {
         String lvt_1_1_ = EntityList.func_75621_b(this);
         if (lvt_1_1_ == null) {
            lvt_1_1_ = "generic";
         }

         return StatCollector.func_74838_a("entity." + lvt_1_1_ + ".name");
      }
   }

   public Entity[] func_70021_al() {
      return null;
   }

   public boolean func_70028_i(Entity p_70028_1_) {
      return this == p_70028_1_;
   }

   public float func_70079_am() {
      return 0.0F;
   }

   public void func_70034_d(float p_70034_1_) {
   }

   public void func_181013_g(float p_181013_1_) {
   }

   public boolean func_70075_an() {
      return true;
   }

   public boolean func_85031_j(Entity p_85031_1_) {
      return false;
   }

   public String toString() {
      return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", this.getClass().getSimpleName(), this.func_70005_c_(), this.field_145783_c, this.field_70170_p == null ? "~NULL~" : this.field_70170_p.func_72912_H().func_76065_j(), this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public boolean func_180431_b(DamageSource p_180431_1_) {
      return this.field_83001_bt && p_180431_1_ != DamageSource.field_76380_i && !p_180431_1_.func_180136_u();
   }

   public void func_82149_j(Entity p_82149_1_) {
      this.func_70012_b(p_82149_1_.field_70165_t, p_82149_1_.field_70163_u, p_82149_1_.field_70161_v, p_82149_1_.field_70177_z, p_82149_1_.field_70125_A);
   }

   public void func_180432_n(Entity p_180432_1_) {
      NBTTagCompound lvt_2_1_ = new NBTTagCompound();
      p_180432_1_.func_70109_d(lvt_2_1_);
      this.func_70020_e(lvt_2_1_);
      this.field_71088_bW = p_180432_1_.field_71088_bW;
      this.field_181016_an = p_180432_1_.field_181016_an;
      this.field_181017_ao = p_180432_1_.field_181017_ao;
      this.field_181018_ap = p_180432_1_.field_181018_ap;
   }

   public void func_71027_c(int p_71027_1_) {
      if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         this.field_70170_p.field_72984_F.func_76320_a("changeDimension");
         MinecraftServer lvt_2_1_ = MinecraftServer.func_71276_C();
         int lvt_3_1_ = this.field_71093_bK;
         WorldServer lvt_4_1_ = lvt_2_1_.func_71218_a(lvt_3_1_);
         WorldServer lvt_5_1_ = lvt_2_1_.func_71218_a(p_71027_1_);
         this.field_71093_bK = p_71027_1_;
         if (lvt_3_1_ == 1 && p_71027_1_ == 1) {
            lvt_5_1_ = lvt_2_1_.func_71218_a(0);
            this.field_71093_bK = 0;
         }

         this.field_70170_p.func_72900_e(this);
         this.field_70128_L = false;
         this.field_70170_p.field_72984_F.func_76320_a("reposition");
         lvt_2_1_.func_71203_ab().func_82448_a(this, lvt_3_1_, lvt_4_1_, lvt_5_1_);
         this.field_70170_p.field_72984_F.func_76318_c("reloading");
         Entity lvt_6_1_ = EntityList.func_75620_a(EntityList.func_75621_b(this), lvt_5_1_);
         if (lvt_6_1_ != null) {
            lvt_6_1_.func_180432_n(this);
            if (lvt_3_1_ == 1 && p_71027_1_ == 1) {
               BlockPos lvt_7_1_ = this.field_70170_p.func_175672_r(lvt_5_1_.func_175694_M());
               lvt_6_1_.func_174828_a(lvt_7_1_, lvt_6_1_.field_70177_z, lvt_6_1_.field_70125_A);
            }

            lvt_5_1_.func_72838_d(lvt_6_1_);
         }

         this.field_70128_L = true;
         this.field_70170_p.field_72984_F.func_76319_b();
         lvt_4_1_.func_82742_i();
         lvt_5_1_.func_82742_i();
         this.field_70170_p.field_72984_F.func_76319_b();
      }
   }

   public float func_180428_a(Explosion p_180428_1_, World p_180428_2_, BlockPos p_180428_3_, IBlockState p_180428_4_) {
      return p_180428_4_.func_177230_c().func_149638_a(this);
   }

   public boolean func_174816_a(Explosion p_174816_1_, World p_174816_2_, BlockPos p_174816_3_, IBlockState p_174816_4_, float p_174816_5_) {
      return true;
   }

   public int func_82143_as() {
      return 3;
   }

   public Vec3 func_181014_aG() {
      return this.field_181017_ao;
   }

   public EnumFacing func_181012_aH() {
      return this.field_181018_ap;
   }

   public boolean func_145773_az() {
      return false;
   }

   public void func_85029_a(CrashReportCategory p_85029_1_) {
      p_85029_1_.func_71500_a("Entity Type", new Callable<String>() {
         public String call() throws Exception {
            return EntityList.func_75621_b(Entity.this) + " (" + Entity.this.getClass().getCanonicalName() + ")";
         }
      });
      p_85029_1_.func_71507_a("Entity ID", this.field_145783_c);
      p_85029_1_.func_71500_a("Entity Name", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.func_70005_c_();
         }
      });
      p_85029_1_.func_71507_a("Entity's Exact location", String.format("%.2f, %.2f, %.2f", this.field_70165_t, this.field_70163_u, this.field_70161_v));
      p_85029_1_.func_71507_a("Entity's Block location", CrashReportCategory.func_85074_a((double)MathHelper.func_76128_c(this.field_70165_t), (double)MathHelper.func_76128_c(this.field_70163_u), (double)MathHelper.func_76128_c(this.field_70161_v)));
      p_85029_1_.func_71507_a("Entity's Momentum", String.format("%.2f, %.2f, %.2f", this.field_70159_w, this.field_70181_x, this.field_70179_y));
      p_85029_1_.func_71500_a("Entity's Rider", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.field_70153_n.toString();
         }
      });
      p_85029_1_.func_71500_a("Entity's Vehicle", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.field_70154_o.toString();
         }
      });
   }

   public boolean func_90999_ad() {
      return this.func_70027_ad();
   }

   public UUID func_110124_au() {
      return this.field_96093_i;
   }

   public boolean func_96092_aw() {
      return true;
   }

   public IChatComponent func_145748_c_() {
      ChatComponentText lvt_1_1_ = new ChatComponentText(this.func_70005_c_());
      lvt_1_1_.func_150256_b().func_150209_a(this.func_174823_aP());
      lvt_1_1_.func_150256_b().func_179989_a(this.func_110124_au().toString());
      return lvt_1_1_;
   }

   public void func_96094_a(String p_96094_1_) {
      this.field_70180_af.func_75692_b(2, p_96094_1_);
   }

   public String func_95999_t() {
      return this.field_70180_af.func_75681_e(2);
   }

   public boolean func_145818_k_() {
      return this.field_70180_af.func_75681_e(2).length() > 0;
   }

   public void func_174805_g(boolean p_174805_1_) {
      this.field_70180_af.func_75692_b(3, Byte.valueOf((byte)(p_174805_1_ ? 1 : 0)));
   }

   public boolean func_174833_aM() {
      return this.field_70180_af.func_75683_a(3) == 1;
   }

   public void func_70634_a(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
      this.func_70012_b(p_70634_1_, p_70634_3_, p_70634_5_, this.field_70177_z, this.field_70125_A);
   }

   public boolean func_94059_bO() {
      return this.func_174833_aM();
   }

   public void func_145781_i(int p_145781_1_) {
   }

   public EnumFacing func_174811_aO() {
      return EnumFacing.func_176731_b(MathHelper.func_76128_c((double)(this.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3);
   }

   protected HoverEvent func_174823_aP() {
      NBTTagCompound lvt_1_1_ = new NBTTagCompound();
      String lvt_2_1_ = EntityList.func_75621_b(this);
      lvt_1_1_.func_74778_a("id", this.func_110124_au().toString());
      if (lvt_2_1_ != null) {
         lvt_1_1_.func_74778_a("type", lvt_2_1_);
      }

      lvt_1_1_.func_74778_a("name", this.func_70005_c_());
      return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new ChatComponentText(lvt_1_1_.toString()));
   }

   public boolean func_174827_a(EntityPlayerMP p_174827_1_) {
      return true;
   }

   public AxisAlignedBB func_174813_aQ() {
      return this.field_70121_D;
   }

   public void func_174826_a(AxisAlignedBB p_174826_1_) {
      this.field_70121_D = p_174826_1_;
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.85F;
   }

   public boolean func_174832_aS() {
      return this.field_174835_g;
   }

   public void func_174821_h(boolean p_174821_1_) {
      this.field_174835_g = p_174821_1_;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      return false;
   }

   public void func_145747_a(IChatComponent p_145747_1_) {
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return true;
   }

   public BlockPos func_180425_c() {
      return new BlockPos(this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v);
   }

   public Vec3 func_174791_d() {
      return new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public World func_130014_f_() {
      return this.field_70170_p;
   }

   public Entity func_174793_f() {
      return this;
   }

   public boolean func_174792_t_() {
      return false;
   }

   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
      this.field_174837_as.func_179672_a(this, p_174794_1_, p_174794_2_);
   }

   public CommandResultStats func_174807_aT() {
      return this.field_174837_as;
   }

   public void func_174817_o(Entity p_174817_1_) {
      this.field_174837_as.func_179671_a(p_174817_1_.func_174807_aT());
   }

   public NBTTagCompound func_174819_aU() {
      return null;
   }

   public void func_174834_g(NBTTagCompound p_174834_1_) {
   }

   public boolean func_174825_a(EntityPlayer p_174825_1_, Vec3 p_174825_2_) {
      return false;
   }

   public boolean func_180427_aV() {
      return false;
   }

   protected void func_174815_a(EntityLivingBase p_174815_1_, Entity p_174815_2_) {
      if (p_174815_2_ instanceof EntityLivingBase) {
         EnchantmentHelper.func_151384_a((EntityLivingBase)p_174815_2_, p_174815_1_);
      }

      EnchantmentHelper.func_151385_b(p_174815_1_, p_174815_2_);
   }
}
