package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.WorldSpaceUIServer;

public abstract class UI
{
    public final int uniqueId;
    public final Transform transform = new Transform();
    public final UILocation location;
    public final UITarget target;

    public UI()
    {
        this(new UILocation(), new UITarget());
    }

    public UI(UILocation location, UITarget target)
    {
        this(WorldSpaceUI.map.getNextUniqueUIId(), location, target);
    }

    public UI(int uniqueId, UILocation location, UITarget target)
    {
        this.uniqueId = uniqueId;
        this.location = location;
        this.target = target;
    }

    public void onUI(UICallMode mode) { }

    public void syncLocation()
    {
        WorldSpaceUIServer.network.syncUILocation(this);
    }

    public void syncTarget()
    {
        WorldSpaceUIServer.network.syncUITarget(this);
    }

    public void syncTransform()
    {
        WorldSpaceUIServer.network.syncUITransform(this);
    }
}
