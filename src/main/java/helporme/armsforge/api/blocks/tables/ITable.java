package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.utils.Vector3Int;
import net.minecraft.item.ItemStack;

public interface ITable
{
    Vector3Int getPosition();

    boolean hasItem();

    ItemStack getItem();

    void setItem(ItemStack itemStack);

    void decrStackSize(int size);
}
