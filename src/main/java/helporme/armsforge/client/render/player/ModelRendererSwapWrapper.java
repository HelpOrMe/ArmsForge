package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererSwapWrapper extends ModelRenderer
{
    public boolean useSwappedRenderer = false;
    public ModelRenderer originalModel;

    public ModelRendererSwapWrapper(ModelBase mainModel, ModelRenderer originalModel, int x, int y)
    {
        super(mainModel, x, y);
        this.originalModel = originalModel;
        cubeList = originalModel.cubeList;
        setRotationPoint(originalModel.rotationPointX, originalModel.rotationPointY, originalModel.rotationPointZ);
    }
}
