package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public abstract class TileEntityMultiTextureTableRenderer extends TileEntityCraftingTableRenderer
{
    protected final Map<Integer, ResourceLocation> textureFramesCache = new HashMap<>();
    protected TextureFramesRenderInfo renderInfo = new TextureFramesRenderInfo();

    public TileEntityMultiTextureTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected void bindTexture(TileEntity tile, float timeDelta)
    {
        long curTick = tile.getWorldObj().getTotalWorldTime();
        if (curTick >= renderInfo.nextTick)
        {
            int futureFrame = renderInfo.frameSign;
            if (futureFrame >= renderInfo.getMaxFrame() || futureFrame < 0)
            {
                renderInfo.frameSign = -renderInfo.frameSign;
            }
            renderInfo.curFrame += renderInfo.frameSign;
            renderInfo.curFrame %= renderInfo.getMaxFrame();
            renderInfo.nextTick = curTick + 20;
        }
        bindTextureBy(renderInfo.curFrame);
    }

    protected void bindTextureBy(int textureFrame)
    {
        if (textureFrame != 0)
        {
            if (!textureFramesCache.containsKey(textureFrame))
            {
                ResourceLocation textureLocation = ResourceManager.get(
                        "textures/blocks/"+ getTextureName() + "_" + textureFrame + ".png");
                textureFramesCache.put(textureFrame, textureLocation);
            }
            modelInfo.texture = textureFramesCache.get(textureFrame);
        }
        bindTexture(modelInfo.texture);
    }

    protected abstract String getTextureName();
}
