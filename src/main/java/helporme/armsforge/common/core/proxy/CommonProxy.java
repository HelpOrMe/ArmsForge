package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.integration.IntegrationManager;
import helporme.armsforge.common.registry.BlocksRegistry;
import helporme.armsforge.common.registry.ItemsRegistry;
import helporme.armsforge.common.registry.RecipesRegistry;
import helporme.armsforge.common.registry.TilesRegistry;
import helporme.worldspaceui.WorldSpaceUIServer;

public class CommonProxy implements IProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        BlocksRegistry.createDefaultBlocks();
        ItemsRegistry.createDefaultItems();
    }

    public void init(FMLInitializationEvent event)
    {
        BlocksRegistry.registerBlocks();
        ItemsRegistry.registerItems();
        TilesRegistry.registerTilesFromBlocks();
        RecipesRegistry.addDefaultRecipes();

        IntegrationManager.registerArmsForge();
        ArmsForge.packetHandler.init();
    }

    @Override
    public void serverInit(FMLServerStartingEvent event)
    {
        WorldSpaceUIServer.commands.register(event);
        WorldSpaceUIServer.commands.cacheDefaultUITargets();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        WorldSpaceUIServer.register();
        WorldSpaceUIServer.registerTestSuit();
        IntegrationManager.registerThaumcraft();
    }
}
