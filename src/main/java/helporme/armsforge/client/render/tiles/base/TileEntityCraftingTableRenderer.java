package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCraftingTableRenderer extends TileEntityTableRenderer
{
    public TileEntityCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void renderItemStack(TileEntityTable table, int slot, Vector3 position)
    {
        if (slot == 0)
        {
            super.renderItemStack(table, slot, position);
        }
    }

    @Override
    protected void renderModel(TileEntity tile, float timeDelta)
    {
        model.renderAllExcept("Recipe");
        tryRenderRecipe((TileEntityCraftingTable)tile);
    }

    protected void tryRenderRecipe(TileEntityCraftingTable craftingTable)
    {
        boolean hasRecipe = !craftingTable.isEmptyInSlot(1);
        if (hasRecipe)
        {
            model.renderPart("Recipe");
        }
    }
}
