package helporme.armsforge.common.tiles.base;

import helporme.armsforge.forge.wrapper.tiles.TileEntityInventoryBase;

public class TileEntityTableBase extends TileEntityInventoryBase
{
    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }
}
