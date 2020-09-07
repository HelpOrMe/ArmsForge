package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.worldspaceui.WorldSpaceUI;

public class CloseUIPacket extends UIPacket implements IMessageHandler<CloseUIPacket, IMessage>
{
    public CloseUIPacket(int uniqueUIId)
    {
        super(uniqueUIId);
    }

    @Override
    public IMessage onMessage(CloseUIPacket message, MessageContext ctx)
    {
        if (validate())
        {
            WorldSpaceUI.closeUI(message.uniqueUIId);
        }
        return null;
    }
}
