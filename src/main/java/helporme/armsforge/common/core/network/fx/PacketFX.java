package helporme.armsforge.common.core.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.armsforge.api.utils.Vector3;
import io.netty.buffer.ByteBuf;

public abstract class PacketFX implements IMessage
{
    protected Vector3 position;
    protected Vector3 motion;

    public PacketFX() { }

    public PacketFX(Vector3 position, Vector3 motion)
    {
        this.position = position;
        this.motion = motion;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeFloat(position.x);
        buffer.writeFloat(position.y);
        buffer.writeFloat(position.z);
        buffer.writeFloat(motion.x);
        buffer.writeFloat(motion.y);
        buffer.writeFloat(motion.z);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        position = new Vector3(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
        motion = new Vector3(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }
}
