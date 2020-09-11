package helporme.worldspaceui.network.targets;

import helporme.worldspaceui.ui.UI;
import net.minecraft.entity.player.EntityPlayerMP;

public interface ITargetFilter
{
    EntityPlayerMP[] filterPlayers(UI ui, EntityPlayerMP... players);
}
