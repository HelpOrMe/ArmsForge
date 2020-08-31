package helporme.armsforge.forge.wrapper.render.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelInfo
{
    public String localTexturePath;
    public String localModelPath;
    public ResourceLocation texture;
    public IModelCustom model;

    public ModelInfo(String localTexturePath, String localModelPath)
    {
        this.localTexturePath = localTexturePath;
        this.localModelPath = localModelPath;

        texture = getTexture(localTexturePath);
        model = getModel(localModelPath);
    }

    public static ResourceLocation getTexture(String localTexturePath)
    {
        String texturePath = "textures/" + localTexturePath + ".png";
        return ResourceManager.get(texturePath);
    }

    public static IModelCustom getModel(String localModelPath)
    {
        String modelPath = "models/" + localModelPath + ".obj";
        ResourceLocation modelLocation = ResourceManager.get(modelPath);
        return AdvancedModelLoader.loadModel(modelLocation);
    }
}
