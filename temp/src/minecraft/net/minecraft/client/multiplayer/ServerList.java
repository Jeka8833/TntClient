package net.minecraft.client.multiplayer;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerList {
   private static final Logger field_147415_a = LogManager.getLogger();
   private final Minecraft field_78859_a;
   private final List<ServerData> field_78858_b = Lists.newArrayList();

   public ServerList(Minecraft p_i1194_1_) {
      this.field_78859_a = p_i1194_1_;
      this.func_78853_a();
   }

   public void func_78853_a() {
      try {
         this.field_78858_b.clear();
         NBTTagCompound lvt_1_1_ = CompressedStreamTools.func_74797_a(new File(this.field_78859_a.field_71412_D, "servers.dat"));
         if (lvt_1_1_ == null) {
            return;
         }

         NBTTagList lvt_2_1_ = lvt_1_1_.func_150295_c("servers", 10);

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
            this.field_78858_b.add(ServerData.func_78837_a(lvt_2_1_.func_150305_b(lvt_3_1_)));
         }
      } catch (Exception var4) {
         field_147415_a.error((String)"Couldn't load server list", (Throwable)var4);
      }

   }

   public void func_78855_b() {
      try {
         NBTTagList lvt_1_1_ = new NBTTagList();
         Iterator lvt_2_1_ = this.field_78858_b.iterator();

         while(lvt_2_1_.hasNext()) {
            ServerData lvt_3_1_ = (ServerData)lvt_2_1_.next();
            lvt_1_1_.func_74742_a(lvt_3_1_.func_78836_a());
         }

         NBTTagCompound lvt_2_2_ = new NBTTagCompound();
         lvt_2_2_.func_74782_a("servers", lvt_1_1_);
         CompressedStreamTools.func_74793_a(lvt_2_2_, new File(this.field_78859_a.field_71412_D, "servers.dat"));
      } catch (Exception var4) {
         field_147415_a.error((String)"Couldn't save server list", (Throwable)var4);
      }

   }

   public ServerData func_78850_a(int p_78850_1_) {
      return (ServerData)this.field_78858_b.get(p_78850_1_);
   }

   public void func_78851_b(int p_78851_1_) {
      this.field_78858_b.remove(p_78851_1_);
   }

   public void func_78849_a(ServerData p_78849_1_) {
      this.field_78858_b.add(p_78849_1_);
   }

   public int func_78856_c() {
      return this.field_78858_b.size();
   }

   public void func_78857_a(int p_78857_1_, int p_78857_2_) {
      ServerData lvt_3_1_ = this.func_78850_a(p_78857_1_);
      this.field_78858_b.set(p_78857_1_, this.func_78850_a(p_78857_2_));
      this.field_78858_b.set(p_78857_2_, lvt_3_1_);
      this.func_78855_b();
   }

   public void func_147413_a(int p_147413_1_, ServerData p_147413_2_) {
      this.field_78858_b.set(p_147413_1_, p_147413_2_);
   }

   public static void func_147414_b(ServerData p_147414_0_) {
      ServerList lvt_1_1_ = new ServerList(Minecraft.func_71410_x());
      lvt_1_1_.func_78853_a();

      for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_.func_78856_c(); ++lvt_2_1_) {
         ServerData lvt_3_1_ = lvt_1_1_.func_78850_a(lvt_2_1_);
         if (lvt_3_1_.field_78847_a.equals(p_147414_0_.field_78847_a) && lvt_3_1_.field_78845_b.equals(p_147414_0_.field_78845_b)) {
            lvt_1_1_.func_147413_a(lvt_2_1_, p_147414_0_);
            break;
         }
      }

      lvt_1_1_.func_78855_b();
   }
}
