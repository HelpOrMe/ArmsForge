package helporme.armsforge.forge.wrapper.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityAdvancedBase extends TileEntityBase
{
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }

    public void markDirtyAndUpdate()
    {
        markDirty();
        worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }
}
