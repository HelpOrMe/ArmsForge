package helporme.armsforge.common.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.common.core.proxy.IProxy;
import helporme.armsforge.common.core.proxy.ProxyInfo;
import helporme.armsforge.common.core.tab.ArmsForgeTab;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = Version.modid, useMetadata = true)
public class ArmsForge
{
    public static final CreativeTabs tab = new ArmsForgeTab();

    @SidedProxy(serverSide = ProxyInfo.serverProxy, clientSide = ProxyInfo.clientProxy)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
