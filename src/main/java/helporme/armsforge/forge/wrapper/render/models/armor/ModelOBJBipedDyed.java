package helporme.armsforge.forge.wrapper.render.models.armor;

import helporme.armsforge.forge.wrapper.utils.Color;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelOBJBipedDyed extends ModelOBJBiped
{
    protected ResourceLocation coloredTexture;
    protected Color color;

    public ModelOBJBipedDyed(ModelInfo modelInfo, float f, float pY, int width, int height)
    {
        super(modelInfo, f, pY, width, height);
        coloredTexture = ModelInfo.getTexture(modelInfo.localTexturePath + "Colored");
    }

    public void setColor(int color)
    {
        this.color = Color.parseFromHex(color);
    }

    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    public int getRenderPasses()
    {
        return 2;
    }

    @Override
    public void renderPass(Entity entity, int pass, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        if (pass == 1)
        {
            GL11.glColor3f(color.r, color.g, color.b);
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(pass == 0 ? modelInfo.texture : coloredTexture);
        originRender(entity, f1, f2, f3, f4, f5, f6);
    }
}
