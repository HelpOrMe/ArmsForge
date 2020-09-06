package helporme.worldspaceui;

import net.minecraft.entity.player.EntityPlayer;

public interface ITargetFilter
{
    double getRange();
    boolean canSendTo(EntityPlayer player);
}
