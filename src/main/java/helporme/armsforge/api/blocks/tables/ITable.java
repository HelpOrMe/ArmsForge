package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.utils.Vector3;
import net.minecraft.item.ItemStack;

public interface ITable
{
    Vector3 getPosition();

    ItemStack getItemOnTable();

    void removeItemFromTable();
}
