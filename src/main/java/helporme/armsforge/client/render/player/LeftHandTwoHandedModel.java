package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;

public class LeftHandTwoHandedModel extends SwappedModelRenderer
{
    public LeftHandTwoHandedModel(ModelBiped modelBiped)
    {
        super(modelBiped, modelBiped.bipedLeftArm);
    }

    @Override
    public void swappedPreRender(float f)
    {
        rotateAngleX = -0.5f;
        rotateAngleZ = 0.3f;
        rotateAngleY = 0.4f;
        offsetX = -0.03f;
        offsetY = 0.05f;
    }
}
