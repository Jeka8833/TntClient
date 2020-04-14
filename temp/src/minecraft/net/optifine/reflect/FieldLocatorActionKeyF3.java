package net.optifine.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;

public class FieldLocatorActionKeyF3 implements IFieldLocator {
   public Field getField() {
      Class mcClass = Minecraft.class;
      Field fieldRenderChunksMany = this.getFieldRenderChunksMany();
      if (fieldRenderChunksMany == null) {
         Config.log("(Reflector) Field not present: " + mcClass.getName() + ".actionKeyF3 (field renderChunksMany not found)");
         return null;
      } else {
         Field fieldActionkeyF3 = ReflectorRaw.getFieldAfter(Minecraft.class, fieldRenderChunksMany, Boolean.TYPE, 0);
         if (fieldActionkeyF3 == null) {
            Config.log("(Reflector) Field not present: " + mcClass.getName() + ".actionKeyF3");
            return null;
         } else {
            return fieldActionkeyF3;
         }
      }
   }

   private Field getFieldRenderChunksMany() {
      Minecraft mc = Minecraft.func_71410_x();
      boolean oldRenderChunksMany = mc.field_175612_E;
      Field[] fields = Minecraft.class.getDeclaredFields();
      mc.field_175612_E = true;
      Field[] fieldsTrue = ReflectorRaw.getFields(mc, fields, Boolean.TYPE, Boolean.TRUE);
      mc.field_175612_E = false;
      Field[] fieldsFalse = ReflectorRaw.getFields(mc, fields, Boolean.TYPE, Boolean.FALSE);
      mc.field_175612_E = oldRenderChunksMany;
      Set<Field> setTrue = new HashSet(Arrays.asList(fieldsTrue));
      Set<Field> setFalse = new HashSet(Arrays.asList(fieldsFalse));
      Set<Field> setFields = new HashSet(setTrue);
      setFields.retainAll(setFalse);
      Field[] fs = (Field[])((Field[])setFields.toArray(new Field[setFields.size()]));
      return fs.length != 1 ? null : fs[0];
   }
}
