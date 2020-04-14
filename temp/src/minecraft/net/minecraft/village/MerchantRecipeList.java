package net.minecraft.village;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;

public class MerchantRecipeList extends ArrayList<MerchantRecipe> {
   public MerchantRecipeList() {
   }

   public MerchantRecipeList(NBTTagCompound p_i1944_1_) {
      this.func_77201_a(p_i1944_1_);
   }

   public MerchantRecipe func_77203_a(ItemStack p_77203_1_, ItemStack p_77203_2_, int p_77203_3_) {
      if (p_77203_3_ > 0 && p_77203_3_ < this.size()) {
         MerchantRecipe lvt_4_1_ = (MerchantRecipe)this.get(p_77203_3_);
         return !this.func_181078_a(p_77203_1_, lvt_4_1_.func_77394_a()) || (p_77203_2_ != null || lvt_4_1_.func_77398_c()) && (!lvt_4_1_.func_77398_c() || !this.func_181078_a(p_77203_2_, lvt_4_1_.func_77396_b())) || p_77203_1_.field_77994_a < lvt_4_1_.func_77394_a().field_77994_a || lvt_4_1_.func_77398_c() && p_77203_2_.field_77994_a < lvt_4_1_.func_77396_b().field_77994_a ? null : lvt_4_1_;
      } else {
         for(int lvt_4_2_ = 0; lvt_4_2_ < this.size(); ++lvt_4_2_) {
            MerchantRecipe lvt_5_1_ = (MerchantRecipe)this.get(lvt_4_2_);
            if (this.func_181078_a(p_77203_1_, lvt_5_1_.func_77394_a()) && p_77203_1_.field_77994_a >= lvt_5_1_.func_77394_a().field_77994_a && (!lvt_5_1_.func_77398_c() && p_77203_2_ == null || lvt_5_1_.func_77398_c() && this.func_181078_a(p_77203_2_, lvt_5_1_.func_77396_b()) && p_77203_2_.field_77994_a >= lvt_5_1_.func_77396_b().field_77994_a)) {
               return lvt_5_1_;
            }
         }

         return null;
      }
   }

   private boolean func_181078_a(ItemStack p_181078_1_, ItemStack p_181078_2_) {
      return ItemStack.func_179545_c(p_181078_1_, p_181078_2_) && (!p_181078_2_.func_77942_o() || p_181078_1_.func_77942_o() && NBTUtil.func_181123_a(p_181078_2_.func_77978_p(), p_181078_1_.func_77978_p(), false));
   }

   public void func_151391_a(PacketBuffer p_151391_1_) {
      p_151391_1_.writeByte((byte)(this.size() & 255));

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.size(); ++lvt_2_1_) {
         MerchantRecipe lvt_3_1_ = (MerchantRecipe)this.get(lvt_2_1_);
         p_151391_1_.func_150788_a(lvt_3_1_.func_77394_a());
         p_151391_1_.func_150788_a(lvt_3_1_.func_77397_d());
         ItemStack lvt_4_1_ = lvt_3_1_.func_77396_b();
         p_151391_1_.writeBoolean(lvt_4_1_ != null);
         if (lvt_4_1_ != null) {
            p_151391_1_.func_150788_a(lvt_4_1_);
         }

         p_151391_1_.writeBoolean(lvt_3_1_.func_82784_g());
         p_151391_1_.writeInt(lvt_3_1_.func_180321_e());
         p_151391_1_.writeInt(lvt_3_1_.func_180320_f());
      }

   }

   public static MerchantRecipeList func_151390_b(PacketBuffer p_151390_0_) throws IOException {
      MerchantRecipeList lvt_1_1_ = new MerchantRecipeList();
      int lvt_2_1_ = p_151390_0_.readByte() & 255;

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = p_151390_0_.func_150791_c();
         ItemStack lvt_5_1_ = p_151390_0_.func_150791_c();
         ItemStack lvt_6_1_ = null;
         if (p_151390_0_.readBoolean()) {
            lvt_6_1_ = p_151390_0_.func_150791_c();
         }

         boolean lvt_7_1_ = p_151390_0_.readBoolean();
         int lvt_8_1_ = p_151390_0_.readInt();
         int lvt_9_1_ = p_151390_0_.readInt();
         MerchantRecipe lvt_10_1_ = new MerchantRecipe(lvt_4_1_, lvt_6_1_, lvt_5_1_, lvt_8_1_, lvt_9_1_);
         if (lvt_7_1_) {
            lvt_10_1_.func_82785_h();
         }

         lvt_1_1_.add(lvt_10_1_);
      }

      return lvt_1_1_;
   }

   public void func_77201_a(NBTTagCompound p_77201_1_) {
      NBTTagList lvt_2_1_ = p_77201_1_.func_150295_c("Recipes", 10);

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
         this.add(new MerchantRecipe(lvt_4_1_));
      }

   }

   public NBTTagCompound func_77202_a() {
      NBTTagCompound lvt_1_1_ = new NBTTagCompound();
      NBTTagList lvt_2_1_ = new NBTTagList();

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.size(); ++lvt_3_1_) {
         MerchantRecipe lvt_4_1_ = (MerchantRecipe)this.get(lvt_3_1_);
         lvt_2_1_.func_74742_a(lvt_4_1_.func_77395_g());
      }

      lvt_1_1_.func_74782_a("Recipes", lvt_2_1_);
      return lvt_1_1_;
   }
}
