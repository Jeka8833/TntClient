package net.minecraft.nbt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.util.StringUtils;

public final class NBTUtil {
   public static GameProfile func_152459_a(NBTTagCompound p_152459_0_) {
      String lvt_1_1_ = null;
      String lvt_2_1_ = null;
      if (p_152459_0_.func_150297_b("Name", 8)) {
         lvt_1_1_ = p_152459_0_.func_74779_i("Name");
      }

      if (p_152459_0_.func_150297_b("Id", 8)) {
         lvt_2_1_ = p_152459_0_.func_74779_i("Id");
      }

      if (StringUtils.func_151246_b(lvt_1_1_) && StringUtils.func_151246_b(lvt_2_1_)) {
         return null;
      } else {
         UUID lvt_3_2_;
         try {
            lvt_3_2_ = UUID.fromString(lvt_2_1_);
         } catch (Throwable var12) {
            lvt_3_2_ = null;
         }

         GameProfile lvt_4_2_ = new GameProfile(lvt_3_2_, lvt_1_1_);
         if (p_152459_0_.func_150297_b("Properties", 10)) {
            NBTTagCompound lvt_5_1_ = p_152459_0_.func_74775_l("Properties");
            Iterator lvt_6_1_ = lvt_5_1_.func_150296_c().iterator();

            while(lvt_6_1_.hasNext()) {
               String lvt_7_1_ = (String)lvt_6_1_.next();
               NBTTagList lvt_8_1_ = lvt_5_1_.func_150295_c(lvt_7_1_, 10);

               for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_.func_74745_c(); ++lvt_9_1_) {
                  NBTTagCompound lvt_10_1_ = lvt_8_1_.func_150305_b(lvt_9_1_);
                  String lvt_11_1_ = lvt_10_1_.func_74779_i("Value");
                  if (lvt_10_1_.func_150297_b("Signature", 8)) {
                     lvt_4_2_.getProperties().put(lvt_7_1_, new Property(lvt_7_1_, lvt_11_1_, lvt_10_1_.func_74779_i("Signature")));
                  } else {
                     lvt_4_2_.getProperties().put(lvt_7_1_, new Property(lvt_7_1_, lvt_11_1_));
                  }
               }
            }
         }

         return lvt_4_2_;
      }
   }

   public static NBTTagCompound func_180708_a(NBTTagCompound p_180708_0_, GameProfile p_180708_1_) {
      if (!StringUtils.func_151246_b(p_180708_1_.getName())) {
         p_180708_0_.func_74778_a("Name", p_180708_1_.getName());
      }

      if (p_180708_1_.getId() != null) {
         p_180708_0_.func_74778_a("Id", p_180708_1_.getId().toString());
      }

      if (!p_180708_1_.getProperties().isEmpty()) {
         NBTTagCompound lvt_2_1_ = new NBTTagCompound();
         Iterator lvt_3_1_ = p_180708_1_.getProperties().keySet().iterator();

         while(lvt_3_1_.hasNext()) {
            String lvt_4_1_ = (String)lvt_3_1_.next();
            NBTTagList lvt_5_1_ = new NBTTagList();

            NBTTagCompound lvt_8_1_;
            for(Iterator lvt_6_1_ = p_180708_1_.getProperties().get(lvt_4_1_).iterator(); lvt_6_1_.hasNext(); lvt_5_1_.func_74742_a(lvt_8_1_)) {
               Property lvt_7_1_ = (Property)lvt_6_1_.next();
               lvt_8_1_ = new NBTTagCompound();
               lvt_8_1_.func_74778_a("Value", lvt_7_1_.getValue());
               if (lvt_7_1_.hasSignature()) {
                  lvt_8_1_.func_74778_a("Signature", lvt_7_1_.getSignature());
               }
            }

            lvt_2_1_.func_74782_a(lvt_4_1_, lvt_5_1_);
         }

         p_180708_0_.func_74782_a("Properties", lvt_2_1_);
      }

      return p_180708_0_;
   }

   public static boolean func_181123_a(NBTBase p_181123_0_, NBTBase p_181123_1_, boolean p_181123_2_) {
      if (p_181123_0_ == p_181123_1_) {
         return true;
      } else if (p_181123_0_ == null) {
         return true;
      } else if (p_181123_1_ == null) {
         return false;
      } else if (!p_181123_0_.getClass().equals(p_181123_1_.getClass())) {
         return false;
      } else if (p_181123_0_ instanceof NBTTagCompound) {
         NBTTagCompound lvt_3_1_ = (NBTTagCompound)p_181123_0_;
         NBTTagCompound lvt_4_1_ = (NBTTagCompound)p_181123_1_;
         Iterator lvt_5_1_ = lvt_3_1_.func_150296_c().iterator();

         String lvt_6_1_;
         NBTBase lvt_7_1_;
         do {
            if (!lvt_5_1_.hasNext()) {
               return true;
            }

            lvt_6_1_ = (String)lvt_5_1_.next();
            lvt_7_1_ = lvt_3_1_.func_74781_a(lvt_6_1_);
         } while(func_181123_a(lvt_7_1_, lvt_4_1_.func_74781_a(lvt_6_1_), p_181123_2_));

         return false;
      } else if (p_181123_0_ instanceof NBTTagList && p_181123_2_) {
         NBTTagList lvt_3_2_ = (NBTTagList)p_181123_0_;
         NBTTagList lvt_4_2_ = (NBTTagList)p_181123_1_;
         if (lvt_3_2_.func_74745_c() == 0) {
            return lvt_4_2_.func_74745_c() == 0;
         } else {
            for(int lvt_5_2_ = 0; lvt_5_2_ < lvt_3_2_.func_74745_c(); ++lvt_5_2_) {
               NBTBase lvt_6_2_ = lvt_3_2_.func_179238_g(lvt_5_2_);
               boolean lvt_7_2_ = false;

               for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_4_2_.func_74745_c(); ++lvt_8_1_) {
                  if (func_181123_a(lvt_6_2_, lvt_4_2_.func_179238_g(lvt_8_1_), p_181123_2_)) {
                     lvt_7_2_ = true;
                     break;
                  }
               }

               if (!lvt_7_2_) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return p_181123_0_.equals(p_181123_1_);
      }
   }
}
