package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.common.blocks.models.ModelInfo;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityTableUpRenderer extends TileEntityTableRenderer
{
    public TileEntityTableUpRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void setItem3dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.925d, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(tile);
    }

    @Override
    protected void setItem2dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.99d, z + 0.5d);
        setFaceRotationFrom(tile);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
