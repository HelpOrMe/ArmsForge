package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.blocks.ICraftingTable;
import helporme.armsforge.api.blocks.ISupportTable;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCraftingTableBase extends TileEntityTableBase implements ICraftingTable
{
    @Override
    public List<ISupportTable> getSupportTablesNear(int radius)
    {
        List<ISupportTable> tables = new ArrayList<ISupportTable>();
        for (int x = -radius; x < radius; x++)
        {
            for (int z = -radius; z < radius; z++)
            {
                TileEntity tile = worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z);
                if (tile instanceof ISupportTable)
                {
                    tables.add((ISupportTable)tile);
                }
            }
        }
        return tables;
    }
}
