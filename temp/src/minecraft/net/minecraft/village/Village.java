package net.minecraft.village;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Village {
   private World field_75586_a;
   private final List<VillageDoorInfo> field_75584_b = Lists.newArrayList();
   private BlockPos field_75585_c;
   private BlockPos field_75582_d;
   private int field_75583_e;
   private int field_75580_f;
   private int field_75581_g;
   private int field_75588_h;
   private int field_82694_i;
   private TreeMap<String, Integer> field_82693_j;
   private List<Village.VillageAggressor> field_75589_i;
   private int field_75587_j;

   public Village() {
      this.field_75585_c = BlockPos.field_177992_a;
      this.field_75582_d = BlockPos.field_177992_a;
      this.field_82693_j = new TreeMap();
      this.field_75589_i = Lists.newArrayList();
   }

   public Village(World p_i1675_1_) {
      this.field_75585_c = BlockPos.field_177992_a;
      this.field_75582_d = BlockPos.field_177992_a;
      this.field_82693_j = new TreeMap();
      this.field_75589_i = Lists.newArrayList();
      this.field_75586_a = p_i1675_1_;
   }

   public void func_82691_a(World p_82691_1_) {
      this.field_75586_a = p_82691_1_;
   }

   public void func_75560_a(int p_75560_1_) {
      this.field_75581_g = p_75560_1_;
      this.func_75557_k();
      this.func_75565_j();
      if (p_75560_1_ % 20 == 0) {
         this.func_75572_i();
      }

      if (p_75560_1_ % 30 == 0) {
         this.func_75579_h();
      }

      int lvt_2_1_ = this.field_75588_h / 10;
      if (this.field_75587_j < lvt_2_1_ && this.field_75584_b.size() > 20 && this.field_75586_a.field_73012_v.nextInt(7000) == 0) {
         Vec3 lvt_3_1_ = this.func_179862_a(this.field_75582_d, 2, 4, 2);
         if (lvt_3_1_ != null) {
            EntityIronGolem lvt_4_1_ = new EntityIronGolem(this.field_75586_a);
            lvt_4_1_.func_70107_b(lvt_3_1_.field_72450_a, lvt_3_1_.field_72448_b, lvt_3_1_.field_72449_c);
            this.field_75586_a.func_72838_d(lvt_4_1_);
            ++this.field_75587_j;
         }
      }

   }

   private Vec3 func_179862_a(BlockPos p_179862_1_, int p_179862_2_, int p_179862_3_, int p_179862_4_) {
      for(int lvt_5_1_ = 0; lvt_5_1_ < 10; ++lvt_5_1_) {
         BlockPos lvt_6_1_ = p_179862_1_.func_177982_a(this.field_75586_a.field_73012_v.nextInt(16) - 8, this.field_75586_a.field_73012_v.nextInt(6) - 3, this.field_75586_a.field_73012_v.nextInt(16) - 8);
         if (this.func_179866_a(lvt_6_1_) && this.func_179861_a(new BlockPos(p_179862_2_, p_179862_3_, p_179862_4_), lvt_6_1_)) {
            return new Vec3((double)lvt_6_1_.func_177958_n(), (double)lvt_6_1_.func_177956_o(), (double)lvt_6_1_.func_177952_p());
         }
      }

      return null;
   }

   private boolean func_179861_a(BlockPos p_179861_1_, BlockPos p_179861_2_) {
      if (!World.func_175683_a(this.field_75586_a, p_179861_2_.func_177977_b())) {
         return false;
      } else {
         int lvt_3_1_ = p_179861_2_.func_177958_n() - p_179861_1_.func_177958_n() / 2;
         int lvt_4_1_ = p_179861_2_.func_177952_p() - p_179861_1_.func_177952_p() / 2;

         for(int lvt_5_1_ = lvt_3_1_; lvt_5_1_ < lvt_3_1_ + p_179861_1_.func_177958_n(); ++lvt_5_1_) {
            for(int lvt_6_1_ = p_179861_2_.func_177956_o(); lvt_6_1_ < p_179861_2_.func_177956_o() + p_179861_1_.func_177956_o(); ++lvt_6_1_) {
               for(int lvt_7_1_ = lvt_4_1_; lvt_7_1_ < lvt_4_1_ + p_179861_1_.func_177952_p(); ++lvt_7_1_) {
                  if (this.field_75586_a.func_180495_p(new BlockPos(lvt_5_1_, lvt_6_1_, lvt_7_1_)).func_177230_c().func_149721_r()) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private void func_75579_h() {
      List<EntityIronGolem> lvt_1_1_ = this.field_75586_a.func_72872_a(EntityIronGolem.class, new AxisAlignedBB((double)(this.field_75582_d.func_177958_n() - this.field_75583_e), (double)(this.field_75582_d.func_177956_o() - 4), (double)(this.field_75582_d.func_177952_p() - this.field_75583_e), (double)(this.field_75582_d.func_177958_n() + this.field_75583_e), (double)(this.field_75582_d.func_177956_o() + 4), (double)(this.field_75582_d.func_177952_p() + this.field_75583_e)));
      this.field_75587_j = lvt_1_1_.size();
   }

   private void func_75572_i() {
      List<EntityVillager> lvt_1_1_ = this.field_75586_a.func_72872_a(EntityVillager.class, new AxisAlignedBB((double)(this.field_75582_d.func_177958_n() - this.field_75583_e), (double)(this.field_75582_d.func_177956_o() - 4), (double)(this.field_75582_d.func_177952_p() - this.field_75583_e), (double)(this.field_75582_d.func_177958_n() + this.field_75583_e), (double)(this.field_75582_d.func_177956_o() + 4), (double)(this.field_75582_d.func_177952_p() + this.field_75583_e)));
      this.field_75588_h = lvt_1_1_.size();
      if (this.field_75588_h == 0) {
         this.field_82693_j.clear();
      }

   }

   public BlockPos func_180608_a() {
      return this.field_75582_d;
   }

   public int func_75568_b() {
      return this.field_75583_e;
   }

   public int func_75567_c() {
      return this.field_75584_b.size();
   }

   public int func_75561_d() {
      return this.field_75581_g - this.field_75580_f;
   }

   public int func_75562_e() {
      return this.field_75588_h;
   }

   public boolean func_179866_a(BlockPos p_179866_1_) {
      return this.field_75582_d.func_177951_i(p_179866_1_) < (double)(this.field_75583_e * this.field_75583_e);
   }

   public List<VillageDoorInfo> func_75558_f() {
      return this.field_75584_b;
   }

   public VillageDoorInfo func_179865_b(BlockPos p_179865_1_) {
      VillageDoorInfo lvt_2_1_ = null;
      int lvt_3_1_ = Integer.MAX_VALUE;
      Iterator lvt_4_1_ = this.field_75584_b.iterator();

      while(lvt_4_1_.hasNext()) {
         VillageDoorInfo lvt_5_1_ = (VillageDoorInfo)lvt_4_1_.next();
         int lvt_6_1_ = lvt_5_1_.func_179848_a(p_179865_1_);
         if (lvt_6_1_ < lvt_3_1_) {
            lvt_2_1_ = lvt_5_1_;
            lvt_3_1_ = lvt_6_1_;
         }
      }

      return lvt_2_1_;
   }

   public VillageDoorInfo func_179863_c(BlockPos p_179863_1_) {
      VillageDoorInfo lvt_2_1_ = null;
      int lvt_3_1_ = Integer.MAX_VALUE;
      Iterator lvt_4_1_ = this.field_75584_b.iterator();

      while(lvt_4_1_.hasNext()) {
         VillageDoorInfo lvt_5_1_ = (VillageDoorInfo)lvt_4_1_.next();
         int lvt_6_1_ = lvt_5_1_.func_179848_a(p_179863_1_);
         if (lvt_6_1_ > 256) {
            lvt_6_1_ *= 1000;
         } else {
            lvt_6_1_ = lvt_5_1_.func_75468_f();
         }

         if (lvt_6_1_ < lvt_3_1_) {
            lvt_2_1_ = lvt_5_1_;
            lvt_3_1_ = lvt_6_1_;
         }
      }

      return lvt_2_1_;
   }

   public VillageDoorInfo func_179864_e(BlockPos p_179864_1_) {
      if (this.field_75582_d.func_177951_i(p_179864_1_) > (double)(this.field_75583_e * this.field_75583_e)) {
         return null;
      } else {
         Iterator lvt_2_1_ = this.field_75584_b.iterator();

         VillageDoorInfo lvt_3_1_;
         do {
            if (!lvt_2_1_.hasNext()) {
               return null;
            }

            lvt_3_1_ = (VillageDoorInfo)lvt_2_1_.next();
         } while(lvt_3_1_.func_179852_d().func_177958_n() != p_179864_1_.func_177958_n() || lvt_3_1_.func_179852_d().func_177952_p() != p_179864_1_.func_177952_p() || Math.abs(lvt_3_1_.func_179852_d().func_177956_o() - p_179864_1_.func_177956_o()) > 1);

         return lvt_3_1_;
      }
   }

   public void func_75576_a(VillageDoorInfo p_75576_1_) {
      this.field_75584_b.add(p_75576_1_);
      this.field_75585_c = this.field_75585_c.func_177971_a(p_75576_1_.func_179852_d());
      this.func_75573_l();
      this.field_75580_f = p_75576_1_.func_75473_b();
   }

   public boolean func_75566_g() {
      return this.field_75584_b.isEmpty();
   }

   public void func_75575_a(EntityLivingBase p_75575_1_) {
      Iterator lvt_2_1_ = this.field_75589_i.iterator();

      Village.VillageAggressor lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            this.field_75589_i.add(new Village.VillageAggressor(p_75575_1_, this.field_75581_g));
            return;
         }

         lvt_3_1_ = (Village.VillageAggressor)lvt_2_1_.next();
      } while(lvt_3_1_.field_75592_a != p_75575_1_);

      lvt_3_1_.field_75590_b = this.field_75581_g;
   }

   public EntityLivingBase func_75571_b(EntityLivingBase p_75571_1_) {
      double lvt_2_1_ = Double.MAX_VALUE;
      Village.VillageAggressor lvt_4_1_ = null;

      for(int lvt_5_1_ = 0; lvt_5_1_ < this.field_75589_i.size(); ++lvt_5_1_) {
         Village.VillageAggressor lvt_6_1_ = (Village.VillageAggressor)this.field_75589_i.get(lvt_5_1_);
         double lvt_7_1_ = lvt_6_1_.field_75592_a.func_70068_e(p_75571_1_);
         if (lvt_7_1_ <= lvt_2_1_) {
            lvt_4_1_ = lvt_6_1_;
            lvt_2_1_ = lvt_7_1_;
         }
      }

      return lvt_4_1_ != null ? lvt_4_1_.field_75592_a : null;
   }

   public EntityPlayer func_82685_c(EntityLivingBase p_82685_1_) {
      double lvt_2_1_ = Double.MAX_VALUE;
      EntityPlayer lvt_4_1_ = null;
      Iterator lvt_5_1_ = this.field_82693_j.keySet().iterator();

      while(lvt_5_1_.hasNext()) {
         String lvt_6_1_ = (String)lvt_5_1_.next();
         if (this.func_82687_d(lvt_6_1_)) {
            EntityPlayer lvt_7_1_ = this.field_75586_a.func_72924_a(lvt_6_1_);
            if (lvt_7_1_ != null) {
               double lvt_8_1_ = lvt_7_1_.func_70068_e(p_82685_1_);
               if (lvt_8_1_ <= lvt_2_1_) {
                  lvt_4_1_ = lvt_7_1_;
                  lvt_2_1_ = lvt_8_1_;
               }
            }
         }
      }

      return lvt_4_1_;
   }

   private void func_75565_j() {
      Iterator lvt_1_1_ = this.field_75589_i.iterator();

      while(true) {
         Village.VillageAggressor lvt_2_1_;
         do {
            if (!lvt_1_1_.hasNext()) {
               return;
            }

            lvt_2_1_ = (Village.VillageAggressor)lvt_1_1_.next();
         } while(lvt_2_1_.field_75592_a.func_70089_S() && Math.abs(this.field_75581_g - lvt_2_1_.field_75590_b) <= 300);

         lvt_1_1_.remove();
      }
   }

   private void func_75557_k() {
      boolean lvt_1_1_ = false;
      boolean lvt_2_1_ = this.field_75586_a.field_73012_v.nextInt(50) == 0;
      Iterator lvt_3_1_ = this.field_75584_b.iterator();

      while(true) {
         VillageDoorInfo lvt_4_1_;
         do {
            if (!lvt_3_1_.hasNext()) {
               if (lvt_1_1_) {
                  this.func_75573_l();
               }

               return;
            }

            lvt_4_1_ = (VillageDoorInfo)lvt_3_1_.next();
            if (lvt_2_1_) {
               lvt_4_1_.func_75466_d();
            }
         } while(this.func_179860_f(lvt_4_1_.func_179852_d()) && Math.abs(this.field_75581_g - lvt_4_1_.func_75473_b()) <= 1200);

         this.field_75585_c = this.field_75585_c.func_177973_b(lvt_4_1_.func_179852_d());
         lvt_1_1_ = true;
         lvt_4_1_.func_179853_a(true);
         lvt_3_1_.remove();
      }
   }

   private boolean func_179860_f(BlockPos p_179860_1_) {
      Block lvt_2_1_ = this.field_75586_a.func_180495_p(p_179860_1_).func_177230_c();
      if (lvt_2_1_ instanceof BlockDoor) {
         return lvt_2_1_.func_149688_o() == Material.field_151575_d;
      } else {
         return false;
      }
   }

   private void func_75573_l() {
      int lvt_1_1_ = this.field_75584_b.size();
      if (lvt_1_1_ == 0) {
         this.field_75582_d = new BlockPos(0, 0, 0);
         this.field_75583_e = 0;
      } else {
         this.field_75582_d = new BlockPos(this.field_75585_c.func_177958_n() / lvt_1_1_, this.field_75585_c.func_177956_o() / lvt_1_1_, this.field_75585_c.func_177952_p() / lvt_1_1_);
         int lvt_2_1_ = 0;

         VillageDoorInfo lvt_4_1_;
         for(Iterator lvt_3_1_ = this.field_75584_b.iterator(); lvt_3_1_.hasNext(); lvt_2_1_ = Math.max(lvt_4_1_.func_179848_a(this.field_75582_d), lvt_2_1_)) {
            lvt_4_1_ = (VillageDoorInfo)lvt_3_1_.next();
         }

         this.field_75583_e = Math.max(32, (int)Math.sqrt((double)lvt_2_1_) + 1);
      }
   }

   public int func_82684_a(String p_82684_1_) {
      Integer lvt_2_1_ = (Integer)this.field_82693_j.get(p_82684_1_);
      return lvt_2_1_ != null ? lvt_2_1_ : 0;
   }

   public int func_82688_a(String p_82688_1_, int p_82688_2_) {
      int lvt_3_1_ = this.func_82684_a(p_82688_1_);
      int lvt_4_1_ = MathHelper.func_76125_a(lvt_3_1_ + p_82688_2_, -30, 10);
      this.field_82693_j.put(p_82688_1_, lvt_4_1_);
      return lvt_4_1_;
   }

   public boolean func_82687_d(String p_82687_1_) {
      return this.func_82684_a(p_82687_1_) <= -15;
   }

   public void func_82690_a(NBTTagCompound p_82690_1_) {
      this.field_75588_h = p_82690_1_.func_74762_e("PopSize");
      this.field_75583_e = p_82690_1_.func_74762_e("Radius");
      this.field_75587_j = p_82690_1_.func_74762_e("Golems");
      this.field_75580_f = p_82690_1_.func_74762_e("Stable");
      this.field_75581_g = p_82690_1_.func_74762_e("Tick");
      this.field_82694_i = p_82690_1_.func_74762_e("MTick");
      this.field_75582_d = new BlockPos(p_82690_1_.func_74762_e("CX"), p_82690_1_.func_74762_e("CY"), p_82690_1_.func_74762_e("CZ"));
      this.field_75585_c = new BlockPos(p_82690_1_.func_74762_e("ACX"), p_82690_1_.func_74762_e("ACY"), p_82690_1_.func_74762_e("ACZ"));
      NBTTagList lvt_2_1_ = p_82690_1_.func_150295_c("Doors", 10);

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
         VillageDoorInfo lvt_5_1_ = new VillageDoorInfo(new BlockPos(lvt_4_1_.func_74762_e("X"), lvt_4_1_.func_74762_e("Y"), lvt_4_1_.func_74762_e("Z")), lvt_4_1_.func_74762_e("IDX"), lvt_4_1_.func_74762_e("IDZ"), lvt_4_1_.func_74762_e("TS"));
         this.field_75584_b.add(lvt_5_1_);
      }

      NBTTagList lvt_3_2_ = p_82690_1_.func_150295_c("Players", 10);

      for(int lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_.func_74745_c(); ++lvt_4_2_) {
         NBTTagCompound lvt_5_2_ = lvt_3_2_.func_150305_b(lvt_4_2_);
         if (lvt_5_2_.func_74764_b("UUID")) {
            PlayerProfileCache lvt_6_1_ = MinecraftServer.func_71276_C().func_152358_ax();
            GameProfile lvt_7_1_ = lvt_6_1_.func_152652_a(UUID.fromString(lvt_5_2_.func_74779_i("UUID")));
            if (lvt_7_1_ != null) {
               this.field_82693_j.put(lvt_7_1_.getName(), lvt_5_2_.func_74762_e("S"));
            }
         } else {
            this.field_82693_j.put(lvt_5_2_.func_74779_i("Name"), lvt_5_2_.func_74762_e("S"));
         }
      }

   }

   public void func_82689_b(NBTTagCompound p_82689_1_) {
      p_82689_1_.func_74768_a("PopSize", this.field_75588_h);
      p_82689_1_.func_74768_a("Radius", this.field_75583_e);
      p_82689_1_.func_74768_a("Golems", this.field_75587_j);
      p_82689_1_.func_74768_a("Stable", this.field_75580_f);
      p_82689_1_.func_74768_a("Tick", this.field_75581_g);
      p_82689_1_.func_74768_a("MTick", this.field_82694_i);
      p_82689_1_.func_74768_a("CX", this.field_75582_d.func_177958_n());
      p_82689_1_.func_74768_a("CY", this.field_75582_d.func_177956_o());
      p_82689_1_.func_74768_a("CZ", this.field_75582_d.func_177952_p());
      p_82689_1_.func_74768_a("ACX", this.field_75585_c.func_177958_n());
      p_82689_1_.func_74768_a("ACY", this.field_75585_c.func_177956_o());
      p_82689_1_.func_74768_a("ACZ", this.field_75585_c.func_177952_p());
      NBTTagList lvt_2_1_ = new NBTTagList();
      Iterator lvt_3_1_ = this.field_75584_b.iterator();

      while(lvt_3_1_.hasNext()) {
         VillageDoorInfo lvt_4_1_ = (VillageDoorInfo)lvt_3_1_.next();
         NBTTagCompound lvt_5_1_ = new NBTTagCompound();
         lvt_5_1_.func_74768_a("X", lvt_4_1_.func_179852_d().func_177958_n());
         lvt_5_1_.func_74768_a("Y", lvt_4_1_.func_179852_d().func_177956_o());
         lvt_5_1_.func_74768_a("Z", lvt_4_1_.func_179852_d().func_177952_p());
         lvt_5_1_.func_74768_a("IDX", lvt_4_1_.func_179847_f());
         lvt_5_1_.func_74768_a("IDZ", lvt_4_1_.func_179855_g());
         lvt_5_1_.func_74768_a("TS", lvt_4_1_.func_75473_b());
         lvt_2_1_.func_74742_a(lvt_5_1_);
      }

      p_82689_1_.func_74782_a("Doors", lvt_2_1_);
      NBTTagList lvt_3_2_ = new NBTTagList();
      Iterator lvt_4_2_ = this.field_82693_j.keySet().iterator();

      while(lvt_4_2_.hasNext()) {
         String lvt_5_2_ = (String)lvt_4_2_.next();
         NBTTagCompound lvt_6_1_ = new NBTTagCompound();
         PlayerProfileCache lvt_7_1_ = MinecraftServer.func_71276_C().func_152358_ax();
         GameProfile lvt_8_1_ = lvt_7_1_.func_152655_a(lvt_5_2_);
         if (lvt_8_1_ != null) {
            lvt_6_1_.func_74778_a("UUID", lvt_8_1_.getId().toString());
            lvt_6_1_.func_74768_a("S", (Integer)this.field_82693_j.get(lvt_5_2_));
            lvt_3_2_.func_74742_a(lvt_6_1_);
         }
      }

      p_82689_1_.func_74782_a("Players", lvt_3_2_);
   }

   public void func_82692_h() {
      this.field_82694_i = this.field_75581_g;
   }

   public boolean func_82686_i() {
      return this.field_82694_i == 0 || this.field_75581_g - this.field_82694_i >= 3600;
   }

   public void func_82683_b(int p_82683_1_) {
      Iterator lvt_2_1_ = this.field_82693_j.keySet().iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         this.func_82688_a(lvt_3_1_, p_82683_1_);
      }

   }

   class VillageAggressor {
      public EntityLivingBase field_75592_a;
      public int field_75590_b;

      VillageAggressor(EntityLivingBase p_i1674_2_, int p_i1674_3_) {
         this.field_75592_a = p_i1674_2_;
         this.field_75590_b = p_i1674_3_;
      }
   }
}
