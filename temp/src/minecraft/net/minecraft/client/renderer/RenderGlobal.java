package net.minecraft.client.renderer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Matrix4f;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.optifine.CustomColors;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lagometer;
import net.optifine.RandomEntities;
import net.optifine.SmartAnimations;
import net.optifine.model.BlockModelUtils;
import net.optifine.reflect.Reflector;
import net.optifine.render.ChunkVisibility;
import net.optifine.render.CloudRenderer;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadowUtils;
import net.optifine.util.ChunkUtils;
import net.optifine.util.RenderChunkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderGlobal implements IWorldAccess, IResourceManagerReloadListener {
   private static final Logger field_147599_m = LogManager.getLogger();
   private static final ResourceLocation field_110927_h = new ResourceLocation("textures/environment/moon_phases.png");
   private static final ResourceLocation field_110928_i = new ResourceLocation("textures/environment/sun.png");
   private static final ResourceLocation field_110925_j = new ResourceLocation("textures/environment/clouds.png");
   private static final ResourceLocation field_110926_k = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation field_175006_g = new ResourceLocation("textures/misc/forcefield.png");
   public final Minecraft field_72777_q;
   private final TextureManager field_72770_i;
   private final RenderManager field_175010_j;
   private WorldClient field_72769_h;
   private Set<RenderChunk> field_175009_l = Sets.newLinkedHashSet();
   private List<RenderGlobal.ContainerLocalRenderInformation> field_72755_R = Lists.newArrayListWithCapacity(69696);
   private final Set<TileEntity> field_181024_n = Sets.newHashSet();
   private ViewFrustum field_175008_n;
   private int field_72772_v = -1;
   private int field_72771_w = -1;
   private int field_72781_x = -1;
   private VertexFormat field_175014_r;
   private VertexBuffer field_175013_s;
   private VertexBuffer field_175012_t;
   private VertexBuffer field_175011_u;
   private int field_72773_u;
   public final Map<Integer, DestroyBlockProgress> field_72738_E = Maps.newHashMap();
   private final Map<BlockPos, ISound> field_147593_P = Maps.newHashMap();
   private final TextureAtlasSprite[] field_94141_F = new TextureAtlasSprite[10];
   private Framebuffer field_175015_z;
   private ShaderGroup field_174991_A;
   private double field_174992_B = Double.MIN_VALUE;
   private double field_174993_C = Double.MIN_VALUE;
   private double field_174987_D = Double.MIN_VALUE;
   private int field_174988_E = Integer.MIN_VALUE;
   private int field_174989_F = Integer.MIN_VALUE;
   private int field_174990_G = Integer.MIN_VALUE;
   private double field_174997_H = Double.MIN_VALUE;
   private double field_174998_I = Double.MIN_VALUE;
   private double field_174999_J = Double.MIN_VALUE;
   private double field_175000_K = Double.MIN_VALUE;
   private double field_174994_L = Double.MIN_VALUE;
   private final ChunkRenderDispatcher field_174995_M = new ChunkRenderDispatcher();
   private ChunkRenderContainer field_174996_N;
   private int field_72739_F = -1;
   private int field_72740_G = 2;
   private int field_72748_H;
   private int field_72749_I;
   private int field_72750_J;
   private boolean field_175002_T = false;
   private ClippingHelper field_175001_U;
   private final Vector4f[] field_175004_V = new Vector4f[8];
   private final Vector3d field_175003_W = new Vector3d();
   private boolean field_175005_X = false;
   IRenderChunkFactory field_175007_a;
   private double field_147596_f;
   private double field_147597_g;
   private double field_147602_h;
   public boolean field_147595_R = true;
   private CloudRenderer cloudRenderer;
   public Entity renderedEntity;
   public Set chunksToResortTransparency = new LinkedHashSet();
   public Set chunksToUpdateForced = new LinkedHashSet();
   private Deque visibilityDeque = new ArrayDeque();
   private List renderInfosEntities = new ArrayList(1024);
   private List renderInfosTileEntities = new ArrayList(1024);
   private List renderInfosNormal = new ArrayList(1024);
   private List renderInfosEntitiesNormal = new ArrayList(1024);
   private List renderInfosTileEntitiesNormal = new ArrayList(1024);
   private List renderInfosShadow = new ArrayList(1024);
   private List renderInfosEntitiesShadow = new ArrayList(1024);
   private List renderInfosTileEntitiesShadow = new ArrayList(1024);
   private int renderDistance = 0;
   private int renderDistanceSq = 0;
   private static final Set SET_ALL_FACINGS;
   private int countTileEntitiesRendered;
   private IChunkProvider worldChunkProvider = null;
   private LongHashMap worldChunkProviderMap = null;
   private int countLoadedChunksPrev = 0;
   private RenderEnv renderEnv;
   public boolean renderOverlayDamaged;
   public boolean renderOverlayEyes;
   private boolean firstWorldLoad;
   private static int renderEntitiesCounter;

   public RenderGlobal(Minecraft p_i1249_1_) {
      this.renderEnv = new RenderEnv(Blocks.field_150350_a.func_176223_P(), new BlockPos(0, 0, 0));
      this.renderOverlayDamaged = false;
      this.renderOverlayEyes = false;
      this.firstWorldLoad = false;
      this.cloudRenderer = new CloudRenderer(p_i1249_1_);
      this.field_72777_q = p_i1249_1_;
      this.field_175010_j = p_i1249_1_.func_175598_ae();
      this.field_72770_i = p_i1249_1_.func_110434_K();
      this.field_72770_i.func_110577_a(field_175006_g);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GlStateManager.func_179144_i(0);
      this.func_174971_n();
      this.field_175005_X = OpenGlHelper.func_176075_f();
      if (this.field_175005_X) {
         this.field_174996_N = new VboRenderList();
         this.field_175007_a = new VboChunkFactory();
      } else {
         this.field_174996_N = new RenderList();
         this.field_175007_a = new ListChunkFactory();
      }

      this.field_175014_r = new VertexFormat();
      this.field_175014_r.func_181721_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
      this.func_174963_q();
      this.func_174980_p();
      this.func_174964_o();
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.func_174971_n();
   }

   private void func_174971_n() {
      TextureMap texturemap = this.field_72777_q.func_147117_R();

      for(int i = 0; i < this.field_94141_F.length; ++i) {
         this.field_94141_F[i] = texturemap.func_110572_b("minecraft:blocks/destroy_stage_" + i);
      }

   }

   public void func_174966_b() {
      if (OpenGlHelper.field_148824_g) {
         if (ShaderLinkHelper.func_148074_b() == null) {
            ShaderLinkHelper.func_148076_a();
         }

         ResourceLocation resourcelocation = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.field_174991_A = new ShaderGroup(this.field_72777_q.func_110434_K(), this.field_72777_q.func_110442_L(), this.field_72777_q.func_147110_a(), resourcelocation);
            this.field_174991_A.func_148026_a(this.field_72777_q.field_71443_c, this.field_72777_q.field_71440_d);
            this.field_175015_z = this.field_174991_A.func_177066_a("final");
         } catch (IOException var3) {
            field_147599_m.warn((String)("Failed to load shader: " + resourcelocation), (Throwable)var3);
            this.field_174991_A = null;
            this.field_175015_z = null;
         } catch (JsonSyntaxException var4) {
            field_147599_m.warn((String)("Failed to load shader: " + resourcelocation), (Throwable)var4);
            this.field_174991_A = null;
            this.field_175015_z = null;
         }
      } else {
         this.field_174991_A = null;
         this.field_175015_z = null;
      }

   }

   public void func_174975_c() {
      if (this.func_174985_d()) {
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 0, 1);
         this.field_175015_z.func_178038_a(this.field_72777_q.field_71443_c, this.field_72777_q.field_71440_d, false);
         GlStateManager.func_179084_k();
      }

   }

   protected boolean func_174985_d() {
      if (!Config.isFastRender() && !Config.isShaders() && !Config.isAntialiasing()) {
         return this.field_175015_z != null && this.field_174991_A != null && this.field_72777_q.field_71439_g != null && this.field_72777_q.field_71439_g.func_175149_v() && this.field_72777_q.field_71474_y.field_152396_an.func_151470_d();
      } else {
         return false;
      }
   }

   private void func_174964_o() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      if (this.field_175011_u != null) {
         this.field_175011_u.func_177362_c();
      }

      if (this.field_72781_x >= 0) {
         GLAllocation.func_74523_b(this.field_72781_x);
         this.field_72781_x = -1;
      }

      if (this.field_175005_X) {
         this.field_175011_u = new VertexBuffer(this.field_175014_r);
         this.func_174968_a(vertexbuffer, -16.0F, true);
         vertexbuffer.func_178977_d();
         vertexbuffer.func_178965_a();
         this.field_175011_u.func_181722_a(vertexbuffer.func_178966_f());
      } else {
         this.field_72781_x = GLAllocation.func_74526_a(1);
         GL11.glNewList(this.field_72781_x, 4864);
         this.func_174968_a(vertexbuffer, -16.0F, true);
         tessellator.func_78381_a();
         GL11.glEndList();
      }

   }

   private void func_174980_p() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      if (this.field_175012_t != null) {
         this.field_175012_t.func_177362_c();
      }

      if (this.field_72771_w >= 0) {
         GLAllocation.func_74523_b(this.field_72771_w);
         this.field_72771_w = -1;
      }

      if (this.field_175005_X) {
         this.field_175012_t = new VertexBuffer(this.field_175014_r);
         this.func_174968_a(vertexbuffer, 16.0F, false);
         vertexbuffer.func_178977_d();
         vertexbuffer.func_178965_a();
         this.field_175012_t.func_181722_a(vertexbuffer.func_178966_f());
      } else {
         this.field_72771_w = GLAllocation.func_74526_a(1);
         GL11.glNewList(this.field_72771_w, 4864);
         this.func_174968_a(vertexbuffer, 16.0F, false);
         tessellator.func_78381_a();
         GL11.glEndList();
      }

   }

   private void func_174968_a(WorldRenderer p_174968_1_, float p_174968_2_, boolean p_174968_3_) {
      int i = true;
      int j = true;
      p_174968_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      int skyDist = (this.renderDistance / 64 + 1) * 64 + 64;

      for(int k = -skyDist; k <= skyDist; k += 64) {
         for(int l = -skyDist; l <= skyDist; l += 64) {
            float f = (float)k;
            float f1 = (float)(k + 64);
            if (p_174968_3_) {
               f1 = (float)k;
               f = (float)(k + 64);
            }

            p_174968_1_.func_181662_b((double)f, (double)p_174968_2_, (double)l).func_181675_d();
            p_174968_1_.func_181662_b((double)f1, (double)p_174968_2_, (double)l).func_181675_d();
            p_174968_1_.func_181662_b((double)f1, (double)p_174968_2_, (double)(l + 64)).func_181675_d();
            p_174968_1_.func_181662_b((double)f, (double)p_174968_2_, (double)(l + 64)).func_181675_d();
         }
      }

   }

   private void func_174963_q() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      if (this.field_175013_s != null) {
         this.field_175013_s.func_177362_c();
      }

      if (this.field_72772_v >= 0) {
         GLAllocation.func_74523_b(this.field_72772_v);
         this.field_72772_v = -1;
      }

      if (this.field_175005_X) {
         this.field_175013_s = new VertexBuffer(this.field_175014_r);
         this.func_180444_a(vertexbuffer);
         vertexbuffer.func_178977_d();
         vertexbuffer.func_178965_a();
         this.field_175013_s.func_181722_a(vertexbuffer.func_178966_f());
      } else {
         this.field_72772_v = GLAllocation.func_74526_a(1);
         GlStateManager.func_179094_E();
         GL11.glNewList(this.field_72772_v, 4864);
         this.func_180444_a(vertexbuffer);
         tessellator.func_78381_a();
         GL11.glEndList();
         GlStateManager.func_179121_F();
      }

   }

   private void func_180444_a(WorldRenderer p_180444_1_) {
      Random random = new Random(10842L);
      p_180444_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);

      for(int i = 0; i < 1500; ++i) {
         double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
         double d4 = d0 * d0 + d1 * d1 + d2 * d2;
         if (d4 < 1.0D && d4 > 0.01D) {
            d4 = 1.0D / Math.sqrt(d4);
            d0 *= d4;
            d1 *= d4;
            d2 *= d4;
            double d5 = d0 * 100.0D;
            double d6 = d1 * 100.0D;
            double d7 = d2 * 100.0D;
            double d8 = Math.atan2(d0, d2);
            double d9 = Math.sin(d8);
            double d10 = Math.cos(d8);
            double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
            double d12 = Math.sin(d11);
            double d13 = Math.cos(d11);
            double d14 = random.nextDouble() * 3.141592653589793D * 2.0D;
            double d15 = Math.sin(d14);
            double d16 = Math.cos(d14);

            for(int j = 0; j < 4; ++j) {
               double d17 = 0.0D;
               double d18 = (double)((j & 2) - 1) * d3;
               double d19 = (double)((j + 1 & 2) - 1) * d3;
               double d20 = 0.0D;
               double d21 = d18 * d16 - d19 * d15;
               double d22 = d19 * d16 + d18 * d15;
               double d23 = d21 * d12 + 0.0D * d13;
               double d24 = 0.0D * d12 - d21 * d13;
               double d25 = d24 * d9 - d22 * d10;
               double d26 = d22 * d9 + d24 * d10;
               p_180444_1_.func_181662_b(d5 + d25, d6 + d23, d7 + d26).func_181675_d();
            }
         }
      }

   }

   public void func_72732_a(WorldClient p_72732_1_) {
      if (this.field_72769_h != null) {
         this.field_72769_h.func_72848_b(this);
      }

      this.field_174992_B = Double.MIN_VALUE;
      this.field_174993_C = Double.MIN_VALUE;
      this.field_174987_D = Double.MIN_VALUE;
      this.field_174988_E = Integer.MIN_VALUE;
      this.field_174989_F = Integer.MIN_VALUE;
      this.field_174990_G = Integer.MIN_VALUE;
      this.field_175010_j.func_78717_a(p_72732_1_);
      this.field_72769_h = p_72732_1_;
      if (Config.isDynamicLights()) {
         DynamicLights.clear();
      }

      ChunkVisibility.reset();
      this.worldChunkProvider = null;
      this.worldChunkProviderMap = null;
      this.renderEnv.reset((IBlockState)null, (BlockPos)null);
      Shaders.checkWorldChanged(this.field_72769_h);
      if (p_72732_1_ != null) {
         p_72732_1_.func_72954_a(this);
         this.func_72712_a();
      } else {
         this.field_175009_l.clear();
         this.clearRenderInfos();
         if (this.field_175008_n != null) {
            this.field_175008_n.func_178160_a();
         }

         this.field_175008_n = null;
      }

   }

   public void func_72712_a() {
      if (this.field_72769_h != null) {
         this.field_147595_R = true;
         Blocks.field_150362_t.func_150122_b(Config.isTreesFancy());
         Blocks.field_150361_u.func_150122_b(Config.isTreesFancy());
         BlockModelRenderer.updateAoLightValue();
         if (Config.isDynamicLights()) {
            DynamicLights.clear();
         }

         SmartAnimations.update();
         this.field_72739_F = this.field_72777_q.field_71474_y.field_151451_c;
         this.renderDistance = this.field_72739_F * 16;
         this.renderDistanceSq = this.renderDistance * this.renderDistance;
         boolean flag = this.field_175005_X;
         this.field_175005_X = OpenGlHelper.func_176075_f();
         if (flag && !this.field_175005_X) {
            this.field_174996_N = new RenderList();
            this.field_175007_a = new ListChunkFactory();
         } else if (!flag && this.field_175005_X) {
            this.field_174996_N = new VboRenderList();
            this.field_175007_a = new VboChunkFactory();
         }

         this.func_174963_q();
         this.func_174980_p();
         this.func_174964_o();
         if (this.field_175008_n != null) {
            this.field_175008_n.func_178160_a();
         }

         this.func_174986_e();
         synchronized(this.field_181024_n) {
            this.field_181024_n.clear();
         }

         this.field_175008_n = new ViewFrustum(this.field_72769_h, this.field_72777_q.field_71474_y.field_151451_c, this, this.field_175007_a);
         if (this.field_72769_h != null) {
            Entity entity = this.field_72777_q.func_175606_aa();
            if (entity != null) {
               this.field_175008_n.func_178163_a(entity.field_70165_t, entity.field_70161_v);
            }
         }

         this.field_72740_G = 2;
      }

      if (this.field_72777_q.field_71439_g == null) {
         this.firstWorldLoad = true;
      }

   }

   protected void func_174986_e() {
      this.field_175009_l.clear();
      this.field_174995_M.func_178514_b();
   }

   public void func_72720_a(int p_72720_1_, int p_72720_2_) {
      if (OpenGlHelper.field_148824_g && this.field_174991_A != null) {
         this.field_174991_A.func_148026_a(p_72720_1_, p_72720_2_);
      }

   }

   public void func_180446_a(Entity p_180446_1_, ICamera p_180446_2_, float p_180446_3_) {
      int pass = 0;
      if (Reflector.MinecraftForgeClient_getRenderPass.exists()) {
         pass = Reflector.callInt(Reflector.MinecraftForgeClient_getRenderPass);
      }

      if (this.field_72740_G > 0) {
         if (pass <= 0) {
            --this.field_72740_G;
         }
      } else {
         double d0 = p_180446_1_.field_70169_q + (p_180446_1_.field_70165_t - p_180446_1_.field_70169_q) * (double)p_180446_3_;
         double d1 = p_180446_1_.field_70167_r + (p_180446_1_.field_70163_u - p_180446_1_.field_70167_r) * (double)p_180446_3_;
         double d2 = p_180446_1_.field_70166_s + (p_180446_1_.field_70161_v - p_180446_1_.field_70166_s) * (double)p_180446_3_;
         this.field_72769_h.field_72984_F.func_76320_a("prepare");
         TileEntityRendererDispatcher.field_147556_a.func_178470_a(this.field_72769_h, this.field_72777_q.func_110434_K(), this.field_72777_q.field_71466_p, this.field_72777_q.func_175606_aa(), p_180446_3_);
         this.field_175010_j.func_180597_a(this.field_72769_h, this.field_72777_q.field_71466_p, this.field_72777_q.func_175606_aa(), this.field_72777_q.field_147125_j, this.field_72777_q.field_71474_y, p_180446_3_);
         ++renderEntitiesCounter;
         if (pass == 0) {
            this.field_72748_H = 0;
            this.field_72749_I = 0;
            this.field_72750_J = 0;
            this.countTileEntitiesRendered = 0;
         }

         Entity entity = this.field_72777_q.func_175606_aa();
         double d3 = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)p_180446_3_;
         double d4 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)p_180446_3_;
         double d5 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)p_180446_3_;
         TileEntityRendererDispatcher.field_147554_b = d3;
         TileEntityRendererDispatcher.field_147555_c = d4;
         TileEntityRendererDispatcher.field_147552_d = d5;
         this.field_175010_j.func_178628_a(d3, d4, d5);
         this.field_72777_q.field_71460_t.func_180436_i();
         this.field_72769_h.field_72984_F.func_76318_c("global");
         List<Entity> list = this.field_72769_h.func_72910_y();
         if (pass == 0) {
            this.field_72748_H = list.size();
         }

         if (Config.isFogOff() && this.field_72777_q.field_71460_t.fogStandard) {
            GlStateManager.func_179106_n();
         }

         boolean forgeEntityPass = Reflector.ForgeEntity_shouldRenderInPass.exists();
         boolean forgeTileEntityPass = Reflector.ForgeTileEntity_shouldRenderInPass.exists();

         int j;
         Entity entity3;
         for(j = 0; j < this.field_72769_h.field_73007_j.size(); ++j) {
            entity3 = (Entity)this.field_72769_h.field_73007_j.get(j);
            if (!forgeEntityPass || Reflector.callBoolean(entity3, Reflector.ForgeEntity_shouldRenderInPass, pass)) {
               ++this.field_72749_I;
               if (entity3.func_145770_h(d0, d1, d2)) {
                  this.field_175010_j.func_147937_a(entity3, p_180446_3_);
               }
            }
         }

         if (this.func_174985_d()) {
            GlStateManager.func_179143_c(519);
            GlStateManager.func_179106_n();
            this.field_175015_z.func_147614_f();
            this.field_175015_z.func_147610_a(false);
            this.field_72769_h.field_72984_F.func_76318_c("entityOutlines");
            RenderHelper.func_74518_a();
            this.field_175010_j.func_178632_c(true);
            j = 0;

            while(true) {
               if (j >= list.size()) {
                  this.field_175010_j.func_178632_c(false);
                  RenderHelper.func_74519_b();
                  GlStateManager.func_179132_a(false);
                  this.field_174991_A.func_148018_a(p_180446_3_);
                  GlStateManager.func_179145_e();
                  GlStateManager.func_179132_a(true);
                  this.field_72777_q.func_147110_a().func_147610_a(false);
                  GlStateManager.func_179127_m();
                  GlStateManager.func_179147_l();
                  GlStateManager.func_179142_g();
                  GlStateManager.func_179143_c(515);
                  GlStateManager.func_179126_j();
                  GlStateManager.func_179141_d();
                  break;
               }

               entity3 = (Entity)list.get(j);
               boolean flag = this.field_72777_q.func_175606_aa() instanceof EntityLivingBase && ((EntityLivingBase)this.field_72777_q.func_175606_aa()).func_70608_bn();
               boolean flag1 = entity3.func_145770_h(d0, d1, d2) && (entity3.field_70158_ak || p_180446_2_.func_78546_a(entity3.func_174813_aQ()) || entity3.field_70153_n == this.field_72777_q.field_71439_g) && entity3 instanceof EntityPlayer;
               if ((entity3 != this.field_72777_q.func_175606_aa() || this.field_72777_q.field_71474_y.field_74330_P != 0 || flag) && flag1) {
                  this.field_175010_j.func_147937_a(entity3, p_180446_3_);
               }

               ++j;
            }
         }

         this.field_72769_h.field_72984_F.func_76318_c("entities");
         boolean isShaders = Config.isShaders();
         if (isShaders) {
            Shaders.beginEntities();
         }

         RenderItemFrame.updateItemRenderDistance();
         boolean oldFancyGraphics = this.field_72777_q.field_71474_y.field_74347_j;
         this.field_72777_q.field_71474_y.field_74347_j = Config.isDroppedItemsFancy();
         Iterator iterInfosEntities = this.renderInfosEntities.iterator();

         label305:
         while(true) {
            ClassInheritanceMultiMap classinheritancemultimap;
            Iterator i$;
            boolean shouldRender;
            do {
               if (!iterInfosEntities.hasNext()) {
                  this.field_72777_q.field_71474_y.field_74347_j = oldFancyGraphics;
                  if (isShaders) {
                     Shaders.endEntities();
                     Shaders.beginBlockEntities();
                  }

                  this.field_72769_h.field_72984_F.func_76318_c("blockentities");
                  RenderHelper.func_74519_b();
                  if (Reflector.ForgeTileEntity_hasFastRenderer.exists()) {
                     TileEntityRendererDispatcher.field_147556_a.preDrawBatch();
                  }

                  TileEntitySignRenderer.updateTextRenderDistance();
                  Iterator iterInfoBlockEntities = this.renderInfosTileEntities.iterator();

                  label251:
                  while(true) {
                     List list1;
                     TileEntity tileentity1;
                     do {
                        if (!iterInfoBlockEntities.hasNext()) {
                           synchronized(this.field_181024_n) {
                              Iterator i$ = this.field_181024_n.iterator();

                              while(true) {
                                 if (!i$.hasNext()) {
                                    break;
                                 }

                                 TileEntity tileentity = (TileEntity)i$.next();
                                 if (!forgeTileEntityPass || Reflector.callBoolean(tileentity, Reflector.ForgeTileEntity_shouldRenderInPass, pass)) {
                                    if (isShaders) {
                                       Shaders.nextBlockEntity(tileentity);
                                    }

                                    TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity, p_180446_3_, -1);
                                 }
                              }
                           }

                           if (Reflector.ForgeTileEntity_hasFastRenderer.exists()) {
                              TileEntityRendererDispatcher.field_147556_a.drawBatch(pass);
                           }

                           this.renderOverlayDamaged = true;
                           this.func_180443_s();
                           Iterator i$ = this.field_72738_E.values().iterator();

                           while(i$.hasNext()) {
                              DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)i$.next();
                              BlockPos blockpos = destroyblockprogress.func_180246_b();
                              tileentity1 = this.field_72769_h.func_175625_s(blockpos);
                              if (tileentity1 instanceof TileEntityChest) {
                                 TileEntityChest tileentitychest = (TileEntityChest)tileentity1;
                                 if (tileentitychest.field_145991_k != null) {
                                    blockpos = blockpos.func_177972_a(EnumFacing.WEST);
                                    tileentity1 = this.field_72769_h.func_175625_s(blockpos);
                                 } else if (tileentitychest.field_145992_i != null) {
                                    blockpos = blockpos.func_177972_a(EnumFacing.NORTH);
                                    tileentity1 = this.field_72769_h.func_175625_s(blockpos);
                                 }
                              }

                              Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
                              if (forgeTileEntityPass) {
                                 shouldRender = false;
                                 if (tileentity1 != null && Reflector.callBoolean(tileentity1, Reflector.ForgeTileEntity_shouldRenderInPass, pass) && Reflector.callBoolean(tileentity1, Reflector.ForgeTileEntity_canRenderBreaking)) {
                                    AxisAlignedBB aabb = (AxisAlignedBB)Reflector.call(tileentity1, Reflector.ForgeTileEntity_getRenderBoundingBox);
                                    if (aabb != null) {
                                       shouldRender = p_180446_2_.func_78546_a(aabb);
                                    }
                                 }
                              } else {
                                 shouldRender = tileentity1 != null && (block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSign || block instanceof BlockSkull);
                              }

                              if (shouldRender) {
                                 if (isShaders) {
                                    Shaders.nextBlockEntity(tileentity1);
                                 }

                                 TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity1, p_180446_3_, destroyblockprogress.func_73106_e());
                              }
                           }

                           this.func_174969_t();
                           this.renderOverlayDamaged = false;
                           if (isShaders) {
                              Shaders.endBlockEntities();
                           }

                           --renderEntitiesCounter;
                           this.field_72777_q.field_71460_t.func_175072_h();
                           this.field_72777_q.field_71424_I.func_76319_b();
                           return;
                        }

                        RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1 = (RenderGlobal.ContainerLocalRenderInformation)iterInfoBlockEntities.next();
                        list1 = renderglobal$containerlocalrenderinformation1.field_178036_a.func_178571_g().func_178485_b();
                     } while(list1.isEmpty());

                     i$ = list1.iterator();

                     while(true) {
                        while(true) {
                           if (!i$.hasNext()) {
                              continue label251;
                           }

                           tileentity1 = (TileEntity)i$.next();
                           if (!forgeTileEntityPass) {
                              break;
                           }

                           if (Reflector.callBoolean(tileentity1, Reflector.ForgeTileEntity_shouldRenderInPass, pass)) {
                              AxisAlignedBB aabb = (AxisAlignedBB)Reflector.call(tileentity1, Reflector.ForgeTileEntity_getRenderBoundingBox);
                              if (aabb == null || p_180446_2_.func_78546_a(aabb)) {
                                 break;
                              }
                           }
                        }

                        if (isShaders) {
                           Shaders.nextBlockEntity(tileentity1);
                        }

                        TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity1, p_180446_3_, -1);
                        ++this.countTileEntitiesRendered;
                     }
                  }
               }

               RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = (RenderGlobal.ContainerLocalRenderInformation)iterInfosEntities.next();
               Chunk chunk = renderglobal$containerlocalrenderinformation.field_178036_a.getChunk();
               classinheritancemultimap = chunk.func_177429_s()[renderglobal$containerlocalrenderinformation.field_178036_a.func_178568_j().func_177956_o() / 16];
            } while(classinheritancemultimap.isEmpty());

            i$ = classinheritancemultimap.iterator();

            while(true) {
               Entity entity2;
               do {
                  boolean flag2;
                  do {
                     label291:
                     do {
                        do {
                           do {
                              if (!i$.hasNext()) {
                                 continue label305;
                              }

                              entity2 = (Entity)i$.next();
                           } while(forgeEntityPass && !Reflector.callBoolean(entity2, Reflector.ForgeEntity_shouldRenderInPass, pass));

                           flag2 = this.field_175010_j.func_178635_a(entity2, p_180446_2_, d0, d1, d2) || entity2.field_70153_n == this.field_72777_q.field_71439_g;
                           if (!flag2) {
                              continue label291;
                           }

                           shouldRender = this.field_72777_q.func_175606_aa() instanceof EntityLivingBase ? ((EntityLivingBase)this.field_72777_q.func_175606_aa()).func_70608_bn() : false;
                        } while(entity2 == this.field_72777_q.func_175606_aa() && this.field_72777_q.field_71474_y.field_74330_P == 0 && !shouldRender || entity2.field_70163_u >= 0.0D && entity2.field_70163_u < 256.0D && !this.field_72769_h.func_175667_e(new BlockPos(entity2)));

                        ++this.field_72749_I;
                        this.renderedEntity = entity2;
                        if (isShaders) {
                           Shaders.nextEntity(entity2);
                        }

                        this.field_175010_j.func_147937_a(entity2, p_180446_3_);
                        this.renderedEntity = null;
                     } while(flag2);
                  } while(!(entity2 instanceof EntityWitherSkull));
               } while(forgeEntityPass && !Reflector.callBoolean(entity2, Reflector.ForgeEntity_shouldRenderInPass, pass));

               this.renderedEntity = entity2;
               if (isShaders) {
                  Shaders.nextEntity(entity2);
               }

               this.field_72777_q.func_175598_ae().func_178630_b(entity2, p_180446_3_);
               this.renderedEntity = null;
            }
         }
      }
   }

   public String func_72735_c() {
      int i = this.field_175008_n.field_178164_f.length;
      int j = 0;
      Iterator i$ = this.field_72755_R.iterator();

      while(i$.hasNext()) {
         RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = (RenderGlobal.ContainerLocalRenderInformation)i$.next();
         CompiledChunk compiledchunk = renderglobal$containerlocalrenderinformation.field_178036_a.field_178590_b;
         if (compiledchunk != CompiledChunk.field_178502_a && !compiledchunk.func_178489_a()) {
            ++j;
         }
      }

      return String.format("C: %d/%d %sD: %d, %s", j, i, this.field_72777_q.field_175612_E ? "(s) " : "", this.field_72739_F, this.field_174995_M.func_178504_a());
   }

   public String func_72723_d() {
      return "E: " + this.field_72749_I + "/" + this.field_72748_H + ", B: " + this.field_72750_J + ", I: " + (this.field_72748_H - this.field_72750_J - this.field_72749_I) + ", " + Config.getVersionDebug();
   }

   public void func_174970_a(Entity p_174970_1_, double p_174970_2_, ICamera p_174970_4_, int p_174970_5_, boolean p_174970_6_) {
      if (this.field_72777_q.field_71474_y.field_151451_c != this.field_72739_F) {
         this.func_72712_a();
      }

      this.field_72769_h.field_72984_F.func_76320_a("camera");
      double d0 = p_174970_1_.field_70165_t - this.field_174992_B;
      double d1 = p_174970_1_.field_70163_u - this.field_174993_C;
      double d2 = p_174970_1_.field_70161_v - this.field_174987_D;
      if (this.field_174988_E != p_174970_1_.field_70176_ah || this.field_174989_F != p_174970_1_.field_70162_ai || this.field_174990_G != p_174970_1_.field_70164_aj || d0 * d0 + d1 * d1 + d2 * d2 > 16.0D) {
         this.field_174992_B = p_174970_1_.field_70165_t;
         this.field_174993_C = p_174970_1_.field_70163_u;
         this.field_174987_D = p_174970_1_.field_70161_v;
         this.field_174988_E = p_174970_1_.field_70176_ah;
         this.field_174989_F = p_174970_1_.field_70162_ai;
         this.field_174990_G = p_174970_1_.field_70164_aj;
         this.field_175008_n.func_178163_a(p_174970_1_.field_70165_t, p_174970_1_.field_70161_v);
      }

      if (Config.isDynamicLights()) {
         DynamicLights.update(this);
      }

      this.field_72769_h.field_72984_F.func_76318_c("renderlistcamera");
      double d3 = p_174970_1_.field_70142_S + (p_174970_1_.field_70165_t - p_174970_1_.field_70142_S) * p_174970_2_;
      double d4 = p_174970_1_.field_70137_T + (p_174970_1_.field_70163_u - p_174970_1_.field_70137_T) * p_174970_2_;
      double d5 = p_174970_1_.field_70136_U + (p_174970_1_.field_70161_v - p_174970_1_.field_70136_U) * p_174970_2_;
      this.field_174996_N.func_178004_a(d3, d4, d5);
      this.field_72769_h.field_72984_F.func_76318_c("cull");
      if (this.field_175001_U != null) {
         Frustum frustum = new Frustum(this.field_175001_U);
         frustum.func_78547_a(this.field_175003_W.field_181059_a, this.field_175003_W.field_181060_b, this.field_175003_W.field_181061_c);
         p_174970_4_ = frustum;
      }

      this.field_72777_q.field_71424_I.func_76318_c("culling");
      BlockPos blockpos1 = new BlockPos(d3, d4 + (double)p_174970_1_.func_70047_e(), d5);
      RenderChunk renderchunk = this.field_175008_n.func_178161_a(blockpos1);
      new BlockPos(MathHelper.func_76128_c(d3 / 16.0D) * 16, MathHelper.func_76128_c(d4 / 16.0D) * 16, MathHelper.func_76128_c(d5 / 16.0D) * 16);
      this.field_147595_R = this.field_147595_R || !this.field_175009_l.isEmpty() || p_174970_1_.field_70165_t != this.field_174997_H || p_174970_1_.field_70163_u != this.field_174998_I || p_174970_1_.field_70161_v != this.field_174999_J || (double)p_174970_1_.field_70125_A != this.field_175000_K || (double)p_174970_1_.field_70177_z != this.field_174994_L;
      this.field_174997_H = p_174970_1_.field_70165_t;
      this.field_174998_I = p_174970_1_.field_70163_u;
      this.field_174999_J = p_174970_1_.field_70161_v;
      this.field_175000_K = (double)p_174970_1_.field_70125_A;
      this.field_174994_L = (double)p_174970_1_.field_70177_z;
      boolean flag = this.field_175001_U != null;
      this.field_72777_q.field_71424_I.func_76318_c("update");
      Lagometer.timerVisibility.start();
      int countLoadedChunks = this.getCountLoadedChunks();
      if (countLoadedChunks != this.countLoadedChunksPrev) {
         this.countLoadedChunksPrev = countLoadedChunks;
         this.field_147595_R = true;
      }

      int maxChunkY = 256;
      if (!ChunkVisibility.isFinished()) {
         this.field_147595_R = true;
      }

      if (!flag && this.field_147595_R && Config.isIntegratedServerRunning()) {
         maxChunkY = ChunkVisibility.getMaxChunkY(this.field_72769_h, p_174970_1_, this.field_72739_F);
      }

      RenderChunk renderChunkPlayer = this.field_175008_n.func_178161_a(new BlockPos(p_174970_1_.field_70165_t, p_174970_1_.field_70163_u, p_174970_1_.field_70161_v));
      RenderGlobal.ContainerLocalRenderInformation renderInfo;
      if (Shaders.isShadowPass) {
         this.field_72755_R = this.renderInfosShadow;
         this.renderInfosEntities = this.renderInfosEntitiesShadow;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
         if (!flag && this.field_147595_R) {
            this.clearRenderInfos();
            if (renderChunkPlayer != null && renderChunkPlayer.func_178568_j().func_177956_o() > maxChunkY) {
               this.renderInfosEntities.add(renderChunkPlayer.getRenderInfo());
            }

            Iterator it = ShadowUtils.makeShadowChunkIterator(this.field_72769_h, p_174970_2_, p_174970_1_, this.field_72739_F, this.field_175008_n);

            label245:
            while(true) {
               RenderChunk chunk;
               do {
                  do {
                     if (!it.hasNext()) {
                        break label245;
                     }

                     chunk = (RenderChunk)it.next();
                  } while(chunk == null);
               } while(chunk.func_178568_j().func_177956_o() > maxChunkY);

               renderInfo = chunk.getRenderInfo();
               if (!chunk.field_178590_b.func_178489_a() || chunk.func_178569_m()) {
                  this.field_72755_R.add(renderInfo);
               }

               if (ChunkUtils.hasEntities(chunk.getChunk())) {
                  this.renderInfosEntities.add(renderInfo);
               }

               if (chunk.func_178571_g().func_178485_b().size() > 0) {
                  this.renderInfosTileEntities.add(renderInfo);
               }
            }
         }
      } else {
         this.field_72755_R = this.renderInfosNormal;
         this.renderInfosEntities = this.renderInfosEntitiesNormal;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
      }

      if (!flag && this.field_147595_R && !Shaders.isShadowPass) {
         this.field_147595_R = false;
         this.clearRenderInfos();
         this.visibilityDeque.clear();
         Deque queue = this.visibilityDeque;
         boolean flag1 = this.field_72777_q.field_175612_E;
         boolean fog;
         RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1;
         if (renderchunk != null && renderchunk.func_178568_j().func_177956_o() <= maxChunkY) {
            fog = false;
            renderglobal$containerlocalrenderinformation1 = new RenderGlobal.ContainerLocalRenderInformation(renderchunk, (EnumFacing)null, 0);
            Set set1 = SET_ALL_FACINGS;
            if (set1.size() == 1) {
               Vector3f vector3f = this.func_174962_a(p_174970_1_, p_174970_2_);
               EnumFacing enumfacing = EnumFacing.func_176737_a(vector3f.x, vector3f.y, vector3f.z).func_176734_d();
               set1.remove(enumfacing);
            }

            if (set1.isEmpty()) {
               fog = true;
            }

            if (fog && !p_174970_6_) {
               this.field_72755_R.add(renderglobal$containerlocalrenderinformation1);
            } else {
               if (p_174970_6_ && this.field_72769_h.func_180495_p(blockpos1).func_177230_c().func_149662_c()) {
                  flag1 = false;
               }

               renderchunk.func_178577_a(p_174970_5_);
               queue.add(renderglobal$containerlocalrenderinformation1);
            }
         } else {
            int i = blockpos1.func_177956_o() > 0 ? Math.min(maxChunkY, 248) : 8;
            if (renderChunkPlayer != null) {
               this.renderInfosEntities.add(renderChunkPlayer.getRenderInfo());
            }

            for(int j = -this.field_72739_F; j <= this.field_72739_F; ++j) {
               for(int k = -this.field_72739_F; k <= this.field_72739_F; ++k) {
                  RenderChunk renderchunk1 = this.field_175008_n.func_178161_a(new BlockPos((j << 4) + 8, i, (k << 4) + 8));
                  if (renderchunk1 != null && renderchunk1.isBoundingBoxInFrustum((ICamera)p_174970_4_, p_174970_5_)) {
                     renderchunk1.func_178577_a(p_174970_5_);
                     RenderGlobal.ContainerLocalRenderInformation info = renderchunk1.getRenderInfo();
                     info.initialize((EnumFacing)null, 0);
                     queue.add(info);
                  }
               }
            }
         }

         this.field_72777_q.field_71424_I.func_76320_a("iteration");
         fog = Config.isFogOn();

         while(!queue.isEmpty()) {
            renderglobal$containerlocalrenderinformation1 = (RenderGlobal.ContainerLocalRenderInformation)queue.poll();
            RenderChunk renderchunk3 = renderglobal$containerlocalrenderinformation1.field_178036_a;
            EnumFacing enumfacing2 = renderglobal$containerlocalrenderinformation1.field_178034_b;
            CompiledChunk compiledChunk3 = renderchunk3.field_178590_b;
            if (!compiledChunk3.func_178489_a() || renderchunk3.func_178569_m()) {
               this.field_72755_R.add(renderglobal$containerlocalrenderinformation1);
            }

            if (ChunkUtils.hasEntities(renderchunk3.getChunk())) {
               this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation1);
            }

            if (compiledChunk3.func_178485_b().size() > 0) {
               this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation1);
            }

            EnumFacing[] facings = flag1 ? ChunkVisibility.getFacingsNotOpposite(renderglobal$containerlocalrenderinformation1.field_178035_c) : EnumFacing.field_82609_l;
            int countFacings = facings.length;

            for(int iv = 0; iv < countFacings; ++iv) {
               EnumFacing enumfacing1 = facings[iv];
               if (!flag1 || enumfacing2 == null || compiledChunk3.func_178495_a(enumfacing2.func_176734_d(), enumfacing1)) {
                  RenderChunk renderchunk2 = this.getRenderChunkOffset(blockpos1, renderchunk3, enumfacing1, fog, maxChunkY);
                  if (renderchunk2 != null && renderchunk2.func_178577_a(p_174970_5_) && renderchunk2.isBoundingBoxInFrustum((ICamera)p_174970_4_, p_174970_5_)) {
                     int setFacing = renderglobal$containerlocalrenderinformation1.field_178035_c | 1 << enumfacing1.ordinal();
                     RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = renderchunk2.getRenderInfo();
                     renderglobal$containerlocalrenderinformation.initialize(enumfacing1, setFacing);
                     queue.add(renderglobal$containerlocalrenderinformation);
                  }
               }
            }
         }

         this.field_72777_q.field_71424_I.func_76319_b();
      }

      this.field_72777_q.field_71424_I.func_76318_c("captureFrustum");
      if (this.field_175002_T) {
         this.func_174984_a(d3, d4, d5);
         this.field_175002_T = false;
      }

      Lagometer.timerVisibility.end();
      if (Shaders.isShadowPass) {
         Shaders.mcProfilerEndSection();
      } else {
         this.field_72777_q.field_71424_I.func_76318_c("rebuildNear");
         this.field_174995_M.func_178513_e();
         Set<RenderChunk> set = this.field_175009_l;
         this.field_175009_l = Sets.newLinkedHashSet();
         Lagometer.timerChunkUpdate.start();
         Iterator i$ = this.field_72755_R.iterator();

         while(true) {
            RenderChunk renderchunk4;
            do {
               if (!i$.hasNext()) {
                  Lagometer.timerChunkUpdate.end();
                  this.field_175009_l.addAll(set);
                  this.field_72777_q.field_71424_I.func_76319_b();
                  return;
               }

               renderInfo = (RenderGlobal.ContainerLocalRenderInformation)i$.next();
               renderchunk4 = renderInfo.field_178036_a;
            } while(!renderchunk4.func_178569_m() && !set.contains(renderchunk4));

            this.field_147595_R = true;
            BlockPos posChunk = renderchunk4.func_178568_j();
            boolean flag3 = blockpos1.func_177954_c((double)(posChunk.func_177958_n() + 8), (double)(posChunk.func_177956_o() + 8), (double)(posChunk.func_177952_p() + 8)) < 768.0D;
            if (!flag3) {
               this.field_175009_l.add(renderchunk4);
            } else if (!renderchunk4.isPlayerUpdate()) {
               this.chunksToUpdateForced.add(renderchunk4);
            } else {
               this.field_72777_q.field_71424_I.func_76320_a("build near");
               this.field_174995_M.func_178505_b(renderchunk4);
               renderchunk4.func_178575_a(false);
               this.field_72777_q.field_71424_I.func_76319_b();
            }
         }
      }
   }

   private boolean func_174983_a(BlockPos p_174983_1_, RenderChunk p_174983_2_) {
      BlockPos blockpos = p_174983_2_.func_178568_j();
      return MathHelper.func_76130_a(p_174983_1_.func_177958_n() - blockpos.func_177958_n()) > 16 ? false : (MathHelper.func_76130_a(p_174983_1_.func_177956_o() - blockpos.func_177956_o()) > 16 ? false : MathHelper.func_76130_a(p_174983_1_.func_177952_p() - blockpos.func_177952_p()) <= 16);
   }

   private Set<EnumFacing> func_174978_c(BlockPos p_174978_1_) {
      VisGraph visgraph = new VisGraph();
      BlockPos blockpos = new BlockPos(p_174978_1_.func_177958_n() >> 4 << 4, p_174978_1_.func_177956_o() >> 4 << 4, p_174978_1_.func_177952_p() >> 4 << 4);
      Chunk chunk = this.field_72769_h.func_175726_f(blockpos);
      Iterator i$ = BlockPos.func_177975_b(blockpos, blockpos.func_177982_a(15, 15, 15)).iterator();

      while(i$.hasNext()) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)i$.next();
         if (chunk.func_177428_a(blockpos$mutableblockpos).func_149662_c()) {
            visgraph.func_178606_a(blockpos$mutableblockpos);
         }
      }

      return visgraph.func_178609_b(p_174978_1_);
   }

   private RenderChunk getRenderChunkOffset(BlockPos p_getRenderChunkOffset_1_, RenderChunk p_getRenderChunkOffset_2_, EnumFacing p_getRenderChunkOffset_3_, boolean p_getRenderChunkOffset_4_, int p_getRenderChunkOffset_5_) {
      RenderChunk neighbour = p_getRenderChunkOffset_2_.getRenderChunkNeighbour(p_getRenderChunkOffset_3_);
      if (neighbour == null) {
         return null;
      } else if (neighbour.func_178568_j().func_177956_o() > p_getRenderChunkOffset_5_) {
         return null;
      } else {
         if (p_getRenderChunkOffset_4_) {
            BlockPos var4 = neighbour.func_178568_j();
            int dxs = p_getRenderChunkOffset_1_.func_177958_n() - var4.func_177958_n();
            int dzs = p_getRenderChunkOffset_1_.func_177952_p() - var4.func_177952_p();
            int distSq = dxs * dxs + dzs * dzs;
            if (distSq > this.renderDistanceSq) {
               return null;
            }
         }

         return neighbour;
      }
   }

   private void func_174984_a(double p_174984_1_, double p_174984_3_, double p_174984_5_) {
      this.field_175001_U = new ClippingHelperImpl();
      ((ClippingHelperImpl)this.field_175001_U).func_78560_b();
      Matrix4f matrix4f = new Matrix4f(this.field_175001_U.field_178626_c);
      matrix4f.transpose();
      Matrix4f matrix4f1 = new Matrix4f(this.field_175001_U.field_178625_b);
      matrix4f1.transpose();
      Matrix4f matrix4f2 = new Matrix4f();
      Matrix4f.mul(matrix4f1, matrix4f, matrix4f2);
      matrix4f2.invert();
      this.field_175003_W.field_181059_a = p_174984_1_;
      this.field_175003_W.field_181060_b = p_174984_3_;
      this.field_175003_W.field_181061_c = p_174984_5_;
      this.field_175004_V[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
      this.field_175004_V[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
      this.field_175004_V[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
      this.field_175004_V[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
      this.field_175004_V[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
      this.field_175004_V[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
      this.field_175004_V[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_175004_V[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);

      for(int i = 0; i < 8; ++i) {
         Matrix4f.transform(matrix4f2, this.field_175004_V[i], this.field_175004_V[i]);
         Vector4f var10000 = this.field_175004_V[i];
         var10000.x /= this.field_175004_V[i].w;
         var10000 = this.field_175004_V[i];
         var10000.y /= this.field_175004_V[i].w;
         var10000 = this.field_175004_V[i];
         var10000.z /= this.field_175004_V[i].w;
         this.field_175004_V[i].w = 1.0F;
      }

   }

   protected Vector3f func_174962_a(Entity p_174962_1_, double p_174962_2_) {
      float f = (float)((double)p_174962_1_.field_70127_C + (double)(p_174962_1_.field_70125_A - p_174962_1_.field_70127_C) * p_174962_2_);
      float f1 = (float)((double)p_174962_1_.field_70126_B + (double)(p_174962_1_.field_70177_z - p_174962_1_.field_70126_B) * p_174962_2_);
      if (Minecraft.func_71410_x().field_71474_y.field_74330_P == 2) {
         f += 180.0F;
      }

      float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
      float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
      float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
      float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
      return new Vector3f(f3 * f4, f5, f2 * f4);
   }

   public int func_174977_a(EnumWorldBlockLayer p_174977_1_, double p_174977_2_, int p_174977_4_, Entity p_174977_5_) {
      RenderHelper.func_74518_a();
      if (p_174977_1_ == EnumWorldBlockLayer.TRANSLUCENT && !Shaders.isShadowPass) {
         this.field_72777_q.field_71424_I.func_76320_a("translucent_sort");
         double d0 = p_174977_5_.field_70165_t - this.field_147596_f;
         double d1 = p_174977_5_.field_70163_u - this.field_147597_g;
         double d2 = p_174977_5_.field_70161_v - this.field_147602_h;
         if (d0 * d0 + d1 * d1 + d2 * d2 > 1.0D) {
            this.field_147596_f = p_174977_5_.field_70165_t;
            this.field_147597_g = p_174977_5_.field_70163_u;
            this.field_147602_h = p_174977_5_.field_70161_v;
            int k = 0;
            this.chunksToResortTransparency.clear();
            Iterator i$ = this.field_72755_R.iterator();

            while(i$.hasNext()) {
               RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = (RenderGlobal.ContainerLocalRenderInformation)i$.next();
               if (renderglobal$containerlocalrenderinformation.field_178036_a.field_178590_b.func_178492_d(p_174977_1_) && k++ < 15) {
                  this.chunksToResortTransparency.add(renderglobal$containerlocalrenderinformation.field_178036_a);
               }
            }
         }

         this.field_72777_q.field_71424_I.func_76319_b();
      }

      this.field_72777_q.field_71424_I.func_76320_a("filterempty");
      int l = 0;
      boolean flag = p_174977_1_ == EnumWorldBlockLayer.TRANSLUCENT;
      int i1 = flag ? this.field_72755_R.size() - 1 : 0;
      int i = flag ? -1 : this.field_72755_R.size();
      int j1 = flag ? -1 : 1;

      for(int j = i1; j != i; j += j1) {
         RenderChunk renderchunk = ((RenderGlobal.ContainerLocalRenderInformation)this.field_72755_R.get(j)).field_178036_a;
         if (!renderchunk.func_178571_g().func_178491_b(p_174977_1_)) {
            ++l;
            this.field_174996_N.func_178002_a(renderchunk, p_174977_1_);
         }
      }

      if (l == 0) {
         this.field_72777_q.field_71424_I.func_76319_b();
         return l;
      } else {
         if (Config.isFogOff() && this.field_72777_q.field_71460_t.fogStandard) {
            GlStateManager.func_179106_n();
         }

         this.field_72777_q.field_71424_I.func_76318_c("render_" + p_174977_1_);
         this.func_174982_a(p_174977_1_);
         this.field_72777_q.field_71424_I.func_76319_b();
         return l;
      }
   }

   private void func_174982_a(EnumWorldBlockLayer p_174982_1_) {
      this.field_72777_q.field_71460_t.func_180436_i();
      if (OpenGlHelper.func_176075_f()) {
         GL11.glEnableClientState(32884);
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
         GL11.glEnableClientState(32888);
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77476_b);
         GL11.glEnableClientState(32888);
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
         GL11.glEnableClientState(32886);
      }

      if (Config.isShaders()) {
         ShadersRender.preRenderChunkLayer(p_174982_1_);
      }

      this.field_174996_N.func_178001_a(p_174982_1_);
      if (Config.isShaders()) {
         ShadersRender.postRenderChunkLayer(p_174982_1_);
      }

      if (OpenGlHelper.func_176075_f()) {
         Iterator i$ = DefaultVertexFormats.field_176600_a.func_177343_g().iterator();

         while(i$.hasNext()) {
            VertexFormatElement vertexformatelement = (VertexFormatElement)i$.next();
            VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.func_177375_c();
            int i = vertexformatelement.func_177369_e();
            switch(vertexformatelement$enumusage) {
            case POSITION:
               GL11.glDisableClientState(32884);
               break;
            case UV:
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + i);
               GL11.glDisableClientState(32888);
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
               break;
            case COLOR:
               GL11.glDisableClientState(32886);
               GlStateManager.func_179117_G();
            }
         }
      }

      this.field_72777_q.field_71460_t.func_175072_h();
   }

   private void func_174965_a(Iterator<DestroyBlockProgress> p_174965_1_) {
      while(p_174965_1_.hasNext()) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)p_174965_1_.next();
         int i = destroyblockprogress.func_82743_f();
         if (this.field_72773_u - i > 400) {
            p_174965_1_.remove();
         }
      }

   }

   public void func_72734_e() {
      if (Config.isShaders() && Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
         Shaders.uninit();
         Shaders.loadShaderPack();
      }

      ++this.field_72773_u;
      if (this.field_72773_u % 20 == 0) {
         this.func_174965_a(this.field_72738_E.values().iterator());
      }

   }

   private void func_180448_r() {
      if (Config.isSkyEnabled()) {
         GlStateManager.func_179106_n();
         GlStateManager.func_179118_c();
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         RenderHelper.func_74518_a();
         GlStateManager.func_179132_a(false);
         this.field_72770_i.func_110577_a(field_110926_k);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer vertexbuffer = tessellator.func_178180_c();

         for(int i = 0; i < 6; ++i) {
            GlStateManager.func_179094_E();
            if (i == 1) {
               GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 2) {
               GlStateManager.func_179114_b(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 3) {
               GlStateManager.func_179114_b(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 4) {
               GlStateManager.func_179114_b(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (i == 5) {
               GlStateManager.func_179114_b(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            int r = 40;
            int g = 40;
            int b = 40;
            if (Config.isCustomColors()) {
               Vec3 vec3d = new Vec3((double)r / 255.0D, (double)g / 255.0D, (double)b / 255.0D);
               vec3d = CustomColors.getWorldSkyColor(vec3d, this.field_72769_h, this.field_72777_q.func_175606_aa(), 0.0F);
               r = (int)(vec3d.field_72450_a * 255.0D);
               g = (int)(vec3d.field_72448_b * 255.0D);
               b = (int)(vec3d.field_72449_c * 255.0D);
            }

            vertexbuffer.func_181662_b(-100.0D, -100.0D, -100.0D).func_181673_a(0.0D, 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
            vertexbuffer.func_181662_b(-100.0D, -100.0D, 100.0D).func_181673_a(0.0D, 16.0D).func_181669_b(r, g, b, 255).func_181675_d();
            vertexbuffer.func_181662_b(100.0D, -100.0D, 100.0D).func_181673_a(16.0D, 16.0D).func_181669_b(r, g, b, 255).func_181675_d();
            vertexbuffer.func_181662_b(100.0D, -100.0D, -100.0D).func_181673_a(16.0D, 0.0D).func_181669_b(r, g, b, 255).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
         }

         GlStateManager.func_179132_a(true);
         GlStateManager.func_179098_w();
         GlStateManager.func_179141_d();
         GlStateManager.func_179084_k();
      }
   }

   public void func_174976_a(float p_174976_1_, int p_174976_2_) {
      if (Reflector.ForgeWorldProvider_getSkyRenderer.exists()) {
         WorldProvider wp = this.field_72777_q.field_71441_e.field_73011_w;
         Object skyRenderer = Reflector.call(wp, Reflector.ForgeWorldProvider_getSkyRenderer);
         if (skyRenderer != null) {
            Reflector.callVoid(skyRenderer, Reflector.IRenderHandler_render, p_174976_1_, this.field_72769_h, this.field_72777_q);
            return;
         }
      }

      if (this.field_72777_q.field_71441_e.field_73011_w.func_177502_q() == 1) {
         this.func_180448_r();
      } else if (this.field_72777_q.field_71441_e.field_73011_w.func_76569_d()) {
         GlStateManager.func_179090_x();
         boolean isShaders = Config.isShaders();
         if (isShaders) {
            Shaders.disableTexture2D();
         }

         Vec3 vec3 = this.field_72769_h.func_72833_a(this.field_72777_q.func_175606_aa(), p_174976_1_);
         vec3 = CustomColors.getSkyColor(vec3, this.field_72777_q.field_71441_e, this.field_72777_q.func_175606_aa().field_70165_t, this.field_72777_q.func_175606_aa().field_70163_u + 1.0D, this.field_72777_q.func_175606_aa().field_70161_v);
         if (isShaders) {
            Shaders.setSkyColor(vec3);
         }

         float f = (float)vec3.field_72450_a;
         float f1 = (float)vec3.field_72448_b;
         float f2 = (float)vec3.field_72449_c;
         if (p_174976_2_ != 2) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
         }

         GlStateManager.func_179124_c(f, f1, f2);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer vertexbuffer = tessellator.func_178180_c();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179127_m();
         if (isShaders) {
            Shaders.enableFog();
         }

         GlStateManager.func_179124_c(f, f1, f2);
         if (isShaders) {
            Shaders.preSkyList();
         }

         if (Config.isSkyEnabled()) {
            if (this.field_175005_X) {
               this.field_175012_t.func_177359_a();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.field_175012_t.func_177358_a(7);
               this.field_175012_t.func_177361_b();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.func_179148_o(this.field_72771_w);
            }
         }

         GlStateManager.func_179106_n();
         if (isShaders) {
            Shaders.disableFog();
         }

         GlStateManager.func_179118_c();
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         RenderHelper.func_74518_a();
         float[] afloat = this.field_72769_h.field_73011_w.func_76560_a(this.field_72769_h.func_72826_c(p_174976_1_), p_174976_1_);
         float f16;
         float f17;
         float f15;
         float f18;
         float f19;
         float f20;
         int i1;
         if (afloat != null && Config.isSunMoonEnabled()) {
            GlStateManager.func_179090_x();
            if (isShaders) {
               Shaders.disableTexture2D();
            }

            GlStateManager.func_179103_j(7425);
            GlStateManager.func_179094_E();
            GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(MathHelper.func_76126_a(this.field_72769_h.func_72929_e(p_174976_1_)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(90.0F, 0.0F, 0.0F, 1.0F);
            f16 = afloat[0];
            f17 = afloat[1];
            f15 = afloat[2];
            if (p_174976_2_ != 2) {
               float f9 = (f16 * 30.0F + f17 * 59.0F + f15 * 11.0F) / 100.0F;
               float f10 = (f16 * 30.0F + f17 * 70.0F) / 100.0F;
               f18 = (f16 * 30.0F + f15 * 70.0F) / 100.0F;
               f16 = f9;
               f17 = f10;
               f15 = f18;
            }

            vertexbuffer.func_181668_a(6, DefaultVertexFormats.field_181706_f);
            vertexbuffer.func_181662_b(0.0D, 100.0D, 0.0D).func_181666_a(f16, f17, f15, afloat[3]).func_181675_d();
            int j = true;

            for(i1 = 0; i1 <= 16; ++i1) {
               f18 = (float)i1 * 3.1415927F * 2.0F / 16.0F;
               f19 = MathHelper.func_76126_a(f18);
               f20 = MathHelper.func_76134_b(f18);
               vertexbuffer.func_181662_b((double)(f19 * 120.0F), (double)(f20 * 120.0F), (double)(-f20 * 40.0F * afloat[3])).func_181666_a(afloat[0], afloat[1], afloat[2], 0.0F).func_181675_d();
            }

            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
            GlStateManager.func_179103_j(7424);
         }

         GlStateManager.func_179098_w();
         if (isShaders) {
            Shaders.enableTexture2D();
         }

         GlStateManager.func_179120_a(770, 1, 1, 0);
         GlStateManager.func_179094_E();
         f16 = 1.0F - this.field_72769_h.func_72867_j(p_174976_1_);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, f16);
         GlStateManager.func_179114_b(-90.0F, 0.0F, 1.0F, 0.0F);
         CustomSky.renderSky(this.field_72769_h, this.field_72770_i, p_174976_1_);
         if (isShaders) {
            Shaders.preCelestialRotate();
         }

         GlStateManager.func_179114_b(this.field_72769_h.func_72826_c(p_174976_1_) * 360.0F, 1.0F, 0.0F, 0.0F);
         if (isShaders) {
            Shaders.postCelestialRotate();
         }

         f17 = 30.0F;
         if (Config.isSunTexture()) {
            this.field_72770_i.func_110577_a(field_110928_i);
            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            vertexbuffer.func_181662_b((double)(-f17), 100.0D, (double)(-f17)).func_181673_a(0.0D, 0.0D).func_181675_d();
            vertexbuffer.func_181662_b((double)f17, 100.0D, (double)(-f17)).func_181673_a(1.0D, 0.0D).func_181675_d();
            vertexbuffer.func_181662_b((double)f17, 100.0D, (double)f17).func_181673_a(1.0D, 1.0D).func_181675_d();
            vertexbuffer.func_181662_b((double)(-f17), 100.0D, (double)f17).func_181673_a(0.0D, 1.0D).func_181675_d();
            tessellator.func_78381_a();
         }

         f17 = 20.0F;
         if (Config.isMoonTexture()) {
            this.field_72770_i.func_110577_a(field_110927_h);
            int i = this.field_72769_h.func_72853_d();
            int k = i % 4;
            i1 = i / 4 % 2;
            f18 = (float)(k + 0) / 4.0F;
            f19 = (float)(i1 + 0) / 2.0F;
            f20 = (float)(k + 1) / 4.0F;
            float f14 = (float)(i1 + 1) / 2.0F;
            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            vertexbuffer.func_181662_b((double)(-f17), -100.0D, (double)f17).func_181673_a((double)f20, (double)f14).func_181675_d();
            vertexbuffer.func_181662_b((double)f17, -100.0D, (double)f17).func_181673_a((double)f18, (double)f14).func_181675_d();
            vertexbuffer.func_181662_b((double)f17, -100.0D, (double)(-f17)).func_181673_a((double)f18, (double)f19).func_181675_d();
            vertexbuffer.func_181662_b((double)(-f17), -100.0D, (double)(-f17)).func_181673_a((double)f20, (double)f19).func_181675_d();
            tessellator.func_78381_a();
         }

         GlStateManager.func_179090_x();
         if (isShaders) {
            Shaders.disableTexture2D();
         }

         f15 = this.field_72769_h.func_72880_h(p_174976_1_) * f16;
         if (f15 > 0.0F && Config.isStarsEnabled() && !CustomSky.hasSkyLayers(this.field_72769_h)) {
            GlStateManager.func_179131_c(f15, f15, f15, f15);
            if (this.field_175005_X) {
               this.field_175013_s.func_177359_a();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.field_175013_s.func_177358_a(7);
               this.field_175013_s.func_177361_b();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.func_179148_o(this.field_72772_v);
            }
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179084_k();
         GlStateManager.func_179141_d();
         GlStateManager.func_179127_m();
         if (isShaders) {
            Shaders.enableFog();
         }

         GlStateManager.func_179121_F();
         GlStateManager.func_179090_x();
         if (isShaders) {
            Shaders.disableTexture2D();
         }

         GlStateManager.func_179124_c(0.0F, 0.0F, 0.0F);
         double d0 = this.field_72777_q.field_71439_g.func_174824_e(p_174976_1_).field_72448_b - this.field_72769_h.func_72919_O();
         if (d0 < 0.0D) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(0.0F, 12.0F, 0.0F);
            if (this.field_175005_X) {
               this.field_175011_u.func_177359_a();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.field_175011_u.func_177358_a(7);
               this.field_175011_u.func_177361_b();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.func_179148_o(this.field_72781_x);
            }

            GlStateManager.func_179121_F();
            f18 = 1.0F;
            f19 = -((float)(d0 + 65.0D));
            f20 = -1.0F;
            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
            vertexbuffer.func_181662_b(-1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            tessellator.func_78381_a();
         }

         if (this.field_72769_h.field_73011_w.func_76561_g()) {
            GlStateManager.func_179124_c(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
         } else {
            GlStateManager.func_179124_c(f, f1, f2);
         }

         if (this.field_72777_q.field_71474_y.field_151451_c <= 4) {
            GlStateManager.func_179124_c(this.field_72777_q.field_71460_t.field_175080_Q, this.field_72777_q.field_71460_t.field_175082_R, this.field_72777_q.field_71460_t.field_175081_S);
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(0.0F, -((float)(d0 - 16.0D)), 0.0F);
         if (Config.isSkyEnabled()) {
            if (this.field_175005_X) {
               this.field_175011_u.func_177359_a();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.field_175011_u.func_177358_a(7);
               this.field_175011_u.func_177361_b();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.func_179148_o(this.field_72781_x);
            }
         }

         GlStateManager.func_179121_F();
         GlStateManager.func_179098_w();
         if (isShaders) {
            Shaders.enableTexture2D();
         }

         GlStateManager.func_179132_a(true);
      }

   }

   public void func_180447_b(float p_180447_1_, int p_180447_2_) {
      if (!Config.isCloudsOff()) {
         if (Reflector.ForgeWorldProvider_getCloudRenderer.exists()) {
            WorldProvider wp = this.field_72777_q.field_71441_e.field_73011_w;
            Object cloudRenderer = Reflector.call(wp, Reflector.ForgeWorldProvider_getCloudRenderer);
            if (cloudRenderer != null) {
               Reflector.callVoid(cloudRenderer, Reflector.IRenderHandler_render, p_180447_1_, this.field_72769_h, this.field_72777_q);
               return;
            }
         }

         if (this.field_72777_q.field_71441_e.field_73011_w.func_76569_d()) {
            if (Config.isShaders()) {
               Shaders.beginClouds();
            }

            if (Config.isCloudsFancy()) {
               this.func_180445_c(p_180447_1_, p_180447_2_);
            } else {
               float partialTicksPrev = p_180447_1_;
               p_180447_1_ = 0.0F;
               GlStateManager.func_179129_p();
               float f = (float)(this.field_72777_q.func_175606_aa().field_70137_T + (this.field_72777_q.func_175606_aa().field_70163_u - this.field_72777_q.func_175606_aa().field_70137_T) * (double)p_180447_1_);
               int i = true;
               int j = true;
               Tessellator tessellator = Tessellator.func_178181_a();
               WorldRenderer vertexbuffer = tessellator.func_178180_c();
               this.field_72770_i.func_110577_a(field_110925_j);
               GlStateManager.func_179147_l();
               GlStateManager.func_179120_a(770, 771, 1, 0);
               Vec3 vec3 = this.field_72769_h.func_72824_f(p_180447_1_);
               float f1 = (float)vec3.field_72450_a;
               float f2 = (float)vec3.field_72448_b;
               float f3 = (float)vec3.field_72449_c;
               this.cloudRenderer.prepareToRender(false, this.field_72773_u, partialTicksPrev, vec3);
               if (this.cloudRenderer.shouldUpdateGlList()) {
                  this.cloudRenderer.startUpdateGlList();
                  float f10;
                  if (p_180447_2_ != 2) {
                     f10 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
                     float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
                     float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
                     f1 = f10;
                     f2 = f5;
                     f3 = f6;
                  }

                  f10 = 4.8828125E-4F;
                  double d2 = (double)((float)this.field_72773_u + p_180447_1_);
                  double d0 = this.field_72777_q.func_175606_aa().field_70169_q + (this.field_72777_q.func_175606_aa().field_70165_t - this.field_72777_q.func_175606_aa().field_70169_q) * (double)p_180447_1_ + d2 * 0.029999999329447746D;
                  double d1 = this.field_72777_q.func_175606_aa().field_70166_s + (this.field_72777_q.func_175606_aa().field_70161_v - this.field_72777_q.func_175606_aa().field_70166_s) * (double)p_180447_1_;
                  int k = MathHelper.func_76128_c(d0 / 2048.0D);
                  int l = MathHelper.func_76128_c(d1 / 2048.0D);
                  d0 -= (double)(k * 2048);
                  d1 -= (double)(l * 2048);
                  float f7 = this.field_72769_h.field_73011_w.func_76571_f() - f + 0.33F;
                  f7 += this.field_72777_q.field_71474_y.ofCloudsHeight * 128.0F;
                  float f8 = (float)(d0 * 4.8828125E-4D);
                  float f9 = (float)(d1 * 4.8828125E-4D);
                  vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);

                  for(int i1 = -256; i1 < 256; i1 += 32) {
                     for(int j1 = -256; j1 < 256; j1 += 32) {
                        vertexbuffer.func_181662_b((double)(i1 + 0), (double)f7, (double)(j1 + 32)).func_181673_a((double)((float)(i1 + 0) * 4.8828125E-4F + f8), (double)((float)(j1 + 32) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                        vertexbuffer.func_181662_b((double)(i1 + 32), (double)f7, (double)(j1 + 32)).func_181673_a((double)((float)(i1 + 32) * 4.8828125E-4F + f8), (double)((float)(j1 + 32) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                        vertexbuffer.func_181662_b((double)(i1 + 32), (double)f7, (double)(j1 + 0)).func_181673_a((double)((float)(i1 + 32) * 4.8828125E-4F + f8), (double)((float)(j1 + 0) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                        vertexbuffer.func_181662_b((double)(i1 + 0), (double)f7, (double)(j1 + 0)).func_181673_a((double)((float)(i1 + 0) * 4.8828125E-4F + f8), (double)((float)(j1 + 0) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                     }
                  }

                  tessellator.func_78381_a();
                  this.cloudRenderer.endUpdateGlList();
               }

               this.cloudRenderer.renderGlList();
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179084_k();
               GlStateManager.func_179089_o();
            }

            if (Config.isShaders()) {
               Shaders.endClouds();
            }
         }

      }
   }

   public boolean func_72721_a(double p_72721_1_, double p_72721_3_, double p_72721_5_, float p_72721_7_) {
      return false;
   }

   private void func_180445_c(float p_180445_1_, int p_180445_2_) {
      float partialTicksPrev = p_180445_1_;
      p_180445_1_ = 0.0F;
      GlStateManager.func_179129_p();
      float f = (float)(this.field_72777_q.func_175606_aa().field_70137_T + (this.field_72777_q.func_175606_aa().field_70163_u - this.field_72777_q.func_175606_aa().field_70137_T) * (double)p_180445_1_);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      float f1 = 12.0F;
      float f2 = 4.0F;
      double d0 = (double)((float)this.field_72773_u + p_180445_1_);
      double d1 = (this.field_72777_q.func_175606_aa().field_70169_q + (this.field_72777_q.func_175606_aa().field_70165_t - this.field_72777_q.func_175606_aa().field_70169_q) * (double)p_180445_1_ + d0 * 0.029999999329447746D) / 12.0D;
      double d2 = (this.field_72777_q.func_175606_aa().field_70166_s + (this.field_72777_q.func_175606_aa().field_70161_v - this.field_72777_q.func_175606_aa().field_70166_s) * (double)p_180445_1_) / 12.0D + 0.33000001311302185D;
      float f3 = this.field_72769_h.field_73011_w.func_76571_f() - f + 0.33F;
      f3 += this.field_72777_q.field_71474_y.ofCloudsHeight * 128.0F;
      int i = MathHelper.func_76128_c(d1 / 2048.0D);
      int j = MathHelper.func_76128_c(d2 / 2048.0D);
      d1 -= (double)(i * 2048);
      d2 -= (double)(j * 2048);
      this.field_72770_i.func_110577_a(field_110925_j);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      Vec3 vec3 = this.field_72769_h.func_72824_f(p_180445_1_);
      float f4 = (float)vec3.field_72450_a;
      float f5 = (float)vec3.field_72448_b;
      float f6 = (float)vec3.field_72449_c;
      this.cloudRenderer.prepareToRender(true, this.field_72773_u, partialTicksPrev, vec3);
      float f26;
      float f27;
      float f28;
      if (p_180445_2_ != 2) {
         f26 = (f4 * 30.0F + f5 * 59.0F + f6 * 11.0F) / 100.0F;
         f27 = (f4 * 30.0F + f5 * 70.0F) / 100.0F;
         f28 = (f4 * 30.0F + f6 * 70.0F) / 100.0F;
         f4 = f26;
         f5 = f27;
         f6 = f28;
      }

      f26 = f4 * 0.9F;
      f27 = f5 * 0.9F;
      f28 = f6 * 0.9F;
      float f10 = f4 * 0.7F;
      float f11 = f5 * 0.7F;
      float f12 = f6 * 0.7F;
      float f13 = f4 * 0.8F;
      float f14 = f5 * 0.8F;
      float f15 = f6 * 0.8F;
      float f16 = 0.00390625F;
      float f17 = (float)MathHelper.func_76128_c(d1) * 0.00390625F;
      float f18 = (float)MathHelper.func_76128_c(d2) * 0.00390625F;
      float f19 = (float)(d1 - (double)MathHelper.func_76128_c(d1));
      float f20 = (float)(d2 - (double)MathHelper.func_76128_c(d2));
      int k = true;
      int l = true;
      float f21 = 9.765625E-4F;
      GlStateManager.func_179152_a(12.0F, 1.0F, 12.0F);

      int j1;
      for(j1 = 0; j1 < 2; ++j1) {
         if (j1 == 0) {
            GlStateManager.func_179135_a(false, false, false, false);
         } else {
            switch(p_180445_2_) {
            case 0:
               GlStateManager.func_179135_a(false, true, true, true);
               break;
            case 1:
               GlStateManager.func_179135_a(true, false, false, true);
               break;
            case 2:
               GlStateManager.func_179135_a(true, true, true, true);
            }
         }

         this.cloudRenderer.renderGlList();
      }

      if (this.cloudRenderer.shouldUpdateGlList()) {
         this.cloudRenderer.startUpdateGlList();

         for(j1 = -3; j1 <= 4; ++j1) {
            for(int k1 = -3; k1 <= 4; ++k1) {
               vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
               float f22 = (float)(j1 * 8);
               float f23 = (float)(k1 * 8);
               float f24 = f22 - f19;
               float f25 = f23 - f20;
               if (f3 > -5.0F) {
                  vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
               }

               if (f3 <= 5.0F) {
                  vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
               }

               int k2;
               if (j1 > -1) {
                  for(k2 = 0; k2 < 8; ++k2) {
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                  }
               }

               if (j1 <= 1) {
                  for(k2 = 0; k2 < 8; ++k2) {
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 1.0F - 9.765625E-4F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 1.0F - 9.765625E-4F), (double)(f3 + 4.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 1.0F - 9.765625E-4F), (double)(f3 + 4.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + (float)k2 + 1.0F - 9.765625E-4F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)k2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                  }
               }

               if (k1 > -1) {
                  for(k2 = 0; k2 < 8; ++k2) {
                     vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                  }
               }

               if (k1 <= 1) {
                  for(k2 = 0; k2 < 8; ++k2) {
                     vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     vertexbuffer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                  }
               }

               tessellator.func_78381_a();
            }
         }

         this.cloudRenderer.endUpdateGlList();
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179089_o();
   }

   public void func_174967_a(long p_174967_1_) {
      p_174967_1_ = (long)((double)p_174967_1_ + 1.0E8D);
      this.field_147595_R |= this.field_174995_M.func_178516_a(p_174967_1_);
      Iterator itTransparency;
      RenderChunk renderChunk;
      if (this.chunksToUpdateForced.size() > 0) {
         itTransparency = this.chunksToUpdateForced.iterator();

         while(itTransparency.hasNext()) {
            renderChunk = (RenderChunk)itTransparency.next();
            if (!this.field_174995_M.func_178507_a(renderChunk)) {
               break;
            }

            renderChunk.func_178575_a(false);
            itTransparency.remove();
            this.field_175009_l.remove(renderChunk);
            this.chunksToResortTransparency.remove(renderChunk);
         }
      }

      if (this.chunksToResortTransparency.size() > 0) {
         itTransparency = this.chunksToResortTransparency.iterator();
         if (itTransparency.hasNext()) {
            renderChunk = (RenderChunk)itTransparency.next();
            if (this.field_174995_M.func_178509_c(renderChunk)) {
               itTransparency.remove();
            }
         }
      }

      double weightTotal = 0.0D;
      int updatesPerFrame = Config.getUpdatesPerFrame();
      if (!this.field_175009_l.isEmpty()) {
         Iterator iterator = this.field_175009_l.iterator();

         while(iterator.hasNext()) {
            RenderChunk renderchunk = (RenderChunk)iterator.next();
            boolean empty = renderchunk.isChunkRegionEmpty();
            boolean flag;
            if (empty) {
               flag = this.field_174995_M.func_178505_b(renderchunk);
            } else {
               flag = this.field_174995_M.func_178507_a(renderchunk);
            }

            if (!flag) {
               break;
            }

            renderchunk.func_178575_a(false);
            iterator.remove();
            if (!empty) {
               double weight = 2.0D * RenderChunkUtils.getRelativeBufferSize(renderchunk);
               weightTotal += weight;
               if (weightTotal > (double)updatesPerFrame) {
                  break;
               }
            }
         }
      }

   }

   public void func_180449_a(Entity p_180449_1_, float p_180449_2_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      WorldBorder worldborder = this.field_72769_h.func_175723_af();
      double d0 = (double)(this.field_72777_q.field_71474_y.field_151451_c * 16);
      if (p_180449_1_.field_70165_t >= worldborder.func_177728_d() - d0 || p_180449_1_.field_70165_t <= worldborder.func_177726_b() + d0 || p_180449_1_.field_70161_v >= worldborder.func_177733_e() - d0 || p_180449_1_.field_70161_v <= worldborder.func_177736_c() + d0) {
         double d1 = 1.0D - worldborder.func_177745_a(p_180449_1_) / d0;
         d1 = Math.pow(d1, 4.0D);
         double d2 = p_180449_1_.field_70142_S + (p_180449_1_.field_70165_t - p_180449_1_.field_70142_S) * (double)p_180449_2_;
         double d3 = p_180449_1_.field_70137_T + (p_180449_1_.field_70163_u - p_180449_1_.field_70137_T) * (double)p_180449_2_;
         double d4 = p_180449_1_.field_70136_U + (p_180449_1_.field_70161_v - p_180449_1_.field_70136_U) * (double)p_180449_2_;
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 1, 1, 0);
         this.field_72770_i.func_110577_a(field_175006_g);
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179094_E();
         int i = worldborder.func_177734_a().func_177766_a();
         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         GlStateManager.func_179131_c(f, f1, f2, (float)d1);
         GlStateManager.func_179136_a(-3.0F, -3.0F);
         GlStateManager.func_179088_q();
         GlStateManager.func_179092_a(516, 0.1F);
         GlStateManager.func_179141_d();
         GlStateManager.func_179129_p();
         float f3 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 128.0F;
         vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         vertexbuffer.func_178969_c(-d2, -d3, -d4);
         double d5 = Math.max((double)MathHelper.func_76128_c(d4 - d0), worldborder.func_177736_c());
         double d6 = Math.min((double)MathHelper.func_76143_f(d4 + d0), worldborder.func_177733_e());
         float f11;
         double d11;
         double d14;
         float f14;
         if (d2 > worldborder.func_177728_d() - d0) {
            f11 = 0.0F;

            for(d11 = d5; d11 < d6; f11 += 0.5F) {
               d14 = Math.min(1.0D, d6 - d11);
               f14 = (float)d14 * 0.5F;
               vertexbuffer.func_181662_b(worldborder.func_177728_d(), 256.0D, d11).func_181673_a((double)(f3 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177728_d(), 256.0D, d11 + d14).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177728_d(), 0.0D, d11 + d14).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177728_d(), 0.0D, d11).func_181673_a((double)(f3 + f11), (double)(f3 + 128.0F)).func_181675_d();
               ++d11;
            }
         }

         if (d2 < worldborder.func_177726_b() + d0) {
            f11 = 0.0F;

            for(d11 = d5; d11 < d6; f11 += 0.5F) {
               d14 = Math.min(1.0D, d6 - d11);
               f14 = (float)d14 * 0.5F;
               vertexbuffer.func_181662_b(worldborder.func_177726_b(), 256.0D, d11).func_181673_a((double)(f3 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177726_b(), 256.0D, d11 + d14).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177726_b(), 0.0D, d11 + d14).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).func_181675_d();
               vertexbuffer.func_181662_b(worldborder.func_177726_b(), 0.0D, d11).func_181673_a((double)(f3 + f11), (double)(f3 + 128.0F)).func_181675_d();
               ++d11;
            }
         }

         d5 = Math.max((double)MathHelper.func_76128_c(d2 - d0), worldborder.func_177726_b());
         d6 = Math.min((double)MathHelper.func_76143_f(d2 + d0), worldborder.func_177728_d());
         if (d4 > worldborder.func_177733_e() - d0) {
            f11 = 0.0F;

            for(d11 = d5; d11 < d6; f11 += 0.5F) {
               d14 = Math.min(1.0D, d6 - d11);
               f14 = (float)d14 * 0.5F;
               vertexbuffer.func_181662_b(d11, 256.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11 + d14, 256.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11 + d14, 0.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11, 0.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f11), (double)(f3 + 128.0F)).func_181675_d();
               ++d11;
            }
         }

         if (d4 < worldborder.func_177736_c() + d0) {
            f11 = 0.0F;

            for(d11 = d5; d11 < d6; f11 += 0.5F) {
               d14 = Math.min(1.0D, d6 - d11);
               f14 = (float)d14 * 0.5F;
               vertexbuffer.func_181662_b(d11, 256.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11 + d14, 256.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11 + d14, 0.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).func_181675_d();
               vertexbuffer.func_181662_b(d11, 0.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f11), (double)(f3 + 128.0F)).func_181675_d();
               ++d11;
            }
         }

         tessellator.func_78381_a();
         vertexbuffer.func_178969_c(0.0D, 0.0D, 0.0D);
         GlStateManager.func_179089_o();
         GlStateManager.func_179118_c();
         GlStateManager.func_179136_a(0.0F, 0.0F);
         GlStateManager.func_179113_r();
         GlStateManager.func_179141_d();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GlStateManager.func_179084_k();
         GlStateManager.func_179121_F();
         GlStateManager.func_179132_a(true);
      }

   }

   private void func_180443_s() {
      GlStateManager.func_179120_a(774, 768, 1, 0);
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.func_179136_a(-1.0F, -10.0F);
      GlStateManager.func_179088_q();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179141_d();
      GlStateManager.func_179094_E();
      if (Config.isShaders()) {
         ShadersRender.beginBlockDamage();
      }

   }

   private void func_174969_t() {
      GlStateManager.func_179118_c();
      GlStateManager.func_179136_a(0.0F, 0.0F);
      GlStateManager.func_179113_r();
      GlStateManager.func_179141_d();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179121_F();
      if (Config.isShaders()) {
         ShadersRender.endBlockDamage();
      }

   }

   public void func_174981_a(Tessellator p_174981_1_, WorldRenderer p_174981_2_, Entity p_174981_3_, float p_174981_4_) {
      double d0 = p_174981_3_.field_70142_S + (p_174981_3_.field_70165_t - p_174981_3_.field_70142_S) * (double)p_174981_4_;
      double d1 = p_174981_3_.field_70137_T + (p_174981_3_.field_70163_u - p_174981_3_.field_70137_T) * (double)p_174981_4_;
      double d2 = p_174981_3_.field_70136_U + (p_174981_3_.field_70161_v - p_174981_3_.field_70136_U) * (double)p_174981_4_;
      if (!this.field_72738_E.isEmpty()) {
         this.field_72770_i.func_110577_a(TextureMap.field_110575_b);
         this.func_180443_s();
         p_174981_2_.func_181668_a(7, DefaultVertexFormats.field_176600_a);
         p_174981_2_.func_178969_c(-d0, -d1, -d2);
         p_174981_2_.func_78914_f();
         Iterator iterator = this.field_72738_E.values().iterator();

         while(iterator.hasNext()) {
            DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iterator.next();
            BlockPos blockpos = destroyblockprogress.func_180246_b();
            double d3 = (double)blockpos.func_177958_n() - d0;
            double d4 = (double)blockpos.func_177956_o() - d1;
            double d5 = (double)blockpos.func_177952_p() - d2;
            Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
            boolean renderBreaking;
            if (Reflector.ForgeTileEntity_canRenderBreaking.exists()) {
               boolean tileEntityRenderBreaking = block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSign || block instanceof BlockSkull;
               if (!tileEntityRenderBreaking) {
                  TileEntity te = this.field_72769_h.func_175625_s(blockpos);
                  if (te != null) {
                     tileEntityRenderBreaking = Reflector.callBoolean(te, Reflector.ForgeTileEntity_canRenderBreaking);
                  }
               }

               renderBreaking = !tileEntityRenderBreaking;
            } else {
               renderBreaking = !(block instanceof BlockChest) && !(block instanceof BlockEnderChest) && !(block instanceof BlockSign) && !(block instanceof BlockSkull);
            }

            if (renderBreaking) {
               if (d3 * d3 + d4 * d4 + d5 * d5 > 1024.0D) {
                  iterator.remove();
               } else {
                  IBlockState iblockstate = this.field_72769_h.func_180495_p(blockpos);
                  if (iblockstate.func_177230_c().func_149688_o() != Material.field_151579_a) {
                     int i = destroyblockprogress.func_73106_e();
                     TextureAtlasSprite textureatlassprite = this.field_94141_F[i];
                     BlockRendererDispatcher blockrendererdispatcher = this.field_72777_q.func_175602_ab();
                     blockrendererdispatcher.func_175020_a(iblockstate, blockpos, textureatlassprite, this.field_72769_h);
                  }
               }
            }
         }

         p_174981_1_.func_78381_a();
         p_174981_2_.func_178969_c(0.0D, 0.0D, 0.0D);
         this.func_174969_t();
      }

   }

   public void func_72731_b(EntityPlayer p_72731_1_, MovingObjectPosition p_72731_2_, int p_72731_3_, float p_72731_4_) {
      if (p_72731_3_ == 0 && p_72731_2_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GlStateManager.func_179131_c(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.func_179090_x();
         if (Config.isShaders()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.func_179132_a(false);
         float f = 0.002F;
         BlockPos blockpos = p_72731_2_.func_178782_a();
         Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
         if (block.func_149688_o() != Material.field_151579_a && this.field_72769_h.func_175723_af().func_177746_a(blockpos)) {
            block.func_180654_a(this.field_72769_h, blockpos);
            double d0 = p_72731_1_.field_70142_S + (p_72731_1_.field_70165_t - p_72731_1_.field_70142_S) * (double)p_72731_4_;
            double d1 = p_72731_1_.field_70137_T + (p_72731_1_.field_70163_u - p_72731_1_.field_70137_T) * (double)p_72731_4_;
            double d2 = p_72731_1_.field_70136_U + (p_72731_1_.field_70161_v - p_72731_1_.field_70136_U) * (double)p_72731_4_;
            AxisAlignedBB aabb = block.func_180646_a(this.field_72769_h, blockpos);
            Block.EnumOffsetType offsetType = block.func_176218_Q();
            if (offsetType != Block.EnumOffsetType.NONE) {
               aabb = BlockModelUtils.getOffsetBoundingBox(aabb, offsetType, blockpos);
            }

            func_181561_a(aabb.func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72317_d(-d0, -d1, -d2));
         }

         GlStateManager.func_179132_a(true);
         GlStateManager.func_179098_w();
         if (Config.isShaders()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.func_179084_k();
      }

   }

   public static void func_181561_a(AxisAlignedBB p_181561_0_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      vertexbuffer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
   }

   public static void func_181563_a(AxisAlignedBB p_181563_0_, int p_181563_1_, int p_181563_2_, int p_181563_3_, int p_181563_4_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer vertexbuffer = tessellator.func_178180_c();
      vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
      vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
      vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181706_f);
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      vertexbuffer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
   }

   private void func_72725_b(int p_72725_1_, int p_72725_2_, int p_72725_3_, int p_72725_4_, int p_72725_5_, int p_72725_6_) {
      this.field_175008_n.func_178162_a(p_72725_1_, p_72725_2_, p_72725_3_, p_72725_4_, p_72725_5_, p_72725_6_);
   }

   public void func_174960_a(BlockPos p_174960_1_) {
      int i = p_174960_1_.func_177958_n();
      int j = p_174960_1_.func_177956_o();
      int k = p_174960_1_.func_177952_p();
      this.func_72725_b(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
   }

   public void func_174959_b(BlockPos p_174959_1_) {
      int i = p_174959_1_.func_177958_n();
      int j = p_174959_1_.func_177956_o();
      int k = p_174959_1_.func_177952_p();
      this.func_72725_b(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
   }

   public void func_147585_a(int p_147585_1_, int p_147585_2_, int p_147585_3_, int p_147585_4_, int p_147585_5_, int p_147585_6_) {
      this.func_72725_b(p_147585_1_ - 1, p_147585_2_ - 1, p_147585_3_ - 1, p_147585_4_ + 1, p_147585_5_ + 1, p_147585_6_ + 1);
   }

   public void func_174961_a(String p_174961_1_, BlockPos p_174961_2_) {
      ISound isound = (ISound)this.field_147593_P.get(p_174961_2_);
      if (isound != null) {
         this.field_72777_q.func_147118_V().func_147683_b(isound);
         this.field_147593_P.remove(p_174961_2_);
      }

      if (p_174961_1_ != null) {
         ItemRecord itemrecord = ItemRecord.func_150926_b(p_174961_1_);
         if (itemrecord != null) {
            this.field_72777_q.field_71456_v.func_73833_a(itemrecord.func_150927_i());
         }

         PositionedSoundRecord positionedsoundrecord = PositionedSoundRecord.func_147675_a(new ResourceLocation(p_174961_1_), (float)p_174961_2_.func_177958_n(), (float)p_174961_2_.func_177956_o(), (float)p_174961_2_.func_177952_p());
         this.field_147593_P.put(p_174961_2_, positionedsoundrecord);
         this.field_72777_q.func_147118_V().func_147682_a(positionedsoundrecord);
      }

   }

   public void func_72704_a(String p_72704_1_, double p_72704_2_, double p_72704_4_, double p_72704_6_, float p_72704_8_, float p_72704_9_) {
   }

   public void func_85102_a(EntityPlayer p_85102_1_, String p_85102_2_, double p_85102_3_, double p_85102_5_, double p_85102_7_, float p_85102_9_, float p_85102_10_) {
   }

   public void func_180442_a(int p_180442_1_, boolean p_180442_2_, final double p_180442_3_, final double p_180442_5_, final double p_180442_7_, double p_180442_9_, double p_180442_11_, double p_180442_13_, int... p_180442_15_) {
      try {
         this.func_174974_b(p_180442_1_, p_180442_2_, p_180442_3_, p_180442_5_, p_180442_7_, p_180442_9_, p_180442_11_, p_180442_13_, p_180442_15_);
      } catch (Throwable var19) {
         CrashReport crashreport = CrashReport.func_85055_a(var19, "Exception while adding particle");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being added");
         crashreportcategory.func_71507_a("ID", p_180442_1_);
         if (p_180442_15_ != null) {
            crashreportcategory.func_71507_a("Parameters", p_180442_15_);
         }

         crashreportcategory.func_71500_a("Position", new Callable<String>() {
            public String call() throws Exception {
               return CrashReportCategory.func_85074_a(p_180442_3_, p_180442_5_, p_180442_7_);
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   private void func_174972_a(EnumParticleTypes p_174972_1_, double p_174972_2_, double p_174972_4_, double p_174972_6_, double p_174972_8_, double p_174972_10_, double p_174972_12_, int... p_174972_14_) {
      this.func_180442_a(p_174972_1_.func_179348_c(), p_174972_1_.func_179344_e(), p_174972_2_, p_174972_4_, p_174972_6_, p_174972_8_, p_174972_10_, p_174972_12_, p_174972_14_);
   }

   private EntityFX func_174974_b(int p_174974_1_, boolean p_174974_2_, double p_174974_3_, double p_174974_5_, double p_174974_7_, double p_174974_9_, double p_174974_11_, double p_174974_13_, int... p_174974_15_) {
      if (this.field_72777_q != null && this.field_72777_q.func_175606_aa() != null && this.field_72777_q.field_71452_i != null) {
         int i = this.field_72777_q.field_71474_y.field_74363_ab;
         if (i == 1 && this.field_72769_h.field_73012_v.nextInt(3) == 0) {
            i = 2;
         }

         double d0 = this.field_72777_q.func_175606_aa().field_70165_t - p_174974_3_;
         double d1 = this.field_72777_q.func_175606_aa().field_70163_u - p_174974_5_;
         double d2 = this.field_72777_q.func_175606_aa().field_70161_v - p_174974_7_;
         if (p_174974_1_ == EnumParticleTypes.EXPLOSION_HUGE.func_179348_c() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.EXPLOSION_LARGE.func_179348_c() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.EXPLOSION_NORMAL.func_179348_c() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SUSPENDED.func_179348_c() && !Config.isWaterParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SUSPENDED_DEPTH.func_179348_c() && !Config.isVoidParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SMOKE_NORMAL.func_179348_c() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SMOKE_LARGE.func_179348_c() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SPELL_MOB.func_179348_c() && !Config.isPotionParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c() && !Config.isPotionParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SPELL.func_179348_c() && !Config.isPotionParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SPELL_INSTANT.func_179348_c() && !Config.isPotionParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.SPELL_WITCH.func_179348_c() && !Config.isPotionParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.PORTAL.func_179348_c() && !Config.isPortalParticles()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.FLAME.func_179348_c() && !Config.isAnimatedFlame()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.REDSTONE.func_179348_c() && !Config.isAnimatedRedstone()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.DRIP_WATER.func_179348_c() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.DRIP_LAVA.func_179348_c() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (p_174974_1_ == EnumParticleTypes.FIREWORKS_SPARK.func_179348_c() && !Config.isFireworkParticles()) {
            return null;
         } else {
            if (!p_174974_2_) {
               double maxDistSq = 1024.0D;
               if (p_174974_1_ == EnumParticleTypes.CRIT.func_179348_c()) {
                  maxDistSq = 38416.0D;
               }

               if (d0 * d0 + d1 * d1 + d2 * d2 > maxDistSq) {
                  return null;
               }

               if (i > 1) {
                  return null;
               }
            }

            EntityFX entityFx = this.field_72777_q.field_71452_i.func_178927_a(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_);
            if (p_174974_1_ == EnumParticleTypes.WATER_BUBBLE.func_179348_c()) {
               CustomColors.updateWaterFX(entityFx, this.field_72769_h, p_174974_3_, p_174974_5_, p_174974_7_, this.renderEnv);
            }

            if (p_174974_1_ == EnumParticleTypes.WATER_SPLASH.func_179348_c()) {
               CustomColors.updateWaterFX(entityFx, this.field_72769_h, p_174974_3_, p_174974_5_, p_174974_7_, this.renderEnv);
            }

            if (p_174974_1_ == EnumParticleTypes.WATER_DROP.func_179348_c()) {
               CustomColors.updateWaterFX(entityFx, this.field_72769_h, p_174974_3_, p_174974_5_, p_174974_7_, this.renderEnv);
            }

            if (p_174974_1_ == EnumParticleTypes.TOWN_AURA.func_179348_c()) {
               CustomColors.updateMyceliumFX(entityFx);
            }

            if (p_174974_1_ == EnumParticleTypes.PORTAL.func_179348_c()) {
               CustomColors.updatePortalFX(entityFx);
            }

            if (p_174974_1_ == EnumParticleTypes.REDSTONE.func_179348_c()) {
               CustomColors.updateReddustFX(entityFx, this.field_72769_h, p_174974_3_, p_174974_5_, p_174974_7_);
            }

            return entityFx;
         }
      } else {
         return null;
      }
   }

   public void func_72703_a(Entity p_72703_1_) {
      RandomEntities.entityLoaded(p_72703_1_, this.field_72769_h);
      if (Config.isDynamicLights()) {
         DynamicLights.entityAdded(p_72703_1_, this);
      }

   }

   public void func_72709_b(Entity p_72709_1_) {
      RandomEntities.entityUnloaded(p_72709_1_, this.field_72769_h);
      if (Config.isDynamicLights()) {
         DynamicLights.entityRemoved(p_72709_1_, this);
      }

   }

   public void func_72728_f() {
   }

   public void func_180440_a(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
      switch(p_180440_1_) {
      case 1013:
      case 1018:
         if (this.field_72777_q.func_175606_aa() != null) {
            double d0 = (double)p_180440_2_.func_177958_n() - this.field_72777_q.func_175606_aa().field_70165_t;
            double d1 = (double)p_180440_2_.func_177956_o() - this.field_72777_q.func_175606_aa().field_70163_u;
            double d2 = (double)p_180440_2_.func_177952_p() - this.field_72777_q.func_175606_aa().field_70161_v;
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            double d4 = this.field_72777_q.func_175606_aa().field_70165_t;
            double d5 = this.field_72777_q.func_175606_aa().field_70163_u;
            double d6 = this.field_72777_q.func_175606_aa().field_70161_v;
            if (d3 > 0.0D) {
               d4 += d0 / d3 * 2.0D;
               d5 += d1 / d3 * 2.0D;
               d6 += d2 / d3 * 2.0D;
            }

            if (p_180440_1_ == 1013) {
               this.field_72769_h.func_72980_b(d4, d5, d6, "mob.wither.spawn", 1.0F, 1.0F, false);
            } else {
               this.field_72769_h.func_72980_b(d4, d5, d6, "mob.enderdragon.end", 5.0F, 1.0F, false);
            }
         }
      default:
      }
   }

   public void func_180439_a(EntityPlayer p_180439_1_, int p_180439_2_, BlockPos p_180439_3_, int p_180439_4_) {
      Random random = this.field_72769_h.field_73012_v;
      double d13;
      double d14;
      double d16;
      double d22;
      int j;
      double d9;
      double d11;
      switch(p_180439_2_) {
      case 1000:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.0F, false);
         break;
      case 1001:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.2F, false);
         break;
      case 1002:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.bow", 1.0F, 1.2F, false);
         break;
      case 1003:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.door_open", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1004:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.fizz", 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
         break;
      case 1005:
         if (Item.func_150899_d(p_180439_4_) instanceof ItemRecord) {
            this.field_72769_h.func_175717_a(p_180439_3_, "records." + ((ItemRecord)Item.func_150899_d(p_180439_4_)).field_150929_a);
         } else {
            this.field_72769_h.func_175717_a(p_180439_3_, (String)null);
         }
         break;
      case 1006:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.door_close", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1007:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.charge", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1008:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.fireball", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1009:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.fireball", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1010:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.wood", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1011:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.metal", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1012:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.woodbreak", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1014:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.wither.shoot", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1015:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.bat.takeoff", 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1016:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.infect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1017:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.unfect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1020:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_break", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1021:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_use", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1022:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_land", 0.3F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 2000:
         int l = p_180439_4_ % 3 - 1;
         int i = p_180439_4_ / 3 % 3 - 1;
         double d15 = (double)p_180439_3_.func_177958_n() + (double)l * 0.6D + 0.5D;
         double d17 = (double)p_180439_3_.func_177956_o() + 0.5D;
         double d19 = (double)p_180439_3_.func_177952_p() + (double)i * 0.6D + 0.5D;

         for(int k1 = 0; k1 < 10; ++k1) {
            d13 = random.nextDouble() * 0.2D + 0.01D;
            d14 = d15 + (double)l * 0.01D + (random.nextDouble() - 0.5D) * (double)i * 0.5D;
            d16 = d17 + (random.nextDouble() - 0.5D) * 0.5D;
            double d6 = d19 + (double)i * 0.01D + (random.nextDouble() - 0.5D) * (double)l * 0.5D;
            double d8 = (double)l * d13 + random.nextGaussian() * 0.01D;
            double d10 = -0.03D + random.nextGaussian() * 0.01D;
            d22 = (double)i * d13 + random.nextGaussian() * 0.01D;
            this.func_174972_a(EnumParticleTypes.SMOKE_NORMAL, d14, d16, d6, d8, d10, d22);
         }

         return;
      case 2001:
         Block block = Block.func_149729_e(p_180439_4_ & 4095);
         if (block.func_149688_o() != Material.field_151579_a) {
            this.field_72777_q.func_147118_V().func_147682_a(new PositionedSoundRecord(new ResourceLocation(block.field_149762_H.func_150495_a()), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F, (float)p_180439_3_.func_177958_n() + 0.5F, (float)p_180439_3_.func_177956_o() + 0.5F, (float)p_180439_3_.func_177952_p() + 0.5F));
         }

         this.field_72777_q.field_71452_i.func_180533_a(p_180439_3_, block.func_176203_a(p_180439_4_ >> 12 & 255));
         break;
      case 2002:
         d13 = (double)p_180439_3_.func_177958_n();
         d14 = (double)p_180439_3_.func_177956_o();
         d16 = (double)p_180439_3_.func_177952_p();

         int j1;
         for(j1 = 0; j1 < 8; ++j1) {
            this.func_174972_a(EnumParticleTypes.ITEM_CRACK, d13, d14, d16, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, Item.func_150891_b(Items.field_151068_bn), p_180439_4_);
         }

         j1 = Items.field_151068_bn.func_77620_a(p_180439_4_);
         float f = (float)(j1 >> 16 & 255) / 255.0F;
         float f1 = (float)(j1 >> 8 & 255) / 255.0F;
         float f2 = (float)(j1 >> 0 & 255) / 255.0F;
         EnumParticleTypes enumparticletypes = EnumParticleTypes.SPELL;
         if (Items.field_151068_bn.func_77833_h(p_180439_4_)) {
            enumparticletypes = EnumParticleTypes.SPELL_INSTANT;
         }

         for(int l1 = 0; l1 < 100; ++l1) {
            d22 = random.nextDouble() * 4.0D;
            double d23 = random.nextDouble() * 3.141592653589793D * 2.0D;
            double d24 = Math.cos(d23) * d22;
            d9 = 0.01D + random.nextDouble() * 0.5D;
            d11 = Math.sin(d23) * d22;
            EntityFX entityfx = this.func_174974_b(enumparticletypes.func_179348_c(), enumparticletypes.func_179344_e(), d13 + d24 * 0.1D, d14 + 0.3D, d16 + d11 * 0.1D, d24, d9, d11);
            if (entityfx != null) {
               float f3 = 0.75F + random.nextFloat() * 0.25F;
               entityfx.func_70538_b(f * f3, f1 * f3, f2 * f3);
               entityfx.func_70543_e((float)d22);
            }
         }

         this.field_72769_h.func_175731_a(p_180439_3_, "game.potion.smash", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 2003:
         double d0 = (double)p_180439_3_.func_177958_n() + 0.5D;
         double d1 = (double)p_180439_3_.func_177956_o();
         double d2 = (double)p_180439_3_.func_177952_p() + 0.5D;

         for(j = 0; j < 8; ++j) {
            this.func_174972_a(EnumParticleTypes.ITEM_CRACK, d0, d1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, Item.func_150891_b(Items.field_151061_bv));
         }

         for(double d18 = 0.0D; d18 < 6.283185307179586D; d18 += 0.15707963267948966D) {
            this.func_174972_a(EnumParticleTypes.PORTAL, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -5.0D, 0.0D, Math.sin(d18) * -5.0D);
            this.func_174972_a(EnumParticleTypes.PORTAL, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -7.0D, 0.0D, Math.sin(d18) * -7.0D);
         }

         return;
      case 2004:
         for(j = 0; j < 20; ++j) {
            d9 = (double)p_180439_3_.func_177958_n() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            d11 = (double)p_180439_3_.func_177956_o() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            double d7 = (double)p_180439_3_.func_177952_p() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            this.field_72769_h.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d9, d11, d7, 0.0D, 0.0D, 0.0D, new int[0]);
            this.field_72769_h.func_175688_a(EnumParticleTypes.FLAME, d9, d11, d7, 0.0D, 0.0D, 0.0D, new int[0]);
         }

         return;
      case 2005:
         ItemDye.func_180617_a(this.field_72769_h, p_180439_3_, p_180439_4_);
      }

   }

   public void func_180441_b(int p_180441_1_, BlockPos p_180441_2_, int p_180441_3_) {
      if (p_180441_3_ >= 0 && p_180441_3_ < 10) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)this.field_72738_E.get(p_180441_1_);
         if (destroyblockprogress == null || destroyblockprogress.func_180246_b().func_177958_n() != p_180441_2_.func_177958_n() || destroyblockprogress.func_180246_b().func_177956_o() != p_180441_2_.func_177956_o() || destroyblockprogress.func_180246_b().func_177952_p() != p_180441_2_.func_177952_p()) {
            destroyblockprogress = new DestroyBlockProgress(p_180441_1_, p_180441_2_);
            this.field_72738_E.put(p_180441_1_, destroyblockprogress);
         }

         destroyblockprogress.func_73107_a(p_180441_3_);
         destroyblockprogress.func_82744_b(this.field_72773_u);
      } else {
         this.field_72738_E.remove(p_180441_1_);
      }

   }

   public void func_174979_m() {
      this.field_147595_R = true;
   }

   public boolean hasNoChunkUpdates() {
      return this.field_175009_l.isEmpty() && this.field_174995_M.hasChunkUpdates();
   }

   public void resetClouds() {
      this.cloudRenderer.reset();
   }

   public int getCountRenderers() {
      return this.field_175008_n.field_178164_f.length;
   }

   public int getCountActiveRenderers() {
      return this.field_72755_R.size();
   }

   public int getCountEntitiesRendered() {
      return this.field_72749_I;
   }

   public int getCountTileEntitiesRendered() {
      return this.countTileEntitiesRendered;
   }

   public int getCountLoadedChunks() {
      if (this.field_72769_h == null) {
         return 0;
      } else {
         IChunkProvider chunkProvider = this.field_72769_h.func_72863_F();
         if (chunkProvider == null) {
            return 0;
         } else {
            if (chunkProvider != this.worldChunkProvider) {
               this.worldChunkProvider = chunkProvider;
               this.worldChunkProviderMap = (LongHashMap)Reflector.getFieldValue(chunkProvider, Reflector.ChunkProviderClient_chunkMapping);
            }

            return this.worldChunkProviderMap == null ? 0 : this.worldChunkProviderMap.func_76162_a();
         }
      }
   }

   public int getCountChunksToUpdate() {
      return this.field_175009_l.size();
   }

   public RenderChunk getRenderChunk(BlockPos p_getRenderChunk_1_) {
      return this.field_175008_n.func_178161_a(p_getRenderChunk_1_);
   }

   public WorldClient getWorld() {
      return this.field_72769_h;
   }

   private void clearRenderInfos() {
      if (renderEntitiesCounter > 0) {
         this.field_72755_R = new ArrayList(this.field_72755_R.size() + 16);
         this.renderInfosEntities = new ArrayList(this.renderInfosEntities.size() + 16);
         this.renderInfosTileEntities = new ArrayList(this.renderInfosTileEntities.size() + 16);
      } else {
         this.field_72755_R.clear();
         this.renderInfosEntities.clear();
         this.renderInfosTileEntities.clear();
      }

   }

   public void onPlayerPositionSet() {
      if (this.firstWorldLoad) {
         this.func_72712_a();
         this.firstWorldLoad = false;
      }

   }

   public void pauseChunkUpdates() {
      if (this.field_174995_M != null) {
         this.field_174995_M.pauseChunkUpdates();
      }

   }

   public void resumeChunkUpdates() {
      if (this.field_174995_M != null) {
         this.field_174995_M.resumeChunkUpdates();
      }

   }

   public void func_181023_a(Collection<TileEntity> p_181023_1_, Collection<TileEntity> p_181023_2_) {
      synchronized(this.field_181024_n) {
         this.field_181024_n.removeAll(p_181023_1_);
         this.field_181024_n.addAll(p_181023_2_);
      }
   }

   static {
      SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(EnumFacing.field_82609_l)));
      renderEntitiesCounter = 0;
   }

   public static class ContainerLocalRenderInformation {
      final RenderChunk field_178036_a;
      EnumFacing field_178034_b;
      int field_178035_c;

      public ContainerLocalRenderInformation(RenderChunk p_i2_1_, EnumFacing p_i2_2_, int p_i2_3_) {
         this.field_178036_a = p_i2_1_;
         this.field_178034_b = p_i2_2_;
         this.field_178035_c = p_i2_3_;
      }

      public void setFacingBit(byte p_setFacingBit_1_, EnumFacing p_setFacingBit_2_) {
         this.field_178035_c = this.field_178035_c | p_setFacingBit_1_ | 1 << p_setFacingBit_2_.ordinal();
      }

      public boolean isFacingBit(EnumFacing p_isFacingBit_1_) {
         return (this.field_178035_c & 1 << p_isFacingBit_1_.ordinal()) > 0;
      }

      private void initialize(EnumFacing p_initialize_1_, int p_initialize_2_) {
         this.field_178034_b = p_initialize_1_;
         this.field_178035_c = p_initialize_2_;
      }
   }
}
