package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import helporme.armsforge.forge.wrapper.render.blocks.TileEntityFacedRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityTableRenderer extends TileEntityFacedRenderer
{
    public TileEntityTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
        renderItemStacksFromTable((TileEntityTable)tile, new Vector3(x, y, z));
    }

    protected void renderItemStacksFromTable(TileEntityTable table, Vector3 position)
    {
        for (int slot = 0; slot < table.getSizeInventory(); slot++)
        {
            if (!table.isEmptyInSlot(slot))
            {
                renderItemStack(table, slot, position);
            }
        }
    }

    protected void renderItemStack(TileEntityTable table, int slot, Vector3 position)
    {
        ItemStack itemStack = table.getStackInSlot(slot);
        EntityItem entityItem = new EntityItem(table.getWorldObj(), 0d, 0d, 0d, itemStack);
        GL11.glPushMatrix();
        entityItem.hoverStart = 0.0F;
        setItemTransformAt(table, itemStack, position);
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
    }

    protected void setItemTransformAt(TileEntityTable table, ItemStack itemStack, Vector3 position)
    {
        if (itemStack.getItem() instanceof ItemBlock)
        {
            setItem3dTransformAt(table, position);
        }
        else
        {
            setItem2dTransformAt(table, position);
        }
    }

    protected void setItem3dTransformAt(TileEntityTable table, Vector3 position)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y + 0.878f, position.z + 0.5f);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(table);
    }

    protected void setItem2dTransformAt(TileEntityTable table, Vector3 position)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y + 0.9f, position.z + 0.5f);
        setFaceRotationFrom(table);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
