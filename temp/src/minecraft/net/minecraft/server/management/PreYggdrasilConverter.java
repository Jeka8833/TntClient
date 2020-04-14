package net.minecraft.server.management;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PreYggdrasilConverter {
   private static final Logger field_152732_e = LogManager.getLogger();
   public static final File field_152728_a = new File("banned-ips.txt");
   public static final File field_152729_b = new File("banned-players.txt");
   public static final File field_152730_c = new File("ops.txt");
   public static final File field_152731_d = new File("white-list.txt");

   private static void func_152717_a(MinecraftServer p_152717_0_, Collection<String> p_152717_1_, ProfileLookupCallback p_152717_2_) {
      String[] lvt_3_1_ = (String[])Iterators.toArray(Iterators.filter(p_152717_1_.iterator(), new Predicate<String>() {
         public boolean apply(String p_apply_1_) {
            return !StringUtils.func_151246_b(p_apply_1_);
         }
      }), String.class);
      if (p_152717_0_.func_71266_T()) {
         p_152717_0_.func_152359_aw().findProfilesByNames(lvt_3_1_, Agent.MINECRAFT, p_152717_2_);
      } else {
         String[] lvt_4_1_ = lvt_3_1_;
         int lvt_5_1_ = lvt_3_1_.length;

         for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
            String lvt_7_1_ = lvt_4_1_[lvt_6_1_];
            UUID lvt_8_1_ = EntityPlayer.func_146094_a(new GameProfile((UUID)null, lvt_7_1_));
            GameProfile lvt_9_1_ = new GameProfile(lvt_8_1_, lvt_7_1_);
            p_152717_2_.onProfileLookupSucceeded(lvt_9_1_);
         }
      }

   }

   public static String func_152719_a(String p_152719_0_) {
      if (!StringUtils.func_151246_b(p_152719_0_) && p_152719_0_.length() <= 16) {
         final MinecraftServer lvt_1_1_ = MinecraftServer.func_71276_C();
         GameProfile lvt_2_1_ = lvt_1_1_.func_152358_ax().func_152655_a(p_152719_0_);
         if (lvt_2_1_ != null && lvt_2_1_.getId() != null) {
            return lvt_2_1_.getId().toString();
         } else if (!lvt_1_1_.func_71264_H() && lvt_1_1_.func_71266_T()) {
            final List<GameProfile> lvt_3_1_ = Lists.newArrayList();
            ProfileLookupCallback lvt_4_1_ = new ProfileLookupCallback() {
               public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                  lvt_1_1_.func_152358_ax().func_152649_a(p_onProfileLookupSucceeded_1_);
                  lvt_3_1_.add(p_onProfileLookupSucceeded_1_);
               }

               public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                  PreYggdrasilConverter.field_152732_e.warn((String)("Could not lookup user whitelist entry for " + p_onProfileLookupFailed_1_.getName()), (Throwable)p_onProfileLookupFailed_2_);
               }
            };
            func_152717_a(lvt_1_1_, Lists.newArrayList((Object[])(p_152719_0_)), lvt_4_1_);
            return lvt_3_1_.size() > 0 && ((GameProfile)lvt_3_1_.get(0)).getId() != null ? ((GameProfile)lvt_3_1_.get(0)).getId().toString() : "";
         } else {
            return EntityPlayer.func_146094_a(new GameProfile((UUID)null, p_152719_0_)).toString();
         }
      } else {
         return p_152719_0_;
      }
   }
}
