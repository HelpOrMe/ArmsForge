package helporme.armsforge.forge.wrapper.inventory;

import net.minecraft.item.ItemStack;

public interface IInventoryExtend
{
    ItemStack popItem(int slot);

    boolean hasSpaceForItem(ItemStack itemStack);

    boolean hasSpaceForItemAt(ItemStack itemStack, int slot);

    boolean isStackableAt(ItemStack itemStack, int slot);

    boolean isEmptyAt(int slot);
}
