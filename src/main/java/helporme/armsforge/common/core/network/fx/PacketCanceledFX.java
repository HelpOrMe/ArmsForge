package helporme.armsforge.common.core.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.fx.EntityCanceledFX;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import java.util.Random;

public class PacketCanceledFX extends PacketFX implements IMessageHandler<PacketCanceledFX, IMessage>
{
    public PacketCanceledFX() { }

    public PacketCanceledFX(Vector3 position, Vector3 motion)
    {
        super(position, motion);
    }

    @Override
    public IMessage onMessage(PacketCanceledFX packetFX, MessageContext ctx)
    {
        drawParticles(packetFX.position, packetFX.motion);
        return null;
    }

    protected void drawParticles(Vector3 position, Vector3 motion)
    {
        World world = Minecraft.getMinecraft().theWorld;
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            Vector3 offset = Vector3.getNextRandomOffset(random, 0.3f);
            EntityCanceledFX fx = new EntityCanceledFX(world, position.add(offset), motion);
            ArmsForge.fxEngine.spawnParticle(fx);
        }
    }
}
