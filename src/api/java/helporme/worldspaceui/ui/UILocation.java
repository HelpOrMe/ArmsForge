package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.types.Vector3d;
import io.netty.buffer.ByteBuf;

public class UILocation implements IMessage
{
    public Vector3d position;
    public int dimension;

    public UILocation() { }

    public UILocation(Vector3d position, int dimension)
    {
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        position = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        dimension = buf.readInt();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof UILocation)) return false;

        UILocation location = (UILocation)object;
        return position == location.position && dimension == location.dimension;
    }

    @Override
    public int hashCode()
    {
        return position.hashCode() ^ dimension << 2;
    }
}
