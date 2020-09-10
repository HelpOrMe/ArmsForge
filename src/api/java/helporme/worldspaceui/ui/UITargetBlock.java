package helporme.worldspaceui.ui;

import helporme.worldspaceui.commands.ICommandSupportedObj;
import helporme.worldspaceui.types.Vector3i;
import io.netty.buffer.ByteBuf;
import net.minecraft.command.ICommandSender;

public class UITargetBlock extends UITarget implements ICommandSupportedObj
{
    public Vector3i position;
    public int dimension;

    public UITargetBlock(Vector3i position, int dimension)
    {
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        position = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        dimension = buf.readInt();
    }

    @Override
    public void copyValues(UITarget target)
    {
        UITargetBlock uiTargetBlock = (UITargetBlock)target;
        position = uiTargetBlock.position;
        dimension = uiTargetBlock.dimension;
    }

    @Override
    public Class<?>[] getRequiredArgumentTypes()
    {
        return new Class<?>[] { Vector3i.class };
    }

    @Override
    public void initFromCommand(ICommandSender sender, Object[] args)
    {
        position = (Vector3i)args[0];
        dimension = sender.getEntityWorld().provider.dimensionId;
    }
}
