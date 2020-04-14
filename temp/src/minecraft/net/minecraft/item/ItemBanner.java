package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBanner extends ItemBlock {
   public ItemBanner() {
      super(Blocks.field_180393_cK);
      this.field_77777_bU = 16;
      this.func_77637_a(CreativeTabs.field_78031_c);
      this.func_77627_a(true);
      this.func_77656_e(0);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (p_180614_5_ == EnumFacing.DOWN) {
         return false;
      } else if (!p_180614_3_.func_180495_p(p_180614_4_).func_177230_c().func_149688_o().func_76220_a()) {
         return false;
      } else {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
         if (!p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_)) {
            return false;
         } else if (!Blocks.field_180393_cK.func_176196_c(p_180614_3_, p_180614_4_)) {
            return false;
         } else if (p_180614_3_.field_72995_K) {
            return true;
         } else {
            if (p_180614_5_ == EnumFacing.UP) {
               int lvt_9_1_ = MathHelper.func_76128_c((double)((p_180614_2_.field_70177_z + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
               p_180614_3_.func_180501_a(p_180614_4_, Blocks.field_180393_cK.func_176223_P().func_177226_a(BlockStandingSign.field_176413_a, lvt_9_1_), 3);
            } else {
               p_180614_3_.func_180501_a(p_180614_4_, Blocks.field_180394_cL.func_176223_P().func_177226_a(BlockWallSign.field_176412_a, p_180614_5_), 3);
            }

            --p_180614_1_.field_77994_a;
            TileEntity lvt_9_2_ = p_180614_3_.func_175625_s(p_180614_4_);
            if (lvt_9_2_ instanceof TileEntityBanner) {
               ((TileEntityBanner)lvt_9_2_).func_175112_a(p_180614_1_);
            }

            return true;
         }
      }
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      String lvt_2_1_ = "item.banner.";
      EnumDyeColor lvt_3_1_ = this.func_179225_h(p_77653_1_);
      lvt_2_1_ = lvt_2_1_ + lvt_3_1_.func_176762_d() + ".name";
      return StatCollector.func_74838_a(lvt_2_1_);
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      NBTTagCompound lvt_5_1_ = p_77624_1_.func_179543_a("BlockEntityTag", false);
      if (lvt_5_1_ != null && lvt_5_1_.func_74764_b("Patterns")) {
         NBTTagList lvt_6_1_ = lvt_5_1_.func_150295_c("Patterns", 10);

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_.func_74745_c() && lvt_7_1_ < 6; ++lvt_7_1_) {
            NBTTagCompound lvt_8_1_ = lvt_6_1_.func_150305_b(lvt_7_1_);
            EnumDyeColor lvt_9_1_ = EnumDyeColor.func_176766_a(lvt_8_1_.func_74762_e("Color"));
            TileEntityBanner.EnumBannerPattern lvt_10_1_ = TileEntityBanner.EnumBannerPattern.func_177268_a(lvt_8_1_.func_74779_i("Pattern"));
            if (lvt_10_1_ != null) {
               p_77624_3_.add(StatCollector.func_74838_a("item.banner." + lvt_10_1_.func_177271_a() + "." + lvt_9_1_.func_176762_d()));
            }
         }

      }
   }

   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      if (p_82790_2_ == 0) {
         return 16777215;
      } else {
         EnumDyeColor lvt_3_1_ = this.func_179225_h(p_82790_1_);
         return lvt_3_1_.func_176768_e().field_76291_p;
      }
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      EnumDyeColor[] lvt_4_1_ = EnumDyeColor.values();
      int lvt_5_1_ = lvt_4_1_.length;

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
         EnumDyeColor lvt_7_1_ = lvt_4_1_[lvt_6_1_];
         NBTTagCompound lvt_8_1_ = new NBTTagCompound();
         TileEntityBanner.func_181020_a(lvt_8_1_, lvt_7_1_.func_176767_b(), (NBTTagList)null);
         NBTTagCompound lvt_9_1_ = new NBTTagCompound();
         lvt_9_1_.func_74782_a("BlockEntityTag", lvt_8_1_);
         ItemStack lvt_10_1_ = new ItemStack(p_150895_1_, 1, lvt_7_1_.func_176767_b());
         lvt_10_1_.func_77982_d(lvt_9_1_);
         p_150895_3_.add(lvt_10_1_);
      }

   }

   public CreativeTabs func_77640_w() {
      return CreativeTabs.field_78031_c;
   }

   private EnumDyeColor func_179225_h(ItemStack p_179225_1_) {
      NBTTagCompound lvt_2_1_ = p_179225_1_.func_179543_a("BlockEntityTag", false);
      EnumDyeColor lvt_3_1_ = null;
      if (lvt_2_1_ != null && lvt_2_1_.func_74764_b("Base")) {
         lvt_3_1_ = EnumDyeColor.func_176766_a(lvt_2_1_.func_74762_e("Base"));
      } else {
         lvt_3_1_ = EnumDyeColor.func_176766_a(p_179225_1_.func_77960_j());
      }

      return lvt_3_1_;
   }
}
