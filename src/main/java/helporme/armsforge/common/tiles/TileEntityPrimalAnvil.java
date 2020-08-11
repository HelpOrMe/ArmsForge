package helporme.armsforge.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.blocks.tiles.table.CraftingTableTypes;
import helporme.armsforge.client.render.tiles.info.PrimalAnvilRenderInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;

public class TileEntityPrimalAnvil extends TileEntityCraftingTable
{
    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.primalAnvil;
    }
}
