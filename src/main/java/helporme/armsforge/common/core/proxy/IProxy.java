package helporme.armsforge.common.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface IProxy
{
    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void serverInit(FMLServerStartingEvent event);

    void postInit(FMLPostInitializationEvent event);
}
