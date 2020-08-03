package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public abstract class TileEntityTextureFramesTableRenderer extends TileEntityTableRenderer
{
    protected final Map<Integer, ResourceLocation> textureFramesCache = new HashMap<>();

    public TileEntityTextureFramesTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    protected void bindTexture(TextureFramesRenderInfo renderInfo, float timeDelta)
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
        bindTextureBy(renderInfo.currentTextureFrame);
    }

    protected void bindTextureBy(int textureFrame)
    {
        if (textureFrame != 0)
        {
            if (!textureFramesCache.containsKey(textureFrame))
            {
                ResourceLocation textureLocation = new ResourceLocation(
                        Version.modid, "textures/blocks/"+ getTextureName() + "_" + textureFrame + ".png");
                textureFramesCache.put(textureFrame, textureLocation);
            }
            texture = textureFramesCache.get(textureFrame);
        }
        bindTexture(texture);
    }

    protected abstract String getTextureName();
}
