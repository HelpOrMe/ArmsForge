package helporme.armsforge.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.info.PrimalAnvilRenderInfo;

public class TileEntityPrimalAnvil extends TileEntityMasterAnvilAdvanced
{
    @SideOnly(Side.CLIENT)
    public PrimalAnvilRenderInfo renderInfo = new PrimalAnvilRenderInfo();
}
