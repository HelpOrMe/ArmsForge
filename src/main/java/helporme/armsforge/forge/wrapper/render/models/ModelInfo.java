package helporme.armsforge.forge.wrapper.render.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.core.Version;
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
        String texturePath = "textures/" + localTexturePath;
        String modelPath = "models/" + localModelPath;

        ResourceLocation textureLocation = new ResourceLocation(Version.modid, texturePath);
        ResourceLocation modelLocation = new ResourceLocation(Version.modid, modelPath);

        texture = textureLocation;
        model = AdvancedModelLoader.loadModel(modelLocation);
    }
}
