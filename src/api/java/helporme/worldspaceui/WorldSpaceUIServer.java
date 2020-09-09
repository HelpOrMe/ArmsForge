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

public class WorldSpaceUIServer
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUIServer");
    public static final UICommands commands = new UICommands();
    public static final UIMapServer map = new UIMapServer();
    public static final UINetwork network = new UINetwork();

    public static void register()
    {
        network.init();
        FMLCommonHandler.instance().bus().register(new ServerTickHandler());
    }

    public static void registerTestSuit()
    {
        registerUI(UIBlockTest.class);
    }

    public static void registerUI(Class<? extends UI> uiClass)
    {
        map.attachUIid(uiClass.getName());
    }

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
