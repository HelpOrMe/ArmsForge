package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;

import java.util.Collection;

public final class UILayout
{
    private static UI ui;
    private static UICallMode mode;

    public static void tick(Collection<UI> pool, UICallMode mode)
    {
        for (UI ui : pool)
        {
            UILayout.beginUICalls(ui, mode);
            ui.onUI();
            UILayout.endUICalls();
        }
    }

    public static void beginUICalls(UI ui, UICallMode mode)
    {
        if (UILayout.ui != null)
        {
            WorldSpaceUI.logger.error(
                    "Illegal access to beginUICalls. Current UI is \"" +
                    UILayout.ui.getClass().getName() + ":" + ui.uniqueId +
                    "\" with mode \"" + UILayout.mode + "\"");
            return;
        }

        UILayout.ui = ui;
        UILayout.mode = mode;
    }

    public static UI getCurrentUI()
    {
        return ui;
    }

    public static UICallMode getCurrentMode()
    {
        return mode;
    }

    public static void endUICalls()
    {
        ui = null;
        mode = UICallMode.NONE;
    }
}
