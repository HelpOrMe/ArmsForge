package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class UILocation implements IMessage
{
    public int chunkX;
    public int chunkZ;
    public int dimension;

    public UILocation() { }

    public UILocation(int chunkX, int chunkZ, int dimension)
    {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        dimension = buf.readInt();
    }

    public void copyValues(UILocation location)
    {
        chunkX = location.chunkX;
        chunkZ = location.chunkZ;
        dimension = location.dimension;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof UILocation)) return false;

        UILocation location = (UILocation)object;
        return chunkX == location.chunkX && chunkZ == location.chunkZ && dimension == location.dimension;
    }

    @Override
    public int hashCode()
    {
        return chunkX ^ chunkZ << 2 ^ dimension >> 2;
    }
}
