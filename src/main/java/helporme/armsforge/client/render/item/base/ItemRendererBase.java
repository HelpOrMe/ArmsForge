package helporme.armsforge.client.render.item.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.block.model.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererBase implements IItemRenderer
{
    protected IModelCustom model;
    protected ResourceLocation texture;

    public ItemRendererBase(ModelInfo modelInfo)
    {
        model = modelInfo.getModel();
        texture = modelInfo.getTexture();
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return isRenderCase(type);
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (isRenderCase(type))
        {
            defaultRender();
        }
    }

    protected boolean isRenderCase(ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case ENTITY:
            case INVENTORY:
                return true;
        }
        return false;
    }

    protected void defaultRender()
    {
        bindBlockTexture();
        GL11.glTranslatef(0.72f, 0.0f, 0.72f);
        GL11.glScaled(1.22f, 1.22f, 1.22f);
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