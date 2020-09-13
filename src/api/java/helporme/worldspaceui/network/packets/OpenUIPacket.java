package helporme.worldspaceui.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import io.netty.buffer.ByteBuf;

public class OpenUIPacket implements IMessage, IMessageHandler<OpenUIPacket, IMessage>
{
    public UI ui;

    public OpenUIPacket() { }

    public OpenUIPacket(UI ui)
    {
        this.ui = ui;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(WorldSpaceUI.map.uiClassToUIid.get(ui.getClass().getName()));
        buf.writeInt(ui.uniqueId);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try
        {
            String clsName = WorldSpaceUI.map.uiIdToUIClassName.get(buf.readInt());
            Class<?> cls = Class.forName(clsName);
            ui = (UI)cls.newInstance();
            ui.uniqueId = buf.readInt();

        }
        catch (ReflectiveOperationException e)
        {
            WorldSpaceUI.logger.error("Unable to build an UI from the OpenUIPacket", e);
        }
    }

    @Override
    public IMessage onMessage(OpenUIPacket message, MessageContext ctx)
    {
        if (message.ui != null)
        {
            WorldSpaceUI.openUI(message.ui);
        }
        return null;
    }
}
