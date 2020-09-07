package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UITarget;
import io.netty.buffer.ByteBuf;

public class SyncUITargetPacket extends UIPacket implements IMessageHandler<SyncUITargetPacket, IMessage>
{
    public UITarget target;

    public SyncUITargetPacket(int uniqueUIId, UITarget target)
    {
        super(uniqueUIId);
        this.target = target;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        target.toBytes(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        target = new UITarget();
        target.fromBytes(buf);
    }

    @Override
    public IMessage onMessage(SyncUITargetPacket message, MessageContext ctx)
    {
        if (validate())
        {
            UI ui = WorldSpaceUI.map.clientUIUpdatePool.get(message.uniqueUIId);
            ui.target.copyValues(message.target);
        }
        return null;
    }
}
