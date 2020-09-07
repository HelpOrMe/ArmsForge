package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UICallMode;
import net.minecraftforge.client.event.RenderWorldEvent;

public class WorldRenderHandler
{
    @SubscribeEvent
    public void onWorldRender(RenderWorldEvent.Post event)
    {
        TickHandler.tick(WorldSpaceUI.map.clientUIUpdatePool.values(), UICallMode.RENDER);
    }
}
