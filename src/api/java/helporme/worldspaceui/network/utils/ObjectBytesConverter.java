package helporme.worldspaceui.network.utils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.WorldSpaceUIServer;
import io.netty.buffer.ByteBuf;

public class ObjectBytesConverter
{
    public static void toBytes(ByteBuf buf, Object obj)
    {
        Class<?> cls = obj.getClass();
        if (cls.isPrimitive())
        {
            writeBytesPrimitive(buf, obj);
        }
        else if (cls.isAssignableFrom(IMessage.class))
        {
            IMessage msgObject = (IMessage)obj;
            msgObject.toBytes(buf);
        }
        else
        {
            WorldSpaceUIServer.logger.error("Unable convert to bytes " + obj.getClass().getName());
        }
    }

    public static void writeBytesPrimitive(ByteBuf buf, Object obj)
    {
        Class<?> cls = obj.getClass();
        if (Boolean.class == cls) buf.writeBoolean((boolean)obj);
        if (Byte.class == cls) buf.writeByte((byte)obj);
        if (Short.class == cls) buf.writeShort((short)obj);
        if (Integer.class == cls) buf.writeInt((int)obj);
        if (Long.class == cls) buf.writeLong((long)obj);
        if (Float.class == cls) buf.writeFloat((float)obj);
        if (Double.class == cls) buf.writeDouble((double)obj);
        if (Character.class == cls) buf.writeChar((char)obj);
        if (String.class == cls) writeString(buf, (String) obj);
    }

    public static void writeString(ByteBuf buf, String str)
    {
        buf.writeInt(str.length());
        for (char chr : str.toCharArray())
        {
            buf.writeChar(chr);
        }
    }

    public static Object fromBytes(ByteBuf buf, Class<?> objCls)
    {
        if (objCls.isPrimitive())
        {
            return fromBytesPrimitive(buf, objCls);
        }

        if (objCls.isAssignableFrom(IMessage.class))
        {
            try
            {
                IMessage msgObject = (IMessage)objCls.newInstance();
                msgObject.fromBytes(buf);
                return msgObject;
            }
            catch (ReflectiveOperationException e)
            {
                throw new IllegalArgumentException("Unable to create object from " + objCls.getName());
            }
        }
        else
        {
            WorldSpaceUIServer.logger.error("Unable read from bytes " + objCls.getName());
        }
        return null;
    }

    public static Object fromBytesPrimitive(ByteBuf buf, Class<?> cls)
    {
        Object obj = null;
        if (Boolean.class == cls) obj = buf.readBoolean();
        if (Byte.class == cls) obj = buf.readByte();
        if (Short.class == cls) obj = buf.readShort();
        if (Integer.class == cls) obj = buf.readInt();
        if (Long.class == cls) obj = buf.readLong();
        if (Float.class == cls) obj = buf.readFloat();
        if (Double.class == cls) obj = buf.readDouble();
        if (Character.class == cls) obj = buf.readChar();
        if (String.class == cls) obj = readString(buf);
        return obj;
    }

    public static String readString(ByteBuf buf)
    {
        short strLength = buf.readShort();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strLength; i++)
        {
            stringBuilder.append(buf.readChar());
        }
        return stringBuilder.toString();
    }
}
