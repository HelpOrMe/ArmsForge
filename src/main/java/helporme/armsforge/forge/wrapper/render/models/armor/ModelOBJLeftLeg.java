package helporme.armsforge.forge.wrapper.render.models.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ModelOBJLeftLeg extends ModelOBJRenderer
{
    public boolean showLeg = false;
    public boolean showBoot = false;

    public ModelOBJLeftLeg(ModelBase modelBase, IModelCustom model, int tX, int tY)
    {
        super(modelBase, model, tX, tY);
    }

    @Override
    public void renderModel()
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180, 0, 0, 1);
        GL11.glTranslatef(0, -0.8f, 0);
        GL11.glTranslatef(0.14f, 0, 0);

        if (showLeg)
        {
            model.renderOnly("LeftLeg");
        }
        if (showBoot)
        {
            model.renderOnly("LeftBoot");
        }
        GL11.glPopMatrix();
    }
}
