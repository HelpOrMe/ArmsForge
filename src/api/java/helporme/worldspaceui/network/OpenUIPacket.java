package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Constructor;

public class OpenUIPacket implements IMessage, IMessageHandler<OpenUIPacket, IMessage>
{
    public UI ui;

    public OpenUIPacket(UI ui)
    {
        this.ui = ui;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(WorldSpaceUI.map.uiClassToUIid.get(ui.getClass().getName()));
        buf.writeInt(ui.location.chunkX);
        buf.writeInt(ui.location.chunkZ);
        buf.writeInt(ui.location.dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try
        {
            Class<?> UIClass = Class.forName(WorldSpaceUI.map.uiIdToUIClassName.get(buf.readInt()));
            Constructor<?> UIConstructor = UIClass.getConstructor(UILocation.class);
            UILocation location = new UILocation(buf.readInt(), buf.readInt(), buf.readInt());
            ui = (UI)UIConstructor.newInstance(new Object[] { location });
        }
        catch (ReflectiveOperationException e)
        {
            WorldSpaceUI.logger.error("Unable to build an UI from the OpenUIPacket", e);
        }
    }

    @Override
    public IMessage onMessage(OpenUIPacket openUIPacket, MessageContext ctx)
    {
        if (openUIPacket.ui != null)
        {
            WorldSpaceUI.openUI(openUIPacket.ui);
        }
        return null;
    }
}
