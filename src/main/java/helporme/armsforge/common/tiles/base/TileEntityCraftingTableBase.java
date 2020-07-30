package helporme.armsforge.common.tiles.base;

public class TileEntityCraftingTableBase extends TileEntityInventoryBase
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
