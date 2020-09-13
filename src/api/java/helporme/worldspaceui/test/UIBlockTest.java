package helporme.worldspaceui.test;

import helporme.worldspaceui.types.Vector3i;
import helporme.worldspaceui.ui.UI;

public class UIBlockTest extends UI
{
    public UIBlockTest() { }

    public UIBlockTest(Vector3i blockPos)
    {
        logger.info("UI constructed with: " + blockPos);
    }

    @Override
    public void onUI()
    {
//        WorldSpaceUI.logger.info("onUI call with mode: " + UILayout.getCurrentMode());
    }
}
