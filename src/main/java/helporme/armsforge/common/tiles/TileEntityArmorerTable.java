package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.blocks.tiles.table.CraftingTableTypes;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;

public class TileEntityArmorerTable extends TileEntityCraftingTable
{
    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.armorerTable;
    }
}
