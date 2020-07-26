package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.block.model.registry.ModelRegistry;
import helporme.armsforge.common.block.registry.BlocksRegistry;
import helporme.armsforge.common.block.tiles.registry.TilesRegistry;

public class CommonProxy implements IProxy
{
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModelRegistry.createDefaultModelSuites();
        BlocksRegistry.registerBlocksFromModelSuites();
        TilesRegistry.registerTilesFromModelSuites();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
