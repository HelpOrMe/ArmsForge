package helporme.worldspaceui.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILayout;
import net.minecraftforge.client.event.RenderWorldEvent;

public class WorldRenderHandler
{
    @SubscribeEvent
    public void onWorldRender(RenderWorldEvent.Post event)
    {
        UILayout.tick(WorldSpaceUI.map.uiPool.values(), UICallMode.RENDER);
    }
}
