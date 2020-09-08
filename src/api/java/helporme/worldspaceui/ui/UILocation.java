package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.types.Vector3i;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.chunk.Chunk;

public class UILocation implements IMessage
{
    public Vector3i chunkPosition;
    public int dimension;

    public UILocation() { }

    public UILocation(Chunk chunk)
    {
        this(new Vector3i(chunk.xPosition, 0, chunk.zPosition), chunk.worldObj.provider.dimensionId);
    }

    public UILocation(Vector3i chunkPosition, int dimension)
    {
        this.chunkPosition = chunkPosition;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(chunkPosition.x);
        buf.writeInt(chunkPosition.z);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        chunkPosition = new Vector3i(buf.readInt(), 0, buf.readInt());
        dimension = buf.readInt();
    }

    public void copyValues(UILocation location)
    {
        chunkPosition = location.chunkPosition;
        dimension = location.dimension;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof UILocation)) return false;

        UILocation location = (UILocation)object;
        return chunkPosition == location.chunkPosition && dimension == location.dimension;
    }

    @Override
    public int hashCode()
    {
        return chunkPosition.x ^ chunkPosition.z << 2 ^ dimension >> 2;
    }
}
