package helporme.armsforge.client.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.item.registry.ItemRendererRegistry;
import helporme.armsforge.common.core.proxy.CommonProxy;
import helporme.armsforge.client.render.block.tiles.registry.TileRendererRegistry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        TileRendererRegistry.registerTileRenderersFromModelSuites();
        ItemRendererRegistry.registerItemRenderersFromModelSuites();
    }
}
