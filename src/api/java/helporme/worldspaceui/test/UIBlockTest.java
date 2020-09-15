package helporme.worldspaceui.test;

import helporme.worldspaceui.types.Vector3i;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILayout;
import helporme.worldspaceui.ui.UISynced;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class UIBlockTest extends UISynced
{
    public UIBlockTest() { }

    public UIBlockTest(Vector3i blockPos)
    {
        logger.info("UI constructed with: " + blockPos);
    }

    @Override
    public void onUI()
    {
        if (UILayout.getCurrentMode() == UICallMode.RENDER)
        {
            GL11.glPushMatrix();
            ItemStack itemStack = new ItemStack(Items.bow);
            EntityItem entityItem = new EntityItem(location.getWorld(), 0d, 0d, 0d, itemStack);
            entityItem.hoverStart = 0.0F;
            GL11.glTranslated(location.position.x + 0.5d, location.position.y + 0.9d, location.position.z + 0.5d);
            GL11.glRotatef(-90f, 1f, 0f, 0f);
            GL11.glTranslatef(0, 0.2f, 0);
            GL11.glRotatef(180f, 0f, 0f, 1f);
            RenderItem.renderInFrame = true;
            RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();
        }
    }
}
