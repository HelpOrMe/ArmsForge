package helporme.armsforge.common.tiles.base;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityInventoryBase extends TileEntityAdvancedBase implements IInventory
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
    public ItemStack decrStackSize(int slot, int size)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack == null)
        {
            throw new IllegalArgumentException(
                    "Trying to decrease stack size, but stack is null." +
                    " Slot " + slot + ", size" + size + ". " + getTileInfo());
        }

        if (itemStack.stackSize <= size)
        {
            items[slot] = null;
        }
        else
        {
            itemStack = itemStack.splitStack(size);
        }

        worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        markDirty();
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
            throw new IllegalArgumentException(
                    "Trying to get stack in slot " + slot + ", but inventory size is " + getSizeInventory() +
                            ". " + getTileInfo());
        }
        return items[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        if (slot >= items.length)
        {
            throw new IllegalArgumentException(
                    "Trying to set stack in slot " + slot + ", but inventory size is " + getSizeInventory() +
                            ". " + getTileInfo());
        }

        items[slot] = itemStack;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    protected String getTileInfo()
    {
        return "Tile " + toString() + ", at " + xCoord + " " + yCoord + " " + zCoord;
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
        //TODO: Automation boolean in config
        return true;
    }
}
