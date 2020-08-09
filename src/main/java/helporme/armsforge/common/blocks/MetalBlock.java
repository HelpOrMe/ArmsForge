package helporme.armsforge.common.blocks;

import helporme.armsforge.forge.wrapper.blocks.BlockColored;
import helporme.armsforge.common.registry.utils.MaterialColors;
import net.minecraft.block.material.Material;

public class MetalBlock extends BlockColored
{
    public MetalBlock()
    {
        super(Material.iron, "MetalBlock", MaterialColors.metalColors);
        setHardness(5f);
        setResistance(5f);
        setHarvestLevel("pickaxe", 2);
    }
}
