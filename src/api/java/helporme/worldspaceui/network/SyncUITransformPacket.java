package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.Transform;
import helporme.worldspaceui.ui.UI;
import io.netty.buffer.ByteBuf;

public class SyncUITransformPacket extends UIPacket implements IMessageHandler<SyncUITransformPacket, IMessage>
{
    public Transform transform;

    public SyncUITransformPacket(int uniqueUIId, Transform transform)
    {
        super(uniqueUIId);
        this.transform = transform;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        transform.toBytes(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        transform = new Transform();
        transform.fromBytes(buf);
    }

    @Override
    public IMessage onMessage(SyncUITransformPacket message, MessageContext ctx)
    {
        if (validate())
        {
            UI ui = WorldSpaceUI.map.clientUIUpdatePool.get(message.uniqueUIId);
            ui.transform.copyValues(message.transform);
        }
        return null;
    }
}
