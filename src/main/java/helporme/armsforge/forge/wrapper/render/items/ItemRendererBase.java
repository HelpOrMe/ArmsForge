package helporme.armsforge.forge.wrapper.render.items;

import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererBase implements IItemRenderer
{
    protected ModelInfo modelInfo;

    public ItemRendererBase(ModelInfo modelInfo)
    {
        this.modelInfo = modelInfo;
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
                inventoryRender(); break;
            default:
                defaultRender();
        }
    }

    protected void defaultRender()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        renderModel();
        GL11.glPopMatrix();
    }

    protected void inventoryRender()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.55f, 0.0f, 0.55f);
        renderModel();
        GL11.glPopMatrix();
    }

    protected void bindBlockTexture()
    {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(modelInfo.texture);
    }

    protected void renderModel()
    {
        modelInfo.model.renderAll();
    }
}
