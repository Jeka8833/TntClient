package optifine;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class ReflectorForge
{
    public static void putLaunchBlackboard(String p_putLaunchBlackboard_0_, Object p_putLaunchBlackboard_1_)
    {
        Map map = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);

        if (map != null)
        {
            map.put(p_putLaunchBlackboard_0_, p_putLaunchBlackboard_1_);
        }
    }

    public static boolean renderFirstPersonHand(RenderGlobal p_renderFirstPersonHand_0_, float p_renderFirstPersonHand_1_, int p_renderFirstPersonHand_2_)
    {
        return Reflector.ForgeHooksClient_renderFirstPersonHand.exists() && Reflector.callBoolean(Reflector.ForgeHooksClient_renderFirstPersonHand, p_renderFirstPersonHand_0_, p_renderFirstPersonHand_1_, p_renderFirstPersonHand_2_);
    }

    public static InputStream getOptiFineResourceStream(String p_getOptiFineResourceStream_0_)
    {
        if (!Reflector.OptiFineClassTransformer_instance.exists())
        {
            return null;
        }
        else
        {
            Object object = Reflector.getFieldValue(Reflector.OptiFineClassTransformer_instance);

            if (object == null)
            {
                return null;
            }
            else
            {
                if (p_getOptiFineResourceStream_0_.startsWith("/"))
                {
                    p_getOptiFineResourceStream_0_ = p_getOptiFineResourceStream_0_.substring(1);
                }

                byte[] abyte = (byte[]) Reflector.call(object, Reflector.OptiFineClassTransformer_getOptiFineResource, new Object[] {p_getOptiFineResourceStream_0_});

                if (abyte == null)
                {
                    return null;
                }
                else
                {
                    return new ByteArrayInputStream(abyte);
                }
            }
        }
    }

    public static boolean blockHasTileEntity(IBlockState p_blockHasTileEntity_0_)
    {
        Block block = p_blockHasTileEntity_0_.getBlock();
        return !Reflector.ForgeBlock_hasTileEntity.exists() ? block.hasTileEntity() : Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, p_blockHasTileEntity_0_);
    }

    public static boolean isItemDamaged(ItemStack p_isItemDamaged_0_)
    {
        return !Reflector.ForgeItem_showDurabilityBar.exists() ? p_isItemDamaged_0_.isItemDamaged() : Reflector.callBoolean(p_isItemDamaged_0_.getItem(), Reflector.ForgeItem_showDurabilityBar, p_isItemDamaged_0_);
    }
}
