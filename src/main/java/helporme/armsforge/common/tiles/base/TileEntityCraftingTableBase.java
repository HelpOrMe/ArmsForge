package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.items.IRecipeItem;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.*;

public abstract class TileEntityCraftingTableBase extends TileEntityTableBase implements ICraftingTable
{
    @Override
    public int getSizeInventory()
    {
        return 2;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IRecipeItem || slot == 1)
        {
            return itemStack.getItem() instanceof IRecipeItem && slot == 1;
        }
        return true;
    }

    @Override
    public Set<ISupportTable> getSupportTablesNear(int radius)
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
                    tables.addAll(getSupportShelvesFrom(new Vector3Int(x, yCoord, z)));
                }
            }
        }
        return tables;
    }

    protected List<ISupportTable> getSupportShelvesFrom(Vector3Int position)
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
    public void onHammerBlow(IHammer hammer)
    {

    }
}
