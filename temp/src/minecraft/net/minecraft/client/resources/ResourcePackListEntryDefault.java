package net.minecraft.client.resources;

import com.google.gson.JsonParseException;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackListEntryDefault extends ResourcePackListEntry {
   private static final Logger field_148322_c = LogManager.getLogger();
   private final IResourcePack field_148320_d;
   private final ResourceLocation field_148321_e;

   public ResourcePackListEntryDefault(GuiScreenResourcePacks p_i45052_1_) {
      super(p_i45052_1_);
      this.field_148320_d = this.field_148317_a.func_110438_M().field_110620_b;

      DynamicTexture lvt_2_2_;
      try {
         lvt_2_2_ = new DynamicTexture(this.field_148320_d.func_110586_a());
      } catch (IOException var4) {
         lvt_2_2_ = TextureUtil.field_111001_a;
      }

      this.field_148321_e = this.field_148317_a.func_110434_K().func_110578_a("texturepackicon", lvt_2_2_);
   }

   protected int func_183019_a() {
      return 1;
   }

   protected String func_148311_a() {
      try {
         PackMetadataSection lvt_1_1_ = (PackMetadataSection)this.field_148320_d.func_135058_a(this.field_148317_a.func_110438_M().field_110621_c, "pack");
         if (lvt_1_1_ != null) {
            return lvt_1_1_.func_152805_a().func_150254_d();
         }
      } catch (JsonParseException var2) {
         field_148322_c.error((String)"Couldn't load metadata info", (Throwable)var2);
      } catch (IOException var3) {
         field_148322_c.error((String)"Couldn't load metadata info", (Throwable)var3);
      }

      return EnumChatFormatting.RED + "Missing " + "pack.mcmeta" + " :(";
   }

   protected boolean func_148309_e() {
      return false;
   }

   protected boolean func_148308_f() {
      return false;
   }

   protected boolean func_148314_g() {
      return false;
   }

   protected boolean func_148307_h() {
      return false;
   }

   protected String func_148312_b() {
      return "Default";
   }

   protected void func_148313_c() {
      this.field_148317_a.func_110434_K().func_110577_a(this.field_148321_e);
   }

   protected boolean func_148310_d() {
      return false;
   }
}
