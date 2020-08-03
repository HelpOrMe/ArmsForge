package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.recipes.HammerBlowPattern;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.tileentity.TileEntity;

import java.util.*;

public abstract class TileEntityCraftingTableBase extends TileEntityTableBase implements ICraftingTable
{
    public boolean recipeActive = false;
    public ICraftingTableRecipe currentRecipe;
    public Iterator<HammerBlowPattern> generator;

    public HammerType hammerTypeForNextBlow;
    public float timeLeftToBlow;

    @Override
    public int getSizeInventory()
    {
        return 2;
    }


    @Override
    public void updateEntity()
    {
        if (recipeActive)
        {
            worldObj.getTotalWorldTime();
        }
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
