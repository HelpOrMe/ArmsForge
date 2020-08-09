package helporme.armsforge.client.render.items;

import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.items.ItemRendererBase;

public class ItemCraftingTableRenderer extends ItemRendererBase
{
    public ItemCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void renderModel()
    {
        model.renderAllExcept("Recipe");
    }
}
