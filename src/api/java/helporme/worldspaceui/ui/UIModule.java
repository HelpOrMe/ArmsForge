package helporme.worldspaceui.ui;

public abstract class UIModule
{
    public final Transform transform = new Transform();

    public abstract void update(UI ui, UICallMode mode);
}
