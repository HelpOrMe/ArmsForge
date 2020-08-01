package helporme.armsforge.api.blocks;

import helporme.armsforge.api.Vector3;
import helporme.armsforge.forge.wrapper.inventory.IInventoryExtend;

public interface ITable extends IInventoryExtend
{
    Vector3 getPosition();
}
