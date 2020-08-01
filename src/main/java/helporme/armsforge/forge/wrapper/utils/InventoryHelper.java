package helporme.armsforge.forge.wrapper.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public final class InventoryHelper
{
    public static boolean hasSpaceForItem(ItemStack itemStack, IInventory inventory, int slotLimit)
    {
        slotLimit %= (inventory.getSizeInventory() + 1);
        for (int slot = 0; slot < slotLimit; slot++)
        {
            if (hasSpaceForItemAt(itemStack, inventory, slot))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpaceForItemAt(ItemStack itemStack, IInventory inventory, int slot)
    {
        return isEmptyAt(inventory, slot) || isStackableAt(itemStack, inventory, slot);
    }

    public static boolean isStackableAt(ItemStack itemStack, IInventory inventory, int slot)
    {
        ItemStack itemStackAtSlot = inventory.getStackInSlot(slot);
        if (itemStackAtSlot.isItemEqual(itemStack))
        {
            int futureSize = itemStackAtSlot.stackSize + itemStack.stackSize;
            return itemStackAtSlot.isStackable() &&
                    futureSize <= itemStackAtSlot.getMaxStackSize() &&
                    futureSize <= inventory.getInventoryStackLimit();
        }
        return false;
    }

    public static boolean isEmptyAt(IInventory inventory, int slot)
    {
        return inventory.getStackInSlot(slot) == null;
    }
}
