package helporme.armsforge.common.block.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModel
{
    @SideOnly(Side.CLIENT)
    ModelSuite getModelSuite();
}
