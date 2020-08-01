package helporme.armsforge.forge.wrapper.render.blocks;

import helporme.armsforge.common.blocks.models.ModelInfo;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererBase extends TileEntitySpecialRenderer
{
    protected ResourceLocation texture;
    protected IModelCustom model;

    public TileEntityRendererBase(ModelInfo modelInfo)
    {
        model = modelInfo.getModel();
        texture = modelInfo.getTexture();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5f, y, z + 0.5f);
        GL11.glPushMatrix();
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
