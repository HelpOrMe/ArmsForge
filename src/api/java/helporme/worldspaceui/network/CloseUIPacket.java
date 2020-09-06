package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import io.netty.buffer.ByteBuf;

public class CloseUIPacket implements IMessage, IMessageHandler<CloseUIPacket, IMessage>
{
    public int uniqueUIId;

    public CloseUIPacket(Integer uniqueUIId)
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

    @Override
    public IMessage onMessage(CloseUIPacket closeUIPacket, MessageContext ctx)
    {
        WorldSpaceUI.closeUI(closeUIPacket.uniqueUIId);
        return null;
    }
}
