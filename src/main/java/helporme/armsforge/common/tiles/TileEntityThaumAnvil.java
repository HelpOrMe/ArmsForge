package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.CraftingTableTypes;
import helporme.armsforge.client.render.tiles.info.ThaumAnvilRenderInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;

public class TileEntityThaumAnvil extends TileEntityCraftingTable
{
    public final ThaumAnvilRenderInfo renderInfo = new ThaumAnvilRenderInfo();

    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.thaumAnvil;
    }
}
