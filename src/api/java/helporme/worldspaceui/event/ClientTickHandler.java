package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILayout;

public class ClientTickHandler
{
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        UILayout.tick(WorldSpaceUI.map.uiPool.values(), UICallMode.CLIENT_TICK);
    }
}
