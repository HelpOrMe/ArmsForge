package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILayout;

public class ServerTickHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        UILayout.tick(WorldSpaceUI.map.uiUpdatePool.values(), UICallMode.SERVER_TICK);
    }
}