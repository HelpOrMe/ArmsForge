package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBiped;

public class RightHandTwoHandedModel extends SwappedModelRenderer
{
    public RightHandTwoHandedModel(ModelBiped modelBiped)
    {
        super(modelBiped, modelBiped.bipedRightArm);
    }

    @Override
    public void swappedPreRender(float f)
    {
        rotateAngleX = -1f;
        rotateAngleZ = -0.75f;
        rotateAngleY = -0.15f;
    }
}
