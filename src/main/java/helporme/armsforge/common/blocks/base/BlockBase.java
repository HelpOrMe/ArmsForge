package helporme.armsforge.common.blocks.base;

import helporme.armsforge.common.core.registry.interfaces.INamedBlock;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block implements INamedBlock
{
    protected String name;

    protected BlockBase(Material material, String name)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setBlockName(name);
    }

    public String getName()
    {
        return name;
    }
}