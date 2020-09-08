package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.event.TickHandler;
import helporme.worldspaceui.event.WorldRenderHandler;
import helporme.worldspaceui.network.ITargetFilter;
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
    public static void openUI(UI ui, ITargetFilter targetFilter)
    {
        network.sendUIOpen(ui, targetFilter);
        map.locationToUISet.put(ui.location, ui.uniqueId);
        map.serverUIUpdatePool.put(ui.uniqueId, ui);
    }

    @SideOnly(Side.SERVER)
    public static void closeUI(int uiUniqueId, ITargetFilter targetFilter)
    {
        network.sendUIClose(uiUniqueId, targetFilter);
        map.locationToUISet.remove(map.serverUIUpdatePool.get(uiUniqueId).location, uiUniqueId);
        map.serverUIUpdatePool.remove(uiUniqueId);
    }

    @SideOnly(Side.CLIENT)
    public static void openUI(UI ui)
    {
        map.renderPool.put(ui.uniqueId, ui);
        map.clientUIUpdatePool.put(ui.uniqueId, ui);
    }

    @SideOnly(Side.CLIENT)
    public static void closeUI(int uiUniqueId)
    {
        map.renderPool.remove(uiUniqueId);
        map.clientUIUpdatePool.remove(uiUniqueId);
    }
}
