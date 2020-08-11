package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityMultiTextureTableRenderer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;

public class TileEntityThaumAnvilRenderer extends TileEntityMultiTextureTableRenderer
{
    public TileEntityThaumAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected String getTextureName()
    {
        return "ThaumAnvil";
    }
}
