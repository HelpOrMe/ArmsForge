package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.base.TileEntityFacedRendererBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityMasterAnvil;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityCrafingTableRendererBase extends TileEntityFacedRendererBase
{
    public TileEntityCrafingTableRendererBase(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
        if (tile instanceof TileEntityMasterAnvil)
        {
            TileEntityMasterAnvil masterAnvil = (TileEntityMasterAnvil)tile;
            ItemStack itemOnCraftingPlace = masterAnvil.getStackInSlot(0);
            if (itemOnCraftingPlace != null)
            {
                renderItemStackAt(masterAnvil, itemOnCraftingPlace, x, y, z);
            }
        }
    }

    protected void renderItemStackAt(TileEntity tile, ItemStack itemStack, double x, double y, double z)
    {
        EntityItem entityItem = new EntityItem(tile.getWorldObj(), 0d, 0d, 0d, itemStack);
        GL11.glPushMatrix();
        entityItem.hoverStart = 0.0F;
        setItemTransformAt(itemStack, x, y, z);
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
    }

    protected void setItemTransformAt(ItemStack itemStack, double x, double y, double z)
    {
        if (itemStack.getItem() instanceof ItemBlock)
        {
            setItem3dTransformAt(x, y, z);
        }
        else
        {
            setItem2dTransformAt(x, y, z);
        }
    }

    protected void setItem3dTransformAt(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.875d, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotation();
    }

    protected void setItem2dTransformAt(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.69d, z + 0.5d);
        setFaceRotation();
        GL11.glRotatef(90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, -0.2f, -0.2f);
    }
}
