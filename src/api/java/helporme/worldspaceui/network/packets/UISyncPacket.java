package helporme.worldspaceui.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.network.ObjectBytesConverter;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.synced.UISynced;
import helporme.worldspaceui.ui.synced.UISyncedCache;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Field;

public class UISyncPacket extends UIPacket implements IMessageHandler<UISyncPacket, IMessage>
{
    public Object[] objects;
    public Field[] fields;

    public UISyncPacket(UISynced ui)
    {
        super(ui.uniqueId);
        objects = UISyncedCache.getSyncObjects(ui).toArray();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeByte(objects.length);
        ObjectBytesConverter.toBytes(buf, objects);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);

        UI ui = WorldSpaceUI.map.uiUpdatePool.get(uniqueUIId);
        fields = UISyncedCache.getSyncFields(ui).toArray(new Field[0]);

        objects = new Object[buf.readByte()];
        for (int i = 0; i < objects.length; i++)
        {
            objects[i] = ObjectBytesConverter.fromBytes(buf, fields[i].getDeclaringClass());
        }
    }

    @Override
    public IMessage onMessage(UISyncPacket syncPacket, MessageContext ctx)
    {
        UI ui = WorldSpaceUI.map.uiUpdatePool.get(syncPacket.uniqueUIId);
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                fields[i].set(ui, objects[i]);
            }
            catch (IllegalAccessException e)
            {
                WorldSpaceUI.logger.error("Unable to sync field value " +
                        ui.getClass().getName() + "#" + fields[i].getName());
            }
        }
        return null;
    }
}
