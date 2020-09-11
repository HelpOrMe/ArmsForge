package helporme.worldspaceui.test;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILayout;

public class UIBlockTest extends UI
{
    @Override
    public void onUI()
    {
        WorldSpaceUI.logger.debug("onUI call with mode: " + UILayout.getCurrentMode());
    }
}
