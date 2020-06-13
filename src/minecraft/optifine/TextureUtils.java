package optifine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import shadersmod.client.MultiTexID;
import shadersmod.client.Shaders;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Iterator;

public class TextureUtils
{
    public static TextureAtlasSprite iconGrassTop;
    public static TextureAtlasSprite iconGrassSide;
    public static TextureAtlasSprite iconGrassSideOverlay;
    public static TextureAtlasSprite iconSnow;
    public static TextureAtlasSprite iconGrassSideSnowed;
    public static TextureAtlasSprite iconMyceliumSide;
    public static TextureAtlasSprite iconMyceliumTop;
    public static TextureAtlasSprite iconWaterStill;
    public static TextureAtlasSprite iconWaterFlow;
    public static TextureAtlasSprite iconLavaStill;
    public static TextureAtlasSprite iconLavaFlow;
    public static TextureAtlasSprite iconPortal;
    public static TextureAtlasSprite iconFireLayer0;
    public static TextureAtlasSprite iconFireLayer1;
    public static TextureAtlasSprite iconGlass;
    public static TextureAtlasSprite iconGlassPaneTop;
    public static TextureAtlasSprite iconCompass;
    public static TextureAtlasSprite iconClock;

    public static void update()
    {
        TextureMap texturemap = getTextureMapBlocks();

        if (texturemap != null)
        {
            String s = "minecraft:blocks/";
            iconGrassTop = texturemap.getSpriteSafe(s + "grass_top");
            iconGrassSide = texturemap.getSpriteSafe(s + "grass_side");
            iconGrassSideOverlay = texturemap.getSpriteSafe(s + "grass_side_overlay");
            iconSnow = texturemap.getSpriteSafe(s + "snow");
            iconGrassSideSnowed = texturemap.getSpriteSafe(s + "grass_side_snowed");
            iconMyceliumSide = texturemap.getSpriteSafe(s + "mycelium_side");
            iconMyceliumTop = texturemap.getSpriteSafe(s + "mycelium_top");
            iconWaterStill = texturemap.getSpriteSafe(s + "water_still");
            iconWaterFlow = texturemap.getSpriteSafe(s + "water_flow");
            iconLavaStill = texturemap.getSpriteSafe(s + "lava_still");
            iconLavaFlow = texturemap.getSpriteSafe(s + "lava_flow");
            iconFireLayer0 = texturemap.getSpriteSafe(s + "fire_layer_0");
            iconFireLayer1 = texturemap.getSpriteSafe(s + "fire_layer_1");
            iconPortal = texturemap.getSpriteSafe(s + "portal");
            iconGlass = texturemap.getSpriteSafe(s + "glass");
            iconGlassPaneTop = texturemap.getSpriteSafe(s + "glass_pane_top");
            String s1 = "minecraft:items/";
            iconCompass = texturemap.getSpriteSafe(s1 + "compass");
            iconClock = texturemap.getSpriteSafe(s1 + "clock");
        }
    }

    public static int ceilPowerOfTwo(int p_ceilPowerOfTwo_0_)
    {
        int i;

        for (i = 1; i < p_ceilPowerOfTwo_0_; i *= 2)
        {

        }

        return i;
    }

    public static ITextureObject getTexture(ResourceLocation p_getTexture_0_)
    {
        ITextureObject itextureobject = Config.getTextureManager().getTexture(p_getTexture_0_);

        if (itextureobject != null)
        {
            return itextureobject;
        }
        else if (!Config.hasResource(p_getTexture_0_))
        {
            return null;
        }
        else
        {
            SimpleTexture simpletexture = new SimpleTexture(p_getTexture_0_);
            Config.getTextureManager().loadTexture(p_getTexture_0_, simpletexture);
            return simpletexture;
        }
    }

    public static void resourcesReloaded()
    {
        if (getTextureMapBlocks() != null)
        {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            TextureAnimations.reset();
            update();
            NaturalTextures.update();
            BetterGrass.update();
            BetterSnow.update();
            TextureAnimations.update();
            CustomColors.update();
            CustomSky.update();
            RandomMobs.resetTextures();
            CustomItems.updateModels();
            Shaders.resourcesReloaded();
            Lang.resourcesReloaded();
            Config.updateTexturePackClouds();
            SmartLeaves.updateLeavesModels();
            Config.getTextureManager().tick();
        }
    }

    public static TextureMap getTextureMapBlocks()
    {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }

    public static void registerResourceListener()
    {
        IResourceManager iresourcemanager = Config.getResourceManager();

        if (iresourcemanager instanceof IReloadableResourceManager)
        {
            IReloadableResourceManager ireloadableresourcemanager = (IReloadableResourceManager)iresourcemanager;
            IResourceManagerReloadListener iresourcemanagerreloadlistener = resourceManager -> TextureUtils.resourcesReloaded();
            ireloadableresourcemanager.registerReloadListener(iresourcemanagerreloadlistener);
        }

        ITickableTextureObject itickabletextureobject = new ITickableTextureObject()
        {
            public void tick()
            {
                TextureAnimations.updateCustomAnimations();
            }
            public void loadTexture(IResourceManager resourceManager) {
            }
            public int getGlTextureId()
            {
                return 0;
            }
            public void setBlurMipmap(boolean p_174936_1_, boolean p_174936_2_)
            {
            }
            public void restoreLastBlurMipmap()
            {
            }
            public MultiTexID getMultiTexID()
            {
                return null;
            }
        };
        ResourceLocation resourcelocation = new ResourceLocation("optifine/TickableTextures");
        Config.getTextureManager().loadTickableTexture(resourcelocation, itickabletextureobject);
    }

