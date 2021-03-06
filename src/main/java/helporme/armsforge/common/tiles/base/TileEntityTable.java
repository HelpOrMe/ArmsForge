package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.blocks.tiles.table.ITable;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.forge.wrapper.tiles.TileEntityInventory;

public abstract class TileEntityTable extends TileEntityInventory implements ITable
{
    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public Vector3Int getPosition()
    {
        return new Vector3Int(xCoord, yCoord, zCoord);
    }

    @Override
    public int getItemSlot()
    {
        return 0;
    }
}
