package net.minecraft.client.renderer.entity.layers;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StringUtils;

public class LayerCustomHead implements LayerRenderer<EntityLivingBase> {
   private final ModelRenderer field_177209_a;

   public LayerCustomHead(ModelRenderer p_i46120_1_) {
      this.field_177209_a = p_i46120_1_;
   }

   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      ItemStack lvt_9_1_ = p_177141_1_.func_82169_q(3);
      if (lvt_9_1_ != null && lvt_9_1_.func_77973_b() != null) {
         Item lvt_10_1_ = lvt_9_1_.func_77973_b();
         Minecraft lvt_11_1_ = Minecraft.func_71410_x();
         GlStateManager.func_179094_E();
         if (p_177141_1_.func_70093_af()) {
            GlStateManager.func_179109_b(0.0F, 0.2F, 0.0F);
         }

         boolean lvt_12_1_ = p_177141_1_ instanceof EntityVillager || p_177141_1_ instanceof EntityZombie && ((EntityZombie)p_177141_1_).func_82231_m();
         float lvt_13_3_;
         if (!lvt_12_1_ && p_177141_1_.func_70631_g_()) {
            lvt_13_3_ = 2.0F;
            float lvt_14_1_ = 1.4F;
            GlStateManager.func_179152_a(lvt_14_1_ / lvt_13_3_, lvt_14_1_ / lvt_13_3_, lvt_14_1_ / lvt_13_3_);
            GlStateManager.func_179109_b(0.0F, 16.0F * p_177141_8_, 0.0F);
         }

         this.field_177209_a.func_78794_c(0.0625F);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         if (lvt_10_1_ instanceof ItemBlock) {
            lvt_13_3_ = 0.625F;
            GlStateManager.func_179109_b(0.0F, -0.25F, 0.0F);
            GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179152_a(lvt_13_3_, -lvt_13_3_, -lvt_13_3_);
            if (lvt_12_1_) {
               GlStateManager.func_179109_b(0.0F, 0.1875F, 0.0F);
            }

            lvt_11_1_.func_175597_ag().func_178099_a(p_177141_1_, lvt_9_1_, ItemCameraTransforms.TransformType.HEAD);
         } else if (lvt_10_1_ == Items.field_151144_bL) {
            lvt_13_3_ = 1.1875F;
            GlStateManager.func_179152_a(lvt_13_3_, -lvt_13_3_, -lvt_13_3_);
            if (lvt_12_1_) {
               GlStateManager.func_179109_b(0.0F, 0.0625F, 0.0F);
            }

            GameProfile lvt_14_2_ = null;
            if (lvt_9_1_.func_77942_o()) {
               NBTTagCompound lvt_15_1_ = lvt_9_1_.func_77978_p();
               if (lvt_15_1_.func_150297_b("SkullOwner", 10)) {
                  lvt_14_2_ = NBTUtil.func_152459_a(lvt_15_1_.func_74775_l("SkullOwner"));
               } else if (lvt_15_1_.func_150297_b("SkullOwner", 8)) {
                  String lvt_16_1_ = lvt_15_1_.func_74779_i("SkullOwner");
                  if (!StringUtils.func_151246_b(lvt_16_1_)) {
                     lvt_14_2_ = TileEntitySkull.func_174884_b(new GameProfile((UUID)null, lvt_16_1_));
                     lvt_15_1_.func_74782_a("SkullOwner", NBTUtil.func_180708_a(new NBTTagCompound(), lvt_14_2_));
                  }
               }
            }

            TileEntitySkullRenderer.field_147536_b.func_180543_a(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, lvt_9_1_.func_77960_j(), lvt_14_2_, -1);
         }

         GlStateManager.func_179121_F();
      }
   }

   public boolean func_177142_b() {
      return true;
   }
}
