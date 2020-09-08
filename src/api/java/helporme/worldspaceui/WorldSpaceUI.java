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

@SideOnly(Side.CLIENT)
public class WorldSpaceUI
{
    public static final Logger logger = LogManager.getLogger("WorldSpaceUI");
    public static final UIMapClient map = new UIMapClient();

    public static void register()
    {
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register(new WorldRenderHandler());
    }

    public static void registerTestSuit()
    {
        registerUI(UIBlockTest.class);
    }

    public static void registerUI(Class<? extends UI> uiClass)
    {
        map.attachUIid(uiClass.getName());
    }

    public static void openUI(UI ui)
    {
        map.renderPool.put(ui.uniqueId, ui);
        map.uiUpdatePool.put(ui.uniqueId, ui);
    }

    public static void closeUI(int uiUniqueId)
    {
        map.renderPool.remove(uiUniqueId);
        map.uiUpdatePool.remove(uiUniqueId);
    }
}
