package helporme.armsforge.client.render.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.block.model.IModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRenderBase implements IItemRenderer
{
    protected IModelCustom model;
    protected ResourceLocation texture;

    public ItemRenderBase(IModelInfo modelInfo)
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
