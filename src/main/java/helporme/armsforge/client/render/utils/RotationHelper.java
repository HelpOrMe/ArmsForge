package helporme.armsforge.client.render.utils;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.util.Vec3;

public final class RotationHelper
{
    public static Vec3 v1 = Vec3.createVectorHelper(0d, 0d, 0d);
    public static Vec3 v2 = Vec3.createVectorHelper(0d, 0d, 0d);
    public static Vec3 v3 = Vec3.createVectorHelper(0d, 0d, 0d);
    public static Vec3 v4 = Vec3.createVectorHelper(0d, 0d, 0d);

    public static void updateByActiveRender()
    {
        float arX = ActiveRenderInfo.rotationX;
        float arZ = ActiveRenderInfo.rotationZ;
        float arYZ = ActiveRenderInfo.rotationYZ;
        float arXY = ActiveRenderInfo.rotationXY;
        float arXZ = ActiveRenderInfo.rotationXZ;

        v1 = Vec3.createVectorHelper(-arX + arYZ, arXZ, -arZ + arXY);
        v2 = Vec3.createVectorHelper(arX + arYZ, arXZ, arZ + arXY);
        v3 = Vec3.createVectorHelper(arX - arYZ, -arXZ, arZ - arXY);
        v4 = Vec3.createVectorHelper(-arX - arYZ, -arXZ, -arZ - arXY);
    }
}
