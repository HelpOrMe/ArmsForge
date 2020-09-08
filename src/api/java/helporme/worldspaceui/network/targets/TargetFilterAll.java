package helporme.worldspaceui.network.targets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.SERVER)
public class TargetFilterAll implements ITargetFilter
{
    public double range;

    public TargetFilterAll(double range)
    {
        this.range = range;
    }

    @Override
    public double getRange()
    {
        return range;
    }

    @Override
    public boolean canSendTo(EntityPlayer player)
    {
        return true;
    }
}
