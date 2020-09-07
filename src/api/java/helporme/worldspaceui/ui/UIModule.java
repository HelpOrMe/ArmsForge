package helporme.worldspaceui.ui;

import helporme.worldspaceui.ui.Transform;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UICallMode;

public abstract class UIModule
{
    public final Transform transform = new Transform();

    public abstract void update(UI ui, UICallMode mode);
}
