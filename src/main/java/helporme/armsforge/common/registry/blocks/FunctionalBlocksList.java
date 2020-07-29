package helporme.armsforge.common.registry.blocks;

import helporme.armsforge.common.blocks.*;

public class FunctionalBlocksList extends BlocksList
{
    public void createDefault()
    {
        addBlocks(
                new BlockMasterAnvil(),
                new BlockMasterAnvilGold(),
                new BlockArmorerTable(),
                new BlockArmorsmithTable(),
                new BlockSupportTable()
        );
    }
}
