package helporme.armsforge.client.render;

import helporme.armsforge.client.render.info.PrimalAnvilRenderInfo;
import helporme.armsforge.client.render.tiles.base.TileEntityTableRendererBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.TileEntityPrimalAnvil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimalAnvilRenderer extends TileEntityTableRendererBase
{
    public TileEntityPrimalAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        if (tile instanceof TileEntityPrimalAnvil)
        {
            renderAnvil((TileEntityPrimalAnvil)tile, x, y, z, timeDelta);
        }
    }

    protected void renderAnvil(TileEntityPrimalAnvil primalAnvil, double x, double y, double z, float timeDelta)
    {
        PrimalAnvilRenderInfo renderInfo = primalAnvil.renderInfo;

        rotateIndex = primalAnvil.getBlockMetadata();
        bindTexture(renderInfo, timeDelta);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotation();
        renderAllModel(renderInfo, timeDelta);
        GL11.glPopMatrix();
    }

    protected void bindTexture(PrimalAnvilRenderInfo renderInfo, float timeDelta)
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
        System.out.println(textureFrame);
        if (textureFrame != 0)
        {
            texture = new ResourceLocation(Version.modid, "textures/blocks/PrimalAnvil_" + (textureFrame - 1) + ".png");
        }
        bindTexture(texture);
    }

    protected void renderAllModel(PrimalAnvilRenderInfo renderInfo, float timeDelta)
    {
        renderAnvilBase(renderInfo, timeDelta);
        renderChains(renderInfo, timeDelta);
        model.renderOnly("FlagPillars", "Flag_1", "Flag_2");
    }

    protected void renderAnvilBase(PrimalAnvilRenderInfo renderInfo, float timeDelta)
    {
        if (renderInfo.anvilYOffset * renderInfo.anvilYOffsetSign >= renderInfo.maxAnvilYOffset)
        {
            renderInfo.anvilYOffsetSign = -renderInfo.anvilYOffsetSign;
        }
        renderInfo.anvilYOffset += renderInfo.anvilYOffsetSpeed * timeDelta * renderInfo.anvilYOffsetSign;

        GL11.glPushMatrix();
        GL11.glTranslated(0, renderInfo.anvilYOffset, 0);
        model.renderOnly("Anvil");
        GL11.glPopMatrix();
    }

    protected void renderChains(PrimalAnvilRenderInfo renderInfo, float timeDelta)
    {
        renderInfo.chainRotationAngle = (renderInfo.chainRotationAngle + renderInfo.chainRotationSpeed * timeDelta) % 360;
        renderChain(1, renderInfo.chainRotationAngle);
        renderChain(2, -renderInfo.chainRotationAngle);
    }

    protected void renderChain(int chainIndex, double angle)
    {
        GL11.glPushMatrix();
        GL11.glRotated(angle, 0, 1, 0);
        model.renderOnly("Chain_" + chainIndex);
        GL11.glPopMatrix();
    }
}
