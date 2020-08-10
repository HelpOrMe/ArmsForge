package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityMultiTextureTableRenderer;
import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityThaumAnvil;
import net.minecraft.tileentity.TileEntity;

public class TileEntityThaumAnvilRenderer extends TileEntityMultiTextureTableRenderer
{
    protected TextureFramesRenderInfo currentRenderInfo;

    public TileEntityThaumAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected String getTextureName()
    {
        return "ThaumAnvil";
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        TileEntityThaumAnvil thaumAnvil = (TileEntityThaumAnvil)tile;
        currentRenderInfo = thaumAnvil.renderInfo;
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
    }

    @Override
    protected TextureFramesRenderInfo getRenderInfo()
    {
        return currentRenderInfo;
    }
}
