package helporme.armsforge.client.render.tiles;

import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.tiles.base.TileEntityMultiTextureTableRenderer;
import helporme.armsforge.client.render.tiles.info.PrimalAnvilRenderInfo;
import helporme.armsforge.common.tiles.TileEntityPrimalAnvil;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimalAnvilRenderer extends TileEntityMultiTextureTableRenderer
{
    protected PrimalAnvilRenderInfo renderInfo = new PrimalAnvilRenderInfo();
    
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
    protected void renderModel(TileEntity tile, float timeDelta)
    {
        long curTick = tile.getWorldObj().getTotalWorldTime();
        renderAnvilBase(curTick);
        renderChains(curTick);
        renderFlags();
        renderCustomObjects((TileEntityPrimalAnvil)tile);
    }

    protected void renderAnvilBase(long curTick)
    {
        if (renderInfo.anvilYTick * renderInfo.anvilYSign >= renderInfo.maxAnvilYTicks)
        {
            renderInfo.anvilYSign = -renderInfo.anvilYSign;
        }
        renderInfo.anvilYTick = curTick % renderInfo.maxAnvilYTicks * renderInfo.anvilYSign;

        GL11.glPushMatrix();
        GL11.glTranslatef(0, renderInfo.anvilYTick * renderInfo.anvilTickDistance, 0);
        modelInfo.model.renderOnly("Anvil");
        GL11.glPopMatrix();
    }

    protected void renderChains(long curTick)
    {
        renderInfo.chainRotationAngle = curTick % 360;
        renderChain(1, renderInfo.chainRotationAngle);
        renderChain(2, -renderInfo.chainRotationAngle);
    }

    protected void renderChain(int chainIndex, float angle)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(angle, 0, 1, 0);
        modelInfo.model.renderOnly("Chain_" + chainIndex);
        GL11.glPopMatrix();
    }

    protected void renderFlags()
    {
        GL11.glTranslatef(0, renderInfo.anvilYTick * renderInfo.anvilTickDistance / -3, 0);
        modelInfo.model.renderOnly("FlagPillars", "Flag_1", "Flag_2");
    }

    @Override
    protected void renderRecipe()
    {
        GL11.glTranslatef(0, renderInfo.anvilYTick * renderInfo.anvilTickDistance, 0);
        modelInfo.model.renderPart("Recipe");
    }

    protected void setItem3dTransformAt(TileEntityTable table, Vector3 position)
    {
        float y = position.y + 0.878f + renderInfo.anvilYTick * renderInfo.anvilTickDistance;
        GL11.glTranslatef(position.x + 0.5f, y, position.z + 0.5f);
        GL11.glScalef(0.85f, 0.85f, 0.85f);
        setFaceRotationFrom(table);
    }

    protected void setItem2dTransformAt(TileEntityTable table, Vector3 position)
    {
        float y = position.y + 0.9f + renderInfo.anvilYTick * renderInfo.anvilTickDistance;
        GL11.glTranslatef(position.x + 0.5f, y, position.z + 0.5f);
        setFaceRotationFrom(table);
        GL11.glRotatef(-90f, 1f, 0f, 0f);
        GL11.glTranslatef(0, 0.2f, 0);
        GL11.glRotatef(180f, 0f, 0f, 1f);
    }
}
