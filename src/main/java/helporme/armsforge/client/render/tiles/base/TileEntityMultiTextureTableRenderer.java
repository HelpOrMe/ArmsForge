package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
                ResourceLocation textureLocation = new ResourceLocation(
                        Version.modid, "textures/blocks/"+ getTextureName() + "_" + textureFrame + ".png");
                textureFramesCache.put(textureFrame, textureLocation);
            }
            texture = textureFramesCache.get(textureFrame);
        }
        bindTexture(texture);
    }

    protected abstract String getTextureName();

    protected abstract TextureFramesRenderInfo getRenderInfo();
}
