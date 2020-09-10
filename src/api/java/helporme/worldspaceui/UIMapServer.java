package helporme.worldspaceui;

import com.google.common.collect.Multimap;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Map;

public class UIMapServer extends UIMap
{
    public Multimap<UILocation, Integer> locationToUIList;
    public Map<Integer, UILocation> uiToLocation;
    public Map<Integer, UI> uiUpdatePool;
    public Multimap<Integer, EntityPlayerMP> uiPlayers;

    public void addLocation(int uiUniqueId, UILocation location)
    {
        locationToUIList.put(location, uiUniqueId);
        uiToLocation.put(uiUniqueId, location);
    }

    public void removeLocation(int uiUniqueId)
    {
        UILocation location = uiToLocation.get(uiUniqueId);
        uiToLocation.remove(uiUniqueId);
        locationToUIList.remove(location, uiUniqueId);
    }
}
