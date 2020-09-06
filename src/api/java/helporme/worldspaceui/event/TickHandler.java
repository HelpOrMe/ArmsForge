package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILayout;
import helporme.worldspaceui.ui.UILayoutMode;

public class TickHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        for (UI ui : WorldSpaceUI.map.serverUIUpdatePool.values())
        {
            UILayout.beginUICalls(ui, UILayoutMode.SERVER_TICK);
            ui.onUI(event.side);
            UILayout.endUICalls();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        for (UI ui : WorldSpaceUI.map.clientUIUpdatePool.values())
        {
            UILayout.beginUICalls(ui, UILayoutMode.CLIENT_TICK);
            ui.onUI(event.side);
            UILayout.endUICalls();
        }
    }
}
