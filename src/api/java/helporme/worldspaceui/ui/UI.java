package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;

public abstract class UI
{
    public final int uniqueId;

    public UI()
    {
        this(WorldSpaceUI.map.getNextUniqueUIId());
    }

    public UI(int uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    public void onUI(UICallMode mode) { }
}
