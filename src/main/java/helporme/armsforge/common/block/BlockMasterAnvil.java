package helporme.armsforge.common.block;

import helporme.armsforge.common.block.base.MasterAnvilBase;
import helporme.armsforge.common.core.Version;

public class BlockMasterAnvil extends MasterAnvilBase
{
    public BlockMasterAnvil()
    {
        super("MasterAnvil");
        setHardness(15f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setBlockTextureName(Version.modid + ":" + name);
        setBlockBounds(0.11f, 0f, 0.11f, 0.89f, 1f, 0.89f);
     }
}
