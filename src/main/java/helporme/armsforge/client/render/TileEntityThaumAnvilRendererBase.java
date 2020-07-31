package helporme.armsforge.client.render;

import helporme.armsforge.client.render.info.ThaumAnvilRenderInfo;
import helporme.armsforge.client.render.tiles.base.TileEntityFacedRendererBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityThaumAnvilRendererBase extends TileEntityFacedRendererBase
{
    protected ThaumAnvilRenderInfo renderInfo = new ThaumAnvilRenderInfo();

    public TileEntityThaumAnvilRendererBase(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        bindTexture(timeDelta);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotationFrom(tile);
        model.renderAll();
        GL11.glPopMatrix();
    }

    protected void bindTexture(float timeDelta)
    {
        renderInfo.timeBetweenFrames += timeDelta;
        if (renderInfo.timeBetweenFrames >= renderInfo.cooldownBetweenFrames)
        {
            int decrFramesCount = renderInfo.textureFramesCount - 1;
            renderInfo.currentTextureFrame = renderInfo.random.nextInt(decrFramesCount) + decrFramesCount;
            renderInfo.timeBetweenFrames = 0;
        }
        bindTextureByTextureFrame(renderInfo.currentTextureFrame);
    }

    protected void bindTextureByTextureFrame(int textureFrame)
    {
        if (textureFrame != 0)
        {
            texture = new ResourceLocation(Version.modid, "textures/blocks/ThaumAnvil" + textureFrame + ".png");
        }
        bindTexture(texture);
    }
}
