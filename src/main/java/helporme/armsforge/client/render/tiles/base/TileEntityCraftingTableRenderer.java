package helporme.armsforge.client.render.tiles.base;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.client.render.utils.QuadRotation;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class TileEntityCraftingTableRenderer extends TileEntityTableRenderer
{
    protected ResourceLocation clockTexture;
    protected Map<String, ResourceLocation> hammerTextures = new HashMap<>();

    public TileEntityCraftingTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
        clockTexture = ResourceManager.get("textures/gui/SandClock.png");
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
        renderCustomObjects((TileEntityCraftingTable)tile);
    }

    protected void renderCustomObjects(TileEntityCraftingTable craftingTable)
    {
        if (!craftingTable.isEmptyInSlot(craftingTable.getRecipeSlot()))
        {
            renderRecipe();
        }
        if (craftingTable.isCraftActive())
        {
            renderClock(craftingTable);
            renderHammerType(craftingTable);
        }
    }

    protected void renderRecipe()
    {
        modelInfo.model.renderOnly("Recipe");
    }

    protected void renderClock(TileEntityCraftingTable craftingTable)
    {
        float timeLeft = craftingTable.getTimeLeft();
        float maxTime = craftingTable.getMaxTime();

        float yOffset = Math.max(timeLeft / maxTime * 11f, 1f);
        double vStart = 11 - Math.round(yOffset) / 11d;
        double vEnd = vStart + 1 / 11d;

        QuadRotation quadRotation = new QuadRotation();
        quadRotation.updateByActiveRender();

        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.renderEngine.bindTexture(clockTexture);
        quadRotation.renderIconQuad(new Vector3(0.225f, 1.4f, 0f), 0.3d, vStart, vEnd);
    }

    protected void renderHammerType(TileEntityCraftingTable craftingTable)
    {
        HammerType hammerType = craftingTable.getNeededHammerType();
        String textureString = ArmsForgeApi.getHammerTexture(hammerType);

        if (!hammerTextures.containsKey(textureString))
        {
            hammerTextures.put(textureString, new ResourceLocation(textureString));
        }
        ResourceLocation hammerTexture = hammerTextures.get(textureString);

        QuadRotation quadRotation = new QuadRotation();
        quadRotation.updateByActiveRender();

        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.renderEngine.bindTexture(hammerTexture);
        quadRotation.renderIconQuad(new Vector3(-0.225f, 1.4f, 0f), 0.3d, 0, 1);
    }
}
