package helporme.worldspaceui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.event.ClientTickHandler;
import helporme.worldspaceui.event.WorldRenderHandler;
import helporme.worldspaceui.test.UIBlockTest;
import helporme.worldspaceui.ui.UI;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Client side UI manager
 */
@SideOnly(Side.CLIENT)
public class WorldSpaceUI
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUI");
    public static final UIMap map = new UIMap();

    /**
     * Register a client side event handlers.
     */
    public static void register()
    {
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register(new WorldRenderHandler());
    }

    /**
     * This will be removed in the future
     */
    public static void registerTestSuit()
    {
        registerUI(UIBlockTest.class);
    }

    /**
     * Register UI on the client side
     * @param uiClass Target UI class
     */
    public static void registerUI(Class<? extends UI> uiClass)
    {
        map.registerUI(uiClass);
    }

    /**
     * Create an UI in the client world.
     * You can close it with WorldSpaceUI.closeUI()
     * @param ui Target UI
     */
    public static void openUI(UI ui)
    {
        map.uiPool.put(ui.uniqueId, ui);
    }

    /**
     * Remove UI from the client world
     * @param uiUniqueId Target UI.uniqueId
     */
    public static void closeUI(int uiUniqueId)
    {
        map.uiPool.remove(uiUniqueId);
    }
}
