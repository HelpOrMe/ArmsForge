package helporme.armsforge.forge.wrapper.inventory;

import net.minecraft.item.ItemStack;

public interface IInventoryExtend
{
    ItemStack popItemAt(int slot);

    boolean hasSpaceFor(ItemStack itemStack);

    boolean hasSpaceForItemAt(ItemStack itemStack, int slot);

    boolean isStackableAt(ItemStack itemStack, int slot);

    boolean isEmptyAt(int slot);
}
