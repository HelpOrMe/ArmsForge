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
    public ResourceLocation texture;
    public IModelCustom model;

    public ModelInfo(String localTexturePath, String localModelPath)
    {
        String texturePath = "textures/" + localTexturePath + ".png";
        String modelPath = "models/" + localModelPath + ".obj";

        ResourceLocation textureLocation = ResourceManager.get(texturePath);
        ResourceLocation modelLocation = ResourceManager.get(modelPath);

        texture = textureLocation;
        model = AdvancedModelLoader.loadModel(modelLocation);
    }
}
