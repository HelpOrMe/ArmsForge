package helporme.armsforge.forge.wrapper.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public final class InventoryHelper
{
    public static boolean hasSpaceForItem(IInventory inventory, int slotLimit, ItemStack itemStack)
    {
        slotLimit %= (inventory.getSizeInventory() + 1);
        for (int slot = 0; slot < slotLimit; slot++)
        {
            if (isSlotHasSpaceForItem(itemStack, inventory, slot))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isSlotHasSpaceForItem(ItemStack itemStack, IInventory inventory, int slot)
    {
        return isSlotEmpty(inventory, slot) || isSlotStackable(itemStack, inventory, slot);
    }

    public static boolean isSlotStackable(ItemStack itemStack, IInventory inventory, int slot)
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

    public static boolean isSlotEmpty(IInventory inventory, int slot)
    {
        return inventory.getStackInSlot(slot) == null;
    }

    public static void fillSlotWithItem(IInventory inventory, int slot, ItemStack itemStack)
    {
        ItemStack stackAtSlot = inventory.getStackInSlot(slot);
        if (stackAtSlot.isItemEqual(itemStack))
        {
            int spaceLeft = (inventory.getInventoryStackLimit() - stackAtSlot.stackSize) % stackAtSlot.getMaxStackSize();
            int sizeToDecrease = Math.min(spaceLeft, itemStack.stackSize);
            itemStack.stackSize -= sizeToDecrease;
            stackAtSlot.stackSize += sizeToDecrease;
        }
    }

    public static boolean hasItems(IInventory inventory)
    {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
        {
            if (!isSlotEmpty(inventory, slot))
            {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getFirstItem(IInventory inventory)
    {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
        {
            if (!isSlotEmpty(inventory, slot))
            {
                return inventory.getStackInSlot(slot);
            }
        }
        return null;
    }

    public static ItemStack popFirstItem(IInventory inventory)
    {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
        {
            if (!isSlotEmpty(inventory, slot))
            {
                ItemStack firstItem = inventory.getStackInSlot(slot);
                inventory.setInventorySlotContents(slot, null);
                return firstItem;
            }
        }
        return null;
    }
}
