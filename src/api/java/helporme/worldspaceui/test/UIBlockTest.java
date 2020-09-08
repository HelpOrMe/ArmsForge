package helporme.worldspaceui.test;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.types.Vector3i;
import helporme.worldspaceui.ui.UI;
import net.minecraft.world.World;

public class UIBlockTest extends UI
{
    public UIBlockTest(World world, Vector3i blockPosition)
    {
        super();
        WorldSpaceUI.logger.debug("Construct UIBlockTest");
    }
}
