package net.minecraft.client.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.optifine.SmartAnimations;
import net.optifine.TextureAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.util.NativeMemory;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GuiOverlayDebug extends Gui {
   private final Minecraft field_175242_a;
   private final FontRenderer field_175241_f;
   private String debugOF = null;

   public GuiOverlayDebug(Minecraft p_i45543_1_) {
      this.field_175242_a = p_i45543_1_;
      this.field_175241_f = p_i45543_1_.field_71466_p;
   }

   public void func_175237_a(ScaledResolution p_175237_1_) {
      this.field_175242_a.field_71424_I.func_76320_a("debug");
      GlStateManager.func_179094_E();
      this.func_180798_a();
      this.func_175239_b(p_175237_1_);
      GlStateManager.func_179121_F();
      if (this.field_175242_a.field_71474_y.field_74332_R) {
         this.func_181554_e();
      }

      this.field_175242_a.field_71424_I.func_76319_b();
   }

   private boolean func_175236_d() {
      return this.field_175242_a.field_71439_g.func_175140_cp() || this.field_175242_a.field_71474_y.field_178879_v;
   }

   protected void func_180798_a() {
      List<String> list = this.call();

      for(int i = 0; i < list.size(); ++i) {
         String s = (String)list.get(i);
         if (!Strings.isNullOrEmpty(s)) {
            int j = this.field_175241_f.field_78288_b;
            int k = this.field_175241_f.func_78256_a(s);
            int l = true;
            int i1 = 2 + j * i;
            func_73734_a(1, i1 - 1, 2 + k + 1, i1 + j - 1, -1873784752);
            this.field_175241_f.func_78276_b(s, 2, i1, 14737632);
         }
      }

   }

   protected void func_175239_b(ScaledResolution p_175239_1_) {
      List<String> list = this.func_175238_c();

      for(int i = 0; i < list.size(); ++i) {
         String s = (String)list.get(i);
         if (!Strings.isNullOrEmpty(s)) {
            int j = this.field_175241_f.field_78288_b;
            int k = this.field_175241_f.func_78256_a(s);
            int l = p_175239_1_.func_78326_a() - 2 - k;
            int i1 = 2 + j * i;
            func_73734_a(l - 1, i1 - 1, l + k + 1, i1 + j - 1, -1873784752);
            this.field_175241_f.func_78276_b(s, l, i1, 14737632);
         }
      }

   }

   protected List<String> call() {
      BlockPos blockpos = new BlockPos(this.field_175242_a.func_175606_aa().field_70165_t, this.field_175242_a.func_175606_aa().func_174813_aQ().field_72338_b, this.field_175242_a.func_175606_aa().field_70161_v);
      if (this.field_175242_a.field_71426_K != this.debugOF) {
         StringBuffer sb = new StringBuffer(this.field_175242_a.field_71426_K);
         int fpsMin = Config.getFpsMin();
         int posFps = this.field_175242_a.field_71426_K.indexOf(" fps ");
         if (posFps >= 0) {
            sb.insert(posFps, "/" + fpsMin);
         }

         if (Config.isSmoothFps()) {
            sb.append(" sf");
         }

         if (Config.isFastRender()) {
            sb.append(" fr");
         }

         if (Config.isAnisotropicFiltering()) {
            sb.append(" af");
         }

         if (Config.isAntialiasing()) {
            sb.append(" aa");
         }

         if (Config.isRenderRegions()) {
            sb.append(" reg");
         }

         if (Config.isShaders()) {
            sb.append(" sh");
         }

         this.field_175242_a.field_71426_K = sb.toString();
         this.debugOF = this.field_175242_a.field_71426_K;
      }

      StringBuilder sb = new StringBuilder();
      TextureMap tm = Config.getTextureMap();
      sb.append(", A: ");
      if (SmartAnimations.isActive()) {
         sb.append(tm.getCountAnimationsActive() + TextureAnimations.getCountAnimationsActive());
         sb.append("/");
      }

      sb.append(tm.getCountAnimations() + TextureAnimations.getCountAnimations());
      String ofInfo = sb.toString();
      if (this.func_175236_d()) {
         return Lists.newArrayList((Object[])("Minecraft 1.8.9 (" + this.field_175242_a.func_175600_c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.field_175242_a.field_71426_K, this.field_175242_a.field_71438_f.func_72735_c(), this.field_175242_a.field_71438_f.func_72723_d(), "P: " + this.field_175242_a.field_71452_i.func_78869_b() + ". T: " + this.field_175242_a.field_71441_e.func_72981_t() + ofInfo, this.field_175242_a.field_71441_e.func_72827_u(), "", String.format("Chunk-relative: %d %d %d", blockpos.func_177958_n() & 15, blockpos.func_177956_o() & 15, blockpos.func_177952_p() & 15)));
      } else {
         Entity entity = this.field_175242_a.func_175606_aa();
         EnumFacing enumfacing = entity.func_174811_aO();
         String s = "Invalid";
         switch(enumfacing) {
         case NORTH:
            s = "Towards negative Z";
            break;
         case SOUTH:
            s = "Towards positive Z";
            break;
         case WEST:
            s = "Towards negative X";
            break;
         case EAST:
            s = "Towards positive X";
         }

         List<String> list = Lists.newArrayList((Object[])("Minecraft 1.8.9 (" + this.field_175242_a.func_175600_c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.field_175242_a.field_71426_K, this.field_175242_a.field_71438_f.func_72735_c(), this.field_175242_a.field_71438_f.func_72723_d(), "P: " + this.field_175242_a.field_71452_i.func_78869_b() + ". T: " + this.field_175242_a.field_71441_e.func_72981_t() + ofInfo, this.field_175242_a.field_71441_e.func_72827_u(), "", String.format("XYZ: %.3f / %.5f / %.3f", this.field_175242_a.func_175606_aa().field_70165_t, this.field_175242_a.func_175606_aa().func_174813_aQ().field_72338_b, this.field_175242_a.func_175606_aa().field_70161_v), String.format("Block: %d %d %d", blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p()), String.format("Chunk: %d %d %d in %d %d %d", blockpos.func_177958_n() & 15, blockpos.func_177956_o() & 15, blockpos.func_177952_p() & 15, blockpos.func_177958_n() >> 4, blockpos.func_177956_o() >> 4, blockpos.func_177952_p() >> 4), String.format("Facing: %s (%s) (%.1f / %.1f)", enumfacing, s, MathHelper.func_76142_g(entity.field_70177_z), MathHelper.func_76142_g(entity.field_70125_A))));
         if (this.field_175242_a.field_71441_e != null && this.field_175242_a.field_71441_e.func_175667_e(blockpos)) {
            Chunk chunk = this.field_175242_a.field_71441_e.func_175726_f(blockpos);
            list.add("Biome: " + chunk.func_177411_a(blockpos, this.field_175242_a.field_71441_e.func_72959_q()).field_76791_y);
            list.add("Light: " + chunk.func_177443_a(blockpos, 0) + " (" + chunk.func_177413_a(EnumSkyBlock.SKY, blockpos) + " sky, " + chunk.func_177413_a(EnumSkyBlock.BLOCK, blockpos) + " block)");
            DifficultyInstance difficultyinstance = this.field_175242_a.field_71441_e.func_175649_E(blockpos);
            if (this.field_175242_a.func_71387_A() && this.field_175242_a.func_71401_C() != null) {
               EntityPlayerMP entityplayermp = this.field_175242_a.func_71401_C().func_71203_ab().func_177451_a(this.field_175242_a.field_71439_g.func_110124_au());
               if (entityplayermp != null) {
                  DifficultyInstance difficultyServer = this.field_175242_a.func_71401_C().getDifficultyAsync(entityplayermp.field_70170_p, new BlockPos(entityplayermp));
                  if (difficultyServer != null) {
                     difficultyinstance = difficultyServer;
                  }
               }
            }

            list.add(String.format("Local Difficulty: %.2f (Day %d)", difficultyinstance.func_180168_b(), this.field_175242_a.field_71441_e.func_72820_D() / 24000L));
         }

         if (this.field_175242_a.field_71460_t != null && this.field_175242_a.field_71460_t.func_147702_a()) {
            list.add("Shader: " + this.field_175242_a.field_71460_t.func_147706_e().func_148022_b());
         }

         if (this.field_175242_a.field_71476_x != null && this.field_175242_a.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_175242_a.field_71476_x.func_178782_a() != null) {
            BlockPos blockpos1 = this.field_175242_a.field_71476_x.func_178782_a();
            list.add(String.format("Looking at: %d %d %d", blockpos1.func_177958_n(), blockpos1.func_177956_o(), blockpos1.func_177952_p()));
         }

         return list;
      }
   }

   protected List<String> func_175238_c() {
      long i = Runtime.getRuntime().maxMemory();
      long j = Runtime.getRuntime().totalMemory();
      long k = Runtime.getRuntime().freeMemory();
      long l = j - k;
      List<String> list = Lists.newArrayList((Object[])(String.format("Java: %s %dbit", System.getProperty("java.version"), this.field_175242_a.func_147111_S() ? 64 : 32), String.format("Mem: % 2d%% %03d/%03dMB", l * 100L / i, func_175240_a(l), func_175240_a(i)), String.format("Allocated: % 2d%% %03dMB", j * 100L / i, func_175240_a(j)), "", String.format("CPU: %s", OpenGlHelper.func_183029_j()), "", String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(7936)), GL11.glGetString(7937), GL11.glGetString(7938)));
      long bufferAllocated = NativeMemory.getBufferAllocated();
      long bufferMaximum = NativeMemory.getBufferMaximum();
      String strNative = "Native: " + func_175240_a(bufferAllocated) + "/" + func_175240_a(bufferMaximum) + "MB";
      list.add(4, strNative);
      if (Reflector.FMLCommonHandler_getBrandings.exists()) {
         Object instance = Reflector.call(Reflector.FMLCommonHandler_instance);
         list.add("");
         list.addAll((Collection)Reflector.call(instance, Reflector.FMLCommonHandler_getBrandings, false));
      }

      if (this.func_175236_d()) {
         return list;
      } else {
         if (this.field_175242_a.field_71476_x != null && this.field_175242_a.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_175242_a.field_71476_x.func_178782_a() != null) {
            BlockPos blockpos = this.field_175242_a.field_71476_x.func_178782_a();
            IBlockState iblockstate = this.field_175242_a.field_71441_e.func_180495_p(blockpos);
            if (this.field_175242_a.field_71441_e.func_175624_G() != WorldType.field_180272_g) {
               iblockstate = iblockstate.func_177230_c().func_176221_a(iblockstate, this.field_175242_a.field_71441_e, blockpos);
            }

            list.add("");
            list.add(String.valueOf(Block.field_149771_c.func_177774_c(iblockstate.func_177230_c())));

            Entry entry;
            String s;
            for(Iterator i$ = iblockstate.func_177228_b().entrySet().iterator(); i$.hasNext(); list.add(((IProperty)entry.getKey()).func_177701_a() + ": " + s)) {
               entry = (Entry)i$.next();
               s = ((Comparable)entry.getValue()).toString();
               if (entry.getValue() == Boolean.TRUE) {
                  s = EnumChatFormatting.GREEN + s;
               } else if (entry.getValue() == Boolean.FALSE) {
                  s = EnumChatFormatting.RED + s;
               }
            }
         }

         return list;
      }
   }

   private void func_181554_e() {
   }

   private int func_181552_c(int p_181552_1_, int p_181552_2_, int p_181552_3_, int p_181552_4_) {
      return p_181552_1_ < p_181552_3_ ? this.func_181553_a(-16711936, -256, (float)p_181552_1_ / (float)p_181552_3_) : this.func_181553_a(-256, -65536, (float)(p_181552_1_ - p_181552_3_) / (float)(p_181552_4_ - p_181552_3_));
   }

   private int func_181553_a(int p_181553_1_, int p_181553_2_, float p_181553_3_) {
      int i = p_181553_1_ >> 24 & 255;
      int j = p_181553_1_ >> 16 & 255;
      int k = p_181553_1_ >> 8 & 255;
      int l = p_181553_1_ & 255;
      int i1 = p_181553_2_ >> 24 & 255;
      int j1 = p_181553_2_ >> 16 & 255;
      int k1 = p_181553_2_ >> 8 & 255;
      int l1 = p_181553_2_ & 255;
      int i2 = MathHelper.func_76125_a((int)((float)i + (float)(i1 - i) * p_181553_3_), 0, 255);
      int j2 = MathHelper.func_76125_a((int)((float)j + (float)(j1 - j) * p_181553_3_), 0, 255);
      int k2 = MathHelper.func_76125_a((int)((float)k + (float)(k1 - k) * p_181553_3_), 0, 255);
      int l2 = MathHelper.func_76125_a((int)((float)l + (float)(l1 - l) * p_181553_3_), 0, 255);
      return i2 << 24 | j2 << 16 | k2 << 8 | l2;
   }

   private static long func_175240_a(long p_175240_0_) {
      return p_175240_0_ / 1024L / 1024L;
   }
}
