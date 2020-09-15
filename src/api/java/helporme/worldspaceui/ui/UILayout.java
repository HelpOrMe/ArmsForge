package helporme.worldspaceui.ui;

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
