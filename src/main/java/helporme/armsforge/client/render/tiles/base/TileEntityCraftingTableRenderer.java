package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.utils.RotationHelper;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class TileEntityCraftingTableRenderer extends TileEntityTableRenderer
{
    protected ResourceLocation clockTexture;

    public TileEntityCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
        clockTexture = new ResourceLocation(Version.modid, "textures/gui/SandClock.png");
    }

    @Override
    protected void renderItemStack(TileEntityTable table, int slot, Vector3 position)
    {
        if (table.getItemSlot() == slot)
        {
            super.renderItemStack(table, slot, position);
        }
    }

    @Override
    protected void renderModel(TileEntity tile, float timeDelta)
    {
        modelInfo.model.renderAllExcept("Recipe");
        renderAdditionalObjects((TileEntityCraftingTable)tile);
    }

    protected void renderAdditionalObjects(TileEntityCraftingTable craftingTable)
    {
        if (!craftingTable.isEmptyInSlot(craftingTable.getRecipeSlot()))
        {
            renderRecipe();
        }
        if (craftingTable.isCraftActive())
        {
            renderCraftingInfo(craftingTable);
        }
        float arX = ActiveRenderInfo.rotationX;
        float arZ = ActiveRenderInfo.rotationZ;
        float arYZ = ActiveRenderInfo.rotationYZ;
        float arXY = ActiveRenderInfo.rotationXY;
        float arXZ = ActiveRenderInfo.rotationXZ;
    }

    protected void renderRecipe()
    {
        modelInfo.model.renderOnly("Recipe");
    }

    protected void renderCraftingInfo(TileEntityCraftingTable craftingTable)
    {
        float timeLeft = craftingTable.getTimeLeft();
        float maxTime = craftingTable.getMaxTime();
        float yOffset = Math.max(timeLeft / maxTime * 11f, 1f);

        double vStart = 11 - Math.round(yOffset) / 11d;
        double vEnd = vStart + 1 / 11d;

        Minecraft minecraft = Minecraft.getMinecraft();
        Tessellator tessellator = Tessellator.instance;

        RotationHelper.updateByActiveRender();
        Vec3 v1 = RotationHelper.v1;
        Vec3 v2 = RotationHelper.v2;
        Vec3 v3 = RotationHelper.v3;
        Vec3 v4 = RotationHelper.v4;

        double x = 0.2d;
        double y = 1.4d;
        double z = 0d;
        double scale = 0.3d;

        minecraft.renderEngine.bindTexture(clockTexture);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + v1.xCoord * scale, y + v1.yCoord * scale, z + v1.zCoord * scale, 0, vStart);
        tessellator.addVertexWithUV(x + v2.xCoord * scale, y + v2.yCoord * scale, z + v2.zCoord * scale, 1, vStart);
        tessellator.addVertexWithUV(x + v3.xCoord * scale, y + v3.yCoord * scale, z + v3.zCoord * scale, 1, vEnd);
        tessellator.addVertexWithUV(x + v4.xCoord * scale, y + v4.yCoord * scale, z + v4.zCoord * scale, 0, vEnd);
        tessellator.draw();

//        ArmsForgeApi.getHammerItem(craftingTable.getHammerType()).;
    }
}
