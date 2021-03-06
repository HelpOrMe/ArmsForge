package helporme.armsforge.forge.wrapper.blocks;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.utils.INamed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block implements INamed
{
    protected final String name;

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
