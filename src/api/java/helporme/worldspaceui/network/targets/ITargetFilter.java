package helporme.worldspaceui.network.targets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.SERVER)
public interface ITargetFilter
{
    double getRange();
    boolean canSendTo(EntityPlayer player);
}
