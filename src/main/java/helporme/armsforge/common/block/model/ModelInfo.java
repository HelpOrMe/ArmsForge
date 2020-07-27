package helporme.armsforge.common.block.model;

import helporme.armsforge.common.core.Version;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelInfo
{
    protected ResourceLocation texture;
    protected IModelCustom model;

    public ModelInfo(String localTexturePath, String localModelPath)
    {
        String texturePath = "textures/" + localTexturePath;
        String modelPath = "models/" + localModelPath;

        ResourceLocation textureLocation = new ResourceLocation(Version.modid, texturePath);
        ResourceLocation modelLocation = new ResourceLocation(Version.modid, modelPath);

        texture = textureLocation;
        model = AdvancedModelLoader.loadModel(modelLocation);
    }

    public ModelInfo(String modelName)
    {
        this("blocks/" + modelName + ".png", modelName + ".obj");
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public IModelCustom getModel()
    {
        return model;
    }
}
