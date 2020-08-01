package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.Vector3;
import helporme.armsforge.api.blocks.ITable;
import helporme.armsforge.forge.wrapper.tiles.TileEntityInventoryBase;

public class TileEntityTableBase extends TileEntityInventoryBase implements ITable
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

    @Override
    public Vector3 getPosition()
    {
        return new Vector3(xCoord, yCoord, zCoord);
    }
}
