package net.optifine.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;

public class ChunkUtils {
   private static ReflectorClass chunkClass = new ReflectorClass(Chunk.class);
   private static ReflectorField fieldHasEntities = findFieldHasEntities();
   private static ReflectorField fieldPrecipitationHeightMap;

   public static boolean hasEntities(Chunk chunk) {
      return Reflector.getFieldValueBoolean(chunk, fieldHasEntities, true);
   }

   public static int getPrecipitationHeight(Chunk chunk, BlockPos pos) {
      int[] precipitationHeightMap = (int[])((int[])Reflector.getFieldValue(chunk, fieldPrecipitationHeightMap));
      if (precipitationHeightMap != null && precipitationHeightMap.length == 256) {
         int cx = pos.func_177958_n() & 15;
         int cz = pos.func_177952_p() & 15;
         int ix = cx | cz << 4;
         int y = precipitationHeightMap[ix];
         if (y >= 0) {
            return y;
         } else {
            BlockPos posPrep = chunk.func_177440_h(pos);
            return posPrep.func_177956_o();
         }
      } else {
         return -1;
      }
   }

   private static ReflectorField findFieldHasEntities() {
      try {
         Chunk chunk = new Chunk((World)null, 0, 0);
         List listBoolFields = new ArrayList();
         List listBoolValuesPre = new ArrayList();
         Field[] fields = Chunk.class.getDeclaredFields();

         for(int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if (field.getType() == Boolean.TYPE) {
               field.setAccessible(true);
               listBoolFields.add(field);
               listBoolValuesPre.add(field.get(chunk));
            }
         }

         chunk.func_177409_g(false);
         List listBoolValuesFalse = new ArrayList();
         Iterator it = listBoolFields.iterator();

         while(it.hasNext()) {
            Field field = (Field)it.next();
            listBoolValuesFalse.add(field.get(chunk));
         }

         chunk.func_177409_g(true);
         List listBoolValuesTrue = new ArrayList();
         Iterator it = listBoolFields.iterator();

         Field field;
         while(it.hasNext()) {
            field = (Field)it.next();
            listBoolValuesTrue.add(field.get(chunk));
         }

         List listMatchingFields = new ArrayList();

         for(int i = 0; i < listBoolFields.size(); ++i) {
            Field field = (Field)listBoolFields.get(i);
            Boolean valFalse = (Boolean)listBoolValuesFalse.get(i);
            Boolean valTrue = (Boolean)listBoolValuesTrue.get(i);
            if (!valFalse && valTrue) {
               listMatchingFields.add(field);
               Boolean valPre = (Boolean)listBoolValuesPre.get(i);
               field.set(chunk, valPre);
            }
         }

         if (listMatchingFields.size() == 1) {
            field = (Field)listMatchingFields.get(0);
            return new ReflectorField(field);
         }
      } catch (Exception var12) {
         Config.warn(var12.getClass().getName() + " " + var12.getMessage());
      }

      Config.warn("Error finding Chunk.hasEntities");
      return new ReflectorField(new ReflectorClass(Chunk.class), "hasEntities");
   }

   static {
      fieldPrecipitationHeightMap = new ReflectorField(chunkClass, int[].class, 0);
   }
}
