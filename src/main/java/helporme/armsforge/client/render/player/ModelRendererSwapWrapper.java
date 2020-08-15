package helporme.armsforge.client.render.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererSwapWrapper extends ModelRenderer
{
    public boolean useSwappedRenderer = false;

    public ModelRendererSwapWrapper(ModelBase mainModel, int x, int y)
    {
        super(mainModel, x, y);
    }
}
