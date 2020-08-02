package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityTextureFramesTableRenderer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityThaumAnvil;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityThaumAnvilRenderer extends TileEntityTextureFramesTableRenderer
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

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        renderAnvil((TileEntityThaumAnvil)tile, x, y, z, timeDelta);
        renderItemStackFromTile(tile, x, y, z);
    }

    protected void renderAnvil(TileEntityThaumAnvil thaumAnvil, double x, double y, double z, float timeDelta)
    {
        bindTexture(thaumAnvil.renderInfo, timeDelta);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotationFrom(thaumAnvil);
        model.renderAll();
        GL11.glPopMatrix();
    }
}
