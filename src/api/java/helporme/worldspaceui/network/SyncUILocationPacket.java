package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import io.netty.buffer.ByteBuf;

public class SyncUILocationPacket extends UIPacket implements IMessageHandler<SyncUILocationPacket, IMessage>
{
    public UILocation location;

    public SyncUILocationPacket(int uniqueUIId, UILocation location)
    {
        super(uniqueUIId);
        this.location = location;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        location.toBytes(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        location = new UILocation();
        location.fromBytes(buf);
    }

    @Override
    public IMessage onMessage(SyncUILocationPacket message, MessageContext ctx)
    {
        if (validate())
        {
            UI ui = WorldSpaceUI.map.clientUIUpdatePool.get(message.uniqueUIId);
            ui.location.copyValues(message.location);
        }
        return null;
    }
}
