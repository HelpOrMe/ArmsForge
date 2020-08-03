package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.CraftingTableTypes;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;

public class TileEntityMasterAnvil extends TileEntityCraftingTableBase
{
    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.masterAnvil;
    }
}
