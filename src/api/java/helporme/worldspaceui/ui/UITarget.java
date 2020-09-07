package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class UITarget implements IMessage
{
    public UITarget() { }

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public void copyValues(UITarget target) { }
}
