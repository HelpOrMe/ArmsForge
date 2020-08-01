package helporme.armsforge.client.render;

import helporme.armsforge.client.render.info.PrimalAnvilRenderInfo;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.TileEntityPrimalAnvil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimalAnvilRenderer extends TileEntityTableRenderer
{
    protected PrimalAnvilRenderInfo renderInfo;

    public TileEntityPrimalAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        TileEntityPrimalAnvil primalAnvil = (TileEntityPrimalAnvil)tile;
        renderInfo = primalAnvil.renderInfo;
        if (renderInfo != null)
        {
            renderAnvil((TileEntityPrimalAnvil)tile, x, y, z, timeDelta);
            renderItemStackFromTile(tile, x, y, z);
        }
    }

    protected void renderAnvil(TileEntityPrimalAnvil tilePrimalAnvil, double x, double y, double z, float timeDelta)
    {
        rotateIndex = tilePrimalAnvil.getBlockMetadata();
        bindTexture(timeDelta);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotationFrom(tilePrimalAnvil);
        renderAllModel(timeDelta);
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
            texture = new ResourceLocation(Version.modid, "textures/blocks/PrimalAnvil_" + textureFrame + ".png");
        }
        bindTexture(texture);
    }

    protected void renderAllModel(float timeDelta)
    {
        renderAnvilBase(timeDelta);
        renderChains(timeDelta);
        renderFlags();
    }

    protected void renderAnvilBase(float timeDelta)
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

    protected void renderChains(float timeDelta)
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

    protected void renderFlags()
    {
        GL11.glTranslated(0, renderInfo.anvilYOffset / -3, 0);
        model.renderOnly("FlagPillars", "Flag_1", "Flag_2");
    }

    @Override
    protected void setItem3dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.810d + renderInfo.anvilYOffset, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(tile);
    }

    @Override
    protected void setItem2dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.84d + renderInfo.anvilYOffset, z + 0.5d);
        setFaceRotationFrom(tile);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
