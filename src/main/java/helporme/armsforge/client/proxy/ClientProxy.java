package helporme.armsforge.client.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.core.proxy.CommonProxy;
import helporme.armsforge.client.render.block.tiles.RenderTilesRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        RenderTilesRegistry.RegisterDefaultTileRenderers();
    }
}
