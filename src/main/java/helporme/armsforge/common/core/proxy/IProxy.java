package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{
    @EventHandler
    void preInit(FMLPreInitializationEvent event);

    @EventHandler
    void init(FMLInitializationEvent event);

    @EventHandler
    void postInit(FMLPostInitializationEvent event);
}
