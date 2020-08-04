package helporme.armsforge.client.render.tiles;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.tiles.base.TileEntityCraftingTableRenderer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
import org.lwjgl.opengl.GL11;

public class TileEntityMasterAnvilRenderer extends TileEntityCraftingTableRenderer
{
    public TileEntityMasterAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void setItem3dTransformAt(TileEntityTableBase table, Vector3 position)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y + 0.858f, position.z + 0.5f);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(table);
    }
}
