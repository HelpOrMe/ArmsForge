package helporme.armsforge.common.tiles;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.client.render.tiles.info.ThaumAnvilRenderInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;

public class TileEntityThaumAnvil extends TileEntityCraftingTableBase
{
    public ThaumAnvilRenderInfo renderInfo = new ThaumAnvilRenderInfo();

    @Override
    public CraftingTableType getType()
    {
        return new CraftingTableType("Anvil", 2);
    }
}
