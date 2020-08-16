package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class RightHandSwapModel extends ModelRendererSwapWrapper
{
    protected ModelRenderer originArm;

    public RightHandSwapModel(ModelBiped modelBiped)
    {
        super(modelBiped, 40, 16);
        originArm = modelBiped.bipedRightArm;
        cubeList = originArm.cubeList;
        setRotationPoint(originArm.rotationPointX, originArm.rotationPointY, originArm.rotationPointZ);
    }

    @Override
    public void render(float f)
    {
        if (useSwappedRenderer)
        {
            rotateAngleX = -1f;
            rotateAngleZ = -0.75f;
            rotateAngleY = -0.15f;
            super.render(f);
        }
        else
        {
            originArm.render(f);
        }
    }
}
