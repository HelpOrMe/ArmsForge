package helporme.armsforge.common.core.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.fx.EntitySuccessfulFX;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import java.util.Random;

public class PacketSuccessfulFX extends PacketFX implements IMessageHandler<PacketSuccessfulFX, IMessage>
{
    public PacketSuccessfulFX() { }

    public PacketSuccessfulFX(Vector3 position, Vector3 motion)
    {
        super(position, motion);
    }

    @Override
    public IMessage onMessage(PacketSuccessfulFX packetFX, MessageContext ctx)
    {
        spawnParticles(packetFX.position, packetFX.motion);
        return null;
    }

    protected void spawnParticles(Vector3 position, Vector3 motion)
    {
        World world = Minecraft.getMinecraft().theWorld;
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            Vector3 offset = Vector3.getNextRandomOffset(random, 0.3f);
            EntitySuccessfulFX fx = new EntitySuccessfulFX(world, position.add(offset), motion);
            ArmsForge.fxEngine.spawnParticle(fx);
        }
    }
}
