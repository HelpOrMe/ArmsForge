package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.event.TickHandler;
import helporme.worldspaceui.event.WorldRenderHandler;
import helporme.worldspaceui.network.UINetwork;
import helporme.worldspaceui.ui.UI;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WorldSpaceUI
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUI");
    public static final UIMap map = new UIMap();

    @SideOnly(Side.SERVER)
    public static final UINetwork network = new UINetwork();

    public static void register()
    {
        FMLCommonHandler.instance().bus().register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(new WorldRenderHandler());
    }

    public static void registerUI(String uiClassName)
    {
        map.attachUIid(uiClassName);
    }

    @SideOnly(Side.SERVER)
    public static void openUI(UI ui, TargetFilter targetFilter, boolean updateOnServer)
    {
        network.sendUIOpen(ui, targetFilter);
        map.uiLocations.put(ui.location, ui);
        if (updateOnServer)
        {
            map.serverUIUpdatePool.put(ui.getUniqueId(), ui);
        }
    }

    @SideOnly(Side.SERVER)
    public static void closeUI(int uiUniqueId, TargetFilter targetFilter)
    {
        network.sendUIClose(uiUniqueId, targetFilter);
        map.serverUIUpdatePool.remove(uiUniqueId);
    }

    @SideOnly(Side.CLIENT)
    public static void openUI(UI ui)
    {
        map.renderPool.put(ui.getUniqueId(), ui);
        map.clientUIUpdatePool.put(ui.getUniqueId(), ui);
    }

    @SideOnly(Side.CLIENT)
    public static void closeUI(int uiUniqueId)
    {
        map.renderPool.remove(uiUniqueId);
        map.clientUIUpdatePool.remove(uiUniqueId);
    }
}
