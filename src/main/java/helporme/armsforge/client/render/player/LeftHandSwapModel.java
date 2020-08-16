package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class LeftHandSwapModel extends ModelRendererSwapWrapper
{
    protected ModelRenderer originArm;

    public LeftHandSwapModel(ModelBiped modelBiped)
    {
        super(modelBiped, modelBiped.bipedLeftArm, 40, 16);
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
            originalModel.render(f);
        }
    }
}
