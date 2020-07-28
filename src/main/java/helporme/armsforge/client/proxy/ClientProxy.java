package helporme.armsforge.client.proxy;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.registry.TileRendererRegistry;
import helporme.armsforge.client.registry.ItemRendererRegistry;
import helporme.armsforge.common.core.registry.ModelsRegistry;
import helporme.armsforge.common.core.proxy.CommonProxy;

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
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
        TileRendererRegistry.registerTileRenderersFromModelSuites();
        ItemRendererRegistry.registerItemRenderersFromModelSuites();
    }
}
