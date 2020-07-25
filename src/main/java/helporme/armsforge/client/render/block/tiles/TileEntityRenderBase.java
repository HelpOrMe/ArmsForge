package helporme.armsforge.client.render.block.tiles;

import helporme.armsforge.common.core.Version;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public abstract class TileEntityRenderBase extends TileEntitySpecialRenderer
{
    protected ResourceLocation texture;
    protected ResourceLocation modelLocation;
    protected IModelCustom model;

    public TileEntityRenderBase(String texturePath, String modelPath)
    {
        texture = new ResourceLocation(Version.modid, texturePath);
        modelLocation = new ResourceLocation(Version.modid, modelPath);
        model = AdvancedModelLoader.loadModel(modelLocation);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5f, y, z + 0.5f);
        // GL11.glScalef(1,1,1);
        // GL11.glPushMatrix();
        // GL11.glRotatef(0, 0f, 1f, 0.5f);
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
