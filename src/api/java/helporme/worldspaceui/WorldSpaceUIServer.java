package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.commands.UICommands;
import helporme.worldspaceui.event.ServerTickHandler;
import helporme.worldspaceui.network.UINetwork;
import helporme.worldspaceui.network.targets.ITargetFilter;
import helporme.worldspaceui.test.UIBlockTest;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Server side UI manager
 */
@SideOnly(Side.SERVER)
public class WorldSpaceUIServer
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUIServer");
    /**
     * UI chat commands manager.
     * You can add your own action to `/ui` command
     */
    public static final UICommands commands = new UICommands();
    public static final UIMapServer map = new UIMapServer();
    public static final UINetwork network = new UINetwork();

    /**
     * Register UI packets and server event handlers
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
     * Register UI on the server side
     * @param uiClass Target UI class
     */
    public static void registerUI(Class<? extends UI> uiClass)
    {
        map.attachUIid(uiClass.getName());
    }

    /**
     * Add UI to the update pool, open and sync it on the target clients
     * @param ui UI
     * @param location UI location, used to resend UI to new users
     * @param range Packet send range
     * @param targetFilter Target filter
     */
    public static void openUI(UI ui, UILocation location, int range, ITargetFilter targetFilter)
    {
        network.sendUIOpen(ui, location, range, targetFilter);
        addUI(ui, location);
        network.syncUI(ui);
    }

    /**
     * Add UI to the update pool, open and sync it on the target clients
     * @param ui UI
     * @param location UI location, used to resend UI to new users
     * @param range Send packet range
     */
    public static void openUI(UI ui, UILocation location, int range)
    {
        network.sendUIOpen(ui, location, range);
        addUI(ui, location);
    }

    /**
     * Add UI to the update pool, but don't open it on the clients
     */
    protected static void addUI(UI ui, UILocation location)
    {
        map.addLocation(ui.uniqueId, location);
        map.uiPool.put(ui.uniqueId, ui);
    }

    /**
     * Remove UI from the server and close it on target clients
     * @param uiUniqueId Target UI.uniqueId
     * @param targetFilter Target filter
     */
    public static void closeUI(int uiUniqueId, ITargetFilter targetFilter)
    {
        network.sendUIClose(uiUniqueId, targetFilter);
        removeUI(uiUniqueId);
    }

    /**
     * Remove UI from the server and close it on clients
     * @param uiUniqueId Target UI.uniqueId
     */
    public static void closeUI(int uiUniqueId)
    {
        network.sendUIClose(uiUniqueId);
        removeUI(uiUniqueId);
    }

    /**
     * Remove UI from the update pool, but don't close it on the clients
     * @param uiUniqueId Target UI.uniqueId
     */
    protected static void removeUI(int uiUniqueId)
    {
        map.removeLocation(uiUniqueId);
        map.uiPool.remove(uiUniqueId);
    }
}
