package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;

public final class UILayout
{
    private static UICallMode mode;
    private static UI ui;

    public static void beginUICalls(UI ui, UICallMode mode)
    {
        if (UILayout.ui != null)
        {
            WorldSpaceUI.logger.error(
                    "Illegal access to beginUICalls. Current UI is \"" +
                    UILayout.ui.getClass().getName() + ":" + ui.getUniqueId() +
                    "\" with mode \"" + UILayout.mode + "\"");
            return;
        }

        UILayout.mode = mode;
        UILayout.ui = ui;
    }

    public static void endUICalls()
    {
        ui = null;
        mode = UICallMode.NONE;
    }
}
