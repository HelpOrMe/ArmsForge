package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.block.BlocksRegistry;
import helporme.armsforge.common.block.tiles.TilesRegistry;

public class CommonProxy implements IProxy
{
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BlocksRegistry.RegisterDefaultBlocks();
        TilesRegistry.RegisterDefaultTiles();
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
