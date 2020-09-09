package helporme.worldspaceui.test;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.types.Vector3i;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UICallMode;
import helporme.worldspaceui.ui.UILocation;
import helporme.worldspaceui.ui.UITargetBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class UIBlockTest extends UI
{
    public UIBlockTest(World world, Vector3i blockPos)
    {
        this(world.provider.dimensionId, blockPos);
    }

    public UIBlockTest(int dimension, Vector3i blockPos)
    {
        super(new UILocation(blockPos, dimension), new UITargetBlock(blockPos, dimension));
        WorldSpaceUI.logger.debug("Construct UIBlockTest");
    }

    @Override
    public void onUI(UICallMode mode)
    {
        WorldSpaceUI.logger.debug("onUI call with mode: " + mode);
    }

    @Override
    public boolean isValid()
    {
        return true;
    }
}
