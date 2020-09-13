package helporme.worldspaceui.network.targets;

import helporme.worldspaceui.ui.UILocation;
import net.minecraft.entity.player.EntityPlayerMP;

public interface ITargetFilter
{
    EntityPlayerMP[] filterPlayers(UILocation location, EntityPlayerMP... players);
}
