package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.types.Vector3d;
import helporme.worldspaceui.types.Vector3f;
import io.netty.buffer.ByteBuf;

public class Transform implements IMessage
{
    public Vector3d position = new Vector3d();
    public Vector3f scale = new Vector3f();
    public Vector3f rotation = new Vector3f();

    public Transform() {  }

    public Transform(Vector3d position, Vector3f scale, Vector3f rotation)
    {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);

        buf.writeFloat(scale.x);
        buf.writeFloat(scale.y);
        buf.writeFloat(scale.z);

        buf.writeFloat(rotation.x);
        buf.writeFloat(rotation.y);
        buf.writeFloat(rotation.z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        position.x = buf.readDouble();
        position.y = buf.readDouble();
        position.z = buf.readDouble();

        scale.x = buf.readFloat();
        scale.y = buf.readFloat();
        scale.z = buf.readFloat();

        rotation.x = buf.readFloat();
        rotation.y = buf.readFloat();
        rotation.z = buf.readFloat();
    }

    public void copyValues(Transform transform)
    {
        position = transform.position.copy();
        scale = transform.scale.copy();
        rotation = transform.rotation.copy();
    }
}
