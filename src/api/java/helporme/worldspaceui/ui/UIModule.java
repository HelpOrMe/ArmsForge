package helporme.worldspaceui.ui;

import helporme.worldspaceui.types.Transform;

public abstract class UIModule
{
    public final Transform transform = new Transform();

    public abstract void update(UI ui, UICallMode mode);
}
