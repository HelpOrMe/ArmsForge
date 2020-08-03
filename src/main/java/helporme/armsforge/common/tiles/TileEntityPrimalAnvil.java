package helporme.armsforge.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.CraftingTableTypes;
import helporme.armsforge.client.render.tiles.info.PrimalAnvilRenderInfo;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;

public class TileEntityPrimalAnvil extends TileEntityCraftingTableBase
{
    @SideOnly(Side.CLIENT)
    public final PrimalAnvilRenderInfo renderInfo = new PrimalAnvilRenderInfo();

    @Override
    public CraftingTableType getTableType()
    {
        return CraftingTableTypes.primalAnvil;
    }
}
