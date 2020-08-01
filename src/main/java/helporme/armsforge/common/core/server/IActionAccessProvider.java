package helporme.armsforge.common.core.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IActionAccessProvider
{
    boolean canInteractWith(EntityPlayer player, World world, int x, int y, int z);
}
