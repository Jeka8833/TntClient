package shadersmod.client;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class SVertexFormatElement extends VertexFormatElement
{
    final int sUsage;

    public SVertexFormatElement(int sUsage, VertexFormatElement.EnumType type, int count)
    {
        super(0, type, VertexFormatElement.EnumUsage.PADDING, count);
        this.sUsage = sUsage;
    }
}
