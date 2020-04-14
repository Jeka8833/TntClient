package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;
import net.optifine.CustomBlockLayers;
import net.optifine.override.ChunkCacheOF;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.AabbFrame;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class RenderChunk {
   private final World field_178588_d;
   private final RenderGlobal field_178589_e;
   public static int field_178592_a;
   private BlockPos field_178586_f;
   public CompiledChunk field_178590_b;
   private final ReentrantLock field_178587_g;
   private final ReentrantLock field_178598_h;
   private ChunkCompileTaskGenerator field_178599_i;
   private final Set<TileEntity> field_181056_j;
   private final int field_178596_j;
   private final FloatBuffer field_178597_k;
   private final VertexBuffer[] field_178594_l;
   public AxisAlignedBB field_178591_c;
   private int field_178595_m;
   private boolean field_178593_n;
   private EnumMap<EnumFacing, BlockPos> field_181702_p;
   private BlockPos[] positionOffsets16;
   public static final EnumWorldBlockLayer[] ENUM_WORLD_BLOCK_LAYERS = EnumWorldBlockLayer.values();
   private final EnumWorldBlockLayer[] blockLayersSingle;
   private final boolean isMipmaps;
   private final boolean fixBlockLayer;
   private boolean playerUpdate;
   public int regionX;
   public int regionZ;
   private final RenderChunk[] renderChunksOfset16;
   private boolean renderChunksOffset16Updated;
   private Chunk chunk;
   private RenderChunk[] renderChunkNeighbours;
   private RenderChunk[] renderChunkNeighboursValid;
   private boolean renderChunkNeighboursUpated;
   private RenderGlobal.ContainerLocalRenderInformation renderInfo;
   public AabbFrame boundingBoxParent;

   public RenderChunk(World p_i46197_1_, RenderGlobal p_i46197_2_, BlockPos p_i46197_3_, int p_i46197_4_) {
      this.field_178590_b = CompiledChunk.field_178502_a;
      this.field_178587_g = new ReentrantLock();
      this.field_178598_h = new ReentrantLock();
      this.field_178599_i = null;
      this.field_181056_j = Sets.newHashSet();
      this.field_178597_k = GLAllocation.func_74529_h(16);
      this.field_178594_l = new VertexBuffer[EnumWorldBlockLayer.values().length];
      this.field_178595_m = -1;
      this.field_178593_n = true;
      this.field_181702_p = null;
      this.positionOffsets16 = new BlockPos[EnumFacing.field_82609_l.length];
      this.blockLayersSingle = new EnumWorldBlockLayer[1];
      this.isMipmaps = Config.isMipmaps();
      this.fixBlockLayer = !Reflector.BetterFoliageClient.exists();
      this.playerUpdate = false;
      this.renderChunksOfset16 = new RenderChunk[6];
      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighbours = new RenderChunk[EnumFacing.field_82609_l.length];
      this.renderChunkNeighboursValid = new RenderChunk[EnumFacing.field_82609_l.length];
      this.renderChunkNeighboursUpated = false;
      this.renderInfo = new RenderGlobal.ContainerLocalRenderInformation(this, (EnumFacing)null, 0);
      this.field_178588_d = p_i46197_1_;
      this.field_178589_e = p_i46197_2_;
      this.field_178596_j = p_i46197_4_;
      if (!p_i46197_3_.equals(this.func_178568_j())) {
         this.func_178576_a(p_i46197_3_);
      }

      if (OpenGlHelper.func_176075_f()) {
         for(int i = 0; i < EnumWorldBlockLayer.values().length; ++i) {
            this.field_178594_l[i] = new VertexBuffer(DefaultVertexFormats.field_176600_a);
         }
      }

   }

   public boolean func_178577_a(int p_178577_1_) {
      if (this.field_178595_m == p_178577_1_) {
         return false;
      } else {
         this.field_178595_m = p_178577_1_;
         return true;
      }
   }

   public VertexBuffer func_178565_b(int p_178565_1_) {
      return this.field_178594_l[p_178565_1_];
   }

   public void func_178576_a(BlockPos p_178576_1_) {
      this.func_178585_h();
      this.field_178586_f = p_178576_1_;
      int bits = 8;
      this.regionX = p_178576_1_.func_177958_n() >> bits << bits;
      this.regionZ = p_178576_1_.func_177952_p() >> bits << bits;
      this.field_178591_c = new AxisAlignedBB(p_178576_1_, p_178576_1_.func_177982_a(16, 16, 16));
      this.func_178567_n();

      int i;
      for(i = 0; i < this.positionOffsets16.length; ++i) {
         this.positionOffsets16[i] = null;
      }

      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighboursUpated = false;

      for(i = 0; i < this.renderChunkNeighbours.length; ++i) {
         RenderChunk neighbour = this.renderChunkNeighbours[i];
         if (neighbour != null) {
            neighbour.renderChunkNeighboursUpated = false;
         }
      }

      this.chunk = null;
      this.boundingBoxParent = null;
   }

   public void func_178570_a(float p_178570_1_, float p_178570_2_, float p_178570_3_, ChunkCompileTaskGenerator p_178570_4_) {
      CompiledChunk compiledchunk = p_178570_4_.func_178544_c();
      if (compiledchunk.func_178487_c() != null && !compiledchunk.func_178491_b(EnumWorldBlockLayer.TRANSLUCENT)) {
         WorldRenderer bufferTranslucent = p_178570_4_.func_178545_d().func_179038_a(EnumWorldBlockLayer.TRANSLUCENT);
         this.func_178573_a(bufferTranslucent, this.field_178586_f);
         bufferTranslucent.func_178993_a(compiledchunk.func_178487_c());
         this.func_178584_a(EnumWorldBlockLayer.TRANSLUCENT, p_178570_1_, p_178570_2_, p_178570_3_, bufferTranslucent, compiledchunk);
      }

   }

   public void func_178581_b(float p_178581_1_, float p_178581_2_, float p_178581_3_, ChunkCompileTaskGenerator p_178581_4_) {
      CompiledChunk compiledchunk = new CompiledChunk();
      int i = true;
      BlockPos blockpos = new BlockPos(this.field_178586_f);
      BlockPos blockpos1 = blockpos.func_177982_a(15, 15, 15);
      p_178581_4_.func_178540_f().lock();

      label354: {
         try {
            if (p_178581_4_.func_178546_a() == ChunkCompileTaskGenerator.Status.COMPILING) {
               p_178581_4_.func_178543_a(compiledchunk);
               break label354;
            }
         } finally {
            p_178581_4_.func_178540_f().unlock();
         }

         return;
      }

      VisGraph lvt_10_1_ = new VisGraph();
      HashSet lvt_11_1_ = Sets.newHashSet();
      if (!this.isChunkRegionEmpty(blockpos)) {
         ++field_178592_a;
         ChunkCacheOF blockAccess = this.makeChunkCacheOF(blockpos);
         blockAccess.renderStart();
         boolean[] aboolean = new boolean[ENUM_WORLD_BLOCK_LAYERS.length];
         BlockRendererDispatcher blockrendererdispatcher = Minecraft.func_71410_x().func_175602_ab();
         boolean forgeBlockCanRenderInLayerExists = Reflector.ForgeBlock_canRenderInLayer.exists();
         boolean forgeHooksSetRenderLayerExists = Reflector.ForgeHooksClient_setRenderLayer.exists();
         Iterator iterBlocks = BlockPosM.getAllInBoxMutable(blockpos, blockpos1).iterator();

         while(iterBlocks.hasNext()) {
            BlockPosM blockpos$mutableblockpos = (BlockPosM)iterBlocks.next();
            IBlockState iblockstate = blockAccess.func_180495_p(blockpos$mutableblockpos);
            Block block = iblockstate.func_177230_c();
            if (block.func_149662_c()) {
               lvt_10_1_.func_178606_a(blockpos$mutableblockpos);
            }

            if (ReflectorForge.blockHasTileEntity(iblockstate)) {
               TileEntity tileentity = blockAccess.func_175625_s(new BlockPos(blockpos$mutableblockpos));
               TileEntitySpecialRenderer<TileEntity> tileentityspecialrenderer = TileEntityRendererDispatcher.field_147556_a.func_147547_b(tileentity);
               if (tileentity != null && tileentityspecialrenderer != null) {
                  compiledchunk.func_178490_a(tileentity);
                  if (tileentityspecialrenderer.func_181055_a()) {
                     lvt_11_1_.add(tileentity);
                  }
               }
            }

            EnumWorldBlockLayer[] blockLayers;
            if (forgeBlockCanRenderInLayerExists) {
               blockLayers = ENUM_WORLD_BLOCK_LAYERS;
            } else {
               blockLayers = this.blockLayersSingle;
               blockLayers[0] = block.func_180664_k();
            }

            for(int ix = 0; ix < blockLayers.length; ++ix) {
               EnumWorldBlockLayer enumworldblocklayer1 = blockLayers[ix];
               if (forgeBlockCanRenderInLayerExists) {
                  boolean canRenderInLayer = Reflector.callBoolean(block, Reflector.ForgeBlock_canRenderInLayer, enumworldblocklayer1);
                  if (!canRenderInLayer) {
                     continue;
                  }
               }

               if (forgeHooksSetRenderLayerExists) {
                  Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, enumworldblocklayer1);
               }

               enumworldblocklayer1 = this.fixBlockLayer(iblockstate, enumworldblocklayer1);
               int j = enumworldblocklayer1.ordinal();
               if (block.func_149645_b() != -1) {
                  WorldRenderer vertexbuffer = p_178581_4_.func_178545_d().func_179039_a(j);
                  vertexbuffer.setBlockLayer(enumworldblocklayer1);
                  RenderEnv renderEnv = vertexbuffer.getRenderEnv(iblockstate, blockpos$mutableblockpos);
                  renderEnv.setRegionRenderCacheBuilder(p_178581_4_.func_178545_d());
                  if (!compiledchunk.func_178492_d(enumworldblocklayer1)) {
                     compiledchunk.func_178493_c(enumworldblocklayer1);
                     this.func_178573_a(vertexbuffer, blockpos);
                  }

                  aboolean[j] |= blockrendererdispatcher.func_175018_a(iblockstate, blockpos$mutableblockpos, blockAccess, vertexbuffer);
                  if (renderEnv.isOverlaysRendered()) {
                     this.postRenderOverlays(p_178581_4_.func_178545_d(), compiledchunk, aboolean);
                     renderEnv.setOverlaysRendered(false);
                  }
               }
            }

            if (forgeHooksSetRenderLayerExists) {
               Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, (Object)null);
            }
         }

         EnumWorldBlockLayer[] arr$ = ENUM_WORLD_BLOCK_LAYERS;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            EnumWorldBlockLayer enumworldblocklayer = arr$[i$];
            if (aboolean[enumworldblocklayer.ordinal()]) {
               compiledchunk.func_178486_a(enumworldblocklayer);
            }

            if (compiledchunk.func_178492_d(enumworldblocklayer)) {
               if (Config.isShaders()) {
                  SVertexBuilder.calcNormalChunkLayer(p_178581_4_.func_178545_d().func_179038_a(enumworldblocklayer));
               }

               WorldRenderer bufferBuilder = p_178581_4_.func_178545_d().func_179038_a(enumworldblocklayer);
               this.func_178584_a(enumworldblocklayer, p_178581_1_, p_178581_2_, p_178581_3_, bufferBuilder, compiledchunk);
               if (bufferBuilder.animatedSprites != null) {
                  compiledchunk.setAnimatedSprites(enumworldblocklayer, (BitSet)bufferBuilder.animatedSprites.clone());
               }
            } else {
               compiledchunk.setAnimatedSprites(enumworldblocklayer, (BitSet)null);
            }
         }

         blockAccess.renderFinish();
      }

      compiledchunk.func_178488_a(lvt_10_1_.func_178607_a());
      this.field_178587_g.lock();

      try {
         Set<TileEntity> set = Sets.newHashSet((Iterable)lvt_11_1_);
         Set<TileEntity> set1 = Sets.newHashSet((Iterable)this.field_181056_j);
         set.removeAll(this.field_181056_j);
         set1.removeAll(lvt_11_1_);
         this.field_181056_j.clear();
         this.field_181056_j.addAll(lvt_11_1_);
         this.field_178589_e.func_181023_a(set1, set);
      } finally {
         this.field_178587_g.unlock();
      }

   }

   protected void func_178578_b() {
      this.field_178587_g.lock();

      try {
         if (this.field_178599_i != null && this.field_178599_i.func_178546_a() != ChunkCompileTaskGenerator.Status.DONE) {
            this.field_178599_i.func_178542_e();
            this.field_178599_i = null;
         }
      } finally {
         this.field_178587_g.unlock();
      }

   }

   public ReentrantLock func_178579_c() {
      return this.field_178587_g;
   }

   public ChunkCompileTaskGenerator func_178574_d() {
      this.field_178587_g.lock();

      ChunkCompileTaskGenerator chunkcompiletaskgenerator;
      try {
         this.func_178578_b();
         this.field_178599_i = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.REBUILD_CHUNK);
         chunkcompiletaskgenerator = this.field_178599_i;
      } finally {
         this.field_178587_g.unlock();
      }

      return chunkcompiletaskgenerator;
   }

   public ChunkCompileTaskGenerator func_178582_e() {
      this.field_178587_g.lock();

      ChunkCompileTaskGenerator var2;
      try {
         ChunkCompileTaskGenerator chunkcompiletaskgenerator;
         if (this.field_178599_i != null && this.field_178599_i.func_178546_a() == ChunkCompileTaskGenerator.Status.PENDING) {
            chunkcompiletaskgenerator = null;
            return chunkcompiletaskgenerator;
         }

         if (this.field_178599_i != null && this.field_178599_i.func_178546_a() != ChunkCompileTaskGenerator.Status.DONE) {
            this.field_178599_i.func_178542_e();
            this.field_178599_i = null;
         }

         this.field_178599_i = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY);
         this.field_178599_i.func_178543_a(this.field_178590_b);
         chunkcompiletaskgenerator = this.field_178599_i;
         var2 = chunkcompiletaskgenerator;
      } finally {
         this.field_178587_g.unlock();
      }

      return var2;
   }

   private void func_178573_a(WorldRenderer p_178573_1_, BlockPos p_178573_2_) {
      p_178573_1_.func_181668_a(7, DefaultVertexFormats.field_176600_a);
      if (Config.isRenderRegions()) {
         int bits = 8;
         int dx = p_178573_2_.func_177958_n() >> bits << bits;
         int dy = p_178573_2_.func_177956_o() >> bits << bits;
         int dz = p_178573_2_.func_177952_p() >> bits << bits;
         dx = this.regionX;
         dz = this.regionZ;
         p_178573_1_.func_178969_c((double)(-dx), (double)(-dy), (double)(-dz));
      } else {
         p_178573_1_.func_178969_c((double)(-p_178573_2_.func_177958_n()), (double)(-p_178573_2_.func_177956_o()), (double)(-p_178573_2_.func_177952_p()));
      }

   }

   private void func_178584_a(EnumWorldBlockLayer p_178584_1_, float p_178584_2_, float p_178584_3_, float p_178584_4_, WorldRenderer p_178584_5_, CompiledChunk p_178584_6_) {
      if (p_178584_1_ == EnumWorldBlockLayer.TRANSLUCENT && !p_178584_6_.func_178491_b(p_178584_1_)) {
         p_178584_5_.func_181674_a(p_178584_2_, p_178584_3_, p_178584_4_);
         p_178584_6_.func_178494_a(p_178584_5_.func_181672_a());
      }

      p_178584_5_.func_178977_d();
   }

   private void func_178567_n() {
      GlStateManager.func_179094_E();
      GlStateManager.func_179096_D();
      float f = 1.000001F;
      GlStateManager.func_179109_b(-8.0F, -8.0F, -8.0F);
      GlStateManager.func_179152_a(f, f, f);
      GlStateManager.func_179109_b(8.0F, 8.0F, 8.0F);
      GlStateManager.func_179111_a(2982, this.field_178597_k);
      GlStateManager.func_179121_F();
   }

   public void func_178572_f() {
      GlStateManager.func_179110_a(this.field_178597_k);
   }

   public CompiledChunk func_178571_g() {
      return this.field_178590_b;
   }

   public void func_178580_a(CompiledChunk p_178580_1_) {
      this.field_178598_h.lock();

      try {
         this.field_178590_b = p_178580_1_;
      } finally {
         this.field_178598_h.unlock();
      }

   }

   public void func_178585_h() {
      this.func_178578_b();
      this.field_178590_b = CompiledChunk.field_178502_a;
   }

   public void func_178566_a() {
      this.func_178585_h();

      for(int i = 0; i < EnumWorldBlockLayer.values().length; ++i) {
         if (this.field_178594_l[i] != null) {
            this.field_178594_l[i].func_177362_c();
         }
      }

   }

   public BlockPos func_178568_j() {
      return this.field_178586_f;
   }

   public void func_178575_a(boolean p_178575_1_) {
      this.field_178593_n = p_178575_1_;
      if (p_178575_1_) {
         if (this.isWorldPlayerUpdate()) {
            this.playerUpdate = true;
         }
      } else {
         this.playerUpdate = false;
      }

   }

   public boolean func_178569_m() {
      return this.field_178593_n;
   }

   public BlockPos func_181701_a(EnumFacing p_181701_1_) {
      return this.getPositionOffset16(p_181701_1_);
   }

   public BlockPos getPositionOffset16(EnumFacing p_getPositionOffset16_1_) {
      int index = p_getPositionOffset16_1_.func_176745_a();
      BlockPos posOffset = this.positionOffsets16[index];
      if (posOffset == null) {
         posOffset = this.func_178568_j().func_177967_a(p_getPositionOffset16_1_, 16);
         this.positionOffsets16[index] = posOffset;
      }

      return posOffset;
   }

   private boolean isWorldPlayerUpdate() {
      if (this.field_178588_d instanceof WorldClient) {
         WorldClient worldClient = (WorldClient)this.field_178588_d;
         return worldClient.isPlayerUpdate();
      } else {
         return false;
      }
   }

   public boolean isPlayerUpdate() {
      return this.playerUpdate;
   }

   protected RegionRenderCache createRegionRenderCache(World p_createRegionRenderCache_1_, BlockPos p_createRegionRenderCache_2_, BlockPos p_createRegionRenderCache_3_, int p_createRegionRenderCache_4_) {
      return new RegionRenderCache(p_createRegionRenderCache_1_, p_createRegionRenderCache_2_, p_createRegionRenderCache_3_, p_createRegionRenderCache_4_);
   }

   private EnumWorldBlockLayer fixBlockLayer(IBlockState p_fixBlockLayer_1_, EnumWorldBlockLayer p_fixBlockLayer_2_) {
      if (CustomBlockLayers.isActive()) {
         EnumWorldBlockLayer layerCustom = CustomBlockLayers.getRenderLayer(p_fixBlockLayer_1_);
         if (layerCustom != null) {
            return layerCustom;
         }
      }

      if (!this.fixBlockLayer) {
         return p_fixBlockLayer_2_;
      } else {
         if (this.isMipmaps) {
            if (p_fixBlockLayer_2_ == EnumWorldBlockLayer.CUTOUT) {
               Block block = p_fixBlockLayer_1_.func_177230_c();
               if (block instanceof BlockRedstoneWire) {
                  return p_fixBlockLayer_2_;
               }

               if (block instanceof BlockCactus) {
                  return p_fixBlockLayer_2_;
               }

               return EnumWorldBlockLayer.CUTOUT_MIPPED;
            }
         } else if (p_fixBlockLayer_2_ == EnumWorldBlockLayer.CUTOUT_MIPPED) {
            return EnumWorldBlockLayer.CUTOUT;
         }

         return p_fixBlockLayer_2_;
      }
   }

   private void postRenderOverlays(RegionRenderCacheBuilder p_postRenderOverlays_1_, CompiledChunk p_postRenderOverlays_2_, boolean[] p_postRenderOverlays_3_) {
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT_MIPPED, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
      this.postRenderOverlay(EnumWorldBlockLayer.TRANSLUCENT, p_postRenderOverlays_1_, p_postRenderOverlays_2_, p_postRenderOverlays_3_);
   }

   private void postRenderOverlay(EnumWorldBlockLayer p_postRenderOverlay_1_, RegionRenderCacheBuilder p_postRenderOverlay_2_, CompiledChunk p_postRenderOverlay_3_, boolean[] p_postRenderOverlay_4_) {
      WorldRenderer bufferOverlay = p_postRenderOverlay_2_.func_179038_a(p_postRenderOverlay_1_);
      if (bufferOverlay.isDrawing()) {
         p_postRenderOverlay_3_.func_178493_c(p_postRenderOverlay_1_);
         p_postRenderOverlay_4_[p_postRenderOverlay_1_.ordinal()] = true;
      }

   }

   private ChunkCacheOF makeChunkCacheOF(BlockPos p_makeChunkCacheOF_1_) {
      BlockPos posFrom = p_makeChunkCacheOF_1_.func_177982_a(-1, -1, -1);
      BlockPos posTo = p_makeChunkCacheOF_1_.func_177982_a(16, 16, 16);
      ChunkCache chunkCache = this.createRegionRenderCache(this.field_178588_d, posFrom, posTo, 1);
      if (Reflector.MinecraftForgeClient_onRebuildChunk.exists()) {
         Reflector.call(Reflector.MinecraftForgeClient_onRebuildChunk, this.field_178588_d, p_makeChunkCacheOF_1_, chunkCache);
      }

      ChunkCacheOF chunkCacheOF = new ChunkCacheOF(chunkCache, posFrom, posTo, 1);
      return chunkCacheOF;
   }

   public RenderChunk getRenderChunkOffset16(ViewFrustum p_getRenderChunkOffset16_1_, EnumFacing p_getRenderChunkOffset16_2_) {
      if (!this.renderChunksOffset16Updated) {
         for(int i = 0; i < EnumFacing.field_82609_l.length; ++i) {
            EnumFacing ef = EnumFacing.field_82609_l[i];
            BlockPos posOffset16 = this.func_181701_a(ef);
            this.renderChunksOfset16[i] = p_getRenderChunkOffset16_1_.func_178161_a(posOffset16);
         }

         this.renderChunksOffset16Updated = true;
      }

      return this.renderChunksOfset16[p_getRenderChunkOffset16_2_.ordinal()];
   }

   public Chunk getChunk() {
      return this.getChunk(this.field_178586_f);
   }

   private Chunk getChunk(BlockPos p_getChunk_1_) {
      Chunk chunkLocal = this.chunk;
      if (chunkLocal != null && chunkLocal.func_177410_o()) {
         return chunkLocal;
      } else {
         chunkLocal = this.field_178588_d.func_175726_f(p_getChunk_1_);
         this.chunk = chunkLocal;
         return chunkLocal;
      }
   }

   public boolean isChunkRegionEmpty() {
      return this.isChunkRegionEmpty(this.field_178586_f);
   }

   private boolean isChunkRegionEmpty(BlockPos p_isChunkRegionEmpty_1_) {
      int yStart = p_isChunkRegionEmpty_1_.func_177956_o();
      int yEnd = yStart + 15;
      return this.getChunk(p_isChunkRegionEmpty_1_).func_76606_c(yStart, yEnd);
   }

   public void setRenderChunkNeighbour(EnumFacing p_setRenderChunkNeighbour_1_, RenderChunk p_setRenderChunkNeighbour_2_) {
      this.renderChunkNeighbours[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
      this.renderChunkNeighboursValid[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
   }

   public RenderChunk getRenderChunkNeighbour(EnumFacing p_getRenderChunkNeighbour_1_) {
      if (!this.renderChunkNeighboursUpated) {
         this.updateRenderChunkNeighboursValid();
      }

      return this.renderChunkNeighboursValid[p_getRenderChunkNeighbour_1_.ordinal()];
   }

   public RenderGlobal.ContainerLocalRenderInformation getRenderInfo() {
      return this.renderInfo;
   }

   private void updateRenderChunkNeighboursValid() {
      int x = this.func_178568_j().func_177958_n();
      int z = this.func_178568_j().func_177952_p();
      int north = EnumFacing.NORTH.ordinal();
      int south = EnumFacing.SOUTH.ordinal();
      int west = EnumFacing.WEST.ordinal();
      int east = EnumFacing.EAST.ordinal();
      this.renderChunkNeighboursValid[north] = this.renderChunkNeighbours[north].func_178568_j().func_177952_p() == z - 16 ? this.renderChunkNeighbours[north] : null;
      this.renderChunkNeighboursValid[south] = this.renderChunkNeighbours[south].func_178568_j().func_177952_p() == z + 16 ? this.renderChunkNeighbours[south] : null;
      this.renderChunkNeighboursValid[west] = this.renderChunkNeighbours[west].func_178568_j().func_177958_n() == x - 16 ? this.renderChunkNeighbours[west] : null;
      this.renderChunkNeighboursValid[east] = this.renderChunkNeighbours[east].func_178568_j().func_177958_n() == x + 16 ? this.renderChunkNeighbours[east] : null;
      this.renderChunkNeighboursUpated = true;
   }

   public boolean isBoundingBoxInFrustum(ICamera p_isBoundingBoxInFrustum_1_, int p_isBoundingBoxInFrustum_2_) {
      return this.getBoundingBoxParent().isBoundingBoxInFrustumFully(p_isBoundingBoxInFrustum_1_, p_isBoundingBoxInFrustum_2_) ? true : p_isBoundingBoxInFrustum_1_.func_78546_a(this.field_178591_c);
   }

   public AabbFrame getBoundingBoxParent() {
      if (this.boundingBoxParent == null) {
         BlockPos pos = this.func_178568_j();
         int x = pos.func_177958_n();
         int y = pos.func_177956_o();
         int z = pos.func_177952_p();
         int bits = 5;
         int xp = x >> bits << bits;
         int yp = y >> bits << bits;
         int zp = z >> bits << bits;
         if (xp != x || yp != y || zp != z) {
            AabbFrame bbp = this.field_178589_e.getRenderChunk(new BlockPos(xp, yp, zp)).getBoundingBoxParent();
            if (bbp != null && bbp.field_72340_a == (double)xp && bbp.field_72338_b == (double)yp && bbp.field_72339_c == (double)zp) {
               this.boundingBoxParent = bbp;
            }
         }

         if (this.boundingBoxParent == null) {
            int delta = 1 << bits;
            this.boundingBoxParent = new AabbFrame((double)xp, (double)yp, (double)zp, (double)(xp + delta), (double)(yp + delta), (double)(zp + delta));
         }
      }

      return this.boundingBoxParent;
   }

   public String toString() {
      return "pos: " + this.func_178568_j() + ", frameIndex: " + this.field_178595_m;
   }
}
