package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tables.ISupportTable;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySupportTable extends TileEntityTable implements ISupportTable
{
    public boolean isShelf = false;

    public void updateShelfStatus()
    {
        TileEntity tileUnderThisTable = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tileUnderThisTable != null)
        {
            isShelf = tileUnderThisTable instanceof TileEntitySupportTable;
            markDirtyAndUpdate();
        }
    }

    public void rotateIfHaveTableUnder()
    {
        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tile != null)
        {
            int metadata = tile.getBlockMetadata();
            if (metadata != getBlockMetadata())
            {
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("IsShelf", isShelf);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        isShelf = nbtTagCompound.getBoolean("IsShelf");
    }

}
