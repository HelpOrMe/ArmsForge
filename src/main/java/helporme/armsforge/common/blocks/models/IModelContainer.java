package helporme.armsforge.common.blocks.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModelContainer
{
    @SideOnly(Side.CLIENT)
    ModelInfo getModelInfo();

    @SideOnly(Side.CLIENT)
    ModelSuite getModelSuite();
}
