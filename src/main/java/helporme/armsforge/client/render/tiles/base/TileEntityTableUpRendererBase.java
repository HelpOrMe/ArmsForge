package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.base.TileEntityTableRendererBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import org.lwjgl.opengl.GL11;

public class TileEntityTableUpRendererBase extends TileEntityTableRendererBase
{
    public TileEntityTableUpRendererBase(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void setItem3dTransformAt(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.925d, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotation();
    }

    @Override
    protected void setItem2dTransformAt(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.75d, z + 0.5d);
        setFaceRotation();
        GL11.glRotatef(90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, -0.2f, -0.2f);
    }
}
