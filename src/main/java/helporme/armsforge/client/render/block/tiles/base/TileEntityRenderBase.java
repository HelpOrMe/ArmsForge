package helporme.armsforge.client.render.block.tiles.base;

import helporme.armsforge.common.block.model.IModelInfo;
import helporme.armsforge.common.core.Version;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityRenderBase extends TileEntitySpecialRenderer
{
    protected ResourceLocation texture;
    protected IModelCustom model;

    public TileEntityRenderBase(IModelInfo modelInfo)
    {
        model = modelInfo.getModel();
        texture = modelInfo.getTexture();

        // texture = new ResourceLocation(Version.modid, texturePath);
        // modelLocation = new ResourceLocation(Version.modid, modelPath);
        // model = AdvancedModelLoader.loadModel(modelLocation);
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
