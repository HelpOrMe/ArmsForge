package helporme.armsforge.common.registry.blocks;

import helporme.armsforge.common.blocks.*;

public class FunctionalBlocksList extends BlocksList
{
    public void addDefault()
    {
        addBlocks(
                new BlockMasterAnvil(),
                new BlockThaumAnvil(),
                new BlockPrimalAnvil(),
                new BlockArmorerTable(),
                new BlockArmssmithTable(),
                new BlockSupportTable()
        );
    }
}
