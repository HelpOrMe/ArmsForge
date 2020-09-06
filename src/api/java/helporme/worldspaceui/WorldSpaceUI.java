package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.event.TickHandler;
import helporme.worldspaceui.network.UINetwork;
import helporme.worldspaceui.ui.UI;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WorldSpaceUI
{
    public static Logger logger = LogManager.getLogger("WorldSpaceUI");
    public static UIMap map = new UIMap();
    @SideOnly(Side.SERVER)
    public static UINetwork network = new UINetwork();

    public static void register()
    {
       FMLCommonHandler.instance().bus().register(new TickHandler());
    }

    public static void registerUI(String uiClassName)
    {
        map.attachUIid(uiClassName);
    }

    @SideOnly(Side.SERVER)
    public static void openUI(UI ui, ITargetFilter targetFilter, boolean updateOnServer)
    {
        network.sendUIOpen(ui, targetFilter);
        map.uiLocations.put(ui.location, ui);
        if (updateOnServer)
        {
            map.serverUIUpdatePool.put(ui.getUniqueId(), ui);
        }
    }

    @SideOnly(Side.SERVER)
    public static void closeUI(int uiUniqueId, ITargetFilter targetFilter)
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
