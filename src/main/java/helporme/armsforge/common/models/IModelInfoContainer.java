package helporme.armsforge.common.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModelInfoContainer
{
    @SideOnly(Side.CLIENT)
    ModelInfo getModelInfo();
}
