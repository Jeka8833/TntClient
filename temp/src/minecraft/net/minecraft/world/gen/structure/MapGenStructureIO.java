package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapGenStructureIO {
   private static final Logger field_151687_a = LogManager.getLogger();
   private static Map<String, Class<? extends StructureStart>> field_143040_a = Maps.newHashMap();
   private static Map<Class<? extends StructureStart>, String> field_143038_b = Maps.newHashMap();
   private static Map<String, Class<? extends StructureComponent>> field_143039_c = Maps.newHashMap();
   private static Map<Class<? extends StructureComponent>, String> field_143037_d = Maps.newHashMap();

   private static void func_143034_b(Class<? extends StructureStart> p_143034_0_, String p_143034_1_) {
      field_143040_a.put(p_143034_1_, p_143034_0_);
      field_143038_b.put(p_143034_0_, p_143034_1_);
   }

   static void func_143031_a(Class<? extends StructureComponent> p_143031_0_, String p_143031_1_) {
      field_143039_c.put(p_143031_1_, p_143031_0_);
      field_143037_d.put(p_143031_0_, p_143031_1_);
   }

   public static String func_143033_a(StructureStart p_143033_0_) {
      return (String)field_143038_b.get(p_143033_0_.getClass());
   }

   public static String func_143036_a(StructureComponent p_143036_0_) {
      return (String)field_143037_d.get(p_143036_0_.getClass());
   }

   public static StructureStart func_143035_a(NBTTagCompound p_143035_0_, World p_143035_1_) {
      StructureStart lvt_2_1_ = null;

      try {
         Class<? extends StructureStart> lvt_3_1_ = (Class)field_143040_a.get(p_143035_0_.func_74779_i("id"));
         if (lvt_3_1_ != null) {
            lvt_2_1_ = (StructureStart)lvt_3_1_.newInstance();
         }
      } catch (Exception var4) {
         field_151687_a.warn("Failed Start with id " + p_143035_0_.func_74779_i("id"));
         var4.printStackTrace();
      }

      if (lvt_2_1_ != null) {
         lvt_2_1_.func_143020_a(p_143035_1_, p_143035_0_);
      } else {
         field_151687_a.warn("Skipping Structure with id " + p_143035_0_.func_74779_i("id"));
      }

      return lvt_2_1_;
   }

   public static StructureComponent func_143032_b(NBTTagCompound p_143032_0_, World p_143032_1_) {
      StructureComponent lvt_2_1_ = null;

      try {
         Class<? extends StructureComponent> lvt_3_1_ = (Class)field_143039_c.get(p_143032_0_.func_74779_i("id"));
         if (lvt_3_1_ != null) {
            lvt_2_1_ = (StructureComponent)lvt_3_1_.newInstance();
         }
      } catch (Exception var4) {
         field_151687_a.warn("Failed Piece with id " + p_143032_0_.func_74779_i("id"));
         var4.printStackTrace();
      }

      if (lvt_2_1_ != null) {
         lvt_2_1_.func_143009_a(p_143032_1_, p_143032_0_);
      } else {
         field_151687_a.warn("Skipping Piece with id " + p_143032_0_.func_74779_i("id"));
      }

      return lvt_2_1_;
   }

   static {
      func_143034_b(StructureMineshaftStart.class, "Mineshaft");
      func_143034_b(MapGenVillage.Start.class, "Village");
      func_143034_b(MapGenNetherBridge.Start.class, "Fortress");
      func_143034_b(MapGenStronghold.Start.class, "Stronghold");
      func_143034_b(MapGenScatteredFeature.Start.class, "Temple");
      func_143034_b(StructureOceanMonument.StartMonument.class, "Monument");
      StructureMineshaftPieces.func_143048_a();
      StructureVillagePieces.func_143016_a();
      StructureNetherBridgePieces.func_143049_a();
      StructureStrongholdPieces.func_143046_a();
      ComponentScatteredFeaturePieces.func_143045_a();
      StructureOceanMonumentPieces.func_175970_a();
   }
}
