package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityTextureFramesTableRenderer;
import helporme.armsforge.client.render.tiles.info.PrimalAnvilRenderInfo;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityPrimalAnvil;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimalAnvilRenderer extends TileEntityTextureFramesTableRenderer
{
    protected PrimalAnvilRenderInfo currentRenderInfo;

    public TileEntityPrimalAnvilRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    @Override
    protected String getTextureName()
    {
        return "PrimalAnvil";
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        renderAnvil((TileEntityPrimalAnvil)tile, x, y, z, timeDelta);
        renderItemStackFromTile(tile, x, y, z);
    }

    protected void renderAnvil(TileEntityPrimalAnvil tilePrimalAnvil, double x, double y, double z, float timeDelta)
    {
        currentRenderInfo = tilePrimalAnvil.renderInfo;
        rotateIndex = tilePrimalAnvil.getBlockMetadata();

        bindTexture(currentRenderInfo, timeDelta);
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5d, y, z + 0.5d);
        setFaceRotationFrom(tilePrimalAnvil);
        renderAllModel(timeDelta);
        GL11.glPopMatrix();
    }

    protected void renderAllModel(float timeDelta)
    {
        renderAnvilBase(timeDelta);
        renderChains(timeDelta);
        renderFlags();
    }

    protected void renderAnvilBase(float timeDelta)
    {
        if (currentRenderInfo.anvilYOffset * currentRenderInfo.anvilYOffsetSign >= currentRenderInfo.maxAnvilYOffset)
        {
            currentRenderInfo.anvilYOffsetSign = -currentRenderInfo.anvilYOffsetSign;
        }
        currentRenderInfo.anvilYOffset += currentRenderInfo.anvilYOffsetSpeed * timeDelta * currentRenderInfo.anvilYOffsetSign;

        GL11.glPushMatrix();
        GL11.glTranslated(0, currentRenderInfo.anvilYOffset, 0);
        model.renderOnly("Anvil");
        GL11.glPopMatrix();
    }

    protected void renderChains(float timeDelta)
    {
        currentRenderInfo.chainRotationAngle = (currentRenderInfo.chainRotationAngle + currentRenderInfo.chainRotationSpeed * timeDelta) % 360;
        renderChain(1, currentRenderInfo.chainRotationAngle);
        renderChain(2, -currentRenderInfo.chainRotationAngle);
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
        GL11.glTranslated(0, currentRenderInfo.anvilYOffset / -3, 0);
        model.renderOnly("FlagPillars", "Flag_1", "Flag_2");
    }

    @Override
    protected void setItem3dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.810d + currentRenderInfo.anvilYOffset, z + 0.5d);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(tile);
    }

    @Override
    protected void setItem2dTransformAt(TileEntity tile, double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5d, y + 0.84d + currentRenderInfo.anvilYOffset, z + 0.5d);
        setFaceRotationFrom(tile);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
