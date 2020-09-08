package helporme.worldspaceui;

import com.google.common.collect.Multimap;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Map;

public class UIMapServer extends UIMap
{
    public Map<Integer, UI> uiUpdatePool;
    public Multimap<UILocation, Integer> locationToUISet;
    public Multimap<Integer, EntityPlayerMP> uiPlayers;
}
