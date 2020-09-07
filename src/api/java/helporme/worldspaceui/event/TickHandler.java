package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILayout;

import java.util.Collection;

public class TickHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        tick(WorldSpaceUI.map.serverUIUpdatePool.values(), UICallMode.SERVER_TICK);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        tick(WorldSpaceUI.map.clientUIUpdatePool.values(), UICallMode.CLIENT_TICK);
    }

    public static void tick(Collection<UI> pool, UICallMode mode)
    {
        for (UI ui : pool)
        {
            UILayout.beginUICalls(ui, mode);
            ui.onUI(mode);
            UILayout.endUICalls();
        }
    }
}
