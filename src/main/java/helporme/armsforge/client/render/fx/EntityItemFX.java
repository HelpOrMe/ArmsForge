package helporme.armsforge.client.render.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.utils.Vector3;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityItemFX extends EntityFX
{
    public EntityItemFX(World world, ItemStack itemStack, Vector3 position, Vector3 motion)
    {
        super(world, position.x, position.y, position.z, motion.x, motion.y, motion.z);

        Item item = itemStack.getItem();
        setParticleIcon(item.getIconFromDamage(itemStack.getItemDamage()));

        this.particleGravity = Blocks.snow.blockParticleGravity;
        this.particleScale /= 2.0F;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float tick, float lrX, float udY, float lrZ, float udX, float udZ)
    {
        float u1 = this.particleIcon.getInterpolatedU(this.particleTextureJitterX / 4 * 16);
        float u2 = this.particleIcon.getInterpolatedU((this.particleTextureJitterX + 1) / 4 * 16);
        float v1 = this.particleIcon.getInterpolatedV(this.particleTextureJitterY / 4 * 16);
        float v2 = this.particleIcon.getInterpolatedV((this.particleTextureJitterY + 1) / 4 * 16);

        double x = prevPosX + (posX - prevPosX) * tick - interpPosX;
        double y = prevPosY + (posY - prevPosY) * tick - interpPosY;
        double z = prevPosZ + (posZ - prevPosZ) * tick - interpPosZ;
        double scale = 0.1f * particleScale;

        tessellator.setBrightness(getBrightnessForRender(tick));
        tessellator.setColorOpaque_F(this.particleRed, this.particleGreen, this.particleBlue);
        tessellator.addVertexWithUV(x - lrX * scale - udX * scale, y - udY * scale, z - lrZ * scale - udZ * scale, u1, v2);
        tessellator.addVertexWithUV(x - lrX * scale + udX * scale, y + udY * scale, z - lrZ * scale + udZ * scale, u1, v1);
        tessellator.addVertexWithUV(x + lrX * scale + udX * scale, y + udY * scale, z + lrZ * scale + udZ * scale, u2, v1);
        tessellator.addVertexWithUV(x + lrX * scale - udX * scale, y - udY * scale, z + lrZ * scale - udZ * scale, u2, v2);
    }

    @Override
    public int getFXLayer()
    {
        return 2;
    }
}
