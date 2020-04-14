package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.util.StringTranslate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager implements IResourceManagerReloadListener {
   private static final Logger field_147648_b = LogManager.getLogger();
   private final IMetadataSerializer field_135047_b;
   private String field_135048_c;
   protected static final Locale field_135049_a = new Locale();
   private Map<String, Language> field_135046_d = Maps.newHashMap();

   public LanguageManager(IMetadataSerializer p_i1304_1_, String p_i1304_2_) {
      this.field_135047_b = p_i1304_1_;
      this.field_135048_c = p_i1304_2_;
      I18n.func_135051_a(field_135049_a);
   }

   public void func_135043_a(List<IResourcePack> p_135043_1_) {
      this.field_135046_d.clear();
      Iterator lvt_2_1_ = p_135043_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         IResourcePack lvt_3_1_ = (IResourcePack)lvt_2_1_.next();

         try {
            LanguageMetadataSection lvt_4_1_ = (LanguageMetadataSection)lvt_3_1_.func_135058_a(this.field_135047_b, "language");
            if (lvt_4_1_ != null) {
               Iterator lvt_5_1_ = lvt_4_1_.func_135018_a().iterator();

               while(lvt_5_1_.hasNext()) {
                  Language lvt_6_1_ = (Language)lvt_5_1_.next();
                  if (!this.field_135046_d.containsKey(lvt_6_1_.func_135034_a())) {
                     this.field_135046_d.put(lvt_6_1_.func_135034_a(), lvt_6_1_);
                  }
               }
            }
         } catch (RuntimeException var7) {
            field_147648_b.warn((String)("Unable to parse metadata section of resourcepack: " + lvt_3_1_.func_130077_b()), (Throwable)var7);
         } catch (IOException var8) {
            field_147648_b.warn((String)("Unable to parse metadata section of resourcepack: " + lvt_3_1_.func_130077_b()), (Throwable)var8);
         }
      }

   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      List<String> lvt_2_1_ = Lists.newArrayList((Object[])("en_US"));
      if (!"en_US".equals(this.field_135048_c)) {
         lvt_2_1_.add(this.field_135048_c);
      }

      field_135049_a.func_135022_a(p_110549_1_, lvt_2_1_);
      StringTranslate.func_135063_a(field_135049_a.field_135032_a);
   }

   public boolean func_135042_a() {
      return field_135049_a.func_135025_a();
   }

   public boolean func_135044_b() {
      return this.func_135041_c() != null && this.func_135041_c().func_135035_b();
   }

   public void func_135045_a(Language p_135045_1_) {
      this.field_135048_c = p_135045_1_.func_135034_a();
   }

   public Language func_135041_c() {
      return this.field_135046_d.containsKey(this.field_135048_c) ? (Language)this.field_135046_d.get(this.field_135048_c) : (Language)this.field_135046_d.get("en_US");
   }

   public SortedSet<Language> func_135040_d() {
      return Sets.newTreeSet((Iterable)this.field_135046_d.values());
   }
}
