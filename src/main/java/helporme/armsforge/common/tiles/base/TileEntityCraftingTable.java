package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.blocks.tiles.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IItemRecipe;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.blocks.tiles.ICraftingTable;
import helporme.armsforge.api.blocks.tiles.ISupportTable;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.common.tiles.logic.CraftingLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TileEntityCraftingTable extends TileEntityTable implements ICraftingTable
{
    private static final int radius = 9;
    protected final CraftingLogic logic = new CraftingLogic(this);

    @Override
    public void onHammerBlow(ItemStack hammerStack, EntityPlayer player)
    {
        logic.onHammerBlow(hammerStack, player);
    }

    @Override
    public boolean isCraftActive()
    {
        return logic.craftActive;
    }

    @Override
    public void cancelCraft()
    {
        logic.cancelCraft();
    }

    @Override
    public void updateEntity()
    {
        logic.onUpdate();
    }

    @Override
    public ISupportTable[] getSupportTablesNear()
    {
        Set<ISupportTable> tables = new HashSet<>();
        for (int x = xCoord - radius; x < xCoord + radius; x++)
        {
            for (int z = zCoord - radius; z < zCoord + radius; z++)
            {
                TileEntity tile = worldObj.getTileEntity(x, yCoord,  z);
                if (tile instanceof ISupportTable)
                {
                    tables.add((ISupportTable)tile);
                    Vector3Int supportTablePosition = new Vector3Int(x, yCoord, z);
                    tables.addAll(getSupportShelvesOver(supportTablePosition));
                }
            }
        }
        return tables.toArray(new ISupportTable[0]);
    }

    protected List<ISupportTable> getSupportShelvesOver(Vector3Int position)
    {
        List<ISupportTable> tables = new ArrayList<>();
        for (int y = 1; y < 3; y ++)
        {
            TileEntity tile = worldObj.getTileEntity(position.x, position.y + y, position.z);
            if (!(tile instanceof ISupportTable))
            {
                break;
            }
            tables.add((ISupportTable)tile);
        }
        return tables;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        if (logic.craftActive)
        {
            logic.cancelCraft();
        }
        super.setInventorySlotContents(slot, itemStack);
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (logic.craftActive)
        {
            logic.cancelCraft();
        }
        return super.decrStackSize(slot, size);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (itemStack != null && (itemStack.getItem() instanceof IItemRecipe || slot == 1))
        {
            return isRecipeValid(itemStack) && slot == 1;
        }
        return true;
    }

    @Override
    public ICraftingTableRecipe getRecipe()
    {
        ItemStack itemStack = getStackInSlot(getRecipeSlot());
        IItemRecipe itemRecipe = (IItemRecipe)itemStack.getItem();
        return itemRecipe.getRecipe(itemStack);
    }

    @Override
    public int getRecipeSlot()
    {
        return 1;
    }

    public boolean isRecipeValid(ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IItemRecipe)
        {
            IItemRecipe recipeItem = (IItemRecipe)itemStack.getItem();
            ICraftingTableRecipe recipe = recipeItem.getRecipe(itemStack);
            CraftingTableType recipeCraftingTable = recipe.getCraftingTableType();
            return recipeCraftingTable.equals(getTableType());
        }
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return 2;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("CraftActive", logic.craftActive);
        if (logic.craftActive)
        {
            nbtTagCompound.setFloat("TimeLeft", logic.timeLeft);

            NBTTagCompound hammerNBT = new NBTTagCompound();
            logic.neededHammerType.writeToNBT(hammerNBT);
            nbtTagCompound.setTag("NeededHammerType", hammerNBT);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        logic.craftActive = nbtTagCompound.getBoolean("CraftActive");
        if (logic.craftActive)
        {
            logic.timeLeft = nbtTagCompound.getFloat("TimeLeft");

            NBTTagCompound hammerNBT = nbtTagCompound.getCompoundTag("NeededHammerType");
            logic.neededHammerType = HammerType.fromNBT(hammerNBT);
        }
    }
}
