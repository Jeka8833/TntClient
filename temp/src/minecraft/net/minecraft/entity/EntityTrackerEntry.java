package net.minecraft.entity;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTrackerEntry {
   private static final Logger field_151262_p = LogManager.getLogger();
   public Entity field_73132_a;
   public int field_73130_b;
   public int field_73131_c;
   public int field_73128_d;
   public int field_73129_e;
   public int field_73126_f;
   public int field_73127_g;
   public int field_73139_h;
   public int field_73140_i;
   public double field_73137_j;
   public double field_73138_k;
   public double field_73135_l;
   public int field_73136_m;
   private double field_73147_p;
   private double field_73146_q;
   private double field_73145_r;
   private boolean field_73144_s;
   private boolean field_73143_t;
   private int field_73142_u;
   private Entity field_85178_v;
   private boolean field_73141_v;
   private boolean field_180234_y;
   public boolean field_73133_n;
   public Set<EntityPlayerMP> field_73134_o = Sets.newHashSet();

   public EntityTrackerEntry(Entity p_i1525_1_, int p_i1525_2_, int p_i1525_3_, boolean p_i1525_4_) {
      this.field_73132_a = p_i1525_1_;
      this.field_73130_b = p_i1525_2_;
      this.field_73131_c = p_i1525_3_;
      this.field_73143_t = p_i1525_4_;
      this.field_73128_d = MathHelper.func_76128_c(p_i1525_1_.field_70165_t * 32.0D);
      this.field_73129_e = MathHelper.func_76128_c(p_i1525_1_.field_70163_u * 32.0D);
      this.field_73126_f = MathHelper.func_76128_c(p_i1525_1_.field_70161_v * 32.0D);
      this.field_73127_g = MathHelper.func_76141_d(p_i1525_1_.field_70177_z * 256.0F / 360.0F);
      this.field_73139_h = MathHelper.func_76141_d(p_i1525_1_.field_70125_A * 256.0F / 360.0F);
      this.field_73140_i = MathHelper.func_76141_d(p_i1525_1_.func_70079_am() * 256.0F / 360.0F);
      this.field_180234_y = p_i1525_1_.field_70122_E;
   }

   public boolean equals(Object p_equals_1_) {
      if (p_equals_1_ instanceof EntityTrackerEntry) {
         return ((EntityTrackerEntry)p_equals_1_).field_73132_a.func_145782_y() == this.field_73132_a.func_145782_y();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_73132_a.func_145782_y();
   }

   public void func_73122_a(List<EntityPlayer> p_73122_1_) {
      this.field_73133_n = false;
      if (!this.field_73144_s || this.field_73132_a.func_70092_e(this.field_73147_p, this.field_73146_q, this.field_73145_r) > 16.0D) {
         this.field_73147_p = this.field_73132_a.field_70165_t;
         this.field_73146_q = this.field_73132_a.field_70163_u;
         this.field_73145_r = this.field_73132_a.field_70161_v;
         this.field_73144_s = true;
         this.field_73133_n = true;
         this.func_73125_b(p_73122_1_);
      }

      if (this.field_85178_v != this.field_73132_a.field_70154_o || this.field_73132_a.field_70154_o != null && this.field_73136_m % 60 == 0) {
         this.field_85178_v = this.field_73132_a.field_70154_o;
         this.func_151259_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
      }

      if (this.field_73132_a instanceof EntityItemFrame && this.field_73136_m % 10 == 0) {
         EntityItemFrame lvt_2_1_ = (EntityItemFrame)this.field_73132_a;
         ItemStack lvt_3_1_ = lvt_2_1_.func_82335_i();
         if (lvt_3_1_ != null && lvt_3_1_.func_77973_b() instanceof ItemMap) {
            MapData lvt_4_1_ = Items.field_151098_aY.func_77873_a(lvt_3_1_, this.field_73132_a.field_70170_p);
            Iterator lvt_5_1_ = p_73122_1_.iterator();

            while(lvt_5_1_.hasNext()) {
               EntityPlayer lvt_6_1_ = (EntityPlayer)lvt_5_1_.next();
               EntityPlayerMP lvt_7_1_ = (EntityPlayerMP)lvt_6_1_;
               lvt_4_1_.func_76191_a(lvt_7_1_, lvt_3_1_);
               Packet lvt_8_1_ = Items.field_151098_aY.func_150911_c(lvt_3_1_, this.field_73132_a.field_70170_p, lvt_7_1_);
               if (lvt_8_1_ != null) {
                  lvt_7_1_.field_71135_a.func_147359_a(lvt_8_1_);
               }
            }
         }

         this.func_111190_b();
      }

      if (this.field_73136_m % this.field_73131_c == 0 || this.field_73132_a.field_70160_al || this.field_73132_a.func_70096_w().func_75684_a()) {
         int lvt_2_2_;
         int lvt_3_2_;
         if (this.field_73132_a.field_70154_o == null) {
            ++this.field_73142_u;
            lvt_2_2_ = MathHelper.func_76128_c(this.field_73132_a.field_70165_t * 32.0D);
            lvt_3_2_ = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
            int lvt_4_2_ = MathHelper.func_76128_c(this.field_73132_a.field_70161_v * 32.0D);
            int lvt_5_2_ = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
            int lvt_6_2_ = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
            int lvt_7_2_ = lvt_2_2_ - this.field_73128_d;
            int lvt_8_2_ = lvt_3_2_ - this.field_73129_e;
            int lvt_9_1_ = lvt_4_2_ - this.field_73126_f;
            Packet lvt_10_1_ = null;
            boolean lvt_11_1_ = Math.abs(lvt_7_2_) >= 4 || Math.abs(lvt_8_2_) >= 4 || Math.abs(lvt_9_1_) >= 4 || this.field_73136_m % 60 == 0;
            boolean lvt_12_1_ = Math.abs(lvt_5_2_ - this.field_73127_g) >= 4 || Math.abs(lvt_6_2_ - this.field_73139_h) >= 4;
            if (this.field_73136_m > 0 || this.field_73132_a instanceof EntityArrow) {
               if (lvt_7_2_ >= -128 && lvt_7_2_ < 128 && lvt_8_2_ >= -128 && lvt_8_2_ < 128 && lvt_9_1_ >= -128 && lvt_9_1_ < 128 && this.field_73142_u <= 400 && !this.field_73141_v && this.field_180234_y == this.field_73132_a.field_70122_E) {
                  if ((!lvt_11_1_ || !lvt_12_1_) && !(this.field_73132_a instanceof EntityArrow)) {
                     if (lvt_11_1_) {
                        lvt_10_1_ = new S14PacketEntity.S15PacketEntityRelMove(this.field_73132_a.func_145782_y(), (byte)lvt_7_2_, (byte)lvt_8_2_, (byte)lvt_9_1_, this.field_73132_a.field_70122_E);
                     } else if (lvt_12_1_) {
                        lvt_10_1_ = new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)lvt_5_2_, (byte)lvt_6_2_, this.field_73132_a.field_70122_E);
                     }
                  } else {
                     lvt_10_1_ = new S14PacketEntity.S17PacketEntityLookMove(this.field_73132_a.func_145782_y(), (byte)lvt_7_2_, (byte)lvt_8_2_, (byte)lvt_9_1_, (byte)lvt_5_2_, (byte)lvt_6_2_, this.field_73132_a.field_70122_E);
                  }
               } else {
                  this.field_180234_y = this.field_73132_a.field_70122_E;
                  this.field_73142_u = 0;
                  lvt_10_1_ = new S18PacketEntityTeleport(this.field_73132_a.func_145782_y(), lvt_2_2_, lvt_3_2_, lvt_4_2_, (byte)lvt_5_2_, (byte)lvt_6_2_, this.field_73132_a.field_70122_E);
               }
            }

            if (this.field_73143_t) {
               double lvt_13_1_ = this.field_73132_a.field_70159_w - this.field_73137_j;
               double lvt_15_1_ = this.field_73132_a.field_70181_x - this.field_73138_k;
               double lvt_17_1_ = this.field_73132_a.field_70179_y - this.field_73135_l;
               double lvt_19_1_ = 0.02D;
               double lvt_21_1_ = lvt_13_1_ * lvt_13_1_ + lvt_15_1_ * lvt_15_1_ + lvt_17_1_ * lvt_17_1_;
               if (lvt_21_1_ > lvt_19_1_ * lvt_19_1_ || lvt_21_1_ > 0.0D && this.field_73132_a.field_70159_w == 0.0D && this.field_73132_a.field_70181_x == 0.0D && this.field_73132_a.field_70179_y == 0.0D) {
                  this.field_73137_j = this.field_73132_a.field_70159_w;
                  this.field_73138_k = this.field_73132_a.field_70181_x;
                  this.field_73135_l = this.field_73132_a.field_70179_y;
                  this.func_151259_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73137_j, this.field_73138_k, this.field_73135_l));
               }
            }

            if (lvt_10_1_ != null) {
               this.func_151259_a((Packet)lvt_10_1_);
            }

            this.func_111190_b();
            if (lvt_11_1_) {
               this.field_73128_d = lvt_2_2_;
               this.field_73129_e = lvt_3_2_;
               this.field_73126_f = lvt_4_2_;
            }

            if (lvt_12_1_) {
               this.field_73127_g = lvt_5_2_;
               this.field_73139_h = lvt_6_2_;
            }

            this.field_73141_v = false;
         } else {
            lvt_2_2_ = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
            lvt_3_2_ = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
            boolean lvt_4_3_ = Math.abs(lvt_2_2_ - this.field_73127_g) >= 4 || Math.abs(lvt_3_2_ - this.field_73139_h) >= 4;
            if (lvt_4_3_) {
               this.func_151259_a(new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)lvt_2_2_, (byte)lvt_3_2_, this.field_73132_a.field_70122_E));
               this.field_73127_g = lvt_2_2_;
               this.field_73139_h = lvt_3_2_;
            }

            this.field_73128_d = MathHelper.func_76128_c(this.field_73132_a.field_70165_t * 32.0D);
            this.field_73129_e = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
            this.field_73126_f = MathHelper.func_76128_c(this.field_73132_a.field_70161_v * 32.0D);
            this.func_111190_b();
            this.field_73141_v = true;
         }

         lvt_2_2_ = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);
         if (Math.abs(lvt_2_2_ - this.field_73140_i) >= 4) {
            this.func_151259_a(new S19PacketEntityHeadLook(this.field_73132_a, (byte)lvt_2_2_));
            this.field_73140_i = lvt_2_2_;
         }

         this.field_73132_a.field_70160_al = false;
      }

      ++this.field_73136_m;
      if (this.field_73132_a.field_70133_I) {
         this.func_151261_b(new S12PacketEntityVelocity(this.field_73132_a));
         this.field_73132_a.field_70133_I = false;
      }

   }

   private void func_111190_b() {
      DataWatcher lvt_1_1_ = this.field_73132_a.func_70096_w();
      if (lvt_1_1_.func_75684_a()) {
         this.func_151261_b(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), lvt_1_1_, false));
      }

      if (this.field_73132_a instanceof EntityLivingBase) {
         ServersideAttributeMap lvt_2_1_ = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
         Set<IAttributeInstance> lvt_3_1_ = lvt_2_1_.func_111161_b();
         if (!lvt_3_1_.isEmpty()) {
            this.func_151261_b(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), lvt_3_1_));
         }

         lvt_3_1_.clear();
      }

   }

   public void func_151259_a(Packet p_151259_1_) {
      Iterator lvt_2_1_ = this.field_73134_o.iterator();

      while(lvt_2_1_.hasNext()) {
         EntityPlayerMP lvt_3_1_ = (EntityPlayerMP)lvt_2_1_.next();
         lvt_3_1_.field_71135_a.func_147359_a(p_151259_1_);
      }

   }

   public void func_151261_b(Packet p_151261_1_) {
      this.func_151259_a(p_151261_1_);
      if (this.field_73132_a instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.field_73132_a).field_71135_a.func_147359_a(p_151261_1_);
      }

   }

   public void func_73119_a() {
      Iterator lvt_1_1_ = this.field_73134_o.iterator();

      while(lvt_1_1_.hasNext()) {
         EntityPlayerMP lvt_2_1_ = (EntityPlayerMP)lvt_1_1_.next();
         lvt_2_1_.func_152339_d(this.field_73132_a);
      }

   }

   public void func_73118_a(EntityPlayerMP p_73118_1_) {
      if (this.field_73134_o.contains(p_73118_1_)) {
         p_73118_1_.func_152339_d(this.field_73132_a);
         this.field_73134_o.remove(p_73118_1_);
      }

   }

   public void func_73117_b(EntityPlayerMP p_73117_1_) {
      if (p_73117_1_ != this.field_73132_a) {
         if (this.func_180233_c(p_73117_1_)) {
            if (!this.field_73134_o.contains(p_73117_1_) && (this.func_73121_d(p_73117_1_) || this.field_73132_a.field_98038_p)) {
               this.field_73134_o.add(p_73117_1_);
               Packet lvt_2_1_ = this.func_151260_c();
               p_73117_1_.field_71135_a.func_147359_a(lvt_2_1_);
               if (!this.field_73132_a.func_70096_w().func_92085_d()) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), this.field_73132_a.func_70096_w(), true));
               }

               NBTTagCompound lvt_3_1_ = this.field_73132_a.func_174819_aU();
               if (lvt_3_1_ != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S49PacketUpdateEntityNBT(this.field_73132_a.func_145782_y(), lvt_3_1_));
               }

               if (this.field_73132_a instanceof EntityLivingBase) {
                  ServersideAttributeMap lvt_4_1_ = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
                  Collection<IAttributeInstance> lvt_5_1_ = lvt_4_1_.func_111160_c();
                  if (!lvt_5_1_.isEmpty()) {
                     p_73117_1_.field_71135_a.func_147359_a(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), lvt_5_1_));
                  }
               }

               this.field_73137_j = this.field_73132_a.field_70159_w;
               this.field_73138_k = this.field_73132_a.field_70181_x;
               this.field_73135_l = this.field_73132_a.field_70179_y;
               if (this.field_73143_t && !(lvt_2_1_ instanceof S0FPacketSpawnMob)) {
                  p_73117_1_.field_71135_a.func_147359_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73132_a.field_70159_w, this.field_73132_a.field_70181_x, this.field_73132_a.field_70179_y));
               }

               if (this.field_73132_a.field_70154_o != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
               }

               if (this.field_73132_a instanceof EntityLiving && ((EntityLiving)this.field_73132_a).func_110166_bE() != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(1, this.field_73132_a, ((EntityLiving)this.field_73132_a).func_110166_bE()));
               }

               if (this.field_73132_a instanceof EntityLivingBase) {
                  for(int lvt_4_2_ = 0; lvt_4_2_ < 5; ++lvt_4_2_) {
                     ItemStack lvt_5_2_ = ((EntityLivingBase)this.field_73132_a).func_71124_b(lvt_4_2_);
                     if (lvt_5_2_ != null) {
                        p_73117_1_.field_71135_a.func_147359_a(new S04PacketEntityEquipment(this.field_73132_a.func_145782_y(), lvt_4_2_, lvt_5_2_));
                     }
                  }
               }

               if (this.field_73132_a instanceof EntityPlayer) {
                  EntityPlayer lvt_4_3_ = (EntityPlayer)this.field_73132_a;
                  if (lvt_4_3_.func_70608_bn()) {
                     p_73117_1_.field_71135_a.func_147359_a(new S0APacketUseBed(lvt_4_3_, new BlockPos(this.field_73132_a)));
                  }
               }

               if (this.field_73132_a instanceof EntityLivingBase) {
                  EntityLivingBase lvt_4_4_ = (EntityLivingBase)this.field_73132_a;
                  Iterator lvt_5_3_ = lvt_4_4_.func_70651_bq().iterator();

                  while(lvt_5_3_.hasNext()) {
                     PotionEffect lvt_6_1_ = (PotionEffect)lvt_5_3_.next();
                     p_73117_1_.field_71135_a.func_147359_a(new S1DPacketEntityEffect(this.field_73132_a.func_145782_y(), lvt_6_1_));
                  }
               }
            }
         } else if (this.field_73134_o.contains(p_73117_1_)) {
            this.field_73134_o.remove(p_73117_1_);
            p_73117_1_.func_152339_d(this.field_73132_a);
         }

      }
   }

   public boolean func_180233_c(EntityPlayerMP p_180233_1_) {
      double lvt_2_1_ = p_180233_1_.field_70165_t - (double)(this.field_73128_d / 32);
      double lvt_4_1_ = p_180233_1_.field_70161_v - (double)(this.field_73126_f / 32);
      return lvt_2_1_ >= (double)(-this.field_73130_b) && lvt_2_1_ <= (double)this.field_73130_b && lvt_4_1_ >= (double)(-this.field_73130_b) && lvt_4_1_ <= (double)this.field_73130_b && this.field_73132_a.func_174827_a(p_180233_1_);
   }

   private boolean func_73121_d(EntityPlayerMP p_73121_1_) {
      return p_73121_1_.func_71121_q().func_73040_p().func_72694_a(p_73121_1_, this.field_73132_a.field_70176_ah, this.field_73132_a.field_70164_aj);
   }

   public void func_73125_b(List<EntityPlayer> p_73125_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_73125_1_.size(); ++lvt_2_1_) {
         this.func_73117_b((EntityPlayerMP)p_73125_1_.get(lvt_2_1_));
      }

   }

   private Packet func_151260_c() {
      if (this.field_73132_a.field_70128_L) {
         field_151262_p.warn("Fetching addPacket for removed entity");
      }

      if (this.field_73132_a instanceof EntityItem) {
         return new S0EPacketSpawnObject(this.field_73132_a, 2, 1);
      } else if (this.field_73132_a instanceof EntityPlayerMP) {
         return new S0CPacketSpawnPlayer((EntityPlayer)this.field_73132_a);
      } else if (this.field_73132_a instanceof EntityMinecart) {
         EntityMinecart lvt_1_1_ = (EntityMinecart)this.field_73132_a;
         return new S0EPacketSpawnObject(this.field_73132_a, 10, lvt_1_1_.func_180456_s().func_180039_a());
      } else if (this.field_73132_a instanceof EntityBoat) {
         return new S0EPacketSpawnObject(this.field_73132_a, 1);
      } else if (this.field_73132_a instanceof IAnimals) {
         this.field_73140_i = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);
         return new S0FPacketSpawnMob((EntityLivingBase)this.field_73132_a);
      } else if (this.field_73132_a instanceof EntityFishHook) {
         Entity lvt_1_2_ = ((EntityFishHook)this.field_73132_a).field_146042_b;
         return new S0EPacketSpawnObject(this.field_73132_a, 90, lvt_1_2_ != null ? lvt_1_2_.func_145782_y() : this.field_73132_a.func_145782_y());
      } else if (this.field_73132_a instanceof EntityArrow) {
         Entity lvt_1_3_ = ((EntityArrow)this.field_73132_a).field_70250_c;
         return new S0EPacketSpawnObject(this.field_73132_a, 60, lvt_1_3_ != null ? lvt_1_3_.func_145782_y() : this.field_73132_a.func_145782_y());
      } else if (this.field_73132_a instanceof EntitySnowball) {
         return new S0EPacketSpawnObject(this.field_73132_a, 61);
      } else if (this.field_73132_a instanceof EntityPotion) {
         return new S0EPacketSpawnObject(this.field_73132_a, 73, ((EntityPotion)this.field_73132_a).func_70196_i());
      } else if (this.field_73132_a instanceof EntityExpBottle) {
         return new S0EPacketSpawnObject(this.field_73132_a, 75);
      } else if (this.field_73132_a instanceof EntityEnderPearl) {
         return new S0EPacketSpawnObject(this.field_73132_a, 65);
      } else if (this.field_73132_a instanceof EntityEnderEye) {
         return new S0EPacketSpawnObject(this.field_73132_a, 72);
      } else if (this.field_73132_a instanceof EntityFireworkRocket) {
         return new S0EPacketSpawnObject(this.field_73132_a, 76);
      } else {
         S0EPacketSpawnObject lvt_2_3_;
         if (this.field_73132_a instanceof EntityFireball) {
            EntityFireball lvt_1_4_ = (EntityFireball)this.field_73132_a;
            lvt_2_3_ = null;
            int lvt_3_1_ = 63;
            if (this.field_73132_a instanceof EntitySmallFireball) {
               lvt_3_1_ = 64;
            } else if (this.field_73132_a instanceof EntityWitherSkull) {
               lvt_3_1_ = 66;
            }

            if (lvt_1_4_.field_70235_a != null) {
               lvt_2_3_ = new S0EPacketSpawnObject(this.field_73132_a, lvt_3_1_, ((EntityFireball)this.field_73132_a).field_70235_a.func_145782_y());
            } else {
               lvt_2_3_ = new S0EPacketSpawnObject(this.field_73132_a, lvt_3_1_, 0);
            }

            lvt_2_3_.func_149003_d((int)(lvt_1_4_.field_70232_b * 8000.0D));
            lvt_2_3_.func_149000_e((int)(lvt_1_4_.field_70233_c * 8000.0D));
            lvt_2_3_.func_149007_f((int)(lvt_1_4_.field_70230_d * 8000.0D));
            return lvt_2_3_;
         } else if (this.field_73132_a instanceof EntityEgg) {
            return new S0EPacketSpawnObject(this.field_73132_a, 62);
         } else if (this.field_73132_a instanceof EntityTNTPrimed) {
            return new S0EPacketSpawnObject(this.field_73132_a, 50);
         } else if (this.field_73132_a instanceof EntityEnderCrystal) {
            return new S0EPacketSpawnObject(this.field_73132_a, 51);
         } else if (this.field_73132_a instanceof EntityFallingBlock) {
            EntityFallingBlock lvt_1_5_ = (EntityFallingBlock)this.field_73132_a;
            return new S0EPacketSpawnObject(this.field_73132_a, 70, Block.func_176210_f(lvt_1_5_.func_175131_l()));
         } else if (this.field_73132_a instanceof EntityArmorStand) {
            return new S0EPacketSpawnObject(this.field_73132_a, 78);
         } else if (this.field_73132_a instanceof EntityPainting) {
            return new S10PacketSpawnPainting((EntityPainting)this.field_73132_a);
         } else {
            BlockPos lvt_3_3_;
            if (this.field_73132_a instanceof EntityItemFrame) {
               EntityItemFrame lvt_1_6_ = (EntityItemFrame)this.field_73132_a;
               lvt_2_3_ = new S0EPacketSpawnObject(this.field_73132_a, 71, lvt_1_6_.field_174860_b.func_176736_b());
               lvt_3_3_ = lvt_1_6_.func_174857_n();
               lvt_2_3_.func_148996_a(MathHelper.func_76141_d((float)(lvt_3_3_.func_177958_n() * 32)));
               lvt_2_3_.func_148995_b(MathHelper.func_76141_d((float)(lvt_3_3_.func_177956_o() * 32)));
               lvt_2_3_.func_149005_c(MathHelper.func_76141_d((float)(lvt_3_3_.func_177952_p() * 32)));
               return lvt_2_3_;
            } else if (this.field_73132_a instanceof EntityLeashKnot) {
               EntityLeashKnot lvt_1_7_ = (EntityLeashKnot)this.field_73132_a;
               lvt_2_3_ = new S0EPacketSpawnObject(this.field_73132_a, 77);
               lvt_3_3_ = lvt_1_7_.func_174857_n();
               lvt_2_3_.func_148996_a(MathHelper.func_76141_d((float)(lvt_3_3_.func_177958_n() * 32)));
               lvt_2_3_.func_148995_b(MathHelper.func_76141_d((float)(lvt_3_3_.func_177956_o() * 32)));
               lvt_2_3_.func_149005_c(MathHelper.func_76141_d((float)(lvt_3_3_.func_177952_p() * 32)));
               return lvt_2_3_;
            } else if (this.field_73132_a instanceof EntityXPOrb) {
               return new S11PacketSpawnExperienceOrb((EntityXPOrb)this.field_73132_a);
            } else {
               throw new IllegalArgumentException("Don't know how to add " + this.field_73132_a.getClass() + "!");
            }
         }
      }
   }

   public void func_73123_c(EntityPlayerMP p_73123_1_) {
      if (this.field_73134_o.contains(p_73123_1_)) {
         this.field_73134_o.remove(p_73123_1_);
         p_73123_1_.func_152339_d(this.field_73132_a);
      }

   }
}
