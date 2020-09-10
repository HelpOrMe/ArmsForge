package helporme.worldspaceui.test;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UICallMode;

public class UIBlockTest extends UI
{
    @Override
    public void onUI(UICallMode mode)
    {
        WorldSpaceUI.logger.debug("onUI call with mode: " + mode);
    }
}
