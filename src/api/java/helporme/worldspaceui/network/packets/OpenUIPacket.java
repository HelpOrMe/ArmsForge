package helporme.worldspaceui.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Constructor;

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
            String uiClassName = WorldSpaceUI.map.uiIdToUIClassName.get(buf.readInt());
            Class<?> UIClass = Class.forName(uiClassName);
            Constructor<?> UIConstructor = UIClass.getConstructor(Integer.class);
            ui = (UI)UIConstructor.newInstance(buf.readInt());
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
