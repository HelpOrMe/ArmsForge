package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.WorldSpaceUI;
import io.netty.buffer.ByteBuf;

public abstract class UIPacket implements IMessage
{
    public int uniqueUIId;

    public UIPacket(int uniqueUIId)
    {
        this.uniqueUIId = uniqueUIId;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(uniqueUIId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        uniqueUIId = buf.readInt();
    }

    protected boolean validate()
    {
        if (!WorldSpaceUI.map.clientUIUpdatePool.containsKey(uniqueUIId))
        {
            WorldSpaceUI.logger.error("Unable to find requested ui " + uniqueUIId);
            return false;
        }
        return true;
    }
}
