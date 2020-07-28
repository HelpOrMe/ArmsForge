package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockWithMetaBase;
import net.minecraft.block.material.Material;

public class MetalBlock extends BlockWithMetaBase
{
    public MetalBlock()
    {
        super(Material.iron, "MetalBlock", 9);
        setHardness(5f);
        setResistance(5f);
        setHarvestLevel("pickaxe", 2);
    }
}
