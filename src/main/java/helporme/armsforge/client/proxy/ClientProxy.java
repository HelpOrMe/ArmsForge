package helporme.armsforge.client.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.registry.ItemRendererRegistry;
import helporme.armsforge.client.registry.TileRendererRegistry;
import helporme.armsforge.common.core.proxy.CommonProxy;
import helporme.armsforge.common.registry.ModelsRegistry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        ModelsRegistry.createModelSuitesFromBlocks();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        TileRendererRegistry.registerTileRenderersFromModelSuites();
        ItemRendererRegistry.registerItemRenderersFromModelSuites();
    }
}
