package helporme.armsforge.common.core.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.fx.EntityItemFX;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class PacketItemFX extends PacketFX implements IMessageHandler<PacketItemFX, IMessage>
{
    protected String convertedItem;

    public PacketItemFX() { }

    public PacketItemFX(Vector3 position, Vector3 motion, String convertedItem)
    {
        super(position, motion);
        this.convertedItem = convertedItem;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        super.toBytes(buffer);
        buffer.writeInt(convertedItem.length());
        for (int i = 0; i < convertedItem.length(); i++)
        {
            buffer.writeChar(convertedItem.charAt(i));
        }

    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        super.fromBytes(buffer);

        int length = buffer.readInt();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            stringBuilder.append(buffer.readChar());
        }
        convertedItem = stringBuilder.toString();
    }

    @Override
    public IMessage onMessage(PacketItemFX packet, MessageContext ctx)
    {
        ItemStack item = ItemStackHelper.convertStringToItem(packet.convertedItem);
        drawParticles(item, packet.position, packet.motion);
        return null;
    }

    protected void drawParticles(ItemStack itemStack, Vector3 position, Vector3 motion)
    {
        World world = Minecraft.getMinecraft().theWorld;
        Random random = new Random();
        for (int i = 0; i < 15; i++)
        {
            Vector3 offset = Vector3.getNextRandomOffset(random, 0.15f);
            EntityItemFX fx = new EntityItemFX(world, itemStack, position.add(offset), motion);
            ArmsForge.fxEngine.spawnParticle(fx);
        }
    }
}
