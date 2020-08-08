package helporme.armsforge.forge.wrapper.inventory;

import net.minecraft.item.ItemStack;

public interface IInventoryExtend
{
    ItemStack popFirstItem();

    ItemStack popItemFromSlot(int slot);

    void fillSlotWithItem(int slot, ItemStack itemStack);

    ItemStack getFirstItem();

    boolean hasItems();

    boolean hasSpaceForItem(ItemStack itemStack);

    boolean hasSpaceForItemInSlot(ItemStack itemStack, int slot);

    boolean isStackableInSlot(ItemStack itemStack, int slot);

    boolean isEmptyInSlot(int slot);
}
