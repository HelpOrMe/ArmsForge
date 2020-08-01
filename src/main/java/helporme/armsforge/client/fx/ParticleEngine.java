package helporme.armsforge.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;

public class ParticleEngine implements IParticleFxEngine
{
    @SideOnly(Side.CLIENT)
    public void spawnParticleOnClient(EntityFX entityFX)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(entityFX);
    }
}
