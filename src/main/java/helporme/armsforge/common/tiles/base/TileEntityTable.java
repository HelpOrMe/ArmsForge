package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.blocks.tables.ITable;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.forge.wrapper.tiles.TileEntityInventoryBase;
import net.minecraft.item.ItemStack;

public abstract class TileEntityTable extends TileEntityInventoryBase implements ITable
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
    public boolean hasItem()
    {
        return !isEmptyAt(0);
    }

    @Override
    public ItemStack getItem()
    {
        return getStackInSlot(0);
    }

    @Override
    public void setItem(ItemStack itemStack)
    {
        setInventorySlotContents(0, itemStack);
    }

    public void decrStackSize(int count)
    {
        decrStackSize(0, count);
    }
}
