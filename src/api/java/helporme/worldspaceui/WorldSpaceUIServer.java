package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import helporme.worldspaceui.commands.UICommands;
import helporme.worldspaceui.event.ServerTickHandler;
import helporme.worldspaceui.network.UINetwork;
import helporme.worldspaceui.network.targets.ITargetFilter;
import helporme.worldspaceui.test.UIBlockTest;
import helporme.worldspaceui.ui.UI;
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
     * @param targetFilter Target filter
     */
    public static void openUI(UI ui, ITargetFilter targetFilter)
    {
        network.sendUIOpen(ui, targetFilter);
        network.syncUILocation(ui);
        network.syncUITarget(ui);
        network.syncUITransform(ui);

        map.locationToUISet.put(ui.location, ui.uniqueId);
        map.uiUpdatePool.put(ui.uniqueId, ui);
    }

    public static void closeUI(int uiUniqueId, ITargetFilter targetFilter)
    {
        network.sendUIClose(uiUniqueId, targetFilter);
        map.locationToUISet.remove(map.uiUpdatePool.get(uiUniqueId).location, uiUniqueId);
        map.uiUpdatePool.remove(uiUniqueId);
    }
}
