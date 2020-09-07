package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.Transform;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import helporme.worldspaceui.ui.UITarget;
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
        buf.writeInt(ui.uniqueId);
        ui.location.toBytes(buf);
        ui.target.toBytes(buf);
        ui.transform.toBytes(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        String uiClassName = WorldSpaceUI.map.uiIdToUIClassName.get(buf.readInt());
        int uniqueId = buf.readInt();

        UILocation location = new UILocation();
        location.fromBytes(buf);
        UITarget target = new UITarget();
        target.fromBytes(buf);
        Transform transform = new Transform();
        transform.fromBytes(buf);

        try
        {
            Class<?> UIClass = Class.forName(uiClassName);
            Constructor<?> UIConstructor = UIClass.getConstructor(
                    Integer.class, UILocation.class, UITarget.class, Transform.class);
            ui = (UI)UIConstructor.newInstance(uniqueId, location, target, transform);
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
