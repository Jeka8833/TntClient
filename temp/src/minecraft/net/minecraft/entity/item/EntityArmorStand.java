package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotations;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityArmorStand extends EntityLivingBase {
   private static final Rotations field_175435_a = new Rotations(0.0F, 0.0F, 0.0F);
   private static final Rotations field_175433_b = new Rotations(0.0F, 0.0F, 0.0F);
   private static final Rotations field_175434_c = new Rotations(-10.0F, 0.0F, -10.0F);
   private static final Rotations field_175431_d = new Rotations(-15.0F, 0.0F, 10.0F);
   private static final Rotations field_175432_e = new Rotations(-1.0F, 0.0F, -1.0F);
   private static final Rotations field_175429_f = new Rotations(1.0F, 0.0F, 1.0F);
   private final ItemStack[] field_175430_g;
   private boolean field_175436_h;
   private long field_175437_i;
   private int field_175442_bg;
   private boolean field_181028_bj;
   private Rotations field_175443_bh;
   private Rotations field_175444_bi;
   private Rotations field_175438_bj;
   private Rotations field_175439_bk;
   private Rotations field_175440_bl;
   private Rotations field_175441_bm;

   public EntityArmorStand(World p_i45854_1_) {
      super(p_i45854_1_);
      this.field_175430_g = new ItemStack[5];
      this.field_175443_bh = field_175435_a;
      this.field_175444_bi = field_175433_b;
      this.field_175438_bj = field_175434_c;
      this.field_175439_bk = field_175431_d;
      this.field_175440_bl = field_175432_e;
      this.field_175441_bm = field_175429_f;
      this.func_174810_b(true);
      this.field_70145_X = this.func_175423_p();
      this.func_70105_a(0.5F, 1.975F);
   }

   public EntityArmorStand(World p_i45855_1_, double p_i45855_2_, double p_i45855_4_, double p_i45855_6_) {
      this(p_i45855_1_);
      this.func_70107_b(p_i45855_2_, p_i45855_4_, p_i45855_6_);
   }

   public boolean func_70613_aW() {
      return super.func_70613_aW() && !this.func_175423_p();
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(10, (byte)0);
      this.field_70180_af.func_75682_a(11, field_175435_a);
      this.field_70180_af.func_75682_a(12, field_175433_b);
      this.field_70180_af.func_75682_a(13, field_175434_c);
      this.field_70180_af.func_75682_a(14, field_175431_d);
      this.field_70180_af.func_75682_a(15, field_175432_e);
      this.field_70180_af.func_75682_a(16, field_175429_f);
   }

   public ItemStack func_70694_bm() {
      return this.field_175430_g[0];
   }

   public ItemStack func_71124_b(int p_71124_1_) {
      return this.field_175430_g[p_71124_1_];
   }

   public ItemStack func_82169_q(int p_82169_1_) {
      return this.field_175430_g[p_82169_1_ + 1];
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
      this.field_175430_g[p_70062_1_] = p_70062_2_;
   }

   public ItemStack[] func_70035_c() {
      return this.field_175430_g;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      int lvt_3_2_;
      if (p_174820_1_ == 99) {
         lvt_3_2_ = 0;
      } else {
         lvt_3_2_ = p_174820_1_ - 100 + 1;
         if (lvt_3_2_ < 0 || lvt_3_2_ >= this.field_175430_g.length) {
            return false;
         }
      }

      if (p_174820_2_ != null && EntityLiving.func_82159_b(p_174820_2_) != lvt_3_2_ && (lvt_3_2_ != 4 || !(p_174820_2_.func_77973_b() instanceof ItemBlock))) {
         return false;
      } else {
         this.func_70062_b(lvt_3_2_, p_174820_2_);
         return true;
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      NBTTagList lvt_2_1_ = new NBTTagList();

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_175430_g.length; ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = new NBTTagCompound();
         if (this.field_175430_g[lvt_3_1_] != null) {
            this.field_175430_g[lvt_3_1_].func_77955_b(lvt_4_1_);
         }

         lvt_2_1_.func_74742_a(lvt_4_1_);
      }

      p_70014_1_.func_74782_a("Equipment", lvt_2_1_);
      if (this.func_174833_aM() && (this.func_95999_t() == null || this.func_95999_t().length() == 0)) {
         p_70014_1_.func_74757_a("CustomNameVisible", this.func_174833_aM());
      }

      p_70014_1_.func_74757_a("Invisible", this.func_82150_aj());
      p_70014_1_.func_74757_a("Small", this.func_175410_n());
      p_70014_1_.func_74757_a("ShowArms", this.func_175402_q());
      p_70014_1_.func_74768_a("DisabledSlots", this.field_175442_bg);
      p_70014_1_.func_74757_a("NoGravity", this.func_175423_p());
      p_70014_1_.func_74757_a("NoBasePlate", this.func_175414_r());
      if (this.func_181026_s()) {
         p_70014_1_.func_74757_a("Marker", this.func_181026_s());
      }

      p_70014_1_.func_74782_a("Pose", this.func_175419_y());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if (p_70037_1_.func_150297_b("Equipment", 9)) {
         NBTTagList lvt_2_1_ = p_70037_1_.func_150295_c("Equipment", 10);

         for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_175430_g.length; ++lvt_3_1_) {
            this.field_175430_g[lvt_3_1_] = ItemStack.func_77949_a(lvt_2_1_.func_150305_b(lvt_3_1_));
         }
      }

      this.func_82142_c(p_70037_1_.func_74767_n("Invisible"));
      this.func_175420_a(p_70037_1_.func_74767_n("Small"));
      this.func_175413_k(p_70037_1_.func_74767_n("ShowArms"));
      this.field_175442_bg = p_70037_1_.func_74762_e("DisabledSlots");
      this.func_175425_j(p_70037_1_.func_74767_n("NoGravity"));
      this.func_175426_l(p_70037_1_.func_74767_n("NoBasePlate"));
      this.func_181027_m(p_70037_1_.func_74767_n("Marker"));
      this.field_181028_bj = !this.func_181026_s();
      this.field_70145_X = this.func_175423_p();
      NBTTagCompound lvt_2_2_ = p_70037_1_.func_74775_l("Pose");
      this.func_175416_h(lvt_2_2_);
   }

   private void func_175416_h(NBTTagCompound p_175416_1_) {
      NBTTagList lvt_2_1_ = p_175416_1_.func_150295_c("Head", 5);
      if (lvt_2_1_.func_74745_c() > 0) {
         this.func_175415_a(new Rotations(lvt_2_1_));
      } else {
         this.func_175415_a(field_175435_a);
      }

      NBTTagList lvt_3_1_ = p_175416_1_.func_150295_c("Body", 5);
      if (lvt_3_1_.func_74745_c() > 0) {
         this.func_175424_b(new Rotations(lvt_3_1_));
      } else {
         this.func_175424_b(field_175433_b);
      }

      NBTTagList lvt_4_1_ = p_175416_1_.func_150295_c("LeftArm", 5);
      if (lvt_4_1_.func_74745_c() > 0) {
         this.func_175405_c(new Rotations(lvt_4_1_));
      } else {
         this.func_175405_c(field_175434_c);
      }

      NBTTagList lvt_5_1_ = p_175416_1_.func_150295_c("RightArm", 5);
      if (lvt_5_1_.func_74745_c() > 0) {
         this.func_175428_d(new Rotations(lvt_5_1_));
      } else {
         this.func_175428_d(field_175431_d);
      }

      NBTTagList lvt_6_1_ = p_175416_1_.func_150295_c("LeftLeg", 5);
      if (lvt_6_1_.func_74745_c() > 0) {
         this.func_175417_e(new Rotations(lvt_6_1_));
      } else {
         this.func_175417_e(field_175432_e);
      }

      NBTTagList lvt_7_1_ = p_175416_1_.func_150295_c("RightLeg", 5);
      if (lvt_7_1_.func_74745_c() > 0) {
         this.func_175427_f(new Rotations(lvt_7_1_));
      } else {
         this.func_175427_f(field_175429_f);
      }

   }

   private NBTTagCompound func_175419_y() {
      NBTTagCompound lvt_1_1_ = new NBTTagCompound();
      if (!field_175435_a.equals(this.field_175443_bh)) {
         lvt_1_1_.func_74782_a("Head", this.field_175443_bh.func_179414_a());
      }

      if (!field_175433_b.equals(this.field_175444_bi)) {
         lvt_1_1_.func_74782_a("Body", this.field_175444_bi.func_179414_a());
      }

      if (!field_175434_c.equals(this.field_175438_bj)) {
         lvt_1_1_.func_74782_a("LeftArm", this.field_175438_bj.func_179414_a());
      }

      if (!field_175431_d.equals(this.field_175439_bk)) {
         lvt_1_1_.func_74782_a("RightArm", this.field_175439_bk.func_179414_a());
      }

      if (!field_175432_e.equals(this.field_175440_bl)) {
         lvt_1_1_.func_74782_a("LeftLeg", this.field_175440_bl.func_179414_a());
      }

      if (!field_175429_f.equals(this.field_175441_bm)) {
         lvt_1_1_.func_74782_a("RightLeg", this.field_175441_bm.func_179414_a());
      }

      return lvt_1_1_;
   }

   public boolean func_70104_M() {
      return false;
   }

   protected void func_82167_n(Entity p_82167_1_) {
   }

   protected void func_85033_bc() {
      List<Entity> lvt_1_1_ = this.field_70170_p.func_72839_b(this, this.func_174813_aQ());
      if (lvt_1_1_ != null && !lvt_1_1_.isEmpty()) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_.size(); ++lvt_2_1_) {
            Entity lvt_3_1_ = (Entity)lvt_1_1_.get(lvt_2_1_);
            if (lvt_3_1_ instanceof EntityMinecart && ((EntityMinecart)lvt_3_1_).func_180456_s() == EntityMinecart.EnumMinecartType.RIDEABLE && this.func_70068_e(lvt_3_1_) <= 0.2D) {
               lvt_3_1_.func_70108_f(this);
            }
         }
      }

   }

   public boolean func_174825_a(EntityPlayer p_174825_1_, Vec3 p_174825_2_) {
      if (this.func_181026_s()) {
         return false;
      } else if (!this.field_70170_p.field_72995_K && !p_174825_1_.func_175149_v()) {
         int lvt_3_1_ = 0;
         ItemStack lvt_4_1_ = p_174825_1_.func_71045_bC();
         boolean lvt_5_1_ = lvt_4_1_ != null;
         if (lvt_5_1_ && lvt_4_1_.func_77973_b() instanceof ItemArmor) {
            ItemArmor lvt_6_1_ = (ItemArmor)lvt_4_1_.func_77973_b();
            if (lvt_6_1_.field_77881_a == 3) {
               lvt_3_1_ = 1;
            } else if (lvt_6_1_.field_77881_a == 2) {
               lvt_3_1_ = 2;
            } else if (lvt_6_1_.field_77881_a == 1) {
               lvt_3_1_ = 3;
            } else if (lvt_6_1_.field_77881_a == 0) {
               lvt_3_1_ = 4;
            }
         }

         if (lvt_5_1_ && (lvt_4_1_.func_77973_b() == Items.field_151144_bL || lvt_4_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK))) {
            lvt_3_1_ = 4;
         }

         double lvt_6_2_ = 0.1D;
         double lvt_8_1_ = 0.9D;
         double lvt_10_1_ = 0.4D;
         double lvt_12_1_ = 1.6D;
         int lvt_14_1_ = 0;
         boolean lvt_15_1_ = this.func_175410_n();
         double lvt_16_1_ = lvt_15_1_ ? p_174825_2_.field_72448_b * 2.0D : p_174825_2_.field_72448_b;
         if (lvt_16_1_ >= 0.1D && lvt_16_1_ < 0.1D + (lvt_15_1_ ? 0.8D : 0.45D) && this.field_175430_g[1] != null) {
            lvt_14_1_ = 1;
         } else if (lvt_16_1_ >= 0.9D + (lvt_15_1_ ? 0.3D : 0.0D) && lvt_16_1_ < 0.9D + (lvt_15_1_ ? 1.0D : 0.7D) && this.field_175430_g[3] != null) {
            lvt_14_1_ = 3;
         } else if (lvt_16_1_ >= 0.4D && lvt_16_1_ < 0.4D + (lvt_15_1_ ? 1.0D : 0.8D) && this.field_175430_g[2] != null) {
            lvt_14_1_ = 2;
         } else if (lvt_16_1_ >= 1.6D && this.field_175430_g[4] != null) {
            lvt_14_1_ = 4;
         }

         boolean lvt_18_1_ = this.field_175430_g[lvt_14_1_] != null;
         if ((this.field_175442_bg & 1 << lvt_14_1_) != 0 || (this.field_175442_bg & 1 << lvt_3_1_) != 0) {
            lvt_14_1_ = lvt_3_1_;
            if ((this.field_175442_bg & 1 << lvt_3_1_) != 0) {
               if ((this.field_175442_bg & 1) != 0) {
                  return true;
               }

               lvt_14_1_ = 0;
            }
         }

         if (lvt_5_1_ && lvt_3_1_ == 0 && !this.func_175402_q()) {
            return true;
         } else {
            if (lvt_5_1_) {
               this.func_175422_a(p_174825_1_, lvt_3_1_);
            } else if (lvt_18_1_) {
               this.func_175422_a(p_174825_1_, lvt_14_1_);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   private void func_175422_a(EntityPlayer p_175422_1_, int p_175422_2_) {
      ItemStack lvt_3_1_ = this.field_175430_g[p_175422_2_];
      if (lvt_3_1_ == null || (this.field_175442_bg & 1 << p_175422_2_ + 8) == 0) {
         if (lvt_3_1_ != null || (this.field_175442_bg & 1 << p_175422_2_ + 16) == 0) {
            int lvt_4_1_ = p_175422_1_.field_71071_by.field_70461_c;
            ItemStack lvt_5_1_ = p_175422_1_.field_71071_by.func_70301_a(lvt_4_1_);
            ItemStack lvt_6_2_;
            if (p_175422_1_.field_71075_bZ.field_75098_d && (lvt_3_1_ == null || lvt_3_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150350_a)) && lvt_5_1_ != null) {
               lvt_6_2_ = lvt_5_1_.func_77946_l();
               lvt_6_2_.field_77994_a = 1;
               this.func_70062_b(p_175422_2_, lvt_6_2_);
            } else if (lvt_5_1_ != null && lvt_5_1_.field_77994_a > 1) {
               if (lvt_3_1_ == null) {
                  lvt_6_2_ = lvt_5_1_.func_77946_l();
                  lvt_6_2_.field_77994_a = 1;
                  this.func_70062_b(p_175422_2_, lvt_6_2_);
                  --lvt_5_1_.field_77994_a;
               }
            } else {
               this.func_70062_b(p_175422_2_, lvt_5_1_);
               p_175422_1_.field_71071_by.func_70299_a(lvt_4_1_, lvt_3_1_);
            }
         }
      }
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.field_70170_p.field_72995_K) {
         return false;
      } else if (DamageSource.field_76380_i.equals(p_70097_1_)) {
         this.func_70106_y();
         return false;
      } else if (!this.func_180431_b(p_70097_1_) && !this.field_175436_h && !this.func_181026_s()) {
         if (p_70097_1_.func_94541_c()) {
            this.func_175409_C();
            this.func_70106_y();
            return false;
         } else if (DamageSource.field_76372_a.equals(p_70097_1_)) {
            if (!this.func_70027_ad()) {
               this.func_70015_d(5);
            } else {
               this.func_175406_a(0.15F);
            }

            return false;
         } else if (DamageSource.field_76370_b.equals(p_70097_1_) && this.func_110143_aJ() > 0.5F) {
            this.func_175406_a(4.0F);
            return false;
         } else {
            boolean lvt_3_1_ = "arrow".equals(p_70097_1_.func_76355_l());
            boolean lvt_4_1_ = "player".equals(p_70097_1_.func_76355_l());
            if (!lvt_4_1_ && !lvt_3_1_) {
               return false;
            } else {
               if (p_70097_1_.func_76364_f() instanceof EntityArrow) {
                  p_70097_1_.func_76364_f().func_70106_y();
               }

               if (p_70097_1_.func_76346_g() instanceof EntityPlayer && !((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75099_e) {
                  return false;
               } else if (p_70097_1_.func_180136_u()) {
                  this.func_175412_z();
                  this.func_70106_y();
                  return false;
               } else {
                  long lvt_5_1_ = this.field_70170_p.func_82737_E();
                  if (lvt_5_1_ - this.field_175437_i > 5L && !lvt_3_1_) {
                     this.field_175437_i = lvt_5_1_;
                  } else {
                     this.func_175421_A();
                     this.func_175412_z();
                     this.func_70106_y();
                  }

                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public boolean func_70112_a(double p_70112_1_) {
      double lvt_3_1_ = this.func_174813_aQ().func_72320_b() * 4.0D;
      if (Double.isNaN(lvt_3_1_) || lvt_3_1_ == 0.0D) {
         lvt_3_1_ = 4.0D;
      }

      lvt_3_1_ *= 64.0D;
      return p_70112_1_ < lvt_3_1_ * lvt_3_1_;
   }

   private void func_175412_z() {
      if (this.field_70170_p instanceof WorldServer) {
         ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.BLOCK_DUST, this.field_70165_t, this.field_70163_u + (double)this.field_70131_O / 1.5D, this.field_70161_v, 10, (double)(this.field_70130_N / 4.0F), (double)(this.field_70131_O / 4.0F), (double)(this.field_70130_N / 4.0F), 0.05D, Block.func_176210_f(Blocks.field_150344_f.func_176223_P()));
      }

   }

   private void func_175406_a(float p_175406_1_) {
      float lvt_2_1_ = this.func_110143_aJ();
      lvt_2_1_ -= p_175406_1_;
      if (lvt_2_1_ <= 0.5F) {
         this.func_175409_C();
         this.func_70106_y();
      } else {
         this.func_70606_j(lvt_2_1_);
      }

   }

   private void func_175421_A() {
      Block.func_180635_a(this.field_70170_p, new BlockPos(this), new ItemStack(Items.field_179565_cj));
      this.func_175409_C();
   }

   private void func_175409_C() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_175430_g.length; ++lvt_1_1_) {
         if (this.field_175430_g[lvt_1_1_] != null && this.field_175430_g[lvt_1_1_].field_77994_a > 0) {
            if (this.field_175430_g[lvt_1_1_] != null) {
               Block.func_180635_a(this.field_70170_p, (new BlockPos(this)).func_177984_a(), this.field_175430_g[lvt_1_1_]);
            }

            this.field_175430_g[lvt_1_1_] = null;
         }
      }

   }

   protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
      this.field_70760_ar = this.field_70126_B;
      this.field_70761_aq = this.field_70177_z;
      return 0.0F;
   }

   public float func_70047_e() {
      return this.func_70631_g_() ? this.field_70131_O * 0.5F : this.field_70131_O * 0.9F;
   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      if (!this.func_175423_p()) {
         super.func_70612_e(p_70612_1_, p_70612_2_);
      }
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      Rotations lvt_1_1_ = this.field_70180_af.func_180115_h(11);
      if (!this.field_175443_bh.equals(lvt_1_1_)) {
         this.func_175415_a(lvt_1_1_);
      }

      Rotations lvt_2_1_ = this.field_70180_af.func_180115_h(12);
      if (!this.field_175444_bi.equals(lvt_2_1_)) {
         this.func_175424_b(lvt_2_1_);
      }

      Rotations lvt_3_1_ = this.field_70180_af.func_180115_h(13);
      if (!this.field_175438_bj.equals(lvt_3_1_)) {
         this.func_175405_c(lvt_3_1_);
      }

      Rotations lvt_4_1_ = this.field_70180_af.func_180115_h(14);
      if (!this.field_175439_bk.equals(lvt_4_1_)) {
         this.func_175428_d(lvt_4_1_);
      }

      Rotations lvt_5_1_ = this.field_70180_af.func_180115_h(15);
      if (!this.field_175440_bl.equals(lvt_5_1_)) {
         this.func_175417_e(lvt_5_1_);
      }

      Rotations lvt_6_1_ = this.field_70180_af.func_180115_h(16);
      if (!this.field_175441_bm.equals(lvt_6_1_)) {
         this.func_175427_f(lvt_6_1_);
      }

      boolean lvt_7_1_ = this.func_181026_s();
      if (!this.field_181028_bj && lvt_7_1_) {
         this.func_181550_a(false);
      } else {
         if (!this.field_181028_bj || lvt_7_1_) {
            return;
         }

         this.func_181550_a(true);
      }

      this.field_181028_bj = lvt_7_1_;
   }

   private void func_181550_a(boolean p_181550_1_) {
      double lvt_2_1_ = this.field_70165_t;
      double lvt_4_1_ = this.field_70163_u;
      double lvt_6_1_ = this.field_70161_v;
      if (p_181550_1_) {
         this.func_70105_a(0.5F, 1.975F);
      } else {
         this.func_70105_a(0.0F, 0.0F);
      }

      this.func_70107_b(lvt_2_1_, lvt_4_1_, lvt_6_1_);
   }

   protected void func_175135_B() {
      this.func_82142_c(this.field_175436_h);
   }

   public void func_82142_c(boolean p_82142_1_) {
      this.field_175436_h = p_82142_1_;
      super.func_82142_c(p_82142_1_);
   }

   public boolean func_70631_g_() {
      return this.func_175410_n();
   }

   public void func_174812_G() {
      this.func_70106_y();
   }

   public boolean func_180427_aV() {
      return this.func_82150_aj();
   }

   private void func_175420_a(boolean p_175420_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(10);
      if (p_175420_1_) {
         lvt_2_1_ = (byte)(lvt_2_1_ | 1);
      } else {
         lvt_2_1_ &= -2;
      }

      this.field_70180_af.func_75692_b(10, lvt_2_1_);
   }

   public boolean func_175410_n() {
      return (this.field_70180_af.func_75683_a(10) & 1) != 0;
   }

   private void func_175425_j(boolean p_175425_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(10);
      if (p_175425_1_) {
         lvt_2_1_ = (byte)(lvt_2_1_ | 2);
      } else {
         lvt_2_1_ &= -3;
      }

      this.field_70180_af.func_75692_b(10, lvt_2_1_);
   }

   public boolean func_175423_p() {
      return (this.field_70180_af.func_75683_a(10) & 2) != 0;
   }

   private void func_175413_k(boolean p_175413_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(10);
      if (p_175413_1_) {
         lvt_2_1_ = (byte)(lvt_2_1_ | 4);
      } else {
         lvt_2_1_ &= -5;
      }

      this.field_70180_af.func_75692_b(10, lvt_2_1_);
   }

   public boolean func_175402_q() {
      return (this.field_70180_af.func_75683_a(10) & 4) != 0;
   }

   private void func_175426_l(boolean p_175426_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(10);
      if (p_175426_1_) {
         lvt_2_1_ = (byte)(lvt_2_1_ | 8);
      } else {
         lvt_2_1_ &= -9;
      }

      this.field_70180_af.func_75692_b(10, lvt_2_1_);
   }

   public boolean func_175414_r() {
      return (this.field_70180_af.func_75683_a(10) & 8) != 0;
   }

   private void func_181027_m(boolean p_181027_1_) {
      byte lvt_2_1_ = this.field_70180_af.func_75683_a(10);
      if (p_181027_1_) {
         lvt_2_1_ = (byte)(lvt_2_1_ | 16);
      } else {
         lvt_2_1_ &= -17;
      }

      this.field_70180_af.func_75692_b(10, lvt_2_1_);
   }

   public boolean func_181026_s() {
      return (this.field_70180_af.func_75683_a(10) & 16) != 0;
   }

   public void func_175415_a(Rotations p_175415_1_) {
      this.field_175443_bh = p_175415_1_;
      this.field_70180_af.func_75692_b(11, p_175415_1_);
   }

   public void func_175424_b(Rotations p_175424_1_) {
      this.field_175444_bi = p_175424_1_;
      this.field_70180_af.func_75692_b(12, p_175424_1_);
   }

   public void func_175405_c(Rotations p_175405_1_) {
      this.field_175438_bj = p_175405_1_;
      this.field_70180_af.func_75692_b(13, p_175405_1_);
   }

   public void func_175428_d(Rotations p_175428_1_) {
      this.field_175439_bk = p_175428_1_;
      this.field_70180_af.func_75692_b(14, p_175428_1_);
   }

   public void func_175417_e(Rotations p_175417_1_) {
      this.field_175440_bl = p_175417_1_;
      this.field_70180_af.func_75692_b(15, p_175417_1_);
   }

   public void func_175427_f(Rotations p_175427_1_) {
      this.field_175441_bm = p_175427_1_;
      this.field_70180_af.func_75692_b(16, p_175427_1_);
   }

   public Rotations func_175418_s() {
      return this.field_175443_bh;
   }

   public Rotations func_175408_t() {
      return this.field_175444_bi;
   }

   public Rotations func_175404_u() {
      return this.field_175438_bj;
   }

   public Rotations func_175411_v() {
      return this.field_175439_bk;
   }

   public Rotations func_175403_w() {
      return this.field_175440_bl;
   }

   public Rotations func_175407_x() {
      return this.field_175441_bm;
   }

   public boolean func_70067_L() {
      return super.func_70067_L() && !this.func_181026_s();
   }
}
