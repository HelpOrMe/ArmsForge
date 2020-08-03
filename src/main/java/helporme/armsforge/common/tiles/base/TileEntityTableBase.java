package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.api.blocks.tables.ITable;
import helporme.armsforge.forge.wrapper.tiles.TileEntityInventoryBase;
import net.minecraft.item.ItemStack;

public class TileEntityTableBase extends TileEntityInventoryBase implements ITable
{
    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getItemOnTable()
    {
        return getStackInSlot(0);
    }

    @Override
    public void removeItemFromTable()
    {
        setInventorySlotContents(0, null);
    }

    @Override
    public Vector3 getPosition()
    {
        return new Vector3(xCoord, yCoord, zCoord);
    }
}
