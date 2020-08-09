package helporme.armsforge.api.blocks.tiles;

import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.forge.wrapper.inventory.IInventoryExtend;
import net.minecraft.inventory.IInventory;

public interface ITable extends ITileEntityUpdatable, IInventory, IInventoryExtend
{
    Vector3Int getPosition();

    int getItemSlot();
}
