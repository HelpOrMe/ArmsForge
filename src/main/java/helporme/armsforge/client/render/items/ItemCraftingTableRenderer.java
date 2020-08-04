package helporme.armsforge.client.render.items;

import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.items.ItemRendererBase;
import org.lwjgl.opengl.GL11;

public class ItemCraftingTableRenderer extends ItemRendererBase
{
    public ItemCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void defaultRender()
    {
        bindBlockTexture();
        model.renderAllExcept("Recipe");
        GL11.glPopMatrix();
    }
}
