package helporme.armsforge.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.Vector3;
import net.minecraft.client.particle.EntityFX;

import java.util.concurrent.Callable;

public interface IParticleFxEngine
{
    @SideOnly(Side.CLIENT)
    void spawnParticleOnClient(EntityFX entityFX);
}
