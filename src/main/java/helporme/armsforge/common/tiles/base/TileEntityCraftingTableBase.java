package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityCraftingTableBase extends TileEntityTableBase implements ICraftingTable
{
    @Override
    public List<ISupportTable> getSupportTablesNear(int radius)
    {
        List<ISupportTable> tables = new ArrayList<ISupportTable>();
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
        List<ISupportTable> tables = new ArrayList<ISupportTable>();
        position.y++;
        while (true)
        {
            TileEntity tile = worldObj.getTileEntity(position.x, position.y, position.z);
            if (!(tile instanceof ISupportTable))
            {
                break;
            }
            tables.add((ISupportTable)tile);
            position.y++;
        }
        return tables;
    }
}
