package helporme.armsforge.client.render.items;

import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.items.ItemRendererBase;

public class ItemWeaponRenderer extends ItemRendererBase
{
    public ItemWeaponRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void renderModel()
    {
        model.renderAllExcept("Recipe");
    }
}
