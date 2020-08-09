package helporme.armsforge.forge.wrapper.render.blocks;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.models.ModelInfo;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityFacedRenderer extends TileEntityRenderer
{
    public TileEntityFacedRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void setTileEntityOffsetAt(TileEntity tile, Vector3 position, float timeDelta)
    {
        super.setTileEntityOffsetAt(tile, position, timeDelta);
        setFaceRotationFrom(tile);
    }

    protected void setFaceRotationFrom(TileEntity tile)
    {
        GL11.glRotatef(tile.getBlockMetadata() * -90f, 0, 1, 0);
    }
}
