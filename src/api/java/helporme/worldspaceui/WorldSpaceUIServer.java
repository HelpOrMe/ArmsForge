package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import helporme.worldspaceui.commands.UICommands;
import helporme.worldspaceui.event.ServerTickHandler;
import helporme.worldspaceui.network.UINetwork;
import helporme.worldspaceui.network.targets.ITargetFilter;
import helporme.worldspaceui.test.UIBlockTest;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import helporme.worldspaceui.ui.synced.UISyncedCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Server side UI manager
 */
public class WorldSpaceUIServer
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUIServer");
    public static final UICommands commands = new UICommands();
    public static final UIMapServer map = new UIMapServer();
    public static final UINetwork network = new UINetwork();

    /**
     * Register UI packets and server even handlers
     */
    public static void register()
    {
        network.init();
        FMLCommonHandler.instance().bus().register(new ServerTickHandler());
    }

    /**
     * This will be removed in the future
     */
    public static void registerTestSuit()
    {
        registerUI(UIBlockTest.class);
    }

    /**
     * Register UI on the server side. You can get UIid from `WorldSpaceUIServer.map`
     * @param uiClass Target UI class
     */
    public static void registerUI(Class<? extends UI> uiClass)
    {
        map.attachUIid(uiClass.getName());
    }

    /**
     * Add UI to update pool and open UI on the target clients.
     * @param ui UI
     * @param location Chunk location,
     * @param targetFilter Target filter
     */
    public static void openUI(UI ui, UILocation location, ITargetFilter targetFilter)
    {
        network.sendUIOpen(ui, location, targetFilter);
        map.addLocation(ui.uniqueId, location);
        map.uiUpdatePool.put(ui.uniqueId, ui);
    }

    /**
     * Remove UI from the server
     * @param uiUniqueId Target UI.uniqueId
     * @param targetFilter Target filter
     */
    public static void closeUI(int uiUniqueId, ITargetFilter targetFilter)
    {
        network.sendUIClose(uiUniqueId, targetFilter);

        map.removeLocation(uiUniqueId);
        map.uiUpdatePool.remove(uiUniqueId);
    }
}
