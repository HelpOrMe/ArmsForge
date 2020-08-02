package helporme.armsforge.common.core.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class DebugHelper
{
    @SideOnly(Side.CLIENT)
    public static void spawnDebugParticleVertLines(World world, List<Vector3> positions)
    {
        for (Vector3 targetPosition : positions)
        {
            Color color = new Color(24, 24, 24);
            spawnDebugParticleVertLine(world, targetPosition, color);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void spawnDebugParticleVertLine(World world, Vector3 position, Color color)
    {
        spawnParticleTrace(world, position, position.add(new Vector3(0, 0.3f, 0)), color);
    }

    @SideOnly(Side.CLIENT)
    public static void spawnDebugParticleTraces(World world, Vector3 start, List<Vector3> targets)
    {
        Random random = new Random(0);
        for (Vector3 targetPosition : targets)
        {
            Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            spawnParticleTrace(world, start, targetPosition, color);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void spawnParticleTrace(World world, Vector3 from, Vector3 to, Color color)
    {
        float distance = Vector3.distance(from, to) * 10;
        for (int i = 0; i < distance; i ++)
        {
            float delta = i / distance;
            Vector3 position = Vector3.lerp(from, to, delta);
            spawnDebugParticleAt(world, position, color);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void spawnDebugParticleAt(World world, Vector3 position, Color color)
    {
        EntityFX particle = new EntityReddustFX(
                world, position.x, position.y, position.z,
                1, 2, 1);
        particle.setRBGColorF(color.r, color.g, color.b);
        ArmsForge.fxEngine.spawnParticleOnClient(particle);
    }
}
