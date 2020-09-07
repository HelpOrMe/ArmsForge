package helporme.worldspaceui.ui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.WorldSpaceUI;

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

    @SideOnly(Side.SERVER)
    public void syncLocation()
    {
        WorldSpaceUI.network.syncUILocation(this);
    }

    @SideOnly(Side.SERVER)
    public void syncTarget()
    {
        WorldSpaceUI.network.syncUITarget(this);
    }

    @SideOnly(Side.SERVER)
    public void syncTransform()
    {
        WorldSpaceUI.network.syncUITransform(this);
    }
}
