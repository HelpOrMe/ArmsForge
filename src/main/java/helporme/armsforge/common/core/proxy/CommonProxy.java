package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.core.registry.BlocksRegistry;
import helporme.armsforge.common.core.registry.ItemsRegistry;
import helporme.armsforge.common.core.registry.TilesRegistry;
import helporme.armsforge.common.integration.IntegrationManager;

public class CommonProxy implements IProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        BlocksRegistry.createDefaultBlocks();
        ItemsRegistry.createDefaultItems();
        IntegrationManager.prepareThaumcraft();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {
        BlocksRegistry.registerBlocks();
        ItemsRegistry.registerItems();
        TilesRegistry.registerTilesFromBlocks();
        IntegrationManager.registerThaumcraft();
    }
}
