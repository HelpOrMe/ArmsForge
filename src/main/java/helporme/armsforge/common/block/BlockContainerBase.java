package helporme.armsforge.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockContainerBase extends BlockContainer implements INamedBlock
{
    protected String name;

    protected BlockContainerBase(Material material, String blockName)
    {
        super(material);
        name = blockName;
        setBlockName(blockName);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
