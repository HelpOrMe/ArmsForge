package helporme.worldspaceui.ui;

public abstract class UIModule
{
    public final UITransform transform = new UITransform();

    public abstract void update(UI ui, UICallMode mode);
}
