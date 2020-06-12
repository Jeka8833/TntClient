package optifine;

import com.google.common.base.Optional;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.vecmath.Matrix4f;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.property.IUnlistedProperty;

public class Reflector {
    private static final boolean logForge = logEntry("*** Reflector Forge ***");
    public static final ReflectorClass Attributes = new ReflectorClass("net.minecraftforge.client.model.Attributes");
    public static final ReflectorField Attributes_DEFAULT_BAKED_FORMAT = new ReflectorField(Attributes, "DEFAULT_BAKED_FORMAT");
    public static final ReflectorClass BetterFoliageClient = new ReflectorClass("mods.betterfoliage.client.BetterFoliageClient");
    public static final ReflectorClass BlamingTransformer = new ReflectorClass("net.minecraftforge.fml.common.asm.transformers.BlamingTransformer");
    public static final ReflectorMethod BlamingTransformer_onCrash = new ReflectorMethod(BlamingTransformer, "onCrash");
    public static final ReflectorClass ChunkWatchEvent_UnWatch = new ReflectorClass("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
    public static ReflectorConstructor ChunkWatchEvent_UnWatch_Constructor = new ReflectorConstructor(ChunkWatchEvent_UnWatch, new Class[]{ChunkCoordIntPair.class, EntityPlayerMP.class});
    public static final ReflectorClass CoreModManager = new ReflectorClass("net.minecraftforge.fml.relauncher.CoreModManager");
    public static final ReflectorMethod CoreModManager_onCrash = new ReflectorMethod(CoreModManager, "onCrash");
    public static final ReflectorClass DimensionManager = new ReflectorClass("net.minecraftforge.common.DimensionManager");
    public static ReflectorMethod DimensionManager_createProviderFor = new ReflectorMethod(DimensionManager, "createProviderFor");
    public static final ReflectorMethod DimensionManager_getStaticDimensionIDs = new ReflectorMethod(DimensionManager, "getStaticDimensionIDs");
    public static final ReflectorClass DrawScreenEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
    public static ReflectorConstructor DrawScreenEvent_Pre_Constructor = new ReflectorConstructor(DrawScreenEvent_Pre, new Class[]{GuiScreen.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
    public static final ReflectorClass DrawScreenEvent_Post = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
    public static ReflectorConstructor DrawScreenEvent_Post_Constructor = new ReflectorConstructor(DrawScreenEvent_Post, new Class[]{GuiScreen.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
    public static final ReflectorClass EntityViewRenderEvent_CameraSetup = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup");
    public static final ReflectorConstructor EntityViewRenderEvent_CameraSetup_Constructor = new ReflectorConstructor(EntityViewRenderEvent_CameraSetup, new Class[]{EntityRenderer.class, Entity.class, Block.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
    public static final ReflectorField EntityViewRenderEvent_CameraSetup_yaw = new ReflectorField(EntityViewRenderEvent_CameraSetup, "yaw");
    public static final ReflectorField EntityViewRenderEvent_CameraSetup_pitch = new ReflectorField(EntityViewRenderEvent_CameraSetup, "pitch");
    public static final ReflectorField EntityViewRenderEvent_CameraSetup_roll = new ReflectorField(EntityViewRenderEvent_CameraSetup, "roll");
    public static final ReflectorClass EntityViewRenderEvent_FogColors = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
    public static final ReflectorConstructor EntityViewRenderEvent_FogColors_Constructor = new ReflectorConstructor(EntityViewRenderEvent_FogColors, new Class[]{EntityRenderer.class, Entity.class, Block.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
    public static final ReflectorField EntityViewRenderEvent_FogColors_red = new ReflectorField(EntityViewRenderEvent_FogColors, "red");
    public static final ReflectorField EntityViewRenderEvent_FogColors_green = new ReflectorField(EntityViewRenderEvent_FogColors, "green");
    public static final ReflectorField EntityViewRenderEvent_FogColors_blue = new ReflectorField(EntityViewRenderEvent_FogColors, "blue");
    public static final ReflectorClass Event = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event");
    public static ReflectorMethod Event_isCanceled = new ReflectorMethod(Event, "isCanceled");
    public static final ReflectorClass EventBus = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.EventBus");
    public static final ReflectorMethod EventBus_post = new ReflectorMethod(EventBus, "post");
    public static final ReflectorClass Event_Result = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event$Result");
    public static final ReflectorField Event_Result_DENY = new ReflectorField(Event_Result, "DENY");
    public static ReflectorField Event_Result_ALLOW = new ReflectorField(Event_Result, "ALLOW");
    public static final ReflectorField Event_Result_DEFAULT = new ReflectorField(Event_Result, "DEFAULT");
    public static final ReflectorClass ExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.ExtendedBlockState");
    public static ReflectorConstructor ExtendedBlockState_Constructor = new ReflectorConstructor(ExtendedBlockState, new Class[]{Block.class, IProperty[].class, IUnlistedProperty[].class});
    public static final ReflectorClass FMLClientHandler = new ReflectorClass("net.minecraftforge.fml.client.FMLClientHandler");
    public static final ReflectorMethod FMLClientHandler_instance = new ReflectorMethod(FMLClientHandler, "instance");
    public static final ReflectorMethod FMLClientHandler_isLoading = new ReflectorMethod(FMLClientHandler, "isLoading");
    public static ReflectorMethod FMLClientHandler_trackBrokenTexture = new ReflectorMethod(FMLClientHandler, "trackBrokenTexture");
    public static ReflectorMethod FMLClientHandler_trackMissingTexture = new ReflectorMethod(FMLClientHandler, "trackMissingTexture");
    public static final ReflectorClass FMLCommonHandler = new ReflectorClass("net.minecraftforge.fml.common.FMLCommonHandler");
    public static ReflectorMethod FMLCommonHandler_callFuture = new ReflectorMethod(FMLCommonHandler, "callFuture");
    public static final ReflectorMethod FMLCommonHandler_enhanceCrashReport = new ReflectorMethod(FMLCommonHandler, "enhanceCrashReport");
    public static final ReflectorMethod FMLCommonHandler_getBrandings = new ReflectorMethod(FMLCommonHandler, "getBrandings");
    public static final ReflectorMethod FMLCommonHandler_handleServerAboutToStart = new ReflectorMethod(FMLCommonHandler, "handleServerAboutToStart");
    public static final ReflectorMethod FMLCommonHandler_handleServerStarting = new ReflectorMethod(FMLCommonHandler, "handleServerStarting");
    public static final ReflectorMethod FMLCommonHandler_instance = new ReflectorMethod(FMLCommonHandler, "instance");
    public static final ReflectorClass ForgeBiome = new ReflectorClass(BiomeGenBase.class);
    public static final ReflectorMethod ForgeBiome_getWaterColorMultiplier = new ReflectorMethod(ForgeBiome, "getWaterColorMultiplier");
    public static final ReflectorClass ForgeBlock = new ReflectorClass(Block.class);
    public static final ReflectorMethod ForgeBlock_addDestroyEffects = new ReflectorMethod(ForgeBlock, "addDestroyEffects");
    public static final ReflectorMethod ForgeBlock_addHitEffects = new ReflectorMethod(ForgeBlock, "addHitEffects");
    public static ReflectorMethod ForgeBlock_canCreatureSpawn = new ReflectorMethod(ForgeBlock, "canCreatureSpawn");
    public static final ReflectorMethod ForgeBlock_canRenderInLayer = new ReflectorMethod(ForgeBlock, "canRenderInLayer", new Class[]{EnumWorldBlockLayer.class});
    public static ReflectorMethod ForgeBlock_doesSideBlockRendering = new ReflectorMethod(ForgeBlock, "doesSideBlockRendering");
    public static final ReflectorMethod ForgeBlock_getBedDirection = new ReflectorMethod(ForgeBlock, "getBedDirection");
    public static final ReflectorMethod ForgeBlock_getExtendedState = new ReflectorMethod(ForgeBlock, "getExtendedState");
    public static ReflectorMethod ForgeBlock_getLightOpacity = new ReflectorMethod(ForgeBlock, "getLightOpacity");
    public static ReflectorMethod ForgeBlock_getLightValue = new ReflectorMethod(ForgeBlock, "getLightValue");
    public static final ReflectorMethod ForgeBlock_hasTileEntity = new ReflectorMethod(ForgeBlock, "hasTileEntity", new Class[]{IBlockState.class});
    public static final ReflectorMethod ForgeBlock_isAir = new ReflectorMethod(ForgeBlock, "isAir");
    public static final ReflectorMethod ForgeBlock_isBed = new ReflectorMethod(ForgeBlock, "isBed");
    public static ReflectorMethod ForgeBlock_isBedFoot = new ReflectorMethod(ForgeBlock, "isBedFoot");
    public static ReflectorMethod ForgeBlock_isSideSolid = new ReflectorMethod(ForgeBlock, "isSideSolid");
    public static final ReflectorClass ForgeEntity = new ReflectorClass(Entity.class);
    public static final ReflectorMethod ForgeEntity_canRiderInteract = new ReflectorMethod(ForgeEntity, "canRiderInteract");
    public static ReflectorField ForgeEntity_captureDrops = new ReflectorField(ForgeEntity, "captureDrops");
    public static ReflectorField ForgeEntity_capturedDrops = new ReflectorField(ForgeEntity, "capturedDrops");
    public static final ReflectorMethod ForgeEntity_shouldRenderInPass = new ReflectorMethod(ForgeEntity, "shouldRenderInPass");
    public static final ReflectorMethod ForgeEntity_shouldRiderSit = new ReflectorMethod(ForgeEntity, "shouldRiderSit");
    public static final ReflectorClass ForgeEventFactory = new ReflectorClass("net.minecraftforge.event.ForgeEventFactory");
    public static final ReflectorMethod ForgeEventFactory_canEntityDespawn = new ReflectorMethod(ForgeEventFactory, "canEntityDespawn");
    public static ReflectorMethod ForgeEventFactory_canEntitySpawn = new ReflectorMethod(ForgeEventFactory, "canEntitySpawn");
    public static final ReflectorMethod ForgeEventFactory_renderBlockOverlay = new ReflectorMethod(ForgeEventFactory, "renderBlockOverlay");
    public static final ReflectorMethod ForgeEventFactory_renderFireOverlay = new ReflectorMethod(ForgeEventFactory, "renderFireOverlay");
    public static final ReflectorMethod ForgeEventFactory_renderWaterOverlay = new ReflectorMethod(ForgeEventFactory, "renderWaterOverlay");
    public static final ReflectorClass ForgeHooks = new ReflectorClass("net.minecraftforge.common.ForgeHooks");
    public static ReflectorMethod ForgeHooks_onLivingAttack = new ReflectorMethod(ForgeHooks, "onLivingAttack");
    public static ReflectorMethod ForgeHooks_onLivingDeath = new ReflectorMethod(ForgeHooks, "onLivingDeath");
    public static ReflectorMethod ForgeHooks_onLivingDrops = new ReflectorMethod(ForgeHooks, "onLivingDrops");
    public static ReflectorMethod ForgeHooks_onLivingFall = new ReflectorMethod(ForgeHooks, "onLivingFall");
    public static ReflectorMethod ForgeHooks_onLivingHurt = new ReflectorMethod(ForgeHooks, "onLivingHurt");
    public static ReflectorMethod ForgeHooks_onLivingJump = new ReflectorMethod(ForgeHooks, "onLivingJump");
    public static final ReflectorMethod ForgeHooks_onLivingSetAttackTarget = new ReflectorMethod(ForgeHooks, "onLivingSetAttackTarget");
    public static ReflectorMethod ForgeHooks_onLivingUpdate = new ReflectorMethod(ForgeHooks, "onLivingUpdate");
    public static final ReflectorClass ForgeHooksClient = new ReflectorClass("net.minecraftforge.client.ForgeHooksClient");
    public static final ReflectorMethod ForgeHooksClient_applyTransform = new ReflectorMethod(ForgeHooksClient, "applyTransform", new Class[]{Matrix4f.class, Optional.class});
    public static final ReflectorMethod ForgeHooksClient_dispatchRenderLast = new ReflectorMethod(ForgeHooksClient, "dispatchRenderLast");
    public static final ReflectorMethod ForgeHooksClient_drawScreen = new ReflectorMethod(ForgeHooksClient, "drawScreen");
    public static final ReflectorMethod ForgeHooksClient_fillNormal = new ReflectorMethod(ForgeHooksClient, "fillNormal");
    public static final ReflectorMethod ForgeHooksClient_handleCameraTransforms = new ReflectorMethod(ForgeHooksClient, "handleCameraTransforms");
    public static ReflectorMethod ForgeHooksClient_getArmorModel = new ReflectorMethod(ForgeHooksClient, "getArmorModel");
    public static final ReflectorMethod ForgeHooksClient_getArmorTexture = new ReflectorMethod(ForgeHooksClient, "getArmorTexture");
    public static final ReflectorMethod ForgeHooksClient_getFogDensity = new ReflectorMethod(ForgeHooksClient, "getFogDensity");
    public static ReflectorMethod ForgeHooksClient_getFOVModifier = new ReflectorMethod(ForgeHooksClient, "getFOVModifier");
    public static final ReflectorMethod ForgeHooksClient_getMatrix = new ReflectorMethod(ForgeHooksClient, "getMatrix", new Class[]{ModelRotation.class});
    public static final ReflectorMethod ForgeHooksClient_getOffsetFOV = new ReflectorMethod(ForgeHooksClient, "getOffsetFOV");
    public static final ReflectorMethod ForgeHooksClient_loadEntityShader = new ReflectorMethod(ForgeHooksClient, "loadEntityShader");
    public static final ReflectorMethod ForgeHooksClient_onDrawBlockHighlight = new ReflectorMethod(ForgeHooksClient, "onDrawBlockHighlight");
    public static final ReflectorMethod ForgeHooksClient_onFogRender = new ReflectorMethod(ForgeHooksClient, "onFogRender");
    public static final ReflectorMethod ForgeHooksClient_onTextureStitchedPre = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPre");
    public static final ReflectorMethod ForgeHooksClient_onTextureStitchedPost = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPost");
    public static final ReflectorMethod ForgeHooksClient_orientBedCamera = new ReflectorMethod(ForgeHooksClient, "orientBedCamera");
    public static ReflectorMethod ForgeHooksClient_putQuadColor = new ReflectorMethod(ForgeHooksClient, "putQuadColor");
    public static final ReflectorMethod ForgeHooksClient_renderFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderFirstPersonHand");
    public static ReflectorMethod ForgeHooksClient_renderMainMenu = new ReflectorMethod(ForgeHooksClient, "renderMainMenu");
    public static final ReflectorMethod ForgeHooksClient_setRenderLayer = new ReflectorMethod(ForgeHooksClient, "setRenderLayer");
    public static final ReflectorMethod ForgeHooksClient_setRenderPass = new ReflectorMethod(ForgeHooksClient, "setRenderPass");
    public static final ReflectorMethod ForgeHooksClient_transform = new ReflectorMethod(ForgeHooksClient, "transform");
    public static final ReflectorClass ForgeItem = new ReflectorClass(Item.class);
    public static final ReflectorMethod ForgeItem_getDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getDurabilityForDisplay");
    public static final ReflectorMethod ForgeItem_getModel = new ReflectorMethod(ForgeItem, "getModel");
    public static ReflectorMethod ForgeItem_onEntitySwing = new ReflectorMethod(ForgeItem, "onEntitySwing");
    public static final ReflectorMethod ForgeItem_shouldCauseReequipAnimation = new ReflectorMethod(ForgeItem, "shouldCauseReequipAnimation");
    public static final ReflectorMethod ForgeItem_showDurabilityBar = new ReflectorMethod(ForgeItem, "showDurabilityBar");
    public static final ReflectorClass ForgeItemRecord = new ReflectorClass(ItemRecord.class);
    public static final ReflectorMethod ForgeItemRecord_getRecordResource = new ReflectorMethod(ForgeItemRecord, "getRecordResource", new Class[]{String.class});
    public static final ReflectorClass ForgeModContainer = new ReflectorClass("net.minecraftforge.common.ForgeModContainer");
    public static final ReflectorField ForgeModContainer_forgeLightPipelineEnabled = new ReflectorField(ForgeModContainer, "forgeLightPipelineEnabled");
    public static final ReflectorClass ForgePotionEffect = new ReflectorClass(PotionEffect.class);
    public static ReflectorMethod ForgePotionEffect_isCurativeItem = new ReflectorMethod(ForgePotionEffect, "isCurativeItem");
    public static final ReflectorClass ForgeTileEntity = new ReflectorClass(TileEntity.class);
    public static final ReflectorMethod ForgeTileEntity_canRenderBreaking = new ReflectorMethod(ForgeTileEntity, "canRenderBreaking");
    public static final ReflectorMethod ForgeTileEntity_getRenderBoundingBox = new ReflectorMethod(ForgeTileEntity, "getRenderBoundingBox");
    public static ReflectorMethod ForgeTileEntity_hasFastRenderer = new ReflectorMethod(ForgeTileEntity, "hasFastRenderer");
    public static final ReflectorMethod ForgeTileEntity_shouldRenderInPass = new ReflectorMethod(ForgeTileEntity, "shouldRenderInPass");
    public static final ReflectorClass ForgeTileEntityRendererDispatcher = new ReflectorClass(TileEntityRendererDispatcher.class);
    public static final ReflectorMethod ForgeTileEntityRendererDispatcher_preDrawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "preDrawBatch");
    public static final ReflectorMethod ForgeTileEntityRendererDispatcher_drawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "drawBatch");
    public static final ReflectorClass ForgeVertexFormatElementEnumUseage = new ReflectorClass(VertexFormatElement.EnumUsage.class);
    public static final ReflectorMethod ForgeVertexFormatElementEnumUseage_preDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "preDraw");
    public static final ReflectorMethod ForgeVertexFormatElementEnumUseage_postDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "postDraw");
    public static final ReflectorClass ForgeWorld = new ReflectorClass(World.class);
    public static ReflectorMethod ForgeWorld_countEntities = new ReflectorMethod(ForgeWorld, "countEntities", new Class[]{EnumCreatureType.class, Boolean.TYPE});
    public static final ReflectorMethod ForgeWorld_getPerWorldStorage = new ReflectorMethod(ForgeWorld, "getPerWorldStorage");
    public static final ReflectorClass ForgeWorldProvider = new ReflectorClass(WorldProvider.class);
    public static final ReflectorMethod ForgeWorldProvider_getCloudRenderer = new ReflectorMethod(ForgeWorldProvider, "getCloudRenderer");
    public static final ReflectorMethod ForgeWorldProvider_getSkyRenderer = new ReflectorMethod(ForgeWorldProvider, "getSkyRenderer");
    public static final ReflectorMethod ForgeWorldProvider_getWeatherRenderer = new ReflectorMethod(ForgeWorldProvider, "getWeatherRenderer");
    public static final ReflectorClass GuiModList = new ReflectorClass("net.minecraftforge.fml.client.GuiModList");
    public static ReflectorConstructor GuiModList_Constructor = new ReflectorConstructor(GuiModList, new Class[]{GuiScreen.class});
    public static final ReflectorClass IColoredBakedQuad = new ReflectorClass("net.minecraftforge.client.model.IColoredBakedQuad");
    public static final ReflectorClass IExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.IExtendedBlockState");
    public static final ReflectorMethod IExtendedBlockState_getClean = new ReflectorMethod(IExtendedBlockState, "getClean");
    public static final ReflectorClass IRenderHandler = new ReflectorClass("net.minecraftforge.client.IRenderHandler");
    public static final ReflectorMethod IRenderHandler_render = new ReflectorMethod(IRenderHandler, "render");
    public static final ReflectorClass ISmartBlockModel = new ReflectorClass("net.minecraftforge.client.model.ISmartBlockModel");
    public static final ReflectorMethod ISmartBlockModel_handleBlockState = new ReflectorMethod(ISmartBlockModel, "handleBlockState");
    public static final ReflectorClass ItemModelMesherForge = new ReflectorClass("net.minecraftforge.client.ItemModelMesherForge");
    public static final ReflectorConstructor ItemModelMesherForge_Constructor = new ReflectorConstructor(ItemModelMesherForge, new Class[]{ModelManager.class});
    public static final ReflectorClass Launch = new ReflectorClass("net.minecraft.launchwrapper.Launch");
    public static final ReflectorField Launch_blackboard = new ReflectorField(Launch, "blackboard");
    public static final ReflectorClass LightUtil = new ReflectorClass("net.minecraftforge.client.model.pipeline.LightUtil");
    public static final ReflectorField LightUtil_itemConsumer = new ReflectorField(LightUtil, "itemConsumer");
    public static final ReflectorMethod LightUtil_putBakedQuad = new ReflectorMethod(LightUtil, "putBakedQuad");
    public static ReflectorMethod LightUtil_renderQuadColor = new ReflectorMethod(LightUtil, "renderQuadColor");
    public static final ReflectorField LightUtil_tessellator = new ReflectorField(LightUtil, "tessellator");
    public static final ReflectorClass MinecraftForge = new ReflectorClass("net.minecraftforge.common.MinecraftForge");
    public static final ReflectorField MinecraftForge_EVENT_BUS = new ReflectorField(MinecraftForge, "EVENT_BUS");
    public static final ReflectorClass MinecraftForgeClient = new ReflectorClass("net.minecraftforge.client.MinecraftForgeClient");
    public static final ReflectorMethod MinecraftForgeClient_getRenderPass = new ReflectorMethod(MinecraftForgeClient, "getRenderPass");
    public static final ReflectorMethod MinecraftForgeClient_onRebuildChunk = new ReflectorMethod(MinecraftForgeClient, "onRebuildChunk");
    public static final ReflectorClass ModelLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader");
    public static final ReflectorMethod ModelLoader_onRegisterItems = new ReflectorMethod(ModelLoader, "onRegisterItems");
    public static final ReflectorClass RenderBlockOverlayEvent_OverlayType = new ReflectorClass("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
    public static final ReflectorField RenderBlockOverlayEvent_OverlayType_BLOCK = new ReflectorField(RenderBlockOverlayEvent_OverlayType, "BLOCK");
    public static final ReflectorClass RenderingRegistry = new ReflectorClass("net.minecraftforge.fml.client.registry.RenderingRegistry");
    public static final ReflectorMethod RenderingRegistry_loadEntityRenderers = new ReflectorMethod(RenderingRegistry, "loadEntityRenderers", new Class[]{RenderManager.class, Map.class});
    public static final ReflectorClass RenderItemInFrameEvent = new ReflectorClass("net.minecraftforge.client.event.RenderItemInFrameEvent");
    public static final ReflectorConstructor RenderItemInFrameEvent_Constructor = new ReflectorConstructor(RenderItemInFrameEvent, new Class[]{EntityItemFrame.class, RenderItemFrame.class});
    public static final ReflectorClass RenderLivingEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Pre");
    public static final ReflectorConstructor RenderLivingEvent_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Pre, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static final ReflectorClass RenderLivingEvent_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Post");
    public static final ReflectorConstructor RenderLivingEvent_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Post, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static final ReflectorClass RenderLivingEvent_Specials_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
    public static final ReflectorConstructor RenderLivingEvent_Specials_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Pre, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static final ReflectorClass RenderLivingEvent_Specials_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
    public static final ReflectorConstructor RenderLivingEvent_Specials_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Post, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, Double.TYPE, Double.TYPE, Double.TYPE});
    public static final ReflectorClass SplashScreen = new ReflectorClass("net.minecraftforge.fml.client.SplashProgress");
    public static final ReflectorClass WorldEvent_Load = new ReflectorClass("net.minecraftforge.event.world.WorldEvent$Load");
    public static final ReflectorConstructor WorldEvent_Load_Constructor = new ReflectorConstructor(WorldEvent_Load, new Class[]{World.class});
    private static final boolean logVanilla = logEntry("*** Reflector Vanilla ***");
    public static final ReflectorClass ChunkProviderClient = new ReflectorClass(ChunkProviderClient.class);
    public static ReflectorField ChunkProviderClient_chunkMapping = new ReflectorField(ChunkProviderClient, LongHashMap.class);
    public static final ReflectorClass GuiMainMenu = new ReflectorClass(GuiMainMenu.class);
    public static ReflectorField GuiMainMenu_splashText = new ReflectorField(GuiMainMenu, String.class);
    public static final ReflectorClass Minecraft = new ReflectorClass(Minecraft.class);
    public static final ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Minecraft, DefaultResourcePack.class);
    public static final ReflectorClass ModelHumanoidHead = new ReflectorClass(ModelHumanoidHead.class);
    public static ReflectorField ModelHumanoidHead_head = new ReflectorField(ModelHumanoidHead, ModelRenderer.class);
    public static final ReflectorClass ModelBat = new ReflectorClass(ModelBat.class);
    public static ReflectorFields ModelBat_ModelRenderers = new ReflectorFields(ModelBat, ModelRenderer.class, 6);
    public static final ReflectorClass ModelBlaze = new ReflectorClass(ModelBlaze.class);
    public static ReflectorField ModelBlaze_blazeHead = new ReflectorField(ModelBlaze, ModelRenderer.class);
    public static ReflectorField ModelBlaze_blazeSticks = new ReflectorField(ModelBlaze, ModelRenderer[].class);
    public static final ReflectorClass ModelDragon = new ReflectorClass(ModelDragon.class);
    public static ReflectorFields ModelDragon_ModelRenderers = new ReflectorFields(ModelDragon, ModelRenderer.class, 12);
    public static final ReflectorClass ModelEnderCrystal = new ReflectorClass(ModelEnderCrystal.class);
    public static ReflectorFields ModelEnderCrystal_ModelRenderers = new ReflectorFields(ModelEnderCrystal, ModelRenderer.class, 3);
    public static final ReflectorClass RenderEnderCrystal = new ReflectorClass(RenderEnderCrystal.class);
    public static ReflectorField RenderEnderCrystal_modelEnderCrystal = new ReflectorField(RenderEnderCrystal, ModelBase.class, 0);
    public static final ReflectorClass ModelEnderMite = new ReflectorClass(ModelEnderMite.class);
    public static ReflectorField ModelEnderMite_bodyParts = new ReflectorField(ModelEnderMite, ModelRenderer[].class);
    public static final ReflectorClass ModelGhast = new ReflectorClass(ModelGhast.class);
    public static ReflectorField ModelGhast_body = new ReflectorField(ModelGhast, ModelRenderer.class);
    public static ReflectorField ModelGhast_tentacles = new ReflectorField(ModelGhast, ModelRenderer[].class);
    public static final ReflectorClass ModelGuardian = new ReflectorClass(ModelGuardian.class);
    public static ReflectorField ModelGuardian_body = new ReflectorField(ModelGuardian, ModelRenderer.class, 0);
    public static ReflectorField ModelGuardian_eye = new ReflectorField(ModelGuardian, ModelRenderer.class, 1);
    public static ReflectorField ModelGuardian_spines = new ReflectorField(ModelGuardian, ModelRenderer[].class, 0);
    public static ReflectorField ModelGuardian_tail = new ReflectorField(ModelGuardian, ModelRenderer[].class, 1);
    public static final ReflectorClass ModelHorse = new ReflectorClass(ModelHorse.class);
    public static ReflectorFields ModelHorse_ModelRenderers = new ReflectorFields(ModelHorse, ModelRenderer.class, 39);
    public static final ReflectorClass RenderLeashKnot = new ReflectorClass(RenderLeashKnot.class);
    public static ReflectorField RenderLeashKnot_leashKnotModel = new ReflectorField(RenderLeashKnot, ModelLeashKnot.class);
    public static final ReflectorClass ModelMagmaCube = new ReflectorClass(ModelMagmaCube.class);
    public static ReflectorField ModelMagmaCube_core = new ReflectorField(ModelMagmaCube, ModelRenderer.class);
    public static ReflectorField ModelMagmaCube_segments = new ReflectorField(ModelMagmaCube, ModelRenderer[].class);
    public static final ReflectorClass ModelOcelot = new ReflectorClass(ModelOcelot.class);
    public static ReflectorFields ModelOcelot_ModelRenderers = new ReflectorFields(ModelOcelot, ModelRenderer.class, 8);
    public static final ReflectorClass ModelRabbit = new ReflectorClass(ModelRabbit.class);
    public static ReflectorFields ModelRabbit_renderers = new ReflectorFields(ModelRabbit, ModelRenderer.class, 12);
    public static final ReflectorClass ModelSilverfish = new ReflectorClass(ModelSilverfish.class);
    public static ReflectorField ModelSilverfish_bodyParts = new ReflectorField(ModelSilverfish, ModelRenderer[].class, 0);
    public static ReflectorField ModelSilverfish_wingParts = new ReflectorField(ModelSilverfish, ModelRenderer[].class, 1);
    public static final ReflectorClass ModelSlime = new ReflectorClass(ModelSlime.class);
    public static ReflectorFields ModelSlime_ModelRenderers = new ReflectorFields(ModelSlime, ModelRenderer.class, 4);
    public static final ReflectorClass ModelSquid = new ReflectorClass(ModelSquid.class);
    public static ReflectorField ModelSquid_body = new ReflectorField(ModelSquid, ModelRenderer.class);
    public static ReflectorField ModelSquid_tentacles = new ReflectorField(ModelSquid, ModelRenderer[].class);
    public static final ReflectorClass ModelWitch = new ReflectorClass(ModelWitch.class);
    public static ReflectorField ModelWitch_mole = new ReflectorField(ModelWitch, ModelRenderer.class, 0);
    public static ReflectorField ModelWitch_hat = new ReflectorField(ModelWitch, ModelRenderer.class, 1);
    public static final ReflectorClass ModelWither = new ReflectorClass(ModelWither.class);
    public static ReflectorField ModelWither_bodyParts = new ReflectorField(ModelWither, ModelRenderer[].class, 0);
    public static ReflectorField ModelWither_heads = new ReflectorField(ModelWither, ModelRenderer[].class, 1);
    public static final ReflectorClass ModelWolf = new ReflectorClass(ModelWolf.class);
    public static ReflectorField ModelWolf_tail = new ReflectorField(ModelWolf, ModelRenderer.class, 6);
    public static ReflectorField ModelWolf_mane = new ReflectorField(ModelWolf, ModelRenderer.class, 7);
    public static final ReflectorClass OptiFineClassTransformer = new ReflectorClass("optifine.OptiFineClassTransformer");
    public static final ReflectorField OptiFineClassTransformer_instance = new ReflectorField(OptiFineClassTransformer, "instance");
    public static final ReflectorMethod OptiFineClassTransformer_getOptiFineResource = new ReflectorMethod(OptiFineClassTransformer, "getOptiFineResource");
    public static final ReflectorClass RenderBoat = new ReflectorClass(RenderBoat.class);
    public static ReflectorField RenderBoat_modelBoat = new ReflectorField(RenderBoat, ModelBase.class);
    public static final ReflectorClass RenderMinecart = new ReflectorClass(RenderMinecart.class);
    public static ReflectorField RenderMinecart_modelMinecart = new ReflectorField(RenderMinecart, ModelBase.class);
    public static final ReflectorClass RenderWitherSkull = new ReflectorClass(RenderWitherSkull.class);
    public static ReflectorField RenderWitherSkull_model = new ReflectorField(RenderWitherSkull, ModelSkeletonHead.class);
    public static final ReflectorClass ResourcePackRepository = new ReflectorClass(ResourcePackRepository.class);
    public static final ReflectorField ResourcePackRepository_repositoryEntries = new ReflectorField(ResourcePackRepository, List.class, 1);
    public static final ReflectorClass TileEntityBannerRenderer = new ReflectorClass(TileEntityBannerRenderer.class);
    public static ReflectorField TileEntityBannerRenderer_bannerModel = new ReflectorField(TileEntityBannerRenderer, ModelBanner.class);
    public static final ReflectorClass TileEntityChestRenderer = new ReflectorClass(TileEntityChestRenderer.class);
    public static ReflectorField TileEntityChestRenderer_simpleChest = new ReflectorField(TileEntityChestRenderer, ModelChest.class, 0);
    public static ReflectorField TileEntityChestRenderer_largeChest = new ReflectorField(TileEntityChestRenderer, ModelChest.class, 1);
    public static final ReflectorClass TileEntityEnchantmentTableRenderer = new ReflectorClass(TileEntityEnchantmentTableRenderer.class);
    public static ReflectorField TileEntityEnchantmentTableRenderer_modelBook = new ReflectorField(TileEntityEnchantmentTableRenderer, ModelBook.class);
    public static final ReflectorClass TileEntityEnderChestRenderer = new ReflectorClass(TileEntityEnderChestRenderer.class);
    public static ReflectorField TileEntityEnderChestRenderer_modelChest = new ReflectorField(TileEntityEnderChestRenderer, ModelChest.class);
    public static final ReflectorClass TileEntitySignRenderer = new ReflectorClass(TileEntitySignRenderer.class);
    public static ReflectorField TileEntitySignRenderer_model = new ReflectorField(TileEntitySignRenderer, ModelSign.class);
    public static final ReflectorClass TileEntitySkullRenderer = new ReflectorClass(TileEntitySkullRenderer.class);
    public static ReflectorField TileEntitySkullRenderer_skeletonHead = new ReflectorField(TileEntitySkullRenderer, ModelSkeletonHead.class, 0);
    public static ReflectorField TileEntitySkullRenderer_humanoidHead = new ReflectorField(TileEntitySkullRenderer, ModelSkeletonHead.class, 1);

    public static void callVoid(ReflectorMethod p_callVoid_0_, Object... p_callVoid_1_) {
        try {
            Method method = p_callVoid_0_.getTargetMethod();

            if (method == null) {
                return;
            }

            method.invoke(null, p_callVoid_1_);
        } catch (Throwable throwable) {
            handleException(throwable, null, p_callVoid_0_, p_callVoid_1_);
        }
    }

    public static boolean callBoolean(ReflectorMethod p_callBoolean_0_, Object... p_callBoolean_1_) {
        try {
            Method method = p_callBoolean_0_.getTargetMethod();

            if (method == null) {
                return false;
            } else {
                return (Boolean) method.invoke(null, p_callBoolean_1_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, null, p_callBoolean_0_, p_callBoolean_1_);
            return false;
        }
    }

    public static int callInt(ReflectorMethod p_callInt_0_, Object... p_callInt_1_) {
        try {
            Method method = p_callInt_0_.getTargetMethod();

            if (method == null) {
                return 0;
            } else {
                return (Integer) method.invoke(null, p_callInt_1_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, null, p_callInt_0_, p_callInt_1_);
            return 0;
        }
    }

    public static float callFloat(ReflectorMethod p_callFloat_0_, Object... p_callFloat_1_) {
        try {
            Method method = p_callFloat_0_.getTargetMethod();

            if (method == null) {
                return 0.0F;
            } else {
                return (Float) method.invoke(null, p_callFloat_1_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, null, p_callFloat_0_, p_callFloat_1_);
            return 0.0F;
        }
    }

    public static String callString(ReflectorMethod p_callString_0_, Object... p_callString_1_) {
        try {
            Method method = p_callString_0_.getTargetMethod();

            if (method == null) {
                return null;
            } else {
                return (String) method.invoke(null, p_callString_1_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, null, p_callString_0_, p_callString_1_);
            return null;
        }
    }

    public static Object call(ReflectorMethod p_call_0_, Object... p_call_1_) {
        try {
            Method method = p_call_0_.getTargetMethod();

            if (method == null) {
                return null;
            } else {
                return method.invoke(null, p_call_1_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, null, p_call_0_, p_call_1_);
            return null;
        }
    }

    public static void callVoid(Object p_callVoid_0_, ReflectorMethod p_callVoid_1_, Object... p_callVoid_2_) {
        try {
            if (p_callVoid_0_ == null) {
                return;
            }

            Method method = p_callVoid_1_.getTargetMethod();

            if (method == null) {
                return;
            }

            method.invoke(p_callVoid_0_, p_callVoid_2_);
        } catch (Throwable throwable) {
            handleException(throwable, p_callVoid_0_, p_callVoid_1_, p_callVoid_2_);
        }
    }

    public static boolean callBoolean(Object p_callBoolean_0_, ReflectorMethod p_callBoolean_1_, Object... p_callBoolean_2_) {
        try {
            Method method = p_callBoolean_1_.getTargetMethod();

            if (method == null) {
                return false;
            } else {
                return (Boolean) method.invoke(p_callBoolean_0_, p_callBoolean_2_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, p_callBoolean_0_, p_callBoolean_1_, p_callBoolean_2_);
            return false;
        }
    }

    public static int callInt(Object p_callInt_0_, ReflectorMethod p_callInt_1_, Object... p_callInt_2_) {
        try {
            Method method = p_callInt_1_.getTargetMethod();

            if (method == null) {
                return 0;
            } else {
                return (Integer) method.invoke(p_callInt_0_, p_callInt_2_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, p_callInt_0_, p_callInt_1_, p_callInt_2_);
            return 0;
        }
    }

    public static double callDouble(Object p_callDouble_0_, ReflectorMethod p_callDouble_1_, Object... p_callDouble_2_) {
        try {
            Method method = p_callDouble_1_.getTargetMethod();

            if (method == null) {
                return 0.0D;
            } else {
                return (Double) method.invoke(p_callDouble_0_, p_callDouble_2_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, p_callDouble_0_, p_callDouble_1_, p_callDouble_2_);
            return 0.0D;
        }
    }

    public static void callString(Object p_callString_0_, ReflectorMethod p_callString_1_, Object... p_callString_2_) {
        try {
            Method method = p_callString_1_.getTargetMethod();

            if (method != null) {
                method.invoke(p_callString_0_, p_callString_2_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, p_callString_0_, p_callString_1_, p_callString_2_);
        }
    }

    public static Object call(Object p_call_0_, ReflectorMethod p_call_1_, Object... p_call_2_) {
        try {
            Method method = p_call_1_.getTargetMethod();

            if (method == null) {
                return null;
            } else {
                return method.invoke(p_call_0_, p_call_2_);
            }
        } catch (Throwable throwable) {
            handleException(throwable, p_call_0_, p_call_1_, p_call_2_);
            return null;
        }
    }

    public static Object getFieldValue(ReflectorField p_getFieldValue_0_) {
        return getFieldValue(null, p_getFieldValue_0_);
    }

    public static Object getFieldValue(Object p_getFieldValue_0_, ReflectorField p_getFieldValue_1_) {
        try {
            Field field = p_getFieldValue_1_.getTargetField();

            if (field == null) {
                return null;
            } else {
                return field.get(p_getFieldValue_0_);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(ReflectorFields p_getFieldValue_0_, int p_getFieldValue_1_) {
        ReflectorField reflectorfield = p_getFieldValue_0_.getReflectorField(p_getFieldValue_1_);
        return reflectorfield == null ? null : getFieldValue(reflectorfield);
    }

    public static Object getFieldValue(Object p_getFieldValue_0_, ReflectorFields p_getFieldValue_1_, int p_getFieldValue_2_) {
        ReflectorField reflectorfield = p_getFieldValue_1_.getReflectorField(p_getFieldValue_2_);
        return reflectorfield == null ? null : getFieldValue(p_getFieldValue_0_, reflectorfield);
    }

    public static float getFieldValueFloat(Object p_getFieldValueFloat_0_, ReflectorField p_getFieldValueFloat_1_, float p_getFieldValueFloat_2_) {
        Object object = getFieldValue(p_getFieldValueFloat_0_, p_getFieldValueFloat_1_);

        if (!(object instanceof Float)) {
            return p_getFieldValueFloat_2_;
        } else {
            return (Float) object;
        }
    }

    public static void setFieldValue(ReflectorField p_setFieldValue_0_, Object p_setFieldValue_1_) {
        setFieldValue(null, p_setFieldValue_0_, p_setFieldValue_1_);
    }

    public static boolean setFieldValue(Object p_setFieldValue_0_, ReflectorField p_setFieldValue_1_, Object p_setFieldValue_2_) {
        try {
            Field field = p_setFieldValue_1_.getTargetField();

            if (field == null) {
                return false;
            } else {
                field.set(p_setFieldValue_0_, p_setFieldValue_2_);
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public static boolean postForgeBusEvent(ReflectorConstructor p_postForgeBusEvent_0_, Object... p_postForgeBusEvent_1_) {
        Object object = newInstance(p_postForgeBusEvent_0_, p_postForgeBusEvent_1_);
        return postForgeBusEvent(object);
    }

    public static boolean postForgeBusEvent(Object p_postForgeBusEvent_0_) {
        if (p_postForgeBusEvent_0_ == null) {
            return false;
        } else {
            Object object = getFieldValue(MinecraftForge_EVENT_BUS);

            if (object == null) {
                return false;
            } else {
                Object object1 = call(object, EventBus_post, p_postForgeBusEvent_0_);

                if (!(object1 instanceof Boolean)) {
                    return false;
                } else {
                    return (Boolean) object1;
                }
            }
        }
    }

    public static Object newInstance(ReflectorConstructor p_newInstance_0_, Object... p_newInstance_1_) {
        Constructor constructor = p_newInstance_0_.getTargetConstructor();

        if (constructor == null) {
            return null;
        } else {
            try {
                return constructor.newInstance(p_newInstance_1_);
            } catch (Throwable throwable) {
                handleException(throwable, p_newInstance_0_, p_newInstance_1_);
                return null;
            }
        }
    }

    public static boolean matchesTypes(Class[] p_matchesTypes_0_, Class[] p_matchesTypes_1_) {
        if (p_matchesTypes_0_.length != p_matchesTypes_1_.length) {
            return false;
        } else {
            for (int i = 0; i < p_matchesTypes_1_.length; ++i) {
                Class oclass = p_matchesTypes_0_[i];
                Class oclass1 = p_matchesTypes_1_[i];

                if (oclass != oclass1) {
                    return false;
                }
            }

            return true;
        }
    }

    private static void handleException(Throwable p_handleException_0_, Object p_handleException_1_, ReflectorMethod p_handleException_2_, Object[] p_handleException_3_) {
        if (p_handleException_0_ instanceof InvocationTargetException) {
            Throwable throwable = p_handleException_0_.getCause();

            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            } else {
                p_handleException_0_.printStackTrace();
            }
        } else {
            if (p_handleException_0_ instanceof IllegalArgumentException) {
                Config.warn("*** IllegalArgumentException ***");
                Config.warn("Method: " + p_handleException_2_.getTargetMethod());
                Config.warn("Object: " + p_handleException_1_);
                Config.warn("Parameter classes: " + Config.arrayToString(getClasses(p_handleException_3_)));
                Config.warn("Parameters: " + Config.arrayToString(p_handleException_3_));
            }

            Config.warn("*** Exception outside of method ***");
            Config.warn("Method deactivated: " + p_handleException_2_.getTargetMethod());
            p_handleException_2_.deactivate();
            p_handleException_0_.printStackTrace();
        }
    }

    private static void handleException(Throwable p_handleException_0_, ReflectorConstructor p_handleException_1_, Object[] p_handleException_2_) {
        if (!(p_handleException_0_ instanceof InvocationTargetException)) {
            if (p_handleException_0_ instanceof IllegalArgumentException) {
                Config.warn("*** IllegalArgumentException ***");
                Config.warn("Constructor: " + p_handleException_1_.getTargetConstructor());
                Config.warn("Parameter classes: " + Config.arrayToString(getClasses(p_handleException_2_)));
                Config.warn("Parameters: " + Config.arrayToString(p_handleException_2_));
            }

            Config.warn("*** Exception outside of constructor ***");
            Config.warn("Constructor deactivated: " + p_handleException_1_.getTargetConstructor());
            p_handleException_1_.deactivate();
        }
        p_handleException_0_.printStackTrace();
    }

    private static Object[] getClasses(Object[] p_getClasses_0_) {
        if (p_getClasses_0_ == null) {
            return new Class[0];
        } else {
            Class[] aclass = new Class[p_getClasses_0_.length];

            for (int i = 0; i < aclass.length; ++i) {
                Object object = p_getClasses_0_[i];

                if (object != null) {
                    aclass[i] = object.getClass();
                }
            }

            return aclass;
        }
    }

    private static boolean logEntry(String p_logEntry_0_) {
        Config.dbg(p_logEntry_0_);
        return true;
    }
}
