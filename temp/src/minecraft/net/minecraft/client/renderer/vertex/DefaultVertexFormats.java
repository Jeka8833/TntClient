package net.minecraft.client.renderer.vertex;

import java.lang.reflect.Field;
import net.minecraft.src.Config;
import net.optifine.reflect.ReflectorClass;
import net.optifine.reflect.ReflectorField;
import net.optifine.shaders.SVertexFormat;

public class DefaultVertexFormats {
   public static VertexFormat field_176600_a = new VertexFormat();
   public static VertexFormat field_176599_b = new VertexFormat();
   private static final VertexFormat BLOCK_VANILLA;
   private static final VertexFormat ITEM_VANILLA;
   public static ReflectorClass Attributes;
   public static ReflectorField Attributes_DEFAULT_BAKED_FORMAT;
   private static final VertexFormat FORGE_BAKED;
   public static final VertexFormat field_181703_c;
   public static final VertexFormat field_181704_d;
   public static final VertexFormat field_181705_e;
   public static final VertexFormat field_181706_f;
   public static final VertexFormat field_181707_g;
   public static final VertexFormat field_181708_h;
   public static final VertexFormat field_181709_i;
   public static final VertexFormat field_181710_j;
   public static final VertexFormat field_181711_k;
   public static final VertexFormat field_181712_l;
   public static final VertexFormatElement field_181713_m;
   public static final VertexFormatElement field_181714_n;
   public static final VertexFormatElement field_181715_o;
   public static final VertexFormatElement field_181716_p;
   public static final VertexFormatElement field_181717_q;
   public static final VertexFormatElement field_181718_r;

   public static void updateVertexFormats() {
      if (Config.isShaders()) {
         field_176600_a = SVertexFormat.makeDefVertexFormatBlock();
         field_176599_b = SVertexFormat.makeDefVertexFormatItem();
         if (Attributes_DEFAULT_BAKED_FORMAT.exists()) {
            SVertexFormat.setDefBakedFormat((VertexFormat)Attributes_DEFAULT_BAKED_FORMAT.getValue());
         }
      } else {
         field_176600_a = BLOCK_VANILLA;
         field_176599_b = ITEM_VANILLA;
         if (Attributes_DEFAULT_BAKED_FORMAT.exists()) {
            SVertexFormat.copy(FORGE_BAKED, (VertexFormat)Attributes_DEFAULT_BAKED_FORMAT.getValue());
         }
      }

   }

   public static Object getFieldValue(ReflectorField p_getFieldValue_0_) {
      try {
         Field field = p_getFieldValue_0_.getTargetField();
         if (field == null) {
            return null;
         } else {
            Object value = field.get((Object)null);
            return value;
         }
      } catch (Throwable var3) {
         var3.printStackTrace();
         return null;
      }
   }

   static {
      BLOCK_VANILLA = field_176600_a;
      ITEM_VANILLA = field_176599_b;
      Attributes = new ReflectorClass("net.minecraftforge.client.model.Attributes");
      Attributes_DEFAULT_BAKED_FORMAT = new ReflectorField(Attributes, "DEFAULT_BAKED_FORMAT");
      FORGE_BAKED = SVertexFormat.duplicate((VertexFormat)getFieldValue(Attributes_DEFAULT_BAKED_FORMAT));
      field_181703_c = new VertexFormat();
      field_181704_d = new VertexFormat();
      field_181705_e = new VertexFormat();
      field_181706_f = new VertexFormat();
      field_181707_g = new VertexFormat();
      field_181708_h = new VertexFormat();
      field_181709_i = new VertexFormat();
      field_181710_j = new VertexFormat();
      field_181711_k = new VertexFormat();
      field_181712_l = new VertexFormat();
      field_181713_m = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3);
      field_181714_n = new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR, 4);
      field_181715_o = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2);
      field_181716_p = new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV, 2);
      field_181717_q = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.NORMAL, 3);
      field_181718_r = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.PADDING, 1);
      field_176600_a.func_181721_a(field_181713_m);
      field_176600_a.func_181721_a(field_181714_n);
      field_176600_a.func_181721_a(field_181715_o);
      field_176600_a.func_181721_a(field_181716_p);
      field_176599_b.func_181721_a(field_181713_m);
      field_176599_b.func_181721_a(field_181714_n);
      field_176599_b.func_181721_a(field_181715_o);
      field_176599_b.func_181721_a(field_181717_q);
      field_176599_b.func_181721_a(field_181718_r);
      field_181703_c.func_181721_a(field_181713_m);
      field_181703_c.func_181721_a(field_181715_o);
      field_181703_c.func_181721_a(field_181717_q);
      field_181703_c.func_181721_a(field_181718_r);
      field_181704_d.func_181721_a(field_181713_m);
      field_181704_d.func_181721_a(field_181715_o);
      field_181704_d.func_181721_a(field_181714_n);
      field_181704_d.func_181721_a(field_181716_p);
      field_181705_e.func_181721_a(field_181713_m);
      field_181706_f.func_181721_a(field_181713_m);
      field_181706_f.func_181721_a(field_181714_n);
      field_181707_g.func_181721_a(field_181713_m);
      field_181707_g.func_181721_a(field_181715_o);
      field_181708_h.func_181721_a(field_181713_m);
      field_181708_h.func_181721_a(field_181717_q);
      field_181708_h.func_181721_a(field_181718_r);
      field_181709_i.func_181721_a(field_181713_m);
      field_181709_i.func_181721_a(field_181715_o);
      field_181709_i.func_181721_a(field_181714_n);
      field_181710_j.func_181721_a(field_181713_m);
      field_181710_j.func_181721_a(field_181715_o);
      field_181710_j.func_181721_a(field_181717_q);
      field_181710_j.func_181721_a(field_181718_r);
      field_181711_k.func_181721_a(field_181713_m);
      field_181711_k.func_181721_a(field_181715_o);
      field_181711_k.func_181721_a(field_181716_p);
      field_181711_k.func_181721_a(field_181714_n);
      field_181712_l.func_181721_a(field_181713_m);
      field_181712_l.func_181721_a(field_181715_o);
      field_181712_l.func_181721_a(field_181714_n);
      field_181712_l.func_181721_a(field_181717_q);
      field_181712_l.func_181721_a(field_181718_r);
   }
}
