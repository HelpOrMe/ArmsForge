package helporme.armsforge.client.render.fx.engine;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.forge.wrapper.utils.Color;
import helporme.armsforge.common.core.network.fx.PacketFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public interface IParticleFxEngine
{
    @SideOnly(Side.CLIENT)
    void spawnDebugParticleVertLines(World world, List<Vector3> positions);

    @SideOnly(Side.CLIENT)
    void spawnDebugParticleVertLine(World world, Vector3 position, Color color);

    @SideOnly(Side.CLIENT)
    void spawnDebugParticleTraces(World world, Vector3 start, List<Vector3> targets);

    @SideOnly(Side.CLIENT)
    void spawnParticleTrace(World world, Vector3 from, Vector3 to, Color color);

    @SideOnly(Side.CLIENT)
    void spawnDebugParticleAt(World world, Vector3 position, Color color);

    @SideOnly(Side.CLIENT)
    void spawnParticle(EntityFX entityFX);

    void spawnParticle(PacketFX packet, TileEntity source);

    void spawnParticle(PacketFX packet, int dimension, Vector3 position);

    void spawnParticle(PacketFX packet, NetworkRegistry.TargetPoint source);
}
