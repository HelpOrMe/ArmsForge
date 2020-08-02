package helporme.armsforge.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;

public interface IParticleFxEngine
{
    @SideOnly(Side.CLIENT)
    void spawnParticleOnClient(EntityFX entityFX);
}
