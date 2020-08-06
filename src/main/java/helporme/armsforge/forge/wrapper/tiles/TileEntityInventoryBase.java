package helporme.armsforge.forge.wrapper.tiles;

import helporme.armsforge.forge.wrapper.inventory.IInventoryExtend;
import helporme.armsforge.forge.wrapper.utils.InventoryHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityInventoryBase extends TileEntityAdvancedBase implements IInventory, IInventoryExtend
{
    protected ItemStack[] items = new ItemStack[getSizeInventory()];

    @Override
    public void onRemove()
    {
        for (ItemStack itemStack : items)
        {
            if (itemStack != null)
            {
                EntityItem entityItem = new EntityItem(worldObj, xCoord, yCoord + 1d, zCoord, itemStack);
                worldObj.spawnEntityInWorld(entityItem);
            }
        }
    }

    @Override
    public boolean hasSpaceFor(ItemStack itemStack)
    {
        return InventoryHelper.hasSpaceFor(itemStack, this, getSizeInventory());
    }

    @Override
    public boolean hasSpaceForItemAt(ItemStack itemStack, int slot)
    {
        return InventoryHelper.hasSpaceForItemAt(itemStack, this, slot);
    }

    @Override
    public boolean isStackableAt(ItemStack itemStack, int slot)
    {
        return InventoryHelper.isStackableAt(itemStack, this, slot);
    }

    @Override
    public boolean isEmptyAt(int slot)
    {
        return InventoryHelper.isEmptyAt(this, slot);
    }

    @Override
    public ItemStack popItemAt(int slot)
    {
        return decrStackSize(slot, getInventoryStackLimit());
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack == null)
        {
            throw new IllegalArgumentException(
                    "Trying to decrease stack size, but stack is null." +
                    " Slot " + slot + ", size " + size + ". ");
        }

        if (itemStack.stackSize <= size)
        {
            items[slot] = null;
        }
        else
        {
            itemStack = itemStack.splitStack(size);
        }

        markDirtyAndUpdate();
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return getStackInSlot(slot);
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        if (slot >= items.length)
        {
            throw new ArrayIndexOutOfBoundsException(
                    "Trying to process stack in slot " + slot + ", but inventory size is " + getSizeInventory());
        }
        return items[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        if (slot >= items.length)
        {
            throw new ArrayIndexOutOfBoundsException(
                    "Trying to process stack in slot " + slot + ", but inventory size is " + getSizeInventory());
        }

        if (!isItemValidForSlot(slot, itemStack))
        {
            throw new IllegalArgumentException(
                    "Trying to set ItemStack " + itemStack.toString() +
                    " in invalid slot " + slot);
        }

        items[slot] = itemStack;
        markDirtyAndUpdate();
    }

    @Override
    public int getSizeInventory()
    {
        return 27;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return false;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public String getInventoryName()
    {
        return toString();
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagList nbtTagList = new NBTTagList();
        for (int slot = 0; slot < items.length; slot++)
        {
            ItemStack itemStack = items[slot];
            if (itemStack != null)
            {
                NBTTagCompound itemNBT = new NBTTagCompound();
                itemNBT.setInteger("Slot", slot);
                itemStack.writeToNBT(itemNBT);
                nbtTagList.appendTag(itemNBT);
            }
        }
        nbtTagCompound.setTag("Items", nbtTagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        items = new ItemStack[getSizeInventory()];
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
        for (int i = 0; i < nbtTagList.tagCount(); i++)
        {
            NBTTagCompound itemNBT = nbtTagList.getCompoundTagAt(i);
            int slot = itemNBT.getInteger("Slot");
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(itemNBT);
            items[slot] = itemStack;
        }
    }
}
