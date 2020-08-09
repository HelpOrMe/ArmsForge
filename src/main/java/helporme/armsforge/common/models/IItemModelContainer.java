package helporme.armsforge.common.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.IItemRenderer;

public interface IItemModelContainer extends IModelInfoContainer
{
    @SideOnly(Side.CLIENT)
    IItemRenderer getItemRenderer(ModelInfo modelInfo);
}
