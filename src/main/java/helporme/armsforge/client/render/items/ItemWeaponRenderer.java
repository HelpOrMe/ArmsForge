package helporme.armsforge.client.render.items;

import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.items.ItemRendererBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ItemWeaponRenderer extends ItemRendererBase
{
    public ItemWeaponRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case EQUIPPED:
                equippedRender(); break;
            case EQUIPPED_FIRST_PERSON:
                quippedFirstPerson(); break;
            case INVENTORY:
                inventoryRender(); break;
            default:
                defaultRender();
        }
    }

    protected void equippedRender()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        GL11.glRotatef(-45, 0, 1, 0);
        GL11.glRotatef(60, 0, 0, 1);
        GL11.glTranslatef(1.1f, -1f, 0);
        GL11.glScalef(2, 2, 2);
        renderModel();
        GL11.glPopMatrix();
    }

    protected void quippedFirstPerson()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        GL11.glRotatef(45, 0, 1, 0);
        GL11.glTranslatef(0, 0.4f, 0.8f);
        GL11.glRotatef(22, 0, 0, 1);
        GL11.glScalef(1.25f, 1.25f, 1.25f);
        renderModel();
        GL11.glPopMatrix();
    }

    @Override
    protected void inventoryRender()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.55f, 0, 0.55f);
        GL11.glRotatef(45, 0, 1, 0);
        GL11.glRotatef(-45, 1, 0, 0);
        GL11.glRotatef(-45, 0, 0, 1);
        GL11.glTranslatef(-0.27f, -0.27f, 0);
//        GL11.glScalef(2.5f, 2.5f, 2.5f);
        renderModel();
        GL11.glPopMatrix();
    }

    @Override
    protected void defaultRender()
    {
        bindBlockTexture();
        GL11.glPushMatrix();
        GL11.glRotatef(90, 0, 1, 0);
        GL11.glRotatef(-45, 0, 0, 1);
        GL11.glTranslatef(0, -0.4f, 0);
//        GL11.glScalef(1.6f, 1.6f, 1.6f);
        renderModel();
        GL11.glPopMatrix();
    }
}
