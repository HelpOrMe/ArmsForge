package helporme.armsforge.client.render;

import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
import helporme.armsforge.forge.wrapper.render.blocks.TileEntityFacedRendererBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityTableRenderer extends TileEntityFacedRendererBase
{
    public TileEntityTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
        renderItemStackFromTile(tile, x, y, z);
    }

    protected void renderItemStackFromTile(TileEntity tile, double x, double y, double z)
    {
        if (tile instanceof TileEntityTableBase)
        {
            TileEntityTableBase masterAnvil = (TileEntityTableBase)tile;
            ItemStack itemOnCraftingPlace = masterAnvil.getStackInSlot(0);
            if (itemOnCraftingPlace != null)
            {
                renderItemStack(masterAnvil, itemOnCraftingPlace, x, y, z);
            }
        }
    }

    protected void renderItemStack(TileEntity tile, ItemStack itemStack, double x, double y, double z)
    {
        EntityItem entityItem = new EntityItem(tile.getWorldObj(), 0d, 0d, 0d, itemStack);
        GL11.glPushMatrix();
        entityItem.hoverStart = 0.0F;
        setItemTransformAt(tile, itemStack, x, y, z);
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
    }

    protected void setItemTransformAt(TileEntity tile, ItemStack itemStack, double x, double y, double z)
    {
        if (itemStack.getItem() instanceof ItemBlock)
        {
            setItem3dTransformAt(tile, x, y, z);
        }
        else
        {
            setItem2dTransformAt(tile, x, y, z);
        }
    }

    protected void setItem3dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.875d, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(tile);
    }

    protected void setItem2dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.89d, z + 0.5d);
        setFaceRotationFrom(tile);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
