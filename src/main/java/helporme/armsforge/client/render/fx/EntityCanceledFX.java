package helporme.armsforge.client.render.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntityCanceledFX extends EntityFXBase
{
    public EntityCanceledFX(World world, Vector3 position, Vector3 motion)
    {
        super(world, position, motion);

        texture = ResourceManager.get("textures/fx/CanceledFX.png");

        motionX *= 0.1;
        motionY *= 0.1;
        motionZ *= 0.1;

        particleScale *= 0.75F;
        particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        particleMaxAge = (int)((float)this.particleMaxAge * motion.x);

        noClip = false;
    }
}
