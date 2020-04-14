package net.minecraft.realms;

import java.lang.reflect.Constructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsBridge extends RealmsScreen {
   private static final Logger LOGGER = LogManager.getLogger();
   private GuiScreen previousScreen;

   public void switchToRealms(GuiScreen p_switchToRealms_1_) {
      this.previousScreen = p_switchToRealms_1_;

      try {
         Class<?> lvt_2_1_ = Class.forName("com.mojang.realmsclient.RealmsMainScreen");
         Constructor<?> lvt_3_1_ = lvt_2_1_.getDeclaredConstructor(RealmsScreen.class);
         lvt_3_1_.setAccessible(true);
         Object lvt_4_1_ = lvt_3_1_.newInstance(this);
         Minecraft.func_71410_x().func_147108_a(((RealmsScreen)lvt_4_1_).getProxy());
      } catch (Exception var5) {
         LOGGER.error((String)"Realms module missing", (Throwable)var5);
      }

   }

   public GuiScreenRealmsProxy getNotificationScreen(GuiScreen p_getNotificationScreen_1_) {
      try {
         this.previousScreen = p_getNotificationScreen_1_;
         Class<?> lvt_2_1_ = Class.forName("com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen");
         Constructor<?> lvt_3_1_ = lvt_2_1_.getDeclaredConstructor(RealmsScreen.class);
         lvt_3_1_.setAccessible(true);
         Object lvt_4_1_ = lvt_3_1_.newInstance(this);
         return ((RealmsScreen)lvt_4_1_).getProxy();
      } catch (Exception var5) {
         LOGGER.error((String)"Realms module missing", (Throwable)var5);
         return null;
      }
   }

   public void init() {
      Minecraft.func_71410_x().func_147108_a(this.previousScreen);
   }
}
