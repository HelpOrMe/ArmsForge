package helporme.armsforge.forge.wrapper.render.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModelInfoContainer
{
    @SideOnly(Side.CLIENT)
    ModelInfo getModelInfo();
}
