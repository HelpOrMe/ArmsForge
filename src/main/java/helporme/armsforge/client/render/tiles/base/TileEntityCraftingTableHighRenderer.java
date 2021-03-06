package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import org.lwjgl.opengl.GL11;

public class TileEntityCraftingTableHighRenderer extends TileEntityCraftingTableRenderer
{
    public TileEntityCraftingTableHighRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void setItem3dTransformAt(TileEntityTable table, Vector3 position)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y + 0.935f, position.z + 0.5f);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(table);
    }

    @Override
    protected void setItem2dTransformAt(TileEntityTable table, Vector3 position)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y + 0.96f, position.z + 0.5f);
        setFaceRotationFrom(table);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
