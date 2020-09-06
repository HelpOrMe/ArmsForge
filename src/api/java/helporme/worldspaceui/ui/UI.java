package helporme.worldspaceui.ui;

import cpw.mods.fml.relauncher.Side;

public abstract class UI
{
    public UILocation location;

    public UI(UILocation location)
    {
        this.location = location;
    }

    public void onUI(Side side) { }

    public abstract int getUniqueId();
}
