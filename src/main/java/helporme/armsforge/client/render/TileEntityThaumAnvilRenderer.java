package helporme.armsforge.client.render;

import helporme.armsforge.client.render.info.ThaumAnvilRenderInfo;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.TileEntityThaumAnvil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityThaumAnvilRenderer extends TileEntityTableRenderer
{
    protected ThaumAnvilRenderInfo renderInfo;

    public TileEntityThaumAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        TileEntityThaumAnvil thaumAnvil = (TileEntityThaumAnvil)tile;
        renderInfo = thaumAnvil.renderInfo;
        if (renderInfo != null)
        {
            renderAnvil(tile, x, y, z, timeDelta);
            renderItemStackFromTile(tile, x, y, z);
        }
    }

    protected void renderAnvil(TileEntity tile, double x, double y, double z, float timeDelta)
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
            int futureFrame = renderInfo.currentTextureFrame + renderInfo.textureFrameSign;
            if (futureFrame >= renderInfo.textureFramesCount || futureFrame < 0)
            {
                renderInfo.textureFrameSign = -renderInfo.textureFrameSign;
            }
            renderInfo.currentTextureFrame += renderInfo.textureFrameSign;
            renderInfo.timeBetweenFrames = 0;
        }
        bindTextureByTextureFrame(renderInfo.currentTextureFrame);
    }

    protected void bindTextureByTextureFrame(int textureFrame)
    {
        if (textureFrame != 0)
        {
            texture = new ResourceLocation(Version.modid, "textures/blocks/ThaumAnvil_" + textureFrame + ".png");
        }
        bindTexture(texture);
    }
}