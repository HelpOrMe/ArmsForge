package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class SwappedModelRenderer extends ModelRenderer
{
    public ModelRenderer originalModel;
    protected boolean useSwappedRender = false;

    public SwappedModelRenderer(ModelBase mainModel, ModelRenderer originalModel)
    {
        // Set texture offset 0, 0 cos we copy cubelist
        super(mainModel, 0, 0);
        this.originalModel = originalModel;
        cubeList = originalModel.cubeList;
        resetRotationPoint();
    }

    public void setSwappedRender(boolean state)
    {
         if (useSwappedRender != state)
         {
             resetRotationPoint();
             useSwappedRender = state;
         }
    }

    public void resetRotationPoint()
    {
        setRotationPoint(originalModel.rotationPointX, originalModel.rotationPointY, originalModel.rotationPointZ);
    }

    @Override
    public void render(float f)
    {
        if (useSwappedRender)
        {
            swappedPreRender(f);
        }
        super.render(f);
    }

    public void swappedPreRender(float f) {}
}