    public static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_)
    {
        String s = "assets/minecraft/";

        if (p_fixResourcePath_0_.startsWith(s))
        {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(s.length());
            return p_fixResourcePath_0_;
        }
        else if (p_fixResourcePath_0_.startsWith("./"))
        {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);

            if (!p_fixResourcePath_1_.endsWith("/"))
            {
                p_fixResourcePath_1_ = p_fixResourcePath_1_ + "/";
            }

            p_fixResourcePath_0_ = p_fixResourcePath_1_ + p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        }
        else
        {
            if (p_fixResourcePath_0_.startsWith("/~"))
            {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(1);
            }

            String s1 = "mcpatcher/";

            if (p_fixResourcePath_0_.startsWith("~/"))
            {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_;
                return p_fixResourcePath_0_;
            }
            else if (p_fixResourcePath_0_.startsWith("/"))
            {
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_.substring(1);
                return p_fixResourcePath_0_;
            }
            else
            {
                return p_fixResourcePath_0_;
            }
        }
    }

    public static String getBasePath(String p_getBasePath_0_)
    {
        int i = p_getBasePath_0_.lastIndexOf(47);
        return i < 0 ? "" : p_getBasePath_0_.substring(0, i);
    }

    public static void applyAnisotropicLevel()
    {
        if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic)
        {
            float f = GL11.glGetFloat(34047);
            float f1 = (float)Config.getAnisotropicFilterLevel();
            f1 = Math.min(f1, f);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, 34046, f1);
        }
    }

    public static void bindTexture(int p_bindTexture_0_)
    {
        GlStateManager.bindTexture(p_bindTexture_0_);
    }

    public static boolean isPowerOfTwo(int p_isPowerOfTwo_0_)
    {
        int i = MathHelper.roundUpToPowerOfTwo(p_isPowerOfTwo_0_);
        return i == p_isPowerOfTwo_0_;
    }

    public static BufferedImage scaleImage(BufferedImage p_scaleImage_0_, int p_scaleImage_1_)
    {
        int i = p_scaleImage_0_.getWidth();
        int j = p_scaleImage_0_.getHeight();
        int k = j * p_scaleImage_1_ / i;
        BufferedImage bufferedimage = new BufferedImage(p_scaleImage_1_, k, 2);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        Object object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;

        if (p_scaleImage_1_ < i || p_scaleImage_1_ % i != 0)
        {
            object = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        }

        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
        graphics2d.drawImage(p_scaleImage_0_, 0, 0, p_scaleImage_1_, k, null);
        return bufferedimage;
    }

    public static BufferedImage scaleToPowerOfTwo(BufferedImage p_scaleToPowerOfTwo_0_, int p_scaleToPowerOfTwo_1_)
    {
        if (p_scaleToPowerOfTwo_0_ == null)
        {
            return p_scaleToPowerOfTwo_0_;
        }
        else
        {
            int i = p_scaleToPowerOfTwo_0_.getWidth();
            int j = p_scaleToPowerOfTwo_0_.getHeight();
            int k = Math.max(i, p_scaleToPowerOfTwo_1_);
            k = MathHelper.roundUpToPowerOfTwo(k);

            if (k == i)
            {
                return p_scaleToPowerOfTwo_0_;
            }
            else
            {
                int l = j * k / i;
                BufferedImage bufferedimage = new BufferedImage(k, l, 2);
                Graphics2D graphics2d = bufferedimage.createGraphics();
                Object object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;

                if (k % i != 0)
                {
                    object = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
                }

                graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
                graphics2d.drawImage(p_scaleToPowerOfTwo_0_, 0, 0, k, l, null);
                return bufferedimage;
            }
        }
    }

    public static Dimension getImageSize(InputStream p_getImageSize_0_, String p_getImageSize_1_)
    {
        Iterator iterator = ImageIO.getImageReadersBySuffix(p_getImageSize_1_);

        while (true)
        {
            if (iterator.hasNext())
            {
                ImageReader imagereader = (ImageReader)iterator.next();
                Dimension dimension;

                try
                {
                    ImageInputStream imageinputstream = ImageIO.createImageInputStream(p_getImageSize_0_);
                    imagereader.setInput(imageinputstream);
                    int i = imagereader.getWidth(imagereader.getMinIndex());
                    int j = imagereader.getHeight(imagereader.getMinIndex());
                    dimension = new Dimension(i, j);
                }
                catch (IOException var11)
                {
                    continue;
                }
                finally
                {
                    imagereader.dispose();
                }

                return dimension;
            }

            return null;
        }
    }

    public static int getGLMaximumTextureSize()
    {
        for (int i = 65536; i > 0; i >>= 1)
        {
            GL11.glTexImage2D(GL11.GL_PROXY_TEXTURE_2D, 0, GL11.GL_RGBA, i, i, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            GL11.glGetError();
            int k = GL11.glGetTexLevelParameteri(GL11.GL_PROXY_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);

            if (k != 0)
            {
                return i;
            }
        }

        return -1;
    }
}
