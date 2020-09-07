package helporme.worldspaceui.ui;

import helporme.worldspaceui.types.Transform;

public abstract class UI
{
    public final UILocation location;
    public final Transform transform = new Transform();

    public UI(UILocation location)
    {
        this.location = location;
    }

    public void onUI(UICallMode mode) { }

    public abstract int getUniqueId();
}
