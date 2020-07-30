package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.common.blocks.models.ModelInfo;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityFacedRendererBase extends TileEntityRendererBase
{
    protected int rotateIndex = 0;

    public TileEntityFacedRendererBase(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        rotateIndex = tile.getBlockMetadata();
        bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotation();
        model.renderAll();
        GL11.glPopMatrix();
    }

    protected void setFaceRotation()
    {
        GL11.glRotatef(rotateIndex * -45f, 0, 1, 0);
    }
}
