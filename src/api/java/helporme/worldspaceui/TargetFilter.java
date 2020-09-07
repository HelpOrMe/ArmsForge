package helporme.worldspaceui;

import net.minecraft.entity.player.EntityPlayer;

public interface TargetFilter
{
    double getRange();
    boolean canSendTo(EntityPlayer player);
}
