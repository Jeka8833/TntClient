package net.minecraft.server.management;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;

public class ItemInWorldManager {
   public World field_73092_a;
   public EntityPlayerMP field_73090_b;
   private WorldSettings.GameType field_73091_c;
   private boolean field_73088_d;
   private int field_73089_e;
   private BlockPos field_180240_f;
   private int field_73100_i;
   private boolean field_73097_j;
   private BlockPos field_180241_i;
   private int field_73093_n;
   private int field_73094_o;

   public ItemInWorldManager(World p_i1524_1_) {
      this.field_73091_c = WorldSettings.GameType.NOT_SET;
      this.field_180240_f = BlockPos.field_177992_a;
      this.field_180241_i = BlockPos.field_177992_a;
      this.field_73094_o = -1;
      this.field_73092_a = p_i1524_1_;
   }

   public void func_73076_a(WorldSettings.GameType p_73076_1_) {
      this.field_73091_c = p_73076_1_;
      p_73076_1_.func_77147_a(this.field_73090_b.field_71075_bZ);
      this.field_73090_b.func_71016_p();
      this.field_73090_b.field_71133_b.func_71203_ab().func_148540_a(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_GAME_MODE, new EntityPlayerMP[]{this.field_73090_b}));
   }

   public WorldSettings.GameType func_73081_b() {
      return this.field_73091_c;
   }

   public boolean func_180239_c() {
      return this.field_73091_c.func_77144_e();
   }

   public boolean func_73083_d() {
      return this.field_73091_c.func_77145_d();
   }

   public void func_73077_b(WorldSettings.GameType p_73077_1_) {
      if (this.field_73091_c == WorldSettings.GameType.NOT_SET) {
         this.field_73091_c = p_73077_1_;
      }

      this.func_73076_a(this.field_73091_c);
   }

   public void func_73075_a() {
      ++this.field_73100_i;
      float lvt_3_1_;
      int lvt_4_2_;
      if (this.field_73097_j) {
         int lvt_1_1_ = this.field_73100_i - this.field_73093_n;
         Block lvt_2_1_ = this.field_73092_a.func_180495_p(this.field_180241_i).func_177230_c();
         if (lvt_2_1_.func_149688_o() == Material.field_151579_a) {
            this.field_73097_j = false;
         } else {
            lvt_3_1_ = lvt_2_1_.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, this.field_180241_i) * (float)(lvt_1_1_ + 1);
            lvt_4_2_ = (int)(lvt_3_1_ * 10.0F);
            if (lvt_4_2_ != this.field_73094_o) {
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180241_i, lvt_4_2_);
               this.field_73094_o = lvt_4_2_;
            }

            if (lvt_3_1_ >= 1.0F) {
               this.field_73097_j = false;
               this.func_180237_b(this.field_180241_i);
            }
         }
      } else if (this.field_73088_d) {
         Block lvt_1_2_ = this.field_73092_a.func_180495_p(this.field_180240_f).func_177230_c();
         if (lvt_1_2_.func_149688_o() == Material.field_151579_a) {
            this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, -1);
            this.field_73094_o = -1;
            this.field_73088_d = false;
         } else {
            int lvt_2_2_ = this.field_73100_i - this.field_73089_e;
            lvt_3_1_ = lvt_1_2_.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, this.field_180241_i) * (float)(lvt_2_2_ + 1);
            lvt_4_2_ = (int)(lvt_3_1_ * 10.0F);
            if (lvt_4_2_ != this.field_73094_o) {
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, lvt_4_2_);
               this.field_73094_o = lvt_4_2_;
            }
         }
      }

   }

   public void func_180784_a(BlockPos p_180784_1_, EnumFacing p_180784_2_) {
      if (this.func_73083_d()) {
         if (!this.field_73092_a.func_175719_a((EntityPlayer)null, p_180784_1_, p_180784_2_)) {
            this.func_180237_b(p_180784_1_);
         }

      } else {
         Block lvt_3_1_ = this.field_73092_a.func_180495_p(p_180784_1_).func_177230_c();
         if (this.field_73091_c.func_82752_c()) {
            if (this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
               return;
            }

            if (!this.field_73090_b.func_175142_cm()) {
               ItemStack lvt_4_1_ = this.field_73090_b.func_71045_bC();
               if (lvt_4_1_ == null) {
                  return;
               }

               if (!lvt_4_1_.func_179544_c(lvt_3_1_)) {
                  return;
               }
            }
         }

         this.field_73092_a.func_175719_a((EntityPlayer)null, p_180784_1_, p_180784_2_);
         this.field_73089_e = this.field_73100_i;
         float lvt_4_2_ = 1.0F;
         if (lvt_3_1_.func_149688_o() != Material.field_151579_a) {
            lvt_3_1_.func_180649_a(this.field_73092_a, p_180784_1_, this.field_73090_b);
            lvt_4_2_ = lvt_3_1_.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, p_180784_1_);
         }

         if (lvt_3_1_.func_149688_o() != Material.field_151579_a && lvt_4_2_ >= 1.0F) {
            this.func_180237_b(p_180784_1_);
         } else {
            this.field_73088_d = true;
            this.field_180240_f = p_180784_1_;
            int lvt_5_1_ = (int)(lvt_4_2_ * 10.0F);
            this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), p_180784_1_, lvt_5_1_);
            this.field_73094_o = lvt_5_1_;
         }

      }
   }

   public void func_180785_a(BlockPos p_180785_1_) {
      if (p_180785_1_.equals(this.field_180240_f)) {
         int lvt_2_1_ = this.field_73100_i - this.field_73089_e;
         Block lvt_3_1_ = this.field_73092_a.func_180495_p(p_180785_1_).func_177230_c();
         if (lvt_3_1_.func_149688_o() != Material.field_151579_a) {
            float lvt_4_1_ = lvt_3_1_.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, p_180785_1_) * (float)(lvt_2_1_ + 1);
            if (lvt_4_1_ >= 0.7F) {
               this.field_73088_d = false;
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), p_180785_1_, -1);
               this.func_180237_b(p_180785_1_);
            } else if (!this.field_73097_j) {
               this.field_73088_d = false;
               this.field_73097_j = true;
               this.field_180241_i = p_180785_1_;
               this.field_73093_n = this.field_73089_e;
            }
         }
      }

   }

   public void func_180238_e() {
      this.field_73088_d = false;
      this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, -1);
   }

   private boolean func_180235_c(BlockPos p_180235_1_) {
      IBlockState lvt_2_1_ = this.field_73092_a.func_180495_p(p_180235_1_);
      lvt_2_1_.func_177230_c().func_176208_a(this.field_73092_a, p_180235_1_, lvt_2_1_, this.field_73090_b);
      boolean lvt_3_1_ = this.field_73092_a.func_175698_g(p_180235_1_);
      if (lvt_3_1_) {
         lvt_2_1_.func_177230_c().func_176206_d(this.field_73092_a, p_180235_1_, lvt_2_1_);
      }

      return lvt_3_1_;
   }

   public boolean func_180237_b(BlockPos p_180237_1_) {
      if (this.field_73091_c.func_77145_d() && this.field_73090_b.func_70694_bm() != null && this.field_73090_b.func_70694_bm().func_77973_b() instanceof ItemSword) {
         return false;
      } else {
         IBlockState lvt_2_1_ = this.field_73092_a.func_180495_p(p_180237_1_);
         TileEntity lvt_3_1_ = this.field_73092_a.func_175625_s(p_180237_1_);
         if (this.field_73091_c.func_82752_c()) {
            if (this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
               return false;
            }

            if (!this.field_73090_b.func_175142_cm()) {
               ItemStack lvt_4_1_ = this.field_73090_b.func_71045_bC();
               if (lvt_4_1_ == null) {
                  return false;
               }

               if (!lvt_4_1_.func_179544_c(lvt_2_1_.func_177230_c())) {
                  return false;
               }
            }
         }

         this.field_73092_a.func_180498_a(this.field_73090_b, 2001, p_180237_1_, Block.func_176210_f(lvt_2_1_));
         boolean lvt_4_2_ = this.func_180235_c(p_180237_1_);
         if (this.func_73083_d()) {
            this.field_73090_b.field_71135_a.func_147359_a(new S23PacketBlockChange(this.field_73092_a, p_180237_1_));
         } else {
            ItemStack lvt_5_1_ = this.field_73090_b.func_71045_bC();
            boolean lvt_6_1_ = this.field_73090_b.func_146099_a(lvt_2_1_.func_177230_c());
            if (lvt_5_1_ != null) {
               lvt_5_1_.func_179548_a(this.field_73092_a, lvt_2_1_.func_177230_c(), p_180237_1_, this.field_73090_b);
               if (lvt_5_1_.field_77994_a == 0) {
                  this.field_73090_b.func_71028_bD();
               }
            }

            if (lvt_4_2_ && lvt_6_1_) {
               lvt_2_1_.func_177230_c().func_180657_a(this.field_73092_a, this.field_73090_b, p_180237_1_, lvt_2_1_, lvt_3_1_);
            }
         }

         return lvt_4_2_;
      }
   }

   public boolean func_73085_a(EntityPlayer p_73085_1_, World p_73085_2_, ItemStack p_73085_3_) {
      if (this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
         return false;
      } else {
         int lvt_4_1_ = p_73085_3_.field_77994_a;
         int lvt_5_1_ = p_73085_3_.func_77960_j();
         ItemStack lvt_6_1_ = p_73085_3_.func_77957_a(p_73085_2_, p_73085_1_);
         if (lvt_6_1_ != p_73085_3_ || lvt_6_1_ != null && (lvt_6_1_.field_77994_a != lvt_4_1_ || lvt_6_1_.func_77988_m() > 0 || lvt_6_1_.func_77960_j() != lvt_5_1_)) {
            p_73085_1_.field_71071_by.field_70462_a[p_73085_1_.field_71071_by.field_70461_c] = lvt_6_1_;
            if (this.func_73083_d()) {
               lvt_6_1_.field_77994_a = lvt_4_1_;
               if (lvt_6_1_.func_77984_f()) {
                  lvt_6_1_.func_77964_b(lvt_5_1_);
               }
            }

            if (lvt_6_1_.field_77994_a == 0) {
               p_73085_1_.field_71071_by.field_70462_a[p_73085_1_.field_71071_by.field_70461_c] = null;
            }

            if (!p_73085_1_.func_71039_bw()) {
               ((EntityPlayerMP)p_73085_1_).func_71120_a(p_73085_1_.field_71069_bz);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean func_180236_a(EntityPlayer p_180236_1_, World p_180236_2_, ItemStack p_180236_3_, BlockPos p_180236_4_, EnumFacing p_180236_5_, float p_180236_6_, float p_180236_7_, float p_180236_8_) {
      if (this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
         TileEntity lvt_9_1_ = p_180236_2_.func_175625_s(p_180236_4_);
         if (lvt_9_1_ instanceof ILockableContainer) {
            Block lvt_10_1_ = p_180236_2_.func_180495_p(p_180236_4_).func_177230_c();
            ILockableContainer lvt_11_1_ = (ILockableContainer)lvt_9_1_;
            if (lvt_11_1_ instanceof TileEntityChest && lvt_10_1_ instanceof BlockChest) {
               lvt_11_1_ = ((BlockChest)lvt_10_1_).func_180676_d(p_180236_2_, p_180236_4_);
            }

            if (lvt_11_1_ != null) {
               p_180236_1_.func_71007_a(lvt_11_1_);
               return true;
            }
         } else if (lvt_9_1_ instanceof IInventory) {
            p_180236_1_.func_71007_a((IInventory)lvt_9_1_);
            return true;
         }

         return false;
      } else {
         if (!p_180236_1_.func_70093_af() || p_180236_1_.func_70694_bm() == null) {
            IBlockState lvt_9_2_ = p_180236_2_.func_180495_p(p_180236_4_);
            if (lvt_9_2_.func_177230_c().func_180639_a(p_180236_2_, p_180236_4_, lvt_9_2_, p_180236_1_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_)) {
               return true;
            }
         }

         if (p_180236_3_ == null) {
            return false;
         } else if (this.func_73083_d()) {
            int lvt_9_3_ = p_180236_3_.func_77960_j();
            int lvt_10_2_ = p_180236_3_.field_77994_a;
            boolean lvt_11_2_ = p_180236_3_.func_179546_a(p_180236_1_, p_180236_2_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
            p_180236_3_.func_77964_b(lvt_9_3_);
            p_180236_3_.field_77994_a = lvt_10_2_;
            return lvt_11_2_;
         } else {
            return p_180236_3_.func_179546_a(p_180236_1_, p_180236_2_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
         }
      }
   }

   public void func_73080_a(WorldServer p_73080_1_) {
      this.field_73092_a = p_73080_1_;
   }
}
