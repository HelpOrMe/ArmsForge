package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class RightHandSwapModel extends ModelRendererSwapWrapper
{
    public RightHandSwapModel(ModelBiped modelBiped)
    {
        super(modelBiped, modelBiped.bipedRightArm, 40, 16);
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
            originalModel.render(f);
        }
    }
}
