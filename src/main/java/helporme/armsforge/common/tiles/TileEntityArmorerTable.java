package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tiles.CraftingTableType;
import helporme.armsforge.api.blocks.tiles.CraftingTableTypes;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;

public class TileEntityArmorerTable extends TileEntityCraftingTable
{
    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.armorerTable;
    }
}
