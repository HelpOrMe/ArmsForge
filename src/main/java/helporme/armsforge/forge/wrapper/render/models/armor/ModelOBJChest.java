package helporme.armsforge.forge.wrapper.render.models.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ModelOBJChest extends ModelOBJRenderer
{
    public ModelOBJChest(ModelBase modelBase, IModelCustom model, int tX, int tY)
    {
        super(modelBase, model, tX, tY);
    }

    @Override
    public void renderModel()
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180, 0, 0, 1);
        GL11.glTranslatef(0, -1.6f, 0);
        model.renderOnly("Chest");
        GL11.glPopMatrix();
    }
}
