package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.core.registry.interfaces.INamed;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block implements INamed
{
    protected String name;

    protected BlockBase(Material material, String name)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setBlockName(name);
        setBlockTextureName(Version.modid + ":" + name);

    }

    public String getName()
    {
        return name;
    }
}
