package helporme.armsforge.common.blocks;

import helporme.armsforge.client.render.colors.Colors;
import helporme.armsforge.common.blocks.base.BlockColoredBase;
import net.minecraft.block.material.Material;

public class MetalBlock extends BlockColoredBase
{
    public MetalBlock()
    {
        super(Material.iron, "MetalBlock", Colors.metalColors);
        setHardness(5f);
        setResistance(5f);
        setHarvestLevel("pickaxe", 2);
    }
}
