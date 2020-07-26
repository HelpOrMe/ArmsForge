package helporme.armsforge.common.block.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

public interface IModelInfo
{
    ResourceLocation getTexture();
    IModelCustom getModel();
}
