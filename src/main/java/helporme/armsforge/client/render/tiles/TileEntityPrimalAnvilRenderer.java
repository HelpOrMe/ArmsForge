package helporme.armsforge.client.render.tiles;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.tiles.base.TileEntityMultiTextureTableRenderer;
import helporme.armsforge.client.render.tiles.info.PrimalAnvilRenderInfo;
import helporme.armsforge.client.render.tiles.info.TextureFramesRenderInfo;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityPrimalAnvil;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimalAnvilRenderer extends TileEntityMultiTextureTableRenderer
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
    protected TextureFramesRenderInfo getRenderInfo()
    {
        return currentRenderInfo;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        TileEntityPrimalAnvil primalAnvil = (TileEntityPrimalAnvil)tile;
        currentRenderInfo = primalAnvil.renderInfo;
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
    }

    @Override
    protected void renderModel(TileEntity tile, float timeDelta)
    {
        renderAnvilBase(timeDelta);
        renderChains(timeDelta);
        renderFlags();
        tryRenderRecipe((TileEntityCraftingTable)tile);
    }

    protected void renderAnvilBase(float timeDelta)
    {
        if (currentRenderInfo.anvilYOffset * currentRenderInfo.anvilYOffsetSign >= currentRenderInfo.maxAnvilYOffset)
        {
            currentRenderInfo.anvilYOffsetSign = -currentRenderInfo.anvilYOffsetSign;
        }
        currentRenderInfo.anvilYOffset += currentRenderInfo.anvilYOffsetSpeed * timeDelta * currentRenderInfo.anvilYOffsetSign;

        GL11.glPushMatrix();
        GL11.glTranslatef(0, currentRenderInfo.anvilYOffset, 0);
        model.renderOnly("Anvil");
        GL11.glPopMatrix();
    }

    protected void renderChains(float timeDelta)
    {
        currentRenderInfo.chainRotationAngle = (currentRenderInfo.chainRotationAngle + currentRenderInfo.chainRotationSpeed * timeDelta) % 360;
        renderChain(1, currentRenderInfo.chainRotationAngle);
        renderChain(2, -currentRenderInfo.chainRotationAngle);
    }

    protected void renderChain(int chainIndex, float angle)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(angle, 0, 1, 0);
        model.renderOnly("Chain_" + chainIndex);
        GL11.glPopMatrix();
    }

    protected void renderFlags()
    {
        GL11.glTranslatef(0, currentRenderInfo.anvilYOffset / -3, 0);
        model.renderOnly("FlagPillars", "Flag_1", "Flag_2");
    }

    @Override
    protected void tryRenderRecipe(TileEntityCraftingTable craftingTable)
    {
        boolean hasRecipe = !craftingTable.isEmptyInSlot(1);
        if (hasRecipe)
        {
            GL11.glTranslatef(0, currentRenderInfo.anvilYOffset, 0);
            model.renderPart("Recipe");
        }
    }

    protected void setItem3dTransformAt(TileEntityTable table, Vector3 position)
    {
        float y = position.y + 0.878f + currentRenderInfo.anvilYOffset;
        GL11.glTranslatef(position.x + 0.5f, y, position.z + 0.5f);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(table);
    }

    protected void setItem2dTransformAt(TileEntityTable table, Vector3 position)
    {
        float y = position.y + 0.9f + currentRenderInfo.anvilYOffset;
        GL11.glTranslatef(position.x + 0.5f, y, position.z + 0.5f);
        setFaceRotationFrom(table);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
