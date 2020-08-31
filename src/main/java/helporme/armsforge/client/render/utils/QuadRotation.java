package helporme.armsforge.client.render.utils;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.common.core.Version;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class QuadRotation
{
    protected Tessellator tessellator;
    public Vec3 vLeftUp = Vec3.createVectorHelper(0d, 0d, 0d);
    public Vec3 vRightUp = Vec3.createVectorHelper(0d, 0d, 0d);
    public Vec3 vRightDown = Vec3.createVectorHelper(0d, 0d, 0d);
    public Vec3 vLeftDown = Vec3.createVectorHelper(0d, 0d, 0d);

    public QuadRotation()
    {
        this(Tessellator.instance);
    }

    public QuadRotation(Tessellator tessellator)
    {
        this.tessellator = tessellator;
    }

    public void updateByActiveRender()
    {
        float arX = ActiveRenderInfo.rotationX;
        float arZ = ActiveRenderInfo.rotationZ;
        float arYZ = ActiveRenderInfo.rotationYZ;
        float arXY = ActiveRenderInfo.rotationXY;
        float arXZ = ActiveRenderInfo.rotationXZ;

        vLeftUp = Vec3.createVectorHelper(-arX + arYZ, arXZ, -arZ + arXY);
        vRightUp = Vec3.createVectorHelper(arX + arYZ, arXZ, arZ + arXY);
        vRightDown = Vec3.createVectorHelper(arX - arYZ, -arXZ, arZ - arXY);
        vLeftDown = Vec3.createVectorHelper(-arX - arYZ, -arXZ, -arZ - arXY);
    }

    public void renderIconQuad(Vector3 position, double scale, double vStart, double vEnd)
    {
        double x = position.x;
        double y = position.y;
        double z = position.z;

        Vec3 vLU = vLeftUp;
        Vec3 vRU = vRightUp;
        Vec3 vRD = vRightDown;
        Vec3 vLD = vLeftDown;

        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921f);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + vLU.xCoord * scale, y + vLU.yCoord * scale, z + vLU.zCoord * scale, 0, vStart);
        tessellator.addVertexWithUV(x + vRU.xCoord * scale, y + vRU.yCoord * scale, z + vRU.zCoord * scale, 1, vStart);
        tessellator.addVertexWithUV(x + vRD.xCoord * scale, y + vRD.yCoord * scale, z + vRD.zCoord * scale, 1, vEnd);
        tessellator.addVertexWithUV(x + vLD.xCoord * scale, y + vLD.yCoord * scale, z + vLD.zCoord * scale, 0, vEnd);
        tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
    }
}
