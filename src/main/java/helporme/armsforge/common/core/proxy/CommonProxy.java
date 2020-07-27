package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.block.registry.BlocksRegistry;
import helporme.armsforge.common.block.tiles.registry.TilesRegistry;
import helporme.armsforge.common.integration.IntegrationManager;

public class CommonProxy implements IProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        BlocksRegistry.createDefaultBlocks();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {
        BlocksRegistry.registerBlocks();
        TilesRegistry.registerTilesFromBlocks();
        IntegrationManager.prepareThaumcraft();
        IntegrationManager.registerThaumcraft();
    }
}
