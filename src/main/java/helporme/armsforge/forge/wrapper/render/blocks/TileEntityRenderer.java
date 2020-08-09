package helporme.armsforge.forge.wrapper.render.blocks;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.forge.wrapper.models.ModelInfo;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityRenderer extends TileEntitySpecialRenderer
{
    protected ResourceLocation texture;
    protected IModelCustom model;

    public TileEntityRenderer(ModelInfo modelInfo)
    {
        model = modelInfo.getModel();
        texture = modelInfo.getTexture();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        bindTexture(tile, timeDelta);
        GL11.glPushMatrix();
        setTileEntityOffsetAt(tile, new Vector3(x, y, z), timeDelta);
        renderModel(tile, timeDelta);
        GL11.glPopMatrix();
    }

    protected void bindTexture(TileEntity tile, float timeDelta)
    {
        bindTexture(texture);
    }

    protected void setTileEntityOffsetAt(TileEntity tile, Vector3 position, float timeDelta)
    {
        GL11.glTranslatef(position.x + 0.5f, position.y, position.z + 0.5f);
    }

    protected void renderModel(TileEntity tile, float timeDelta)
    {
        model.renderAll();
    }
}
