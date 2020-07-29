package helporme.armsforge.client.render.item.base;

import helporme.armsforge.common.blocks.models.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ItemRendererBase implements IItemRenderer
{
    protected IModelCustom model;
    protected ResourceLocation texture;

    public ItemRendererBase(ModelInfo modelInfo)
    {
        model = modelInfo.getModel();
        texture = modelInfo.getTexture();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                inventoryRender();
                break;
            default:
                defaultRender();
        }
    }

    protected void defaultRender()
    {
        bindBlockTexture();
        model.renderAll();
        GL11.glPopMatrix();
    }

    protected void inventoryRender()
    {
        bindBlockTexture();
        GL11.glTranslatef(0.55f, 0.0f, 0.55f);
        model.renderAll();
        GL11.glPopMatrix();
    }

    protected void bindBlockTexture()
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(texture);
        GL11.glPushMatrix();
    }
}
