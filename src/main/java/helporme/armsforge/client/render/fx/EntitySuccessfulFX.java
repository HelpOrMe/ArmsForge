package helporme.armsforge.client.render.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class EntitySuccessfulFX extends EntityFXBase
{
    protected int xSign;
    protected int zSign;

    public EntitySuccessfulFX(World world, Vector3 position, Vector3 motion)
    {
        super(world, position, motion);

        texture = ResourceManager.get("textures/fx/SuccessfulFX.png");

        particleGravity = Blocks.snow.blockParticleGravity;
        particleScale /= 2.0F;

        Random random = new Random();
        xSign = random.nextInt(2) == 0 ? -1 : 1;
        zSign = random.nextInt(2) == 0 ? -1 : 1;

        motionX *= xSign;
        motionZ *= zSign;
    }

    @Override
    public int getFXLayer()
    {
        return 3;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.motionY -= 0.04D * (double)this.particleGravity;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
