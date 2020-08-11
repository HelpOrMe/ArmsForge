package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public abstract class TileEntityMultiTextureTableRenderer extends TileEntityCraftingTableRenderer
{
    protected final Map<Integer, ResourceLocation> textureFramesCache = new HashMap<>();

    public TileEntityMultiTextureTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void bindTexture(TileEntity tile, float timeDelta)
    {
        TextureFramesRenderInfo renderInfo = getRenderInfo();
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
                ResourceLocation textureLocation = ResourceManager.get("textures/blocks/"+ getTextureName() + "_" + textureFrame + ".png");
                textureFramesCache.put(textureFrame, textureLocation);
            }
            modelInfo.texture = textureFramesCache.get(textureFrame);
        }
        bindTexture(modelInfo.texture);
    }

    protected abstract String getTextureName();

    protected abstract TextureFramesRenderInfo getRenderInfo();
}
