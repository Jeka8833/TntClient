package optifine;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.util.EnumFacing;

public class ModelUtils
{
    public static IBakedModel duplicateModel(IBakedModel p_duplicateModel_0_)
    {
        List list = duplicateQuadList(p_duplicateModel_0_.getGeneralQuads());
        EnumFacing[] aenumfacing = EnumFacing.VALUES;
        List list1 = new ArrayList();

        for (int i = 0; i < aenumfacing.length; ++i)
        {
            EnumFacing enumfacing = aenumfacing[i];
            List list2 = p_duplicateModel_0_.getFaceQuads(enumfacing);
            List list3 = duplicateQuadList(list2);
            list1.add(list3);
        }

        return new SimpleBakedModel(list, list1, p_duplicateModel_0_.isAmbientOcclusion(), p_duplicateModel_0_.isGui3d(), p_duplicateModel_0_.getParticleTexture(), p_duplicateModel_0_.getItemCameraTransforms());
    }

    public static List duplicateQuadList(List p_duplicateQuadList_0_)
    {
        List list = new ArrayList();

        for (Object bakedquad : p_duplicateQuadList_0_)
        {
            BakedQuad bakedquad1 = duplicateQuad((BakedQuad) bakedquad);
            list.add(bakedquad1);
        }

        return list;
    }

    public static BakedQuad duplicateQuad(BakedQuad p_duplicateQuad_0_)
    {
        return new BakedQuad(p_duplicateQuad_0_.getVertexData().clone(), p_duplicateQuad_0_.getTintIndex(), p_duplicateQuad_0_.getFace(), p_duplicateQuad_0_.getSprite());
    }
}
