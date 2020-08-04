package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCraftingTableRenderer extends TileEntityTableRenderer
{
    public TileEntityCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void renderItemStack(TileEntityTableBase table, int slot, Vector3 position)
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
        tryRenderRecipe((TileEntityCraftingTableBase)tile);
    }

    protected void tryRenderRecipe(TileEntityCraftingTableBase craftingTable)
    {
        boolean hasRecipe = !craftingTable.isEmptyAt(1);
        if (hasRecipe)
        {
            model.renderPart("Recipe");
        }
    }
}
