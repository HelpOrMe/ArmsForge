package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class LeftHandSwapModel extends ModelRendererSwapWrapper
{
    protected ModelRenderer originArm;

    public LeftHandSwapModel(ModelBiped modelBiped)
    {
        super(modelBiped, 40, 16);
        originArm = modelBiped.bipedLeftArm;
        cubeList = originArm.cubeList;
        setRotationPoint(originArm.rotationPointX, originArm.rotationPointY, originArm.rotationPointZ);
    }

    @Override
    public void render(float f)
    {
        if (useSwappedRenderer)
        {
            rotateAngleX = -0.5f;
            rotateAngleZ = 0.4f;
            rotateAngleY = 0.4f;
            offsetX = -0.05f;
            super.render(f);
        }
        else
        {
            originArm.render(f);
        }
    }
}
